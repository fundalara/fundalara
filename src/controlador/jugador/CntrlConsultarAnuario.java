package controlador.jugador;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.zkoss.image.AImage;
import org.zkoss.util.media.AMedia;
import modelo.Categoria;

import modelo.DatoBasico;
import modelo.Equipo;
import modelo.Jugador;
import modelo.LapsoDeportivo;
import modelo.Roster;
import modelo.Competencia;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.*;

import comun.ConeccionBD;
import comun.Mensaje;
import comun.Ruta;
import comun.TipoDatoBasico;
import controlador.jugador.bean.Anuario;

import servicio.implementacion.ServicioCategoria;
import servicio.implementacion.ServicioCompetencia;
import servicio.implementacion.ServicioDatoBasico;
import servicio.implementacion.ServicioEquipo;
import servicio.implementacion.ServicioLapsoDeportivo;
import servicio.implementacion.ServicioRoster;

/**
 * Clase controladora de los eventos de la vista de igual nombre, el presente
 * controlador se encarga de buscar por categoria, equipo y temporada a los
 * jugadores pertenecientes a la misma y mostrar su foto, nombre y número
 * 
 * @author Glendy Oliveros
 * @author Aimee Monsalve
 * @author Greisy Rodriguez
 * @author Alberto Perozo
 * @author Edgar Luzardo
 * 
 * @version 1.1 25/01/2012
 * 
 * */
public class CntrlConsultarAnuario extends GenericForwardComposer {
	
	private ServicioCategoria servicioCategoria;
	private ServicioEquipo servicioEquipo;
	private ServicioRoster servicioRoster;
	private ServicioCompetencia servicioCompetencia;
	private ServicioLapsoDeportivo servicioLapsoDeportivo;
	private ServicioDatoBasico servicioDatoBasico;

	private Window winAnuarioJugadores;
	
	private Equipo equipo = new Equipo();
	private Categoria categoria = new Categoria();
	private Jugador jugador = new Jugador();
	private Roster roster = new Roster();
	private static Roster rosters;
	private static String valorRetornado = "";
	private Competencia competencia = new Competencia();
	private LapsoDeportivo temporada = new LapsoDeportivo();
	
	private String rutasGen = Ruta.GENERAL.getRutaVista();
	
	private Anuario anuario = new Anuario();
	private List<Anuario> listaAnuario = new ArrayList<Anuario>();

	private List<Jugador> Jugadores = new ArrayList<Jugador>();
	private List<Equipo> Equipos;
	private List<Jugador> listaRoster = new ArrayList<Jugador>();

	private Listbox listAnuario;
	private Div divLista;

	private Component catalogo;
	private AnnotateDataBinder binder;

	private Combobox cmbEquipo, cmbCategoria, cmbTemporada;
	private Listcell celda;
	private Image img1;
	
	private Connection con;
	private String jrxmlSrc;
	private Map parameters = new HashMap();
	
	private Button btnGenerar;
	private Button btnsalir;

	@Override
	public void doAfterCompose(Component c) throws Exception {
		super.doAfterCompose(c);
		c.setVariable("controller", this, true);
		catalogo = c;
		cmbEquipo.setDisabled(true);
		cmbTemporada.setDisabled(true);
	}

	// Getters y Setters
	public Equipo getEquipo() {
		return equipo;
	}

	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Jugador getJugador() {
		return jugador;
	}

	public void setJugador(Jugador jugador) {
		this.jugador = jugador;
	}

	public void setListaRoster(List<Jugador> listaRoster) {
		this.listaRoster = listaRoster;
	}

	public List<Jugador> getListaRoster() {
		return listaRoster;
	}

	public static void setRosters(Roster rosters) {
		CntrlConsultarAnuario.rosters = rosters;
	}

	public static Roster getRosters() {
		return rosters;
	}

	public static void setValorRetornado(String valorRetornado) {
		CntrlConsultarAnuario.valorRetornado = valorRetornado;
	}

	public static String getValorRetornado() {
		return valorRetornado;
	}
	
	public LapsoDeportivo getTemporada() {
		return temporada;
	}

	public void setTemporada(LapsoDeportivo temporada) {
		this.temporada = temporada;
	}
	
	// Para llenar Listas y combos
	public List<Categoria> getCategorias() {
		return servicioCategoria.listar();
	}

	public List<Equipo> getEquipos() {
		return servicioEquipo.buscarPorCategoria(categoria);
	}

	public List<LapsoDeportivo> getLapsosDeportivos() {
		DatoBasico datoLapsoDeportivo = servicioDatoBasico.buscarTipo(
				TipoDatoBasico.TIPO_LAPSO_DEPORTIVO, "TEMPORADA REGULAR");
		servicioLapsoDeportivo = new ServicioLapsoDeportivo();
		return servicioLapsoDeportivo.buscarPorTipoLapso(datoLapsoDeportivo);
	}
	
	//Metodos
	public void onSelect$cmbCategoria() {
		cmbEquipo.setDisabled(false);
		cmbEquipo.getItems().clear();
		cmbEquipo.setSelectedIndex(-1);
		cmbTemporada.setSelectedIndex(-1);
		cmbTemporada.setDisabled(true);
	}	
	
	public void onSelect$cmbEquipo() {
		cmbTemporada.setDisabled(false);
		cmbTemporada.getItems().clear();
		cmbTemporada.setSelectedIndex(-1);
	}	
	
	public void onSelect$cmbTemporada(){		
		listaRoster = servicioRoster.listarJugadores(equipo);

		divLista.removeChild(listAnuario);		
		Listitem listItem = null;
		listAnuario = new Listbox();
		
		listaAnuario = new ArrayList<Anuario>();
		
		if (listaRoster.size() != 0) {
			for (int i = 0; i < listaRoster.size() ; i++) {
				ImageIcon n = new ImageIcon();
				AImage aImage = null;
				anuario = new Anuario();
				img1 = new Image();
				img1.setHeight("80px");
				img1.setWidth("80px");
				
				if (i%4 == 0){
					listItem = new Listitem();
					listItem.setAttribute("align", "center");
				}
				
				Jugador jug = listaRoster.get(i);		
				byte[] foto = jug.getPersonaNatural().getFoto();			
		        if (foto != null){
		            try {
		              aImage = new AImage("foto.jpg", foto);
		              n = new ImageIcon((byte[]) jug.getPersonaNatural().getFoto());
		              img1.setContent(aImage);
		            } catch (IOException e) {
		              e.printStackTrace();
		            }	
		          }
		        
		        Listcell listCell = new Listcell();	        
		        listCell.appendChild(img1);	        	        
		        listItem.appendChild(listCell);	        
				listAnuario.appendChild(listItem);
						
				Label lbl = new Label();
				lbl.setValue("#" + jug.getNumero() + "\n" + jug.getPersonaNatural().getPrimerNombre() + "\n" + jug.getPersonaNatural().getPrimerApellido());
				lbl.setMultiline(true);
				Listcell listCell2 = new Listcell();
				listCell2.appendChild(lbl);
				
				anuario.setNombreJugador(jug.getPersonaNatural().getPrimerNombre());
				anuario.setApellidoJugador(jug.getPersonaNatural().getPrimerApellido());
				anuario.setFotoJugador(n.getImage());
				anuario.setNumeroJugador(jug.getNumero());
				listaAnuario.add(anuario);
				
				listItem.appendChild(listCell2);
				listAnuario.appendChild(listItem);
			}			
		}
		else{
			Mensaje.mostrarMensaje("No hay fotos registradas para los valores seleccionados",
					Mensaje.ERROR_DATOS, Messagebox.EXCLAMATION);
		}
		divLista.appendChild(listAnuario);		
	}
	
	public void onClick$btnGenerar() throws SQLException, JRException, IOException {
		if (cmbCategoria.getSelectedIndex() >= 0) {
			if (cmbEquipo.getSelectedIndex() >= 0) {
				if (cmbTemporada.getSelectedIndex() >= 0) {
					con = ConeccionBD.getCon("postgres","postgres","123456");
					jrxmlSrc = Sessions.getCurrent().getWebApp().getRealPath("/WEB-INF/reportes/Anuario.jrxml");
					parameters.put("categoriaJug" , cmbCategoria.getSelectedItem().getLabel());
					parameters.put("equipoJug" , cmbEquipo.getSelectedItem().getLabel());
					parameters.put("tempJug" , cmbTemporada.getSelectedItem().getLabel());
					showReportfromJrxml();							
				}
				else{
					Mensaje.mostrarMensaje("Seleccione una Temporada", Mensaje.ERROR_DATOS, Messagebox.EXCLAMATION);
					cmbTemporada.setFocus(true);			
				}				
			}
			else{
				Mensaje.mostrarMensaje("Seleccione un Equipo", Mensaje.ERROR_DATOS, Messagebox.EXCLAMATION);
				cmbEquipo.setFocus(true);			
			}			
		}
		else{
			Mensaje.mostrarMensaje("Seleccione una Categoria", Mensaje.ERROR_DATOS, Messagebox.EXCLAMATION);
			cmbCategoria.setFocus(true);			
		}			
	}	
	
	public void showReportfromJrxml() throws JRException, IOException{
		JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(listaAnuario);	
		JasperReport jasp = JasperCompileManager.compileReport(jrxmlSrc);
		JasperPrint jaspPrint = JasperFillManager.fillReport(jasp, parameters, ds);
		
		byte[] archivo = JasperExportManager.exportReportToPdf(jaspPrint);//Generar Pdf
		final AMedia amedia = new AMedia("Anuario.pdf","pdf","application/pdf", archivo);
				
		Component visor = Executions.createComponents(rutasGen
					+ "frmVisorDocumento.zul", null, null);
			visor.setVariable("archivo", amedia, false);
	}	
	
	public void onClick$btnSalir(){
		winAnuarioJugadores.detach();
	}

}