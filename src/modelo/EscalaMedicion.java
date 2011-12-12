package modelo;

// Generated 11/12/2011 04:17:03 PM by Hibernate Tools 3.4.0.CR1

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * EscalaMedicion generated by hbm2java
 */
@Entity
@Table(name = "escala_medicion")
public class EscalaMedicion implements java.io.Serializable {

	private String codEscalaMedicion;
	private TipoEscalaMedicion tipoEscalaMedicion;
	private String descripcion;
	private char estatus;
	private String nombre;
	private Set<ValorEscalaMedicion> valorEscalaMedicions = new HashSet<ValorEscalaMedicion>(
			0);
	private Set<IndicadorActividadEscala> indicadorActividadEscalas = new HashSet<IndicadorActividadEscala>(
			0);

	public EscalaMedicion() {
	}

	public EscalaMedicion(String codEscalaMedicion,
			TipoEscalaMedicion tipoEscalaMedicion, String descripcion,
			char estatus, String nombre) {
		this.codEscalaMedicion = codEscalaMedicion;
		this.tipoEscalaMedicion = tipoEscalaMedicion;
		this.descripcion = descripcion;
		this.estatus = estatus;
		this.nombre = nombre;
	}

	public EscalaMedicion(String codEscalaMedicion,
			TipoEscalaMedicion tipoEscalaMedicion, String descripcion,
			char estatus, String nombre,
			Set<ValorEscalaMedicion> valorEscalaMedicions,
			Set<IndicadorActividadEscala> indicadorActividadEscalas) {
		this.codEscalaMedicion = codEscalaMedicion;
		this.tipoEscalaMedicion = tipoEscalaMedicion;
		this.descripcion = descripcion;
		this.estatus = estatus;
		this.nombre = nombre;
		this.valorEscalaMedicions = valorEscalaMedicions;
		this.indicadorActividadEscalas = indicadorActividadEscalas;
	}

	@Id
	@Column(name = "cod_escala_medicion", unique = true, nullable = false)
	public String getCodEscalaMedicion() {
		return this.codEscalaMedicion;
	}

	public void setCodEscalaMedicion(String codEscalaMedicion) {
		this.codEscalaMedicion = codEscalaMedicion;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_tipo_escala_medicion", nullable = false)
	public TipoEscalaMedicion getTipoEscalaMedicion() {
		return this.tipoEscalaMedicion;
	}

	public void setTipoEscalaMedicion(TipoEscalaMedicion tipoEscalaMedicion) {
		this.tipoEscalaMedicion = tipoEscalaMedicion;
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "escalaMedicion")
	public Set<ValorEscalaMedicion> getValorEscalaMedicions() {
		return this.valorEscalaMedicions;
	}

	public void setValorEscalaMedicions(
			Set<ValorEscalaMedicion> valorEscalaMedicions) {
		this.valorEscalaMedicions = valorEscalaMedicions;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "escalaMedicion")
	public Set<IndicadorActividadEscala> getIndicadorActividadEscalas() {
		return this.indicadorActividadEscalas;
	}

	public void setIndicadorActividadEscalas(
			Set<IndicadorActividadEscala> indicadorActividadEscalas) {
		this.indicadorActividadEscalas = indicadorActividadEscalas;
	}

}
