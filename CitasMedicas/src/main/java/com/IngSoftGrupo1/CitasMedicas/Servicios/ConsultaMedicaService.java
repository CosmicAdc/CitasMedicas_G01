package com.IngSoftGrupo1.CitasMedicas.Servicios;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.IngSoftGrupo1.CitasMedicas.Modelos.ConsultaMedica;
import com.IngSoftGrupo1.CitasMedicas.Modelos.Medico;
import com.IngSoftGrupo1.CitasMedicas.Repositorios.ConsultaMedicaRepositorio;
import com.IngSoftGrupo1.CitasMedicas.Repositorios.MedicoRepositorio;

@Service
public class ConsultaMedicaService {
	
	private final ConsultaMedicaRepositorio consultamedicaRepositorio;
	
	@Autowired
    public ConsultaMedicaService(ConsultaMedicaRepositorio consultamedicaRepositorio) {
        this.consultamedicaRepositorio = consultamedicaRepositorio;
    }

    public List<ConsultaMedica> getAllConsultas() {
        return consultamedicaRepositorio.findAll();
    }

    public Optional<ConsultaMedica> getConsultaMedicaById(long id) {
        return consultamedicaRepositorio.findById(id);
    }

    public ConsultaMedica createConsultaMedica(ConsultaMedica consultamedica) {
        return consultamedicaRepositorio.save(consultamedica);
    }

    
    public ConsultaMedica updateConsultaMedica(long id, ConsultaMedica consultamedicaDetails) {
    	ConsultaMedica consultamedica = consultamedicaRepositorio.findById(id)
                .orElseThrow(() -> new IllegalStateException("Consulta Medica with id " + id + " does not exist"));
        
        consultamedica.setCitamedica(consultamedicaDetails.getCitamedica());
        consultamedica.setReceta(consultamedicaDetails.getReceta());
        consultamedica.setDiagnostico(consultamedicaDetails.getDiagnostico());
        return consultamedicaRepositorio.save(consultamedica);
    }

    public void deleteConsultaMedica(long id) {

        if (!consultamedicaRepositorio.existsById(id)) {
            throw new IllegalStateException("Consulta medica with id " + id + " does not exist");
        }
        consultamedicaRepositorio.deleteById(id);
    }

}
