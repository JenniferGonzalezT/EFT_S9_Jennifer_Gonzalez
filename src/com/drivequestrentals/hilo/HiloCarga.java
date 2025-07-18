/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.drivequestrentals.hilo;

import com.drivequestrentals.servicio.GestionVehiculos;

/**
 * Hilo que hereda de Thread para cargar vehículos.
 * @author jennifer
 */

public class HiloCarga extends Thread {
    private final GestionVehiculos gestor;
    private final String archivo;

    public HiloCarga(GestionVehiculos gestor, String archivo) {
        this.gestor = gestor;
        this.archivo = archivo;
    }
    
    @Override
    public void run() {
        System.out.println("HiloCarga: Cargando vehículos desde archivo...");
        gestor.CargarVehiculosCSV(archivo);
        System.out.println("HiloCarga: Carga finalizada.");
    }
    
}
