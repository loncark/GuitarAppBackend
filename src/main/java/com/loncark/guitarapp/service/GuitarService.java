package com.loncark.guitarapp.service;

import com.loncark.guitarapp.dto.GuitarDTO;
import com.loncark.guitarapp.model.guitar.Guitar;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

public interface GuitarService {

    List<GuitarDTO> findAll();

    Optional<GuitarDTO> findByCode(String code);

    Optional<GuitarDTO> save(Guitar guitar);

    void deleteByCode(String code);

    Supplier<Number> fetchGuitarCount();
}
