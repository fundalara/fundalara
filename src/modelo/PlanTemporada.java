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
 * PlanTemporada generated by hbm2java
 */
@Entity
@Table(name = "plan_temporada", schema = "public")
public class PlanTemporada implements java.io.Serializable {

	private int codigoPlanTemporada;
	private Categoria categoria;
	private LapsoDeportivo lapsoDeportivo;
	private char estatus;
	private Set<PersonalEquipo> personalEquipos = new HashSet<PersonalEquipo>(0);
	private Set<HorarioPlanTemporada> horarioPlanTemporadas = new HashSet<HorarioPlanTemporada>(
			0);
	private Set<PlanEntrenamiento> planEntrenamientos = new HashSet<PlanEntrenamiento>(
			0);

	public PlanTemporada() {
	}

	public PlanTemporada(int codigoPlanTemporada,
			LapsoDeportivo lapsoDeportivo, char estatus) {
		this.codigoPlanTemporada = codigoPlanTemporada;
		this.lapsoDeportivo = lapsoDeportivo;
		this.estatus = estatus;
	}

	public PlanTemporada(int codigoPlanTemporada, Categoria categoria,
			LapsoDeportivo lapsoDeportivo, char estatus,
			Set<PersonalEquipo> personalEquipos,
			Set<HorarioPlanTemporada> horarioPlanTemporadas,
			Set<PlanEntrenamiento> planEntrenamientos) {
		this.codigoPlanTemporada = codigoPlanTemporada;
		this.categoria = categoria;
		this.lapsoDeportivo = lapsoDeportivo;
		this.estatus = estatus;
		this.personalEquipos = personalEquipos;
		this.horarioPlanTemporadas = horarioPlanTemporadas;
		this.planEntrenamientos = planEntrenamientos;
	}

	@Id
	@Column(name = "codigo_plan_temporada", unique = true, nullable = false)
	public int getCodigoPlanTemporada() {
		return this.codigoPlanTemporada;
	}

	public void setCodigoPlanTemporada(int codigoPlanTemporada) {
		this.codigoPlanTemporada = codigoPlanTemporada;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codigo_categoria")
	public Categoria getCategoria() {
		return this.categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codigo_lapso_deportivo", nullable = false)
	public LapsoDeportivo getLapsoDeportivo() {
		return this.lapsoDeportivo;
	}

	public void setLapsoDeportivo(LapsoDeportivo lapsoDeportivo) {
		this.lapsoDeportivo = lapsoDeportivo;
	}

	@Column(name = "estatus", nullable = false, length = 1)
	public char getEstatus() {
		return this.estatus;
	}

	public void setEstatus(char estatus) {
		this.estatus = estatus;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "planTemporada")
	public Set<PersonalEquipo> getPersonalEquipos() {
		return this.personalEquipos;
	}

	public void setPersonalEquipos(Set<PersonalEquipo> personalEquipos) {
		this.personalEquipos = personalEquipos;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "planTemporada")
	public Set<HorarioPlanTemporada> getHorarioPlanTemporadas() {
		return this.horarioPlanTemporadas;
	}

	public void setHorarioPlanTemporadas(
			Set<HorarioPlanTemporada> horarioPlanTemporadas) {
		this.horarioPlanTemporadas = horarioPlanTemporadas;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "planTemporada")
	public Set<PlanEntrenamiento> getPlanEntrenamientos() {
		return this.planEntrenamientos;
	}

	public void setPlanEntrenamientos(Set<PlanEntrenamiento> planEntrenamientos) {
		this.planEntrenamientos = planEntrenamientos;
	}

}
