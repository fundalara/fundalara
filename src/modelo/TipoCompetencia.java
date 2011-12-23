package modelo;

// Generated Dec 23, 2011 1:26:53 PM by Hibernate Tools 3.4.0.CR1

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * TipoCompetencia generated by hbm2java
 */
@Entity
@Table(name = "tipo_competencia", schema = "public")
public class TipoCompetencia implements java.io.Serializable {

	private int codigoTipoCompetencia;
	private String nombre;
	private String descripcion;
	private int estatus;
	private Set<DatoBasico> datoBasicos = new HashSet<DatoBasico>(0);
	private Set<Competencia> competencias = new HashSet<Competencia>(0);
	private Set<ModalidadCompetencia> modalidadCompetencias = new HashSet<ModalidadCompetencia>(
			0);

	public TipoCompetencia() {
	}

	public TipoCompetencia(int codigoTipoCompetencia, String nombre,
			String descripcion, int estatus) {
		this.codigoTipoCompetencia = codigoTipoCompetencia;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.estatus = estatus;
	}

	public TipoCompetencia(int codigoTipoCompetencia, String nombre,
			String descripcion, int estatus, Set<DatoBasico> datoBasicos,
			Set<Competencia> competencias,
			Set<ModalidadCompetencia> modalidadCompetencias) {
		this.codigoTipoCompetencia = codigoTipoCompetencia;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.estatus = estatus;
		this.datoBasicos = datoBasicos;
		this.competencias = competencias;
		this.modalidadCompetencias = modalidadCompetencias;
	}

	@Id
	@Column(name = "codigo_tipo_competencia", unique = true, nullable = false)
	public int getCodigoTipoCompetencia() {
		return this.codigoTipoCompetencia;
	}

	public void setCodigoTipoCompetencia(int codigoTipoCompetencia) {
		this.codigoTipoCompetencia = codigoTipoCompetencia;
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

	@Column(name = "estatus", nullable = false)
	public int getEstatus() {
		return this.estatus;
	}

	public void setEstatus(int estatus) {
		this.estatus = estatus;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "condicion_competencia", schema = "public", joinColumns = { @JoinColumn(name = "codigo_tipo_competencia", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "codigo_condicion", nullable = false, updatable = false) })
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tipoCompetencia")
	public Set<ModalidadCompetencia> getModalidadCompetencias() {
		return this.modalidadCompetencias;
	}

	public void setModalidadCompetencias(
			Set<ModalidadCompetencia> modalidadCompetencias) {
		this.modalidadCompetencias = modalidadCompetencias;
	}

}
