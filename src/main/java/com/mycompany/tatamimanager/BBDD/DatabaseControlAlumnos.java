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
import com.mycompany.tatamimanager.Alumnos.*;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author diana
 */
public class DatabaseControlAlumnos {
    
    public static void guardarAlumno(String nombreAlumno, String apellidos, String curso, int anyo, String nombreTutor, int telf, String correo, 
           String cinturon, int idColegio){
       
       try (Connection conn = DatabaseManager.getConnection()) { // Obtiene la conexión
            String query = "INSERT INTO alumnos (nombre, apellidos, curso, anyo, nombre_tutor, telefono, correo, cinturon, id_colegio) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement st = conn.prepareStatement(query)) {
                st.setString(1, nombreAlumno);
                st.setString(2, apellidos);
                st.setString(3, curso);
                st.setInt(4, anyo);
                st.setString(5, nombreTutor);
                st.setInt(6, telf);
                st.setString(7, correo);
                st.setString(8, cinturon);
                st.setInt(6, idColegio);

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

            // Asignar los valores de los campos de texto a la query
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

            // Ejecutar la query
            int rowsUpdated = st.executeUpdate();

            // Verificar si se realizó el cambio
            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(null , "El alumno ha sido actualizado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null , "No se pudo actualizar al alumno.", "Error", JOptionPane.ERROR_MESSAGE);
            }
            
            DatabaseManager.closeConnection();  //cierre de la conexión a la bbdd
            
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
                    "No se realizó ninguna eliminación.",
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

    public static void mostrarInformacionAlumno(int idAlumno) {
         try (Connection conn = DatabaseManager.getConnection()){
            // Consulta principal para obtener datos del alumno
            String query = "SELECT alumnos.nombre, alumnos.apellidos, alumnos.curso, alumnos.anyo, alumnos.nombre_tutor, alumnos.telefono, alumnos.correo, alumnos.cinturon, "
                            + "clases.nombre AS clase_nombre, colegios.nombre AS colegio_nombre, profesores.nombre AS profesor_nombre "
                            + "FROM alumnos "
                            + "LEFT JOIN clases ON alumnos.id_clase = clases.id_clase "
                            + "LEFT JOIN colegios ON alumnos.id_colegio = colegios.id_colegio "
                            + "LEFT JOIN profesores ON alumnos.id_profesor = profesores.id_profesor "
                            + "WHERE alumnos.id_alumno = ?";

            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, idAlumno);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                StringBuilder infoAlumno = new StringBuilder();
                
                // Añadir solo los campos que no sean nulos
            agregarTexto(infoAlumno, "Nombre", rs.getString("nombre"));
            agregarTexto(infoAlumno, "Apellidos", rs.getString("apellidos"));
            agregarTexto(infoAlumno, "Curso", rs.getString("curso"));
            agregarTexto(infoAlumno, "Año de nacimiento", rs.getInt("anyo") == 0 ? null : String.valueOf(rs.getInt("anyo")));
            agregarTexto(infoAlumno, "Nombre del padre/madre/tutor", rs.getString("nombre_tutor"));
            agregarTexto(infoAlumno, "Teléfono de los padres", rs.getInt("telefono") == 0 ? null : String.valueOf(rs.getInt("telefono")));
            agregarTexto(infoAlumno, "Correo electrónico de los padres", rs.getString("correo"));
            agregarTexto(infoAlumno, "Cinturón", rs.getString("cinturon"));
            agregarTexto(infoAlumno, "Clase", rs.getString("clase_nombre"));
            agregarTexto(infoAlumno, "Colegio", rs.getString("colegio_nombre"));
            agregarTexto(infoAlumno, "Profesor", rs.getString("profesor_nombre"));


                // Crear el JFrame con JTextArea
                JFrame frame = new JFrame("Información del Alumno");
                JTextArea textArea = new JTextArea(infoAlumno.toString());
                textArea.setEditable(false);
                textArea.setFont(new Font("Arial", Font.PLAIN, 14));
                JScrollPane scrollPane = new JScrollPane(textArea);

                // Configuración del frame
                frame.add(scrollPane);
                frame.setSize(450, 300);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró información del alumno.");
            }

            // Cerrar conexión
            DatabaseManager.closeConnection();
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener datos del alumno. ");
            e.printStackTrace();
        } catch (Exception e) {
            Logger.getLogger(ListaAlumnos.class.getName()).log(Level.SEVERE, null, e);
            System.out.println("Error inesperado: " + e.getMessage());
        }
        
    }
    private static void agregarTexto(StringBuilder sb, String etiqueta, String valor) {
        if (valor != null && !valor.trim().isEmpty()) {
            sb.append(etiqueta).append(":  ").append(valor).append("\n");
        }
    }

}
