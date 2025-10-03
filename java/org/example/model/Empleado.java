package org.example.model;
import jakarta.persistence.*;

@Entity
@Table(name = "empleados")
public class Empleado {
    @Id
    private String dni;
    private Profesion  profesion;
    private String nombre;
    private int experiencia;

    //Constructor vacio
    public Empleado() {
    }
    //Constructor completo
    public Empleado(int experiencia, String nombre, Profesion profesion, String dni) {
        this.experiencia = experiencia;
        this.nombre = nombre;
        this.profesion = profesion;
        this.dni = dni;
    }

    //Getters & Setters
    public String getDni() {
        return dni;
    }
    public void setDni(String dni) {
        this.dni = dni;
    }

    public Profesion getProfesion() {
        return profesion;
    }
    public void setProfesion(Profesion profesion) {
        this.profesion = profesion;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getExperiencia() {
        return experiencia;
    }
    public void setExperiencia(int experiencia) {
        this.experiencia = experiencia;
    }

    @Override
    public String toString() {
        return dni + " - " + (nombre != null ? nombre : "");
    }

}
