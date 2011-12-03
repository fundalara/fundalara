package modelo;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the dato_deportivo database table.
 * 
 */
@Entity
@Table(name="dato_deportivo")
public class DatoDeportivo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="cedula_jugador")
	private String cedulaJugador;

	//bi-directional many-to-one association to Competencia
    @ManyToOne
	@JoinColumn(name="codigo_competencia")
	private Competencia competencia;

	//bi-directional one-to-one association to Jugador
	@OneToOne
	@JoinColumn(name="cedula_jugador")
	private Jugador jugador;

	//bi-directional many-to-one association to TipoLogro
    @ManyToOne
	@JoinColumn(name="codigo_logro")
	private TipoLogro tipoLogro;

    public DatoDeportivo() {
    }

	public String getCedulaJugador() {
		return this.cedulaJugador;
	}

	public void setCedulaJugador(String cedulaJugador) {
		this.cedulaJugador = cedulaJugador;
	}

	public Competencia getCompetencia() {
		return this.competencia;
	}

	public void setCompetencia(Competencia competencia) {
		this.competencia = competencia;
	}
	
	public Jugador getJugador() {
		return this.jugador;
	}

	public void setJugador(Jugador jugador) {
		this.jugador = jugador;
	}
	
	public TipoLogro getTipoLogro() {
		return this.tipoLogro;
	}

	public void setTipoLogro(TipoLogro tipoLogro) {
		this.tipoLogro = tipoLogro;
	}
	
}