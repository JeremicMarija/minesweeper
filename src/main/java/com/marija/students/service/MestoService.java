package com.marija.students.service;

import com.marija.students.model.Fakultet;
import com.marija.students.model.Mesto;

import java.util.List;

public interface MestoService {

    Mesto addMesto(Mesto mesto);
    List<Mesto>getMesta();
    Mesto getMesto(Long ptt);
    Mesto deleteMesto(Long ptt);
    Mesto editMesto(Long ptt, Mesto mesto);
    Mesto addFakultetToMesto(Long mestoId, String fakultetId);
    Mesto removeFakultetFromMesto(Long mestoId, String fakultetId);

// ODL CODE
//    List<Mesto> findAll();
//    Mesto findByID(Long ptt);
//    Mesto createMesto(Mesto mesto);
//    Mesto updateMesto(Mesto mesto, Long ptt);
//    void deleteById(Long ptt);
}
