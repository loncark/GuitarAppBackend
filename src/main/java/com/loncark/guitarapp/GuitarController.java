package com.loncark.guitarapp;

import com.loncark.guitarapp.dto.GuitarDTO;
import com.loncark.guitarapp.model.Guitar;
import com.loncark.guitarapp.service.GuitarService;
import jakarta.jms.Destination;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/")
public class GuitarController {

    private final GuitarService guitarService;
    private final JmsTemplate jmsTemplate;

    public GuitarController(GuitarService guitarService, JmsTemplate jmsTemplate) {
        this.guitarService = guitarService;
        this.jmsTemplate = jmsTemplate;
        this.jmsTemplate.setDefaultDestinationName("GuitarAppQueue");
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

        jmsTemplate.convertAndSend(
                "Saving a guitar to the database: " +
                        guitar.getName());

        return guitarService.save(guitar)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.CONFLICT, "A guitar with the same code already exists"));
    }

    @PutMapping
    public GuitarDTO update(@Valid @RequestBody final Guitar guitar) {

        jmsTemplate.convertAndSend("Updated a guitar with code: " + guitar.getCode());

        return guitarService.save(guitar)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "A guitar was not found by that id"));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{code}")
    public void delete(@PathVariable String code){
        jmsTemplate.convertAndSend("Deleted a guitar with code: " + code);
        guitarService.deleteByCode(code);
    }

}
