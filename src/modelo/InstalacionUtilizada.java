package modelo;

// Generated 27/01/2012 03:27:22 PM by Hibernate Tools 3.4.0.CR1

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
 * InstalacionUtilizada generated by hbm2java
 */
@Entity
@Table(name = "instalacion_utilizada", schema = "public")
public class InstalacionUtilizada implements java.io.Serializable {

	private int codigoInstalacionUtilizada;
	private Instalacion instalacion;
	private Sesion sesion;
	private Date fechaInicio;
	private Date fechaFin;
	private char estatus;
	private Date horaInicio;
	private Date horaFin;
	private Set<PlanificacionActividad> planificacionActividads = new HashSet<PlanificacionActividad>(
			0);
	private Set<InstalacionEjecutada> instalacionEjecutadas = new HashSet<InstalacionEjecutada>(
			0);
	private Set<Actividad> actividads = new HashSet<Actividad>(0);

	public InstalacionUtilizada() {
	}

	public InstalacionUtilizada(int codigoInstalacionUtilizada,
			Instalacion instalacion, Date fechaInicio, Date fechaFin,
			char estatus, Date horaInicio, Date horaFin) {
		this.codigoInstalacionUtilizada = codigoInstalacionUtilizada;
		this.instalacion = instalacion;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.estatus = estatus;
		this.horaInicio = horaInicio;
		this.horaFin = horaFin;
	}

	public InstalacionUtilizada(int codigoInstalacionUtilizada,
			Instalacion instalacion, Sesion sesion, Date fechaInicio,
			Date fechaFin, char estatus, Date horaInicio, Date horaFin,
			Set<PlanificacionActividad> planificacionActividads,
			Set<InstalacionEjecutada> instalacionEjecutadas,
			Set<Actividad> actividads) {
		this.codigoInstalacionUtilizada = codigoInstalacionUtilizada;
		this.instalacion = instalacion;
		this.sesion = sesion;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.estatus = estatus;
		this.horaInicio = horaInicio;
		this.horaFin = horaFin;
		this.planificacionActividads = planificacionActividads;
		this.instalacionEjecutadas = instalacionEjecutadas;
		this.actividads = actividads;
	}

	@Id
	@Column(name = "codigo_instalacion_utilizada", unique = true, nullable = false)
	public int getCodigoInstalacionUtilizada() {
		return this.codigoInstalacionUtilizada;
	}

	public void setCodigoInstalacionUtilizada(int codigoInstalacionUtilizada) {
		this.codigoInstalacionUtilizada = codigoInstalacionUtilizada;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codigo_instalacion", nullable = false)
	public Instalacion getInstalacion() {
		return this.instalacion;
	}

	public void setInstalacion(Instalacion instalacion) {
		this.instalacion = instalacion;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codigo_sesion")
	public Sesion getSesion() {
		return this.sesion;
	}

	public void setSesion(Sesion sesion) {
		this.sesion = sesion;
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
	@Column(name = "fecha_fin", nullable = false, length = 13)
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

	@Temporal(TemporalType.TIME)
	@Column(name = "hora_inicio", nullable = false, length = 15)
	public Date getHoraInicio() {
		return this.horaInicio;
	}

	public void setHoraInicio(Date horaInicio) {
		this.horaInicio = horaInicio;
	}

	@Temporal(TemporalType.TIME)
	@Column(name = "hora_fin", nullable = false, length = 15)
	public Date getHoraFin() {
		return this.horaFin;
	}

	public void setHoraFin(Date horaFin) {
		this.horaFin = horaFin;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "instalacionUtilizada")
	public Set<PlanificacionActividad> getPlanificacionActividads() {
		return this.planificacionActividads;
	}

	public void setPlanificacionActividads(
			Set<PlanificacionActividad> planificacionActividads) {
		this.planificacionActividads = planificacionActividads;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "instalacionUtilizada")
	public Set<InstalacionEjecutada> getInstalacionEjecutadas() {
		return this.instalacionEjecutadas;
	}

	public void setInstalacionEjecutadas(
			Set<InstalacionEjecutada> instalacionEjecutadas) {
		this.instalacionEjecutadas = instalacionEjecutadas;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "instalacionUtilizada")
	public Set<Actividad> getActividads() {
		return this.actividads;
	}

	public void setActividads(Set<Actividad> actividads) {
		this.actividads = actividads;
	}

}
