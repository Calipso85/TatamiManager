/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.tatamimanager.Clases;

import com.mycompany.tatamimanager.BBDD.DatabaseControlClases;
import com.mycompany.tatamimanager.BBDD.DatabaseManager;
import com.mycompany.tatamimanager.Componentes_ComboBox.*;
import com.mycompany.tatamimanager.Inicio;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author diana
 */
public class AsignarAlumnos extends javax.swing.JPanel {

    private JComboBox<ComboBox_Item> comboBox_colegios;
    private JComboBox<ComboBox_Item> comboBox_clases;
    Object[] cabecera;
    DefaultTableModel modelo;
    public int idAlumno;
    
    /**
     * Creates new form ListaAlumnosEnClases
     */
    public AsignarAlumnos() {
        initComponents();
        
        //Crear ComboBox colegios
        comboBox_colegios = new JComboBox<>();
        this.add(comboBox_colegios);
        comboBox_colegios.setBounds(110, 65, 210, 25);
        
        //Crear ComboBox clases
        comboBox_clases = new JComboBox<>();
        this.add(comboBox_clases);
        comboBox_clases.setBounds(430, 65, 210, 25);
        
        // Modificar el ComboBox clases segun el colegio elegido--- no?
        comboBox_colegios.addActionListener(new ActionListener() { 
            @Override 
            public void actionPerformed(ActionEvent e) { 
                
                ComboBox_Item colegioSeleccionado = (ComboBox_Item) comboBox_colegios.getSelectedItem(); 
                int index = comboBox_colegios.getSelectedIndex();
                
                //System.out.println("id colegio: "+colegioSeleccionado.getId());
                if(index != 0){
                    DatabaseControlClases.mostrarClasesPorColegio(comboBox_clases, colegioSeleccionado.getId());  
                    iniTabla(colegioSeleccionado.getId()); // Llamar al método iniTabla con el id del colegio seleccionado 
                }
            }
        }); 
    }
    
    public void cargarDatosComboBox() {
        // Vaciar los ComboBox antes de cargar nuevos datos
        comboBox_colegios.removeAllItems();
        comboBox_clases.removeAllItems();

        // Cargar los datos en los ComboBox
        metodos_ComboBox.mostrarDatos_ComboBox(comboBox_colegios, "id_colegio", "colegio", "colegios");
        metodos_ComboBox.mostrarDatos_ComboBox(comboBox_clases, "id_clase", "clase", "clases");
    }
    
    public boolean validarDatos(){
        if(comboBox_colegios.getSelectedIndex()==0){
            JOptionPane.showMessageDialog(null, "Por favor, selecciona un colegio.", "Colegio sin seleccionar", JOptionPane.WARNING_MESSAGE);
            return false;        
        }else if(comboBox_clases.getSelectedIndex()==0){
            JOptionPane.showMessageDialog(null, "Por favor, selecciona una clase.", "Clase sin seleccionar", JOptionPane.WARNING_MESSAGE);
            return false;        
        }
        return true;
    }
    
    public void iniTabla(int idColegio){
        cabecera = new Object [] {"id","Nombre", "Apellidos", "Curso", "Cinturon", "Colegio", ""};
        
        modelo = new DefaultTableModel(cabecera, 0){ //0-> La tabla al principio no tiene columnas
            @Override
            public Class getColumnClass(int columnIndex) {
                switch(columnIndex){
                    case 6: return Boolean.class;
                    default:return String.class;
                }
            }
        };
        tablaListaAlumnos.setModel(modelo);
        
        try (Connection conn = DatabaseManager.getConnection()) { // Obtiene la conexión
            modelo.setRowCount(0); // Limpiar las filas anteriores
            
            // Sentencia sql
            modelo.setRowCount(0);
            String query = "SELECT alumnos.id_alumno, alumnos.nombre, alumnos.apellidos, alumnos.curso, alumnos.cinturon, " +
                "colegios.nombre AS nombre_colegio " +
                "FROM alumnos " +
                "JOIN colegios ON alumnos.id_colegio = colegios.id_colegio " + 
                "WHERE alumnos.id_colegio = ?";
            PreparedStatement st = conn.prepareStatement(query);
            st.setInt(1, idColegio); // Asignar el id del colegio seleccionado
            ResultSet resultado = st.executeQuery();

            while (resultado.next()) {
                // Agregar una nueva fila con los datos y los iconos
                modelo.addRow(new Object[] {
                    resultado.getInt("id_alumno"),  // Columna oculta
                    resultado.getString("nombre"), 
                    resultado.getString("apellidos"),
                    resultado.getString("curso"),
                    resultado.getString("cinturon"),
                    resultado.getString("nombre_colegio"),
                });
            }
            
            // Ocultar la columna id_profesor
            tablaListaAlumnos.getColumnModel().getColumn(0).setMinWidth(0);
            tablaListaAlumnos.getColumnModel().getColumn(0).setMaxWidth(0);
            tablaListaAlumnos.getColumnModel().getColumn(0).setPreferredWidth(0);
            
            DatabaseManager.closeConnection();  //cierre de la conexión a la bbdd
            
        } catch (SQLException e) {
            System.out.println("Error al conectar o ejecutar consulta a la base de datos.");
        } catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
        }
        
        //ajustar tamaño columnas 
        tablaListaAlumnos.getColumnModel().getColumn(1).setPreferredWidth(15);
        tablaListaAlumnos.getColumnModel().getColumn(2).setPreferredWidth(60);
        tablaListaAlumnos.getColumnModel().getColumn(3).setPreferredWidth(20);
        tablaListaAlumnos.getColumnModel().getColumn(4).setPreferredWidth(45);
        tablaListaAlumnos.getColumnModel().getColumn(5).setPreferredWidth(120);
        tablaListaAlumnos.getColumnModel().getColumn(6).setPreferredWidth(3);
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
        tablaListaAlumnos = new javax.swing.JTable();
        lb_coles = new javax.swing.JLabel();
        lb_profesor = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setBackground(new java.awt.Color(204, 255, 255));
        setForeground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("Selecciona los alumnos que estarán en esta clase:");

        tablaListaAlumnos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tablaListaAlumnos);

        lb_coles.setText("Colegio:");

        lb_profesor.setText("Clase:");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setText("Generar listado de Alumnos por Clase");

        jButton1.setBackground(new java.awt.Color(0, 102, 153));
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Guardar Listado");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addComponent(lb_coles)
                        .addGap(277, 277, 277)
                        .addComponent(lb_profesor))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel1))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 844, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(193, 193, 193)
                        .addComponent(jLabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(322, 322, 322)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lb_coles)
                    .addComponent(lb_profesor))
                .addGap(27, 27, 27)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addContainerGap(33, Short.MAX_VALUE))
        );

        jLabel2.getAccessibleContext().setAccessibleName("Generar listado de Alumnos por Clase");
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (!validarDatos()) {
            return; // Si alguna validación falla, detener la ejecución
        }
        
        //id de la clase seleccionada
        int idClase = metodos_ComboBox.obtenerIdItemSeleccionado(comboBox_clases);
        int idColegio = metodos_ComboBox.obtenerIdItemSeleccionado(comboBox_clases);
        
        // Recorrer las filas de la tabla y actualizar la base de datos 
        for (int i = 0; i < tablaListaAlumnos.getRowCount(); i++) { 
            Boolean isSelected = (Boolean) tablaListaAlumnos.getValueAt(i, 6);
            if (isSelected != null && isSelected) { 
                int idAlumno = (int) tablaListaAlumnos.getValueAt(i, 0);
                DatabaseControlClases.actualizarClaseAlumno(idAlumno, idClase); // Asignar a la clase seleccionada 
            }
        } 
        //cambiar panel a listaClases
        JFrame frameInicio = (JFrame) SwingUtilities.getWindowAncestor(this);
        if (frameInicio instanceof Inicio) {
            ((Inicio) frameInicio).cambiarPanel(new VistaClasesFormadas(idClase));
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lb_coles;
    private javax.swing.JLabel lb_profesor;
    private javax.swing.JTable tablaListaAlumnos;
    // End of variables declaration//GEN-END:variables
}
