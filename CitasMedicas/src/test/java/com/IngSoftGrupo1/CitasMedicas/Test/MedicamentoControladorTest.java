package com.IngSoftGrupo1.CitasMedicas.Test;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

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
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.IngSoftGrupo1.CitasMedicas.Controladores.MedicamentoControlador;
import com.IngSoftGrupo1.CitasMedicas.Controladores.UsuarioControlador;
import com.IngSoftGrupo1.CitasMedicas.Modelos.Medicamento;
import com.IngSoftGrupo1.CitasMedicas.Servicios.MedicamentoService;
import com.IngSoftGrupo1.CitasMedicas.Servicios.UsuarioService;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;



class MedicamentoControladorTest {
	
	@InjectMocks
	private MedicamentoControlador medicamentoControlador;

	@Mock
	private MedicamentoService medicamentoService;

	private MockMvc mockMvc;
	
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(medicamentoControlador).build();
    }

    @Test
    void testCrear() throws Exception {
    	Medicamento medicamento=new Medicamento(1L, "Medicamento Aspirina");
    	

        when(medicamentoService.crear(any())).thenReturn(medicamento);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/medicamentos")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(medicamentoService, times(1)).crear(any());
        verifyNoMoreInteractions(medicamentoService);
    }
    
    @Test
    void testObtenerPorId() throws Exception {
        long medicamentoId = 1L;
    	Medicamento medicamento=new Medicamento(1L, "Medicamento Aspirina");

        when(medicamentoService.encontrarPorId(medicamentoId)).thenReturn(Optional.of(medicamento));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/medicamentos/{id}", medicamentoId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());


        verify(medicamentoService, times(1)).encontrarPorId(medicamentoId);
        verifyNoMoreInteractions(medicamentoService);
    }
    @Test
    void testListarTodos() throws Exception {
        List<Medicamento> medicamentosList = Arrays.asList(
        		new Medicamento(2L, "Medicamento Loratadina"),
        		new Medicamento(1L, "Medicamento Aspirina")
        );

        when(medicamentoService.listarTodos()).thenReturn(medicamentosList);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/medicamentos")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
   

        verify(medicamentoService, times(1)).listarTodos();
        verifyNoMoreInteractions(medicamentoService);
    }

    @Test
    void testActualizar() throws Exception {
        long medicamentoId = 1L;
        Medicamento medicamento = new Medicamento(2L, "Medicamento Loratadina");

        when(medicamentoService.encontrarPorId(medicamentoId)).thenReturn(Optional.of(medicamento));
        when(medicamentoService.actualizar(any())).thenReturn(medicamento);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/medicamentos/{id}", medicamentoId)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(medicamentoService, times(1)).encontrarPorId(medicamentoId);
        verify(medicamentoService, times(1)).actualizar(any());
        verifyNoMoreInteractions(medicamentoService);
    }
    
    @Test
    void testEliminar() throws Exception {
        long medicamentoId = 1L;
        when(medicamentoService.encontrarPorId(medicamentoId)).thenReturn(Optional.of(new Medicamento()));

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/medicamentos/{id}", medicamentoId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(medicamentoService, times(1)).encontrarPorId(medicamentoId);
        verify(medicamentoService, times(1)).eliminar(medicamentoId);
        verifyNoMoreInteractions(medicamentoService);
    }

	
    

}
