package org.example.dao;
import jakarta.persistence.*;
import org.example.model.Empleado;

import java.util.List;

public class EmpleadoDAO {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("zoologicoPU");

    public void insertar(Empleado e) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(e);
            em.getTransaction().commit();
        } finally {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            em.close();
        }
    }

    public List<Empleado> listar() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT e FROM Empleado e", Empleado.class).getResultList();
        } finally {
            em.close();
        }
    }

    public List<String> listarDnis() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT e.dni FROM Empleado e", String.class)
                    .getResultList();
        } finally {
            em.close();
        }
    }


    public Empleado buscarPorDni(String dni) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Empleado.class, dni);
        } finally {
            em.close();
        }
    }

    public void actualizar(Empleado e) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(e);
            em.getTransaction().commit();
        } finally {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            em.close();
        }
    }

    public void eliminar(String dni) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Empleado e = em.find(Empleado.class, dni);
            if (e != null) em.remove(e);
            em.getTransaction().commit();
        } finally {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            em.close();
        }
    }
}
