package modelo;

// Generated 05/12/2011 10:49:17 AM by Hibernate Tools 3.4.0.CR1

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
	private String nombre;
	private char estatus;
	private Set<IndicadorActividadEscala> indicadorActividadEscalas = new HashSet<IndicadorActividadEscala>(
			0);
	private Set<ValorEscalaMedicion> valorEscalaMedicions = new HashSet<ValorEscalaMedicion>(
			0);

	public EscalaMedicion() {
	}

	public EscalaMedicion(String codEscalaMedicion,
			TipoEscalaMedicion tipoEscalaMedicion, String descripcion,
			String nombre, char estatus) {
		this.codEscalaMedicion = codEscalaMedicion;
		this.tipoEscalaMedicion = tipoEscalaMedicion;
		this.descripcion = descripcion;
		this.nombre = nombre;
		this.estatus = estatus;
	}

	public EscalaMedicion(String codEscalaMedicion,
			TipoEscalaMedicion tipoEscalaMedicion, String descripcion,
			String nombre, char estatus,
			Set<IndicadorActividadEscala> indicadorActividadEscalas,
			Set<ValorEscalaMedicion> valorEscalaMedicions) {
		this.codEscalaMedicion = codEscalaMedicion;
		this.tipoEscalaMedicion = tipoEscalaMedicion;
		this.descripcion = descripcion;
		this.nombre = nombre;
		this.estatus = estatus;
		this.indicadorActividadEscalas = indicadorActividadEscalas;
		this.valorEscalaMedicions = valorEscalaMedicions;
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

	@Column(name = "nombre", nullable = false)
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column(name = "estatus", nullable = false, length = 1)
	public char getEstatus() {
		return this.estatus;
	}

	public void setEstatus(char estatus) {
		this.estatus = estatus;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "escalaMedicion")
	public Set<IndicadorActividadEscala> getIndicadorActividadEscalas() {
		return this.indicadorActividadEscalas;
	}

	public void setIndicadorActividadEscalas(
			Set<IndicadorActividadEscala> indicadorActividadEscalas) {
		this.indicadorActividadEscalas = indicadorActividadEscalas;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "escalaMedicion")
	public Set<ValorEscalaMedicion> getValorEscalaMedicions() {
		return this.valorEscalaMedicions;
	}

	public void setValorEscalaMedicions(
			Set<ValorEscalaMedicion> valorEscalaMedicions) {
		this.valorEscalaMedicions = valorEscalaMedicions;
	}

}
