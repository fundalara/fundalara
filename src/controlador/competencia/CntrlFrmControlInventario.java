package controlador.competencia;

import java.util.List;

import modelo.Divisa;

import modelo.Material;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

import servicio.competencia.ServicioControlInventario;
import servicio.competencia.ServicioDivisa;


public class CntrlFrmControlInventario extends GenericForwardComposer {
	
	ServicioControlInventario servicioControlInventario;
	AnnotateDataBinder binder;
	List<Material> CI;
	Component comp;
	Listbox lsbxCI;
	Material control;
	
	public void doAfterCompose(Component c) throws Exception {
		super.doAfterCompose(c);
		c.setVariable("cntrl", this, true);
		comp = c;
		CI = servicioControlInventario.listarActivos();
		if (lsbxCI.getItems().size() != 0){
			lsbxCI.setSelectedIndex(0);
		}

	}

	public void onClick$btnAceptar() {
		if (lsbxCI.getSelectedIndex() != -1) {
			Material cin = CI.get(lsbxCI.getSelectedIndex());
			CntrlFrmControlInventario c = (CntrlFrmControlInventario) comp.getVariable("ref", false);
			//c.setControlInventario(cin);
			comp.detach();

		} else {
			try {
				Messagebox.show("Seleccione un Recurso", "Mensaje",
						Messagebox.YES, Messagebox.INFORMATION);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	private void setControlInventario(List<Material> CI) {
		// TODO Auto-generated method stub
		this.CI = CI;
	}

	public List<Material> getControlInventario() {
		return CI;
	}

	
	public void onClick$btnBuscar() {
	    comp = Executions.createComponents("/Competencias/Vistas/FrmControlInventario.zul",null,null);
	    comp.setVariable("ref",this,false);
	}

	/*public void onClick$btnGuardar() throws InterruptedException{
		control.setEstatus("A");
		servicioControlInventario.agregar(control);
		control = new Material();
		binder.loadAll();
		Messagebox.show("Datos agregados exitosamente","Mensaje",Messagebox.OK,Messagebox.EXCLAMATION);
		
	}*/
	
	
	
	public void onClick$btnCancelar(){
		control = new Material();
		binder.loadAll();
	}
	
	
	public void onClick$btnSalir(){
		comp.detach();
		
	}
	
	public void onClick$btnEliminar() throws InterruptedException{
		if(Messagebox.show("¿Realmente desea eliminar el Material","Mensaje",Messagebox.YES+Messagebox.NO,Messagebox.QUESTION) == Messagebox.YES){
					
			servicioControlInventario.eliminar(control);
			control = new Material();
			binder.loadAll();
			Messagebox.show("Datos eliminados exitosamente","Mensaje",Messagebox.OK,Messagebox.EXCLAMATION);
		}
	}
}
