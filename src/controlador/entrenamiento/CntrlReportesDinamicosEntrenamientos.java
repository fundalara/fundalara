package controlador.entrenamiento;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import modelo.ActividadCalendario;
import modelo.ActividadEntrenamiento;
import modelo.Categoria;
import modelo.DatoBasico;
import modelo.Equipo;
import modelo.InstalacionUtilizada;
import modelo.Jugador;
import modelo.Personal;
import modelo.PersonalCargo;
import modelo.PersonalEquipo;
import modelo.Roster;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import org.zkoss.util.media.AMedia;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Iframe;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Window;

import comun.ConeccionBD;
import servicio.implementacion.ServicioActividadCalendario;
import servicio.implementacion.ServicioActividadEntrenamiento;
import servicio.implementacion.ServicioCategoria;
import servicio.implementacion.ServicioEquipo;
import servicio.implementacion.ServicioEscalaMedicion;
import servicio.implementacion.ServicioIndicadorActividadEscala;
import servicio.implementacion.ServicioInstalacionUtilizada;
import servicio.implementacion.ServicioRoster;

public class CntrlReportesDinamicosEntrenamientos extends GenericForwardComposer {
	Datebox dtbox1, dtbox2;
	Combobox cmbcategoria, cmbequipo, cmbEstatus, cmbClases;
	Listbox lboxDatos;
	Button btnImprimir, btnSalir;
	ServicioCategoria servicioCategoria;
	ServicioEquipo servicioEquipo;
	ServicioRoster servicioRoster;
	ServicioActividadCalendario servicioActividadCalendario;
	ServicioInstalacionUtilizada servicioInstalacionUtilizada;
	ServicioActividadEntrenamiento servicioActividadEntrenamiento;
	ServicioIndicadorActividadEscala servicioIndicadorActividadEscala;
	ServicioEscalaMedicion servicioEscalaMedicion;
	List<Roster> listRoster;
	List<Equipo> listEquipo;
	List<Categoria> listCategoria;
	List<ActividadCalendario> listActividadCalendario;
	List<InstalacionUtilizada> listInstalacionUtilizada;
	List<ActividadEntrenamiento> listActividadEntrenamiento;
	AnnotateDataBinder binder;
	Categoria categoria = new Categoria();
	Map parameters = new HashMap();
	private Connection con;
	private String jrxmlSrc;
	Iframe ifReport;
	Window wndReporteDesempennoEquipo;
	
	String[] estatusActividadesCalendario = { "PEDIENTES (INACTIVAS)", "PLANIFICADAS SIN INSTALACION", "PLANIFICADAS CON INSTALACION", "REGISTRADO EL ENTRENAMIENTO", "REGISTRADO EL RENDIMIENTO", "SUSPENDIDAS" };
	Character[] estatusAC = { 'P', 'A', 'I', 'C', 'F', 'S' };
	String[] estatusRegistrosCompuestos = { "REGISTRADA", "ELIMINADA"};
	Character[] estatusRC = { 'A', 'Z'};
	String[] estatusInstalaciones = { "PLANIFICADAS", "ASIGNADAS"};
	Character[] estatusI = { 'A', 'Z'};
	
	public void llenarCombo(String[]  valores){
		cmbEstatus.getItems().clear();
		for (int i = 0; i < valores.length; i++) {
			Comboitem item = new Comboitem();
			item.setLabel(valores[i]);
			item.setValue(i);
			cmbEstatus.appendChild(item);
		}
	}
	
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		comp.setVariable("ctrl", this, true);
		listCategoria = servicioCategoria.getDaoCategoria().listarActivos();
		listEquipo = new ArrayList<Equipo>();
		listRoster = new ArrayList<Roster>();
		//llenarCombo(estatusActividadesCalendario);
	}
	
	
	public void compararItem (){
		if (cmbClases.getSelectedIndex()==0){
			llenarCombo(estatusRegistrosCompuestos);
		}else if (cmbClases.getSelectedIndex()==1){
			llenarCombo(estatusRegistrosCompuestos);
		}else if (cmbClases.getSelectedIndex()==2){
			llenarCombo(estatusActividadesCalendario);
		}else if (cmbClases.getSelectedIndex()==3){
			llenarCombo(estatusRegistrosCompuestos);
		}else if (cmbClases.getSelectedIndex()==4){
			llenarCombo(estatusRegistrosCompuestos);
		}
	}
	public void onChange$dtbox2(){
		Date date = dtbox2.getValue();
		if (date.before(dtbox1.getValue())) {
			alert("La fecha final es menor que la fecha inicial");
		} 
		else{
		if (cmbClases.getSelectedIndex()==3){
			Character pos = estatusAC[(Integer)cmbEstatus.getSelectedIndex()];
			listActividadCalendario = servicioActividadCalendario.getDaoActividadCalendario().listarDinamico(dtbox1.getValue(), dtbox2.getValue(), pos);		
			lboxDatos.getItems().clear();
			for (int i = 0; i < listActividadCalendario.size(); i++) {
				llenarListbox(lboxDatos, listActividadCalendario.get(i).getDescripcion(), listActividadCalendario.get(i).getFechaInicio()+"", listActividadCalendario.get(i).getCodigoActividadCalendario());		
			}
		}
		if (cmbClases.getSelectedIndex()==4){
			Character  pos = estatusI[(Integer)cmbEstatus.getSelectedIndex()];
			listInstalacionUtilizada =  servicioInstalacionUtilizada.getDaoInstalacionUtilizada().listarDinamico(dtbox1.getValue(), dtbox2.getValue(), pos);
			lboxDatos.getItems().clear();
			for (int i = 0; i < listInstalacionUtilizada.size(); i++) {
				llenarListbox(lboxDatos, listInstalacionUtilizada.get(i).getInstalacion().getDescripcion(), listInstalacionUtilizada.get(i).getFechaInicio()+"", listInstalacionUtilizada.get(i).getCodigoInstalacionUtilizada());		
			}
		}
		}
	}
	public void onSelect$cmbClases(){
		compararItem();
	}
	
	public void onChange$cmbClases(){
		compararItem();
	}
	
	public void onChange$cmbEstatus(){
		if (cmbClases.getSelectedIndex()==2){
			System.out.println("aaa2");
			Character pos = estatusAC[(Integer)cmbEstatus.getSelectedIndex()];
			System.out.println(pos);
			listActividadCalendario = (List<ActividadCalendario>)servicioActividadCalendario.getDaoActividadCalendario().listarUnCampo(ActividadCalendario.class, "estatus", pos);
			System.out.println(listActividadCalendario.size()+"aaaaaaaaaaaaaaa");		
			lboxDatos.getItems().clear();
			for (int i = 0; i < listActividadCalendario.size(); i++) {
				llenarListbox(lboxDatos, listActividadCalendario.get(i).getDescripcion(), listActividadCalendario.get(i).getFechaInicio()+"", listActividadCalendario.get(i).getCodigoActividadCalendario());		
			}
		}
		
		if (cmbClases.getSelectedIndex()==4){
			Character  pos = estatusI[(Integer)cmbEstatus.getSelectedIndex()];
			listInstalacionUtilizada =  (List<InstalacionUtilizada>)servicioInstalacionUtilizada.getDaoInstalacionUtilizada().listarUnCampo(InstalacionUtilizada.class, "estatus", pos);
			lboxDatos.getItems().clear();
			for (int i = 0; i < listInstalacionUtilizada.size(); i++) {
				llenarListbox(lboxDatos, listInstalacionUtilizada.get(i).getInstalacion().getDescripcion(), listInstalacionUtilizada.get(i).getFechaInicio()+"", listInstalacionUtilizada.get(i).getCodigoInstalacionUtilizada());		
			}
		}
		
		if (cmbClases.getSelectedIndex()==0){
			Character pos = estatusRC[(Integer)cmbEstatus.getSelectedItem().getValue()];
			listActividadEntrenamiento = servicioActividadEntrenamiento.listar(); 
			lboxDatos.getItems().clear();
			for (int i = 0; i < listActividadEntrenamiento.size(); i++) {
				llenarListbox(lboxDatos, listActividadEntrenamiento.get(i).getNombre(), listActividadEntrenamiento.get(i).getDatoBasico().getNombre(),listActividadEntrenamiento.get(i).getCodigoActividadEntrenamiento());		
			}
		}
		
		
		
		
	
	}
	
	public void llenarListbox(Listbox list, String txtA, String txtB, Integer db) {
		Listitem nvoItem = new Listitem();
		nvoItem.appendChild(new Listcell(txtA));
		nvoItem.appendChild(new Listcell(txtB));
		nvoItem.setValue(db);
		nvoItem.setHeight("25px");
		list.appendChild(nvoItem);	
	}
	
	
	public void onChange$cmbcategoria() {

		listEquipo.clear();
		Categoria cc = (Categoria) cmbcategoria.getSelectedItem().getValue();
		listEquipo = servicioEquipo.buscarPorCategoria(cc);
		cmbequipo.setDisabled(false);

		binder.loadComponent(cmbequipo);
		cmbequipo.setValue("--Seleccione--");

	}
	
	

	public void onChange$cmbequipo() {
		btnImprimir.setDisabled(false);
		
	}

	public List<Roster> getListRoster() {
		return listRoster;
	}

	public void setListRoster(List<Roster> listRoster) {
		this.listRoster = listRoster;
	}

	public List<Equipo> getListEquipo() {
		return listEquipo;
	}

	public void setListEquipo(List<Equipo> listEquipo) {
		this.listEquipo = listEquipo;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Categoria buscarCategoria() {
		Categoria c;

		int codigo;
		for (Object o : servicioCategoria.listar()) {
			c = (Categoria) o;
			codigo = Integer.parseInt(""
					+ cmbcategoria.getSelectedItem().getValue());
			if (codigo == c.getCodigoCategoria()) {
				return c;
			}
		}
		return null;
	}

	public List<Categoria> getListCategoria() {
		return listCategoria;
	}

	public void setListCategoria(List<Categoria> listCategoria) {
		this.listCategoria = listCategoria;
	}


		
	public void onClick$btnImprimir() throws SQLException, JRException, IOException {
		Categoria cc = (Categoria) cmbcategoria.getSelectedItem().getValue();
		Equipo c = (Equipo) cmbequipo.getSelectedItem().getValue();
		con = ConeccionBD.getCon("postgres", "postgres","123456");
		parameters.put("fecha_inicio",dtbox1.getText());
		parameters.put("fecha_fin",dtbox2.getText() );
		parameters.put("categoria", cc.getCodigoCategoria());
		parameters.put("equipo",c.getCodigoEquipo());
		String rutaReporte = Sessions
				.getCurrent()
				.getWebApp()
				.getRealPath(
						"/WEB-INF/reportes/DesempenoJugadoresEquipo.jrxml");
		JasperReport report = JasperCompileManager.compileReport(rutaReporte);
		JasperPrint print = JasperFillManager.fillReport(report, parameters,
				con);

		byte[] archivo = JasperExportManager.exportReportToPdf(print);

		final AMedia amedia = new AMedia("DesempeñoEquipos.pdf", "pdf",
				"application/pdf", archivo);

		Component visor = Executions.createComponents("General/"
				+ "frmVisorDocumento.zul", null, null);
		visor.setVariable("archivo", amedia, false);
		
	}

	public void onClick$btnSalir() {
		wndReporteDesempennoEquipo.detach();
	}

}

