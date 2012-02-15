package modelo;

// Generated 28-ene-2012 22:13:44 by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Hospedaje generated by hbm2java
 */
@Entity
@Table(name = "hospedaje", schema = "public")
@SequenceGenerator(name = "hospedaje_seq", sequenceName = "hospedaje_codigo_hospedaje_seq_1")
public class Hospedaje implements java.io.Serializable {

	private int codigoHospedaje;
	private FamiliarJugador familiarJugador;
	private Competencia competencia;
	private char estatus;

	public Hospedaje() {
	}

	public Hospedaje(int codigoHospedaje, FamiliarJugador familiarJugador,
			Competencia competencia, char estatus) {
		this.codigoHospedaje = codigoHospedaje;
		this.familiarJugador = familiarJugador;
		this.competencia = competencia;
		this.estatus = estatus;
	}

	@Id
	@Column(name = "codigo_hospedaje", unique = true, nullable = false)
	@GeneratedValue(generator = "hospedaje_seq")
	public int getCodigoHospedaje() {
		return this.codigoHospedaje;
	}

	public void setCodigoHospedaje(int codigoHospedaje) {
		this.codigoHospedaje = codigoHospedaje;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codigo_familiar_jugador", nullable = false)
	public FamiliarJugador getFamiliarJugador() {
		return this.familiarJugador;
	}

	public void setFamiliarJugador(FamiliarJugador familiarJugador) {
		this.familiarJugador = familiarJugador;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codigo_competencia", nullable = false)
	public Competencia getCompetencia() {
		return this.competencia;
	}

	public void setCompetencia(Competencia competencia) {
		this.competencia = competencia;
	}

	@Column(name = "estatus", nullable = false, length = 1)
	public char getEstatus() {
		return this.estatus;
	}

	public void setEstatus(char estatus) {
		this.estatus = estatus;
	}

}
