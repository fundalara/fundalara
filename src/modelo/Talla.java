package modelo;

// Generated 14/12/2011 05:11:39 PM by Hibernate Tools 3.4.0.CR1

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Talla generated by hbm2java
 */
@Entity
@Table(name = "talla")
public class Talla implements java.io.Serializable {

	private String codigoTalla;
	private TipoIndumentaria tipoIndumentaria;
	private String talla;
	private char estatus;
	private Set<Jugador> jugadors = new HashSet<Jugador>(0);
	private Set<Jugador> jugadors_1 = new HashSet<Jugador>(0);

	public Talla() {
	}

	public Talla(String codigoTalla, TipoIndumentaria tipoIndumentaria,
			String talla, char estatus) {
		this.codigoTalla = codigoTalla;
		this.tipoIndumentaria = tipoIndumentaria;
		this.talla = talla;
		this.estatus = estatus;
	}

	public Talla(String codigoTalla, TipoIndumentaria tipoIndumentaria,
			String talla, char estatus, Set<Jugador> jugadors,
			Set<Jugador> jugadors_1) {
		this.codigoTalla = codigoTalla;
		this.tipoIndumentaria = tipoIndumentaria;
		this.talla = talla;
		this.estatus = estatus;
		this.jugadors = jugadors;
		this.jugadors_1 = jugadors_1;
	}

	@Id
	@Column(name = "codigo_talla", unique = true, nullable = false)
	public String getCodigoTalla() {
		return this.codigoTalla;
	}

	public void setCodigoTalla(String codigoTalla) {
		this.codigoTalla = codigoTalla;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codigo_indumentaria", nullable = false)
	public TipoIndumentaria getTipoIndumentaria() {
		return this.tipoIndumentaria;
	}

	public void setTipoIndumentaria(TipoIndumentaria tipoIndumentaria) {
		this.tipoIndumentaria = tipoIndumentaria;
	}

	@Column(name = "talla", nullable = false)
	public String getTalla() {
		return this.talla;
	}

	public void setTalla(String talla) {
		this.talla = talla;
	}

	@Column(name = "estatus", nullable = false, length = 1)
	public char getEstatus() {
		return this.estatus;
	}

	public void setEstatus(char estatus) {
		this.estatus = estatus;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "talla")
	public Set<Jugador> getJugadors() {
		return this.jugadors;
	}

	public void setJugadors(Set<Jugador> jugadors) {
		this.jugadors = jugadors;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "talla")
	public Set<Jugador> getJugadors_1() {
		return this.jugadors_1;
	}

	public void setJugadors_1(Set<Jugador> jugadors_1) {
		this.jugadors_1 = jugadors_1;
	}

}
