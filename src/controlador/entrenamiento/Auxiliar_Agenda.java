package controlador.entrenamiento;

import java.util.Date;

import modelo.Sesion;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Include;
import org.zkoss.zul.Window;

public class Auxiliar_Agenda extends GenericForwardComposer {
	Combobox actividad;
	Include include;
	Window wind;
	Sesion sesion;
	Date fechaInicio, fechaFin;
	public void doAfterCompose(Component component) throws Exception {
			// TODO Auto-generated method stub
			super.doAfterCompose(component);
			sesion = new Sesion();
			sesion = (Sesion)execution.getAttribute("sesion");
			fechaInicio = (Date) execution.getAttribute("fechaInicio");
			fechaFin = (Date) execution.getAttribute("fechaFin");
	}
	
	
	public void onClose$wind(){
		wind.detach();
	}

	public void onChange$actividad(){
		
		if (actividad.getSelectedItem().getLabel().compareTo("Rendimiento de jugadores") == 0){
			Window win = new Window();
			win = (Window)execution.createComponents("Entrenamiento/Vistas/Desempeno_Atleta.zul", null, null);
			win.doHighlighted();
			win.setPosition("center");
		}
		else{
			execution.setAttribute("sesion",sesion);
			execution.setAttribute("fechaInicio", fechaInicio);
			execution.setAttribute("fechaFin", fechaFin);
			Window win = new Window();
			win = (Window)execution.createComponents("Entrenamiento/Vistas/frmCumplimientoEntrenamiento.zul", null, null);
			win.doHighlighted();
			win.setPosition("center");
		}
	}
}