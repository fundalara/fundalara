package modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the tipo_concepto_nomina database table.
 * 
 */
@Entity
@Table(name="tipo_concepto_nomina")
public class TipoConceptoNomina implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="codigo_tipo_concepto_nomina")
	private String codigoTipoConceptoNomina;

	private String descripcion;

	private String estatus;

	//bi-directional many-to-one association to ConceptoNomina
	@OneToMany(mappedBy="tipoConceptoNomina")
	private Set<ConceptoNomina> conceptoNominas;

    public TipoConceptoNomina() {
    }

	public String getCodigoTipoConceptoNomina() {
		return this.codigoTipoConceptoNomina;
	}

	public void setCodigoTipoConceptoNomina(String codigoTipoConceptoNomina) {
		this.codigoTipoConceptoNomina = codigoTipoConceptoNomina;
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

	public Set<ConceptoNomina> getConceptoNominas() {
		return this.conceptoNominas;
	}

	public void setConceptoNominas(Set<ConceptoNomina> conceptoNominas) {
		this.conceptoNominas = conceptoNominas;
	}
	
}