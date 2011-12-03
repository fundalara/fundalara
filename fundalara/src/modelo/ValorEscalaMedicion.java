package modelo;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the valor_escala_medicion database table.
 * 
 */
@Entity
@Table(name="valor_escala_medicion")
public class ValorEscalaMedicion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="cod_valor")
	private String codValor;

	private String descripcion;

	private String valor;

	//bi-directional many-to-one association to EscalaMedicion
    @ManyToOne
	@JoinColumn(name="cod_escala_medicion")
	private EscalaMedicion escalaMedicion;

    public ValorEscalaMedicion() {
    }

	public String getCodValor() {
		return this.codValor;
	}

	public void setCodValor(String codValor) {
		this.codValor = codValor;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getValor() {
		return this.valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public EscalaMedicion getEscalaMedicion() {
		return this.escalaMedicion;
	}

	public void setEscalaMedicion(EscalaMedicion escalaMedicion) {
		this.escalaMedicion = escalaMedicion;
	}
	
}