package com.marija.students.service;


import com.marija.students.model.Student;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface StudentService {

    public Student createStudent(Student student);

    public List<Student> findAll();

    public void delete(String studentId);

    Optional<Student>findByID(String studentID);

    Student assignFakultetToStudent(String studentId, String fakultetId);

    int calcStarost(LocalDate datumRodjenja);
}
