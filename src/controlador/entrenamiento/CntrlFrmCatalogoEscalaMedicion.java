package controlador.entrenamiento;

import java.util.List;

import modelo.DatoBasico;
import modelo.EscalaMedicion;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Button;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Window;

import servicio.implementacion.ServicioEscalaMedicion;

public class CntrlFrmCatalogoEscalaMedicion extends GenericForwardComposer {
	Window winCatalogoEscalaMedicion;
	Listbox lboxValoresEscala;
	List<EscalaMedicion> listEscalaMedicion;
	Button btnAceptar, btnCancelar;
	ServicioEscalaMedicion servicioEscalaMedicion;
	CntrlFrmEscalaMedicion medicion;
	
	AnnotateDataBinder binder;

	public CntrlFrmEscalaMedicion getMedicion() {
		return medicion;
	}

	public void setMedicion(CntrlFrmEscalaMedicion medicion) {
		this.medicion = medicion;
	}

	public List<EscalaMedicion> getListEscalaMedicion() {
		return listEscalaMedicion;
	}

	public void setListEscalaMedicion(List<EscalaMedicion> listEscalaMedicion) {
		this.listEscalaMedicion = listEscalaMedicion;
	}
	
	public void doAfterCompose(Component comp) throws Exception{
		super.doAfterCompose(comp);
		comp.setVariable("cntrl", this, true);
		DatoBasico db = (DatoBasico)execution.getAttribute("tipoEscala");
		listEscalaMedicion = servicioEscalaMedicion.listarPorTipoEscala(db);
		if (arg.containsKey("ModuleMainController")) {
			setMedicion((CntrlFrmEscalaMedicion) arg.get("ModuleMainController"));
			getMedicion().setMedicion(this);
		}
//		binder.loadAll();
	}
	
	public void onClick$btnCancelar(){
		winCatalogoEscalaMedicion.detach();
	}
	
	public void onClick$btnAceptar(){
		if(lboxValoresEscala.getSelectedItem() != null){
			getMedicion().listValoresEscala.clear();
			EscalaMedicion em=(EscalaMedicion)lboxValoresEscala.getSelectedItem().getValue();
			getMedicion().setEscalaMedicion(em);
			getMedicion().listValoresEscala.addAll(getMedicion().servicioValorEscala.buscarValor(em));			
			getMedicion().txtDescripcionValor.setReadonly(false);
			getMedicion().txtValorEscala.setReadonly(false);
			getMedicion().cmbTipoEscala.setDisabled(true);
			getMedicion().binder.loadAll();
			getMedicion().txtNombreEscala.setReadonly(true);
			getMedicion().txtDescripcionEscala.setReadonly(true);
			winCatalogoEscalaMedicion.detach();
		}else
			throw new WrongValueException(lboxValoresEscala, "Debes seleccionar un item");
	}
}
