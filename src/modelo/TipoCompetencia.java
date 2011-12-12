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
 * TipoCompetencia generated by hbm2java
 */
@Entity
@Table(name = "tipo_competencia")
public class TipoCompetencia implements java.io.Serializable {

	private String codigoTipoCompetencia;
	private String nombre;
	private Set<TipoModalidadCompetencia> tipoModalidadCompetencias = new HashSet<TipoModalidadCompetencia>(
			0);

	public TipoCompetencia() {
	}

	public TipoCompetencia(String codigoTipoCompetencia, String nombre) {
		this.codigoTipoCompetencia = codigoTipoCompetencia;
		this.nombre = nombre;
	}

	public TipoCompetencia(String codigoTipoCompetencia, String nombre,
			Set<TipoModalidadCompetencia> tipoModalidadCompetencias) {
		this.codigoTipoCompetencia = codigoTipoCompetencia;
		this.nombre = nombre;
		this.tipoModalidadCompetencias = tipoModalidadCompetencias;
	}

	@Id
	@Column(name = "codigo_tipo_competencia", unique = true, nullable = false)
	public String getCodigoTipoCompetencia() {
		return this.codigoTipoCompetencia;
	}

	public void setCodigoTipoCompetencia(String codigoTipoCompetencia) {
		this.codigoTipoCompetencia = codigoTipoCompetencia;
	}

	@Column(name = "nombre", nullable = false)
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tipoCompetencia")
	public Set<TipoModalidadCompetencia> getTipoModalidadCompetencias() {
		return this.tipoModalidadCompetencias;
	}

	public void setTipoModalidadCompetencias(
			Set<TipoModalidadCompetencia> tipoModalidadCompetencias) {
		this.tipoModalidadCompetencias = tipoModalidadCompetencias;
	}

}
