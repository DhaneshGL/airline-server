package com.dhanesh.social.repositories;

import com.dhanesh.social.models.EmailVerification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmailVerificationRepository extends JpaRepository<EmailVerification, Long> {
    Optional<EmailVerification> findByUserIdAndCode(Long userId, String code);
    void deleteByUserId(Long userId);
}