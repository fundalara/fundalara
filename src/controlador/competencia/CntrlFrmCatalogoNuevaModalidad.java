package controlador.competencia;

import java.util.List;

import modelo.ModalidadCompetencia;
import modelo.TipoCompetencia;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import servicio.implementacion.ServicioModalidadCompetencia;

public class CntrlFrmCatalogoNuevaModalidad extends GenericForwardComposer {

	AnnotateDataBinder binder;
	ServicioModalidadCompetencia servicioModalidadCompetencia;
	List<ModalidadCompetencia> modalidades;
	Listbox lsbxModalidad;
	Component catalogo;
	Textbox txtFiltro;
	Combobox cmbTipoCompetencia;
	
	public void onCreate$frmCatalogoModalidadCompetencia(){
		//OBTIENE LA VARIABLE DEL FORMULARIO ANTERIOR EN EL CMB
		TipoCompetencia tc = (TipoCompetencia) catalogo.getVariable("tc",false);
		modalidades = servicioModalidadCompetencia.listarModalidad(tc);
		binder.loadAll();
		
	}
	
	public void doAfterCompose(Component c) throws Exception {
		super.doAfterCompose(c);		
		c.setVariable("cntrl", this, true);		
		catalogo = c;	
		if (lsbxModalidad.getItems().size() != 0){
			lsbxModalidad.setSelectedIndex(0);
		}
	}
	
	public void onClick$btnAceptar() throws Exception {
		if (lsbxModalidad.getSelectedIndex() != -1) {
			ModalidadCompetencia m = modalidades.get(lsbxModalidad.getSelectedIndex());
			Component formulario = (Component) catalogo.getVariable("formulario",false);
			formulario.setVariable("modalidadCompetencia",m,false);
			Events.sendEvent(new Event("onCatalogoCerrado",formulario));
			catalogo.detach();
		}else {
			Messagebox.show("Seleccione una modalidad", "Mensaje",	Messagebox.YES, Messagebox.INFORMATION);
			}
	}
	
	
	public void onCtrlKey$txtFiltro(){
		System.out.println("changing...");
	}
	
	public void onChanging$txtFiltro(){
		//divisas = servicioDivisa.filtar(txtFiltro.getText()+"%");
		binder.loadAll();
	}
	

	public void onClick$btnSalir() {
	 catalogo.detach();
	}



	public List<ModalidadCompetencia> getModalidades() {
		return modalidades;
	}



	public void setModalidades(List<ModalidadCompetencia> modalidades) {
		this.modalidades = modalidades;
	}
	

	
}