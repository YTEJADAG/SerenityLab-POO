package com.example;

import java.util.Scanner;

public class Psicologo extends Usuario {
    
    public Psicologo(String nombreUsuario, String contrasena) {
        super(nombreUsuario, contrasena, "Psicologo");
    }

    @Override
    public void mostrarMenu(SistemaSerenityLab sistema, Scanner scanner) {
        int opcion;
        do {
            System.out.println("--- Menú de Psicólogo ---");
            System.out.println("-");
            System.out.println("1. Añadir Horario de Disponibilidad");
            System.out.println("2. Ver Citas Agendadas");
            System.out.println("3. Cerrar Sesión");
            System.out.println("-");
            System.out.print("Ingresa tu opción: ");
            
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    sistema.anadirDisponibilidadPsicologo(this.nombreUsuario);
                    break;
                case 2:
                    sistema.verCitasAgendadas();
                    break;
                case 3:
                    System.out.println("Cerrando sesión de Psicólogo...");
                    break;
                default:
                    System.out.println("Opción inválida. Por favor, intenta de nuevo.");
            }
        } while (opcion != 3);
    }
}