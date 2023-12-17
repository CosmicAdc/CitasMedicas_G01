package com.IngSoftGrupo1.CitasMedicas.Repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.IngSoftGrupo1.CitasMedicas.Modelos.Medico;
import com.IngSoftGrupo1.CitasMedicas.Modelos.Usuarios;

@Repository
public interface MedicoRepositorio extends JpaRepository<Medico, Long> {
    
    // Buscar médicos por su especialización
    List<Medico> findByEspecializacion(String especializacion);
    

    // Buscar médicos por usuario ID (mediante la relación con Usuarios)
    List<Medico> findByUsuario_Id(long usuId);
    
    // Otros métodos personalizados que necesites
}
