package com.IngSoftGrupo1.CitasMedicas.Controladores;

import com.IngSoftGrupo1.CitasMedicas.Modelos.Medicamento;
import com.IngSoftGrupo1.CitasMedicas.Servicios.MedicamentoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medicamentos")
public class MedicamentoControlador {

    private final MedicamentoService medicamentoService;

    @Autowired
    public MedicamentoControlador(MedicamentoService medicamentoService) {
        this.medicamentoService = medicamentoService;
    }

    @Operation(summary = "Crear un nuevo medicamento", description = "Crea un nuevo medicamento.")
    @PostMapping
    public ResponseEntity<Medicamento> crear(@RequestBody Medicamento medicamento) {
        Medicamento nuevoMedicamento = medicamentoService.crear(medicamento);
        return ResponseEntity.ok(nuevoMedicamento);
    }

    @Operation(summary = "Obtener un medicamento por ID", description = "Obtiene un medicamento por su ID.")
    @GetMapping("/{id}")
    public ResponseEntity<Medicamento> obtenerPorId(@PathVariable Long id) {
        return medicamentoService.encontrarPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Listar todos los medicamentos", description = "Obtiene una lista de todos los medicamentos.")
    @GetMapping
    public List<Medicamento> listarTodos() {
        return medicamentoService.listarTodos();
    }

    @Operation(summary = "Actualizar un medicamento por ID", description = "Actualiza un medicamento existente por su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Medicamento actualizado"),
            @ApiResponse(responseCode = "404", description = "Medicamento no encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Medicamento> actualizar(@PathVariable Long id, @RequestBody Medicamento medicamento) {
        return medicamentoService.encontrarPorId(id)
                .map(medicamentoExistente -> {
                    medicamento.setMedica_id(id);
                    Medicamento medicamentoActualizado = medicamentoService.actualizar(medicamento);
                    return ResponseEntity.ok(medicamentoActualizado);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Eliminar un medicamento por ID", description = "Elimina un medicamento por su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Medicamento eliminado"),
            @ApiResponse(responseCode = "404", description = "Medicamento no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        return medicamentoService.encontrarPorId(id)
                .map(medicamento -> {
                    medicamentoService.eliminar(id);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
