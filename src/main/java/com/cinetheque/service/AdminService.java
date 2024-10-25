package com.cinetheque.service;

import com.cinetheque.entity.CD;
import com.cinetheque.entity.Emprunt;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class AdminService {
    @PersistenceContext
    private EntityManager em;

    public void ajouterCD(CD cd) {
        cd.setDisponible(true);
        em.persist(cd);
    }

    public void modifierCD(CD cd) {
        em.merge(cd);
    }

    public void supprimerCD(Long cdId) {
        CD cd = em.find(CD.class, cdId);
        if (cd != null) {
            em.remove(cd);
        }
    }

    public List<Emprunt> getAllEmprunts() {
        return em.createQuery("SELECT e FROM Emprunt e", Emprunt.class)
                .getResultList();
    }
}