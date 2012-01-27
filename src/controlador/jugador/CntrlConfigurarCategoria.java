package controlador.jugador;

import java.util.ArrayList;

import java.util.List;
import org.zkoss.zk.ui.event.ForwardEvent;

import modelo.Categoria;
import modelo.Divisa;
import modelo.Equipo;
import modelo.Institucion;


import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.*;

import controlador.jugador.restriccion.Restriccion;

import servicio.implementacion.ServicioDivisa;
import servicio.implementacion.ServicioCategoria;
import servicio.implementacion.ServicioEquipo;
import servicio.implementacion.ServicioInstitucion;

import java.awt.FlowLayout;
import javax.swing.*;
import javax.swing.event.*;


public class CntrlConfigurarCategoria extends GenericForwardComposer {

 private Textbox txtNombre;
 private Spinner spCantidadEquipo;
 private Spinner spEdadInferior;
 private Spinner spEdadSuperior;
 private Spinner spMinJugadores;
 private Spinner spMaxJugadores;
 ServicioCategoria servicioCategoria;
 ServicioEquipo servicioEquipo;
 private Equipo equipo = new Equipo();
 private Categoria categoria = new Categoria();
 private Listbox listCategoria;
 private Listbox listEquipo;
 List<Categoria> listaCategoria;
 List<Categoria> categorias;
 List<Equipo> listaEquipo;
 private AnnotateDataBinder binder ;
 private Listcell item2;
 Button btnAgregar, btnQuitar, btnModificar,btnCancelar,btnSalir;

    public void onCreate$winConfigurarCategoria(ForwardEvent event) {
	    // get the databinder for later extra load and save
	    // note: binder is not ready in doAfterCompose, so do it here
    	inicializar();
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
	
	public void onSelect$listCategoria() {
		// btnagregar.setDisabled(false);
		btnModificar.setDisabled(false);
		btnQuitar.setDisabled(false);
		btnAgregar.setDisabled(true);
	}
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		comp.setVariable("controller", this, false);  //Hacemos visible el modelo para el databinder
		listaCategoria=servicioCategoria.listar();
		//aplicarConstraints(); DESACTIVADO POR FALTA DE CONSTRAINT
	}
	
	public  List<Categoria> getCategorias(){
		 return servicioCategoria.listar();
	}
 
	public void onClick$btnAgregar(){
		if (txtNombre.getText() == "") {
			alert("Seleccione una nombre");
			txtNombre.focus();
		} else if (spEdadInferior.getValue() == 0) {
			alert("Seleccione una edad inferior");
			spEdadInferior.focus();
		} else if (spEdadSuperior.getValue() == 0) {
			alert("Seleccione una edad superior");
			spEdadSuperior.focus();
		} else if (spMinJugadores.getValue() == 0) {
			alert("Seleccione un minimo de jugadores");
			spMinJugadores.focus();
		} else if (spMaxJugadores.getValue() == 0) {
					alert("Seleccione un maximo de jugadores");
					spMaxJugadores.focus();
		} else if (spCantidadEquipo.getValue() == 0) {
							alert("Seleccione la cantidad de equipos");
							spMinJugadores.focus();
		} else {	
		    if(validar()){
			   for (int i = 0; i < listaCategoria.size(); i++) {
				 if(txtNombre.getValue().equals(listaCategoria.get(i).getNombre())){
						alert("Ya existe la categoria");
						 return;
				 }
			   }			
			   categoria.setEstatus('A');
			   categoria.setCodigoCategoria(servicioCategoria.listar().size()+1);
			   servicioCategoria.agregar(categoria);
			   
			   try {
					Messagebox.show("Categoria agregada", "Exito", Messagebox.OK, Messagebox.INFORMATION);
			   } catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
			   }
			   limpiar(); 
		    }
		}
	}

   public void onClick$btnQuitar(){
		if (categoria!= null){
				if (listCategoria.getSelectedIndex() >= 0) {
					Categoria categoriaTemp = (Categoria) listCategoria.getSelectedItem().getValue();
					  if (servicioCategoria.buscarPorCodigo(categoria)==false)
					//System.out.println(servicioCategoria.buscarPorCodigo(categoria));
						alert ("No se puede borrar mientras existan equipos en esta categoria");
					  else {
						categoria.setEstatus('E');
			            listaCategoria.remove(categoriaTemp);
						servicioCategoria.eliminar(categoriaTemp);
				        limpiar();    
					    }
					} 
				else {
					alert("Seleccione un dato para eliminar.");
				 } 
		}
  }
  
	private void aplicarConstraints() {
		// Registro Jugador
		txtNombre.setConstraint(Restriccion.TEXTO_SIMPLE
				.asignarRestriccionExtra("no empty"));
		spCantidadEquipo.setConstraint(Restriccion.CANTIDAD_EQUIPO
				.getRestriccion());
		spEdadInferior.setConstraint(Restriccion.EDAD_INFERIOR
				.getRestriccion());
		spEdadSuperior.setConstraint(Restriccion.EDAD_SUPERIOR
				.getRestriccion());
		spMinJugadores.setConstraint(Restriccion.MIN_JUGADORES
				.getRestriccion());
		spMaxJugadores.setConstraint(Restriccion.MAX_JUGADORES
				.getRestriccion());
	}
	
	public void onClick$btnModificar() throws InterruptedException{
		if (categoria!= null){
			categoria.setNombre(txtNombre.getValue());
			categoria.setEdadInferior(spEdadInferior.getValue());
			categoria.setEdadSuperior(spEdadSuperior.getValue());
			categoria.setCantidadEquipo(spCantidadEquipo.getValue());
			categoria.setMaximoJugador(spMaxJugadores.getValue());
			categoria.setMinimoJugador(spMinJugadores.getValue());
			categoria.setEstatus('A');
			
			servicioCategoria.actualizar(categoria);
			
			 try {
					Messagebox.show("Categoria modificada", "Exito", Messagebox.OK, Messagebox.INFORMATION);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 limpiar();
		}
		else{
			alert("Seleccione un dato");
		}
	}
	
	public void limpiar(){
		categoria= new Categoria();
		binder.loadAll();
		inicializar();
	}
	
	public void onClick$btnCancelar(){
		limpiar();
	}
	
	public void inicializar() {
		spEdadInferior.setValue(3);
		spEdadSuperior.setValue(5);
		spMinJugadores.setValue(12);
		spMaxJugadores.setValue(20);
		btnAgregar.setDisabled(false);
		btnQuitar.setDisabled(true);
		btnModificar.setDisabled(true);
	    btnSalir.setDisabled(false);
		btnCancelar.setDisabled(false);
	}
	
	public boolean validar() {
		 if (spCantidadEquipo.getValue()==0 && spEdadInferior.getValue()==0 && spEdadSuperior.getValue()==0 && spMinJugadores.getValue()==0 && spMaxJugadores.getValue()==0){
			 alert("Ningun dato numerico puede ser cero (0)");
			 spEdadInferior.setFocus(true);
			 return false;
	         }
		 else if (spEdadInferior.getValue() > spEdadSuperior.getValue() ){
						 alert("La edad inferior es mayor a edad superior");
						 spEdadInferior.setFocus(true);
						 return false;
				         }
					 else {
						 int dif =  spEdadSuperior.getValue() - spEdadInferior.getValue() ;
						
						 if ( dif > 1 ){
							 alert("Rango de edad no permitido en esta categoria");
							 spEdadInferior.setFocus(true);
							 return false;
						 }
					 }
	       return true; 
		}
}