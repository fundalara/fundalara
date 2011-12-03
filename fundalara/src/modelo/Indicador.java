package modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the indicador database table.
 * 
 */
@Entity
public class Indicador implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="cod_indicador")
	private String codIndicador;

	private String descripcion;

	private String estatus;

	private String nombre;

	//bi-directional many-to-one association to TipoIndicador
    @ManyToOne
	@JoinColumn(name="cod_tipo_indicador")
	private TipoIndicador tipoIndicador;

	//bi-directional many-to-one association to IndicadorActividadEscala
	@OneToMany(mappedBy="indicador")
	private Set<IndicadorActividadEscala> indicadorActividadEscalas;

    public Indicador() {
    }

	public String getCodIndicador() {
		return this.codIndicador;
	}

	public void setCodIndicador(String codIndicador) {
		this.codIndicador = codIndicador;
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

	public TipoIndicador getTipoIndicador() {
		return this.tipoIndicador;
	}

	public void setTipoIndicador(TipoIndicador tipoIndicador) {
		this.tipoIndicador = tipoIndicador;
	}
	
	public Set<IndicadorActividadEscala> getIndicadorActividadEscalas() {
		return this.indicadorActividadEscalas;
	}

	public void setIndicadorActividadEscalas(Set<IndicadorActividadEscala> indicadorActividadEscalas) {
		this.indicadorActividadEscalas = indicadorActividadEscalas;
	}
	
}