package com.IngSoftGrupo1.CitasMedicas.Servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.IngSoftGrupo1.CitasMedicas.Modelos.Medico;
import com.IngSoftGrupo1.CitasMedicas.Repositorios.MedicoRepositorio;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MedicoService {

    private final MedicoRepositorio medicoRepositorio;

    @Autowired
    public MedicoService(MedicoRepositorio medicoRepositorio) {
        this.medicoRepositorio = medicoRepositorio;
    }

    @Transactional(readOnly = true)
    public List<Medico> getAllMedicos() {
        // Carga la lista de médicos y asegura que la entidad Usuarios esté inicializada
        return medicoRepositorio.findAll().stream()
                .peek(medico -> medico.getUsuario().getId()) // Accede al ID para cargar el proxy de Usuarios
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Optional<Medico> getMedicoById(long id) {
        // Encuentra el médico por ID y carga completamente la entidad Usuarios asociada
        return medicoRepositorio.findById(id)
                .map(medico -> {
                    medico.getUsuario().getId(); // Accede al ID para cargar el proxy de Usuarios
                    return medico;
                });
    }

    public Medico saveMedico(Medico medico) {
        // Guarda el médico en la base de datos
        return medicoRepositorio.save(medico);
    }

    @Transactional
    public Medico updateMedico(long id, Medico medicoDetails) {
        // Encuentra el médico existente y actualiza sus detalles
        Medico medico = medicoRepositorio.findById(id)
                .orElseThrow(() -> new IllegalStateException("Medico with id " + id + " does not exist"));
        
        medico.setEspecializacion(medicoDetails.getEspecializacion());
        medico.setSexo(medicoDetails.getSexo());
        medico.setDireccion(medicoDetails.getDireccion());
        medico.setCorreo(medicoDetails.getCorreo());
        medico.setTurnoInicio(medicoDetails.getTurnoInicio());
        medico.setTurnoFin(medicoDetails.getTurnoFin());
        medico.setUsuario(medicoDetails.getUsuario());
        
        return medicoRepositorio.save(medico);
    }

    public void deleteMedico(long id) {
        // Elimina el médico por ID
        if (!medicoRepositorio.existsById(id)) {
            throw new IllegalStateException("Medico with id " + id + " does not exist");
        }
        medicoRepositorio.deleteById(id);
    }
}
