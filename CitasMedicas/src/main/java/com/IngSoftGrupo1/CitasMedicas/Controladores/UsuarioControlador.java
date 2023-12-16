package com.IngSoftGrupo1.CitasMedicas.Controladores;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.IngSoftGrupo1.CitasMedicas.Modelos.Usuarios;
import com.IngSoftGrupo1.CitasMedicas.Servicios.UsuarioService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/citas/usuarios")
public class UsuarioControlador {
	
	private final UsuarioService usuariosService;
	

    @Autowired
    public UsuarioControlador(UsuarioService usuariosService) {
        this.usuariosService = usuariosService;
    }
    @GetMapping
    public List<Usuarios> getAllUsuarios() {
        return usuariosService.getAllUsuarios();
    }

    @GetMapping("/{id}")
    public Optional<Usuarios> getUsuarioById(@PathVariable long id) {
        return usuariosService.getUsuarioById(id);
    }

    @PostMapping
    public Usuarios createUsuario(@RequestBody Usuarios usuario) {
        return usuariosService.createUsuario(usuario);
    }

    @PutMapping("/{id}")
    public Usuarios updateUsuario(@PathVariable long id, @RequestBody Usuarios usuario) {
        return usuariosService.updateUsuario(id, usuario);
    }

    @DeleteMapping("/{id}")
    public void deleteUsuario(@PathVariable long id) {
        usuariosService.deleteUsuario(id);
    }

}
