package es.iessoterohernandez.BBaker.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/login")
public class UserLoginController {
    
    @GetMapping
    public String welcome() {
        return "Yeeeeeeey!";
    }
}
