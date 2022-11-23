package com.marija.students.service.impl;

import com.marija.students.model.Fakultet;
import com.marija.students.model.Student;
import com.marija.students.repository.FakultetRepository;
import com.marija.students.repository.StudentRepository;
import com.marija.students.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;


@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private FakultetRepository fakultetRepository;


    public StudentServiceImpl(StudentRepository studentRepository, FakultetRepository fakultetRepository) {
        this.studentRepository = studentRepository;
        this.fakultetRepository = fakultetRepository;
    }

    @Override
    public Student createStudent(Student student) {

        Optional<Student> studentOptional = studentRepository.findById(student.getBrojIndeksa());
        if (studentOptional.isPresent()){
            throw new IllegalStateException("Student postoji");
        }else{
            student.setStarost(calcStarost(student.getDatumRodjenja()));
            return studentRepository.save(student);
        }
    }

    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public void delete(String studentId) {
        studentRepository.deleteById(studentId);
    }

    @Override
    public Optional<Student> findByID(String studentID) {
        return studentRepository.findById(studentID);
    }

    @Override
    public Student assignFakultetToStudent(String studentId, String fakultetId) {

        List<Fakultet> listaFakulteta = null;

        Student student = studentRepository.findById(studentId).get();
        Fakultet fakultet = fakultetRepository.findById(fakultetId).get();

        listaFakulteta = student.getAssignedFakulteti();
        listaFakulteta.add(fakultet);

        student.setAssignedFakulteti(listaFakulteta);

        return studentRepository.save(student);
    }

    @Override
    public int calcStarost(LocalDate datumRodjenja) {
        LocalDate d = LocalDate.now();
        return Period.between(datumRodjenja, d).getYears();
    }

}
