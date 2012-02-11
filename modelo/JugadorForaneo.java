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
 * JugadorForaneo generated by hbm2java
 */
@Entity
@Table(name = "jugador_foraneo", schema = "public")
public class JugadorForaneo implements java.io.Serializable {

	private int codigoJugadorForaneo;
	private Equipo equipo;
	private String cedula;
	private String nombre;
	private char estatus;
	private Set<RosterCompetencia> rosterCompetencias = new HashSet<RosterCompetencia>(
			0);

	public JugadorForaneo() {
	}

	public JugadorForaneo(int codigoJugadorForaneo, Equipo equipo,
			String cedula, String nombre, char estatus) {
		this.codigoJugadorForaneo = codigoJugadorForaneo;
		this.equipo = equipo;
		this.cedula = cedula;
		this.nombre = nombre;
		this.estatus = estatus;
	}

	public JugadorForaneo(int codigoJugadorForaneo, Equipo equipo,
			String cedula, String nombre, char estatus,
			Set<RosterCompetencia> rosterCompetencias) {
		this.codigoJugadorForaneo = codigoJugadorForaneo;
		this.equipo = equipo;
		this.cedula = cedula;
		this.nombre = nombre;
		this.estatus = estatus;
		this.rosterCompetencias = rosterCompetencias;
	}

	@Id
	@Column(name = "codigo_jugador_foraneo", unique = true, nullable = false)
	public int getCodigoJugadorForaneo() {
		return this.codigoJugadorForaneo;
	}

	public void setCodigoJugadorForaneo(int codigoJugadorForaneo) {
		this.codigoJugadorForaneo = codigoJugadorForaneo;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codigo_equipo", nullable = false)
	public Equipo getEquipo() {
		return this.equipo;
	}

	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}

	@Column(name = "cedula", nullable = false)
	public String getCedula() {
		return this.cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "jugadorForaneo")
	public Set<RosterCompetencia> getRosterCompetencias() {
		return this.rosterCompetencias;
	}

	public void setRosterCompetencias(Set<RosterCompetencia> rosterCompetencias) {
		this.rosterCompetencias = rosterCompetencias;
	}

}
