package modelo;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the material_mantenimiento database table.
 * 
 */
@Embeddable
public class MaterialMantenimientoPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="codigo_mantenimiento")
	private String codigoMantenimiento;

	@Column(name="codigo_material")
	private String codigoMaterial;

    public MaterialMantenimientoPK() {
    }
	public String getCodigoMantenimiento() {
		return this.codigoMantenimiento;
	}
	public void setCodigoMantenimiento(String codigoMantenimiento) {
		this.codigoMantenimiento = codigoMantenimiento;
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
		if (!(other instanceof MaterialMantenimientoPK)) {
			return false;
		}
		MaterialMantenimientoPK castOther = (MaterialMantenimientoPK)other;
		return 
			this.codigoMantenimiento.equals(castOther.codigoMantenimiento)
			&& this.codigoMaterial.equals(castOther.codigoMaterial);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.codigoMantenimiento.hashCode();
		hash = hash * prime + this.codigoMaterial.hashCode();
		
		return hash;
    }
}