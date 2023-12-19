package com.IngSoftGrupo1.CitasMedicas.Controladores;

import com.IngSoftGrupo1.CitasMedicas.Modelos.Usuarios;
import com.IngSoftGrupo1.CitasMedicas.Servicios.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @Operation(summary = "Obtener todos los usuarios", description = "Obtiene una lista de todos los usuarios.")
    @GetMapping
    public List<Usuarios> getAllUsuarios() {
        return usuariosService.getAllUsuarios();
    }

    @Operation(summary = "Obtener un usuario por ID", description = "Obtiene un usuario por su ID.")
    @GetMapping("/{id}")
    public Optional<Usuarios> getUsuarioById(@PathVariable long id) {
        return usuariosService.getUsuarioById(id);
    }

    @Operation(summary = "Crear un nuevo usuario", description = "Crea un nuevo usuario.")
    @PostMapping
    public Usuarios createUsuario(@RequestBody Usuarios usuario) {
        return usuariosService.createUsuario(usuario);
    }

    @Operation(summary = "Actualizar un usuario por ID", description = "Actualiza un usuario existente por su ID.")
    @PutMapping("/{id}")
    public Usuarios updateUsuario(@PathVariable long id, @RequestBody Usuarios usuario) {
        return usuariosService.updateUsuario(id, usuario);
    }

    @Operation(summary = "Eliminar un usuario por ID", description = "Elimina un usuario por su ID.")
    @DeleteMapping("/{id}")
    public void deleteUsuario(@PathVariable long id) {
        usuariosService.deleteUsuario(id);
    }

    @Operation(summary = "Iniciar sesión de usuario", description = "Inicia sesión de un usuario con correo y contraseña.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Inicio de sesión exitoso"),
            @ApiResponse(responseCode = "401", description = "Credenciales incorrectas")
    })
    @PostMapping("/login")
    public String loginUsuario(@RequestParam String correo, @RequestParam String contrasenia) {
        Optional<Usuarios> usuarioAutenticado = usuariosService.autenticarUsuario(correo, contrasenia);

        if (usuarioAutenticado.isPresent()) {
            return "Inicio de sesión exitoso para el usuario: " + usuarioAutenticado.get().getNom_usuarios();
        } else {
            return "Credenciales incorrectas. Inicio de sesión fallido.";
        }
    }
}
