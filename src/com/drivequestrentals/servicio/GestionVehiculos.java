/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.drivequestrentals.servicio;

import com.drivequestrentals.modelo.CalculoBoleta;
import com.drivequestrentals.modelo.VehiculoPasajeros;
import com.drivequestrentals.modelo.VehiculoCarga;
import com.drivequestrentals.modelo.Vehiculo;

import java.io.*;
import java.util.*;

/**
 * Clase auxiliar GestionVehiculos.
 * @author jennifer
 */

public class GestionVehiculos {
    private final Map<String, Vehiculo> flota = Collections.synchronizedMap(new HashMap<>());
    
    // Método para agregar vehículos únicos
    public boolean agregarVehiculo(Vehiculo vehiculo) {
        String patente = vehiculo.getPatente().toUpperCase();
        if (flota.containsKey(patente)) {
            System.out.println("La patente" + patente + "ya se encuentra en la flota de vehículos.");
            return false;
        }
        flota.put(patente, vehiculo);
        return true;
    }
    
    // Método para listar todos los vehículos registrados
    public void listarVehiculos() {
        for (Vehiculo vehiculo : flota.values()) {
            vehiculo.mostrarDatos();
        }
    }
    
    // Método para filtrar vehiculos por arriendo largo
    public int VehiculosConArriendoLargo() {
        int contador = 0;
        for (Vehiculo vehiculo : flota.values()) {
            if (vehiculo.getDiasDeArriendo() >= 7) {
                contador++;
            }
        }
        return contador;
    }
    
    // Método para mostrar las boletas
    public void mostrarBoletas() {
        for (Vehiculo vehiculo : flota.values()) {
            if (vehiculo instanceof CalculoBoleta calculoBoleta) {
                calculoBoleta.calcularBoleta();
            }
        }
    }
    
    // Método que devuelve la flota de vehículos
    public Collection<Vehiculo> getFlota() {
        return flota.values();
    }
    
    // Método para cargar los vehículos desde un archivo csv
    public void CargarVehiculosCSV(String archivo) {
        try (BufferedReader lector = new BufferedReader(new FileReader(archivo))) {
            String linea;
            
            while ((linea = lector.readLine()) != null) {
                String[] datos = linea.split(",");
                String tipo = datos[0].trim();
                String patente = datos[1].trim().toUpperCase();
                String marca = datos[2].trim();
                String modelo = datos[3].trim();
                int year = Integer.parseInt(datos[4].trim());
                int precioPorDia = Integer.parseInt(datos[5].trim());
                int diasDeArriendo = Integer.parseInt(datos[6].trim());
                
                if (tipo.equalsIgnoreCase("Carga")) {
                    double capacidad = Double.parseDouble(datos[7].trim());
                    agregarVehiculo(new VehiculoCarga(capacidad, patente, marca, modelo, year, precioPorDia, diasDeArriendo));
                } else if (tipo.equalsIgnoreCase("Pasajeros")) {
                    int pasajeros = Integer.parseInt(datos[7].trim());
                    agregarVehiculo(new VehiculoPasajeros(pasajeros, patente, marca, modelo, year, precioPorDia, diasDeArriendo));
                }
            }
            System.out.println("Vehículos cargados correctamente desde el archivo.");
        } catch (IOException e) {
            System.out.println("Error al cargar los vehículos desde el archivo: " + e.getMessage());
        }
    }
    
    // Método para guardar vehículos en un archivo csv
    public void GuardarVehiculosCSV(String archivo) {
        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(archivo))) {
            for (Vehiculo vehiculo : flota.values()) {
                String linea = "";
                
                if (vehiculo instanceof VehiculoCarga vc) {
                    linea = "Carga," + vc.getPatente() + "," + vc.getMarca() + "," + vc.getModelo() + "," + vc.getYear() + "," + vc.getPrecioPorDia() + "," + vc.getDiasDeArriendo() + "," + vc.getCapacidadCarga();
                } else if (vehiculo instanceof VehiculoPasajeros vp) {
                    linea = "Pasajeros," + vp.getPatente() + "," + vp.getMarca() + "," + vp.getModelo() + "," + vp.getYear() + "," + vp.getPrecioPorDia() + "," + vp.getDiasDeArriendo() + "," + vp.getCapacidadPasajeros();
                }
                
                escritor.write(linea);
                escritor.newLine();
            }
            System.out.println("Los vehículos han sido guardados correctamente en el archivo.");
        } catch (IOException e) {
            System.out.println("Error al guardar los vehículos en el archivo: " + e.getMessage());
        }
    }
    
}
