package com.IngSoftGrupo1.CitasMedicas.Modelos;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "recetas")
public class Receta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long re_id;

    @Column(name = "re_indicaciones", nullable = false) // Asumiendo que siempre quieres una indicación.
    private String re_indicaciones;
    
	
	public Receta() {
	    	
	    }
	
    
    public Receta(Long re_id, String re_indicaciones) {
		this.re_id = re_id;
		this.re_indicaciones = re_indicaciones;
	}


	// Getters y setters
    public Long getRe_id() {
        return re_id;
    }

    public void setRe_id(Long re_id) {
        this.re_id = re_id;
    }

    public String getRe_indicaciones() {
        return re_indicaciones;
    }

    public void setRe_indicaciones(String re_indicaciones) {
        if (re_indicaciones == null) {
            throw new IllegalArgumentException("Las indicaciones no pueden ser nulas");
        }
        this.re_indicaciones = re_indicaciones;
    }

    // ... otros métodos ...
}
