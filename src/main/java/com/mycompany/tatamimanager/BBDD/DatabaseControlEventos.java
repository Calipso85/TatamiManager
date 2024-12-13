/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tatamimanager.BBDD;

import com.mycompany.tatamimanager.Eventos.AddEventos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;

/**
 *
 * @author diana
 */
public class DatabaseControlEventos {
    
    public static void guardarEventos(String nombreEvento, String descripcion, String fecha, String hora, String lugar){
        try (Connection conn = DatabaseManager.getConnection()) { // Obtiene la conexión
        
            String query = "INSERT INTO eventos (nombre, descripcion, fecha, horario, lugar) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement st = conn.prepareStatement(query)) {
                st.setString(1, nombreEvento);
                st.setString(2, descripcion);
                st.setString(3, fecha);
                st.setString(4, hora);
                st.setString(5, lugar);

                // Ejecutar el INSERT
                int filasInsertadas = st.executeUpdate();
                if (filasInsertadas > 0) {
                    Logger.getLogger(AddEventos.class.getName()).log(Level.INFO, "Datos guardados correctamente en la tabla eventos.");
                } else {
                    Logger.getLogger(AddEventos.class.getName()).log(Level.WARNING, "No se pudieron guardar los datos en la tabla eventos.");
                }
            }

            DatabaseManager.closeConnection();  //cierre de la conexión a la bbdd

        } catch (SQLException e) {
            Logger.getLogger(AddEventos.class.getName()).log(Level.SEVERE, null, e);
            System.out.println("Error al conectar o ejecutar consulta a la base de datos.");
        } catch (Exception e) {
            Logger.getLogger(AddEventos.class.getName()).log(Level.SEVERE, null, e);
            System.out.println("Error inesperado: " + e.getMessage());
        }
    }
    
    // Método para cargar las fechas de eventos
    public static Set<Date> obtenerFechas_Eventos(){
        Set<Date> fechas = new HashSet<>();

        try (Connection conn = DatabaseManager.getConnection()) { // Obtiene la conexión
            String query = "SELECT fecha FROM eventos";
            PreparedStatement st = conn.prepareStatement(query);
            ResultSet resultado = st.executeQuery();

            SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
            while (resultado.next()) {
                try {
                    // Convertir la fecha en texto al formato Date
                    String fechaTexto = resultado.getString("fecha");
                    Date fecha = formatoFecha.parse(fechaTexto);
                    fechas.add(fecha);
                } catch (ParseException e) {
                    e.printStackTrace(); // Manejo de errores en fechas mal formateadas
                }
            }
        } catch (Exception e) {
            e.printStackTrace(); // Manejo de errores en conexión o query
        }
        return fechas;
    }
    
    public static void obtenerDatos_Eventos(String fecha, JLabel lbNombre, JLabel lbDescripcion, JLabel lbFecha, JLabel lbHora, JLabel lbLugar) {
        try (Connection conn = DatabaseManager.getConnection()) { // Obtiene la conexión
            String query = "SELECT * FROM eventos WHERE fecha = ?"; // Consulta para obtener datos del evento
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, fecha); // Asignar la fecha como parámetro
            ResultSet resultado = st.executeQuery();

            if (resultado.next()) {
                // Cambiar el texto de los JLabel con los datos obtenidos
                lbNombre.setText(resultado.getString("nombre"));
                lbDescripcion.setText(resultado.getString("descripcion"));
                lbFecha.setText("Fecha: " + resultado.getString("fecha"));
                lbHora.setText("Hora: " + resultado.getString("horario"));
                lbLugar.setText("Lugar: " + resultado.getString("lugar"));
            } else {
                // Restablecer los textos si no hay evento
                lbNombre.setText("No hay eventos para esta fecha.");
                lbDescripcion.setText("");
                lbFecha.setText("");
                lbHora.setText("");
                lbLugar.setText("");
            }
        } catch (Exception e) {
            e.printStackTrace(); // Manejo de errores en conexión o query
        }
    }
}


