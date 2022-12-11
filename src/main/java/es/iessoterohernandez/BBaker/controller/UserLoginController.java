package es.iessoterohernandez.BBaker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.iessoterohernandez.BBaker.DTO.UserDTO;
import es.iessoterohernandez.BBaker.model.AuthRequest;
import es.iessoterohernandez.BBaker.model.ChangeRoleRequest;
import es.iessoterohernandez.BBaker.model.User;
import es.iessoterohernandez.BBaker.service.JwtService;
import es.iessoterohernandez.BBaker.service.UserService;

@RestController
@RequestMapping(path = "/api/login")
public class UserLoginController {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;
    
    @PostMapping
    public String generateJwtToken(@RequestBody AuthRequest authRequest) throws Exception {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword())
            );
        return jwtService.generateToken(authRequest.getEmail());
    }

    @PostMapping("getuser")
    public UserDTO getUser(@RequestBody AuthRequest authr) throws Exception {
        return userService.findByEmail(authr.getEmail());
    }

    @PostMapping("getusers")
    public List <User> getUsers() throws Exception {
        return userService.findAll();
    }

    @PostMapping("role")
    public User changeRole(@RequestBody ChangeRoleRequest crl) throws Exception {
        return userService.changeRole(crl.getEmail(),crl.getRole());
    }
}
