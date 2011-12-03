package modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the tipo_mantenimiento database table.
 * 
 */
@Entity
@Table(name="tipo_mantenimiento")
public class TipoMantenimiento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="codigo_tipo_mantenimiento")
	private String codigoTipoMantenimiento;

	private String descripcion;

	private String estatus;

	//bi-directional many-to-one association to PlanificacionMantenimiento
	@OneToMany(mappedBy="tipoMantenimiento")
	private Set<PlanificacionMantenimiento> planificacionMantenimientos;

	//bi-directional many-to-one association to ClaseMantenimiento
    @ManyToOne
	@JoinColumn(name="codigo_clase_mantenimiento")
	private ClaseMantenimiento claseMantenimiento;

    public TipoMantenimiento() {
    }

	public String getCodigoTipoMantenimiento() {
		return this.codigoTipoMantenimiento;
	}

	public void setCodigoTipoMantenimiento(String codigoTipoMantenimiento) {
		this.codigoTipoMantenimiento = codigoTipoMantenimiento;
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

	public Set<PlanificacionMantenimiento> getPlanificacionMantenimientos() {
		return this.planificacionMantenimientos;
	}

	public void setPlanificacionMantenimientos(Set<PlanificacionMantenimiento> planificacionMantenimientos) {
		this.planificacionMantenimientos = planificacionMantenimientos;
	}
	
	public ClaseMantenimiento getClaseMantenimiento() {
		return this.claseMantenimiento;
	}

	public void setClaseMantenimiento(ClaseMantenimiento claseMantenimiento) {
		this.claseMantenimiento = claseMantenimiento;
	}
	
}