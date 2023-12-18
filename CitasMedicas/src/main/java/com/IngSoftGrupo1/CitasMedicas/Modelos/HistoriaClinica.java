package com.IngSoftGrupo1.CitasMedicas.Modelos;

import java.sql.Timestamp;

import jakarta.persistence.*;

@Entity
@Table(name = "historias_medicas")
public class HistoriaClinica {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id; 
	
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "paciente_id", referencedColumnName = "id")
    private Usuarios paciente;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "consultamedica_id", referencedColumnName = "id")
    private ConsultaMedica consultamedica;
    
    public HistoriaClinica(int id, Usuarios paciente, ConsultaMedica consultamedica) {
        this.id = id;
        this.paciente = paciente;
        this.consultamedica = consultamedica;
    }

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Usuarios getPaciente() {
		return paciente;
	}

	public void setPaciente(Usuarios paciente) {
		this.paciente = paciente;
	}

	public ConsultaMedica getConsultamedica() {
		return consultamedica;
	}

	public void setConsultamedica(ConsultaMedica consultamedica) {
		this.consultamedica = consultamedica;
	}
    
	 @Override
	    public String toString() {
	        return "HistoriaClinica{" +
	                "historiaId=" + id +
	                ", paciente=" + (paciente != null ? paciente.getId() : null) +
	                ", consulta_medica=" + (consultamedica != null ? consultamedica.getId() : null) +
	                '}';
	    }
}
