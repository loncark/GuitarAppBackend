package com.loncark.guitarapp.repository;

import com.loncark.guitarapp.model.Guitar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Transactional
public interface GuitarRepository extends JpaRepository<Guitar, Long> {

}
