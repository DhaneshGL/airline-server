package com.dhanesh.social.services;

import com.dhanesh.social.models.PasswordResetToken;
import com.dhanesh.social.repositories.PasswordResetTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PasswordResetTokenService {

    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;

    public Optional<PasswordResetToken> getTokenByUserIdAndToken(Long userId, String token) {
        return passwordResetTokenRepository.findByUserIdAndToken(userId, token);
    }

    public Optional<PasswordResetToken> getTokenByEmail(String email) {
        return passwordResetTokenRepository.findByEmail(email);
    }

    public PasswordResetToken createToken(PasswordResetToken token) {
        return passwordResetTokenRepository.save(token);
    }

    public void deleteTokenByUserId(Long userId) {
        passwordResetTokenRepository.deleteByUserId(userId);
    }
}