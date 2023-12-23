package com.IngSoftGrupo1.CitasMedicas.Repositorios;

import com.IngSoftGrupo1.CitasMedicas.Modelos.Receta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecetaRepositorio extends JpaRepository<Receta, Long> {
    // Métodos CRUD básicos ya incluidos
}
