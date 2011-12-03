package modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the dato_academico database table.
 * 
 */
@Entity
@Table(name="dato_academico")
public class DatoAcademico implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private DatoAcademicoPK id;

	private byte[] documento;

	private String estatus;

    @Temporal( TemporalType.DATE)
	@Column(name="fecha_ingreso")
	private Date fechaIngreso;

	//bi-directional many-to-one association to AnnoEscolar
    @ManyToOne
	@JoinColumn(name="codigo_anno_escolar")
	private AnnoEscolar annoEscolar;

	//bi-directional many-to-one association to Curso
    @ManyToOne
	@JoinColumn(name="codigo_curso")
	private Curso curso;

	//bi-directional many-to-one association to Institucion
    @ManyToOne
	@JoinColumn(name="codigo_institucion")
	private Institucion institucion;

	//bi-directional many-to-one association to Jugador
    @ManyToOne
	@JoinColumn(name="cedula_jugador")
	private Jugador jugador;

    public DatoAcademico() {
    }

	public DatoAcademicoPK getId() {
		return this.id;
	}

	public void setId(DatoAcademicoPK id) {
		this.id = id;
	}
	
	public byte[] getDocumento() {
		return this.documento;
	}

	public void setDocumento(byte[] documento) {
		this.documento = documento;
	}

	public String getEstatus() {
		return this.estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

	public Date getFechaIngreso() {
		return this.fechaIngreso;
	}

	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public AnnoEscolar getAnnoEscolar() {
		return this.annoEscolar;
	}

	public void setAnnoEscolar(AnnoEscolar annoEscolar) {
		this.annoEscolar = annoEscolar;
	}
	
	public Curso getCurso() {
		return this.curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}
	
	public Institucion getInstitucion() {
		return this.institucion;
	}

	public void setInstitucion(Institucion institucion) {
		this.institucion = institucion;
	}
	
	public Jugador getJugador() {
		return this.jugador;
	}

	public void setJugador(Jugador jugador) {
		this.jugador = jugador;
	}
	
}