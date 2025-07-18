/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.drivequestrentals.modelo;

/**
 * Clase VehiculoPasajeros que hereda de la clase abstracta Vehiculo e
 * implementa la interfaz CalculoBoleta.
 * @author jennifer
 */

public class VehiculoPasajeros extends Vehiculo implements CalculoBoleta {
    private int capacidadPasajeros;

    public VehiculoPasajeros() {
    }

    public VehiculoPasajeros(int capacidadPasajeros) {
        this.capacidadPasajeros = capacidadPasajeros;
    }

    public VehiculoPasajeros(int capacidadPasajeros, String patente, String marca, String modelo, int year, int precioPorDia, int diasDeArriendo) {
        super(patente, marca, modelo, year, precioPorDia, diasDeArriendo);
        this.capacidadPasajeros = capacidadPasajeros;
    }

    public int getCapacidadPasajeros() {
        return capacidadPasajeros;
    }

    public void setCapacidadPasajeros(int capacidadPasajeros) {
        this.capacidadPasajeros = capacidadPasajeros;
    }
    
    
    @Override
    public void mostrarDatos() {
        System.out.println("Vehículo de Pasajeros:"
            + "\n-> Capacidad máxima de pasajeros: " + capacidadPasajeros
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
        double precioConDescuento = precioBase * DESCUENTO_PASAJEROS;
        double descuento = precioBase - precioConDescuento;
        double precioFinalConIVA = precioConDescuento * IVA;
        
        System.out.println("Boleta Vehículo de Pasajeros:"
            + "\n-> Precio base: $" + precioBase
            + "\n-> Descuento: $" + descuento
            + "\n-> Precio con descuento: $" + precioConDescuento
            + "\n-> IVA 19% aplicado"
            + "\nPrecio final: $" + precioFinalConIVA
        );
    }
    
}
