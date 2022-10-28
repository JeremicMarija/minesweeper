package com.marija.students.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "mesto")
public class Mesto implements Serializable {

    @Id
    @Basic(optional = false)
    @Min(value=5, message="Ptt mora imati 5 cifara")
    @Column(name = "ptt")
    private Long ptt;

    @NotNull
    @Size(max = 35,min = 2,message = "Broj karaktera mora biti min 2 a max 35")
    @Column(name = "naziv")
    private String naziv;

    @Min(value = 1, message = "Broj stanovnika mora biti veci od nule")
    @Column(name = "broj_stanovnika")
    private Long brojStanovnika;

//    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "fakultet_maticni_broj", referencedColumnName = "maticni_broj")
//    @NotNull
//    private Fakultet fakultet;

//    @OneToMany(mappedBy = "mesto")
//    @JsonIgnore
//    private List<Fakultet> fakulteti;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "mesto_ptt")
    private List<Fakultet> fakulteti = new ArrayList<>();


    public Mesto() {
    }

    public void addFakultet(Fakultet fakultet){
        fakulteti.add(fakultet);
    }

    public void removeFakultet(Fakultet fakultet){
        fakulteti.remove(fakultet);
    }

    public Mesto(Long ptt, String naziv, Long brojStanovnika, List<Fakultet> fakulteti) {
        this.ptt = ptt;
        this.naziv = naziv;
        this.brojStanovnika = brojStanovnika;
        this.fakulteti = fakulteti;
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

    public List<Fakultet> getFakulteti() {
        return fakulteti;
    }

    public void setFakulteti(List<Fakultet> fakulteti) {
        this.fakulteti = fakulteti;
    }


//    OLD CODE
    //    public Mesto(Long ptt, String naziv, Long brojStanovnika) {
//        this.ptt = ptt;
//        this.naziv = naziv;
//        this.brojStanovnika = brojStanovnika;
//    }
//
//    public Long getPtt() {
//        return ptt;
//    }
//
//    public void setPtt(Long ptt) {
//        this.ptt = ptt;
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
//    public Long getBrojStanovnika() {
//        return brojStanovnika;
//    }
//
//    public void setBrojStanovnika(Long brojStanovnika) {
//        this.brojStanovnika = brojStanovnika;
//    }
//
//    public List<Fakultet> getFakulteti() {
//        return fakulteti;
//    }
//
//    public void setFakulteti(List<Fakultet> fakulteti) {
//        this.fakulteti = fakulteti;
//    }
//
//    @Override
//    public int hashCode() {
//        int hash = 0;
//        hash += (ptt != null ? ptt.hashCode() : 0);
//        return hash;
//    }
//
//    @Override
//    public boolean equals(Object object){
//        if(!(object instanceof Mesto)){
//            return false;
//        }
//        Mesto other = (Mesto) object;
//        if ((this.ptt == null && other.ptt != null) || (this.ptt != null && !this.ptt.equals(other.ptt))){
//            return false;
//        }
//        return true;
//    }

    @Override
    public String toString(){
        return "domen.Mesto [ ptt= " + ptt + " ]";
    }
}
