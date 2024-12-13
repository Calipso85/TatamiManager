/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.tatamimanager.Clases;

import com.mycompany.tatamimanager.BBDD.DatabaseManager;
import com.mycompany.tatamimanager.BBDD.DatabaseControlClases;
import com.mycompany.tatamimanager.Inicio;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author diana
 */
public class ListaClases extends javax.swing.JPanel {

    Object[] cabecera;
    DefaultTableModel modelo;
    public int idClase;
    JFrame inicioFrame;
    
    /**
     * Creates new form ListaClases
     */
    public ListaClases() {
        initComponents();
        iniTabla();
    }
/**
     * Metodo para inicializar la tabla
     */
    public void iniTabla(){
        
        cabecera = new Object [] {"id","Nombre", "Colegio", "Profesor", "Horario", "Días", "Ver más", "Editar", "Eliminar"};
        
        modelo = new DefaultTableModel(cabecera, 0){ //0-> La tabla al principio no tiene columnas
            @Override
            public Class getColumnClass(int columnIndex) {
                switch(columnIndex){
                    case 6: return ImageIcon.class;
                    case 7: return ImageIcon.class;
                    case 8: return ImageIcon.class;
                    default:return String.class;
                }
            }
        };
        tablaClases.setModel(modelo);
        
        try (Connection conn = DatabaseManager.getConnection()) { // Obtiene la conexión
            modelo.setRowCount(0); // Limpiar las filas anteriores
            
            // Sentencia sql
            modelo.setRowCount(0);
            PreparedStatement st = conn.prepareStatement(
                "SELECT clases.id_clase, " +
                    " clases.nombre AS nombre_clase, " +
                    " clases.horario, " +
                    " clases.dias, " +
                    " colegios.nombre AS nombre_colegio," +
                    " profesores.nombre AS nombre_profesor," +
                    " profesores.apellidos AS apellidos_profesor " +
                "FROM clases " +
                "LEFT JOIN colegios ON clases.id_colegio = colegios.id_colegio " +
                "LEFT JOIN profesores ON clases.id_profesor = profesores.id_profesor;"
            );
            ResultSet resultado = st.executeQuery();

            // Cargar los iconos desde el classpath
            ImageIcon iconVer = new ImageIcon(ListaClases.class.getResource("/images/ojo.png"));
            ImageIcon iconEditar = new ImageIcon(ListaClases.class.getResource("/images/lapiz.png"));
            ImageIcon iconEliminar = new ImageIcon(ListaClases.class.getResource("/images/papelera.png"));

            while (resultado.next()) {
                // Agregar una nueva fila con los datos y los iconos
                modelo.addRow(new Object[] {
                    resultado.getInt("id_clase"),  // Columna oculta
                    resultado.getString("nombre_clase"), 
                    resultado.getString("nombre_colegio"),
                    resultado.getString("nombre_profesor"),
                    resultado.getString("horario"),
                    resultado.getString("dias"),
                    iconVer,  // Icono para la columna "Ver Más"
                    iconEditar,  // Icono para la columna "Editar"
                    iconEliminar // Icono para la columna "Eliminar"
                });
            }
            
            // Ocultar la columna id_colegio
            tablaClases.getColumnModel().getColumn(0).setMinWidth(0);
            tablaClases.getColumnModel().getColumn(0).setMaxWidth(0);
            tablaClases.getColumnModel().getColumn(0).setPreferredWidth(0);
            
            DatabaseManager.closeConnection();  //cierre de la conexión a la bbdd
            
        } catch (SQLException e) {
            Logger.getLogger(ListaClases.class.getName()).log(Level.SEVERE, null, e);
            System.out.println("Error al conectar o ejecutar consulta a la base de datos.");
        } catch (Exception e) {
            Logger.getLogger(ListaClases.class.getName()).log(Level.SEVERE, null, e);
            System.out.println("Error inesperado: " + e.getMessage());
        }
        
        //ajustar tamaño columnas 
        tablaClases.getColumnModel().getColumn(1).setPreferredWidth(100);
        tablaClases.getColumnModel().getColumn(2).setPreferredWidth(160);
        tablaClases.getColumnModel().getColumn(3).setPreferredWidth(40);
        tablaClases.getColumnModel().getColumn(4).setPreferredWidth(50);
        tablaClases.getColumnModel().getColumn(5).setPreferredWidth(160);
        tablaClases.getColumnModel().getColumn(6).setPreferredWidth(20);
        tablaClases.getColumnModel().getColumn(7).setPreferredWidth(20);
        tablaClases.getColumnModel().getColumn(8).setPreferredWidth(20);
        
}
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaClases = new javax.swing.JTable();

        setBackground(new java.awt.Color(204, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Listado de Clases");

        tablaClases.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tablaClases.setRowSelectionAllowed(false);
        tablaClases.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaClasesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaClases);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(176, 176, 176)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 768, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(95, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tablaClasesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaClasesMouseClicked
        if (evt.getClickCount() == 1 || evt.getClickCount() == 2) {
            // Obtener la fila y columna donde ocurrió el click
            int fila = tablaClases.rowAtPoint(evt.getPoint());
            int columna = tablaClases.columnAtPoint(evt.getPoint());
            // Obtener el id_alumno desde la tabla (columna oculta)
            idClase = (int) modelo.getValueAt(fila, 0);
            
            switch (columna) {
                case 6:
                    //Cambiar de panel para ver la lista de alumnos de esa clase
                    VistaClasesFormadas vistaClases= new VistaClasesFormadas(idClase);

                    inicioFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
                    if (inicioFrame instanceof Inicio) {
                        ((Inicio) inicioFrame).cambiarPanel(vistaClases);
                    }
                    
                    break;
                case 7:
                    //editar
                    try (Connection conn = DatabaseManager.getConnection()) { // Obtiene la conexión
                        // Sentencia sql para obtener el id de la clase
                        PreparedStatement st = conn.prepareStatement(
                                "SELECT nombre, horario, dias, id_colegio, id_profesor FROM clases WHERE id_clase = ?"
                        );
                        st.setInt(1, idClase); // Establece el valor del parámetro
                        ResultSet resultado = st.executeQuery();
                        
                        System.out.println("id_alumno ="+idClase);
                        
                        if (resultado.next()) {
                            String nombreClase = resultado.getString("nombre");
                            String horario = resultado.getString("horario");
                            String dias = resultado.getString("dias");
                            int cole = resultado.getInt("id_colegio");
                            int profe = resultado.getInt("id_profesor");
                            
                            //separar horario por el -
                            String[] partes = horario.split(" - "); 
                            String horaInicio = partes[0]; 
                            String horaFin = partes[1];
                            
                            // Cambiar al panel AddClases y pasarle los datos
                            AddClase panelAddClase = new AddClase();
                            panelAddClase.actualizarDatos(idClase, nombreClase, horaInicio, horaFin, dias, cole, profe);
                            
                            inicioFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
                            if (inicioFrame instanceof Inicio) {
                                ((Inicio) inicioFrame).cambiarPanel(panelAddClase);
                            }
                        }
                        
                    } catch (SQLException e) {
                        Logger.getLogger(ListaClases.class.getName()).log(Level.SEVERE, null, e);
                        System.out.println("Error al conectar o ejecutar consulta a la base de datos.");
                    } catch (Exception e) {
                        Logger.getLogger(ListaClases.class.getName()).log(Level.SEVERE, null, e);
                        System.out.println("Error inesperado: " + e.getMessage());
                    }   
                    break; 
                case 8:
                    //eliminar
                    int confirm = JOptionPane.showConfirmDialog(null,
                            "¿Estás seguro de que deseas eliminar esta clase?",
                            "Confirmar eliminación",
                            JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                       DatabaseControlClases.eliminarClase(idClase);
                    }   
                    iniTabla();
                    break;
                default:
                    break;
            }
        } 
    }//GEN-LAST:event_tablaClasesMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaClases;
    // End of variables declaration//GEN-END:variables
}
