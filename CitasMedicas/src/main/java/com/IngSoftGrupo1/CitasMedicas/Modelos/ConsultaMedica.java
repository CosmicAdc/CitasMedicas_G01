package com.IngSoftGrupo1.CitasMedicas.Modelos;


import jakarta.persistence.*;


@Entity
@Table(name = "consultas_medicas")
public class ConsultaMedica {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id; 

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "citamedica_id", referencedColumnName = "id")
    private CitaMedica citamedica;

    //@ManyToOne(fetch = FetchType.EAGER)
   // @JoinColumn(name = "medicina_id", referencedColumnName = "id")
    //private Medicina medicina;
 
    // Constructor vacío
    public ConsultaMedica() {
    }

    // Constructor con todos los parámetros
    public ConsultaMedica(long id, CitaMedica citamedica) {
        this.id = id;
        this.citamedica = citamedica;
        //this.medicina = medicina;
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
    
	@Override
    public String toString() {
        return "ConsultaMedica{" +
                "consultaId=" + id +
                ", citamedica=" + (citamedica != null ? citamedica.getCitaId() : null) +
                '}';
    }
}
