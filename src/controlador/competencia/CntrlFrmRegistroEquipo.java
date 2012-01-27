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
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;
import org.zkoss.zul.api.Comboitem;

import com.lowagie.text.ListItem;

import comun.EstadoCompetencia;
import controlador.jugador.bean.Persona;

import servicio.implementacion.ServicioCategoriaCompetencia;
import servicio.implementacion.ServicioDivisa;
import servicio.implementacion.ServicioEquipo;
import servicio.implementacion.ServicioEquipoCompetencia;
import servicio.implementacion.ServicioIndicador;
import servicio.implementacion.ServicioPersona;
import servicio.implementacion.ServicioPersonaNatural;
import servicio.implementacion.ServicioPersonal;
import servicio.implementacion.ServicioRegistroEquipo;

public class CntrlFrmRegistroEquipo extends GenericForwardComposer {
	int posicion, tipoOperacion, tipoOperacionLocal, tipoOperacionForaneo = 0;
	Equipo equipo;

	Divisa divisa;
	EquipoCompetencia equipoComptencia, equipoCompetencia;
	Competencia competencia;
	PersonaNatural personaNatural, personaNaturalForaneo;
	Component comp, formulario;
	AnnotateDataBinder binder;
	ServicioDivisa servicioDivisa;
	ServicioCategoriaCompetencia servicioCategoriaCompetencia;
	ServicioEquipo servicioEquipo;
	ServicioRegistroEquipo servicioRegistroEquipo;
	ServicioEquipoCompetencia servicioEquipoCompetencia;
	ServicioPersonaNatural servicioPersonaNatural;
	ServicioPersona servicioPersona;
	ServicioPersonaNatural servicioPersonaNaturalForaneo;
	List<CategoriaCompetencia> categorias;
	List<Persona> persona;
	List<EquipoCompetencia> equipocompetencia, equipocompetenciaforaneo;
	List<Equipo> equipos, equiposforaneos;
	List<Divisa> divisas;
	Combobox cmbCategorias, cmbCategoriasForaneas;
	Listbox lsbxDivisa, lsbxEquiposLocales, lsbxEquiposSeleccionadosLocales,
			lsbxEquiposForaneos, lsbxEquiposForaneosSeleccionados;
	Textbox Nombre, txtNombreCompetencia, txtTipoCompetencia,
			txtModalidadCompetencia;
	Window frmRegistroEquipo;
	Button btnBuscarDelegado;
	Combobox cmbDivisa;

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
		// personaNaturalForaneo =
		// servicioPersonaNaturalForaneo.buscarPersonaNatural();

	}

	// Llama al evento select de la lista de equipos locales
	public void onClick$btnAgregar() {
		Agregar(lsbxEquiposLocales, lsbxEquiposSeleccionadosLocales,
				equipocompetencia);
		binder.loadAll();
	}

	// Llama al evento select de la lista de equipos locales
	public void onClick$btnAgregarEquiposForaneos() {
		personaNatural=personaNaturalForaneo;
		Agregar(lsbxEquiposForaneos, lsbxEquiposForaneosSeleccionados,
				equipocompetenciaforaneo);
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
				if (c1.getNombre().equals(c2.getEquipo().getNombre())) {
					sw = true;
					break;
				}
			}
			if (!sw) {
				equipoCompetencia = new EquipoCompetencia();				
				equipoCompetencia.setPersonaNatural(personaNatural);				
				equipoCompetencia.setCompetencia(competencia);
				equipoCompetencia.setEstatus('A');
				equipoCompetencia.setEquipo(c1);
				lista.add(equipoCompetencia);
				personaNatural = new PersonaNatural();
			}
		}
	}

	// /////////////// Evento Click del Boton Quitar Equipo Local
	// ///////////////
	public void onClick$btnQuitar() {
		Quitar(lsbxEquiposLocales, equipocompetencia);
		binder.loadAll();
	}

	// /////////////// Evento Click del Boton Quitar Equipo Foraneo
	// ////////////////
	public void onClick$btnQuitarEquiposForaneos() {
		Quitar(lsbxEquiposForaneosSeleccionados, equipocompetenciaforaneo);
		binder.loadAll();
	}

	// /////////////// Funcion Quitar Equipos ///////////////
	public void Quitar(Listbox origen, List lista) {
		Set seleccionados = origen.getSelectedItems();
		for (Iterator i = seleccionados.iterator(); i.hasNext();) {
			Listitem li = (Listitem) i.next();
			Object o = li.getValue();
			lista.remove(o);
		}
	}

	// //////////// Llama al catalogo de Competencias
	// ///////////////////////////////
	public void onClick$btnBuscar() {
		equipocompetencia = new ArrayList<EquipoCompetencia>();
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
				categorias = servicioCategoriaCompetencia.listarCategoriaPorCompetencia(competencia.getCodigoCompetencia());
				
				divisas = servicioDivisa.listarDivisaForanea();
				
				//personaNaturalForaneo = servicioPersonaNatural.buscarPersonaNatural("V-0000000");
				
				if (equipocompetencia.size() == 0) {
					CargarEquipos();
				}

				binder.loadAll();
			}
		});
	}

	// // activa el boton de catalogo de persona natural ////
	public void onSelect$lsbxEquiposSeleccionadosLocales() {
		posicion = lsbxEquiposSeleccionadosLocales.getSelectedIndex();
		personaNatural = equipocompetencia.get(posicion).getPersonaNatural();
		btnBuscarDelegado.setDisabled(false);
		
		binder.loadAll();

	}

	// ////////////////////////////////////////////////////////
	// //////////// Llama al catalogo de Persona Natural ///
	// ///////////////////////////////////////////////////////
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
			public void onEvent(Event arg0) throws Exception {//
				// se obtiene la competencia
				if (equipocompetencia.size() > 0) {
					personaNatural = (PersonaNatural) formulario.getVariable(
							"personaNatural", false);//
					equipocompetencia.get(posicion).setPersonaNatural(
							personaNatural);
					personaNatural = new PersonaNatural();
					btnBuscarDelegado.setDisabled(true);
				}

				binder.loadAll();
			}
		});
	}

	// //////////////////////////// Llama a la interfaz de agregar equipos
	// /////////////////
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

	}

	// ////// Cuando ocurre un cambio en el combo llama a este procedimiento
	// /////////
	public void onChange$cmbCategoriasForaneas() {
		// se crea el catalogo y se llama
		Comboitem li = cmbCategoriasForaneas.getSelectedItem();
		CategoriaCompetencia c1 = (CategoriaCompetencia) li.getValue();
		equiposforaneos = servicioEquipo.buscarEquiposForaneosPorCategoria(c1
				.getCategoria().getCodigoCategoria());
		binder.loadAll();
		// equipocompetenciaforaneo = new ArrayList<EquipoCompetencia>();
	}

	// // llamando el evento guardar
	public void onClick$btnGuardar() throws InterruptedException {
//		int estado;
//		estado = 0;
//		if (txtNombreCompetencia.getText() != null) {
//			
//				if (equipocompetencia.size() > 0) {
//					if (tipoOperacionLocal == 2) {
//						servicioEquipoCompetencia.actualizar(equipocompetencia);
//					} else {
////						alert("entro en agregar");
//						servicioEquipoCompetencia.agregar(equipocompetencia);
//					}
//					estado = 1;
//				}
//				if (equipocompetenciaforaneo.size() > 0) {
//					if (tipoOperacionForaneo == 2) {
//						servicioEquipoCompetencia
//								.actualizar(equipocompetenciaforaneo);
//					} else {
//						servicioEquipoCompetencia
//								.agregar(equipocompetenciaforaneo);
//					}
//					estado = estado+1;
//				}
//			
//
//			if (estado > 0) {
//				Messagebox.show("Datos agregados exitosamente", "Mensaje",
//						Messagebox.OK, Messagebox.EXCLAMATION);
//				restaurar();
//				onClick$btnCancelar();
//				binder.loadAll();
//			} else {
//				Messagebox.show("Seleccione los Equipos ", "Mensaje",
//						Messagebox.OK, Messagebox.EXCLAMATION);
//
//			}
//	}
		Messagebox.show("Datos agregados exitosamente", "Mensaje",
				Messagebox.OK, Messagebox.EXCLAMATION);
		restaurar();
		onClick$btnCancelar();
		binder.loadAll();
		   
	}

	public void onClick$btnEliminar() throws InterruptedException {
		if (Messagebox
				.show("¿Realmente desea eliminar los equipos asociados a la competencia",
						"Mensaje", Messagebox.YES + Messagebox.NO,
						Messagebox.QUESTION) == Messagebox.YES) {
			servicioEquipoCompetencia.eliminar(equipocompetencia);
			servicioEquipoCompetencia.eliminar(equipocompetenciaforaneo);
			restaurar();
			binder.loadAll();
			Messagebox.show("Datos eliminados exitosamente", "Mensaje",
					Messagebox.OK, Messagebox.EXCLAMATION);
		}
	}

	public void limpiar() {
		cmbCategorias.setValue("--Seleccione--");
		cmbCategoriasForaneas.setValue("--Seleccione--");
		txtNombreCompetencia.setValue("");
		txtTipoCompetencia.setValue("");
		txtModalidadCompetencia.setValue("");
		equipocompetencia = new ArrayList<EquipoCompetencia>();
		equipocompetenciaforaneo = new ArrayList<EquipoCompetencia>();
		equipos = new ArrayList<Equipo>();
		equiposforaneos = new ArrayList<Equipo>();
		categorias = new ArrayList<CategoriaCompetencia>();
		competencia = new Competencia();
		binder.loadAll();

	}

	public void onClick$btnCancelar() {
		limpiar();

	}

	public void onClick$btnSalir() throws InterruptedException {
		if (equipocompetencia != null) {
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

	public void CargarEquipos() {
		equipocompetencia = new ArrayList<EquipoCompetencia>();
		equipocompetenciaforaneo = new ArrayList<EquipoCompetencia>();
		List<EquipoCompetencia> EC = servicioEquipoCompetencia
				.buscarEquipoporCompetencia(competencia);
		for (Iterator i = EC.iterator(); i.hasNext();) {
			EquipoCompetencia c1 = (EquipoCompetencia) i.next();
			if (c1.getEquipo().getDivisa().getCodigoDivisa() == 1) {
				//equipocompetencia.add(c1);
				tipoOperacionLocal = 2;
			} else {
				equipocompetenciaforaneo.add(c1);
				tipoOperacionForaneo = 2;
			}

		}
		binder.loadAll();

	}

	// //////////////////// Getter and Setter /////////////////////////////
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

	public List<EquipoCompetencia> getEquipocompetenciaforaneo() {
		return equipocompetenciaforaneo;
	}

	public void setEquipocompetenciaforaneo(
			List<EquipoCompetencia> equipocompetenciaforaneo) {
		this.equipocompetenciaforaneo = equipocompetenciaforaneo;
	}

	public List<EquipoCompetencia> getEquipocompetencia() {
		return equipocompetencia;
	}

	public void setEquipocompetencia(List<EquipoCompetencia> equipocompetencia) {
		this.equipocompetencia = equipocompetencia;
	}

	public EquipoCompetencia getEquipoCompetencia() {
		return equipoCompetencia;
	}

	public void setEquipoCompetencia(EquipoCompetencia equipoCompetencia) {
		this.equipoCompetencia = equipoCompetencia;
	}

	public EquipoCompetencia getEquipoComptencia() {
		return equipoComptencia;
	}

	public void setEquipoComptencia(EquipoCompetencia equipoComptencia) {
		this.equipoComptencia = equipoComptencia;
	}

}