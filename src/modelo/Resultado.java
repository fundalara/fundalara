package modelo;

// Generated 11/12/2011 04:17:03 PM by Hibernate Tools 3.4.0.CR1

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Resultado generated by hbm2java
 */
@Entity
@Table(name = "resultado")
public class Resultado implements java.io.Serializable {

	private String codigoResultado;
	private String descripcion;
	private char estatus;
	private Set<ResultadoMantenimiento> resultadoMantenimientos = new HashSet<ResultadoMantenimiento>(
			0);

	public Resultado() {
	}

	public Resultado(String codigoResultado, String descripcion, char estatus) {
		this.codigoResultado = codigoResultado;
		this.descripcion = descripcion;
		this.estatus = estatus;
	}

	public Resultado(String codigoResultado, String descripcion, char estatus,
			Set<ResultadoMantenimiento> resultadoMantenimientos) {
		this.codigoResultado = codigoResultado;
		this.descripcion = descripcion;
		this.estatus = estatus;
		this.resultadoMantenimientos = resultadoMantenimientos;
	}

	@Id
	@Column(name = "codigo_resultado", unique = true, nullable = false)
	public String getCodigoResultado() {
		return this.codigoResultado;
	}

	public void setCodigoResultado(String codigoResultado) {
		this.codigoResultado = codigoResultado;
	}

	@Column(name = "descripcion", nullable = false)
	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Column(name = "estatus", nullable = false, length = 1)
	public char getEstatus() {
		return this.estatus;
	}

	public void setEstatus(char estatus) {
		this.estatus = estatus;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "resultado")
	public Set<ResultadoMantenimiento> getResultadoMantenimientos() {
		return this.resultadoMantenimientos;
	}

	public void setResultadoMantenimientos(
			Set<ResultadoMantenimiento> resultadoMantenimientos) {
		this.resultadoMantenimientos = resultadoMantenimientos;
	}

}
