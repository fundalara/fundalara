package controlador.entrenamiento;

import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.*;

public class Actividad_Entrenamiento extends GenericForwardComposer {
	Button btnSalir, btnCancelar, btnAgregar, btnEditar, btnQuitar;
	Window winActividadEntrenamiento;
	Textbox txtActividades;
	Listbox lbxActividades;
	Treecell treecell;
	Combobox cmbCategoria, cmbFase;

	public void onClick$btnSalir() {
		winActividadEntrenamiento.detach();
	}

	public void onCreate$winActividadEntrenamiento() {
		inicializar();
		System.out.println("");
	}
	
	public void onSelect$cmbCategoria(){
		cmbFase.setDisabled(false);
		btnCancelar.setDisabled(false);
	}
	
	public void onSelect$cmbFase(){
		txtActividades.setDisabled(false);		
	}

	public void inicializar() {
		// txtActividades.setDisabled(true);
		// lbxActividades.setDisabled(true);
		// btnAgregar.setDisabled(true);
		btnCancelar.setDisabled(true);
		btnEditar.setDisabled(true);
		btnQuitar.setDisabled(true);
		cmbCategoria.setValue("- Categoria -");
		cmbFase.setValue("- Fase -");

	}

	public void onSelect$lbxActividades() {
		if (lbxActividades.getSelectedItem().getIndex() >= 0) {
			btnEditar.setDisabled(false);
			btnQuitar.setDisabled(false);
			btnCancelar.setDisabled(false);
		}
	}

	public void onClick$btnAgregar() {
		if (cmbCategoria.getValue().equals("- Categoria -")) {
			alert("Seleccione una categoria");
			cmbCategoria.focus();
		} else if (cmbFase.getValue().equals("- Fase -")) {
			alert("Seleccione una Fase");
			cmbFase.focus();
		} else if (txtActividades.getValue() == "") {
			alert("Debe ingresar una actividad");
			txtActividades.focus();
		} else if (lbxActividades.getItemCount() == 0) {
			lbxActividades.appendItem(txtActividades.getValue(),
					txtActividades.getValue());
			txtActividades.setText("");			
		} else {
			int I = 0;
			do {
				Listitem item = lbxActividades.getItemAtIndex(I);
				Listcell lc = (Listcell) item.getChildren().get(0);
				if (txtActividades.getValue().equals(lc.getLabel())) {
					alert("No puedes agregar una actividad duplicada");
					return;
				}
				I++;
			} while (I < lbxActividades.getItemCount());
			lbxActividades.appendItem(txtActividades.getValue(),
					txtActividades.getValue());
			txtActividades.setText("");
		}

	}
	
	public void onClick$btnQuitar() {		
		if (lbxActividades.getItems().size() != 0) {
			lbxActividades.removeItemAt(lbxActividades.getSelectedItem().getIndex());			
			if (lbxActividades.getItems().size() == 0){
				btnEditar.setDisabled(true);
				btnQuitar.setDisabled(true);
			}
		}
	}

	public void onClick$btnEditar() {
		if (lbxActividades.getSelectedItem().getIndex() >= 0) {
			txtActividades.setText(lbxActividades.getSelectedItem().getLabel());
			lbxActividades.removeItemAt(lbxActividades.getSelectedItem()
					.getIndex());
		}
	}

	public void onClick$btnCancelar() {
		inicializar();
		int numElementos = lbxActividades.getItems().size();		
		for (int I = 2; I <= numElementos; I++) {
			lbxActividades.removeItemAt(1);
		}
		cmbFase.setDisabled(true);
		txtActividades.setDisabled(true);
	}

}
