/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tatamimanager.Componentes_ComboBox;

import com.mycompany.tatamimanager.BBDD.DatabaseManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JComboBox;

/**
 *
 * @author diana
 */
public class metodos_ComboBox {
    
    @SuppressWarnings("unchecked")
    public static void mostrarColegios(JComboBox<ComboBox_Item> combo, String nombre_id, String texto, String tabla){
        combo.removeAllItems();
        // Agregar un elemento especial como primer texto
        combo.addItem(new ComboBox_Item(-1, "Selecciona un "+texto));
        String query = "SELECT "+nombre_id+", nombre FROM "+tabla;
        
         try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
             
            while (rs.next()) {
                int id = rs.getInt(nombre_id);
                String nombre = rs.getString("nombre");

                // Crear un objeto de ComboBox_Item y añadirlo al combo box
                ComboBox_Item item = new ComboBox_Item(id, nombre);
                combo.addItem(item);
            }
             
         } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error al cargar los datos en el comboBox: " + ex.getMessage());
        } catch (Exception ex) {
        }
    }
    
    public static int obtenerIdItemSeleccionado(JComboBox<ComboBox_Item> combo) {
        ComboBox_Item seleccionado = (ComboBox_Item) combo.getSelectedItem();
        if (seleccionado != null) {
            return seleccionado.getId();
        } else {
            throw new IllegalStateException("No hay colegio seleccionado.");
        }
    }
    
    public static void mostrarClasesPorColegio(JComboBox<ComboBox_Item> combo, int idColegio) { 
        combo.removeAllItems(); 
        combo.addItem(new ComboBox_Item(-1, "Selecciona una clase")); 
        String query = "SELECT id_clase, nombre FROM clases WHERE id_colegio = ?"; 
        try (Connection conn = DatabaseManager.getConnection(); 
            PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, idColegio); 
            ResultSet rs = ps.executeQuery(); 
            
            while (rs.next()) { 
                int id = rs.getInt("id_clase"); 
                String nombre = rs.getString("nombre"); 
                ComboBox_Item item = new ComboBox_Item(id, nombre); 
                combo.addItem(item); 
            } 
        } catch (SQLException ex) {
            ex.printStackTrace(); 
            System.out.println("Error al cargar las clases en el comboBox: " + ex.getMessage()); 
        } catch (Exception ex) { 
        } 
    }

    /*
    public static int obtenerIdColegioPorClase(int idClase) {
        String query = "SELECT id_colegio FROM clases WHERE id_clase = ?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, idClase);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("id_colegio");
            } else {
                throw new IllegalStateException("No se encontró el colegio para la clase con id: " + idClase);
            }
        } catch (SQLException e) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, e);
            throw new RuntimeException("Error al obtener el id del colegio para la clase con id: " + idClase, e);
        } catch (Exception ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Error inesperado al obtener el id del colegio para la clase con id: " + idClase, ex);
        }
    }

    */
    
}
