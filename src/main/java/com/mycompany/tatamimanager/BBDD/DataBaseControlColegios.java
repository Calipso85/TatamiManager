package com.mycompany.tatamimanager.BBDD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import com.mycompany.tatamimanager.colegios.*;
import javax.swing.JOptionPane;

public class DataBaseControlColegios {
    
    public static void guardarColegio(String nombreCole, String direccionCole, int telefonoCole, String barrioCole, int cpCole) {
        try (Connection conn = DatabaseManager.getConnection()) { // Obtiene la conexión
        
             String query = "INSERT INTO colegios (nombre, direccion, telefono, barrio, cod_postal) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement st = conn.prepareStatement(query)) {
                st.setString(1, nombreCole);
                st.setString(2, direccionCole);
                st.setInt(3, telefonoCole);
                st.setString(4, barrioCole);
                st.setInt(5, cpCole);

                // Ejecutar el INSERT
                int filasInsertadas = st.executeUpdate();
                if (filasInsertadas > 0) {
                    Logger.getLogger(AddColegios.class.getName()).log(Level.INFO, "Datos guardados correctamente en la tabla colegios: {0}, {1}, {2}, {3}, {4}", 
                    new Object[]{nombreCole, direccionCole, telefonoCole, barrioCole, cpCole});
                } else {
                    Logger.getLogger(AddColegios.class.getName()).log(Level.WARNING, "No se pudieron guardar los datos en la tabla colegios.");
                }
            }
        
            DatabaseManager.closeConnection();  //cierre de la conexión a la bbdd
            
        } catch (SQLException e) {
            Logger.getLogger(AddColegios.class.getName()).log(Level.SEVERE, null, e);
            System.out.println("Error al conectar o ejecutar consulta a la base de datos.");
        } catch (Exception e) {
            Logger.getLogger(AddColegios.class.getName()).log(Level.SEVERE, null, e);
            System.out.println("Error inesperado: " + e.getMessage());
        }
    }

    public static void listarColegios(DefaultTableModel modelo){
        try (Connection conn = DatabaseManager.getConnection()) { // Obtiene la conexión
            System.out.println("Connection created successfully");

            // Sentencias
            modelo.setRowCount(0);
            PreparedStatement st = conn.prepareStatement(
                "SELECT nombre, direccion, telefono, barrio, cod_postal FROM colegios"
            );
            ResultSet resultado = st.executeQuery();

            while (resultado.next()) {
                modelo.addRow(new Object[] {
                    resultado.getString("nombre"), 
                    resultado.getString("direccion"),
                    resultado.getInt("telefono"),
                    resultado.getString("barrio"),
                    resultado.getInt("cod_postal")
                });
            }
        } catch (SQLException e) {
            Logger.getLogger(ListaColegios.class.getName()).log(Level.SEVERE, null, e);
            System.out.println("Error al conectar o ejecutar consulta a la base de datos.");
        } catch (Exception e) {
            Logger.getLogger(ListaColegios.class.getName()).log(Level.SEVERE, null, e);
            System.out.println("Error inesperado: " + e.getMessage());
        }
    }
    public static void editarColegio(String nombreCole, String direccionCole, int telefonoCole, String barrioCole, int cpCole, int id){
        try (Connection conn = DatabaseManager.getConnection()) {
                
            String query = "UPDATE colegios SET nombre = ?, direccion = ?, telefono = ?, barrio = ?, cod_postal = ? "
                         + "WHERE id_colegio = ?";
            PreparedStatement st = conn.prepareStatement(query);

            // Asignar los valores de los campos de texto a la consulta
            st.setString(1, nombreCole);
            st.setString(2, direccionCole);
            st.setInt(3, telefonoCole);
            st.setString(4, barrioCole);
            st.setInt(5, cpCole);
            st.setInt(6, id); // id es el identificador del colegio que se está editando

            // Ejecutar la consulta
            int rowsUpdated = st.executeUpdate();

            // Verificar si se realizó el cambio
            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(null , "El colegio ha sido actualizado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null , "No se pudo actualizar el colegio.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null , "Error al actualizar el colegio: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            Logger.getLogger(AddColegios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
