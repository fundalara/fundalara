package modelo;

// Generated 16/12/2011 03:51:27 PM by Hibernate Tools 3.4.0.CR1

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * PersonalEquipo generated by hbm2java
 */
@Entity
@Table(name = "personal_equipo")
public class PersonalEquipo implements java.io.Serializable {

	private String codigoEquipo;
	private Temporada temporada;
	private Equipo equipo;
	private Date fechaInicio;
	private Date fechaFinalizacion;
	private String eventualidad;

	public PersonalEquipo() {
	}

	public PersonalEquipo(Temporada temporada, Equipo equipo, Date fechaInicio,
			Date fechaFinalizacion, String eventualidad) {
		this.temporada = temporada;
		this.equipo = equipo;
		this.fechaInicio = fechaInicio;
		this.fechaFinalizacion = fechaFinalizacion;
		this.eventualidad = eventualidad;
	}

	@GenericGenerator(name = "generator", strategy = "foreign", parameters = @Parameter(name = "property", value = "equipo"))
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "codigo_equipo", unique = true, nullable = false)
	public String getCodigoEquipo() {
		return this.codigoEquipo;
	}

	public void setCodigoEquipo(String codigoEquipo) {
		this.codigoEquipo = codigoEquipo;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codigo_temporada", nullable = false)
	public Temporada getTemporada() {
		return this.temporada;
	}

	public void setTemporada(Temporada temporada) {
		this.temporada = temporada;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	public Equipo getEquipo() {
		return this.equipo;
	}

	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
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
	@Column(name = "fecha_finalizacion", nullable = false, length = 13)
	public Date getFechaFinalizacion() {
		return this.fechaFinalizacion;
	}

	public void setFechaFinalizacion(Date fechaFinalizacion) {
		this.fechaFinalizacion = fechaFinalizacion;
	}

	@Column(name = "eventualidad", nullable = false)
	public String getEventualidad() {
		return this.eventualidad;
	}

	public void setEventualidad(String eventualidad) {
		this.eventualidad = eventualidad;
	}

}