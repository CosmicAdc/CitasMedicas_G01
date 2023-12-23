package com.IngSoftGrupo1.CitasMedicas.Controladores;

import com.IngSoftGrupo1.CitasMedicas.Modelos.Receta;
import com.IngSoftGrupo1.CitasMedicas.Servicios.RecetaService;
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

    @PostMapping
    public ResponseEntity<Receta> crear(@RequestBody Receta receta) {
        Receta nuevaReceta = recetaService.crear(receta);
        return ResponseEntity.ok(nuevaReceta);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Receta> obtenerPorId(@PathVariable Long id) {
        return recetaService.encontrarPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<Receta> listarTodos() {
        return recetaService.listarTodos();
    }

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
