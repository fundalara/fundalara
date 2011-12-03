package modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the liga database table.
 * 
 */
@Entity
public class Liga implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="codigo_liga")
	private String codigoLiga;

	private byte[] logo;

	private String nombre;

	//bi-directional many-to-many association to Categoria
    @ManyToMany
	@JoinTable(
		name="liga_categoria"
		, joinColumns={
			@JoinColumn(name="codigo_liga")
			}
		, inverseJoinColumns={
			@JoinColumn(name="codigo_categoria")
			}
		)
	private Set<Categoria> categorias;

    public Liga() {
    }

	public String getCodigoLiga() {
		return this.codigoLiga;
	}

	public void setCodigoLiga(String codigoLiga) {
		this.codigoLiga = codigoLiga;
	}

	public byte[] getLogo() {
		return this.logo;
	}

	public void setLogo(byte[] logo) {
		this.logo = logo;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Set<Categoria> getCategorias() {
		return this.categorias;
	}

	public void setCategorias(Set<Categoria> categorias) {
		this.categorias = categorias;
	}
	
}