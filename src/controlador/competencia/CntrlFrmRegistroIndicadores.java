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

import modelo.Competencia;
import modelo.Constante;
import modelo.DatoBasico;
import modelo.Indicador;
import modelo.IndicadorCategoriaCompetencia;
import modelo.TipoDato;

import org.jruby.RubyProcess.Sys;
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
import servicio.implementacion.ServicioIndicadorCategoriaCompetencia;

/**
 * Clase que tiene como funci�n controlar la interfaz de igual nombre y los
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
	ServicioIndicadorCategoriaCompetencia servicioIndicadorCategoriaCompetencia;
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
	Button btnCero;
	Button btnUno;
	Button btnDos;
	Button btnTres;
	Button btnCuatro;
	Button btnCinco;
	Button btnSeis;
	Button btnSiete;
	Button btnOcho;
	Button btnNueve;
	Button btnComa;
	Button btnBorrarUnidad;
	Button btnBuscarIndicador;
	Boolean agregarFormula = true;
	Boolean agregarNumero = true;
	Boolean agregarComa = false;
	Boolean agregandoNumero = false;
	Boolean modificando = false;
	Boolean ultimaComa = false;
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
		txtAbreviatura.setReadonly(true);
		txtNombre.setReadonly(true);
		txtNombre.setConstraint("");
		txtNombre.setText("");
		txtFormula.setReadonly(true);
		txtFormula.setValue("");
	}

	public void inicializarBoton() {
		btnSumar.setDisabled(true);
		btnRestar.setDisabled(true);
		btnMultiplicar.setDisabled(true);
		btnDividir.setDisabled(true);
		btnParentesisAbre.setDisabled(true);
		btnParentesisCierra.setDisabled(true);
		btnBorrar.setDisabled(true);
		btnBorrarUnidad.setDisabled(true);
		btnCero.setDisabled(true);
		btnUno.setDisabled(true);
		btnDos.setDisabled(true);
		btnTres.setDisabled(true);
		btnCuatro.setDisabled(true);
		btnCinco.setDisabled(true);
		btnSeis.setDisabled(true);
		btnSiete.setDisabled(true);
		btnOcho.setDisabled(true);
		btnNueve.setDisabled(true);
		btnComa.setDisabled(true);
	}

	public void inicializar() {
		indicador = new Indicador();
		inicializarPanel();
		inicializarCombo();
		inicializarTexto();
		inicializarBoton();
		agregarFormula = true;
		agregarNumero = true;
		agregarComa = false;
		agregandoNumero = false;
		pilaString.clear();
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
				btnBorrar.setDisabled(false);
				btnBorrarUnidad.setDisabled(false);
				btnCero.setDisabled(false);
				btnUno.setDisabled(false);
				btnDos.setDisabled(false);
				btnTres.setDisabled(false);
				btnCuatro.setDisabled(false);
				btnCinco.setDisabled(false);
				btnSeis.setDisabled(false);
				btnSiete.setDisabled(false);
				btnOcho.setDisabled(false);
				btnNueve.setDisabled(false);
				btnComa.setDisabled(false);
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

	public void agregarOperador(String operador) {
		if (indicador.getFormula() != null)
			if (!agregarFormula) {
				indicador.setFormula(indicador.getFormula() + operador);
				agregarFormula = true;
				agregarNumero = true;
				agregarComa = false;
				agregandoNumero = false;
				pilaString.push(1);
			} else
				alert("Debe seleccionar primero una constante o un indicador");
		else
			alert("Debe seleccionar primero una constante o un indicador");
		binder.loadAll();
	}

	public void onClick$btnSumar() {
		agregarOperador("+");
	}

	public void onClick$btnRestar() {
		agregarOperador("-");
	}

	public void onClick$btnMultiplicar() {
		agregarOperador("*");
	}

	public void onClick$btnDividir() {
		agregarOperador("/");
	}

	public void onClick$btnComa() {
		if (indicador.getFormula() != null) {
			if (agregarComa) {
				indicador.setFormula(indicador.getFormula() + ",");
				pilaString.push(1);
				agregarFormula = false;
				agregarComa = false;
				agregarNumero = true;
				// ultimaComa = true;
			} else
				alert("Debe seleccionar primero un numero");
		} else
			alert("Debe seleccionar primero un numero");
		binder.loadAll();
	}

	public void onClick$btnParentesisAbre() {
		if (indicador.getFormula() != null) {
			if (agregarFormula) {
				indicador.setFormula(indicador.getFormula() + "(");
				agregarComa = false;
				pilaString.push(1);
			} else
				alert("Debe seleccionar primero un operador");
		} else {
			indicador.setFormula("(");
			agregarComa = false;
		}
		binder.loadAll();
	}

	public void onClick$btnParentesisCierra() {
		if (indicador.getFormula() != null) {
			if (!agregarFormula) {
				if (contarCaracter(indicador.getFormula(), '(') > contarCaracter(
						indicador.getFormula(), ')')) {
					indicador.setFormula(indicador.getFormula() + ")");
					agregarComa = false;
					pilaString.push(1);
				} else
					alert("No puede agregar un \")\" ..");
			} else
				alert("Debe seleccionar primero otros elementos..");
		} else
			alert("Debe seleccionar primero un indicador o \"(\"...");
		binder.loadAll();
	}

	public void agregarNumeros(int num) {
		if (indicador.getFormula() != null) {
			if (agregarFormula || agregarNumero) {
				indicador.setFormula(indicador.getFormula() + num);
				agregarNumero = true;
				agregarFormula = false;
				if (!agregandoNumero) {
					agregandoNumero = true;
					agregarComa = true;
				}
				pilaString.push(1);
			} else
				alert("Debe seleccionar primero un operador");
		} else if (agregarFormula || agregarNumero) {
			indicador.setFormula("1");
			agregandoNumero = true;
			agregarNumero = true;
			agregarFormula = false;
			agregarComa = true;
			pilaString.push(1);
		} else
			alert("Debe seleccionar primero un operador");
		binder.loadAll();
	}

	public void onClick$btnUno() {
		agregarNumeros(1);
	}

	public void onClick$btnDos() {
		agregarNumeros(2);
	}

	public void onClick$btnTres() {
		agregarNumeros(3);
	}

	public void onClick$btnCuatro() {
		agregarNumeros(4);
	}

	public void onClick$btnCinco() {
		agregarNumeros(5);
	}

	public void onClick$btnSeis() {
		agregarNumeros(6);
	}

	public void onClick$btnSiete() {
		agregarNumeros(7);
	}

	public void onClick$btnOcho() {
		agregarNumeros(8);
	}

	public void onClick$btnNueve() {
		agregarNumeros(9);
	}

	public void onClick$btnCero() {
		agregarNumeros(0);
	}

	public void onClick$btnBorrar() {
		pilaString.clear();
		indicador.setFormula(null);
		agregarFormula = true;
		agregarNumero = true;
		agregarComa = false;
		agregandoNumero = false;
		binder.loadAll();
	}

	public void onChange$cmbIndicador() {
		Indicador I = (Indicador) cmbIndicador.getSelectedItem().getValue();
		if (indicador.getFormula() != null) {
			if (agregarFormula) {
				indicador.setFormula(indicador.getFormula()
						+ I.getAbreviatura());
				agregarFormula = false;
				agregarNumero = false;
				agregandoNumero = false;
				pilaString.push(I.getAbreviatura().length());
			} else
				alert("Debe seleccionar primero un operador");
		} else if (agregarFormula) {
			indicador.setFormula(I.getAbreviatura());
			agregarFormula = false;
			agregarNumero = false;
			agregandoNumero = false;
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
				agregarNumero = false;
				agregandoNumero = false;
				pilaString.push(C.getAbreviatura().length());
			} else
				alert("Debe seleccionar primero un operador");
		} else if (agregarFormula) {
			indicador.setFormula(C.getAbreviatura());
			agregarFormula = false;
			agregarNumero = false;
			agregandoNumero = false;
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

	public void onClick$btnBorrarUnidad() { // Se utiliza para borrar elementos
											// de formula
		if (!pilaString.isEmpty()) {
			Integer auxInt = pilaString.pop();
			if (auxInt == 1) {
				String borrar = indicador.getFormula().substring(
						indicador.getFormula().length() - auxInt);
				if (borrar.compareTo(")") != 0 && borrar.compareTo("(") != 0)
					agregarFormula = !agregarFormula;
			} else
				agregarFormula = !agregarFormula;
			String auxFormula = indicador.getFormula().substring(0,
					indicador.getFormula().length() - auxInt);
			indicador.setFormula(auxFormula);
			binder.loadAll();
		} else
			throw new WrongValueException(txtFormula, "Esta vacia la formula");
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
			int result = Messagebox.show("¿Desea salir?", "Question",
					Messagebox.OK | Messagebox.CANCEL, Messagebox.QUESTION);
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

	public void modificarIndicador() throws InterruptedException {
		if (txtNombre.getValue().isEmpty()) {
			throw new WrongValueException(txtNombre, "Debe ingresar un nombre");
		} else if (txtAbreviatura.getValue().isEmpty()) {
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
			indicador.setAbreviatura(indicador.getAbreviatura().toUpperCase());
			indicador.setNombre(indicador.getNombre().toUpperCase());
			servicioIndicador.agregar(indicador);
			Messagebox.show("Datos agregados exitosamente", "Mensaje",
					Messagebox.OK, Messagebox.EXCLAMATION);
			inicializar();
			binder.loadAll();
		}
	}

	public boolean buscarAbreviaturaIndicador() {
		Boolean encontrado = false;
		List<Indicador> lstIndicador = servicioIndicador.listarActivos();
		List<Constante> lstConstante = servicioConstante.listarActivos();
		for (Indicador indica : lstIndicador) {
			if (indica.getAbreviatura().equals(indicador.getAbreviatura())) {
				encontrado = true;
			}
		}
		for (Constante cons : lstConstante) {
			if (cons.getAbreviatura().equals(indicador.getAbreviatura())) {
				encontrado = true;
			}
		}
		return encontrado;
	}

	public void onClick$btnGuardar() throws InterruptedException {	
		String nombre = txtNombre.getValue();
		String abreviatura = txtAbreviatura.getValue();
		if (modificando)
			modificarIndicador();
		else {
			if (cmbTipo.getText().equalsIgnoreCase("--Seleccione--")) {
				throw new WrongValueException(cmbTipo,
						"Debe seleccionar un tipo de indicador");
			}
			if (cmbModalidad.getText().equalsIgnoreCase("--Seleccione--")) {
				throw new WrongValueException(cmbModalidad,
						"Debe seleccionar una modalidad");
			}
			if (cmbTipo.getText().equals("COMPUESTO")) {
				if (cmbMedicion.getText().equalsIgnoreCase("--Seleccione--")) {
					throw new WrongValueException(cmbMedicion,
							"Debe seleccionar una medicion");
				} else if (nombre.isEmpty() || nombre.trim().length() == 0) {
					throw new WrongValueException(txtNombre,
							"Debe ingresar un nombre");
				} else if (abreviatura.isEmpty() || abreviatura.trim().length() == 0) {
					throw new WrongValueException(txtAbreviatura,
							"Debe ingresar una abreviatura");
				} else if (agregarFormula || ultimaComa) {
					throw new WrongValueException(txtFormula,
							"Debe completar la formula");
				}
			} else if (nombre.isEmpty() || nombre.trim().length() == 0) {
				System.out.println(txtNombre.getValue().trim());
				throw new WrongValueException(txtNombre,
						"Debe ingresar un nombre");
			} else if (abreviatura.isEmpty() || abreviatura.trim().length() == 0) {
				throw new WrongValueException(txtAbreviatura,
						"Debe ingresar una abreviatura");
			} else if (agregarFormula || ultimaComa) {
				throw new WrongValueException(txtFormula,
						"Debe completar la formula");
			}
			System.out.println(buscarAbreviaturaIndicador());
			if (buscarAbreviaturaIndicador()) {
				// Verificar que la abreviatura del indicador no est� registrada
				throw new WrongValueException(txtAbreviatura,
						"Debe ingresar otra abreviatura");
			} else {
				indicador
						.setDatoBasicoByCodigoTipoIndicador((DatoBasico) cmbTipo
								.getSelectedItem().getValue());
				indicador
						.setDatoBasicoByCodigoModalidad((DatoBasico) cmbModalidad
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
				indicador.setAbreviatura(indicador.getAbreviatura()
						.toUpperCase());
				indicador.setNombre(indicador.getNombre().toUpperCase());
				servicioIndicador.agregar(indicador);
				Messagebox.show("Datos agregados exitosamente", "Mensaje",
						Messagebox.OK, Messagebox.EXCLAMATION);
				inicializar();
				binder.loadAll();
			}
		}
	}

	public Competencia buscarIndicadorCompetencia() {
		// Boolean encontrado = false;
		Competencia compe = null;
		List<IndicadorCategoriaCompetencia> lstCompetencia = servicioIndicadorCategoriaCompetencia
				.listarCompetenciaIndicador(indicador);

		for (IndicadorCategoriaCompetencia indCatComp : lstCompetencia) {
			String nombre = indCatComp.getCompetencia()
					.getDatoBasicoByCodigoEstadoCompetencia().getNombre();
			if (nombre.equals("REGISTRADA") || nombre.equals("APERTURADA")) {
				compe = indCatComp.getCompetencia();
			}
		}
		return compe;
	}

	public void onClick$btnEliminar() throws InterruptedException {
		if (modificando) {
			if (buscarIndicadorCompetencia() != null) {
				Messagebox.show(
						"No se puede eliminar el indicador, se encuentra asociado a la competencia: "
								+ buscarIndicadorCompetencia().getNombre()
								+ " ", "Mensaje", Messagebox.OK,
						Messagebox.ERROR);
			} else {
				indicador.setEstatus('E');
				servicioIndicador.agregar(indicador);
				Messagebox.show("Datos eliminados exitosamente", "Mensaje",
						Messagebox.OK, Messagebox.EXCLAMATION);
				onClick$btnCancelar();
				binder.loadAll();
			}
		} else {
			throw new WrongValueException(btnBuscarIndicador, "Debe seleccionar un indicador");
		}
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
			// Este metodo se llama cuando se envia la señal desde el catalogo
			public void onEvent(Event arg0) throws Exception {
				// se obtiene el indicador
				indicador = (Indicador) formulario.getVariable("indicador",
						false);
				modificando = (Boolean) formulario.getVariable("modificar",
						false);
				cmbTipo.setSelectedIndex(buscarCombo(indicador,
						listTipoIndicador, 1));
				cmbModalidad.setSelectedIndex(buscarCombo(indicador,
						listModalidadIndicador, 2));
				cmbMedicion.setSelectedIndex(buscarCombo(indicador,
						listMedicionIndicador, 3));
				llenarPila(indicador); // HACER habilitar calculadora
				cambiarTipo();
				cargarIndicador();
				deshabilitarCatalogo();
				indicador.setFormula(indicador.getFormula());
				binder.loadAll();
			}
		});
	}

	public void llenarPila(Indicador indicador) {
		String auxIndicador = indicador.getFormula();
		Integer aux = 0;
		if (auxIndicador != null) {
			if (auxIndicador.substring(auxIndicador.length() - 1).equals(")")) {
				agregarFormula = false;
			}
			for (int i = 0; i < indicador.getFormula().length(); i++) {
				if (auxIndicador.substring(i, i + 1).equals(")")
						|| auxIndicador.substring(i, i + 1).equals("(")
						|| auxIndicador.substring(i, i + 1).equals("+")
						|| auxIndicador.substring(i, i + 1).equals("-")
						|| auxIndicador.substring(i, i + 1).equals("*")
						|| auxIndicador.substring(i, i + 1).equals("/")) {
					if (aux != 0) {
						pilaString.push(aux);
						pilaString.push(1);
						aux = 0;
					} else
						pilaString.push(1);
				} else {
					aux++;
				}
			}
			if (aux != 0) {
				pilaString.push(aux);
			} else
				;
		}
	}

	/**
	 * Busca el �ndice correspondiente al �tem seleccionado en un combo,
	 * para relacionarlo con un objeto
	 * 
	 * @param objeto
	 *            objeto relacionado con el combo
	 * @param lista
	 *            lista enlazada con el combo
	 * @param campo
	 *            valor utilizado en los casos del switch
	 * 
	 * @return �ndice del �tem seleccionado
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
			case 3: // Medici�n Indicador
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
