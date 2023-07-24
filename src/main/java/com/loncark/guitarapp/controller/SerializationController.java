package com.loncark.guitarapp.controller;

import com.loncark.guitarapp.service.SerializationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SerializationController {

    @Autowired
    private SerializationService serializationService;

    @GetMapping("/serialize")
    public String serializeUser() { return serializationService.serializeUserInfo(); }

    @GetMapping("/serializeMalicious")
    public String serializeMaliciousUser() { return serializationService.serializeMaliciousUserInfo(); }

    @GetMapping("/deserialize")
    public String deserialize() { return serializationService.deserializeUserInfo(); }
}
