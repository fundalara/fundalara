package modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.Set;


/**
 * The persistent class for the cargo database table.
 * 
 */
@Entity
public class Cargo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="codigo_cargo")
	private String codigoCargo;

	private String descripcion;

	private String estatus;

    @Temporal( TemporalType.DATE)
	@Column(name="fecha_actualizacion")
	private Date fechaActualizacion;

	//bi-directional many-to-one association to EmpleadoCargo
	@OneToMany(mappedBy="cargo")
	private Set<EmpleadoCargo> empleadoCargos;

    public Cargo() {
    }

	public String getCodigoCargo() {
		return this.codigoCargo;
	}

	public void setCodigoCargo(String codigoCargo) {
		this.codigoCargo = codigoCargo;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getEstatus() {
		return this.estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

	public Date getFechaActualizacion() {
		return this.fechaActualizacion;
	}

	public void setFechaActualizacion(Date fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}

	public Set<EmpleadoCargo> getEmpleadoCargos() {
		return this.empleadoCargos;
	}

	public void setEmpleadoCargos(Set<EmpleadoCargo> empleadoCargos) {
		this.empleadoCargos = empleadoCargos;
	}
	
}