package com.marija.students.dto;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

public class StudentDto {

    @NotNull
    @Pattern(regexp = "^[0-9]{2}+[/]{1}+[0-9]{1,4}$", message = "Broj indeksa mora biti u formatu 21/3720, dve cifre za godinu i maksimalno 4 cifre za redni broj")
    private String brojIndeksa;

    @NotNull
    @Pattern(regexp = "^[A-Z]+[a-zA-Z\s]{2,35}$", message = "Naziv mora pocinjati velikim slovom i broj karaktera mora biti min 2 a max 35")
    private String ime;

    @NotNull
    @Pattern(regexp = "^[A-Z]+[a-zA-Z\s]{2,35}$", message = "Naziv mora pocinjati velikim slovom i broj karaktera mora biti min 2 a max 35")
    private String prezime;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Past(message = "Unesite validan datum.")
    private LocalDate datumRodjenja;

    @Pattern(regexp="^[0-9]{13}",message ="JMBG mora imati 13 cifara")
    private String jmbg;

    private int starost;

    private String fakultetId;

    public String getBrojIndeksa() {
        return brojIndeksa;
    }

    public void setBrojIndeksa(String brojIndeksa) {
        this.brojIndeksa = brojIndeksa;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public LocalDate getDatumRodjenja() {
        return datumRodjenja;
    }

    public void setDatumRodjenja(LocalDate datumRodjenja) {
        this.datumRodjenja = datumRodjenja;
    }

    public String getJmbg() {
        return jmbg;
    }

    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
    }

    public int getStarost() {
        return starost;
    }

    public void setStarost(int starost) {
        this.starost = starost;
    }

    public String getFakultetId() {
        return fakultetId;
    }

    public void setFakultetId(String fakultetId) {
        this.fakultetId = fakultetId;
    }
}
