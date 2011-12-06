package modelo;

// Generated 06/12/2011 05:27:34 PM by Hibernate Tools 3.4.0.CR1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * FamiliarJugador generated by hbm2java
 */
@Entity
@Table(name = "familiar_jugador")
public class FamiliarJugador implements java.io.Serializable {

	private FamiliarJugadorId id;
	private Jugador jugador;
	private Familiar familiar;
	private String parentesco;
	private boolean representante;
	private char estatus;
	private Date fechaIngreso;
	private Set<FamiliarComisionEquipo> familiarComisionEquiposForFke079614752b2d13c = new HashSet<FamiliarComisionEquipo>(
			0);
	private Set<FamiliarComisionEquipo> familiarComisionEquiposForFamiliarJugadorFamiliarComisionFk = new HashSet<FamiliarComisionEquipo>(
			0);

	public FamiliarJugador() {
	}

	public FamiliarJugador(FamiliarJugadorId id, Jugador jugador,
			Familiar familiar, String parentesco, boolean representante,
			char estatus, Date fechaIngreso) {
		this.id = id;
		this.jugador = jugador;
		this.familiar = familiar;
		this.parentesco = parentesco;
		this.representante = representante;
		this.estatus = estatus;
		this.fechaIngreso = fechaIngreso;
	}

	public FamiliarJugador(
			FamiliarJugadorId id,
			Jugador jugador,
			Familiar familiar,
			String parentesco,
			boolean representante,
			char estatus,
			Date fechaIngreso,
			Set<FamiliarComisionEquipo> familiarComisionEquiposForFke079614752b2d13c,
			Set<FamiliarComisionEquipo> familiarComisionEquiposForFamiliarJugadorFamiliarComisionFk) {
		this.id = id;
		this.jugador = jugador;
		this.familiar = familiar;
		this.parentesco = parentesco;
		this.representante = representante;
		this.estatus = estatus;
		this.fechaIngreso = fechaIngreso;
		this.familiarComisionEquiposForFke079614752b2d13c = familiarComisionEquiposForFke079614752b2d13c;
		this.familiarComisionEquiposForFamiliarJugadorFamiliarComisionFk = familiarComisionEquiposForFamiliarJugadorFamiliarComisionFk;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "cedulaJugador", column = @Column(name = "cedula_jugador", nullable = false)),
			@AttributeOverride(name = "cedulaFamiliar", column = @Column(name = "cedula_familiar", nullable = false)) })
	public FamiliarJugadorId getId() {
		return this.id;
	}

	public void setId(FamiliarJugadorId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cedula_jugador", nullable = false, insertable = false, updatable = false)
	public Jugador getJugador() {
		return this.jugador;
	}

	public void setJugador(Jugador jugador) {
		this.jugador = jugador;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cedula_familiar", nullable = false, insertable = false, updatable = false)
	public Familiar getFamiliar() {
		return this.familiar;
	}

	public void setFamiliar(Familiar familiar) {
		this.familiar = familiar;
	}

	@Column(name = "parentesco", nullable = false)
	public String getParentesco() {
		return this.parentesco;
	}

	public void setParentesco(String parentesco) {
		this.parentesco = parentesco;
	}

	@Column(name = "representante", nullable = false)
	public boolean isRepresentante() {
		return this.representante;
	}

	public void setRepresentante(boolean representante) {
		this.representante = representante;
	}

	@Column(name = "estatus", nullable = false, length = 1)
	public char getEstatus() {
		return this.estatus;
	}

	public void setEstatus(char estatus) {
		this.estatus = estatus;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_ingreso", nullable = false, length = 13)
	public Date getFechaIngreso() {
		return this.fechaIngreso;
	}

	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "familiarJugadorByFke079614752b2d13c")
	public Set<FamiliarComisionEquipo> getFamiliarComisionEquiposForFke079614752b2d13c() {
		return this.familiarComisionEquiposForFke079614752b2d13c;
	}

	public void setFamiliarComisionEquiposForFke079614752b2d13c(
			Set<FamiliarComisionEquipo> familiarComisionEquiposForFke079614752b2d13c) {
		this.familiarComisionEquiposForFke079614752b2d13c = familiarComisionEquiposForFke079614752b2d13c;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "familiarJugadorByFke079614752b2d13c")
	public Set<FamiliarComisionEquipo> getFamiliarComisionEquiposForFamiliarJugadorFamiliarComisionFk() {
		return this.familiarComisionEquiposForFamiliarJugadorFamiliarComisionFk;
	}

	public void setFamiliarComisionEquiposForFamiliarJugadorFamiliarComisionFk(
			Set<FamiliarComisionEquipo> familiarComisionEquiposForFamiliarJugadorFamiliarComisionFk) {
		this.familiarComisionEquiposForFamiliarJugadorFamiliarComisionFk = familiarComisionEquiposForFamiliarJugadorFamiliarComisionFk;
	}

}
