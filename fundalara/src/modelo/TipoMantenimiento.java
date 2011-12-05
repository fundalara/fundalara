package modelo;

// Generated 05/12/2011 10:49:17 AM by Hibernate Tools 3.4.0.CR1

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * TipoMantenimiento generated by hbm2java
 */
@Entity
@Table(name = "tipo_mantenimiento")
public class TipoMantenimiento implements java.io.Serializable {

	private String codigoTipoMantenimiento;
	private ClaseMantenimiento claseMantenimiento;
	private char estatus;
	private String descripcion;
	private Set<PlanificacionMantenimiento> planificacionMantenimientos = new HashSet<PlanificacionMantenimiento>(
			0);

	public TipoMantenimiento() {
	}

	public TipoMantenimiento(String codigoTipoMantenimiento,
			ClaseMantenimiento claseMantenimiento, char estatus,
			String descripcion) {
		this.codigoTipoMantenimiento = codigoTipoMantenimiento;
		this.claseMantenimiento = claseMantenimiento;
		this.estatus = estatus;
		this.descripcion = descripcion;
	}

	public TipoMantenimiento(String codigoTipoMantenimiento,
			ClaseMantenimiento claseMantenimiento, char estatus,
			String descripcion,
			Set<PlanificacionMantenimiento> planificacionMantenimientos) {
		this.codigoTipoMantenimiento = codigoTipoMantenimiento;
		this.claseMantenimiento = claseMantenimiento;
		this.estatus = estatus;
		this.descripcion = descripcion;
		this.planificacionMantenimientos = planificacionMantenimientos;
	}

	@Id
	@Column(name = "codigo_tipo_mantenimiento", unique = true, nullable = false)
	public String getCodigoTipoMantenimiento() {
		return this.codigoTipoMantenimiento;
	}

	public void setCodigoTipoMantenimiento(String codigoTipoMantenimiento) {
		this.codigoTipoMantenimiento = codigoTipoMantenimiento;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codigo_clase_mantenimiento", nullable = false)
	public ClaseMantenimiento getClaseMantenimiento() {
		return this.claseMantenimiento;
	}

	public void setClaseMantenimiento(ClaseMantenimiento claseMantenimiento) {
		this.claseMantenimiento = claseMantenimiento;
	}

	@Column(name = "estatus", nullable = false, length = 1)
	public char getEstatus() {
		return this.estatus;
	}

	public void setEstatus(char estatus) {
		this.estatus = estatus;
	}

	@Column(name = "descripcion", nullable = false)
	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tipoMantenimiento")
	public Set<PlanificacionMantenimiento> getPlanificacionMantenimientos() {
		return this.planificacionMantenimientos;
	}

	public void setPlanificacionMantenimientos(
			Set<PlanificacionMantenimiento> planificacionMantenimientos) {
		this.planificacionMantenimientos = planificacionMantenimientos;
	}

}
