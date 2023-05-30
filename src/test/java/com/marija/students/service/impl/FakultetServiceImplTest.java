package com.marija.students.service.impl;

import com.marija.students.dto.FakultetDto;
import com.marija.students.exception.ResursNijePronadjenException;
import com.marija.students.model.Fakultet;
import com.marija.students.model.Mesto;
import com.marija.students.model.Student;
import com.marija.students.repository.FakultetRepository;
import com.marija.students.repository.MestoRepository;
import com.marija.students.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
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
class FakultetServiceImplTest {

    @Mock
    private MestoRepository mestoRepository;

    @Mock
    private FakultetRepository fakultetRepository;

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private ModelMapper modelMapper;

    private FakultetServiceImpl underTest;

    @BeforeEach
    void setUp() {
        underTest = new FakultetServiceImpl( modelMapper, mestoRepository,fakultetRepository);
    }

    @Test
    void canGetAllFakulteti() {
        //when
        underTest.findAll();

        //then
        verify(fakultetRepository).findAll();
    }

    @Test
    void getFakultetById_FakultetNonExistend_ShouldThrowEx() {
        //given
        String nonExistendMaticniBroj = "kkk";

        given(fakultetRepository.findById(nonExistendMaticniBroj)).willReturn(Optional.empty());

        //when
        //then
        assertThatThrownBy(() -> underTest.getFakultetById(nonExistendMaticniBroj))
                .isInstanceOf(ResursNijePronadjenException.class)
                .hasMessageContaining("Fakultet ne postoji sa ", "ID= ", nonExistendMaticniBroj);
    }

    @Test
    void getFakultetById_canGetFakultet() {
        //given
        String maticniBroj = "07004044";
        Mesto mesto = new Mesto(11000L,"Beograd",2000000L);
        Fakultet fakultet = new Fakultet(maticniBroj,"FON",mesto);

        given(fakultetRepository.findById(maticniBroj)).willReturn(Optional.of(fakultet));

        //when
        underTest.getFakultetById(maticniBroj);

        //then
        verify(fakultetRepository).findById(maticniBroj);
    }

    @Test
    void createFakultet_FakultetExist_ShouldThrowEx(){
        //given
        String maticniBroj = "07004044";
        Mesto mesto = new Mesto(11000L,"Beograd",2000000L);
        Fakultet fakultet = new Fakultet(maticniBroj,"FON",mesto);
        FakultetDto fakultetDto = new FakultetDto(maticniBroj,"FON", mesto.getPtt());

        given(fakultetRepository.findById(maticniBroj)).willReturn(Optional.of(fakultet));

        //when
        //then
        assertThatThrownBy(() -> underTest.createFakultet(fakultetDto))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("Fakultet postoji");
    }

    @Test
    void createFakultet_FakultetNotExists_ShouldSuccess() {
        //given
        String maticniBroj = "07004044";
        long mestoPtt = 11000;
        Mesto mesto = new Mesto(mestoPtt,"Beograd",2000000L);
        FakultetDto fakultetDto = new FakultetDto(maticniBroj,"FON", mesto.getPtt());
        Fakultet expectedFakultet = new Fakultet();
        given(mestoRepository.getById(mestoPtt)).willReturn(mesto);
        given(modelMapper.map(fakultetDto, Fakultet.class)).willReturn(expectedFakultet);

        //when
        underTest.createFakultet(fakultetDto);

        //then
        ArgumentCaptor<Fakultet> fakultetArgumentCaptor = ArgumentCaptor.forClass(Fakultet.class);
        verify(fakultetRepository).save(fakultetArgumentCaptor.capture());
        Fakultet capturedFakultet = fakultetArgumentCaptor.getValue();
        assertThat(capturedFakultet).isEqualTo(expectedFakultet);
        assertThat(fakultetArgumentCaptor.getValue().getMesto().getNaziv()).isEqualTo(mesto.getNaziv());
    }


    @Test
    void updateFakultet_NonExistent_ShouldThrowEx() {
        //given
        long mestoPtt = 11000;
//        String maticniBroj = "07004044";
        String nonExistent = "test";
//        FakultetDto fakultetDto = new FakultetDto(maticniBroj,"FON", mestoPtt);
        FakultetDto fakultetDtoNonExist = new FakultetDto(nonExistent,"FON", mestoPtt);
        given(fakultetRepository.findById(nonExistent)).willReturn(Optional.empty());

        //when
        //then
        assertThatThrownBy(() -> underTest.updateFakultet(fakultetDtoNonExist))
                .isInstanceOf(ResursNijePronadjenException.class)
                .hasMessageContaining("Fakultet ", "Maticni broj= ", nonExistent);
        verify(fakultetRepository, never()).save(any());

    }
    @Test
    void updateFakultet_Existing_ShouldSuccess(){
        //given
        String existingMaticniBroj = "07004044";
        long mestoPtt = 11000;
        Mesto mesto = new Mesto(mestoPtt,"Beograd",2000000L);

        Fakultet existingFakultet = new Fakultet(existingMaticniBroj,"FON", mesto);
        FakultetDto newFakultetDto = new FakultetDto(existingMaticniBroj,"FONN", mestoPtt);
        given(mestoRepository.getById(mestoPtt)).willReturn(mesto);
        when(fakultetRepository.findById(existingMaticniBroj)).thenReturn(Optional.of(existingFakultet));
        ArgumentCaptor<Fakultet> fakultetArgumentCaptor = ArgumentCaptor.forClass(Fakultet.class);

        //when
       Fakultet updateFakultet = this.underTest.updateFakultet(newFakultetDto);
       verify(fakultetRepository).save(fakultetArgumentCaptor.capture());

       //then
        assertThat(fakultetArgumentCaptor.getValue().getNaziv()).isEqualTo(newFakultetDto.getNaziv());
    }

    @Test
    void delete_NonExistent_ShouldThrowEx() {
        //given
        String maticniBroj = "test";
        given(fakultetRepository.findFakultetById(maticniBroj)).willReturn(null);

        //when
        //then
        assertThatThrownBy(() -> underTest.delete(maticniBroj))
                .isInstanceOf(ResursNijePronadjenException.class)
                .hasMessageContaining("Fakultet ne postoji sa ", "ID= ", maticniBroj);
        verify(fakultetRepository, never()).save(any());
    }


    @Test
//    @Disabled
    void delete_Existing_ShouldSuccess(){
        //given
        String maticniBroj = "07004044";
        long mestoPtt = 11000;
        Mesto mesto = new Mesto(mestoPtt,"Beograd",2000000L);
        Fakultet expectedFakultet = new Fakultet(maticniBroj,"FON",mesto);
        Student student = new Student("21/3720","Marija","Jeremic",LocalDate.parse("1990-05-31"),"3105990766026");
        Student student1 = new Student("21/3720","Marija","Jeremic",LocalDate.parse("1990-05-31"),"3105990766026");
        List<Student> studentList = new ArrayList<>();
        List<Fakultet> fakultetList = new ArrayList<>();
        expectedFakultet.setStudenti(studentList);
        student.setFakulteti(fakultetList);
//        given(expectedFakultet.getStudenti()).willReturn((List<Student>) student);
//        when(fakultetRepository.findFakultetById(maticniBroj)).thenReturn(expectedFakultet);
        given(fakultetRepository.findFakultetById(maticniBroj)).willReturn(expectedFakultet);


        //when
        underTest.delete(maticniBroj);

       //then
        verify(fakultetRepository).deleteById(maticniBroj);
       for(Student student2 : expectedFakultet.getStudenti()) {
           assertFalse(student2.getFakulteti().contains(expectedFakultet));
       }

    }

}