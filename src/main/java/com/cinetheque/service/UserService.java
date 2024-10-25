package com.cinetheque.service;

import com.cinetheque.entity.CD;
import com.cinetheque.entity.Emprunt;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.*;

@Stateless
public class UserService {
    @PersistenceContext
    private EntityManager em;

    public List<CD> getCDsDisponibles() {
        return em.createQuery("SELECT c FROM CD c WHERE c.disponible = true", CD.class)
                .getResultList();
    }

    public void emprunterCD(Long cdId, String emprunteur) {
        CD cd = em.find(CD.class, cdId);
        if (cd != null && cd.isDisponible()) {
            cd.setDisponible(false);

            Emprunt emprunt = new Emprunt();
            emprunt.setCd(cd);
            emprunt.setEmprunteur(emprunteur);
            emprunt.setDateEmprunt(new Date());

            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DAY_OF_MONTH, 14);
            emprunt.setDateRetourPrevue(cal.getTime());

            em.persist(emprunt);
        }
    }

    public List<Emprunt> getEmpruntsUtilisateur(String emprunteur) {
        return em.createQuery(
                        "SELECT e FROM Emprunt e WHERE e.emprunteur = :emprunteur",
                        Emprunt.class)
                .setParameter("emprunteur", emprunteur)
                .getResultList();
    }

    public void retournerCD(Long empruntId) {
        Emprunt emprunt = em.find(Emprunt.class, empruntId);
        if (emprunt != null) {
            CD cd = emprunt.getCd();
            cd.setDisponible(true);
            em.remove(emprunt);
        }
    }
}