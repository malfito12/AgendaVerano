package com.example.prueba2agenda.entidades;

public class Fecha {
    private String fecha;
    private String hora;
    private String descripsion;

    public Fecha() {
        this.fecha = fecha;
        this.hora = hora;
        this.descripsion = descripsion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getDescripsion() {
        return descripsion;
    }

    public void setDescripsion(String descripsion) {
        this.descripsion = descripsion;
    }
}
