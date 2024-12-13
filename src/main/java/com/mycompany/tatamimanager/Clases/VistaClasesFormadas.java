/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.tatamimanager.Clases;

import com.mycompany.tatamimanager.BBDD.DatabaseControlClases;
import com.mycompany.tatamimanager.BBDD.DatabaseManager;
import com.mycompany.tatamimanager.Componentes_ComboBox.ComboBox_Item;
import com.mycompany.tatamimanager.Componentes_ComboBox.metodos_ComboBox;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author diana
 */
public class VistaClasesFormadas extends javax.swing.JPanel {

    private JComboBox<ComboBox_Item> comboBox_colegios;
    private JComboBox<ComboBox_Item> comboBox_clases;
    Object[] cabecera;
    DefaultTableModel modelo;
    public int idAlumno;
    public int idClase;
    
    /**
     * Creates new form VistaClasesFormadas
     */
    public VistaClasesFormadas(){
        initComponents();
        crearComboBox();
    }
    public VistaClasesFormadas(int idClase) {
        initComponents();
        
        crearComboBox();
        
        this.idClase = idClase; //inicializar id
        
        //Seleccionar ambos comboBox con los valores recibidos
        seleccionarComboBoxPorId(comboBox_clases, idClase);
    }
    
    public void crearComboBox(){
        //Crear ComboBox clases
        comboBox_clases = new JComboBox<>();
        this.add(comboBox_clases);
        comboBox_clases.setBounds(130, 75, 210, 25);
        metodos_ComboBox.mostrarDatos_ComboBox(comboBox_clases, "id_clase", "clase", "clases");
        
        comboBox_clases.addActionListener(new ActionListener() { 
            @Override 
            public void actionPerformed(ActionEvent e) { 
                
                ComboBox_Item claseSeleccionada = (ComboBox_Item) comboBox_clases.getSelectedItem(); 
                int index = comboBox_clases.getSelectedIndex();
                
                if(index != 0){
                    iniTabla(claseSeleccionada.getId()); // Llamar al método iniTabla con el id del colegio seleccionado 
                }
            }
        }); 
    }

    private void seleccionarComboBoxPorId(JComboBox<ComboBox_Item> comboBox, int id) { 
        for (int i = 0; i < comboBox.getItemCount(); i++) { 
            ComboBox_Item item = comboBox.getItemAt(i); 
            if (item.getId() == id) { 
                comboBox.setSelectedIndex(i); break; 
            } 
        } 
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
    
    public void iniTabla(int idClase){
        cabecera = new Object [] {"id","Nombre", "Apellidos", "Curso", "Cinturon", "Clase", ""};
        
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
                "clases.nombre AS nombre_clase " +
                "FROM alumnos " +
                "JOIN clases ON alumnos.id_clase = clases.id_clase " + 
                "WHERE alumnos.id_clase = ?";
            PreparedStatement st = conn.prepareStatement(query);
            st.setInt(1, idClase); // Asignar el id del colegio seleccionado
            ResultSet resultado = st.executeQuery();

            while (resultado.next()) {
                // Agregar una nueva fila con los datos y los iconos
                modelo.addRow(new Object[] {
                    resultado.getInt("id_alumno"),  // Columna oculta
                    resultado.getString("nombre"), 
                    resultado.getString("apellidos"),
                    resultado.getString("curso"),
                    resultado.getString("cinturon"),
                    resultado.getString("nombre_clase"),
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

        jScrollPane1 = new javax.swing.JScrollPane();
        tablaListaAlumnos = new javax.swing.JTable();
        lb_profesor = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(204, 255, 255));

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

        lb_profesor.setText("Clase:");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setText("Listado de Clases Formadas");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(189, 189, 189)
                        .addComponent(jLabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(89, 89, 89)
                        .addComponent(lb_profesor))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 854, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel2)
                .addGap(27, 27, 27)
                .addComponent(lb_profesor)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(90, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lb_profesor;
    private javax.swing.JTable tablaListaAlumnos;
    // End of variables declaration//GEN-END:variables
}
