package controlador.jugador.bean;

import modelo.DatoBasico;

/**
 * Clase bean para representar el tipo de sangre
 * 
 * @author Robert A
 * @author German L
 * @version 0.1 22/12/2011
 * 
 */
public class TipoSangre {
	private DatoBasico grupoSanguineo;
	private DatoBasico factorRH;

	public TipoSangre() {
		grupoSanguineo = new DatoBasico();
		factorRH = new DatoBasico();
	}
	
	public TipoSangre(DatoBasico grupoSanguineo, DatoBasico factorRH){
		this();
		this.grupoSanguineo = grupoSanguineo;
		this.factorRH = factorRH;
		
	}

	public DatoBasico getGrupoSanguineo() {
		return grupoSanguineo;
	}

	public void setGrupoSanguineo(DatoBasico grupoSanguineo) {
		this.grupoSanguineo = grupoSanguineo;
	}

	public DatoBasico getFactorRH() {
		return factorRH;
	}

	public void setFactorRH(DatoBasico factorRH) {
		this.factorRH = factorRH;
	}

	public String getTipoSangre() {
		if (grupoSanguineo.getNombre() == null || factorRH.getNombre() == null) {
			return null;
		} else {
			return grupoSanguineo.getNombre() + "-" + factorRH.getNombre();
		}
	}

}
