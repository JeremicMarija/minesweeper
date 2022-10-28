package com.marija.students.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "student")
public class Student {

    @Id
    @NotNull
    @Column(name = "broj_indeksa")
    private String brojIndeksa;

    @NotNull
    @Column(name = "ime")
    private String ime;

    @NotNull
    @Column(name = "prezime")
    private String prezime;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Past(message = "Unesite validan datum.")
    @Column(name = "datum_rodjenja")
    private LocalDate datumRodjenja;

    @Min(value=13, message="JMBG mora imati 13 cifara")
    @Max(value=13, message="JMBG mora imati 13 cifara")
    @Column(name = "jmbg")
    private long jmbg;

    @Column(name = "starost")
    private int starost;

    //  @ManyToMany(mappedBy = "studenti", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @NotNull
    @JoinTable(
            name = "studira",
            joinColumns = @JoinColumn(name = "student_broj_indeksa", referencedColumnName = "broj_indeksa"),
            inverseJoinColumns = @JoinColumn(name = "fakultet_maticni_broj", referencedColumnName = "maticni_broj"))
    private List<Fakultet> fakulteti;


    public Student(String brojIndeksa, String ime, String prezime, LocalDate datumRodjenja, long jmbg ) {
        this.brojIndeksa = brojIndeksa;
        this.ime = ime;
        this.prezime = prezime;
        this.datumRodjenja = datumRodjenja;
        this.jmbg = jmbg;
        this.starost = LocalDate.now().getYear() - datumRodjenja.getYear();
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
        this.ime = ime.substring(0,1).toUpperCase() + ime.substring(1);
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime.substring(0,1).toUpperCase() + prezime.substring(1);
    }

    public LocalDate getDatumRodjenja() {
        return datumRodjenja;
    }

    public void setDatumRodjenja(LocalDate datumRodjenja) {
        this.datumRodjenja = datumRodjenja;
    }

    public long getJmbg() {
        return jmbg;
    }

    public void setJmbg(long jmbg) {
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

    public void dodajFakultet(Fakultet fakultet){
        this.fakulteti.add(fakultet);
        fakultet.getStudenti().add(this);
    }
    public void ukloniFakultet(String maticniBroj){
        Fakultet fakultet = this.fakulteti.stream().filter(f -> f.getMaticniBroj() == maticniBroj).findFirst().orElse(null);
        if(fakultet != null){
            this.fakulteti.remove(fakultet);
            fakultet.getStudenti().remove(this);
        }
    }
}
