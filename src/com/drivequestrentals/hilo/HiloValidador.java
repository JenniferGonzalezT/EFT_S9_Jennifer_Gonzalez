/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.drivequestrentals.hilo;

import com.drivequestrentals.modelo.Vehiculo;
import com.drivequestrentals.servicio.GestionFlota;

import java.util.concurrent.BlockingQueue;

/**
 * Clase para Hilo que implementa Runnable para validar los vehículos desde la
 * cola compartida y agregarlos a la flota si son válidos.
 * @author jennifer
 */

public class HiloValidador implements Runnable {
    private final BlockingQueue<Vehiculo> colaVehiculos;
    private final GestionFlota gestor;
    private final Coordinador coordinador;

    public HiloValidador(BlockingQueue<Vehiculo> colaVehiculos, GestionFlota gestor, Coordinador coordinador) {
        this.colaVehiculos = colaVehiculos;
        this.gestor = gestor;
        this.coordinador = coordinador;
    }
    
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + ": Validando vehículos desde la cola a la flota...");
        
        // El hilo espera a que la carga desde el archivo termine
        try {
            coordinador.esperarCarga();
        } catch (IllegalStateException e) {
            System.out.println(Thread.currentThread().getName() + ": Termina su tarea por error en la carga.");
        } catch (InterruptedException eh) {
            System.out.println(Thread.currentThread().getName() + " fue interrumpido.");
        }
        
        // Agregar vehiculos a la flota
        while (!coordinador.estadoErrorCarga()) {
            Vehiculo vehiculo = colaVehiculos.poll();
            
            if (vehiculo == null) {
                System.out.println(Thread.currentThread().getName() + ": No encontró más elementos en la cola.");
                break;
            }
            
            gestor.agregarVehiculo(vehiculo);
            System.out.println("Vehículo agregado: " + vehiculo.getPatente());
        }
        
        System.out.println(Thread.currentThread().getName() + ": Ha finalizado.");
        
    }
}
