package com.IngSoftGrupo1.CitasMedicas.Controladores;

import com.IngSoftGrupo1.CitasMedicas.Modelos.CitaMedica;
import com.IngSoftGrupo1.CitasMedicas.Servicios.CitaMedicaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

@RestController
@RequestMapping("/citas/medicas")
public class CitaMedicaControlador {

    private final CitaMedicaService citaMedicaService;

    @Autowired
    public CitaMedicaControlador(CitaMedicaService citaMedicaService) {
        this.citaMedicaService = citaMedicaService;
    }

    @Operation(summary = "Obtener todas las citas médicas", description = "Obtiene una lista de todas las citas médicas.")
    @GetMapping
    public ResponseEntity<List<CitaMedica>> getAllCitas() {
        List<CitaMedica> citas = citaMedicaService.getAllCitas();
        return ResponseEntity.ok(citas);
    }

    @Operation(summary = "Obtener una cita médica por ID", description = "Obtiene una cita médica por su ID.")
    @GetMapping("/{id}")
    public ResponseEntity<CitaMedica> getCitaById(@PathVariable long id) {
        return citaMedicaService.getCitaById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Crear una nueva cita médica", description = "Crea una nueva cita médica.")
    @PostMapping
    public ResponseEntity<CitaMedica> createCita(@RequestBody CitaMedica cita) {
        CitaMedica nuevaCita = citaMedicaService.saveCita(cita);
        return ResponseEntity.ok(nuevaCita);
    }

    @Operation(summary = "Actualizar una cita médica por ID", description = "Actualiza una cita médica existente por su ID.")
    @PutMapping("/{id}")
    public ResponseEntity<CitaMedica> updateCita(@PathVariable long id, @RequestBody CitaMedica citaDetails) {
        CitaMedica updatedCita = citaMedicaService.updateCita(id, citaDetails);
        return ResponseEntity.ok(updatedCita);
    }

    @Operation(summary = "Eliminar una cita médica por ID", description = "Elimina una cita médica por su ID.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCita(@PathVariable long id) {
        citaMedicaService.deleteCita(id);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Obtener citas médicas por ID de paciente", description = "Obtiene una lista de citas médicas por ID de paciente.")
    @GetMapping("/paciente/{pacienteId}")
    public ResponseEntity<List<CitaMedica>> getCitasByPacienteId(@PathVariable long pacienteId) {
        List<CitaMedica> citas = citaMedicaService.getCitasByPacienteId(pacienteId);
        return ResponseEntity.ok(citas);
    }

    @Operation(summary = "Obtener citas médicas por ID de médico", description = "Obtiene una lista de citas médicas por ID de médico.")
    @GetMapping("/medico/{medicoId}")
    public ResponseEntity<List<CitaMedica>> getCitasByMedicoId(@PathVariable long medicoId) {
        List<CitaMedica> citas = citaMedicaService.getCitasByMedicoId(medicoId);
        return ResponseEntity.ok(citas);
    }

    @Operation(summary = "Obtener citas médicas por fecha", description = "Obtiene una lista de citas médicas por fecha.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Citas médicas encontradas"),
            @ApiResponse(responseCode = "400", description = "Formato de fecha incorrecto")
    })
    @GetMapping("/fecha")
    public ResponseEntity<List<CitaMedica>> getCitasByFecha(@RequestParam String fecha) {
        try {
            LocalDate localDate = LocalDate.parse(fecha, DateTimeFormatter.ISO_LOCAL_DATE);
            List<CitaMedica> citas = citaMedicaService.getCitasByFecha(localDate);
            return ResponseEntity.ok(citas);
        } catch (DateTimeParseException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
