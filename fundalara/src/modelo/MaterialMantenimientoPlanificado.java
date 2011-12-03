package modelo;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the material_mantenimiento_planificado database table.
 * 
 */
@Entity
@Table(name="material_mantenimiento_planificado")
public class MaterialMantenimientoPlanificado implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private MaterialMantenimientoPlanificadoPK id;

	@Column(name="cantidad_requerida")
	private Integer cantidadRequerida;

	private String estatus;

	//bi-directional many-to-one association to Material
    @ManyToOne
	@JoinColumn(name="codigo_material")
	private Material material;

	//bi-directional many-to-one association to PlanificacionMantenimiento
    @ManyToOne
	@JoinColumn(name="codigo_planificacion_mantenimiento")
	private PlanificacionMantenimiento planificacionMantenimiento;

    public MaterialMantenimientoPlanificado() {
    }

	public MaterialMantenimientoPlanificadoPK getId() {
		return this.id;
	}

	public void setId(MaterialMantenimientoPlanificadoPK id) {
		this.id = id;
	}
	
	public Integer getCantidadRequerida() {
		return this.cantidadRequerida;
	}

	public void setCantidadRequerida(Integer cantidadRequerida) {
		this.cantidadRequerida = cantidadRequerida;
	}

	public String getEstatus() {
		return this.estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

	public Material getMaterial() {
		return this.material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}
	
	public PlanificacionMantenimiento getPlanificacionMantenimiento() {
		return this.planificacionMantenimiento;
	}

	public void setPlanificacionMantenimiento(PlanificacionMantenimiento planificacionMantenimiento) {
		this.planificacionMantenimiento = planificacionMantenimiento;
	}
	
}