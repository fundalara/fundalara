package controlador.jugador;

import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Include;

import comun.Util;


public class TrasladarJugadorCtrl extends GenericForwardComposer {
	private Combobox cmbTipoTraslado;
	private Include incCuerpo;

	public void onChange$cmbTipoTraslado() {
		String src = "";
		String valor = cmbTipoTraslado.getSelectedItem().getValue().toString();
		Util enlace = new Util();
		if (valor.equals("1")) {
			src = "Jugador/Vistas/retirarJugador.zul";
		} else {
			src = "Jugador/Vistas/registrarPase.zul";
		}
		enlace.insertarContenido(incCuerpo, src);
	}

}
