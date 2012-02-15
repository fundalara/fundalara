package modelo;

// Generated 14/02/2012 08:24:27 PM by Hibernate Tools 3.4.0.CR1

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
	private Usuario usuario;
	private Date fechaRegistro;
	private String nombreTabla;
	private Boolean agregar;
	private Boolean modificar;
	private Boolean eliminar;

	public SeguridadFuncional() {
	}

	public SeguridadFuncional(int codigoSeguridad, Usuario usuario,
			Date fechaRegistro, String nombreTabla) {
		this.codigoSeguridad = codigoSeguridad;
		this.usuario = usuario;
		this.fechaRegistro = fechaRegistro;
		this.nombreTabla = nombreTabla;
	}

	public SeguridadFuncional(int codigoSeguridad, Usuario usuario,
			Date fechaRegistro, String nombreTabla, Boolean agregar,
			Boolean modificar, Boolean eliminar) {
		this.codigoSeguridad = codigoSeguridad;
		this.usuario = usuario;
		this.fechaRegistro = fechaRegistro;
		this.nombreTabla = nombreTabla;
		this.agregar = agregar;
		this.modificar = modificar;
		this.eliminar = eliminar;
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

	@Column(name = "nombre_tabla", nullable = false)
	public String getNombreTabla() {
		return this.nombreTabla;
	}

	public void setNombreTabla(String nombreTabla) {
		this.nombreTabla = nombreTabla;
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

}
