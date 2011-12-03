package modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the tipo_material database table.
 * 
 */
@Entity
@Table(name="tipo_material")
public class TipoMaterial implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="codigo_tipo_material")
	private String codigoTipoMaterial;

	private String descripcion;

	private String estatus;

	//bi-directional many-to-one association to Material
	@OneToMany(mappedBy="tipoMaterial")
	private Set<Material> materials;

	//bi-directional many-to-one association to ClaseMaterial
    @ManyToOne
	@JoinColumn(name="codigo_clase_material")
	private ClaseMaterial claseMaterial;

    public TipoMaterial() {
    }

	public String getCodigoTipoMaterial() {
		return this.codigoTipoMaterial;
	}

	public void setCodigoTipoMaterial(String codigoTipoMaterial) {
		this.codigoTipoMaterial = codigoTipoMaterial;
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
	
	public ClaseMaterial getClaseMaterial() {
		return this.claseMaterial;
	}

	public void setClaseMaterial(ClaseMaterial claseMaterial) {
		this.claseMaterial = claseMaterial;
	}
	
}