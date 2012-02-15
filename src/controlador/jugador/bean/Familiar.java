package controlador.jugador.bean;

import java.util.ArrayList;
import java.util.List;

import comun.EstatusRegistro;

import modelo.DatoBasico;

/**
 * Clase bean para representar al Familiar
 * @author Robert A
 * @author German L
 * @version 0.1 22/12/2011
 *
 */
public class Familiar  extends Persona{

	private DatoBasico parentesco;
	private DatoBasico profesion;
	private List<DatoBasico> comisionesFamiliar;
	private boolean representante;
	private char estatus;
		
	public Familiar() {
		 super();
		 parentesco = new DatoBasico();
		 profesion =  new DatoBasico();
		 comisionesFamiliar = new ArrayList<DatoBasico>();
		 estatus= EstatusRegistro.TEMPORAL;
	}

	
	
	public char getEstatus() {
		return estatus;
	}

	public void setEstatus(char estatus) {
		this.estatus = estatus;
	}



	public DatoBasico getParentesco() {
		return parentesco;
	}

	public void setParentesco(DatoBasico parentesco) {
		this.parentesco = parentesco;
	}

	public DatoBasico getProfesion() {
		return profesion;
	}

	public void setProfesion(DatoBasico profesion) {
		this.profesion = profesion;
	}

	public List<DatoBasico> getComisionesFamiliar() {
		return comisionesFamiliar;
	}

	public void setComisionesFamiliar(List<DatoBasico> comisionesFamiliar) {
		this.comisionesFamiliar = comisionesFamiliar;
	}
	
	public boolean buscarComision(DatoBasico comision){
		return comisionesFamiliar.contains(comision);
	}
	
	public boolean agregarComision(DatoBasico comision){
		return comisionesFamiliar.add(comision);
	}
	public boolean quitarComision(DatoBasico comision){
		return comisionesFamiliar.remove(comision);
	}

	public boolean isRepresentante() {
		return representante;
	}

	public void setRepresentante(boolean representante) {
		this.representante = representante;
	}

	public String getCedulaCompleta() {
		return getNacionalidad().toUpperCase() + "-" + getCedula();
	}
	
	
	
}
