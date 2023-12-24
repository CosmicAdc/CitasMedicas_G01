package com.IngSoftGrupo1.CitasMedicas.Controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.IngSoftGrupo1.CitasMedicas.Modelos.HistoriaClinica;
import com.IngSoftGrupo1.CitasMedicas.Servicios.HistoriaClinicaService;

@RestController
@RequestMapping("/citas/historiaclinica")
public class HistoriaClinicaControlador {

	private final HistoriaClinicaService historiaClinicaService;

    @Autowired
    public HistoriaClinicaControlador(HistoriaClinicaService historiaClinicaService) {
        this.historiaClinicaService = historiaClinicaService;
    }

    @PostMapping
    public ResponseEntity<HistoriaClinica> createHistoriaClinica(@RequestBody HistoriaClinica historiaClinica) {
    	HistoriaClinica nuevoHistoriaClinica = historiaClinicaService.createHistoriaClinica(historiaClinica);
        return ResponseEntity.ok(nuevoHistoriaClinica);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HistoriaClinica> getHistoriaClinicaById(@PathVariable long id) {
        return historiaClinicaService.getHistoriaClinicaById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<HistoriaClinica> getAllHistorias() {
        return historiaClinicaService.getAllHistorias();
    }

    @PutMapping("/{id}")
    public ResponseEntity<HistoriaClinica> updateHistoriaClinica(@PathVariable long id, @RequestBody HistoriaClinica historiaclinicaDetails) {
        return historiaClinicaService.getHistoriaClinicaById(id)
                .map(historiaClinicaExistente -> {
                	historiaclinicaDetails.getPaciente();
                	historiaclinicaDetails.getConsultamedica();
                	HistoriaClinica historiaClinicaActualizado = historiaClinicaService.updateHistoriaClinica(id, historiaclinicaDetails);
                    return ResponseEntity.ok(historiaClinicaActualizado);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHistoriaClinica(@PathVariable long id) {
        return historiaClinicaService.getHistoriaClinicaById(id)
                .map(historiaClinica -> {
                	historiaClinicaService.deleteHistoriaClinica(id);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
