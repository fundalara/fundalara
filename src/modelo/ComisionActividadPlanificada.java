package modelo;

// Generated 11/02/2012 01:49:19 AM by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * ComisionActividadPlanificada generated by hbm2java
 */
@Entity
@Table(name = "comision_actividad_planificada", schema = "public")
public class ComisionActividadPlanificada implements java.io.Serializable {

	private int codigoComisionActividadPlan;
	private DatoBasico datoBasico;
	private PlanificacionActividad planificacionActividad;

	public ComisionActividadPlanificada() {
	}

	public ComisionActividadPlanificada(int codigoComisionActividadPlan,
			DatoBasico datoBasico, PlanificacionActividad planificacionActividad) {
		this.codigoComisionActividadPlan = codigoComisionActividadPlan;
		this.datoBasico = datoBasico;
		this.planificacionActividad = planificacionActividad;
	}

	@Id
	@Column(name = "codigo_comision_actividad_plan", unique = true, nullable = false)
	public int getCodigoComisionActividadPlan() {
		return this.codigoComisionActividadPlan;
	}

	public void setCodigoComisionActividadPlan(int codigoComisionActividadPlan) {
		this.codigoComisionActividadPlan = codigoComisionActividadPlan;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codigo_comision", nullable = false)
	public DatoBasico getDatoBasico() {
		return this.datoBasico;
	}

	public void setDatoBasico(DatoBasico datoBasico) {
		this.datoBasico = datoBasico;
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

}
