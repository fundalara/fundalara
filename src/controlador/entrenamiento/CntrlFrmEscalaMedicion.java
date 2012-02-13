package controlador.entrenamiento;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import modelo.DatoBasico;
import modelo.EscalaMedicion;
import modelo.TipoDato;
import modelo.ValorEscala;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import servicio.implementacion.ServicioDatoBasico;
import servicio.implementacion.ServicioEscalaMedicion;
import servicio.implementacion.ServicioTipoDato;
import servicio.implementacion.ServicioValorEscala;

public class CntrlFrmEscalaMedicion extends GenericForwardComposer {
	Button btnAgregar, btnCancelar, btnSalir, btnQuitar, btnConsultar;
	Window wndEscalaMedicion, winCatalogo;
	Listbox lboxValoresEscala, lboxEscalas;
	Label lbDescripcionTipo;
	Combobox cmbTipoEscala;
	Textbox txtNombreEscala, txtDescripcionEscala, txtValorEscala,
			txtDescripcionValor;
	ServicioEscalaMedicion servicioEscalaMedicion;
	ServicioValorEscala servicioValorEscala;
	ServicioDatoBasico servicioDatoBasico;
	ServicioTipoDato servicioTipoDato;
	AnnotateDataBinder binder;
	EscalaMedicion escalaMedicion;
	ValorEscala valorEscala;
	int pos = 0;
	boolean modificando = false;
	List<DatoBasico> listTipoEscala;
	List<ValorEscala> listValoresEscala;
	List<EscalaMedicion> listEscalaMedicion;
	CntrlFrmCatalogoEscalaMedicion medicion;

	public CntrlFrmCatalogoEscalaMedicion getMedicion() {
		return medicion;
	}

	public void setMedicion(CntrlFrmCatalogoEscalaMedicion medicion) {
		this.medicion = medicion;
	}

	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		comp.setVariable("cntrl", this, true);
		cmbTipoEscala.setValue("--Seleccione--");
		listTipoEscala = new ArrayList<DatoBasico>();
		listValoresEscala = new ArrayList<ValorEscala>();
		listEscalaMedicion = new ArrayList<EscalaMedicion>();
		TipoDato td = servicioTipoDato.buscarPorTipo("ESCALA MEDICION");
		listTipoEscala = servicioDatoBasico.buscarPorTipoDato(td);
	}

	public List<ValorEscala> getListValoresEscala() {
		return listValoresEscala;
	}

	public void setListValoresEscala(List<ValorEscala> listValoresEscala) {
		this.listValoresEscala = listValoresEscala;
	}

	public ValorEscala getValorEscala() {
		return valorEscala;
	}

	public void setValorEscala(ValorEscala valorEscala) {
		this.valorEscala = valorEscala;
	}

	public List<EscalaMedicion> getListEscalaMedicion() {
		return listEscalaMedicion;
	}

	public void setListEscalaMedicion(List<EscalaMedicion> listEscalaMedicion) {
		this.listEscalaMedicion = listEscalaMedicion;
	}

	public List<DatoBasico> getListTipoEscala() {
		return listTipoEscala;
	}

	public void setListTipoEscala(List<DatoBasico> listTipoEscala) {
		this.listTipoEscala = listTipoEscala;
	}

	public EscalaMedicion getEscalaMedicion() {
		return escalaMedicion;
	}

	public void setEscalaMedicion(EscalaMedicion escalaMedicion) {
		this.escalaMedicion = escalaMedicion;
	}

	public void limpiarTxtEscalaMedicion() {
		txtDescripcionEscala.setValue("");
		txtNombreEscala.setValue("");
	}

	public void limpiarTxtValoresEscala() {
		txtValorEscala.setValue("");
		txtDescripcionValor.setValue("");
	}

	public void agregarEscala() {
		// Agrego la escala de medicion
		if (servicioEscalaMedicion.buscar(escalaMedicion.getCodigoEscala()) == null) {
			escalaMedicion.setCodigoEscala(servicioEscalaMedicion
					.generarCodigo());
			escalaMedicion.setDatoBasico((DatoBasico) cmbTipoEscala
					.getSelectedItem().getValue());
			escalaMedicion.setEstatus('A');
			servicioEscalaMedicion.agregar(escalaMedicion);
		}
	}

	public void agregarValorEscala() {
		// Agregar cada valor a la escala de medicion
		valorEscala.setCodigoValorEscala(servicioValorEscala.generarCodigo());
		valorEscala.setEscalaMedicion(escalaMedicion);
		valorEscala.setEstatus('A');
		servicioValorEscala.agregar(valorEscala);
	}

	public void onChanging$txtNombreEscala() {
		txtDescripcionEscala.setReadonly(false);
	}

	public void onChanging$txtDescripcionEscala() {
		txtValorEscala.setReadonly(false);
		txtDescripcionValor.setReadonly(false);
	}

	public void onChange$cmbTipoEscala() {
		txtNombreEscala.setReadonly(false);
		txtNombreEscala.setReadonly(false);
		txtNombreEscala.setValue("");
		txtDescripcionEscala.setValue("");
		DatoBasico tipo = (DatoBasico) cmbTipoEscala.getSelectedItem().getValue();
		lbDescripcionTipo.setValue(tipo.getDescripcion());
		escalaMedicion = new EscalaMedicion();
		valorEscala = new ValorEscala();
		/* Extra */
		binder.loadAll();
	}

	public void onSelect$lboxValoresEscala() {
		if (lboxValoresEscala.getItems().size() > 0) {
			valorEscala = (ValorEscala) lboxValoresEscala.getSelectedItem()
					.getValue();
			pos = lboxValoresEscala.getSelectedItem().getIndex();
			txtDescripcionValor.setValue(valorEscala.getDescripcion());
			txtValorEscala.setValue(valorEscala.getValor());
			modificando = true;
		} else {
		}
	}

	public void onClick$btnAgregar() {
		if (cmbTipoEscala.getValue().compareTo("--Seleccione--") == 0) {
			alert("Debe seleccionar un tipo de escala");
		} else if (txtNombreEscala.getValue().isEmpty()) {
			alert("Debes ingresar un nombre a la escala");
		} else if (txtDescripcionEscala.getValue().isEmpty()) {
			alert("Debes ingresar una descripcion a la escala");
		} else if (txtValorEscala.getValue().isEmpty()) {
			alert("Debes ingresar un valor a la escala");
		} else if (txtDescripcionValor.getValue().isEmpty()) {
			alert("Debes ingresar una descripcion al valor de la escala");
		} else {
			if (!txtNombreEscala.isReadonly()) {
				txtNombreEscala.setReadonly(true);
				txtDescripcionEscala.setReadonly(true);
			}
			if (modificando) {
				listValoresEscala.set(pos, valorEscala);
				servicioValorEscala.actualizar(valorEscala);
				modificando = false;
			} else {
				agregarEscala();
				agregarValorEscala();
				listValoresEscala.add(valorEscala);
				cmbTipoEscala.setDisabled(true);
			}
			valorEscala = new ValorEscala();
			binder.loadAll();
			limpiarTxtValoresEscala();
			txtValorEscala.focus();
		}
	}

	public void onClick$btnConsultar() {
		if (cmbTipoEscala.getValue().compareTo("--Seleccione--") == 0) {
			alert("Debe seleccionar un tipo de escala");
		} else {
			execution.setAttribute("tipoEscala", cmbTipoEscala
					.getSelectedItem().getValue());
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("ModuleMainController", this);
			Window win = (Window) execution.createComponents(
					"Entrenamiento/Vistas/frmCatalogoEscalaMedicion.zul", null,
					map);
			win.doHighlighted();
		}
	}

	public void onClick$btnCancelar() {
		cmbTipoEscala.setValue("--Seleccione--");
		cmbTipoEscala.setDisabled(false);
		valorEscala = new ValorEscala();
		escalaMedicion = new EscalaMedicion();
		listValoresEscala.clear();
		txtNombreEscala.setReadonly(true);
		txtDescripcionEscala.setReadonly(true);
		txtValorEscala.setReadonly(true);
		txtDescripcionValor.setReadonly(true);
		lbDescripcionTipo.setValue("");
		binder.loadAll();
	}

	public void onClick$btnSalir() throws InterruptedException{
		if (valorEscala != null || escalaMedicion != null) {
			int result = Messagebox.show("Existen elementos en el formulario ¿Realmente desea salir?","Question", Messagebox.OK | Messagebox.CANCEL,	Messagebox.QUESTION);
			switch (result) {
			case Messagebox.OK:				
				escalaMedicion = new EscalaMedicion();
				valorEscala = new ValorEscala();
				binder.loadAll();
				wndEscalaMedicion.detach();
				break;
			case Messagebox.CANCEL:
				break;
			default:
				break;
			}
		}else
			wndEscalaMedicion.detach();
	}

	public void onClick$btnQuitar() throws InterruptedException {
		if (lboxValoresEscala.getSelectedItem() != null) {
			int i = lboxValoresEscala.getSelectedIndex();
			if (lboxValoresEscala.getItemCount() == 1) {
				int result = Messagebox
						.show("Si elimina este elemento, tambien eliminara esta escala de medicion ¿Esta seguro?",
								"Question", Messagebox.OK | Messagebox.CANCEL,
								Messagebox.QUESTION);
				switch (result) {
				case Messagebox.OK:
					escalaMedicion.setEstatus('E');
					servicioEscalaMedicion.actualizar(escalaMedicion);
					listValoresEscala.get(i).setEstatus('E');
					servicioValorEscala.actualizar(listValoresEscala.get(i));
					listValoresEscala.remove(lboxValoresEscala
							.getSelectedIndex());
					escalaMedicion = new EscalaMedicion();
					valorEscala = new ValorEscala();
					binder.loadAll();
					onClick$btnCancelar();
					break;
				case Messagebox.CANCEL:
					break;
				default:
					break;
				}
			} else {
				listValoresEscala.get(i).setEstatus('E');
				servicioValorEscala.actualizar(listValoresEscala.get(i));
				listValoresEscala.remove(lboxValoresEscala.getSelectedIndex());
				valorEscala = new ValorEscala();
				binder.loadAll();
			}
		} else
			alert("Debe seleccionar un item de la lista");
	}
}
