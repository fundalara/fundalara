package modelo;

// Generated 28/01/2012 11:49:55 AM by Hibernate Tools 3.4.0.CR1

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * IngresoFormaPago generated by hbm2java
 */
@Entity
@Table(name = "ingreso_forma_pago", schema = "public")
public class IngresoFormaPago implements java.io.Serializable {

	private IngresoFormaPagoId id;
	private Ingreso ingreso;
	private DatoBasico datoBasicoByCodigoFormaPago;
	private DatoBasico datoBasicoByCodigoTarjeta;
	private DatoBasico datoBasicoByCodigoBanco;
	private double monto;
	private char estatus;
	private String numeroDocumentoPago;

	public IngresoFormaPago() {
	}

	public IngresoFormaPago(IngresoFormaPagoId id, Ingreso ingreso,
			double monto, char estatus) {
		this.id = id;
		this.ingreso = ingreso;
		this.monto = monto;
		this.estatus = estatus;
	}

	public IngresoFormaPago(IngresoFormaPagoId id, Ingreso ingreso,
			DatoBasico datoBasicoByCodigoFormaPago,
			DatoBasico datoBasicoByCodigoTarjeta,
			DatoBasico datoBasicoByCodigoBanco, double monto, char estatus,
			String numeroDocumentoPago) {
		this.id = id;
		this.ingreso = ingreso;
		this.datoBasicoByCodigoFormaPago = datoBasicoByCodigoFormaPago;
		this.datoBasicoByCodigoTarjeta = datoBasicoByCodigoTarjeta;
		this.datoBasicoByCodigoBanco = datoBasicoByCodigoBanco;
		this.monto = monto;
		this.estatus = estatus;
		this.numeroDocumentoPago = numeroDocumentoPago;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "codigoIngresoFormaPago", column = @Column(name = "codigo_ingreso_forma_pago", nullable = false)),
			@AttributeOverride(name = "codigoIngreso", column = @Column(name = "codigo_ingreso", nullable = false)) })
	public IngresoFormaPagoId getId() {
		return this.id;
	}

	public void setId(IngresoFormaPagoId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codigo_ingreso", nullable = false, insertable = false, updatable = false)
	public Ingreso getIngreso() {
		return this.ingreso;
	}

	public void setIngreso(Ingreso ingreso) {
		this.ingreso = ingreso;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codigo_forma_pago")
	public DatoBasico getDatoBasicoByCodigoFormaPago() {
		return this.datoBasicoByCodigoFormaPago;
	}

	public void setDatoBasicoByCodigoFormaPago(
			DatoBasico datoBasicoByCodigoFormaPago) {
		this.datoBasicoByCodigoFormaPago = datoBasicoByCodigoFormaPago;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codigo_tarjeta")
	public DatoBasico getDatoBasicoByCodigoTarjeta() {
		return this.datoBasicoByCodigoTarjeta;
	}

	public void setDatoBasicoByCodigoTarjeta(
			DatoBasico datoBasicoByCodigoTarjeta) {
		this.datoBasicoByCodigoTarjeta = datoBasicoByCodigoTarjeta;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codigo_banco")
	public DatoBasico getDatoBasicoByCodigoBanco() {
		return this.datoBasicoByCodigoBanco;
	}

	public void setDatoBasicoByCodigoBanco(DatoBasico datoBasicoByCodigoBanco) {
		this.datoBasicoByCodigoBanco = datoBasicoByCodigoBanco;
	}

	@Column(name = "monto", nullable = false, precision = 17, scale = 17)
	public double getMonto() {
		return this.monto;
	}

	public void setMonto(double monto) {
		this.monto = monto;
	}

	@Column(name = "estatus", nullable = false, length = 1)
	public char getEstatus() {
		return this.estatus;
	}

	public void setEstatus(char estatus) {
		this.estatus = estatus;
	}

	@Column(name = "numero_documento_pago")
	public String getNumeroDocumentoPago() {
		return this.numeroDocumentoPago;
	}

	public void setNumeroDocumentoPago(String numeroDocumentoPago) {
		this.numeroDocumentoPago = numeroDocumentoPago;
	}

}
