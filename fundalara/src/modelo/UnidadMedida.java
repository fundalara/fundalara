package modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the unidad_medida database table.
 * 
 */
@Entity
@Table(name="unidad_medida")
public class UnidadMedida implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="codigo_unidad_medida")
	private String codigoUnidadMedida;

	private String descripcion;

	private String estatus;

	//bi-directional many-to-one association to Material
	@OneToMany(mappedBy="unidadMedida")
	private Set<Material> materials;

    public UnidadMedida() {
    }

	public String getCodigoUnidadMedida() {
		return this.codigoUnidadMedida;
	}

	public void setCodigoUnidadMedida(String codigoUnidadMedida) {
		this.codigoUnidadMedida = codigoUnidadMedida;
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

	public Set<Material> getMaterials() {
		return this.materials;
	}

	public void setMaterials(Set<Material> materials) {
		this.materials = materials;
	}
	
}