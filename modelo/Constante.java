package modelo;

// Generated 28/01/2012 11:49:55 AM by Hibernate Tools 3.4.0.CR1

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Constante generated by hbm2java
 */
@Entity
@Table(name = "constante", schema = "public")
public class Constante implements java.io.Serializable {

	private int codigoConstante;
	private String abreviatura;
	private String nombre;
	private char estatus;
	private Set<ConstanteCategoria> constanteCategorias = new HashSet<ConstanteCategoria>(
			0);

	public Constante() {
	}

	public Constante(int codigoConstante, String abreviatura, char estatus) {
		this.codigoConstante = codigoConstante;
		this.abreviatura = abreviatura;
		this.estatus = estatus;
	}

	public Constante(int codigoConstante, String abreviatura, String nombre,
			char estatus, Set<ConstanteCategoria> constanteCategorias) {
		this.codigoConstante = codigoConstante;
		this.abreviatura = abreviatura;
		this.nombre = nombre;
		this.estatus = estatus;
		this.constanteCategorias = constanteCategorias;
	}

	@Id
	@Column(name = "codigo_constante", unique = true, nullable = false)
	public int getCodigoConstante() {
		return this.codigoConstante;
	}

	public void setCodigoConstante(int codigoConstante) {
		this.codigoConstante = codigoConstante;
	}

	@Column(name = "abreviatura", nullable = false)
	public String getAbreviatura() {
		return this.abreviatura;
	}

	public void setAbreviatura(String abreviatura) {
		this.abreviatura = abreviatura;
	}

	@Column(name = "nombre")
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column(name = "estatus", nullable = false, length = 1)
	public char getEstatus() {
		return this.estatus;
	}

	public void setEstatus(char estatus) {
		this.estatus = estatus;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "constante")
	public Set<ConstanteCategoria> getConstanteCategorias() {
		return this.constanteCategorias;
	}

	public void setConstanteCategorias(
			Set<ConstanteCategoria> constanteCategorias) {
		this.constanteCategorias = constanteCategorias;
	}

}
