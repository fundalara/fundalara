package seguridad;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


import javax.persistence.AttributeOverrides;
import javax.xml.soap.Text;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import modelo.Categoria;
import modelo.Competencia;
import modelo.Familiar;
import modelo.Representante;
import modelo.Rol;
import modelo.RolGrupo;
import modelo.RolGrupoId;
import modelo.SeguridadFuncional;
import modelo.Usuario;

import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Image;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import org.zkoss.lang.Strings;
import org.zkoss.lang.Objects;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.*;

import servicio.implementacion.ServicioCompetencia;
import servicio.implementacion.ServicioRepresentante;
import servicio.implementacion.ServicioSeguridadFuncional;
import servicio.implementacion.ServicioUsuario;
import comun.FileLoader;
import comun.Ruta;
import comun.Util;

public class CntrlGestionarUsuario extends GenericForwardComposer {
	
	private Window winGestionarUsuario;


	//Datos del Usuario
	private Textbox txtCedula;
	private Textbox txtNombre;
	private Textbox txtApellido;
	private Textbox txtOcupacion;
	private Image imgUsuario;
	
	
	
	private String rutasJug= Ruta.JUGADOR.getRutaVista();
	
	//Datos de Acceso
	private Textbox txtContra;
	private Textbox txtConfirmContra;

	//Botones
	private Button btnGuardar;
	private Button btnCancelar;
	private Button btnSalir;
	private Button btnCatalogoUsuario;

	//Binder
	private AnnotateDataBinder binder;
	
	//Servicios
	
	private ServicioUsuario servicioUsuario;
	private ServicioSeguridadFuncional servicioSeguridadFuncional;


	//Modelo
	private Usuario usuario = new Usuario();
	private SeguridadFuncional seguridadFuncional = new SeguridadFuncional();
	private Rol rol = new Rol();
	private RolGrupo rolGrupo = new RolGrupo();
	private RolGrupoId rolGrupoId = new RolGrupoId();
 
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		comp.setVariable("controller", this, false); // Hacemos visible el
														// modelo para el
														// databinder
	}
	
	
	
	//GET AND SET

	public Textbox getTxtCedula() {
		return txtCedula;
	}


	public void setTxtCedula(Textbox txtCedula) {
		this.txtCedula = txtCedula;
	}


	public Textbox getTxtApellido() {
		return txtApellido;
	}


	public void setTxtApellido(Textbox txtApellido) {
		this.txtApellido = txtApellido;
	}


	public Textbox getTxtContra() {
		return txtContra;
	}


	public void setTxtContra(Textbox txtContra) {
		this.txtContra = txtContra;
	}


	public Textbox getTxtConfirmContra() {
		return txtConfirmContra;
	}


	public void setTxtConfirmContra(Textbox txtConfirmContra) {
		this.txtConfirmContra = txtConfirmContra;
	}
	
	
    // METODOS
	
	
	public void onCreate$win(ForwardEvent event){
		 binder = (AnnotateDataBinder) event.getTarget().getVariable("binder", false);  
	}
	
	
	public void onClick$btnSalir() {
		
		winGestionarUsuario.detach();
	}
	
	
}


