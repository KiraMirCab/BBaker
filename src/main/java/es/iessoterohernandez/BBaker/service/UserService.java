package es.iessoterohernandez.BBaker.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import es.iessoterohernandez.BBaker.DTO.UserDTO;
import es.iessoterohernandez.BBaker.model.ConfirmationToken;
import es.iessoterohernandez.BBaker.model.User;
import es.iessoterohernandez.BBaker.model.UserRole;
import es.iessoterohernandez.BBaker.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {

    private final static Logger LOGGER = LoggerFactory.getLogger(EmailService.class);

    @Autowired
    UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    ConfirmationTokenService confirmationTokenService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User with emal " + email + " not found"));
    }

    public String signUpUser(User user) {
        Optional<User> dbUser = userRepository.findByEmail(user.getEmail());
        boolean userExists = dbUser.isPresent();

        if (!userExists) {
            LOGGER.info("User is not there!");
            String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
            user.setPassword(encodedPassword);

            userRepository.save(user);
        } else {
            LOGGER.info("User is there and isn't confirmed yet!");
            Long userId = dbUser.map(User::getId).orElse(null);
            user.setId(userId);

            if (user.isEnabled()) {
                LOGGER.info("User is there and IS CONFIRMED ALREADY!");
                throw new IllegalStateException("email already taken");
            }
        }

        String token = UUID.randomUUID().toString();

        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                user);
        LOGGER.info("creating a new Token: " + confirmationToken.getToken());
        confirmationTokenService.saveConfirmationToken(confirmationToken);

        return token;
    }

    public int enableUser(String email) {
        return userRepository.enableUser(email);
    }

    public UserDTO findByEmail(String email) {
        User dbUser = userRepository.findByEmail(email).orElse(null);
        UserDTO userDTO = new UserDTO(dbUser.getId(), dbUser.getEmail(), dbUser.getUserRole(), dbUser.getFirstName(), dbUser.getLastName());
        return userDTO;
    }

    public User changeRole(String email, UserRole role) throws Exception {
        Optional<User> dbUser = userRepository.findByEmail(email);
        boolean userExists = dbUser.isPresent();
        User user = dbUser.orElse(null);
        if (userExists) {
            LOGGER.info("Setting a new role " + role + "to the user" + email);
            user.setUserRole(role);
        } else {
            LOGGER.info("User with this email does not exist!");
            throw new IllegalStateException("email not registered");
        } 
        return userRepository.save(user);
    }

    public List <User> findAll() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id);
    }
}
