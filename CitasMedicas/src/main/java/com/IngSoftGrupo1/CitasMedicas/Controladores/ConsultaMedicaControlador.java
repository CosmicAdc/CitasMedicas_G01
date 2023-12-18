package com.IngSoftGrupo1.CitasMedicas.Controladores;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.IngSoftGrupo1.CitasMedicas.Modelos.ConsultaMedica;
import com.IngSoftGrupo1.CitasMedicas.Modelos.Medico;
import com.IngSoftGrupo1.CitasMedicas.Servicios.ConsultaMedicaService;
import com.IngSoftGrupo1.CitasMedicas.Servicios.MedicoService;

@RestController
@RequestMapping("/citas/consultamedica")
public class ConsultaMedicaControlador {
	
	 private final ConsultaMedicaService consultamedicaservice;

	    @Autowired
	    public ConsultaMedicaControlador(ConsultaMedicaService consultamedicaservice) {
	        this.consultamedicaservice = consultamedicaservice;
	    }

	    @Transactional(readOnly = true)
	    @GetMapping
	    public ResponseEntity<List<ConsultaMedica>> getAllConsultaMedica() {
	        List<ConsultaMedica> consultas = consultamedicaservice.getAllConsultas();
	        return ResponseEntity.ok(consultas);
	    }

	    @Transactional(readOnly = true)
	    @GetMapping("/{id}")
	    public ResponseEntity<ConsultaMedica> getConsultaMedicaById(@PathVariable long id) {
	        Optional<ConsultaMedica> consulta = consultamedicaservice.getConsultaMedicaById(id);
	        return consulta.map(ResponseEntity::ok)
	                     .orElseGet(() -> ResponseEntity.notFound().build());
	    }

	    @PostMapping
	    public ResponseEntity<ConsultaMedica> createConsultaMedica(@RequestBody ConsultaMedica consultamedica) {
	    	ConsultaMedica nuevoConsulta = consultamedicaservice.saveConsultaMedica(consultamedica);
	        return ResponseEntity.ok(nuevoConsulta);
	    }

	    @PutMapping("/{id}")
	    public ResponseEntity<ConsultaMedica> updateConsultaMedica(@PathVariable long id, @RequestBody ConsultaMedica consultamedicaDetails) {
	        try {
	        	ConsultaMedica updatedConsulta = consultamedicaservice.updateConsultaMedica(id, consultamedicaDetails);
	            return ResponseEntity.ok(updatedConsulta);
	        } catch (IllegalStateException e) {
	            return ResponseEntity.notFound().build();
	        }
	    }

	    @DeleteMapping("/{id}")
	    public ResponseEntity<?> deleteConsultaMedica(@PathVariable long id) {
	    	consultamedicaservice.deleteConsultaMedica(id);
	        return ResponseEntity.ok().build();
	    }
}
