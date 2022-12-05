package com.marija.students.service;


import com.marija.students.dto.FakultetDto;
import com.marija.students.model.Fakultet;

import java.util.List;
import java.util.Optional;

public interface FakultetService {

    Fakultet createFakultet(FakultetDto fakultetDto);

    List<Fakultet>findAll();

//    Optional<Fakultet>findByID(String maticniBroj);
    Fakultet getFakultetById(String maticniBroj);
//    FakultetDto getFakultetById(String maticniBroj);

    Fakultet updateFakultet(FakultetDto fakultetDto);

    public void delete(String maticniBroj);

}
