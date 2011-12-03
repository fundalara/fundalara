package modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the dato_academico_1 database table.
 * 
 */
@Entity
@Table(name="dato_academico_1")
public class DatoAcademico1 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="codigo_dato_academico")
	private String codigoDatoAcademico;

	private String estatus;

    @Temporal( TemporalType.DATE)
	@Column(name="fecha_egreso")
	private Date fechaEgreso;

	private String instituto;

	private String titulo;

	//bi-directional many-to-one association to Empleado
    @ManyToOne
	@JoinColumn(name="cedula")
	private Empleado empleado;

    public DatoAcademico1() {
    }

	public String getCodigoDatoAcademico() {
		return this.codigoDatoAcademico;
	}

	public void setCodigoDatoAcademico(String codigoDatoAcademico) {
		this.codigoDatoAcademico = codigoDatoAcademico;
	}

	public String getEstatus() {
		return this.estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

	public Date getFechaEgreso() {
		return this.fechaEgreso;
	}

	public void setFechaEgreso(Date fechaEgreso) {
		this.fechaEgreso = fechaEgreso;
	}

	public String getInstituto() {
		return this.instituto;
	}

	public void setInstituto(String instituto) {
		this.instituto = instituto;
	}

	public String getTitulo() {
		return this.titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Empleado getEmpleado() {
		return this.empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}
	
}