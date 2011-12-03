package modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.Set;


/**
 * The persistent class for the medico database table.
 * 
 */
@Entity
public class Medico implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String matricula;

	private String apellido;

	@Column(name="cedula_medico")
	private String cedulaMedico;

	private String estatus;

    @Temporal( TemporalType.DATE)
	@Column(name="fecha_ingreso")
	private Date fechaIngreso;

	private String nombre;

	@Column(name="numero_colegio")
	private String numeroColegio;

	//bi-directional many-to-one association to DatoMedico
	@OneToMany(mappedBy="medico")
	private Set<DatoMedico> datoMedicos;

    public Medico() {
    }

	public String getMatricula() {
		return this.matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getApellido() {
		return this.apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getCedulaMedico() {
		return this.cedulaMedico;
	}

	public void setCedulaMedico(String cedulaMedico) {
		this.cedulaMedico = cedulaMedico;
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

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNumeroColegio() {
		return this.numeroColegio;
	}

	public void setNumeroColegio(String numeroColegio) {
		this.numeroColegio = numeroColegio;
	}

	public Set<DatoMedico> getDatoMedicos() {
		return this.datoMedicos;
	}

	public void setDatoMedicos(Set<DatoMedico> datoMedicos) {
		this.datoMedicos = datoMedicos;
	}
	
}