package modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the resultado database table.
 * 
 */
@Entity
public class Resultado implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="codigo_resultado")
	private String codigoResultado;

	private String descripcion;

	private String estatus;

	//bi-directional many-to-one association to ResultadoMantenimiento
	@OneToMany(mappedBy="resultado")
	private Set<ResultadoMantenimiento> resultadoMantenimientos;

    public Resultado() {
    }

	public String getCodigoResultado() {
		return this.codigoResultado;
	}

	public void setCodigoResultado(String codigoResultado) {
		this.codigoResultado = codigoResultado;
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

	public Set<ResultadoMantenimiento> getResultadoMantenimientos() {
		return this.resultadoMantenimientos;
	}

	public void setResultadoMantenimientos(Set<ResultadoMantenimiento> resultadoMantenimientos) {
		this.resultadoMantenimientos = resultadoMantenimientos;
	}
	
}