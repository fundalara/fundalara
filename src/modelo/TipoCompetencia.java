package modelo;

// Generated 16/12/2011 03:51:27 PM by Hibernate Tools 3.4.0.CR1

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * TipoCompetencia generated by hbm2java
 */
@Entity
@Table(name = "tipo_competencia")
public class TipoCompetencia implements java.io.Serializable {

	private String codigoTipocompetencia;
	private DatoBasico datoBasico;
	private String nombre;
	private String descripcion;
	private Set<DatoBasico> datoBasicos = new HashSet<DatoBasico>(0);
	private Set<Competencia> competencias = new HashSet<Competencia>(0);

	public TipoCompetencia() {
	}

	public TipoCompetencia(String codigoTipocompetencia, DatoBasico datoBasico,
			String nombre, String descripcion) {
		this.codigoTipocompetencia = codigoTipocompetencia;
		this.datoBasico = datoBasico;
		this.nombre = nombre;
		this.descripcion = descripcion;
	}

	public TipoCompetencia(String codigoTipocompetencia, DatoBasico datoBasico,
			String nombre, String descripcion, Set<DatoBasico> datoBasicos,
			Set<Competencia> competencias) {
		this.codigoTipocompetencia = codigoTipocompetencia;
		this.datoBasico = datoBasico;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.datoBasicos = datoBasicos;
		this.competencias = competencias;
	}

	@Id
	@Column(name = "codigo_tipocompetencia", unique = true, nullable = false)
	public String getCodigoTipocompetencia() {
		return this.codigoTipocompetencia;
	}

	public void setCodigoTipocompetencia(String codigoTipocompetencia) {
		this.codigoTipocompetencia = codigoTipocompetencia;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codigo_modalidad_competencia", nullable = false)
	public DatoBasico getDatoBasico() {
		return this.datoBasico;
	}

	public void setDatoBasico(DatoBasico datoBasico) {
		this.datoBasico = datoBasico;
	}

	@Column(name = "nombre", nullable = false)
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column(name = "descripcion", nullable = false)
	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "condicion_competencia", joinColumns = { @JoinColumn(name = "codigo_tipo_competencia_condicion", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "codigo_condicion", nullable = false, updatable = false) })
	public Set<DatoBasico> getDatoBasicos() {
		return this.datoBasicos;
	}

	public void setDatoBasicos(Set<DatoBasico> datoBasicos) {
		this.datoBasicos = datoBasicos;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tipoCompetencia")
	public Set<Competencia> getCompetencias() {
		return this.competencias;
	}

	public void setCompetencias(Set<Competencia> competencias) {
		this.competencias = competencias;
	}

}