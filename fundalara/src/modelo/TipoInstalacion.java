package modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the tipo_instalacion database table.
 * 
 */
@Entity
@Table(name="tipo_instalacion")
public class TipoInstalacion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="codigo_tipo_instalacion")
	private String codigoTipoInstalacion;

	private String descripcion;

	private String estatus;

	//bi-directional many-to-one association to Instalacion
	@OneToMany(mappedBy="tipoInstalacion")
	private Set<Instalacion> instalacions;

    public TipoInstalacion() {
    }

	public String getCodigoTipoInstalacion() {
		return this.codigoTipoInstalacion;
	}

	public void setCodigoTipoInstalacion(String codigoTipoInstalacion) {
		this.codigoTipoInstalacion = codigoTipoInstalacion;
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

	public Set<Instalacion> getInstalacions() {
		return this.instalacions;
	}

	public void setInstalacions(Set<Instalacion> instalacions) {
		this.instalacions = instalacions;
	}
	
}