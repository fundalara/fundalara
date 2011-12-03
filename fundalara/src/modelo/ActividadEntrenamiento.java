package modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the actividad_entrenamiento database table.
 * 
 */
@Entity
@Table(name="actividad_entrenamiento")
public class ActividadEntrenamiento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="cod_actividad_entrenamiento")
	private String codActividadEntrenamiento;

	private String descripcion;

	private String estatus;

	private String nombre;

	//bi-directional many-to-one association to Fase
    @ManyToOne
	@JoinColumn(name="cod_fase_entrenamiento")
	private Fase fase;

	//bi-directional many-to-one association to IndicadorActividadEscala
	@OneToMany(mappedBy="actividadEntrenamiento")
	private Set<IndicadorActividadEscala> indicadorActividadEscalas;

    public ActividadEntrenamiento() {
    }

	public String getCodActividadEntrenamiento() {
		return this.codActividadEntrenamiento;
	}

	public void setCodActividadEntrenamiento(String codActividadEntrenamiento) {
		this.codActividadEntrenamiento = codActividadEntrenamiento;
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

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Fase getFase() {
		return this.fase;
	}

	public void setFase(Fase fase) {
		this.fase = fase;
	}
	
	public Set<IndicadorActividadEscala> getIndicadorActividadEscalas() {
		return this.indicadorActividadEscalas;
	}

	public void setIndicadorActividadEscalas(Set<IndicadorActividadEscala> indicadorActividadEscalas) {
		this.indicadorActividadEscalas = indicadorActividadEscalas;
	}
	
}