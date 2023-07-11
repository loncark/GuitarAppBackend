package com.loncark.guitarapp;

import com.loncark.guitarapp.dto.GuitarDTO;
import com.loncark.guitarapp.model.Guitar;
import com.loncark.guitarapp.service.GuitarService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
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

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public GuitarDTO save(@Valid @RequestBody final Guitar guitar) {
        return guitarService.save(guitar)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.CONFLICT, "A guitar with the same code already exists"));
    }

    @PutMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public GuitarDTO update(@Valid @RequestBody final Guitar guitar) {
        return guitarService.update(guitar)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "A guitar was not found by that id"));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{code}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public void delete(@PathVariable String code){
        guitarService.deleteByCode(code);
    }

}
