package com.IngSoftGrupo1.CitasMedicas.Repositorios;

import com.IngSoftGrupo1.CitasMedicas.Modelos.RecetaMedicamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecetaMedicamentoRepositorio extends JpaRepository<RecetaMedicamento, Long> {
    // Los métodos CRUD básicos ya están incluidos en JpaRepository
}
