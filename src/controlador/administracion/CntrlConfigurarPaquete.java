package controlador.administracion;

import java.util.ArrayList;
import java.util.List;

import modelo.DatoBasico;
import modelo.IngresoInscripcion;
import modelo.TipoIngreso;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Doublebox;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Spinner;

import servicio.implementacion.ServicioDatoBasico;
import servicio.implementacion.ServicioIngresoInscripcion;
import servicio.implementacion.ServicioTipoDato;
import servicio.implementacion.ServicioTipoIngreso;

public class CntrlConfigurarPaquete extends GenericForwardComposer {
	
	Combobox cmbTipoInscripcion,cmbCuotas;
	DatoBasico tipoInscripcion;
	Listbox lbxIngresoInscripcion;
	Doublebox dbxPrecio;
	Spinner sprCantidad,sprAdelantos;
	TipoIngreso cuota;
	List<TipoIngreso> cuotas=new ArrayList<TipoIngreso>();
	List<DatoBasico> listaInscripcion=new ArrayList<DatoBasico>();
	List<IngresoInscripcion> listaIngresoInscripcion=new ArrayList<IngresoInscripcion>();
	IngresoInscripcion ingresoInscripcion;
	AnnotateDataBinder binder;

	ServicioDatoBasico servicioDatoBasico;
	ServicioTipoIngreso servicioTipoIngreso;
	ServicioIngresoInscripcion servicioIngresoInscripcion;

	// ---------------------------------------------------------------------------------------------------
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		comp.setVariable("cntrl", this, true);
		listaInscripcion=servicioDatoBasico.listarPorPadre("SUBPROCESO",servicioDatoBasico.buscarPorString("INSCRIPCION").getCodigoDatoBasico());
		cuotas=servicioTipoIngreso.listarCuotas();
		
	}
	
	public void onChange$cmbTipoInscripcion(){
		listaIngresoInscripcion=servicioIngresoInscripcion.listarIngresoInscripcion(cmbTipoInscripcion.getValue().toString());
		
	
		
		
		
		
		
		//System.out.println(listaIngresoInscripcion.get(1).getMonto());
		binder.loadComponent(lbxIngresoInscripcion);
		
		
	}
//public void onClick$btnAgregar() {
//		
//		if(!buscar()){
//			if (flag == false){ 
//				if (txtDescripcion.getText().trim() == "") {
//					alert("Debe indicar una Descripcion o Nombre para el Ingreso");
//					txtDescripcion.setFocus(true);
//					return;
//				} else if (dbxPrecio.getText() == "") {
//					alert("Debe indicar en el precio ");
//					dbxPrecio.setFocus(true);
//					return;
//				} else  {
//					tipoIngreso.setCodigoTipoIngreso(servicioTipoIngreso.listar().size()+1);
//
//					tipoIngreso.setDatoBasicoByCodigoPeriodicidad((DatoBasico) cmbPeriodicidad.getSelectedItem().getValue());
//					tipoIngreso.setDatoBasicoByCodigoTipo((DatoBasico) cmbTipoIngreso.getSelectedItem().getValue());
//					tipoIngreso.setDescripcion(txtDescripcion.getValue().toUpperCase());
//					tipoIngreso.setEstatus('A');
//					tipoIngreso.setMonto(dbxPrecio.getValue());
//					listaTipoIngresos.add(tipoIngreso);
//					if(rdoSi.isChecked())
//						tipoIngreso.setAplicaRepresentante(rdoSi.isChecked());
//					else
//						tipoIngreso.setAplicaRepresentante(false);
//					clear();
//				}
//			} else {
//				alert("Debe terminar el proceso de edición del Concepto");
//			}
//			tipoIngreso = new TipoIngreso();
//		}else{
//			if(lbxTipoIngresos.getSelectedCount()!=0){
//			System.out.println("hola");
//			tipoIngreso=(TipoIngreso) lbxTipoIngresos.getSelectedItem().getValue();
//			tipoIngreso.setMonto(dbxPrecio.getValue());
//			tipoIngreso.setDatoBasicoByCodigoPeriodicidad((DatoBasico) cmbPeriodicidad.getSelectedItem().getValue());
//			binder.loadComponent(lbxTipoIngresos);
//			}else
//				alert("Este compromiso ya esta registro");
//			
//		}
//		btnQuitar.setDisabled(true);
//	}
//	

	// ---------------------------------------------------------------------------------------------------	
	public void onSelect$lbxIngresoInscripcion(){
		IngresoInscripcion auxIngreso= new IngresoInscripcion();
		auxIngreso= listaIngresoInscripcion.get(lbxIngresoInscripcion.getSelectedIndex());
		cmbCuotas.setValue(auxIngreso.getTipoIngreso().getDescripcion()); 
		dbxPrecio.setValue(auxIngreso.getMonto());
		sprCantidad.setValue(auxIngreso.getCantidad());
//		sprAdelantos.setValue(auxIngreso.getAdelantos());
//		btnQuitar.setDisabled(false);
//		
		
		//clear();
		//binder.loadComponent(cmbTipoTalla);
	}
	// ---------------------------------------------------------------------------------------------------
	
	// ---------------------------------------------------------------------------------------------------
	
	public List<DatoBasico> getListaInscripcion() {
		return listaInscripcion;
	}
	
	public void setListaInscripcion(List<DatoBasico>  listaInscripcion) {
		this.listaInscripcion =  listaInscripcion;
	}
	
	public DatoBasico getTipoInscripcion() {
		return tipoInscripcion;
	}

	public void setTipoInscripcion(DatoBasico tipoInscripcion) {
		this.tipoInscripcion = tipoInscripcion;
	}

	public TipoIngreso getCuota() {
		return cuota;
	}

	public void setCuota(TipoIngreso cuota) {
		this.cuota = cuota;
	}

	public List<TipoIngreso> getCuotas() {
		return cuotas;
	}

	public void setCuotas(List<TipoIngreso> cuotas) {
		this.cuotas = cuotas;
	}

	public List<IngresoInscripcion> getListaIngresoInscripcion() {
		return listaIngresoInscripcion;
	}

	public void setListaIngresoInscripcion(
			List<IngresoInscripcion> listaIngresoInscripcion) {
		this.listaIngresoInscripcion = listaIngresoInscripcion;
	}

	public IngresoInscripcion getIngresoInscripcion() {
		return ingresoInscripcion;
	}

	public void setIngresoInscripcion(IngresoInscripcion ingresoInscripcion) {
		this.ingresoInscripcion = ingresoInscripcion;
	}

}
