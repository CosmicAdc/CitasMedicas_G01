package com.IngSoftGrupo1.CitasMedicas.Controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.IngSoftGrupo1.CitasMedicas.Modelos.Medico;
import com.IngSoftGrupo1.CitasMedicas.Servicios.MedicoService;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional(readOnly = true)
    @GetMapping
    public ResponseEntity<List<Medico>> getAllMedicos() {
        List<Medico> medicos = medicoService.getAllMedicos();
        return ResponseEntity.ok(medicos);
    }

    @Transactional(readOnly = true)
    @GetMapping("/{id}")
    public ResponseEntity<Medico> getMedicoById(@PathVariable long id) {
        Optional<Medico> medico = medicoService.getMedicoById(id);
        return medico.map(ResponseEntity::ok)
                     .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Medico> createMedico(@RequestBody Medico medico) {
        Medico nuevoMedico = medicoService.saveMedico(medico);
        return ResponseEntity.ok(nuevoMedico);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Medico> updateMedico(@PathVariable long id, @RequestBody Medico medicoDetails) {
        try {
            Medico updatedMedico = medicoService.updateMedico(id, medicoDetails);
            return ResponseEntity.ok(updatedMedico);
        } catch (IllegalStateException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMedico(@PathVariable long id) {
        medicoService.deleteMedico(id);
        return ResponseEntity.ok().build();
    }
}
