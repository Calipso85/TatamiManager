import com.mycompany.tatamimanager.Alumnos.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestListaAlumnos {
    private ListaAlumnos listaAlumnos;

    @BeforeEach
    public void setUp() {
        listaAlumnos = new ListaAlumnos();
    }

    @Test
    public void testEtiquetaListadoAlumnos() {
        JLabel label = listaAlumnos.getLb_titulo();
        assertNotNull(label);
        assertEquals("Listado de Alumnos", label.getText());
     }

     @Test
     public void testModeloTabla() {
        JTable table = listaAlumnos.getTablaAlumnos();
        assertNotNull(table.getModel());
        assertTrue(table.getModel() instanceof DefaultTableModel);

        // Verificar nombres de columnas
        assertEquals("Nombre", table.getColumnName(0));
        assertEquals("Apellidos", table.getColumnName(1));
        assertEquals("Curso", table.getColumnName(2));
        assertEquals("Cinturón", table.getColumnName(3));
        assertEquals("Nombre tutor", table.getColumnName(4));
        assertEquals("Teléfono", table.getColumnName(5));
        assertEquals("Correo electrónico", table.getColumnName(6));
     }
     @Test
     public void testComponentesVisibles() { 
        // Verificar que la etiqueta es visible
        JLabel label = listaAlumnos.getLb_titulo();
        assertTrue(label.isVisible());
    
        // Verificar que la tabla es visible
        JTable table = listaAlumnos.getTablaAlumnos();
        assertTrue(table.isVisible());
    }
}
