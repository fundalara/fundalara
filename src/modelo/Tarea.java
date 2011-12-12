package modelo;

// Generated 11/12/2011 04:17:03 PM by Hibernate Tools 3.4.0.CR1

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Tarea generated by hbm2java
 */
@Entity
@Table(name = "tarea")
public class Tarea implements java.io.Serializable {

	private String codigoTarea;
	private String descripcion;
	private char estatus;
	private String nombre;
	private Set<TareaMantenimiento> tareaMantenimientos = new HashSet<TareaMantenimiento>(
			0);
	private Set<TareaMantenimientoPlanificado> tareaMantenimientoPlanificados = new HashSet<TareaMantenimientoPlanificado>(
			0);

	public Tarea() {
	}

	public Tarea(String codigoTarea, String descripcion, char estatus,
			String nombre) {
		this.codigoTarea = codigoTarea;
		this.descripcion = descripcion;
		this.estatus = estatus;
		this.nombre = nombre;
	}

	public Tarea(String codigoTarea, String descripcion, char estatus,
			String nombre, Set<TareaMantenimiento> tareaMantenimientos,
			Set<TareaMantenimientoPlanificado> tareaMantenimientoPlanificados) {
		this.codigoTarea = codigoTarea;
		this.descripcion = descripcion;
		this.estatus = estatus;
		this.nombre = nombre;
		this.tareaMantenimientos = tareaMantenimientos;
		this.tareaMantenimientoPlanificados = tareaMantenimientoPlanificados;
	}

	@Id
	@Column(name = "codigo_tarea", unique = true, nullable = false)
	public String getCodigoTarea() {
		return this.codigoTarea;
	}

	public void setCodigoTarea(String codigoTarea) {
		this.codigoTarea = codigoTarea;
	}

	@Column(name = "descripcion", nullable = false)
	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Column(name = "estatus", nullable = false, length = 1)
	public char getEstatus() {
		return this.estatus;
	}

	public void setEstatus(char estatus) {
		this.estatus = estatus;
	}

	@Column(name = "nombre", nullable = false)
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tarea")
	public Set<TareaMantenimiento> getTareaMantenimientos() {
		return this.tareaMantenimientos;
	}

	public void setTareaMantenimientos(
			Set<TareaMantenimiento> tareaMantenimientos) {
		this.tareaMantenimientos = tareaMantenimientos;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tarea")
	public Set<TareaMantenimientoPlanificado> getTareaMantenimientoPlanificados() {
		return this.tareaMantenimientoPlanificados;
	}

	public void setTareaMantenimientoPlanificados(
			Set<TareaMantenimientoPlanificado> tareaMantenimientoPlanificados) {
		this.tareaMantenimientoPlanificados = tareaMantenimientoPlanificados;
	}

}
