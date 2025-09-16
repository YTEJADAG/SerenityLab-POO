package com.example;

import java.util.ArrayList;
import java.util.Scanner;

public class Estudiante extends Usuario {
    
    public Estudiante(String nombreUsuario, String contrasena) {
        super(nombreUsuario, contrasena, "Estudiante");
    }

    @Override
    public void mostrarMenu(SistemaSerenityLab sistema, Scanner scanner) {
        int opcion;
        do {
            System.out.println("- Menú de Estudiante -");
            System.out.println("-");
            System.out.println("1. Ver Horarios Disponibles");
            System.out.println("2. Agendar Cita");
            System.out.println("3. Ver/Cancelar Mis Citas");
            System.out.println("4. Cerrar Sesión");
            System.out.println("-");
            System.out.print("Ingresa tu opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    sistema.mostrarHorariosDisponibles();
                    break;
                case 2:
                    sistema.agendarCitaEstudiante(this.nombreUsuario);
                    break;
                case 3:
                    sistema.gestionarMisCitasEstudiante(this.nombreUsuario);
                    break;
                case 4:
                    System.out.println("Cerrando sesión de Estudiante...");
                    break;
                default:
                    System.out.println("Opción inválida. Por favor, intenta de nuevo.");
            }
        } while (opcion != 4);
    }
}