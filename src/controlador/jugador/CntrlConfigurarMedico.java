package controlador.jugador;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

import org.zkoss.util.media.AMedia;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;

import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Window;

import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import servicio.implementacion.ServicioMedico;
import servicio.implementacion.ServicioDatoBasico;

import comun.ConeccionBD;
import comun.Mensaje;
import comun.Ruta;

import comun.TipoDatoBasico;

import modelo.Medico;

import modelo.DatoBasico;

/**
 * Clase controladora de los eventos de la vista de igual nombre el presente
 * controlador se encarga de agregar, eliminar logicamente y modifica
 * 
 * 
 * @author Robert A
 * @author Miguel B
 * 
 * @version 4.0 6/02/2012
 * 
 * */
public class CntrlConfigurarMedico extends GenericForwardComposer {
	private ServicioMedico servicioMedico;
	private ServicioDatoBasico servicioDatoBasico;

	// private DatoBasico especialidad = new DatoBasico();
	private Medico medico = new Medico();
	private Window winConfigurarMedico;

	private Button btnGuardar;
	private Button btnModificar;
	private Button btnEliminar;
	private Button btnCancelar;
	private Button btnSalir;
	private Button btnBuscar;

	private Connection con;
	private String jrxmlSrc;
	private Map parameters = new HashMap();
	
	private String rutasGen = Ruta.GENERAL.getRutaVista();

	private Listbox listmedico;
	
	private Textbox txtApellido,txtTelefonoCelular,txtTelefonoHabitacion,txtNumcol, txtCedula,txtMatricula,txtNombre;
	private Component formulario;
	private Combobox cmbEspecialidad,cmbNacionalidad,cmbCodCelular,cmbCodArea;
	
	private List<Medico> medicos = new ArrayList<Medico>();
	private List<DatoBasico> codigosArea;
	private List<DatoBasico> codigosCelular;
	private List<DatoBasico> especialidades = new ArrayList<DatoBasico>() ;

	private AnnotateDataBinder binder;
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		comp.setVariable("controller", this, true);
		formulario = comp;
		txtNumcol.setFocus(true);
		btnModificar.setDisabled(true);
		btnEliminar.setDisabled(true);
		medicos=servicioMedico.listar();
	}

	public void onClick$btnGuardar() {
		if(txtNumcol.getValue().toString().length()!=6){
			Mensaje.mostrarMensaje("Debe Indicar Apellido",
					Mensaje.INFORMACION, Messagebox.EXCLAMATION);
		}
		else if(txtNumcol.getValue()==""){
			Mensaje.mostrarMensaje("Debe Indicar Número de Colegio",
					Mensaje.INFORMACION, Messagebox.EXCLAMATION);
		}
		else if(txtMatricula.getValue()==""){
			Mensaje.mostrarMensaje("Debe Indicar Número de Matrícula",
					Mensaje.INFORMACION, Messagebox.EXCLAMATION);
		}
		else if(txtMatricula.getValue().length()!=6){
			Mensaje.mostrarMensaje("Debe Indicar Número de Matrícula",
					Mensaje.INFORMACION, Messagebox.EXCLAMATION);
		}
		else if(cmbEspecialidad.getSelectedIndex()==-1){
			Mensaje.mostrarMensaje("Debe Seleccionar una Especialidad",
					Mensaje.INFORMACION, Messagebox.EXCLAMATION);
		}
		else if(cmbNacionalidad.getSelectedIndex()==-1){
			Mensaje.mostrarMensaje("Debe Seleccionar la Nacionalidad",
					Mensaje.INFORMACION, Messagebox.EXCLAMATION);
		}
		else if(txtCedula.getValue()==""){
			Mensaje.mostrarMensaje("Debe Indicar Cédula",
					Mensaje.INFORMACION, Messagebox.EXCLAMATION);
		}
		else if(txtNombre.getValue().toString().length()==0){
			Mensaje.mostrarMensaje("Debe Indicar Nombre",
					Mensaje.INFORMACION, Messagebox.EXCLAMATION);
		}
		else if(txtApellido.getValue().toString().length()==0) {
			Mensaje.mostrarMensaje("Debe Indicar Número de Colegio",
					Mensaje.INFORMACION, Messagebox.EXCLAMATION);
		}
		else if(cmbCodArea.getSelectedIndex()==-1){
			Mensaje.mostrarMensaje("Debe Seleccionar el Código de Área",
					Mensaje.INFORMACION, Messagebox.EXCLAMATION);
		}
		else if(txtTelefonoHabitacion.getValue().toString().length()!=7){
			Mensaje.mostrarMensaje("Debe Indicar Teléfono de Habitación",
					Mensaje.INFORMACION, Messagebox.EXCLAMATION);
		}
		else if(cmbCodCelular.getSelectedIndex()==-1){
			Mensaje.mostrarMensaje("Debe Seleccionar el Código de Celular",
					Mensaje.INFORMACION, Messagebox.EXCLAMATION);
		}
		else if(txtTelefonoCelular.getValue().toString().length()!=7){
			Mensaje.mostrarMensaje("Debe Indicar Teléfono Celular",
					Mensaje.INFORMACION, Messagebox.EXCLAMATION);
		}		
		else{
		especialidades=servicioDatoBasico.buscar(TipoDatoBasico.ESPECIALIDAD);
		medico.setApellido(txtApellido.getValue().toUpperCase());
		medico.setNombre(txtNombre.getValue().toUpperCase());
		medico.setCedulaMedico(cmbNacionalidad.getValue().toUpperCase()+"-"+txtCedula.getValue().toString().toUpperCase());
		medico.setTelefonoCelular(codigosCelular.get(cmbCodCelular.getSelectedIndex()).getNombre()+"-"+txtTelefonoCelular.getValue().toUpperCase());
		medico.setDatoBasico(especialidades.get(cmbEspecialidad.getSelectedIndex()));
		medico.setTelefonoOficina(codigosArea.get(cmbCodArea.getSelectedIndex()).getNombre()+"-"+txtTelefonoHabitacion.getValue().toUpperCase());
		medico.setEstatus('A');
		Date fecha = new Date();
		medico.setFechaIngreso(fecha);
		servicioMedico.agregar(medico);
		limpiar();
		}
	}
	
	public void onBlur$txtNumcol(){
		if(servicioMedico.buscar(txtNumcol.getValue().toString())!=null){
			Mensaje.mostrarMensaje("El Número de Colegio ya Existe",
					Mensaje.INFORMACION, Messagebox.EXCLAMATION);
			txtNumcol.setRawValue(null);
		}
	}

	public void onClick$btnModificar() throws InterruptedException {
		if(txtNumcol.getValue().toString().length()!=6){
			Mensaje.mostrarMensaje("Debe Indicar Apellido",
					Mensaje.INFORMACION, Messagebox.EXCLAMATION);
		}
		else if(txtNumcol.getValue()==""){
			Mensaje.mostrarMensaje("Debe Indicar Número de Colegio",
					Mensaje.INFORMACION, Messagebox.EXCLAMATION);
		}
		else if(txtMatricula.getValue()==""){
			Mensaje.mostrarMensaje("Debe Indicar Número de Matrícula",
					Mensaje.INFORMACION, Messagebox.EXCLAMATION);
		}
		else if(txtMatricula.getValue().length()!=6){
			Mensaje.mostrarMensaje("Debe Indicar Número de Matrícula",
					Mensaje.INFORMACION, Messagebox.EXCLAMATION);
		}
		else if(cmbEspecialidad.getSelectedIndex()==-1){
			Mensaje.mostrarMensaje("Debe Seleccionar una Especialidad",
					Mensaje.INFORMACION, Messagebox.EXCLAMATION);
		}
		else if(cmbNacionalidad.getSelectedIndex()==-1){
			Mensaje.mostrarMensaje("Debe Seleccionar la Nacionalidad",
					Mensaje.INFORMACION, Messagebox.EXCLAMATION);
		}
		else if(txtCedula.getValue()==""){
			Mensaje.mostrarMensaje("Debe Indicar Cédula",
					Mensaje.INFORMACION, Messagebox.EXCLAMATION);
		}
		else if(txtNombre.getValue().toString().length()==0){
			Mensaje.mostrarMensaje("Debe Indicar Nombre",
					Mensaje.INFORMACION, Messagebox.EXCLAMATION);
		}
		else if(txtApellido.getValue().toString().length()==0) {
			Mensaje.mostrarMensaje("Debe Indicar Número de Colegio",
					Mensaje.INFORMACION, Messagebox.EXCLAMATION);
		}
		else if(cmbCodArea.getSelectedIndex()==-1){
			Mensaje.mostrarMensaje("Debe Seleccionar el Código de Área",
					Mensaje.INFORMACION, Messagebox.EXCLAMATION);
		}
		else if(txtTelefonoHabitacion.getValue().toString().length()!=7){
			Mensaje.mostrarMensaje("Debe Indicar Teléfono de Habitación",
					Mensaje.INFORMACION, Messagebox.EXCLAMATION);
		}
		else if(cmbCodCelular.getSelectedIndex()==-1){
			Mensaje.mostrarMensaje("Debe Seleccionar el Código de Celular",
					Mensaje.INFORMACION, Messagebox.EXCLAMATION);
		}
		else if(txtTelefonoCelular.getValue().toString().length()!=7){
			Mensaje.mostrarMensaje("Debe Indicar Teléfono Celular",
					Mensaje.INFORMACION, Messagebox.EXCLAMATION);
		}		
		else{
		especialidades=servicioDatoBasico.buscar(TipoDatoBasico.ESPECIALIDAD);
		medico.setApellido(txtApellido.getValue().toUpperCase());
		medico.setNombre(txtNombre.getValue().toUpperCase());
		medico.setCedulaMedico(cmbNacionalidad.getValue()+"-"+txtCedula.getValue().toString());
		medico.setTelefonoCelular(cmbCodCelular.getValue()+"-"+txtTelefonoCelular.getValue());
		medico.setDatoBasico(especialidades.get(buscaresps(cmbEspecialidad.getValue())));
		medico.setTelefonoOficina(cmbCodArea.getValue()+"-"+txtTelefonoHabitacion.getValue());
		medico.setEstatus('A');
		Messagebox.show("Esta seguro que Desea Modificar el Medico?", "MODIFICAR", Messagebox.YES|Messagebox.NO, Messagebox.QUESTION,
			     new EventListener() {
			       public void onEvent(Event evt) {
			         switch (((Integer)evt.getData()).intValue()) {
			           case Messagebox.YES: actualizar(); break; 
			           case Messagebox.NO: limpiar(); break; 
			      }
			    }
			   });
		}
	}

	public void actualizar(){
		servicioMedico.actualizar(medico);
		limpiar();
	}

	public void onClick$btnEliminar() throws InterruptedException {
		Messagebox.show("Esta seguro que Desea Desactivar el Medico?", "ELIMINAR", Messagebox.YES|Messagebox.NO, Messagebox.QUESTION,
			     new EventListener() {
			       public void onEvent(Event evt) {
			         switch (((Integer)evt.getData()).intValue()) {
			           case Messagebox.YES: doYes(); break; 
			           case Messagebox.NO: break; 
			      }
			    }
			   });
	}
	
	public void doYes(){
		medico.setEstatus('E');
		servicioMedico.actualizar(medico);
		limpiar();
	}

	public void onClick$btnCancelar() {
		limpiar();
	}

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	public List<DatoBasico> getEspecialidades() {
		especialidades=servicioDatoBasico.buscar(TipoDatoBasico.ESPECIALIDAD);
		return servicioDatoBasico.buscar(TipoDatoBasico.ESPECIALIDAD);
	}
	
	public List<DatoBasico> getCodigosCelular() {
		codigosCelular=servicioDatoBasico.buscar(TipoDatoBasico.CODIGO_CELULAR);
		return servicioDatoBasico.buscar(TipoDatoBasico.CODIGO_CELULAR);
	}
	
	public List<DatoBasico> getCodigosArea() {
		codigosArea=servicioDatoBasico.buscar(TipoDatoBasico.CODIGO_AREA);
		return servicioDatoBasico.buscar(TipoDatoBasico.CODIGO_AREA);
	}

	public void setCodigosArea(List<DatoBasico> codigosArea) {
		this.codigosArea = codigosArea;
	}
	
	public void limpiar(){
		txtTelefonoCelular.setRawValue(null);
		txtTelefonoHabitacion.setRawValue(null);
		cmbEspecialidad.setValue("--Seleccione--");
		cmbNacionalidad.setValue("-");
		cmbCodCelular.setValue("--");
		cmbCodArea.setValue("--");
		txtCedula.setRawValue(null);
		medico = new Medico();
		txtNumcol.setReadonly(false);
		btnGuardar.setDisabled(false);
		btnModificar.setDisabled(true);
		btnEliminar.setDisabled(true);
		binder.loadAll();
		binder.loadComponent(listmedico);
		txtNumcol.setFocus(true);
		btnGuardar.setImage("/Recursos/Imagenes/agregar.ico");
		btnModificar.setImage("/Recursos/Imagenes/editar.ico");
		btnEliminar.setImage("/Recursos/Imagenes/quitar.ico");
	}

	public int buscaresp(Medico medico){
		for (int i = 0; i < especialidades.size(); i++) {
			if (especialidades.get(i).getNombre().equals(medico.getDatoBasico().getNombre())){
				return i;
			}
		}
		return -1;
	}
	
	public int buscaresps(String medico){
		for (int i = 0; i < especialidades.size(); i++) {
			if (especialidades.get(i).getNombre().equals(medico)){
				return i;
			}
		}
		return -1;
	}
	
	public int buscarcarea(Medico medico){
		for (int i = 0; i < codigosArea.size(); i++) {
			if (codigosArea.get(i).getNombre().equals(medico.getTelefonoOficina().substring(0,4))){
				return i;
			}
		}
		return -1;
	}
	
	public int buscarcelu(Medico medico){
		for (int i = 0; i < codigosCelular.size(); i++) {
			if (codigosCelular.get(i).getNombre().equals(medico.getTelefonoCelular().substring(0,4))){
				return i;
			}
		}
		return -1;
	}
	
	public int buscarnac(Medico medico){
		if (medico.getCedulaMedico().substring(0,1).equals("V")) {
			return 0;
		}
		else{ 
			if (medico.getCedulaMedico().substring(0,1).equals("E")) {
			return 1;
		}
			else{
				if (medico.getCedulaMedico().substring(0,1).equals("R")) {
					return 2;
				}
			}
		}
		return -1;
	}
	
	public  List<Medico> getMedicos() {
		return servicioMedico.filtrar("",""
				,"","");
	}
		
	public void onSelect$listmedico(){
		medicos=servicioMedico.filtrar("",""
				,"","");
		medico = (Medico) medicos.get(listmedico.getSelectedIndex());
		cmbEspecialidad.setSelectedIndex(buscaresp(medico));
		cmbCodArea.setSelectedIndex(buscarcarea(medico));
		cmbCodCelular.setSelectedIndex(buscarcelu(medico));
		txtTelefonoCelular.setValue(medico.getTelefonoCelular().substring(5));
		txtTelefonoHabitacion.setValue(medico.getTelefonoOficina().substring(5));
		cmbNacionalidad.setSelectedIndex(buscarnac(medico));
		txtCedula.setValue(medico.getCedulaMedico().substring(2));
		txtNumcol.setReadonly(true);
		btnGuardar.setDisabled(true);
		btnModificar.setDisabled(false);
		if(medico.getEstatus()=='A'){
		btnEliminar.setDisabled(false);
		}
		else{
			btnEliminar.setDisabled(true);	
		}
		btnGuardar.setImage("/Recursos/Imagenes/agregar.ico");
		btnModificar.setImage("/Recursos/Imagenes/editar.ico");
		btnEliminar.setImage("/Recursos/Imagenes/quitar.ico");
		binder.loadAll();
	}
	
	public void onClick$btnSalir(){
		winConfigurarMedico.detach();
	}
	
	public void onClick$btnImprimir() throws SQLException, JRException, IOException {
		con = ConeccionBD.getCon("postgres","postgres","123456");
		jrxmlSrc = Sessions.getCurrent().getWebApp().getRealPath("/WEB-INF/reportes/ReporteListadoMedico.jrxml");
		mostrarVisor();
	}

	public void mostrarVisor() throws JRException {
		JasperReport jasp = JasperCompileManager.compileReport(jrxmlSrc);
		JasperPrint jaspPrint = JasperFillManager.fillReport(jasp, parameters, con);
		
		byte[] archivo = JasperExportManager.exportReportToPdf(jaspPrint);//Generar Pdf
		final AMedia amedia = new AMedia("ListadoDeMedicos.pdf","pdf","application/pdf", archivo);
		
		Component visor = Executions.createComponents(rutasGen
					+ "frmVisorDocumento.zul", null, null);
			visor.setVariable("archivo", amedia, false);
	}
	
}