package org.example.dao;

import jakarta.persistence.*;
import org.example.model.Interaccion;
import org.example.model.Motivo;
import org.example.model.Empleado;
import org.example.model.Profesion;
import org.example.model.Animal;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class InteraccionDAO {

    private static final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("zoologicoPU");

    // --- INSERTAR un alquiler ---
    public void insertar(Interaccion i) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(i);
            em.getTransaction().commit();
        } finally {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            em.close();
        }
    }

    // --- LISTAR todos los alquileres ---
    public List<Interaccion> listar() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT i FROM Interaccion i", Interaccion.class)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    // --- BUSCAR alquiler por ID ---
    public Interaccion buscarPorId(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Interaccion.class, id);
        } finally {
            em.close();
        }
    }

    // --- ACTUALIZAR un alquiler ---
    public void actualizar(Interaccion i) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(i);
            em.getTransaction().commit();
        } finally {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            em.close();
        }
    }

    // --- ELIMINAR un alquiler ---
    public void eliminar(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Interaccion i = em.find(Interaccion.class, id);
            if (i != null) {
                em.remove(i);
            }
            em.getTransaction().commit();
        } finally {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            em.close();
        }
    }
    // ===================== NUEVOS METODOS =====================

    // 1. Interacciones recibidas de un animal
    public List<Interaccion> interaccionesDeAnimal(int numeroChip) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery(
                            "SELECT i FROM Interaccion i WHERE i.animal.numeroChip = :chip", Interaccion.class)
                    .setParameter("chip", numeroChip)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    // 2. Interacciones realizadas por un empleado
    public List<Interaccion> interaccionesDeEmpleado(String dni) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery(
                            "SELECT i FROM Interaccion i WHERE i.empleado.dni = :dni", Interaccion.class)
                    .setParameter("dni", dni)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    // 3. Todas las interacciones de salud de un animal
    public List<Interaccion> interaccionesSaludDeAnimal(int numeroChip) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery(
                            "SELECT i FROM Interaccion i WHERE i.animal.numeroChip = :chip AND i.motivo = :motivo", Interaccion.class)
                    .setParameter("chip", numeroChip)
                    .setParameter("motivo", Motivo.CONTROLSALUD)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    // 4. Listado de empleados por tipo de profesión
    public List<Empleado> empleadosPorProfesion(Profesion profesion) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery(
                            "SELECT e FROM Empleado e WHERE e.profesion = :profesion", Empleado.class)
                    .setParameter("profesion", profesion)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public List<Interaccion> interaccionesPorFecha(LocalDate fecha) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery(
                            "SELECT i FROM Interaccion i WHERE i.fecha = :fecha", Interaccion.class)
                    .setParameter("fecha", fecha)
                    .getResultList();
        } finally {
            em.close();
        }
    }


    public List<Animal> animalesSinAlimentacionHoy(LocalDate fecha) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery(
                            "SELECT a FROM Animal a " +
                                    "WHERE a.numeroChip NOT IN (" +
                                    "   SELECT i.animal.numeroChip FROM Interaccion i " +
                                    "   WHERE i.motivo = :motivo AND i.fecha = :fecha" +
                                    ")", Animal.class)
                    .setParameter("motivo", Motivo.ALIMENTACION)
                    .setParameter("fecha", fecha)
                    .getResultList();
        } finally {
            em.close();
        }
    }



}

