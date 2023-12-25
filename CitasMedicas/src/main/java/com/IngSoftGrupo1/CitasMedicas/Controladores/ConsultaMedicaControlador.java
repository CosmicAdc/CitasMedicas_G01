package com.IngSoftGrupo1.CitasMedicas.Controladores;

import com.IngSoftGrupo1.CitasMedicas.Modelos.ConsultaMedica;
import com.IngSoftGrupo1.CitasMedicas.Servicios.ConsultaMedicaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/citas/consultamedica")
public class ConsultaMedicaControlador {

    private final ConsultaMedicaService consultamedicaservice;

    @Autowired
    public ConsultaMedicaControlador(ConsultaMedicaService consultamedicaservice) {
        this.consultamedicaservice = consultamedicaservice;
    }

    @Operation(summary = "Obtener todas las consultas médicas", description = "Obtiene una lista de todas las consultas médicas.")
    @GetMapping
    public ResponseEntity<List<ConsultaMedica>> getAllConsultaMedica() {
        List<ConsultaMedica> consultas = consultamedicaservice.getAllConsultas();
        return ResponseEntity.ok(consultas);
    }

    @Operation(summary = "Obtener una consulta médica por ID", description = "Obtiene una consulta médica por su ID.")
    @GetMapping("/{id}")
    public ResponseEntity<ConsultaMedica> getConsultaMedicaById(@PathVariable long id) {
        Optional<ConsultaMedica> consulta = consultamedicaservice.getConsultaMedicaById(id);
        return consulta.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Crear una nueva consulta médica", description = "Crea una nueva consulta médica.")
    @PostMapping
    public ResponseEntity<ConsultaMedica> createConsultaMedica(@RequestBody ConsultaMedica consultamedica) {
        ConsultaMedica nuevoConsulta = consultamedicaservice.createConsultaMedica(consultamedica);
        return ResponseEntity.ok(nuevoConsulta);
    }

    @Operation(summary = "Actualizar una consulta médica por ID", description = "Actualiza una consulta médica existente por su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Consulta médica actualizada"),
            @ApiResponse(responseCode = "404", description = "Consulta médica no encontrada")
    })
    @PutMapping("/{id}")
    public ResponseEntity<ConsultaMedica> updateConsultaMedica(@PathVariable long id, @RequestBody ConsultaMedica consultamedicaDetails) {
        try {
            ConsultaMedica updatedConsulta = consultamedicaservice.updateConsultaMedica(id, consultamedicaDetails);
            return ResponseEntity.ok(updatedConsulta);
        } catch (IllegalStateException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Eliminar una consulta médica por ID", description = "Elimina una consulta médica por su ID.")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteConsultaMedica(@PathVariable long id) {
        consultamedicaservice.deleteConsultaMedica(id);
        return ResponseEntity.ok().build();
    }
}
