package modelo;

// Generated 31/12/2011 11:02:01 AM by Hibernate Tools 3.4.0.CR1

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
 * PersonalTipoNomina generated by hbm2java
 */
@Entity
@Table(name = "personal_tipo_nomina", schema = "public")
public class PersonalTipoNomina implements java.io.Serializable {

	private int codigoPersonalTipoNomina;
	private DatoBasico datoBasico;
	private Personal personal;
	private Date fechaInicio;
	private Date fechaFin;
	private char estado;
	private char estatus;

	public PersonalTipoNomina() {
	}

	public PersonalTipoNomina(int codigoPersonalTipoNomina,
			DatoBasico datoBasico, Personal personal, Date fechaInicio,
			char estado, char estatus) {
		this.codigoPersonalTipoNomina = codigoPersonalTipoNomina;
		this.datoBasico = datoBasico;
		this.personal = personal;
		this.fechaInicio = fechaInicio;
		this.estado = estado;
		this.estatus = estatus;
	}

	public PersonalTipoNomina(int codigoPersonalTipoNomina,
			DatoBasico datoBasico, Personal personal, Date fechaInicio,
			Date fechaFin, char estado, char estatus) {
		this.codigoPersonalTipoNomina = codigoPersonalTipoNomina;
		this.datoBasico = datoBasico;
		this.personal = personal;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.estado = estado;
		this.estatus = estatus;
	}

	@Id
	@Column(name = "codigo_personal_tipo_nomina", unique = true, nullable = false)
	public int getCodigoPersonalTipoNomina() {
		return this.codigoPersonalTipoNomina;
	}

	public void setCodigoPersonalTipoNomina(int codigoPersonalTipoNomina) {
		this.codigoPersonalTipoNomina = codigoPersonalTipoNomina;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codigo_tipo_nomina", nullable = false)
	public DatoBasico getDatoBasico() {
		return this.datoBasico;
	}

	public void setDatoBasico(DatoBasico datoBasico) {
		this.datoBasico = datoBasico;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cedula_rif", nullable = false)
	public Personal getPersonal() {
		return this.personal;
	}

	public void setPersonal(Personal personal) {
		this.personal = personal;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_inicio", nullable = false, length = 13)
	public Date getFechaInicio() {
		return this.fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_fin", length = 13)
	public Date getFechaFin() {
		return this.fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	@Column(name = "estado", nullable = false, length = 1)
	public char getEstado() {
		return this.estado;
	}

	public void setEstado(char estado) {
		this.estado = estado;
	}

	@Column(name = "estatus", nullable = false, length = 1)
	public char getEstatus() {
		return this.estatus;
	}

	public void setEstatus(char estatus) {
		this.estatus = estatus;
	}

}
