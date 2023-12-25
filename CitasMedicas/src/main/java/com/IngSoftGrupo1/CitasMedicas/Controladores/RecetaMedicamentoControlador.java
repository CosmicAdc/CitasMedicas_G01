package com.IngSoftGrupo1.CitasMedicas.Controladores;

import com.IngSoftGrupo1.CitasMedicas.Modelos.RecetaMedicamento;
import com.IngSoftGrupo1.CitasMedicas.Servicios.RecetaMedicamentoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recetas-medicamentos")
public class RecetaMedicamentoControlador {

    private final RecetaMedicamentoService recetaMedicamentoService;

    @Autowired
    public RecetaMedicamentoControlador(RecetaMedicamentoService recetaMedicamentoService) {
        this.recetaMedicamentoService = recetaMedicamentoService;
    }

    @Operation(summary = "Crear un nuevo detalle de receta médica", description = "Crea un nuevo detalle de receta médica.")
    @PostMapping
    public ResponseEntity<RecetaMedicamento> crear(@RequestBody RecetaMedicamento recetaMedicamento) {
        RecetaMedicamento nuevoRecetaMedicamento = recetaMedicamentoService.crear(recetaMedicamento);
        return ResponseEntity.ok(nuevoRecetaMedicamento);
    }
    
    @Operation(summary = "Obtener un detalle de receta médica por ID", description = "Obtiene un detalle de receta médica por su ID.")
    @GetMapping("/{id}")
    public ResponseEntity<RecetaMedicamento> obtenerPorId(@PathVariable Long id) {
        return recetaMedicamentoService.encontrarPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Listar todos los detalles de recetas médicas", description = "Obtiene una lista de todos los detalles de recetas médicas.")
    @GetMapping
    public List<RecetaMedicamento> listarTodos() {
        return recetaMedicamentoService.listarTodos();
    }

    @Operation(summary = "Actualizar un detalle de receta médica por ID", description = "Actualiza un detalle de receta médica existente por su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Detalle de receta médica actualizado"),
            @ApiResponse(responseCode = "404", description = "Detalle de receta médica no encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<RecetaMedicamento> actualizar(@PathVariable Long id, @RequestBody RecetaMedicamento recetaMedicamento) {
        return recetaMedicamentoService.encontrarPorId(id)
                .map(recetaMedicamentoExistente -> {
                    recetaMedicamento.setId(id);
                    RecetaMedicamento recetaMedicamentoActualizado = recetaMedicamentoService.actualizar(recetaMedicamento);
                    return ResponseEntity.ok(recetaMedicamentoActualizado);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    @Operation(summary = "Eliminar un detalle de receta médica por ID", description = "Elimina un detalle de receta médica por su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Detalle de receta médica eliminado"),
            @ApiResponse(responseCode = "404", description = "Detalle de receta médica no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        return recetaMedicamentoService.encontrarPorId(id)
                .map(recetaMedicamento -> {
                    recetaMedicamentoService.eliminar(id);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
