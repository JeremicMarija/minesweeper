package com.marija.students.dto;


import javax.persistence.Column;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class FakultetDto {

    @Id
    @NotNull
    @Size(max = 8,min = 8,message = "broj karaktera mora biti 8")
    @Column(name = "maticni_broj")
    private String maticniBroj;

    @NotNull
    @Size(max = 35,min = 2,message = "broj karaktera mora biti min 2 a max 35")
    @Column(name = "naziv")
    private String naziv;

    @NotNull
    @Column(name = "mesto_id")
    private Long mestoId;

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
