package controlador.competencia;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import modelo.CategoriaCompetencia;
import modelo.Competencia;
import modelo.Categoria;
import modelo.ConstanteCategoria;
import modelo.DatoBasico;
import modelo.Equipo;
import modelo.EquipoCompetencia;
import modelo.EquipoJuego;
import modelo.Estadio;
import modelo.FaseCompetencia;
import modelo.FaseCompetenciaId;
import modelo.Juego;
//import modelo.TipoCompetencia;

import org.zkoss.calendar.impl.SimpleDateFormatter;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Spinner;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Timebox;
import org.zkoss.zul.api.Comboitem;

import servicio.implementacion.ServicioDatoBasico;
import servicio.implementacion.ServicioEquipoCompetencia;
import servicio.implementacion.ServicioEquipoJuego;
import servicio.implementacion.ServicioEstadio;
import servicio.implementacion.ServicioFaseCompetencia;
import servicio.implementacion.ServicioJuego;

import comun.EstadoCompetencia;

/**
 * Controlador para el archivo 'FrmCalendario.zul'
 * 
 * @author Gomez Niurka, Villanueva Alix
 * @version 1000.8
 */

public class CntrlFrmCalendario extends GenericForwardComposer {

	Component formulario;
	AnnotateDataBinder binder;

	FaseCompetencia fase;
	Competencia competencia;
	Estadio estadio;
	Juego juego;
	Juego juegoExistente;
	EquipoCompetencia equipoCompetencia1;
	EquipoCompetencia equipoCompetencia2;
	EquipoJuego equipoHomeClubJuego;
	EquipoJuego equipoVisitanteJuego;

	ServicioFaseCompetencia servicioFaseCompetencia;
	ServicioEstadio servicioEstadio;
	ServicioDatoBasico servicioDatoBasico;
	ServicioEquipoCompetencia servicioEquipoCompetencia;
	ServicioJuego servicioJuego;
	ServicioEquipoJuego servicioEquipoJuego;
	

	List<CategoriaCompetencia> categorias;
	List<FaseCompetencia> fases;
	List<Estadio> estadios;
	List<Juego> juegos;
	List<EquipoCompetencia> equiposCompetenciasH;
	List<EquipoCompetencia> equiposCompetenciasV;

	Set<EquipoCompetencia> equipoCompetencia;

	Textbox txtHomeClub;
	Textbox txtVisitante;
	Timebox tmbxHoraInicio;
	Datebox dtbxFecha;

	Combobox cmbCategoria;
	Combobox cmbFases;
	Combobox cmbEstadios;

	Listbox lsbxEnfrentamientos;

	int codCat;

	Categoria categ;

	/**
	 * Metodo que se ejecuta una vez es renderizado el componente. Sobreescrito
	 * de la clase GenericFordwardComposer
	 * 
	 * @param c
	 *            Component
	 */
	@Override
	public void doAfterCompose(Component c) throws Exception {
		super.doAfterCompose(c);
		c.setVariable("cntrl", this, true);
		formulario = c;
		restaurar();

	}

	public void restaurar() {
		competencia = new Competencia();
		equipoCompetencia1 = new EquipoCompetencia();
		equipoCompetencia2 = new EquipoCompetencia();
		equipoHomeClubJuego = new EquipoJuego();
		equipoVisitanteJuego = new EquipoJuego();
		juego = new Juego();
		juegos = new ArrayList<Juego>();
		fases = new ArrayList<FaseCompetencia>();
		categorias = new ArrayList<CategoriaCompetencia>();
		estadios = new ArrayList<Estadio>();
		cmbCategoria.setText("-- Seleccione --");
		cmbFases.setText("-- Seleccione --");
		cmbEstadios.setText("-- Seleccione --");

	}

	// LLAMA AL CATALOGO DE COMPETENCIAS
	public void onClick$btnBuscarCompetencia() {

		Component catalogo = Executions.createComponents("/Competencias/Vistas/FrmCatalogoCompetencia.zul", null, null);
		catalogo.setVariable("formulario", formulario, false);
		catalogo.setVariable("estado_comp", EstadoCompetencia.REGISTRADA, false);

		formulario.addEventListener("onCatalogoCerrado", new EventListener() {
			@Override
			public void onEvent(Event arg0) throws Exception {
				competencia = (Competencia) formulario.getVariable("competencia", false);
				categorias = ConvertirConjuntoALista(competencia.getCategoriaCompetencias());
				estadios = servicioEstadio.listarEstadiosPorCompetencia(competencia, estadio);
				fases = servicioFaseCompetencia.listarFaseCompetencia(competencia);
				binder.loadAll();
			}
		});

	}

	public void onSelect$cmbFases() {
		restaurarJuego();
		fase = (FaseCompetencia) cmbFases.getSelectedItem().getValue();
		juegos = filtrarJuegosPorCategoria(servicioJuego.listarJuegosPorFaseCompetenciaYCategoria(competencia,fase,categ));		
		binder.loadAll();
	}
	
	public void onChange$cmbCategoria()  {
	    restaurarJuego();
	    cmbFases.setText("--Seleccione--");
		Comboitem cmbItem = cmbCategoria.getSelectedItem();
		CategoriaCompetencia cat = (CategoriaCompetencia) cmbItem.getValue();
		categ = cat.getCategoria();
		binder.loadAll();		

}
	
	public List<Juego> filtrarJuegosPorCategoria(List<Juego> js){
		List<Juego> aux = new ArrayList<Juego>();
		for (Juego j:js){
			List<EquipoJuego> equipos = ConvertirConjuntoALista(j.getEquipoJuegos());
			if ((equipos.get(0).getEquipoCompetencia().getEquipo().getCategoria().getCodigoCategoria() == categ.getCodigoCategoria()))
				aux.add(j);
		}		
		return aux;
	}

	

	// LLAMA AL CATALOGO DE EQUIPOS
	public void onClick$btnBuscarEquipo() {
		// se crea el catalogo y se llama
		Component catalogo = Executions.createComponents(
				"/Competencias/Vistas/FrmCatalogoEquipoCompetencia.zul", null,
				null);
		// asigna una referencia del formulario al catalogo.
		catalogo.setVariable("competencia", competencia, false);
		catalogo.setVariable("formulario", formulario, false);
		catalogo.setVariable("id", 1, false);
		catalogo.setVariable("equipoCompetencia", equipoCompetencia2, false);
		catalogo.setVariable("categoria", categ, false);
		catalogo.setVariable("fase", fase, false);
		formulario.addEventListener("onCatalogoCerrado1", new EventListener() {
			// Este metodo se llama cuando se envia la se�al desde el catalogo
			@Override
			public void onEvent(Event arg0) throws Exception {
				// SE OBTIENE LOS EQUIPOS
				equipoCompetencia1 = (EquipoCompetencia) formulario
						.getVariable("equipoCompetencia", false);
				binder.loadAll();
			}
		});
	}

	// LLAMA AL CATALOGO DE EQUIPOS
	public void onClick$btnBuscarEquipo2() {
		// se crea el catalogo y se llama
		Component catalogo = Executions.createComponents("/Competencias/Vistas/FrmCatalogoEquipoCompetencia.zul", null,	null);
		// asigna una referencia del formulario al catalogo.
		catalogo.setVariable("competencia", competencia, false);
		catalogo.setVariable("formulario", formulario, false);
		catalogo.setVariable("equipoCompetencia", equipoCompetencia1, false);
		catalogo.setVariable("id", 2, false);
		catalogo.setVariable("fase", fase, false);
		formulario.addEventListener("onCatalogoCerrado2", new EventListener() {
			// Este metodo se llama cuando se envia la se�al desde el catalogo
			@Override
			public void onEvent(Event arg0) throws Exception {
				// SE OBTIENE LOS EQUIPOS
				equipoCompetencia2 = (EquipoCompetencia) formulario.getVariable("equipoCompetencia", false);
				binder.loadAll();
			}
		});

	}

	// Agregado Convierte un conjunto a una lista...
	public List ConvertirConjuntoALista(Set conjunto) {
		List l = new ArrayList();
		for (Iterator i = conjunto.iterator(); i.hasNext();) {
			l.add(i.next());
		}
		return l;
	}

	// BOTONES AGREGAR Y QUITAR...
	public void onClick$btnAgregar() throws InterruptedException {
		if (cmbCategoria.getText().equalsIgnoreCase("-- Seleccione --")) {
			throw new WrongValueException(cmbCategoria,
					"Debe seleccionar una categoria");
		} else if (cmbFases.getText().equalsIgnoreCase("-- Seleccione --")) {
			throw new WrongValueException(cmbFases, "Debe seleccionar una fase");
		} else if (txtHomeClub.getValue().isEmpty()) {
			throw new WrongValueException(txtHomeClub,
					"Debe seleccionar un equipo");
		} else if (txtVisitante.getValue().isEmpty()) {
			throw new WrongValueException(txtVisitante,
					"Debe seleccionar un equipo");
		} else if (cmbEstadios.getText().equalsIgnoreCase("")) {
			throw new WrongValueException(cmbEstadios,
					"Debe seleccionar un estadio");
		} else if (dtbxFecha.getText() == "") {
			throw new WrongValueException(dtbxFecha,
					"Debe seleccionar una Fecha");
		} else if (tmbxHoraInicio.getText() == "") {
			throw new WrongValueException(tmbxHoraInicio,
					"Debe indicar la hora del juego");
		} else {
			EquipoJuego ej = new EquipoJuego();
			ej.setEquipoCompetencia(equipoCompetencia1);

			juego.setCompetencia(competencia);
			juego.setFaseCompetencia(fase);
			juego.setDatoBasico(servicioDatoBasico
					.buscarPorString("POR REALIZAR"));
			Set<EquipoJuego> equipos = new HashSet<EquipoJuego>();
			EquipoJuego equipoJuego1 = new EquipoJuego();
			equipoJuego1.setJuego(juego);
			equipoJuego1.setHomeClub(true);
			EquipoJuego equipoJuego2 = new EquipoJuego();
			equipoJuego2.setJuego(juego);
			equipoJuego2.setHomeClub(false);
			equipoJuego1.setEquipoCompetencia(equipoCompetencia1);
			equipoJuego2.setEquipoCompetencia(equipoCompetencia2);
			equipos.add(equipoJuego2);
			equipos.add(equipoJuego1);
			juego.setEquipoJuegos(equipos);
			if (buscarJuego(juego)) {
				throw new WrongValueException(lsbxEnfrentamientos,"juego duplicado");
			}
			juegos.add(juego);
			juego = new Juego();
			equipoCompetencia1 = new EquipoCompetencia();
			equipoCompetencia2 = new EquipoCompetencia();
			binder.loadAll();

		}

	}

	public boolean buscarJuego(Juego j) {

		SimpleDateFormat format = new SimpleDateFormat("hh:mm a");
		List<EquipoJuego> equipos1 = ConvertirConjuntoALista(j
				.getEquipoJuegos());
		for (Juego j2 : juegos) {
			List<EquipoJuego> equipos2 = ConvertirConjuntoALista(j2.getEquipoJuegos());
			if (j.getFecha() == j2.getFecha() && j.getHoraInicio() == j2.getHoraInicio()
					&& j.getEstadio().getCodigoEstadio() == j2.getEstadio()
							.getCodigoEstadio()) {

				return true;
			}
		

		}

		return false;
	} 
	
	public void restaurarJuego(){
		  juego = new Juego();
		  equipoCompetencia1 = new EquipoCompetencia();
		  equipoCompetencia2 = new EquipoCompetencia();
	}

	public boolean BuscarLista(EquipoJuego enfrentamientoNuevo) {

		boolean sw = false;
		for (int i = 0; i < juegos.size(); i++) {

			juegoExistente = juegos.get(i);
			if (juegoExistente.getEquipoJuegos().equals(
					enfrentamientoNuevo.getCodigoEquipoJuego())) {
				sw = true;
				break;
			}

		}
		return sw;
	}

	public void onClick$btnQuitar() {
		Quitar(lsbxEnfrentamientos, juegos);
		binder.loadAll();
	}

	public void Quitar(Listbox origen, List lista) {
		Set seleccionados = origen.getSelectedItems();
		for (Iterator i = seleccionados.iterator(); i.hasNext();) {
			Listitem li = (Listitem) i.next();
			Object o = li.getValue();
			lista.remove(o);
		}
	}

	// BOTONES GUARDAR, CANCELAR Y SALIR...

	public void onClick$btnGuardar() throws InterruptedException {
		if (juegos.size() > 0) {

			for (Juego j : juegos) {
				 servicioJuego.agregar(j);
				 List<EquipoJuego> equipos = ConvertirConjuntoALista(j.getEquipoJuegos());
				 for (EquipoJuego ej: j.getEquipoJuegos()){
					  servicioEquipoJuego.agregar(ej);
				 }
			}
			Messagebox.show("Datos agregados exitosamente", "Mensaje",Messagebox.OK, Messagebox.EXCLAMATION);
			restaurar();
			binder.loadAll();

		} else
			Messagebox.show("Seleccione la Categoria e Ingrese un Valor","Mensaje", Messagebox.OK, Messagebox.EXCLAMATION);

	}

	public void onSelect$lsbxEnfrentamientos() {
		juego = (Juego) lsbxEnfrentamientos.getSelectedItem().getValue();
		List<EquipoJuego> equipos = ConvertirConjuntoALista(juego.getEquipoJuegos());
		equipoCompetencia1 = equipos.get(0).getEquipoCompetencia();
		equipoCompetencia2 = equipos.get(1).getEquipoCompetencia();

		binder.loadAll();
	}

	public void onClick$btnCancelar() {
		restaurar();
		binder.loadAll();
	}

	public void onClick$btnTablaPosiciones() {
		Component catalogo = Executions.createComponents("/Competencias/Vistas/FrmTablaPosiciones.zul", null, null);
		binder.loadAll();
	}

	public void onClick$btnSalir() throws InterruptedException {

		if (Messagebox.show("¿Desea Salir?", "Mensaje", Messagebox.YES
				+ Messagebox.NO, Messagebox.QUESTION) == Messagebox.YES) {
			formulario.detach();
		}

	}

	// SETTERS Y GETTERS
	public Competencia getCompetencia() {
		return competencia;
	}

	public void setCompetencia(Competencia competencia) {
		this.competencia = competencia;
	}

	public List<CategoriaCompetencia> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<CategoriaCompetencia> categorias) {
		this.categorias = categorias;
	}

	public EquipoCompetencia getEquipoCompetencia1() {
		return equipoCompetencia1;
	}

	public List<FaseCompetencia> getFases() {
		return fases;
	}

	public void setFases(List<FaseCompetencia> fases) {
		this.fases = fases;
	}

	public void setEquipoCompetencia1(EquipoCompetencia equipoCompetencia1) {
		this.equipoCompetencia1 = equipoCompetencia1;
	}

	public EquipoCompetencia getEquipoCompetencia2() {
		return equipoCompetencia2;
	}

	public void setEquipoCompetencia2(EquipoCompetencia equipoCompetencia2) {
		this.equipoCompetencia2 = equipoCompetencia2;
	}

	public List<Estadio> getEstadios() {
		return estadios;
	}

	public void setEstadios(List<Estadio> estadios) {
		this.estadios = estadios;
	}

	public List<Juego> getJuegos() {
		return juegos;
	}

	public void setJuegos(List<Juego> juegos) {
		this.juegos = juegos;
	}

	public Juego getJuego() {
		return juego;
	}

	public void setJuego(Juego juego) {
		this.juego = juego;
	}

	public EquipoJuego getEquipoHomeClubJuego() {
		return equipoHomeClubJuego;
	}

	public void setEquipoHomeClubJuego(EquipoJuego equipoHomeClubJuego) {
		this.equipoHomeClubJuego = equipoHomeClubJuego;
	}

	public EquipoJuego getEquipoVisitanteJuego() {
		return equipoVisitanteJuego;
	}

	public void setEquipoVisitanteJuego(EquipoJuego equipoVisitanteJuego) {
		this.equipoVisitanteJuego = equipoVisitanteJuego;
	}

}