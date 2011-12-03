package modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the periodicidad database table.
 * 
 */
@Entity
public class Periodicidad implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PeriodicidadPK id;

	@Column(name="dia_repeticion")
	private Integer diaRepeticion;

	private String estatus;

    @Temporal( TemporalType.DATE)
	@Column(name="fecha_finalizacion_periodicidad")
	private Date fechaFinalizacionPeriodicidad;

	@Column(name="frecuencia_periodicidad")
	private String frecuenciaPeriodicidad;

	@Column(name="lapso_repeticion")
	private Integer lapsoRepeticion;

	@Column(name="mes_repeticion")
	private Integer mesRepeticion;

	@Column(name="numero_repeticiones_periodicidad")
	private Integer numeroRepeticionesPeriodicidad;

	@Column(name="periodicidad_semanal")
	private String periodicidadSemanal;

	//bi-directional many-to-one association to PlanificacionMantenimiento
    @ManyToOne
	@JoinColumn(name="codigo_planificacion_mantenimiento")
	private PlanificacionMantenimiento planificacionMantenimiento;

    public Periodicidad() {
    }

	public PeriodicidadPK getId() {
		return this.id;
	}

	public void setId(PeriodicidadPK id) {
		this.id = id;
	}
	
	public Integer getDiaRepeticion() {
		return this.diaRepeticion;
	}

	public void setDiaRepeticion(Integer diaRepeticion) {
		this.diaRepeticion = diaRepeticion;
	}

	public String getEstatus() {
		return this.estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

	public Date getFechaFinalizacionPeriodicidad() {
		return this.fechaFinalizacionPeriodicidad;
	}

	public void setFechaFinalizacionPeriodicidad(Date fechaFinalizacionPeriodicidad) {
		this.fechaFinalizacionPeriodicidad = fechaFinalizacionPeriodicidad;
	}

	public String getFrecuenciaPeriodicidad() {
		return this.frecuenciaPeriodicidad;
	}

	public void setFrecuenciaPeriodicidad(String frecuenciaPeriodicidad) {
		this.frecuenciaPeriodicidad = frecuenciaPeriodicidad;
	}

	public Integer getLapsoRepeticion() {
		return this.lapsoRepeticion;
	}

	public void setLapsoRepeticion(Integer lapsoRepeticion) {
		this.lapsoRepeticion = lapsoRepeticion;
	}

	public Integer getMesRepeticion() {
		return this.mesRepeticion;
	}

	public void setMesRepeticion(Integer mesRepeticion) {
		this.mesRepeticion = mesRepeticion;
	}

	public Integer getNumeroRepeticionesPeriodicidad() {
		return this.numeroRepeticionesPeriodicidad;
	}

	public void setNumeroRepeticionesPeriodicidad(Integer numeroRepeticionesPeriodicidad) {
		this.numeroRepeticionesPeriodicidad = numeroRepeticionesPeriodicidad;
	}

	public String getPeriodicidadSemanal() {
		return this.periodicidadSemanal;
	}

	public void setPeriodicidadSemanal(String periodicidadSemanal) {
		this.periodicidadSemanal = periodicidadSemanal;
	}

	public PlanificacionMantenimiento getPlanificacionMantenimiento() {
		return this.planificacionMantenimiento;
	}

	public void setPlanificacionMantenimiento(PlanificacionMantenimiento planificacionMantenimiento) {
		this.planificacionMantenimiento = planificacionMantenimiento;
	}
	
}