package controlador.jugador;

import java.util.ArrayList;

import java.util.List;
import org.zkoss.zk.ui.event.ForwardEvent;

import modelo.Categoria;
import modelo.Divisa;
import modelo.Equipo;
import modelo.EstadoVenezuela;
import modelo.TipoInstitucion;
import modelo.Institucion;
import modelo.Municipio;
import modelo.Parroquia;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.*;

import servicio.implementacion.ServicioDivisa;
import servicio.implementacion.ServicioCategoria;
import servicio.implementacion.ServicioClasificacionEquipo;
import servicio.implementacion.ServicioEquipo;
import servicio.implementacion.ServicioEstadoVenezuela;
import servicio.implementacion.ServicioInstitucion;
import servicio.implementacion.ServicioMunicipio;
import servicio.implementacion.ServicioParroquia;
import servicio.implementacion.ServicioTipoInstitucion;


public class ConfigurarCategoriaCtrl extends GenericForwardComposer {

 private Textbox txtNombre;
 private Spinner spCantidadEquipo;
 private Spinner spEdadInferior;
 private Spinner spEdadSuperior;
 private Spinner spMinJugadores;
 private Spinner spMaxJugadores;
 ServicioCategoria servicioCategoria;
 ServicioEquipo servicioEquipo;
 private Equipo equipo = new Equipo();
 private Categoria categoria = new Categoria();; 
 Listbox listCategoria;
 List<Categoria> listaCategoria;
 List<Categoria> categorias;
 private AnnotateDataBinder binder ;
 
 
 
 public void iniciar() {
	 
/*	spMinJugadores.setValue(12);
	spMaxJugadores.setValue(20);
	spCantidadEquipo.setSelectionRange(0, 20); // el valor max de equipos es variable (CAMBIAR)
	spEdadInferior.setSelectionRange(3, 16);
    spEdadSuperior.setSelectionRange(5, 17);
	 */
 }


 
   public void onCreate$winConfigurarCategoria(ForwardEvent event) {
	    // get the databinder for later extra load and save
	    // note: binder is not ready in doAfterCompose, so do it here
	    binder = (AnnotateDataBinder) event.getTarget().getVariable("binder", false);
	  }
 
   public Categoria getCategoria() {
		return categoria;
	}


	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}
	
	public Equipo getEquipo() {
		return equipo;
	}


	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		comp.setVariable("controller", this, false);  //Hacemos visible el modelo para el databinder
		listaCategoria=servicioCategoria.listar();
	
	
	}
	
	public  List<Categoria> getCategorias() {
		return servicioCategoria.listar();
		
	}
	
 
	public void onClick$btnAgregar(){
		
		//Divisa y Clasificacion fijas
//		equipo.setDivisa(servicioDivisa.listar().get(0));
	
		for (int i = 0; i < listaCategoria.size(); i++) {
			if(txtNombre.getValue().equals(listaCategoria.get(i).getNombre())){
				alert("Ya existe la categoria");
					return;
					
				}
			
			}
			
			
		
	    categoria.setEstatus('A');
	    categoria.setCodigoCategoria("00"+String.valueOf(servicioCategoria.listar().size()+1));
	    servicioCategoria.agregar(categoria);
	
		
		 try {
			Messagebox.show("Categoria agregada", "Exito", Messagebox.OK, Messagebox.INFORMATION);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 limpiar();
	}

	public void onClick$btnQuitar(){
		Categoria categoriaSeleccionada =  (Categoria) listCategoria.getSelectedItem().getValue();
		if (categoria!= null){
			servicioCategoria.eliminar(categoriaSeleccionada);
			limpiar();
		}
		else{
			alert("Seleccione un dato");
		}
	}
	
	public void limpiar(){
		categoria= new Categoria();
		iniciar();
		binder.loadAll();
	}
	
	public void onClick$btnCancelar(){
		limpiar();
	}
}

