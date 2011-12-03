package modelo;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the empleado_alergia database table.
 * 
 */
@Entity
@Table(name="empleado_alergia")
public class EmpleadoAlergia implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private EmpleadoAlergiaPK id;

	private String estatus;

	//bi-directional many-to-one association to Alergia
    @ManyToOne
	@JoinColumn(name="codigo_alergia")
	private Alergia alergia;

	//bi-directional many-to-one association to Empleado
    @ManyToOne
	@JoinColumn(name="cedula")
	private Empleado empleado;

    public EmpleadoAlergia() {
    }

	public EmpleadoAlergiaPK getId() {
		return this.id;
	}

	public void setId(EmpleadoAlergiaPK id) {
		this.id = id;
	}
	
	public String getEstatus() {
		return this.estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

	public Alergia getAlergia() {
		return this.alergia;
	}

	public void setAlergia(Alergia alergia) {
		this.alergia = alergia;
	}
	
	public Empleado getEmpleado() {
		return this.empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}
	
}