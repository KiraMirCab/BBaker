package es.iessoterohernandez.BBaker.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.iessoterohernandez.BBaker.model.ConfirmationToken;
import es.iessoterohernandez.BBaker.repository.ConfirmationTokenRepository;

@Service
public class ConfirmationTokenService {
    
    private final static Logger LOGGER = LoggerFactory.getLogger(ConfirmationTokenService.class);

    @Autowired
    ConfirmationTokenRepository confirmationTokenRepository;
    
    public void saveConfirmationToken(ConfirmationToken token) {
        confirmationTokenRepository.save(token);
        LOGGER.info("ConfirmationToken guardado: " + token.getToken());
    }

    public Optional<ConfirmationToken> getToken(String token) {
        LOGGER.info("Buscando el token en la lista: " + token);
        return confirmationTokenRepository.findByToken(token);
    }

    public int setConfirmedAt(String token) {
        LOGGER.info("Estableciendo confirmedAt para el token: " + token);
        return confirmationTokenRepository.updateConfirmedAt(
                token, LocalDateTime.now());
    }
}
