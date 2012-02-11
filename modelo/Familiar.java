package modelo;

// Generated 10/02/2012 11:18:51 PM by Hibernate Tools 3.4.0.CR1

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Familiar generated by hbm2java
 */
@Entity
@Table(name = "familiar", schema = "public")
public class Familiar implements java.io.Serializable {

	private String cedulaRif;
	private Integer codigoProfesion;
	private char estatus;
	private Set<FamiliarJugador> familiarJugadors = new HashSet<FamiliarJugador>(
			0);
	private Set<RepresentanteJugadorPlan> representanteJugadorPlans = new HashSet<RepresentanteJugadorPlan>(
			0);

	public Familiar() {
	}

	public Familiar(String cedulaRif, char estatus) {
		this.cedulaRif = cedulaRif;
		this.estatus = estatus;
	}

	public Familiar(String cedulaRif, Integer codigoProfesion, char estatus,
			Set<FamiliarJugador> familiarJugadors,
			Set<RepresentanteJugadorPlan> representanteJugadorPlans) {
		this.cedulaRif = cedulaRif;
		this.codigoProfesion = codigoProfesion;
		this.estatus = estatus;
		this.familiarJugadors = familiarJugadors;
		this.representanteJugadorPlans = representanteJugadorPlans;
	}

	@Id
	@Column(name = "cedula_rif", unique = true, nullable = false)
	public String getCedulaRif() {
		return this.cedulaRif;
	}

	public void setCedulaRif(String cedulaRif) {
		this.cedulaRif = cedulaRif;
	}

	@Column(name = "codigo_profesion")
	public Integer getCodigoProfesion() {
		return this.codigoProfesion;
	}

	public void setCodigoProfesion(Integer codigoProfesion) {
		this.codigoProfesion = codigoProfesion;
	}

	@Column(name = "estatus", nullable = false, length = 1)
	public char getEstatus() {
		return this.estatus;
	}

	public void setEstatus(char estatus) {
		this.estatus = estatus;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "familiar")
	public Set<FamiliarJugador> getFamiliarJugadors() {
		return this.familiarJugadors;
	}

	public void setFamiliarJugadors(Set<FamiliarJugador> familiarJugadors) {
		this.familiarJugadors = familiarJugadors;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "familiar")
	public Set<RepresentanteJugadorPlan> getRepresentanteJugadorPlans() {
		return this.representanteJugadorPlans;
	}

	public void setRepresentanteJugadorPlans(
			Set<RepresentanteJugadorPlan> representanteJugadorPlans) {
		this.representanteJugadorPlans = representanteJugadorPlans;
	}

}
