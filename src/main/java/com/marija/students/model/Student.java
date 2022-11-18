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


//    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
//    @NotNull
//    @JoinTable(
//            name = "studira",
//            joinColumns = @JoinColumn(name = "student_broj_indeksa", referencedColumnName = "broj_indeksa"),
//            inverseJoinColumns = @JoinColumn(name = "fakultet_maticni_broj", referencedColumnName = "maticni_broj"))
//    private List<Fakultet> fakulteti;



}
