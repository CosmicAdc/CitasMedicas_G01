package com.IngSoftGrupo1.CitasMedicas.Repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.IngSoftGrupo1.CitasMedicas.Modelos.ConsultaMedica;

@Repository
public interface ConsultaMedicaRepositorio extends JpaRepository<ConsultaMedica, Long>{
	
	//Buscar la consulta medica por su Id
	List<ConsultaMedica> findByid(long consultaId);
}
