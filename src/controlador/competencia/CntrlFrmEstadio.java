package controlador.competencia;

import java.util.Iterator;
import java.util.List;

import modelo.DatoBasico;
import modelo.Divisa;
import modelo.Estadio;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;

import com.lowagie.text.ListItem;

import servicio.implementacion.ServicioDatoBasico;
import servicio.implementacion.ServicioEstadio;

/**
 * Controlador para el archivo 'FrmEstadio.zul'
 * 
 * @author Alix Villanueva
 * @version 1.0
 */

public class CntrlFrmEstadio extends GenericForwardComposer{
		
		//Servicios utilizados
		ServicioEstadio servicioEstadio;
		ServicioDatoBasico servicioDatoBasico;
		//Atributos
		Component formulario; 
		AnnotateDataBinder binder;
		List<DatoBasico> estados;
		List<DatoBasico> municipios;
		List<DatoBasico> parroquias;
		Estadio estadio;
	    Combobox cmbEstados; //Para mostrar los estados
	    Combobox cmbMunicipios;
	    Combobox cmbParroquias;
	    
	    
	    
		//Este metodo se llama al cargar la ventana
		public void doAfterCompose(Component c) throws Exception {
			super.doAfterCompose(c);
			//Se utiliza para hacer referencia a los objetos desde la vista (ej cntrl.algo). Debe ir siempre aqui
			c.setVariable("cntrl", this, true); 
			//se guarda la referencia al formulario actual
			formulario = c; 	
			restaurar();

		}
		
		public void restaurar(){
			estadio = new Estadio(); 
			estados = servicioDatoBasico.listarEstados();			
			cmbEstados.setText("-- Seleccione --");
			cmbMunicipios.setText("-- Seleccione --");
			cmbParroquias.setText("-- Seleccione --");
			
		}
		
		

		//Llama al catalogo
		public void onClick$btnBuscar() {
			//se crea el catalogo y se llama
			Component catalogo = Executions.createComponents("/Competencias/Vistas/FrmCatalogoEstadios.zul", null, null);
			//asigna una referencia del formulario al catalogo.
			catalogo.setVariable("formulario",formulario, false);
		    formulario.addEventListener("onCatalogoCerrado", new EventListener() {		
			
		    	
		    	@Override
				//Este metodo se llama cuando se envia la señal desde el catalogo
				public void onEvent(Event arg0) throws Exception {
					//se obtiene el estadio
					estadio = (Estadio) formulario.getVariable("estadio",false);
					cmbEstados.setSelectedIndex(buscar(estadio.getDatoBasico().getDatoBasico().getDatoBasico(), estados));
					
					municipios = servicioDatoBasico.listarMunicipiosPorEstados(estadio.getDatoBasico().getDatoBasico().getDatoBasico());
					llenar(municipios,cmbMunicipios);
					cmbMunicipios.setSelectedIndex(buscar(estadio.getDatoBasico().getDatoBasico(),municipios));
					parroquias = servicioDatoBasico.listarParroquiasPorMunicipios(estadio.getDatoBasico().getDatoBasico());
					llenar(parroquias,cmbParroquias);
					cmbParroquias.setSelectedIndex(buscar(estadio.getDatoBasico(),parroquias));					
					binder.loadAll();
					
				}
		    	
			});

		}
		
		
		public void onChange$cmbEstados(){
			cmbMunicipios.setText("-- Seleccione --");
			cmbParroquias.setText("-- Seleccione --");
			municipios = servicioDatoBasico.listarMunicipiosPorEstados((DatoBasico) cmbEstados.getSelectedItem().getValue());
			binder.loadAll();
		}
		
		public void onChange$cmbMunicipios(){
			cmbParroquias.setText("-- Seleccione --");
			parroquias = servicioDatoBasico.listarParroquiasPorMunicipios((DatoBasico) cmbMunicipios.getSelectedItem().getValue());
			binder.loadAll();
			
		}
		
		
		
		public void onClick$btnGuardar() throws InterruptedException {
	        
			if(!cmbParroquias.getText().equalsIgnoreCase("-- Seleccione --")){
				if(!cmbMunicipios.getText().equalsIgnoreCase("-- Seleccione --")){
					if(!cmbEstados.getText().equalsIgnoreCase("-- Seleccione --")){
						estadio.setDatoBasico((DatoBasico) cmbParroquias.getSelectedItem().getValue());
						servicioEstadio.agregar(estadio);
						Messagebox.show("Datos agregados exitosamente", "Mensaje",Messagebox.OK, Messagebox.EXCLAMATION);
						restaurar();
						binder.loadAll();
					}else Messagebox.show("Debe Seleccionar Estado", "Mensaje",Messagebox.OK, Messagebox.EXCLAMATION);
					
				}else Messagebox.show("Debe Seleccionar Municipio", "Mensaje",Messagebox.OK, Messagebox.EXCLAMATION);
			}else Messagebox.show("Debe Seleccionar Parroquia", "Mensaje",Messagebox.OK, Messagebox.EXCLAMATION);					
		}
		
		
		public void llenar(List<DatoBasico> lista, Combobox combo){
			for (Iterator i=lista.iterator(); i.hasNext();){
				DatoBasico db= (DatoBasico) i.next();
				Comboitem ci = new Comboitem(db.getNombre());
				ci.setValue(db);
				combo.appendChild(ci);
			}
		}
		
		  public int buscar(DatoBasico d,List l){
		    	int j=-1;
		    	for (Iterator<DatoBasico> i = l.iterator();i.hasNext();){  		  
		    		   DatoBasico db = i.next();
		    		   j++;
		    		   if (db.getNombre().equals(d.getNombre())){
		    			  
		    			  return j; 
		    		   }
		    	}
		    	return j;
		    }
		
		public void onClick$btnCancelar() {
			restaurar(); 
		    binder.loadAll();
		}

		public void onClick$btnSalir() {
			formulario.detach();

		}
		
		public void onClick$btnEliminar() throws InterruptedException {
			if (Messagebox.show("¿Realmente desea eliminar este estadio", "Mensaje",
					Messagebox.YES + Messagebox.NO, Messagebox.QUESTION) == Messagebox.YES) {
				servicioEstadio.eliminar(estadio);
	            restaurar();
	            binder.loadAll();
				Messagebox.show("Datos eliminados exitosamente", "Mensaje",	Messagebox.OK, Messagebox.EXCLAMATION);
			}
		}
		
		
		
	 	public List<DatoBasico> getMunicipios() {
			return municipios;
		}

		public void setMunicipios(List<DatoBasico> municipios) {
			this.municipios = municipios;
		}

		public List<DatoBasico> getParroquias() {
			return parroquias;
		}

		public void setParroquias(List<DatoBasico> parroquias) {
			this.parroquias = parroquias;
		}	

		public ServicioEstadio getServicioEstadio() {
			return servicioEstadio;
		}

		public void setServicioEstadio(ServicioEstadio servicioEstadio) {
			this.servicioEstadio = servicioEstadio;
		}

		public Estadio getEstadio() {
			return estadio;
		}

		public void setEstadio(Estadio estadio) {
			this.estadio = estadio;
		}

		public List<DatoBasico> getEstados() {
			return estados;
		}

		public void setEstados(List<DatoBasico> estados) {
			this.estados = estados;
		}
		
		
		
		
		
		

}
