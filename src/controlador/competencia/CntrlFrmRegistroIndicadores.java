package controlador.competencia;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Stack;

import javax.xml.soap.Text;

import modelo.Constante;
import modelo.DatoBasico;
import modelo.Indicador;
import modelo.TipoDato;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Label;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Panel;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import servicio.implementacion.ServicioConstante;
import servicio.implementacion.ServicioDatoBasico;
import servicio.implementacion.ServicioIndicador;
import servicio.implementacion.ServicioTipoDato;

/**
 * Clase que tiene como función controlar la interfaz de igual nombre y los
 * servicios relacionados con el registro de los indicadores.
 * 
 * @author Nohely P.
 * @author Gabrielena P.
 * 
 * */

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
	Combobox cmbModalidad;
	Combobox cmbMedicion;
	Combobox cmbIndicador;
	Combobox cmbConstante;
	Component formulario;
	Panel pnlIndicador;
	Panel pnlFormula;
	Textbox txtFormula;
	Textbox txtNombre;
	Textbox txtAbreviatura;
	Label lblMedicion;
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
	Button btnBuscarIndicador;
	Boolean agregarFormula = true;
	Stack<Integer> pilaString = new Stack<Integer>();

	public void inicializarPanel() {
		pnlIndicador.setVisible(false);
		pnlFormula.setVisible(false);
	}

	public void inicializarCombo() {
		cmbTipo.setValue("--Seleccione--");
		cmbTipo.setReadonly(true);
		cmbTipo.setDisabled(false);
		cmbModalidad.setValue("--Seleccione--");
		cmbModalidad.setReadonly(true);
		cmbMedicion.setValue("--Seleccione--");
		cmbMedicion.setReadonly(true);
		cmbMedicion.setVisible(false);
		cmbIndicador.setValue("--Seleccione--");
		cmbIndicador.setReadonly(true);
		cmbIndicador.setDisabled(true);
		cmbConstante.setValue("--Seleccione--");
		cmbConstante.setReadonly(true);
		cmbConstante.setDisabled(true);
	}

	public void inicializarTexto() {
		lblMedicion.setVisible(false);
		txtAbreviatura.setConstraint("");
		txtAbreviatura.setText("");
		// txtAbreviatura.setConstraint("/[a-zA-Z0-9]+/ : No se permiten espacios en blanco ni vocales acentuadas");
		txtAbreviatura.setReadonly(true);
		txtNombre.setReadonly(true);
		txtNombre.setConstraint("");
		txtNombre.setText("");
		// txtNombre.setConstraint("/[a-z A-Z 0-9 áéíóúÁÉÍÓÚñÑ]+/ : Sólo números y letras");
		txtFormula.setReadonly(true);
		txtFormula.setValue("");
	}

	public void inicializarBoton() {
		btnGuardar.setDisabled(true);
		btnEliminar.setDisabled(true);
		btnCancelar.setDisabled(true);
		// btnBuscarIndicador.setDisabled(true);
		btnSumar.setDisabled(true);
		btnRestar.setDisabled(true);
		btnMultiplicar.setDisabled(true);
		btnDividir.setDisabled(true);
		btnParentesisAbre.setDisabled(true);
		btnParentesisCierra.setDisabled(true);
		btnBorrar.setDisabled(true);
	}

	public void inicializar() {
		indicador = new Indicador();
		inicializarPanel();
		inicializarCombo();
		inicializarTexto();
		inicializarBoton();
		agregarFormula = true;
	}

	public static void ordenarLista(List lista) {
		Collections.sort(lista, new Comparator<Object>() {
			public int compare(Object obj1, Object obj2) {

				Class clase = obj1.getClass();
				String getter = "get"
						+ Character.toUpperCase("nombre".charAt(0))
						+ "nombre".substring(1);
				try {
					Method getPropiedad = clase.getMethod(getter);

					Object propiedad1 = getPropiedad.invoke(obj1);
					Object propiedad2 = getPropiedad.invoke(obj2);

					if (propiedad1 instanceof Comparable
							&& propiedad2 instanceof Comparable) {
						Comparable prop1 = (Comparable) propiedad1;
						Comparable prop2 = (Comparable) propiedad2;
						return prop1.compareTo(prop2);
					} else {
						if (propiedad1.equals(propiedad2))
							return 0;
						else
							return 1;

					}

				} catch (Exception e) {
					e.printStackTrace();
				}
				return 0;
			}
		});
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
	}

	public void cambiarTipo() {
		if (cmbTipo.getText().equals("SENCILLO")) {
			pnlIndicador.setTitle("Indicadores Sencillos");
			pnlFormula.setVisible(false);
			lblMedicion.setVisible(false);
			cmbMedicion.setVisible(false);
			agregarFormula = false;
		} else {
			pnlIndicador.setTitle("Indicadores Compuestos");
			pnlFormula.setVisible(true);
			lblMedicion.setVisible(true);
			cmbMedicion.setVisible(true);
		}
		pnlIndicador.setVisible(true);
		cmbTipo.setDisabled(true);
		btnGuardar.setDisabled(false);
		btnEliminar.setDisabled(false);
		btnCancelar.setDisabled(false);
	}

	public void onChange$cmbTipo() {
		cambiarTipo();
	}

	public List<Indicador> ConvertirConjuntoALista(Set<Indicador> conjunto) {
		List<Indicador> l = new ArrayList<Indicador>(conjunto);
		return l;
	}

	public void cargarIndicador() {
		DatoBasico DB = (DatoBasico) cmbModalidad.getSelectedItem().getValue();
		listIndicador = new ArrayList<Indicador>();
		listIndicador = servicioIndicador.buscarPorDatoBasico(DB);
		ordenarLista(listIndicador);
		listConstante = new ArrayList<Constante>();
		listConstante = servicioConstante.listarActivos();
		ordenarLista(listConstante);
		binder.loadAll();
	}

	public void validarModalidadMedicion() {
		if (cmbTipo.getText().equals("COMPUESTO")) {
			if ((cmbModalidad.getValue().equals("--Seleccione--"))
					|| (cmbMedicion.getValue().equals("--Seleccione--"))) {
			} else {
				txtNombre.setReadonly(false);
				txtAbreviatura.setReadonly(false);
				btnBuscarIndicador.setDisabled(false);
				cmbIndicador.setDisabled(false);
				cmbConstante.setDisabled(false);
				btnSumar.setDisabled(false);
				btnRestar.setDisabled(false);
				btnMultiplicar.setDisabled(false);
				btnDividir.setDisabled(false);
				btnParentesisAbre.setDisabled(false);
				btnParentesisCierra.setDisabled(false);
				btnBorrar.setDisabled(false);
			}
		} else {
			txtNombre.setReadonly(false);
			txtAbreviatura.setReadonly(false);
			btnBuscarIndicador.setDisabled(false);
		}
	}

	public void onChange$cmbModalidad() { // Carga el combo indicador y
		// el combo constante
		if (cmbTipo.getText().equals("COMPUESTO")) {
			cargarIndicador();
		}
		validarModalidadMedicion();
	}

	public void onChange$cmbMedicion() {
		validarModalidadMedicion();
	}

	public void onClick$btnSumar() {
		if (indicador.getFormula() != null)
			if (!agregarFormula) {
				indicador.setFormula(indicador.getFormula() + "+");
				agregarFormula = true;
				pilaString.push(1);
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
				pilaString.push(1);
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
				pilaString.push(1);
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
				pilaString.push(1);
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
				pilaString.push(1);
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
					pilaString.push(1);
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
				pilaString.push(I.getAbreviatura().length());
			} else
				alert("Debe seleccionar primero un operador");
		} else if (agregarFormula) {
			indicador.setFormula(I.getAbreviatura());
			agregarFormula = false;
			pilaString.push(I.getAbreviatura().length());
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
				pilaString.push(C.getAbreviatura().length());
			} else
				alert("Debe seleccionar primero un operador");
		} else if (agregarFormula) {
			indicador.setFormula(C.getAbreviatura());
			agregarFormula = false;
			pilaString.push(C.getAbreviatura().length());
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
		Integer auxInt = pilaString.pop();
		if (auxInt == 1) {
			String borrar = indicador.getFormula().substring(
					indicador.getFormula().length() - auxInt);
			if (borrar.compareTo(")") != 0 && borrar.compareTo("(") != 0)
				agregarFormula = !agregarFormula;
		} else {
			System.out.println("2");
			agregarFormula = !agregarFormula;
		}
		String auxFormula = indicador.getFormula().substring(0,
				indicador.getFormula().length() - auxInt);
		indicador.setFormula(auxFormula);
		binder.loadAll();
	}

	public void onClick$btnCancelar() {
		inicializar();
		cargarCombos();
		binder.loadAll();
	}

	public void onClick$btnSalir() throws InterruptedException {
		if (cmbTipo.getValue().equals("--Seleccione--")) {
			onClick$btnCancelar();
			binder.loadAll();
			winRegistroIndicador.detach();

		} else {

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
		}
	}

	public void onClick$btnGuardar() throws InterruptedException {
		if (txtNombre.getValue().isEmpty()) { // ----- MODIFICAAAR -----
			throw new WrongValueException(txtNombre, "Debe ingresar un nombre");
		} else if (txtAbreviatura.getValue().isEmpty()) { // ----- MODIFICAAAR
															// -----
			throw new WrongValueException(txtAbreviatura,
					"Debe ingresar una abreviatura");
		} else if (agregarFormula) {
			throw new WrongValueException(txtFormula,
					"Debe completar la formula");
		} else {
			indicador.setDatoBasicoByCodigoTipoIndicador((DatoBasico) cmbTipo
					.getSelectedItem().getValue());
			indicador.setDatoBasicoByCodigoModalidad((DatoBasico) cmbModalidad
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
			indicador.setEstatus('A');
			indicador.setCodigoIndicador(servicioIndicador.generarCodigo());
			indicador.setAbreviatura(indicador.getAbreviatura().toUpperCase());
			indicador.setNombre(indicador.getNombre().toUpperCase());
			servicioIndicador.agregar(indicador);
			Messagebox.show("Datos agregados exitosamente", "Mensaje",
					Messagebox.OK, Messagebox.EXCLAMATION);
			inicializar();
			binder.loadAll();
		}
	}

	// public void onClick$btnGuardar() throws InterruptedException {
	// indicador.setDatoBasicoByCodigoTipoIndicador((DatoBasico) cmbTipo
	// .getSelectedItem().getValue());
	// indicador.setDatoBasicoByCodigoModalidad((DatoBasico) cmbModalidad
	// .getSelectedItem().getValue());
	// if (cmbTipo.getText().equals("SENCILLO")) {
	// DatoBasico medicion = servicioDatoBasico
	// .buscarPorString("INDIVIDUAL");
	// indicador.setDatoBasicoByCodigoMedicion(medicion);
	// } else {
	// indicador.setDatoBasicoByCodigoMedicion((DatoBasico) cmbMedicion
	// .getSelectedItem().getValue());
	// indicador.setFormula(indicador.getFormula().toUpperCase());
	// }
	// indicador.setEstatus('A');
	// indicador.setCodigoIndicador(servicioIndicador.generarCodigo());
	// indicador.setAbreviatura(indicador.getAbreviatura().toUpperCase());
	// indicador.setNombre(indicador.getNombre().toUpperCase());
	// servicioIndicador.agregar(indicador);
	// Messagebox.show("Datos agregados exitosamente", "Mensaje",
	// Messagebox.OK, Messagebox.EXCLAMATION);
	// inicializar();
	// binder.loadAll();
	// }

	public void onClick$btnGuardar2() throws InterruptedException {
		// indicador.setDatoBasicoByCodigoTipoIndicador((DatoBasico)
		// cmbTipo.getSelectedItem().getValue());
		// if (pnlSencillo.setVisible(true)) {
		// indicador
		// .setDatoBasicoByCodigoModalidad((DatoBasico) cmbModalidadSencillo
		// .getSelectedItem().getValue());
		// if (cmbTipo.getText().equals("SENCILLO")) {
		// DatoBasico medicion = servicioDatoBasico
		// .buscarPorString("INDIVIDUAL");
		// indicador.setDatoBasicoByCodigoMedicion(medicion);
		// } else {
		// indicador
		// .setDatoBasicoByCodigoMedicion((DatoBasico) cmbMedicion
		// .getSelectedItem().getValue());
		// }
		// indicador.setFormula("");
		// } //else if (pnlCompuesto.setVisible(true)) {
		//
		// indicador
		// .setDatoBasicoByCodigoModalidad((DatoBasico) cmbModalidadCompuesto
		// .getSelectedItem().getValue());
		// indicador.setDatoBasicoByCodigoMedicion((DatoBasico) cmbMedicion
		// .getSelectedItem().getValue());
		// }
		// indicador.setEstatus('A');
		// indicador.setCodigoIndicador(servicioIndicador.generarCodigo());
		// servicioIndicador.agregar(indicador);
		// Messagebox.show("Datos agregados exitosamente", "Mensaje",
		// Messagebox.OK, Messagebox.EXCLAMATION);
		// limpiar();
		// inicializar();
		// binder.loadAll();
		// else if (txtDescripcionValor.getValue().isEmpty()) { (USAR PARA
		// MENSAJES DE VALIDACION)
		// throw new WrongValueException(txtDescripcionValor,
		// "Debes ingresar una descripcion al valor de la escala");
	}

	public void onClick$btnEliminar() throws InterruptedException {
		indicador.setEstatus('E');
		servicioIndicador.agregar(indicador);
		Messagebox.show("Datos eliminados exitosamente", "Mensaje",
				Messagebox.OK, Messagebox.EXCLAMATION);
		onClick$btnCancelar();
		binder.loadAll();
	}

	public void deshabilitarCatalogo() {
		txtNombre.setReadonly(false);
		txtAbreviatura.setReadonly(false);
		cmbIndicador.setDisabled(false);
		cmbConstante.setDisabled(false);
		btnSumar.setDisabled(false);
		btnRestar.setDisabled(false);
		btnMultiplicar.setDisabled(false);
		btnDividir.setDisabled(false);
		btnParentesisAbre.setDisabled(false);
		btnParentesisCierra.setDisabled(false);
		btnBorrar.setDisabled(false);
	}

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
				cmbTipo.setSelectedIndex(buscarCombo(indicador,
						listTipoIndicador, 1));
				cmbModalidad.setSelectedIndex(buscarCombo(indicador,
						listModalidadIndicador, 2));
				cmbMedicion.setSelectedIndex(buscarCombo(indicador,
						listMedicionIndicador, 3));
				cambiarTipo();
				cargarIndicador();
				deshabilitarCatalogo();
				agregarFormula = false;
				binder.loadAll();
			}
		});
	}

	/**
	 * Busca el índice correspondiente al ítem seleccionado en un combo, para
	 * relacionarlo con un objeto
	 * 
	 * @param objeto
	 *            objeto relacionado con el combo
	 * @param lista
	 *            lista enlazada con el combo
	 * @param campo
	 *            valor utilizado en los casos del switch
	 * 
	 * @return índice del ítem seleccionado
	 */

	public int buscarCombo(Indicador objeto, List<DatoBasico> lista,
			Integer campo) {
		int j = -1;
		for (Iterator<DatoBasico> k = lista.iterator(); k.hasNext();) {
			DatoBasico db = k.next();
			j++;
			switch (campo) {
			case 1: // Tipo Indicador
				if (db.getNombre()
						.equals(objeto.getDatoBasicoByCodigoTipoIndicador()
								.getNombre())) {
					return j;
				}
			case 2: // Modalidad Indicador
				if (db.getNombre().equals(
						objeto.getDatoBasicoByCodigoModalidad().getNombre())) {
					return j;
				}
			case 3: // Medición Indicador
				if (db.getNombre().equals(
						objeto.getDatoBasicoByCodigoMedicion().getNombre())) {
					return j;
				}
			default:
				break;
			}

		}
		return j;
	}

	// ********************** GETTERS AND SETTERS **********************

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
