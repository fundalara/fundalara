package modelo;

// Generated 14/02/2012 08:24:27 PM by Hibernate Tools 3.4.0.CR1

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
 * EgresoCuentaPagar generated by hbm2java
 */
@Entity
@Table(name = "egreso_cuenta_pagar", schema = "public")
public class EgresoCuentaPagar implements java.io.Serializable {

	private EgresoCuentaPagarId id;
	private Egreso egreso;
	private CuentaPagar cuentaPagar;
	private double montoAbonado;
	private char estatus;

	public EgresoCuentaPagar() {
	}

	public EgresoCuentaPagar(EgresoCuentaPagarId id, Egreso egreso,
			CuentaPagar cuentaPagar, double montoAbonado, char estatus) {
		this.id = id;
		this.egreso = egreso;
		this.cuentaPagar = cuentaPagar;
		this.montoAbonado = montoAbonado;
		this.estatus = estatus;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "codigoEgreso", column = @Column(name = "codigo_egreso", nullable = false)),
			@AttributeOverride(name = "codigoCuentaPagar", column = @Column(name = "codigo_cuenta_pagar", nullable = false)) })
	public EgresoCuentaPagarId getId() {
		return this.id;
	}

	public void setId(EgresoCuentaPagarId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codigo_egreso", nullable = false, insertable = false, updatable = false)
	public Egreso getEgreso() {
		return this.egreso;
	}

	public void setEgreso(Egreso egreso) {
		this.egreso = egreso;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codigo_cuenta_pagar", nullable = false, insertable = false, updatable = false)
	public CuentaPagar getCuentaPagar() {
		return this.cuentaPagar;
	}

	public void setCuentaPagar(CuentaPagar cuentaPagar) {
		this.cuentaPagar = cuentaPagar;
	}

	@Column(name = "monto_abonado", nullable = false, precision = 17, scale = 17)
	public double getMontoAbonado() {
		return this.montoAbonado;
	}

	public void setMontoAbonado(double montoAbonado) {
		this.montoAbonado = montoAbonado;
	}

	@Column(name = "estatus", nullable = false, length = 1)
	public char getEstatus() {
		return this.estatus;
	}

	public void setEstatus(char estatus) {
		this.estatus = estatus;
	}

}
