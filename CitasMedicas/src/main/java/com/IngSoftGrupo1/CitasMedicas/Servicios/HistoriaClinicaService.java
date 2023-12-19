package com.IngSoftGrupo1.CitasMedicas.Servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.IngSoftGrupo1.CitasMedicas.Modelos.ConsultaMedica;
import com.IngSoftGrupo1.CitasMedicas.Modelos.HistoriaClinica;
import com.IngSoftGrupo1.CitasMedicas.Repositorios.ConsultaMedicaRepositorio;
import com.IngSoftGrupo1.CitasMedicas.Repositorios.HistoriaClinicaRepositorio;

@Service
public class HistoriaClinicaService {
	

private final HistoriaClinicaRepositorio historiaclinicaRepositorio;
	
	@Autowired
    public HistoriaClinicaService(HistoriaClinicaRepositorio historiaclinicaRepositorio) {
        this.historiaclinicaRepositorio = historiaclinicaRepositorio;
    }

    @Transactional(readOnly = true)
    public List<HistoriaClinica> getAllHistorias() {
        return historiaclinicaRepositorio.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<HistoriaClinica> getHistoriaClinicaById(long id) {
        return historiaclinicaRepositorio.findById(id);
    }

    public HistoriaClinica saveHistoriaClinica(HistoriaClinica historiaclinica) {
        return historiaclinicaRepositorio.save(historiaclinica);
    }

    @Transactional
    public HistoriaClinica updateHistoriaClinica(long id, HistoriaClinica historiaclinicaDetails) {
    	HistoriaClinica historiaclinica = historiaclinicaRepositorio.findById(id)
                .orElseThrow(() -> new IllegalStateException("Historia Clinica with id " + id + " does not exist"));
        
    	historiaclinica.setPaciente(historiaclinicaDetails.getPaciente());
    	historiaclinica.setConsultamedica(historiaclinicaDetails.getConsultamedica());
        return historiaclinicaRepositorio.save(historiaclinica);
    }

    public void deleteHistoriaClinica(long id) {

        if (!historiaclinicaRepositorio.existsById(id)) {
            throw new IllegalStateException("Historia Clinica  with id " + id + " does not exist");
        }
        historiaclinicaRepositorio.deleteById(id);
}
}