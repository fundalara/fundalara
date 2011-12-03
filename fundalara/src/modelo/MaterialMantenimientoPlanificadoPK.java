package modelo;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the material_mantenimiento_planificado database table.
 * 
 */
@Embeddable
public class MaterialMantenimientoPlanificadoPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="codigo_planificacion_mantenimiento")
	private String codigoPlanificacionMantenimiento;

	@Column(name="codigo_material")
	private String codigoMaterial;

    public MaterialMantenimientoPlanificadoPK() {
    }
	public String getCodigoPlanificacionMantenimiento() {
		return this.codigoPlanificacionMantenimiento;
	}
	public void setCodigoPlanificacionMantenimiento(String codigoPlanificacionMantenimiento) {
		this.codigoPlanificacionMantenimiento = codigoPlanificacionMantenimiento;
	}
	public String getCodigoMaterial() {
		return this.codigoMaterial;
	}
	public void setCodigoMaterial(String codigoMaterial) {
		this.codigoMaterial = codigoMaterial;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof MaterialMantenimientoPlanificadoPK)) {
			return false;
		}
		MaterialMantenimientoPlanificadoPK castOther = (MaterialMantenimientoPlanificadoPK)other;
		return 
			this.codigoPlanificacionMantenimiento.equals(castOther.codigoPlanificacionMantenimiento)
			&& this.codigoMaterial.equals(castOther.codigoMaterial);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.codigoPlanificacionMantenimiento.hashCode();
		hash = hash * prime + this.codigoMaterial.hashCode();
		
		return hash;
    }
}