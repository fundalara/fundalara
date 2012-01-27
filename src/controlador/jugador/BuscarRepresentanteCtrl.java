package controlador.jugador;


import java.util.ArrayList;
import java.util.List;

import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
/**
 * Clase controladora de los eventos de la vista de igual nombre.
 * 
 * @author Andres Oropeza
 * 
 * @version 1.0 11/12/2011
 *
 * */
public class BuscarRepresentanteCtrl extends GenericForwardComposer{
	

	private Listbox listListadoRepresentantes;
	private Listcell item2;
	
	
	public void onClick$btnRepresentanteSalir() {
		self.detach();
		
	}
	
	public void onSelect$listListadoRepresentantes() throws InterruptedException{
		
		/*
		 * Esta funcion se encarga de capturar el evento de seleccionar un
		 * elemento del Listbox para que los datos sean devueltos posteriormente
		 * a la ventana de consulta de hospedajes por representante.
		 */
		
		
		List lista = new ArrayList();
		String representante = "";
		List lista2 = new ArrayList();

		Listitem item = listListadoRepresentantes.getSelectedItem();
		lista = item.getChildren();
		
		for (int i = 0; i < lista.size(); i++) {
			Object objeto = lista.get(i);
			item2 = (Listcell) objeto;
			lista2.add(item2.getLabel()); 
		}
		
		
		//********************************************************************** 
		/*
		 * Esta seccion de codigo es para imprimir una ventana que demuestra que 
		 * se estan capturando los datos de la ventana buscarRepresentante.zul al
		 * hacer click en un elemento del Listbox. Es solo para uso de los
		 * desarrolladores, a nivel de defensa se deben comentar estas lineas de
		 * codigo.
		 */
		
			for (int i = 0; i < lista2.size(); i++){
				representante = representante + lista2.get(i).toString();
				representante = representante + " ";
			}
			
			Messagebox.show("DATOS REPRESENTANTE SELECCIONADO: "+representante);
			
		//***********************************************************************	
		
		
		//***********************************************************************	
		/*
		 * En esta seccion de codigo la intencion es de alguna manera devolver
		 * los datos seleccionados en la ventana buscarRepresentante.zul a la
		 * ventana de consultarHospedajeRepresentante.zul, pero despues de
		 * varias pruebas no se consguio dicho objetivo. Se espera contar con
		 * la ayuda de alguien que presente una propuesta para dicha solucion.	
		 */
			
			/*this.txtRepresenteNomb.setValue(lista2.get(1).toString());
			this.txtRepresentApell.setValue(lista2.get(2).toString());
			this.txtRepresentTelf.setValue("No tiene");
			this.txtRepresentCed.setValue(17626665);*/
		//************************************************************************
	}

	
}
