package modelo;

// Generated 13/01/2012 12:48:04 AM by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * ValorEscala generated by hbm2java
 */
@Entity
@Table(name = "valor_escala", schema = "public")
public class ValorEscala implements java.io.Serializable {

	private int codigoValorEscala;
	private EscalaMedicion escalaMedicion;
	private String valor;
	private String descripcion;
	private char estatus;

	public ValorEscala() {
	}

	public ValorEscala(int codigoValorEscala, EscalaMedicion escalaMedicion,
			String valor, String descripcion, char estatus) {
		this.codigoValorEscala = codigoValorEscala;
		this.escalaMedicion = escalaMedicion;
		this.valor = valor;
		this.descripcion = descripcion;
		this.estatus = estatus;
	}

	@Id
	@Column(name = "codigo_valor_escala", unique = true, nullable = false)
	public int getCodigoValorEscala() {
		return this.codigoValorEscala;
	}

	public void setCodigoValorEscala(int codigoValorEscala) {
		this.codigoValorEscala = codigoValorEscala;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codigo_escala", nullable = false)
	public EscalaMedicion getEscalaMedicion() {
		return this.escalaMedicion;
	}

	public void setEscalaMedicion(EscalaMedicion escalaMedicion) {
		this.escalaMedicion = escalaMedicion;
	}

	@Column(name = "valor", nullable = false)
	public String getValor() {
		return this.valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
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

}
