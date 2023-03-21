package com.marija.students.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "student")
public class Student {

    @Id
    @NotNull
    @Pattern(regexp = "^[0-9]{2}+[/]{1}+[0-9]{1,4}$", message = "Broj indeksa mora biti u formatu 21/3720, dve cifre za godinu i maksimalno 4 cifre za redni broj")
    @Column(name = "broj_indeksa")
    private String brojIndeksa;

    @NotNull
    @Pattern(regexp = "^[A-Z]+[a-zA-Z\s]{2,35}$", message = "Naziv mora pocinjati velikim slovom i broj karaktera mora biti min 2 a max 35")
    @Column(name = "ime")
    private String ime;

    @NotNull
    @Pattern(regexp = "^[A-Z]+[a-zA-Z\s]{2,35}$", message = "Naziv mora pocinjati velikim slovom i broj karaktera mora biti min 2 a max 35")
    @Column(name = "prezime")
    private String prezime;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Past(message = "Unesite validan datum.")
    @Column(name = "datum_rodjenja")
    private LocalDate datumRodjenja;


//    @Digits(integer = 13, fraction = 0, message = "JMBG mora imati 13 cifara")
    @Pattern(regexp="^[0-9]{13}",message ="JMBG mora imati 13 cifara")
    @Column(name = "jmbg")
    private String jmbg;

    @Column(name = "starost")
    private int starost;


    @ManyToMany(mappedBy = "studenti")
    private List<Fakultet> fakulteti = new ArrayList<>();

    public Student() {
    }

    public Student(String brojIndeksa, String ime, String prezime, LocalDate datumRodjenja, String jmbg) {
        this.brojIndeksa = brojIndeksa;
        this.ime = ime;
        this.prezime = prezime;
        this.datumRodjenja = datumRodjenja;
        this.jmbg = jmbg;
    }


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

    public List<Fakultet> getFakulteti() {
        return fakulteti;
    }

    public void setFakulteti(List<Fakultet> fakulteti) {
        this.fakulteti = fakulteti;
    }

    public void dodajFakultetZaStudenta(Fakultet fakultet){
        fakulteti.add(fakultet);
        fakultet.getStudenti().add(this);
    }

    public void ukloniFakultetZaStudenta(Fakultet fakultet){
        fakulteti.remove(fakultet);
        fakultet.getStudenti().remove(this);
    }


}
