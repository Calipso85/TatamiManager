

import com.mycompany.tatamimanager.Alumnos.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TestAddAlumno {
    
   private void new_alumno(){
        panel.getTxt_nombre().setText("Juan");
        panel.getTxt_apell().setText("Pérez");
        panel.getTxt_curso().setText("1º ESO");
        panel.getTxt_anyo().setText("2010");
        panel.getTxt_tutor().setText("Carlos Pérez");
        panel.getTxt_telf().setText("612345678");
        panel.getTxt_correo().setText("juan.perez@example.com");
        panel.getComboBox_colegios().setSelectedIndex(1); // tiene que haber al menos un colegio creado sino falla
   }
    private AddAlumnos panel;

    @BeforeEach
    public void setUp() {
        // Inicializa el panel 
        panel = new AddAlumnos();
    }

    @Test
    public void test_addAlumnos() {
        // Verificar que el constructor inicializa 
        assertNotNull(panel); // Se puede borrar

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
        assertEquals("", panel.getTxt_nombre().getText()); 
        assertEquals("", panel.getTxt_apell().getText());
        assertEquals("", panel.getTxt_tutor().getText());
        assertEquals("", panel.getTxt_correo().getText());
        assertEquals("", panel.getTxt_anyo().getText());
        assertEquals("", panel.getTxt_curso().getText());
        assertEquals("", panel.getTxt_telf().getText());
        assertEquals("Selecciona un colegio", panel.getComboBox_colegios().getSelectedItem());

    }
    
    @Test
    public void test_btn_GuardarAlumnoActionPerformed(){
        new_alumno();
        
        // Simula hacer clic en el botón de guardar
        panel.getBtn_GuardarAlumno().doClick();

        assertEquals("Juan", panel.getTxt_nombre().getText());
        assertEquals("Pérez", panel.getTxt_apell().getText());
        assertEquals("1º ESO", panel.getTxt_curso().getText());
        assertEquals("2010", panel.getTxt_anyo().getText());  
        assertEquals("Carlos Pérez", panel.getTxt_tutor().getText());
        assertEquals("612345678", panel.getTxt_telf().getText());
        assertEquals("juan.perez@example.com", panel.getTxt_correo().getText());
    }
     @Test
    public void test_invalidAnyo() {
        // Simula el caso en que el año ingresado no sea válido, debe comprobar que salte mensaje de error
        new_alumno();
        panel.getTxt_anyo().setText("2025"); 
        panel.getBtn_GuardarAlumno().doClick(); 

    }
    @Test
    public void test_invalidPhone() {
        // Simula ingresar un teléfono no válido, debe comprobar que salte mensaje de error
        new_alumno();
        panel.getTxt_telf().setText("123"); 
        panel.getBtn_GuardarAlumno().doClick();
    }
    @Test
    public void test_noCollegeSelected() {
        new_alumno();
        // Simula no seleccionar un colegio, debe comprobar que salte mensaje de error
        panel.getComboBox_colegios().setSelectedIndex(0); 
        panel.getBtn_GuardarAlumno().doClick();

    }
    @Test
    public void test_emptyFieldsValidation() {
        // Simula hacer clic en el botón de guardar con campos vacios, debe comprobar que salte mensaje de error
        panel.getBtn_GuardarAlumno().doClick();


        assertTrue(panel.getTxt_nombre().getText().isEmpty());
        assertTrue(panel.getTxt_apell().getText().isEmpty());
        assertTrue(panel.getTxt_tutor().getText().isEmpty());
        assertTrue(panel.getTxt_correo().getText().isEmpty());
        assertTrue(panel.getTxt_anyo().getText().isEmpty());
        assertTrue(panel.getTxt_curso().getText().isEmpty());
        assertTrue(panel.getTxt_telf().getText().isEmpty());
        assertEquals("Selecciona un colegio", panel.getComboBox_colegios().getSelectedItem());
    }

}
