package org.example.model;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "interacciones")
public class Interaccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // Relación N:1 con Empleado
    @ManyToOne
    @JoinColumn(name = "empleado")
    private Empleado empleado;
    // Relación N:1 con Animal
    @ManyToOne
    @JoinColumn(name = "animal")
    private Animal animal;
    private Motivo motivo;
    private LocalDate fecha;

    //Constructor vacio
    public Interaccion() {
    }
    //Constructor completo
    public Interaccion(Long id, Empleado empleado, Animal animal, Motivo motivo, LocalDate fecha) {
        this.id = id;
        this.empleado = empleado;
        this.animal = animal;
        this.motivo = motivo;
        this.fecha = fecha;
    }

    //Getters & Setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Empleado getEmpleado() {
        return empleado;
    }
    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public Animal getAnimal() {
        return animal;
    }
    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public Motivo getMotivo() {
        return motivo;
    }
    public void setMotivo(Motivo motivo) {
        this.motivo = motivo;
    }

    public LocalDate getFecha() {
        return fecha;
    }
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
}
