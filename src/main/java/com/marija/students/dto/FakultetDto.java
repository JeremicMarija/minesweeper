package com.marija.students.dto;


import com.marija.students.model.Fakultet;
import org.hibernate.validator.constraints.Range;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class FakultetDto {

    @Id
    @NotNull
    @Pattern(regexp="^[0-9a-zA-Z]{8}",message ="Maticni broj mora imati 8 cifara")
    private String maticniBroj;

    @NotNull
    @Pattern(regexp = "^[A-Z]+[a-zA-Z\s]{2,35}$", message = "Naziv mora pocinjati velikim slovom i broj karaktera mora biti min 2 a max 35")
    private String naziv;

    @NotNull
    @Digits(integer = 5, fraction = 0, message = "Ptt mora imati 5 cifara")
    private Long mestoId;

    public FakultetDto() {
    }

    public FakultetDto(String maticniBroj, String naziv, Long mestoId) {
        this.maticniBroj = maticniBroj;
        this.naziv = naziv;
        this.mestoId = mestoId;
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

    public Long getMestoId() {
        return mestoId;
    }

    public void setMestoId(Long mestoId) {
        this.mestoId = mestoId;
    }
}
