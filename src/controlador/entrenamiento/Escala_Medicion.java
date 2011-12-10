package controlador.entrenamiento;

import java.util.ArrayList;
import java.util.List;

import modelo.EscalaMedicion;
import modelo.TipoEscalaMedicion;
import modelo.ValorEscalaMedicion;

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

import servicio.entrenamiento.ServicioEscalaMedicion;
import servicio.entrenamiento.ServicioTipoEscalaMedicion;
import servicio.entrenamiento.ServicioValorEscalaMedicion;

public class Escala_Medicion extends GenericForwardComposer {
	Button btnAgregar, btnCancelar, btnSalir, btnModificar, btnQuitar,
			btnConsultar;
	Window wndEscalaMedicion, form;
	Listbox lbValoresEscala;
	Combobox cmbTipoEscala;
	Textbox txtEscala, txtDescripcionEscala, txtValorEscala,
			txtDescripcionValor;
	ServicioEscalaMedicion servicioEscalaMedicion;
	ServicioTipoEscalaMedicion servicioTipoEscalaMedicion;
	ServicioValorEscalaMedicion servicioValorEscalaMedicion;
	AnnotateDataBinder binder;
	TipoEscalaMedicion tem;
	EscalaMedicion escala;
	ValorEscalaMedicion valor;
	int pos = 0;
	List<TipoEscalaMedicion> tipoEscala;
	List<ValorEscalaMedicion> lista;

	public void llenarCombos() {
		Comboitem item;
		for (int i = 0; i < tipoEscala.size(); i++) {
			item = new Comboitem();
			item.setLabel(tipoEscala.get(i).getNombre().toString());
			item.setId(tipoEscala.get(i).getIdTipoEscalaMedicion().toString());
			cmbTipoEscala.appendChild(item);
		}
	}

	public List<TipoEscalaMedicion> getTipoEscala() {
		return tipoEscala;
	}

	public void setTipoEscala(List<TipoEscalaMedicion> tipoEscala) {
		this.tipoEscala = tipoEscala;
	}

	public void traeAlgo(){
		List<EscalaMedicion> lis = servicioEscalaMedicion.buscar('E');
	}
	
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		comp.setAttribute("cntrl", this);
		escala = new EscalaMedicion();
		tipoEscala = servicioTipoEscalaMedicion.listar();
		llenarCombos();
		List<EscalaMedicion> lista = servicioEscalaMedicion.listar();
		int codigo;
		if (lista == null) {
			codigo = 0;
		} else {
			codigo = lista.size();
		}
		escala.setCodEscalaMedicion(String.valueOf(codigo + 1));
		escala.setEstatus('A');
	}

	public EscalaMedicion getEscala() {
		return escala;
	}

	public void setEscala(EscalaMedicion escala) {
		this.escala = escala;
	}

	public ValorEscalaMedicion getValor() {
		return valor;
	}

	public void setValor(ValorEscalaMedicion valor) {
		this.valor = valor;
	}

	public void agregarEscala() {
		// Agrego la escala de medicion
		tem = tipoEscala.get(cmbTipoEscala.getSelectedIndex());
		escala.setTipoEscalaMedicion(tem);
		escala.setDescripcion(txtDescripcionEscala.getValue().toString());
		escala.setNombre(txtEscala.getValue().toString());
		servicioEscalaMedicion.agregar(escala);
	}

	public void agregarValorEscala() {
		// Agregar cada valor a la escala de medicion
		int cod = servicioValorEscalaMedicion.listar().size();
		valor = new ValorEscalaMedicion();
		valor.setCodValor(String.valueOf(cod + 1));
		valor.setEscalaMedicion(escala);
		valor.setValor(txtValorEscala.getValue().toString());
		valor.setDescripcion(txtDescripcionValor.getValue().toString());
		//valor.setEstatus('A');
		servicioValorEscalaMedicion.agregar(valor);
	}

	public void onClick$btnAgregar() {
		agregarEscala();
		agregarValorEscala();
		Listitem nvoItem = new Listitem();
		nvoItem.appendChild(new Listcell(txtValorEscala.getValue()));
		nvoItem.appendChild(new Listcell(txtDescripcionValor.getValue()));
		lbValoresEscala.appendChild(nvoItem);
		valor = new ValorEscalaMedicion();
		binder.loadAll();
	}

	public void onClick$btnConsultar() {
		form = (Window) Executions
				.createComponents(
						"Entrenamiento/Vistas/Catalogo_Escala_Medicion.zul",
						null, null);
		form.doHighlighted();
	}

	public void onClick$btnCancelar() {
		txtValorEscala.setValue("");
		txtDescripcionValor.setValue("");
		cmbTipoEscala.setValue("--Seleccione--");
		txtEscala.setValue("");
		txtDescripcionEscala.setValue("");
	}

	public void onClick$btnSalir() {
		wndEscalaMedicion.detach();
	}

	public void onClick$btnQuitar() {
		
		lista = servicioValorEscalaMedicion.listar();
		int i = 0;
		int n = lbValoresEscala.getSelectedIndex();
		
		while (i <= lista.size()) {
			if ((lista.get(i).getEscalaMedicion().getCodEscalaMedicion() == escala
					.getCodEscalaMedicion())
					&& lista.get(i).getValor() == lbValoresEscala.getSelectedItem().getLabel()) {
				//lista.get(i).setEstatus('E');
				servicioValorEscalaMedicion.actualizar(lista.get(i));
				break;
			}
			i++;
		}
		lbValoresEscala.removeItemAt(n);
	}

	public void onClick$btnModificar() {
		if (lbValoresEscala.getSelectedIndex() >= 0) {
			Listcell lc1 = (Listcell) lbValoresEscala.getSelectedItem()
					.getChildren().get(0);
			Listcell lc2 = (Listcell) lbValoresEscala.getSelectedItem()
					.getChildren().get(1);

			txtValorEscala.setText(lc1.getLabel());
			txtDescripcionValor.setText(lc2.getLabel());

			lbValoresEscala.removeItemAt(lbValoresEscala.getSelectedIndex());
			txtValorEscala.focus();
		}
	}
}
