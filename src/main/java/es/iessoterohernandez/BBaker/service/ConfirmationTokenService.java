package es.iessoterohernandez.BBaker.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.iessoterohernandez.BBaker.model.ConfirmationToken;
import es.iessoterohernandez.BBaker.repository.ConfirmationTokenRepository;

@Service
public class ConfirmationTokenService {
    
    @Autowired
    ConfirmationTokenRepository confirmationTokenRepository;
    
    public void saveConfirmationToken(ConfirmationToken token) {
        confirmationTokenRepository.save(token);
    }

    public Optional<ConfirmationToken> getToken(String token) {
        System.out.println("BUSCANDO TOKEN EN LA LISTA");
        return confirmationTokenRepository.findByToken(token);
    }

    public int setConfirmedAt(String token) {
        return confirmationTokenRepository.updateConfirmedAt(
                token, LocalDateTime.now());
    }
}
