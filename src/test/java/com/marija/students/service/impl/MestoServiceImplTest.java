package com.marija.students.service.impl;

import com.marija.students.exception.ResursNijePronadjenException;
import com.marija.students.model.Mesto;
import com.marija.students.repository.MestoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MestoServiceImplTest {

    @Mock
    private MestoRepository mestoRepository;

    private MestoServiceImpl underTest;

    @BeforeEach
    void setUp() {
        underTest = new MestoServiceImpl(mestoRepository);
    }


    @Test
    void canGetAllMesta() {
        //when
        underTest.getAllMesta();

        //then
        verify(mestoRepository).findAll();

    }

    @Test
    void canGetMesto() {
        //given
        long ptt = 18000;

        //when
        underTest.getMesto(ptt);

        //then
        verify(mestoRepository).findById(ptt);
    }

    @Test
    void createMesto_MestoExists_ShouldThrowEx() {
        //given
        Mesto mesto = new Mesto(
                18000L,
                "Nis",
                185987L
        );
        given(mestoRepository.findById(mesto.getPtt())).willReturn(Optional.of(mesto));

        //when
        //then
        assertThatThrownBy(() -> underTest.createMesto(mesto))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("Mesto postoji");
        verify(mestoRepository, never()).save(any());
    }

    @Test
    void createMesto_MestoNotExists_ShouldSuccess() {
        //given
        Mesto mesto = new Mesto(
                18000L,
                "Nis",
                185987L
        );

        //when
        underTest.createMesto(mesto);

        //then
        ArgumentCaptor<Mesto> mestoArgumentCaptor = ArgumentCaptor.forClass(Mesto.class);
        verify(mestoRepository).save(mestoArgumentCaptor.capture());
        Mesto capturedMesto = mestoArgumentCaptor.getValue();
        assertThat(capturedMesto).isEqualTo(mesto);
    }


    @Test
    void updateMesto_NonExistent_ShouldThrowEx(){
        //given
        long nonExistent = 11000L;
        given(mestoRepository.findById(nonExistent)).willReturn(Optional.empty());
        //when
        //then
        assertThatThrownBy(() -> underTest.updateMesto(null,nonExistent))
                .isInstanceOf(ResursNijePronadjenException.class)
                .hasMessageContaining("Mesto ", "Ptt= ", nonExistent);
        verify(mestoRepository, never()).save(any());
    }

    @Test
    void updateMesto_Existing_ShouldSuccess(){
        //given
        long existingPtt = 18000L;
        Mesto newMesto = new Mesto(555L, "Niss", 1800000L);
        Mesto oldMesto = new Mesto(existingPtt, "Nis", 185987L);
        when(mestoRepository.findById(existingPtt)).thenReturn(Optional.of(oldMesto));
        ArgumentCaptor<Mesto> mestoArgumentCaptor = ArgumentCaptor.forClass(Mesto.class);

        //when
        Mesto updateMesto = this.underTest.updateMesto(newMesto,existingPtt);
        verify(mestoRepository).save(mestoArgumentCaptor.capture());

        //then
        assertThat(mestoArgumentCaptor.getValue().getNaziv()).isEqualTo(newMesto.getNaziv());
        assertThat(mestoArgumentCaptor.getValue().getBrojStanovnika()).isEqualTo(newMesto.getBrojStanovnika());

    }


    @Test
    void deleteMesto_ShouldDeleteMesto() {
        //given
        long ptt = 18000;
        //when
        underTest.delete(ptt);
        //then
        verify(mestoRepository).deleteById(ptt);
    }
}