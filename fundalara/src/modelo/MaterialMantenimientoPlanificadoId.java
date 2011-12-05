package modelo;

// Generated 05/12/2011 10:49:17 AM by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * MaterialMantenimientoPlanificadoId generated by hbm2java
 */
@Embeddable
public class MaterialMantenimientoPlanificadoId implements java.io.Serializable {

	private String codigoPlanificacionMantenimiento;
	private String codigoMaterial;

	public MaterialMantenimientoPlanificadoId() {
	}

	public MaterialMantenimientoPlanificadoId(
			String codigoPlanificacionMantenimiento, String codigoMaterial) {
		this.codigoPlanificacionMantenimiento = codigoPlanificacionMantenimiento;
		this.codigoMaterial = codigoMaterial;
	}

	@Column(name = "codigo_planificacion_mantenimiento", nullable = false)
	public String getCodigoPlanificacionMantenimiento() {
		return this.codigoPlanificacionMantenimiento;
	}

	public void setCodigoPlanificacionMantenimiento(
			String codigoPlanificacionMantenimiento) {
		this.codigoPlanificacionMantenimiento = codigoPlanificacionMantenimiento;
	}

	@Column(name = "codigo_material", nullable = false)
	public String getCodigoMaterial() {
		return this.codigoMaterial;
	}

	public void setCodigoMaterial(String codigoMaterial) {
		this.codigoMaterial = codigoMaterial;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof MaterialMantenimientoPlanificadoId))
			return false;
		MaterialMantenimientoPlanificadoId castOther = (MaterialMantenimientoPlanificadoId) other;

		return ((this.getCodigoPlanificacionMantenimiento() == castOther
				.getCodigoPlanificacionMantenimiento()) || (this
				.getCodigoPlanificacionMantenimiento() != null
				&& castOther.getCodigoPlanificacionMantenimiento() != null && this
				.getCodigoPlanificacionMantenimiento().equals(
						castOther.getCodigoPlanificacionMantenimiento())))
				&& ((this.getCodigoMaterial() == castOther.getCodigoMaterial()) || (this
						.getCodigoMaterial() != null
						&& castOther.getCodigoMaterial() != null && this
						.getCodigoMaterial().equals(
								castOther.getCodigoMaterial())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getCodigoPlanificacionMantenimiento() == null ? 0 : this
						.getCodigoPlanificacionMantenimiento().hashCode());
		result = 37
				* result
				+ (getCodigoMaterial() == null ? 0 : this.getCodigoMaterial()
						.hashCode());
		return result;
	}

}
