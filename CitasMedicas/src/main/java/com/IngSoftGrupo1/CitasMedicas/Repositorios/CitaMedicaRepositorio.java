package com.IngSoftGrupo1.CitasMedicas.Repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.IngSoftGrupo1.CitasMedicas.Modelos.CitaMedica;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface CitaMedicaRepositorio extends JpaRepository<CitaMedica, Long> {
    
    // Encuentra todas las citas para un paciente específico por su ID
    List<CitaMedica> findByPaciente_Id(long pacienteId);
    
    // Encuentra todas las citas para un médico específico por su ID
    List<CitaMedica> findByMedico_Id(long medicoId); // Usando medi_id como está en la entidad
    
    List<CitaMedica> findByFechaBetween(Timestamp start, Timestamp end);

    
}
