package controlador.jugador;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRTextExporter;
import net.sf.jasperreports.engine.export.JRTextExporterParameter;

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
 * 
 * @author German L
 * @author Robert A
 * @version 1.1 25/01/2012
 * 
 * */
public class CntrlConsultarJugador extends GenericForwardComposer {
	
	private ServicioCategoria servicioCategoria;
	private ServicioEquipo servicioEquipo;
	private ServicioRoster servicioRoster;
	private ServicioLapsoDeportivo servicioLapsoDeportivo;
	private ServicioDatoBasico servicioDatoBasico;

	private Window winConsultarJugador;
	
	private Equipo equipo = new Equipo();
	private Categoria categoria = new Categoria();
	private Jugador jugador = new Jugador();
	private static Roster rosters;
	private static String valorRetornado = "";

	
	private String rutasGen = Ruta.GENERAL.getRutaVista();
	
	private Anuario anuario = new Anuario();
	private List<Anuario> listaJugador = new ArrayList<Anuario>();

	private List<Jugador> Jugadores = new ArrayList<Jugador>();
	private List<Equipo> Equipos;
	private List<Jugador> listaRoster = new ArrayList<Jugador>();

	private Listbox listJugadores;
	
	private Component catalogo;
	private AnnotateDataBinder binder;

	private Combobox cmbEquipo, cmbCategoria;
	
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
		listaRoster = servicioRoster.listarJugadores(equipo);
		return listaRoster;
	}

	public static void setRosters(Roster rosters) {
		CntrlConsultarJugador.rosters = rosters;
	}

	public static Roster getRosters() {
		return rosters;
	}

	public static void setValorRetornado(String valorRetornado) {
		CntrlConsultarJugador.valorRetornado = valorRetornado;
	}

	public static String getValorRetornado() {
		return valorRetornado;
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
	}	
	
	public void onClick$btnGenerar() throws SQLException, JRException, IOException {
		if (cmbCategoria.getSelectedIndex() >= 0) {
			if (cmbEquipo.getSelectedIndex() >= 0) {
					con = ConeccionBD.getCon("postgres","postgres","123456");
					jrxmlSrc = Sessions.getCurrent().getWebApp().getRealPath("/WEB-INF/reportes/jugadores.jrxml");
					parameters.put("categoria" , cmbCategoria.getSelectedItem().getLabel());
					parameters.put("equipo" , cmbEquipo.getSelectedItem().getLabel());
					showReportfromJrxml();							
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
		JasperReport jasp = JasperCompileManager.compileReport(jrxmlSrc);
		JasperPrint jaspPrint = JasperFillManager.fillReport(jasp, parameters, con);
		
		byte[] archivo = JasperExportManager.exportReportToPdf(jaspPrint);//Generar Pdf
		final AMedia amedia = new AMedia("JugadoresInscritos.pdf","pdf","application/pdf", archivo);
				
		Component visor = Executions.createComponents(rutasGen
					+ "frmVisorDocumento.zul", null, null);
			visor.setVariable("archivo", amedia, false);		
	}
	
	public void onClick$btnExportar() throws SQLException, JRException, IOException {
		if (cmbCategoria.getSelectedIndex() >= 0) {
			if (cmbEquipo.getSelectedIndex() >= 0) {
					con = ConeccionBD.getCon("postgres","postgres","123456");
					jrxmlSrc = Sessions.getCurrent().getWebApp().getRealPath("/WEB-INF/reportes/jugadoresTexto.jrxml");
					parameters.put("categoria" , cmbCategoria.getSelectedItem().getLabel());
					parameters.put("equipo" , cmbEquipo.getSelectedItem().getLabel());
					generarTxt();							
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
	
	public void generarTxt() throws JRException, IOException{
		JasperReport jasp = JasperCompileManager.compileReport(jrxmlSrc);
		JasperPrint jaspPrint = JasperFillManager.fillReport(jasp, parameters, con);
	
		ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
		JRExporter exporter = new JRTextExporter();
		exporter.setParameter(JRExporterParameter.JASPER_PRINT ,jaspPrint);
		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM,arrayOutputStream);
		exporter.setParameter(JRTextExporterParameter.CHARACTER_WIDTH, 12f);// text
		exporter.setParameter(JRTextExporterParameter.CHARACTER_HEIGHT, 12f);// text
		exporter.exportReport();
		arrayOutputStream.close();
		
		final AMedia amedia = new AMedia("JugadoresInscritos.txt","txt","text/plain", arrayOutputStream.toByteArray());
		Filedownload.save(amedia);
	}	
	
	public void onClick$btnSalir(){
		winConsultarJugador.detach();
	}

}