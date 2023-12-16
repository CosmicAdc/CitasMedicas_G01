package com.IngSoftGrupo1.CitasMedicas.Servicios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.IngSoftGrupo1.CitasMedicas.Modelos.Usuarios;
import com.IngSoftGrupo1.CitasMedicas.Repositorios.UsuarioRepositorio;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
	
	private final UsuarioRepositorio usuariosRepositorio;

   
	@Autowired
    public UsuarioService(UsuarioRepositorio usuariosRepositorio) {
		this.usuariosRepositorio = usuariosRepositorio;
	}
	

	public List<Usuarios> getAllUsuarios() {
        return usuariosRepositorio.findAll();
    }

    public Optional<Usuarios> getUsuarioById(long id) {
        return usuariosRepositorio.findById(id);
    }

    public Usuarios createUsuario(Usuarios usuario) {
        return usuariosRepositorio.save(usuario);
    }

    public Usuarios updateUsuario(long id, Usuarios usuario) {
        if (usuariosRepositorio.existsById(id)) {
            usuario.setId(id);
            return usuariosRepositorio.save(usuario);
        }
        return null; 
    }

    public void deleteUsuario(long id) {
    	usuariosRepositorio.deleteById(id);
    }
    
    public Optional<Usuarios> autenticarUsuario(String correo, String contrasenia) {
        return usuariosRepositorio.findByCorreoAndContrasenia(correo, contrasenia);
    }
}