package controlador.logistica;

import java.util.List;

import modelo.DatoBasico;
import modelo.Instalacion;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Doublebox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Spinner;
import org.zkoss.zul.Textbox;

import comun.TipoDatoBasico;

import servicio.interfaz.IServicioDatoBasico;
import servicio.interfaz.IServicioInstalacion;

/**
 * Clase controladora de los eventos de la vista de igual nombre y manejo de los
 * servicios de datos para el registro de nuevas instalaciones
 * 
 * @author Reinaldo L.
 * 
 * */
public class CntrlRegistrarInstalacion extends GenericForwardComposer {
	
	private Instalacion instalacion = new Instalacion();
	
	private IServicioDatoBasico servicioDatoBasico;
	private IServicioInstalacion servicioInstalacion;
	
	private List<Instalacion> instalaciones;
	private List<DatoBasico> tiposInstalaciones;
	
	private Textbox txtDescripcion;
	private Combobox cmbTipoInstalacion;
	private Spinner spCapacidad;
	private Doublebox dboxTamano;
	
	private AnnotateDataBinder binder;
	
	public void doAfterCompose(Component comp)throws Exception{
		super.doAfterCompose(comp);		
		comp.setVariable("cntrl", this, false);
	}
	
	public Instalacion getInstalacion() {
		return instalacion;
	}
	
	public void setInstalacion(Instalacion instalacion) {
		this.instalacion = instalacion;
	}
	
	public List<Instalacion> getInstalaciones() {
		instalaciones = servicioInstalacion.listarActivos();
		return instalaciones;
	}

	public void setInstalaciones(List<Instalacion> instalaciones) {
		this.instalaciones = instalaciones;
	}
	
	public List<DatoBasico> getTiposInstalaciones() {
		tiposInstalaciones = servicioDatoBasico.buscar(TipoDatoBasico.TIPOS_INSTALACIONES);
		return tiposInstalaciones;
	}

	public void setTiposInstalaciones(List<DatoBasico> tiposInstalaciones) {
		this.tiposInstalaciones = tiposInstalaciones;
	}

	public void onClick$btnRegistrar() throws InterruptedException{
		
		validar();
		
		instalacion.setEstatus('A');
		servicioInstalacion.agregar(instalacion);
		
		this.onClick$btnCancelar();
		Messagebox.show("Datos agregados exitosamente","Mensaje",Messagebox.OK,Messagebox.EXCLAMATION);
	}
	
	public void onClick$btnCancelar(){
		instalacion = new Instalacion();
		binder.loadAll();
	}
	
	public void onClick$btnModificar() throws InterruptedException{
		servicioInstalacion.actualizar(instalacion);
		onClick$btnCancelar();
		Messagebox.show("Datos modificados exitosamente","Mensaje",Messagebox.OK,Messagebox.EXCLAMATION);
	}
	
	public void onClick$btnEliminar() throws InterruptedException{
		if(Messagebox.show("¿Realmente desea eliminar esta instalación?","Mensaje",Messagebox.YES+Messagebox.NO,Messagebox.QUESTION) == Messagebox.YES){
			instalacion.setEstatus('E');
			servicioInstalacion.actualizar(instalacion);
			this.onClick$btnCancelar();
			Messagebox.show("Datos eliminados exitosamente","Mensaje",Messagebox.OK,Messagebox.EXCLAMATION);
		}else{
			onClick$btnCancelar();			
		}
	}

	public void onSelect$lboxInstalaciones() throws InterruptedException{
		
		Messagebox.show("Datos cargados exitosamente","Mensaje",Messagebox.OK,Messagebox.EXCLAMATION);
		for(int i=0; i<tiposInstalaciones.size(); i++){
			if(tiposInstalaciones.get(i).getNombre().equals(instalacion.getDatoBasico().getNombre())){
				System.out.println("yyyyyyyyyyyyyyyyy");
				cmbTipoInstalacion.setSelectedIndex(i);
				break;
			}
		}		
	}
	
	public void validar(){
		txtDescripcion.getValue();
		cmbTipoInstalacion.getValue();
		spCapacidad.getValue();
		dboxTamano.getValue();
	}











	//------------
	//<!-- 	         seccion de instancias de modelos -->
	//modelo.Instalacion instalacion = new modelo.Instalacion();
//	modelo.TipoInstalacion tInstalacion = new modelo.TipoInstalacion();
//	List listaTipoInstalacion = servicioTipoInstalacion.listar();
//	List listaArea = servicioInstalacion.listarInstalacion();
//	List listaInstalacion = servicioInstalacion.listarInstalacion();
//	//<!-- seccion de llamada de metodos y funciones -->
//	public void guardar() {
//		instalacion.setCodigoInstalacion(servicioInstalacion.generarCodigo());
//		instalacion.setEstatus('A');
//		tInstalacion = servicioTipoInstalacion.buscar(comboTipoInstalacion
//				.getSelectedItem().getValue());
//		instalacion.setTipoInstalacion(tInstalacion);
//		try {
//			instalacion.setArea(comboArea.getSelectedItem().getValue()
//					.toString());
//		} catch (Exception e) {
//		}
//		servicioInstalacion.guardar(instalacion);
//		this.cargarCombo();
//		comboArea.setValue("");
//		comboTipoInstalacion.setValue("");
//		instalacion = new modelo.Instalacion();
//		tInstalacion = new modelo.TipoInstalacion();
//		alert("Se guardo exitosamente");
//		binder.loadAll();
//	}
//	public void eliminar() {
//		instalacion.setEstatus('E');
//		servicioInstalacion.guardar(instalacion);
//		instalacion = new modelo.Instalacion();
//		comboTipoInstalacion.setValue("");
//		cargarCombo();
//		binder.loadAll();
//
//		alert("Se elimino exitosamente");
//	}
//	public void cancelar() {
//		this.cargarCombo();
//		comboArea.setValue("");
//		comboTipoInstalacion.setValue("");
//		instalacion = new modelo.Instalacion();
//		tInstalacion = new modelo.TipoInstalacion();
//		binder.loadAll();
//	}
//	public void cargarCombo() {
//		listaTipoInstalacion = servicioTipoInstalacion.listar();
//		listaArea = servicioInstalacion.listarInstalacion();
//		listaInstalacion = servicioInstalacion.listarInstalacion();
//	}
//	public void cargar_datos() {
//		String codigo = tabla.getSelectedItem().getLabel();
//		instalacion = servicioInstalacion.buscar(codigo);
//		String codigoTI = instalacion.getTipoInstalacion()
//				.getCodigoTipoInstalacion();
//		tInstalacion = servicioTipoInstalacion.buscar(codigoTI);
//		comboTipoInstalacion.setValue(tInstalacion.getDescripcion());
//		comboArea.setValue(instalacion.getArea());
//		binder.loadAll();
//	}

}
