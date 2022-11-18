package com.marija.students.model;


import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "fakulteti")
public class Fakultet {

    @Id
    @NotNull
    @Size(max = 8,min = 8,message = "broj karaktera mora biti 8")
    @Column(name = "maticni_broj")
    private String maticniBroj;

    @NotNull
    @Size(max = 35,min = 2,message = "broj karaktera mora biti min 2 a max 35")
    @Column(name = "naziv")
    private String naziv;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "mesto_id", referencedColumnName = "ptt") // FK
    private Mesto mesto;

    public Fakultet(){

    }

    public Fakultet(String maticniBroj, String naziv) {
        this.maticniBroj = maticniBroj;
        this.naziv = naziv;
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

    public Mesto getMesto() {
        return mesto;
    }

    public void setMesto(Mesto mesto) {
        this.mesto = mesto;
    }

    @Override
    public String toString(){
        return "Fakultet[maticni broj= " + maticniBroj + ", naziv= " + naziv + "]";
    }

    //FON Maticni broj = 07004044
    //Saobracajni Maticni broj = 07032587
    //Masinski Maticni broj = 07032501
    //Gradjevincski Matacni broj = 07006454
}
