package modelo;

// Generated 10/02/2012 11:18:51 PM by Hibernate Tools 3.4.0.CR1

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
 * PersonalEquipoCompetencia generated by hbm2java
 */
@Entity
@Table(name = "personal_equipo_competencia", schema = "public")
public class PersonalEquipoCompetencia implements java.io.Serializable {

	private String codigoPersonalEquipoCompetencia;
	private Competencia competencia;
	private PersonalEquipo personalEquipo;
	private char estatus;
	private Set<PersonalEquipoJuego> personalEquipoJuegos = new HashSet<PersonalEquipoJuego>(
			0);

	public PersonalEquipoCompetencia() {
	}

	public PersonalEquipoCompetencia(String codigoPersonalEquipoCompetencia,
			Competencia competencia, PersonalEquipo personalEquipo, char estatus) {
		this.codigoPersonalEquipoCompetencia = codigoPersonalEquipoCompetencia;
		this.competencia = competencia;
		this.personalEquipo = personalEquipo;
		this.estatus = estatus;
	}

	public PersonalEquipoCompetencia(String codigoPersonalEquipoCompetencia,
			Competencia competencia, PersonalEquipo personalEquipo,
			char estatus, Set<PersonalEquipoJuego> personalEquipoJuegos) {
		this.codigoPersonalEquipoCompetencia = codigoPersonalEquipoCompetencia;
		this.competencia = competencia;
		this.personalEquipo = personalEquipo;
		this.estatus = estatus;
		this.personalEquipoJuegos = personalEquipoJuegos;
	}

	@Id
	@Column(name = "codigo_personal_equipo_competencia", unique = true, nullable = false)
	public String getCodigoPersonalEquipoCompetencia() {
		return this.codigoPersonalEquipoCompetencia;
	}

	public void setCodigoPersonalEquipoCompetencia(
			String codigoPersonalEquipoCompetencia) {
		this.codigoPersonalEquipoCompetencia = codigoPersonalEquipoCompetencia;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codigo_competencia", nullable = false)
	public Competencia getCompetencia() {
		return this.competencia;
	}

	public void setCompetencia(Competencia competencia) {
		this.competencia = competencia;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codigo_personal_equipo", nullable = false)
	public PersonalEquipo getPersonalEquipo() {
		return this.personalEquipo;
	}

	public void setPersonalEquipo(PersonalEquipo personalEquipo) {
		this.personalEquipo = personalEquipo;
	}

	@Column(name = "estatus", nullable = false, length = 1)
	public char getEstatus() {
		return this.estatus;
	}

	public void setEstatus(char estatus) {
		this.estatus = estatus;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "personalEquipoCompetencia")
	public Set<PersonalEquipoJuego> getPersonalEquipoJuegos() {
		return this.personalEquipoJuegos;
	}

	public void setPersonalEquipoJuegos(
			Set<PersonalEquipoJuego> personalEquipoJuegos) {
		this.personalEquipoJuegos = personalEquipoJuegos;
	}

}
