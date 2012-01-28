package controlador.entrenamiento;

import org.zkoss.zk.ui.util.GenericForwardComposer;

import org.zkoss.zul.Button;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

public class CntrlAsignarInstalaciones extends GenericForwardComposer{

	Window wndAsignarInstalaciones;	
	Button btnGuardar, btnCancelar, btnSalir, btnImprimir;
	
	
	public void onClick$btnGuardar() throws InterruptedException{
		Messagebox.show("Guardado Exitosamente","Olimpo - Informacion", Messagebox.OK,Messagebox.INFORMATION);
		onClick$btnSalir();
	}
	
	public void onClick$btnSalir(){
		wndAsignarInstalaciones.detach();
	}
	
}