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
    protected double precioPorDia;
    protected int diasDeArriendo;

    public Vehiculo() {
    }

    public Vehiculo(String patente, String marca, String modelo, int year, double precioPorDia, int diasDeArriendo) {
        this.patente = patente;
        this.marca = marca;
        this.modelo = modelo;
        this.year = year;
        this.precioPorDia = precioPorDia;
        this.diasDeArriendo = diasDeArriendo;
    }

    public String getPatente() {
        return patente;
    }

    public void setPatente(String patente) {
        this.patente = patente;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getPrecioPorDia() {
        return precioPorDia;
    }

    public void setPrecioPorDia(double precioPorDia) {
        this.precioPorDia = precioPorDia;
    }

    public int getDiasDeArriendo() {
        return diasDeArriendo;
    }

    public void setDiasDeArriendo(int diasDeArriendo) {
        this.diasDeArriendo = diasDeArriendo;
    }
    
    public abstract void mostrarDatos();
    
}
