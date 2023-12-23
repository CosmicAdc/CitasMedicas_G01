package com.IngSoftGrupo1.CitasMedicas.Servicios;

import com.IngSoftGrupo1.CitasMedicas.Modelos.Medicamento;
import com.IngSoftGrupo1.CitasMedicas.Repositorios.MedicamentoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicamentoService {

    private final MedicamentoRepositorio medicamentoRepositorio;

    @Autowired
    public MedicamentoService(MedicamentoRepositorio medicamentoRepositorio) {
        this.medicamentoRepositorio = medicamentoRepositorio;
    }

    public Medicamento crear(Medicamento medicamento) {
        return medicamentoRepositorio.save(medicamento);
    }

    public Optional<Medicamento> encontrarPorId(Long id) {
        return medicamentoRepositorio.findById(id);
    }

    public List<Medicamento> listarTodos() {
        return medicamentoRepositorio.findAll();
    }

    public Medicamento actualizar(Medicamento medicamento) {
        return medicamentoRepositorio.save(medicamento);
    }

    public void eliminar(Long id) {
        medicamentoRepositorio.deleteById(id);
    }
}
