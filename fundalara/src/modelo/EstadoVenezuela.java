package modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the estado_venezuela database table.
 * 
 */
@Entity
@Table(name="estado_venezuela")
public class EstadoVenezuela implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="codigo_estado")
	private String codigoEstado;

	private String nombre;

	//bi-directional many-to-one association to Municipio
	@OneToMany(mappedBy="estadoVenezuela")
	private Set<Municipio> municipios;

    public EstadoVenezuela() {
    }

	public String getCodigoEstado() {
		return this.codigoEstado;
	}

	public void setCodigoEstado(String codigoEstado) {
		this.codigoEstado = codigoEstado;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Set<Municipio> getMunicipios() {
		return this.municipios;
	}

	public void setMunicipios(Set<Municipio> municipios) {
		this.municipios = municipios;
	}
	
}