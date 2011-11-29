package vista.atleta;

import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Include;

import vista.comun.Util;

public class InscribirJugadorCtrl extends GenericForwardComposer {
	private Combobox cmbTipoInscrip;
	private Include incCuerpo;

	public void onChange$cmbTipoInscrip() {
		String src = "";
		String valor = cmbTipoInscrip.getSelectedItem().getValue().toString();
		Util enlace = new Util();
		if (valor.equals("1")) {
			src = "Atleta/Vistas/registrarJugador.zul";
		} else if (valor.equals("2")) {
			src = "Atleta/Vistas/reingresarJugador.zul";
		} else {
			src = "Atleta/Vistas/registrarPlanVacacional.zul";
		}
		enlace.insertarContenido(incCuerpo, src);
	}

}
