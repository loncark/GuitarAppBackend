package com.loncark.guitarapp;

import com.loncark.guitarapp.dto.GuitarDTO;
import com.loncark.guitarapp.model.guitar.Guitar;
import com.loncark.guitarapp.service.GuitarService;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/")
public class GuitarController {

    private final GuitarService guitarService;

    public GuitarController(GuitarService guitarService, MeterRegistry registry) {
        this.guitarService = guitarService;

        // custom Prometheus metric configuration
        Gauge.builder("guitarapp.guitarcount", guitarService.fetchGuitarCount()).
                tag("version","v1").
                description("Number of guitars in database.").
                register(registry);
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

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public GuitarDTO save(@Valid @RequestBody final Guitar guitar) {
        return guitarService.save(guitar)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.CONFLICT, "A guitar with the same code already exists"));
    }

    @PutMapping
    public GuitarDTO update(@Valid @RequestBody final Guitar guitar) {
        return guitarService.save(guitar)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "A guitar was not found by that id"));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{code}")
    public void delete(@PathVariable String code){
        guitarService.deleteByCode(code);
    }
}
