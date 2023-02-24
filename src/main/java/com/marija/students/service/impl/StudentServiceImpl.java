package com.marija.students.service.impl;

import com.marija.students.dto.StudentDto;
import com.marija.students.exception.FakultetVecDodeljenException;
import com.marija.students.exception.ResursNijePronadjenException;
import com.marija.students.model.Fakultet;
import com.marija.students.model.Student;
import com.marija.students.repository.FakultetRepository;
import com.marija.students.repository.StudentRepository;
import com.marija.students.service.StudentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class StudentServiceImpl implements StudentService {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private FakultetRepository fakultetRepository;


    public StudentServiceImpl(StudentRepository studentRepository, FakultetRepository fakultetRepository) {
        this.studentRepository = studentRepository;
        this.fakultetRepository = fakultetRepository;
    }

    @Override
    public Student createStudent(StudentDto studentDto) {
       Optional<Student> studentOptional = studentRepository.findStudentById(studentDto.getBrojIndeksa());

       if (studentOptional.isPresent()){
           throw new IllegalStateException("Student postoji");
       }else{
//           Student student = modelMapper.map(studentDto,Student.class);
//           student.setStarost(calcStarost(studentDto.getDatumRodjenja()));
//           Fakultet fakultet = fakultetRepository.findFakultetById(studentDto.getFakultetId());
//           student.registrujStudentaZaFakultet(fakultet);
//           return studentRepository.save(student);

           Student student = modelMapper.map(studentDto,Student.class);
           student.setStarost(calcStarost(studentDto.getDatumRodjenja()));
           List<Fakultet> fakultetList = new ArrayList<>();

           for (String fakultetId: studentDto.getFakultetIds()){
               Fakultet tempFakultet = fakultetRepository.findFakultetById(fakultetId);
               fakultetList.add(tempFakultet);
               student.registrujStudentaZaFakultet(tempFakultet);
           }
           student.setFakulteti(fakultetList);
           return studentRepository.save(student);
       }
    }

    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public void delete(String studentId) {

        Optional<Student> student = studentRepository.findStudentById(studentId);
        if (student.isPresent()){
//            student.get().getFakulteti().forEach(fakultet -> student.get().ukloniStudentaZaFakultet(fakultet));
            for(int i = 0; i< student.get().getFakulteti().size(); i++){
                student.get().ukloniStudentaZaFakultet(student.get().getFakulteti().get(i));
            }
            studentRepository.delete(student.get());
        }
    }

    @Override
    public Optional<Student> findByID(String studentID) {
        return studentRepository.findById(studentID);
    }

    @Override
    public Student updateStudent(StudentDto studentDto) {

        Student existingStudent = studentRepository.findStudentById(studentDto.getBrojIndeksa()).orElseThrow(
                () -> new ResursNijePronadjenException("Student nije pronadjen ", "Broj indeksa= ", studentDto.getBrojIndeksa()));

        existingStudent.setIme(studentDto.getIme());
        existingStudent.setPrezime(studentDto.getPrezime());
        existingStudent.setJmbg(studentDto.getJmbg());
        existingStudent.setDatumRodjenja(studentDto.getDatumRodjenja());
        existingStudent.setStarost(calcStarost(studentDto.getDatumRodjenja()));

        List<Fakultet> fakultetList = existingStudent.getFakulteti();
//        List<Fakultet> fakultetList = new ArrayList<>();

        for (String fakultetId: studentDto.getFakultetIds()){
            Fakultet tempFakultet = fakultetRepository.findFakultetById(fakultetId);
//            Fakultet tempFakultet = fakultetRepository.getOne(fakultetId);

            if (fakultetList.contains(tempFakultet)){
                throw new FakultetVecDodeljenException(fakultetId,studentDto.getBrojIndeksa());
            }else {
                existingStudent.registrujStudentaZaFakultet(tempFakultet);
            }
        }
//        existingStudent.setFakulteti(fakultetList);

//        studentRepository.save(existingStudent);

        return existingStudent;

    }


    @Override
    public int calcStarost(LocalDate datumRodjenja) {
        LocalDate d = LocalDate.now();
        return Period.between(datumRodjenja, d).getYears();
    }

}
