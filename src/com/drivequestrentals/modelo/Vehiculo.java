/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.drivequestrentals.modelo;

/**
 * Clase abstracta Vehiculo.
 * @author jennifer
 */

public abstract class Vehiculo {
    protected String patente;
    protected String marca;
    protected String modelo;
    protected int year;
    protected int precioPorDia;
    protected int diasDeArriendo;

    public Vehiculo() {
    }

    public Vehiculo(String patente, String marca, String modelo, int year, int precioPorDia, int diasDeArriendo) {
        if (patente.isBlank()) {
            throw new IllegalArgumentException("La patente no puede estar vacía.");
        } else {
            this.patente = patente;
        }
        
        if (marca.isBlank()) {
            throw new IllegalArgumentException("La marca no puede estar vacía.");
        } else {
            this.marca = marca;
        }
        
        if (modelo.isBlank()) {
            throw new IllegalArgumentException("El modelo no puede estar vacío.");
        } else {
            this.modelo = modelo;
        }
        
        int yearActual = java.time.LocalDate.now().getYear();
        if (year < 2000 || year > yearActual) {
            throw new IllegalArgumentException("El año no es válido (rango: 2000 - " + yearActual + ").");
        } else {
            this.year = year;
        }
        
        if (precioPorDia <= 0) {
            throw new IllegalArgumentException("El precio no es válido (debe ser mayor a 0).");
        } else {
            this.precioPorDia = precioPorDia;
        }
        
        if (diasDeArriendo <= 0) {
            throw new IllegalArgumentException("Los días de arriendo no son válidos (deben ser mayor a 0).");
        } else {
            this.diasDeArriendo = diasDeArriendo;
        }
    }

    public String getPatente() {
        return patente;
    }

    public void setPatente(String patente) {
        if (patente.isBlank()) {
            throw new IllegalArgumentException("La patente no puede estar vacía.");
        }
        this.patente = patente;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        if (marca.isBlank()) {
            throw new IllegalArgumentException("La marca no puede estar vacía.");
        }
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        if (modelo.isBlank()) {
            throw new IllegalArgumentException("El modelo no puede estar vacío.");
        }
        this.modelo = modelo;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        int yearActual = java.time.LocalDate.now().getYear();
        if (year < 2000 || year > yearActual) {
            throw new IllegalArgumentException("El año no es válido (rango: 2000 - " + yearActual + ").");
        }
        this.year = year;
    }

    public int getPrecioPorDia() {
        return precioPorDia;
    }

    public void setPrecioPorDia(int precioPorDia) {
        if (precioPorDia <= 0) {
            throw new IllegalArgumentException("El precio no es válido (debe ser mayor a 0).");
        }
        this.precioPorDia = precioPorDia;
    }

    public int getDiasDeArriendo() {
        return diasDeArriendo;
    }

    public void setDiasDeArriendo(int diasDeArriendo) {
        if (diasDeArriendo <= 0) {
            throw new IllegalArgumentException("Los días de arriendo no son válidos (deben ser mayor a 0).");
        }
        this.diasDeArriendo = diasDeArriendo;
    }
    
    public abstract void mostrarDatos();
    
}
