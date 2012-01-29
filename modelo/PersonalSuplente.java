package modelo;

// Generated 28/01/2012 11:49:55 AM by Hibernate Tools 3.4.0.CR1

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
 * PersonalSuplente generated by hbm2java
 */
@Entity
@Table(name = "personal_suplente", schema = "public")
public class PersonalSuplente implements java.io.Serializable {

	private PersonalSuplenteId id;
	private SesionEjecutada sesionEjecutada;
	private DatoBasico datoBasico;
	private Personal personal;
	private String observacion;
	private char estatus;

	public PersonalSuplente() {
	}

	public PersonalSuplente(PersonalSuplenteId id,
			SesionEjecutada sesionEjecutada, Personal personal, char estatus) {
		this.id = id;
		this.sesionEjecutada = sesionEjecutada;
		this.personal = personal;
		this.estatus = estatus;
	}

	public PersonalSuplente(PersonalSuplenteId id,
			SesionEjecutada sesionEjecutada, DatoBasico datoBasico,
			Personal personal, String observacion, char estatus) {
		this.id = id;
		this.sesionEjecutada = sesionEjecutada;
		this.datoBasico = datoBasico;
		this.personal = personal;
		this.observacion = observacion;
		this.estatus = estatus;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "cedulaRif", column = @Column(name = "cedula_rif", nullable = false)),
			@AttributeOverride(name = "codigoSesionEjecutada", column = @Column(name = "codigo_sesion_ejecutada", nullable = false)) })
	public PersonalSuplenteId getId() {
		return this.id;
	}

	public void setId(PersonalSuplenteId id) {
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
	@JoinColumn(name = "eventualidad_entrenador")
	public DatoBasico getDatoBasico() {
		return this.datoBasico;
	}

	public void setDatoBasico(DatoBasico datoBasico) {
		this.datoBasico = datoBasico;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cedula_rif", nullable = false, insertable = false, updatable = false)
	public Personal getPersonal() {
		return this.personal;
	}

	public void setPersonal(Personal personal) {
		this.personal = personal;
	}

	@Column(name = "observacion")
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
