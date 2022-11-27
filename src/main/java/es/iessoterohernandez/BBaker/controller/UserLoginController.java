package es.iessoterohernandez.BBaker.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.iessoterohernandez.BBaker.model.AuthRequest;
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
    public Map generateJwtToken(@RequestBody AuthRequest authRequest) throws Exception {
        String email = authRequest.getEmail();
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword())
            );
        HashMap<String, String> jwtYrole = new HashMap<String, String>();
        jwtYrole.put("token", jwtService.generateToken(authRequest.getEmail()));
        jwtYrole.put("role",userService.findByEmail(email).name());
        return jwtYrole;
    }

   // @PostMapping("role")
    //public int changeRole(@RequestBody ChangeRoleRequest changeRoleRequest) throws Exception {
     //   return userService.changeRole(changeRoleRequest.getEmail(),changeRoleRequest.getRole());
    //}
}
