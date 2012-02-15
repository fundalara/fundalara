package controlador.jugador;

import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Include;

import comun.Util;


public class AscenderJugadorCtrl extends GenericForwardComposer {
	private Combobox cmbTipoAscenso;
	private Include incCuerpo;

	public void onChange$cmbTipoAscenso() {
		String src = "";
		String valor = cmbTipoAscenso.getSelectedItem().getValue().toString();
		Util enlace = new Util();
		if (valor.equals("1")) {
			src = "Jugador/Vistas/ascensoCategoria.zul";
		} else if (valor.equals("2")) {
			src = "Jugador/Vistas/frmAscensoEspecial.zul";
		} 
		enlace.insertarContenido(incCuerpo, src);
	}

}
