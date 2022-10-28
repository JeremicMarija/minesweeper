package com.marija.students.dto;

import com.marija.students.model.Fakultet;
import com.marija.students.model.Mesto;
import com.marija.students.repository.MestoRepository;
import com.marija.students.service.MestoService;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

public class FakultetDto {

    private MestoService mestoService;

    @Size(max = 8,min = 8,message = "broj karaktera mora biti 8")
    private String maticniBroj;

    @Size(max = 35,min = 2,message = "broj karaktera mora biti min 2 a max 35")
    private String naziv;
    private Long mestoPtt;

    public FakultetDto() {
    }


    public static FakultetDto from(Fakultet fakultet){
        FakultetDto fakultetDto = new FakultetDto();
        fakultetDto.setMaticniBroj(fakultet.getMaticniBroj());
        fakultetDto.setNaziv(fakultet.getNaziv());
        fakultetDto.setMestoPtt(fakultet.getMesto().getPtt());
        return fakultetDto;
    }

    public String getMaticniBroj() {
        return maticniBroj;
    }

    public void setMaticniBroj(String maticniBroj) {
        this.maticniBroj = maticniBroj;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Long getMestoPtt() {
        return mestoPtt;
    }

    public void setMestoPtt(Long mestoPtt) {
        this.mestoPtt = mestoPtt;
    }
}
