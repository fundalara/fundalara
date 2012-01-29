package modelo;

// Generated 28/01/2012 11:49:55 AM by Hibernate Tools 3.4.0.CR1

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
 * Horario generated by hbm2java
 */
@Entity
@Table(name = "horario", schema = "public")
public class Horario implements java.io.Serializable {

	private int codigoHorario;
	private DatoBasico datoBasico;
	private Date horaInicio;
	private Date horaFin;
	private char estatus;
	private Set<HorarioPlanTemporada> horarioPlanTemporadas = new HashSet<HorarioPlanTemporada>(
			0);

	public Horario() {
	}

	public Horario(int codigoHorario, DatoBasico datoBasico, Date horaInicio,
			Date horaFin, char estatus) {
		this.codigoHorario = codigoHorario;
		this.datoBasico = datoBasico;
		this.horaInicio = horaInicio;
		this.horaFin = horaFin;
		this.estatus = estatus;
	}

	public Horario(int codigoHorario, DatoBasico datoBasico, Date horaInicio,
			Date horaFin, char estatus,
			Set<HorarioPlanTemporada> horarioPlanTemporadas) {
		this.codigoHorario = codigoHorario;
		this.datoBasico = datoBasico;
		this.horaInicio = horaInicio;
		this.horaFin = horaFin;
		this.estatus = estatus;
		this.horarioPlanTemporadas = horarioPlanTemporadas;
	}

	@Id
	@Column(name = "codigo_horario", unique = true, nullable = false)
	public int getCodigoHorario() {
		return this.codigoHorario;
	}

	public void setCodigoHorario(int codigoHorario) {
		this.codigoHorario = codigoHorario;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "dia", nullable = false)
	public DatoBasico getDatoBasico() {
		return this.datoBasico;
	}

	public void setDatoBasico(DatoBasico datoBasico) {
		this.datoBasico = datoBasico;
	}

	@Temporal(TemporalType.TIME)
	@Column(name = "hora_inicio", nullable = false, length = 15)
	public Date getHoraInicio() {
		return this.horaInicio;
	}

	public void setHoraInicio(Date horaInicio) {
		this.horaInicio = horaInicio;
	}

	@Temporal(TemporalType.TIME)
	@Column(name = "hora_fin", nullable = false, length = 15)
	public Date getHoraFin() {
		return this.horaFin;
	}

	public void setHoraFin(Date horaFin) {
		this.horaFin = horaFin;
	}

	@Column(name = "estatus", nullable = false, length = 1)
	public char getEstatus() {
		return this.estatus;
	}

	public void setEstatus(char estatus) {
		this.estatus = estatus;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "horario")
	public Set<HorarioPlanTemporada> getHorarioPlanTemporadas() {
		return this.horarioPlanTemporadas;
	}

	public void setHorarioPlanTemporadas(
			Set<HorarioPlanTemporada> horarioPlanTemporadas) {
		this.horarioPlanTemporadas = horarioPlanTemporadas;
	}

}
