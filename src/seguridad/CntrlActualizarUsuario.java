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
import java.util.EventListener;
import java.util.List;

import modelo.Categoria;
import modelo.Competencia;
import modelo.Familiar;
import modelo.PersonalCargo;
import modelo.Representante;
import modelo.Rol;
import modelo.RolGrupo;
import modelo.RolGrupoId;
import modelo.SeguridadFuncional;
import modelo.Usuario;

import org.springframework.security.core.Authentication;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Image;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import org.zkoss.image.AImage;
import org.zkoss.lang.Strings;
import org.zkoss.lang.Objects;
import org.zkoss.spring.security.SecurityUtil;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.*;

import servicio.implementacion.ServicioCompetencia;
import servicio.implementacion.ServicioPersonalCargo;
import servicio.implementacion.ServicioRepresentante;
import servicio.implementacion.ServicioSeguridadFuncional;
import servicio.implementacion.ServicioUsuario;
import comun.FileLoader;
import comun.Ruta;
import comun.Util;
import comun.Mensaje;


public class CntrlActualizarUsuario extends GenericForwardComposer {
	
	private Window winActualizarUsuario;


	//Datos del Usuario
	private Textbox txtCedula;
	private Textbox txtNombre;
	private Textbox txtApellido;
	private Textbox txtOcupacion;
	private Textbox txtAcceso;
	private Image imgUsuario;
	String nombres;
	String apellidos;
	String usuarios;
	String contrasena;
	Authentication authentication;

	
	
	private String rutasJug= Ruta.JUGADOR.getRutaVista();
	
	//Datos de Acceso
	private Textbox txtContra;
	private Textbox txtConfirmContra;

	//Botones
	private Button btnGuardar;
	private Button btnCancelar;
	private Button btnSalir;
	
	Component formulario;


	//Binder
	private AnnotateDataBinder binder;
	
	//Servicios
	
	private ServicioUsuario servicioUsuario;
	private ServicioSeguridadFuncional servicioSeguridadFuncional;
	private ServicioPersonalCargo servicioPersonalCargo;


	//Modelo
	private Usuario usuario = new Usuario();
	private PersonalCargo personalCargo = new PersonalCargo();
	private SeguridadFuncional seguridadFuncional = new SeguridadFuncional();
//	private Rol rol = new Rol();
//	private RolGrupo rolGrupo = new RolGrupo();
//	private RolGrupoId rolGrupoId = new RolGrupoId();
 
	
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		comp.setVariable("controller", this, false); // Hacemos visible el
														// modelo para el
														// databinder
		formulario = comp;
		cargarDatos();

		
	}
	public void cargarDatos() {
		btnGuardar.setImage("/Recursos/Imagenes/guardar.ico");
		btnSalir.setImage("/Recursos/Imagenes/salir.png");
		
		imgUsuario.setSrc("/Recursos/Imagenes/noFoto.jpg");
		usuarios = SecurityUtil.getAuthentication().getName();
	    txtAcceso.setValue(usuarios);
	    
	    usuario = servicioUsuario.buscarPorNombre(usuarios);
	    txtCedula.setValue(usuario.getCedulaRif());
	    txtContra.setValue(usuario.getPassword()); 

	    
	    nombres = usuario.getPersonal().getPersonaNatural().getPrimerNombre() + (usuario.getPersonal().getPersonaNatural().getSegundoNombre() == null? "": " " +usuario.getPersonal().getPersonaNatural().getSegundoNombre());
	    txtNombre.setValue(nombres);
	    apellidos = usuario.getPersonal().getPersonaNatural().getPrimerApellido()+ (usuario.getPersonal().getPersonaNatural().getSegundoApellido() == null? "" : " " + usuario.getPersonal().getPersonaNatural().getSegundoApellido());
	    txtApellido.setValue(apellidos);
	    txtConfirmContra.setValue(usuario.getPassword());
	    
	    personalCargo = servicioPersonalCargo.buscarCargoActual(usuario.getPersonal());
	    txtOcupacion.setValue(personalCargo.getDatoBasico().getNombre());
	    byte[] foto = usuario.getPersonal().getPersonaNatural().getFoto();
		if (foto != null) {
			try {
				AImage aImage = new AImage("foto.jpg", foto);
				imgUsuario.setContent(aImage);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void onClick$btnGuardar(){
	    if (txtContra.getValue()!= "" && txtConfirmContra.getValue()!= ""){
	    	String contra, sena;
	    	contra = txtContra.getValue();
	    	sena = txtConfirmContra.getValue();
	    	
		 System.out.println(txtContra.getValue());
		 System.out.println(txtConfirmContra.getValue());
	    	if (contra.equals(sena)){
	    		System.out.println("fuck you");
			  usuario.setPassword(txtContra.getValue());
			  servicioUsuario.actualizar(usuario);
	    	}
	    	else {
				Mensaje.mostrarMensaje(
						"Las Contraseñas no COINCIDEN",
						Mensaje.INFORMACION, Messagebox.INFORMATION);
			
	    	}
	    }
	    else {
	    	Mensaje.mostrarMensaje(
					"Debe introducir una Contraseña válida ",
					Mensaje.INFORMACION, Messagebox.INFORMATION);

	    }
	    
	    Mensaje.mostrarMensaje(
				"Los datos del Usuario se han guardado ",
				Mensaje.INFORMACION, Messagebox.INFORMATION);
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
				winActualizarUsuario.detach();	
	}
	
}


