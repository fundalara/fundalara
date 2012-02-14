package controlador.administracion;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import modelo.DatoBasico;
import modelo.IngresoInscripcion;
import modelo.IngresoInscripcionId;
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
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Spinner;
import org.zkoss.zul.Window;

import comun.ConeccionBD;
import comun.MensajeMostrar;
import controlador.general.ManejadorJasper;

import servicio.implementacion.ServicioDatoBasico;
import servicio.implementacion.ServicioIngresoInscripcion;
import servicio.implementacion.ServicioTipoIngreso;

public class CntrlConfigurarPaquete extends GenericForwardComposer {

	Combobox cmbTipoInscripcion, cmbCuotas;
	DatoBasico tipoInscripcion;
	Listbox lbxIngresoInscripcion;
	Spinner sprCantidad, sprAdelantos;
	Window frmConfigurarPaquete;
	Button btnQuitar, btnSalir, btnRegistrar,btnImprimir;
	TipoIngreso cuota;
	List<TipoIngreso> cuotas = new ArrayList<TipoIngreso>();
	List<DatoBasico> listaInscripcion = new ArrayList<DatoBasico>();
	List<IngresoInscripcion> listaIngresoInscripcion = new ArrayList<IngresoInscripcion>();
	List<IngresoInscripcion> listaAuxIngreso = new ArrayList<IngresoInscripcion>();
	IngresoInscripcion ingresoInscripcion = new IngresoInscripcion();;
	AnnotateDataBinder binder;
	ServicioDatoBasico servicioDatoBasico;
	ServicioTipoIngreso servicioTipoIngreso;
	ServicioIngresoInscripcion servicioIngresoInscripcion;
	Component formulario;
	Boolean flag = false;

	// ---------------------------------------------------------------------------------------------------
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		formulario = comp;
		comp.setVariable("cntrl", this, true);
		listaInscripcion = servicioDatoBasico.listarPorPadre("SUBPROCESO",
				servicioDatoBasico.buscarPorString("INSCRIPCION")
						.getCodigoDatoBasico());
		cuotas = servicioTipoIngreso.listarCuotas();
	}

	
	// ------------------------------------------------------------------------------------------------------
	public boolean buscar() {
		int indice = listaIngresoInscripcion.size();
		int i = 0;
		boolean encontrado = false;
		while (i < indice && !encontrado) {
			if (cmbCuotas.getValue().equals(
					listaIngresoInscripcion.get(i).getTipoIngreso()
							.getDescripcion())) {
				encontrado = true;
				return encontrado;
			}
			i++;
		}
		return encontrado;
	}

	// ----------------------------------------------------------------------------------------------------
	public void onChange$cmbTipoInscripcion() {
		listaIngresoInscripcion = servicioIngresoInscripcion
				.listarIngresoInscripcion(((DatoBasico) cmbTipoInscripcion
						.getSelectedItem().getValue()));
		binder.loadComponent(lbxIngresoInscripcion);
	}

	// -------------------------------------------------------------------------------------------------------
	public void onClick$btnAgregar() {
		ingresoInscripcion = new IngresoInscripcion();
		if (cmbCuotas.getText().trim() == "--Seleccione--") {
			throw new WrongValueException(cmbCuotas,
					"Debe Seleccionar una Descripcion o Nombre para el Ingreso");
		} else if (sprCantidad.getText() == "") {
			throw new WrongValueException(sprCantidad,
					"Debe indicar  la Cantidad de pagos requeridos");
		} else if (sprAdelantos.getText() == "") {
			throw new WrongValueException(sprAdelantos,
					"Debe indicar  la Cantidad de pagos adelantados");
		} else {
			if (buscar() == false) {
				DatoBasico codigoInscripcion = (DatoBasico) cmbTipoInscripcion
						.getSelectedItem().getValue();
				TipoIngreso codigoTipoIngreso = (TipoIngreso) cmbCuotas
						.getSelectedItem().getValue();

				ingresoInscripcion.setId(new IngresoInscripcionId(
						codigoTipoIngreso.getCodigoTipoIngreso(),
						codigoInscripcion.getCodigoDatoBasico()));
				ingresoInscripcion
						.setDatoBasico(((DatoBasico) cmbTipoInscripcion
								.getSelectedItem().getValue()));
				ingresoInscripcion.setAdelantos(sprAdelantos.getValue());
				ingresoInscripcion.setCantidad(sprCantidad.getValue());
				ingresoInscripcion.setTipoIngreso((TipoIngreso) cmbCuotas
						.getSelectedItem().getValue());
				ingresoInscripcion.setEstatus('A');
				listaIngresoInscripcion.add(ingresoInscripcion);
				clear();
			} else {
				int indice = listaIngresoInscripcion.size();
				int i = 0;
				boolean encontrado = false;
				while (i < indice && !encontrado) {
					if (cmbCuotas.getValue().equals(
							listaIngresoInscripcion.get(i).getTipoIngreso()
									.getDescripcion())) {
						ingresoInscripcion = listaIngresoInscripcion.get(i);
						ingresoInscripcion
								.setAdelantos(sprAdelantos.getValue());
						ingresoInscripcion.setCantidad(sprCantidad.getValue());
						clear();
						encontrado = true;
					}
					i++;
				}
			}
		}
		btnQuitar.setDisabled(true);
		binder.loadAll();
	}

	// ---------------------------------------------------------------------------------------------------
	public void onClick$btnImprimir() throws SQLException, JRException, IOException{
		Map parametros1 = new HashMap();
		Connection con = ConeccionBD.getCon("postgres","postgres","123456");
		String jrxmlSrc = Sessions.getCurrent().getWebApp().getRealPath("/WEB-INF/reportes/ReportePaquetes.jrxml");
		String nombre="Listado de Paquetes";
		System.out.println(parametros1);
		System.out.println(nombre);
		System.out.println(jrxmlSrc);
		AMedia report = ManejadorJasper.showReportfromJrxml(jrxmlSrc, parametros1, con, nombre);
		//ifReport.setContent(report)	ReportePaquetes
	}
	// ---------------------------------------------------------------------------------------------------
	public void onSelect$lbxIngresoInscripcion() {
		IngresoInscripcion auxIngreso = new IngresoInscripcion();
		auxIngreso = listaIngresoInscripcion.get(lbxIngresoInscripcion
				.getSelectedIndex());
		cmbCuotas.setValue(auxIngreso.getTipoIngreso().getDescripcion());
		cmbTipoInscripcion.setValue(auxIngreso.getDatoBasico().getNombre());
		sprCantidad.setValue(auxIngreso.getCantidad());
		sprAdelantos.setValue(auxIngreso.getAdelantos());
		btnQuitar.setDisabled(false);
	}

	// ---------------------------------------------------------------------------------------------------
	public void onClick$btnQuitar() {
		listaIngresoInscripcion.get(lbxIngresoInscripcion.getSelectedIndex())
				.setEstatus('E');
		listaAuxIngreso.add(listaIngresoInscripcion.get(lbxIngresoInscripcion
				.getSelectedIndex()));
		System.out.println(listaIngresoInscripcion.get(
				lbxIngresoInscripcion.getSelectedIndex()).getEstatus());
		listaIngresoInscripcion
				.remove(lbxIngresoInscripcion.getSelectedIndex());

		binder.loadComponent(lbxIngresoInscripcion);

		btnQuitar.setDisabled(true);
		clear();
	}

	// ---------------------------------------------------------------------------------------------------
	public void clear() {
		cmbCuotas.setText("--Seleccione--");
		btnQuitar.setDisabled(true);
		sprAdelantos.setValue(null);
		sprCantidad.setValue(null);
		binder.loadAll();
	}

	// ---------------------------------------------------------------------------------------------------
	public void onClick$btnCancelar() {
		limpiar();
	}

	// ---------------------------------------------------------------------------------------------------
	public void onClick$btnSalir() {
		frmConfigurarPaquete.detach();
	}

	// ----------------------------------------------------------------------------------------------------
	public void onClick$btnRegistrar() throws InterruptedException {
		Messagebox.show(MensajeMostrar.GUARDAR, MensajeMostrar.REGISTRO_EXITOSO
				+ "Importante", Messagebox.OK | Messagebox.CANCEL,
				Messagebox.QUESTION, new EventListener() {
					@Override
					public void onEvent(Event arg0) throws InterruptedException {
						if (arg0.getName().toString() == "onOK") {
							for (int i = 0; i < listaIngresoInscripcion.size(); i++) {
								servicioIngresoInscripcion
										.agregar(listaIngresoInscripcion.get(i));
							}
							System.out.println("entro");
							clear();
							binder.loadAll();
							Messagebox.show(MensajeMostrar.REGISTRO_EXITOSO,
									MensajeMostrar.TITULO + "Información",
									Messagebox.OK, Messagebox.INFORMATION);
						}
					}
				});

	}

	// ----------------------------------------------------------------------------------------------------
	public void limpiar() {
		clear();
		cmbTipoInscripcion.setValue("--Seleccione--");
		listaIngresoInscripcion = new ArrayList<IngresoInscripcion>();
		binder.loadComponent(lbxIngresoInscripcion);
	}

	// ---------------------------------------------------------------------------------------------------

	public List<DatoBasico> getListaInscripcion() {
		return listaInscripcion;
	}

	public void setListaInscripcion(List<DatoBasico> listaInscripcion) {
		this.listaInscripcion = listaInscripcion;
	}

	public DatoBasico getTipoInscripcion() {
		return tipoInscripcion;
	}

	public void setTipoInscripcion(DatoBasico tipoInscripcion) {
		this.tipoInscripcion = tipoInscripcion;
	}

	public TipoIngreso getCuota() {
		return cuota;
	}

	public void setCuota(TipoIngreso cuota) {
		this.cuota = cuota;
	}

	public List<TipoIngreso> getCuotas() {
		return cuotas;
	}

	public void setCuotas(List<TipoIngreso> cuotas) {
		this.cuotas = cuotas;
	}

	public List<IngresoInscripcion> getListaIngresoInscripcion() {
		return listaIngresoInscripcion;
	}

	public void setListaIngresoInscripcion(
			List<IngresoInscripcion> listaIngresoInscripcion) {
		this.listaIngresoInscripcion = listaIngresoInscripcion;
	}

	public IngresoInscripcion getIngresoInscripcion() {
		return ingresoInscripcion;
	}

	public void setIngresoInscripcion(IngresoInscripcion ingresoInscripcion) {
		this.ingresoInscripcion = ingresoInscripcion;
	}

}
