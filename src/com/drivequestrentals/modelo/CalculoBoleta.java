/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.drivequestrentals.modelo;

/**
 * Interfaz CalculoBoleta.
 * @author jennifer
 */

public interface CalculoBoleta {
    final double IVA = 1.19;                    // +19%
    final double DESCUENTO_CARGA = 0.93;        // -7%
    final double DESCUENTO_PASAJEROS = 0.88;    // -12%
    
    public void calcularBoleta();
}
