/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.drivequestrentals.app_main;

import com.drivequestrentals.hilo.HiloCarga;
import com.drivequestrentals.modelo.*;
import com.drivequestrentals.servicio.GestionFlota;

import java.util.Scanner;
// Al menos dos hilos, uno que carge ✅ y otro que valide
// Deberia mostrar los vehículos con arriendo largo??
/**
 * Sistema para catalogar y administrar una flota de vehículos de alquiler.
 * @author jennifer
 */

public class DriveQuestRentals {
    private static final String ARCHIVO_VEHICULOS_CSV = "Vehiculos.csv";
    
    private static final Scanner scanner = new Scanner(System.in);
    private static final GestionFlota gestor = new GestionFlota();
    
    public static void main(String[] args) {
        
        System.out.println("¡Bienvenid@ a DriveQuest Rentals!");
        
        // Cargar archivos en un hilo y esperar a que termine.
        Thread hiloCarga = new HiloCarga(gestor, ARCHIVO_VEHICULOS_CSV);
        hiloCarga.start();
        try {
            hiloCarga.join();
        } catch (InterruptedException e) {
            System.out.println("Hilo interrumpido: " +  e.getMessage());
        }
        
        // Ménu de opciones
        int opcionMenu;
        do {
            opcionMenu = mostrarMenu();
            switch (opcionMenu) {
                case 0:
                    System.out.println("\n\n==================== SALIR =====================");
                    System.out.println("Saliendo...\n¡Muchas gracias por su visita!");
                    break;
                case 1:
                    agregarVehiculo();
                    break;
                case 2:
                    System.out.println("\n\n============== LISTA DE VEHÍCULOS ==============");
                    gestor.listarVehiculos();
                    break;
                case 3:
                    System.out.println("\n\n================ MOSTRAR BOLETAS ===============");
                    gestor.mostrarBoletas();
                    break;
                case 4:
                    System.out.println("\n\n=================== ARRIENDO ===================");
                    System.out.println("Cantidad de vehículos con arriendo largo (igual o superior a 7 días): " + gestor.contarVehiculosConArriendoLargo());
                    break;
                case 5:
                    System.out.println("\n\n=============== GUARDAR VEHÍCULOS ==============");
                    gestor.guardarVehiculosCSV(ARCHIVO_VEHICULOS_CSV);
                    break;
            }
        } while (opcionMenu != 0);
        scanner.close();
    }
    
    
    // Método para mostrar el menú y devolver la opción elegida.
    static int mostrarMenu() {
        System.out.println("\n\n===================== MENÚ =====================");
        System.out.println("(1) Agregar vehículo");
        System.out.println("(2) Listar vehículos");
        System.out.println("(3) Mostrar boletas");
        System.out.println("(4) Ver cantidad de vehículos con arriendos largos");
        System.out.println("(5) Guardar vehículos en archivo");
        System.out.println("(0) Salir");
        
        final int OPCION_MIN = 0;
        final int OPCION_MAX = 5;
        int opcion = 0;
        boolean opcionValida = false;
        String mensajeOpciones = "\nRecuerde ingresar un número de " + OPCION_MIN + " a " + OPCION_MAX;
        
        while (!opcionValida) {
            System.out.print("\nIngrese el número correspondiente a su opción: ");
            try {
                String entrada = scanner.nextLine().trim();
                opcion = Integer.parseInt(entrada);
                if (opcion >= OPCION_MIN && opcion <= OPCION_MAX) {
                    opcionValida = true;
                } else {
                    System.out.println("El número ingresado no es válido." + mensajeOpciones);
                }
            } catch (NumberFormatException e) {
                System.out.println("La entrada no es válida." + mensajeOpciones);
            }
        }
        
        return opcion;
    }
    
    
    // Método para agregar vehículos
    private static void agregarVehiculo() {
        System.out.println("\n\n=============== AGREGAR VEHÍCULO ===============");
        System.out.println("Para agregar un vehículo ingrese los siguientes datos:");
        boolean datoValido = false;
        
        System.out.println("\nTipo de vehículo:");
        System.out.println("(1) Carga");
        System.out.println("(2) Pasajeros");
        int tipo = 0;
        while (!datoValido) {
            System.out.print("Ingrese su opción: ");
            String entrada = scanner.nextLine().trim();
            try {
                tipo = Integer.parseInt(entrada);
                if (tipo == 1 || tipo == 2) {
                    datoValido = true;
                } else {
                    System.out.println("El número ingresado no es válido. Recuerde ingresar 1 (carga) o 2 (pasajeros).");
                }
            } catch (NumberFormatException e) {
                System.out.println("El dato ingresado no es válido. Recuerde ingresar 1 (carga) o 2 (pasajeros).");
            }
        }
        
        String patente;
        do {
            System.out.print("\nPatente: ");
            patente = scanner.nextLine().trim().toUpperCase();
            if (patente.isBlank()) {
                System.out.println("La patente no puede estar vacía. Intente nuevamente.");
            }
        } while (patente.isBlank());
        
        if (gestor.existePatente(patente)) {
            System.out.println("La patente ya existe en la flota. No se puede agregar el vehículo.");
            return;
        }
        
        String marca;
        do {
            System.out.print("\nMarca: ");
            marca = scanner.nextLine().trim();
            if (marca.isBlank()) {
                System.out.println("La marca no puede estar vacía. Intente nuevamente.");
            }
        } while (marca.isBlank());
        
        String modelo;
        do {
            System.out.print("\nModelo: ");
            modelo = scanner.nextLine().trim();
            if (modelo.isBlank()) {
                System.out.println("El modelo no puede estar vacío. Intente nuevamente.");
            }
        } while (modelo.isBlank());
        
        int year = 0;
        int yearActual = java.time.LocalDate.now().getYear();
        datoValido = false;
        while (!datoValido) {
            System.out.print("\nAño: ");
            String entrada = scanner.nextLine().trim();
            try {
                year = Integer.parseInt(entrada);
                if (year < 2000 || year > yearActual) {
                    System.out.println("El año ingresado no es válido. Intente nuevamente.");
                } else {
                    datoValido = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("El dato ingresado no es válido. Recuerde ingresar un número entero positivo.");
            }
        }
        
        int precioPorDia = 0;
        datoValido = false;
        while (!datoValido) {
            System.out.print("\nPrecio por día: $");
            String entrada = scanner.nextLine().trim();
            try {
                precioPorDia = Integer.parseInt(entrada);
                if (precioPorDia <= 0) {
                    System.out.println("El precio ingresado no es válido. Intente ingresar un número mayor a 0.");
                } else {
                    datoValido = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("El dato ingresado no es válido. Recuerde ingresar un número entero positivo.");
            }
        }
        
        int diasDeArriendo = 0;
        datoValido = false;
        while (!datoValido) {
            System.out.print("\nDías de arriendo: ");
            String entrada = scanner.nextLine().trim();
            try {
                diasDeArriendo = Integer.parseInt(entrada);
                if (diasDeArriendo <= 0) {
                    System.out.println("El número de días ingresado no es válido. Intente ingresar un número mayor a 0.");
                } else {
                    datoValido = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("El dato ingresado no es válido. Recuerde ingresar un número entero positivo.");
            }
        }
        
        datoValido = false;
        if (tipo == 1) {
            while (!datoValido) {
                System.out.print("\nCapacidad de carga (en kilogramos): ");
                String entrada = scanner.nextLine();
                try {
                    double capacidadCarga = Double.parseDouble(entrada);
                    if (capacidadCarga <= 0) {
                        System.out.println("El número ingresado no es válido. Intente ingresar un número mayor a 0.");
                    } else {
                        datoValido = true;
                        VehiculoCarga vehiculo = new VehiculoCarga(capacidadCarga, patente, marca, modelo, year, precioPorDia, diasDeArriendo);
                        if (gestor.agregarVehiculo(vehiculo)) {
                            System.out.println("\n¡El vehículo ha sido agregado con éxito a la flota!");
                        } else {
                            System.out.println("El vehículo no se agregará a la flota.");
                        }
                    }
                } catch (NumberFormatException e) {
                    System.out.println("El valor ingresado no es válido. Recuerde ingresar un número mayor a 0.");
                }
            }
        } else if (tipo == 2) {
            while (!datoValido) {
                System.out.print("\nMáximo de pasajeros: ");
                String entrada = scanner.nextLine();
                try {
                    int capacidadPasajeros = Integer.parseInt(entrada);
                    if (capacidadPasajeros <= 0) {
                        System.out.println("El número ingresado no es válido. Intente ingresar un número mayor a 0.");
                    } else {
                        datoValido = true;
                        VehiculoPasajeros vehiculo = new VehiculoPasajeros(capacidadPasajeros, patente, marca, modelo, year, precioPorDia, diasDeArriendo);
                        if (gestor.agregarVehiculo(vehiculo)) {
                            System.out.println("\n¡El vehículo ha sido agregado con éxito a la flota!");
                        } else {
                            System.out.println("El vehículo no se agregará a la flota.");
                        }
                    }
                } catch (NumberFormatException e) {
                    System.out.println("El valor ingresado no es válido. Recuerde ingresar un número mayor a 0.");
                }
            }
        }
    }
    
}
