package modelo;

// Generated Dec 23, 2011 1:26:53 PM by Hibernate Tools 3.4.0.CR1

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * Familiar generated by hbm2java
 */
@Entity
@Table(name = "familiar", schema = "public")
public class Familiar implements java.io.Serializable {

	private String cedulaFamiliar;
	private Persona persona;
	private DatoBasico datoBasico;
	private Set<FamiliarJugador> familiarJugadors = new HashSet<FamiliarJugador>(
			0);

	public Familiar() {
	}

	public Familiar(Persona persona, DatoBasico datoBasico) {
		this.persona = persona;
		this.datoBasico = datoBasico;
	}

	public Familiar(Persona persona, DatoBasico datoBasico,
			Set<FamiliarJugador> familiarJugadors) {
		this.persona = persona;
		this.datoBasico = datoBasico;
		this.familiarJugadors = familiarJugadors;
	}

	@GenericGenerator(name = "generator", strategy = "foreign", parameters = @Parameter(name = "property", value = "persona"))
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "cedula_familiar", unique = true, nullable = false)
	public String getCedulaFamiliar() {
		return this.cedulaFamiliar;
	}

	public void setCedulaFamiliar(String cedulaFamiliar) {
		this.cedulaFamiliar = cedulaFamiliar;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	public Persona getPersona() {
		return this.persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codigo_profesion", nullable = false)
	public DatoBasico getDatoBasico() {
		return this.datoBasico;
	}

	public void setDatoBasico(DatoBasico datoBasico) {
		this.datoBasico = datoBasico;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "familiar")
	public Set<FamiliarJugador> getFamiliarJugadors() {
		return this.familiarJugadors;
	}

	public void setFamiliarJugadors(Set<FamiliarJugador> familiarJugadors) {
		this.familiarJugadors = familiarJugadors;
	}

}
