package modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the tipo_logro database table.
 * 
 */
@Entity
@Table(name="tipo_logro")
public class TipoLogro implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="codigo_logro")
	private String codigoLogro;

	private String estatus;

	private String nombre;

	//bi-directional many-to-one association to DatoDeportivo
	@OneToMany(mappedBy="tipoLogro")
	private Set<DatoDeportivo> datoDeportivos;

    public TipoLogro() {
    }

	public String getCodigoLogro() {
		return this.codigoLogro;
	}

	public void setCodigoLogro(String codigoLogro) {
		this.codigoLogro = codigoLogro;
	}

	public String getEstatus() {
		return this.estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Set<DatoDeportivo> getDatoDeportivos() {
		return this.datoDeportivos;
	}

	public void setDatoDeportivos(Set<DatoDeportivo> datoDeportivos) {
		this.datoDeportivos = datoDeportivos;
	}
	
}