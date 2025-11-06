package com.tecsup.petclinic.services;

import com.tecsup.petclinic.dtos.SpecialtyDTO;
import com.tecsup.petclinic.entities.Specialty;
import com.tecsup.petclinic.exceptions.SpecialtyNotFoundException;
import com.tecsup.petclinic.mappers.SpecialtyMapper;
import com.tecsup.petclinic.repositories.SpecialtyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * * @author jgomezm
 * (Adaptado para Specialty)
 */
@Service
@Slf4j
public class SpecialtyServiceImpl implements SpecialtyService {

    // Usando final para inyección por constructor, igual que PetServiceImpl
    private final SpecialtyRepository specialtyRepository;
    private final SpecialtyMapper specialtyMapper;

    // Inyección por constructor (igual que PetServiceImpl)
    public SpecialtyServiceImpl(SpecialtyRepository specialtyRepository, SpecialtyMapper specialtyMapper) {
        this.specialtyRepository = specialtyRepository;
        this.specialtyMapper = specialtyMapper;
    }

    @Override
    public SpecialtyDTO create(SpecialtyDTO specialtyDTO) {
        Specialty newSpecialty = specialtyRepository.save(specialtyMapper.mapToEntity(specialtyDTO));
        log.info("Specialty created: {}", newSpecialty);
        return specialtyMapper.mapToDto(newSpecialty);
    }

    @Override
    public SpecialtyDTO update(SpecialtyDTO specialtyDTO) {
        Specialty updatedSpecialty = specialtyRepository.save(specialtyMapper.mapToEntity(specialtyDTO));
        log.info("Specialty updated: {}", updatedSpecialty);
        return specialtyMapper.mapToDto(updatedSpecialty);
    }

    @Override
    public void delete(Integer id) throws SpecialtyNotFoundException {
        SpecialtyDTO specialtyDTO = findById(id);
        specialtyRepository.delete(specialtyMapper.mapToEntity(specialtyDTO));
        log.info("Specialty deleted with id: {}", id);
    }

    @Override
    public SpecialtyDTO findById(Integer id) throws SpecialtyNotFoundException {
        Optional<Specialty> specialty = specialtyRepository.findById(id);
        if (!specialty.isPresent()) {
            log.warn("Specialty not found with id: {}", id);
            throw new SpecialtyNotFoundException("Record not found...!");
        }
        return specialtyMapper.mapToDto(specialty.get());
    }

    @Override
    public List<SpecialtyDTO> findByName(String name) {
        List<Specialty> specialties = specialtyRepository.findByName(name);
        return specialties.stream()
                .map(this.specialtyMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<Specialty> findAll() {
        return specialtyRepository.findAll();
    }
}
