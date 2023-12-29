package com.IngSoftGrupo1.CitasMedicas.Test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.IngSoftGrupo1.CitasMedicas.Modelos.Medicamento;
import com.IngSoftGrupo1.CitasMedicas.Repositorios.MedicamentoRepositorio;
import com.IngSoftGrupo1.CitasMedicas.Servicios.MedicamentoService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MedicamentoTest {
	
    @InjectMocks
    private MedicamentoService medicamentoService;

    @Mock
    private MedicamentoRepositorio medicamentoRepositorio;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

	
    @Test
    void testCrearMedicamento() {
    	
        Medicamento medicamentoEntrada = new Medicamento(null, "Medicamento Aspirina");
        Medicamento medicamentoGuardado = new Medicamento(1L, "Medicamento Aspirina");
        when(medicamentoRepositorio.save(medicamentoEntrada)).thenReturn(medicamentoGuardado);

        Medicamento medicamentoCreado = medicamentoService.crear(medicamentoEntrada);

        assertNotNull(medicamentoCreado.getMedica_id());
        assertEquals("Medicamento Aspirina", medicamentoCreado.getMedica_nombre());
    }
   

    @Test
    void testEncontrarPorId() {
        Long idBuscado = 1L;
        Medicamento medicamentoEncontrado = new Medicamento(idBuscado, "MedicamentoEncontrado");
        when(medicamentoRepositorio.findById(idBuscado)).thenReturn(Optional.of(medicamentoEncontrado));

        Optional<Medicamento> medicamentoOptional = medicamentoService.encontrarPorId(idBuscado);

        assertTrue(medicamentoOptional.isPresent());
        assertEquals("MedicamentoEncontrado", medicamentoOptional.get().getMedica_nombre());
    }

    @Test
    void testListarTodos() {
        List<Medicamento> medicamentos = Arrays.asList(
                new Medicamento(1L, "Medicamento1"),
                new Medicamento(2L, "Medicamento2")
        );
        when(medicamentoRepositorio.findAll()).thenReturn(medicamentos);

        List<Medicamento> medicamentosListados = medicamentoService.listarTodos();

        assertEquals(2, medicamentosListados.size());
        assertEquals("Medicamento1", medicamentosListados.get(0).getMedica_nombre());
        assertEquals("Medicamento2", medicamentosListados.get(1).getMedica_nombre());
    }

    @Test
    void testActualizarMedicamento() {
        Medicamento medicamentoEntrada = new Medicamento(1L, "MedicamentoActualizado");
        when(medicamentoRepositorio.save(medicamentoEntrada)).thenReturn(medicamentoEntrada);

        Medicamento medicamentoActualizado = medicamentoService.actualizar(medicamentoEntrada);

        assertEquals(1L, medicamentoActualizado.getMedica_id());
        assertEquals("MedicamentoActualizado", medicamentoActualizado.getMedica_nombre());
    }

    @Test
    void testEliminarMedicamento() {
        Long idAEliminar = 1L;

        medicamentoService.eliminar(idAEliminar);

        verify(medicamentoRepositorio, times(1)).deleteById(idAEliminar);
    }

}
