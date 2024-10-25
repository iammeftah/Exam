package com.cinetheque.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "emprunts")
public class Emprunt implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cd_id", nullable = false)
    private CD cd;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateEmprunt;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateRetourPrevue;

    private String emprunteur;

    // Getters et setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public CD getCd() { return cd; }
    public void setCd(CD cd) { this.cd = cd; }
    public Date getDateEmprunt() { return dateEmprunt; }
    public void setDateEmprunt(Date dateEmprunt) { this.dateEmprunt = dateEmprunt; }
    public Date getDateRetourPrevue() { return dateRetourPrevue; }
    public void setDateRetourPrevue(Date dateRetourPrevue) { this.dateRetourPrevue = dateRetourPrevue; }
    public String getEmprunteur() { return emprunteur; }
    public void setEmprunteur(String emprunteur) { this.emprunteur = emprunteur; }
}