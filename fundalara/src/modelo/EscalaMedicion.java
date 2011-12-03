package modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the escala_medicion database table.
 * 
 */
@Entity
@Table(name="escala_medicion")
public class EscalaMedicion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="cod_escala_medicion")
	private String codEscalaMedicion;

	private String descripcion;

	private String estatus;

	private String nombre;

	//bi-directional many-to-one association to TipoEscalaMedicion
    @ManyToOne
	@JoinColumn(name="id_tipo_escala_medicion")
	private TipoEscalaMedicion tipoEscalaMedicion;

	//bi-directional many-to-one association to IndicadorActividadEscala
	@OneToMany(mappedBy="escalaMedicion")
	private Set<IndicadorActividadEscala> indicadorActividadEscalas;

	//bi-directional many-to-one association to ValorEscalaMedicion
	@OneToMany(mappedBy="escalaMedicion")
	private Set<ValorEscalaMedicion> valorEscalaMedicions;

    public EscalaMedicion() {
    }

	public String getCodEscalaMedicion() {
		return this.codEscalaMedicion;
	}

	public void setCodEscalaMedicion(String codEscalaMedicion) {
		this.codEscalaMedicion = codEscalaMedicion;
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

	public TipoEscalaMedicion getTipoEscalaMedicion() {
		return this.tipoEscalaMedicion;
	}

	public void setTipoEscalaMedicion(TipoEscalaMedicion tipoEscalaMedicion) {
		this.tipoEscalaMedicion = tipoEscalaMedicion;
	}
	
	public Set<IndicadorActividadEscala> getIndicadorActividadEscalas() {
		return this.indicadorActividadEscalas;
	}

	public void setIndicadorActividadEscalas(Set<IndicadorActividadEscala> indicadorActividadEscalas) {
		this.indicadorActividadEscalas = indicadorActividadEscalas;
	}
	
	public Set<ValorEscalaMedicion> getValorEscalaMedicions() {
		return this.valorEscalaMedicions;
	}

	public void setValorEscalaMedicions(Set<ValorEscalaMedicion> valorEscalaMedicions) {
		this.valorEscalaMedicions = valorEscalaMedicions;
	}
	
}