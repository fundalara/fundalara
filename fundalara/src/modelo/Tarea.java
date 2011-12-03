package modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the tarea database table.
 * 
 */
@Entity
public class Tarea implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="codigo_tarea")
	private String codigoTarea;

	private String descripcion;

	private String estatus;

	private String nombre;

	//bi-directional many-to-one association to TareaMantenimiento
	@OneToMany(mappedBy="tarea")
	private Set<TareaMantenimiento> tareaMantenimientos;

	//bi-directional many-to-one association to TareaMantenimientoPlanificada
	@OneToMany(mappedBy="tarea")
	private Set<TareaMantenimientoPlanificada> tareaMantenimientoPlanificadas;

    public Tarea() {
    }

	public String getCodigoTarea() {
		return this.codigoTarea;
	}

	public void setCodigoTarea(String codigoTarea) {
		this.codigoTarea = codigoTarea;
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

	public Set<TareaMantenimiento> getTareaMantenimientos() {
		return this.tareaMantenimientos;
	}

	public void setTareaMantenimientos(Set<TareaMantenimiento> tareaMantenimientos) {
		this.tareaMantenimientos = tareaMantenimientos;
	}
	
	public Set<TareaMantenimientoPlanificada> getTareaMantenimientoPlanificadas() {
		return this.tareaMantenimientoPlanificadas;
	}

	public void setTareaMantenimientoPlanificadas(Set<TareaMantenimientoPlanificada> tareaMantenimientoPlanificadas) {
		this.tareaMantenimientoPlanificadas = tareaMantenimientoPlanificadas;
	}
	
}