/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tatamimanager.BBDD;

import com.mycompany.tatamimanager.Inicio;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import com.mycompany.tatamimanager.Profesores.*;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;


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
    
    public static void guardarProfesor(String nombreProfe, String apellidos, String dni, int telefono, String correo){
        try (Connection conn = DatabaseManager.getConnection()) { // Obtiene la conexión
        
                String query = "INSERT INTO profesores (nombre, apellidos, dni, telefono, correo) VALUES (?, ?, ?, ?, ?)";
                try (PreparedStatement st = conn.prepareStatement(query)) {
                    st.setString(1, nombreProfe);
                    st.setString(2, apellidos);
                    st.setString(3, dni);
                    st.setInt(4, telefono);
                    st.setString(5, correo);

                    // Ejecutar el INSERT
                    int filasInsertadas = st.executeUpdate();
                    if (filasInsertadas > 0) {
                        Logger.getLogger(AddProfesores.class.getName()).log(Level.INFO, "Datos guardados correctamente en la tabla profesores: {0}, {1}, {2}, {3}, {4}", 
                        new Object[]{nombreProfe, apellidos, dni, telefono, correo});
                    } else {
                        Logger.getLogger(AddProfesores.class.getName()).log(Level.WARNING, "No se pudieron guardar los datos en la tabla colegios.");
                    }
                }

                DatabaseManager.closeConnection();  //cierre de la conexión a la bbdd
            
            } catch (SQLException e) {
                Logger.getLogger(AddProfesores.class.getName()).log(Level.SEVERE, null, e);
                System.out.println("Error al conectar o ejecutar consulta a la base de datos.");
            } catch (Exception e) {
                Logger.getLogger(AddProfesores.class.getName()).log(Level.SEVERE, null, e);
                System.out.println("Error inesperado: " + e.getMessage());
            }
    }
    
    public static void editarProfesor(String nombreProfe, String apellidos, String dni, int telefono, String correo, int id){
        try (Connection conn = DatabaseManager.getConnection()) {

                String query = "UPDATE profesores SET nombre = ?, apellidos = ?, dni = ?, telefono = ?, correo = ? "
                             + "WHERE id_profesor = ?";
                PreparedStatement st = conn.prepareStatement(query);

                // Asignar los valores de los campos de texto a la consulta
                st.setString(1, nombreProfe);
                st.setString(2, apellidos);
                st.setString(3, dni);
                st.setInt(4, telefono);
                st.setString(5, correo);
                st.setInt(6, id); // id es el identificador del colegio que se está editando

                // Ejecutar la consulta
                int rowsUpdated = st.executeUpdate();

                // Verificar si se realizó el cambio
                if (rowsUpdated > 0) {
                    JOptionPane.showMessageDialog(null , "El profesor ha sido actualizado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null , "No se pudo actualizar al profesor.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null , "Error al actualizar el profesor: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                Logger.getLogger(AddProfesores.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    
    public static void eliminaProfesor(int idProfesor){
        try (Connection conn = DatabaseManager.getConnection()) {
            // Sentencia SQL para eliminar
            PreparedStatement st = conn.prepareStatement(
                "DELETE FROM profesores WHERE id_profesor = ?"
            );
            st.setInt(1, idProfesor);
            int filasEliminadas = st.executeUpdate();
            System.out.println("id_profesor ="+idProfesor);

            // Verificar si se eliminó algo
            if (filasEliminadas > 0) {
                JOptionPane.showMessageDialog(null,
                    "El profesor ha sido eliminado correctamente.",
                    "Eliminación exitosa",
                    JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null,
                    "No se encontró profesor con ese ID. No se realizó ninguna eliminación.",
                    "Error",
                    JOptionPane.WARNING_MESSAGE);
            }

        } catch (SQLException e) {
            Logger.getLogger(ListaProfesores.class.getName()).log(Level.SEVERE, null, e);
            System.out.println("Error al conectar o ejecutar consulta a la base de datos.");
        } catch (Exception e) {
            Logger.getLogger(ListaProfesores.class.getName()).log(Level.SEVERE, null, e);
            System.out.println("Error inesperado: " + e.getMessage());
        }
    }
    
    
    
    
    public static void ObtenerIdProfesorSeleccionado(int idProfesor){
        
    }
}
