package modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the tipo_competencia database table.
 * 
 */
@Entity
@Table(name="tipo_competencia")
public class TipoCompetencia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="codigo_tipo_competencia")
	private String codigoTipoCompetencia;

	private String nombre;

	//bi-directional many-to-many association to ModalidadCompetencia
    @ManyToMany
	@JoinTable(
		name="tipo_modalidad_competencia"
		, joinColumns={
			@JoinColumn(name="codigo_tipo_competencia")
			}
		, inverseJoinColumns={
			@JoinColumn(name="codigo_modalidad_competencia")
			}
		)
	private Set<ModalidadCompetencia> modalidadCompetencias;

    public TipoCompetencia() {
    }

	public String getCodigoTipoCompetencia() {
		return this.codigoTipoCompetencia;
	}

	public void setCodigoTipoCompetencia(String codigoTipoCompetencia) {
		this.codigoTipoCompetencia = codigoTipoCompetencia;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Set<ModalidadCompetencia> getModalidadCompetencias() {
		return this.modalidadCompetencias;
	}

	public void setModalidadCompetencias(Set<ModalidadCompetencia> modalidadCompetencias) {
		this.modalidadCompetencias = modalidadCompetencias;
	}
	
}