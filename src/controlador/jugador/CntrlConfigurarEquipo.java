package controlador.jugador;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
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
import org.zkoss.zhtml.Messagebox;
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
import controlador.jugador.restriccion.Restriccion;

import modelo.Categoria;

import modelo.Divisa;
import modelo.Equipo;

import servicio.implementacion.ServicioCategoria;
import servicio.implementacion.ServicioDatoBasico;
import modelo.DatoBasico;

import servicio.implementacion.ServicioEquipo;
import servicio.implementacion.ServicioDivisa;

/**
 * Clase controladora de los eventos de la vista de igual nombre el presente
 * controlador se encarga de agregar, eliminar logicamente, la modificacion se
 * ha pospuesto momentaneamente por problemas con los combos
 * 
 * @author Robert A
 * @author Miguel B
 * 
 * @version 2.0 29/12/2011
 * 
 * */
public class CntrlConfigurarEquipo extends GenericForwardComposer {
	private Window winConfigurarEquipo;

	private ServicioCategoria servicioCategoria;
	private ServicioEquipo servicioEquipo;
	private ServicioDivisa servicioDivisa;
	private ServicioDatoBasico servicioDatoBasico;

	private DatoBasico clasificacion = new DatoBasico();
	private Equipo equipo = new Equipo();
	private Categoria categoria = new Categoria();
	private Divisa divisa = new Divisa();

	private List<Categoria> categorias = new ArrayList<Categoria>();
	private Listbox listEquipo;
	private List<Equipo> equipos = new ArrayList<Equipo>();
	private List<DatoBasico> tip = new ArrayList<DatoBasico>();
	private List<DatoBasico> tip2 = new ArrayList<DatoBasico>();
	private List<DatoBasico> tipo = new ArrayList<DatoBasico>();
	private List<DatoBasico> tip3 = new ArrayList<DatoBasico>();
	private List<DatoBasico> tip4 = new ArrayList<DatoBasico>();
	private List<Divisa> divisas = new ArrayList<Divisa>();
	private List<DatoBasico> tipoLapsos = new ArrayList<DatoBasico>();

	private Connection con;
	private String jrxmlSrc;
	private Map parameters = new HashMap();
	private String rutasGen = Ruta.GENERAL.getRutaVista();

	private Textbox txtNombre;
	private Label lbTipo, lbTipoLapso;

	private Combobox cmbTipo;
	private Combobox cmbTipoLapso;
	private Combobox cmbCategoria;
	private Combobox cmbDivisa;
	private Spinner spMinJugadores, spMaxJugadores;
	private AnnotateDataBinder binder;
	private Button btnSeleccionar, btnAgregar, btnQuitar, btnModificar, btnCancelar,
			btnSalir;

	public DatoBasico getClasificacion() {
		return clasificacion;
	}

	public void setClasificacion(DatoBasico clasificacion) {
		this.clasificacion = clasificacion;
	}

	public List<DatoBasico> getTipo() {
		return tipo;
	}

	public void setTipo(List<DatoBasico> tipo) {
		this.tipo = tipo;
	}

	public Divisa getDivisa() {
		return divisa;
	}

	public void setDivisa(Divisa divisa) {
		this.divisa = divisa;
	}

	public List<DatoBasico> getTipoLapsos() {
		return tipoLapsos;
	}

	public void setTipoLapsos(List<DatoBasico> tipoLapsos) {
		this.tipoLapsos = tipoLapsos;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Equipo getEquipo() {
		return equipo;
	}

	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		comp.setVariable("controller", this, false); // Hacemos visible el
		equipos = servicioEquipo.listar(); // modelo para el
											// databinder
		tipo.clear();
		tipo = servicioDatoBasico.buscar(TipoDatoBasico.CLASIFICACION_EQUIPO);
		tipoLapsos.clear();
		tipoLapsos = servicioDatoBasico
				.buscar(TipoDatoBasico.TIPO_LAPSO_DEPORTIVO);
		inicializar();
		aplicarConstraints();
	}

	public List<Categoria> getCategorias() {
		categorias = servicioCategoria.listar();
		return servicioCategoria.listar();
	}

	public List<Equipo> getEquipos() {
		return equipos;
		// return servicioEquipo.listar();
	}

	public void setEquipos(List<Equipo> equipos) {
		this.equipos = equipos;
	}

	public List<Divisa> getDivisas() {
		divisas = servicioDivisa.listar();
		return servicioDivisa.listar();
	}

	public void onChange$cmbDivisa() {

		for (int i = 0; i < divisas.size(); i++) {
			if (divisas.get(i).getNombre() != "FUNDALARA") {
				cmbDivisa.setValue("FUNDALARA");
			}
		}
	}

	public void onChange$cmbCategoria() {
		categorias = servicioCategoria.listar();
		tip.clear();
		tip2.clear();
		tip = servicioDatoBasico.buscar(TipoDatoBasico.CLASIFICACION_EQUIPO);
		for (int i = 0; i < categorias.get(cmbCategoria.getSelectedIndex())
				.getCantidadEquipo(); i++) {
			tip2.add(tip.get(i));
		}
		// Events.postEvent(new Event(Events.ON_SELECT, cmbCategoria));
		tipo.clear();
		tipo.addAll(tip2);
		binder.loadComponent(cmbTipo);
		cmbTipoLapso.setDisabled(false);
	}

	public void onChange$cmbTipo() {
		String valor = cmbCategoria.getValue();
		String valor2 = tipo.get(cmbTipo.getSelectedIndex()).getNombre();
		txtNombre.setValue(valor + " " + valor2);
	}

	public void onChange$cmbTipoLapso() {
		if (cmbTipoLapso.getValue().equals("PLAN VACACIONAL")) {
			cmbTipo.setDisabled(false);
			txtNombre.setValue(cmbTipoLapso.getValue());
			cmbTipo.setDisabled(true);
			cmbTipo.setText("  ");
		} else {
			txtNombre.setValue("");
			cmbTipo.setDisabled(false);
			cmbTipo.setText("  ");
		}
	}

	public void onClick$btnAgregar() throws InterruptedException {
		if (txtNombre.getText() == "") {
			Messagebox.show("Seleccione un nombre", "Mensaje", Messagebox.OK,
					Messagebox.INFORMATION);
			txtNombre.focus();
			return;
		} else if (cmbDivisa.getText() == "") {
			Messagebox.show("Seleccione una Divisa", "Mensaje", Messagebox.OK,
					Messagebox.INFORMATION);
			cmbDivisa.focus();
			return;
		} else if (cmbCategoria.getText() == "") {
			Messagebox.show("Seleccione una Categoria", "Mensaje",
					Messagebox.OK, Messagebox.INFORMATION);
			cmbCategoria.focus();
			return;
		} else if (cmbTipoLapso.getText() == "") {
			Messagebox.show("Seleccione un lapso", "Mensaje", Messagebox.OK,
					Messagebox.INFORMATION);
			cmbTipoLapso.focus();
			return;
		}
		else if (cmbTipoLapso.getValue() != ("PLAN VACACIONAL"))
			if (cmbTipo.getText() == "") {
				Messagebox.show("Seleccione una Clasificación", "Mensaje",
						Messagebox.OK, Messagebox.INFORMATION);
				cmbTipo.focus();
				return;
			} else if (spMinJugadores.getValue() == 0) {
				Messagebox.show("Seleccione un Mínimo de jugadores", "Mensaje",
						Messagebox.OK, Messagebox.INFORMATION);
				spMinJugadores.focus();
				return;
			} else if (spMaxJugadores.getValue() == 0) {
				Messagebox.show("Seleccione un Máximo de jugadores", "Mensaje",
						Messagebox.OK, Messagebox.INFORMATION);
				spMaxJugadores.focus();
				return;
		}else {
				if (cmbTipo.getText() == ("")) {
					for (int i = 0; i < equipos.size(); i++) {
						if (cmbTipo
								.getSelectedItem()
								.getLabel()
								.equals(equipos.get(i)
										.getDatoBasicoByCodigoClasificacion()
										.getNombre())) {
							if (cmbCategoria
									.getSelectedItem()
									.getLabel()
									.equals(equipos.get(i).getCategoria()
											.getNombre())) {
								Mensaje.mostrarMensaje("Ya existe el equipo para la categoría",
										Mensaje.ERROR_DATOS, Messagebox.EXCLAMATION);
								return;
							}
						}
					}
				}
				if (cmbTipoLapso.getValue().equals("PLAN VACACIONAL")) {
					equipo.setDivisa(divisas.get(cmbDivisa.getSelectedIndex()));
					equipo.setDatoBasicoByCodigoClasificacion(null);
					equipo.setDatoBasicoByCodigoTipoLapso(tipoLapsos
							.get(cmbTipoLapso.getSelectedIndex()));
					equipo.setCategoria(categorias.get(cmbCategoria
							.getSelectedIndex()));
					equipo.setNombre(txtNombre.getValue().toUpperCase());
					equipo.setMaximoJugador(spMaxJugadores.getValue());
					equipo.setMinimoJugador(spMinJugadores.getValue());
					equipo.setEstatus('A');
					equipo.setCodigoEquipo(servicioEquipo.listar().size() + 1);
					servicioEquipo.agregar(equipo);
				} else {
					equipo.setDivisa(divisas.get(cmbDivisa.getSelectedIndex()));
					equipo.setDatoBasicoByCodigoClasificacion(tipo.get(cmbTipo
							.getSelectedIndex()));
					equipo.setDatoBasicoByCodigoTipoLapso(tipoLapsos
							.get(cmbTipoLapso.getSelectedIndex()));
					equipo.setCategoria(categorias.get(cmbCategoria
							.getSelectedIndex()));
					equipo.setNombre(txtNombre.getValue().toUpperCase());
					equipo.setMaximoJugador(spMaxJugadores.getValue());
					equipo.setMinimoJugador(spMinJugadores.getValue());
					equipo.setEstatus('A');
					equipo.setCodigoEquipo(servicioEquipo.listar().size() + 1);
					servicioEquipo.agregar(equipo);
				}
				Mensaje.mostrarMensaje("Equipo agregado",
						Mensaje.EXITO, Messagebox.INFORMATION);
				equipos.add(equipo);
				limpiar();
				binder.loadAll();
			}
	}

	public void onClick$btnModificar() throws InterruptedException {
		if (cmbTipoLapso.getValue().equals("PLAN VACACIONAL")) {
			equipo = equipos.get(listEquipo.getSelectedIndex());
			equipo.setNombre(txtNombre.getValue().toUpperCase());
			equipo.setDivisa(divisas.get(cmbDivisa.getSelectedIndex()));
			equipo.setDatoBasicoByCodigoClasificacion(null);
			equipo.setDatoBasicoByCodigoTipoLapso(tipoLapsos.get(cmbTipoLapso.getSelectedIndex()));
			equipo.setCategoria(categorias.get(cmbCategoria.getSelectedIndex()));
			equipo.setMaximoJugador(spMaxJugadores.getValue());
			equipo.setMinimoJugador(spMinJugadores.getValue());
			equipo.setEstatus('A');
			servicioEquipo.actualizar(equipo);
		}
		else {
			equipo = equipos.get(listEquipo.getSelectedIndex());
			equipo.setNombre(txtNombre.getValue().toUpperCase());
			equipo.setDivisa(divisas.get(cmbDivisa.getSelectedIndex()));
			equipo.setDatoBasicoByCodigoClasificacion(tipo.get(cmbTipo
					.getSelectedIndex()));
			equipo.setDatoBasicoByCodigoTipoLapso(tipoLapsos.get(cmbTipoLapso
					.getSelectedIndex()));
			equipo.setCategoria(categorias.get(cmbCategoria.getSelectedIndex()));
			equipo.setMaximoJugador(spMaxJugadores.getValue());
			equipo.setMinimoJugador(spMinJugadores.getValue());
			equipo.setEstatus('A');
			servicioEquipo.actualizar(equipo);
		}
		Mensaje.mostrarMensaje("Equipo modificado",
				Mensaje.EXITO, Messagebox.INFORMATION);
		limpiar();
	}

	public void onClick$btnQuitar() throws InterruptedException {
		if (Messagebox
				.show("Esta seguro que Desea Desactivar el equipo?",
						"ELIMINAR", Messagebox.YES | Messagebox.NO,
						Messagebox.QUESTION) == Messagebox.YES)
			doYes();
		else
			return;
	}

	public void doYes() {
		if (listEquipo.getSelectedIndex() >= 0) {
			Equipo equipoTemp = (Equipo) listEquipo.getSelectedItem()
					.getValue();
			if (servicioEquipo.buscarPorCodigo(equipo) == false){
				Mensaje.mostrarMensaje("No se puede borrar mientras existan jugadores en este equipo",
						Mensaje.ERROR, Messagebox.ERROR);
			}
			else {
				equipo.setEstatus('E');
				equipos.remove(equipoTemp);
				servicioEquipo.eliminar(equipoTemp);
				limpiar();
				inicializar();
			}
		} else {
			Mensaje.mostrarMensaje("Seleccione un dato para eliminar.",
					Mensaje.INFORMACION, Messagebox.EXCLAMATION);
		}
	}

	public void onClick$btnSalir() {
		winConfigurarEquipo.detach();
	}

	public void limpiar() {
		equipo = new Equipo();
		binder.loadAll();
		binder.loadComponent(listEquipo);
		inicializar();
	}

	public void onClick$btnCancelar() {
		limpiar();
	}

	public void inicializar() {
		cmbDivisa.setFocus(true);
		btnAgregar.setDisabled(false);
		btnQuitar.setDisabled(true);
		btnModificar.setDisabled(true);
		btnSalir.setDisabled(false);
		btnCancelar.setDisabled(false);
		cmbTipo.setDisabled(true);
		cmbTipoLapso.setDisabled(true);
		spMinJugadores.setValue(12);
		spMaxJugadores.setValue(20);
		cmbCategoria.setDisabled(false);
		cmbCategoria.setText("");
		cmbDivisa.setDisabled(false);
		cmbDivisa.setText("");
		cmbTipo.setText("");
		cmbTipoLapso.setText("");
		txtNombre.setText("");
	}

	public void onSelect$listEquipo() {
		cmbCategoria.setDisabled(true);
		cmbDivisa.setDisabled(true);
		cmbTipo.setDisabled(false);
		cmbTipoLapso.setDisabled(true);
		lbTipo.setVisible(true);
		lbTipoLapso.setVisible(true);
		cmbCategoria.setSelectedIndex(buscarCat(equipos.get(
				listEquipo.getSelectedIndex()).getCategoria()));
		cmbDivisa.setSelectedIndex(buscarDiv(equipos.get(
				listEquipo.getSelectedIndex()).getDivisa()));
		txtNombre.setValue((equipos.get(listEquipo.getSelectedIndex())
				.getNombre()));
		spMaxJugadores.setValue(equipos.get(listEquipo.getSelectedIndex())
				.getMaximoJugador());
		spMinJugadores.setValue(equipos.get(listEquipo.getSelectedIndex())
				.getMinimoJugador());
		btnModificar.setDisabled(false);
		btnQuitar.setDisabled(false);
		btnAgregar.setDisabled(true);
		cmbTipoLapso.setSelectedIndex(buscarTipoSelecionado(equipos
				.get(listEquipo.getSelectedIndex())));
		cmbTipo.setSelectedIndex(buscarTipo(equipos.get(listEquipo
				.getSelectedIndex())));
	}

	public int buscarCat(Categoria categoria) {
		for (int i = 0; i < categorias.size(); i++) {
			if (categorias.get(i).getNombre().equals(categoria.getNombre())) {
				return i;
			}
		}
		return -1;
	}

	public int buscarDiv(Divisa divisa) {
		for (int i = 0; i < divisas.size(); i++) {
			if (divisas.get(i).getNombre().equals(divisa.getNombre())) {
				return i;
			}
		}
		return -1;
	}

	public int buscarTipo(Equipo equipo) {
		for (int i = 0; i < tipo.size(); i++) {
			if (equipo.getDatoBasicoByCodigoClasificacion() == null)
				return -1;
			if (tipo.get(i)
					.getNombre()
					.equals(equipo.getDatoBasicoByCodigoClasificacion()
							.getNombre())) {
				return i;
			}
		}
		return -1;
	}

	public int buscarTipoSelecionado(Equipo equipo) {
		for (int i = 0; i < tipoLapsos.size(); i++) {
			if (tipoLapsos
					.get(i)
					.getNombre()
					.equals(equipo.getDatoBasicoByCodigoTipoLapso().getNombre())) {
				return i;
			}
		}
		return -1;
	}

	private void aplicarConstraints() {
		spMinJugadores
				.setConstraint(Restriccion.MIN_JUGADORES.getRestriccion());
		spMaxJugadores
				.setConstraint(Restriccion.MAX_JUGADORES.getRestriccion());
		}
	
    public boolean validar() throws InterruptedException{
		if(spMinJugadores.getValue().equals(spMaxJugadores)){
			Messagebox.show("Seleccione rango de jugadores distintos ", "Mensaje", Messagebox.OK,Messagebox.INFORMATION);
		    spMinJugadores.setFocus(true);
		     return false;
		} else if(spMinJugadores.getValue() > spMaxJugadores.getValue()){
			       Messagebox.show("El minimo de jugadores sobrepasa a el maximo de jugadores", "Mensaje", Messagebox.OK, Messagebox.INFORMATION);
		           spMinJugadores.setFocus(true);
	               return false;
		     }
		return true;
	}

	public void showReportfromJrxml() throws JRException, IOException {
		JasperReport jasp = JasperCompileManager.compileReport(jrxmlSrc);
		JasperPrint jaspPrint = JasperFillManager.fillReport(jasp, parameters, con);
		
		byte[] archivo = JasperExportManager.exportReportToPdf(jaspPrint);//Generar Pdf
		final AMedia amedia = new AMedia("Reporte Equipos.pdf","pdf","application/pdf", archivo);
		
		Component visor = Executions.createComponents(rutasGen
					+ "frmVisorDocumento.zul", null, null);
			visor.setVariable("archivo", amedia, false);
	}

	public void onClick$btnImprimir() throws SQLException, JRException,
			IOException {
		con = ConeccionBD.getCon("postgres", "postgres", "123456");
		jrxmlSrc = Sessions.getCurrent().getWebApp()
				.getRealPath("/WEB-INF/reportes/reporte_Equipo.jrxml");
		showReportfromJrxml();
	}

}