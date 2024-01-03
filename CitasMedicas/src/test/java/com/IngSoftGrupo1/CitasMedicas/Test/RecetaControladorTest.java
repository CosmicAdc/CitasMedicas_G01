package com.IngSoftGrupo1.CitasMedicas.Test;

import com.IngSoftGrupo1.CitasMedicas.Controladores.RecetaControlador;
import com.IngSoftGrupo1.CitasMedicas.Modelos.Receta;
import com.IngSoftGrupo1.CitasMedicas.Servicios.RecetaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class RecetaControladorTest {
    
    @InjectMocks
    private RecetaControlador recetaControlador;

    @Mock
    private RecetaService recetaService;

    private MockMvc mockMvc;
    
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(recetaControlador).build();
    }

    @Test
    void testCrear() throws Exception {
        Receta receta = new Receta(1L, "Tomar cada 8 horas");

        when(recetaService.crear(any(Receta.class))).thenReturn(receta);

        mockMvc.perform(post("/api/recetas")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"re_indicaciones\":\"Tomar cada 8 horas\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.re_indicaciones").value("Tomar cada 8 horas"));

        verify(recetaService, times(1)).crear(any(Receta.class));
    }

    @Test
    void testObtenerPorId() throws Exception {
        Long recetaId = 1L;
        Optional<Receta> receta = Optional.of(new Receta(recetaId, "Tomar cada 8 horas"));

        when(recetaService.encontrarPorId(recetaId)).thenReturn(receta);

        mockMvc.perform(get("/api/recetas/{id}", recetaId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.re_id").value(recetaId));

        verify(recetaService, times(1)).encontrarPorId(recetaId);
    }

    @Test
    void testListarTodos() throws Exception {
        List<Receta> recetaList = Arrays.asList(
                new Receta(1L, "Tomar cada 8 horas"),
                new Receta(2L, "Aplicar cada 12 horas")
        );

        when(recetaService.listarTodos()).thenReturn(recetaList);

        mockMvc.perform(get("/api/recetas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].re_id").value(1L))
                .andExpect(jsonPath("$[1].re_id").value(2L));

        verify(recetaService, times(1)).listarTodos();
    }

    @Test
    void testActualizar() throws Exception {
        Long recetaId = 1L;
        Receta receta = new Receta(recetaId, "Tomar cada 8 horas");

        when(recetaService.encontrarPorId(recetaId)).thenReturn(Optional.of(receta));
        when(recetaService.actualizar(any(Receta.class))).thenReturn(receta);

        mockMvc.perform(put("/api/recetas/{id}", recetaId)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"re_indicaciones\":\"Actualizar indicaciones\"}"))
                .andExpect(status().isOk());

        verify(recetaService, times(1)).encontrarPorId(recetaId);
        verify(recetaService, times(1)).actualizar(any(Receta.class));
    }

    @Test
    void testEliminar() throws Exception {
        Long recetaId = 1L;

        when(recetaService.encontrarPorId(recetaId)).thenReturn(Optional.of(new Receta()));

        mockMvc.perform(delete("/api/recetas/{id}", recetaId))
                .andExpect(status().isOk());

        verify(recetaService, times(1)).encontrarPorId(recetaId);
        verify(recetaService, times(1)).eliminar(recetaId);
    }
}
