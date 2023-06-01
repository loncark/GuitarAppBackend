package com.loncark.guitarapp;

import com.loncark.guitarapp.dto.GuitarDTO;
import com.loncark.guitarapp.service.GuitarService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/")
public class GuitarController {

    private final GuitarService guitarService;

    public GuitarController(GuitarService guitarService) {
        this.guitarService = guitarService;
    }

    @RequestMapping
    public List<GuitarDTO> getAll() {
        return guitarService.findAll();
    }

    @GetMapping("{code}")
    public GuitarDTO getByCode(@PathVariable final String code) {
        return guitarService.findByCode(code)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Guitar was not found by that code")
                );
    }

}
