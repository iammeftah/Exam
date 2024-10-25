package com.cinetheque.bean;

import com.cinetheque.entity.CD;
import com.cinetheque.service.UserService;
import jakarta.ejb.EJB;
import jakarta.faces.view.ViewScoped;

import java.io.Serializable;
import java.util.List;

@ManagedBean
@ViewScoped
public class CatalogueBean implements Serializable {
    @EJB
    private UserService userService;

    private List<CD> cdsDisponibles;
    private String utilisateurConnecte = "user1"; // À remplacer par authentification

    public void init() {
        refreshCDs();
    }

    public void refreshCDs() {
        cdsDisponibles = userService.getCDsDisponibles();
    }

    public void emprunterCD(Long cdId) {
        userService.emprunterCD(cdId, utilisateurConnecte);
        refreshCDs();
    }

    // Getters et setters
    public List<CD> getCdsDisponibles() { return cdsDisponibles; }
    public void setCdsDisponibles(List<CD> cdsDisponibles) { this.cdsDisponibles = cdsDisponibles; }
}