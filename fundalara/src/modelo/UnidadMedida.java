package modelo;

// Generated 05/12/2011 10:49:17 AM by Hibernate Tools 3.4.0.CR1

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * UnidadMedida generated by hbm2java
 */
@Entity
@Table(name = "unidad_medida")
public class UnidadMedida implements java.io.Serializable {

	private String codigoUnidadMedida;
	private String descripcion;
	private char estatus;
	private Set<Material> materials = new HashSet<Material>(0);

	public UnidadMedida() {
	}

	public UnidadMedida(String codigoUnidadMedida, String descripcion,
			char estatus) {
		this.codigoUnidadMedida = codigoUnidadMedida;
		this.descripcion = descripcion;
		this.estatus = estatus;
	}

	public UnidadMedida(String codigoUnidadMedida, String descripcion,
			char estatus, Set<Material> materials) {
		this.codigoUnidadMedida = codigoUnidadMedida;
		this.descripcion = descripcion;
		this.estatus = estatus;
		this.materials = materials;
	}

	@Id
	@Column(name = "codigo_unidad_medida", unique = true, nullable = false)
	public String getCodigoUnidadMedida() {
		return this.codigoUnidadMedida;
	}

	public void setCodigoUnidadMedida(String codigoUnidadMedida) {
		this.codigoUnidadMedida = codigoUnidadMedida;
	}

	@Column(name = "descripcion", nullable = false)
	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Column(name = "estatus", nullable = false, length = 1)
	public char getEstatus() {
		return this.estatus;
	}

	public void setEstatus(char estatus) {
		this.estatus = estatus;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "unidadMedida")
	public Set<Material> getMaterials() {
		return this.materials;
	}

	public void setMaterials(Set<Material> materials) {
		this.materials = materials;
	}

}
