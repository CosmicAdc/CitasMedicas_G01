package com.IngSoftGrupo1.CitasMedicas.Test;

import com.IngSoftGrupo1.CitasMedicas.Modelos.Medico;
import com.IngSoftGrupo1.CitasMedicas.Modelos.Usuarios;
import com.IngSoftGrupo1.CitasMedicas.Repositorios.MedicoRepositorio;
import com.IngSoftGrupo1.CitasMedicas.Servicios.MedicoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MedicoTest {

    @InjectMocks
    private MedicoService medicoService;

    @Mock
    private MedicoRepositorio medicoRepositorio;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetAllMedicos() {
        Medico medico1 = new Medico();
        medico1.setId(1L);
        medico1.setEspecializacion("Cardiología");
        medico1.setSexo("Masculino");
        medico1.setDireccion("Calle 123");
        medico1.setCorreo("medico1@hospital.com");
        medico1.setTurnoInicio(new Timestamp(System.currentTimeMillis()));
        medico1.setTurnoFin(new Timestamp(System.currentTimeMillis()));
        medico1.setUsuario(new Usuarios());  // Suponiendo que Usuarios es una clase existente

        Medico medico2 = new Medico();
        medico2.setId(2L);
        medico2.setEspecializacion("Neurología");
        medico2.setSexo("Femenino");
        medico2.setDireccion("Calle 456");
        medico2.setCorreo("medico2@hospital.com");
        medico2.setTurnoInicio(new Timestamp(System.currentTimeMillis()));
        medico2.setTurnoFin(new Timestamp(System.currentTimeMillis()));
        medico2.setUsuario(new Usuarios());  // Suponiendo que Usuarios es una clase existente

        when(medicoRepositorio.findAll()).thenReturn(Arrays.asList(medico1, medico2));

        List<Medico> medicos = medicoService.getAllMedicos();

        assertEquals(2, medicos.size());
        assertEquals("Cardiología", medicos.get(0).getEspecializacion());
        assertEquals("Neurología", medicos.get(1).getEspecializacion());
    }

    @Test
    void testGetMedicoById() {
        long id = 1L;
        Medico medicoMock = new Medico();
        medicoMock.setId(id);
        medicoMock.setEspecializacion("Cardiología");
        medicoMock.setSexo("Masculino");
        medicoMock.setDireccion("Calle 123");
        medicoMock.setCorreo("medico1@hospital.com");
        medicoMock.setTurnoInicio(new Timestamp(System.currentTimeMillis()));
        medicoMock.setTurnoFin(new Timestamp(System.currentTimeMillis()));
        medicoMock.setUsuario(new Usuarios());  // Suponiendo que Usuarios es una clase existente

        when(medicoRepositorio.findById(id)).thenReturn(Optional.of(medicoMock));

        Optional<Medico> resultado = medicoService.getMedicoById(id);

        assertTrue(resultado.isPresent());
        assertEquals("Cardiología", resultado.get().getEspecializacion());
    }

    @Test
    void testSaveMedico() {
        Medico medicoNuevo = new Medico();
        medicoNuevo.setId(3L);
        medicoNuevo.setEspecializacion("Pediatría");
        medicoNuevo.setSexo("Femenino");
        medicoNuevo.setDireccion("Calle 789");
        medicoNuevo.setCorreo("medico3@hospital.com");
        medicoNuevo.setTurnoInicio(new Timestamp(System.currentTimeMillis()));
        medicoNuevo.setTurnoFin(new Timestamp(System.currentTimeMillis()));
        medicoNuevo.setUsuario(new Usuarios());  // Suponiendo que Usuarios es una clase existente

        when(medicoRepositorio.save(medicoNuevo)).thenReturn(medicoNuevo);

        Medico medicoGuardado = medicoService.saveMedico(medicoNuevo);

        assertNotNull(medicoGuardado);
        assertEquals("Pediatría", medicoGuardado.getEspecializacion());
    }

    @Test
    void testUpdateMedico() {
        long id = 1L;
        Medico medicoExistente = new Medico();
        medicoExistente.setId(id);
        medicoExistente.setEspecializacion("Cardiología");
        medicoExistente.setSexo("Masculino");
        medicoExistente.setDireccion("Calle 123");
        medicoExistente.setCorreo("medico1@hospital.com");
        medicoExistente.setTurnoInicio(new Timestamp(System.currentTimeMillis()));
        medicoExistente.setTurnoFin(new Timestamp(System.currentTimeMillis()));
        medicoExistente.setUsuario(new Usuarios());  // Suponiendo que Usuarios es una clase existente

        Medico detallesMedico = new Medico();
        detallesMedico.setEspecializacion("Dermatología");
        detallesMedico.setSexo("Femenino");
        detallesMedico.setDireccion("Calle 789");
        detallesMedico.setCorreo("medico1@hospital.com");
        detallesMedico.setTurnoInicio(new Timestamp(System.currentTimeMillis()));
        detallesMedico.setTurnoFin(new Timestamp(System.currentTimeMillis()));
        detallesMedico.setUsuario(new Usuarios());  // Suponiendo que Usuarios es una clase existente

        when(medicoRepositorio.findById(id)).thenReturn(Optional.of(medicoExistente));
        when(medicoRepositorio.save(any(Medico.class))).thenReturn(detallesMedico);

        Medico medicoActualizado = medicoService.updateMedico(id, detallesMedico);

        assertNotNull(medicoActualizado);
        assertEquals("Dermatología", medicoActualizado.getEspecializacion());
    }

    @Test
    void testDeleteMedico() {
        long id = 1L;
        doNothing().when(medicoRepositorio).deleteById(id);
        when(medicoRepositorio.existsById(id)).thenReturn(true);

        medicoService.deleteMedico(id);

        verify(medicoRepositorio, times(1)).deleteById(id);
    }

}
