package com.marija.students.dto;

import com.marija.students.model.Fakultet;
import com.marija.students.model.Mesto;

import javax.persistence.Column;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MestoDto {

    @Min(value=5, message="Ptt mora imati 5 cifara")
    private Long ptt;

    @Size(max = 35,min = 2,message = "Broj karaktera mora biti min 2 a max 35")
    private String naziv;

    @Min(value = 1, message = "Broj stanovnika mora biti veci od nule")
    private Long brojStanovnika;

    private List<FakultetDto> fakultetiDto = new ArrayList<>();

    public Long getPtt() {
        return ptt;
    }

    public void setPtt(Long ptt) {
        this.ptt = ptt;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Long getBrojStanovnika() {
        return brojStanovnika;
    }

    public void setBrojStanovnika(Long brojStanovnika) {
        this.brojStanovnika = brojStanovnika;
    }

    public List<FakultetDto> getFakultetiDto() {
        return fakultetiDto;
    }

    public void setFakultetiDto(List<FakultetDto> fakultetiDto) {
        this.fakultetiDto = fakultetiDto;
    }

    public static MestoDto from(Mesto mesto){
        MestoDto mestoDto = new MestoDto();
        mestoDto.setPtt(mesto.getPtt());
        mestoDto.setNaziv(mesto.getNaziv());
        mestoDto.setBrojStanovnika(mesto.getBrojStanovnika());
        mestoDto.setFakultetiDto(mesto.getFakulteti().stream().map(FakultetDto::from).collect(Collectors.toList()));
        return mestoDto;
    }

}
