package com.loncark.guitarapp.service;

import com.loncark.guitarapp.dto.GuitarDTO;

import java.util.List;
import java.util.Optional;

public interface GuitarService {

    List<GuitarDTO> findAll();

    Optional<GuitarDTO> findByCode(String code);

    //Optional<GuitarDTO> save(HardwareCommand hardwareCommand);

    //Optional<GuitarDTO> update(String code, HardwareUpdateCommand updatedHardwareCommand);

    //void deleteByCode(String code);
}
