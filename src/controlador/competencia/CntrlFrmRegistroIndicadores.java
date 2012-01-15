package controlador.competencia;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Stack;

import modelo.Constante;
import modelo.DatoBasico;
import modelo.Indicador;
import modelo.TipoDato;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Panel;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import servicio.implementacion.ServicioConstante;
import servicio.implementacion.ServicioDatoBasico;
import servicio.implementacion.ServicioIndicador;
import servicio.implementacion.ServicioTipoDato;

public class CntrlFrmRegistroIndicadores extends GenericForwardComposer {

	ServicioTipoDato servicioTipoDato;
	ServicioDatoBasico servicioDatoBasico;
	ServicioIndicador servicioIndicador;
	ServicioConstante servicioConstante;
	List<DatoBasico> listTipoIndicador;
	List<DatoBasico> listModalidadIndicador;
	List<DatoBasico> listMedicionIndicador;
	List<Indicador> listIndicador;
	List<Constante> listConstante;
	AnnotateDataBinder binder;
	Combobox cmbTipo;
	Combobox cmbModalidadSencillo;
	Combobox cmbModalidadCompuesto;
	Combobox cmbMedicion;
	Combobox cmbIndicador;
	Combobox cmbConstante;
	Component formulario;
	Panel pnlSencillo, pnlCompuesto;
	Textbox txtAbreviaturaSencillo;
	Textbox txtAbreviaturaCompuesto;
	Textbox txtNombreSencillo;
	Textbox txtNombreCompuesto;
	Textbox txtFormula;
	Indicador indicador;
	Window winRegistroIndicador;
	Button btnGuardar;
	Button btnEliminar;
	Button btnCancelar;
	Button btnSumar;
	Button btnRestar;
	Button btnMultiplicar;
	Button btnDividir;
	Button btnParentesisAbre;
	Button btnParentesisCierra;
	Button btnBorrar;
	Boolean agregarFormula = true;
	Stack<Integer> pilaString = new Stack<Integer>();

	public void inicializar() {
		indicador = new Indicador();
		cmbTipo.setValue("--Seleccione--");
		cmbTipo.setReadonly(true);
		cmbModalidadSencillo.setReadonly(true);
		txtAbreviaturaSencillo.setReadonly(true);
		txtNombreSencillo.setReadonly(true);
		cmbModalidadCompuesto.setValue("--Seleccione--");
		cmbModalidadCompuesto.setReadonly(true);
		cmbMedicion.setValue("--Seleccione--");
		cmbMedicion.setReadonly(true);
		cmbIndicador.setValue("--Seleccione--");
		cmbIndicador.setReadonly(true);
		cmbConstante.setValue("--Seleccione--");
		cmbConstante.setReadonly(true);
		txtFormula.setReadonly(true);
		btnGuardar.setDisabled(true);
		btnEliminar.setDisabled(true);
		btnCancelar.setDisabled(true);
	}

	public void cargarCombos() {
		TipoDato nombre = servicioTipoDato.buscarPorTipo("TIPO INDICADOR");
		listTipoIndicador = servicioDatoBasico.buscarPorTipoDato(nombre);
		TipoDato modalidadIndicador = servicioTipoDato
				.buscarPorTipo("MODALIDAD INDICADOR");
		listModalidadIndicador = servicioDatoBasico
				.buscarPorTipoDato(modalidadIndicador);
		TipoDato medicionIndicador = servicioTipoDato
				.buscarPorTipo("MEDICION INDICADOR");
		listMedicionIndicador = servicioDatoBasico
				.buscarPorTipoDato(medicionIndicador);
	}

	public void doAfterCompose(Component com) throws Exception {
		super.doAfterCompose(com);
		com.setVariable("cntrl", this, true);
		formulario = com; // se guarda la referencia al formulario actual ej
							// (frmRegistroIndicadores)
		inicializar();
		cargarCombos();
		agregarFormula = true;
	}

	public void cambiarTipo() {
		if (cmbTipo.getText().equals("SENCILLO")) {
			pnlSencillo.setVisible(true);
			pnlCompuesto.setVisible(false);
		} else {
			pnlSencillo.setVisible(false);
			pnlCompuesto.setVisible(true);
		}
		btnGuardar.setDisabled(false);
		btnEliminar.setDisabled(false);
		btnCancelar.setDisabled(false);
		cmbTipo.setDisabled(true);
	}

	public void onChange$cmbTipo() {
		cambiarTipo();
	}

	public void onChange$cmbModalidadSencillo() {
		txtAbreviaturaSencillo.setReadonly(false);
		txtNombreSencillo.setReadonly(false);
	}

	public List<Indicador> ConvertirConjuntoALista(Set<Indicador> conjunto) {
		List<Indicador> l = new ArrayList<Indicador>(conjunto);
		return l;
	}

	public void cargarIndicador() {
		DatoBasico DB = (DatoBasico) cmbModalidadCompuesto.getSelectedItem()
				.getValue();
		listIndicador = new ArrayList<Indicador>();
		listIndicador = servicioIndicador.buscarPorDatoBasico(DB);
		cmbIndicador.setDisabled(false);
		cmbConstante.setDisabled(false);
		listConstante = new ArrayList<Constante>();
		listConstante = servicioConstante.listarActivos();
		binder.loadAll();
	}

	public void onChange$cmbModalidadCompuesto() { // Carga el combo indicador y
		// el combo constante
		cargarIndicador();
	}

	public void onClick$btnSumar() {
		if (indicador.getFormula() != null)
			if (!agregarFormula) {
				indicador.setFormula(indicador.getFormula() + "+");
				agregarFormula = true;
				pilaString.add(1);
			} else
				alert("Debe seleccionar primero una constante o un indicador");
		else
			alert("Debe seleccionar primero una constante o un indicador");
		binder.loadAll();
	}

	public void onClick$btnRestar() {
		if (indicador.getFormula() != null)
			if (!agregarFormula) {
				indicador.setFormula(indicador.getFormula() + "-");
				agregarFormula = true;
				pilaString.add(1);
			} else
				alert("Debe seleccionar primero una constante o un indicador");
		else
			alert("Debe seleccionar primero una constante o un indicador");
		binder.loadAll();
	}

	public void onClick$btnMultiplicar() {
		if (indicador.getFormula() != null)
			if (!agregarFormula) {
				indicador.setFormula(indicador.getFormula() + "*");
				agregarFormula = true;
				pilaString.add(1);
			} else
				alert("Debe seleccionar primero una constante o un indicador");
		else
			alert("Debe seleccionar primero una constante o un indicador");
		binder.loadAll();
	}

	public void onClick$btnDividir() {
		if (indicador.getFormula() != null)
			if (!agregarFormula) {
				indicador.setFormula(indicador.getFormula() + "/");
				agregarFormula = true;
				pilaString.add(1);
			} else
				alert("Debe seleccionar primero una constante o un indicador");
		else
			alert("Debe seleccionar primero una constante o un indicador");
		binder.loadAll();
	}

	public void onClick$btnParentesisAbre() {
		if (indicador.getFormula() != null) {
			if (agregarFormula) {
				indicador.setFormula(indicador.getFormula() + "(");
				pilaString.add(1);
			} else
				alert("Debe seleccionar primero un operador");
		} else
			indicador.setFormula("(");
		binder.loadAll();
	}

	public void onClick$btnParentesisCierra() {
		if (indicador.getFormula() != null) {
			if (!agregarFormula) {
				if (contarCaracter(indicador.getFormula(), '(') > contarCaracter(
						indicador.getFormula(), ')')) {
					indicador.setFormula(indicador.getFormula() + ")");
					pilaString.add(1);
				} else
					alert("No puede agregar un \")\" ..");
			} else
				alert("Debe seleccionar primero ..");
		} else
			alert("Debe seleccionar primero un indicador o \"(\"...");
		binder.loadAll();
	}

	public void onChange$cmbIndicador() {
		Indicador I = (Indicador) cmbIndicador.getSelectedItem().getValue();
		if (indicador.getFormula() != null) {
			if (agregarFormula) {
				indicador.setFormula(indicador.getFormula()
						+ I.getAbreviatura());
				agregarFormula = false;
				pilaString.add(I.getAbreviatura().length());
			} else
				alert("Debe seleccionar primero un operador");
		} else if (agregarFormula) {
			indicador.setFormula(I.getAbreviatura());
			System.out.println(indicador.getFormula());
			agregarFormula = false;
			pilaString.add(I.getAbreviatura().length());
		} else
			alert("Debe seleccionar primero un operador");
		cmbIndicador.setValue("--Seleccione--");
		binder.loadAll();
	}

	public void onChange$cmbConstante() {
		Constante C = (Constante) cmbConstante.getSelectedItem().getValue();
		if (indicador.getFormula() != null) {
			if (agregarFormula) {
				indicador.setFormula(indicador.getFormula()
						+ C.getAbreviatura());
				agregarFormula = false;
				pilaString.add(C.getAbreviatura().length());
			} else
				alert("Debe seleccionar primero un operador");
		} else if (agregarFormula) {
			indicador.setFormula(C.getAbreviatura());
			agregarFormula = false;
			pilaString.add(C.getAbreviatura().length());
		} else
			alert("Debe seleccionar primero un operador");
		cmbConstante.setValue("--Seleccione--");
		binder.loadAll();
	}

	public int contarCaracter(String s, Character c) {
		int x = 0;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == c)
				x++;
		}
		return x;
	}

	public void onClick$btnBorrar() { // Se utiliza para borrar formula
		if (pilaString.remove(pilaString.size() - 1) == 1) {
			String borrar = indicador.getFormula().substring(
					indicador.getFormula().length());
			System.out.println(indicador.getFormula().length());
			System.out.println(borrar);
			if (!borrar.equals(')') || !borrar.equals('('))
				agregarFormula = !agregarFormula;
		} else
			agregarFormula = !agregarFormula;

		String aux = indicador.getFormula().substring(
				0,
				indicador.getFormula().length()
						- pilaString.remove(pilaString.size() - 1));
		indicador.setFormula(aux);
		binder.loadAll();
	}

	public void limpiar() {
		cmbTipo.setValue("--Seleccione--");
		cmbTipo.setDisabled(false);
		cmbModalidadSencillo.setValue("--Seleccione--");
		cmbModalidadCompuesto.setValue("--Seleccione--");
		cmbMedicion.setValue("--Seleccione--");
		txtAbreviaturaSencillo.setValue("");
		txtAbreviaturaSencillo.setReadonly(true);
		txtNombreSencillo.setValue("");
		txtNombreSencillo.setReadonly(true);
		txtNombreCompuesto.setValue("");
		txtAbreviaturaCompuesto.setValue("");
		pnlSencillo.setVisible(false);
		pnlCompuesto.setVisible(false);
		cmbTipo.setValue("--Seleccione--");
		inicializar();
		cargarCombos();
		agregarFormula = true;
		binder.loadAll();
	}

	public void onClick$btnCancelar() {
		limpiar();
	}

	public void onClick$btnSalir() throws InterruptedException {
		if (indicador != null) {
			int result = Messagebox
					.show("Existen elementos en el formulario ¿Realmente desea salir?",
							"Question", Messagebox.OK | Messagebox.CANCEL,
							Messagebox.QUESTION);
			switch (result) {
			case Messagebox.OK:
				onClick$btnCancelar();
				binder.loadAll();
				winRegistroIndicador.detach();
				break;
			case Messagebox.CANCEL:
				break;
			default:
				break;
			}
		} else {
			onClick$btnCancelar();
			binder.loadAll();
			winRegistroIndicador.detach();
		}
	}

	public void onClick$btnGuardar() throws InterruptedException {
		indicador.setDatoBasicoByCodigoTipoIndicador((DatoBasico) cmbTipo
				.getSelectedItem().getValue());
		if (pnlSencillo.setVisible(true)) {
			indicador
					.setDatoBasicoByCodigoModalidad((DatoBasico) cmbModalidadSencillo
							.getSelectedItem().getValue());
			if (cmbTipo.getText().equals("SENCILLO")) {
				DatoBasico medicion = servicioDatoBasico
						.buscarPorString("INDIVIDUAL");
				indicador.setDatoBasicoByCodigoMedicion(medicion);
			} else {
				indicador
						.setDatoBasicoByCodigoMedicion((DatoBasico) cmbMedicion
								.getSelectedItem().getValue());
			}
			indicador.setFormula("");
		} else if (pnlCompuesto.setVisible(true)) {

			indicador
					.setDatoBasicoByCodigoModalidad((DatoBasico) cmbModalidadCompuesto
							.getSelectedItem().getValue());
			indicador.setDatoBasicoByCodigoMedicion((DatoBasico) cmbMedicion
					.getSelectedItem().getValue());
		}
		indicador.setEstatus('A');
		indicador.setCodigoIndicador(servicioIndicador.generarCodigo());
		servicioIndicador.agregar(indicador);
		Messagebox.show("Datos agregados exitosamente", "Mensaje",
				Messagebox.OK, Messagebox.EXCLAMATION);
		limpiar();
		inicializar();
		binder.loadAll();
		// else if (txtDescripcionValor.getValue().isEmpty()) { (USAR PARA
		// MENSAJES DE VALIDACION)
		// throw new WrongValueException(txtDescripcionValor,
		// "Debes ingresar una descripcion al valor de la escala");
	}

	// public void onClick$btnEliminar() throws InterruptedException {
	// indicador.setEstatus('E');
	// servicioIndicador.agregar(indicador);
	// Messagebox.show("Datos eliminados exitosamente", "Mensaje",
	// Messagebox.OK, Messagebox.EXCLAMATION);
	// onClick$btnCancelar();
	// binder.loadAll();
	// }

	// Llama al catalogo

	public void onClick$btnBuscarIndicador() {
		// se crea el catalogo y se llama
		Component catalogo = Executions.createComponents(
				"/Competencias/Vistas/FrmCatalogoIndicador.zul", null, null);
		// asigna una referencia del formulario al catalogo.
		catalogo.setVariable("formulario", formulario, false);

		formulario.addEventListener("onCatalogoCerrado", new EventListener() {
			@Override
			// Este metodo se llama cuando se envia la seÃ±al desde el catalogo
			public void onEvent(Event arg0) throws Exception {
				// se obtiene el indicador
				indicador = (Indicador) formulario.getVariable("indicador",
						false);
				cmbTipo.setSelectedIndex(buscarTipo(indicador));
				cmbModalidadSencillo
						.setSelectedIndex(buscarModalidad(indicador));
				cmbModalidadCompuesto
						.setSelectedIndex(buscarModalidad(indicador));
				cmbMedicion.setSelectedIndex(buscarMedicion(indicador));
				cambiarTipo();
				cargarIndicador();
				binder.loadAll();
			}
		});

	}

	public int buscarTipo(Indicador i) {
		int j = -1;
		for (Iterator<DatoBasico> k = listTipoIndicador.iterator(); k.hasNext();) {
			DatoBasico db = k.next();
			j++;
			if (db.getNombre().equals(
					indicador.getDatoBasicoByCodigoTipoIndicador().getNombre())) {
				return j;
			}
		}
		return j;
	}

	public int buscarModalidad(Indicador i) {
		int j = -1;
		for (Iterator<DatoBasico> k = listModalidadIndicador.iterator(); k
				.hasNext();) {
			DatoBasico db = k.next();
			j++;
			if (db.getNombre().equals(
					indicador.getDatoBasicoByCodigoModalidad().getNombre())) {

				return j;
			}
		}
		return j;
	}

	public int buscarMedicion(Indicador i) {
		int j = -1;
		for (Iterator<DatoBasico> k = listMedicionIndicador.iterator(); k
				.hasNext();) {
			DatoBasico db = k.next();
			j++;
			if (db.getNombre().equals(
					indicador.getDatoBasicoByCodigoMedicion().getNombre())) {

				return j;
			}
		}
		return j;
	}

	// ********************** GET AND SET **********************

	public Textbox getTxtFormula() {
		return txtFormula;
	}

	public void setTxtFormula(Textbox txtFormula) {
		this.txtFormula = txtFormula;
	}

	public List<Constante> getListConstante() {
		return listConstante;
	}

	public void setListConstante(List<Constante> listConstante) {
		this.listConstante = listConstante;
	}

	public List<Indicador> getListIndicador() {
		return listIndicador;
	}

	public void setListIndicador(List<Indicador> listIndicador) {
		this.listIndicador = listIndicador;
	}

	public List<DatoBasico> getListMedicionIndicador() {
		return listMedicionIndicador;
	}

	public void setListMedicionIndicador(List<DatoBasico> listMedicionIndicador) {
		this.listMedicionIndicador = listMedicionIndicador;
	}

	public List<DatoBasico> getListModalidadIndicador() {
		return listModalidadIndicador;
	}

	public void setListModalidadIndicador(
			List<DatoBasico> listModalidadIndicador) {
		this.listModalidadIndicador = listModalidadIndicador;
	}

	public List<DatoBasico> getListTipoIndicador() {
		return listTipoIndicador;
	}

	public void setListTipoIndicador(List<DatoBasico> listTipoIndicador) {
		this.listTipoIndicador = listTipoIndicador;
	}

	public Indicador getIndicador() {
		return indicador;
	}

	public void setIndicador(Indicador indicador) {
		this.indicador = indicador;
	}
}
