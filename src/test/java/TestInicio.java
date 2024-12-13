import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.swing.JMenuItem;
import javax.swing.JPanel;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mycompany.tatamimanager.Inicio;
import com.mycompany.tatamimanager.Alumnos.AddAlumnos;
import com.mycompany.tatamimanager.Alumnos.ListaAlumnos;
import com.mycompany.tatamimanager.Colegios.*;

public class TestInicio { // Este no sabia que poner
    private Inicio inicio;  

    @BeforeEach
    public void setUp() {
        inicio = new Inicio();
        inicio.setSize(760, 590);  
    }

    @Test
    public void testCambiarPanel() {
        // Crear un nuevo panel
        JPanel nuevoPanel = new JPanel();
        inicio.cambiarPanel(nuevoPanel);
        
        assertEquals(nuevoPanel, inicio.getContentPane().getComponent(0));
    }

    @Test
    public void testAccionVerAlumnos() {
        JMenuItem verAlumnos = inicio.getjMenuItem_VerAlumnos();
        verAlumnos.doClick();
        
        assertTrue(inicio.getContentPane().getComponent(0) instanceof ListaAlumnos);
    }

    @Test
    public void testAccionAddAlumno() {
        JMenuItem addAlumno = inicio.getjMenuItem_AddAlumno();
        addAlumno.doClick();
        
        assertTrue(inicio.getContentPane().getComponent(0) instanceof AddAlumnos);
    }

    @Test
    public void testAccionVerColegios() {
        JMenuItem verColegios = inicio.getjMenuItem_VerColes();
        verColegios.doClick();
        
        assertTrue(inicio.getContentPane().getComponent(0) instanceof ListaColegios);
    }

    @Test
    public void testAccionAddColegio() {
        JMenuItem addColegio = inicio.getjMenuItem_AddCole();
        addColegio.doClick();
        
        assertTrue(inicio.getContentPane().getComponent(0) instanceof AddColegios);
    }


}
