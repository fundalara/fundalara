package controlador.administracion;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import modelo.DatoBasico;
import modelo.TipoIngreso;
import net.sf.jasperreports.engine.JRException;

import org.zkoss.util.media.AMedia;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Doublebox;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Radio;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;
import org.zkoss.zul.Button;

import comun.ConeccionBD;
import comun.Mensaje;
import comun.MensajeMostrar;
import controlador.general.ManejadorJasper;

import servicio.implementacion.ServicioDocumentoAcreedor;
import servicio.implementacion.ServicioTipoIngreso;
import servicio.implementacion.ServicioDatoBasico;
import servicio.implementacion.ServicioTipoDato;

public class CntrlActualizarCompromisos extends GenericForwardComposer {
	Window frmActualizarConceptoNomina;
	ServicioTipoIngreso servicioTipoIngreso;
	ServicioDatoBasico servicioDatoBasico;
	ServicioTipoDato servicioTipoDato;
	ServicioDocumentoAcreedor servicioDocumentoAcreedor;
	AnnotateDataBinder binder;
	Textbox txtDescripcion;
	Doublebox dbxPrecio;
	Listbox lbxTipoIngresos;
	Button btnRegistrar, btnSalir, btnAgregar, btnQuitar, btnCancelar,btnImprimir;
	Combobox cmbPeriodicidad, cmbTipoIngreso;
	DatoBasico tipoIngresoCombo, periodicidad;
	Radio rdoNo, rdoSi;
	List<DatoBasico> tipoIngresoCombos,
			periodicidades = new ArrayList<DatoBasico>();
	List<TipoIngreso> listaTipoIngresos = new ArrayList<TipoIngreso>();
	List<TipoIngreso> listaAuxTipoIngresos = new ArrayList<TipoIngreso>();
	TipoIngreso tipoIngreso = new TipoIngreso();
	Boolean flag = false;
	int indice = 0;

	// ---------------------------------------------------------------------------------------------------
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		comp.setVariable("cntrl", this, true);
		listaTipoIngresos = servicioTipoIngreso.listarActivos();
		periodicidades = servicioDatoBasico.listarPorTipoDato("PERIODICIDAD");
		tipoIngresoCombos = servicioDatoBasico
				.listarPorTipoDato("CLASIFICACION DE INGRESO");
	}

	// ---------------------------------------------------------------------------------------------------
	public void onClick$btnRegistrar() {
		try {
			Messagebox.show(MensajeMostrar.GUARDAR, MensajeMostrar.TITULO
					+ "Importante", Messagebox.OK | Messagebox.CANCEL,
					Messagebox.QUESTION, new EventListener() {
						@Override
						public void onEvent(Event arg0)
								throws InterruptedException {
							if (arg0.getName().toString() == "onOK") {
								for (int i = 0; i < listaTipoIngresos.size(); i++) {
									Integer a = listaTipoIngresos.get(i)
											.getCodigoTipoIngreso();
									if (a == 0) {
										listaTipoIngresos.get(i)
												.setCodigoTipoIngreso(
														servicioTipoIngreso
																.listar()
																.size() + 1);
										servicioTipoIngreso
												.agregar(listaTipoIngresos
														.get(i));
									} else {
										servicioTipoIngreso
												.agregar(listaTipoIngresos
														.get(i));
									}
								}
								for (int i = 0; i < listaAuxTipoIngresos.size(); i++) {
									servicioTipoIngreso
											.agregar(listaAuxTipoIngresos
													.get(i));
								}

								clear();
								listaAuxTipoIngresos = new ArrayList<TipoIngreso>();
								binder.loadAll();
								Messagebox.show(
										MensajeMostrar.PROCESO_EXITOSO,
										MensajeMostrar.TITULO
												+ "Información", Messagebox.OK,
										Messagebox.INFORMATION);
							}
						}
					});
		} catch (Exception e) {
			System.out.println(e);
		}

	}
	// ---------------------------------------------------------------------------------------------------

	public void onClick$btnImprimir() throws SQLException, JRException, IOException{
		Map parametros1 = new HashMap();
		Connection con = ConeccionBD.getCon("postgres","postgres","123456");
		String jrxmlSrc = Sessions.getCurrent().getWebApp().getRealPath("/WEB-INF/reportes/ReporteCompromisos.jrxml");
		String nombre="Listado de Compromisos";
		System.out.println(parametros1);
		System.out.println(nombre);
		System.out.println(jrxmlSrc);
		AMedia report = ManejadorJasper.showReportfromJrxml(jrxmlSrc, parametros1, con, nombre);
		
	}
	// ---------------------------------------------------------------------------------------------------
	public void onClick$btnQuitar() {
		if (servicioDocumentoAcreedor.buscarPorTipoIngreso(txtDescripcion
				.getValue().toString()) != null) {
			try {
				Messagebox.show("Eliminación del Tipo de Ingreso no permitida",
						MensajeMostrar.TITULO + "Importante",
						Messagebox.OK, Messagebox.EXCLAMATION);
			} catch (Exception e) {
				// --------
			}
		} else {
			listaTipoIngresos.get(lbxTipoIngresos.getSelectedIndex())
					.setEstatus('E');
			listaAuxTipoIngresos.add(listaTipoIngresos.get(lbxTipoIngresos
					.getSelectedIndex()));
			listaTipoIngresos.remove(lbxTipoIngresos.getSelectedIndex());
			binder.loadComponent(lbxTipoIngresos);
		}

		btnQuitar.setDisabled(true);
	}

	// ---------------------------------------------------------------------------------------------------
	public void onClick$btnAgregar() {
		tipoIngreso = new TipoIngreso();

		if (txtDescripcion.getText().trim() == "") {
			throw new WrongValueException(txtDescripcion,
					"Debe indicar una Descripcion o Nombre para el Ingreso");
		} else {
			int indice = listaTipoIngresos.size();
			int i = 0;
			boolean encontrado = false;
			while (i < indice && !encontrado) {
				if (cmbTipoIngreso.getValue().toString() == (listaTipoIngresos
						.get(i).getDatoBasicoByCodigoTipo()).getNombre().toString()) {
					tipoIngreso = (TipoIngreso) lbxTipoIngresos
							.getSelectedItem().getValue();
					tipoIngreso.setMonto(dbxPrecio.getValue());
					tipoIngreso
							.setDatoBasicoByCodigoPeriodicidad((DatoBasico) cmbPeriodicidad
									.getSelectedItem().getValue());
					binder.loadComponent(lbxTipoIngresos);
					clear();
					encontrado = true;
					return;
				}
				i++;
			}
			tipoIngreso.setCodigoTipoIngreso(servicioTipoIngreso.listar()
					.size() + 1);
			tipoIngreso
					.setDatoBasicoByCodigoPeriodicidad((DatoBasico) cmbPeriodicidad
							.getSelectedItem().getValue());
			tipoIngreso.setDatoBasicoByCodigoTipo((DatoBasico) cmbTipoIngreso
					.getSelectedItem().getValue());
			tipoIngreso.setDescripcion(txtDescripcion.getValue().toUpperCase());
			tipoIngreso.setEstatus('A');
			tipoIngreso.setMonto(dbxPrecio.getValue());
			listaTipoIngresos.add(tipoIngreso);
			if (rdoSi.isChecked())
				tipoIngreso.setAplicaRepresentante(rdoSi.isChecked());
			else
				tipoIngreso.setAplicaRepresentante(false);
			clear();
			binder.loadAll();
		}
		btnQuitar.setDisabled(true);
		binder.loadAll();
	}

	// ---------------------------------------------------------------------------------------------------
	public void onSelect$lbxTipoIngresos() {
		TipoIngreso auxIngreso = new TipoIngreso();
		auxIngreso = (TipoIngreso) lbxTipoIngresos.getSelectedItem().getValue();
		txtDescripcion.setValue(auxIngreso.getDescripcion().toString());
		dbxPrecio.setValue(auxIngreso.getMonto());
		cmbPeriodicidad.setValue(auxIngreso.getDatoBasicoByCodigoPeriodicidad()
				.getNombre());
		cmbTipoIngreso.setValue(auxIngreso.getDatoBasicoByCodigoTipo()
				.getNombre());
		txtDescripcion.setDisabled(false);
		if (auxIngreso.isAplicaRepresentante())
			rdoSi.setChecked(true);
		else
			rdoNo.setChecked(true);
		btnQuitar.setDisabled(false);
	}

	// ---------------------------------------------------------------------------------------------------
	public void clear() {
		txtDescripcion.setText("");
		dbxPrecio.setText("");
		btnQuitar.setDisabled(true);
		btnAgregar.setDisabled(false);
		cmbPeriodicidad.setText("--Seleccione--");
		cmbTipoIngreso.setText("--Seleccione--");
		rdoNo.setChecked(false);
		rdoSi.setChecked(false);
		binder.loadAll();
	}

	// ---------------------------------------------------------------------------------------------------
	public void onClick$btnCancelar() {
		clear();
	}

	// ---------------------------------------------------------------------------------------------------
	public boolean buscar() {
		int indice = listaTipoIngresos.size();
		int i = 0;
		boolean encontrado = false;
		while (i < indice && !encontrado) {
			if (txtDescripcion.getValue().equals(
					listaTipoIngresos.get(i).getDescripcion())) {
				encontrado = true;
				return encontrado;
			}
			i++;
		}
		return encontrado;
	}

	// ----------------------------------------------------------------------------------------------------
	public TipoIngreso getTipoIngreso() {
		return tipoIngreso;
	}

	public void setTipoIngreso(TipoIngreso tipoIngreso) {
		this.tipoIngreso = tipoIngreso;
	}

	public List<TipoIngreso> getListaTipoIngresos() {
		return listaTipoIngresos;
	}

	public void setListaTipoIngresos(List<TipoIngreso> listaTipoIngresos) {
		this.listaTipoIngresos = listaTipoIngresos;
	}

	public List<DatoBasico> getTipoIngresoCombos() {
		return tipoIngresoCombos;
	}

	public void setTipoIngresoCombos(List<DatoBasico> tipoIngresoCombos) {
		this.tipoIngresoCombos = tipoIngresoCombos;
	}

	public List<DatoBasico> getPeriodicidades() {
		return periodicidades;
	}

	public void setPeriodicidades(List<DatoBasico> periodicidades) {
		this.periodicidades = periodicidades;
	}

	public DatoBasico getPeriodicidad() {
		return periodicidad;
	}

	public void setPeriodicidad(DatoBasico periodicidad) {
		this.periodicidad = periodicidad;
	}

	public DatoBasico getTipoIngresoCombo() {
		return tipoIngresoCombo;
	}

	public void setTipoIngresoCombo(DatoBasico tipoIngresoCombo) {
		this.tipoIngresoCombo = tipoIngresoCombo;
	}
	// ---------------------------------------------------------------------------------------------------

}
