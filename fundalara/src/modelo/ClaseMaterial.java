package modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the clase_material database table.
 * 
 */
@Entity
@Table(name="clase_material")
public class ClaseMaterial implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="codigo_clase_material")
	private String codigoClaseMaterial;

	private String descripcion;

	private String estatus;

	//bi-directional many-to-one association to TipoMaterial
	@OneToMany(mappedBy="claseMaterial")
	private Set<TipoMaterial> tipoMaterials;

    public ClaseMaterial() {
    }

	public String getCodigoClaseMaterial() {
		return this.codigoClaseMaterial;
	}

	public void setCodigoClaseMaterial(String codigoClaseMaterial) {
		this.codigoClaseMaterial = codigoClaseMaterial;
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

	public Set<TipoMaterial> getTipoMaterials() {
		return this.tipoMaterials;
	}

	public void setTipoMaterials(Set<TipoMaterial> tipoMaterials) {
		this.tipoMaterials = tipoMaterials;
	}
	
}