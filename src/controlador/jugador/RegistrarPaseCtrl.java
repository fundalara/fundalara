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

import java.io.IOException;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import modelo.Categoria;
import modelo.Institucion;
//import modelo.Parroquia;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Radiogroup;
import org.zkoss.zul.Include;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Label;
import org.zkoss.zul.Button;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Window;

import servicio.implementacion.ServicioDivisa;
import servicio.implementacion.ServicioCategoria;
import servicio.implementacion.ServicioEquipo;
import servicio.implementacion.ServicioFamiliar;
import servicio.implementacion.ServicioJugador;
//import servicio.implementacion.ServicioLiga;
//import servicio.implementacion.ServicioMunicipio;


import comun.Util;

public class RegistrarPaseCtrl extends GenericForwardComposer {

	private Window windregistrarPase;
	private String txtnNumeroPase;

	//Datos del jugador
	private Combobox cmbNacionalidad;
	private String txtCedulaJug;
	private Button btnCatalogoJugador;
	private Datebox dtboxFechaNac;
	private String txtNombreJug;
	private String txtApellidoJug;
	private Combobox cmbCategoria;
	private String txtEquipo;
			
	//Datos de donde quiere jugar
	private String txtDivisaNueva;	
	private String txtLigaNueva;
	private Combobox cmbEstado;
	private String txtMotivoCambio;
	private String txtObservaciones;

	private Button btnExportar;
	private Button btnCancelar;
	private Button btnSalir;	
	
	private AnnotateDataBinder binder;
	
	
	//Set y Get
	
	public String getTxtnNumeroPase() {
		return txtnNumeroPase;
	}

	public void setTxtnNumeroPase(String txtnNumeroPase) {
		this.txtnNumeroPase = txtnNumeroPase;
	}	
	
	public Combobox getNacionalidad() {
		return cmbNacionalidad;
	}

	public void setNacionalidad(Combobox cmbNacionalidad) {
		this.cmbNacionalidad = cmbNacionalidad;
	}	
	
	public String getTxtCedulaJug() {
		return txtCedulaJug;
	}

	public void setTxtCedulaJug(String txtCedulaJug) {
		this.txtCedulaJug = txtCedulaJug;
	}
	
	public Datebox getDtboxFechaNac() {
		return dtboxFechaNac;
	}

	public void setDtboxFechaNac(Datebox dtboxFechaNac) {
		this.dtboxFechaNac = dtboxFechaNac;
	}
	
	public String getTxtNombreJug() {
		return txtNombreJug;
	}

	public void setTxtNombreJug(String txtNombreJug) {
		this.txtNombreJug = txtNombreJug;
	}

	public String getTxtApellidoJug() {
		return txtApellidoJug;
	}

	public void setTxtApellidoJug(String txtApellidoJug) {
		this.txtApellidoJug = txtApellidoJug;
	}

	public String getTxtEquipo() {
		return txtEquipo;
	}

	public Combobox getCmbCategoria() {
		return cmbCategoria;
	}

	public void setCmbCategoria(Combobox cmbCategoria) {
		this.cmbCategoria = cmbCategoria;
	}	
	
	public void setTxtEquipo(String txtEquipo) {
		this.txtEquipo = txtEquipo;
	}	
	
	public String getTxtDivisaNueva() {
		return txtDivisaNueva;
	}

	public void setTxtDivisaNueva(String txtDivisaNueva) {
		this.txtDivisaNueva = txtDivisaNueva;
	}
	
	public String getTxtLigaNueva() {
		return txtLigaNueva;
	}

	public void setTxtLigaNueva(String txtLigaNueva) {
		this.txtLigaNueva = txtLigaNueva;
	}
	
	public Combobox getCmbEstado() {
		return cmbEstado;
	}

	public void setCmbEstado(Combobox cmbEstado) {
		this.cmbEstado = cmbEstado;
	}

	public String getTxtMotivoCambio() {
		return txtMotivoCambio;
	}

	public void setTxtMotivoCambio(String txtMotivoCambio) {
		this.txtMotivoCambio = txtMotivoCambio;
	}

	public String getTxtObservaciones() {
		return txtObservaciones;
	}

	public void setTxtObservaciones(String txtObservaciones) {
		this.txtObservaciones = txtObservaciones;
	}		

	
	//Servicios, Procesos y Metodos	
	public void onCreate$win(ForwardEvent event){
		 binder = (AnnotateDataBinder) event.getTarget().getVariable("binder", false);  
	}
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		comp.setVariable("controller", this, false); // Hacemos visible el
													 // modelo para el databinder
	/*													
		categ =  new Categoria();
		int codigo = servicioCategoria.listar().size();
		categ.setCodigoCategoria(String.valueOf(codigo + 1));
		lista = new ArrayList<String>();
		categorias = servicioCategoria.listar(); */
	}
	
	
	public void onClick$btnCatalogoJugador(){
		new Util().crearVentana("Jugador/Vistas/buscarJugador.zul", null, null); 		
	}
	
	public void onClick$btnCatalogoRepresentante(){
		new Util().crearVentana("Jugador/Vistas/buscarRepresentante.zul", null, null); 		
	}
	
	public void onClick$btnSalir(){
		windregistrarPase.detach();
	}
		
	public void onClick$btnCancelar(){
				
	}
	
	public void onClick$btnExportar(){
/*		try{
			Runtime.getRuntime().exec("rundll32 url.dll, FileProtocolHandler c:\\reportepase.pdf ");
			System.out.println("Final");
			}
		catch(IOException e) {
			e.printStackTrace();
		}*/		
	}
	
	public void onSelect$Categoria(){
		
	}	
}



