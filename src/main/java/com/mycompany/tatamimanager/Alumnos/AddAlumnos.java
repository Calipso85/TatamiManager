                                                                                                                                                                                          /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.tatamimanager.Alumnos;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author diana
 */
public class AddAlumnos extends javax.swing.JPanel {

    private String nombreAlumno;
    private String apellidos;
    private String curso; 
    private int anyo; 
    private String colegio;
    private String nombreTutor;
    private int telf;
    private String correo;

     /**
     * Creates new form Panel_AddAlumnos
     */
    public JButton getBtn_GuardarAlumno() {
        return btn_GuardarAlumno;
    }

    public JComboBox<String> getComboBox_colegios() {
        return comboBox_colegios;
    }

    public JTextField getTxt_anyo() {
        return txt_anyo;
    }

    public JTextField getTxt_apell() {
        return txt_apell;
    }

    public JTextField getTxt_correo() {
        return txt_correo;
    }

    public JTextField getTxt_curso() {
        return txt_curso;
    }

    public JTextField getTxt_nombre() {
        return txt_nombre;
    }

    public JTextField getTxt_telf() {
        return txt_telf;
    }

    public JTextField getTxt_tutor() {
        return txt_tutor;
    }

    
    /**
     * Creates new form Panel_AddAlumnos
     */
    public AddAlumnos() {
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

        lb_titulo = new javax.swing.JLabel();
        lb_nombre = new javax.swing.JLabel();
        lb_apell = new javax.swing.JLabel();
        lb_tutor = new javax.swing.JLabel();
        txt_apell = new javax.swing.JTextField();
        txt_nombre = new javax.swing.JTextField();
        txt_tutor = new javax.swing.JTextField();
        txt_correo = new javax.swing.JTextField();
        txt_anyo = new javax.swing.JTextField();
        lb_telf = new javax.swing.JLabel();
        lb_anyo = new javax.swing.JLabel();
        lb_cole = new javax.swing.JLabel();
        txt_curso = new javax.swing.JTextField();
        comboBox_colegios = new javax.swing.JComboBox<>();
        lb_curso = new javax.swing.JLabel();
        btn_GuardarAlumno = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        lb_correo = new javax.swing.JLabel();
        txt_telf = new javax.swing.JTextField();

        setBackground(new java.awt.Color(204, 255, 255));
        setPreferredSize(new java.awt.Dimension(750, 550));

        lb_titulo.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lb_titulo.setText("Añadir Alumno");

        lb_nombre.setText("Nombre:");

        lb_apell.setText("Apellidos:");

        lb_tutor.setText("Nombre padre/madre/tutor:");

        lb_telf.setText("Teléfono de contacto:");

        lb_anyo.setText("Año de nacimiento:");

        lb_cole.setText("Colegio:");

        comboBox_colegios.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione un colegio", "cole 1" }));

        lb_curso.setText("Curso:");

        btn_GuardarAlumno.setBackground(new java.awt.Color(0, 102, 204));
        btn_GuardarAlumno.setForeground(new java.awt.Color(255, 255, 255));
        btn_GuardarAlumno.setText("Guardar Alumno");
        btn_GuardarAlumno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_GuardarAlumnoActionPerformed(evt);
            }
        });

        jLabel7.setText("foto alumno");

        lb_correo.setText("Correo electrónico:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(122, 122, 122)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lb_anyo, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28)
                                .addComponent(txt_anyo, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lb_apell, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lb_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lb_curso, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_apell, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_curso, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(98, 98, 98)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lb_cole)
                                    .addComponent(lb_tutor)
                                    .addComponent(lb_telf)
                                    .addComponent(lb_correo))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_tutor, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(comboBox_colegios, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_telf, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_correo, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(240, 240, 240)
                        .addComponent(btn_GuardarAlumno, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(155, 155, 155)
                        .addComponent(lb_titulo)))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(lb_titulo)
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lb_nombre))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_apell, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lb_apell))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_curso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lb_curso))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_anyo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lb_anyo))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboBox_colegios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lb_cole))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_tutor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lb_tutor))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_telf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lb_telf))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_correo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lb_correo))
                .addGap(33, 33, 33)
                .addComponent(btn_GuardarAlumno)
                .addContainerGap(25, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btn_GuardarAlumnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_GuardarAlumnoActionPerformed
        // Verificar si los campos están vacíos
    if (txt_nombre.getText().trim().isEmpty() || txt_apell.getText().trim().isEmpty() 
            || txt_curso.getText().trim().isEmpty() || 
        txt_anyo.getText().trim().isEmpty() || txt_tutor.getText().trim().isEmpty() 
            || txt_telf.getText().trim().isEmpty() || 
        txt_correo.getText().trim().isEmpty() ) {

        // Mostrar mensaje de advertencia
        JOptionPane.showMessageDialog(null, "Por favor, rellena todos los campos.",
                "Campos Vacíos", JOptionPane.WARNING_MESSAGE);

    }else if(comboBox_colegios.getSelectedIndex()==0){
        JOptionPane.showMessageDialog(null, "Por favor, selecciona un colegio.", 
                "Colegio sin seleccionar", JOptionPane.WARNING_MESSAGE);
    } else {
        // Validar y asignar los valores
        nombreAlumno = txt_nombre.getText().trim();
        apellidos = txt_apell.getText().trim();
        curso = txt_curso.getText().trim();

        // Validar que el año sea un número y tenga 4 dígitos
        String anyoTexto = txt_anyo.getText().trim(); 
        if ( !anyoTexto.matches("\\d{4}")) { 
            JOptionPane.showMessageDialog(null, "El año de nacimiento debe ser un número de 4 dígitos.", 
                    "Error en el Año", JOptionPane.ERROR_MESSAGE);
            return; // Salir del método si la validación falla
        }
        anyo = Integer.parseInt(anyoTexto); // asignar cp
        // Validar que el año esté en el rango permitido (entre 2010 y 2017)
        if (anyo < 2010 || anyo > 2017) {
            JOptionPane.showMessageDialog(null, "El año es incorrecto, asegurese de introducir el año bien.", 
                    "Error en el Año", JOptionPane.ERROR_MESSAGE);
            return; // Salir del método si la validación falla
        }
        // Validar que el telefono sea un número y tenga 9 dígitos
        String telefono = txt_telf.getText().trim();
        if (!telefono.matches("\\d{9}")) {
            JOptionPane.showMessageDialog(null, "El teléfono debe ser un número de 9 dígitos", 
                    "Error en el Teléfono", JOptionPane.ERROR_MESSAGE);
            return; // Salir del método si la validación falla
        } 
        telf = Integer.parseInt(txt_telf.getText().trim()); //asignat telf
        
        // Mensaje de éxito
        JOptionPane.showMessageDialog(null, "Todos los datos han sido ingresados correctamente.", 
                "Éxito", JOptionPane.INFORMATION_MESSAGE);
    }



        //guardar en la bbdd
        
        
        
        
    }//GEN-LAST:event_btn_GuardarAlumnoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_GuardarAlumno;
    private javax.swing.JComboBox<String> comboBox_colegios;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel lb_anyo;
    private javax.swing.JLabel lb_apell;
    private javax.swing.JLabel lb_cole;
    private javax.swing.JLabel lb_correo;
    private javax.swing.JLabel lb_curso;
    private javax.swing.JLabel lb_nombre;
    private javax.swing.JLabel lb_telf;
    private javax.swing.JLabel lb_titulo;
    private javax.swing.JLabel lb_tutor;
    private javax.swing.JTextField txt_anyo;
    private javax.swing.JTextField txt_apell;
    private javax.swing.JTextField txt_correo;
    private javax.swing.JTextField txt_curso;
    private javax.swing.JTextField txt_nombre;
    private javax.swing.JTextField txt_telf;
    private javax.swing.JTextField txt_tutor;
    // End of variables declaration//GEN-END:variables
}
