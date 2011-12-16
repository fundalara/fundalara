package modelo;

// Generated 14/12/2011 05:11:39 PM by Hibernate Tools 3.4.0.CR1

import java.util.Date;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * DatoAcademico generated by hbm2java
 */
@Entity
@Table(name = "dato_academico")
public class DatoAcademico implements java.io.Serializable {

	private DatoAcademicoId id;
	private Institucion institucion;
	private Jugador jugador;
	private Curso curso;
	private AnnoEscolar annoEscolar;
	private byte[] documento;
	private Date fechaIngreso;
	private char estatus;

	public DatoAcademico() {
	}

	public DatoAcademico(DatoAcademicoId id, Institucion institucion,
			Jugador jugador, Curso curso, AnnoEscolar annoEscolar,
			Date fechaIngreso, char estatus) {
		this.id = id;
		this.institucion = institucion;
		this.jugador = jugador;
		this.curso = curso;
		this.annoEscolar = annoEscolar;
		this.fechaIngreso = fechaIngreso;
		this.estatus = estatus;
	}

	public DatoAcademico(DatoAcademicoId id, Institucion institucion,
			Jugador jugador, Curso curso, AnnoEscolar annoEscolar,
			byte[] documento, Date fechaIngreso, char estatus) {
		this.id = id;
		this.institucion = institucion;
		this.jugador = jugador;
		this.curso = curso;
		this.annoEscolar = annoEscolar;
		this.documento = documento;
		this.fechaIngreso = fechaIngreso;
		this.estatus = estatus;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "cedulaJugador", column = @Column(name = "cedula_jugador", nullable = false)),
			@AttributeOverride(name = "codigoInstitucion", column = @Column(name = "codigo_institucion", nullable = false)),
			@AttributeOverride(name = "codigoAnnoEscolar", column = @Column(name = "codigo_anno_escolar", nullable = false)) })
	public DatoAcademicoId getId() {
		return this.id;
	}

	public void setId(DatoAcademicoId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codigo_institucion", nullable = false, insertable = false, updatable = false)
	public Institucion getInstitucion() {
		return this.institucion;
	}

	public void setInstitucion(Institucion institucion) {
		this.institucion = institucion;
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
	@JoinColumn(name = "codigo_curso", nullable = false)
	public Curso getCurso() {
		return this.curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codigo_anno_escolar", nullable = false, insertable = false, updatable = false)
	public AnnoEscolar getAnnoEscolar() {
		return this.annoEscolar;
	}

	public void setAnnoEscolar(AnnoEscolar annoEscolar) {
		this.annoEscolar = annoEscolar;
	}

	@Column(name = "documento")
	public byte[] getDocumento() {
		return this.documento;
	}

	public void setDocumento(byte[] documento) {
		this.documento = documento;
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

}
