package com.marija.students.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;

@Entity
@Table(name = "fakulteti")
public class Fakultet {

    @Id
    @NotNull
    @Pattern(regexp="^[0-9a-zA-Z]{8}",message ="Maticni broj mora imati 8 cifara")
    @Column(name = "maticni_broj")
    private String maticniBroj;

    @NotNull
    @Pattern(regexp = "^[A-Z]+[a-zA-Z\s]{2,35}$", message = "Naziv mora pocinjati velikim slovom i broj karaktera mora biti min 2 a max 35")
    @Column(name = "naziv")
    private String naziv;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "mesto_id", referencedColumnName = "ptt") // FK
    private Mesto mesto;

    @JsonIgnore
    @ManyToMany()
    @JoinTable(
            name = "studira",
            joinColumns = @JoinColumn(name = "fakultetID", referencedColumnName = "maticni_broj"),
            inverseJoinColumns = @JoinColumn(name = "studentID", referencedColumnName = "broj_indeksa")
    )
    private List<Student>studenti;


    public Fakultet(){

    }

    public Fakultet(String maticniBroj, String naziv, Mesto mesto) {
        this.maticniBroj = maticniBroj;
        this.naziv = naziv;
        this.mesto = mesto;
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

    public List<Student> getStudenti() {
        return studenti;
    }

    public void setStudenti(List<Student> studenti) {
        this.studenti = studenti;
    }

    public void addStudent(Student student){
        studenti.add(student);
    }

    @Override
    public String toString(){
        return "Fakultet[maticni broj= " + maticniBroj + ", naziv= " + naziv + ", mesto ptt = "+ mesto.getPtt() +"]";
    }

    //FON Maticni broj = 07004044
    //Saobracajni Maticni broj = 07032587
    //Masinski Maticni broj = 07032501
    //Gradjevincski Matacni broj = 07006454
}
