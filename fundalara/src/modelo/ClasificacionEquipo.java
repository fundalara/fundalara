package modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the clasificacion_equipo database table.
 * 
 */
@Entity
@Table(name="clasificacion_equipo")
public class ClasificacionEquipo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="codigo_clasificacion")
	private String codigoClasificacion;

	private String descripcion;

	private String estatus;

	private String nombre;

	//bi-directional many-to-one association to Equipo
	@OneToMany(mappedBy="clasificacionEquipo")
	private Set<Equipo> equipos;

    public ClasificacionEquipo() {
    }

	public String getCodigoClasificacion() {
		return this.codigoClasificacion;
	}

	public void setCodigoClasificacion(String codigoClasificacion) {
		this.codigoClasificacion = codigoClasificacion;
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

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Set<Equipo> getEquipos() {
		return this.equipos;
	}

	public void setEquipos(Set<Equipo> equipos) {
		this.equipos = equipos;
	}
	
}