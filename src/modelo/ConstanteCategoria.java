package modelo;

<<<<<<< HEAD
// Generated 25/01/2012 12:32:42 AM by Hibernate Tools 3.4.0.CR1
=======
<<<<<<< HEAD
// Generated 25/01/2012 12:32:42 AM by Hibernate Tools 3.4.0.CR1
=======
<<<<<<< HEAD
// Generated 25/01/2012 12:32:42 AM by Hibernate Tools 3.4.0.CR1
=======
// Generated 24/01/2012 04:28:30 AM by Hibernate Tools 3.4.0.CR1
>>>>>>> 7d4823278d0a354855f5032a99e2b48c65b33e7f
>>>>>>> 9d67536bdd2e3b33aa6400eb3bb09741a4535bb0
>>>>>>> 873ccb018e72b00b831aa9284cc0745bd1c2d514

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * ConstanteCategoria generated by hbm2java
 */
@Entity
@Table(name = "constante_categoria", schema = "public")
public class ConstanteCategoria implements java.io.Serializable {

	private int codigoConstanteCategoria;
	private Constante constante;
	private Categoria categoria;
	private int valor;

	public ConstanteCategoria() {
	}

	public ConstanteCategoria(int codigoConstanteCategoria,
			Constante constante, Categoria categoria, int valor) {
		this.codigoConstanteCategoria = codigoConstanteCategoria;
		this.constante = constante;
		this.categoria = categoria;
		this.valor = valor;
	}

	@Id
	@Column(name = "codigo_constante_categoria", unique = true, nullable = false)
	public int getCodigoConstanteCategoria() {
		return this.codigoConstanteCategoria;
	}

	public void setCodigoConstanteCategoria(int codigoConstanteCategoria) {
		this.codigoConstanteCategoria = codigoConstanteCategoria;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codigo_constante", nullable = false)
	public Constante getConstante() {
		return this.constante;
	}

	public void setConstante(Constante constante) {
		this.constante = constante;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codigo_categoria_1", nullable = false)
	public Categoria getCategoria() {
		return this.categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	@Column(name = "valor", nullable = false)
	public int getValor() {
		return this.valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

}
