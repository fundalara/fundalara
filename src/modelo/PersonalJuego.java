package modelo;

// Generated 19-dic-2011 14:08:48 by Hibernate Tools 3.4.0.CR1

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
 * PersonalJuego generated by hbm2java
 */
@Entity
@Table(name = "personal_juego")
public class PersonalJuego implements java.io.Serializable {

	private PersonalJuegoId id;
	private Juego juego;
	private Personal personal;
	private DatoBasico datoBasico;

	public PersonalJuego() {
	}

	public PersonalJuego(PersonalJuegoId id, Juego juego, Personal personal,
			DatoBasico datoBasico) {
		this.id = id;
		this.juego = juego;
		this.personal = personal;
		this.datoBasico = datoBasico;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "codigoJuego1", column = @Column(name = "codigo_juego1", nullable = false)),
			@AttributeOverride(name = "cedulaRif", column = @Column(name = "cedula_rif", nullable = false)) })
	public PersonalJuegoId getId() {
		return this.id;
	}

	public void setId(PersonalJuegoId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codigo_juego1", nullable = false, insertable = false, updatable = false)
	public Juego getJuego() {
		return this.juego;
	}

	public void setJuego(Juego juego) {
		this.juego = juego;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cedula_rif", nullable = false, insertable = false, updatable = false)
	public Personal getPersonal() {
		return this.personal;
	}

	public void setPersonal(Personal personal) {
		this.personal = personal;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codigo_cargo_personal", nullable = false)
	public DatoBasico getDatoBasico() {
		return this.datoBasico;
	}

	public void setDatoBasico(DatoBasico datoBasico) {
		this.datoBasico = datoBasico;
	}

}
