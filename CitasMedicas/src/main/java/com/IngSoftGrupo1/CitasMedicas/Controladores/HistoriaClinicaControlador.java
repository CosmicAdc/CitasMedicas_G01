package com.IngSoftGrupo1.CitasMedicas.Controladores;

import com.IngSoftGrupo1.CitasMedicas.Modelos.HistoriaClinica;
import com.IngSoftGrupo1.CitasMedicas.Servicios.HistoriaClinicaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/citas/historiaclinica")
public class HistoriaClinicaControlador {

    private final HistoriaClinicaService historiaClinicaService;

    @Autowired
    public HistoriaClinicaControlador(HistoriaClinicaService historiaClinicaService) {
        this.historiaClinicaService = historiaClinicaService;
    }

    @Operation(summary = "Crear una nueva historia clínica", description = "Crea una nueva historia clínica.")
    @PostMapping
    public ResponseEntity<HistoriaClinica> createHistoriaClinica(@RequestBody HistoriaClinica historiaClinica) {
        HistoriaClinica nuevoHistoriaClinica = historiaClinicaService.createHistoriaClinica(historiaClinica);
        return ResponseEntity.ok(nuevoHistoriaClinica);
    }

    @Operation(summary = "Obtener una historia clínica por ID", description = "Obtiene una historia clínica por su ID.")
    @GetMapping("/{id}")
    public ResponseEntity<HistoriaClinica> getHistoriaClinicaById(@PathVariable long id) {
        return historiaClinicaService.getHistoriaClinicaById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Obtener todas las historias clínicas", description = "Obtiene una lista de todas las historias clínicas.")
    @GetMapping
    public List<HistoriaClinica> getAllHistorias() {
        return historiaClinicaService.getAllHistorias();
    }

    @Operation(summary = "Actualizar una historia clínica por ID", description = "Actualiza una historia clínica existente por su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Historia clínica actualizada"),
            @ApiResponse(responseCode = "404", description = "Historia clínica no encontrada")
    })
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

    @Operation(summary = "Eliminar una historia clínica por ID", description = "Elimina una historia clínica por su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Historia clínica eliminada"),
            @ApiResponse(responseCode = "404", description = "Historia clínica no encontrada")
    })
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
