package controlador.administracion;

import java.io.IOException;
import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import modelo.ConceptoNomina;
import modelo.DatoBasico;
import modelo.Material;
import modelo.TallaPorIndumentaria;
import net.sf.jasperreports.engine.JRException;

import org.zkoss.util.media.AMedia;
import org.zkoss.zhtml.Messagebox;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.*;

import comun.ConeccionBD;
import comun.MensajeMostrar;
import controlador.general.ManejadorJasper;

import servicio.implementacion.ServicioConceptoNomina;
import servicio.implementacion.ServicioDatoBasico;
import servicio.implementacion.ServicioTallaPorIndumentaria;
import servicio.implementacion.ServicioTipoDato;

public class CntrlActualizarIndumentaria extends GenericForwardComposer {
	Window frmActualizarConceptoNomina;
	ServicioTipoDato servicioTipoDato;
	ServicioDatoBasico servicioDatoBasico;
	ServicioTallaPorIndumentaria servicioTallaPorIndumentaria;
	AnnotateDataBinder binder;
	Textbox txtDescripcion;
	Combobox cmbTipoUniforme, cmbTipoIndumentaria, cmbTipoTalla;
	Doublebox dbxMonto;
	List<TallaPorIndumentaria> tallasPorIndumentaria = new ArrayList<TallaPorIndumentaria>();
	List<TallaPorIndumentaria> eliminados = new ArrayList<TallaPorIndumentaria>();
	TallaPorIndumentaria tallaPorIndumentaria;
	Button btnRegistrar, btnSalir, btnAgregar, btnQuitar, btnCancelar,btnImprimir;
	List<DatoBasico> tipoUniformes, tipoIndumentarias, tipoTallas;
	DatoBasico tipoUniforme, tipoIndumentaria, tipoTalla;
	Listbox lbxTallasPorIndumentaria;
	
	// ---------------------------------------------------------------------------------------------------
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		comp.setVariable("cntrl", this, true);
		tipoUniformes = servicioDatoBasico.buscarPorTipoDato(servicioTipoDato
				.buscarTipo("TIPO UNIFORME"));
		tipoIndumentarias = servicioDatoBasico
				.buscarPorTipoDato(servicioTipoDato.buscarTipo("INDUMENTARIA"));
		tallasPorIndumentaria = servicioTallaPorIndumentaria
				.listarActivosOrdenados();
		btnQuitar.setDisabled(true);
	}

	// ---------------------------------------------------------------------------------------------------
	public void onChange$cmbTipoIndumentaria() {
		cmbTipoTalla.setDisabled(false);
		tipoTallas = servicioDatoBasico.listarPorPadre("TALLA INDUMENTARIA",
				Integer.parseInt(cmbTipoIndumentaria.getSelectedItem()
						.getContext()));
		binder.loadComponent(cmbTipoTalla);
	}

	// ---------------------------------------------------------------------------------------------------
	public void onChange$cmbTipoTalla() {
		cmbTipoTalla.setContext(cmbTipoTalla.getSelectedItem().getContext());
	}
	
	// ---------------------------------------------------------------------------------------------------
	public void onClick$btnImprimir() throws SQLException, JRException, IOException{
		Map parametros1 = new HashMap();
		Connection con = ConeccionBD.getCon("postgres","postgres","123456");
		String jrxmlSrc = Sessions.getCurrent().getWebApp().getRealPath("/WEB-INF/reportes/ReporteUniformes.jrxml");
		String nombre="Listado de Uniforme";
		System.out.println(parametros1);
		System.out.println(nombre);
		System.out.println(jrxmlSrc);
		AMedia report = ManejadorJasper.showReportfromJrxml(jrxmlSrc, parametros1, con, nombre);
		
	}

	// ---------------------------------------------------------------------------------------------------
	public void onClick$btnRegistrar() {
		try {
			Messagebox.show(MensajeMostrar.GUARDAR,
					MensajeMostrar.TITULO.toString() + "Importante",
					Messagebox.OK | Messagebox.CANCEL, Messagebox.QUESTION,
					new EventListener() {
						@Override
						public void onEvent(Event arg0)
								throws InterruptedException {
							if (arg0.getName().toString() == "onOK") {
								for (int i = 0; i < tallasPorIndumentaria
										.size(); i++) {
									Integer a = tallasPorIndumentaria.get(i)
											.getCodigoTallaIndumentaria();
									System.out.println(a);
									if (a == 0) {
										tallasPorIndumentaria.get(i)
												.setCodigoTallaIndumentaria(
														servicioTallaPorIndumentaria
																.listar()
																.size() + 1);
										servicioTallaPorIndumentaria
												.agregar(tallasPorIndumentaria
														.get(i));
									} else {
										servicioTallaPorIndumentaria
												.agregar(tallasPorIndumentaria
														.get(i));
									}
								}
								for (int i = 0; i < eliminados.size(); i++)
									servicioTallaPorIndumentaria
											.agregar(eliminados.get(i));

								Messagebox.show(MensajeMostrar.PROCESO_EXITOSO, MensajeMostrar.TITULO
										+ "Información", Messagebox.OK,
										Messagebox.INFORMATION);
								clear();
								eliminados = new ArrayList<TallaPorIndumentaria>();
								binder.loadAll();
							}
						}
					});
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// ---------------------------------------------------------------------------------------------------
	public void clear() {
		cmbTipoIndumentaria.setValue("--Seleccione--");
		cmbTipoTalla.setValue("--Seleccione--");
		cmbTipoUniforme.setValue("--Seleccione--");
		dbxMonto.setValue(null);
		cmbTipoTalla.setDisabled(true);
		btnQuitar.setDisabled(true);
		binder.loadAll();
	}

	// ---------------------------------------------------------------------------------------------------
	public void onClick$btnAgregar() {
		if (cmbTipoUniforme.getSelectedIndex() == -1) {
			throw new WrongValueException(cmbTipoUniforme,
					"Seleccione el Tipo de Uniforme");
		} else if (cmbTipoIndumentaria.getSelectedIndex() == -1) {
			throw new WrongValueException(cmbTipoIndumentaria,
					"Seleccione el Uniforme");
		} else if (cmbTipoTalla.getValue() == null
				|| cmbTipoTalla.getValue() == "--Seleccione--") {
			throw new WrongValueException(cmbTipoTalla,
					"Seleccione la Talla del Uniforme");
		} else if (dbxMonto.getValue() == null || dbxMonto.getValue() <= 0) {
			throw new WrongValueException(dbxMonto, "Indique el Monto");
		} else {
			TallaPorIndumentaria tallaPorIndumentaria = new TallaPorIndumentaria();
			if (lbxTallasPorIndumentaria.getItemCount() == 0) {
				tallaPorIndumentaria
						.setDatoBasicoByCodigoTalla((DatoBasico) servicioDatoBasico
								.buscarPorCodigo(Integer.parseInt(cmbTipoTalla
										.getContext())));
				tallaPorIndumentaria
						.setDatoBasicoByCodigoTipoUniforme((DatoBasico) cmbTipoUniforme
								.getSelectedItem().getValue());
				tallaPorIndumentaria
						.setPrecio(dbxMonto.getValue().floatValue());
				tallaPorIndumentaria.setEstatus('A');

			} else {
				int i = 0;
				do {
					System.out.println(i);
					/*Listcell celda1 = (Listcell) lbxTallasPorIndumentaria
							.getItemAtIndex(i).getChildren().get(0);
					Listcell celda2 = (Listcell) lbxTallasPorIndumentaria
							.getItemAtIndex(i).getChildren().get(2);*/
					if (cmbTipoUniforme.getSelectedItem().getLabel()
							.equals(tallasPorIndumentaria.get(i).getDatoBasicoByCodigoTipoUniforme().getNombre())
							&& cmbTipoTalla.getValue()
									.equals(tallasPorIndumentaria.get(i).getDatoBasicoByCodigoTalla().getNombre())) {
						tallasPorIndumentaria.get(i).setPrecio(
								dbxMonto.getValue().floatValue());
						binder.loadComponent(lbxTallasPorIndumentaria);
						clear();
						return;
					}
					i++;
				} while (i < lbxTallasPorIndumentaria.getItemCount());

				tallaPorIndumentaria
						.setDatoBasicoByCodigoTalla((DatoBasico) servicioDatoBasico
								.buscarPorCodigo(Integer.parseInt(cmbTipoTalla
										.getContext())));
				tallaPorIndumentaria
						.setDatoBasicoByCodigoTipoUniforme((DatoBasico) cmbTipoUniforme
								.getSelectedItem().getValue());
				tallaPorIndumentaria
						.setPrecio(dbxMonto.getValue().floatValue());
				tallaPorIndumentaria.setEstatus('A');
			}

			tallasPorIndumentaria.add(tallaPorIndumentaria);
			btnQuitar.setDisabled(true);
			binder.loadComponent(lbxTallasPorIndumentaria);
			clear();
		}
	}

	// ---------------------------------------------------------------------------------------------------
	public void onSelect$lbxTallasPorIndumentaria() {
		btnQuitar.setDisabled(false);
		TallaPorIndumentaria auxTalla = new TallaPorIndumentaria();
		auxTalla = (TallaPorIndumentaria) lbxTallasPorIndumentaria
				.getSelectedItem().getValue();
		for (int i = 0; i < tipoUniformes.size(); i++) {
			if (tipoUniformes.get(i).getCodigoDatoBasico() == auxTalla
					.getDatoBasicoByCodigoTipoUniforme().getCodigoDatoBasico()) {
				cmbTipoUniforme.setSelectedIndex(i);
			}
		}
		tipoTallas = servicioDatoBasico.listarPorPadre("TALLA INDUMENTARIA",
				auxTalla.getDatoBasicoByCodigoTalla().getDatoBasico()
						.getCodigoDatoBasico());
		cmbTipoTalla
				.setValue(auxTalla.getDatoBasicoByCodigoTalla().getNombre());
		cmbTipoTalla.setContext(String.valueOf(auxTalla
				.getDatoBasicoByCodigoTalla().getCodigoDatoBasico()));
		cmbTipoIndumentaria.setValue(auxTalla.getDatoBasicoByCodigoTalla()
				.getDatoBasico().getNombre());
		dbxMonto.setValue(auxTalla.getPrecio());
		cmbTipoTalla.setDisabled(false);
		btnQuitar.setDisabled(false);
		binder.loadComponent(cmbTipoTalla);
	}

	// ---------------------------------------------------------------------------------------------------
	public void onClick$btnQuitar() {
		Integer a = tallasPorIndumentaria.get(
				lbxTallasPorIndumentaria.getSelectedIndex())
				.getCodigoTallaIndumentaria();
		if (a == 0) {
			tallasPorIndumentaria.remove(lbxTallasPorIndumentaria
					.getSelectedIndex());
		} else {
			TallaPorIndumentaria auxTalla = tallasPorIndumentaria
					.get(lbxTallasPorIndumentaria.getSelectedIndex());
			auxTalla.setEstatus('E');
			eliminados.add(auxTalla);
			tallasPorIndumentaria.remove(lbxTallasPorIndumentaria
					.getSelectedIndex());
		}
		clear();
	}

	// ---------------------------------------------------------------------------------------------------
	public void onClick$btnCancelar() {
		clear();
		eliminados = new ArrayList<TallaPorIndumentaria>();
		binder.loadAll();
	}

	// ---------------------------------------------------------------------------------------------------
	public List<TallaPorIndumentaria> getTallasPorIndumentaria() {
		return tallasPorIndumentaria;
	}

	public void setTallasPorIndumentaria(
			List<TallaPorIndumentaria> tallasPorIndumentaria) {
		this.tallasPorIndumentaria = tallasPorIndumentaria;
	}

	public TallaPorIndumentaria getTallaPorIndumentaria() {
		return tallaPorIndumentaria;
	}

	public void setTallaPorIndumentaria(
			TallaPorIndumentaria tallaPorIndumentaria) {
		this.tallaPorIndumentaria = tallaPorIndumentaria;
	}

	public List<DatoBasico> getTipoUniformes() {
		return tipoUniformes;
	}

	public void setTipoUniformes(List<DatoBasico> tipoUniformes) {
		this.tipoUniformes = tipoUniformes;
	}

	public List<DatoBasico> getTipoIndumentarias() {
		return tipoIndumentarias;
	}

	public void setTipoIndumentarias(List<DatoBasico> tipoIndumentarias) {
		this.tipoIndumentarias = tipoIndumentarias;
	}

	public List<DatoBasico> getTipoTallas() {
		return tipoTallas;
	}

	public void setTipoTallas(List<DatoBasico> tipoTallas) {
		this.tipoTallas = tipoTallas;
	}

	public DatoBasico getTipoUniforme() {
		return tipoUniforme;
	}

	public void setTipoUniforme(DatoBasico tipoUniforme) {
		this.tipoUniforme = tipoUniforme;
	}

	public DatoBasico getTipoIndumentaria() {
		return tipoIndumentaria;
	}

	public void setTipoIndumentaria(DatoBasico tipoIndumentaria) {
		this.tipoIndumentaria = tipoIndumentaria;
	}

	public DatoBasico getTipoTalla() {
		return tipoTalla;
	}

	public void setTipoTalla(DatoBasico tipoTalla) {
		this.tipoTalla = tipoTalla;
	}
	// ---------------------------------------------------------------------------------------------------
}
