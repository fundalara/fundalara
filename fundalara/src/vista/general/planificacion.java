package vista.general;

import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Window;
import org.zkoss.zk.ui.Executions;

public class planificacion extends GenericForwardComposer {
	Window form;
	String pantalla;
	
	public void onClick$btnAceptar(){
		pantalla = "Entrenamiento/Vistas/Plan_Temporada.zul";
		//onClose$wndPlanificacion();
		form = (Window)Executions.createComponents(pantalla, null, null);
		form.doHighlighted();
	}
}
