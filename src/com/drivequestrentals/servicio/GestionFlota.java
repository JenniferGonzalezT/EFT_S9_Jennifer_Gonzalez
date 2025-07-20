/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.drivequestrentals.servicio;

import com.drivequestrentals.modelo.*;

import java.io.*;
import java.util.*;

/**
 * Clase auxiliar GestionFlota dedicada a la gestión integral de la flota de vehículos.
 * @author jennifer
 */

public class GestionFlota {
    private final Map<String, Vehiculo> flota = Collections.synchronizedMap(new HashMap<>());
    
    // Método para agregar vehículos únicos
    public boolean agregarVehiculo(Vehiculo vehiculo) {
        synchronized (flota) {
            String patente = vehiculo.getPatente().toUpperCase();
            if (flota.containsKey(patente)) {
                System.out.println("La patente " + patente + " ya se encuentra en la flota de vehículos.");
                return false;
            }
            flota.put(patente, vehiculo);
            return true;
        }
    }
    
    // Método que devuelve si la patente se encuentra en la flota
    public boolean existePatente(String patente) {
        synchronized (flota) {
            return flota.containsKey(patente.toUpperCase());
        }
    }
    
    // Método para listar todos los vehículos registrados
    public void listarVehiculos() {
        synchronized (flota) {
            if (flota.isEmpty()) {
                System.out.println("Aún no hay vehículos en la flota para mostrar.");
                return;
            }
            
            for (Vehiculo vehiculo : flota.values()) {
                vehiculo.mostrarDatos();
                System.out.println();
            }
        }
    }
    
    // Método para filtrar y contar vehiculos por arriendo largo (>= 7 dias)
    public int contarVehiculosConArriendoLargo() {
        synchronized (flota) {
            int contador = 0;
            for (Vehiculo vehiculo : flota.values()) {
                if (vehiculo.getDiasDeArriendo() >= 7) {
                    contador++;
                }
            }
            return contador;
        }
    }
    
    // Método para mostrar las boletas
    public void mostrarBoletas() {
        synchronized (flota) {
            if (flota.isEmpty()) {
                System.out.println("No hay boletas para mostrar.");
                return;
            }
            
            for (Vehiculo vehiculo : flota.values()) {
                if (vehiculo instanceof CalculoBoleta calculoBoleta) {
                    calculoBoleta.calcularBoleta();
                }
                System.out.println();
            }
        }
    }

    // Método para guardar vehículos en un archivo csv
    public void guardarVehiculosCSV(String archivo) {
        if (flota.isEmpty()) {
            System.out.println("No hay vehículos para guardar.");
            return;
        }
        
        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(archivo))) {
            escritor.write("tipo,patente,marca,modelo,year,precioPorDia,diasDeArriendo,capacidad"); // Encabezado
            escritor.newLine();

            for (Vehiculo vehiculo : flota.values()) {
                String linea= "";

                if (vehiculo instanceof VehiculoCarga vc) {
                    linea = "CARGA," + vc.getPatente() + "," + vc.getMarca() + "," + vc.getModelo() + "," + vc.getYear() + "," + vc.getPrecioPorDia() + "," + vc.getDiasDeArriendo() + "," + vc.getCapacidadCarga();
                } else if (vehiculo instanceof VehiculoPasajeros vp) {
                    linea = "PASAJEROS," + vp.getPatente() + "," + vp.getMarca() + "," + vp.getModelo() + "," + vp.getYear() + "," + vp.getPrecioPorDia() + "," + vp.getDiasDeArriendo() + "," + vp.getCapacidadPasajeros();
                }

                escritor.write(linea);
                escritor.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al guardar los vehículos en el archivo: " + e.getMessage());
        }

        System.out.println("Los vehículos han sido guardados correctamente en el archivo.");
    }
    
}
