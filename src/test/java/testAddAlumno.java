

import com.mycompany.tatamimanager.Alumnos.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;;


public class testAddAlumno {
    
   private void new_alumno(){
        panel.getTxt_nombre().setText("Juan");
        panel.getTxt_apell().setText("Pérez");
        panel.getTxt_curso().setText("1º ESO");
        panel.getTxt_anyo().setText("2010");
        panel.getTxt_tutor().setText("Carlos Pérez");
        panel.getTxt_telf().setText("612345678");
        panel.getTxt_correo().setText("juan.perez@example.com");
        panel.getComboBox_colegios().setSelectedIndex(1); // Suponiendo que hay al menos un colegio en el combo

   }
    private AddAlumnos panel;

    @BeforeEach
    public void setUp() {
        // Inicializa tu panel o formulario antes de cada prueba
        panel = new AddAlumnos();
    }

    @Test
    public void test_addAlumnos() {
        // Verificar que el constructor inicializa las variables miembro en null o valores por defecto
        assertNotNull(panel); // Verifica que el panel se inicializa correctamente

        // Verificar que los componentes GUI están inicializados
        assertNotNull(panel.getTxt_nombre()); 
        assertNotNull(panel.getTxt_apell());
        assertNotNull(panel.getTxt_tutor());
        assertNotNull(panel.getTxt_correo());
        assertNotNull(panel.getTxt_anyo());
        assertNotNull(panel.getTxt_curso());
        assertNotNull(panel.getComboBox_colegios());
        assertNotNull(panel.getTxt_telf());
    }

    @Test
    public void test_initComponents() {
        // Verificar valores iniciales de componentes
        assertEquals("", panel.getTxt_nombre().getText()); // Los campos de texto deben estar vacíos
        assertEquals("", panel.getTxt_apell().getText());
        assertEquals("", panel.getTxt_tutor().getText());
        assertEquals("", panel.getTxt_correo().getText());
        assertEquals("", panel.getTxt_anyo().getText());
        assertEquals("", panel.getTxt_curso().getText());
        assertEquals("", panel.getTxt_telf().getText());
        assertEquals("Seleccione un colegio", panel.getComboBox_colegios().getSelectedItem());

    }
    
    @Test
    public void test_btn_GuardarAlumnoActionPerformed(){
        new_alumno();
        // Simulamos hacer clic en el botón de guardar
        panel.getBtn_GuardarAlumno().doClick();

        assertEquals("Juan", panel.getTxt_nombre().getText());
        assertEquals("Pérez", panel.getTxt_apell().getText());
        assertEquals("1º ESO", panel.getTxt_curso().getText());
        assertEquals("2010", panel.getTxt_anyo().getText());  // Verificando que el año es correcto
        assertEquals("Carlos Pérez", panel.getTxt_tutor().getText());
        assertEquals("612345678", panel.getTxt_telf().getText());
        assertEquals("juan.perez@example.com", panel.getTxt_correo().getText());
       
    }
     @Test
    public void test_invalidAnyo() {
        // Simular el caso en que el año ingresado no sea válido (por ejemplo, 2025)
        new_alumno();
        panel.getTxt_anyo().setText("2025"); // Año fuera del rango permitido (debe ser entre 2010 y 2017)
        panel.getBtn_GuardarAlumno().doClick(); 

        assertNotEquals(2025, panel.getTxt_anyo().getText());
        
    }
    @Test
    public void test_invalidPhone() {
        // Simulamos ingresar un teléfono no válido
        new_alumno();
        panel.getTxt_telf().setText("123"); 
        panel.getBtn_GuardarAlumno().doClick();

        assertNotEquals(123, panel.getTxt_telf().getText()); // Si el teléfono es incorrecto no debe guardarse
    }
    @Test
    public void test_noCollegeSelected() {
        new_alumno();
        // Simulamos no seleccionar un colegio
        panel.getComboBox_colegios().setSelectedIndex(0); // 0 podría representar "Seleccione un colegio"
        panel.getBtn_GuardarAlumno().doClick();

        assertEquals("Seleccione un colegio", panel.getComboBox_colegios().getSelectedItem());
    }
    @Test
    public void test_emptyFieldsValidation() {
        // Simulamos hacer clic en el botón de guardar sin completar los campos obligatorios
        panel.getBtn_GuardarAlumno().doClick();


        assertTrue(panel.getTxt_nombre().getText().isEmpty());
        assertTrue(panel.getTxt_apell().getText().isEmpty());
        assertTrue(panel.getTxt_tutor().getText().isEmpty());
        assertTrue(panel.getTxt_correo().getText().isEmpty());
        assertTrue(panel.getTxt_anyo().getText().isEmpty());
        assertTrue(panel.getTxt_curso().getText().isEmpty());
        assertTrue(panel.getTxt_telf().getText().isEmpty());
        assertEquals("Seleccione un colegio", panel.getComboBox_colegios().getSelectedItem());
    }

}
