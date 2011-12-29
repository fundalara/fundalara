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

}
