package modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the talla database table.
 * 
 */
@Entity
public class Talla implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="codigo_talla")
	private String codigoTalla;

	private String estatus;

	private String talla;

	//bi-directional many-to-one association to Jugador
	@OneToMany(mappedBy="talla")
	private Set<Jugador> jugadors;

	//bi-directional many-to-one association to TipoIndumentaria
    @ManyToOne
	@JoinColumn(name="codigo_indumentaria")
	private TipoIndumentaria tipoIndumentaria;

    public Talla() {
    }

	public String getCodigoTalla() {
		return this.codigoTalla;
	}

	public void setCodigoTalla(String codigoTalla) {
		this.codigoTalla = codigoTalla;
	}

	public String getEstatus() {
		return this.estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

	public String getTalla() {
		return this.talla;
	}

	public void setTalla(String talla) {
		this.talla = talla;
	}

	public Set<Jugador> getJugadors() {
		return this.jugadors;
	}

	public void setJugadors(Set<Jugador> jugadors) {
		this.jugadors = jugadors;
	}
	
	public TipoIndumentaria getTipoIndumentaria() {
		return this.tipoIndumentaria;
	}

	public void setTipoIndumentaria(TipoIndumentaria tipoIndumentaria) {
		this.tipoIndumentaria = tipoIndumentaria;
	}
	
}