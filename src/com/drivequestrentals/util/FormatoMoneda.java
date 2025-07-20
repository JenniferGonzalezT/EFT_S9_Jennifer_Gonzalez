/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.drivequestrentals.util;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * Clase FormatoMoneda para darle el formato de pesos chilenos a la moneda.
 * @author jennifer
 */

public class FormatoMoneda {
    private static final Locale LOCALE_CL = Locale.forLanguageTag("es-CL");
    private static final NumberFormat CLP = NumberFormat.getCurrencyInstance(LOCALE_CL);
    
    // Constructor privado para evitar que se instancie la clase
    private FormatoMoneda() {
    }
    
    // Método para darle el formato de pesos chilenos
    public static String formatearCLP(double valor) {
        return CLP.format(valor);
    }
    
}
