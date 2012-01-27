package controlador.competencia;

import modelo.Competencia;

import org.zkoss.zk.ui.util.GenericForwardComposer;

public class CntrlFrmCompetencia extends GenericForwardComposer {

	Competencia competencias;

	public Competencia getCompetencias() {
		return competencias;
	}

	public void setCompetencias(Competencia competencias) {
		this.competencias = competencias;
	}
}
