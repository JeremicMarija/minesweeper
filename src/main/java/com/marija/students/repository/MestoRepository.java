package com.marija.students.repository;

import com.marija.students.model.Mesto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MestoRepository extends JpaRepository<Mesto,Long> {


}
