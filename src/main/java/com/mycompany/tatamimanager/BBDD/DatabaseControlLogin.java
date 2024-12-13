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
import javax.swing.JOptionPane;

/**
 *
 * @author diana
 */
public class DatabaseControlLogin {
    public static boolean  validarCredenciales(String usuario, String contrasenya){
         String query = "SELECT usuario, contrasenya FROM usuarios WHERE usuario = ? AND contrasenya = ?";
        try (Connection conn = DatabaseManager.getConnection();
            PreparedStatement ps = conn.prepareStatement(query)) {
            
            ps.setString(1, usuario); 
            ps.setString(2, contrasenya);
            ResultSet rs = ps.executeQuery();
            return rs.next(); // Devuelve true si se encuentra una coincidencia
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error al cargar las clases en el comboBox: " + ex.getMessage());
        } catch (Exception ex) { 
            Logger.getLogger(DatabaseControlLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
