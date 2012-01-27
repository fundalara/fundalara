/**
 * Clase controladora de los eventos de la vista de igual nombre.
 * 
 * @author Glendy Oliveros
 * @author Aimee Monsalve
 * @author Greisy Rodriguez
 * @author Alberto Perozo
 * @author Edgar Luzardo
 * 
 * @version 0.3 12/12/2011
 *
 * */

package controlador.jugador;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


import javax.persistence.AttributeOverrides;


import java.util.Date;
import java.util.List;

//import modelo.TipoInstitucion;

import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Image;
import org.zkoss.zul.Include;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.api.Tab;

import org.zkoss.lang.Strings;
import org.zkoss.lang.Objects;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.*;
import comun.FileLoader;
import comun.Util;

import servicio.implementacion.ServicioAfeccionJugador;
import servicio.implementacion.ServicioCategoria;
/*import servicio.implementacion.ServicioCodigoArea;
import servicio.implementacion.ServicioCodigoCelular;*/
//import servicio.implementacion.ServicioCurso;
import servicio.implementacion.ServicioDatoAcademico;
import servicio.implementacion.ServicioDatoDeportivo;
import servicio.implementacion.ServicioDatoMedico;
import servicio.implementacion.ServicioDatoSocial;
//import servicio.implementacion.ServicioEstadoVenezuela;
import servicio.implementacion.ServicioInstitucion;
import servicio.implementacion.ServicioJugador;
import servicio.implementacion.ServicioMedico;
/*import servicio.implementacion.ServicioMunicipio;
import servicio.implementacion.ServicioParroquia;
import servicio.implementacion.ServicioTipoActividadSocial;
import servicio.implementacion.ServicioTipoAfeccion;
import servicio.implementacion.ServicioTipoInstitucion;

import modelo.EstadoVenezuela;
import modelo.Municipio;
import modelo.Parroquia;
*/
public class ActualizarJugadorCtrl extends GenericForwardComposer {
	
	private Include incCuerpo;
	//Perfil Jugador
	private Combobox cmbNacionalidad=null;
	private Textbox txtCedula=null;
	private Button btnCatalogoJugador=null;
	private Textbox txtPrimerNombre=null;
	private Textbox txtSegundoNombre=null;
	private Textbox txtPrimerApellido=null;
	private Textbox txtSegundoApellido=null;
	private Button btnCatalogoMedico=null;
	private Image img;
	
	//Panel Detalle del Jugador
	//Pestaña Personales
	//Datos de Residencia
	private Combobox cmbEstado=null;
	private Combobox cmbMunicipio=null;
	private Combobox cmbParroquia=null;
	private Textbox txtDireccionHabitacion;
	
	//Datos de Contacto
	private Combobox cmbCodigoArea=null;
	private Intbox txtTelefonoHabitacion=null;
	private Combobox cmbTelefonoCelular=null;
	private Textbox txtTelefonoCelular=null;
	private Textbox txtCorreoElectronico=null;
	private Textbox txtTwitter=null;
	private Textbox txtFacebook=null;
	
	//Pestaña Medicos

	private Combobox cmbTipoActualizacion=null;
	
	//Pestaña Academicos
	private Textbox txtInstitucion=null;
	private Button btnCatalogoInstitucion=null;
	private Combobox cmbCurso=null;
	private Combobox cmbAnnoEscolar=null;
	private Grid gridDetalleInstitucion=null;
	private Button btnAgregarInstitucion=null;
	private Button btnQuitarInstitucion=null;

	//Pestaña Social
	private Textbox txtInstitucionSocial=null;
	private Button btnCatalogoInstitucionSocial=null;
	private Combobox cmbActividad=null;
	private Textbox txtHoras=null;
	private Datebox dtboxFechaInicioAct=null;
	private Listbox listDetalleActividad=null;
	private Button btnAgregarActividad=null;
	private Button btnEditarActividad=null;
	private Button btnQuitarActividad=null;
	
	//Pestaña Conducta
	private Combobox cmbMotivo=null;
	private Grid gridMotivo=null;
	private Button btnAgregarMotivo=null;
	private Button btnEditarMotivo=null;
	private Button btnQuitarMotivo=null;
	private Combobox cmbTipoSancion=null;
	private Label lbCantidad=null;
	private Datebox dtboxFechaInicioSancion=null;
	private Grid gridDocumentos=null;
	private Button btnSubirDocumentoInf=null;
	private Button btnSubirDocumentoMen=null;
	private Textbox txtObservacion=null;

	//Pestaña Deportivos
	private Decimalbox dcboxPeso=null;
	private Decimalbox dcboxAltura=null;
	private Combobox cmbBrazoLanzar=null;
	private Combobox cmbPosicionBateo=null;
	private Combobox cmbCamisa=null;
	private Combobox cmbPantalon=null;
	private Combobox cmbCalzado=null;
	
	private Button btnGuardar;
	private Button btnCancelar;
	private Button btnSalir;
	
	
/*	ServicioEstadoVenezuela servicioEstadoVenezuela;
	ServicioMunicipio servicioMunicipio;
	ServicioParroquia servicioParroquia;
	TipoInstitucion tipoInstitucion;
	List<TipoInstitucion> tipoInst;
	List<String> lista;
	List<Parroquia> parroquias;
	List<EstadoVenezuela> estados;
	List<Municipio> municipios;
	AnnotateDataBinder binder;
	
	public void onCreate$win(ForwardEvent event){
		 binder = (AnnotateDataBinder) event.getTarget().getVariable("binder", false);  
 }
	

	public List<EstadoVenezuela> getEstadoVenezuela() {
		return servicioEstadoVenezuela.listar();
	}

	
	public Combobox getCmbNacionalidad() {
		return cmbNacionalidad;
	}

	public void setCmbNacionalidad(Combobox cmbNacionalidad) {
		this.cmbNacionalidad = cmbNacionalidad;
	}

	public Textbox getTxtCedula() {
		return txtCedula;
	}

	public void setTxtCedula(Textbox txtCedula) {
		this.txtCedula = txtCedula;
	}

	public Button getBtnCatalogoJugador() {
		return btnCatalogoJugador;
	}

	public void setBtnCatalogoJugador(Button btnCatalogoJugador) {
		this.btnCatalogoJugador = btnCatalogoJugador;
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

	public Button getBtnCatalogoMedico() {
		return btnCatalogoMedico;
	}

	public void setBtnCatalogoMedico(Button btnCatalogoMedico) {
		this.btnCatalogoMedico = btnCatalogoMedico;
	}

	public Image getImg() {
		return img;
	}

	public void setImg(Image img) {
		this.img = img;
	}

	public Combobox getCmbEstado() {
		return cmbEstado;
	}

	public void setCmbEstado(Combobox cmbEstado) {
		this.cmbEstado = cmbEstado;
	}

	public Combobox getCmbMunicipio() {
		return cmbMunicipio;
	}

	public void setCmbMunicipio(Combobox cmbMunicipio) {
		this.cmbMunicipio = cmbMunicipio;
	}

	public Combobox getCmbParroquia() {
		return cmbParroquia;
	}

	public void setCmbParroquia(Combobox cmbParroquia) {
		this.cmbParroquia = cmbParroquia;
	}

	public Textbox getTxtDireccionHabitacion() {
		return txtDireccionHabitacion;
	}

	public void setTxtDireccionHabitacion(Textbox txtDireccionHabitacion) {
		this.txtDireccionHabitacion = txtDireccionHabitacion;
	}

	public Combobox getCmbCodigoArea() {
		return cmbCodigoArea;
	}

	public void setCmbCodigoArea(Combobox cmbCodigoArea) {
		this.cmbCodigoArea = cmbCodigoArea;
	}

	public Intbox getTxtTelefonoHabitacion() {
		return txtTelefonoHabitacion;
	}

	public void setTxtTelefonoHabitacion(Intbox txtTelefonoHabitacion) {
		this.txtTelefonoHabitacion = txtTelefonoHabitacion;
	}

	public Combobox getCmbTelefonoCelular() {
		return cmbTelefonoCelular;
	}

	public void setCmbTelefonoCelular(Combobox cmbTelefonoCelular) {
		this.cmbTelefonoCelular = cmbTelefonoCelular;
	}

	public Textbox getTxtTelefonoCelular() {
		return txtTelefonoCelular;
	}

	public void setTxtTelefonoCelular(Textbox txtTelefonoCelular) {
		this.txtTelefonoCelular = txtTelefonoCelular;
	}

	public Textbox getTxtCorreoElectronico() {
		return txtCorreoElectronico;
	}

	public void setTxtCorreoElectronico(Textbox txtCorreoElectronico) {
		this.txtCorreoElectronico = txtCorreoElectronico;
	}

	public Textbox getTxtTwitter() {
		return txtTwitter;
	}

	public void setTxtTwitter(Textbox txtTwitter) {
		this.txtTwitter = txtTwitter;
	}

	public Textbox getTxtFacebook() {
		return txtFacebook;
	}

	public void setTxtFacebook(Textbox txtFacebook) {
		this.txtFacebook = txtFacebook;
	}

	public Combobox getCmbTipoActualizacion() {
		return cmbTipoActualizacion;
	}

	public void setCmbTipoActualizacion(Combobox cmbTipoActualizacion) {
		this.cmbTipoActualizacion = cmbTipoActualizacion;
	}

	public Textbox getTxtInstitucion() {
		return txtInstitucion;
	}

	public void setTxtInstitucion(Textbox txtInstitucion) {
		this.txtInstitucion = txtInstitucion;
	}

	public Button getBtnCatalogoInstitucion() {
		return btnCatalogoInstitucion;
	}

	public void setBtnCatalogoInstitucion(Button btnCatalogoInstitucion) {
		this.btnCatalogoInstitucion = btnCatalogoInstitucion;
	}

	public Combobox getCmbCurso() {
		return cmbCurso;
	}

	public void setCmbCurso(Combobox cmbCurso) {
		this.cmbCurso = cmbCurso;
	}

	public Combobox getCmbAnnoEscolar() {
		return cmbAnnoEscolar;
	}

	public void setCmbAnnoEscolar(Combobox cmbAnnoEscolar) {
		this.cmbAnnoEscolar = cmbAnnoEscolar;
	}

	public Grid getGridDetalleInstitucion() {
		return gridDetalleInstitucion;
	}

	public void setGridDetalleInstitucion(Grid gridDetalleInstitucion) {
		this.gridDetalleInstitucion = gridDetalleInstitucion;
	}

	public Button getBtnAgregarInstitucion() {
		return btnAgregarInstitucion;
	}

	public void setBtnAgregarInstitucion(Button btnAgregarInstitucion) {
		this.btnAgregarInstitucion = btnAgregarInstitucion;
	}

	public Button getBtnQuitarInstitucion() {
		return btnQuitarInstitucion;
	}

	public void setBtnQuitarInstitucion(Button btnQuitarInstitucion) {
		this.btnQuitarInstitucion = btnQuitarInstitucion;
	}

	public Textbox getTxtInstitucionSocial() {
		return txtInstitucionSocial;
	}

	public void setTxtInstitucionSocial(Textbox txtInstitucionSocial) {
		this.txtInstitucionSocial = txtInstitucionSocial;
	}

	public Button getBtnCatalogoInstitucionSocial() {
		return btnCatalogoInstitucionSocial;
	}

	public void setBtnCatalogoInstitucionSocial(Button btnCatalogoInstitucionSocial) {
		this.btnCatalogoInstitucionSocial = btnCatalogoInstitucionSocial;
	}

	public Combobox getCmbActividad() {
		return cmbActividad;
	}

	public void setCmbActividad(Combobox cmbActividad) {
		this.cmbActividad = cmbActividad;
	}

	public Textbox getTxtHoras() {
		return txtHoras;
	}

	public void setTxtHoras(Textbox txtHoras) {
		this.txtHoras = txtHoras;
	}

	public Datebox getDtboxFechaInicioAct() {
		return dtboxFechaInicioAct;
	}

	public void setDtboxFechaInicioAct(Datebox dtboxFechaInicioAct) {
		this.dtboxFechaInicioAct = dtboxFechaInicioAct;
	}

	public Listbox getListDetalleActividad() {
		return listDetalleActividad;
	}

	public void setListDetalleActividad(Listbox listDetalleActividad) {
		this.listDetalleActividad = listDetalleActividad;
	}

	public Button getBtnAgregarActividad() {
		return btnAgregarActividad;
	}

	public void setBtnAgregarActividad(Button btnAgregarActividad) {
		this.btnAgregarActividad = btnAgregarActividad;
	}

	public Button getBtnEditarActividad() {
		return btnEditarActividad;
	}

	public void setBtnEditarActividad(Button btnEditarActividad) {
		this.btnEditarActividad = btnEditarActividad;
	}

	public Button getBtnQuitarActividad() {
		return btnQuitarActividad;
	}

	public void setBtnQuitarActividad(Button btnQuitarActividad) {
		this.btnQuitarActividad = btnQuitarActividad;
	}

	public Combobox getCmbMotivo() {
		return cmbMotivo;
	}

	public void setCmbMotivo(Combobox cmbMotivo) {
		this.cmbMotivo = cmbMotivo;
	}

	public Grid getGridMotivo() {
		return gridMotivo;
	}

	public void setGridMotivo(Grid gridMotivo) {
		this.gridMotivo = gridMotivo;
	}

	public Button getBtnAgregarMotivo() {
		return btnAgregarMotivo;
	}

	public void setBtnAgregarMotivo(Button btnAgregarMotivo) {
		this.btnAgregarMotivo = btnAgregarMotivo;
	}

	public Button getBtnEditarMotivo() {
		return btnEditarMotivo;
	}

	public void setBtnEditarMotivo(Button btnEditarMotivo) {
		this.btnEditarMotivo = btnEditarMotivo;
	}

	public Button getBtnQuitarMotivo() {
		return btnQuitarMotivo;
	}

	public void setBtnQuitarMotivo(Button btnQuitarMotivo) {
		this.btnQuitarMotivo = btnQuitarMotivo;
	}

	public Combobox getCmbTipoSancion() {
		return cmbTipoSancion;
	}

	public void setCmbTipoSancion(Combobox cmbTipoSancion) {
		this.cmbTipoSancion = cmbTipoSancion;
	}

	public Label getLbCantidad() {
		return lbCantidad;
	}

	public void setLbCantidad(Label lbCantidad) {
		this.lbCantidad = lbCantidad;
	}

	public Datebox getDtboxFechaInicioSancion() {
		return dtboxFechaInicioSancion;
	}

	public void setDtboxFechaInicioSancion(Datebox dtboxFechaInicioSancion) {
		this.dtboxFechaInicioSancion = dtboxFechaInicioSancion;
	}

	public Grid getGridDocumentos() {
		return gridDocumentos;
	}

	public void setGridDocumentos(Grid gridDocumentos) {
		this.gridDocumentos = gridDocumentos;
	}

	public Button getBtnSubirDocumentoInf() {
		return btnSubirDocumentoInf;
	}

	public void setBtnSubirDocumentoInf(Button btnSubirDocumentoInf) {
		this.btnSubirDocumentoInf = btnSubirDocumentoInf;
	}

	public Button getBtnSubirDocumentoMen() {
		return btnSubirDocumentoMen;
	}

	public void setBtnSubirDocumentoMen(Button btnSubirDocumentoMen) {
		this.btnSubirDocumentoMen = btnSubirDocumentoMen;
	}

	public Textbox getTxtObservacion() {
		return txtObservacion;
	}

	public void setTxtObservacion(Textbox txtObservacion) {
		this.txtObservacion = txtObservacion;
	}

	public Decimalbox getDcboxPeso() {
		return dcboxPeso;
	}

	public void setDcboxPeso(Decimalbox dcboxPeso) {
		this.dcboxPeso = dcboxPeso;
	}

	public Decimalbox getDcboxAltura() {
		return dcboxAltura;
	}

	public void setDcboxAltura(Decimalbox dcboxAltura) {
		this.dcboxAltura = dcboxAltura;
	}

	public Combobox getcmbBrazoLanzar() {
		return cmbBrazoLanzar;
	}

	public void setcmbBrazoLanzar(Combobox cmbBrazoLanzar) {
		this.cmbBrazoLanzar = cmbBrazoLanzar;
	}

	public Combobox getcmbPosicionBateo() {
		return cmbPosicionBateo;
	}

	public void setcmbPosicionBateo(Combobox cmbPosicionBateo) {
		this.cmbPosicionBateo = cmbPosicionBateo;
	}

	public Combobox getCmbCamisa() {
		return cmbCamisa;
	}

	public void setCmbCamisa(Combobox cmbCamisa) {
		this.cmbCamisa = cmbCamisa;
	}

	public Combobox getCmbPantalon() {
		return cmbPantalon;
	}

	public void setCmbPantalon(Combobox cmbPantalon) {
		this.cmbPantalon = cmbPantalon;
	}

	public Combobox getCmbCalzado() {
		return cmbCalzado;
	}

	public void setCmbCalzado(Combobox cmbCalzado) {
		this.cmbCalzado = cmbCalzado;
	}

	public Button getBtnGuardar() {
		return btnGuardar;
	}

	public void setBtnGuardar(Button btnGuardar) {
		this.btnGuardar = btnGuardar;
	}
		
	public Button getBtnCancelar() {
		return btnCancelar;
	}

	public void setBtnCancelar(Button btnCancelar) {
		this.btnCancelar = btnCancelar;
	}

	public Button getBtnSalir() {
		return btnSalir;
	}

	public void setBtnSalir(Button btnSalir) {
		this.btnSalir = btnSalir;
	}

	
	public void onClick$btnLoad() {
		new FileLoader().cargarImagen(img);
		
	}
	*/
	public void onChange$cmbTipoActualizacion() {
		String src = "";
		String valor = cmbTipoActualizacion.getSelectedItem().getValue().toString();
		Util enlace = new Util();
		if (valor.equals("1")) {
			src = "Jugador/Vistas/actualizarDatosMedicos.zul";
		} else {
			src = "Jugador/Vistas/actualizarAfeccion.zul";
		}
		enlace.insertarContenido(incCuerpo, src);
	  }

	public void validar() {
		if (cmbTipoSancion.getSelectedItem().getValue().toString()
				.equals("Juegos")) {
			lbCantidad.setVisible(true);
			lbCantidad.setValue("Juegos");
		} else {
			if (cmbTipoSancion.getSelectedItem().getValue().equals("Dias")) {
				lbCantidad.setVisible(true);
				lbCantidad.setValue("Días");
			}
		}
	}
	
	public void onClick$btnCatalogoJugador() {
		new Util().crearVentana("Jugador/Vistas/buscarJugador.zul",
				null, null);
	}	
	
	
	public void onClick$btnCatalogoInstitucion() {
		new Util().crearVentana("Jugador/Vistas/buscarInstitucion.zul",
				null, null);
	}

	public void onClick$btnCatalogoInstitucionSocial() {
		new Util().crearVentana("Jugador/Vistas/buscarInstitucion.zul", null,
				null);
	}
/*	
	public void onClick$btnGuardar() {
		if (validarVacios() == false){
			try {
				Messagebox.show(
						"Se ha actualizado con exito",
						"Fundalara", Messagebox.OK, Messagebox.EXCLAMATION);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}			
		}
			
		else{	
			try {
				Messagebox.show(
						"Existen  campos obligatorios, por favor verifique.",
						"Fundalara", Messagebox.OK, Messagebox.EXCLAMATION);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public boolean validarVacios(){
		boolean error=false;
       	if(this.getTxtPrimerNombre().getValue() == "")
        	error = true;
		if(this.getTxtPrimerApellido().getValue() == "")
        	error = true;
		if(this.getCmbEstado().getSelectedIndex() == -1)
			error = true;
		if(this.getCmbMunicipio().getSelectedIndex() == -1)
			error = true;
		if(this.getCmbParroquia().getSelectedIndex() == -1)
			error = true;
		if (this.getTxtDireccionHabitacion().getValue() == "")
			error = true;
		
        
		return error;
	}
*/
}


