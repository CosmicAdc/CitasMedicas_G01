package com.IngSoftGrupo1.CitasMedicas.Test;

import com.IngSoftGrupo1.CitasMedicas.Modelos.CitaMedica;
import com.IngSoftGrupo1.CitasMedicas.Modelos.Medico;
import com.IngSoftGrupo1.CitasMedicas.Modelos.Usuarios;
import com.IngSoftGrupo1.CitasMedicas.Repositorios.CitaMedicaRepositorio;
import com.IngSoftGrupo1.CitasMedicas.Servicios.CitaMedicaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CitaMedicaTest {

    @InjectMocks
    private CitaMedicaService citaMedicaService;

    @Mock
    private CitaMedicaRepositorio citaMedicaRepositorio;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetAllCitas() {
        CitaMedica cita1 = new CitaMedica(1, new Timestamp(System.currentTimeMillis()), new Usuarios(), new Medico());
        CitaMedica cita2 = new CitaMedica(2, new Timestamp(System.currentTimeMillis()), new Usuarios(), new Medico());

        when(citaMedicaRepositorio.findAll()).thenReturn(Arrays.asList(cita1, cita2));

        List<CitaMedica> citas = citaMedicaService.getAllCitas();

        assertEquals(2, citas.size());
    }

    @Test
    void testGetCitaById() {
        long id = 1L;
        CitaMedica citaMock = new CitaMedica(id, new Timestamp(System.currentTimeMillis()), new Usuarios(), new Medico());
        when(citaMedicaRepositorio.findById(id)).thenReturn(Optional.of(citaMock));

        Optional<CitaMedica> resultado = citaMedicaService.getCitaById(id);

        assertTrue(resultado.isPresent());
        assertEquals(id, resultado.get().getCitaId());
    }

    @Test
    void testSaveCita() {
        CitaMedica citaNueva = new CitaMedica(3, new Timestamp(System.currentTimeMillis()), new Usuarios(), new Medico());
        when(citaMedicaRepositorio.save(citaNueva)).thenReturn(citaNueva);

        CitaMedica citaGuardada = citaMedicaService.saveCita(citaNueva);

        assertNotNull(citaGuardada);
        assertEquals(3, citaGuardada.getCitaId());
    }

    @Test
    void testUpdateCita() {
        long id = 1L;
        CitaMedica citaExistente = new CitaMedica(id, new Timestamp(System.currentTimeMillis()), new Usuarios(), new Medico());
        CitaMedica detallesCita = new CitaMedica(id, new Timestamp(System.currentTimeMillis()), new Usuarios(), new Medico());

        when(citaMedicaRepositorio.findById(id)).thenReturn(Optional.of(citaExistente));
        when(citaMedicaRepositorio.save(any(CitaMedica.class))).thenReturn(detallesCita);

        CitaMedica citaActualizada = citaMedicaService.updateCita(id, detallesCita);

        assertNotNull(citaActualizada);
        assertEquals(id, citaActualizada.getCitaId());
    }

    @Test
    void testDeleteCita() {
        long id = 1L;
        doNothing().when(citaMedicaRepositorio).deleteById(id);
        when(citaMedicaRepositorio.existsById(id)).thenReturn(true);

        citaMedicaService.deleteCita(id);

        verify(citaMedicaRepositorio, times(1)).deleteById(id);
    }

    // Aquí puedes agregar más pruebas para cubrir otros casos y métodos
}
