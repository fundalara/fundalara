package modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the estado database table.
 * 
 */
@Entity
public class Estado implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="codigo_estado")
	private String codigoEstado;

	private String descripcion;

	private String estatus;

	//bi-directional many-to-one association to EstadoMantenimiento
	@OneToMany(mappedBy="estado")
	private Set<EstadoMantenimiento> estadoMantenimientos;

    public Estado() {
    }

	public String getCodigoEstado() {
		return this.codigoEstado;
	}

	public void setCodigoEstado(String codigoEstado) {
		this.codigoEstado = codigoEstado;
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

	public Set<EstadoMantenimiento> getEstadoMantenimientos() {
		return this.estadoMantenimientos;
	}

	public void setEstadoMantenimientos(Set<EstadoMantenimiento> estadoMantenimientos) {
		this.estadoMantenimientos = estadoMantenimientos;
	}
	
}