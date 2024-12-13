import com.mycompany.tatamimanager.BBDD.DatabaseManager;
import com.mycompany.tatamimanager.Colegios.*;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import java.sql.*;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class TestListaColegios {
  private ListaColegios listaColegios;

    @BeforeEach
    public void setUp() {
        listaColegios = new ListaColegios(); 
    }

    @Test
    public void testEtiquetaListadoColegios() {
        // Verifica que la etiqueta está inicializada y contiene el texto correcto
        JLabel label = listaColegios.getLb_titulo();
        assertNotNull(label);
        assertEquals("Listado de Colegios", label.getText());
    }

    @Test
    public void testModeloTabla() {
        // Verifica que el modelo de la tabla está correctamente inicializado
        JTable table = listaColegios.getTablaColegios();
        assertNotNull(table.getModel());
        assertTrue(table.getModel() instanceof DefaultTableModel);

        // Verifica los nombres de las columnas
        assertEquals("Nombre", table.getColumnName(0));
        assertEquals("Dirección", table.getColumnName(1));
        assertEquals("Teléfono", table.getColumnName(2));
        assertEquals("Barrio", table.getColumnName(3));
        assertEquals("Código Postal", table.getColumnName(4));
    }

    @Test
    public void testComponentesVisibles() { // ¿?borrar
        // Verifica que la etiqueta es visible
        JLabel label = listaColegios.getLb_titulo();
        assertTrue(label.isVisible());

        // Verifica que la tabla es visible
        JTable table = listaColegios.getTablaColegios();
        assertTrue(table.isVisible());
    }

    @Test
    public void testCargarColegios() throws Exception {
        // Crear un colegio de prueba para insertar
        try (Connection conn = DatabaseManager.getConnection()) {
            String queryInsert = "INSERT INTO colegios (nombre, direccion, telefono, barrio, cod_postal) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement st = conn.prepareStatement(queryInsert);
            st.setString(1, "Colegio Test");
            st.setString(2, "Calle Falsa 123");
            st.setInt(3, 987654321);
            st.setString(4, "Barrio X");
            st.setInt(5, 10001);
            st.executeUpdate();
        }

        // Cargar la lista de colegios
        listaColegios = new ListaColegios();
        
        DefaultTableModel modelo = listaColegios.getModelo();
        int pos = modelo.getRowCount()-1;
        //assertEquals(1, modelo.getRowCount()); // Da error porquee esta rellena la tabla con mas datos
        assertEquals("Colegio Test", modelo.getValueAt(pos, 0));
        assertEquals("Calle Falsa 123", modelo.getValueAt(pos, 1));
        assertEquals(987654321, modelo.getValueAt(pos, 2));
        assertEquals("Barrio X", modelo.getValueAt(pos, 3));
        assertEquals(10001, modelo.getValueAt(pos, 4));
        borrarDatosDePrueba();
    }
    
    private void borrarDatosDePrueba() throws Exception {
        try (Connection conn = DatabaseManager.getConnection()) {
            String deleteSQL = "DELETE FROM colegios WHERE id_colegio IN (SELECT id_colegio FROM colegios WHERE direccion = 'Calle Falsa _123')";
            try (PreparedStatement st = conn.prepareStatement(deleteSQL)) {
                st.executeUpdate(); // Elimina los datos de prueba
            }
            // Forzado a 3 porque hay 3 datos cambiar valor o buscar mejor solucion
            String resetSequenceSQL = "UPDATE sqlite_sequence SET seq = 3 WHERE name = 'colegios'";
            try (PreparedStatement st = conn.prepareStatement(resetSequenceSQL)) {
                st.executeUpdate(); 
            }
        }
    }
}
