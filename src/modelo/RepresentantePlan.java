package modelo;

<<<<<<< HEAD
// Generated 25/01/2012 12:32:42 AM by Hibernate Tools 3.4.0.CR1
=======
<<<<<<< HEAD
// Generated 25/01/2012 12:32:42 AM by Hibernate Tools 3.4.0.CR1
=======
// Generated 24/01/2012 04:28:30 AM by Hibernate Tools 3.4.0.CR1
>>>>>>> 7d4823278d0a354855f5032a99e2b48c65b33e7f
>>>>>>> 9d67536bdd2e3b33aa6400eb3bb09741a4535bb0

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * RepresentantePlan generated by hbm2java
 */
@Entity
@Table(name = "representante_plan", schema = "public")
public class RepresentantePlan implements java.io.Serializable {

	private String cedulaFamiliar;
	private Familiar familiar;
	private String nombre;
	private String apellido;
	private String telefonoHabitacion;
	private String telefonoCelular;
	private char estatus;
	private Set<RepresentanteJugadorPlan> representanteJugadorPlans = new HashSet<RepresentanteJugadorPlan>(
			0);

	public RepresentantePlan() {
	}

	public RepresentantePlan(Familiar familiar, String nombre, String apellido,
			char estatus) {
		this.familiar = familiar;
		this.nombre = nombre;
		this.apellido = apellido;
		this.estatus = estatus;
	}

	public RepresentantePlan(Familiar familiar, String nombre, String apellido,
			String telefonoHabitacion, String telefonoCelular, char estatus,
			Set<RepresentanteJugadorPlan> representanteJugadorPlans) {
		this.familiar = familiar;
		this.nombre = nombre;
		this.apellido = apellido;
		this.telefonoHabitacion = telefonoHabitacion;
		this.telefonoCelular = telefonoCelular;
		this.estatus = estatus;
		this.representanteJugadorPlans = representanteJugadorPlans;
	}

	@GenericGenerator(name = "generator", strategy = "foreign", parameters = @Parameter(name = "property", value = "familiar"))
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
	public Familiar getFamiliar() {
		return this.familiar;
	}

	public void setFamiliar(Familiar familiar) {
		this.familiar = familiar;
	}

	@Column(name = "nombre", nullable = false)
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column(name = "apellido", nullable = false)
	public String getApellido() {
		return this.apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	@Column(name = "telefono_habitacion")
	public String getTelefonoHabitacion() {
		return this.telefonoHabitacion;
	}

	public void setTelefonoHabitacion(String telefonoHabitacion) {
		this.telefonoHabitacion = telefonoHabitacion;
	}

	@Column(name = "telefono_celular")
	public String getTelefonoCelular() {
		return this.telefonoCelular;
	}

	public void setTelefonoCelular(String telefonoCelular) {
		this.telefonoCelular = telefonoCelular;
	}

	@Column(name = "estatus", nullable = false, length = 1)
	public char getEstatus() {
		return this.estatus;
	}

	public void setEstatus(char estatus) {
		this.estatus = estatus;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "representantePlan")
	public Set<RepresentanteJugadorPlan> getRepresentanteJugadorPlans() {
		return this.representanteJugadorPlans;
	}

	public void setRepresentanteJugadorPlans(
			Set<RepresentanteJugadorPlan> representanteJugadorPlans) {
		this.representanteJugadorPlans = representanteJugadorPlans;
	}

}
