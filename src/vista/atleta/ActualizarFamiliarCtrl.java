package vista.atleta;

import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Image;

import vista.comun.FileLoader;

public class ActualizarFamiliarCtrl extends GenericForwardComposer{
	private Image imgFamiliar;
	
	public void onClick$btnFotoFamiliar() {
		new FileLoader().cargarImagen(imgFamiliar);
	}
}
