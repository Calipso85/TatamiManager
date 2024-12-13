/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.tatamimanager.Profesores;


import com.mycompany.tatamimanager.BBDD.DatabaseControlProfesores;
import com.mycompany.tatamimanager.Inicio;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;


/**
 *
 * @author diana
 */
public class AddProfesores extends javax.swing.JPanel {

    private String nombreProfe;
    private String apellidos;
    private String dni; 
    private int telefono; 
    private String correo;
    private int id; 
    public boolean isUpdate = false;

    /**
     * Geters new form AddProfesores
     */
    public JButton getBtn_guardarProfesor() {
        return btn_guardarProfesor;
    }

    public JTextField getTxt_apellidos() {
        return txt_apellidos;
    }

    public JTextField getTxt_correo() {
        return txt_correo;
    }

    public JTextField getTxt_dni() {
        return txt_dni;
    }

    public JTextField getTxt_nombre() {
        return txt_nombre;
    }

    public JTextField getTxt_telf() {
        return txt_telf;
    }
    
    /**
     * Creates new form AddProfesores
     */
    public AddProfesores() {
        initComponents();
    }

    public void vaciarCampos(){
        txt_nombre.setText("");
        txt_apellidos.setText("");
        txt_dni.setText("");
        txt_telf.setText("");
        txt_correo.setText("");
    }
    
    public boolean validarDatos(){
        // Verificar si los campos están vacíos
        if (txt_nombre.getText().trim().isEmpty() || txt_apellidos.getText().trim().isEmpty() || txt_telf.getText().trim().isEmpty() || 
            txt_dni.getText().trim().isEmpty() || txt_correo.getText().trim().isEmpty()) {
            // Mostrar mensaje de advertencia
            JOptionPane.showMessageDialog(null, "Por favor, rellena todos los campos.", "Campos Vacíos", JOptionPane.WARNING_MESSAGE);
            return false;
            
        } else {
            // Validar y asignar los valores
            nombreProfe = txt_nombre.getText().trim();
            apellidos = txt_apellidos.getText().trim();
              
            // Comprobación del DNI en la base de datos
            dni = txt_dni.getText().trim();
            if (!isDniCorrecto(dni)) {
                JOptionPane.showMessageDialog(null, "El DNI no es válido. Indroducelo de nuevo.", "Error en el DNI", JOptionPane.ERROR_MESSAGE);
                return false; // Detener el flujo si el DNI no es válido
            }
            if(!isUpdate){
                DatabaseControlProfesores.comprobarDni(dni); //comprobar que es unico en la bbdd
            }
            
            correo = txt_correo.getText().trim();
            if(!isUpdate){
                DatabaseControlProfesores.comprobarCorreo(correo); //comprobar que es unico en la bbdd
            }
            
            // Validar que el telefono sea un número y tenga 9 dígitos
            String telef = txt_telf.getText().trim();
            if (!telef.matches("\\d{9}")) {
                JOptionPane.showMessageDialog(null, "El teléfono debe ser un número de 9 dígitos.", "Error en el Teléfono", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            telefono = Integer.parseInt(txt_telf.getText().trim()); //asignar telf

            if(!isUpdate){
                // Mensaje de éxito
                JOptionPane.showMessageDialog(null, "Todos los datos han sido ingresados correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        return true;
    }
     
    public boolean isDniCorrecto(String dni) {
        // Verificar que el DNI tenga el formato correcto: 8 dígitos + 1 letra
        if (dni == null || !dni.matches("\\d{8}[A-Z]")) {
            return false;
        }

        // Extraer la parte numérica y la letra
        int numeros = Integer.parseInt(dni.substring(0, 8));
        char letra = dni.charAt(8);
        String letrasValidas = "TRWAGMYFPDXBNJZSQVHLCKE";
        char letraCorrecta = letrasValidas.charAt(numeros % 23);

        return letra == letraCorrecta; //true
    }

     // Método para modificar(actualizar) los datos
    public void actualizarDatos(int idProfe, String nombre, String apellidos, String dni, int telefono, String correo) {
        isUpdate = true;
        id = idProfe;
        txt_nombre.setText(nombre);
        txt_apellidos.setText(apellidos);
        txt_dni.setText(dni);
        txt_telf.setText(Integer.toString(telefono));
        txt_correo.setText(correo);
        lb_titulo.setText("Modificar Profesor");
        btn_guardarProfesor.setText("Modificar profesor");
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lb_nombre = new javax.swing.JLabel();
        lb_apellidos = new javax.swing.JLabel();
        txt_apellidos = new javax.swing.JTextField();
        lb_titulo = new javax.swing.JLabel();
        btn_guardarProfesor = new javax.swing.JButton();
        txt_nombre = new javax.swing.JTextField();
        txt_dni = new javax.swing.JTextField();
        txt_telf = new javax.swing.JTextField();
        lb_dni = new javax.swing.JLabel();
        txt_correo = new javax.swing.JTextField();
        lb_telf = new javax.swing.JLabel();
        lb_correo = new javax.swing.JLabel();

        setBackground(new java.awt.Color(204, 255, 255));

        lb_nombre.setText("Nombre");

        lb_apellidos.setText("Apellidos:");

        lb_titulo.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lb_titulo.setText("Añadir Profesor");

        btn_guardarProfesor.setBackground(new java.awt.Color(0, 102, 153));
        btn_guardarProfesor.setForeground(new java.awt.Color(255, 255, 255));
        btn_guardarProfesor.setText("Guardar Profesor");
        btn_guardarProfesor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_guardarProfesorActionPerformed(evt);
            }
        });

        lb_dni.setText("DNI / NIF:");

        lb_telf.setText("Teléfono:");

        lb_correo.setText("Correo electrónico:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(186, 186, 186)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lb_nombre)
                            .addComponent(lb_apellidos)
                            .addComponent(lb_dni)
                            .addComponent(lb_telf)
                            .addComponent(lb_correo))
                        .addGap(89, 89, 89)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txt_dni)
                            .addComponent(txt_apellidos)
                            .addComponent(txt_nombre, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                            .addComponent(txt_telf, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                            .addComponent(txt_correo, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(252, 252, 252)
                        .addComponent(btn_guardarProfesor, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(253, 253, 253)
                        .addComponent(lb_titulo)))
                .addContainerGap(204, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addComponent(lb_titulo)
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lb_nombre)
                    .addComponent(txt_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_apellidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lb_apellidos))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_dni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lb_dni))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_telf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lb_telf))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_correo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lb_correo))
                .addGap(36, 36, 36)
                .addComponent(btn_guardarProfesor)
                .addContainerGap(122, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btn_guardarProfesorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_guardarProfesorActionPerformed
        
        if (!validarDatos()) {
            return; // Si alguna validación falla, detener la ejecución
        }
        
        if(!isUpdate){ 
            //si estoy añadiendo colegio
            DatabaseControlProfesores.guardarProfesor(nombreProfe, apellidos, dni, telefono, correo);
        }else{  
            //si estoy modificando
            DatabaseControlProfesores.editarProfesor(nombreProfe, apellidos, dni, telefono, correo, id);
        }
        
        isUpdate = false; //volvemoa a establecer isUpdate como false
        
        //cambiar panel a listaColegios
        JFrame frameInicio = (JFrame) SwingUtilities.getWindowAncestor(this);
        if (frameInicio instanceof Inicio) {
            ((Inicio) frameInicio).cambiarPanel(new ListaProfesores());
        }
    }//GEN-LAST:event_btn_guardarProfesorActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_guardarProfesor;
    private javax.swing.JLabel lb_apellidos;
    private javax.swing.JLabel lb_correo;
    private javax.swing.JLabel lb_dni;
    private javax.swing.JLabel lb_nombre;
    private javax.swing.JLabel lb_telf;
    private javax.swing.JLabel lb_titulo;
    private javax.swing.JTextField txt_apellidos;
    private javax.swing.JTextField txt_correo;
    private javax.swing.JTextField txt_dni;
    private javax.swing.JTextField txt_nombre;
    private javax.swing.JTextField txt_telf;
    // End of variables declaration//GEN-END:variables
}
