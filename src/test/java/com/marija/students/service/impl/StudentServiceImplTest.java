package com.marija.students.service.impl;

import com.marija.students.dto.FakultetDto;
import com.marija.students.dto.StudentDto;
import com.marija.students.exception.ResursNijePronadjenException;
import com.marija.students.model.Fakultet;
import com.marija.students.model.Mesto;
import com.marija.students.model.Student;
import com.marija.students.repository.FakultetRepository;
import com.marija.students.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StudentServiceImplTest {
    @Mock
    private FakultetRepository fakultetRepository;

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private ModelMapper modelMapper;

    private StudentServiceImpl underTest;

    @BeforeEach
    void setUp() {
        underTest = new StudentServiceImpl(modelMapper,studentRepository,fakultetRepository);
    }

    @Test
    void findAll_CanGetAllStudent() {

        //when
        underTest.findAll();

        //then
        verify(studentRepository).findAll();
    }

    @Test
    void getStudentById_StudentNonExistend_ShouldThrowEx() {

        //given
        String nonExistendBrojIndeksa = "test";

        given(studentRepository.findStudentById(nonExistendBrojIndeksa)).willReturn(null);

        //when
        //then
        assertThatThrownBy(() -> underTest.getStudentById(nonExistendBrojIndeksa))
                .isInstanceOf(ResursNijePronadjenException.class)
                .hasMessageContaining("Student ne postoji sa ", "ID= ", nonExistendBrojIndeksa);
    }

    @Test
    void getStudentById_StudentExist_ShouldSucces(){
        //given
        List<Fakultet> fakultetList = new ArrayList<>();
        Student student = new Student("21/3720","Marija","Jeremic",LocalDate.parse("1990-05-31"),"3105990766026");
        student.setFakulteti(fakultetList);

        given(studentRepository.findStudentById(student.getBrojIndeksa())).willReturn(student);

        //when
        underTest.getStudentById(student.getBrojIndeksa());

        //then
        verify(studentRepository).findStudentById(student.getBrojIndeksa());
    }


    @Test
    void createStudent_StudentExist_ShouldThrowEx() {

        //given
        List<Fakultet> fakultetList = new ArrayList<>();
        List<String> fakultetIds = new ArrayList<>();
        Student student = new Student("21/3720","Marija","Jeremic",LocalDate.parse("1990-05-31"),"3105990766026");
        student.setFakulteti(fakultetList);
        StudentDto studentDto = new StudentDto();
        studentDto.setBrojIndeksa("21/3720");
        studentDto.setIme("Marija");
        studentDto.setPrezime("Jeremic");
        studentDto.setDatumRodjenja(LocalDate.parse("1990-05-31"));
        studentDto.setJmbg("3105990766026");
        studentDto.setFakultetIds(fakultetIds);
        given(studentRepository.findStudentById("21/3720")).willReturn(student);

        //when
        // then
        assertThatThrownBy(() -> underTest.createStudent(studentDto))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("Student postoji");

    }

    @Test
    void createStudent_StudentNotExist_ShouldSuccess(){
        //given
        List<Fakultet> fakultetList = new ArrayList<>();
        List<String> fakultetIds = new ArrayList<>();
        StudentDto studentDto = new StudentDto();
        studentDto.setBrojIndeksa("21/3720");
        studentDto.setIme("Marija");
        studentDto.setPrezime("Jeremic");
        studentDto.setDatumRodjenja(LocalDate.parse("1990-05-31"));
        studentDto.setJmbg("3105990766026");
        studentDto.setFakultetIds(fakultetIds);
        Student exceptedStudent = new Student();
//        Student exceptedStudent = new Student("21/3720","Marija","Jeremic",LocalDate.parse("1990-05-31"),"3105990766026");
        given(modelMapper.map(studentDto,Student.class)).willReturn(exceptedStudent);
        exceptedStudent.setFakulteti(fakultetList);

        //when
        underTest.createStudent(studentDto);

        //then
        ArgumentCaptor<Student> studentArgumentCaptor = ArgumentCaptor.forClass(Student.class);
        assertTrue(exceptedStudent.getFakulteti().containsAll(fakultetList));
        verify(studentRepository).save(studentArgumentCaptor.capture());
        Student captaredStudent = studentArgumentCaptor.getValue();
        assertThat(captaredStudent).isEqualTo(exceptedStudent);
        assertThat(studentArgumentCaptor.getValue().getIme()).isEqualTo(exceptedStudent.getIme());

    }

    @Test
    void updateStudent_NonExistent_ShouldThrowEx() {
        //given
        StudentDto studentDtoNotExist = new StudentDto();
        given(studentRepository.findStudentById(studentDtoNotExist.getBrojIndeksa())).willReturn(null);

        //when
        //then
        assertThatThrownBy(() -> underTest.updateStudent(studentDtoNotExist))
                .isInstanceOf(ResursNijePronadjenException.class)
                .hasMessageContaining("Student nije pronadjen ", "Broj indeksa= ", studentDtoNotExist.getBrojIndeksa());
        verify(studentRepository, never()).save(any());
    }

    @Test
    void updateStudent_Existing_ShouldSuccess(){
        //given
        Student existingStudent = new Student("21/3720","Marija","Jeremic",LocalDate.parse("1990-05-31"),"3105990766026");
        StudentDto newStudentDto = new StudentDto();
        newStudentDto.setBrojIndeksa("21/3720");
        newStudentDto.setIme("Test");
        newStudentDto.setPrezime("Jeremic");
        newStudentDto.setDatumRodjenja(LocalDate.parse("1990-05-31"));
        newStudentDto.setJmbg("12345678912");
        newStudentDto.setStarost(underTest.calcStarost(LocalDate.parse("1990-05-31")));
        given(studentRepository.findStudentById(existingStudent.getBrojIndeksa())).willReturn(existingStudent);
        ArgumentCaptor<Student> studentArgumentCaptor = ArgumentCaptor.forClass(Student.class);

        //when
        Student updateStudent = this.underTest.updateStudent(newStudentDto);
        verify(studentRepository).save(studentArgumentCaptor.capture());

        //then
        assertThat(studentArgumentCaptor.getValue().getIme()).isEqualTo(newStudentDto.getIme());

    }

    @Test
    void delete_Existing_ShouldSuccess() {
        //given
        Student existingStudent = new Student("21/3720","Marija","Jeremic",LocalDate.parse("1990-05-31"),"3105990766026");
        given(studentRepository.findStudentById(existingStudent.getBrojIndeksa())).willReturn(existingStudent);

        //when
        underTest.delete(existingStudent.getBrojIndeksa());

        //then
        verify(studentRepository).delete(existingStudent);
        for(Fakultet fakultet : existingStudent.getFakulteti()) {
            verify(existingStudent, times(1)).ukloniFakultetZaStudenta(fakultet);
            assertFalse(fakultet.getStudenti().contains(existingStudent));
        }

    }


    @Test
    void findByID_StudentExist_ShouldSucces() {
        //given
        Student student = new Student("21/3720","Marija","Jeremic",LocalDate.parse("1990-05-31"),"3105990766026");
        String studentId = "21/3720";

        //when
        underTest.findByID(studentId);

        //then
        verify(studentRepository).findById(studentId);
    }


}