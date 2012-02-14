package modelo;

// Generated 13/02/2012 08:44:08 PM by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Anuario generated by hbm2java
 */
@Entity
@Table(name = "anuario", schema = "public")
public class Anuario implements java.io.Serializable {

	private int codigoAnuario;
	private Roster roster;
	private LapsoDeportivo lapsoDeportivo;
	private byte[] foto;
	private char estatus;

	public Anuario() {
	}

	public Anuario(int codigoAnuario, Roster roster,
			LapsoDeportivo lapsoDeportivo, byte[] foto, char estatus) {
		this.codigoAnuario = codigoAnuario;
		this.roster = roster;
		this.lapsoDeportivo = lapsoDeportivo;
		this.foto = foto;
		this.estatus = estatus;
	}

	@Id
	@Column(name = "codigo_anuario", unique = true, nullable = false)
	public int getCodigoAnuario() {
		return this.codigoAnuario;
	}

	public void setCodigoAnuario(int codigoAnuario) {
		this.codigoAnuario = codigoAnuario;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codigo_roster", nullable = false)
	public Roster getRoster() {
		return this.roster;
	}

	public void setRoster(Roster roster) {
		this.roster = roster;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codigo_lapso_deportivo", nullable = false)
	public LapsoDeportivo getLapsoDeportivo() {
		return this.lapsoDeportivo;
	}

	public void setLapsoDeportivo(LapsoDeportivo lapsoDeportivo) {
		this.lapsoDeportivo = lapsoDeportivo;
	}

	@Column(name = "foto", nullable = false)
	public byte[] getFoto() {
		return this.foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

	@Column(name = "estatus", nullable = false, length = 1)
	public char getEstatus() {
		return this.estatus;
	}

	public void setEstatus(char estatus) {
		this.estatus = estatus;
	}

}
