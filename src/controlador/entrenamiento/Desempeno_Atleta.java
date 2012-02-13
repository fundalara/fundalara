package controlador.entrenamiento;

import modelo.ActividadEntrenamiento;

import org.omg.CORBA.PUBLIC_MEMBER;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Timebox;
import org.zkoss.zul.Window;

import controlador.entrenamiento.ListenerCheck;
import controlador.entrenamiento.ModeloListBox;
import controlador.entrenamiento.Render;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Desempeno_Atleta extends GenericForwardComposer {
	Window wDesempeno_Atleta;
	Button btnSalir,btnCancelar, btnGuardar, btnImprimir;
	Combobox cmbFase, cmbActividad, cmbMaterial, cmbCantidad;
	Listitem lst2;
	Intbox ibTiempo;
	Listbox lbActividades, lista;
	Datebox dtbox1, dtbox2;
	Checkbox chcGeneral;
	ListenerCheck escuchador;
	
	
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		comp.setVariable("ctrl", this, true);
		escuchador = new ListenerCheck(lista,chcGeneral);
		chcGeneral.addEventListener("onCheck",escuchador);
		List lista1 = new ArrayList();
		Checkbox c = new Checkbox("1");
		c.addEventListener("onCheck", escuchador);
		lista1.add(c);
		lista1.add("Oscar");
		lista1.add("Gutierrez");
		lista1.add(new Combobox());
		lista1.add(new Combobox());
		lista1.add(new Combobox());
		lista1.add(new Checkbox());
		lista1.add(new Checkbox());
		lista1.add(new Textbox());
		
		List lista2 = new ArrayList();
		Checkbox c2 = new Checkbox("6");
		c2.addEventListener("onCheck", escuchador);
		lista2.add(c2);
		lista2.add("Anthony");
		lista2.add("Rodriguez");
		lista2.add(new Combobox());
		lista2.add(new Combobox());
		lista2.add(new Combobox());
		lista2.add(new Checkbox());
		lista2.add(new Checkbox());
		lista2.add(new Textbox());
		
		List lista3 = new ArrayList();
		Checkbox c3 = new Checkbox("45");
		c3.addEventListener("onCheck", escuchador);
		lista3.add(c3);
		lista3.add("Jose Jesus");
		lista3.add("Perez");
		lista3.add(new Combobox());
		lista3.add(new Combobox());
		lista3.add(new Combobox());
		lista3.add(new Checkbox());
		lista3.add(new Checkbox());
		lista3.add(new Textbox());
		
		List lista4 = new ArrayList();
		Checkbox c4 = new Checkbox("7");
		c4.addEventListener("onCheck", escuchador);
		lista4.add(c4);
		lista4.add("Francisco");
		lista4.add("Melendez");
		lista4.add(new Combobox());
		lista4.add(new Combobox());
		lista4.add(new Combobox());
		lista4.add(new Checkbox());
		lista4.add(new Checkbox());
		lista4.add(new Textbox());
		
		
		List lista5 = new ArrayList();
		Checkbox c5 = new Checkbox("15");
		c5.addEventListener("onCheck", escuchador);
		lista5.add(c5);
		lista5.add("Renny");
		lista5.add("Morales");
		lista5.add(new Combobox());
		lista5.add(new Combobox());
		lista5.add(new Combobox());
		lista5.add(new Checkbox());
		lista5.add(new Checkbox());
		lista5.add(new Textbox());
		
		
		List listaAUX = new ArrayList();
		listaAUX.add(lista1);
		listaAUX.add(lista2);
		listaAUX.add(lista3);
		listaAUX.add(lista4);
		listaAUX.add(lista5);
		ModeloListBox modelo = new ModeloListBox(listaAUX);
		lista.setModel(modelo);
		lista.setItemRenderer(new Render());
	
	}
	
	

	public ListenerCheck getEscuchador() {
		return escuchador;
	}



	public void setEscuchador(ListenerCheck escuchador) {
		this.escuchador = escuchador;
	}



	public void onClick$btnSalir() {
		wDesempeno_Atleta.detach();
	}

	public void inicializar() {
		cmbFase.setDisabled(true);
		cmbActividad.setDisabled(true);
		ibTiempo.setDisabled(true);
		cmbMaterial.setDisabled(true);
		cmbCantidad.setDisabled(true);
		btnGuardar.setDisabled(true);
		btnImprimir.setDisabled(true);

	}

	public void limpiar1() {
		cmbFase.setSelectedIndex(-1);
		cmbFase.setValue(cmbFase.getValue().valueOf("--Seleccione--"));
		cmbActividad.setSelectedIndex(-1);
		cmbActividad.setValue(cmbFase.getValue().valueOf("--Seleccione--"));
		ibTiempo.setValue(0);
	}

	public void onClick$btnCancelar() {

		limpiar1();
		dtbox1.setValue(null);
		dtbox2.setValue(null);

		
		int contador = lbActividades.getItemCount();
		for (int A = 0; A < contador; A++) {
			lbActividades.removeItemAt(0);
		}
		inicializar();
	}
}
