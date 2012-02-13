package controlador.entrenamiento;

/**
 * Controlador de la Agenda Auxiliar Para 
 * mostrar los eventos/calendarios y los componentes visuales
 * para la asignacion de Instalaciones para los entrenamientos
 * @version 1.0, 15/02/12
 * @authors Leyner Castillo y Taner Morón
 */

import modelo.ActividadCalendario;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Include;
import org.zkoss.zul.Window;

public class CntrlAuxiliarAgenda extends GenericForwardComposer {
	Combobox cmbActividad;
	Include include;
	Window wndAuxiliarAgenda;
	ActividadCalendario actividadCalendario;
	Character estatus;
	public void doAfterCompose(Component component) throws Exception {
			// TODO Auto-generated method stub
			super.doAfterCompose(component);
			actividadCalendario = new ActividadCalendario();
			actividadCalendario = (ActividadCalendario)execution.getAttribute("actividad");
			
			if (actividadCalendario.getEstatus()=='I'){
				cmbActividad.getItems().clear();
				Comboitem item = new Comboitem();
				item.setLabel("Cumplimiento de Entrenamiento");			
				cmbActividad.appendChild(item);	
				item = new Comboitem();
				item.setLabel("Rendimiento de Jugadores");
				cmbActividad.appendChild(item);	
			}
			else if (actividadCalendario.getEstatus()=='C'){	
				Comboitem item = new Comboitem();
				item.setLabel("Rendimiento de Jugadores");
				cmbActividad.appendChild(item);		
			}		
	}
	
	public void onClose$wndAuxiliarAgenda(){
		wndAuxiliarAgenda.detach();
	}
	
	public void onChange$cmbActividad(){
	}
}