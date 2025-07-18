/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.drivequestrentals.modelo;

/**
 * Clase VehiculoCarga que hereda de la clase abstracta Vehiculo e 
 * implementa la interfaz CalculoBoleta.
 * @author jennifer
 */

public class VehiculoCarga extends Vehiculo implements CalculoBoleta {
    private double capacidadCarga;

    public VehiculoCarga() {
    }

    public VehiculoCarga(double capacidadCarga) {
        this.capacidadCarga = capacidadCarga;
    }

    public VehiculoCarga(double capacidadCarga, String patente, String marca, String modelo, int year, int precioPorDia, int diasDeArriendo) {
        super(patente, marca, modelo, year, precioPorDia, diasDeArriendo);
        this.capacidadCarga = capacidadCarga;
    }

    public double getCapacidadCarga() {
        return capacidadCarga;
    }

    public void setCapacidadCarga(double capacidadCarga) {
        this.capacidadCarga = capacidadCarga;
    }
    
    
    @Override
    public void mostrarDatos() {
        System.out.println("Vehículo de Carga:"
            + "\n-> Capacidad de carga: " + capacidadCarga + " kilos."
            + "\n-> Patente: " + patente
            + "\n-> Marca: " + marca
            + "\n-> Modelo: " + modelo
            + "\n-> Año: " + year
            + "\n-> Precio por día: " + precioPorDia
            + "\n-> Días de arriendo: " + diasDeArriendo
        );
    }

    @Override
    public void calcularBoleta() {
        double precioBase = precioPorDia * diasDeArriendo;
        double precioConDescuento = precioBase * DESCUENTO_CARGA;
        double descuento = precioBase - precioConDescuento;
        double precioFinalConIVA = precioConDescuento * IVA;
        
        System.out.println("Boleta Vehículo de Carga:"
            + "\n-> Precio base: $" + precioBase
            + "\n-> Descuento: $" + descuento
            + "\n-> Precio con descuento: $" + precioConDescuento
            + "\n-> IVA 19% aplicado"
            + "\nPrecio final: $" + precioFinalConIVA
        );
    }
    
}
