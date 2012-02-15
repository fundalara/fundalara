package controlador.competencia;


import java.util.List;



import modelo.Competencia;
import modelo.EquipoCompetencia;
import modelo.RosterCompetencia;
import modelo.Categoria;

import modelo.Equipo;


import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.api.Comboitem;





import servicio.implementacion.ServicioEquipo;
import servicio.implementacion.ServicioEquipoCompetencia;
import servicio.implementacion.ServicioRosterCompetencia;


public class CntrlFrmCatalogoEquipoForaneo extends GenericForwardComposer {
	//Servicios utilizados
		ServicioEquipo servicioEquipo;
		List<Equipo> equipos;
		//Atributos
		AnnotateDataBinder binder;
		Listbox lsbxEquipos;
		Component catalogo;
		Textbox txtFiltro;
		Equipo equipo;
        int codigo;
        int codCat;
    	Categoria cat;
		  //SETTERS Y GETTERS...
		
		public ServicioEquipo getServicioEquipo() {
			return servicioEquipo;
		}

		public void setServicioEquipo(ServicioEquipo servicioEquipo) {
			this.servicioEquipo = servicioEquipo;
		}

		public List<Equipo> getEquipos() {
			return equipos;
		}

		public void setEquipos(List<Equipo> equipos) {
			this.equipos = equipos;
		}

		public Listbox getLsbxEquipos() {
			return lsbxEquipos;
		}

		public void setLsbxEquipos(Listbox lsbxEquipos) {
			this.lsbxEquipos = lsbxEquipos;
		}

		public Equipo getEquipo() {
			return equipo;
		}

		public void setEquipo(Equipo equipo) {
			this.equipo = equipo;
		}
		
		public void onCreate$frmCatalogoEf(){
			codCat = (Integer) catalogo.getVariable("categoria",false);	
			//System.out.print("Codigo=");
			//System.out.println(codCat);
			equipos = servicioEquipo.buscarEquiposForaneosPorCategoria(codCat);
			
			binder.loadAll();
		}
		
		//METODO PARA TRABAJAR EL CATALOGO EQUIPO
		
		public void doAfterCompose(Component c) throws Exception {
			super.doAfterCompose(c);
			c.setVariable("cntrl", this, true);
			catalogo = c;
			
			//si selecciona por defecto el primero de la lista si hay al menos 1
			if (lsbxEquipos.getItems().size() != 0){
				lsbxEquipos.setSelectedIndex(0);
			}

		}

		

		public void onClick$btnAceptar() throws InterruptedException {
			//Se comprueba que se haya seleccionado un elemento de la lista
			if (lsbxEquipos.getSelectedIndex() != -1) {
				//se obtiene la divisa seleccionada
				Equipo e = equipos.get(lsbxEquipos.getSelectedIndex());
				//se obtiene la referencia del formulario
				Component formulario = (Component) catalogo.getVariable("formulario",false);
	            //se le asigna el objeto divisa al formulario
				formulario.setVariable("equipos", e,false);
				//se le envia una seÃ±al al formulario indicado que el formulario se cerro y que los datos se han enviado
				Events.sendEvent(new Event("onCatalogoCerrado6",formulario));          
				//se cierra el catalogo
				catalogo.detach();
				
			} else {
					Messagebox.show("Seleccione un Equipo", "Olimpo - Información",	Messagebox.YES, Messagebox.INFORMATION);

			}

		}
	   
	   
		public void onCtrlKey$txtFiltro(){
			System.out.println("changing...");
		}
		
		public void onChanging$txtFiltro(){
			
			
			binder.loadAll();
		}
		
		
		public void onClick$btnSalir() {
			catalogo.detach();
		}

}
	
	

