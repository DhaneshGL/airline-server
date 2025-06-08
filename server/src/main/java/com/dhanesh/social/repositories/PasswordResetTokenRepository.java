package com.dhanesh.social.repositories;

import com.dhanesh.social.models.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {
    Optional<PasswordResetToken> findByUserIdAndToken(Long userId, String token);
    Optional<PasswordResetToken> findByEmail(String email);
    void deleteByUserId(Long userId);
}