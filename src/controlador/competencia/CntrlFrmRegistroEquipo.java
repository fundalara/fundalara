package controlador.competencia;

import java.util.List;

import modelo.Categoria;
import modelo.CategoriaCompetencia;
import modelo.Competencia;
import modelo.Divisa;
import modelo.Equipo;
import modelo.Indicador;

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
import org.zkoss.zul.Textbox;
import org.zkoss.zul.api.Comboitem;

import comun.EstadoCompetencia;

import servicio.implementacion.ServicioCategoriaCompetencia;
import servicio.implementacion.ServicioDivisa;
import servicio.implementacion.ServicioEquipo;
import servicio.implementacion.ServicioIndicador;
import servicio.implementacion.ServicioRegistroEquipo;

public class CntrlFrmRegistroEquipo extends GenericForwardComposer {
	Equipo equipo;
	Divisa divisa;
	List<CategoriaCompetencia> categorias;
	Component comp;
	ServicioCategoriaCompetencia servicioCategoriaCompetencia;
	ServicioEquipo servicioEquipo;
	ServicioRegistroEquipo servicioRegistroEquipo;
	ServicioDivisa servicioDivisa;
	Competencia competencia;
	AnnotateDataBinder binder;
	List<Equipo> equipos, equiposforaneos;
	List<Equipo> Selectes;
	
	List<Divisa> divisas;
	Component formulario;
	Combobox cmbCategorias;
	Combobox cmbDivisa;
	Listbox lsbxDivisa;
	Listbox lsbxEquiposLocales;
	Listbox lsbxEquiposSeleccionadosLocales;
	Textbox Nombre;

	// Este metodo se llama al cargar la ventana
	public void doAfterCompose(Component c) throws Exception {
		super.doAfterCompose(c);
		// Se utiliza para hacer referencia a los objetos desde la vista (ej
		// cntrl.algo). Debe ir siempre aqui
		c.setVariable("cntrl", this, true);
		// se guarda la referencia al formulario actual ej (frmDivisa)
		formulario = c;
		restaurar();
	}

	public void restaurar() {
		competencia = new Competencia();
		// Selectes = new HashSet<Equipo>();

	}

	// Llama al evento select de la lista de equipos locales
	public void onClick$btnAgregar() {
		Agregar(lsbxEquiposLocales, lsbxEquiposSeleccionadosLocales, Selectes);
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
				Equipo c2 = (Equipo) li2.getValue();
				if (c1.getNombre().equals(c2.getNombre())) {
					sw = true;
					break;
				}
			}
			if (!sw) {
				lista.add(c1);
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
						.listarCategoriaPorCompetencia(competencia.getCodigoCompetencia());
				
				divisas = servicioDivisa.listarDivisaForanea();
				equiposforaneos = servicioEquipo.listarEquipoForaneos();
				binder.loadAll();
			}
		});

	}
	
	public void onClick$btnAgregarEquipoForaneo(){
		alert(String.valueOf(servicioEquipo.listar().size()+1));
		equipo.setCodigoEquipo(servicioEquipo.listar().size()+1);
		CategoriaCompetencia c1 = (CategoriaCompetencia) cmbCategorias.getSelectedItem().getValue();		
		equipo.setCategoria(c1.getCategoria());
		Divisa c2 = (Divisa) cmbDivisa.getSelectedItem().getValue();
		equipo.setDivisa(c2);
		equipo.setEstatus('A');
		equipo.setNombre(Nombre.getValue());
//		equipo.setDatoBasico("2");
		equiposforaneos.add(equipo);
		binder.loadAll();
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
		Selectes = new ArrayList<Equipo>();
	}

	// ///////////////////////////// Getter and Setter
	// ///////////////////////////
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

	public List<Equipo> getSelectes() {
		return Selectes;
	}

	public List<Divisa> getDivisas() {
		return divisas;
	}

	public void setDivisas(List<Divisa> divisas) {
		this.divisas = divisas;
	}

	public void setSelectes(List<Equipo> selectes) {
		Selectes = selectes;
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

}
