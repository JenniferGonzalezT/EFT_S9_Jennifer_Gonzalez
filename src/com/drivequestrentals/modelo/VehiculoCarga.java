/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.drivequestrentals.modelo;

import com.drivequestrentals.util.FormatoMoneda;

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
        if (capacidadCarga <= 0) {
            throw new IllegalArgumentException("La capacidad de carga no es válida (debe ser mayor a 0).");
        }
        this.capacidadCarga = capacidadCarga;
    }

    public VehiculoCarga(double capacidadCarga, String patente, String marca, String modelo, int year, int precioPorDia, int diasDeArriendo) {
        super(patente, marca, modelo, year, precioPorDia, diasDeArriendo);
        if (capacidadCarga <= 0) {
            throw new IllegalArgumentException("La capacidad de carga no es válida (debe ser mayor a 0).");
        }
        this.capacidadCarga = capacidadCarga;
    }

    public double getCapacidadCarga() {
        return capacidadCarga;
    }

    public void setCapacidadCarga(double capacidadCarga) {
        if (capacidadCarga <= 0) {
            throw new IllegalArgumentException("La capacidad de carga no es válida (debe ser mayor a 0).");
        }
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
            + "\n-> Precio por día: " + FormatoMoneda.formatearCLP(precioPorDia)
            + "\n-> Días de arriendo: " + diasDeArriendo
        );
    }

    @Override
    public void calcularBoleta() {
        int precioBase = precioPorDia * diasDeArriendo;
        int precioConDescuento = (int) (precioBase * DESCUENTO_CARGA);
        int descuento = precioBase - precioConDescuento;
        int precioFinalConIVA = (int) (precioConDescuento * IVA);
        
        System.out.println("Boleta Vehículo de Carga:"
            + "\n-> Patente: " + patente
            + "\n-> Precio por día: " + FormatoMoneda.formatearCLP(precioPorDia)
            + "\n-> Días de arriendo: " + diasDeArriendo
            + "\n-> Precio base: $" + FormatoMoneda.formatearCLP(precioBase)
            + "\n-> Descuento: $" + FormatoMoneda.formatearCLP(descuento)
            + "\n-> Precio con descuento: $" + FormatoMoneda.formatearCLP(precioConDescuento)
            + "\n-> IVA 19% aplicado"
            + "\nPrecio final: $" + FormatoMoneda.formatearCLP(precioFinalConIVA)
        );
    }
    
}
