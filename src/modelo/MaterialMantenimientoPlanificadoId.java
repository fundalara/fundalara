package modelo;

// Generated 11/12/2011 04:17:03 PM by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * MaterialMantenimientoPlanificadoId generated by hbm2java
 */
@Embeddable
public class MaterialMantenimientoPlanificadoId implements java.io.Serializable {

	private String codigoMaterial;
	private String codigoPlanificacionMantenimiento;

	public MaterialMantenimientoPlanificadoId() {
	}

	public MaterialMantenimientoPlanificadoId(String codigoMaterial,
			String codigoPlanificacionMantenimiento) {
		this.codigoMaterial = codigoMaterial;
		this.codigoPlanificacionMantenimiento = codigoPlanificacionMantenimiento;
	}

	@Column(name = "codigo_material", nullable = false)
	public String getCodigoMaterial() {
		return this.codigoMaterial;
	}

	public void setCodigoMaterial(String codigoMaterial) {
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

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof MaterialMantenimientoPlanificadoId))
			return false;
		MaterialMantenimientoPlanificadoId castOther = (MaterialMantenimientoPlanificadoId) other;

		return ((this.getCodigoMaterial() == castOther.getCodigoMaterial()) || (this
				.getCodigoMaterial() != null
				&& castOther.getCodigoMaterial() != null && this
				.getCodigoMaterial().equals(castOther.getCodigoMaterial())))
				&& ((this.getCodigoPlanificacionMantenimiento() == castOther
						.getCodigoPlanificacionMantenimiento()) || (this
						.getCodigoPlanificacionMantenimiento() != null
						&& castOther.getCodigoPlanificacionMantenimiento() != null && this
						.getCodigoPlanificacionMantenimiento()
						.equals(castOther.getCodigoPlanificacionMantenimiento())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getCodigoMaterial() == null ? 0 : this.getCodigoMaterial()
						.hashCode());
		result = 37
				* result
				+ (getCodigoPlanificacionMantenimiento() == null ? 0 : this
						.getCodigoPlanificacionMantenimiento().hashCode());
		return result;
	}

}
