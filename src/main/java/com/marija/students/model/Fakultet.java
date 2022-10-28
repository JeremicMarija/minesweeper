package com.marija.students.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.marija.students.dto.FakultetDto;
import com.marija.students.service.MestoService;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;

@Entity
@Table(name = "fakultet")
public class Fakultet {

    private MestoService mestoService;

    @Id
    @NotNull
    @Size(max = 8,min = 8,message = "broj karaktera mora biti 8")
    @Column(name = "maticni_broj")
    private String maticniBroj;

    @NotNull
    @Size(max = 35,min = 2,message = "broj karaktera mora biti min 2 a max 35")
    @Column(name = "naziv")
    private String naziv;


    @ManyToMany(mappedBy = "fakulteti", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Student>studenti;


//    @ManyToOne(fetch = FetchType.EAGER, optional = false)
//    @JoinColumn(name = "mesto_ptt", referencedColumnName = "ptt")
//    @NotNull
//    @JsonIgnore
//    private Mesto mesto;

    @ManyToOne
    private Mesto mesto;

    public Fakultet() {
    }

    public static Fakultet from(FakultetDto fakultetDto){
        Fakultet fakultet = new Fakultet();
        fakultet.setMaticniBroj(fakultetDto.getMaticniBroj());
        fakultet.setNaziv(fakultetDto.getNaziv());
        MestoService mestoService = null;
        Mesto mesto = mestoService.getMesto(fakultetDto.getMestoPtt());
        fakultet.setMesto(mesto);
        return fakultet;
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

    public List<Student> getStudenti() {
        return studenti;
    }

    public void setStudenti(List<Student> studenti) {
        this.studenti = studenti;
    }

    public Mesto getMesto() {
        return mesto;
    }

    public void setMesto(Mesto mesto) {
        this.mesto = mesto;
    }

//    OLD CODE
    //    public Fakultet() {
//    }
//
//    public String getMaticniBroj() {
//        return maticniBroj;
//    }
//
//    public void setMaticniBroj(String maticniBroj) {
//        this.maticniBroj = maticniBroj;
//    }
//
//    public String getNaziv() {
//        return naziv;
//    }
//
//    public void setNaziv(String naziv) {
//        this.naziv = naziv.substring(0,1).toUpperCase() + naziv.substring(1);
//    }
//
//    public Mesto getMesto() {
//        return mesto;
//    }
//
//    public void setMesto(Mesto mesto) {
//        this.mesto = mesto;
//    }
//
//    public List<Student> getStudenti() {
//        return studenti;
//    }
//
//    public void setStudenti(List<Student> studenti) {
//        this.studenti = studenti;
//    }
//
//    public void registrujFakultetZaStudenta(Student student){
//        studenti.add(student);
//        student.getFakulteti().add(this);
//    }

    @Override
    public String toString(){
        return "Fakultet{ " + "maticni broj= " + maticniBroj + ", naziv= " + naziv + '\'' + '}';
    }

    //FON Maticni broj = 07004044
    //Saobracajni Maticni broj = 07032587
    //Masinski Maticni broj = 07032501
    //Gradjevincski Matacni broj = 07006454
}
