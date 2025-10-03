package org.example.dao;
import jakarta.persistence.*;
import org.example.model.Animal;

import java.util.List;

public class AnimalDAO {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("zoologicoPU");

    public void insertar(Animal a) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(a);
            em.getTransaction().commit();
        } finally {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            em.close();
        }
    }

    public List<Animal> listar() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT a FROM Animal a", Animal.class).getResultList();
        } finally {
            em.close();
        }
    }

    public List<String> listarDnis() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT a.numeroChip FROM Animal a", String.class)
                    .getResultList();
        } finally {
            em.close();
        }
    }


    public Animal buscarPorChip(int chip) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Animal.class, chip);
        } finally {
            em.close();
        }
    }

    public void actualizar(Animal a) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(a);
            em.getTransaction().commit();
        } finally {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            em.close();
        }
    }

    public void eliminar(int chip) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Animal a = em.find(Animal.class, chip);
            if (a != null) em.remove(a);
            em.getTransaction().commit();
        } finally {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            em.close();
        }
    }
}
