package com.loncark.guitarapp.service;

import com.loncark.guitarapp.model.UserInfo;
import com.loncark.guitarapp.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private UserInfoRepository repository;

    public String addUser(UserInfo userInfo) {
        userInfo.setPassword(encoder.encode(userInfo.getPassword()));
        Optional<UserInfo> response = repository.save(userInfo);
        return "New user saved: " + response.toString();
    }
}
