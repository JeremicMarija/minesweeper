package com.marija.students.service.impl;


import com.marija.students.dto.FakultetDto;
import com.marija.students.exception.ResourceNotFoundException;
import com.marija.students.model.Fakultet;
import com.marija.students.model.Mesto;
import com.marija.students.repository.FakultetRepository;
import com.marija.students.repository.MestoRepository;
import com.marija.students.service.FakultetService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class FakultetServiceImpl implements FakultetService {

    private final ModelMapper modelMapper;

    private final MestoRepository mestoRepository;
    private final FakultetRepository fakultetRepository;

    public FakultetServiceImpl(ModelMapper modelMapper, MestoRepository mestoRepository, FakultetRepository fakultetRepository) {
        this.modelMapper = modelMapper;
        this.mestoRepository = mestoRepository;
        this.fakultetRepository = fakultetRepository;
    }

    @Override
    public Fakultet createFakultet(FakultetDto fakultetDto) {
        Optional<Fakultet> fakultetOptional = fakultetRepository.findById(fakultetDto.getMaticniBroj());
        if (fakultetOptional.isPresent()){
            throw new IllegalStateException("Fakultet postoji");
        }
        Fakultet fakultet = modelMapper.map(fakultetDto, Fakultet.class);
        Mesto mesto = mestoRepository.getReferenceById(fakultetDto.getMestoId());
        fakultet.setMesto(mesto);
        return fakultetRepository.save(fakultet);
    }

    @Override
    public List<Fakultet> findAll() {
        return fakultetRepository.findAll();
    }

    @Override
    public Fakultet getFakultetById(String maticniBroj) {
       Optional<Fakultet> fakultet = fakultetRepository.findById(maticniBroj);
       if (fakultet.isPresent()){
           return fakultet.get();
       }else {
           throw new ResourceNotFoundException("Fakultet ne postoji sa ", "ID= ", maticniBroj);
       }
    }

    @Override
    public Fakultet updateFakultet( FakultetDto fakultetDto) {

        Fakultet existingFakultet = fakultetRepository.findById(fakultetDto.getMaticniBroj()).orElseThrow(
                () -> new ResourceNotFoundException("Fakultet ", "Maticni broj= ",fakultetDto.getMaticniBroj()));

//        existingFakultet.setMaticniBroj(fakultetDto.getMaticniBroj());
        existingFakultet.setNaziv(fakultetDto.getNaziv());
//        Mesto mesto = mestoRepository.getReferenceById(fakultetDto.getMestoId());
        Mesto mesto = mestoRepository.getById(fakultetDto.getMestoId());
        existingFakultet.setMesto(mesto);

        fakultetRepository.save(existingFakultet);

        return existingFakultet;
    }

    @Override
    public void delete(String maticniBroj) {
        Fakultet fakultet = fakultetRepository.findFakultetById(maticniBroj);
        if (fakultet != null){
            fakultet.getStudenti().forEach(student -> {
                student.getFakulteti().remove(fakultet);
            });
            fakultetRepository.deleteById(maticniBroj);
        }else {
            throw new ResourceNotFoundException("Fakultet ne postoji sa ", "ID= ", maticniBroj);
        }
//        fakultetRepository.deleteById(maticniBroj);
    }
}
