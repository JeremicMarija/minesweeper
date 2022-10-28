package com.marija.students.service;

import com.marija.students.dto.FakultetDto;
import com.marija.students.model.Fakultet;

import java.util.List;

public interface FakultetService {

    Fakultet addFakultet(Fakultet fakultet);
    List<Fakultet>getFakulteti();
    Fakultet getFakultet(String maticniBroj);
    Fakultet deleteFakultet(String maticniBroj);
    Fakultet editFakultet(String maticniBroj, Fakultet fakultet);
//
//    List<Fakultet> findAll();
//    Fakultet findByID(String maticniBroj);
//    Fakultet createFakultet(FakultetDto fakultetDto);
//    Fakultet updateFakultet(FakultetDto fakultetDto, String maticniBroj);
//    void deleteFakultetByMaticniBroj(String maticniBroj);
}
