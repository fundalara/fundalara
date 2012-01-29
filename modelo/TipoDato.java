package modelo;

// Generated 28/01/2012 11:49:55 AM by Hibernate Tools 3.4.0.CR1

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
 * TipoDato generated by hbm2java
 */
@Entity
@Table(name = "tipo_dato", schema = "public")
public class TipoDato implements java.io.Serializable {

	private int codigoTipoDato;
	private TipoDato tipoDato;
	private String nombre;
	private String descripcion;
	private char estatus;
	private boolean tipo;
	private Set<DetalleSeguridadFuncional> detalleSeguridadFuncionals = new HashSet<DetalleSeguridadFuncional>(
			0);
	private Set<SeguridadFuncional> seguridadFuncionals = new HashSet<SeguridadFuncional>(
			0);
	private Set<DatoBasico> datoBasicos = new HashSet<DatoBasico>(0);
	private Set<TipoDato> tipoDatos = new HashSet<TipoDato>(0);

	public TipoDato() {
	}

	public TipoDato(int codigoTipoDato, String nombre, char estatus,
			boolean tipo) {
		this.codigoTipoDato = codigoTipoDato;
		this.nombre = nombre;
		this.estatus = estatus;
		this.tipo = tipo;
	}

	public TipoDato(int codigoTipoDato, TipoDato tipoDato, String nombre,
			String descripcion, char estatus, boolean tipo,
			Set<DetalleSeguridadFuncional> detalleSeguridadFuncionals,
			Set<SeguridadFuncional> seguridadFuncionals,
			Set<DatoBasico> datoBasicos, Set<TipoDato> tipoDatos) {
		this.codigoTipoDato = codigoTipoDato;
		this.tipoDato = tipoDato;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.estatus = estatus;
		this.tipo = tipo;
		this.detalleSeguridadFuncionals = detalleSeguridadFuncionals;
		this.seguridadFuncionals = seguridadFuncionals;
		this.datoBasicos = datoBasicos;
		this.tipoDatos = tipoDatos;
	}

	@Id
	@Column(name = "codigo_tipo_dato", unique = true, nullable = false)
	public int getCodigoTipoDato() {
		return this.codigoTipoDato;
	}

	public void setCodigoTipoDato(int codigoTipoDato) {
		this.codigoTipoDato = codigoTipoDato;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_codigo_tipo_dato")
	public TipoDato getTipoDato() {
		return this.tipoDato;
	}

	public void setTipoDato(TipoDato tipoDato) {
		this.tipoDato = tipoDato;
	}

	@Column(name = "nombre", nullable = false)
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column(name = "descripcion")
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

	@Column(name = "tipo", nullable = false)
	public boolean isTipo() {
		return this.tipo;
	}

	public void setTipo(boolean tipo) {
		this.tipo = tipo;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tipoDato")
	public Set<DetalleSeguridadFuncional> getDetalleSeguridadFuncionals() {
		return this.detalleSeguridadFuncionals;
	}

	public void setDetalleSeguridadFuncionals(
			Set<DetalleSeguridadFuncional> detalleSeguridadFuncionals) {
		this.detalleSeguridadFuncionals = detalleSeguridadFuncionals;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tipoDato")
	public Set<SeguridadFuncional> getSeguridadFuncionals() {
		return this.seguridadFuncionals;
	}

	public void setSeguridadFuncionals(
			Set<SeguridadFuncional> seguridadFuncionals) {
		this.seguridadFuncionals = seguridadFuncionals;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tipoDato")
	public Set<DatoBasico> getDatoBasicos() {
		return this.datoBasicos;
	}

	public void setDatoBasicos(Set<DatoBasico> datoBasicos) {
		this.datoBasicos = datoBasicos;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tipoDato")
	public Set<TipoDato> getTipoDatos() {
		return this.tipoDatos;
	}

	public void setTipoDatos(Set<TipoDato> tipoDatos) {
		this.tipoDatos = tipoDatos;
	}

}
