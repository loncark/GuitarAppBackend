package com.loncark.guitarapp;

import com.loncark.guitarapp.dto.GuitarDTO;
import com.loncark.guitarapp.service.GuitarService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
