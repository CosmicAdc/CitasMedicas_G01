package com.IngSoftGrupo1.CitasMedicas.Modelos;


import jakarta.persistence.*;


@Entity
@Table(name = "consultas_medicas")
public class ConsultaMedica {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id; 

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "citamedica_id")
    private CitaMedica citamedica;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "re_id")
    private Receta receta;
 
    @Column(name = "diagnostico")
    private String diagnostico;

    
    
    public ConsultaMedica() {
    	
    }
    // Constructor con todos los parámetros
    public ConsultaMedica(long id, CitaMedica citamedica, Receta receta, String diagnostico) {
        this.id = id;
        this.citamedica = citamedica;
        this.receta = receta;
        this.diagnostico = diagnostico;
    }



	public long getId() {
		return id;
	}



	public void setId(long id) {
		this.id = id;
	}



	public CitaMedica getCitamedica() {
		return citamedica;
	}



	public void setCitamedica(CitaMedica citamedica) {
		this.citamedica = citamedica;
	}



	public Receta getReceta() {
		return receta;
	}



	public void setReceta(Receta receta) {
		this.receta = receta;
	}



	public String getDiagnostico() {
		return diagnostico;
	}



	public void setDiagnostico(String diagnostico) {
		this.diagnostico = diagnostico;
	}



	@Override
    public String toString() {
        return "ConsultaMedica{" +
                "consultaId=" + id +
                ", citamedica=" + (citamedica != null ? citamedica.getCitaId() : null) +
                ", receta=" + (receta != null ? receta.getRe_id() : null) +
                ", diagnostico=" + diagnostico +
                '}';
    }
}
