package modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the municipio database table.
 * 
 */
@Entity
public class Municipio implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="cod_municipio")
	private String codMunicipio;

	private String nombre;

	//bi-directional many-to-one association to EstadoVenezuela
    @ManyToOne
	@JoinColumn(name="codigo_estado")
	private EstadoVenezuela estadoVenezuela;

	//bi-directional many-to-one association to Parroquia
	@OneToMany(mappedBy="municipio")
	private Set<Parroquia> parroquias;

    public Municipio() {
    }

	public String getCodMunicipio() {
		return this.codMunicipio;
	}

	public void setCodMunicipio(String codMunicipio) {
		this.codMunicipio = codMunicipio;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public EstadoVenezuela getEstadoVenezuela() {
		return this.estadoVenezuela;
	}

	public void setEstadoVenezuela(EstadoVenezuela estadoVenezuela) {
		this.estadoVenezuela = estadoVenezuela;
	}
	
	public Set<Parroquia> getParroquias() {
		return this.parroquias;
	}

	public void setParroquias(Set<Parroquia> parroquias) {
		this.parroquias = parroquias;
	}
	
}