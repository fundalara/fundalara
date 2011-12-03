package modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the tipo_indumentaria database table.
 * 
 */
@Entity
@Table(name="tipo_indumentaria")
public class TipoIndumentaria implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="codigo_indumentaria")
	private String codigoIndumentaria;

	private String estatus;

	private String nombre;

	//bi-directional many-to-one association to Talla
	@OneToMany(mappedBy="tipoIndumentaria")
	private Set<Talla> tallas;

    public TipoIndumentaria() {
    }

	public String getCodigoIndumentaria() {
		return this.codigoIndumentaria;
	}

	public void setCodigoIndumentaria(String codigoIndumentaria) {
		this.codigoIndumentaria = codigoIndumentaria;
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

	public Set<Talla> getTallas() {
		return this.tallas;
	}

	public void setTallas(Set<Talla> tallas) {
		this.tallas = tallas;
	}
	
}