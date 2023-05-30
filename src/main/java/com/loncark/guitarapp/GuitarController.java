package com.loncark.guitarapp;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class GuitarController {

    @RequestMapping
    public String HelloWorld() {
        return "Hello world";
    }

}
