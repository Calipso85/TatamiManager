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
import com.mycompany.tatamimanager.Alumnos.*;
import javax.swing.JOptionPane;

/**
 *
 * @author diana
 */
public class DatabaseControlAlumnos {
    
    public static void guardarAlumno(String nombreAlumno, String apellidos, String curso, int anyo, String nombreTutor, int telf, String correo, 
           String cinturon, int id_profesor){
       
       try (Connection conn = DatabaseManager.getConnection()) { // Obtiene la conexión
            String query = "INSERT INTO alumnos (nombre, apellidos, curso, anyo, nombre_tutor, telefono, correo, cinturon) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement st = conn.prepareStatement(query)) {
                st.setString(1, nombreAlumno);
                st.setString(2, apellidos);
                st.setString(3, curso);
                st.setInt(4, anyo);
                st.setString(5, nombreTutor);
                st.setInt(6, telf);
                st.setString(7, correo);
                st.setString(8, cinturon);

                // Ejecutar el INSERT
                int filasInsertadas = st.executeUpdate();
                if (filasInsertadas > 0) {
                    Logger.getLogger(AddAlumnos.class.getName()).log(Level.INFO, "Datos guardados correctamente en la tabla alumnos");
                } else {
                    Logger.getLogger(AddAlumnos.class.getName()).log(Level.WARNING, "No se pudieron guardar los datos en la tabla alumnos.");
                }
            }

            DatabaseManager.closeConnection();  //cierre de la conexión a la bbdd

        } catch (SQLException e) {
            Logger.getLogger(AddAlumnos.class.getName()).log(Level.SEVERE, null, e);
            System.out.println("Error al conectar o ejecutar consulta a la base de datos.");
        } catch (Exception e) {
            Logger.getLogger(AddAlumnos.class.getName()).log(Level.SEVERE, null, e);
            System.out.println("Error inesperado: " + e.getMessage());
        }
    }
    
    
    public static void editarAlumno(String nombreAlumno, String apellidos, String curso, int anyo, String nombreTutor, int telf, 
            String correo, String cinturon, int idColegio, int id){ 
        try (Connection conn = DatabaseManager.getConnection()) {
            String query = "UPDATE alumnos SET nombre = ?, apellidos = ?, curso = ?, anyo = ?, nombre_tutor = ?, telefono = ?, correo = ?,"
                    + " cinturon = ?, id_colegio = ?"
                    + "WHERE id_alumno = ?";
            PreparedStatement st = conn.prepareStatement(query);

            // Asignar los valores de los campos de texto a la consulta
            st.setString(1, nombreAlumno);
            st.setString(2, apellidos);
            st.setString(3, curso);
            st.setInt(4, anyo);
            st.setString(5, nombreTutor);
            st.setInt(6, telf);
            st.setString(7, correo);
            st.setString(8, cinturon);
            st.setInt(9, idColegio);
            st.setInt(10, id); // id es el identificador del alumno que se está editando

            // Ejecutar la consulta
            int rowsUpdated = st.executeUpdate();

            // Verificar si se realizó el cambio
            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(null , "El alumno ha sido actualizado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null , "No se pudo actualizar al alumno.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null , "Error al actualizar el alumno. ", "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            Logger.getLogger(AddAlumnos.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    
    public static void eliminarAlumno(int idAlumno){
        try (Connection conn = DatabaseManager.getConnection()) {
            // Sentencia SQL para eliminar
            PreparedStatement st = conn.prepareStatement(
                "DELETE FROM alumnos WHERE id_alumno = ?"
            );
            st.setInt(1, idAlumno);
            int filasEliminadas = st.executeUpdate();
            System.out.println("id_alumno ="+idAlumno);

            // Verificar si se eliminó algo
            if (filasEliminadas > 0) {
                JOptionPane.showMessageDialog(null,
                    "El alumno ha sido eliminado correctamente.",
                    "Eliminación exitosa",
                    JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null,
                    "No se encontró alumno con ese ID. No se realizó ninguna eliminación.",
                    "Error",
                    JOptionPane.WARNING_MESSAGE);
            }
        } catch (SQLException e) {
            Logger.getLogger(ListaAlumnos.class.getName()).log(Level.SEVERE, null, e);
            System.out.println("Error al conectar o ejecutar consulta a la base de datos.");
        } catch (Exception e) {
            Logger.getLogger(ListaAlumnos.class.getName()).log(Level.SEVERE, null, e);
            System.out.println("Error inesperado: " + e.getMessage());
        }
    }
    
}
