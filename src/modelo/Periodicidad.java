package modelo;

// Generated 13/02/2012 08:44:08 PM by Hibernate Tools 3.4.0.CR1

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
 * Periodicidad generated by hbm2java
 */
@Entity
@Table(name = "periodicidad", schema = "public")
public class Periodicidad implements java.io.Serializable {

	private int codigoPeriodicidad;
	private PlanificacionActividad planificacionActividad;
	private String frecuenciaPeriodicidad;
	private int lapsoRepeticion;
	private String periodicidadSemanal;
	private int diaRepeticion;
	private int mesRepeticion;
	private int numeroRepeticionesPeriodicidad;
	private Date fechaFinalizacionPeriodicidad;
	private Date horaInicio;
	private Date horaCulminacion;
	private char estatus1;

	public Periodicidad() {
	}

	public Periodicidad(int codigoPeriodicidad,
			PlanificacionActividad planificacionActividad,
			String frecuenciaPeriodicidad, int lapsoRepeticion,
			String periodicidadSemanal, int diaRepeticion, int mesRepeticion,
			int numeroRepeticionesPeriodicidad,
			Date fechaFinalizacionPeriodicidad, Date horaInicio,
			Date horaCulminacion, char estatus1) {
		this.codigoPeriodicidad = codigoPeriodicidad;
		this.planificacionActividad = planificacionActividad;
		this.frecuenciaPeriodicidad = frecuenciaPeriodicidad;
		this.lapsoRepeticion = lapsoRepeticion;
		this.periodicidadSemanal = periodicidadSemanal;
		this.diaRepeticion = diaRepeticion;
		this.mesRepeticion = mesRepeticion;
		this.numeroRepeticionesPeriodicidad = numeroRepeticionesPeriodicidad;
		this.fechaFinalizacionPeriodicidad = fechaFinalizacionPeriodicidad;
		this.horaInicio = horaInicio;
		this.horaCulminacion = horaCulminacion;
		this.estatus1 = estatus1;
	}

	@Id
	@Column(name = "codigo_periodicidad", unique = true, nullable = false)
	public int getCodigoPeriodicidad() {
		return this.codigoPeriodicidad;
	}

	public void setCodigoPeriodicidad(int codigoPeriodicidad) {
		this.codigoPeriodicidad = codigoPeriodicidad;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codigo_planificacion_actividad", nullable = false)
	public PlanificacionActividad getPlanificacionActividad() {
		return this.planificacionActividad;
	}

	public void setPlanificacionActividad(
			PlanificacionActividad planificacionActividad) {
		this.planificacionActividad = planificacionActividad;
	}

	@Column(name = "frecuencia_periodicidad", nullable = false)
	public String getFrecuenciaPeriodicidad() {
		return this.frecuenciaPeriodicidad;
	}

	public void setFrecuenciaPeriodicidad(String frecuenciaPeriodicidad) {
		this.frecuenciaPeriodicidad = frecuenciaPeriodicidad;
	}

	@Column(name = "lapso_repeticion", nullable = false)
	public int getLapsoRepeticion() {
		return this.lapsoRepeticion;
	}

	public void setLapsoRepeticion(int lapsoRepeticion) {
		this.lapsoRepeticion = lapsoRepeticion;
	}

	@Column(name = "periodicidad_semanal", nullable = false)
	public String getPeriodicidadSemanal() {
		return this.periodicidadSemanal;
	}

	public void setPeriodicidadSemanal(String periodicidadSemanal) {
		this.periodicidadSemanal = periodicidadSemanal;
	}

	@Column(name = "dia_repeticion", nullable = false)
	public int getDiaRepeticion() {
		return this.diaRepeticion;
	}

	public void setDiaRepeticion(int diaRepeticion) {
		this.diaRepeticion = diaRepeticion;
	}

	@Column(name = "mes_repeticion", nullable = false)
	public int getMesRepeticion() {
		return this.mesRepeticion;
	}

	public void setMesRepeticion(int mesRepeticion) {
		this.mesRepeticion = mesRepeticion;
	}

	@Column(name = "numero_repeticiones_periodicidad", nullable = false)
	public int getNumeroRepeticionesPeriodicidad() {
		return this.numeroRepeticionesPeriodicidad;
	}

	public void setNumeroRepeticionesPeriodicidad(
			int numeroRepeticionesPeriodicidad) {
		this.numeroRepeticionesPeriodicidad = numeroRepeticionesPeriodicidad;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_finalizacion_periodicidad", nullable = false, length = 13)
	public Date getFechaFinalizacionPeriodicidad() {
		return this.fechaFinalizacionPeriodicidad;
	}

	public void setFechaFinalizacionPeriodicidad(
			Date fechaFinalizacionPeriodicidad) {
		this.fechaFinalizacionPeriodicidad = fechaFinalizacionPeriodicidad;
	}

	@Temporal(TemporalType.TIME)
	@Column(name = "hora_inicio", nullable = false, length = 15)
	public Date getHoraInicio() {
		return this.horaInicio;
	}

	public void setHoraInicio(Date horaInicio) {
		this.horaInicio = horaInicio;
	}

	@Temporal(TemporalType.TIME)
	@Column(name = "hora_culminacion", nullable = false, length = 15)
	public Date getHoraCulminacion() {
		return this.horaCulminacion;
	}

	public void setHoraCulminacion(Date horaCulminacion) {
		this.horaCulminacion = horaCulminacion;
	}

	@Column(name = "estatus_1", nullable = false, length = 1)
	public char getEstatus1() {
		return this.estatus1;
	}

	public void setEstatus1(char estatus1) {
		this.estatus1 = estatus1;
	}

}
