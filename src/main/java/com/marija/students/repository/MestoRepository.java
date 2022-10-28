package com.marija.students.repository;

import com.marija.students.model.Mesto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MestoRepository extends JpaRepository<Mesto,Long> {

//    @Query("SELECT m FROM Mesto m WHERE m.ptt = :ptt")
//    Optional<Mesto>findByPtt(Long ptt);
}
