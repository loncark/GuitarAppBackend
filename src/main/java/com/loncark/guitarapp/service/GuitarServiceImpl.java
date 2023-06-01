package com.loncark.guitarapp.service;

import com.loncark.guitarapp.dto.GuitarDTO;
import com.loncark.guitarapp.model.Guitar;
import com.loncark.guitarapp.repository.GuitarRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GuitarServiceImpl implements GuitarService {

    private final GuitarRepository guitarRepository;

    public GuitarServiceImpl(GuitarRepository guitarRepository) {
        this.guitarRepository = guitarRepository;
    }

    @Override
    public List<GuitarDTO> findAll() {
        return guitarRepository.findAll().stream().map(GuitarDTO::new).collect(Collectors.toList());
    }

    @Override
    public Optional<GuitarDTO> findByCode(String code) {
        return guitarRepository.findByCode(code).map(guitar -> new GuitarDTO(code, guitar));
    }

}
