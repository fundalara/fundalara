package modelo;

<<<<<<< HEAD
// Generated 25/01/2012 12:32:42 AM by Hibernate Tools 3.4.0.CR1
=======
<<<<<<< HEAD
// Generated 25/01/2012 12:32:42 AM by Hibernate Tools 3.4.0.CR1
=======
// Generated 24/01/2012 04:28:30 AM by Hibernate Tools 3.4.0.CR1
>>>>>>> 7d4823278d0a354855f5032a99e2b48c65b33e7f
>>>>>>> 9d67536bdd2e3b33aa6400eb3bb09741a4535bb0

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
 * Nomina generated by hbm2java
 */
@Entity
@Table(name = "nomina", schema = "public")
public class Nomina implements java.io.Serializable {

	private String codigoNomina;
	private DatoBasico datoBasico;
	private Date fechaInicio;
	private Date fechaFin;
	private char estatus;
	private char estado;
	private Set<Movimiento> movimientos = new HashSet<Movimiento>(0);

	public Nomina() {
	}

	public Nomina(String codigoNomina, DatoBasico datoBasico, Date fechaInicio,
			char estatus, char estado) {
		this.codigoNomina = codigoNomina;
		this.datoBasico = datoBasico;
		this.fechaInicio = fechaInicio;
		this.estatus = estatus;
		this.estado = estado;
	}

	public Nomina(String codigoNomina, DatoBasico datoBasico, Date fechaInicio,
			Date fechaFin, char estatus, char estado,
			Set<Movimiento> movimientos) {
		this.codigoNomina = codigoNomina;
		this.datoBasico = datoBasico;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.estatus = estatus;
		this.estado = estado;
		this.movimientos = movimientos;
	}

	@Id
	@Column(name = "codigo_nomina", unique = true, nullable = false)
	public String getCodigoNomina() {
		return this.codigoNomina;
	}

	public void setCodigoNomina(String codigoNomina) {
		this.codigoNomina = codigoNomina;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codigo_tipo_nomina", nullable = false)
	public DatoBasico getDatoBasico() {
		return this.datoBasico;
	}

	public void setDatoBasico(DatoBasico datoBasico) {
		this.datoBasico = datoBasico;
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

	@Column(name = "estatus", nullable = false, length = 1)
	public char getEstatus() {
		return this.estatus;
	}

	public void setEstatus(char estatus) {
		this.estatus = estatus;
	}

	@Column(name = "estado", nullable = false, length = 1)
	public char getEstado() {
		return this.estado;
	}

	public void setEstado(char estado) {
		this.estado = estado;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "nomina")
	public Set<Movimiento> getMovimientos() {
		return this.movimientos;
	}

	public void setMovimientos(Set<Movimiento> movimientos) {
		this.movimientos = movimientos;
	}

}
