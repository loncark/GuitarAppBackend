package com.loncark.guitarapp.repository;

import com.loncark.guitarapp.model.guitar.Guitar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
public interface GuitarRepository extends JpaRepository<Guitar, Long> {

    Optional<Guitar> findByCode(String code);

    void deleteByCode(String code);

    Guitar save(Guitar guitar);
}
