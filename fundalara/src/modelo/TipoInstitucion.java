package modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the tipo_institucion database table.
 * 
 */
@Entity
@Table(name="tipo_institucion")
public class TipoInstitucion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="codigo_tipo")
	private String codigoTipo;

	private String estatus;

	private String nombre;

	//bi-directional many-to-one association to Institucion
	@OneToMany(mappedBy="tipoInstitucion")
	private Set<Institucion> institucions;

    public TipoInstitucion() {
    }

	public String getCodigoTipo() {
		return this.codigoTipo;
	}

	public void setCodigoTipo(String codigoTipo) {
		this.codigoTipo = codigoTipo;
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

	public Set<Institucion> getInstitucions() {
		return this.institucions;
	}

	public void setInstitucions(Set<Institucion> institucions) {
		this.institucions = institucions;
	}
	
}