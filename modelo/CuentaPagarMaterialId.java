package modelo;

// Generated 10/02/2012 11:18:51 PM by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * CuentaPagarMaterialId generated by hbm2java
 */
@Embeddable
public class CuentaPagarMaterialId implements java.io.Serializable {

	private int codigoMaterial;
	private int codigoCuentaPagar;

	public CuentaPagarMaterialId() {
	}

	public CuentaPagarMaterialId(int codigoMaterial, int codigoCuentaPagar) {
		this.codigoMaterial = codigoMaterial;
		this.codigoCuentaPagar = codigoCuentaPagar;
	}

	@Column(name = "codigo_material", nullable = false)
	public int getCodigoMaterial() {
		return this.codigoMaterial;
	}

	public void setCodigoMaterial(int codigoMaterial) {
		this.codigoMaterial = codigoMaterial;
	}

	@Column(name = "codigo_cuenta_pagar", nullable = false)
	public int getCodigoCuentaPagar() {
		return this.codigoCuentaPagar;
	}

	public void setCodigoCuentaPagar(int codigoCuentaPagar) {
		this.codigoCuentaPagar = codigoCuentaPagar;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof CuentaPagarMaterialId))
			return false;
		CuentaPagarMaterialId castOther = (CuentaPagarMaterialId) other;

		return (this.getCodigoMaterial() == castOther.getCodigoMaterial())
				&& (this.getCodigoCuentaPagar() == castOther
						.getCodigoCuentaPagar());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getCodigoMaterial();
		result = 37 * result + this.getCodigoCuentaPagar();
		return result;
	}

}
