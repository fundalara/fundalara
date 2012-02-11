package modelo;

// Generated 11/02/2012 01:49:19 AM by Hibernate Tools 3.4.0.CR1

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
 * DatoConducta generated by hbm2java
 */
@Entity
@Table(name = "dato_conducta", schema = "public")
public class DatoConducta implements java.io.Serializable {

	private int codigoDatoConducta;
	private DatoBasico datoBasico;
	private Jugador jugador;
	private Date fechaInicio;
	private Date fechaFin;
	private String observacion;
	private int cantidad;
	private Date fechaOcurrencia;
	private char estatus;
	private Set<DocumentoConducta> documentoConductas = new HashSet<DocumentoConducta>(
			0);
	private Set<MotivoSancion> motivoSancions = new HashSet<MotivoSancion>(0);

	public DatoConducta() {
	}

	public DatoConducta(int codigoDatoConducta, DatoBasico datoBasico,
			Jugador jugador, Date fechaInicio, int cantidad,
			Date fechaOcurrencia, char estatus) {
		this.codigoDatoConducta = codigoDatoConducta;
		this.datoBasico = datoBasico;
		this.jugador = jugador;
		this.fechaInicio = fechaInicio;
		this.cantidad = cantidad;
		this.fechaOcurrencia = fechaOcurrencia;
		this.estatus = estatus;
	}

	public DatoConducta(int codigoDatoConducta, DatoBasico datoBasico,
			Jugador jugador, Date fechaInicio, Date fechaFin,
			String observacion, int cantidad, Date fechaOcurrencia,
			char estatus, Set<DocumentoConducta> documentoConductas,
			Set<MotivoSancion> motivoSancions) {
		this.codigoDatoConducta = codigoDatoConducta;
		this.datoBasico = datoBasico;
		this.jugador = jugador;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.observacion = observacion;
		this.cantidad = cantidad;
		this.fechaOcurrencia = fechaOcurrencia;
		this.estatus = estatus;
		this.documentoConductas = documentoConductas;
		this.motivoSancions = motivoSancions;
	}

	@Id
	@Column(name = "codigo_dato_conducta", unique = true, nullable = false)
	public int getCodigoDatoConducta() {
		return this.codigoDatoConducta;
	}

	public void setCodigoDatoConducta(int codigoDatoConducta) {
		this.codigoDatoConducta = codigoDatoConducta;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codigo_tipo_suspension", nullable = false)
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

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_inicio", nullable = false, length = 13)
	public Date getFechaInicio() {
		return this.fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_fin", length = 13)
	public Date getFechaFin() {
		return this.fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	@Column(name = "observacion")
	public String getObservacion() {
		return this.observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	@Column(name = "cantidad", nullable = false)
	public int getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_ocurrencia", nullable = false, length = 13)
	public Date getFechaOcurrencia() {
		return this.fechaOcurrencia;
	}

	public void setFechaOcurrencia(Date fechaOcurrencia) {
		this.fechaOcurrencia = fechaOcurrencia;
	}

	@Column(name = "estatus", nullable = false, length = 1)
	public char getEstatus() {
		return this.estatus;
	}

	public void setEstatus(char estatus) {
		this.estatus = estatus;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "datoConducta")
	public Set<DocumentoConducta> getDocumentoConductas() {
		return this.documentoConductas;
	}

	public void setDocumentoConductas(Set<DocumentoConducta> documentoConductas) {
		this.documentoConductas = documentoConductas;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "datoConducta")
	public Set<MotivoSancion> getMotivoSancions() {
		return this.motivoSancions;
	}

	public void setMotivoSancions(Set<MotivoSancion> motivoSancions) {
		this.motivoSancions = motivoSancions;
	}

}
