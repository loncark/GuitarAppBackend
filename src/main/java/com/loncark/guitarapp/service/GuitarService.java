package com.loncark.guitarapp.service;

import com.loncark.guitarapp.dto.GuitarDTO;
import com.loncark.guitarapp.model.Guitar;

import java.util.List;
import java.util.Optional;

public interface GuitarService {

    List<GuitarDTO> findAll();

    Optional<GuitarDTO> findByCode(String code);

    Optional<GuitarDTO> save(Guitar guitar);

    Optional<GuitarDTO> update(Guitar updatedGuitar);

    void deleteByCode(String code);
}
