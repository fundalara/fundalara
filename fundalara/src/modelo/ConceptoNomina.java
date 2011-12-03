package modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.Set;


/**
 * The persistent class for the concepto_nomina database table.
 * 
 */
@Entity
@Table(name="concepto_nomina")
public class ConceptoNomina implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="codigo_concepto_nomina")
	private String codigoConceptoNomina;

	@Column(name="aplicable_sueldo")
	private Boolean aplicableSueldo;

	private String estatus;

    @Temporal( TemporalType.DATE)
	@Column(name="fecha_activacion")
	private Date fechaActivacion;

	@Column(name="tipo_aplicacion")
	private Boolean tipoAplicacion;

	private double valor;

	//bi-directional many-to-one association to TipoConceptoNomina
    @ManyToOne
	@JoinColumn(name="codigo_tipo_concepto_nomina")
	private TipoConceptoNomina tipoConceptoNomina;

	//bi-directional many-to-one association to EmpleadoConceptoNomina
	@OneToMany(mappedBy="conceptoNomina")
	private Set<EmpleadoConceptoNomina> empleadoConceptoNominas;

    public ConceptoNomina() {
    }

	public String getCodigoConceptoNomina() {
		return this.codigoConceptoNomina;
	}

	public void setCodigoConceptoNomina(String codigoConceptoNomina) {
		this.codigoConceptoNomina = codigoConceptoNomina;
	}

	public Boolean getAplicableSueldo() {
		return this.aplicableSueldo;
	}

	public void setAplicableSueldo(Boolean aplicableSueldo) {
		this.aplicableSueldo = aplicableSueldo;
	}

	public String getEstatus() {
		return this.estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

	public Date getFechaActivacion() {
		return this.fechaActivacion;
	}

	public void setFechaActivacion(Date fechaActivacion) {
		this.fechaActivacion = fechaActivacion;
	}

	public Boolean getTipoAplicacion() {
		return this.tipoAplicacion;
	}

	public void setTipoAplicacion(Boolean tipoAplicacion) {
		this.tipoAplicacion = tipoAplicacion;
	}

	public double getValor() {
		return this.valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public TipoConceptoNomina getTipoConceptoNomina() {
		return this.tipoConceptoNomina;
	}

	public void setTipoConceptoNomina(TipoConceptoNomina tipoConceptoNomina) {
		this.tipoConceptoNomina = tipoConceptoNomina;
	}
	
	public Set<EmpleadoConceptoNomina> getEmpleadoConceptoNominas() {
		return this.empleadoConceptoNominas;
	}

	public void setEmpleadoConceptoNominas(Set<EmpleadoConceptoNomina> empleadoConceptoNominas) {
		this.empleadoConceptoNominas = empleadoConceptoNominas;
	}
	
}