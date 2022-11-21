package com.marija.students.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "mesto")
public class Mesto {


//    @Range(min=5, max=6, message ="Ptt mora imati 5 cifara")
    @Id
    @NotNull
    @Digits(integer = 5, fraction = 0, message = "Ptt mora imati 5 cifara")
    @Column(name = "ptt")
    private Long ptt;

    @NotNull
    @Pattern(regexp = "^[A-Z]+[a-zA-Z\s]{2,35}$", message = "Naziv mora pocinjati velikim slovom i broj karaktera mora biti min 2 a max 35")
    @Column(name = "naziv")
    private String naziv;

    @NotNull
    @Min(value = 1, message = "Broj stanovnika mora biti veci od nule")
    @Column(name = "broj_stanovnika")
    private Long brojStanovnika;

//    @OneToMany(fetch = FetchType.EAGER, mappedBy = "mesto", cascade = CascadeType.ALL)
//    @JsonIgnoreProperties("mesto")
//    private List<Fakultet> fakultetList = new ArrayList<>();


    public Mesto() {

    }

    public Mesto(Long ptt, String naziv, Long brojStanovnika) {
        this.ptt = ptt;
        this.naziv = naziv;
        this.brojStanovnika = brojStanovnika;
    }

    public Long getPtt() {
        return ptt;
    }

    public void setPtt(Long ptt) {
        this.ptt = ptt;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Long getBrojStanovnika() {
        return brojStanovnika;
    }

    public void setBrojStanovnika(Long brojStanovnika) {
        this.brojStanovnika = brojStanovnika;
    }

    @Override
    public String toString(){
        return "Mesto [ ptt= " + ptt + " ]";
    }
}
