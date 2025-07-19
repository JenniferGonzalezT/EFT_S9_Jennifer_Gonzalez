/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.drivequestrentals.servicio;

import com.drivequestrentals.modelo.*;

import java.io.*;
import java.util.*;

/**
 * Clase auxiliar GestionFlota.
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
            for (Vehiculo vehiculo : flota.values()) {
                vehiculo.mostrarDatos();
                System.out.println();
            }
        }
        
    }
    
    // Método para filtrar vehiculos por arriendo largo
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
            for (Vehiculo vehiculo : flota.values()) {
                if (vehiculo instanceof CalculoBoleta calculoBoleta) {
                    calculoBoleta.calcularBoleta();
                }
                System.out.println();
            }
        }
    }
    
    // Método para cargar los vehículos desde un archivo csv
    public void cargarVehiculosCSV(String archivo) {
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

                        if (tipo.equalsIgnoreCase("CARGA")) {
                            double capacidadCarga = Double.parseDouble(datos[7].trim());
                            agregarVehiculo(new VehiculoCarga(capacidadCarga, patente, marca, modelo, year, precioPorDia, diasDeArriendo));
                        } else if (tipo.equalsIgnoreCase("PASAJEROS")) {
                            int capacidadPasajeros = Integer.parseInt(datos[7].trim());
                            agregarVehiculo(new VehiculoPasajeros(capacidadPasajeros, patente, marca, modelo, year, precioPorDia, diasDeArriendo));
                        } else {
                            System.out.println("Vehículo omitido \"" + linea + "\": El tipo de vehículo no es válido.");
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println("Vehículo omitido \"" + linea + "\": " + e.getMessage());
                    }
                    
                } else {
                    System.out.println("Vehículo omitido \"" + linea + "\": El formato no es válido.");
                }
            }
            System.out.println("Vehículos cargados correctamente desde el archivo.");
        } catch (IOException e) {
            System.out.println("Error al cargar los vehículos desde el archivo: " + e.getMessage());
        }
    }
    
    // Método para guardar vehículos en un archivo csv
    public void guardarVehiculosCSV(String archivo) {
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
            if (flota.isEmpty()) {
                System.out.println("No hay vehículos para guardar.");
            } else {
                System.out.println("Los vehículos han sido guardados correctamente en el archivo.");
            }
        } catch (IOException e) {
            System.out.println("Error al guardar los vehículos en el archivo: " + e.getMessage());
        }
    }
    
}
