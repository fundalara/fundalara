package modelo;

// Generated 10/02/2012 01:24:38 AM by Hibernate Tools 3.4.0.CR1

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
 * ActividadPlanificada generated by hbm2java
 */
@Entity
@Table(name = "actividad_planificada", schema = "public")
public class ActividadPlanificada implements java.io.Serializable {

	private ActividadPlanificadaId id;
	private ActividadEntrenamiento actividadEntrenamiento;
	private Sesion sesion;
	private int tiempo;
	private char estatus;

	public ActividadPlanificada() {
	}

	public ActividadPlanificada(ActividadPlanificadaId id,
			ActividadEntrenamiento actividadEntrenamiento, Sesion sesion,
			int tiempo, char estatus) {
		this.id = id;
		this.actividadEntrenamiento = actividadEntrenamiento;
		this.sesion = sesion;
		this.tiempo = tiempo;
		this.estatus = estatus;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "codigoSesion", column = @Column(name = "codigo_sesion", nullable = false)),
			@AttributeOverride(name = "codigoActividadEntrenamiento", column = @Column(name = "codigo_actividad_entrenamiento", nullable = false)) })
	public ActividadPlanificadaId getId() {
		return this.id;
	}

	public void setId(ActividadPlanificadaId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codigo_actividad_entrenamiento", nullable = false, insertable = false, updatable = false)
	public ActividadEntrenamiento getActividadEntrenamiento() {
		return this.actividadEntrenamiento;
	}

	public void setActividadEntrenamiento(
			ActividadEntrenamiento actividadEntrenamiento) {
		this.actividadEntrenamiento = actividadEntrenamiento;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codigo_sesion", nullable = false, insertable = false, updatable = false)
	public Sesion getSesion() {
		return this.sesion;
	}

	public void setSesion(Sesion sesion) {
		this.sesion = sesion;
	}

	@Column(name = "tiempo", nullable = false)
	public int getTiempo() {
		return this.tiempo;
	}

	public void setTiempo(int tiempo) {
		this.tiempo = tiempo;
	}

	@Column(name = "estatus", nullable = false, length = 1)
	public char getEstatus() {
		return this.estatus;
	}

	public void setEstatus(char estatus) {
		this.estatus = estatus;
	}

}
