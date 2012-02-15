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
import modelo.Grupo;
import modelo.GrupoUsuario;
import modelo.Jugador;
import modelo.Persona;
import modelo.PersonalCargo;
import modelo.Representante;
import modelo.Rol;
import modelo.RolGrupo;
import modelo.RolGrupoId;
import modelo.SeguridadFuncional;
import modelo.Usuario;
import modelo.Personal;

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
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.*;

import servicio.implementacion.ServicioCompetencia;
import servicio.implementacion.ServicioGrupo;
import servicio.implementacion.ServicioGrupoUsuario;
import servicio.implementacion.ServicioPersona;
import servicio.implementacion.ServicioPersonal;
import servicio.implementacion.ServicioPersonalCargo;
import servicio.implementacion.ServicioRepresentante;
import servicio.implementacion.ServicioSeguridadFuncional;
import servicio.implementacion.ServicioUsuario;
import comun.FileLoader;
import comun.Mensaje;
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
	private Textbox txtNombreUsuario;
	Component formulario;

	//Botones
	private Button btnGuardar;
	private Button btnCancelar;
	private Button btnSalir;
	private Button btnBuscar;
	private Button btnAgregar;
	private Button btnBuscarUsuario;
	private Button btnQuitar;
	private Combobox cmbGrupos;

	//Binder
	private AnnotateDataBinder binder;
	
	//Servicios
	
	private ServicioUsuario servicioUsuario;
	private ServicioSeguridadFuncional servicioSeguridadFuncional;
	private ServicioPersonal servicioPersonal;
	private ServicioPersona servicioPersona;
	private ServicioPersonalCargo servicioPersonalCargo;
	private ServicioGrupo servicioGrupo;
	private ServicioGrupoUsuario servicioGrupoUsuario;

	//Modelo
	private Persona persona = new Persona();
	private Personal personal = new Personal();
	
	private GrupoUsuario grupoUsuario = new GrupoUsuario();
	private PersonalCargo personalCargo = new PersonalCargo();
	private Usuario usuario = new Usuario();
	private SeguridadFuncional seguridadFuncional = new SeguridadFuncional();
	private Grupo grupo = new Grupo();
	private Rol rol = new Rol();
	private RolGrupo rolGrupo = new RolGrupo();
	private RolGrupoId rolGrupoId = new RolGrupoId();
	private int auxiliar = 0;
	Listbox listGrupos, listGruposAsignados;
	List<Grupo> listaGrupos = new ArrayList<Grupo>();
	List<Grupo> listaGruposAsignados = new ArrayList<Grupo>();
	List<GrupoUsuario> listaGruposUsuario = new ArrayList<GrupoUsuario>();
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		comp.setVariable("controller", this, false); // Hacemos visible el
														// modelo para el
														// databinder
		formulario=comp;
		imgUsuario.setSrc("/Recursos/Imagenes/nofoto.jpg");
		
	}
	
	public void onClick$btnGuardar(){
		if(auxiliar==0){
		String name = txtNombreUsuario.getRawText();

		if(name != ""){
				usuario = servicioUsuario.buscarPorNombre(name);
				
				if (usuario==null){
					String contra, sena;
					
			    	contra = txtContra.getValue();
			    	sena = txtConfirmContra.getValue();
			    	
					   if (contra != "" && sena!= ""){
					    	
					    	if (contra.equals(sena)){
					    		
					    		usuario = new Usuario();
					    		System.out.println("1");
					    		usuario.setCedulaRif(persona.getCedulaRif());
					    		System.out.println("2");
					    		usuario.setNick(txtNombreUsuario.getValue());
					    		System.out.println("3");
					    		usuario.setPassword(txtContra.getValue());
					    		System.out.println("4");
					    		usuario.setPersonal(persona.getPersonaNatural().getPersonal());
					    		System.out.println("5");
					    		usuario.setEstatus('A');
					    		System.out.println("6");
					    		servicioUsuario.guardar(usuario);
					    		System.out.println("7");
					    		
					    		Mensaje.mostrarMensaje(
					    				"Los datos del Usuario se han guardado ",
					    				Mensaje.INFORMACION, Messagebox.INFORMATION);
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
					
				}
				else{
					Mensaje.mostrarMensaje("Nombre de Usuario ya existente ingrese otro.",Mensaje.INFORMACION ,Messagebox.EXCLAMATION);
					txtNombreUsuario.setRawValue("");
				}
		}
	
			else{
				Mensaje.mostrarMensaje("Debe Ingresar un Usuario.",Mensaje.INFORMACION ,Messagebox.EXCLAMATION);
			
		}
		}
		else{
			  if (txtContra.getValue()!= "" && txtConfirmContra.getValue()!= ""){
			    	String contra, sena;
			    	contra = txtContra.getValue();
			    	sena = txtConfirmContra.getValue();
			    	
			    	if (contra.equals(sena)){
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
	}	
	 
	public void onClick$btnCancelar(){
		limpiarcampos();
	}
	
	public void onClick$btnAgregar(){
		if( listGrupos.getSelectedCount() != 0){
			grupo = listaGrupos.get(listGrupos.getSelectedIndex());
			
			if (!listaGruposAsignados.contains(grupo)){
			
			listaGruposAsignados.add(grupo);
			binder.loadComponent(listGruposAsignados);
			
			}
			else{
				Mensaje.mostrarMensaje("Grupo ya Asignado.",Mensaje.INFORMACION ,Messagebox.EXCLAMATION);
				
			}
		}
		else{
			Mensaje.mostrarMensaje("Seleccione un Grupo a Asignar",Mensaje.INFORMACION ,Messagebox.EXCLAMATION);
		
	}
		
	}
	
	public void onClick$btnQuitar(){
		if( listGruposAsignados.getSelectedCount() != 0){
					
			listaGruposAsignados.remove(listGruposAsignados.getSelectedIndex());
			binder.loadComponent(listGruposAsignados);
			
		
		}
		else{
			Mensaje.mostrarMensaje("Seleccione un Grupo Asignado.",Mensaje.INFORMACION ,Messagebox.EXCLAMATION);
		
	}
	}
	
	public void onClick$btnBuscarUsuario(){
		String name = txtNombreUsuario.getRawText();

		if(name != ""){
				usuario = servicioUsuario.buscarPorNombre(name);
				
				if (usuario==null){
					Mensaje.mostrarMensaje("Usuario correcto.",Mensaje.INFORMACION ,Messagebox.EXCLAMATION);
					txtContra.setFocus(true);
				}
				else{
					Mensaje.mostrarMensaje("Nombre de Usuario ya existente ingrese otro.",Mensaje.INFORMACION ,Messagebox.EXCLAMATION);
					txtNombreUsuario.setRawValue("");
				}
			
			}
			else{
				Mensaje.mostrarMensaje("Debe Ingresar un Usuario.",Mensaje.INFORMACION ,Messagebox.EXCLAMATION);
			
		}

		
	}
	
	public void cargardatos(){
		
		txtCedula.setValue(persona.getCedulaRif());
		txtNombre.setValue(persona.getPersonaNatural().getPrimerNombre());
		txtApellido.setValue(persona.getPersonaNatural().getPrimerApellido());
	
		Personal personal1 = new Personal();
		
		personal1 = persona.getPersonaNatural().getPersonal();
		personalCargo = servicioPersonalCargo.buscarCargoActual(personal1);
		txtOcupacion.setValue(personalCargo.getDatoBasico().getNombre());
		
		byte[] foto = persona.getPersonaNatural().getFoto();
		if (foto != null){
			try{
				AImage aImage = new AImage("foto.jpg", foto);
				imgUsuario.setContent(aImage);
			} catch (IOException e) {
				e.printStackTrace();				
			}			
		}
		listaGrupos = servicioGrupo.listarActivos();
		
		binder.loadComponent(listGrupos);
		 
	    usuario = servicioUsuario.buscarPorCedula(persona.getCedulaRif());
	   
	    if (usuario!=null){
	    	txtNombreUsuario.setReadonly(true);
	    	auxiliar = 1;
	    txtNombreUsuario.setValue(usuario.getNick());
	    txtContra.setValue(usuario.getPassword()); 
	    txtConfirmContra.setValue(usuario.getPassword());
	    
	 
	    }
	};
	
	public void onClick$btnBuscar(){
		
		limpiarcampos();
		auxiliar=0;
		txtNombreUsuario.setReadonly(false);
		
		final Component catalogoPersonal = Executions.createComponents("/Logistica/Vistas/frmCatalogoPersonal.zul", null, null);

		catalogoPersonal.setVariable("frmPadre", formulario, false);
		int numero = 1;
		catalogoPersonal.setVariable("numero", numero, false);
		int aux = 2;
		catalogoPersonal.setVariable("aux", aux, false);
		formulario.addEventListener("onCatalogoCerradoResponsable", new EventListener() {
			public void onEvent(Event arg0) throws Exception {
			
				persona = (Persona) formulario.getVariable("persona", false);
				cargardatos();
				binder.loadAll();
				arg0.stopPropagation();
			}
		});
	}
	
	public void limpiarcampos(){
		txtNombre.setValue("");
		txtApellido.setValue("");
		txtCedula.setValue("");
		txtOcupacion.setValue("");
		txtContra.setValue("");
		txtConfirmContra.setValue("");
		txtNombreUsuario.setValue("");
	usuario = new Usuario();
	persona = new Persona();
	personal = new Personal();
	personalCargo = new PersonalCargo();  
	listaGruposAsignados.clear();
	listaGrupos.clear();
	}
	//GET AND SET

	
    // METODOS
	public List<Grupo> getListaGrupos() {
		return listaGrupos;
	}

	public void setListaGrupos(List<Grupo> listaGrupos) {
		this.listaGrupos = listaGrupos;
	}

	
	public List<Grupo> getListaGruposAsignados() {
		return listaGruposAsignados;
	}
	public void setListaGruposAsignados(List<Grupo> listaGruposAsignados) {
		this.listaGruposAsignados = listaGruposAsignados;
	}
	
	public void onCreate$win(ForwardEvent event){
		 binder = (AnnotateDataBinder) event.getTarget().getVariable("binder", false);  
	}
	
	
	public void onClick$btnSalir() {
		
		winGestionarUsuario.detach();
	}
	
	
}


