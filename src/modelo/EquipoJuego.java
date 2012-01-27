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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * EquipoJuego generated by hbm2java
 */
@Entity
@Table(name = "equipo_juego", schema = "public")
public class EquipoJuego implements java.io.Serializable {

	private int codigoEquipoJuego;
	private Juego juego;
	private EquipoCompetencia equipoCompetencia;
	private boolean homeClub;
	private Set<DesempennoColectivo> desempennoColectivos = new HashSet<DesempennoColectivo>(
			0);
	private Set<PersonalEquipo> personalEquipos = new HashSet<PersonalEquipo>(0);

	public EquipoJuego() {
	}

	public EquipoJuego(int codigoEquipoJuego, Juego juego,
			EquipoCompetencia equipoCompetencia, boolean homeClub) {
		this.codigoEquipoJuego = codigoEquipoJuego;
		this.juego = juego;
		this.equipoCompetencia = equipoCompetencia;
		this.homeClub = homeClub;
	}

	public EquipoJuego(int codigoEquipoJuego, Juego juego,
			EquipoCompetencia equipoCompetencia, boolean homeClub,
			Set<DesempennoColectivo> desempennoColectivos,
			Set<PersonalEquipo> personalEquipos) {
		this.codigoEquipoJuego = codigoEquipoJuego;
		this.juego = juego;
		this.equipoCompetencia = equipoCompetencia;
		this.homeClub = homeClub;
		this.desempennoColectivos = desempennoColectivos;
		this.personalEquipos = personalEquipos;
	}

	@Id
	@Column(name = "codigo_equipo_juego", unique = true, nullable = false)
	public int getCodigoEquipoJuego() {
		return this.codigoEquipoJuego;
	}

	public void setCodigoEquipoJuego(int codigoEquipoJuego) {
		this.codigoEquipoJuego = codigoEquipoJuego;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codigo_juego", nullable = false)
	public Juego getJuego() {
		return this.juego;
	}

	public void setJuego(Juego juego) {
		this.juego = juego;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codigo_equipo_competencia", nullable = false)
	public EquipoCompetencia getEquipoCompetencia() {
		return this.equipoCompetencia;
	}

	public void setEquipoCompetencia(EquipoCompetencia equipoCompetencia) {
		this.equipoCompetencia = equipoCompetencia;
	}

	@Column(name = "home_club", nullable = false)
	public boolean isHomeClub() {
		return this.homeClub;
	}

	public void setHomeClub(boolean homeClub) {
		this.homeClub = homeClub;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "equipoJuego")
	public Set<DesempennoColectivo> getDesempennoColectivos() {
		return this.desempennoColectivos;
	}

	public void setDesempennoColectivos(
			Set<DesempennoColectivo> desempennoColectivos) {
		this.desempennoColectivos = desempennoColectivos;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "personal_equipo_juego", schema = "public", joinColumns = { @JoinColumn(name = "codigo_equipo_juego", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "codigo_personal_equipo", nullable = false, updatable = false) })
	public Set<PersonalEquipo> getPersonalEquipos() {
		return this.personalEquipos;
	}

	public void setPersonalEquipos(Set<PersonalEquipo> personalEquipos) {
		this.personalEquipos = personalEquipos;
	}

}
