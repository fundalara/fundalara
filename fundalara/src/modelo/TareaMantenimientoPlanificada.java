package modelo;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tarea_mantenimiento_planificada database table.
 * 
 */
@Entity
@Table(name="tarea_mantenimiento_planificada")
public class TareaMantenimientoPlanificada implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private TareaMantenimientoPlanificadaPK id;

	private String estatus;

	//bi-directional many-to-one association to EmpleadoMantenimientoPlanificado
    @ManyToOne
	@JoinColumns({
		@JoinColumn(name="cedula", referencedColumnName="codigo_planificacion_mantenimiento"),
		@JoinColumn(name="codigo_planificacion_mantenimiento", referencedColumnName="cedula")
		})
	private EmpleadoMantenimientoPlanificado empleadoMantenimientoPlanificado;

	//bi-directional many-to-one association to PlanificacionMantenimiento
    @ManyToOne
	@JoinColumn(name="codigo_planificacion_mantenimiento")
	private PlanificacionMantenimiento planificacionMantenimiento;

	//bi-directional many-to-one association to Tarea
    @ManyToOne
	@JoinColumn(name="codigo_tarea")
	private Tarea tarea;

    public TareaMantenimientoPlanificada() {
    }

	public TareaMantenimientoPlanificadaPK getId() {
		return this.id;
	}

	public void setId(TareaMantenimientoPlanificadaPK id) {
		this.id = id;
	}
	
	public String getEstatus() {
		return this.estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

	public EmpleadoMantenimientoPlanificado getEmpleadoMantenimientoPlanificado() {
		return this.empleadoMantenimientoPlanificado;
	}

	public void setEmpleadoMantenimientoPlanificado(EmpleadoMantenimientoPlanificado empleadoMantenimientoPlanificado) {
		this.empleadoMantenimientoPlanificado = empleadoMantenimientoPlanificado;
	}
	
	public PlanificacionMantenimiento getPlanificacionMantenimiento() {
		return this.planificacionMantenimiento;
	}

	public void setPlanificacionMantenimiento(PlanificacionMantenimiento planificacionMantenimiento) {
		this.planificacionMantenimiento = planificacionMantenimiento;
	}
	
	public Tarea getTarea() {
		return this.tarea;
	}

	public void setTarea(Tarea tarea) {
		this.tarea = tarea;
	}
	
}