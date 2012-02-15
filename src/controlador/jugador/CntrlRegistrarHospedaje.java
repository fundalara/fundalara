package controlador.jugador;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


import modelo.Categoria;
import modelo.Competencia;
import modelo.Familiar;
import modelo.Hospedaje;
import modelo.Representante;
import modelo.TipoCompetencia;


import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Image;
import org.zkoss.zul.Include;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import servicio.implementacion.ServicioCategoria;
import servicio.implementacion.ServicioCompetencia;
import servicio.implementacion.ServicioHospedaje;
import servicio.implementacion.ServicioFamiliar;
import servicio.implementacion.ServicioRepresentante;



import comun.FileLoader;
import comun.Ruta;
import comun.Util;

/**
 * Clase controladora de los eventos de la vista de igual nombre y manejo de los
 * servicios de datos para el registro de hospedaje
 * 
 * @author Edgar L
 * @author Erika O
 * @version 0.2 16/12/2011
 * 
 * */

public class CntrlRegistrarHospedaje extends GenericForwardComposer {

	private Window winRegistrarHospedaje;


	//Datos de la Competencia
	private Combobox cmbCompetencia;
	private Datebox dtboxFechaIni;
	private Datebox dtboxFechaFin;
	private Textbox txtLugar;
	private String rutasJug= Ruta.JUGADOR.getRutaVista();
	
	//Datos del Representate
	private Combobox cmbCedulaRep;
	private Intbox txtCedulaRep;
	private Textbox txtNombreRep;
	private Textbox txtApellidoRep;
	private Textbox txtDireccionRep;

	//Botones
	private Button btnCatalogoRepresentante;
	private Button btnAgregar;
	private Button btnQuitar;
	private Button btnGuardar;
	private Button btnCancelar;
	private Button btnSalir;

	//Binder
	private AnnotateDataBinder binder;
	
	//Servicios
	
	private ServicioCompetencia servicioCompetencia;
	private ServicioRepresentante servicioRepresentante;


	//Modelo
	private Categoria categoria = new Categoria();
	private Competencia competencia = new Competencia();
	private Familiar familiar = new Familiar();
	private List<Competencia> listCompetencias = new ArrayList<Competencia>();
	private Representante representante = new Representante ();
 
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		comp.setVariable("controller", this, false); // Hacemos visible el
														// modelo para el
														// databinder
	}
	//Set y Get
		
	public Combobox getCmbCompetencia() {
		return cmbCompetencia;
	}
	
	public void setCmbCompetencia(Combobox cmbCompetencia) {
		this.cmbCompetencia = cmbCompetencia;
	}
	
	public Datebox getDtboxFechaIni() {
		return dtboxFechaIni;
	}
	
	public void setDtboxFechaIni(Datebox dtboxFechaIni) {
		this.dtboxFechaIni = dtboxFechaIni;
	}
	
	public Datebox getDtboxFechaFin() {
		return dtboxFechaFin;
	}
	
	public void setDtboxFechaFin(Datebox dtboxFechaFin) {
		this.dtboxFechaFin = dtboxFechaFin;
	}
	
	public Textbox getTxtLugar() {
		return txtLugar;
	}
	
	public void setTxtLugar(Textbox txtLugar) {
		this.txtLugar = txtLugar;
	}
	
	public Combobox getCmbCedulaRep() {
		return cmbCedulaRep;
	}
	
	public void setCmbCedulaRep(Combobox cmbCedulaRep) {
		this.cmbCedulaRep = cmbCedulaRep;
	}
	
	public Intbox getTxtCedulaRep() {
		return txtCedulaRep;
	}
	
	public void setTxtCedulaRep(Intbox txtCedulaRep) {
		this.txtCedulaRep = txtCedulaRep;
	}
	
	public Textbox getTxtNombreRep() {
		return txtNombreRep;
	}
	
	public void setTxtNombreRep(Textbox txtNombreRep) {
		this.txtNombreRep = txtNombreRep;
	}
	
	public Textbox getTxtApellidoRep() {
		return txtApellidoRep;
	}
	
	public void setTxtApellidoRep(Textbox txtApellidoRep) {
		this.txtApellidoRep = txtApellidoRep;
	}
	
	public Textbox getTxtDireccionRep() {
		return txtDireccionRep;
	}
	
	public void setTxtDireccionRep(Textbox txtDireccionRep) {
		this.txtDireccionRep = txtDireccionRep;
	}
	
	public Competencia getCompetencia() {
		return competencia;
	}
	
	public void setCompetencia(Competencia competencia) {
		this.competencia = competencia;
	}
	
	
	//Metodos
	
	public void onCreate$win(ForwardEvent event){
		 binder = (AnnotateDataBinder) event.getTarget().getVariable("binder", false);  
	}
	
	public List<Competencia> getCompetencias() {
		return servicioCompetencia.listar();
	}
	
	public void onChange$cmbCompetencia(){
		
		competencia = (Competencia) cmbCompetencia.getSelectedItem().getValue();
		if (cmbCompetencia.getSelectedIndex() >=0) {
			for (int i = 0; i < listCompetencias.size(); i++) {
				if(listCompetencias.get(i).getNombre().equals(cmbCompetencia.getSelectedItem().getLabel())) {
					competencia.getFechaInicio();
					competencia.getFechaFin();
					competencia.getDatoBasicoByCodigoEstado();
				}
			}
		}
		binder.loadAll(); // actualiza para mostrar los cambios
	}
			
		
	
	public void onClick$btnCatalogoRepresentante() {
		representante = servicioRepresentante.listar().get(0);
			
	}
	
	
	public void onClick$btnAgregar() {
	
	}
	
	public void onClick$btnQuitar() {
	
	}
	
	/*public void onClick$btnGuardar() {
			  hospedaje.setEstatus('A');
			  Date fecha = new Date();
			  java.text.SimpleDateFormat formato = new java.text.SimpleDateFormat("dd/MM/yyyy");
			  String cadenaFecha = formato.format(fecha);
			  
			  hospedaje.setFechaIngreso(fecha);
			  ServicioHospedaje.agregar(hospedaje);
			  hospedaje = new modelo.Hospedaje();
			  binder.loadAll();
		  }*/
	
	
	public void onClick$btnCancelar() {
	
	}
	
	public void onClick$btnSalir() {
	
		winRegistrarHospedaje.detach();
	}

}
