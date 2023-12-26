package com.IngSoftGrupo1.CitasMedicas.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.IngSoftGrupo1.CitasMedicas.Modelos.ConsultaMedica;
import com.IngSoftGrupo1.CitasMedicas.Repositorios.ConsultaMedicaRepositorio;
import com.IngSoftGrupo1.CitasMedicas.Repositorios.UsuarioRepositorio;
import com.IngSoftGrupo1.CitasMedicas.Servicios.ConsultaMedicaService;
import com.IngSoftGrupo1.CitasMedicas.Servicios.UsuarioService;

import java.util.ArrayList;
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


class ConsultaMedicaTest {

	
	@InjectMocks
    private ConsultaMedicaService consultaMedicaService;
	
	@Mock
	private ConsultaMedicaRepositorio consultaMedicaRepositorio;
	
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }
	

	 @Test
	    void testGetAllConsultas() {
	        // Definir comportamiento del repositorio mock
	        when(consultaMedicaRepositorio.findAll()).thenReturn(new ArrayList<>());

	        // Llamar al método del servicio y verificar el resultado
	        List<ConsultaMedica> consultas = consultaMedicaService.getAllConsultas();
	        assertNotNull(consultas);
	        assertEquals(0, consultas.size());
	    }

	    @Test
	    void testGetConsultaMedicaById() {
	        // Definir comportamiento del repositorio mock
	        long consultaId = 1L;
	        ConsultaMedica consultaMedicaMock = new ConsultaMedica();
	        when(consultaMedicaRepositorio.findById(consultaId)).thenReturn(Optional.of(consultaMedicaMock));

	        // Llamar al método del servicio y verificar el resultado
	        Optional<ConsultaMedica> consultaMedica = consultaMedicaService.getConsultaMedicaById(consultaId);
	        assertTrue(consultaMedica.isPresent());
	        assertEquals(consultaMedicaMock, consultaMedica.get());
	    }

	    @Test
	    void testCreateConsultaMedica() {
	        // Definir comportamiento del repositorio mock
	        ConsultaMedica consultaMedicaMock = new ConsultaMedica();
	        when(consultaMedicaRepositorio.save(any(ConsultaMedica.class))).thenReturn(consultaMedicaMock);

	        // Llamar al método del servicio y verificar el resultado
	        ConsultaMedica nuevaConsulta = new ConsultaMedica();
	        ConsultaMedica resultado = consultaMedicaService.createConsultaMedica(nuevaConsulta);
	        assertNotNull(resultado);
	        assertEquals(consultaMedicaMock, resultado);
	    }

	    @Test
	    void testUpdateConsultaMedica() {
	        // Definir comportamiento del repositorio mock
	        long consultaId = 1L;
	        ConsultaMedica consultaMedicaMock = new ConsultaMedica();
	        when(consultaMedicaRepositorio.findById(consultaId)).thenReturn(Optional.of(consultaMedicaMock));
	        when(consultaMedicaRepositorio.save(any(ConsultaMedica.class))).thenReturn(consultaMedicaMock);

	        // Llamar al método del servicio y verificar el resultado
	        ConsultaMedica consultaActualizada = new ConsultaMedica();
	        ConsultaMedica resultado = consultaMedicaService.updateConsultaMedica(consultaId, consultaActualizada);
	        assertNotNull(resultado);
	        assertEquals(consultaMedicaMock, resultado);
	    }
	    
	    @Test
	    void testUpdateConsultaMedicaCuandoNoExiste() {
	        // Definir comportamiento del repositorio mock
	        long consultaId = 1L;
	        when(consultaMedicaRepositorio.findById(consultaId)).thenReturn(Optional.empty());

	        // Llamar al método del servicio y verificar que lanza IllegalStateException
	        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
	            consultaMedicaService.updateConsultaMedica(consultaId, new ConsultaMedica());
	        });

	        assertEquals("Consulta Medica with id " + consultaId + " does not exist", exception.getMessage());
	    }

	    @Test
	    void testDeleteConsultaMedica() {
	        // Definir comportamiento del repositorio mock
	        long consultaId = 1L;
	        when(consultaMedicaRepositorio.existsById(consultaId)).thenReturn(true);

	        // Llamar al método del servicio y verificar que no lance excepciones
	        assertDoesNotThrow(() -> consultaMedicaService.deleteConsultaMedica(consultaId));
	    }
	    
	    @Test
	    void testDeleteConsultaMedicaNoExistente() {
	        // Definir comportamiento del repositorio mock
	        long consultaId = 1L;
	        when(consultaMedicaRepositorio.existsById(consultaId)).thenReturn(false);

	        // Llamar al método del servicio y verificar que lanza IllegalStateException
	        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
	            consultaMedicaService.deleteConsultaMedica(consultaId);
	        });

	        assertEquals("Consulta medica with id " + consultaId + " does not exist", exception.getMessage());

	        // Verificar que el método existsById y deleteById del repositorio se llamaron correctamente
	        verify(consultaMedicaRepositorio, times(1)).existsById(consultaId);
	        verify(consultaMedicaRepositorio, never()).deleteById(anyLong());
	    }
}
