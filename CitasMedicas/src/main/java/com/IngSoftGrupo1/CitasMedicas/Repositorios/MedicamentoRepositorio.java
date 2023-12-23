package com.IngSoftGrupo1.CitasMedicas.Repositorios;

import com.IngSoftGrupo1.CitasMedicas.Modelos.Medicamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicamentoRepositorio extends JpaRepository<Medicamento, Long> {
    // Spring Data JPA proporciona automáticamente los métodos CRUD
}
