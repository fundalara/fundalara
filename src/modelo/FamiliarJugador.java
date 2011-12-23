package modelo;

// Generated Dec 23, 2011 1:26:53 PM by Hibernate Tools 3.4.0.CR1

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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * FamiliarJugador generated by hbm2java
 */
@Entity
@Table(name = "familiar_jugador", schema = "public")
public class FamiliarJugador implements java.io.Serializable {

	private int codigoFamiliarJugador;
	private DatoBasico datoBasico;
	private Jugador jugador;
	private Familiar familiar;
	private boolean representanteActual;
	private Date fechaIngreso;
	private char estatus;
	private Set<FamiliarComisionEquipo> familiarComisionEquipos = new HashSet<FamiliarComisionEquipo>(
			0);
	private Representante representante;
	private Set<Hospedaje> hospedajes = new HashSet<Hospedaje>(0);

	public FamiliarJugador() {
	}

	public FamiliarJugador(int codigoFamiliarJugador, DatoBasico datoBasico,
			Jugador jugador, Familiar familiar, boolean representanteActual,
			Date fechaIngreso, char estatus) {
		this.codigoFamiliarJugador = codigoFamiliarJugador;
		this.datoBasico = datoBasico;
		this.jugador = jugador;
		this.familiar = familiar;
		this.representanteActual = representanteActual;
		this.fechaIngreso = fechaIngreso;
		this.estatus = estatus;
	}

	public FamiliarJugador(int codigoFamiliarJugador, DatoBasico datoBasico,
			Jugador jugador, Familiar familiar, boolean representanteActual,
			Date fechaIngreso, char estatus,
			Set<FamiliarComisionEquipo> familiarComisionEquipos,
			Representante representante, Set<Hospedaje> hospedajes) {
		this.codigoFamiliarJugador = codigoFamiliarJugador;
		this.datoBasico = datoBasico;
		this.jugador = jugador;
		this.familiar = familiar;
		this.representanteActual = representanteActual;
		this.fechaIngreso = fechaIngreso;
		this.estatus = estatus;
		this.familiarComisionEquipos = familiarComisionEquipos;
		this.representante = representante;
		this.hospedajes = hospedajes;
	}

	@Id
	@Column(name = "codigo_familiar_jugador", unique = true, nullable = false)
	public int getCodigoFamiliarJugador() {
		return this.codigoFamiliarJugador;
	}

	public void setCodigoFamiliarJugador(int codigoFamiliarJugador) {
		this.codigoFamiliarJugador = codigoFamiliarJugador;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codigo_parentesco", nullable = false)
	public DatoBasico getDatoBasico() {
		return this.datoBasico;
	}

	public void setDatoBasico(DatoBasico datoBasico) {
		this.datoBasico = datoBasico;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cedula_rif", nullable = false)
	public Jugador getJugador() {
		return this.jugador;
	}

	public void setJugador(Jugador jugador) {
		this.jugador = jugador;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cedula_familiar", nullable = false)
	public Familiar getFamiliar() {
		return this.familiar;
	}

	public void setFamiliar(Familiar familiar) {
		this.familiar = familiar;
	}

	@Column(name = "representante_actual", nullable = false)
	public boolean isRepresentanteActual() {
		return this.representanteActual;
	}

	public void setRepresentanteActual(boolean representanteActual) {
		this.representanteActual = representanteActual;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_ingreso", nullable = false, length = 13)
	public Date getFechaIngreso() {
		return this.fechaIngreso;
	}

	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	@Column(name = "estatus", nullable = false, length = 1)
	public char getEstatus() {
		return this.estatus;
	}

	public void setEstatus(char estatus) {
		this.estatus = estatus;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "familiarJugador")
	public Set<FamiliarComisionEquipo> getFamiliarComisionEquipos() {
		return this.familiarComisionEquipos;
	}

	public void setFamiliarComisionEquipos(
			Set<FamiliarComisionEquipo> familiarComisionEquipos) {
		this.familiarComisionEquipos = familiarComisionEquipos;
	}

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "familiarJugador")
	public Representante getRepresentante() {
		return this.representante;
	}

	public void setRepresentante(Representante representante) {
		this.representante = representante;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "familiarJugador")
	public Set<Hospedaje> getHospedajes() {
		return this.hospedajes;
	}

	public void setHospedajes(Set<Hospedaje> hospedajes) {
		this.hospedajes = hospedajes;
	}

}
