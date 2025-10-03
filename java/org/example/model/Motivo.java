package org.example.model;

public enum Motivo {
    CONTROLSALUD("Control de salud"),
    LIMPIEZA("Limpieza"),
    ALIMENTACION("Alimentación"),
    ADIESTRAMIENTO("Adiestramiento");

    private final String descripcion;

    // Constructor
    Motivo(String descripcion) {
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
