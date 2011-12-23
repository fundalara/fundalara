package modelo;

// Generated Dec 23, 2011 1:26:53 PM by Hibernate Tools 3.4.0.CR1

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * AsistenciaPersonalEntrenamiento generated by hbm2java
 */
@Entity
@Table(name = "asistencia_personal_entrenamiento", schema = "public")
public class AsistenciaPersonalEntrenamiento implements java.io.Serializable {

	private AsistenciaPersonalEntrenamientoId id;
	private SesionEjecutada sesionEjecutada;
	private PersonalEquipo personalEquipo;
	private Usuario usuario;
	private boolean asistencia;
	private String observacion;
	private char estatus;

	public AsistenciaPersonalEntrenamiento() {
	}

	public AsistenciaPersonalEntrenamiento(
			AsistenciaPersonalEntrenamientoId id,
			SesionEjecutada sesionEjecutada, PersonalEquipo personalEquipo,
			Usuario usuario, boolean asistencia, String observacion,
			char estatus) {
		this.id = id;
		this.sesionEjecutada = sesionEjecutada;
		this.personalEquipo = personalEquipo;
		this.usuario = usuario;
		this.asistencia = asistencia;
		this.observacion = observacion;
		this.estatus = estatus;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "codigoSesionEjecutada", column = @Column(name = "codigo_sesion_ejecutada", nullable = false)),
			@AttributeOverride(name = "codigoPersonalEquipo", column = @Column(name = "codigo_personal_equipo", nullable = false)) })
	public AsistenciaPersonalEntrenamientoId getId() {
		return this.id;
	}

	public void setId(AsistenciaPersonalEntrenamientoId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codigo_sesion_ejecutada", nullable = false, insertable = false, updatable = false)
	public SesionEjecutada getSesionEjecutada() {
		return this.sesionEjecutada;
	}

	public void setSesionEjecutada(SesionEjecutada sesionEjecutada) {
		this.sesionEjecutada = sesionEjecutada;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codigo_personal_equipo", nullable = false, insertable = false, updatable = false)
	public PersonalEquipo getPersonalEquipo() {
		return this.personalEquipo;
	}

	public void setPersonalEquipo(PersonalEquipo personalEquipo) {
		this.personalEquipo = personalEquipo;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cedula_rif", nullable = false)
	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Column(name = "asistencia", nullable = false)
	public boolean isAsistencia() {
		return this.asistencia;
	}

	public void setAsistencia(boolean asistencia) {
		this.asistencia = asistencia;
	}

	@Column(name = "observacion", nullable = false)
	public String getObservacion() {
		return this.observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	@Column(name = "estatus", nullable = false, length = 1)
	public char getEstatus() {
		return this.estatus;
	}

	public void setEstatus(char estatus) {
		this.estatus = estatus;
	}

}
