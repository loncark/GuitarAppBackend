package com.loncark.guitarapp.service;

import com.loncark.guitarapp.model.RefreshToken;
import com.loncark.guitarapp.model.UserInfo;
import com.loncark.guitarapp.repository.RefreshTokenRepository;
import com.loncark.guitarapp.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenService {

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;
    @Autowired
    private UserInfoRepository userInfoRepository;

    public RefreshToken createRefreshToken(String username) throws ClassNotFoundException {
        Optional<UserInfo> uInfo = userInfoRepository.findByName(username);
        if(uInfo.isPresent()) {
            RefreshToken rToken = RefreshToken.builder()
                    .userInfo(uInfo.get())
                    .token(UUID.randomUUID().toString())
                    .expiryDate(Instant.now().plusMillis((long)1000 * 60 * 60))
                    .build();
            return refreshTokenRepository.save(rToken);
        }
        else throw new ClassNotFoundException("Optional value is not present.");
    }

    public Optional<RefreshToken> findByToken(String rToken) {
        return refreshTokenRepository.findByToken(rToken);
    }

    public RefreshToken verifyExpiration(RefreshToken rToken) {
        if(rToken.getExpiryDate().compareTo(Instant.now()) < 0) {
            refreshTokenRepository.delete(rToken);
            throw new RuntimeException("Token " + rToken.getToken() + "has expired. Please make a new Sign-In request.");
        }
        return rToken;
    }
}
