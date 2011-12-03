package modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the empleado_mantenimiento_planificado database table.
 * 
 */
@Entity
@Table(name="empleado_mantenimiento_planificado")
public class EmpleadoMantenimientoPlanificado implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private EmpleadoMantenimientoPlanificadoPK id;

	private String estatus;

	//bi-directional many-to-one association to Empleado
    @ManyToOne
	@JoinColumn(name="cedula")
	private Empleado empleado;

	//bi-directional many-to-one association to PlanificacionMantenimiento
    @ManyToOne
	@JoinColumn(name="codigo_planificacion_mantenimiento")
	private PlanificacionMantenimiento planificacionMantenimiento;

	//bi-directional many-to-one association to TareaMantenimientoPlanificada
	@OneToMany(mappedBy="empleadoMantenimientoPlanificado")
	private Set<TareaMantenimientoPlanificada> tareaMantenimientoPlanificadas;

    public EmpleadoMantenimientoPlanificado() {
    }

	public EmpleadoMantenimientoPlanificadoPK getId() {
		return this.id;
	}

	public void setId(EmpleadoMantenimientoPlanificadoPK id) {
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
	
	public PlanificacionMantenimiento getPlanificacionMantenimiento() {
		return this.planificacionMantenimiento;
	}

	public void setPlanificacionMantenimiento(PlanificacionMantenimiento planificacionMantenimiento) {
		this.planificacionMantenimiento = planificacionMantenimiento;
	}
	
	public Set<TareaMantenimientoPlanificada> getTareaMantenimientoPlanificadas() {
		return this.tareaMantenimientoPlanificadas;
	}

	public void setTareaMantenimientoPlanificadas(Set<TareaMantenimientoPlanificada> tareaMantenimientoPlanificadas) {
		this.tareaMantenimientoPlanificadas = tareaMantenimientoPlanificadas;
	}
	
}