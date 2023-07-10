package com.loncark.guitarapp.repository;

import com.loncark.guitarapp.model.Guitar;

import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

@Transactional
public interface GuitarRepository {

    Set<Guitar> findAll();

    Optional<Guitar> findByCode(String code);

    void deleteByCode(String code);

    Optional<Guitar> save(Guitar guitar);

    Optional<Guitar> update(Guitar updatedGuitar);
}
