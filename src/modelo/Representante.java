package modelo;

// Generated 13/01/2012 02:49:46 AM by Hibernate Tools 3.4.0.CR1

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * Representante generated by hbm2java
 */
@Entity
@Table(name = "representante", schema = "public")
public class Representante implements java.io.Serializable {

	private int codigoFamiliarJugador;
	private FamiliarJugador familiarJugador;
	private Date fechaAsignacion;
	private Date fechaRetiro;

	public Representante() {
	}

	public Representante(FamiliarJugador familiarJugador, Date fechaAsignacion,
			Date fechaRetiro) {
		this.familiarJugador = familiarJugador;
		this.fechaAsignacion = fechaAsignacion;
		this.fechaRetiro = fechaRetiro;
	}

	@GenericGenerator(name = "generator", strategy = "foreign", parameters = @Parameter(name = "property", value = "familiarJugador"))
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "codigo_familiar_jugador", unique = true, nullable = false)
	public int getCodigoFamiliarJugador() {
		return this.codigoFamiliarJugador;
	}

	public void setCodigoFamiliarJugador(int codigoFamiliarJugador) {
		this.codigoFamiliarJugador = codigoFamiliarJugador;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	public FamiliarJugador getFamiliarJugador() {
		return this.familiarJugador;
	}

	public void setFamiliarJugador(FamiliarJugador familiarJugador) {
		this.familiarJugador = familiarJugador;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_asignacion", nullable = false, length = 13)
	public Date getFechaAsignacion() {
		return this.fechaAsignacion;
	}

	public void setFechaAsignacion(Date fechaAsignacion) {
		this.fechaAsignacion = fechaAsignacion;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_retiro", nullable = false, length = 13)
	public Date getFechaRetiro() {
		return this.fechaRetiro;
	}

	public void setFechaRetiro(Date fechaRetiro) {
		this.fechaRetiro = fechaRetiro;
	}

}
