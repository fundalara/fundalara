package modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.Set;


/**
 * The persistent class for the mantenimiento database table.
 * 
 */
@Entity
public class Mantenimiento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="codigo_mantenimiento")
	private String codigoMantenimiento;

	private String estatus;

    @Temporal( TemporalType.DATE)
	@Column(name="fecha_culminacion")
	private Date fechaCulminacion;

    @Temporal( TemporalType.DATE)
	@Column(name="fecha_inicio")
	private Date fechaInicio;

	//bi-directional many-to-one association to EmpleadoMantenimiento
	@OneToMany(mappedBy="mantenimiento")
	private Set<EmpleadoMantenimiento> empleadoMantenimientos;

	//bi-directional many-to-one association to EstadoMantenimiento
	@OneToMany(mappedBy="mantenimiento")
	private Set<EstadoMantenimiento> estadoMantenimientos;

	//bi-directional many-to-one association to PlanificacionMantenimiento
    @ManyToOne
	@JoinColumn(name="codigo_planificacion_mantenimiento")
	private PlanificacionMantenimiento planificacionMantenimiento;

	//bi-directional many-to-one association to MaterialMantenimiento
	@OneToMany(mappedBy="mantenimiento")
	private Set<MaterialMantenimiento> materialMantenimientos;

	//bi-directional many-to-one association to TareaMantenimiento
	@OneToMany(mappedBy="mantenimiento")
	private Set<TareaMantenimiento> tareaMantenimientos;

    public Mantenimiento() {
    }

	public String getCodigoMantenimiento() {
		return this.codigoMantenimiento;
	}

	public void setCodigoMantenimiento(String codigoMantenimiento) {
		this.codigoMantenimiento = codigoMantenimiento;
	}

	public String getEstatus() {
		return this.estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

	public Date getFechaCulminacion() {
		return this.fechaCulminacion;
	}

	public void setFechaCulminacion(Date fechaCulminacion) {
		this.fechaCulminacion = fechaCulminacion;
	}

	public Date getFechaInicio() {
		return this.fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Set<EmpleadoMantenimiento> getEmpleadoMantenimientos() {
		return this.empleadoMantenimientos;
	}

	public void setEmpleadoMantenimientos(Set<EmpleadoMantenimiento> empleadoMantenimientos) {
		this.empleadoMantenimientos = empleadoMantenimientos;
	}
	
	public Set<EstadoMantenimiento> getEstadoMantenimientos() {
		return this.estadoMantenimientos;
	}

	public void setEstadoMantenimientos(Set<EstadoMantenimiento> estadoMantenimientos) {
		this.estadoMantenimientos = estadoMantenimientos;
	}
	
	public PlanificacionMantenimiento getPlanificacionMantenimiento() {
		return this.planificacionMantenimiento;
	}

	public void setPlanificacionMantenimiento(PlanificacionMantenimiento planificacionMantenimiento) {
		this.planificacionMantenimiento = planificacionMantenimiento;
	}
	
	public Set<MaterialMantenimiento> getMaterialMantenimientos() {
		return this.materialMantenimientos;
	}

	public void setMaterialMantenimientos(Set<MaterialMantenimiento> materialMantenimientos) {
		this.materialMantenimientos = materialMantenimientos;
	}
	
	public Set<TareaMantenimiento> getTareaMantenimientos() {
		return this.tareaMantenimientos;
	}

	public void setTareaMantenimientos(Set<TareaMantenimiento> tareaMantenimientos) {
		this.tareaMantenimientos = tareaMantenimientos;
	}
	
}