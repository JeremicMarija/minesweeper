package com.marija.students.service.impl;

import com.marija.students.exception.ResourceNotFoundException;
import com.marija.students.model.Student;
import com.marija.students.repository.StudentRepository;
import com.marija.students.service.StudentService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    private StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public Student findByID(String brojIndeksa) {
        Optional<Student>student = studentRepository.findById(brojIndeksa);
        if (student.isPresent()){
            return student.get();
        }else {
            throw new ResourceNotFoundException("Student ne postoji sa ", "ID= ", brojIndeksa);
        }
    }
}
