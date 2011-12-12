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
 * EstadoCompetencia generated by hbm2java
 */
@Entity
@Table(name = "estado_competencia")
public class EstadoCompetencia implements java.io.Serializable {

	private String codigoEstadoCompetencia;
	private String descripcion;
	private String nombre;
	private Set<Competencia> competencias = new HashSet<Competencia>(0);

	public EstadoCompetencia() {
	}

	public EstadoCompetencia(String codigoEstadoCompetencia,
			String descripcion, String nombre) {
		this.codigoEstadoCompetencia = codigoEstadoCompetencia;
		this.descripcion = descripcion;
		this.nombre = nombre;
	}

	public EstadoCompetencia(String codigoEstadoCompetencia,
			String descripcion, String nombre, Set<Competencia> competencias) {
		this.codigoEstadoCompetencia = codigoEstadoCompetencia;
		this.descripcion = descripcion;
		this.nombre = nombre;
		this.competencias = competencias;
	}

	@Id
	@Column(name = "codigo_estado_competencia", unique = true, nullable = false)
	public String getCodigoEstadoCompetencia() {
		return this.codigoEstadoCompetencia;
	}

	public void setCodigoEstadoCompetencia(String codigoEstadoCompetencia) {
		this.codigoEstadoCompetencia = codigoEstadoCompetencia;
	}

	@Column(name = "descripcion", nullable = false)
	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Column(name = "nombre", nullable = false)
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "estadoCompetencia")
	public Set<Competencia> getCompetencias() {
		return this.competencias;
	}

	public void setCompetencias(Set<Competencia> competencias) {
		this.competencias = competencias;
	}

}
