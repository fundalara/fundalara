package modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the empleado_mantenimiento database table.
 * 
 */
@Entity
@Table(name="empleado_mantenimiento")
public class EmpleadoMantenimiento implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private EmpleadoMantenimientoPK id;

	private String estatus;

	//bi-directional many-to-one association to Empleado
    @ManyToOne
	@JoinColumn(name="cedula")
	private Empleado empleado;

	//bi-directional many-to-one association to Mantenimiento
    @ManyToOne
	@JoinColumn(name="codigo_mantenimiento")
	private Mantenimiento mantenimiento;

	//bi-directional many-to-one association to TareaMantenimiento
	@OneToMany(mappedBy="empleadoMantenimiento")
	private Set<TareaMantenimiento> tareaMantenimientos;

    public EmpleadoMantenimiento() {
    }

	public EmpleadoMantenimientoPK getId() {
		return this.id;
	}

	public void setId(EmpleadoMantenimientoPK id) {
		this.id = id;
	}
	
	public String getEstatus() {
		return this.estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

	public Empleado getEmpleado() {
		return this.empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}
	
	public Mantenimiento getMantenimiento() {
		return this.mantenimiento;
	}

	public void setMantenimiento(Mantenimiento mantenimiento) {
		this.mantenimiento = mantenimiento;
	}
	
	public Set<TareaMantenimiento> getTareaMantenimientos() {
		return this.tareaMantenimientos;
	}

	public void setTareaMantenimientos(Set<TareaMantenimiento> tareaMantenimientos) {
		this.tareaMantenimientos = tareaMantenimientos;
	}
	
}