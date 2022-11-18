package com.marija.students.repository;

import com.marija.students.model.Fakultet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FakultetRepository extends JpaRepository<Fakultet,String> {

}
