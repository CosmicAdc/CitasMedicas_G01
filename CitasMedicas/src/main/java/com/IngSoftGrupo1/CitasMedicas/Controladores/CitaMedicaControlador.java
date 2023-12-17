package com.IngSoftGrupo1.CitasMedicas.Controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.IngSoftGrupo1.CitasMedicas.Modelos.CitaMedica;
import com.IngSoftGrupo1.CitasMedicas.Servicios.CitaMedicaService;

import java.sql.Timestamp;
import java.util.List;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;


@RestController
@RequestMapping("/citas/medicas")
public class CitaMedicaControlador {

    private final CitaMedicaService citaMedicaService;

    @Autowired
    public CitaMedicaControlador(CitaMedicaService citaMedicaService) {
        this.citaMedicaService = citaMedicaService;
    }

    @GetMapping
    public ResponseEntity<List<CitaMedica>> getAllCitas() {
        List<CitaMedica> citas = citaMedicaService.getAllCitas();
        return ResponseEntity.ok(citas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CitaMedica> getCitaById(@PathVariable long id) {
        return citaMedicaService.getCitaById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CitaMedica> createCita(@RequestBody CitaMedica cita) {
        CitaMedica nuevaCita = citaMedicaService.saveCita(cita);
        return ResponseEntity.ok(nuevaCita);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CitaMedica> updateCita(@PathVariable long id, @RequestBody CitaMedica citaDetails) {
        CitaMedica updatedCita = citaMedicaService.updateCita(id, citaDetails);
        return ResponseEntity.ok(updatedCita);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCita(@PathVariable long id) {
        citaMedicaService.deleteCita(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/paciente/{pacienteId}")
    public ResponseEntity<List<CitaMedica>> getCitasByPacienteId(@PathVariable long pacienteId) {
        List<CitaMedica> citas = citaMedicaService.getCitasByPacienteId(pacienteId);
        return ResponseEntity.ok(citas);
    }

    @GetMapping("/medico/{medicoId}")
    public ResponseEntity<List<CitaMedica>> getCitasByMedicoId(@PathVariable long medicoId) {
        List<CitaMedica> citas = citaMedicaService.getCitasByMedicoId(medicoId);
        return ResponseEntity.ok(citas);
    }

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
