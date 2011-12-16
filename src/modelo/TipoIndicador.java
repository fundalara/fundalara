package modelo;

// Generated 14/12/2011 05:11:39 PM by Hibernate Tools 3.4.0.CR1

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * TipoIndicador generated by hbm2java
 */
@Entity
@Table(name = "tipo_indicador")
public class TipoIndicador implements java.io.Serializable {

	private String codTipoIndicador;
	private String nombre;
	private char estatus;
	private Set<Indicador> indicadors = new HashSet<Indicador>(0);
	private Set<Indicador> indicadors_1 = new HashSet<Indicador>(0);

	public TipoIndicador() {
	}

	public TipoIndicador(String codTipoIndicador, String nombre, char estatus) {
		this.codTipoIndicador = codTipoIndicador;
		this.nombre = nombre;
		this.estatus = estatus;
	}

	public TipoIndicador(String codTipoIndicador, String nombre, char estatus,
			Set<Indicador> indicadors, Set<Indicador> indicadors_1) {
		this.codTipoIndicador = codTipoIndicador;
		this.nombre = nombre;
		this.estatus = estatus;
		this.indicadors = indicadors;
		this.indicadors_1 = indicadors_1;
	}

	@Id
	@Column(name = "cod_tipo_indicador", unique = true, nullable = false)
	public String getCodTipoIndicador() {
		return this.codTipoIndicador;
	}

	public void setCodTipoIndicador(String codTipoIndicador) {
		this.codTipoIndicador = codTipoIndicador;
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tipoIndicador")
	public Set<Indicador> getIndicadors() {
		return this.indicadors;
	}

	public void setIndicadors(Set<Indicador> indicadors) {
		this.indicadors = indicadors;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tipoIndicador")
	public Set<Indicador> getIndicadors_1() {
		return this.indicadors_1;
	}

	public void setIndicadors_1(Set<Indicador> indicadors_1) {
		this.indicadors_1 = indicadors_1;
	}

}
