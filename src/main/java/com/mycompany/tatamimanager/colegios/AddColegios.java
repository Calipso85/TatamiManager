/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.tatamimanager.colegios;

import com.mycompany.tatamimanager.DatabaseManager;
import com.mycompany.tatamimanager.Inicio;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author diana
 */
public class AddColegios extends javax.swing.JPanel {

    private String nombreCole;
    private String direccionCole;
    private int telefonoCole; 
    private String barrioCole;
    private int cpCole; 
        
        
    /**
     * Creates new form AddColegio
     */
    public AddColegios() {
        initComponents();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lb_barrio = new javax.swing.JLabel();
        txt_telf = new javax.swing.JTextField();
        lb_telf = new javax.swing.JLabel();
        lb_titulo = new javax.swing.JLabel();
        lb_nombreAlumno = new javax.swing.JLabel();
        lb_dir = new javax.swing.JLabel();
        txt_direccion = new javax.swing.JTextField();
        txt_nombre = new javax.swing.JTextField();
        txt_barrio = new javax.swing.JTextField();
        lb_cp = new javax.swing.JLabel();
        txt_cp = new javax.swing.JTextField();
        btn_GuardarColegio = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));

        lb_barrio.setText("Barrio:");

        lb_telf.setText("Teléfono:");

        lb_titulo.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lb_titulo.setText("Añadir Colegio");

        lb_nombreAlumno.setText("Nombre:");

        lb_dir.setText("Dirección:");

        lb_cp.setText("Código Postal:");

        txt_cp.setToolTipText("");

        btn_GuardarColegio.setBackground(new java.awt.Color(0, 102, 204));
        btn_GuardarColegio.setForeground(new java.awt.Color(255, 255, 255));
        btn_GuardarColegio.setText("Guardar Colegio");
        btn_GuardarColegio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_GuardarColegioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(138, 138, 138)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lb_dir, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lb_nombreAlumno, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lb_telf, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_direccion, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_telf, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lb_barrio, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lb_cp))
                                .addGap(28, 28, 28)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txt_cp)
                                    .addComponent(txt_barrio, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(204, 204, 204)
                        .addComponent(btn_GuardarColegio, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(187, 187, 187)
                        .addComponent(lb_titulo)))
                .addContainerGap(229, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addComponent(lb_titulo)
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lb_nombreAlumno))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_direccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lb_dir))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_telf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lb_telf))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_barrio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lb_barrio))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_cp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lb_cp))
                .addGap(30, 30, 30)
                .addComponent(btn_GuardarColegio)
                .addContainerGap(146, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btn_GuardarColegioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_GuardarColegioActionPerformed
        
        // Verificar si los campos están vacíos
        if (txt_nombre.getText().trim().isEmpty() || txt_direccion.getText().trim().isEmpty() || txt_telf.getText().trim().isEmpty() || 
            txt_barrio.getText().trim().isEmpty() || txt_cp.getText().trim().isEmpty()) {

            // Mostrar mensaje de advertencia
            JOptionPane.showMessageDialog(null, "Por favor, rellena todos los campos.", "Campos Vacíos", JOptionPane.WARNING_MESSAGE);

        } else {
            // Validar y asignar los valores
            nombreCole = txt_nombre.getText().trim();
            direccionCole = txt_direccion.getText().trim();
            barrioCole = txt_barrio.getText().trim();

            // Validar que el telefono sea un número y tenga 9 dígitos
            String telefono = txt_telf.getText().trim();
            if (!telefono.matches("\\d{9}")) {
                JOptionPane.showMessageDialog(null, "El teléfono debe ser un número de 9 dígitos", "Error en el Teléfono", JOptionPane.ERROR_MESSAGE);
                return; // Salir del método si la validación falla
            } 
            telefonoCole = Integer.parseInt(txt_telf.getText().trim()); //asignat telf

            // Validar que el CP sea un número y tenga 5 dígitos
            String cpTexto = txt_cp.getText().trim(); 
            if ( !cpTexto.matches("\\d{5}")) { 
                JOptionPane.showMessageDialog(null, "El código postal debe ser un número de 5 dígitos.", "Error en el Código Postal", JOptionPane.ERROR_MESSAGE);
                return; // Salir del método si la validación falla
            }
            cpCole = Integer.parseInt(cpTexto); // asignar cp

            // Mensaje de éxito
            //JOptionPane.showMessageDialog(null, "Todos los datos han sido ingresados correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        }

        //guardar en la bbdd
        try (Connection conn = DatabaseManager.getConnection()) { // Obtiene la conexión
        
             String query = "INSERT INTO colegios (nombre, direccion, telefono, barrio, cod_postal) "
                     + "VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement st = conn.prepareStatement(query)) {
                st.setString(1, nombreCole);
                st.setString(2, direccionCole);
                st.setInt(3, telefonoCole);
                st.setString(4, barrioCole);
                st.setInt(5, cpCole);

                // Ejecutar el INSERT
                int filasInsertadas = st.executeUpdate();
                if (filasInsertadas > 0) {
                    Logger.getLogger(ListaColegios.class.getName()).log(Level.INFO, "Datos guardados correctamente en la tabla colegios: {0}, {1}, {2}, {3}, {4}", 
                    new Object[]{nombreCole, direccionCole, telefonoCole, barrioCole, cpCole});
                } else {
                    Logger.getLogger(ListaColegios.class.getName()).log(Level.WARNING, "No se pudieron guardar los datos en la tabla colegios.");
                }
            }
        
            DatabaseManager.closeConnection();  //cierre de la conexión a la bbdd
            
        } catch (SQLException e) {
            Logger.getLogger(ListaColegios.class.getName()).log(Level.SEVERE, null, e);
            System.out.println("Error al conectar o ejecutar consulta a la base de datos.");
        } catch (Exception e) {
            Logger.getLogger(ListaColegios.class.getName()).log(Level.SEVERE, null, e);
            System.out.println("Error inesperado: " + e.getMessage());
        }
        //cambiar panel a listaColegios
        JFrame frameInicio = (JFrame) SwingUtilities.getWindowAncestor(this);
        if (frameInicio instanceof Inicio) {
            ((Inicio) frameInicio).cambiarPanel(new ListaColegios());
        }
    }//GEN-LAST:event_btn_GuardarColegioActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_GuardarColegio;
    private javax.swing.JLabel lb_barrio;
    private javax.swing.JLabel lb_cp;
    private javax.swing.JLabel lb_dir;
    private javax.swing.JLabel lb_nombreAlumno;
    private javax.swing.JLabel lb_telf;
    private javax.swing.JLabel lb_titulo;
    private javax.swing.JTextField txt_barrio;
    private javax.swing.JTextField txt_cp;
    private javax.swing.JTextField txt_direccion;
    private javax.swing.JTextField txt_nombre;
    private javax.swing.JTextField txt_telf;
    // End of variables declaration//GEN-END:variables
}
