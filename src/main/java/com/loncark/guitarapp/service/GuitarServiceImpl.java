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

    @Override
    public Optional<GuitarDTO> save(Guitar guitar) {
        Optional<Guitar> savedGuitar = guitarRepository.save(guitar);

        if(savedGuitar.isPresent()) {
            return Optional.of(new GuitarDTO(savedGuitar.get()));
        }
        else return Optional.empty();
    }

    @Override
    public Optional<GuitarDTO> update(Guitar updatedGuitar) {
        return guitarRepository
                .update(updatedGuitar)
                .map(guitar -> new GuitarDTO(guitar));
    }

    @Override
    public void deleteByCode(String code) {
        guitarRepository.deleteByCode(code);
    }


}
