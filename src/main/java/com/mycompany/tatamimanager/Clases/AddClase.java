/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.tatamimanager.Clases;

import com.mycompany.tatamimanager.Componentes_ComboBox.metodos_ComboBox;
import com.mycompany.tatamimanager.Componentes_ComboBox.ComboBox_Item;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
/**
 *
 * @author diana
 */
public class AddClase extends javax.swing.JPanel {
    
    private JComboBox<ComboBox_Item> comboBox_colegios;
    private JComboBox<ComboBox_Item> comboBox_profesores;
    private String nombre;
    private String horaInicio;
    private String horafin; 
    private String dias; 

    /**
     * Creates new form AddClase
     */
    public AddClase() {
        initComponents();
        
        //ComboBox colegios
        comboBox_colegios = new JComboBox<>();
        this.add(comboBox_colegios);
        comboBox_colegios.setBounds(404, 106, 210, 25);
        metodos_ComboBox.mostrarColegios(comboBox_colegios, "id_colegio", "colegio", "colegios");
        
        //ComboBox profesores
        comboBox_profesores = new JComboBox<>();
        this.add(comboBox_profesores);
        comboBox_profesores.setBounds(404, 175, 210, 25);
        metodos_ComboBox.mostrarColegios(comboBox_profesores, "id_profesor", "profesor", "profesores");
    }

     
    public boolean validarDatos(){
                    // Verificar si los campos están vacíos
        if (txt_nombre.getText().trim().isEmpty() || txt_horaInicio.getText().trim().isEmpty() 
                || txt_horaFin.getText().trim().isEmpty()) {

            // Mostrar mensaje de advertencia
            JOptionPane.showMessageDialog(null, "Por favor, rellena todos los campos.", "Campos Vacíos", JOptionPane.WARNING_MESSAGE);
            return false;
        }else if(comboBox_colegios.getSelectedIndex()==0){
            JOptionPane.showMessageDialog(null, "Por favor, selecciona un colegio.", "Colegio sin seleccionar", JOptionPane.WARNING_MESSAGE);
            return false;        
        }else if(comboBox_profesores.getSelectedIndex()==0){
            JOptionPane.showMessageDialog(null, "Por favor, selecciona un profesor.", "Profesor sin seleccionar", JOptionPane.WARNING_MESSAGE);
            return false;        
        }else {
            // Validar y asignar los valores
            nombre = txt_nombre.getText().trim();
            horaInicio = txt_horaInicio.getText().trim();
            horafin = txt_horaFin.getText().trim();
             /*
            // Validar que el año sea un número y tenga 4 dígitos
            String anyoTexto = txt_anyo.getText().trim(); 
            if ( !anyoTexto.matches("\\d{4}")) { 
                JOptionPane.showMessageDialog(null, "El año de nacimiento debe ser un número de 4 dígitos.", "Error en el Año", JOptionPane.ERROR_MESSAGE);
                return false; // Salir del método si la validación falla
            }
            anyo = Integer.parseInt(anyoTexto); // asignar cp
            // Validar que el año esté en el rango permitido (entre 2010 y 2017)
            if (anyo < 2010 || anyo > 2021) {
                JOptionPane.showMessageDialog(null, "El año es incorrecto, asegurese de introducir el año bien.", "Error en el Año", JOptionPane.ERROR_MESSAGE);
                return false; // Salir del método si la validación falla
            }
            // Validar que el telefono sea un número y tenga 9 dígitos
            String telefono = txt_telf.getText().trim();
            if (!telefono.matches("\\d{9}")) {
                JOptionPane.showMessageDialog(null, "El teléfono debe ser un número de 9 dígitos", "Error en el Teléfono", JOptionPane.ERROR_MESSAGE);
                return false; // Salir del método si la validación falla
            } 
            telf = Integer.parseInt(txt_telf.getText().trim()); //asignat telf

            correo = txt_correo.getText().trim();        //-------------------------  COMPROBAR CORREO
            if(!isUpdate){
                // Mensaje de éxito
                JOptionPane.showMessageDialog(null, "Todos los datos han sido ingresados correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            }*/
        }
        return true;
    }
    
    /*
    public void actualizarDatos(int idAlumno, String nombreAlumno, String apellAlumno, String cursoAlumno, int anyoAlumno, String tutorAlumno,
                                int telefonoAlumno, String correoAlumno, String cinturonAlumno, int coleAlumno){
        isUpdate = true;
        id = idAlumno;
        txt_nombre.setText(nombreAlumno);
        txt_apell.setText(apellAlumno);
        txt_curso.setText(cursoAlumno);
        txt_anyo.setText(Integer.toString(anyoAlumno));
        txt_tutor.setText(tutorAlumno);
        txt_telf.setText(Integer.toString(telefonoAlumno));
        txt_correo.setText(correoAlumno);
        txt_cinturon.setText(cinturonAlumno);
        
         // Buscar el ComboBox_Item por ID y seleccionarlo en el JComboBox
        for (int i = 0; i < comboBox_colegios.getItemCount(); i++) {
            ComboBox_Item item = (ComboBox_Item) comboBox_colegios.getItemAt(i);
            if (item.getId() == coleAlumno) {
                comboBox_colegios.setSelectedItem(item);
                break;
            }
        }
        
        lb_titulo.setText("Modificar Alumno");
        btn_GuardarAlumno.setText("Modificar Alumno");
    }
    */
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        lb_titulo = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        btn_GuardarClase = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txt_horaFin = new javax.swing.JTextField();
        lb_profesor = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txt_nombre = new javax.swing.JTextField();
        txt_horaInicio = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        panel_Semana = new javax.swing.JPanel();
        cb_Lunes = new javax.swing.JCheckBox();
        cb_Martes = new javax.swing.JCheckBox();
        cb_Miercoles = new javax.swing.JCheckBox();
        cb_Jueves = new javax.swing.JCheckBox();
        cb_Viernes = new javax.swing.JCheckBox();

        setBackground(new java.awt.Color(204, 255, 255));

        lb_titulo.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lb_titulo.setText("Añadir nueva clase ");

        jLabel1.setText("Colegio:");

        btn_GuardarClase.setBackground(new java.awt.Color(0, 102, 204));
        btn_GuardarClase.setForeground(new java.awt.Color(255, 255, 255));
        btn_GuardarClase.setText("Guardar Clase");
        btn_GuardarClase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_GuardarClaseActionPerformed(evt);
            }
        });

        jLabel2.setText("Nombre de la clase:");

        lb_profesor.setText("Profesor:");

        jLabel3.setText("Hora de inicio:");

        jLabel4.setText("Hora de fin:");

        panel_Semana.setBackground(new java.awt.Color(204, 255, 255));
        panel_Semana.setBorder(javax.swing.BorderFactory.createTitledBorder("Días de la semana que tiene lugar la clase:"));

        buttonGroup1.add(cb_Lunes);
        cb_Lunes.setText("Lunes");

        cb_Martes.setText("Martes");

        cb_Miercoles.setText("Miércoles");

        cb_Jueves.setText("Jueves");

        cb_Viernes.setText("Viernes");

        javax.swing.GroupLayout panel_SemanaLayout = new javax.swing.GroupLayout(panel_Semana);
        panel_Semana.setLayout(panel_SemanaLayout);
        panel_SemanaLayout.setHorizontalGroup(
            panel_SemanaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_SemanaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cb_Lunes)
                .addGap(32, 32, 32)
                .addComponent(cb_Martes)
                .addGap(34, 34, 34)
                .addComponent(cb_Miercoles)
                .addGap(31, 31, 31)
                .addComponent(cb_Jueves)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addComponent(cb_Viernes)
                .addGap(24, 24, 24))
        );
        panel_SemanaLayout.setVerticalGroup(
            panel_SemanaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_SemanaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_SemanaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cb_Lunes)
                    .addComponent(cb_Martes)
                    .addComponent(cb_Miercoles)
                    .addComponent(cb_Jueves)
                    .addComponent(cb_Viernes))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(227, 227, 227)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(41, 41, 41)
                                .addComponent(lb_titulo))
                            .addComponent(jLabel2)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lb_profesor)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel1))
                                .addGap(98, 98, 98)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txt_nombre)
                                    .addComponent(txt_horaInicio)
                                    .addComponent(txt_horaFin, javax.swing.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(328, 328, 328)
                        .addComponent(btn_GuardarClase, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(184, 184, 184)
                        .addComponent(panel_Semana, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(239, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addComponent(lb_titulo)
                .addGap(31, 31, 31)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txt_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lb_profesor)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_horaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_horaFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(29, 29, 29)
                .addComponent(panel_Semana, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(btn_GuardarClase)
                .addContainerGap(43, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btn_GuardarClaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_GuardarClaseActionPerformed
        /*if (!validarDatos()) {
            return; // Si alguna validación falla, detener la ejecución
        }

        int idColegio = metodos_ComboBox.obtenerIdColegioSeleccionado(comboBox_colegios);

        if(!isUpdate){
            //si estoy añadiendo colegio
            DatabaseControlAlumnos.guardarAlumno(nombreAlumno, apellidos, curso, anyo, nombreTutor, telf, correo, cinturon, idColegio);

        }else{
            //si estoy modificando
            DatabaseControlAlumnos.editarAlumno(nombreAlumno, apellidos, curso, anyo, nombreTutor, telf, correo, cinturon, idColegio, id);
        }

        isUpdate = false; //volvemoa a establecer isUpdate como false

        //cambiar panel a listaColegios
        JFrame frameInicio = (JFrame) SwingUtilities.getWindowAncestor(this);
        if (frameInicio instanceof Inicio) {
            ListaAlumnos nuevoPanel = new ListaAlumnos(); // Nueva instancia
            nuevoPanel.iniTabla();
            ((Inicio) frameInicio).cambiarPanel(nuevoPanel);
            //((Inicio) frameInicio).cambiarPanel(new ListaAlumnos());
        }
*/
    }//GEN-LAST:event_btn_GuardarClaseActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_GuardarClase;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JCheckBox cb_Jueves;
    private javax.swing.JCheckBox cb_Lunes;
    private javax.swing.JCheckBox cb_Martes;
    private javax.swing.JCheckBox cb_Miercoles;
    private javax.swing.JCheckBox cb_Viernes;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel lb_profesor;
    private javax.swing.JLabel lb_titulo;
    private javax.swing.JPanel panel_Semana;
    private javax.swing.JTextField txt_horaFin;
    private javax.swing.JTextField txt_horaInicio;
    private javax.swing.JTextField txt_nombre;
    // End of variables declaration//GEN-END:variables
}
