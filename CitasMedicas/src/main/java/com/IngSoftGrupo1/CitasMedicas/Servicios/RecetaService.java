package com.IngSoftGrupo1.CitasMedicas.Servicios;

import com.IngSoftGrupo1.CitasMedicas.Modelos.Receta;
import com.IngSoftGrupo1.CitasMedicas.Repositorios.RecetaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class RecetaService {

    private final RecetaRepositorio recetaRepositorio;

    @Autowired
    public RecetaService(RecetaRepositorio recetaRepositorio) {
        this.recetaRepositorio = recetaRepositorio;
    }

    public Receta crear(Receta receta) {
        return recetaRepositorio.save(receta);
    }

    public Optional<Receta> encontrarPorId(Long id) {
        return recetaRepositorio.findById(id);
    }

    public List<Receta> listarTodos() {
        return recetaRepositorio.findAll();
    }

    public Receta actualizar(Receta receta) {
        return recetaRepositorio.save(receta);
    }

    public void eliminar(Long id) {
        recetaRepositorio.deleteById(id);
    }
}
