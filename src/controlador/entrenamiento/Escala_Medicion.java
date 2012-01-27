package controlador.entrenamiento;

import java.util.ArrayList;
import java.util.List;

import modelo.DatoBasico;
import modelo.EscalaMedicion;
import modelo.TipoDato;
import modelo.ValorEscala;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import servicio.implementacion.ServicioDatoBasico;
import servicio.implementacion.ServicioEscalaMedicion;
import servicio.implementacion.ServicioTipoDato;
import servicio.implementacion.ServicioValorEscala;


public class Escala_Medicion extends GenericForwardComposer {
	Button btnAgregar, btnCancelar, btnSalir, btnModificar, btnQuitar,
			btnConsultar;
	Window wndEscalaMedicion, form;
	Listbox lbValoresEscala;
	Combobox cmbTipoEscala, cmbEscala;
	Textbox txtEscala, txtDescripcionEscala, txtValorEscala,
			txtDescripcionValor;

	ServicioEscalaMedicion servicioEscalaMedicion;
	ServicioValorEscala servicioValorEscalaMedicion;
	ServicioDatoBasico servicioDatoBasico;
	ServicioTipoDato servicioTipoDato;

	AnnotateDataBinder binder;

	DatoBasico tem;
	EscalaMedicion escala;
	ValorEscala valor;

	int pos = 0;
	boolean modificando = false;
	List<DatoBasico> tipoEscala;
	List<ValorEscala> lista;
	List<EscalaMedicion> listaEscala;

	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		comp.setVariable("cntrl", this, true);
		escala = new EscalaMedicion();
		System.out.println(servicioTipoDato.buscarTipo("ESCALA MEDICION").getDescripcion());
		TipoDato td = servicioTipoDato.buscarTipo("ESCALA MEDICION");
		tipoEscala = servicioDatoBasico.buscarPorTipoDato(td);
		cargarListaEscala();
		List<EscalaMedicion> lista = servicioEscalaMedicion.listar();
		int codigo;
		if (lista == null) {
			codigo = 0;
		} else {
			codigo = lista.size();
		}
		escala.setCodigoEscala(codigo + 1);
		escala.setEstatus('A');
	}
	
	public void cargarListaEscala() {
		cmbEscala.setReadonly(false);
		listaEscala = servicioEscalaMedicion.listar();
		cmbEscala.setReadonly(true);
	}

	public void onCreate$wndEscalaMedicion() {
		onClick$btnCancelar();
	}

	public void inicializar() {
		txtValorEscala.setValue("");
		txtDescripcionValor.setValue("");
	}

	public List<DatoBasico> getTipoEscala() {
		return tipoEscala;
	}

	public void setTipoEscala(List<DatoBasico> tipoEscala) {
		this.tipoEscala = tipoEscala;
	}

	public EscalaMedicion getEscala() {
		return escala;
	}

	public void setEscala(EscalaMedicion escala) {
		this.escala = escala;
	}

	public List<EscalaMedicion> getListaEscala() {
		return listaEscala;
	}

	public void setListaEscala(List<EscalaMedicion> listaEscala) {
		this.listaEscala = listaEscala;
	}

	public ValorEscala getValor() {
		return valor;
	}

	public void setValor(ValorEscala valor) {
		this.valor = valor;
	}

	public void agregarEscala() {
		// Agrego la escala de medicion
		System.out.println(cmbTipoEscala.getSelectedItem().getValue());
		tem = servicioDatoBasico.buscarPorCodigo(cmbTipoEscala.getSelectedItem().getValue().toString());
		escala.setDatoBasico(tem);
		escala.setDescripcion(txtDescripcionEscala.getValue().toString());
		escala.setNombre(txtEscala.getValue().toString());
		servicioEscalaMedicion.agregar(escala);
	}

	public void agregarValorEscala() {
		// Agregar cada valor a la escala de medicion
//		List<ValorEscala> list = servicioValorEscalaMedicion.listar();
//		int codigo;
//		if (list == null) {
//			codigo = 0;
//		} else {
//			codigo = list.size();
//		}
//		valor = new ValorEscala();
//		valor.setCodigoValorEscala(String.valueOf(codigo));
//		valor.setEscalaMedicion(escala);
//		valor.setNombre(txtValorEscala.getValue().toString());
//		valor.setDescripcion(txtDescripcionValor.getValue().toString());
//		valor.setEstatus('A');
//		servicioValorEscalaMedicion.agregar(valor);

	}

	public void onClick$btnAgregar() {
		if (modificando) {
			valor.setValor(txtValorEscala.getValue().toString());
			valor.setDescripcion(txtDescripcionValor.getValue().toString());
			servicioValorEscalaMedicion.agregar(valor);
			modificando = false;
		} else {
			agregarEscala();
			agregarValorEscala();
		}
		Listitem nvoItem = new Listitem();
		nvoItem.appendChild(new Listcell(txtValorEscala.getValue()));
		nvoItem.appendChild(new Listcell(txtDescripcionValor.getValue()));
		lbValoresEscala.appendChild(nvoItem);
		valor = new ValorEscala();
		inicializar();
		txtValorEscala.focus();
		cargarListaEscala();
	}

	public void onClick$btnConsultar() {
		cmbEscala.setVisible(true);

	}

	public void onClick$btnCancelar() {
		txtValorEscala.setValue("");
		txtDescripcionValor.setValue("");
		cmbTipoEscala.setValue("--Seleccione--");
		txtEscala.setValue("");
		txtDescripcionEscala.setValue("");
		btnModificar.setVisible(false);
		btnQuitar.setVisible(false);
		if (lbValoresEscala.getItems().size() > 0) {
			int numItems = lbValoresEscala.getItems().size();
			for (int i = 0; i < numItems; i++) {
				lbValoresEscala.removeItemAt(0);
			}
		}
		txtValorEscala.setDisabled(true);
		txtDescripcionValor.setDisabled(true);
		cmbTipoEscala.setDisabled(true);
		txtDescripcionEscala.setDisabled(true);
		btnCancelar.setVisible(false);
		cmbEscala.setSelectedIndex(-1);
		cmbEscala.setVisible(false);
		cargarListaEscala();
	}

	public void onClick$btnSalir() {
		wndEscalaMedicion.detach();
	}

	public void onClick$btnQuitar() {

		lista = servicioValorEscalaMedicion.listar();
		int i = 0;
		int n = lbValoresEscala.getSelectedIndex();

		while (i <= lista.size()) {
			if ((lista.get(i).getEscalaMedicion().getCodigoEscala() == escala
					.getCodigoEscala())
					&& lista.get(i).getValor() == lbValoresEscala
							.getSelectedItem().getLabel()) {
				lista.get(i).setEstatus('E');
				servicioValorEscalaMedicion.actualizar(lista.get(i));
				break;
			}
			i++;
		}
		lbValoresEscala.removeItemAt(n);
	}

	public void onSelect$lbValoresEscala() {
		if (lbValoresEscala.getItems().size() > 0) {
			btnModificar.setVisible(true);
			btnQuitar.setVisible(true);

		}
	}

	public void onChanging$txtEscala() {
		txtDescripcionEscala.setDisabled(false);
		btnCancelar.setVisible(true);
	}

	public void onChanging$txtDescripcionEscala() {
		cmbTipoEscala.setDisabled(false);
	}

	public void onChange$cmbTipoEscala() {
		txtValorEscala.setDisabled(false);
		txtDescripcionValor.setDisabled(false);

	}

	public void onChange$cmbEscala() {
		escala = servicioEscalaMedicion.buscar(cmbEscala.getSelectedItem()
				.getValue().toString());
		
		btnCancelar.setVisible(true);
		txtEscala.setValue(escala.getNombre());
		txtDescripcionEscala.setValue(escala.getDescripcion());
		cmbTipoEscala.setValue(escala.getDatoBasico().getNombre());
		
		txtValorEscala.setDisabled(false);
		txtDescripcionValor.setDisabled(false);
		cmbTipoEscala.setDisabled(false);
		txtDescripcionEscala.setDisabled(false);
		
		if (lbValoresEscala.getItems().size() > 0) {
			int numItems = lbValoresEscala.getItems().size();
			for (int i = 0; i < numItems; i++) {
				lbValoresEscala.removeItemAt(0);
			}
		}
		
		ValorEscala v;
		for (Object o: escala.getValorEscalas()) {
			v=(ValorEscala)o;
			Listitem nvoItem = new Listitem();
			nvoItem.appendChild(new Listcell(v.getValor()));
			nvoItem.appendChild(new Listcell(v.getDescripcion()));
			lbValoresEscala.appendChild(nvoItem);
		}	
		
	}

	public void onClick$btnModificar() {
		if (lbValoresEscala.getSelectedIndex() >= 0) {
			Listcell lc1 = (Listcell) lbValoresEscala.getSelectedItem()
					.getChildren().get(0);
			Listcell lc2 = (Listcell) lbValoresEscala.getSelectedItem()
					.getChildren().get(1);

			txtValorEscala.setText(lc1.getLabel());
			txtDescripcionValor.setText(lc2.getLabel());
			lista = servicioValorEscalaMedicion.listar();
			int i = 0;
			while (i <= lista.size()) {
				if ((lista.get(i).getEscalaMedicion().getCodigoEscala() == escala
						.getCodigoEscala())
						&& lista.get(i).getValor() == lbValoresEscala
								.getSelectedItem().getLabel()) {
					valor = lista.get(i);
					modificando = true;
					break;
				}
				i++;
			}

			servicioValorEscalaMedicion.actualizar(lista.get(i));
			lbValoresEscala.removeItemAt(lbValoresEscala.getSelectedIndex());
			txtValorEscala.focus();
		}
	}
}
