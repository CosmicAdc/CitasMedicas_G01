package com.IngSoftGrupo1.CitasMedicas.Controladores;

import com.IngSoftGrupo1.CitasMedicas.Modelos.Medicamento;
import com.IngSoftGrupo1.CitasMedicas.Servicios.MedicamentoService;
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

    @PostMapping
    public ResponseEntity<Medicamento> crear(@RequestBody Medicamento medicamento) {
        Medicamento nuevoMedicamento = medicamentoService.crear(medicamento);
        return ResponseEntity.ok(nuevoMedicamento);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Medicamento> obtenerPorId(@PathVariable Long id) {
        return medicamentoService.encontrarPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<Medicamento> listarTodos() {
        return medicamentoService.listarTodos();
    }

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
