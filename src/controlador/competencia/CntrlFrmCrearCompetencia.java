package controlador.competencia;

import java.util.ArrayList;//
import java.util.Collection;
import java.util.Collections;//
import java.util.Comparator;//
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set; //

import javax.swing.JFileChooser;

import jxl.read.biff.File;

import comun.EstadoCompetencia;
import comun.FileLoader;

import modelo.Competencia;
import modelo.Estadio;
import modelo.FaseCompetenciaId;
//import modelo.TipoCompetencia;
import modelo.LapsoDeportivo;
import modelo.DatoBasico;
//import modelo.ModalidadCompetencia;
import modelo.Categoria;
import modelo.Liga;
import modelo.FaseCompetencia;
import modelo.TipoDato;
import modelo.CategoriaCompetencia;
import modelo.CategoriaCompetenciaId;
import modelo.FaseCompetenciaId;
import modelo.ClasificacionCompetencia;

import org.zkoss.zk.scripting.Namespace;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.InputEvent;
import org.zkoss.zk.ui.event.KeyEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Image;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Panel;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Spinner;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Button;
import org.zkoss.zk.ui.*;
import org.zkoss.zul.Listitem;//
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Decimalbox;
import org.zkoss.zul.Doublebox;

import servicio.implementacion.ServicioCompetencia;
import servicio.implementacion.ServicioFaseCompetencia;
//import servicio.implementacion.ServicioTipoCompetencia;
import servicio.implementacion.ServicioLapsoDeportivo;
import servicio.implementacion.ServicioDatoBasico;
//import servicio.implementacion.ServicioModalidadCompetencia;
import servicio.implementacion.ServicioCategoria;
import servicio.implementacion.ServicioLiga;
import servicio.implementacion.ServicioCategoriaCompetencia;
import servicio.implementacion.ServicioClasificacionCompetencia;

import org.zkoss.zk.ui.util.GenericForwardComposer;

public class CntrlFrmCrearCompetencia extends GenericForwardComposer {

	AnnotateDataBinder binder;
	Component formulario;

	Competencia competencia;

	ServicioClasificacionCompetencia servicioClasificacionCompetencia;
	ServicioCompetencia servicioCompetencia;
	// ServicioTipoCompetencia servicioTipoCompetencia;
	ServicioLapsoDeportivo servicioLapsoDeportivo;
	ServicioDatoBasico servicioDatoBasico;
	// ServicioModalidadCompetencia servicioModalidadCompetencia;
	ServicioCategoria servicioCategoria;
	ServicioLiga servicioLiga, servicioLigaAux;
	ServicioCategoriaCompetencia servicioCategoriaCompetencia;
	ServicioFaseCompetencia servicioFaseCompetencia;

	// List<ClasificacionCompetencia> clasificacionCompetencias;
	List<DatoBasico> tiposCompetencias;
	List<ClasificacionCompetencia> clasificacionCompetencias;

	// List<TipoCompetencia> tipoCompetencias;

	List<LapsoDeportivo> lapsoDeportivos;
	List<DatoBasico> estados, OrganizacionCompetencias;
	// List<ModalidadCompetencia> modalidadCompetencias;
	List<Categoria> categorias, categoriaLigas2;
	List<Liga> ligas, ligasAux = new ArrayList<Liga>();
	List<String> fases = new ArrayList<String>();
	List<CategoriaCompetencia> categoriaCompetencias = new ArrayList<CategoriaCompetencia>(),
			categoriaCompetenciasAux = new ArrayList<CategoriaCompetencia>(),
			categoriaLigas = new ArrayList<CategoriaCompetencia>();
	List<FaseCompetencia> faseCompetencias = new ArrayList<FaseCompetencia>();

	// TipoCompetencia tipoCompetencia;
	ClasificacionCompetencia clacificacionCompetencia;
	LapsoDeportivo lapsoDeportivo;
	DatoBasico datoBasio, organizacionCompetencia, estado;
	// ModalidadCompetencia modalidadCompetencia;
	Categoria categoria;
	Liga liga, ligaAux;
	FaseCompetencia faseCompetencia = new FaseCompetencia();
	FaseCompetenciaId faseID = new FaseCompetenciaId();
	CategoriaCompetencia categoriaCompetencia, ligaCategCompetencia;
	CategoriaCompetenciaId categCompID;

	Button btnMoverD, btnMoverI, btnExaminar,btnApertura,btnClausura;
	Combobox cmbTipoCompetencia, cmbNroFases, cmbTipoOrganizacion,
			cmbTemporada, cmbEstado, cmbClasificacion;
	Datebox datefechaInicio, datefechaFin;
	Doublebox txtmontoInscripcion;
	Listbox lsbxFases, lsbxCategorias, lsbxLigas, lsbxLigasSeleccionadas,
			lsbxligasCategorias;
	Listitem itemFases;
	Panel pnlCategorias;
	Spinner spnNroFases, spnNroJugadores;
	Tab tabLigaPorCompetencia, tabCategoriaPorCompetencia;
	Textbox txtNombre;

	String fase, tipoOrg;

	int ining, horas;

	private Image img;
	private EventListener arg;

	public void doAfterCompose(Component c) throws Exception {
		super.doAfterCompose(c);
		c.setVariable("cntrl", this, true);
		formulario = c;
		competencia = new Competencia();
		tipoOrg = new String();
		// clasificacionCompetencias =
		// servicioClasificacionCompetencia.listarActivos();
		tiposCompetencias = servicioDatoBasico
				.listarTipoDato("TIPO COMPETENCIA");
		lapsoDeportivos = servicioLapsoDeportivo.listarActivos();
		estados = servicioDatoBasico.listarEstados();
		ordenarEstado(estados);
		OrganizacionCompetencias = servicioDatoBasico
				.listarOrganizacionCompetencia();
		categorias = servicioCategoria.listarActivos();
		ligas = servicioLiga.listarActivos();
		ordenarLiga(ligas);
		competencia.setCantidadFase(1);
		competencia.setCantidadJugador(9);
		txtmontoInscripcion.setText("");

		faseID.setNumeroFase(1);
		faseCompetencia.setId(faseID);
		faseCompetencia.setEquipoIngresan(2);
		faseCompetencia.setEquipoClasifican(2);
		faseCompetencias.add(faseCompetencia);

		// datefechaInicio.setDisabled(true);
		// datefechaFin.setDisabled(true);
	}

	// Habilita y desabilita los tabs de LigaPorCategoria y
	// CategoriaPorCompetencia
	public void onChange$cmbTipoOrganizacion() {

		DatoBasico org = (DatoBasico) cmbTipoOrganizacion.getSelectedItem()
				.getValue();

		if (org.getCodigoDatoBasico() == 312) {
			tabLigaPorCompetencia.setVisible(false);
			tabCategoriaPorCompetencia.setVisible(true);
			tipoOrg = "C";
			cargarCategCompetencia();

		} else {
			tabLigaPorCompetencia.setVisible(true);
			tipoOrg = "L";
			tabCategoriaPorCompetencia.setVisible(false);
		}
	}

	public void onChanging$spnNroFases(InputEvent event) {

		faseCompetencias.removeAll(faseCompetencias);

		Integer cantFases = Integer.valueOf(event.getValue());

		agregarFases(cantFases);
		/*
		 * for(int i =1;i<=cantFases;i++){
		 * 
		 * faseCompetencia = new FaseCompetencia(); FaseCompetenciaId faseID =
		 * new FaseCompetenciaId();
		 * 
		 * faseID.setNumeroFase(i);
		 * 
		 * faseCompetencia.setId(faseID);
		 * System.out.println(faseCompetencia.getId().getNumeroFase());
		 * faseCompetencias.add(faseCompetencia); binder.loadAll();
		 * 
		 * }
		 */
	}

	// Agrega fases de la cometencia segun la cantidad de fases indicada
	public void agregarFases(int cantfase) {

		for (int i = 1; i <= cantfase; i++) {

			faseCompetencia = new FaseCompetencia();
			FaseCompetenciaId faseID = new FaseCompetenciaId();
			faseID.setNumeroFase(i);

			faseCompetencia.setId(faseID);
			faseCompetencia.setCompetencia(competencia);
			// System.out.println(faseCompetencia.getId().getNumeroFase());
			faseCompetencias.add(faseCompetencia);
			binder.loadAll();

		}
	}

	// Determina los valores tope para las fechas de inicio y de fin
	public void onChange$cmbTemporada() {

		LapsoDeportivo lapso = (LapsoDeportivo) cmbTemporada.getSelectedItem()
				.getValue();
		// Date fechainicio = lapso.getFechaInicio();
		// Date fechafin = lapso.getFechaFin();
		// binder.loadAll();
		//
		//
		// datefechaInicio.setValue(fechainicio);
		// datefechaFin.setValue(fechafin);
		//
		// datefechaInicio.setDisabled(false);
		// datefechaFin.setDisabled(false);

	}

	// Valida que la fecha de inicio para la competencia sea correcta
	public void onChange$datefechaInicio() throws InterruptedException {

		// Date fecha;
		//
		// LapsoDeportivo lapso = (LapsoDeportivo)
		// cmbTemporada.getSelectedItem().getValue();
		// Date fechainicio = lapso.getFechaInicio();
		// Date fechafin = lapso.getFechaFin();
		//
		// fecha = datefechaInicio.getValue();
		//
		// if (fecha.before(fechainicio)){
		// Messagebox.show("Debe introducir una fecha dentro del rango("+fechainicio+" / "+fechafin+")",
		// "Mensaje", Messagebox.OK, Messagebox.EXCLAMATION);
		// datefechaInicio.setValue(fechainicio);
		// }
		//
		// if (fecha.after(fechafin)){
		// Messagebox.show("Debe introducir una fecha dentro del rango("+fechainicio+" / "+fechafin+")",
		// "Mensaje", Messagebox.OK, Messagebox.EXCLAMATION);
		// datefechaInicio.setValue(fechainicio);
		// }

	}

	public void onChange$datefechaFin() throws InterruptedException {

		// Date fecha;
		//
		//
		// LapsoDeportivo lapso = (LapsoDeportivo)
		// cmbTemporada.getSelectedItem().getValue();
		// Date fechainicio = lapso.getFechaInicio();
		// Date fechafin = lapso.getFechaFin();
		//
		// fecha = datefechaFin.getValue();
		//
		//
		// if (fecha.before(fechainicio)){
		// Messagebox.show("Debe introducir una fecha dentro del rango("+fechainicio+" / "+fechafin+")",
		// "Mensaje", Messagebox.OK, Messagebox.EXCLAMATION);
		// datefechaFin.setValue(fechafin);
		// }
		//
		// if (fecha.after(fechafin)){
		// Messagebox.show("Debe introducir una fecha dentro del rango("+fechainicio+" / "+fechafin+")",
		// "Mensaje", Messagebox.OK, Messagebox.EXCLAMATION);
		// datefechaFin.setValue(fechafin);
		// }

	}

	public void onChange$cmbTipoCompetencia() {

		DatoBasico db = (DatoBasico) cmbTipoCompetencia.getSelectedItem()
				.getValue();
		System.out.println(db.getNombre());

		clasificacionCompetencias = servicioClasificacionCompetencia
				.listarClasificacion(db);
		cmbClasificacion.setText("--Seleccione--");

		// TipoCompetencia tipocomp = (TipoCompetencia)
		// cmbTipoCompetencia.getSelectedItem().getValue();

		// modalidadCompetencias =
		// servicioModalidadCompetencia.listarModalidad(tipocomp);
		binder.loadAll();
	}

	// Llama al metodo que permite seleccionar Ligas por Competencia
	public void onClick$btnMoverD() {

		seleccionarLigasD();
	}

	// Llama al metodo que permite quitar las Ligas por Competencia
	// seleccionadas
	public void onClick$btnMoverI() {

		seleccionarLigasI();
	}

	// Llama al metodo cargar que por ahora no hace nada
	public void onClick$btnExaminar() {

		Cargar();
	}

	// Metodo para cargar un archivo pdf
	public void Cargar() {

		new FileLoader().cargarArchivo();

	}

	// Mueve las ligas del lsbxLigas a lsbxligasseleccionadas
	public void seleccionarLigasD() {

		int cant = ligasAux.size();

		Set set = lsbxLigas.getSelectedItems();
		for (Object obj : new ArrayList(set)) {
			Liga lig = (Liga) ((Listitem) obj).getValue();

			if (cant > 0) {
				Set conjligasAux = ConvertirListaAConjunto(ligasAux);
				Iterator iter = conjligasAux.iterator();
				Liga ligaAux;

				while (iter.hasNext()) {
					ligaAux = (Liga) iter.next();

					if (lig != ligaAux) {
						ligasAux.add(lig);

						categoriaLigas2 = ConvertirConjuntoALista(lig
								.getCategorias());

						Set conj = ConvertirListaAConjunto(categoriaLigas2);

						Iterator iter2 = conj.iterator();

						Categoria cat;

						while (iter2.hasNext()) {
							cat = (Categoria) iter2.next();
							// System.out.println(cat.getNombre());

							CategoriaCompetencia cc = new CategoriaCompetencia();

							cc.setCategoria(cat);
							cc.setCompetencia(competencia);
							// System.out.println(cc.getCategoria().getNombre());
							categoriaLigas.add(cc);
							binder.loadAll();

						}

						binder.loadAll();
					}

				}
			} else {

				ligasAux.add(lig);

				categoriaLigas2 = ConvertirConjuntoALista(lig.getCategorias());

				Set conj = ConvertirListaAConjunto(categoriaLigas2);

				Iterator iter2 = conj.iterator();

				Categoria cat;

				while (iter2.hasNext()) {
					cat = (Categoria) iter2.next();
					// System.out.println(cat.getNombre());

					CategoriaCompetencia cc = new CategoriaCompetencia();

					cc.setCategoria(cat);
					cc.setCompetencia(competencia);
					// System.out.println(cc.getCategoria().getNombre());
					categoriaLigas.add(cc);
					binder.loadAll();

				}

				binder.loadAll();

			}

			binder.loadAll();

		}
		ordenarLiga(ligasAux);

	}

	// Mueve las ligas del lsbxligasseleccionadas a lsbxLigas
	public void seleccionarLigasI() {
		Set set = lsbxLigasSeleccionadas.getSelectedItems();
		for (Object obj : new ArrayList(set)) {
			Liga lig = (Liga) ((Listitem) obj).getValue();

			// ligas.add(lig);
			ligasAux.remove(lig);

			categoriaLigas2 = ConvertirConjuntoALista(lig.getCategorias());

			Set conj = ConvertirListaAConjunto(categoriaLigas2);

			Iterator iter = conj.iterator();

			Categoria cat;

			while (iter.hasNext()) {
				cat = (Categoria) iter.next();
				// System.out.println(cat.getNombre());

				CategoriaCompetencia cc = new CategoriaCompetencia();

				Set conjligacatg = ConvertirListaAConjunto(categoriaLigas);

				Iterator iter2 = conjligacatg.iterator();

				while (iter2.hasNext()) {
					cc = (CategoriaCompetencia) iter2.next();

					if (cat == cc.getCategoria()) {

						categoriaLigas.remove(cc);
					}
				}
				binder.loadAll();

			}

			// while (iter.hasNext()){ cat = (Categoria) iter.next();
			// categoriaLigas.remove(cat);}

			binder.loadAll();
		}
		ordenarLiga(ligas);
	}

	// Ordena una lista de tipo Liga por nombre
	public void ordenarLiga(List<Liga> ligLista) {

		Collections.sort(ligLista, new Comparator() {

			public int compare(Object o1, Object o2) {
				Liga liga1 = (Liga) o1;
				Liga liga2 = (Liga) o2;
				return liga1.getNombre().compareToIgnoreCase(liga2.getNombre());
			}
		});
	}

	// Ordena una lista de Estados por nombre
	public void ordenarEstado(List<DatoBasico> estadoLista) {

		Collections.sort(estadoLista, new Comparator() {

			public int compare(Object o1, Object o2) {
				DatoBasico estado1 = (DatoBasico) o1;
				DatoBasico estado2 = (DatoBasico) o2;
				return estado1.getNombre().compareToIgnoreCase(
						estado2.getNombre());
			}
		});
	}

	// Ordena una lista de LapsoDeportivo por nombre
	public void ordenarlapso(List<LapsoDeportivo> lapsoLista) {

		Collections.sort(lapsoLista, new Comparator() {

			public int compare(Object o1, Object o2) {
				LapsoDeportivo lapso1 = (LapsoDeportivo) o1;
				LapsoDeportivo lapso2 = (LapsoDeportivo) o2;
				return lapso1.getNombre().compareToIgnoreCase(
						lapso2.getNombre());
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

	// Agregado Convierte una lista a un conjunto...
	public Set ConvertirListaAConjunto(List lista) {
		Set c = new HashSet();
		for (Iterator i = lista.iterator(); i.hasNext();) {
			c.add(i.next());
		}
		return c;
	}

	public void cargarCategCompetencia() {

		Set conj = ConvertirListaAConjunto(categorias);
		Iterator iter = conj.iterator();
		Categoria cat;

		while (iter.hasNext()) {
			cat = (Categoria) iter.next();
			// System.out.println(cat.getNombre());
			CategoriaCompetencia cc = new CategoriaCompetencia();
			cc.setCategoria(cat);
			// System.out.println(cc.getCategoria().getNombre());
			categoriaCompetencias.add(cc);
			binder.loadAll();
		}

	}

	public void onClick$btnBuscarCompetencia() {

		Component catalogo = Executions.createComponents(
				"/Competencias/Vistas/FrmCatalogoCompetencia.zul", null, null);
		catalogo.setVariable("formulario", formulario, false);
		catalogo.setVariable("estatus", EstadoCompetencia.REGISTRADA, false);
		catalogo.setVariable("codigo", EstadoCompetencia.REGISTRADA, false);

		formulario.addEventListener("onCatalogoCerrado", new EventListener() {
			@Override
			public void onEvent(Event arg0) throws Exception {
				competencia = (Competencia) formulario.getVariable(
						"competencia", false);

				cmbTemporada.setSelectedIndex(buscarLapsoDeportivo(
						competencia.getLapsoDeportivo(), lapsoDeportivos));
				cmbEstado.setSelectedIndex(buscarDatoBasico(
						competencia.getDatoBasicoByCodigoEstado(), estados));
				cmbTipoOrganizacion.setSelectedIndex(buscarDatoBasico(
						competencia.getDatoBasicoByCodigoOrganizacion(),
						OrganizacionCompetencias));

				DatoBasico org = (DatoBasico) cmbTipoOrganizacion
						.getSelectedItem().getValue();

				if (org.getCodigoDatoBasico() == 312) {
					tabLigaPorCompetencia.setVisible(false);
					tabCategoriaPorCompetencia.setVisible(true);
					tipoOrg = "C";

				} else {
					tabLigaPorCompetencia.setVisible(true);
					tipoOrg = "L";
					tabCategoriaPorCompetencia.setVisible(false);
				}

				categorias = ConvertirConjuntoALista(competencia
						.getCategoriaCompetencias());

                
                cmbTipoCompetencia.setSelectedIndex(buscarDatoBasico(competencia.getClasificacionCompetencia().getDatoBasico(),tiposCompetencias));
                cmbClasificacion.setText(competencia.getClasificacionCompetencia().getNombre());
				binder.loadAll();
			}
		});
	}
    	
	public void llenar(List<DatoBasico> lista, Combobox combo) {
		for (Iterator i = lista.iterator(); i.hasNext();) {
			DatoBasico db = (DatoBasico) i.next();
			Comboitem ci = new Comboitem(db.getNombre());
			ci.setValue(db);
			combo.appendChild(ci);
		}
	}

	public int buscarDatoBasico(DatoBasico d, List l) {
		int j = -1;
		for (Iterator<DatoBasico> i = l.iterator(); i.hasNext();) {
			DatoBasico db = i.next();
			j++;
			if (db.getNombre().equals(d.getNombre())) {
				return j;
			}
		}
		return j;
	}

	public int buscarLapsoDeportivo(LapsoDeportivo d, List l) {
		int j = -1;
		for (Iterator<LapsoDeportivo> i = l.iterator(); i.hasNext();) {
			LapsoDeportivo db = i.next();
			j++;
			if (db.getNombre().equals(d.getNombre())) {
				return j;
			}
		}
		return j;
	}

	public void seleccionarCategoriasAGuardar(Listbox listaCategComp) {

		Set set = listaCategComp.getSelectedItems();
		for (Object obj : new ArrayList(set)) {

			CategoriaCompetencia categ = new CategoriaCompetencia();
			categ = (CategoriaCompetencia) ((Listitem) obj).getValue();

			categ.setCompetencia(competencia);
			categoriaCompetenciasAux.add(categ);
		}

	}

	public void llevarAMayusculas() {

		competencia.setNombre(competencia.getNombre().toUpperCase()
				.toUpperCase());
		competencia.setCondicionesGenerales(competencia
				.getCondicionesGenerales().toUpperCase());
		competencia.setDesempate(competencia.getDesempate().toUpperCase());
		competencia.setExtrainning(competencia.getExtrainning().toUpperCase());

	}

	public void agregarEstadoCompetencia() {

		DatoBasico dato;
		TipoDato tipo;
		tipo = new TipoDato();
		dato = new DatoBasico();

		tipo.setCodigoTipoDato(114);
		tipo.setNombre("ESTADO COMPETENCIA");
		tipo.setDescripcion("ALMACENA LOS DIFERENTES ESTADOS DE UNA COMPETENCIA");
		tipo.setEstatus('A');
		tipo.setTipo(true);

		dato.setCodigoDatoBasico(287);
		dato.setNombre("REGISTRADA");
		dato.setTipoDato(tipo);
		dato.setEstatus('A');

	}

	public void onClick$btnApertura() throws InterruptedException {
		if (Messagebox
				.show("Una vez aperturada una competencia no se podran agregar equipos ni jugadores adicionales. ¿Desea continuar?",
						"Mensaje", Messagebox.YES + Messagebox.NO,
						Messagebox.QUESTION) == Messagebox.YES) {

			Messagebox.show("Competencia Aperturada", "Mensaje",
					Messagebox.OK, Messagebox.EXCLAMATION);
			btnApertura.setDisabled(true);
			btnClausura.setDisabled(false);
		}
	}

	public void onClick$btnGuardar() throws InterruptedException {

//		DatoBasico dato;
//		TipoDato tipo;
//		tipo = new TipoDato();
//		dato = new DatoBasico();
//
//		tipo.setCodigoTipoDato(114);
//		tipo.setNombre("ESTADO COMPETENCIA");
//		tipo.setDescripcion("ALMACENA LOS DIFERENTES ESTADOS DE UNA COMPETENCIA");
//		tipo.setEstatus('A');
//		tipo.setTipo(true);
//
//		dato.setCodigoDatoBasico(287);
//		dato.setNombre("REGISTRADA");
//		dato.setTipoDato(tipo);
//		dato.setEstatus('A');
//
//		if (tipoOrg == "C") {
//			seleccionarCategoriasAGuardar(lsbxCategorias);
//		} else {
//			categoriaCompetenciasAux = categoriaLigas;
//		}
//
//		llevarAMayusculas();

		// competencia.setModalidadCompetencia((ModalidadCompetencia)
		// cmbModalidadCompetencia.getSelectedItem().getValue());

//		competencia
//				.setClasificacionCompetencia((ClasificacionCompetencia) cmbClasificacion
//						.getSelectedItem().getValue());
//		competencia
//				.setDatoBasicoByCodigoOrganizacion((DatoBasico) cmbTipoOrganizacion
//						.getSelectedItem().getValue());
//		competencia.setLapsoDeportivo((LapsoDeportivo) cmbTemporada
//				.getSelectedItem().getValue());
//		competencia.setDatoBasicoByCodigoEstado((DatoBasico) cmbEstado
//				.getSelectedItem().getValue());
//		competencia.setDatoBasicoByCodigoEstadoCompetencia(dato);
//
//		System.out.println(faseCompetencias.size());
//
//		servicioCompetencia.agregar(competencia);
//		int codcomp = servicioCompetencia.obtenerCodigoCompetencia();
//		servicioCategoriaCompetencia.agregar(categoriaCompetenciasAux, codcomp);
		// servicioFaseCompetencia.agregar(faseCompetencias,codcomp);

		Messagebox.show("Datos agregados exitosamente", "Mensaje",
				Messagebox.OK, Messagebox.EXCLAMATION);
		restaurar();
		binder.loadAll();
	}

	public void onClick$btnEliminar() throws InterruptedException {

		if (Messagebox.show("¿Realmente desea eliminar esta Competencia",
				"Mensaje", Messagebox.YES + Messagebox.NO, Messagebox.QUESTION) == Messagebox.YES) {
			servicioCompetencia.eliminar(competencia);
			restaurar();
			binder.loadAll();
			Messagebox.show("Datos eliminados exitosamente", "Mensaje",
					Messagebox.OK, Messagebox.EXCLAMATION);
		}

	}

	public void restaurar() {

		competencia = new Competencia();

		// tipoCompetencias = servicioTipoCompetencia.listarActivos();
		lapsoDeportivos = servicioLapsoDeportivo.listarActivos();
		estados = servicioDatoBasico.listarEstados();
		ordenarEstado(estados);
		categorias = servicioCategoria.listarActivos();
		ligas = servicioLiga.listarActivos();
		ordenarLiga(ligas);

		cmbTipoCompetencia.setText("-- Seleccione --");
		// cmbModalidadCompetencia.setText("-- Seleccione --");
		cmbTipoOrganizacion.setText("-- Seleccione --");
		cmbTemporada.setText("-- Seleccione --");
		cmbEstado.setText("-- Seleccione --");

		tabLigaPorCompetencia.setVisible(false);
		tabCategoriaPorCompetencia.setVisible(false);

		datefechaInicio.setDisabled(true);
		datefechaFin.setDisabled(true);

		faseCompetencias.removeAll(faseCompetencias);
		faseID.setNumeroFase(1);
		faseCompetencia.setId(faseID);
		faseCompetencia.setEquipoIngresan(2);
		faseCompetencia.setEquipoClasifican(2);
		faseCompetencias.add(faseCompetencia);

		categoriaCompetenciasAux.removeAll(categoriaCompetenciasAux);

		categoriaLigas.removeAll(categoriaLigas);

		ligasAux.removeAll(ligasAux);

	}

	public void onClick$btnCancelar() {
		restaurar();
		binder.loadAll();
	}

	public void onClick$btnSalir() {

		// formulario.detach();

	}

	// GET y SET para Competencia
	public Competencia getCompetencia() {
		return competencia;
	}

	public void setCompetencia(Competencia competencia) {
		this.competencia = competencia;
	}

	// ////

	public ServicioCompetencia getServicioCompetencia() {
		return servicioCompetencia;
	}

	// GET y SET para servicioCompetencia
	public void setServicioCompetencia(ServicioCompetencia servicioCompetencia) {
		this.servicioCompetencia = servicioCompetencia;
	}

	// ////

	// GET y SET para Tipo de Competencia
	// public List<ClasificacionCompetencia> getClasificacionCompetencias() {
	// return clasificacionCompetencias;
	// }
	//
	// public void ClasificacionCompetencias(List<ClasificacionCompetencia>
	// clasificacionCompetencias) {
	// this.clasificacionCompetencias = clasificacionCompetencias;
	// }
	// ////

	// GET y SET para Lapso Deportivo
	public List<LapsoDeportivo> getLapsoDeportivos() {
		return lapsoDeportivos;
	}

	public void setLapsoDeportivos(List<LapsoDeportivo> lapsoDeportivos) {
		this.lapsoDeportivos = lapsoDeportivos;
	}

	// ////

	// GET y SET para Estados
	public List<DatoBasico> getEstados() {
		return estados;
	}

	public void setEstados(List<DatoBasico> estados) {
		this.estados = estados;
	}

	// ////

	// GET y SET para Categoria
	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

	// ////

	// GET y SET para Liga
	public List<Liga> getLigas() {
		return ligas;
	}

	public void setLigas(List<Liga> ligas) {
		this.ligas = ligas;
	}

	// ////

	// GET y SET para LigaAux
	public List<Liga> getLigasAux() {
		return ligasAux;
	}

	public void setLigasAux(List<Liga> ligasAux) {
		this.ligasAux = ligasAux;
	}

	// ////

	public List<String> getFases() {
		return fases;
	}

	public void setFases(List<String> fases) {
		this.fases = fases;
	}

	public String getFase() {
		return fase;
	}

	public void setFase(String fase) {
		this.fase = fase;
	}

	public List<CategoriaCompetencia> getCategoriaLigas() {
		return categoriaLigas;
	}

	public void setCategoriaLigas(List<CategoriaCompetencia> categoriaLigas) {
		this.categoriaLigas = categoriaLigas;
	}

	// GET y SET para OrganizacionCompetencias
	public List<DatoBasico> getOrganizacionCompetencias() {
		return OrganizacionCompetencias;
	}

	public void setOrganizacionCompetencias(
			List<DatoBasico> organizacionCompetencias) {
		OrganizacionCompetencias = organizacionCompetencias;
	}

	// ////

	public List<CategoriaCompetencia> getCategoriaCompetencias() {
		return categoriaCompetencias;
	}

	public void setCategoriaCompetencias(
			List<CategoriaCompetencia> categoriaCompetencias) {
		this.categoriaCompetencias = categoriaCompetencias;
	}

	public CategoriaCompetencia getCategoriaCompetencia() {
		return categoriaCompetencia;
	}

	public void setCategCompetencia(CategoriaCompetencia categoriaCompetencia) {
		this.categoriaCompetencia = categoriaCompetencia;
	}

	public List<CategoriaCompetencia> getCategoriaCompetenciasAux() {
		return categoriaCompetenciasAux;
	}

	public void setCategoriaCompetenciasAux(
			List<CategoriaCompetencia> categoriaCompetenciasAux) {
		this.categoriaCompetenciasAux = categoriaCompetenciasAux;
	}

	public String getTipoOrg() {
		return tipoOrg;
	}

	public void setTipoOrg(String tipoOrg) {
		this.tipoOrg = tipoOrg;
	}

	public List<FaseCompetencia> getFaseCompetencias() {
		return faseCompetencias;
	}

	public void setFaseCompetencias(List<FaseCompetencia> faseCompetencias) {
		this.faseCompetencias = faseCompetencias;
	}

	public void onChanging$txtNombre(InputEvent event) {

		String valor = event.getValue();

		String viejo = new String();
		String nuevo = new String();

		if (valor.length() > 0) {
			// char v = valor.charAt(valor.length()-1);

			Character caracter = valor.charAt(valor.length() - 1);

			// System.out.println(caracter);

			if (!esValido(caracter)) {
				String texto = "";

				for (int i = 0; i < valor.length() - 1; i++) {

					// System.out.println(valor);
					// System.out.println(valor.charAt(i));
					// viejo += valor.charAt(i);

					// nuevo += valor.charAt(i);
					viejo += valor.charAt(i);

					// if (esValido(new Character(valor.charAt(i+1))))
					// texto += valor.charAt(i);

					// competencia.setNombre(viejo);
				}

				// System.out.println(viejo);
				// txtNombre.setValue(viejo);
				// txtNombre.setText(viejo);
				competencia.setNombre(viejo);
				binder.loadAll();

			}
		}
	}

	public boolean esValido(Character caracter) {
		char c = caracter.charValue();
		if (!(Character.isLetter(c) // si es letra
				|| c == ' ' // o un espacio
		|| c == 8 // o backspace
		))
			return false;
		else
			return true;
	}

	// Get and Set de tiposCompetencias
	public List<DatoBasico> getTiposCompetencias() {
		return tiposCompetencias;
	}

	public void setTiposCompetencias(List<DatoBasico> tiposCompetencias) {
		this.tiposCompetencias = tiposCompetencias;
	}

	// Get and Set ClasificacionCompetencias
	public List<ClasificacionCompetencia> getClasificacionCompetencias() {
		return clasificacionCompetencias;
	}

	public void setClasificacionCompetencias(
			List<ClasificacionCompetencia> clasificacionCompetencias) {
		this.clasificacionCompetencias = clasificacionCompetencias;
	}

}
