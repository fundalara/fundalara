package controlador.jugador;

import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import comun.Util;

public class ConsultarRepresentantesGrupoCtrl extends GenericForwardComposer {
	
	private Button btnCatalogoJugadores;

	public void onClick$btnCatalogoJugadores() {
		new Util().crearVentana("Jugador/Vistas/consultarRepresentantesDetalle.zul", null, null);
	
	}
	
}
