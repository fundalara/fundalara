package controlador.entrenamiento;

import java.awt.Container;

import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

public class Asignar_Instalacion extends GenericForwardComposer {
	Combobox cmbinstalacion, cmbequipo;
	Textbox txtmanager;
	Button btnagregar, btneditar, btnquitar, btnguardar, btnimprimir,
			btncancelar, btnsalir;
	Window win;

	public void onChange$cmbinstalacion() {
		cmbequipo.setDisabled(false);

	}

	public void onChange$cmbequipo() {
		txtmanager.setDisabled(false);
		txtmanager.setText("Pedro Jose Grifol");
		btnagregar.setDisabled(false);
	}

	public void onClick$btnagregar() {
		btneditar.setDisabled(false);
		btnquitar.setDisabled(false);
		btnguardar.setDisabled(false);
	}

	public void onClick$btnquitar(int row) {
		Container data = null;
		data.remove(row);
		this.fireTableRowsDeleted(row, row);
	}

	private void fireTableRowsDeleted(int row, int row2) {
		// TODO Auto-generated method stub

	}

	public void onClick$btnguardar() {
		btnimprimir.setDisabled(false);
	}

	public void onClick$btncancelar() {
		cmbinstalacion.setText("--Seleccione--");
		cmbequipo.setText("--Seleccione--");
		txtmanager.setText("");
		btnagregar.setDisabled(true);
		btneditar.setDisabled(true);
		btnquitar.setDisabled(true);
		btnguardar.setDisabled(true);
		btnimprimir.setDisabled(true);
	}

	public void onClick$btnsalir() {
		win.detach();
	}

}
