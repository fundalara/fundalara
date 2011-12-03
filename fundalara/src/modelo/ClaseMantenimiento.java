package modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the clase_mantenimiento database table.
 * 
 */
@Entity
@Table(name="clase_mantenimiento")
public class ClaseMantenimiento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="codigo_clase_mantenimiento")
	private String codigoClaseMantenimiento;

	private String descripcion;

	private String estatus;

	//bi-directional many-to-one association to TipoMantenimiento
	@OneToMany(mappedBy="claseMantenimiento")
	private Set<TipoMantenimiento> tipoMantenimientos;

    public ClaseMantenimiento() {
    }

	public String getCodigoClaseMantenimiento() {
		return this.codigoClaseMantenimiento;
	}

	public void setCodigoClaseMantenimiento(String codigoClaseMantenimiento) {
		this.codigoClaseMantenimiento = codigoClaseMantenimiento;
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

	public Set<TipoMantenimiento> getTipoMantenimientos() {
		return this.tipoMantenimientos;
	}

	public void setTipoMantenimientos(Set<TipoMantenimiento> tipoMantenimientos) {
		this.tipoMantenimientos = tipoMantenimientos;
	}
	
}