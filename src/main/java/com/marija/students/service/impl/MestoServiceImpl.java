package com.marija.students.service.impl;

import com.marija.students.exception.ResourceNotFoundException;
import com.marija.students.model.Fakultet;
import com.marija.students.model.Mesto;
import com.marija.students.repository.MestoRepository;
import com.marija.students.service.FakultetService;
import com.marija.students.service.MestoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MestoServiceImpl implements MestoService {

    private final MestoRepository mestoRepository;
    private final FakultetService fakultetService;

    public MestoServiceImpl(MestoRepository mestoRepository, FakultetService fakultetService) {
        this.mestoRepository = mestoRepository;
        this.fakultetService = fakultetService;
    }

    @Override
    public Mesto addMesto(Mesto mesto) {
        return mestoRepository.save(mesto);
    }

    @Override
    public List<Mesto> getMesta() {
        return mestoRepository.findAll();
    }

    @Override
    public Mesto getMesto(Long ptt) {
        return mestoRepository.findById(ptt).orElseThrow(
                ()-> new ResourceNotFoundException("Mesto ne postoji sa ", "ID= ", ptt));
    }

    @Override
    public Mesto deleteMesto(Long ptt) {
        Mesto mesto = getMesto(ptt);
        mestoRepository.delete(mesto);
        return mesto;
    }

    @Override
    public Mesto editMesto(Long ptt, Mesto mesto) {
        Mesto mestoToEdit = getMesto(ptt);

        mestoToEdit.setPtt(mesto.getPtt());
        mestoToEdit.setNaziv(mesto.getNaziv());
        mestoToEdit.setBrojStanovnika(mesto.getBrojStanovnika());
        mestoToEdit.setFakulteti(mesto.getFakulteti());

        return mestoToEdit;
    }

    @Override
    public Mesto addFakultetToMesto(Long mestoId, String fakultetId) {
        Mesto mesto = getMesto(mestoId);
        Fakultet fakultet = fakultetService.getFakultet(fakultetId);
        mesto.addFakultet(fakultet);
        return mesto;
    }

    @Override
    public Mesto removeFakultetFromMesto(Long mestoId, String fakultetId) {
        Mesto mesto = getMesto(mestoId);
        Fakultet fakultet = fakultetService.getFakultet(fakultetId);
        mesto.removeFakultet(fakultet);
        return mesto;
    }


    //OLD CODE
//    private final MestoRepository mestoRepository;
//
//    public MestoServiceImpl(MestoRepository mestoRepository) {
//        this.mestoRepository = mestoRepository;
//    }
//
//    @Override
//    public List<Mesto> findAll() {
//        return mestoRepository.findAll();
//    }
//
//    @Override
//    public Mesto findByID(Long ptt) {
//        Optional<Mesto> mesto = mestoRepository.findById(ptt);
//        if (mesto.isPresent()){
//            return mesto.get();
//        }else {
//            throw new ResourceNotFoundException("Mesto ne postoji sa ", "ID= ",ptt);
//        }
//    }
//
//    @Override
//    public Mesto createMesto(Mesto mesto) {
//        Optional<Mesto>mestoOptional = mestoRepository.findByPtt(mesto.getPtt());
//        if(mestoOptional.isPresent()){
//            throw new IllegalStateException("Mesto postoji!");
//        }
//        return mestoRepository.save(mesto);
//    }
//
//    @Override
//    public Mesto updateMesto(Mesto mesto, Long ptt) {
//        Mesto existingMesto = mestoRepository.findByPtt(ptt).orElseThrow(
//        () -> new ResourceNotFoundException("Mesto ne postoji sa ","ptt= ", ptt));
//
//        existingMesto.setNaziv(mesto.getNaziv());
//        existingMesto.setBrojStanovnika(mesto.getBrojStanovnika());
//
//        mestoRepository.save(existingMesto);
//
//        return existingMesto;
//    }
//
//    @Override
//    public void deleteById(Long ptt) {
//        mestoRepository.deleteById(ptt);
//    }

}
