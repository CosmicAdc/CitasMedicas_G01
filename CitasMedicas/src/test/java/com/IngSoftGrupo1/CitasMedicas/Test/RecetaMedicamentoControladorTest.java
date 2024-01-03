package com.IngSoftGrupo1.CitasMedicas.Test;

import com.IngSoftGrupo1.CitasMedicas.Controladores.RecetaMedicamentoControlador;
import com.IngSoftGrupo1.CitasMedicas.Modelos.RecetaMedicamento;
import com.IngSoftGrupo1.CitasMedicas.Servicios.RecetaMedicamentoService;
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

class RecetaMedicamentoControladorTest {

    @Mock
    private RecetaMedicamentoService recetaMedicamentoService;

    @InjectMocks
    private RecetaMedicamentoControlador recetaMedicamentoControlador;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(recetaMedicamentoControlador).build();
    }

    @Test
    void testCrear() throws Exception {
        RecetaMedicamento recetaMedicamento = new RecetaMedicamento();
        when(recetaMedicamentoService.crear(any(RecetaMedicamento.class))).thenReturn(recetaMedicamento);

        mockMvc.perform(post("/api/recetas-medicamentos")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andExpect(status().isOk());

        verify(recetaMedicamentoService, times(1)).crear(any(RecetaMedicamento.class));
    }

    @Test
    void testObtenerPorId() throws Exception {
        Long id = 1L;
        RecetaMedicamento recetaMedicamento = new RecetaMedicamento();
        when(recetaMedicamentoService.encontrarPorId(id)).thenReturn(Optional.of(recetaMedicamento));

        mockMvc.perform(get("/api/recetas-medicamentos/{id}", id))
                .andExpect(status().isOk());

        verify(recetaMedicamentoService, times(1)).encontrarPorId(id);
    }

    @Test
    void testListarTodos() throws Exception {
        List<RecetaMedicamento> listaRecetaMedicamentos = Arrays.asList(new RecetaMedicamento(), new RecetaMedicamento());
        when(recetaMedicamentoService.listarTodos()).thenReturn(listaRecetaMedicamentos);

        mockMvc.perform(get("/api/recetas-medicamentos"))
                .andExpect(status().isOk());

        verify(recetaMedicamentoService, times(1)).listarTodos();
    }

    @Test
    void testActualizar() throws Exception {
        Long id = 1L;
        RecetaMedicamento recetaMedicamento = new RecetaMedicamento();
        when(recetaMedicamentoService.encontrarPorId(id)).thenReturn(Optional.of(recetaMedicamento));
        when(recetaMedicamentoService.actualizar(any(RecetaMedicamento.class))).thenReturn(recetaMedicamento);

        mockMvc.perform(put("/api/recetas-medicamentos/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andExpect(status().isOk());

        verify(recetaMedicamentoService, times(1)).actualizar(any(RecetaMedicamento.class));
    }

    @Test
    void testEliminar() throws Exception {
        Long id = 1L;
        when(recetaMedicamentoService.encontrarPorId(id)).thenReturn(Optional.of(new RecetaMedicamento()));

        mockMvc.perform(delete("/api/recetas-medicamentos/{id}", id))
                .andExpect(status().isOk());

        verify(recetaMedicamentoService, times(1)).eliminar(id);
    }
}
