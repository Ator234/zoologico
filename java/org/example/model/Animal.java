package org.example.model;
import jakarta.persistence.*;

@Entity
@Table(name = "animales")
public class Animal {
    @Id
    private int numeroChip;
    private String apodo;
    private int parcela;
    private String raza;

    //Constructor vacio
    public Animal() {
    }
    //Constructor completo
    public Animal(int numeroChip, String apodo, int parcela, String raza) {
        this.numeroChip = numeroChip;
        this.apodo = apodo;
        this.parcela = parcela;
        this.raza = raza;
    }


    //Getters & Setters
    public int getNumeroChip() {
        return numeroChip;
    }
    public void setNumeroChip(int numeroChip) {
        this.numeroChip = numeroChip;
    }

    public String getApodo() {
        return apodo;
    }
    public void setApodo(String apodo) {
        this.apodo = apodo;
    }

    public int getParcela() {
        return parcela;
    }
    public void setParcela(int parcela) {
        this.parcela = parcela;
    }

    public String getRaza() {
        return raza;
    }
    public void setRaza(String raza) {
        this.raza = raza;
    }

    @Override
    public String toString() {
        return numeroChip + " - " + (apodo != null ? apodo : "");
    }

}
