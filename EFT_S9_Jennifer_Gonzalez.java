/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package eft_s9_jennifer_gonzalez;

import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Programa para gestionar ventas y reservas del Teatro Moro
 * @author jennifer
 */

public class EFT_S9_Jennifer_Gonzalez {

    static Scanner scanner = new Scanner(System.in);
    
    static byte opcionMenu;
    
    //Varibles con los nombres, precios y disponibilidad de las ubicaciones.
    static String[] ubicacionNombre = {"Vip", "Platea Alta", "Platea Baja", "Palco", "Galería"};
    static int[] ubicacionPrecio = {30000, 18000, 15000, 13000, 10000};
    static int[] disponibilidad = {10, 10, 10, 10, 10};
    
    //Creación de listas para almacenar las ventas y reservas
    static LinkedList <String[]> ventas = new LinkedList<>();
    static LinkedList <String[]> reservas = new LinkedList<>();
    static List <Integer> clientes = new ArrayList<>();
    
    //Creación e inicialización de variables para identificar ventas, reservas y clientes.
    static int codigoVentas = 1;
    static int codigoReservas = 1;
    static int codigoClientes = 1;
    
    //Variable acumuladora para almacenar el ingreso total por ventas.
    static int ingresosTotales = 0;
    
    
    
    public static void main(String[] args) {

        //Mensaje de bienvenida
        System.out.println("¡Bienvenid@ al Teatro Moro!");
        
        do {
            menu();
            //breakpoint para ver el valor que toma la variable opcionMenu luego de pasar por el método menu()
            switch (opcionMenu) {
                case 1:
                    reservarEntradas();
                    break;
                case 2:
                    comprarEntradas();
                    break;
                case 3:
                    modificarVenta();
                    break;
                case 4:
                    imprimirBoleta();
                    break;
                case 5:
                    System.out.println("\n=========== RESUMEN VENTAS =========");
                    mostrarResumen("ventas", ventas);
                    break;
                case 6:
                    System.out.println("\n========= RESUMEN RESERVAS =========");
                    mostrarResumen("reservas", reservas);
                    break;
                case 7:
                    System.out.println("\n========== INGRESOS TOTALES ========");
                    System.out.println("El ingreso total por todas las ventas es de:");
                    System.out.println("$" + ingresosTotales);
                    break;
                case 8:
                    System.out.println("\n=============== SALIR ==============");
                    System.out.println("Ha salido del programa. ¡Muchas gracias por su visita!");
                    break;
                default:
                    System.out.println("Opción no válida. Recuerde ingresar un número del 1 al 8.");
                    break;
            }
        } while (opcionMenu != 8);
    }
    
    
    
    //Método para mostrar el menú
    static void menu() {
        System.out.println("\n=============== MENÚ ===============");
        System.out.println("(1) Reservar entradas");
        System.out.println("(2) Comprar entradas");
        System.out.println("(3) Modificar una venta");
        System.out.println("(4) Imprimir boleta");
        System.out.println("(5) Resumen de ventas realizadas");
        System.out.println("(6) Resumen de reservas actuales");
        System.out.println("(7) Mostrar los ingresos totales");
        System.out.println("(8) Salir");
        System.out.print("Ingrese el número correspondiente a su opción: ");
        
        //Validar la opcion ingresada
        try {
            opcionMenu = scanner.nextByte();
        } catch (InputMismatchException e) {
            opcionMenu = 0;
        } finally {
            scanner.nextLine();
        }
    }
    
    
    
    //Método para Reservar entradas
    static void reservarEntradas() {
        System.out.println("\n======== RESERVA DE ENTRADAS =======");
        procesarEntradas("Reserva", codigoReservas, reservas);
    }
    
    
    
    //Método para Comprar entradas
    static void comprarEntradas() {
        
        byte opcion = 0;
        boolean opcionValida = false;
        
        System.out.println("\n======== COMPRA DE ENTRADAS ========");
        System.out.println("¿Desea comprar una reserva existente?");
        System.out.println("(1) Si");
        System.out.println("(2) No");
        
        
        while (!opcionValida) {
            System.out.print("Ingrese el número correspondiente a su opción: ");
            try {
                opcion = scanner.nextByte();
                switch (opcion) {
                    case 1:
                        if (reservas.isEmpty()) {
                            System.out.println("Aún no hay reservas registradas. Por favor ingrese \"2\" para comprar.");
                        } else {
                            opcionValida = true;
                        }
                        break;
                    case 2:
                        opcionValida = true;
                        break;
                    default:
                        System.out.println("El número ingresado no es válido. Recuerde ingresar 1 o 2.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("El valor ingresado no es válido. Recuerde ingresar 1 o 2.");
            } finally {
                scanner.nextLine();
            }
        }
        
        switch (opcion) {
            case 1:
                int indiceReserva = 0;
                boolean numeroValido = false;
                
                while (!numeroValido) {
                    System.out.print("Ingrese su número de reserva: #");
                    try {
                        int numeroEntrada = scanner.nextInt();
                        for (int i = 0; i < reservas.size(); i++) {
                            String[] reserva = reservas.get(i);
                            if (Integer.parseInt(reserva[1]) == numeroEntrada) {
                                numeroValido = true;
                                indiceReserva = i;
                                //breakpoint para evaluar el valor de indiceReserva
                                break;
                            } 
                        }
                        if (numeroValido == false) {
                            System.out.println("El número de reserva no es válido.");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("El número de reserva no es válido.");
                    } finally {
                        scanner.nextLine();
                    }
                }
                
                //Cambio de estado de la entrada
                String[] reserva = reservas.get(indiceReserva);     //breakpoint para ver los datos de reserva
                //datos = [proceso, indice, codigoCliente, cantidadEntradas, ubicacion, precioBase, precioTotal, descuento, precioFinal]
                
                reserva[0] = "Compra";
                reserva[1] = String.valueOf(codigoVentas);
                
                ventas.add(reserva);                   //breakpoint para validar que la reserva se convierta en venta
                reservas.remove(indiceReserva);     //breakpoint para validar que la reserva se elimine
                
                System.out.println("\nCompra de Reserva realizada con éxito");
                mostrarBoleta(reserva, "Gracias por su compra. ¡Disfrute la función!");
                
                codigoVentas++;
                //breakpoint para validar la actualización de codigoVentas
                
                ingresosTotales += Integer.parseInt(reserva[8]);
                //breakpoint para validar la actualización de ingresosTotales
                break;   
            case 2:
                System.out.println("\n======== COMPRA DE ENTRADAS ========");
                procesarEntradas("Compra", codigoVentas, ventas);
                break;
        }  
    }
    
    
    
    //Método para Modificar una venta
    static void modificarVenta() {
        
        System.out.println("\n========= MODIFICAR VENTAS =========");
 
        if (ventas.isEmpty()) {
            System.out.println("Aún no hay ventas para modificar.");
            return;
        }
        
        boolean numeroCompraValido = false;
        int numeroCompra = 0;
        int indiceVenta = 0;
        
        while(!numeroCompraValido) {
            System.out.print("Ingrese el número de compra que desea modificar: #");
            try {
                numeroCompra = scanner.nextInt();
                for (int i = 0; i < ventas.size(); i++) {
                    String[] venta = ventas.get(i);
                    if (Integer.parseInt(venta[1]) == numeroCompra) {
                        numeroCompraValido = true;
                        indiceVenta = i;
                        break;
                    } 
                }
                if (!numeroCompraValido) {
                    System.out.println("El número de Compra no es válido.");
                }
            } catch (InputMismatchException e) {
                System.out.println("El valor ingresado no es válido.");
            } finally {
                scanner.nextLine();
            }
        }
        
        String[] venta = ventas.get(indiceVenta);
        String codigoCliente = venta[2];
        
        System.out.println("\nVenta actual:");
        mostrarBoleta(venta, "Modifique su compra y disfrute de la función");
        
        
        //Modificar disponibilidad
        
        //breakpoint para ver el valor que va tomando la variable datoCantidad
        int datoCantidad = Integer.parseInt(venta[3]);
        
        //breakpoint para ver el valor que va tomando la variable datoUbicacion
        String datoUbicacion = venta[4];
        
        for (int i = 0; i < ubicacionNombre.length; i++) {
            if (ubicacionNombre[i].equals(datoUbicacion)) {
                disponibilidad[i] += datoCantidad;
            }
        }
        
        ingresosTotales -= Integer.parseInt(venta[8]);
        //breakpoint para ver la actualización de ingresosTotales
        
        System.out.println("\nModificando...");
        
        ubicacionesDisponibles(); //breakpoint para verificar la actualizacion de las ubicaciones disponibles
        
        int cantidadEntradas = solicitarCantidadEntradas();
        //brekpoint para ver el valor que toma la variable cantidadEntradas a traves del método solicitarCantidadEntradas()
        
        int ubicacion = solicitarUbicacion();
        //brekpoint para ver el valor que toma la variable ubicacion a traves del método solicitarUbicacion()
        
        
        //Validar disponibilidad
        if (cantidadEntradas > disponibilidad[ubicacion - 1]) {
            System.out.println("No hay suficientes entradas disponibles en esta ubicación.");
            System.out.println("Volverá al menú para realizar la operación que desee.");
            //Revertir la disponibilidad
            for (int i = 0; i < ubicacionNombre.length; i++) {
                if (ubicacionNombre[i].equals(datoUbicacion)) {
                    disponibilidad[i] -= datoCantidad;
                }
            }
            ingresosTotales += Integer.parseInt(venta[8]);
            return;
        }
        
        //Calcular precios
        String[] datosDescuento = aplicarDescuento();
        String descuentoTexto = datosDescuento[0];
        double descuentoValor = Double.parseDouble(datosDescuento[1]);
        
        int precioTotal = ubicacionPrecio[ubicacion - 1] * cantidadEntradas;    //breakpoint para ver el valor de precioTotal
        
        int precioFinal = (int) (precioTotal * descuentoValor);                  //breakpoint para ver el valor de precioFinal
        
        String[] entradaModificada = {
            "Compra", 
            String.valueOf(numeroCompra), 
            codigoCliente, 
            String.valueOf(cantidadEntradas), 
            ubicacionNombre[ubicacion - 1], 
            String.valueOf(ubicacionPrecio[ubicacion - 1]), 
            String.valueOf(precioTotal), 
            descuentoTexto, 
            String.valueOf(precioFinal)
        };
        
        ventas.set(indiceVenta,entradaModificada);
        
        System.out.println("\nVenta modificada con éxito");
        mostrarBoleta(entradaModificada, "Gracias por su compra. ¡Disfrute la función!");
        
        disponibilidad[ubicacion - 1] -= cantidadEntradas;
        //breakpoint para validar la actualizacion de disponibilidad
        
        ingresosTotales += precioFinal;
        //breakpoint para verificar el valor de ingresosTotales
    }
    
    
    
    //Método para Procesar entradas en compra y reserva
    static void procesarEntradas(String proceso, int indice, LinkedList<String[]> lista) {
        
        ubicacionesDisponibles();
        
        int cantidadEntradas = solicitarCantidadEntradas();     //breakpoint para ver el valor de cantidadEntradas
        int ubicacion = solicitarUbicacion();                   //breakpoint para ver el valor de ubicacion
        
        
        //Validar disponibilidad
        if (cantidadEntradas > disponibilidad[ubicacion - 1]) {
            System.out.println("No hay suficientes entradas disponibles en esta ubicación.");
            System.out.println("Volverá al menú para realizar la operación que desee.");
            return;
        }
        
        
        //Calcular precios
        String[] datosDescuento = aplicarDescuento();
        String descuentoTexto = datosDescuento[0];
        double descuentoValor = Double.parseDouble(datosDescuento[1]);
        
        int precioTotal = ubicacionPrecio[ubicacion - 1] * cantidadEntradas;
        //breakpoint para validar el valor de precioTotal
        
        int precioFinal = (int) (precioTotal * descuentoValor);
        //breakpoint para evaluar los valores que toma la variable descuento y el valor de precioFinal
        
        
        //Obtener datos del cliente
        String codigoCliente = procesarClientes();
        
        
        //breakpoint para verificar los valores que toman las variables en nuevaEntrada
        String[] nuevaEntrada = {
            proceso, 
            String.valueOf(indice), 
            codigoCliente,
            String.valueOf(cantidadEntradas), 
            ubicacionNombre[ubicacion - 1], 
            String.valueOf(ubicacionPrecio[ubicacion - 1]), 
            String.valueOf(precioTotal), 
            descuentoTexto, 
            String.valueOf(precioFinal), 
        };
        
        lista.add(nuevaEntrada); //breakpoint para comprobar que nuevaEntrada se agregue a lista
        
        System.out.println("\n" + proceso + " realizada con éxito");
        
        if (proceso.equals("Compra")) {
            mostrarBoleta(nuevaEntrada, "Gracias por su compra. ¡Disfrute la función!");
            ingresosTotales += precioFinal;
            codigoVentas++;
        } else {
            mostrarBoleta(nuevaEntrada, "Gracias por su reserva. ¡Esperamos su compra!");
            codigoReservas++;
        }
        
        disponibilidad[ubicacion - 1] -= cantidadEntradas;
    }
    
    
    
    //Método para solicitar la cantidad de entradas
    static int solicitarCantidadEntradas() {
        
        boolean cantidadValida = false;
        int cantidadEntradas = 0;
        final int MAX_ENTRADAS = 5;
        
        while (!cantidadValida) {
            System.out.print("\nIngrese la cantidad de entradas que desea (Máximo " + MAX_ENTRADAS + "): ");
            try {
                cantidadEntradas = scanner.nextInt();
                if (cantidadEntradas > 0 && cantidadEntradas <= MAX_ENTRADAS) {
                    cantidadValida = true;
                } else {
                    System.out.println("La cantidad ingresada no es válida.");
                }
            } catch (InputMismatchException e) {
                System.out.println("El valor ingresado no es válido.");
            } finally {
                scanner.nextLine();
            }
        }
        
        return cantidadEntradas;
    }
    
    
    
    //Método para solicitar la ubicacion de las entradas
    static int solicitarUbicacion() {
        
        boolean ubicacionValida = false;
        int ubicacion = 0;
        final int CANT_UBICACIONES = ubicacionNombre.length;
        
        while (!ubicacionValida) {
            System.out.print("\nIngrese el número correspondiente a la ubicación que desea: ");
            try {
                ubicacion = scanner.nextInt();
                if (ubicacion > 0 && ubicacion <= CANT_UBICACIONES) {
                    ubicacionValida = true;
                } else {
                    System.out.println("El número ingresado no es válido. Recuerde ingresar un número del 1 al " + CANT_UBICACIONES + ".");
                }
            } catch (InputMismatchException e) {
                System.out.println("El valor ingresado no es válido. Recuerde ingresar un número del 1 al " + CANT_UBICACIONES + ".");
            } finally {
                scanner.nextLine();
            }
        }
        
        return ubicacion;
    }
    
    
    
    //Método para Imprimir boleta
    static void imprimirBoleta() {
        
        boolean numeroCompraValido = false;
        
        System.out.println("\n========== IMPRIMIR BOLETA =========");
        
        //breakpoint para verificar los valores de ventas
        if (ventas.isEmpty()) {
            System.out.println("Aún no hay boletas de ventas para imprimir.");
            return;
        }
        
        while(!numeroCompraValido) {
            System.out.print("Ingrese el número de compra: #");
            try {
                int numeroCompra = scanner.nextInt();
                for (int i = 0; i < ventas.size(); i++) {
                    String[] venta = ventas.get(i);
                    if (Integer.parseInt(venta[1]) == numeroCompra) {
                        numeroCompraValido = true;
                        mostrarBoleta(venta, "Gracias por su compra. ¡Disfrute la función!");
                        return;
                    }
                }
                if (numeroCompraValido == false) {
                    System.out.println("El número de Compra no es válido.");
                }
            } catch (InputMismatchException e) {
                System.out.println("El valor ingresado no es válido.");
            } finally {
                scanner.nextLine();
            }
        }
    }
    
    
    
    //Método para mostrar un resumen de todas las ventas o reservas realizadas
    static void mostrarResumen(String proceso, LinkedList<String[]> lista) {
        
        if (lista.isEmpty()) {
            System.out.println("Aún no hay "+ proceso + " para mostrar.");
            return;
        }
        
        for (String[] datos : lista) {
            mostrarBoleta(datos, "Resumen de " + proceso + " realizadas.");
        }
    }
    
    
    
    //Método para procesar clientes otorgandoles u obteniendo su código
    static String procesarClientes() {
        
        boolean opcionValida = false;
        byte opcion = 0;
        String cliente = "";
        
        System.out.println("\n¿Tiene su código de cliente?");
        System.out.println("(1) Si");
        System.out.println("(2) No");
        
        while (!opcionValida) {
            System.out.print("Ingrese el número correspondiente a su opción: ");
            try {
                opcion = scanner.nextByte();
                switch (opcion) {
                    case 1:
                        if (clientes.isEmpty()) {
                            System.out.println("Aún no hay clientes registrados. Ingrese \"2\" para registrarse.");
                        } else {
                            opcionValida = true;
                        }
                        break;
                    case 2:
                        opcionValida = true;
                        break;
                    default:
                        System.out.println("El número ingresado no es válido. Recuerde ingresar 1 o 2.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("El valor ingresado no es válido. Recuerde ingresar 1 o 2.");
            } finally {
                scanner.nextLine();
            }
        }
        
        switch (opcion) {
            case 1:
                boolean codigoValido = false;
                int codigoIngresado;
                
                while(!codigoValido) {
                    System.out.print("Ingrese su código de cliente: #");
                    try {
                        codigoIngresado = scanner.nextInt();
                        for (int codigoCliente : clientes) {
                            if (codigoCliente == codigoIngresado) {
                                codigoValido = true;
                                cliente = String.valueOf(codigoCliente);    //breakpoint para validar la variable cliente
                                break;
                            }
                        }
                        if (!codigoValido) {
                            System.out.println("El código de cliente no es válido. Intente otra vez.");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("El código de cliente no es válido. Intente otra vez.");
                    } finally {
                        scanner.nextLine();
                    }
                }
                break;
            case 2:
                System.out.println("Su código de cliente es: #" + codigoClientes);
                clientes.add(codigoClientes);
                cliente = String.valueOf(codigoClientes);
                codigoClientes++;
                //breakpoint para verificar la actualización de codigoClientes
                break;
        }
        
        return cliente;
    }
    
    
    
    
    //Método para mostrar boletas durante los procesos
    static void mostrarBoleta(String[] datos, String motivo) {
        
        //datos = [proceso, indice, codigoCliente, cantidadEntradas, ubicacion, precioBase, precioTotal, descuento, precioFinal]
        String lineaBoleta = "--------------------------------------------";
        
        System.out.println("\nBOLETA:");
        System.out.println(lineaBoleta);
        System.out.println(
            "-> " + datos[0] + " #" + datos[1] + 
            "\n-> Código cliente #" + datos[2] +
            "\n-> " + datos[3] + " entradas en " + datos[4] + 
            "\n-> Precio Base $" + datos[5] + 
            "\n-> Precio Total $" + datos[6] +
            "\n-> " + datos[7] + 
            "\n-> Precio Final $" + datos[8]
        );
        System.out.println(lineaBoleta);
        System.out.println(motivo);
        System.out.println(lineaBoleta);
    }
    
    
    
    //Método para mostrar las ubicaciones disponibles
    static void ubicacionesDisponibles() {
        System.out.println("\nUbicaciones disponibles:");
        for (int i = 0; i < ubicacionNombre.length; i++) {
            if (disponibilidad[i] == 0) {
                System.out.println("(" + (i + 1) + ") " + ubicacionNombre[i] + " : " + "AGOTADO");
            } else {
                System.out.println("(" + (i + 1) + ") " + ubicacionNombre[i] + " : " + disponibilidad[i]);
            }
        }
    }
    
    
    
    //Método para realizar el descuento verificando la edad
    static String[] aplicarDescuento() {
        
        //Lista con los descuentos: [descuentoTexto, descuentoValor]
        List<String[]> listaDescuentos = new ArrayList <>();
        listaDescuentos.add(new String[] {"Descuento Niños: 10%", "0.90"});
        listaDescuentos.add(new String[] {"Descuento Estudiantes: 15%", "0.85"});
        listaDescuentos.add(new String[] {"Descuento Mujeres: 20%", "0.80"});
        listaDescuentos.add(new String[] {"Descuento Tercera Edad: 25%", "0.75"});
        listaDescuentos.add(new String[] {"Sin Descuento", "1"});
        
        final int EDAD_MIN = 0;
        final int EDAD_MAX = 100;
        final int EDAD_MAX_NINOS = 17;
        final int EDAD_MIN_TERCERA_EDAD = 60;
        
        int opcion = 0;
        final int CANT_OPCIONES = listaDescuentos.size();
        boolean descuentoValido = false;
        String mensajeEdadInvalida = "La edad ingresada no aplica para el descuento elegido.\nIntente nuevamente";
        
        while (!descuentoValido) {
            //Ingreso del descuento
            System.out.println("\nAplicar descuento:");
            System.out.println("¿Usted es?");
            System.out.println("(1) Niño/a                      (Edad: " + EDAD_MIN + " - " + EDAD_MAX_NINOS + ")");
            System.out.println("(2) Estudiante                  (Edad: " + EDAD_MIN + " - " + (EDAD_MIN_TERCERA_EDAD-1) + ")");
            System.out.println("(3) Mujer                       (Edad: " + (EDAD_MAX_NINOS+1) + " - " + (EDAD_MIN_TERCERA_EDAD-1) + ")");
            System.out.println("(4) Tercera edad                (Edad: " + EDAD_MIN_TERCERA_EDAD + " - " + EDAD_MAX + ")");
            System.out.println("(5) Ninguna de las anteriores   (Edad: " + (EDAD_MAX_NINOS+1) + " - " + (EDAD_MIN_TERCERA_EDAD-1) + ")");
        
            //Validar opción descuento
            opcion = 0;
            boolean opcionValida = false;
            
            while (!opcionValida) {
                System.out.print("Ingrese el número correspondiente a su opción: ");
                try {
                    opcion = scanner.nextInt();
                    if (opcion >= 1 && opcion <= CANT_OPCIONES) {
                        opcionValida = true;
                    } else {
                        System.out.println("El número ingresado no es válido. Recuerde ingresar un número del 1 al " + CANT_OPCIONES + ".");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("El valor ingresado no es válido. Recuerde ingresar un número del 1 al " + CANT_OPCIONES + ".");
                } finally {
                    scanner.nextLine();
                }
            }
            
            //Ingreso de la edad
            boolean edadValida = false;
            int edad = 0;
            
            while (!edadValida) {
                System.out.print("\nIngrese su edad: ");
                try {
                    edad = scanner.nextInt();
                    if (edad >= EDAD_MIN && edad <= EDAD_MAX) {
                        edadValida = true;
                    } else {
                        System.out.println("La edad ingresada no es válida. Recuerde ingresar un número del " + EDAD_MIN + " al " + EDAD_MAX + ".\nIntente nuevamente.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("La edad ingresada no es válida. Recuerde ingresar un número del " + EDAD_MIN + " al " + EDAD_MAX + ".\nIntente nuevamente.");
                } finally {
                    scanner.nextLine();
                }
            }
            
            //Validar opcion descuento con edad
            switch (opcion) {
                case 1:
                    if (edad <= EDAD_MAX_NINOS) {
                        descuentoValido = true;
                    } else {
                        System.out.println(mensajeEdadInvalida);
                    }
                    break;
                case 2:
                    if (edad < EDAD_MIN_TERCERA_EDAD) {
                        System.out.println("¿Tiene su pase de estudiante?");
                        System.out.println("(1) Si");
                        System.out.println("(2) No");
                        
                        boolean estudianteValidado = false;
                        
                        while(!estudianteValidado) {
                            System.out.print("Ingrese el número correspondiente a su opción: ");
                            try {
                                byte opcionEstudiante = scanner.nextByte();
                                switch (opcionEstudiante) {
                                    case 1:
                                        System.out.println("Pase validado con éxito");
                                        estudianteValidado = true;
                                        descuentoValido = true;
                                        break;
                                    case 2:
                                        System.out.println("Sin pase no puede optar a este descuento.\nIntente nuevamente.");
                                        estudianteValidado = true;
                                        break;
                                    default:
                                        System.out.println("La opción ingresada no es válida. Recuerde ingresar el número 1 o 2.");
                                        break;
                                }
                            } catch (InputMismatchException e) {
                                System.out.println("La opción ingresada no es válida. Recuerde ingresar el número 1 o 2.");
                            } finally {
                                scanner.nextLine();
                            }
                        }
                    } else {
                        System.out.println(mensajeEdadInvalida);
                    }
                    break;
                case 3:
                    if (edad <= EDAD_MAX_NINOS || edad >= EDAD_MIN_TERCERA_EDAD) {
                        System.out.println(mensajeEdadInvalida);
                    } else {
                        descuentoValido = true;
                    }
                    break;
                case 4:
                    if (edad >= EDAD_MIN_TERCERA_EDAD) {
                        descuentoValido = true;
                    } else {
                        System.out.println(mensajeEdadInvalida);
                    }
                    break;
                case 5:
                    if (edad <= EDAD_MAX_NINOS || edad >= EDAD_MIN_TERCERA_EDAD) {
                        System.out.println(mensajeEdadInvalida);
                    } else {
                        descuentoValido = true;
                    }
                    break;
            }
        }
        
        return listaDescuentos.get(opcion-1);
    }
    
}


/**
 * Realización de pruebas y depuración:
 * A este programa le realice pruebas a cada método individualmente y en conjunto
 * Con puntos de depuración para verificar el correcto funcionamiento del código.
 * 
 * En el método principal "main", verifique que cada método funcionara correctamente según
 * la opción del usuario.
 * Mostrando primero un menú a través del método "menu()", en el cual se valida con
 * un try-catch el ingreso de datos del usuario y si no es lo esperado muestra un mensaje
 * por pantalla y regresa al menú.
 * Probe ingresando letras y números fuera de rango, por ejemplo: "njk", "njk k", "12", "0".
 * Estas pruebas las hice cada vez que se le solicita al usuario ingresar datos.
 * 
 * También realice pruebas en los métodos que muestran información como "imprimirBoleta()"
 * o "mostrarResumen()" cuando aún no hay datos de ventas o reservas, mostrando un mensaje
 * por pantalla y dando la posibilidad de volver a elegir una opción del menú, evitando
 * ingresar a un bucle sin fin.
 * Esto lo probe al ejecutar el programa, en el menú, seleccionando las opciones 4, 5 y 6.
 * 
 * Otras pruebas que realice, usando puntos de depuración que se encuentran comentados
 * en el código, fue en el resto de métodos como "procesarEntradas()", "modificarVenta()", entre otros.
 * Para evaluar los valores que iban tomando las variables durante la ejecucion del programa y
 * de esta forma verificar, por ejemplo: la disponibilidad de las ubicaciones, el total de ingresos,
 * el descuento aplicado, los códigos de ventas, reservas y clientes, entre otras variables.
 * 
 * Estas son algunas de las pruebas y depuración que aplique en mi código durante su realización
 * para comprobar que el programa funciona bajo distintas situaciones.
 */
