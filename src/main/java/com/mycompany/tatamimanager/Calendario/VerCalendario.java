/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.tatamimanager.Calendario;

import java.awt.Color;
import java.util.Calendar;
import java.util.Date;
import com.toedter.calendar.IDateEvaluator;
import java.util.*;
import com.mycompany.tatamimanager.BBDD.DatabaseControlEventos;
import com.mycompany.tatamimanager.BBDD.DatabaseManager;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import javax.swing.JLabel;

/**
 *
 * @author diana
 */
public class VerCalendario extends javax.swing.JPanel  implements IDateEvaluator{

    private Set<Date> fechasEventos; // Fechas de los eventos
    
    /**
     * Creates new form Calendario
     */
    public VerCalendario() {
        initComponents();
        
        
        // Ocultar la numeración de semanas
        calendar.setWeekOfYearVisible(false);

        // Cargar fechas desde la base de datos
        fechasEventos = DatabaseControlEventos.obtenerFechas_Eventos();
        System.out.println(fechasEventos);

        // Añadir este evaluador al calendario
        calendar.getDayChooser().addDateEvaluator(this);
        calendar.repaint();
        
        // Agregar PropertyChangeListener para detectar cambios en la fecha seleccionada
        calendar.addPropertyChangeListener("calendar", evt -> {
            Date selectedDate = calendar.getDate(); // Obtener la fecha seleccionada
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String fechaTexto = sdf.format(selectedDate);
            System.out.println("Día seleccionado: " + fechaTexto);

            // Llamar al método para obtener los datos del evento y actualizar los JLabel
            DatabaseControlEventos.obtenerDatos_Eventos(fechaTexto, lb_nombre, lb_descripcion, lb_fecha, lb_hora, lb_lugar);
            
            // Forzar la actualización del calendario 
            actualizarCalendario();
            
        });

        
        calendar.addPropertyChangeListener("calendar", evt -> {
            Date selectedDate = calendar.getDate(); // Obtener la fecha seleccionada
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String fechaTexto = sdf.format(selectedDate);
            System.out.println("Día seleccionado: " + fechaTexto);

            // Llamar al método para obtener los datos del evento y actualizar los JLabel
            DatabaseControlEventos.obtenerDatos_Eventos(fechaTexto, lb_nombre, lb_descripcion, lb_fecha, lb_hora, lb_lugar);
        });
    }
    
    // Método para forzar la actualización del calendario 
    private void actualizarCalendario() { 
        calendar.getDayChooser().removeDateEvaluator(this); 
        calendar.getDayChooser().addDateEvaluator(this); 
        calendar.repaint(); 
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


    /**
     * Implementación de IDateEvaluator
     */
    @Override
    public boolean isSpecial(Date date) {
        // Comprobar si la fecha es especial (coincide con un evento)
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        //comprobar todas las fechas del mes con las que tienen eventos
        for (Date fechaEvento : fechasEventos) {  
            //System.out.println("Fecha cargada: " + sdf.format(fechaEvento));
            cal1.setTime(fechaEvento);
            cal2.setTime(date);
            
            if (cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH) &&
                cal1.get(Calendar.DAY_OF_MONTH) == cal2.get(Calendar.DAY_OF_MONTH)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Color getSpecialForegroundColor() {
         return Color.BLACK;
    }

    @Override
    public Color getSpecialBackroundColor() { 
        return Color.PINK;
    }

    @Override
    public String getSpecialTooltip() {
        return "Evento registrado";
    }
    
    @Override
    public boolean isInvalid(Date date) {
        return false; // No hay fechas inválidas
    }

    @Override
    public Color getInvalidForegroundColor() {
        return null;
    }

    @Override
    public Color getInvalidBackroundColor() {
        return null;
    }

    @Override
    public String getInvalidTooltip() {
        return null;
    } 
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        calendar = new com.toedter.calendar.JCalendar();
        lb_titulo = new javax.swing.JLabel();
        lb_descripcion = new javax.swing.JLabel();
        lb_nombre = new javax.swing.JLabel();
        lb_lugar = new javax.swing.JLabel();
        lb_hora = new javax.swing.JLabel();
        lb_fecha = new javax.swing.JLabel();

        setBackground(new java.awt.Color(204, 255, 255));

        lb_titulo.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lb_titulo.setText("Calendario");

        lb_descripcion.setText(" ");

        lb_nombre.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lb_nombre.setText(" ");

        lb_lugar.setText(" ");

        lb_hora.setText(" ");

        lb_fecha.setText(" ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(229, 229, 229)
                        .addComponent(lb_titulo))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(72, 72, 72)
                        .addComponent(calendar, javax.swing.GroupLayout.PREFERRED_SIZE, 508, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(169, 169, 169)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(lb_lugar, javax.swing.GroupLayout.DEFAULT_SIZE, 367, Short.MAX_VALUE)
                                .addComponent(lb_hora, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lb_fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lb_nombre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(lb_descripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 475, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(157, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(lb_titulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(calendar, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lb_nombre)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lb_descripcion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lb_fecha)
                .addGap(12, 12, 12)
                .addComponent(lb_hora)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lb_lugar)
                .addContainerGap(88, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JCalendar calendar;
    private javax.swing.JLabel lb_descripcion;
    private javax.swing.JLabel lb_fecha;
    private javax.swing.JLabel lb_hora;
    private javax.swing.JLabel lb_lugar;
    private javax.swing.JLabel lb_nombre;
    private javax.swing.JLabel lb_titulo;
    // End of variables declaration//GEN-END:variables


}
