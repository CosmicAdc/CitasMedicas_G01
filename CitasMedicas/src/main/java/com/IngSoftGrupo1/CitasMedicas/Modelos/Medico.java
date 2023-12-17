package com.IngSoftGrupo1.CitasMedicas.Modelos;

import java.sql.Timestamp;
import jakarta.persistence.*;

@Entity
@Table(name = "medicos")
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "especializacion")
    private String especializacion;

    @Column(name = "sexo")
    private String sexo;

    @Column(name = "direccion")
    private String direccion;

    @Column(name = "correo")
    private String correo;

    @Column(name = "turnoInicio", columnDefinition = "TIMESTAMP")
    private Timestamp turnoInicio;

    @Column(name = "turnoFin", columnDefinition = "TIMESTAMP")
    private Timestamp turnoFin;

    // Relación con la entidad Usuarios
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usu_id", referencedColumnName = "id")
    private Usuarios usuario;

    // Getters y setters

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEspecializacion() {
        return especializacion;
    }

    public void setEspecializacion(String especializacion) {
        this.especializacion = especializacion;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Timestamp getTurnoInicio() {
        return turnoInicio;
    }

    public void setTurnoInicio(Timestamp turnoInicio) {
        this.turnoInicio = turnoInicio;
    }

    public Timestamp getTurnoFin() {
        return turnoFin;
    }

    public void setTurnoFin(Timestamp turnoFin) {
        this.turnoFin = turnoFin;
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "Medico{" +
                "id=" + id +
                ", especializacion='" + especializacion + '\'' +
                ", sexo='" + sexo + '\'' +
                ", direccion='" + direccion + '\'' +
                ", correo='" + correo + '\'' +
                ", turnoInicio=" + turnoInicio +
                ", turnoFin=" + turnoFin +
                ", usuario=" + (usuario != null ? usuario.getId() : null) +
                '}';
    }
}
