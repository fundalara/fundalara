package controlador.jugador;

import org.zkoss.zul.Combobox;
import org.zkoss.zul.Button;
import org.zkoss.zul.Include;
import org.zkoss.zul.Label;



import comun.Util;

import org.zkoss.zk.ui.util.GenericForwardComposer;

public class ConsultarRepresentantesCtrl extends GenericForwardComposer {

	
	private Combobox tipoI;
	private Combobox tipoA;
	private Label tipoC;
	private Button imprimir;
	private Include cuerpo;
	
/*Habilita o deshabilita los campos de la vista.*/
	
	private void disabledFields(boolean flag) {
		
		tipoI.setDisabled(flag);
		tipoA.setDisabled(flag);
		imprimir.setDisabled(flag);
		

	}

	
	
	private void visiblecmbtipoI(boolean flag){
		
		Util enlace = new Util();
		String src = "";
		
		if (tipoI.getSelectedItem().getValue().equals("1")) {
			imprimir.setDisabled(true);
			tipoA.setVisible(true);
			tipoC.setVisible(true);
			
		} else {
			
			src= "Jugador/Vistas/ConsultarRepresentantesIndividual.zul";
			enlace.insertarContenido(cuerpo, src);
			
		}
	}
	
	private void visiblecmbtipoA(boolean flag){
		Util enlace = new Util();
		String src = "";
		src= "Jugador/Vistas/ConsultarRepresentantesGrupo.zul";
		enlace.insertarContenido(cuerpo, src);
		imprimir.setDisabled(false);
	
	}
	
	public void onChange$tipoI() {
		visiblecmbtipoI(true);
		
	}
	
	public void onChange$tipoA() {
	visiblecmbtipoA(true);
	}
	
	public void enlace(String cad) {
		cuerpo.setSrc(cad);
	}

	
	}
