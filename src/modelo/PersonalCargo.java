package modelo;

<<<<<<< HEAD
// Generated 25/01/2012 12:32:42 AM by Hibernate Tools 3.4.0.CR1
=======
// Generated 24/01/2012 04:28:30 AM by Hibernate Tools 3.4.0.CR1
>>>>>>> 7d4823278d0a354855f5032a99e2b48c65b33e7f

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
 * PersonalCargo generated by hbm2java
 */
@Entity
@Table(name = "personal_cargo", schema = "public")
public class PersonalCargo implements java.io.Serializable {

	private int codigoPersonalCargo;
	private DatoBasico datoBasico;
	private Personal personal;
	private Date fechaFin;
	private Date fechaInicio;
	private char estatus;

	public PersonalCargo() {
	}

	public PersonalCargo(int codigoPersonalCargo, DatoBasico datoBasico,
			Personal personal, Date fechaInicio, char estatus) {
		this.codigoPersonalCargo = codigoPersonalCargo;
		this.datoBasico = datoBasico;
		this.personal = personal;
		this.fechaInicio = fechaInicio;
		this.estatus = estatus;
	}

	public PersonalCargo(int codigoPersonalCargo, DatoBasico datoBasico,
			Personal personal, Date fechaFin, Date fechaInicio, char estatus) {
		this.codigoPersonalCargo = codigoPersonalCargo;
		this.datoBasico = datoBasico;
		this.personal = personal;
		this.fechaFin = fechaFin;
		this.fechaInicio = fechaInicio;
		this.estatus = estatus;
	}

	@Id
	@Column(name = "codigo_personal_cargo", unique = true, nullable = false)
	public int getCodigoPersonalCargo() {
		return this.codigoPersonalCargo;
	}

	public void setCodigoPersonalCargo(int codigoPersonalCargo) {
		this.codigoPersonalCargo = codigoPersonalCargo;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codigo_cargo", nullable = false)
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
	@Column(name = "fecha_fin", length = 13)
	public Date getFechaFin() {
		return this.fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_inicio", nullable = false, length = 13)
	public Date getFechaInicio() {
		return this.fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	@Column(name = "estatus", nullable = false, length = 1)
	public char getEstatus() {
		return this.estatus;
	}

	public void setEstatus(char estatus) {
		this.estatus = estatus;
	}

}
