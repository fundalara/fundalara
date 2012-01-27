package modelo;

// Generated 27/01/2012 03:27:22 PM by Hibernate Tools 3.4.0.CR1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Egreso generated by hbm2java
 */
@Entity
@Table(name = "egreso", schema = "public")
public class Egreso implements java.io.Serializable {

	private int codigoEgreso;
	private String numeroDocumento;
	private Date fechaPago;
	private char estatus;
	private Set<EgresoCuentaPagar> egresoCuentaPagars = new HashSet<EgresoCuentaPagar>(
			0);
	private Set<EgresoFormaPago> egresoFormaPagos = new HashSet<EgresoFormaPago>(
			0);

	public Egreso() {
	}

	public Egreso(int codigoEgreso, Date fechaPago, char estatus) {
		this.codigoEgreso = codigoEgreso;
		this.fechaPago = fechaPago;
		this.estatus = estatus;
	}

	public Egreso(int codigoEgreso, String numeroDocumento, Date fechaPago,
			char estatus, Set<EgresoCuentaPagar> egresoCuentaPagars,
			Set<EgresoFormaPago> egresoFormaPagos) {
		this.codigoEgreso = codigoEgreso;
		this.numeroDocumento = numeroDocumento;
		this.fechaPago = fechaPago;
		this.estatus = estatus;
		this.egresoCuentaPagars = egresoCuentaPagars;
		this.egresoFormaPagos = egresoFormaPagos;
	}

	@Id
	@Column(name = "codigo_egreso", unique = true, nullable = false)
	public int getCodigoEgreso() {
		return this.codigoEgreso;
	}

	public void setCodigoEgreso(int codigoEgreso) {
		this.codigoEgreso = codigoEgreso;
	}

	@Column(name = "numero_documento")
	public String getNumeroDocumento() {
		return this.numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_pago", nullable = false, length = 13)
	public Date getFechaPago() {
		return this.fechaPago;
	}

	public void setFechaPago(Date fechaPago) {
		this.fechaPago = fechaPago;
	}

	@Column(name = "estatus", nullable = false, length = 1)
	public char getEstatus() {
		return this.estatus;
	}

	public void setEstatus(char estatus) {
		this.estatus = estatus;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "egreso")
	public Set<EgresoCuentaPagar> getEgresoCuentaPagars() {
		return this.egresoCuentaPagars;
	}

	public void setEgresoCuentaPagars(Set<EgresoCuentaPagar> egresoCuentaPagars) {
		this.egresoCuentaPagars = egresoCuentaPagars;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "egreso")
	public Set<EgresoFormaPago> getEgresoFormaPagos() {
		return this.egresoFormaPagos;
	}

	public void setEgresoFormaPagos(Set<EgresoFormaPago> egresoFormaPagos) {
		this.egresoFormaPagos = egresoFormaPagos;
	}

}
