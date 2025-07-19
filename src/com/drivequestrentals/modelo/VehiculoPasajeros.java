/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.drivequestrentals.modelo;

import com.drivequestrentals.util.FormatoMoneda;

/**
 * Clase VehiculoPasajeros que hereda de la clase abstracta Vehiculo e
 * implementa la interfaz CalculoBoleta.
 * @author jennifer
 */

public class VehiculoPasajeros extends Vehiculo implements CalculoBoleta {
    // Atributo específico
    private int capacidadPasajeros;

    // Constructores
    public VehiculoPasajeros() {
    }

    public VehiculoPasajeros(int capacidadPasajeros) {
        if (capacidadPasajeros <= 0) {
            throw new IllegalArgumentException("La capacidad de pasajeros no es válida (debe ser mayor a 0).");
        }
        this.capacidadPasajeros = capacidadPasajeros;
    }

    public VehiculoPasajeros(int capacidadPasajeros, String patente, String marca, String modelo, int year, int precioPorDia, int diasDeArriendo) {
        super(patente, marca, modelo, year, precioPorDia, diasDeArriendo);
        if (capacidadPasajeros <= 0) {
            throw new IllegalArgumentException("La capacidad de pasajeros no es válida (debe ser mayor a 0).");
        }
        this.capacidadPasajeros = capacidadPasajeros;
    }

    // Métodos Getter y Setter específicos
    public int getCapacidadPasajeros() {
        return capacidadPasajeros;
    }

    public void setCapacidadPasajeros(int capacidadPasajeros) {
        if (capacidadPasajeros <= 0) {
            throw new IllegalArgumentException("La capacidad de pasajeros no es válida (debe ser mayor a 0).");
        }
        this.capacidadPasajeros = capacidadPasajeros;
    }
    
    
    // Sobreescritura del método abstracto heredado para mostar los datos del vehículo de pasajeros
    @Override
    public void mostrarDatos() {
        System.out.println("Vehículo de Pasajeros:"
            + "\n-> Capacidad máxima de pasajeros: " + capacidadPasajeros
            + "\n-> Patente: " + patente
            + "\n-> Marca: " + marca
            + "\n-> Modelo: " + modelo
            + "\n-> Año: " + year
            + "\n-> Precio por día: " + FormatoMoneda.formatearCLP(precioPorDia)
            + "\n-> Días de arriendo: " + diasDeArriendo
        );
    }

    // Sobreescritura del método implementado de la interfaz para calcular y mostrar la boleta
    @Override
    public void calcularBoleta() {
        int precioBase = precioPorDia * diasDeArriendo;
        int precioConDescuento = (int) (precioBase * DESCUENTO_PASAJEROS);
        int descuento = precioBase - precioConDescuento;
        int precioFinalConIVA = (int) (precioConDescuento * IVA);
        
        System.out.println("Boleta Vehículo de Pasajeros:"
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
