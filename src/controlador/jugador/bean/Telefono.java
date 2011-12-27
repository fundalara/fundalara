package controlador.jugador.bean;

import modelo.DatoBasico;

public class Telefono {
	private DatoBasico codigo;
	private String numero;
	
	public Telefono() {
		codigo= new DatoBasico();
		// TODO Auto-generated constructor stub
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
	
	public String getTelefonoCompleto(){
		return codigo.getNombre()+"-"+numero;
	}
	

}
