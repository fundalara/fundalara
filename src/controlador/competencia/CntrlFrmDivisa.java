package controlador.competencia;

import java.util.Iterator;
import java.util.List;


import modelo.DatoBasico;
import modelo.Divisa;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;
import org.zkoss.zul.Comboitem;
import servicio.implementacion.ServicioDatoBasico;
import servicio.implementacion.ServicioDivisa;

public class CntrlFrmDivisa extends GenericForwardComposer {
	
	//Atributos
	Component formulario; 
	AnnotateDataBinder binder;
	//Servicios utilizados
	ServicioDivisa servicioDivisa;
	ServicioDatoBasico servicioDatoBasico;
	List<DatoBasico> parroquias;
	Divisa divisa;
    Combobox cmbParroquia;	



	//Este metodo se llama al cargar la ventana
	public void doAfterCompose(Component c) throws Exception {
		super.doAfterCompose(c);
		//Se utiliza para hacer referencia a los objetos desde la vista (ej cntrl.algo). Debe ir siempre aqui
		c.setVariable("cntrl", this, true); 
		//se guarda la referencia al formulario actual ej (frmDivisa)
		formulario = c; 	
		restaurar();

	}
	
	public void restaurar(){
		divisa = new Divisa(); 
		parroquias = servicioDatoBasico.listarParroquias();
		cmbParroquia.setText("-- Seleccione --");      
	}
	

	
    //Llama al catalogo
	public void onClick$btnBuscar() {
		//se crea el catalogo y se llama
		Component catalogo = Executions.createComponents("/Competencias/Vistas/FrmCatalogoDivisa.zul", null, null);
		//asigna una referencia del formulario al catalogo.
		catalogo.setVariable("formulario",formulario, false);
	    		
		formulario.addEventListener("onCatalogoCerrado", new EventListener() {		
			@Override
			//Este metodo se llama cuando se envia la señal desde el catalogo
			public void onEvent(Event arg0) throws Exception {
				//se obtiene la divisa
				divisa = (Divisa) formulario.getVariable("divisa",false);
				cmbParroquia.setSelectedIndex(buscar(divisa));
				binder.loadAll();				
			}
		});
	
	
	}

 
	public void onClick$btnGuardar() throws InterruptedException {
        
		divisa.setDatoBasico((DatoBasico) cmbParroquia.getSelectedItem().getValue());
		servicioDivisa.agregar(divisa);
		Messagebox.show("Datos agregados exitosamente", "Mensaje",Messagebox.OK, Messagebox.EXCLAMATION);
		restaurar();
		binder.loadAll();

	}
	
    public int buscar(Divisa d){
    	int j=-1;
    	for (Iterator<DatoBasico> i = parroquias.iterator();i.hasNext();){  		  
    		   DatoBasico db = i.next();
    		   j++;
    		   if (db.getNombre().equals(divisa.getDatoBasico().getNombre())){
    			  
    			  return j; 
    		   }
    	}
    	return j;
    }

	public ServicioDivisa getServicioDivisa() {
		return servicioDivisa;
	}

	public void setServicioDivisa(ServicioDivisa servicioDivisa) {
		this.servicioDivisa = servicioDivisa;
	}

	public void onClick$btnCancelar() {
	    restaurar();
	    binder.loadAll();
	}

	public void onClick$btnSalir() {
		formulario.detach();

	}


	public void onClick$btnEliminar() throws InterruptedException {
		if (Messagebox.show("¿Realmente desea eliminar esta divisa", "Mensaje",
				Messagebox.YES + Messagebox.NO, Messagebox.QUESTION) == Messagebox.YES) {
			servicioDivisa.eliminar(divisa);
            restaurar();
            binder.loadAll();
			Messagebox.show("Datos eliminados exitosamente", "Mensaje",	Messagebox.OK, Messagebox.EXCLAMATION);
		}
	}

	public Divisa getDivisa() {
		return divisa;
	}

	public void setDivisa(Divisa divisa) {
		this.divisa = divisa;
		binder.loadAll();
	}

	public List<DatoBasico> getParroquias() {
		return parroquias;
	}

	public void setParroquias(List<DatoBasico> parroquias) {
		this.parroquias = parroquias;
	}

}
