package com.loncark.guitarapp.controller;

import com.loncark.guitarapp.dto.AuthRequest;
import com.loncark.guitarapp.dto.JwtResponse;
import com.loncark.guitarapp.dto.RefreshTokenRequest;
import com.loncark.guitarapp.model.RefreshToken;
import com.loncark.guitarapp.model.UserInfo;
import com.loncark.guitarapp.service.AuthService;
import com.loncark.guitarapp.service.JwtService;
import com.loncark.guitarapp.service.RefreshTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authManager;
    @Autowired
    private RefreshTokenService refreshTokenService;

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public String addNewUser(@RequestBody UserInfo userInfo) {
        return authService.addUser(userInfo);
    }

    @PostMapping("/login")
    public JwtResponse authenticateAndReturnToken(@RequestBody AuthRequest authRequest) throws ClassNotFoundException {
        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(),
                        authRequest.getPassword()));

        if (authentication.isAuthenticated()) {
            return JwtResponse.builder()
                    .accessToken(jwtService.generateToken(authRequest.getUsername()))
                    .refreshToken(refreshTokenService.createRefreshToken(authRequest.getUsername()).getToken())
                    .build();
        } else throw new UsernameNotFoundException("Bad Credentials, authentication failed.");
    }

    @PostMapping("/refresh")
    public JwtResponse refreshToken(@RequestBody RefreshTokenRequest rtRequest) {
        return refreshTokenService.findByToken(rtRequest.getRefreshToken())
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUserInfo)
                .map( userInfo -> {
                            String accessToken = jwtService.generateToken(userInfo.getName());
                            return JwtResponse.builder()
                                    .accessToken(accessToken)
                                    .refreshToken(rtRequest.getRefreshToken())
                                    .build();
                }).orElseThrow(()->new RuntimeException("Refresh token not in database!"));
    }

}
