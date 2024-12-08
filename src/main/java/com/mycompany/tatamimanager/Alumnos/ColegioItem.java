/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tatamimanager.Alumnos;

/**
 *
 * @author diana
 */
public class ColegioItem {
    
    private int id;
    private String nombre;

    public ColegioItem(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return nombre; // Esto es lo que se mostrará en el combo box
    }
}
