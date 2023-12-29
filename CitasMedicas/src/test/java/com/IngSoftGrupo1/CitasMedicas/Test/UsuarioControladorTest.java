package com.IngSoftGrupo1.CitasMedicas.Test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.IngSoftGrupo1.CitasMedicas.Controladores.UsuarioControlador;
import com.IngSoftGrupo1.CitasMedicas.Modelos.Usuarios;
import com.IngSoftGrupo1.CitasMedicas.Servicios.UsuarioService;
import org.mockito.junit.MockitoJUnitRunner;

class UsuarioControladorTest {

	@InjectMocks
	private UsuarioControlador usuarioControlador;

	@Mock
	private UsuarioService usuarioService;

	private MockMvc mockMvc;
	
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(usuarioControlador).build();
    }
	
    @Test
    void testGetAllUsuarios() throws Exception {
        List<Usuarios> usuariosList = Arrays.asList(
                new Usuarios(1L, "Usuario1", "Apellido1", 1, "NomUsuario1", "Cedula1", "Contraseña1", "Telefono1", "Correo1", "Direccion1"),
                new Usuarios(2L, "Usuario2", "Apellido2", 2, "NomUsuario2", "Cedula2", "Contraseña2", "Telefono2", "Correo2", "Direccion2")
        );

        when(usuarioService.getAllUsuarios()).thenReturn(usuariosList);

        mockMvc.perform(MockMvcRequestBuilders.get("/citas/usuarios")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2));

        verify(usuarioService, times(1)).getAllUsuarios();
        verifyNoMoreInteractions(usuarioService);
    }
    
    
    @Test
    void testGetUsuarioById() throws Exception {
        long userId = 1L;
        Usuarios usuario = new Usuarios(userId, "Usuario1", "Apellido1", 1, "NomUsuario1", "Cedula1", "Contraseña1", "Telefono1", "Correo1", "Direccion1");

        when(usuarioService.getUsuarioById(userId)).thenReturn(Optional.of(usuario));

        mockMvc.perform(MockMvcRequestBuilders.get("/citas/usuarios/{id}", userId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(userId));

        verify(usuarioService, times(1)).getUsuarioById(userId);
        verifyNoMoreInteractions(usuarioService);
    }
    
    @Test
    void testCreateUsuario() throws Exception {
        Usuarios usuario = new Usuarios(1L, "Usuario1", "Apellido1", 1, "NomUsuario1", "Cedula1", "Contraseña1", "Telefono1", "Correo1", "Direccion1");

        when(usuarioService.createUsuario(any())).thenReturn(usuario);

        mockMvc.perform(MockMvcRequestBuilders.post("/citas/usuarios")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(usuarioService, times(1)).createUsuario(any());
        verifyNoMoreInteractions(usuarioService);
    }

    @Test
    void testUpdateUsuario() throws Exception {
        long userId = 1L;
        Usuarios usuario = new Usuarios(userId, "Usuario1", "Apellido1", 1, "NomUsuario1", "Cedula1", "Contraseña1", "Telefono1", "Correo1", "Direccion1");

        when(usuarioService.updateUsuario(eq(userId), any())).thenReturn(usuario);

        mockMvc.perform(MockMvcRequestBuilders.put("/citas/usuarios/{id}", userId)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(usuarioService, times(1)).updateUsuario(eq(userId), any());
        verifyNoMoreInteractions(usuarioService);
    }
    

    @Test
    void testDeleteUsuario() throws Exception {
        long userId = 1L;

        mockMvc.perform(MockMvcRequestBuilders.delete("/citas/usuarios/{id}", userId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(usuarioService, times(1)).deleteUsuario(userId);
        verifyNoMoreInteractions(usuarioService);
    }
    
    @Test
    void testLoginUsuario() throws Exception {
        String correo = "test@example.com";
        String contrasenia = "password";

        when(usuarioService.autenticarUsuario(correo, contrasenia)).thenReturn(Optional.of(new Usuarios()));

        mockMvc.perform(MockMvcRequestBuilders.post("/citas/usuarios/login")
                .contentType(MediaType.APPLICATION_JSON)
                .param("correo", correo)
                .param("contrasenia", contrasenia))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(usuarioService, times(1)).autenticarUsuario(correo, contrasenia);
        verifyNoMoreInteractions(usuarioService);
    }
    
    
/*
	@Test
	void testGetUsuarioById() {
		fail("Not yet implemented");
	}

	@Test
	void testCreateUsuario() {
		fail("Not yet implemented");
	}

	@Test
	void testUpdateUsuario() {
		fail("Not yet implemented");
	}

	@Test
	void testDeleteUsuario() {
		fail("Not yet implemented");
	}

	@Test
	void testLoginUsuario() {
		fail("Not yet implemented");
	}*/

}
