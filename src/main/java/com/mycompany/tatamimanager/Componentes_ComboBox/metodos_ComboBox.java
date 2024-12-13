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
    
    //Metodo para mostrar en el comboBox los nombres de la tabla correspondiente
    @SuppressWarnings("unchecked")
    public static void mostrarDatos_ComboBox(JComboBox<ComboBox_Item> combo, String nombre_id, String texto, String tabla){
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
    
    //Método para obtener el Id del item seleccionado del comboBox
    public static int obtenerIdItemSeleccionado(JComboBox<ComboBox_Item> combo) {
        ComboBox_Item seleccionado = (ComboBox_Item) combo.getSelectedItem();
        if (seleccionado != null) {
            return seleccionado.getId();
        } else {
            throw new IllegalStateException("No hay colegio seleccionado.");
        }
    }
    
}
