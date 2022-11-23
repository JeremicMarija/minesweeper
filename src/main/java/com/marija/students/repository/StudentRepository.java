package com.marija.students.repository;

import com.marija.students.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface StudentRepository extends JpaRepository<Student,String> {

//    @Query(value = "UPDATE studentsdb.student SET starost = DATE_FORMAT(FROM_DAYS(DATEDIFF(NOW(),'datum_rodjenja')), '%Y'))", nativeQuery = true)
//    void calcStarost(LocalDate datumRodjenja);



}
