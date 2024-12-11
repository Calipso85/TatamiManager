/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.tatamimanager.Clases;

import com.mycompany.tatamimanager.Componentes_ComboBox.metodos_ComboBox;
import com.mycompany.tatamimanager.Componentes_ComboBox.ComboBox_Item;
import com.mycompany.tatamimanager.BBDD.DatabaseControlClases;
import java.awt.Component;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
/**
 *
 * @author diana
 */
public class AddClase extends javax.swing.JPanel {
    
    private JComboBox<ComboBox_Item> comboBox_colegios;
    private JComboBox<ComboBox_Item> comboBox_profesores;
    private int idClase;
    private String nombre;
    private String horaInicio;
    private String horaFin; 
    private String horario; 
    private String diasSeleccionados; 
    public boolean isUpdate = false; 

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
        }else if (!algunDiaMarcado(panel_Semana)) {
            JOptionPane.showMessageDialog(null, "Por favor, marque la casilla de al menos un día.", "Día sin seleccionar", JOptionPane.WARNING_MESSAGE);
            return false;        
        }else{
            // Validar y asignar los valores
            nombre = txt_nombre.getText().trim();
            horaInicio = txt_horaInicio.getText().trim();
            horaFin = txt_horaFin.getText().trim();
            horario = horaInicio + " - " + horaFin; //horas unidas
            diasSeleccionados = obtenerDiasSeleccionados(panel_Semana);
            
            if(!isUpdate){
                // Mensaje de éxito
                JOptionPane.showMessageDialog(null, "Todos los datos han sido ingresados correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        return true;
    }
    
    // Método para verificar si al menos un JCheckBox está marcado en panel_semana
    private boolean algunDiaMarcado(JPanel panel) {
        for (Component component : panel.getComponents()) {
            if (component instanceof JCheckBox) {
                JCheckBox checkbox = (JCheckBox) component;
                if (checkbox.isSelected()) {
                    return true; // Retorna true si al menos uno está marcado
                }
            }
        }
        return false; // Retorna false si ninguno está marcado
    }
    
    // Método para verificar si al menos un JCheckBox está marcado y construir un String dinámico con los días seleccionados
    private String obtenerDiasSeleccionados(JPanel panel) {
        StringBuilder diasSeleccionados = new StringBuilder();

        for (Component component : panel.getComponents()) {
            if (component instanceof JCheckBox) {
                JCheckBox checkbox = (JCheckBox) component;
                if (checkbox.isSelected()) {
                    // Agregar el texto del JCheckBox al StringBuilder
                    if (diasSeleccionados.length() > 0) {
                        diasSeleccionados.append(", "); // Agregar coma y espacio si no es el primero
                    }
                    diasSeleccionados.append(checkbox.getText());
                }
            }
        }

        // Retorna los días seleccionados como String
        return diasSeleccionados.toString();
    }
    /*
    public void actualizarDatos(int idClase, String nombre, String horaInicio, String horaFin, String dias, int idColegio, int idProfesor){
        isUpdate = true;
        id = idClase;
        txt_nombre.setText(nombre);
        txt_horaInicio.setText(horaInicio);
        txt_horaFin.setText(horaFin);
        txt_curso.setText(dias);
        
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
        lb_coles = new javax.swing.JLabel();
        btn_GuardarClase = new javax.swing.JButton();
        lb_nombre = new javax.swing.JLabel();
        txt_horaFin = new javax.swing.JTextField();
        lb_profesor = new javax.swing.JLabel();
        lb_hora = new javax.swing.JLabel();
        txt_nombre = new javax.swing.JTextField();
        txt_horaInicio = new javax.swing.JTextField();
        lb_hora2 = new javax.swing.JLabel();
        panel_Semana = new javax.swing.JPanel();
        cb_Lunes = new javax.swing.JCheckBox();
        cb_Martes = new javax.swing.JCheckBox();
        cb_Miercoles = new javax.swing.JCheckBox();
        cb_Jueves = new javax.swing.JCheckBox();
        cb_Viernes = new javax.swing.JCheckBox();

        setBackground(new java.awt.Color(204, 255, 255));

        lb_titulo.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lb_titulo.setText("Añadir nueva clase ");

        lb_coles.setText("Colegio:");

        btn_GuardarClase.setBackground(new java.awt.Color(0, 102, 204));
        btn_GuardarClase.setForeground(new java.awt.Color(255, 255, 255));
        btn_GuardarClase.setText("Guardar Clase");
        btn_GuardarClase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_GuardarClaseActionPerformed(evt);
            }
        });

        lb_nombre.setText("Nombre de la clase:");

        lb_profesor.setText("Profesor:");

        lb_hora.setText("Hora de inicio:");

        lb_hora2.setText("Hora de fin:");

        panel_Semana.setBackground(new java.awt.Color(204, 255, 255));
        panel_Semana.setBorder(javax.swing.BorderFactory.createTitledBorder("Días de la semana que tiene lugar la clase:"));

        buttonGroup1.add(cb_Lunes);
        cb_Lunes.setText("Lunes");

        buttonGroup1.add(cb_Martes);
        cb_Martes.setText("Martes");

        buttonGroup1.add(cb_Miercoles);
        cb_Miercoles.setText("Miércoles");

        buttonGroup1.add(cb_Jueves);
        cb_Jueves.setText("Jueves");

        buttonGroup1.add(cb_Viernes);
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
                            .addComponent(lb_nombre)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lb_profesor)
                                    .addComponent(lb_hora)
                                    .addComponent(lb_hora2)
                                    .addComponent(lb_coles))
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
                .addComponent(lb_coles)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lb_nombre)
                    .addComponent(txt_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lb_profesor)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_horaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lb_hora))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_horaFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lb_hora2))
                .addGap(29, 29, 29)
                .addComponent(panel_Semana, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(btn_GuardarClase)
                .addContainerGap(43, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btn_GuardarClaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_GuardarClaseActionPerformed
        if (!validarDatos()) {
            return; // Si alguna validación falla, detener la ejecución
        }

        int idColegio = metodos_ComboBox.obtenerIdItemSeleccionado(comboBox_colegios);
        int idProfesor = metodos_ComboBox.obtenerIdItemSeleccionado(comboBox_profesores);

        if(!isUpdate){
            //si estoy añadiendo colegio
            DatabaseControlClases.guardarClase(nombre, horario, diasSeleccionados, idClase, idProfesor);
        }else{
            //si estoy modificando
            //DatabaseControlAlumnos.editarAlumno(nombreAlumno, apellidos, curso, anyo, nombreTutor, telf, correo, cinturon, idColegio, id);
        }

        isUpdate = false; //volvemoa a establecer isUpdate como false
/*
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
    private javax.swing.JLabel lb_coles;
    private javax.swing.JLabel lb_hora;
    private javax.swing.JLabel lb_hora2;
    private javax.swing.JLabel lb_nombre;
    private javax.swing.JLabel lb_profesor;
    private javax.swing.JLabel lb_titulo;
    private javax.swing.JPanel panel_Semana;
    private javax.swing.JTextField txt_horaFin;
    private javax.swing.JTextField txt_horaInicio;
    private javax.swing.JTextField txt_nombre;
    // End of variables declaration//GEN-END:variables
}
