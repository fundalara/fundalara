package modelo;

// Generated 31/12/2011 11:02:01 AM by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * TipoPagoRepresentante generated by hbm2java
 */
@Entity
@Table(name = "tipo_pago_representante", schema = "public")
public class TipoPagoRepresentante implements java.io.Serializable {

	private int codigoTipoIngreso;
	private DatoBasico datoBasico;
	private int codigoTipoPagoRepresentante;
	private String descripcion;
	private Double monto;
	private char estatus;

	public TipoPagoRepresentante() {
	}

	public TipoPagoRepresentante(DatoBasico datoBasico,
			int codigoTipoPagoRepresentante, String descripcion, char estatus) {
		this.datoBasico = datoBasico;
		this.codigoTipoPagoRepresentante = codigoTipoPagoRepresentante;
		this.descripcion = descripcion;
		this.estatus = estatus;
	}

	public TipoPagoRepresentante(DatoBasico datoBasico,
			int codigoTipoPagoRepresentante, String descripcion, Double monto,
			char estatus) {
		this.datoBasico = datoBasico;
		this.codigoTipoPagoRepresentante = codigoTipoPagoRepresentante;
		this.descripcion = descripcion;
		this.monto = monto;
		this.estatus = estatus;
	}

	@GenericGenerator(name = "generator", strategy = "foreign", parameters = @Parameter(name = "property", value = "datoBasico"))
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "codigo_tipo_ingreso", unique = true, nullable = false)
	public int getCodigoTipoIngreso() {
		return this.codigoTipoIngreso;
	}

	public void setCodigoTipoIngreso(int codigoTipoIngreso) {
		this.codigoTipoIngreso = codigoTipoIngreso;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	public DatoBasico getDatoBasico() {
		return this.datoBasico;
	}

	public void setDatoBasico(DatoBasico datoBasico) {
		this.datoBasico = datoBasico;
	}

	@Column(name = "codigo_tipo_pago_representante", nullable = false)
	public int getCodigoTipoPagoRepresentante() {
		return this.codigoTipoPagoRepresentante;
	}

	public void setCodigoTipoPagoRepresentante(int codigoTipoPagoRepresentante) {
		this.codigoTipoPagoRepresentante = codigoTipoPagoRepresentante;
	}

	@Column(name = "descripcion", nullable = false)
	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Column(name = "monto", precision = 17, scale = 17)
	public Double getMonto() {
		return this.monto;
	}

	public void setMonto(Double monto) {
		this.monto = monto;
	}

	@Column(name = "estatus", nullable = false, length = 1)
	public char getEstatus() {
		return this.estatus;
	}

	public void setEstatus(char estatus) {
		this.estatus = estatus;
	}

}
