package com.marija.students.service;


import com.marija.students.model.Mesto;

import java.util.List;
import java.util.Optional;

public interface MestoService {

    public Mesto createMesto(Mesto mesto);

    public List<Mesto> findAll();

    public void delete(Long ptt);

    Optional<Mesto>findByID(Long ptt);

    Mesto updateMesto(Mesto mesto, Long ptt);
}
