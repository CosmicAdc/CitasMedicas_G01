package com.IngSoftGrupo1.CitasMedicas.Repositorios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.IngSoftGrupo1.CitasMedicas.Modelos.Usuarios;
@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuarios, Long> {
	
}
