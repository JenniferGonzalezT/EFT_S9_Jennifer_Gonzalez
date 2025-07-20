/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.drivequestrentals.hilo;

/**
 * Clase Coordinador para sincronizar los hilos en la carga y validaciones.
 * @author jennifer
 */

public class Coordinador {
    private boolean archivoCargado = false;
    private boolean errorCarga = false;

    // Método que espera a que se carguen los datos desde el archivo.
    public synchronized void esperarCarga() throws InterruptedException {
        while (!archivoCargado && !errorCarga) {
            System.out.println(Thread.currentThread().getName() + ": Esperando la carga de archivos...");
            wait();
        }
        
        if (errorCarga) {
            throw new IllegalStateException(Thread.currentThread().getName() + ": No ejecutará esta tarea por error en la carga.");
        }
    }

    // Método que notifica a los hilos que están en espera que la carga está lista.
    public synchronized void cargaLista() {
        archivoCargado = true;
        notifyAll();
    }
    
    // Método que notifica a los hilos si hubo un error en la carga.
    public synchronized void errorEnCarga() {
        errorCarga = true;
        notifyAll();
    }
    
    public synchronized boolean estadoErrorCarga() {
        return errorCarga;
    }
    
}