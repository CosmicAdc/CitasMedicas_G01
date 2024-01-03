package com.IngSoftGrupo1.CitasMedicas.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.IngSoftGrupo1.CitasMedicas.Modelos.Receta;

import static org.junit.jupiter.api.Assertions.*;

class RecetaTest {

    private Receta receta;

    @BeforeEach
    void setUp() {
        // Aquí se inicializa tu objeto Receta antes de cada test
        receta = new Receta(1L, "Tomar cada 8 horas");
    }

    @Test
    void constructorTest() {
        // Test para el constructor con parámetros
        assertNotNull(receta);
        assertEquals(1L, receta.getRe_id());
        assertEquals("Tomar cada 8 horas", receta.getRe_indicaciones());
    }

    @Test
    void defaultConstructorTest() {
        // Test para el constructor por defecto
        Receta recetaDefault = new Receta();
        assertNotNull(recetaDefault);
        assertNull(recetaDefault.getRe_id()); // Asumiendo que es null por defecto
        assertNull(recetaDefault.getRe_indicaciones()); // Asumiendo que es null por defecto
    }

    @Test
    void getRe_idTest() {
        // Test para el getter de re_id
        assertEquals(1L, receta.getRe_id());
    }

    @Test
    void setRe_idTest() {
        // Test para el setter de re_id
        receta.setRe_id(2L);
        assertEquals(2L, receta.getRe_id());
    }

    @Test
    void getRe_indicacionesTest() {
        // Test para el getter de re_indicaciones
        assertEquals("Tomar cada 8 horas", receta.getRe_indicaciones());
    }

    @Test
    void setRe_indicacionesTest() {
        // Test para el setter de re_indicaciones
        receta.setRe_indicaciones("Tomar cada 12 horas");
        assertEquals("Tomar cada 12 horas", receta.getRe_indicaciones());
    }

    @Test
    void nullIndicacionesTest() {
        // Test para verificar el comportamiento cuando se introducen indicaciones nulas
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Receta receta = new Receta();
            receta.setRe_indicaciones(null);
        });
        String expectedMessage = "Las indicaciones no pueden ser nulas";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    // Aquí puedes agregar cualquier otro test que desees realizar.
}
