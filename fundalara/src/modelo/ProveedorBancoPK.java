package modelo;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the proveedor_banco database table.
 * 
 */
@Embeddable
public class ProveedorBancoPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="codigo_banco")
	private String codigoBanco;

	@Column(name="rif_nic")
	private String rifNic;

	@Column(name="numero_cuenta")
	private String numeroCuenta;

    public ProveedorBancoPK() {
    }
	public String getCodigoBanco() {
		return this.codigoBanco;
	}
	public void setCodigoBanco(String codigoBanco) {
		this.codigoBanco = codigoBanco;
	}
	public String getRifNic() {
		return this.rifNic;
	}
	public void setRifNic(String rifNic) {
		this.rifNic = rifNic;
	}
	public String getNumeroCuenta() {
		return this.numeroCuenta;
	}
	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ProveedorBancoPK)) {
			return false;
		}
		ProveedorBancoPK castOther = (ProveedorBancoPK)other;
		return 
			this.codigoBanco.equals(castOther.codigoBanco)
			&& this.rifNic.equals(castOther.rifNic)
			&& this.numeroCuenta.equals(castOther.numeroCuenta);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.codigoBanco.hashCode();
		hash = hash * prime + this.rifNic.hashCode();
		hash = hash * prime + this.numeroCuenta.hashCode();
		
		return hash;
    }
}