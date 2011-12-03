package modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the tipo_actividad_social database table.
 * 
 */
@Entity
@Table(name="tipo_actividad_social")
public class TipoActividadSocial implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="codigo_actividad_social")
	private String codigoActividadSocial;

	private String estatus;

	private String nombre;

	//bi-directional many-to-one association to DatoSocial
//	@OneToMany(mappedBy="tipoActividadSocial")
	//private Set<DatoSocial> datoSocials;

    public TipoActividadSocial() {
    }

	public String getCodigoActividadSocial() {
		return this.codigoActividadSocial;
	}

	public void setCodigoActividadSocial(String codigoActividadSocial) {
		this.codigoActividadSocial = codigoActividadSocial;
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

//	public Set<DatoSocial> getDatoSocials() {
//		return this.datoSocials;
//	}
//
//	public void setDatoSocials(Set<DatoSocial> datoSocials) {
//		this.datoSocials = datoSocials;
//	}
	
}