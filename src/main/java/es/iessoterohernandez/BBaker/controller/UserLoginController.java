package es.iessoterohernandez.BBaker.controller;

import java.util.List;
import java.util.Optional;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
    public Optional <User> getUser(@RequestBody String email) throws Exception {
        return userService.findByEmail(email);
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
