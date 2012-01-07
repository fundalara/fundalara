package controlador.jugador.bean;

import modelo.DatoBasico;

public class TipoSangre {
	private DatoBasico grupoSanguineo;
	private DatoBasico factorRH;

	public TipoSangre() {
		grupoSanguineo = new DatoBasico();
		factorRH = new DatoBasico();
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
		return grupoSanguineo.getNombre() + "-" + factorRH.getNombre();
	}

}
