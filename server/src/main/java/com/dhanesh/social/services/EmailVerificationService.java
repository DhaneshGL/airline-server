package com.dhanesh.social.services;

import com.dhanesh.social.models.EmailVerification;
import com.dhanesh.social.repositories.EmailVerificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmailVerificationService {

    @Autowired
    private EmailVerificationRepository emailVerificationRepository;

    public Optional<EmailVerification> getVerificationByUserIdAndCode(Long userId, String code) {
        return emailVerificationRepository.findByUserIdAndCode(userId, code);
    }

    public EmailVerification createVerification(EmailVerification verification) {
        return emailVerificationRepository.save(verification);
    }

    public void deleteVerificationByUserId(Long userId) {
        emailVerificationRepository.deleteByUserId(userId);
    }
}