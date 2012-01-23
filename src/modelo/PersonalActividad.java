package modelo;

// Generated 24/01/2012 04:28:30 AM by Hibernate Tools 3.4.0.CR1

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

/**
 * PersonalActividad generated by hbm2java
 */
@Entity
@Table(name = "personal_actividad", schema = "public")
public class PersonalActividad implements java.io.Serializable {

	private PersonalActividadId id;
	private Actividad actividad;
	private Personal personal;
	private char estatus;
	private Set<TareaActividad> tareaActividads = new HashSet<TareaActividad>(0);

	public PersonalActividad() {
	}

	public PersonalActividad(PersonalActividadId id, Actividad actividad,
			Personal personal, char estatus) {
		this.id = id;
		this.actividad = actividad;
		this.personal = personal;
		this.estatus = estatus;
	}

	public PersonalActividad(PersonalActividadId id, Actividad actividad,
			Personal personal, char estatus, Set<TareaActividad> tareaActividads) {
		this.id = id;
		this.actividad = actividad;
		this.personal = personal;
		this.estatus = estatus;
		this.tareaActividads = tareaActividads;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "cedulaRif", column = @Column(name = "cedula_rif", nullable = false)),
			@AttributeOverride(name = "codigoActividad", column = @Column(name = "codigo_actividad", nullable = false)) })
	public PersonalActividadId getId() {
		return this.id;
	}

	public void setId(PersonalActividadId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codigo_actividad", nullable = false, insertable = false, updatable = false)
	public Actividad getActividad() {
		return this.actividad;
	}

	public void setActividad(Actividad actividad) {
		this.actividad = actividad;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cedula_rif", nullable = false, insertable = false, updatable = false)
	public Personal getPersonal() {
		return this.personal;
	}

	public void setPersonal(Personal personal) {
		this.personal = personal;
	}

	@Column(name = "estatus", nullable = false, length = 1)
	public char getEstatus() {
		return this.estatus;
	}

	public void setEstatus(char estatus) {
		this.estatus = estatus;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "personalActividad")
	public Set<TareaActividad> getTareaActividads() {
		return this.tareaActividads;
	}

	public void setTareaActividads(Set<TareaActividad> tareaActividads) {
		this.tareaActividads = tareaActividads;
	}

}
