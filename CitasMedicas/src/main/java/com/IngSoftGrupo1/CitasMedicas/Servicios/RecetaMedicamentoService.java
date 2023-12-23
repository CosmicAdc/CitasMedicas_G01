package com.IngSoftGrupo1.CitasMedicas.Servicios;

import com.IngSoftGrupo1.CitasMedicas.Modelos.RecetaMedicamento;
import com.IngSoftGrupo1.CitasMedicas.Repositorios.RecetaMedicamentoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecetaMedicamentoService {

    private final RecetaMedicamentoRepositorio repositorio;

    @Autowired
    public RecetaMedicamentoService(RecetaMedicamentoRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    public RecetaMedicamento crear(RecetaMedicamento recetaMedicamento) {
        return repositorio.save(recetaMedicamento);
    }

    public Optional<RecetaMedicamento> encontrarPorId(Long id) {
        return repositorio.findById(id);
    }

    public List<RecetaMedicamento> listarTodos() {
        return repositorio.findAll();
    }

    public RecetaMedicamento actualizar(RecetaMedicamento recetaMedicamento) {
        return repositorio.save(recetaMedicamento);
    }

    public void eliminar(Long id) {
        repositorio.deleteById(id);
    }
}
