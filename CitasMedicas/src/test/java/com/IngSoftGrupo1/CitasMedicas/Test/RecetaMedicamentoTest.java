package com.IngSoftGrupo1.CitasMedicas.Test;

import com.IngSoftGrupo1.CitasMedicas.Modelos.Medicamento;
import com.IngSoftGrupo1.CitasMedicas.Modelos.Receta;
import com.IngSoftGrupo1.CitasMedicas.Modelos.RecetaMedicamento;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RecetaMedicamentoTest {

    private Receta receta;
    private Medicamento medicamento;
    private RecetaMedicamento recetaMedicamento;

    @BeforeEach
    void setUp() {
        // Inicializar los objetos Receta y Medicamento
        receta = new Receta(1L, "Tomar cada 8 horas");
        medicamento = new Medicamento(1L, "Ibuprofeno");

        // Inicializar RecetaMedicamento y establecer sus relaciones
        recetaMedicamento = new RecetaMedicamento();
        recetaMedicamento.setReceta(receta);
        recetaMedicamento.setMedicamento(medicamento);
    }

    @Test
    void testGetReceta() {
        assertEquals(receta, recetaMedicamento.getReceta());
    }

    @Test
    void testSetReceta() {
        Receta nuevaReceta = new Receta(2L, "Tomar cada 12 horas");
        recetaMedicamento.setReceta(nuevaReceta);
        assertEquals(nuevaReceta, recetaMedicamento.getReceta());
    }

    @Test
    void testGetMedicamento() {
        assertEquals(medicamento, recetaMedicamento.getMedicamento());
    }

    @Test
    void testSetMedicamento() {
        Medicamento nuevoMedicamento = new Medicamento(2L, "Paracetamol");
        recetaMedicamento.setMedicamento(nuevoMedicamento);
        assertEquals(nuevoMedicamento, recetaMedicamento.getMedicamento());
    }
}
