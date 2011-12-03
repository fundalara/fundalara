package modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the alergia database table.
 * 
 */
@Entity
public class Alergia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="codigo_alergia")
	private String codigoAlergia;

	private String descripcion;

	private String estatus;

	//bi-directional many-to-one association to EmpleadoAlergia
	@OneToMany(mappedBy="alergia")
	private Set<EmpleadoAlergia> empleadoAlergias;

    public Alergia() {
    }

	public String getCodigoAlergia() {
		return this.codigoAlergia;
	}

	public void setCodigoAlergia(String codigoAlergia) {
		this.codigoAlergia = codigoAlergia;
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

	public Set<EmpleadoAlergia> getEmpleadoAlergias() {
		return this.empleadoAlergias;
	}

	public void setEmpleadoAlergias(Set<EmpleadoAlergia> empleadoAlergias) {
		this.empleadoAlergias = empleadoAlergias;
	}
	
}