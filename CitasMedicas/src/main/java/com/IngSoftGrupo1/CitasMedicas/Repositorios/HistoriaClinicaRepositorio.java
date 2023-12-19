package com.IngSoftGrupo1.CitasMedicas.Repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.IngSoftGrupo1.CitasMedicas.Modelos.HistoriaClinica;

@Repository
public interface HistoriaClinicaRepositorio extends JpaRepository<HistoriaClinica, Long>{


	//Buscar la historia medica por su Id
		List<HistoriaClinica> findByid(long historiaId);
}
