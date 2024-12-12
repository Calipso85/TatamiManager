/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.tatamimanager.Alumnos;

import com.mycompany.tatamimanager.BBDD.DatabaseManager;
import com.mycompany.tatamimanager.BBDD.DatabaseControlAlumnos;
import com.mycompany.tatamimanager.Inicio;
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
public class ListaAlumnos extends javax.swing.JPanel {

    Object[] cabecera;
    DefaultTableModel modelo;
    public int idAlumno;
    
    /**
     * Creates new form ListaAlumnos
     */
    public ListaAlumnos() {
        initComponents();
        iniTabla();
    }
    
    public void iniTabla(){
        cabecera = new Object [] {"id","Nombre", "Apellidos", "Curso", "Cinturon", "Colegio", "Ver más", "Editar", "Eliminar"};
        
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
        tablaAlumnos.setModel(modelo);
        
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

            // Cargar los iconos desde el classpath
            ImageIcon iconVer = new ImageIcon(ListaAlumnos.class.getResource("/images/ojo.png"));
            ImageIcon iconEditar = new ImageIcon(ListaAlumnos.class.getResource("/images/lapiz.png"));
            ImageIcon iconEliminar = new ImageIcon(ListaAlumnos.class.getResource("/images/papelera.png"));

            while (resultado.next()) {
                // Agregar una nueva fila con los datos y los iconos
                modelo.addRow(new Object[] {
                    resultado.getInt("id_alumno"),  // Columna oculta
                    resultado.getString("nombre"), 
                    resultado.getString("apellidos"),
                    resultado.getString("curso"),
                    resultado.getString("cinturon"),
                    resultado.getString("nombre_colegio"),
                    iconVer,  // Icono para la columna "Ver Más"
                    iconEditar,  // Icono para la columna "Editar"
                    iconEliminar // Icono para la columna "Eliminar"
                });
            }
            
            // Ocultar la columna id_profesor
            tablaAlumnos.getColumnModel().getColumn(0).setMinWidth(0);
            tablaAlumnos.getColumnModel().getColumn(0).setMaxWidth(0);
            tablaAlumnos.getColumnModel().getColumn(0).setPreferredWidth(0);
            
            DatabaseManager.closeConnection();  //cierre de la conexión a la bbdd
            
        } catch (SQLException e) {
            Logger.getLogger(ListaAlumnos.class.getName()).log(Level.SEVERE, null, e);
            System.out.println("Error al conectar o ejecutar consulta a la base de datos.");
        } catch (Exception e) {
            Logger.getLogger(ListaAlumnos.class.getName()).log(Level.SEVERE, null, e);
            System.out.println("Error inesperado: " + e.getMessage());
        }
        
        //ajustar tamaño columnas 
        tablaAlumnos.getColumnModel().getColumn(1).setPreferredWidth(15);
        tablaAlumnos.getColumnModel().getColumn(2).setPreferredWidth(60);
        tablaAlumnos.getColumnModel().getColumn(3).setPreferredWidth(20);
        tablaAlumnos.getColumnModel().getColumn(4).setPreferredWidth(45);
        tablaAlumnos.getColumnModel().getColumn(5).setPreferredWidth(120);
        tablaAlumnos.getColumnModel().getColumn(6).setPreferredWidth(3);
        tablaAlumnos.getColumnModel().getColumn(7).setPreferredWidth(3);
        tablaAlumnos.getColumnModel().getColumn(8).setPreferredWidth(3); 
            System.out.println("Se actualiza la tabla Alumnos.");
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
        tablaAlumnos = new javax.swing.JTable();

        setBackground(new java.awt.Color(204, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Listado de Alumnos");

        tablaAlumnos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tablaAlumnos.setEnabled(false);
        tablaAlumnos.setRowSelectionAllowed(false);
        tablaAlumnos.getTableHeader().setReorderingAllowed(false);
        tablaAlumnos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaAlumnosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaAlumnos);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 873, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(175, 175, 175)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 378, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(85, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tablaAlumnosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaAlumnosMouseClicked
        if (evt.getClickCount() == 1 || evt.getClickCount() == 2) {
            // Obtener la fila y columna donde ocurrió el click
            int fila = tablaAlumnos.rowAtPoint(evt.getPoint());
            int columna = tablaAlumnos.columnAtPoint(evt.getPoint());
            // Obtener el id_alumno desde la tabla (columna oculta)
            idAlumno = (int) modelo.getValueAt(fila, 0);
            
            switch (columna) {
                case 6:
                    //Mostrar todos los datos del alumno
                    DatabaseControlAlumnos.mostrarInformacionAlumno(idAlumno);
                    break;
                case 7:
                    //editar
                    try (Connection conn = DatabaseManager.getConnection()) { // Obtiene la conexión
                        PreparedStatement st = conn.prepareStatement(
                                "SELECT nombre, apellidos, curso, anyo, nombre_tutor, telefono, correo, cinturon, id_colegio FROM alumnos WHERE id_alumno = ?"
                        );
                        st.setInt(1, idAlumno); // Establece el valor del parámetro
                        ResultSet resultado = st.executeQuery();
                        
                        System.out.println("id_alumno ="+idAlumno);
                        
                        if (resultado.next()) {
                            String nombreAlumno = resultado.getString("nombre");
                            String apellAlumno = resultado.getString("apellidos");
                            String cursoAlumno = resultado.getString("curso");
                            int anyoAlumno = resultado.getInt("anyo");
                            String tutorAlumno = resultado.getString("nombre_tutor");
                            int telefonoAlumno = resultado.getInt("telefono");
                            String correoAlumno = resultado.getString("correo");
                            String cinturonAlumno = resultado.getString("cinturon");
                            int coleAlumno = resultado.getInt("id_colegio");
                            
                            // Cambiar al panel AddColegios y pasarle los datos
                            AddAlumnos panelAddAlumnos = new AddAlumnos();
                            panelAddAlumnos.actualizarDatos(idAlumno, nombreAlumno, apellAlumno, cursoAlumno, anyoAlumno, tutorAlumno,
                                    telefonoAlumno, correoAlumno, cinturonAlumno, coleAlumno);
                            
                            JFrame inicioFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
                            if (inicioFrame instanceof Inicio) {
                                ((Inicio) inicioFrame).cambiarPanel(panelAddAlumnos);
                            }
                        }
                        
                    } catch (SQLException e) {
                        Logger.getLogger(ListaAlumnos.class.getName()).log(Level.SEVERE, null, e);
                        System.out.println("Error al conectar o ejecutar consulta a la base de datos.");
                    } catch (Exception e) {
                        Logger.getLogger(ListaAlumnos.class.getName()).log(Level.SEVERE, null, e);
                        System.out.println("Error inesperado: " + e.getMessage());
                    }   
                    break;
                case 8:
                    //eliminar
                    int confirm = JOptionPane.showConfirmDialog(null,
                            "¿Estás seguro de que deseas eliminar este alumno?",
                            "Confirmar eliminación",
                            JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        DatabaseControlAlumnos.eliminarAlumno(idAlumno);
                    }   iniTabla();
                    break;
                default:
                    break;
            }
        } 
    }//GEN-LAST:event_tablaAlumnosMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaAlumnos;
    // End of variables declaration//GEN-END:variables
}
