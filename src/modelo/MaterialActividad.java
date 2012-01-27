package modelo;

// Generated 25/01/2012 12:32:42 AM by Hibernate Tools 3.4.0.CR1

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
 * MaterialActividad generated by hbm2java
 */
@Entity
@Table(name = "material_actividad", schema = "public")
public class MaterialActividad implements java.io.Serializable {

	private int codigoMaterialActividad;
	private SesionEjecutada sesionEjecutada;
	private Material material;
	private Juego juego;
	private Actividad actividad;
	private char estatus;
	private int cantidadEntregada;
	private Integer cantidadDevuelta;
	private Date fechaDevolucion;
	private String observacion;

	public MaterialActividad() {
	}

	public MaterialActividad(int codigoMaterialActividad, Material material,
			char estatus, int cantidadEntregada) {
		this.codigoMaterialActividad = codigoMaterialActividad;
		this.material = material;
		this.estatus = estatus;
		this.cantidadEntregada = cantidadEntregada;
	}

	public MaterialActividad(int codigoMaterialActividad,
			SesionEjecutada sesionEjecutada, Material material, Juego juego,
			Actividad actividad, char estatus, int cantidadEntregada,
			Integer cantidadDevuelta, Date fechaDevolucion, String observacion) {
		this.codigoMaterialActividad = codigoMaterialActividad;
		this.sesionEjecutada = sesionEjecutada;
		this.material = material;
		this.juego = juego;
		this.actividad = actividad;
		this.estatus = estatus;
		this.cantidadEntregada = cantidadEntregada;
		this.cantidadDevuelta = cantidadDevuelta;
		this.fechaDevolucion = fechaDevolucion;
		this.observacion = observacion;
	}

	@Id
	@Column(name = "codigo_material_actividad", unique = true, nullable = false)
	public int getCodigoMaterialActividad() {
		return this.codigoMaterialActividad;
	}

	public void setCodigoMaterialActividad(int codigoMaterialActividad) {
		this.codigoMaterialActividad = codigoMaterialActividad;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codigo_sesion_ejecutada")
	public SesionEjecutada getSesionEjecutada() {
		return this.sesionEjecutada;
	}

	public void setSesionEjecutada(SesionEjecutada sesionEjecutada) {
		this.sesionEjecutada = sesionEjecutada;
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
	@JoinColumn(name = "codigo_actividad")
	public Actividad getActividad() {
		return this.actividad;
	}

	public void setActividad(Actividad actividad) {
		this.actividad = actividad;
	}

	@Column(name = "estatus", nullable = false, length = 1)
	public char getEstatus() {
		return this.estatus;
	}

	public void setEstatus(char estatus) {
		this.estatus = estatus;
	}

	@Column(name = "cantidad_entregada", nullable = false)
	public int getCantidadEntregada() {
		return this.cantidadEntregada;
	}

	public void setCantidadEntregada(int cantidadEntregada) {
		this.cantidadEntregada = cantidadEntregada;
	}

	@Column(name = "cantidad_devuelta")
	public Integer getCantidadDevuelta() {
		return this.cantidadDevuelta;
	}

	public void setCantidadDevuelta(Integer cantidadDevuelta) {
		this.cantidadDevuelta = cantidadDevuelta;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_devolucion", length = 13)
	public Date getFechaDevolucion() {
		return this.fechaDevolucion;
	}

	public void setFechaDevolucion(Date fechaDevolucion) {
		this.fechaDevolucion = fechaDevolucion;
	}

	@Column(name = "observacion")
	public String getObservacion() {
		return this.observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

}
