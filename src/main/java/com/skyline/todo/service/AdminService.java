package com.skyline.todo.service;

import com.skyline.todo.model.user.Role;
import com.skyline.todo.model.user.User;
import com.skyline.todo.repository.TokenRepository;
import com.skyline.todo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;

    public void upgradeUser(String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow();
        user.setRole(Role.ADMIN);
        userRepository.save(user);
    }

    public void banUser(String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow();
        user.setBanned(true);
        revokeAllUserTokens(user);
        userRepository.save(user);
    }

    public void unbanUser(String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow();
        user.setBanned(false);
        userRepository.save(user);
    }

    private void revokeAllUserTokens(User user) {
        var validUserToken = tokenRepository.findAllValidTokenByUser(user.getId());
        if(validUserToken.isEmpty()) {
            return;
        }
        validUserToken.forEach(tokenRepository::delete);
    }
}
