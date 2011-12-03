package modelo;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the fase_competencia database table.
 * 
 */
@Entity
@Table(name="fase_competencia")
public class FaseCompetencia implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private FaseCompetenciaPK id;

	@Column(name="equipo_clasifican")
	private Integer equipoClasifican;

	@Column(name="equipo_ingresan")
	private Integer equipoIngresan;

	//bi-directional many-to-one association to Competencia
    @ManyToOne
	@JoinColumn(name="codigo_competencia")
	private Competencia competencia;

    public FaseCompetencia() {
    }

	public FaseCompetenciaPK getId() {
		return this.id;
	}

	public void setId(FaseCompetenciaPK id) {
		this.id = id;
	}
	
	public Integer getEquipoClasifican() {
		return this.equipoClasifican;
	}

	public void setEquipoClasifican(Integer equipoClasifican) {
		this.equipoClasifican = equipoClasifican;
	}

	public Integer getEquipoIngresan() {
		return this.equipoIngresan;
	}

	public void setEquipoIngresan(Integer equipoIngresan) {
		this.equipoIngresan = equipoIngresan;
	}

	public Competencia getCompetencia() {
		return this.competencia;
	}

	public void setCompetencia(Competencia competencia) {
		this.competencia = competencia;
	}
	
}