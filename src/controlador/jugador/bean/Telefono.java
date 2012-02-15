package controlador.jugador.bean;

import modelo.DatoBasico;

/**
 * Clase bean para representar  el teléfono
 * 
 * @author Robert A
 * @author German L
 * @version 0.1 22/12/2011
 * 
 */
public class Telefono {
	private DatoBasico codigo;
	private String numero;

	public Telefono() {
		codigo = new DatoBasico();
	}

	public DatoBasico getCodigo() {
		return codigo;
	}

	public void setCodigo(DatoBasico codigo) {
		this.codigo = codigo;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getTelefonoCompleto() {
		if (codigo.getNombre() == null || numero == null) {
			return null;
		} else {
			return codigo.getNombre() + "-" + numero;
		}
	}

}
