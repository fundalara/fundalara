package controlador.entrenamiento;

import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Include;
import org.zkoss.zul.Window;

public class Auxiliar_Agenda extends GenericForwardComposer {
	Combobox actividad;
	Include include;
	Window wind;
	
	public void onClose$wind(){
		wind.detach();
	}

	public void onSelect$actividad(){
		if (actividad.getSelectedItem().getLabel().compareTo("Desempeño atletas") == 0){
			wind = (Window)execution.createComponents("Entrenamiento/Vistas/Desempeno_Atleta.zul", null, null);
			wind.doHighlighted();
			wind.setPosition("center");
		}
		else{
			wind = (Window)execution.createComponents("Entrenamiento/Vistas/Cumplimiento_Entrenamiento.zul", null, null);
			wind.doHighlighted();
			wind.setPosition("center");
		}
	}
}