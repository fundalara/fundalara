package controlador.entrenamiento;

import java.util.ArrayList;
import java.util.List;

import org.zkoss.zk.ui.AbstractComponent;
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

public class IndicadorEvaluacion extends GenericForwardComposer {
	Combobox cmbEscala, cmbCategoria, cmbActividad;
	Textbox txtIndicador;
	Comboitem nuevo;
	Window wndIndicadorEvaluacion;
	Listbox lbIndicador;
	Tree treeCategoria;
	Button btnAgregar, btnCancelar, btnSalir, btnModificar, btnQuitar;

	public void onCreate$wndIndicadorEvaluacion() {
		onClick$btnCancelar();
	}

	public void inicializar() {
		txtIndicador.setValue("");
		cmbEscala.setValue("-Escala de Medicion-");

	}

	public void onClick$btnAgregar() {
		if (cmbCategoria.getValue().equals("-Categoria-")) {
			alert("Seleccine la Categoria a la que desea asociar el indicador");
			cmbCategoria.focus();

		} else {
			if (cmbActividad.getValue().equals("-Actividad-")) {
				alert("Seleccine la Actividad a la que desea asociar el indicador");
				cmbActividad.focus();
			} else {
				if (txtIndicador.getValue() == "") {
					alert("Debe introducir el nombre del Indicador que desea Registrar");
					txtIndicador.focus();
				} else {
					if (cmbEscala.getValue().equals("-Escala de Medicion-")) {
						alert("Debe selecionar una Escala de Medición");
						cmbEscala.focus();
					} else {

						int i = 0;
						boolean repetido = true;
						while (i < lbIndicador.getItems().size()) {
							Listitem item = lbIndicador.getItemAtIndex(i);
							Listcell lc = (Listcell) item.getChildren().get(0);
							if (txtIndicador.getValue().equals(lc.getLabel())) {
								alert("El Indicador ya se encuentra registrado");
								repetido = false;
							}
							i++;
						}
						if (repetido) {
							Listitem nvoItem = new Listitem();
							nvoItem.appendChild(new Listcell(txtIndicador
									.getValue()));
							nvoItem.appendChild(new Listcell(cmbEscala
									.getValue()));
							nvoItem.appendChild(new Listcell("[" + "4,3,2,1"
									+ "]"));
							lbIndicador.appendChild(nvoItem);
							inicializar();
						}
					}
				}
			}
		}
	}

	public void onClick$btnCancelar() {
		inicializar();
		cmbCategoria.setValue("-Categoria-");
		cmbActividad.setValue("-Actividad-");
		if (lbIndicador.getItems().size() > 0) {
			int numItems = lbIndicador.getItems().size();
			for (int i = 0; i < numItems; i++) {
				lbIndicador.removeItemAt(0);
			}
		}
		cmbActividad.setDisabled(true);
		txtIndicador.setDisabled(true);
		cmbEscala.setDisabled(true);
		
		btnCancelar.setVisible(false);
		btnModificar.setVisible(false);
		btnQuitar.setVisible(false);
	}

	public void onSelect$lbIndicador() {
		if (lbIndicador.getItems().size() > 0) {
			btnModificar.setVisible(true);	
			btnQuitar.setVisible(true);

		}
	}

	public void onChange$cmbCategoria() {
		cmbActividad.setDisabled(false);
		btnCancelar.setVisible(true);
	}

	public void onChange$cmbActividad() {
		cmbEscala.setDisabled(false);
		txtIndicador.setDisabled(false);
	}

	public void onClick$btnSalir() {
		wndIndicadorEvaluacion.detach();
	}

	public void onClick$btnQuitar() {
		int n = lbIndicador.getSelectedIndex();
		lbIndicador.removeItemAt(n);

	}

	public void onClick$btnModificar() {
		if (lbIndicador.getSelectedIndex() >= 0) {

			Listcell lc1 = (Listcell) lbIndicador.getSelectedItem()
					.getChildren().get(0);
			Listcell lc2 = (Listcell) lbIndicador.getSelectedItem()
					.getChildren().get(1);

			txtIndicador.setText(lc1.getLabel());
			cmbEscala.setValue(lc2.getLabel());
			lbIndicador.removeItemAt(lbIndicador.getSelectedIndex());
			txtIndicador.focus();
		}
	}

}
