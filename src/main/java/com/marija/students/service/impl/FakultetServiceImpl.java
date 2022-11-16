package com.marija.students.service.impl;

import com.marija.students.exception.ResourceNotFoundException;
import com.marija.students.model.Fakultet;
import com.marija.students.model.Mesto;
import com.marija.students.repository.FakultetRepository;
import com.marija.students.repository.MestoRepository;
import com.marija.students.service.FakultetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class FakultetServiceImpl implements FakultetService {

    private final FakultetRepository fakultetRepository;
    private final MestoRepository mestoRepository;

    @Autowired
    public FakultetServiceImpl(FakultetRepository fakultetRepository, MestoRepository mestoRepository) {
        this.fakultetRepository = fakultetRepository;
        this.mestoRepository = mestoRepository;
    }

    @Override
    public Fakultet addFakultet(Fakultet fakultet) {
        return fakultetRepository.save(fakultet);
    }

    @Override
    public List<Fakultet> getFakulteti() {
        return fakultetRepository.findAll();
    }

    @Override
    public Fakultet getFakultet(String maticniBroj) {
        return fakultetRepository.findById(maticniBroj).orElseThrow(
                () -> new ResourceNotFoundException("Fakultet ne postoji sa ", "ID= ", maticniBroj));
    }

    @Override
    public Fakultet deleteFakultet(String maticniBroj) {
        Fakultet fakultet = getFakultet(maticniBroj);
        fakultetRepository.delete(fakultet);
        return fakultet;
    }


    @Override
    public Fakultet editFakultet(String maticniBroj, Fakultet fakultet) {
        Fakultet fakultetToEdit = getFakultet(maticniBroj);

        fakultetToEdit.setMaticniBroj(fakultet.getMaticniBroj());
        fakultetToEdit.setNaziv(fakultet.getNaziv());
        fakultetToEdit.setMesto(fakultet.getMesto());
        return fakultetToEdit;
    }


    //    OLD CODE

//    private final ModelMapper modelMapper;
//
//    private final FakultetRepository fakultetRepository;
//
//    private final MestoRepository mestoRepository;
//
//    private EntityManager entityManager;
//
//    public FakultetServiceImpl(ModelMapper modelMapper, FakultetRepository fakultetRepository, MestoRepository mestoRepository, EntityManager entityManager) {
//        this.modelMapper = modelMapper;
//        this.fakultetRepository = fakultetRepository;
//        this.mestoRepository = mestoRepository;
//        this.entityManager = entityManager;
//    }
//
//    @Override
//    public List<Fakultet> findAll() {
//        return fakultetRepository.findAll();
//    }
//
//    @Override
//    public Fakultet findByID(String maticniBroj) {
//        Optional<Fakultet> fakultet = fakultetRepository.findByMaticniBroj(maticniBroj);
//        if (fakultet.isPresent()){
//            return fakultet.get();
//        }else {
//            throw new ResourceNotFoundException("Fakultet ne postoji sa ", "ID= ",maticniBroj);
//        }
//    }
//
//    @Override
//    public Fakultet createFakultet(FakultetDto fakultetDto) {
//        Optional<Fakultet> fakultetOptional = fakultetRepository.findByMaticniBroj(fakultetDto.getMaticniBroj());
//        if (fakultetOptional.isPresent()){
//            throw new IllegalStateException("Fakultet postoji!");
//        }
//        Fakultet fakultet = modelMapper.map(fakultetDto, Fakultet.class);
//        Mesto mesto = mestoRepository.getById(fakultetDto.getMestoPtt());
//        fakultet.setMesto(mesto);
//        return fakultetRepository.save(fakultet);
//    }
//
//    @Override
//    public Fakultet updateFakultet(FakultetDto fakultetDto, String maticniBroj) {
//        Fakultet existingFakultet = fakultetRepository.findByMaticniBroj(maticniBroj).orElseThrow(
//                ()-> new ResourceNotFoundException("Fakultet ne postoji sa ", "ID= ", maticniBroj));
//
//        existingFakultet.setNaziv(fakultetDto.getNaziv());
//        Mesto mesto = mestoRepository.getById(fakultetDto.getMestoPtt());
//        existingFakultet.setMesto(mesto);
//
//        fakultetRepository.save(existingFakultet);
//
//        return existingFakultet;
//    }
//
//    @Override
//    public void deleteFakultetByMaticniBroj(String maticniBroj) {
//        fakultetRepository.deleteFakultetByMaticniBroj(maticniBroj);
//    }

}
