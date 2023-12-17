package com.IngSoftGrupo1.CitasMedicas.Servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.IngSoftGrupo1.CitasMedicas.Modelos.CitaMedica;
import com.IngSoftGrupo1.CitasMedicas.Repositorios.CitaMedicaRepositorio;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class CitaMedicaService {

    private final CitaMedicaRepositorio citaMedicaRepositorio;

    @Autowired
    public CitaMedicaService(CitaMedicaRepositorio citaMedicaRepositorio) {
        this.citaMedicaRepositorio = citaMedicaRepositorio;
    }

    @Transactional(readOnly = true)
    public List<CitaMedica> getAllCitas() {
        return citaMedicaRepositorio.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<CitaMedica> getCitaById(long id) {
        return citaMedicaRepositorio.findById(id);
    }

    @Transactional
    public CitaMedica saveCita(CitaMedica cita) {
        return citaMedicaRepositorio.save(cita);
    }

    @Transactional
    public CitaMedica updateCita(long id, CitaMedica citaDetails) {
        CitaMedica cita = citaMedicaRepositorio.findById(id)
                .orElseThrow(() -> new IllegalStateException("Cita with id " + id + " does not exist"));
        
        cita.setFecha(citaDetails.getFecha());
        cita.setPaciente(citaDetails.getPaciente());
        cita.setMedico(citaDetails.getMedico());
        return citaMedicaRepositorio.save(cita);
    }

    @Transactional
    public void deleteCita(long id) {
        boolean exists = citaMedicaRepositorio.existsById(id);
        if (!exists) {
            throw new IllegalStateException("Cita with id " + id + " does not exist");
        }
        citaMedicaRepositorio.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<CitaMedica> getCitasByPacienteId(long pacienteId) {
        return citaMedicaRepositorio.findByPaciente_Id(pacienteId);
    }

    @Transactional(readOnly = true)
    public List<CitaMedica> getCitasByMedicoId(long medicoId) {
        return citaMedicaRepositorio.findByMedico_Id(medicoId);
    }

    @Transactional(readOnly = true)
    public List<CitaMedica> getCitasByFecha(LocalDate fecha) {
        LocalDateTime startOfDay = fecha.atStartOfDay();
        LocalDateTime endOfDay = fecha.atTime(LocalTime.MAX);

        return citaMedicaRepositorio.findByFechaBetween(
            Timestamp.valueOf(startOfDay),
            Timestamp.valueOf(endOfDay)
        );
    }
}
