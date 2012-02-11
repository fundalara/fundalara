package modelo;

// Generated 11/02/2012 01:49:19 AM by Hibernate Tools 3.4.0.CR1

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
 * DesempennoJugador generated by hbm2java
 */
@Entity
@Table(name = "desempenno_jugador", schema = "public")
public class DesempennoJugador implements java.io.Serializable {

	private int codigoDesempennoJugador;
	private AsistenciaJugador asistenciaJugador;
	private ActividadEjecutada actividadEjecutada;
	private char estatus;
	private Set<PuntuacionJugador> puntuacionJugadors = new HashSet<PuntuacionJugador>(
			0);

	public DesempennoJugador() {
	}

	public DesempennoJugador(int codigoDesempennoJugador,
			AsistenciaJugador asistenciaJugador,
			ActividadEjecutada actividadEjecutada, char estatus) {
		this.codigoDesempennoJugador = codigoDesempennoJugador;
		this.asistenciaJugador = asistenciaJugador;
		this.actividadEjecutada = actividadEjecutada;
		this.estatus = estatus;
	}

	public DesempennoJugador(int codigoDesempennoJugador,
			AsistenciaJugador asistenciaJugador,
			ActividadEjecutada actividadEjecutada, char estatus,
			Set<PuntuacionJugador> puntuacionJugadors) {
		this.codigoDesempennoJugador = codigoDesempennoJugador;
		this.asistenciaJugador = asistenciaJugador;
		this.actividadEjecutada = actividadEjecutada;
		this.estatus = estatus;
		this.puntuacionJugadors = puntuacionJugadors;
	}

	@Id
	@Column(name = "codigo_desempenno_jugador", unique = true, nullable = false)
	public int getCodigoDesempennoJugador() {
		return this.codigoDesempennoJugador;
	}

	public void setCodigoDesempennoJugador(int codigoDesempennoJugador) {
		this.codigoDesempennoJugador = codigoDesempennoJugador;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codigo_asistencia", nullable = false)
	public AsistenciaJugador getAsistenciaJugador() {
		return this.asistenciaJugador;
	}

	public void setAsistenciaJugador(AsistenciaJugador asistenciaJugador) {
		this.asistenciaJugador = asistenciaJugador;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codigo_actividad_ejecutada", nullable = false)
	public ActividadEjecutada getActividadEjecutada() {
		return this.actividadEjecutada;
	}

	public void setActividadEjecutada(ActividadEjecutada actividadEjecutada) {
		this.actividadEjecutada = actividadEjecutada;
	}

	@Column(name = "estatus", nullable = false, length = 1)
	public char getEstatus() {
		return this.estatus;
	}

	public void setEstatus(char estatus) {
		this.estatus = estatus;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "desempennoJugador")
	public Set<PuntuacionJugador> getPuntuacionJugadors() {
		return this.puntuacionJugadors;
	}

	public void setPuntuacionJugadors(Set<PuntuacionJugador> puntuacionJugadors) {
		this.puntuacionJugadors = puntuacionJugadors;
	}

}
