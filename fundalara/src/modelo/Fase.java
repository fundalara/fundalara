package modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the fase database table.
 * 
 */
@Entity
public class Fase implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="cod_fase_entrenamiento")
	private String codFaseEntrenamiento;

	private String descripcion;

	private String estatus;

	private String nombre;

	//bi-directional many-to-one association to ActividadEntrenamiento
	@OneToMany(mappedBy="fase")
	private Set<ActividadEntrenamiento> actividadEntrenamientos;

    public Fase() {
    }

	public String getCodFaseEntrenamiento() {
		return this.codFaseEntrenamiento;
	}

	public void setCodFaseEntrenamiento(String codFaseEntrenamiento) {
		this.codFaseEntrenamiento = codFaseEntrenamiento;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getEstatus() {
		return this.estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Set<ActividadEntrenamiento> getActividadEntrenamientos() {
		return this.actividadEntrenamientos;
	}

	public void setActividadEntrenamientos(Set<ActividadEntrenamiento> actividadEntrenamientos) {
		this.actividadEntrenamientos = actividadEntrenamientos;
	}
	
}