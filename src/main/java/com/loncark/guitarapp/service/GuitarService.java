package com.loncark.guitarapp.service;

import com.loncark.guitarapp.dto.GuitarDTO;
import com.loncark.guitarapp.model.Guitar;

import java.util.List;
import java.util.Optional;

public interface GuitarService {

    List<GuitarDTO> findAll();

    Optional<GuitarDTO> findByCode(String code);

    Optional<GuitarDTO> save(Guitar guitar);

    void deleteByCode(String code);
}
