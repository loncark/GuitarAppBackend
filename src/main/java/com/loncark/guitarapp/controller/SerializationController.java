package com.loncark.guitarapp.controller;

import com.loncark.guitarapp.service.SerializationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class SerializationController {

    @Autowired
    private SerializationService serializationService;

    @GetMapping("/serialize/good")
    public String serializeUser() { return serializationService.serializeUserInfo(); }

    @GetMapping("/serialize/bad")
    public String serializeMaliciousUser() throws IOException { return serializationService.serializeMaliciousUserInfo(); }

    @GetMapping("/deserialize/safe")
    public String deserializeSafely() throws IOException { return serializationService.deserializeUserInfoSafely(); }

    @GetMapping("/deserialize/unsafe")
    public String deserializeUnsafely() throws IOException { return serializationService.deserializeUserInfoUnsafely(); }
}
