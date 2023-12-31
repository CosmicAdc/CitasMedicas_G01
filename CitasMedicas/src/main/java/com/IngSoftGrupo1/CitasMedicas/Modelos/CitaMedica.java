package com.IngSoftGrupo1.CitasMedicas.Modelos;

import java.sql.Timestamp;
import jakarta.persistence.*;

@Entity
@Table(name = "citas_medicas")
public class CitaMedica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id; // Actualizado a camelCase

    @Column(name = "fecha", columnDefinition = "TIMESTAMP")
    private Timestamp fecha;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "paciente_id", referencedColumnName = "id")
    private Usuarios paciente;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "medico_id", referencedColumnName = "id")
    private Medico medico;
 
    // Constructor vacío
    public CitaMedica() {
    }

    // Constructor con todos los parámetros
    public CitaMedica(long id, Timestamp fecha, Usuarios paciente, Medico medico) {
        this.id = id;
        this.fecha = fecha;
        this.paciente = paciente;
        this.medico = medico;
    }

    // Getters y setters
    public long getCitaId() {
        return id;
    }

    public void setCitaId(long id) {
        this.id = id;
    }

    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }

    public Usuarios getPaciente() {
        return paciente;
    }

    public void setPaciente(Usuarios paciente) {
        this.paciente = paciente;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    // ToString
    @Override
    public String toString() {
        return "CitaMedica{" +
                "citaId=" + id +
                ", fecha=" + fecha +
                ", paciente=" + (paciente != null ? paciente.getId() : null) +
                ", medico=" + (medico != null ? medico.getId() : null) +
                '}';
    }
}
