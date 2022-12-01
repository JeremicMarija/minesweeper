package com.marija.students.repository;

import com.marija.students.model.Fakultet;
import com.marija.students.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student,String> {

    @Query("SELECT s FROM Student s WHERE s.brojIndeksa = ?1")
    Optional<Student> findStudentById(String brojIndeksa);



}
