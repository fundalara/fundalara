package vista.entrenamiento;

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
import org.zkoss.zul.Window;

import vista.comun.Util;

public class Escala_Medicion extends GenericForwardComposer {
	Button btnAgregar, btnCancelar, btnSalir, btnModificar, btnQuitar, btnConsultar;
	Window wndEscalaMedicion, form;
	Listbox lbValoresEscala;
	Combobox cmbTipoEscala;
	Textbox txtEscala, txtDescripcionEscala, txtValorEscala, txtDescripcionValor;
	
	int pos = 0;

	public void onClick$btnAgregar() {
	
		Listitem nvoItem = new Listitem();
		nvoItem.appendChild(new Listcell(txtValorEscala.getValue()));
		nvoItem.appendChild(new Listcell(txtDescripcionValor.getValue()));
		lbValoresEscala.appendChild(nvoItem);

		txtValorEscala.setValue("");
		txtDescripcionValor.setValue("");
		 	
	}

	public void onClick$btnConsultar() {
		
		form = (Window)Executions.createComponents("Entrenamiento/Vistas/Catalogo_Escala_Medicion.zul", null, null);
		form.doHighlighted();
		 	
	}
	
	public void onClick$btnCancelar() {
		txtValorEscala.setValue("");
		txtDescripcionValor.setValue("");
		cmbTipoEscala.setValue("--Seleccione--");
		txtEscala.setValue("");
		txtDescripcionEscala.setValue("");
	}

	public void onClick$btnSalir() {
		wndEscalaMedicion.detach();
	}

	public void onClick$btnQuitar() {
		int n = lbValoresEscala.getSelectedIndex();
		lbValoresEscala.removeItemAt(n);
	}

	public void onClick$btnModificar() {
		if (lbValoresEscala.getSelectedIndex() >= 0) {
								
			Listcell lc1 = (Listcell) lbValoresEscala.getSelectedItem().getChildren().get(0);
			Listcell lc2 = (Listcell) lbValoresEscala.getSelectedItem().getChildren().get(1);

			txtValorEscala.setText(lc1.getLabel());
			txtDescripcionValor.setText(lc2.getLabel());
			
			lbValoresEscala.removeItemAt(lbValoresEscala.getSelectedIndex());			
			txtValorEscala.focus();
		}	
	}
}
