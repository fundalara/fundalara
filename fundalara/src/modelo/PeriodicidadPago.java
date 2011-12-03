package modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the periodicidad_pago database table.
 * 
 */
@Entity
@Table(name="periodicidad_pago")
public class PeriodicidadPago implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="codigo_periodicidad_pago")
	private String codigoPeriodicidadPago;

	private String descripcion;

	private String estatus;

	//bi-directional many-to-one association to TipoNomina
	@OneToMany(mappedBy="periodicidadPago")
	private Set<TipoNomina> tipoNominas;

    public PeriodicidadPago() {
    }

	public String getCodigoPeriodicidadPago() {
		return this.codigoPeriodicidadPago;
	}

	public void setCodigoPeriodicidadPago(String codigoPeriodicidadPago) {
		this.codigoPeriodicidadPago = codigoPeriodicidadPago;
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

	public Set<TipoNomina> getTipoNominas() {
		return this.tipoNominas;
	}

	public void setTipoNominas(Set<TipoNomina> tipoNominas) {
		this.tipoNominas = tipoNominas;
	}
	
}