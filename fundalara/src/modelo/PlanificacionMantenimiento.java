package modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the planificacion_mantenimiento database table.
 * 
 */
@Entity
@Table(name="planificacion_mantenimiento")
public class PlanificacionMantenimiento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="codigo_planificacion_mantenimiento")
	private String codigoPlanificacionMantenimiento;

	private String estatus;

	@Column(name="mantenimiento_periodico")
	private Boolean mantenimientoPeriodico;

	@Column(name="mantenimiento_plantilla")
	private Boolean mantenimientoPlantilla;

	//bi-directional many-to-one association to EmpleadoMantenimientoPlanificado
	@OneToMany(mappedBy="planificacionMantenimiento")
	private Set<EmpleadoMantenimientoPlanificado> empleadoMantenimientoPlanificados;

	//bi-directional many-to-one association to Mantenimiento
	@OneToMany(mappedBy="planificacionMantenimiento")
	private Set<Mantenimiento> mantenimientos;

	//bi-directional many-to-one association to MaterialMantenimientoPlanificado
	@OneToMany(mappedBy="planificacionMantenimiento")
	private Set<MaterialMantenimientoPlanificado> materialMantenimientoPlanificados;

	//bi-directional many-to-one association to Periodicidad
	@OneToMany(mappedBy="planificacionMantenimiento")
	private Set<Periodicidad> periodicidads;

	//bi-directional many-to-one association to Instalacion
    @ManyToOne
	@JoinColumn(name="codigo_instalacion")
	private Instalacion instalacion;

	//bi-directional many-to-one association to TipoMantenimiento
    @ManyToOne
	@JoinColumn(name="codigo_tipo_mantenimiento")
	private TipoMantenimiento tipoMantenimiento;

	//bi-directional many-to-one association to TareaMantenimientoPlanificada
	@OneToMany(mappedBy="planificacionMantenimiento")
	private Set<TareaMantenimientoPlanificada> tareaMantenimientoPlanificadas;

    public PlanificacionMantenimiento() {
    }

	public String getCodigoPlanificacionMantenimiento() {
		return this.codigoPlanificacionMantenimiento;
	}

	public void setCodigoPlanificacionMantenimiento(String codigoPlanificacionMantenimiento) {
		this.codigoPlanificacionMantenimiento = codigoPlanificacionMantenimiento;
	}

	public String getEstatus() {
		return this.estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

	public Boolean getMantenimientoPeriodico() {
		return this.mantenimientoPeriodico;
	}

	public void setMantenimientoPeriodico(Boolean mantenimientoPeriodico) {
		this.mantenimientoPeriodico = mantenimientoPeriodico;
	}

	public Boolean getMantenimientoPlantilla() {
		return this.mantenimientoPlantilla;
	}

	public void setMantenimientoPlantilla(Boolean mantenimientoPlantilla) {
		this.mantenimientoPlantilla = mantenimientoPlantilla;
	}

	public Set<EmpleadoMantenimientoPlanificado> getEmpleadoMantenimientoPlanificados() {
		return this.empleadoMantenimientoPlanificados;
	}

	public void setEmpleadoMantenimientoPlanificados(Set<EmpleadoMantenimientoPlanificado> empleadoMantenimientoPlanificados) {
		this.empleadoMantenimientoPlanificados = empleadoMantenimientoPlanificados;
	}
	
	public Set<Mantenimiento> getMantenimientos() {
		return this.mantenimientos;
	}

	public void setMantenimientos(Set<Mantenimiento> mantenimientos) {
		this.mantenimientos = mantenimientos;
	}
	
	public Set<MaterialMantenimientoPlanificado> getMaterialMantenimientoPlanificados() {
		return this.materialMantenimientoPlanificados;
	}

	public void setMaterialMantenimientoPlanificados(Set<MaterialMantenimientoPlanificado> materialMantenimientoPlanificados) {
		this.materialMantenimientoPlanificados = materialMantenimientoPlanificados;
	}
	
	public Set<Periodicidad> getPeriodicidads() {
		return this.periodicidads;
	}

	public void setPeriodicidads(Set<Periodicidad> periodicidads) {
		this.periodicidads = periodicidads;
	}
	
	public Instalacion getInstalacion() {
		return this.instalacion;
	}

	public void setInstalacion(Instalacion instalacion) {
		this.instalacion = instalacion;
	}
	
	public TipoMantenimiento getTipoMantenimiento() {
		return this.tipoMantenimiento;
	}

	public void setTipoMantenimiento(TipoMantenimiento tipoMantenimiento) {
		this.tipoMantenimiento = tipoMantenimiento;
	}
	
	public Set<TareaMantenimientoPlanificada> getTareaMantenimientoPlanificadas() {
		return this.tareaMantenimientoPlanificadas;
	}

	public void setTareaMantenimientoPlanificadas(Set<TareaMantenimientoPlanificada> tareaMantenimientoPlanificadas) {
		this.tareaMantenimientoPlanificadas = tareaMantenimientoPlanificadas;
	}
	
}