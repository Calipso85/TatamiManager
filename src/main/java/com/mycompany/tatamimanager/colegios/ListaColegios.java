/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.tatamimanager.colegios;

import com.mycompany.tatamimanager.BBDD.DatabaseManager;
import com.mycompany.tatamimanager.BBDD.DataBaseControlColegios;
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
public class ListaColegios extends javax.swing.JPanel {

    Object[] cabecera;
    DefaultTableModel modelo;
    int idColegio;

    /**
     * Creates new form ListaColegios
     */
    public ListaColegios() {
        initComponents();
        iniTabla();
    }
    
    /**
     * Metodo para inicializar la tabla
     */
    public void iniTabla(){
        
        cabecera = new Object [] {"id","Nombre", "Dirección", "Teléfono", "Barrio", "Código Postal", "Editar", "Eliminar"};
        
        modelo = new DefaultTableModel(cabecera, 0){ //0-> La tabla al principio no tiene columnas
            @Override
            public Class getColumnClass(int columnIndex) {
                switch(columnIndex){
                    case 3: return Integer.class;
                    case 5: return Integer.class;
                    case 6: return ImageIcon.class;
                    case 7: return ImageIcon.class;
                    default:return String.class;
                }
            }
        };
        tablaColegios.setModel(modelo);
        
        try (Connection conn = DatabaseManager.getConnection()) { // Obtiene la conexión
            modelo.setRowCount(0); // Limpiar las filas anteriores
            
            // Sentencia sql
            modelo.setRowCount(0);
            PreparedStatement st = conn.prepareStatement(
                "SELECT * FROM colegios"
            );
            ResultSet resultado = st.executeQuery();

            // Cargar los iconos desde el classpath
            ImageIcon iconEditar = new ImageIcon(ListaColegios.class.getResource("/images/lapiz.png"));
            ImageIcon iconEliminar = new ImageIcon(ListaColegios.class.getResource("/images/basura.png"));

            while (resultado.next()) {
                // Agregar una nueva fila con los datos y los iconos
                modelo.addRow(new Object[] {
                    resultado.getInt("id_colegio"),  // Columna oculta
                    resultado.getString("nombre"), 
                    resultado.getString("direccion"),
                    resultado.getInt("telefono"),
                    resultado.getString("barrio"),
                    resultado.getInt("cod_postal"),
                    iconEditar,  // Icono para la columna "Editar"
                    iconEliminar // Icono para la columna "Eliminar"
                });
            }
            
            // Ocultar la columna id_colegio
            tablaColegios.getColumnModel().getColumn(0).setMinWidth(0);
            tablaColegios.getColumnModel().getColumn(0).setMaxWidth(0);
            tablaColegios.getColumnModel().getColumn(0).setPreferredWidth(0);
            
            DatabaseManager.closeConnection();  //cierre de la conexión a la bbdd
            
        } catch (SQLException e) {
            Logger.getLogger(ListaColegios.class.getName()).log(Level.SEVERE, null, e);
            System.out.println("Error al conectar o ejecutar consulta a la base de datos.");
        } catch (Exception e) {
            Logger.getLogger(ListaColegios.class.getName()).log(Level.SEVERE, null, e);
            System.out.println("Error inesperado: " + e.getMessage());
        }
        
        //ajustar tamaño columnas 
        tablaColegios.getColumnModel().getColumn(1).setPreferredWidth(120);
        tablaColegios.getColumnModel().getColumn(2).setPreferredWidth(120);
        tablaColegios.getColumnModel().getColumn(3).setPreferredWidth(5);
        tablaColegios.getColumnModel().getColumn(4).setPreferredWidth(30);
        tablaColegios.getColumnModel().getColumn(5).setPreferredWidth(10);
        tablaColegios.getColumnModel().getColumn(6).setPreferredWidth(5);
        tablaColegios.getColumnModel().getColumn(7).setPreferredWidth(5);
        
        /*
        // Aplica el renderer solo a columnas Integer
        for (int col = 0; col < tablaColegios.getColumnCount(); col++) {
            if (tablaColegios.getColumnClass(col) == Integer.class) {
                tablaColegios.getColumnModel().getColumn(col).setCellRenderer(rightRenderer);
            }
        }
        */
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
        tablaColegios = new javax.swing.JTable();
        lb_titulo = new javax.swing.JLabel();

        setBackground(new java.awt.Color(204, 255, 255));

        tablaColegios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tablaColegios.setEnabled(false);
        tablaColegios.setRowSelectionAllowed(false);
        tablaColegios.getTableHeader().setReorderingAllowed(false);
        tablaColegios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaColegiosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaColegios);
        if (tablaColegios.getColumnModel().getColumnCount() > 0) {
            tablaColegios.getColumnModel().getColumn(0).setPreferredWidth(60);
            tablaColegios.getColumnModel().getColumn(1).setPreferredWidth(60);
            tablaColegios.getColumnModel().getColumn(2).setPreferredWidth(20);
            tablaColegios.getColumnModel().getColumn(3).setPreferredWidth(30);
            tablaColegios.getColumnModel().getColumn(4).setPreferredWidth(20);
        }

        lb_titulo.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lb_titulo.setText("Listado de Colegios");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(166, 166, 166)
                .addComponent(lb_titulo)
                .addContainerGap(482, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(lb_titulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(54, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tablaColegiosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaColegiosMouseClicked
        if (evt.getClickCount() == 1 || evt.getClickCount() == 2) {
            // Obtener la fila y columna donde ocurrió el clic
            int fila = tablaColegios.rowAtPoint(evt.getPoint());
            int columna = tablaColegios.columnAtPoint(evt.getPoint());
                // Obtener el id_colegio desde la tabla (columna oculta)
                idColegio = (int) modelo.getValueAt(fila, 0);

            if (columna == 6) { //si le doy a editar
                try (Connection conn = DatabaseManager.getConnection()) {               
                    // Sentencia sql para obtener el id del colegio 
                    PreparedStatement st = conn.prepareStatement( 
                            "SELECT * FROM colegios WHERE id_colegio = ?" 
                    ); 
                    st.setInt(1, idColegio); // Establece el valor del parámetro 
                    ResultSet resultado = st.executeQuery();
                    
                    System.out.println("id_colegio ="+idColegio);         

                     if (resultado.next()) {
                        String nombreColegio = resultado.getString("nombre");
                        String direccionColegio = resultado.getString("direccion");
                        int telefonoColegio = resultado.getInt("telefono");
                        String barrioColegio = resultado.getString("barrio");
                        int codPostal = resultado.getInt("cod_postal");

                        // Cambiar al panel AddColegios y pasarle los datos
                        AddColegios panelAddColegios = new AddColegios();
                        panelAddColegios.actualizarDatos(idColegio, nombreColegio, direccionColegio, telefonoColegio, barrioColegio, codPostal);

                        JFrame inicioFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
                        if (inicioFrame instanceof Inicio) {
                            ((Inicio) inicioFrame).cambiarPanel(panelAddColegios);
                        }
                    }
                    
                } catch (SQLException e) {
                    Logger.getLogger(ListaColegios.class.getName()).log(Level.SEVERE, null, e);
                    System.out.println("Error al conectar o ejecutar consulta a la base de datos.");
                } catch (Exception e) {
                    Logger.getLogger(ListaColegios.class.getName()).log(Level.SEVERE, null, e);
                    System.out.println("Error inesperado: " + e.getMessage());
                }
            }else if(columna == 7) {  //eliminar
                int confirm = JOptionPane.showConfirmDialog(null, 
                    "¿Estás seguro de que deseas eliminar este colegio?", 
                    "Confirmar eliminación", 
                    JOptionPane.YES_NO_OPTION);

                if (confirm == JOptionPane.YES_OPTION) {
                    DataBaseControlColegios.eliminarColegio(idColegio);
                }
                iniTabla();
            }
        }
    }//GEN-LAST:event_tablaColegiosMouseClicked
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lb_titulo;
    private javax.swing.JTable tablaColegios;
    // End of variables declaration//GEN-END:variables
}
