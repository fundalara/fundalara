package controlador.administracion;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import modelo.ConceptoNomina;
import modelo.DatoBasico;
import modelo.TallaPorIndumentaria;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.*;

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
	Button btnRegistrar, btnSalir, btnAgregar, btnQuitar, btnCancelar,
			btnEditar;
	List<DatoBasico> tipoUniformes, tipoIndumentarias, tipoTallas;
	DatoBasico tipoUniforme, tipoIndumentaria, tipoTalla;
	List<DatoBasico> listaConceptos = new ArrayList<DatoBasico>();
	List<ConceptoNomina> listaAuxConceptos = new ArrayList<ConceptoNomina>();
	Listbox lbxTallasPorIndumentaria;

	// ---------------------------------------------------------------------------------------------------
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		comp.setVariable("cntrl", this, true);
		tipoUniformes = servicioDatoBasico.buscarPorTipoDato(servicioTipoDato
				.buscarTipo("TIPO UNIFORME"));
		tipoIndumentarias = servicioDatoBasico
				.buscarPorTipoDato(servicioTipoDato.buscarTipo("INDUMENTARIA"));
		tallasPorIndumentaria = servicioTallaPorIndumentaria.listar();
	}

	public void onChange$cmbTipoIndumentaria() {
		cmbTipoTalla.setDisabled(false);
		tipoTallas = servicioDatoBasico.listarPorPadre("TALLA INDUMENTARIA", Integer
				.parseInt(cmbTipoIndumentaria.getSelectedItem().getContext()));
		binder.loadComponent(cmbTipoTalla);
	}

	public void onChange$cmbTipoTalla() {
		cmbTipoTalla.setContext(cmbTipoTalla.getSelectedItem().getContext());
	}

	public void onClick$btnRegistrar() {
		for (int i = 0; i < tallasPorIndumentaria.size(); i++) {
			Integer a = tallasPorIndumentaria.get(i).getCodigoTallaIndumentaria();
			System.out.println(a);
			if(a==0){
				tallasPorIndumentaria.get(i).setCodigoTallaIndumentaria(servicioTallaPorIndumentaria.listar().size()+1);
				servicioTallaPorIndumentaria.agregar(tallasPorIndumentaria.get(i));
			}
			else{
				servicioTallaPorIndumentaria.agregar(tallasPorIndumentaria.get(i));
			}
		}
		for (int i = 0; i < eliminados.size(); i++) 
			servicioTallaPorIndumentaria.agregar(eliminados.get(i));
			
		alert("Registrado con Exito");
		clear();
		eliminados = new ArrayList<TallaPorIndumentaria>();
	}

	public void clear() {
		cmbTipoIndumentaria.setValue(null);
		cmbTipoTalla.setValue(null);
		cmbTipoUniforme.setValue(null);
		dbxMonto.setValue(null);
		cmbTipoTalla.setDisabled(true);
		btnEditar.setDisabled(true);
		btnQuitar.setDisabled(true);
		btnAgregar.setDisabled(false);
		binder.loadAll();
	}

	public void onClick$btnAgregar() {
		try {
		TallaPorIndumentaria tallaPorIndumentaria = new TallaPorIndumentaria();

		tallaPorIndumentaria
				.setDatoBasicoByCodigoTalla((DatoBasico) servicioDatoBasico
						.buscarPorCodigo(Integer.parseInt(cmbTipoTalla
								.getContext())));
		tallaPorIndumentaria
				.setDatoBasicoByCodigoTipoUniforme((DatoBasico) cmbTipoUniforme
						.getSelectedItem().getValue());
		tallaPorIndumentaria.setPrecio(dbxMonto.getValue().floatValue());
		tallaPorIndumentaria.setEstatus('A');
		tallasPorIndumentaria.add(tallaPorIndumentaria);
		clear();
		} catch (Exception e) {
			// -----------
		}
	}

	public void onSelect$lbxTallasPorIndumentaria() {
		TallaPorIndumentaria auxTalla = new TallaPorIndumentaria();
		auxTalla = (TallaPorIndumentaria) lbxTallasPorIndumentaria
				.getSelectedItem().getValue();
		for (int i = 0; i < tipoUniformes.size(); i++) {
			if (tipoUniformes.get(i).getCodigoDatoBasico() == auxTalla
					.getDatoBasicoByCodigoTipoUniforme().getCodigoDatoBasico()) {
				cmbTipoUniforme.setSelectedIndex(i);
			}
		}
		tipoTallas = servicioDatoBasico.listarPorPadre("TALLA INDUMENTARIA", auxTalla
				.getDatoBasicoByCodigoTalla().getDatoBasico()
				.getCodigoDatoBasico());
		cmbTipoTalla
				.setValue(auxTalla.getDatoBasicoByCodigoTalla().getNombre());
		cmbTipoTalla.setContext(String.valueOf(auxTalla
				.getDatoBasicoByCodigoTalla().getCodigoDatoBasico()));
		cmbTipoIndumentaria.setValue(auxTalla.getDatoBasicoByCodigoTalla()
				.getDatoBasico().getNombre());
		dbxMonto.setValue(auxTalla.getPrecio());
		cmbTipoTalla.setDisabled(false);
		btnEditar.setDisabled(false);
		btnQuitar.setDisabled(false);
		btnAgregar.setDisabled(true);
		binder.loadComponent(cmbTipoTalla);
	}

	public void onClick$btnEditar() {
		TallaPorIndumentaria auxTalla = tallasPorIndumentaria
				.get(lbxTallasPorIndumentaria.getSelectedIndex());
		auxTalla.setDatoBasicoByCodigoTalla((DatoBasico) servicioDatoBasico
				.buscarPorCodigo(Integer.parseInt(cmbTipoTalla.getContext())));
		auxTalla.setDatoBasicoByCodigoTipoUniforme((DatoBasico) cmbTipoUniforme
				.getSelectedItem().getValue());
		auxTalla.setPrecio(dbxMonto.getValue().floatValue());
		tallasPorIndumentaria.set(lbxTallasPorIndumentaria.getSelectedIndex(),
				auxTalla);
		clear();
	}

	public void onClick$btnQuitar() {
		Integer a = tallasPorIndumentaria.get(lbxTallasPorIndumentaria.getSelectedIndex()).getCodigoTallaIndumentaria();
		if(a==0){
			tallasPorIndumentaria.remove(lbxTallasPorIndumentaria
					.getSelectedIndex());
		}else{
			TallaPorIndumentaria auxTalla = tallasPorIndumentaria.get(lbxTallasPorIndumentaria.getSelectedIndex());
			auxTalla.setEstatus('E');
			eliminados.add(auxTalla);
			tallasPorIndumentaria.remove(lbxTallasPorIndumentaria
					.getSelectedIndex());
		}
		clear();
	}
	
	public void onClick$btnCancelar(){
		clear();
		eliminados = new ArrayList<TallaPorIndumentaria>();
	}

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

}
