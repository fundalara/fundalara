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
 * InstalacionEjecutada generated by hbm2java
 */
@Entity
@Table(name = "instalacion_ejecutada", schema = "public")
public class InstalacionEjecutada implements java.io.Serializable {

	private int codigoInstalacionEjecutada;
	private DatoBasico datoBasico;
	private SesionEjecutada sesionEjecutada;
	private InstalacionUtilizada instalacionUtilizada;
	private Instalacion instalacion;
	private String observacion;
	private char estatus;

	public InstalacionEjecutada() {
	}

	public InstalacionEjecutada(int codigoInstalacionEjecutada,
			SesionEjecutada sesionEjecutada,
			InstalacionUtilizada instalacionUtilizada, char estatus) {
		this.codigoInstalacionEjecutada = codigoInstalacionEjecutada;
		this.sesionEjecutada = sesionEjecutada;
		this.instalacionUtilizada = instalacionUtilizada;
		this.estatus = estatus;
	}

	public InstalacionEjecutada(int codigoInstalacionEjecutada,
			DatoBasico datoBasico, SesionEjecutada sesionEjecutada,
			InstalacionUtilizada instalacionUtilizada, Instalacion instalacion,
			String observacion, char estatus) {
		this.codigoInstalacionEjecutada = codigoInstalacionEjecutada;
		this.datoBasico = datoBasico;
		this.sesionEjecutada = sesionEjecutada;
		this.instalacionUtilizada = instalacionUtilizada;
		this.instalacion = instalacion;
		this.observacion = observacion;
		this.estatus = estatus;
	}

	@Id
	@Column(name = "codigo_instalacion_ejecutada", unique = true, nullable = false)
	public int getCodigoInstalacionEjecutada() {
		return this.codigoInstalacionEjecutada;
	}

	public void setCodigoInstalacionEjecutada(int codigoInstalacionEjecutada) {
		this.codigoInstalacionEjecutada = codigoInstalacionEjecutada;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "eventualidad_instalacion")
	public DatoBasico getDatoBasico() {
		return this.datoBasico;
	}

	public void setDatoBasico(DatoBasico datoBasico) {
		this.datoBasico = datoBasico;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codigo_sesion_ejecutada", nullable = false)
	public SesionEjecutada getSesionEjecutada() {
		return this.sesionEjecutada;
	}

	public void setSesionEjecutada(SesionEjecutada sesionEjecutada) {
		this.sesionEjecutada = sesionEjecutada;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codigo_instalacion_utilizada", nullable = false)
	public InstalacionUtilizada getInstalacionUtilizada() {
		return this.instalacionUtilizada;
	}

	public void setInstalacionUtilizada(
			InstalacionUtilizada instalacionUtilizada) {
		this.instalacionUtilizada = instalacionUtilizada;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codigo_instalacion")
	public Instalacion getInstalacion() {
		return this.instalacion;
	}

	public void setInstalacion(Instalacion instalacion) {
		this.instalacion = instalacion;
	}

	@Column(name = "observacion")
	public String getObservacion() {
		return this.observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	@Column(name = "estatus", nullable = false, length = 1)
	public char getEstatus() {
		return this.estatus;
	}

	public void setEstatus(char estatus) {
		this.estatus = estatus;
	}

}
