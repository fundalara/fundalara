package modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the empleado_tipo_nomina database table.
 * 
 */
@Entity
@Table(name="empleado_tipo_nomina")
public class EmpleadoTipoNomina implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private EmpleadoTipoNominaPK id;

	private String estado;

	private String estatus;

    @Temporal( TemporalType.DATE)
	@Column(name="fecha_fin")
	private Date fechaFin;

    @Temporal( TemporalType.DATE)
	@Column(name="fecha_inicio")
	private Date fechaInicio;

	//bi-directional many-to-one association to Empleado
    @ManyToOne
	@JoinColumn(name="cedula")
	private Empleado empleado;

	//bi-directional many-to-one association to TipoNomina
    @ManyToOne
	@JoinColumn(name="codigo_tipo_nomina")
	private TipoNomina tipoNomina;

    public EmpleadoTipoNomina() {
    }

	public EmpleadoTipoNominaPK getId() {
		return this.id;
	}

	public void setId(EmpleadoTipoNominaPK id) {
		this.id = id;
	}
	
	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getEstatus() {
		return this.estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

	public Date getFechaFin() {
		return this.fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public Date getFechaInicio() {
		return this.fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Empleado getEmpleado() {
		return this.empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}
	
	public TipoNomina getTipoNomina() {
		return this.tipoNomina;
	}

	public void setTipoNomina(TipoNomina tipoNomina) {
		this.tipoNomina = tipoNomina;
	}
	
}