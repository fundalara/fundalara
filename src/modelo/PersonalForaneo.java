package modelo;

<<<<<<< HEAD
// Generated 25/01/2012 12:32:42 AM by Hibernate Tools 3.4.0.CR1
=======
// Generated 24/01/2012 04:28:30 AM by Hibernate Tools 3.4.0.CR1
>>>>>>> 7d4823278d0a354855f5032a99e2b48c65b33e7f

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
 * PersonalForaneo generated by hbm2java
 */
@Entity
@Table(name = "personal_foraneo", schema = "public")
public class PersonalForaneo implements java.io.Serializable {

	private int codigoPersonalForaneo;
	private DatoBasico datoBasico;
	private String nombre;
	private Set<PersonalForaneoJuego> personalForaneoJuegos = new HashSet<PersonalForaneoJuego>(
			0);

	public PersonalForaneo() {
	}

	public PersonalForaneo(int codigoPersonalForaneo, DatoBasico datoBasico) {
		this.codigoPersonalForaneo = codigoPersonalForaneo;
		this.datoBasico = datoBasico;
	}

	public PersonalForaneo(int codigoPersonalForaneo, DatoBasico datoBasico,
			String nombre, Set<PersonalForaneoJuego> personalForaneoJuegos) {
		this.codigoPersonalForaneo = codigoPersonalForaneo;
		this.datoBasico = datoBasico;
		this.nombre = nombre;
		this.personalForaneoJuegos = personalForaneoJuegos;
	}

	@Id
	@Column(name = "codigo_personal_foraneo", unique = true, nullable = false)
	public int getCodigoPersonalForaneo() {
		return this.codigoPersonalForaneo;
	}

	public void setCodigoPersonalForaneo(int codigoPersonalForaneo) {
		this.codigoPersonalForaneo = codigoPersonalForaneo;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codigo_tipo_personal_foraneo", nullable = false)
	public DatoBasico getDatoBasico() {
		return this.datoBasico;
	}

	public void setDatoBasico(DatoBasico datoBasico) {
		this.datoBasico = datoBasico;
	}

	@Column(name = "nombre")
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "personalForaneo")
	public Set<PersonalForaneoJuego> getPersonalForaneoJuegos() {
		return this.personalForaneoJuegos;
	}

	public void setPersonalForaneoJuegos(
			Set<PersonalForaneoJuego> personalForaneoJuegos) {
		this.personalForaneoJuegos = personalForaneoJuegos;
	}

}
