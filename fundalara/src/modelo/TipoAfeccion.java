package modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the tipo_afeccion database table.
 * 
 */
@Entity
@Table(name="tipo_afeccion")
public class TipoAfeccion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="codigo_afeccion")
	private String codigoAfeccion;

	private String descripcion;

	private String estatus;

	private String nombre;

	//bi-directional many-to-one association to AfeccionJugador
	@OneToMany(mappedBy="tipoAfeccion")
	private Set<AfeccionJugador> afeccionJugadors;

    public TipoAfeccion() {
    }

	public String getCodigoAfeccion() {
		return this.codigoAfeccion;
	}

	public void setCodigoAfeccion(String codigoAfeccion) {
		this.codigoAfeccion = codigoAfeccion;
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

	public Set<AfeccionJugador> getAfeccionJugadors() {
		return this.afeccionJugadors;
	}

	public void setAfeccionJugadors(Set<AfeccionJugador> afeccionJugadors) {
		this.afeccionJugadors = afeccionJugadors;
	}
	
}