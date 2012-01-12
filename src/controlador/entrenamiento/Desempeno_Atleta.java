package controlador.entrenamiento;

import org.omg.CORBA.PUBLIC_MEMBER;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Timebox;
import org.zkoss.zul.Window;
import java.util.Date;

public class Desempeno_Atleta extends GenericForwardComposer {
	Window wDesempeno_Atleta;
	Button btnSalir, btnAgregar1, btnQuitar1, btnAgregar2, btnQuitar2,
			btnCancelar, btnGuardar, btnImprimir;
	Combobox cmbFase, cmbActividad, cmbMaterial, cmbCantidad;
	Listitem lst2;
	Intbox ibTiempo;
	Listbox lbActividades, lsb;
	Datebox dtbox1, dtbox2;

	public void onClick$btnSalir() {
		wDesempeno_Atleta.detach();
	}

	public void inicializar() {
		cmbFase.setDisabled(true);
		cmbActividad.setDisabled(true);
		ibTiempo.setDisabled(true);
		btnAgregar1.setDisabled(true);
		btnAgregar2.setDisabled(true);
		btnQuitar1.setDisabled(true);
		btnQuitar2.setDisabled(true);
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
		btnAgregar1.setDisabled(true);
	}

	public void onClick$btnCancelar() {

		limpiar1();
		dtbox1.setValue(null);
		dtbox2.setValue(null);

		int cantidad = lsb.getItemCount();
		for (int I = 0; I < cantidad; I++) {
			lsb.removeItemAt(0);
		}
		int contador = lbActividades.getItemCount();
		for (int A = 0; A < contador; A++) {
			lbActividades.removeItemAt(0);
		}
		inicializar();
	}
}
