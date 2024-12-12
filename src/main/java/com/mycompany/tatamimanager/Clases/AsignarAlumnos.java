/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.tatamimanager.Clases;

import com.mycompany.tatamimanager.BBDD.DatabaseManager;
import com.mycompany.tatamimanager.Componentes_ComboBox.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JComboBox;
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
    private boolean coleElegido = false;
    private boolean claseElegida = false;
    
    /**
     * Creates new form ListaAlumnosEnClases
     */
    public AsignarAlumnos() {
        initComponents();
        
        //ComboBox colegios
        comboBox_colegios = new JComboBox<>();
        this.add(comboBox_colegios);
        comboBox_colegios.setBounds(110, 65, 210, 25);
        metodos_ComboBox.mostrarColegios(comboBox_colegios, "id_colegio", "colegio", "colegios");
        
        //ComboBox profesores
        comboBox_clases = new JComboBox<>();
        this.add(comboBox_clases);
        comboBox_clases.setBounds(430, 65, 210, 25);
        metodos_ComboBox.mostrarColegios(comboBox_clases, "id_clase", "clase", "clases");
        
        // Modificar el ComboBox clases segun el colegio elegido
        comboBox_colegios.addActionListener(new ActionListener() { 
            @Override 
            public void actionPerformed(ActionEvent e) { 
                ComboBox_Item colegioSeleccionado = (ComboBox_Item) comboBox_colegios.getSelectedItem(); 
                int index = comboBox_colegios.getSelectedIndex();
                System.out.println("index clase: "+index);
                if (colegioSeleccionado != null) { 
                    metodos_ComboBox.mostrarClasesPorColegio(comboBox_clases, colegioSeleccionado.getId()); 
                }else if(index != 0){
                    coleElegido = true;
                }
                comprobar();
            } 
        }); 
        
        // Añadir ActionListener al ComboBox de clases 
        comboBox_clases.addActionListener(new ActionListener() {
            @Override 
            public void actionPerformed(ActionEvent e) {
                ComboBox_Item claseSeleccionada = (ComboBox_Item) comboBox_clases.getSelectedItem(); 
                System.out.println(claseSeleccionada);
                if (claseSeleccionada != null && !claseSeleccionada.equals("Selecciona una clase") && claseSeleccionada.getId() != -1) {
                    claseElegida = true;
                } 
            } 
        });  
    /*
    // Añadir ActionListener al ComboBox de clases 
    comboBox_clases.addActionListener(new ActionListener() {
        @Override 
        public void actionPerformed(ActionEvent e) {
            ComboBox_Item claseSeleccionada = (ComboBox_Item) comboBox_clases.getSelectedItem(); 
            
            /*
            if (claseSeleccionada != null && claseSeleccionada.getId() != -1) {
                int idColegio = metodos_ComboBox.obtenerIdColegioPorClase(claseSeleccionada.getId()); 
                for (int i = 0; i < comboBox_colegios.getItemCount(); i++) { 
                    ComboBox_Item item = comboBox_colegios.getItemAt(i); 
                    if (item.getId() == idColegio) { 
                        comboBox_colegios.setSelectedIndex(i); 
                        break; 
                    } 
                }
                // Actualizar el comboBox_clases para mostrar las clases del colegio seleccionado
                metodos_ComboBox.mostrarClasesPorColegio(comboBox_clases, idColegio);
                /*
                // Seleccionar la clase original
                for (int i = 0; i < comboBox_clases.getItemCount(); i++) {
                    ComboBox_Item item = comboBox_clases.getItemAt(i);
                    if (item.getId() == claseOriginal.getId()) {
                        comboBox_clases.setSelectedIndex(i);
                        break;
                    }
                } 
                
            } 
        } 
    });  
        */
        
        System.out.println("cole elegido: "+coleElegido+ ", clase elegida: "+claseElegida);
        comprobar();
    }
    
    public void comprobar(){
        if(comboBox_colegios.getSelectedIndex() != 0 && comboBox_colegios.getSelectedIndex() != -1 
                || comboBox_clases.getSelectedIndex() != 0 && comboBox_clases.getSelectedIndex() != -1){
           
            //coleElegido = true;
            //claseElegida = true;
            System.out.println("cole elegido: "+coleElegido+ ", clase elegida: "+claseElegida);
            iniTabla();
        }
    }
    
    public void iniTabla(){
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
                "JOIN colegios ON alumnos.id_colegio = colegios.id_colegio";
            PreparedStatement st = conn.prepareStatement(query);
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
            System.out.println("Se actualiza la tabla.");
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
        jLabel2.setText("Listado de Alumnos por Clase");

        jButton1.setBackground(new java.awt.Color(0, 102, 153));
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Guardar Listado");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(298, 298, 298)
                        .addComponent(jLabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(320, 320, 320)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 844, javax.swing.GroupLayout.PREFERRED_SIZE))))
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addContainerGap(14, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


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
