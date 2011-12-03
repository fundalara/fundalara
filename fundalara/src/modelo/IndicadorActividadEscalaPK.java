package modelo;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the indicador_actividad_escala database table.
 * 
 */
@Embeddable
public class IndicadorActividadEscalaPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="cod_indicador")
	private String codIndicador;

	@Column(name="cod_actividad_entrenamiento")
	private String codActividadEntrenamiento;

	@Column(name="cod_escala_medicion")
	private String codEscalaMedicion;

	@Column(name="codigo_categoria")
	private String codigoCategoria;

    public IndicadorActividadEscalaPK() {
    }
	public String getCodIndicador() {
		return this.codIndicador;
	}
	public void setCodIndicador(String codIndicador) {
		this.codIndicador = codIndicador;
	}
	public String getCodActividadEntrenamiento() {
		return this.codActividadEntrenamiento;
	}
	public void setCodActividadEntrenamiento(String codActividadEntrenamiento) {
		this.codActividadEntrenamiento = codActividadEntrenamiento;
	}
	public String getCodEscalaMedicion() {
		return this.codEscalaMedicion;
	}
	public void setCodEscalaMedicion(String codEscalaMedicion) {
		this.codEscalaMedicion = codEscalaMedicion;
	}
	public String getCodigoCategoria() {
		return this.codigoCategoria;
	}
	public void setCodigoCategoria(String codigoCategoria) {
		this.codigoCategoria = codigoCategoria;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof IndicadorActividadEscalaPK)) {
			return false;
		}
		IndicadorActividadEscalaPK castOther = (IndicadorActividadEscalaPK)other;
		return 
			this.codIndicador.equals(castOther.codIndicador)
			&& this.codActividadEntrenamiento.equals(castOther.codActividadEntrenamiento)
			&& this.codEscalaMedicion.equals(castOther.codEscalaMedicion)
			&& this.codigoCategoria.equals(castOther.codigoCategoria);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.codIndicador.hashCode();
		hash = hash * prime + this.codActividadEntrenamiento.hashCode();
		hash = hash * prime + this.codEscalaMedicion.hashCode();
		hash = hash * prime + this.codigoCategoria.hashCode();
		
		return hash;
    }
}