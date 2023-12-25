package com.IngSoftGrupo1.CitasMedicas.Controladores;

import com.IngSoftGrupo1.CitasMedicas.Modelos.Medico;
import com.IngSoftGrupo1.CitasMedicas.Servicios.MedicoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/citas/medicos")
public class MedicoControlador {

    private final MedicoService medicoService;

    @Autowired
    public MedicoControlador(MedicoService medicoService) {
        this.medicoService = medicoService;
    }

    @Operation(summary = "Obtener todos los médicos", description = "Obtiene una lista de todos los médicos.")
    @Transactional(readOnly = true)
    @GetMapping
    public ResponseEntity<List<Medico>> getAllMedicos() {
        List<Medico> medicos = medicoService.getAllMedicos();
        return ResponseEntity.ok(medicos);
    }

    @Operation(summary = "Obtener un médico por ID", description = "Obtiene un médico por su ID.")
    @Transactional(readOnly = true)
    @GetMapping("/{id}")
    public ResponseEntity<Medico> getMedicoById(@PathVariable long id) {
        Optional<Medico> medico = medicoService.getMedicoById(id);
        return medico.map(ResponseEntity::ok)
                     .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Crear un nuevo médico", description = "Crea un nuevo médico.")
    @PostMapping
    public ResponseEntity<Medico> createMedico(@RequestBody Medico medico) {
        Medico nuevoMedico = medicoService.saveMedico(medico);
        return ResponseEntity.ok(nuevoMedico);
    }

    @Operation(summary = "Actualizar un médico por ID", description = "Actualiza un médico existente por su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Médico actualizado"),
            @ApiResponse(responseCode = "404", description = "Médico no encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Medico> updateMedico(@PathVariable long id, @RequestBody Medico medicoDetails) {
        try {
            Medico updatedMedico = medicoService.updateMedico(id, medicoDetails);
            return ResponseEntity.ok(updatedMedico);
        } catch (IllegalStateException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Eliminar un médico por ID", description = "Elimina un médico por su ID.")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMedico(@PathVariable long id) {
        medicoService.deleteMedico(id);
        return ResponseEntity.ok().build();
    }
}
