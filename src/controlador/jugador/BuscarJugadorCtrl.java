package controlador.jugador;

import java.util.ArrayList;
import java.util.List;
import org.zkoss.zk.ui.event.ForwardEvent;
import modelo.Categoria;
import modelo.ClasificacionEquipo;
import modelo.Equipo;


import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.*;

import servicio.implementacion.ServicioCategoria;
import servicio.implementacion.ServicioClasificacionEquipo;
import servicio.implementacion.ServicioEquipo;

public class BuscarJugadorCtrl extends GenericForwardComposer {
	ServicioCategoria servicioCategoria;
	ServicioEquipo servicioEquipo;
	ServicioClasificacionEquipo servicioClasificacionEquipo;
	private Equipo equipo = new Equipo();
	private Categoria categoria = new Categoria();
	Listbox listEquipo;
	List<Equipo> listaEquipos;
	private ClasificacionEquipo clasificacionEquipo = new ClasificacionEquipo(); 
	
	private AnnotateDataBinder binder ;
	
	Combobox cmbEquipo, cmbCategoria;
	 
	 
	public void onCreate$winConfigurarEquipo(ForwardEvent event) {
		    // get the databinder for later extra load and save
		    // note: binder is not ready in doAfterCompose, so do it here
		    binder = (AnnotateDataBinder) event.getTarget().getVariable("binder", false);
		  }

	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		comp.setVariable("controller", this, false);  //Hacemos visible el modelo para el databinder
		cmbEquipo.setDisabled(true);
	}

	public void onSelect$cmbCategoria() {
		cmbEquipo.setDisabled(false);
		cmbEquipo.getItems().clear();
		cmbEquipo.setValue("--Seleccione--");

		listaEquipos = servicioEquipo.listar();
		for (int i = 0; i < listaEquipos.size(); i++) {
			if (listaEquipos.get(i).getCategoria().getCodigoCategoria()
					.toString()
					.equals(cmbCategoria.getSelectedItem().getValue().toString())) {
				Comboitem item = new Comboitem();
				item.setLabel(listaEquipos.get(i).getClasificacionEquipo().getNombre().toString());
				item.setValue(listaEquipos.get(i).getCodigoEquipo().toString());
				
				cmbEquipo.appendChild(item);
			} else {
			}
		}   
	}

	public ClasificacionEquipo getClasificacionEquipo() {
		return clasificacionEquipo;
	}

	public void setClasificacionEquipo(ClasificacionEquipo clasificacionEquipo) {
		this.clasificacionEquipo = clasificacionEquipo;
	}
	
	public Equipo getEquipo() {
		return equipo;
	}


	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}


	public Categoria getCategoria() {
		return categoria;
	}


	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}


	public Listbox getListEquipo() {
		return listEquipo;
	}


	public void setListEquipo(Listbox listEquipo) {
		this.listEquipo = listEquipo;
	}


	public List<Equipo> getListaEquipos() {
		return listaEquipos;
	}


	public void setListaEquipos(List<Equipo> listaEquipos) {
		this.listaEquipos = listaEquipos;
	}
	
	public  List<Categoria> getCategorias() {
		return servicioCategoria.listar();
		
	}
	
	public  List<Equipo> getEquipos() {
		return servicioEquipo.listar();
		
	}
	
}


