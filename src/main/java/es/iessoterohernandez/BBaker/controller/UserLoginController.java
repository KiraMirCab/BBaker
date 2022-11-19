package es.iessoterohernandez.BBaker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.iessoterohernandez.BBaker.model.AuthRequest;
import es.iessoterohernandez.BBaker.service.JwtService;

@RestController
//@RequestMapping(path = "/api/login")
public class UserLoginController {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;
    
    @GetMapping("/api")
    public String welcome() {
        return "Yeeeeeeey!";
    }

    @PostMapping("/api/login")
    public String generateJwtToken(@RequestBody AuthRequest authRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword())
            );
        } catch (Exception e) {
            throw new Exception("inavalid username/password");
        }
        return jwtService.generateToken(authRequest.getEmail());
    }
}
