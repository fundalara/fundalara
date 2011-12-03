package modelo;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the empleado_sueldo database table.
 * 
 */
@Entity
@Table(name="empleado_sueldo")
public class EmpleadoSueldo implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private EmpleadoSueldoPK id;

	private String estatus;

	private double monto;

	//bi-directional many-to-one association to Empleado
    @ManyToOne
	@JoinColumn(name="cedula")
	private Empleado empleado;

    public EmpleadoSueldo() {
    }

	public EmpleadoSueldoPK getId() {
		return this.id;
	}

	public void setId(EmpleadoSueldoPK id) {
		this.id = id;
	}
	
	public String getEstatus() {
		return this.estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

	public double getMonto() {
		return this.monto;
	}

	public void setMonto(double monto) {
		this.monto = monto;
	}

	public Empleado getEmpleado() {
		return this.empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}
	
}