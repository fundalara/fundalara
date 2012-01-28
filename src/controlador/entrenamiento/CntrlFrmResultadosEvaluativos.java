package controlador.entrenamiento;

import java.util.ArrayList;
import java.util.List;

import modelo.ActividadEntrenamiento;
import modelo.Categoria;
import modelo.Equipo;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Auxheader;
import org.zkoss.zul.Button;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listhead;
import org.zkoss.zul.Listheader;
import org.zkoss.zul.Window;

import controlador.entrenamiento.ModeloListBox;
import controlador.entrenamiento.Render;

import servicio.implementacion.ServicioCategoria;
import servicio.implementacion.ServicioActividadEntrenamiento;
import servicio.implementacion.ServicioDatoBasico;
import servicio.implementacion.ServicioEquipo;

public class CntrlFrmResultadosEvaluativos extends GenericForwardComposer {
	Window winResEvaluativos;
	Combobox cmbEficiencia,cmbCategoria,cmbEquipo;
	ServicioActividadEntrenamiento servicioActividadEntrenamiento;
	ServicioCategoria servicioCategoria;
	ServicioEquipo servicioEquipo;
	List<ActividadEntrenamiento> listActividad;
	List<Categoria> listCategoria;
	List<Equipo> listEquipo;
	Auxheader ahEficiencia;
	Button btnCancelar, btnSalir;
	Listbox lista;
	Listhead lhCabecera;
	Checkbox chcGeneral;
	AnnotateDataBinder binder;

	
	

	public List<Equipo> getListEquipo() {
		return listEquipo;
	}

	public void setListEquipo(List<Equipo> listEquipo) {
		this.listEquipo = listEquipo;
	}

	public List<ActividadEntrenamiento> getListActividad() {
		return listActividad;
	}

	public void setListActividad(List<ActividadEntrenamiento> listActividad) {
		this.listActividad = listActividad;
	}

	public List<Categoria> getListCategoria() {
		return listCategoria;
	}

	public void setListCategoria(List<Categoria> listCategoria) {
		this.listCategoria = listCategoria;
	}

	public List<ActividadEntrenamiento> listarEficiencia() {
		listActividad = servicioActividadEntrenamiento.listar();
		return listActividad;
	}

	public List<Categoria> listarCategoria() {
		listCategoria = servicioCategoria.listar();
		return listCategoria;
	}

	public void doAfterCompose(Component comp) throws Exception {
		
		super.doAfterCompose(comp);
		
		cmbEquipo.setDisabled(true);
		cmbEficiencia.setDisabled(true);
		comp.setVariable("ctrl", this, true);
		listActividad = servicioActividadEntrenamiento.listar();
		listCategoria = servicioCategoria.listar();
		List fila = new ArrayList();
		fila.add(new Checkbox());
		fila.add("1");
		fila.add("Lorena Andres");
		fila.add(new Combobox());
		fila.add(new Combobox());
		fila.add(new Combobox());
		List matriz = new ArrayList();
		matriz.add(fila);
		lista.setModel(new ModeloListBox(matriz));
		lista.setItemRenderer(new Render());
		
	}

	public void onChange$cmbCategoria(){
		cmbEquipo.setDisabled(false);
		listEquipo = new ArrayList<Equipo>();
		listEquipo = servicioEquipo.buscarporCategoria((Categoria)cmbCategoria.getSelectedItem().getValue());
		binder.loadAll();
	}
	
	public void onChange$cmbEquipo() {
		cmbEficiencia.setDisabled(false);
		listActividad = new ArrayList<ActividadEntrenamiento>();
		listActividad = servicioActividadEntrenamiento.buscarPorCategoria((Categoria)cmbCategoria.getSelectedItem().getValue());
	    binder.loadAll();
	}
	
	public Categoria buscarCategoria() {
		Categoria c;
		for (Object o : servicioCategoria.listar()) {
			c=(Categoria)o;
			if(String.valueOf(c.getCodigoCategoria()).equals(cmbCategoria.getSelectedItem().getValue()))
				return c;
		}
		return null;
	}
	
	
	public void onChange$cmbEficiencia() {
		ahEficiencia.setLabel(cmbEficiencia.getSelectedItem().getLabel());
	}


	

	public void onChange$cmbEscala3() {
		int index = 4;
		for (int i = 0; i < lista.getItemCount(); i++) {
			Listcell cell = (Listcell) lista.getItemAtIndex(i).getChildren()
					.get(4);
			Combobox combo = (Combobox) cell.getChildren().get(0);
			//combo.setValue(cmbEscala3.getSelectedItem().getLabel());
		}

	}

	public void onClick$btnCancelar() {
		cmbCategoria.setValue("--Seleccione--");
		cmbEquipo.setValue("--");
		cmbEficiencia.setValue("--Seleccione--");
		ahEficiencia.setLabel("");

		for (int n = 2; n < 5; n++) {
			Listheader header = (Listheader) lhCabecera.getChildren();
			Combobox comboh = (Combobox) header.getChildren().get(0);
			comboh.setValue("4");
			for (int i = 0; i < lista.getItemCount(); i++) {
				Listcell cell = (Listcell) lista.getItemAtIndex(i)
						.getChildren().get(n);
				Combobox combo = (Combobox) cell.getChildren().get(0);
				combo.setValue("4");
			}
		}

	}

	public void onClick$btnSalir() {
		winResEvaluativos.detach();
	}
	
	
}
