/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.drivequestrentals.hilo;

import com.drivequestrentals.modelo.*;

import java.io.*;
import java.util.concurrent.BlockingQueue;

/**
 * Clase para Hilo que implementa Runnable para leer vehículos desde el archivo
 * y ponerlos en una cola compartida.
 * @author jennifer
 */

public class HiloCarga implements Runnable {
    private final BlockingQueue<Vehiculo> colaVehiculos;
    private final String archivo;
    private final Coordinador coordinador;

    public HiloCarga(BlockingQueue<Vehiculo> colaVehiculos, String archivo, Coordinador coordinador) {
        this.colaVehiculos = colaVehiculos;
        this.archivo = archivo;
        this.coordinador = coordinador;
    }
    
    // Agregar los vehículos a la cola desde la lectura del archivo csv
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + ": Cargando vehículos desde el archivo a la cola...");
        
        try (BufferedReader lector = new BufferedReader(new FileReader(archivo))) {
            String linea;
            lector.readLine(); // Encabezado

            while ((linea = lector.readLine()) != null) {
                String[] datos = linea.split(",");

                if (datos.length == 8) {
                    try {
                        String tipo = datos[0].trim().toUpperCase();
                        String patente = datos[1].trim().toUpperCase();
                        String marca = datos[2].trim();
                        String modelo = datos[3].trim();
                        int year = Integer.parseInt(datos[4].trim());
                        int precioPorDia = Integer.parseInt(datos[5].trim());
                        int diasDeArriendo = Integer.parseInt(datos[6].trim());
                        
                        Vehiculo vehiculo = null;
                        
                        if (tipo.equalsIgnoreCase("CARGA")) {
                            double capacidadCarga = Double.parseDouble(datos[7].trim());
                            vehiculo = new VehiculoCarga(capacidadCarga, patente, marca, modelo, year, precioPorDia, diasDeArriendo);
                        } else if (tipo.equalsIgnoreCase("PASAJEROS")) {
                            int capacidadPasajeros = Integer.parseInt(datos[7].trim());
                            vehiculo = new VehiculoPasajeros(capacidadPasajeros, patente, marca, modelo, year, precioPorDia, diasDeArriendo);
                        } else {
                            System.out.println("Carga Vehículo omitida \"" + linea + "\": El tipo de vehículo no es válido.");
                        }
                        
                        if (vehiculo != null) {
                            colaVehiculos.put(vehiculo);
                        }
                        
                    } catch (IllegalArgumentException e) {
                        System.out.println("Carga Vehículo omitida \"" + linea + "\": " + e.getMessage());
                    }

                } else {
                    System.out.println("Carga Vehículo omitida \"" + linea + "\": El formato no es válido.");
                }
            }
            System.out.println("Vehículos cargados correctamente desde el archivo.");
            coordinador.cargaLista();
        } catch (IOException ea) {
            System.out.println("Error al cargar los vehículos desde el archivo: " + ea.getMessage());
            coordinador.errorEnCarga();
        } catch (InterruptedException eh) {
            System.out.println(Thread.currentThread().getName() + " interrumpido: " + eh.getMessage());
            Thread.currentThread().interrupt();
        }
            
        System.out.println(Thread.currentThread().getName() + ": Ha finalizado.");
    }
}
