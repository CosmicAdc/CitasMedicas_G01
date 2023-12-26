package com.IngSoftGrupo1.CitasMedicas.Test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.IngSoftGrupo1.CitasMedicas.Modelos.HistoriaClinica;
import com.IngSoftGrupo1.CitasMedicas.Repositorios.HistoriaClinicaRepositorio;
import com.IngSoftGrupo1.CitasMedicas.Servicios.HistoriaClinicaService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;





import org.junit.jupiter.api.Test;

class HistoriaClinicaTest {

	@InjectMocks
    private HistoriaClinicaService historiaClinicaService;

    @Mock
    private HistoriaClinicaRepositorio historiaClinicaRepositorio;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetAllHistorias() {
        // Definir comportamiento del repositorio mock
        when(historiaClinicaRepositorio.findAll()).thenReturn(new ArrayList<>());

        // Llamar al método del servicio y verificar el resultado
        List<HistoriaClinica> historias = historiaClinicaService.getAllHistorias();
        assertNotNull(historias);
        assertEquals(0, historias.size());
    }

    @Test
    void testGetHistoriaClinicaById() {
        // Definir comportamiento del repositorio mock
        long historiaId = 1L;
        HistoriaClinica historiaClinicaMock = new HistoriaClinica();
        when(historiaClinicaRepositorio.findById(historiaId)).thenReturn(Optional.of(historiaClinicaMock));

        // Llamar al método del servicio y verificar el resultado
        Optional<HistoriaClinica> historiaClinica = historiaClinicaService.getHistoriaClinicaById(historiaId);
        assertTrue(historiaClinica.isPresent());
        assertEquals(historiaClinicaMock, historiaClinica.get());
    }

    @Test
    void testCreateHistoriaClinica() {
        // Definir comportamiento del repositorio mock
        HistoriaClinica historiaClinicaMock = new HistoriaClinica();
        when(historiaClinicaRepositorio.save(any(HistoriaClinica.class))).thenReturn(historiaClinicaMock);

        // Llamar al método del servicio y verificar el resultado
        HistoriaClinica nuevaHistoria = new HistoriaClinica();
        HistoriaClinica resultado = historiaClinicaService.createHistoriaClinica(nuevaHistoria);
        assertNotNull(resultado);
        assertEquals(historiaClinicaMock, resultado);
    }

    @Test
    void testUpdateHistoriaClinica() {
        // Definir comportamiento del repositorio mock
        long historiaId = 1L;
        HistoriaClinica historiaClinicaMock = new HistoriaClinica();
        when(historiaClinicaRepositorio.findById(historiaId)).thenReturn(Optional.of(historiaClinicaMock));
        when(historiaClinicaRepositorio.save(any(HistoriaClinica.class))).thenReturn(historiaClinicaMock);

        // Llamar al método del servicio y verificar el resultado
        HistoriaClinica historiaActualizada = new HistoriaClinica();
        HistoriaClinica resultado = historiaClinicaService.updateHistoriaClinica(historiaId, historiaActualizada);
        assertNotNull(resultado);
        assertEquals(historiaClinicaMock, resultado);
    }

    @Test
    void testUpdateHistoriaClinicaNoExistente() {
        // Definir comportamiento del repositorio mock
        long historiaId = 1L;
        when(historiaClinicaRepositorio.findById(historiaId)).thenReturn(Optional.empty());

        // Llamar al método del servicio y verificar que lanza IllegalStateException
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            historiaClinicaService.updateHistoriaClinica(historiaId, new HistoriaClinica());
        });

        assertEquals("Historia Clinica with id " + historiaId + " does not exist", exception.getMessage());

        // Verificar que el método findById del repositorio se llamó con el ID correcto
        verify(historiaClinicaRepositorio, times(1)).findById(historiaId);
        
        // Verificar que el método save del repositorio no se llamó
        verify(historiaClinicaRepositorio, never()).save(any());
    }
    
    @Test
    void testDeleteHistoriaClinicaExistente() {
        // Definir comportamiento del repositorio mock
        long historiaId = 1L;
        when(historiaClinicaRepositorio.existsById(historiaId)).thenReturn(true);

        // Llamar al método del servicio y verificar que no lanza excepciones
        assertDoesNotThrow(() -> historiaClinicaService.deleteHistoriaClinica(historiaId));

        // Verificar que el método deleteById del repositorio se llamó con el ID correcto
        verify(historiaClinicaRepositorio, times(1)).deleteById(historiaId);
    }

    @Test
    void testDeleteHistoriaClinicaNoExistente() {
        // Definir comportamiento del repositorio mock
        long historiaId = 1L;
        when(historiaClinicaRepositorio.existsById(historiaId)).thenReturn(false);

        // Llamar al método del servicio y verificar que lanza IllegalStateException
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            historiaClinicaService.deleteHistoriaClinica(historiaId);
        });

        assertEquals("Historia Clinica with id " + historiaId + " does not exist", exception.getMessage());

        // Verificar que el método deleteById del repositorio no se llamó
        verify(historiaClinicaRepositorio, never()).deleteById(anyLong());
    }

}
