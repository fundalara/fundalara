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
 * Ingreso generated by hbm2java
 */
@Entity
@Table(name = "ingreso", schema = "public")
public class Ingreso implements java.io.Serializable {

	private String numeroDocumento;
	private DatoBasico datoBasico;
	private Date fechaPago;
	private char estatus;
	private Set<IngresoDocumentoAcreedor> ingresoDocumentoAcreedors = new HashSet<IngresoDocumentoAcreedor>(
			0);
	private Set<IngresoFormaPago> ingresoFormaPagos = new HashSet<IngresoFormaPago>(
			0);

	public Ingreso() {
	}

	public Ingreso(String numeroDocumento, Date fechaPago, char estatus) {
		this.numeroDocumento = numeroDocumento;
		this.fechaPago = fechaPago;
		this.estatus = estatus;
	}

	public Ingreso(String numeroDocumento, DatoBasico datoBasico,
			Date fechaPago, char estatus,
			Set<IngresoDocumentoAcreedor> ingresoDocumentoAcreedors,
			Set<IngresoFormaPago> ingresoFormaPagos) {
		this.numeroDocumento = numeroDocumento;
		this.datoBasico = datoBasico;
		this.fechaPago = fechaPago;
		this.estatus = estatus;
		this.ingresoDocumentoAcreedors = ingresoDocumentoAcreedors;
		this.ingresoFormaPagos = ingresoFormaPagos;
	}

	@Id
	@Column(name = "numero_documento", unique = true, nullable = false)
	public String getNumeroDocumento() {
		return this.numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codigo_tipo_documento")
	public DatoBasico getDatoBasico() {
		return this.datoBasico;
	}

	public void setDatoBasico(DatoBasico datoBasico) {
		this.datoBasico = datoBasico;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_pago", nullable = false, length = 13)
	public Date getFechaPago() {
		return this.fechaPago;
	}

	public void setFechaPago(Date fechaPago) {
		this.fechaPago = fechaPago;
	}

	@Column(name = "estatus", nullable = false, length = 1)
	public char getEstatus() {
		return this.estatus;
	}

	public void setEstatus(char estatus) {
		this.estatus = estatus;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "ingreso")
	public Set<IngresoDocumentoAcreedor> getIngresoDocumentoAcreedors() {
		return this.ingresoDocumentoAcreedors;
	}

	public void setIngresoDocumentoAcreedors(
			Set<IngresoDocumentoAcreedor> ingresoDocumentoAcreedors) {
		this.ingresoDocumentoAcreedors = ingresoDocumentoAcreedors;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "ingreso")
	public Set<IngresoFormaPago> getIngresoFormaPagos() {
		return this.ingresoFormaPagos;
	}

	public void setIngresoFormaPagos(Set<IngresoFormaPago> ingresoFormaPagos) {
		this.ingresoFormaPagos = ingresoFormaPagos;
	}

}
