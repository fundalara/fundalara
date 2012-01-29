package modelo;

// Generated 28/01/2012 11:49:55 AM by Hibernate Tools 3.4.0.CR1

import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * SeguridadFuncional generated by hbm2java
 */
@Entity
@Table(name = "seguridad_funcional", schema = "public")
public class SeguridadFuncional implements java.io.Serializable {

	private int codigoSeguridad;
	private TipoDato tipoDato;
	private Usuario usuario;
	private Date fechaRegistro;
	private int codigoRegistro;
	private Boolean agregar;
	private Boolean modificar;
	private Boolean eliminar;
	private Set<DetalleSeguridadFuncional> detalleSeguridadFuncionals = new HashSet<DetalleSeguridadFuncional>(
			0);

	public SeguridadFuncional() {
	}

	public SeguridadFuncional(int codigoSeguridad, TipoDato tipoDato,
			Usuario usuario, Date fechaRegistro, int codigoRegistro) {
		this.codigoSeguridad = codigoSeguridad;
		this.tipoDato = tipoDato;
		this.usuario = usuario;
		this.fechaRegistro = fechaRegistro;
		this.codigoRegistro = codigoRegistro;
	}

	public SeguridadFuncional(int codigoSeguridad, TipoDato tipoDato,
			Usuario usuario, Date fechaRegistro, int codigoRegistro,
			Boolean agregar, Boolean modificar, Boolean eliminar,
			Set<DetalleSeguridadFuncional> detalleSeguridadFuncionals) {
		this.codigoSeguridad = codigoSeguridad;
		this.tipoDato = tipoDato;
		this.usuario = usuario;
		this.fechaRegistro = fechaRegistro;
		this.codigoRegistro = codigoRegistro;
		this.agregar = agregar;
		this.modificar = modificar;
		this.eliminar = eliminar;
		this.detalleSeguridadFuncionals = detalleSeguridadFuncionals;
	}

	@Id
	@Column(name = "codigo_seguridad", unique = true, nullable = false)
	public int getCodigoSeguridad() {
		return this.codigoSeguridad;
	}

	public void setCodigoSeguridad(int codigoSeguridad) {
		this.codigoSeguridad = codigoSeguridad;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codigo_tipo_dato", nullable = false)
	public TipoDato getTipoDato() {
		return this.tipoDato;
	}

	public void setTipoDato(TipoDato tipoDato) {
		this.tipoDato = tipoDato;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cedula_rif", nullable = false)
	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_registro", nullable = false, length = 13)
	public Date getFechaRegistro() {
		return this.fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	@Column(name = "codigo_registro", nullable = false)
	public int getCodigoRegistro() {
		return this.codigoRegistro;
	}

	public void setCodigoRegistro(int codigoRegistro) {
		this.codigoRegistro = codigoRegistro;
	}

	@Column(name = "agregar")
	public Boolean getAgregar() {
		return this.agregar;
	}

	public void setAgregar(Boolean agregar) {
		this.agregar = agregar;
	}

	@Column(name = "modificar")
	public Boolean getModificar() {
		return this.modificar;
	}

	public void setModificar(Boolean modificar) {
		this.modificar = modificar;
	}

	@Column(name = "eliminar")
	public Boolean getEliminar() {
		return this.eliminar;
	}

	public void setEliminar(Boolean eliminar) {
		this.eliminar = eliminar;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "seguridadFuncional")
	public Set<DetalleSeguridadFuncional> getDetalleSeguridadFuncionals() {
		return this.detalleSeguridadFuncionals;
	}

	public void setDetalleSeguridadFuncionals(
			Set<DetalleSeguridadFuncional> detalleSeguridadFuncionals) {
		this.detalleSeguridadFuncionals = detalleSeguridadFuncionals;
	}

}
