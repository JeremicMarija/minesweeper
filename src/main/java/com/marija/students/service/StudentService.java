package com.marija.students.service;


import com.marija.students.dto.FakultetDto;
import com.marija.students.dto.StudentDto;
import com.marija.students.model.Fakultet;
import com.marija.students.model.Student;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface StudentService {

    public Student createStudent(StudentDto student);

    public List<Student> findAll();

    Optional<Student>findByID(String studentID);
    Student getStudentById(String studentId);

    Student updateStudent(StudentDto studentDto);

    int calcStarost(LocalDate datumRodjenja);

    public void delete(String studentId);
}
