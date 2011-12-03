package modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the instalacion database table.
 * 
 */
@Entity
public class Instalacion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="codigo_instalacion")
	private String codigoInstalacion;

	private String descripcion;

	private String estatus;

	//bi-directional many-to-one association to TipoInstalacion
    @ManyToOne
	@JoinColumn(name="codigo_tipo_instalacion")
	private TipoInstalacion tipoInstalacion;

	//bi-directional many-to-one association to PlanificacionMantenimiento
	@OneToMany(mappedBy="instalacion")
	private Set<PlanificacionMantenimiento> planificacionMantenimientos;

    public Instalacion() {
    }

	public String getCodigoInstalacion() {
		return this.codigoInstalacion;
	}

	public void setCodigoInstalacion(String codigoInstalacion) {
		this.codigoInstalacion = codigoInstalacion;
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

	public TipoInstalacion getTipoInstalacion() {
		return this.tipoInstalacion;
	}

	public void setTipoInstalacion(TipoInstalacion tipoInstalacion) {
		this.tipoInstalacion = tipoInstalacion;
	}
	
	public Set<PlanificacionMantenimiento> getPlanificacionMantenimientos() {
		return this.planificacionMantenimientos;
	}

	public void setPlanificacionMantenimientos(Set<PlanificacionMantenimiento> planificacionMantenimientos) {
		this.planificacionMantenimientos = planificacionMantenimientos;
	}
	
}