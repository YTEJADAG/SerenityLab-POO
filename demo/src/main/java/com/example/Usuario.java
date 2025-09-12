package com.example;

import java.util.Scanner;

public abstract class Usuario {
    protected String nombreUsuario;
    protected String contrasena;
    protected String ocupacion;

    public Usuario(String nombreUsuario, String contrasena, String ocupacion) {
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
        this.ocupacion = ocupacion;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public String getOcupacion() {
        return ocupacion;
    }

    public abstract void mostrarMenu(SistemaSerenityLab sistema, Scanner scanner);
}