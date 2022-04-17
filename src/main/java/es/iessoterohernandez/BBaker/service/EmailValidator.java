package es.iessoterohernandez.BBaker.service;

import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

@Service
public class EmailValidator implements Predicate<String>{
    
    public static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@(.+)$";
    
    @Override
    public boolean test(String email) {
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
