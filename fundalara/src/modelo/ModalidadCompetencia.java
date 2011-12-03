package modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the modalidad_competencia database table.
 * 
 */
@Entity
@Table(name="modalidad_competencia")
public class ModalidadCompetencia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="codigo_modalidad_competencia")
	private String codigoModalidadCompetencia;

	private String nombre;

	//bi-directional many-to-many association to TipoCompetencia
	@ManyToMany(mappedBy="modalidadCompetencias")
	private Set<TipoCompetencia> tipoCompetencias;

    public ModalidadCompetencia() {
    }

	public String getCodigoModalidadCompetencia() {
		return this.codigoModalidadCompetencia;
	}

	public void setCodigoModalidadCompetencia(String codigoModalidadCompetencia) {
		this.codigoModalidadCompetencia = codigoModalidadCompetencia;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Set<TipoCompetencia> getTipoCompetencias() {
		return this.tipoCompetencias;
	}

	public void setTipoCompetencias(Set<TipoCompetencia> tipoCompetencias) {
		this.tipoCompetencias = tipoCompetencias;
	}
	
}