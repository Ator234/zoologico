package org.example.model;

public enum Profesion {
    VETERINARIO("Veterinario"),
    MANTENIMIENTO("Mantenimiento"),
    EDUCADOR("Educador");

    private final String descripcion;

    // Constructor
    Profesion(String descripcion) {
        this.descripcion = descripcion;
    }

    // Getter
    public String getDescripcion() {
        return descripcion;
    }

    @Override
    public String toString() {
        return descripcion;
    }
}
