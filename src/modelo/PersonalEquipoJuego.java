package modelo;

// Generated 11/02/2012 01:49:19 AM by Hibernate Tools 3.4.0.CR1

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * PersonalEquipoJuego generated by hbm2java
 */
@Entity
@Table(name = "personal_equipo_juego", schema = "public")
public class PersonalEquipoJuego implements java.io.Serializable {

	private PersonalEquipoJuegoId id;
	private PersonalEquipoCompetencia personalEquipoCompetencia;
	private EquipoJuego equipoJuego;
	private char estatus;

	public PersonalEquipoJuego() {
	}

	public PersonalEquipoJuego(PersonalEquipoJuegoId id,
			PersonalEquipoCompetencia personalEquipoCompetencia,
			EquipoJuego equipoJuego, char estatus) {
		this.id = id;
		this.personalEquipoCompetencia = personalEquipoCompetencia;
		this.equipoJuego = equipoJuego;
		this.estatus = estatus;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "codigoEquipoJuego", column = @Column(name = "codigo_equipo_juego", nullable = false)),
			@AttributeOverride(name = "codigoPersonalEquipoCompetencia", column = @Column(name = "codigo_personal_equipo_competencia", nullable = false)) })
	public PersonalEquipoJuegoId getId() {
		return this.id;
	}

	public void setId(PersonalEquipoJuegoId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codigo_personal_equipo_competencia", nullable = false, insertable = false, updatable = false)
	public PersonalEquipoCompetencia getPersonalEquipoCompetencia() {
		return this.personalEquipoCompetencia;
	}

	public void setPersonalEquipoCompetencia(
			PersonalEquipoCompetencia personalEquipoCompetencia) {
		this.personalEquipoCompetencia = personalEquipoCompetencia;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codigo_equipo_juego", nullable = false, insertable = false, updatable = false)
	public EquipoJuego getEquipoJuego() {
		return this.equipoJuego;
	}

	public void setEquipoJuego(EquipoJuego equipoJuego) {
		this.equipoJuego = equipoJuego;
	}

	@Column(name = "estatus", nullable = false, length = 1)
	public char getEstatus() {
		return this.estatus;
	}

	public void setEstatus(char estatus) {
		this.estatus = estatus;
	}

}
