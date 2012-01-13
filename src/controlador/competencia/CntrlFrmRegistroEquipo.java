package controlador.competencia;

import org.zkoss.zul.Button;
import java.util.List;

import modelo.Categoria;
import modelo.CategoriaCompetencia;
import modelo.Competencia;
import modelo.Divisa;
import modelo.Equipo;
import modelo.EquipoCompetencia;
import modelo.Indicador;
import modelo.PersonaNatural;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.mvel.tests.main.AbstractTest.Model;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;
import org.zkoss.zul.api.Comboitem;

import comun.EstadoCompetencia;

import servicio.implementacion.ServicioCategoriaCompetencia;
import servicio.implementacion.ServicioDivisa;
import servicio.implementacion.ServicioEquipo;
import servicio.implementacion.ServicioEquipoCompetencia;
import servicio.implementacion.ServicioIndicador;
import servicio.implementacion.ServicioRegistroEquipo;

public class CntrlFrmRegistroEquipo extends GenericForwardComposer {
	int posicion;
	Equipo equipo;
	Divisa divisa;
	EquipoCompetencia equipoCompetencia;
	PersonaNatural personaNatural;
	List<CategoriaCompetencia> categorias;
	Component comp;
	ServicioCategoriaCompetencia servicioCategoriaCompetencia;
	ServicioEquipo servicioEquipo;
	ServicioRegistroEquipo servicioRegistroEquipo;
	ServicioEquipoCompetencia servicioEquipoCompetencia;
	EquipoCompetencia equipoComptencia;
	ServicioDivisa servicioDivisa;
	Competencia competencia;
	AnnotateDataBinder binder;
	List<Equipo> equipos, equiposforaneos;
	List<EquipoCompetencia> equipocompetencia,equipocompetenciaforaneo;
	Window frmRegistroEquipo;

	public List<EquipoCompetencia> getEquipocompetencia() {
		return equipocompetencia;
	}

	public void setEquipocompetencia(List<EquipoCompetencia> equipocompetencia) {
		this.equipocompetencia = equipocompetencia;
	}

	Button btnBuscarDelegado;
	List<Divisa> divisas;
	Component formulario, formularios;
	Combobox cmbCategorias;
	Combobox cmbDivisa, cmbCategoriasForaneas;
	Listbox lsbxDivisa;
	Listbox lsbxEquiposLocales;
	Listbox lsbxEquiposSeleccionadosLocales;
	Listbox lsbxEquiposForaneos,lsbxEquiposForaneosSeleccionados;
	Textbox Nombre,txtNombreCompetencia,txtTipoCompetencia,txtModalidadCompetencia;

	// Este metodo se llama al cargar la ventana
	public void doAfterCompose(Component c) throws Exception {
		super.doAfterCompose(c);
		// Se utiliza para hacer referencia a los objetos desde la vista (ej
		// cntrl.algo). Debe ir siempre aqui
		c.setVariable("cntrl", this, true);
		// se guarda la referencia al formulario actual ej (frmDivisa)
		formulario = c;
		restaurar();
		equipoCompetencia = new EquipoCompetencia();
	}

	public void restaurar() {
		competencia = new Competencia();

	}

	// Llama al evento select de la lista de equipos locales
	public void onClick$btnAgregar() {
		Agregar(lsbxEquiposLocales, lsbxEquiposSeleccionadosLocales,
				equipocompetencia);
		binder.loadAll();
	}

	// ////////////// agrega de una lista origen a destino validando que no
	// exista en la de destino ///////////////
	public void Agregar(Listbox origen, Listbox destino, List lista) {
		Set seleccionados = origen.getSelectedItems();
		for (Iterator i = seleccionados.iterator(); i.hasNext();) {
			boolean sw = false;
			Listitem li = (Listitem) i.next();
			Equipo c1 = (Equipo) li.getValue();
			List seleccionDestino = destino.getItems();
			for (Iterator j = seleccionDestino.iterator(); j.hasNext();) {
				Listitem li2 = (Listitem) j.next();
				EquipoCompetencia c2 = (EquipoCompetencia) li2.getValue();
				alert(c2.getEquipo().getNombre());
				if (c1.getNombre().equals(c2.getEquipo().getNombre())) {
					sw = true;
					break;
				}
			}
			if (!sw) {
				equipoCompetencia.setCompetencia(competencia);
				equipoCompetencia.setEstatus('A');
				equipoCompetencia.setEquipo(c1);
				lista.add(equipoCompetencia);
			}
		}
	}

	// //////////// Llama al catalogo de Competencias
	// ///////////////////////////////
	public void onClick$btnBuscar() {
		// se crea el catalogo y se llama
		Component catalogo = Executions.createComponents(
				"/Competencias/Vistas/FrmCatalogoCompetencia.zul", null, null);
		// asigna una referencia del formulario al catalogo.
		catalogo.setVariable("formulario", formulario, false);
		catalogo.setVariable("estatus", EstadoCompetencia.REGISTRADA, false);
		formulario.addEventListener("onCatalogoCerrado", new EventListener() {
			@Override
			// Este metodo se llama cuando se envia la señal desde el catalogo
			public void onEvent(Event arg0) throws Exception {
				// se obtiene la competencia
				competencia = (Competencia) formulario.getVariable(
						"competencia", false);
				categorias = servicioCategoriaCompetencia
						.listarCategoriaPorCompetencia(competencia
								.getCodigoCompetencia());
				divisas = servicioDivisa.listarDivisaForanea();
//				equiposforaneos = servicioEquipo.listarEquipoForaneos();
				binder.loadAll();
			}
		});
	}

	// // activa el boton de catalogo de persona natural ////
	public void onSelect$lsbxEquiposSeleccionadosLocales() {		
		posicion =lsbxEquiposSeleccionadosLocales.getSelectedIndex();
		personaNatural = equipocompetencia.get(posicion).getPersonaNatural();
		btnBuscarDelegado.setDisabled(false);
		binder.loadAll();
		
	}

	// ///////////////////////////////////////////////////////

	// //////////// Llama al catalogo de Persona Natural
	// ///////////////////////////////
	public void onClick$btnBuscarDelegado() {
		// se crea el catalogo y se llama
		Component catalogos = Executions.createComponents(
				"/Competencias/Vistas/FrmCatalogoPersonaNatural.zul", null,
				null);
		// asigna una referencia del formulario al catalogo.
		catalogos.setVariable("formulario", formulario, false);
		formulario.addEventListener("onCatalogoCerrado", new EventListener() {
			@Override
			// Este metodo se llama cuando se envia la señal desde el catalogo
			public void onEvent(Event arg0) throws Exception {
				// se obtiene la competencia
				personaNatural = (PersonaNatural) formulario.getVariable(
						"personaNatural", false);
				equipocompetencia.get(posicion).setPersonaNatural(personaNatural);
				personaNatural = new PersonaNatural();
				binder.loadAll();
			}
		});
	}

	
	public void onClick$btnAgregarPersonaNatural() {
//		alert(personaNatural.getCedulaRif());
//		alert(String.valueOf(lsbxEquiposLocales.getSelectedIndex()));
		
	}
	

	public void onClick$btnAgregarEquipoForaneo() throws InterruptedException {
		if (cmbCategoriasForaneas.getText() != "--Seleccione--") {
		} else {
			// se crea el catalogo y se llama
			Component catalogos = Executions.createComponents(
					"/Jugador/Vistas/frmConfigurarEquipo.zul", null, null);
			// asigna una referencia del formulario al catalogo.
			Window w = (Window) catalogos;
			w.setMode("popup");
			w.setPosition("center");
			catalogos.setVariable("formulario", formulario, false);
			formulario.addEventListener("onCatalogoCerrado",
					new EventListener() {
						@Override
						// Este metodo se llama cuando se envia la señal desde
						// el catalogo
						public void onEvent(Event arg0) throws Exception {
							// se obtiene la competencia
							Comboitem li = cmbCategorias.getSelectedItem();
							CategoriaCompetencia c1 = (CategoriaCompetencia) li
									.getValue();
							equiposforaneos = servicioEquipo
									.listarEquipoPorCategoria(c1.getCategoria()
											.getCodigoCategoria());

							binder.loadAll();

						}
					});
		}

	}

	// ////// Cuando ocurre un cambio en el combo llama a este procedimiento
	// /////////
	public void onChange$cmbCategorias() {
		// se crea el catalogo y se llama
		Comboitem li = cmbCategorias.getSelectedItem();
		CategoriaCompetencia c1 = (CategoriaCompetencia) li.getValue();
		equipos = servicioEquipo.listarEquipoPorCategoria(c1.getCategoria()
				.getCodigoCategoria());
		binder.loadAll();
		equipocompetencia = new ArrayList<EquipoCompetencia>();
	}

	// ////// Cuando ocurre un cambio en el combo llama a este procedimiento
	// /////////
	public void onChange$cmbCategoriasForaneas() {
		// se crea el catalogo y se llama
		Comboitem li = cmbCategoriasForaneas.getSelectedItem();
		CategoriaCompetencia c1 = (CategoriaCompetencia) li.getValue();
		equiposforaneos = servicioEquipo.listarEquipoPorCategoria(c1
				.getCategoria().getCodigoCategoria());
		binder.loadAll();
		equipocompetencia = new ArrayList<EquipoCompetencia>();
	}

	// // llamando el evento guardar
	public void onClick$btnGuardar() {
		binder.loadAll();
	}
	public void limpiar() {
		cmbCategorias.setValue("--Seleccione--");
		cmbCategoriasForaneas.setValue("--Seleccione--");
		txtNombreCompetencia.setValue("");
		txtTipoCompetencia.setValue("");
		txtModalidadCompetencia.setValue("");
		/*txtFechaInicioCompetencia.setValue("00/00/00");
		txtFechaFin.setValue("");*/
		
		
	}
	
	public void onClick$btnCancelar() {
		limpiar();
	}
	
	public void onClick$btnSalir() throws InterruptedException {
		if (equipocompetencia!= null) {
			int result = Messagebox
					.show("Existen elementos en el formulario ¿Realmente desea salir?",
							"Question", Messagebox.OK | Messagebox.CANCEL,
							Messagebox.QUESTION);
			switch (result) {
			case Messagebox.OK:
				onClick$btnCancelar();
				binder.loadAll();
				frmRegistroEquipo.detach();
				break;
			case Messagebox.CANCEL:
				break;
			default:
				break;
			}
		} else {
			onClick$btnCancelar();
			binder.loadAll();
			frmRegistroEquipo.detach();
		}
		
	}
	
	
	// // llenar el objeto para grabar
	// ///////////////////////////// Getter and Setter
	// ///////////////////////////
	

	public int getPosicion() {
		return posicion;
	}

	public void setPosicion(int posicion) {
		this.posicion = posicion;
	}


	public PersonaNatural getPersonaNatural() {
		return personaNatural;
	}

	public void setPersonaNatural(PersonaNatural personaNatural) {
		this.personaNatural = personaNatural;
	}

	public Listbox getLsbxEquiposLocales() {
		return lsbxEquiposLocales;
	}

	public void setLsbxEquiposLocales(Listbox lsbxEquiposLocales) {
		this.lsbxEquiposLocales = lsbxEquiposLocales;
	}

	public Listbox getLsbxEquiposSeleccionadosLocales() {
		return lsbxEquiposSeleccionadosLocales;
	}

	public void setLsbxEquiposSeleccionadosLocales(
			Listbox lsbxEquiposSeleccionadosLocales) {
		this.lsbxEquiposSeleccionadosLocales = lsbxEquiposSeleccionadosLocales;
	}

	public List<EquipoCompetencia> getequipocompetencia() {
		return equipocompetencia;
	}

	public List<Divisa> getDivisas() {
		return divisas;
	}

	public void setDivisas(List<Divisa> divisas) {
		this.divisas = divisas;
	}

	public void setequipocompetencia(List<EquipoCompetencia> equipocompetencia) {
		equipocompetencia = equipocompetencia;
	}

	public Divisa getDivisa() {
		return divisa;
	}

	public void setDivisa(Divisa divisa) {
		this.divisa = divisa;
	}

	public Equipo getEquipo() {
		return equipo;
	}

	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}

	public List<CategoriaCompetencia> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<CategoriaCompetencia> categorias) {
		this.categorias = categorias;
	}

	public Competencia getCompetencia() {
		return competencia;
	}

	public void setCompetencia(Competencia competencia) {
		this.competencia = competencia;
	}

	public List<Equipo> getEquipos() {
		return equipos;
	}

	public void setEquipos(List<Equipo> equipos) {
		this.equipos = equipos;
	}

	public List<Equipo> getEquiposforaneos() {
		return equiposforaneos;
	}

	public void setEquiposforaneos(List<Equipo> equiposforaneos) {
		this.equiposforaneos = equiposforaneos;
	}
	public Button getBtnBuscarDelegado() {
		return btnBuscarDelegado;
	}

	public void setBtnBuscarDelegado(Button btnBuscarDelegado) {
		this.btnBuscarDelegado = btnBuscarDelegado;
	}

	public Component getFormularios() {
		return formularios;
	}

	public void setFormularios(Component formularios) {
		this.formularios = formularios;
	}

}
