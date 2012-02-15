package controlador.jugador;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import modelo.Categoria;
import modelo.DatoAcademico;
import modelo.DatoBasico;
import modelo.DatoMedico;
import modelo.DatoSocial;
import modelo.Equipo;
import modelo.Medico;

import org.zkoss.image.AImage;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Groupbox;
import org.zkoss.zul.Image;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Window;

import comun.Util;
import controlador.jugador.bean.Familiar;

/**
 * Clase controladora de los eventos de la vista de igual nombre y manejo de los
 * servicios de datos para la inscripcion de jugadores nuevo ingreso
 * 
 * @author Robert A
 * @author German L
 * @version 0.1 22/01/2012
 * 
 * */
public class CntrlVistaRegistroJugador extends GenericForwardComposer {
	private Window winVistaPreviaJugador;   
	private Component vista;
	private Label lblCedula;
	private Label lblPrimerNombre;
	private Label lblSegundoNombre;
	private Label lblPrimerApellido;
	private Label lblSegundoApellido;
	private Label lblGenero;
	private Image imgJugador;
	private Label lblFechaNac;
	private Label lblEdad;
	private Label lblPais;
	private Label lblEstado;
	private Label lblMunicipio;
	private Label lblParroquia;
	private Label lblEstadoResi;
	private Label lblMunicipioResi;
	private Label lblParroquiaResi;
	private Label lblDireccion;
	private Label lblTelefonoHabitacion;
	private Label lblTelefonoCelular;
	private Label lblCorreo;
	private Label lblTwitter;
	private Label lblGrupoSan;
	private Label lblMedico;
	private Label lblNumeroCol;
	private Label lblFechaRev;
	private Listbox listAfeccionesActuales;
	private Label lblObservacion;
	private Label lblInstitucion;
	private Label lblAnnoEsc;
	private Label lblCurso;
	private Listbox listActividadesSociales;
	private Label lblCategoria;
	private Label lblEquipo;
	private Label lblNumero;
	private Label lblPeso;
	private Label lblAltura;
	private Label lblBrazo;
	private Label lblPosicion;
	private Label lblCamisa;
	private Label lblPantalon;
	private Label lblCalzado;
	private Listbox listFamiliares;
	private Label lblCedulaFamiliar;
	private Label lblPrimerNombreF;
	private Label lblSegundoNombreF;
	private Label lblPrimerApellidoF;
	private Label lblSegundoApellidoF;
	private Label lblParentesco;
	private Label lblProfesion;
	private Image imgFamiliar;
	private Groupbox grboxFamiliar;
	
	private AnnotateDataBinder binder;
	
	private controlador.jugador.bean.Jugador jugador;
	private Medico medico;
	private DatoMedico datoMedico = new DatoMedico();
	private List<DatoBasico> afeccionesJugador = new ArrayList<DatoBasico>();
	private DatoAcademico datoAcademico = new DatoAcademico();
	private List<DatoSocial> datoSociales = new ArrayList<DatoSocial>();
	private Categoria categoria = new Categoria();
	private Equipo equipo = new Equipo();
	private List<controlador.jugador.bean.Familiar> familiares = new ArrayList<controlador.jugador.bean.Familiar>();
	
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		comp.setVariable("controller", this, false);
		vista = comp;
	}

	public void onCreate$winVistaPreviaJugador() {
		jugador = (controlador.jugador.bean.Jugador) vista.getVariable(
				"jugadorBean", false);
		medico = (Medico) vista.getVariable("medico", false);
		datoMedico = (DatoMedico) vista.getVariable("datoMedico", false);
		afeccionesJugador = (List<DatoBasico>) vista.getVariable("afeccionesJugador", false);
		datoAcademico = (DatoAcademico) vista.getVariable("datoAcademico", false);
		datoSociales = (List<DatoSocial>) vista.getVariable("datoSociales", false);
		categoria = (Categoria) vista.getVariable("categoria", false);
		equipo = (Equipo) vista.getVariable("equipo", false);
		familiares = (List<Familiar>) vista.getVariable("familiares", false);
		mostrarValores();
	}

	private void mostrarValores() {
		// Datos Basicos
		lblCedula.setValue(jugador.getCedulaCompleta());
		lblPrimerNombre.setValue(jugador.getPrimerNombre());
		lblSegundoNombre.setValue(jugador.getSegundoNombre());
		lblPrimerApellido.setValue(jugador.getPrimerApellido());
		lblSegundoApellido.setValue(jugador.getSegundoApellido());
		lblGenero.setValue(jugador.getGenero().getNombre());
		if (jugador.getFoto() != null) {
			try {
				AImage aImage = new AImage("foto.jpg", jugador.getFoto());
				imgJugador.setContent(aImage);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (jugador.getFechaNacimiento() != null) {
			lblFechaNac.setValue(Util.convertirFecha(jugador.getFechaNacimiento(),"dd/MM/yyyy"));
			lblEdad.setValue(String.valueOf(Util.calcularDiferenciaAnnios(jugador.getFechaNacimiento()))+ " AÑOS");
		}
		if (jugador.getPaisNac() != null) {
			lblPais.setValue(jugador.getPaisNac().getNombre());
		}
		if (jugador.getParroquiaNac() != null) {
			lblEstado.setValue(jugador.getParroquiaNac().getDatoBasico().getDatoBasico().getNombre());
			lblMunicipio.setValue(jugador.getParroquiaNac().getDatoBasico().getNombre());
			lblParroquia.setValue(jugador.getParroquiaNac().getNombre());
		}
		if (jugador.getParroquiaResi() != null) {
			lblEstadoResi.setValue(jugador.getParroquiaResi().getDatoBasico().getDatoBasico().getNombre());
			lblMunicipioResi.setValue(jugador.getParroquiaResi().getDatoBasico().getNombre());
			lblParroquiaResi.setValue(jugador.getParroquiaResi().getNombre());
		}
		lblDireccion.setValue(jugador.getDireccion());
		lblTelefonoHabitacion.setValue(jugador.getTelefonoHabitacion()
				.getTelefonoCompleto());
		lblTelefonoCelular.setValue(jugador.getTelefonoCelular()
				.getTelefonoCompleto());
		lblCorreo.setValue(jugador.getCorreoElectronico());
		lblTwitter.setValue(jugador.getTwitter());

		// Cargar Datos Medicos
		String tipoSangre = "";
		if (jugador.getTipoSangre().getTipoSangre()!=null){
			tipoSangre=jugador.getTipoSangre().getTipoSangre();
		}
		lblGrupoSan.setValue(tipoSangre);
		if (medico.getNombre() != null) {
			lblMedico.setValue(medico.getNombre() + " " + medico.getApellido());
		}
		lblNumeroCol.setValue(medico.getNumeroColegio());
		if (datoMedico.getFechaInforme() != null) {
			lblFechaRev.setValue(Util.convertirFecha(datoMedico.getFechaInforme(),"dd/MM/yyyy"));
		}
		
		listAfeccionesActuales.removeItemAt(0);
		for (int i = 0; i < afeccionesJugador.size(); i++) {
			Listitem listItem = new Listitem();
			Listcell listCell = new Listcell();	        
			Label aux = new Label();
			
			aux.setValue(afeccionesJugador.get(i).getNombre());
			listCell.appendChild(aux);
			listItem.appendChild(listCell);
			
			listAfeccionesActuales.appendChild(listItem);
		}
		
		lblObservacion.setValue(datoMedico.getObservacion());
		
		//Datos Academicos
		if (datoAcademico.getInstitucion() != null) {
			lblInstitucion.setValue(datoAcademico.getInstitucion().getNombre());
		}
		if (datoAcademico.getDatoBasicoByCodigoAnnoEscolar() != null) {
			lblAnnoEsc.setValue(datoAcademico.getDatoBasicoByCodigoAnnoEscolar().getNombre());
		}
		if (datoAcademico.getDatoBasicoByCodigoCurso() != null) {
			lblCurso.setValue(datoAcademico.getDatoBasicoByCodigoCurso().getNombre());
		}
		
		//Datos Sociales
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		listActividadesSociales.removeItemAt(0);
		for (int i = 0; i < datoSociales.size(); i++) {
			Listitem listItem = new Listitem();
			Listcell listCell1 = new Listcell();	        
			Listcell listCell2 = new Listcell();	        
			Listcell listCell3 = new Listcell();	        
			Listcell listCell4 = new Listcell();	        
			Label aux1 = new Label();
			Label aux2 = new Label();
			Label aux3 = new Label();
			Label aux4 = new Label();
			
			aux1.setValue(datoSociales.get(i).getDatoBasico().getNombre());
			listCell1.appendChild(aux1);
			listItem.appendChild(listCell1);
			
			aux2.setValue(datoSociales.get(i).getInstitucion().getNombre());
			listCell2.appendChild(aux2);
			listItem.appendChild(listCell2);
			
			String fecha = formato.format(datoSociales.get(i).getFechaInicio());
			aux3.setValue(fecha);
			listCell3.appendChild(aux3);
			listItem.appendChild(listCell3);
			
			aux4.setValue(datoSociales.get(i).getHorasDedicadas().toString());
			listCell4.appendChild(aux4);		
			listItem.appendChild(listCell4);
			
			listActividadesSociales.appendChild(listItem);
		}
		
		//Datos Deportivos
		lblCategoria.setValue(categoria.getNombre());
		lblEquipo.setValue(equipo.getNombre());
		lblNumero.setValue(String.valueOf(jugador.getNumero()));
		lblPeso.setValue(String.valueOf(jugador.getPeso()));
		lblAltura.setValue(String.valueOf(jugador.getAltura()));
		if (jugador.getBrazoLanzar() != null) {
			lblBrazo.setValue(jugador.getBrazoLanzar().getNombre());
		}
		if (jugador.getPosicionBateo() != null) {
			lblPosicion.setValue(jugador.getPosicionBateo().getNombre());
		}
		if (jugador.getTallaCamisa() != null) {
			lblCamisa.setValue(jugador.getTallaCamisa().getNombre());;
		}
		if (jugador.getTallaPantalon() != null) {
			lblPantalon.setValue(jugador.getTallaPantalon().getNombre());
		}
		if (jugador.getTallaCalzado() != null) {
			lblCalzado.setValue(jugador.getTallaCalzado().getNombre());
		}
		
		// Datos de Familiares
		listFamiliares.removeItemAt(0);
		for (int i = 0; i < familiares.size(); i++) {
			Listitem listItem = new Listitem();
			Listcell listCell1 = new Listcell();	        
			Listcell listCell2 = new Listcell();	        
			Listcell listCell3 = new Listcell();	        
			Listcell listCell4 = new Listcell();	        
			Listcell listCell5 = new Listcell();	        
			Label aux1 = new Label();
			Label aux2 = new Label();
			Label aux3 = new Label();
			Label aux4 = new Label();
			Checkbox aux5 = new Checkbox();
			
			aux1.setValue(familiares.get(i).getCedulaCompleta());
			listCell1.appendChild(aux1);
			listItem.appendChild(listCell1);
			
			aux2.setValue(familiares.get(i).getPrimerNombre() + " " + familiares.get(i).getSegundoNombre());
			listCell2.appendChild(aux2);
			listItem.appendChild(listCell2);
			
			aux3.setValue(familiares.get(i).getPrimerApellido() + " " + familiares.get(i).getSegundoApellido());
			listCell3.appendChild(aux3);
			listItem.appendChild(listCell3);
			
			aux4.setValue(familiares.get(i).getParentesco().getNombre());
			listCell4.appendChild(aux4);		
			listItem.appendChild(listCell4);

			aux5.setChecked(familiares.get(i).isRepresentante());
			aux5.setDisabled(true);
			listCell5.appendChild(aux5);		
			listItem.appendChild(listCell5);
			
			listFamiliares.appendChild(listItem);
		}
	}
	
	public void onSelect$listFamiliares() {
		Listcell ls = (Listcell) listFamiliares.getSelectedItem().getChildren().get(0);
		Label lb = (Label) ls.getChildren().get(0);
		if (lblCedulaFamiliar.getValue().equals(lb.getValue())) {
			grboxFamiliar.setVisible(false);
			lblCedulaFamiliar.setValue("");
		} else {
			for (int x = 0; x < familiares.size(); x++) {
				if (familiares.get(x).getCedulaCompleta().equals(lb.getValue())) {
					controlador.jugador.bean.Familiar familiar = familiares.get(x);
					lblCedulaFamiliar.setValue(familiar.getCedulaCompleta());
					lblPrimerNombreF.setValue(familiar.getPrimerNombre());
					if (familiar.getSegundoNombre() != null) {
						lblSegundoNombreF.setValue(familiar.getSegundoNombre());
					}
					lblPrimerApellidoF.setValue(familiar.getPrimerApellido());
					if (familiar.getSegundoApellido() != null) {
						lblSegundoApellidoF.setValue(familiar.getSegundoApellido());
					}
					lblParentesco.setValue(familiar.getParentesco().getNombre());
					if (familiar.getProfesion() != null) {
						lblProfesion.setValue(familiar.getProfesion().getNombre());
					}
					if (familiar.getFoto() != null) {
						try {
							AImage aImage = new AImage("fotoF.jpg", familiar.getFoto());
							imgFamiliar.setContent(aImage);
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					grboxFamiliar.setVisible(true);
				}
			}
		}
		listFamiliares.setSelectedIndex(-1);
	}

	public controlador.jugador.bean.Jugador getJugador() {
		return jugador;
	}

	public void setJugador(controlador.jugador.bean.Jugador jugadorBean) {
		this.jugador = jugadorBean;
	}
	
	public void onClick$btnSalir(){
		winVistaPreviaJugador.detach();
	}

}