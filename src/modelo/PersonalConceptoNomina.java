package modelo;

<<<<<<< HEAD
// Generated 25/01/2012 12:32:42 AM by Hibernate Tools 3.4.0.CR1
=======
<<<<<<< HEAD
// Generated 25/01/2012 12:32:42 AM by Hibernate Tools 3.4.0.CR1
=======
<<<<<<< HEAD
// Generated 25/01/2012 12:32:42 AM by Hibernate Tools 3.4.0.CR1
=======
// Generated 24/01/2012 04:28:30 AM by Hibernate Tools 3.4.0.CR1
>>>>>>> 7d4823278d0a354855f5032a99e2b48c65b33e7f
>>>>>>> 9d67536bdd2e3b33aa6400eb3bb09741a4535bb0
>>>>>>> 873ccb018e72b00b831aa9284cc0745bd1c2d514

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
 * PersonalConceptoNomina generated by hbm2java
 */
@Entity
@Table(name = "personal_concepto_nomina", schema = "public")
public class PersonalConceptoNomina implements java.io.Serializable {

	private int codigoPersonalConceptoNomina;
	private ConceptoNomina conceptoNomina;
	private Personal personal;
	private float monto;
	private Date fechaFin;
	private char estatus;
	private Date fechaActivacion;

	public PersonalConceptoNomina() {
	}

	public PersonalConceptoNomina(int codigoPersonalConceptoNomina,
			ConceptoNomina conceptoNomina, Personal personal, float monto,
			char estatus, Date fechaActivacion) {
		this.codigoPersonalConceptoNomina = codigoPersonalConceptoNomina;
		this.conceptoNomina = conceptoNomina;
		this.personal = personal;
		this.monto = monto;
		this.estatus = estatus;
		this.fechaActivacion = fechaActivacion;
	}

	public PersonalConceptoNomina(int codigoPersonalConceptoNomina,
			ConceptoNomina conceptoNomina, Personal personal, float monto,
			Date fechaFin, char estatus, Date fechaActivacion) {
		this.codigoPersonalConceptoNomina = codigoPersonalConceptoNomina;
		this.conceptoNomina = conceptoNomina;
		this.personal = personal;
		this.monto = monto;
		this.fechaFin = fechaFin;
		this.estatus = estatus;
		this.fechaActivacion = fechaActivacion;
	}

	@Id
	@Column(name = "codigo_personal_concepto_nomina", unique = true, nullable = false)
	public int getCodigoPersonalConceptoNomina() {
		return this.codigoPersonalConceptoNomina;
	}

	public void setCodigoPersonalConceptoNomina(int codigoPersonalConceptoNomina) {
		this.codigoPersonalConceptoNomina = codigoPersonalConceptoNomina;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codigo_concepto_nomina", nullable = false)
	public ConceptoNomina getConceptoNomina() {
		return this.conceptoNomina;
	}

	public void setConceptoNomina(ConceptoNomina conceptoNomina) {
		this.conceptoNomina = conceptoNomina;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cedula_rif", nullable = false)
	public Personal getPersonal() {
		return this.personal;
	}

	public void setPersonal(Personal personal) {
		this.personal = personal;
	}

	@Column(name = "monto", nullable = false, precision = 8, scale = 8)
	public float getMonto() {
		return this.monto;
	}

	public void setMonto(float monto) {
		this.monto = monto;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_fin", length = 13)
	public Date getFechaFin() {
		return this.fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	@Column(name = "estatus", nullable = false, length = 1)
	public char getEstatus() {
		return this.estatus;
	}

	public void setEstatus(char estatus) {
		this.estatus = estatus;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_activacion", nullable = false, length = 13)
	public Date getFechaActivacion() {
		return this.fechaActivacion;
	}

	public void setFechaActivacion(Date fechaActivacion) {
		this.fechaActivacion = fechaActivacion;
	}

}
