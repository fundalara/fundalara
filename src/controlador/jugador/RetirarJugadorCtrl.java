/**
 * Clase controladora de los eventos de la vista de igual nombre.
 * 
 * @author Glendy Oliveros
 * @author Aimee Monsalve
 * @author Greisy Rodriguez
 * @author Alberto Perozo
 * @author Edgar Luzardo
 * 
 * @version 1.0 12/12/2011
 *
 * */

package controlador.jugador;


import org.zkoss.lang.Strings;
import org.zkoss.lang.Objects;
import javax.swing.JOptionPane;
import javax.swing.JDialog;
import javax.swing.JComboBox;
import java.io.IOException;

import java.util.Date;
import java.util.List;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Image;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Radiogroup;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;
import org.zkoss.zul.api.Tab;
import org.zkoss.*;

import antlr.debug.Event;

import servicio.implementacion.ServicioJugador;
import servicio.implementacion.ServicioCategoria;
//import servicio.implementacion.ServicioCurso;

import comun.FileLoader;
import comun.Ruta;
import comun.Util;
import modelo.Categoria;
//import modelo.Curso;
import modelo.Institucion;
import modelo.Jugador;
//import modelo.Parroquia;




public class RetirarJugadorCtrl extends GenericForwardComposer {
	
	private Window winRetirarJugador;
	
	private Combobox cmbNacionalidadJugador;
	private Intbox txtCedula;	
	private Button btnCatalogoJugador;
	private Textbox txtPrimerNombre;
	private Textbox txtSegundoNombre;
	private Textbox txtPrimerApellido;
	private Textbox txtSegundoApellido;
	private Datebox dtboxFechaIngreso;
	private Textbox txtCategoriaActual;
	private Radiogroup radioGneroJ;
	private Textbox txtMotivo;
	private Jugador jugador;
	
	private Button btnVerExpediente;
	private Button btnRetirar;
	private Button btnCancelar;
	private Button btnSalir;
	
	private Image imgJugador;
	AnnotateDataBinder binder;
	private ServicioCategoria servicioCategoria;
	private String rutasJug= Ruta.JUGADOR.getRutaVista();

	//Set y Get
	public Combobox getCmbNacionalidadJugador() {
		return cmbNacionalidadJugador;
	}

	public void setCmbNacionalidadJugador(Combobox cmbNacionalidadJugador) {
		this.cmbNacionalidadJugador = cmbNacionalidadJugador;
	}
	
	public Intbox getTxtCedula() {
		return txtCedula;
	}

	public void setTxtCedula(Intbox txtCedula) {
		this.txtCedula = txtCedula;
	}

	public Textbox getTxtPrimerNombre() {
		return txtPrimerNombre;
	}

	public void setTxtPrimerNombre(Textbox txtPrimerNombre) {
		this.txtPrimerNombre = txtPrimerNombre;
	}

	public Textbox getTxtSegundoNombre() {
		return txtSegundoNombre;
	}

	public void setTxtSegundoNombre(Textbox txtSegundoNombre) {
		this.txtSegundoNombre = txtSegundoNombre;
	}

	public Textbox getTxtPrimerApellido() {
		return txtPrimerApellido;
	}

	public void setTxtPrimerApellido(Textbox txtPrimerApellido) {
		this.txtPrimerApellido = txtPrimerApellido;
	}

	public Textbox getTxtSegundoApellido() {
		return txtSegundoApellido;
	}

	public void setTxtSegundoApellido(Textbox txtSegundoApellido) {
		this.txtSegundoApellido = txtSegundoApellido;
	}

	public Datebox getDtboxFechaIngreso() {
		return dtboxFechaIngreso;
	}

	public void setDtboxFechaIngreso(Datebox dtboxFechaIngreso) {
		this.dtboxFechaIngreso = dtboxFechaIngreso;
	}

	public Textbox getTxtCategoriaActual() {
		return txtCategoriaActual;
	}

	public void setTxtCategoriaActual(Textbox txtCategoriaActual) {
		this.txtCategoriaActual = txtCategoriaActual;
	}

	public Radiogroup getRadioGneroJ() {
		return radioGneroJ;
	}

	public void setRadioGneroJ(Radiogroup radioGneroJ) {
		this.radioGneroJ = radioGneroJ;
	}

	public Jugador getJugador() {
		return jugador;
	}

	public Textbox getTxtMotivo() {
		return txtMotivo;
	}

	public void setTxtMotivo(Textbox txtMotivo) {
		this.txtMotivo = txtMotivo;
	}	
	
	public void setJugador(Jugador jugador) {
		this.jugador = jugador;
	}

	public Image getImgJugador() {
		return imgJugador;
	}

	public void setImgJugador(Image imgJugador) {
		this.imgJugador = imgJugador;
	}

	//Servicios, Procesos y Metodos
	public ServicioCategoria getServicioCategoria() {
		return servicioCategoria;
	}

	public void setServicioCategoria(ServicioCategoria servicioCategoria) {
		this.servicioCategoria = servicioCategoria;
	}

	public String getRutasJug() {
		return rutasJug;
	}

	public void setRutasJug(String rutasJug) {
		this.rutasJug = rutasJug;
	}
	
	public void onCreate$win(ForwardEvent event){
		 binder = (AnnotateDataBinder) event.getTarget().getVariable("binder", false);  
	}
	
	public void onClick$btnCatalogoJugador() {
		new Util().crearVentana("Jugador/Vistas/buscarJugador.zul",
				null, null);
	}		
	
	public boolean camposVacios(){
		
		boolean resp = false;
		
		if (this.getCmbNacionalidadJugador().getSelectedIndex() == -1)
			resp = true;
		
//		if (this.getTxtCedula().getValue().toString() == "")
//			resp = true;
		
		return resp;
			
	}
	
	public void onClick$btnVerExpediente() throws InterruptedException{
		if (!camposVacios()){
				new Util().crearVentana("Jugador/Vistas/vistaRegistroJugador.zul",
						null, null);				
		}
		else{
			try {
				Messagebox.show(
						"Hay campos obligatorios",
						"Fundalara", Messagebox.OK, Messagebox.EXCLAMATION);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}			
		}
	}	

	public void onClick$btnRetirar(){
		if (!camposVacios()){
			try {
				Messagebox.show("¿Está seguro de retirar al jugador?",
						"Question", Messagebox.OK | Messagebox.CANCEL,Messagebox.QUESTION);
				
					String prueba = "OK";			    
					if (prueba == "OK")
			            alert("Se ha retirado satisfactoriamente!");
			          else if (prueba == "Cancelar")
			            Messagebox.show("Ignore Save", "Warning", Messagebox.OK, Messagebox.EXCLAMATION);
			          else
			            alert("Save Canceled!");
			    
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		else{
			try {
				Messagebox.show(
						"No jodas!",
						"Fundalara", Messagebox.OK, Messagebox.EXCLAMATION);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}			
		}				
	}
	

	public void onClick$btnCancelar(){
		jugador = new Jugador();
		binder.loadAll();				
	}	
	
	public void onClick$btnSalir() {
		winRetirarJugador.detach();
	}	
	
}
	
	

