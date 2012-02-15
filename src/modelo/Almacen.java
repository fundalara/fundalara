package modelo;

// Generated 14/02/2012 08:24:27 PM by Hibernate Tools 3.4.0.CR1

import java.math.BigDecimal;
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

/**
 * Almacen generated by hbm2java
 */
@Entity
@Table(name = "almacen", schema = "public")
public class Almacen implements java.io.Serializable {

	private int codigoAlmacen;
	private Instalacion instalacion;
	private String nombre;
	private String descripcion;
	private BigDecimal capacidad;
	private char estatus;
	private Set<Material> materials = new HashSet<Material>(0);

	public Almacen() {
	}

	public Almacen(int codigoAlmacen, Instalacion instalacion, String nombre,
			BigDecimal capacidad, char estatus) {
		this.codigoAlmacen = codigoAlmacen;
		this.instalacion = instalacion;
		this.nombre = nombre;
		this.capacidad = capacidad;
		this.estatus = estatus;
	}

	public Almacen(int codigoAlmacen, Instalacion instalacion, String nombre,
			String descripcion, BigDecimal capacidad, char estatus,
			Set<Material> materials) {
		this.codigoAlmacen = codigoAlmacen;
		this.instalacion = instalacion;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.capacidad = capacidad;
		this.estatus = estatus;
		this.materials = materials;
	}

	@Id
	@Column(name = "codigo_almacen", unique = true, nullable = false)
	public int getCodigoAlmacen() {
		return this.codigoAlmacen;
	}

	public void setCodigoAlmacen(int codigoAlmacen) {
		this.codigoAlmacen = codigoAlmacen;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codigo_instalacion", nullable = false)
	public Instalacion getInstalacion() {
		return this.instalacion;
	}

	public void setInstalacion(Instalacion instalacion) {
		this.instalacion = instalacion;
	}

	@Column(name = "nombre", nullable = false)
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column(name = "descripcion")
	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Column(name = "capacidad", nullable = false, precision = 131089, scale = 0)
	public BigDecimal getCapacidad() {
		return this.capacidad;
	}

	public void setCapacidad(BigDecimal capacidad) {
		this.capacidad = capacidad;
	}

	@Column(name = "estatus", nullable = false, length = 1)
	public char getEstatus() {
		return this.estatus;
	}

	public void setEstatus(char estatus) {
		this.estatus = estatus;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "almacen")
	public Set<Material> getMaterials() {
		return this.materials;
	}

	public void setMaterials(Set<Material> materials) {
		this.materials = materials;
	}

}
