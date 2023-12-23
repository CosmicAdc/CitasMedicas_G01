package com.IngSoftGrupo1.CitasMedicas.Modelos;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "medicamentos")
public class Medicamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long medica_id;

    @Column(name = "medica_nombre")
    private String medica_nombre;

    // Getters y setters
    public Long getMedica_id() {
        return medica_id;
    }

    public void setMedica_id(Long medica_id) {
        this.medica_id = medica_id;
    }

    public String getMedica_nombre() {
        return medica_nombre;
    }

    public void setMedica_nombre(String medica_nombre) {
        this.medica_nombre = medica_nombre;
    }
}
