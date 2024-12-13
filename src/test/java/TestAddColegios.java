import com.mycompany.tatamimanager.BBDD.DatabaseManager;
import com.mycompany.tatamimanager.Colegios.*;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestAddColegios {

    private AddColegios addColegios;

    @BeforeEach
    public void setUp() {
        // Inicializa AddColegios 
        addColegios = new AddColegios();
    }

    @Test
    public void testCamposVacios() {
        // Verifica que los campos estén vacíos inicialmente
        assertEquals("", addColegios.getTxt_nombre().getText());
        assertEquals("", addColegios.getTxt_direccion().getText());
        assertEquals("", addColegios.getTxt_telf().getText());
        assertEquals("", addColegios.getTxt_barrio().getText());
        assertEquals("", addColegios.getTxt_cp().getText());
    }

    @Test
    public void testTelefonoInvalido() {

        addColegios.getTxt_nombre().setText("Colegio Test");
        addColegios.getTxt_direccion().setText("Calle Test");
        addColegios.getTxt_barrio().setText("Barrio Test");
        addColegios.getTxt_cp().setText("28001");
        addColegios.getTxt_telf().setText("12345"); 

        // Simula el click en el botón de guardar, debes comprobar que salta msj error
        addColegios.getBtn_GuardarColegio().doClick();
    }

    @Test
    public void testCodigoPostalInvalido() {
        addColegios.getTxt_nombre().setText("Colegio Test");
        addColegios.getTxt_direccion().setText("Calle Test");
        addColegios.getTxt_barrio().setText("Barrio Test");
        addColegios.getTxt_telf().setText("123456789");
        addColegios.getTxt_cp().setText("123"); 

        // Simula el click en el botón de guardar, debes comprobar que salta msj error
        addColegios.getBtn_GuardarColegio().doClick();
    }

    @Test
    public void testGuardarDatosEnBaseDeDatos() throws Exception {
        // datos válidos
        addColegios.getTxt_nombre().setText("Colegio Test");
        addColegios.getTxt_direccion().setText("Calle Falsa _123");
        addColegios.getTxt_telf().setText("123456789");
        addColegios.getTxt_barrio().setText("Barrio X");
        addColegios.getTxt_cp().setText("28001");

        addColegios.getBtn_GuardarColegio().doClick();
        
        // Verificar que los datos se guardaron correctamente en la base de datos
        try (Connection conn = DatabaseManager.getConnection()) {
            String query = "SELECT * FROM colegios WHERE nombre = ?";
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, "Colegio Test");
            ResultSet rs = st.executeQuery();
            
            assertTrue(rs.next());
            assertEquals("Colegio Test", rs.getString("nombre"));
            assertEquals("Calle Falsa _123", rs.getString("direccion"));
            assertEquals(123456789, rs.getInt("telefono"));
            assertEquals("Barrio X", rs.getString("barrio"));
            assertEquals(28001, rs.getInt("cod_postal"));
        }
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
