package modelo;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the indicador_actividad_escala database table.
 * 
 */
@Entity
@Table(name="indicador_actividad_escala")
public class IndicadorActividadEscala implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private IndicadorActividadEscalaPK id;

	//bi-directional many-to-one association to ActividadEntrenamiento
    @ManyToOne
	@JoinColumn(name="cod_actividad_entrenamiento")
	private ActividadEntrenamiento actividadEntrenamiento;

	//bi-directional many-to-one association to Categoria
    @ManyToOne
	@JoinColumn(name="codigo_categoria")
	private Categoria categoria;

	//bi-directional many-to-one association to EscalaMedicion
    @ManyToOne
	@JoinColumn(name="cod_escala_medicion")
	private EscalaMedicion escalaMedicion;

	//bi-directional many-to-one association to Indicador
    @ManyToOne
	@JoinColumn(name="cod_indicador")
	private Indicador indicador;

    public IndicadorActividadEscala() {
    }

	public IndicadorActividadEscalaPK getId() {
		return this.id;
	}

	public void setId(IndicadorActividadEscalaPK id) {
		this.id = id;
	}
	
	public ActividadEntrenamiento getActividadEntrenamiento() {
		return this.actividadEntrenamiento;
	}

	public void setActividadEntrenamiento(ActividadEntrenamiento actividadEntrenamiento) {
		this.actividadEntrenamiento = actividadEntrenamiento;
	}
	
	public Categoria getCategoria() {
		return this.categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	public EscalaMedicion getEscalaMedicion() {
		return this.escalaMedicion;
	}

	public void setEscalaMedicion(EscalaMedicion escalaMedicion) {
		this.escalaMedicion = escalaMedicion;
	}
	
	public Indicador getIndicador() {
		return this.indicador;
	}

	public void setIndicador(Indicador indicador) {
		this.indicador = indicador;
	}
	
}