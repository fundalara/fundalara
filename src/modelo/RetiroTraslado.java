package modelo;

// Generated 11/02/2012 01:49:19 AM by Hibernate Tools 3.4.0.CR1

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * RetiroTraslado generated by hbm2java
 */
@Entity
@Table(name = "retiro_traslado", schema = "public")
public class RetiroTraslado implements java.io.Serializable {

	private int codigoRetiroTraslado;
	private DatoBasico datoBasicoByCodigoTipoOperacion;
	private DatoBasico datoBasicoByCodigoMotivoRetiro;
	private Jugador jugador;
	private Date fechaRetiro;
	private char estatus;

	public RetiroTraslado() {
	}

	public RetiroTraslado(int codigoRetiroTraslado,
			DatoBasico datoBasicoByCodigoTipoOperacion,
			DatoBasico datoBasicoByCodigoMotivoRetiro, Jugador jugador,
			Date fechaRetiro, char estatus) {
		this.codigoRetiroTraslado = codigoRetiroTraslado;
		this.datoBasicoByCodigoTipoOperacion = datoBasicoByCodigoTipoOperacion;
		this.datoBasicoByCodigoMotivoRetiro = datoBasicoByCodigoMotivoRetiro;
		this.jugador = jugador;
		this.fechaRetiro = fechaRetiro;
		this.estatus = estatus;
	}

	@Id
	@Column(name = "codigo_retiro_traslado", unique = true, nullable = false)
	public int getCodigoRetiroTraslado() {
		return this.codigoRetiroTraslado;
	}

	public void setCodigoRetiroTraslado(int codigoRetiroTraslado) {
		this.codigoRetiroTraslado = codigoRetiroTraslado;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codigo_tipo_operacion", nullable = false)
	public DatoBasico getDatoBasicoByCodigoTipoOperacion() {
		return this.datoBasicoByCodigoTipoOperacion;
	}

	public void setDatoBasicoByCodigoTipoOperacion(
			DatoBasico datoBasicoByCodigoTipoOperacion) {
		this.datoBasicoByCodigoTipoOperacion = datoBasicoByCodigoTipoOperacion;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codigo_motivo_retiro", nullable = false)
	public DatoBasico getDatoBasicoByCodigoMotivoRetiro() {
		return this.datoBasicoByCodigoMotivoRetiro;
	}

	public void setDatoBasicoByCodigoMotivoRetiro(
			DatoBasico datoBasicoByCodigoMotivoRetiro) {
		this.datoBasicoByCodigoMotivoRetiro = datoBasicoByCodigoMotivoRetiro;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cedula_rif", nullable = false)
	public Jugador getJugador() {
		return this.jugador;
	}

	public void setJugador(Jugador jugador) {
		this.jugador = jugador;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_retiro", nullable = false, length = 13)
	public Date getFechaRetiro() {
		return this.fechaRetiro;
	}

	public void setFechaRetiro(Date fechaRetiro) {
		this.fechaRetiro = fechaRetiro;
	}

	@Column(name = "estatus", nullable = false, length = 1)
	public char getEstatus() {
		return this.estatus;
	}

	public void setEstatus(char estatus) {
		this.estatus = estatus;
	}

}
