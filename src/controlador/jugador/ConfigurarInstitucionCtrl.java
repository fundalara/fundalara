package controlador.jugador;

import java.util.ArrayList;

import java.util.List;
import org.zkoss.zk.ui.event.ForwardEvent;
import modelo.EstadoVenezuela;
import modelo.TipoInstitucion;
import modelo.Institucion;
import modelo.Municipio;
import modelo.Parroquia;
import modelo.TipoActividadSocial;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.*;

//import servicio.general.ServicioDatosBasicos;
import servicio.implementacion.ServicioEstadoVenezuela;
import servicio.implementacion.ServicioInstitucion;
import servicio.implementacion.ServicioMunicipio;
import servicio.implementacion.ServicioParroquia;
import servicio.implementacion.ServicioTipoInstitucion;

public class ConfigurarInstitucionCtrl extends GenericForwardComposer {
	ServicioInstitucion servicioInstitucion;
	ServicioTipoInstitucion servicioTipoInstitucion;
	ServicioEstadoVenezuela servicioEstadoVenezuela;
	ServicioMunicipio servicioMunicipio;
	ServicioParroquia servicioParroquia;
	
	//ServicioDatosBasicos servicioDatosBasicos;
	
	Institucion institucion;
	TipoInstitucion tipoInstitucion;
	List<EstadoVenezuela> estadoVenezuela;
	Textbox txtCodigo;
	Textbox txtNombre;
	Textbox txtDireccion;
	List<TipoInstitucion> tipoInst;
	List<String> lista;
	List<Parroquia> parroquias;
	List<EstadoVenezuela> estados;
	List<Municipio> municipios;
	 AnnotateDataBinder binder;
	Combobox cmbTipo, cmbEstado, cmbParroquia, cmbMunicipio;
 
	public void onCreate$win(ForwardEvent event){
		 binder = (AnnotateDataBinder) event.getTarget().getVariable("binder", false);  
  }
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		comp.setVariable("cntrl", this, false);  //Hacemos visible el modelo para el databinder
		institucion = new Institucion();

		 int codigo = servicioInstitucion.listar().size();
		institucion.setCodigoInstitucion(String.valueOf(codigo + 1));
		lista = new ArrayList<String>();
		tipoInst = servicioTipoInstitucion.listar();
		estados = servicioEstadoVenezuela.listar();
		
		estadoVenezuela= servicioEstadoVenezuela.listar();
		for (int i = 0; i < estadoVenezuela.size(); i++) {
				Comboitem item = new Comboitem();
				item.setLabel(estadoVenezuela.get(i).getNombre());
				item.setValue(estadoVenezuela.get(i).getCodigoEstado());
				cmbEstado.appendChild(item);
			
		}
		
		
		
	}

	public void onSelect$cmbEstado() {
		cmbMunicipio.setDisabled(false);
		cmbMunicipio.getItems().clear();
		cmbMunicipio.setValue("--Seleccione--");
		cmbParroquia.setDisabled(true);
		cmbParroquia.setValue("--Seleccione--");
		municipios = servicioMunicipio.listar();
		for (int i = 0; i < municipios.size(); i++) {
			if (municipios.get(i).getEstadoVenezuela().getCodigoEstado()
					.toString()
					.equals(cmbEstado.getSelectedItem().getValue().toString())) {
				Comboitem item = new Comboitem();
				item.setLabel(municipios.get(i).getNombre().toString());
				item.setValue(municipios.get(i).getCodigoMunicipio().toString());
				cmbMunicipio.appendChild(item);
			} else {
			}
		}
	}

	public void onSelect$cmbMunicipio() {
		cmbParroquia.setDisabled(false);
		cmbParroquia.getItems().clear();
		cmbParroquia.setValue("--Seleccione--");
		parroquias = servicioParroquia.listar();
		for (int i = 0; i < parroquias.size(); i++) {
			if (parroquias
					.get(i)
					.getMunicipio()
					.getCodigoMunicipio()
					.toString()
					.equals(cmbMunicipio.getSelectedItem().getValue()
							.toString())) {
				Comboitem item = new Comboitem();
				item.setLabel(parroquias.get(i).getNombre().toString());
				item.setValue(parroquias.get(i).getCodigoParroquia().toString());
				cmbParroquia.appendChild(item);
			} else {
			}
		}
	}

	public void onClick$btnGuardar(){
	//TipoInstitucion tipoI = tipoInst.get(cmbTipo.getSelectedIndex());
	//institucion.setTipoInstitucion(tipoI);
		Parroquia parro= new Parroquia() ;
		parroquias = servicioParroquia.listar();
		for (int i = 0; i < parroquias.size(); i++) {
			if (parroquias
					.get(i)
					.getCodigoParroquia()
					.equals(cmbParroquia.getSelectedItem().getValue().toString())) {
				parro=parroquias.get(i);
			} 
		}
	
		
	institucion.setParroquia(parro);
	///institucion.
	//institucion.setCodigoInstitucion(txtCodigo.getValue());
//	institucion.setDireccion(txtDireccion.getValue());
	institucion.setEstatus('A');
	servicioInstitucion.agregar(institucion);
	institucion= new Institucion();
	binder.loadAll();
	}
	
	public void onClick$btnCancelar(){
		institucion= new Institucion();
		binder.loadAll();
		
	}

    public void onClick$btnConsultar(){
    		  Institucion InstTemp = new Institucion();
    		  InstTemp = servicioInstitucion.buscar(institucion.getCodigoInstitucion());
    		 
    		  if (InstTemp!=null){
    		  	institucion = InstTemp;
    		  }
    		  else {
    		  	try {
					Messagebox.show("Institucion no encontrada", "Information", Messagebox.OK, Messagebox.INFORMATION);
		 
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		  	InstTemp = new Institucion();
    		  }
    		  binder.loadAll();
    	  }

  
	
	public List<TipoInstitucion> getTipoInstituciones() {
		return servicioTipoInstitucion.listar();
	}

	public List<EstadoVenezuela> getEstadoVenezuela() {
		return servicioEstadoVenezuela.listar();
	}

	public Institucion getInstitucion() {
		return institucion;
	}

	public void setInstitucion(Institucion institucion) {
		this.institucion = institucion;
	}

	public List<String> getLista() {
		return lista;
	}

	public void setLista(List<String> lista) {
		this.lista = lista;
	}

}
