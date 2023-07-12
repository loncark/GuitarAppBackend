package com.loncark.guitarapp.controller;

import com.loncark.guitarapp.model.UserInfo;
import com.loncark.guitarapp.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/new")
    public String addNewUser(@RequestBody UserInfo userInfo) {
        return authService.addUser(userInfo);
    }
}
