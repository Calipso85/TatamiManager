/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tatamimanager.BBDD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
import com.mycompany.tatamimanager.Profesores.*;


/**
 *
 * @author diana
 */
public class DatabaseControlProfesores {
    
    public static void comprobarDni(String dni){
        try (Connection conn = DatabaseManager.getConnection()) { // Obtiene la conexión
                String validadDniQuery = "SELECT COUNT(*) FROM profesores WHERE dni = ?";
                try (PreparedStatement checkStmt = conn.prepareStatement(validadDniQuery)) {
                    checkStmt.setString(1, dni);
                    ResultSet rs = checkStmt.executeQuery();

                    if (rs.next() && rs.getInt(1) > 0) { 
                        // Si el resultado es mayor que 0, el DNI ya existe
                        JOptionPane.showMessageDialog(null, "El DNI ya está registrado.", "Error en el DNI", JOptionPane.ERROR_MESSAGE);
                        //return false; // Detener el flujo si el DNI no es único
                    }
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null , "Error al actualizar el colegio: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                Logger.getLogger(AddProfesores.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    public static void comprobarCorreo(String correo){
        try (Connection conn = DatabaseManager.getConnection()) { // Obtiene la conexión
                String validadDniQuery = "SELECT COUNT(*) FROM profesores WHERE correo = ?";
                try (PreparedStatement checkStmt = conn.prepareStatement(validadDniQuery)) {
                    checkStmt.setString(1, correo);
                    ResultSet rs = checkStmt.executeQuery();

                    if (rs.next() && rs.getInt(1) > 0) { 
                        // Si el resultado es mayor que 0, el DNI ya existe
                        JOptionPane.showMessageDialog(null, "El correo electrónico ya está registrado.", "Error en el correo", JOptionPane.ERROR_MESSAGE);
                        //return false; // Detener el flujo si el DNI no es único
                    }
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null , "Error al actualizar el colegio: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                Logger.getLogger(AddProfesores.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    public static void guardarProfesor(){
        
    }
    
    
}
