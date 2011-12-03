package modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the tipo_indicador database table.
 * 
 */
@Entity
@Table(name="tipo_indicador")
public class TipoIndicador implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="cod_tipo_indicador")
	private String codTipoIndicador;

	private String estatus;

	private String nombre;

	//bi-directional many-to-one association to Indicador
	@OneToMany(mappedBy="tipoIndicador")
	private Set<Indicador> indicadors;

    public TipoIndicador() {
    }

	public String getCodTipoIndicador() {
		return this.codTipoIndicador;
	}

	public void setCodTipoIndicador(String codTipoIndicador) {
		this.codTipoIndicador = codTipoIndicador;
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

	public Set<Indicador> getIndicadors() {
		return this.indicadors;
	}

	public void setIndicadors(Set<Indicador> indicadors) {
		this.indicadors = indicadors;
	}
	
}