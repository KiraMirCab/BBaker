package es.iessoterohernandez.BBaker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.iessoterohernandez.BBaker.model.RegistrationRequest;
import es.iessoterohernandez.BBaker.service.RegistrationService;

@RestController
@RequestMapping(path = "/api/registration")
public class UserRegistrationController {

    @Autowired
    RegistrationService registrationService;

    @PostMapping
    public String register(@RequestBody RegistrationRequest request) {
        return registrationService.register(request);
    }
    
    @GetMapping
    public String confirm(@RequestParam("token") String token) {
        return registrationService.confirmToken(token);
    }
}
