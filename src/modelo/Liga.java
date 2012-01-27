package modelo;

// Generated 27/01/2012 03:27:22 PM by Hibernate Tools 3.4.0.CR1

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * Liga generated by hbm2java
 */
@Entity
@Table(name = "liga", schema = "public")
public class Liga implements java.io.Serializable {

	private int codigoLiga;
	private String nombre;
	private String localidad;
	private char estatus;
	private Set<Competencia> competencias = new HashSet<Competencia>(0);
	private Set<Categoria> categorias = new HashSet<Categoria>(0);

	public Liga() {
	}

	public Liga(int codigoLiga, String nombre, String localidad, char estatus) {
		this.codigoLiga = codigoLiga;
		this.nombre = nombre;
		this.localidad = localidad;
		this.estatus = estatus;
	}

	public Liga(int codigoLiga, String nombre, String localidad, char estatus,
			Set<Competencia> competencias, Set<Categoria> categorias) {
		this.codigoLiga = codigoLiga;
		this.nombre = nombre;
		this.localidad = localidad;
		this.estatus = estatus;
		this.competencias = competencias;
		this.categorias = categorias;
	}

	@Id
	@Column(name = "codigo_liga", unique = true, nullable = false)
	public int getCodigoLiga() {
		return this.codigoLiga;
	}

	public void setCodigoLiga(int codigoLiga) {
		this.codigoLiga = codigoLiga;
	}

	@Column(name = "nombre", nullable = false)
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column(name = "localidad", nullable = false)
	public String getLocalidad() {
		return this.localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	@Column(name = "estatus", nullable = false, length = 1)
	public char getEstatus() {
		return this.estatus;
	}

	public void setEstatus(char estatus) {
		this.estatus = estatus;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "liga_competencia", schema = "public", joinColumns = { @JoinColumn(name = "codigo_liga", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "codigo_competencia", nullable = false, updatable = false) })
	public Set<Competencia> getCompetencias() {
		return this.competencias;
	}

	public void setCompetencias(Set<Competencia> competencias) {
		this.competencias = competencias;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "categoria_liga", schema = "public", joinColumns = { @JoinColumn(name = "codigo_liga", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "codigo_categoria", nullable = false, updatable = false) })
	public Set<Categoria> getCategorias() {
		return this.categorias;
	}

	public void setCategorias(Set<Categoria> categorias) {
		this.categorias = categorias;
	}

}
