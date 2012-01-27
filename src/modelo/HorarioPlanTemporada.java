package modelo;

<<<<<<< HEAD
// Generated 25/01/2012 12:32:42 AM by Hibernate Tools 3.4.0.CR1
=======
<<<<<<< HEAD
// Generated 25/01/2012 12:32:42 AM by Hibernate Tools 3.4.0.CR1
=======
<<<<<<< HEAD
// Generated 25/01/2012 12:32:42 AM by Hibernate Tools 3.4.0.CR1
=======
// Generated 24/01/2012 04:28:30 AM by Hibernate Tools 3.4.0.CR1
>>>>>>> 7d4823278d0a354855f5032a99e2b48c65b33e7f
>>>>>>> 9d67536bdd2e3b33aa6400eb3bb09741a4535bb0
>>>>>>> 873ccb018e72b00b831aa9284cc0745bd1c2d514

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * HorarioPlanTemporada generated by hbm2java
 */
@Entity
@Table(name = "horario_plan_temporada", schema = "public")
public class HorarioPlanTemporada implements java.io.Serializable {

	private int codigoHorarioPlan;
	private PlanTemporada planTemporada;
	private Horario horario;
	private Equipo equipo;
	private char estatus;

	public HorarioPlanTemporada() {
	}

	public HorarioPlanTemporada(int codigoHorarioPlan,
			PlanTemporada planTemporada, Horario horario, Equipo equipo,
			char estatus) {
		this.codigoHorarioPlan = codigoHorarioPlan;
		this.planTemporada = planTemporada;
		this.horario = horario;
		this.equipo = equipo;
		this.estatus = estatus;
	}

	@Id
	@Column(name = "codigo_horario_plan", unique = true, nullable = false)
	public int getCodigoHorarioPlan() {
		return this.codigoHorarioPlan;
	}

	public void setCodigoHorarioPlan(int codigoHorarioPlan) {
		this.codigoHorarioPlan = codigoHorarioPlan;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codigo_plan_temporada", nullable = false)
	public PlanTemporada getPlanTemporada() {
		return this.planTemporada;
	}

	public void setPlanTemporada(PlanTemporada planTemporada) {
		this.planTemporada = planTemporada;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codigo_horario", nullable = false)
	public Horario getHorario() {
		return this.horario;
	}

	public void setHorario(Horario horario) {
		this.horario = horario;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codigo_equipo", nullable = false)
	public Equipo getEquipo() {
		return this.equipo;
	}

	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}

	@Column(name = "estatus", nullable = false, length = 1)
	public char getEstatus() {
		return this.estatus;
	}

	public void setEstatus(char estatus) {
		this.estatus = estatus;
	}

}
