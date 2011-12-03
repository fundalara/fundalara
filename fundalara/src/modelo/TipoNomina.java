package modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.Set;


/**
 * The persistent class for the tipo_nomina database table.
 * 
 */
@Entity
@Table(name="tipo_nomina")
public class TipoNomina implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="codigo_tipo_nomina")
	private String codigoTipoNomina;

	private String descripcion;

	private String estatus;

    @Temporal( TemporalType.DATE)
	@Column(name="fecha_actualizacion")
	private Date fechaActualizacion;

	//bi-directional many-to-one association to EmpleadoTipoNomina
	@OneToMany(mappedBy="tipoNomina")
	private Set<EmpleadoTipoNomina> empleadoTipoNominas;

	//bi-directional many-to-one association to PeriodicidadPago
    @ManyToOne
	@JoinColumn(name="codigo_periodicidad_pago")
	private PeriodicidadPago periodicidadPago;

    public TipoNomina() {
    }

	public String getCodigoTipoNomina() {
		return this.codigoTipoNomina;
	}

	public void setCodigoTipoNomina(String codigoTipoNomina) {
		this.codigoTipoNomina = codigoTipoNomina;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getEstatus() {
		return this.estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

	public Date getFechaActualizacion() {
		return this.fechaActualizacion;
	}

	public void setFechaActualizacion(Date fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}

	public Set<EmpleadoTipoNomina> getEmpleadoTipoNominas() {
		return this.empleadoTipoNominas;
	}

	public void setEmpleadoTipoNominas(Set<EmpleadoTipoNomina> empleadoTipoNominas) {
		this.empleadoTipoNominas = empleadoTipoNominas;
	}
	
	public PeriodicidadPago getPeriodicidadPago() {
		return this.periodicidadPago;
	}

	public void setPeriodicidadPago(PeriodicidadPago periodicidadPago) {
		this.periodicidadPago = periodicidadPago;
	}
	
}