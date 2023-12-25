package com.IngSoftGrupo1.CitasMedicas.Test;

import com.IngSoftGrupo1.CitasMedicas.Modelos.Usuarios;
import com.IngSoftGrupo1.CitasMedicas.Repositorios.UsuarioRepositorio;
import com.IngSoftGrupo1.CitasMedicas.Servicios.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;


class UsuariosTest {
	
	@InjectMocks
    private UsuarioService usuarioService;
	
	@Mock
	private UsuarioRepositorio usuarioRepositorio;
	
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

	
	@Test
	void testGetAllUsuarios() {
    
        when(usuarioRepositorio.findAll()).thenReturn(Arrays.asList(
                new Usuarios(1L, "Usuario1", "Apellido1", 1, "NomUsuario1", "Cedula1", "Contraseña1", "Telefono1", "Correo1", "Direccion1"),
                new Usuarios(2L, "Usuario2", "Apellido2", 2, "NomUsuario2", "Cedula2", "Contraseña2", "Telefono2", "Correo2", "Direccion2")
        ));

        List<Usuarios> usuarios = usuarioService.getAllUsuarios();

        assertEquals(2, usuarios.size());

        Usuarios usuario1 = usuarios.get(0);
        assertEquals(1L, usuario1.getId());
        assertEquals("Usuario1", usuario1.getNombre());
        assertEquals("Apellido1", usuario1.getApellido());
        assertEquals(1, usuario1.getRol());
        assertEquals("NomUsuario1", usuario1.getNom_usuarios());
        assertEquals("Cedula1", usuario1.getCedula());
        assertEquals("Contraseña1", usuario1.getContrasenia());
        assertEquals("Telefono1", usuario1.getTelefono());
        assertEquals("Correo1", usuario1.getCorreo());
        assertEquals("Direccion1", usuario1.getDireccion());
    }

    @Test
    void testGetUsuarioById() {
        when(usuarioRepositorio.findById(40L)).thenReturn(Optional.of(new Usuarios(40L, "Usuario1", "Apellido1", 1, "NomUsuario1", "Cedula1", "Contraseña1", "Telefono1", "Correo1", "Direccion1")));

        Optional<Usuarios> usuario = usuarioService.getUsuarioById(40L);

        assertTrue(usuario.isPresent());
        assertEquals("Usuario1", usuario.get().getNombre());
    }

    @Test
    void testCreateUsuario() {
        Usuarios usuarioNuevo = new Usuarios(3L, "NuevoUsuario", "ApellidoNuevo", 1, "NuevoNomUsuario", "NuevaCedula", "NuevaContraseña", "NuevoTelefono", "NuevoCorreo", "NuevaDireccion");
        when(usuarioRepositorio.save(usuarioNuevo)).thenReturn(usuarioNuevo);

        Usuarios usuarioCreado = usuarioService.createUsuario(usuarioNuevo);

        assertEquals("NuevoUsuario", usuarioCreado.getNombre());
    }

    @Test
    void testUpdateUsuarioExitoso() {
        Usuarios usuarioExistente = new Usuarios(1L, "UsuarioExistente", "ApellidoExistente", 1, "NomUsuarioExistente", "CedulaExistente", "ContraseñaExistente", "TelefonoExistente", "CorreoExistente", "DireccionExistente");

        when(usuarioRepositorio.existsById(1L)).thenReturn(true);
        when(usuarioRepositorio.save(any(Usuarios.class))).thenReturn(usuarioExistente);

        Usuarios usuarioActualizado = usuarioService.updateUsuario(1L, usuarioExistente);

        assertEquals("UsuarioExistente", usuarioActualizado.getNombre());
    }
    
    @Test
    void testUpdateUsuarioNoExistente() {
        Usuarios usuarioNoExistente = new Usuarios(1L, "UsuarioNoExistente", "ApellidoNoExistente", 1, "NomUsuarioNoExistente", "CedulaNoExistente", "ContraseñaNoExistente", "TelefonoNoExistente", "CorreoNoExistente", "DireccionNoExistente");

        when(usuarioRepositorio.existsById(1L)).thenReturn(false); 

        Usuarios usuarioActualizado = usuarioService.updateUsuario(1L, usuarioNoExistente);

        assertNull(usuarioActualizado);
    }
    

    @Test
    void testDeleteUsuario() {
        doNothing().when(usuarioRepositorio).deleteById(1L);

        usuarioService.deleteUsuario(1L);

        verify(usuarioRepositorio, times(1)).deleteById(1L);
    }


    @Test
    void testAutenticarUsuarioUsuarioNoExiste() {
        when(usuarioRepositorio.findByCorreoAndContrasenia("correoNoExistente", "contrasenia")).thenReturn(Optional.empty());

        Optional<Usuarios> usuarioAutenticado = usuarioService.autenticarUsuario("correoNoExistente", "contrasenia");

        assertFalse(usuarioAutenticado.isPresent());
    }
    
    @Test
    void testAutenticarUsuarioUsuarioExistenteContraseniaCorrecta() {
        Usuarios usuarioExistente = new Usuarios();
        usuarioExistente.setCorreo("correo");
        usuarioExistente.setContrasenia("contrasenia");

        when(usuarioRepositorio.findByCorreoAndContrasenia("correo", "contrasenia")).thenReturn(Optional.of(usuarioExistente));

        Optional<Usuarios> usuarioAutenticado = usuarioService.autenticarUsuario("correo", "contrasenia");

        assertTrue(usuarioAutenticado.isPresent());
        assertEquals("correo", usuarioAutenticado.get().getCorreo());
        assertEquals("contrasenia", usuarioAutenticado.get().getContrasenia());
    }




}
