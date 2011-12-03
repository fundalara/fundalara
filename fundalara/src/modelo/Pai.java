package modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the pais database table.
 * 
 */
@Entity
@Table(name="pais")
public class Pai implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="codigo_pais")
	private String codigoPais;

	private String nombre;

	//bi-directional many-to-one association to Jugador
	@OneToMany(mappedBy="pai")
	private Set<Jugador> jugadors;

    public Pai() {
    }

	public String getCodigoPais() {
		return this.codigoPais;
	}

	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Set<Jugador> getJugadors() {
		return this.jugadors;
	}

	public void setJugadors(Set<Jugador> jugadors) {
		this.jugadors = jugadors;
	}
	
}