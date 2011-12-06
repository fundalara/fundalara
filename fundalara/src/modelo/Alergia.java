package modelo;

// Generated 06/12/2011 05:27:34 PM by Hibernate Tools 3.4.0.CR1

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Alergia generated by hbm2java
 */
@Entity
@Table(name = "alergia")
public class Alergia implements java.io.Serializable {

	private String codigoAlergia;
	private String descripcion;
	private char estatus;
	private Set<EmpleadoAlergia> empleadoAlergias = new HashSet<EmpleadoAlergia>(
			0);
	private Set<EmpleadoAlergia> empleadoAlergias_1 = new HashSet<EmpleadoAlergia>(
			0);

	public Alergia() {
	}

	public Alergia(String codigoAlergia, String descripcion, char estatus) {
		this.codigoAlergia = codigoAlergia;
		this.descripcion = descripcion;
		this.estatus = estatus;
	}

	public Alergia(String codigoAlergia, String descripcion, char estatus,
			Set<EmpleadoAlergia> empleadoAlergias,
			Set<EmpleadoAlergia> empleadoAlergias_1) {
		this.codigoAlergia = codigoAlergia;
		this.descripcion = descripcion;
		this.estatus = estatus;
		this.empleadoAlergias = empleadoAlergias;
		this.empleadoAlergias_1 = empleadoAlergias_1;
	}

	@Id
	@Column(name = "codigo_alergia", unique = true, nullable = false)
	public String getCodigoAlergia() {
		return this.codigoAlergia;
	}

	public void setCodigoAlergia(String codigoAlergia) {
		this.codigoAlergia = codigoAlergia;
	}

	@Column(name = "descripcion", nullable = false)
	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Column(name = "estatus", nullable = false, length = 1)
	public char getEstatus() {
		return this.estatus;
	}

	public void setEstatus(char estatus) {
		this.estatus = estatus;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "alergia")
	public Set<EmpleadoAlergia> getEmpleadoAlergias() {
		return this.empleadoAlergias;
	}

	public void setEmpleadoAlergias(Set<EmpleadoAlergia> empleadoAlergias) {
		this.empleadoAlergias = empleadoAlergias;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "alergia")
	public Set<EmpleadoAlergia> getEmpleadoAlergias_1() {
		return this.empleadoAlergias_1;
	}

	public void setEmpleadoAlergias_1(Set<EmpleadoAlergia> empleadoAlergias_1) {
		this.empleadoAlergias_1 = empleadoAlergias_1;
	}

}
