package com.loncark.guitarapp.repository;

import com.loncark.guitarapp.model.Guitar;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface GuitarRepository extends JpaRepository<Guitar, Long> {

}
