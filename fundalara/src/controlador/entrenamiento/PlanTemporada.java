package controlador.entrenamiento;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Window;

public class PlanTemporada extends GenericForwardComposer {
	
	Window pTemporada;
	
	Button btnAgregarHorario,btnEditarHorario,btnQuitarHorario,btnAgregarPersTecnico,
	btnEditarPersTecnico,btnQuitarPersTecnico,btnGuardar,btnImprimir,btnCancelar,btnSalir;
	
	Combobox cmbTemporada,cmbEquipo,cmbTipoPersonal,cmbNombre;
	
	Hbox hbAsigHorario;
	
	Listbox listHorario,list;
	
	Window planTemp;
	
	public void onClick$btnAgregarHorario(){
		pTemporada.setVisible(false);
		planTemp = (Window)Executions.createComponents("/fundalara/WebContent/General/agenda.zul", null, null);
		planTemp.doHighlighted();
	}

	
	
	public void onClick$btnAgregarPersTecnico(){
		int equipo = cmbEquipo.getSelectedIndex();
		int tipo = cmbTipoPersonal.getSelectedIndex();
		int nombre = cmbNombre.getSelectedIndex();
		
		if (equipo==-1)
			alert("Debe seleccionar un Equipo");
		else
			if (tipo==-1)
				alert("Debe seleccionar un Tipo de Personal");
			else
				if (nombre==-1)
					alert("Debe seleccionar el Nombre");
				else{
					Listitem item = new Listitem();
			        item.appendChild(new Listcell(cmbEquipo.getSelectedItem().getLabel()));
			        item.appendChild(new Listcell(cmbTipoPersonal.getSelectedItem().getLabel()));
			        item.appendChild(new Listcell(cmbNombre.getSelectedItem().getLabel()));
			        list.appendChild(item);	
				}
	}
	
	public void onClick$btnQuitarPersTecnico(){
		list.removeItemAt(list.getSelectedIndex());
	}
		
	public void onClick$btnSalir(){
		pTemporada.detach();
	}
	
	public void onClick$btnCancelar(){
		cmbTemporada.setValue("--Seleccione--");
		cmbEquipo.setValue("--Seleccione--");
		cmbTipoPersonal.setValue("--Seleccione--");
		cmbNombre.setValue("--Seleccione--");
	    removerTodoLista(list);
	    removerTodoLista(listHorario);
	}
	
	public void removerTodoLista(Listbox newlista) {
		int cant;
		cant=newlista.getItemCount();
		
		for (int i = cant-1; i>=0 ; i--)
			newlista.removeItemAt(i);		
	}
}
