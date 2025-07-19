/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.drivequestrentals.modelo;

/**
 * Interfaz CalculoBoleta, la cual contiene constantes y métodos para calcular
 * el valor de la boleta.
 * @author jennifer
 */

public interface CalculoBoleta {
    // Constantes monetarias
    final double IVA = 1.19;                    // +19%
    final double DESCUENTO_CARGA = 0.93;        // -7%
    final double DESCUENTO_PASAJEROS = 0.88;    // -12%
    
    // Método abstracto
    void calcularBoleta();
}
