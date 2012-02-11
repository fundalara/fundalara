package modelo;

// Generated 10/02/2012 11:18:51 PM by Hibernate Tools 3.4.0.CR1

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
 * Movimiento generated by hbm2java
 */
@Entity
@Table(name = "movimiento", schema = "public")
public class Movimiento implements java.io.Serializable {

	private int codigoMovimiento;
	private ConceptoNomina conceptoNomina;
	private Personal personal;
	private Nomina nomina;
	private double monto;
	private Date fecha;
	private byte[] justificacionInasistencia;
	private char estatus;
	private Integer cantidadHoraDia;
	private String observacion;

	public Movimiento() {
	}

	public Movimiento(int codigoMovimiento, ConceptoNomina conceptoNomina,
			Personal personal, Nomina nomina, double monto, Date fecha,
			char estatus) {
		this.codigoMovimiento = codigoMovimiento;
		this.conceptoNomina = conceptoNomina;
		this.personal = personal;
		this.nomina = nomina;
		this.monto = monto;
		this.fecha = fecha;
		this.estatus = estatus;
	}

	public Movimiento(int codigoMovimiento, ConceptoNomina conceptoNomina,
			Personal personal, Nomina nomina, double monto, Date fecha,
			byte[] justificacionInasistencia, char estatus,
			Integer cantidadHoraDia, String observacion) {
		this.codigoMovimiento = codigoMovimiento;
		this.conceptoNomina = conceptoNomina;
		this.personal = personal;
		this.nomina = nomina;
		this.monto = monto;
		this.fecha = fecha;
		this.justificacionInasistencia = justificacionInasistencia;
		this.estatus = estatus;
		this.cantidadHoraDia = cantidadHoraDia;
		this.observacion = observacion;
	}

	@Id
	@Column(name = "codigo_movimiento", unique = true, nullable = false)
	public int getCodigoMovimiento() {
		return this.codigoMovimiento;
	}

	public void setCodigoMovimiento(int codigoMovimiento) {
		this.codigoMovimiento = codigoMovimiento;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codigo_nomina", nullable = false)
	public Nomina getNomina() {
		return this.nomina;
	}

	public void setNomina(Nomina nomina) {
		this.nomina = nomina;
	}

	@Column(name = "monto", nullable = false, precision = 17, scale = 17)
	public double getMonto() {
		return this.monto;
	}

	public void setMonto(double monto) {
		this.monto = monto;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha", nullable = false, length = 13)
	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	@Column(name = "justificacion_inasistencia")
	public byte[] getJustificacionInasistencia() {
		return this.justificacionInasistencia;
	}

	public void setJustificacionInasistencia(byte[] justificacionInasistencia) {
		this.justificacionInasistencia = justificacionInasistencia;
	}

	@Column(name = "estatus", nullable = false, length = 1)
	public char getEstatus() {
		return this.estatus;
	}

	public void setEstatus(char estatus) {
		this.estatus = estatus;
	}

	@Column(name = "cantidad_hora_dia")
	public Integer getCantidadHoraDia() {
		return this.cantidadHoraDia;
	}

	public void setCantidadHoraDia(Integer cantidadHoraDia) {
		this.cantidadHoraDia = cantidadHoraDia;
	}

	@Column(name = "observacion")
	public String getObservacion() {
		return this.observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

}
