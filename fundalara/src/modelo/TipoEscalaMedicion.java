package modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the tipo_escala_medicion database table.
 * 
 */
@Entity
@Table(name="tipo_escala_medicion")
public class TipoEscalaMedicion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_tipo_escala_medicion")
	private String idTipoEscalaMedicion;

	private String estatus;

	private String nombre;

	//bi-directional many-to-one association to EscalaMedicion
	@OneToMany(mappedBy="tipoEscalaMedicion")
	private Set<EscalaMedicion> escalaMedicions;

    public TipoEscalaMedicion() {
    }

	public String getIdTipoEscalaMedicion() {
		return this.idTipoEscalaMedicion;
	}

	public void setIdTipoEscalaMedicion(String idTipoEscalaMedicion) {
		this.idTipoEscalaMedicion = idTipoEscalaMedicion;
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

	public Set<EscalaMedicion> getEscalaMedicions() {
		return this.escalaMedicions;
	}

	public void setEscalaMedicions(Set<EscalaMedicion> escalaMedicions) {
		this.escalaMedicions = escalaMedicions;
	}
	
}