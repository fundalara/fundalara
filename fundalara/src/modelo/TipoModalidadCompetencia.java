package modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the tipo_modalidad_competencia database table.
 * 
 */
@Entity
@Table(name="tipo_modalidad_competencia")
public class TipoModalidadCompetencia implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private TipoModalidadCompetenciaPK id;

	//bi-directional many-to-one association to Competencia
	@OneToMany(mappedBy="tipoModalidadCompetencia")
	private Set<Competencia> competencias;

    public TipoModalidadCompetencia() {
    }

	public TipoModalidadCompetenciaPK getId() {
		return this.id;
	}

	public void setId(TipoModalidadCompetenciaPK id) {
		this.id = id;
	}
	
	public Set<Competencia> getCompetencias() {
		return this.competencias;
	}

	public void setCompetencias(Set<Competencia> competencias) {
		this.competencias = competencias;
	}
	
}