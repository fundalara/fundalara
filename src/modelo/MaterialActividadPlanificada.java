package modelo;

// Generated 27/01/2012 03:27:22 PM by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * MaterialActividadPlanificada generated by hbm2java
 */
@Entity
@Table(name = "material_actividad_planificada", schema = "public")
public class MaterialActividadPlanificada implements java.io.Serializable {

	private int codigoMaterialActividadPlanificada;
	private PlanificacionActividad planificacionActividad;
	private Material material;
	private Juego juego;
	private Sesion sesion;
	private char estatus;
	private int cantidadRequerida;

	public MaterialActividadPlanificada() {
	}

	public MaterialActividadPlanificada(int codigoMaterialActividadPlanificada,
			Material material, char estatus, int cantidadRequerida) {
		this.codigoMaterialActividadPlanificada = codigoMaterialActividadPlanificada;
		this.material = material;
		this.estatus = estatus;
		this.cantidadRequerida = cantidadRequerida;
	}

	public MaterialActividadPlanificada(int codigoMaterialActividadPlanificada,
			PlanificacionActividad planificacionActividad, Material material,
			Juego juego, Sesion sesion, char estatus, int cantidadRequerida) {
		this.codigoMaterialActividadPlanificada = codigoMaterialActividadPlanificada;
		this.planificacionActividad = planificacionActividad;
		this.material = material;
		this.juego = juego;
		this.sesion = sesion;
		this.estatus = estatus;
		this.cantidadRequerida = cantidadRequerida;
	}

	@Id
	@Column(name = "codigo_material_actividad_planificada", unique = true, nullable = false)
	public int getCodigoMaterialActividadPlanificada() {
		return this.codigoMaterialActividadPlanificada;
	}

	public void setCodigoMaterialActividadPlanificada(
			int codigoMaterialActividadPlanificada) {
		this.codigoMaterialActividadPlanificada = codigoMaterialActividadPlanificada;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codigo_planificacion_actividad")
	public PlanificacionActividad getPlanificacionActividad() {
		return this.planificacionActividad;
	}

	public void setPlanificacionActividad(
			PlanificacionActividad planificacionActividad) {
		this.planificacionActividad = planificacionActividad;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codigo_material", nullable = false)
	public Material getMaterial() {
		return this.material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codigo_juego")
	public Juego getJuego() {
		return this.juego;
	}

	public void setJuego(Juego juego) {
		this.juego = juego;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codigo_sesion")
	public Sesion getSesion() {
		return this.sesion;
	}

	public void setSesion(Sesion sesion) {
		this.sesion = sesion;
	}

	@Column(name = "estatus", nullable = false, length = 1)
	public char getEstatus() {
		return this.estatus;
	}

	public void setEstatus(char estatus) {
		this.estatus = estatus;
	}

	@Column(name = "cantidad_requerida", nullable = false)
	public int getCantidadRequerida() {
		return this.cantidadRequerida;
	}

	public void setCantidadRequerida(int cantidadRequerida) {
		this.cantidadRequerida = cantidadRequerida;
	}

}
