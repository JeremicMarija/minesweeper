package com.marija.students.repository;

import com.marija.students.model.Fakultet;
import com.marija.students.model.Mesto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface FakultetRepository extends JpaRepository<Fakultet,String> {

//    @Query("SELECT f FROM Fakultet f WHERE f.maticniBroj = :maticniBroj")
//    Optional<Fakultet> findByMaticniBroj(String maticniBroj);
//
//    @Transactional
//    @Modifying
//    @Query("DELETE FROM Fakultet f WHERE f.maticniBroj = :maticniBroj")
//    void deleteFakultetByMaticniBroj(String maticniBroj);
}
