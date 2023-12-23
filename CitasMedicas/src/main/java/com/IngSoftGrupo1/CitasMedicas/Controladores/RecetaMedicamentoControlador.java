package com.IngSoftGrupo1.CitasMedicas.Controladores;

import com.IngSoftGrupo1.CitasMedicas.Modelos.RecetaMedicamento;
import com.IngSoftGrupo1.CitasMedicas.Servicios.RecetaMedicamentoService;
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

    @PostMapping
    public ResponseEntity<RecetaMedicamento> crear(@RequestBody RecetaMedicamento recetaMedicamento) {
        RecetaMedicamento nuevoRecetaMedicamento = recetaMedicamentoService.crear(recetaMedicamento);
        return ResponseEntity.ok(nuevoRecetaMedicamento);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecetaMedicamento> obtenerPorId(@PathVariable Long id) {
        return recetaMedicamentoService.encontrarPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<RecetaMedicamento> listarTodos() {
        return recetaMedicamentoService.listarTodos();
    }

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
