package controlador.general;

import java.util.ArrayList;
import java.util.List;

import org.zkoss.zhtml.Tbody;
import org.zkoss.zk.ui.AbstractComponent;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Tree;
import org.zkoss.zul.Window;

import comun.Util;

public class Datos_Basicos_NC extends GenericForwardComposer {
	Button btnAgregar, btnCancelar, btnSalir, btnModificar, btnQuitar;
	Window wndDatosBasicosNC,form;
	Listbox lbDatos;
	Combobox cmbTipoDato;
	Textbox txtNombre, txtDescripcion;
	Comboitem cmbItem;
	
	int pos = 0;

	public void onClick$btnAgregar() {
	
		Listitem nvoItem = new Listitem();
		nvoItem.appendChild(new Listcell(txtNombre.getValue()));
		nvoItem.appendChild(new Listcell(txtDescripcion.getValue()));
		lbDatos.appendChild(nvoItem);

		txtNombre.setValue("");
		txtDescripcion.setValue("");
		
		if (cmbTipoDato
				.getSelectedItem()
				.getValue()
				.equals("Categoria")) {

			form = (Window)Executions.createComponents("Atletas/Vistas/tipoCategoria.zul", null, null);
			form.doHighlighted();

		} 	
	}

	public void onClick$btnCancelar() {
		txtNombre.setValue("");
		txtDescripcion.setValue("");
		cmbTipoDato.setValue("--Seleccione--");
	}

	public void onClick$btnSalir() {
		wndDatosBasicosNC.detach();
	}

	public void onClick$btnQuitar() {
		int n = lbDatos.getSelectedIndex();
		lbDatos.removeItemAt(n);
	}

	public void onClick$btnModificar() {
		if (lbDatos.getSelectedIndex() >= 0) {
			
			
			

			Listcell lc1 = (Listcell) lbDatos.getSelectedItem().getChildren().get(0);
			Listcell lc2 = (Listcell) lbDatos.getSelectedItem().getChildren().get(1);

			txtNombre.setText(lc1.getLabel());
			txtDescripcion.setText(lc2.getLabel());
			
			lbDatos.removeItemAt(lbDatos.getSelectedIndex());
			
			txtNombre.focus();
		}	
	}
}