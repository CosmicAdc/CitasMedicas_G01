package com.IngSoftGrupo1.CitasMedicas.Controladores;

import com.IngSoftGrupo1.CitasMedicas.Modelos.Receta;
import com.IngSoftGrupo1.CitasMedicas.Servicios.RecetaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recetas")
public class RecetaControlador {

    private final RecetaService recetaService;

    @Autowired
    public RecetaControlador(RecetaService recetaService) {
        this.recetaService = recetaService;
    }

    @Operation(summary = "Crear una nueva receta", description = "Crea una nueva receta.")
    @PostMapping
    public ResponseEntity<Receta> crear(@RequestBody Receta receta) {
        Receta nuevaReceta = recetaService.crear(receta);
        return ResponseEntity.ok(nuevaReceta);
    }

    @Operation(summary = "Obtener una receta por ID", description = "Obtiene una receta por su ID.")
    @GetMapping("/{id}")
    public ResponseEntity<Receta> obtenerPorId(@PathVariable Long id) {
        return recetaService.encontrarPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Listar todas las recetas", description = "Obtiene una lista de todas las recetas.")
    @GetMapping
    public List<Receta> listarTodos() {
        return recetaService.listarTodos();
    }

    @Operation(summary = "Actualizar una receta por ID", description = "Actualiza una receta existente por su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Receta actualizada"),
            @ApiResponse(responseCode = "404", description = "Receta no encontrada")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Receta> actualizar(@PathVariable Long id, @RequestBody Receta receta) {
        return recetaService.encontrarPorId(id)
                .map(recetaExistente -> {
                    receta.setRe_id(id);
                    Receta recetaActualizada = recetaService.actualizar(receta);
                    return ResponseEntity.ok(recetaActualizada);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Eliminar una receta por ID", description = "Elimina una receta por su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Receta eliminada"),
            @ApiResponse(responseCode = "404", description = "Receta no encontrada")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        return recetaService.encontrarPorId(id)
                .map(receta -> {
                    recetaService.eliminar(id);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
