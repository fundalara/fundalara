package modelo;

// Generated 14/02/2012 08:24:27 PM by Hibernate Tools 3.4.0.CR1

import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * JugadorPlan generated by hbm2java
 */
@Entity
@Table(name = "jugador_plan", schema = "public")
public class JugadorPlan implements java.io.Serializable {

	private String cedulaRif;
	private TallaPorIndumentaria tallaPorIndumentaria;
	private DatoBasico datoBasico;
	private String apellido;
	private String nombre;
	private char estatus;
	private Date fechaNacimiento;
	private Set<RepresentanteJugadorPlan> representanteJugadorPlans = new HashSet<RepresentanteJugadorPlan>(
			0);
	private Set<RosterPlan> rosterPlans = new HashSet<RosterPlan>(0);

	public JugadorPlan() {
	}

	public JugadorPlan(String cedulaRif,
			TallaPorIndumentaria tallaPorIndumentaria, DatoBasico datoBasico,
			String apellido, String nombre, char estatus, Date fechaNacimiento) {
		this.cedulaRif = cedulaRif;
		this.tallaPorIndumentaria = tallaPorIndumentaria;
		this.datoBasico = datoBasico;
		this.apellido = apellido;
		this.nombre = nombre;
		this.estatus = estatus;
		this.fechaNacimiento = fechaNacimiento;
	}

	public JugadorPlan(String cedulaRif,
			TallaPorIndumentaria tallaPorIndumentaria, DatoBasico datoBasico,
			String apellido, String nombre, char estatus, Date fechaNacimiento,
			Set<RepresentanteJugadorPlan> representanteJugadorPlans,
			Set<RosterPlan> rosterPlans) {
		this.cedulaRif = cedulaRif;
		this.tallaPorIndumentaria = tallaPorIndumentaria;
		this.datoBasico = datoBasico;
		this.apellido = apellido;
		this.nombre = nombre;
		this.estatus = estatus;
		this.fechaNacimiento = fechaNacimiento;
		this.representanteJugadorPlans = representanteJugadorPlans;
		this.rosterPlans = rosterPlans;
	}

	@Id
	@Column(name = "cedula_rif", unique = true, nullable = false)
	public String getCedulaRif() {
		return this.cedulaRif;
	}

	public void setCedulaRif(String cedulaRif) {
		this.cedulaRif = cedulaRif;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codigo_talla_indumentaria", nullable = false)
	public TallaPorIndumentaria getTallaPorIndumentaria() {
		return this.tallaPorIndumentaria;
	}

	public void setTallaPorIndumentaria(
			TallaPorIndumentaria tallaPorIndumentaria) {
		this.tallaPorIndumentaria = tallaPorIndumentaria;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codigo_tipo_jugador", nullable = false)
	public DatoBasico getDatoBasico() {
		return this.datoBasico;
	}

	public void setDatoBasico(DatoBasico datoBasico) {
		this.datoBasico = datoBasico;
	}

	@Column(name = "apellido", nullable = false)
	public String getApellido() {
		return this.apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	@Column(name = "nombre", nullable = false)
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column(name = "estatus", nullable = false, length = 1)
	public char getEstatus() {
		return this.estatus;
	}

	public void setEstatus(char estatus) {
		this.estatus = estatus;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_nacimiento", nullable = false, length = 13)
	public Date getFechaNacimiento() {
		return this.fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "jugadorPlan")
	public Set<RepresentanteJugadorPlan> getRepresentanteJugadorPlans() {
		return this.representanteJugadorPlans;
	}

	public void setRepresentanteJugadorPlans(
			Set<RepresentanteJugadorPlan> representanteJugadorPlans) {
		this.representanteJugadorPlans = representanteJugadorPlans;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "jugadorPlan")
	public Set<RosterPlan> getRosterPlans() {
		return this.rosterPlans;
	}

	public void setRosterPlans(Set<RosterPlan> rosterPlans) {
		this.rosterPlans = rosterPlans;
	}

}
