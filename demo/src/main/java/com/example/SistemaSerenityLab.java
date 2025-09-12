package com.example;

import java.util.ArrayList;
import java.util.Scanner;

public class SistemaSerenityLab {

    private ArrayList<Usuario> usuarios = new ArrayList<>();
    private ArrayList<String> horariosDisponibles = new ArrayList<>();
    private ArrayList<String> citasAgendadas = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public void ejecutar() {
        int opcionPrincipal;
        System.out.println("------------------------------------");
        System.out.println("      ¡Bienvenido a SerenityLab!      ");
        System.out.println("------------------------------------");

        do {
            System.out.println("\n--- Menú Principal ---");
            System.out.println("1. Registrarse");
            System.out.println("2. Iniciar Sesión");
            System.out.println("3. Salir");
            System.out.print("Ingresa tu opción: ");
            opcionPrincipal = scanner.nextInt();
            scanner.nextLine();

            switch (opcionPrincipal) {
                case 1:
                    registrarUsuario();
                    break;
                case 2:
                    iniciarSesion();
                    break;
                case 3:
                    System.out.println("¡Adiós! Gracias por estar con SerenityLab.");
                    break;
                default:
                    System.out.println("Opción inválida. Por favor, intenta de nuevo.");
                    break;
            }
        } while (opcionPrincipal != 3);

        scanner.close();
    }

    private String obtenerEntradaCadena(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine();
    }

    private void registrarUsuario() {
        System.out.println("\n--- Registro ---");
        String usuario = obtenerEntradaCadena("Ingresa nombre de usuario: ");
        String clave = obtenerEntradaCadena("Ingresa contraseña: ");
        String ocupacion;

        while (true) {
            ocupacion = obtenerEntradaCadena("Ingresa ocupación (Estudiante/Psicologo): ");
            if (ocupacion.equalsIgnoreCase("Estudiante") || ocupacion.equalsIgnoreCase("Psicologo")) {
                break;
            } else {
                System.out.println("Ocupación inválida. Por favor, ingresa 'Estudiante' o 'Psicologo'.");
            }
        }

        if (usuario.isEmpty() || clave.isEmpty()) {
            System.out.println("Por favor, completa todos los campos.");
            return;
        }

        for (Usuario u : usuarios) {
            if (u.getNombreUsuario().equals(usuario)) {
                System.out.println("Ese nombre de usuario ya está en uso.");
                return;
            }
        }

        if (ocupacion.equalsIgnoreCase("Estudiante")) {
            usuarios.add(new Estudiante(usuario, clave));
        } else {
            usuarios.add(new Psicologo(usuario, clave));
        }

        System.out.println("¡Registro exitoso!");
    }

    private void iniciarSesion() {
        System.out.println("\n--- Inicio de Sesión ---");
        String usuario = obtenerEntradaCadena("Ingresa nombre de usuario: ");
        String clave = obtenerEntradaCadena("Ingresa contraseña: ");

        if (usuario.isEmpty() || clave.isEmpty()) {
            System.out.println("Por favor, llena todos los campos.");
            return;
        }

        for (Usuario u : usuarios) {
            if (u.getNombreUsuario().equals(usuario) && u.getContrasena().equals(clave)) {
                System.out.println("¡Inicio de sesión exitoso! Bienvenido/a " + u.getNombreUsuario() + ".");
                u.mostrarMenu(this, scanner);
                return;
            }
        }
        System.out.println("Usuario no encontrado o credenciales incorrectas. Por favor, regístrate primero.");
    }

    public void anadirDisponibilidadPsicologo(String nombrePsicologo) {
        System.out.println("\n--- Añadir Horario de Disponibilidad (Psicólogo) ---");
        System.out.println("Ingresa la fecha y hora disponible (ej. '2025-12-25 10:00 AM').");
        System.out.println("---");
        System.out.println("NOTA: Escribe 'fin' para terminar de añadir horarios.");
        System.out.println();

        while (true) {
            String horario = obtenerEntradaCadena("Horario a añadir: ");
            if (horario.equalsIgnoreCase("fin")) {
                System.out.println("Finalizando la adición de horarios.");
                break;
            }
            if (!horario.isEmpty()) {
                horariosDisponibles.add(horario + " (Psicólogo: " + nombrePsicologo + ")");
                System.out.println("----> Horario añadido: " + horario);
            } else {
                System.out.println("Por favor, ingresa una fecha y hora.");
            }
        }
    }

    public void mostrarHorariosDisponibles() {
        System.out.println("\n--- Horarios Disponibles ---");
        if (horariosDisponibles.isEmpty()) {
            System.out.println("No hay horarios disponibles en este momento. Por favor, espera a que un psicólogo añada su disponibilidad.");
            return;
        }
        for (int i = 0; i < horariosDisponibles.size(); i++) {
            System.out.println((i + 1) + ". " + horariosDisponibles.get(i));
        }
    }

    public void agendarCitaEstudiante(String nombreEstudiante) {
        mostrarHorariosDisponibles();
        if (horariosDisponibles.isEmpty()) {
            return;
        }

        System.out.print("\nElige el número del horario deseado: ");
        int numeroHorario = scanner.nextInt();
        scanner.nextLine();

        if (numeroHorario < 1 || numeroHorario > horariosDisponibles.size()) {
            System.out.println("Número de horario inválido.");
            return;
        }

        String horarioElegidoConPsicologo = horariosDisponibles.remove(numeroHorario - 1);
        String[] partes = horarioElegidoConPsicologo.split(" \\(Psicólogo: ");
        String horarioReal = partes[0];
        String nombrePsicologo = partes[1].substring(0, partes[1].length() - 1);
        String nuevaCita = nombreEstudiante + " - " + horarioReal + " (Psicólogo: " + nombrePsicologo + ")";
        citasAgendadas.add(nuevaCita);

        System.out.println("¡Cita agendada con éxito para " + nombreEstudiante + " con el Psicólogo " + nombrePsicologo + "!");
    }

    public void gestionarMisCitasEstudiante(String nombreEstudiante) {
        System.out.println("\n--- Tus Citas Agendadas (" + nombreEstudiante + ") ---");
        ArrayList<String> misCitas = new ArrayList<>();
        ArrayList<Integer> indicesCitasReales = new ArrayList<>();

        for (int i = 0; i < citasAgendadas.size(); i++) {
            if (citasAgendadas.get(i).startsWith(nombreEstudiante + " - ")) {
                misCitas.add(citasAgendadas.get(i));
                indicesCitasReales.add(i);
            }
        }

        if (misCitas.isEmpty()) {
            System.out.println("No tienes citas agendadas en este momento.");
            return;
        }

        for (int i = 0; i < misCitas.size(); i++) {
            System.out.println((i + 1) + ". " + misCitas.get(i));
        }

        System.out.print("\n¿Deseas cancelar alguna de estas citas? (Ingresa el número de la cita o 0 para volver): ");
        int numCitaACancelar = scanner.nextInt();
        scanner.nextLine();

        if (numCitaACancelar > 0 && numCitaACancelar <= misCitas.size()) {
            int indiceReal = indicesCitasReales.get(numCitaACancelar - 1);
            String citaCancelada = citasAgendadas.remove(indiceReal);
            String[] partes = citaCancelada.split(" - ");
            String horarioCompletoParaDisponibles = partes[1];
            horariosDisponibles.add(horarioCompletoParaDisponibles);
            System.out.println("Cita cancelada con éxito. El horario ha sido liberado.");
        } else if (numCitaACancelar == 0) {
            System.out.println("Volviendo al menú del Estudiante.");
        } else {
            System.out.println("Número de cita inválido.");
        }
    }

    public void verCitasAgendadas() {
        System.out.println("\n--- Todas las Citas Agendadas (Psicólogo) ---");
        if (citasAgendadas.isEmpty()) {
            System.out.println("No hay citas agendadas en este momento.");
            return;
        }
        for (int i = 0; i < citasAgendadas.size(); i++) {
            System.out.println((i + 1) + ". " + citasAgendadas.get(i));
        }
    }
}