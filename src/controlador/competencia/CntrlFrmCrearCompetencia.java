package controlador.competencia;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set; 

import comun.EstadoCompetencia;
import comun.FileLoader;

import modelo.Categoria;
import modelo.CategoriaCompetencia;
import modelo.CategoriaCompetenciaId;
import modelo.CategoriaLiga;
import modelo.ClasificacionCompetencia;
import modelo.Competencia;
import modelo.DatoBasico;
import modelo.FaseCompetencia;
import modelo.FaseCompetenciaId;
import modelo.LapsoDeportivo;
import modelo.Liga;
import modelo.TipoDato;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.InputEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;

import org.zkoss.zkplus.databind.AnnotateDataBinder;

import org.zkoss.zul.Button;

import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Doublebox;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Panel;
import org.zkoss.zul.Spinner;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Timebox;

import servicio.implementacion.ServicioCategoria;
import servicio.implementacion.ServicioCategoriaCompetencia;
import servicio.implementacion.ServicioClasificacionCompetencia;
import servicio.implementacion.ServicioCompetencia;
import servicio.implementacion.ServicioDatoBasico;
import servicio.implementacion.ServicioFaseCompetencia;
import servicio.implementacion.ServicioLapsoDeportivo;
import servicio.implementacion.ServicioLiga;

import org.zkoss.zk.ui.util.GenericForwardComposer;

import controlador.jugador.restriccion.Restriccion;

public class CntrlFrmCrearCompetencia extends GenericForwardComposer {

	AnnotateDataBinder binder;
	Component formulario;

	Competencia competencia;

	ServicioCategoria servicioCategoria;
	ServicioCategoriaCompetencia servicioCategoriaCompetencia;
	ServicioClasificacionCompetencia servicioClasificacionCompetencia;
	ServicioCompetencia servicioCompetencia;
	ServicioDatoBasico servicioDatoBasico;
	ServicioFaseCompetencia servicioFaseCompetencia;
	ServicioLapsoDeportivo servicioLapsoDeportivo;
	ServicioLiga servicioLiga, servicioLigaAux;

	// List<ClasificacionCompetencia> clasificacionCompetencias;
	List<Categoria> categorias = new ArrayList<Categoria>(), categoriaLigas2;
	List<CategoriaCompetencia> categoriaCompetencias = new ArrayList<CategoriaCompetencia>(),
								 categoriaCompetenciasAux = new ArrayList<CategoriaCompetencia>(),
									categoriaLigas = new ArrayList<CategoriaCompetencia>();
	List<ClasificacionCompetencia> clasificacionCompetencias;
	List<DatoBasico> tiposCompetencias,estados, OrganizacionCompetencias;;
	List<FaseCompetencia> faseCompetencias = new ArrayList<FaseCompetencia>(), 
							faseCompetenciasAux = new ArrayList<FaseCompetencia>();
	List<LapsoDeportivo> lapsoDeportivos;
	List<Liga> ligas, ligasAux = new ArrayList<Liga>();

	ClasificacionCompetencia clacificacionCompetencia;
	LapsoDeportivo lapsoDeportivo;
	DatoBasico datoBasio, organizacionCompetencia, estado;
	Categoria categoria;
	Liga liga, ligaAux;
	FaseCompetencia faseCompetencia;
	FaseCompetenciaId faseID;
	CategoriaCompetencia categoriaCompetencia, ligaCategCompetencia;
	CategoriaCompetenciaId categCompID;

	Button btnMoverD, btnMoverI, btnExaminar,btnApertura,btnClausura,btnBuscarCompetencia;
	Combobox cmbTipoCompetencia, cmbNroFases, cmbTipoOrganizacion,
			cmbTemporada, cmbEstado, cmbClasificacion;
	Datebox datefechaInicio, datefechaFin;
	Doublebox txtmontoInscripcion;
	Listbox lsbxFases, lsbxCategorias, lsbxLigas, lsbxLigasSeleccionadas,
			lsbxligasCategorias, lsbxCategoriasAux;
	Listitem itemFases;
	Listitem itemCategoria;
	Panel pnlCategorias;
	Spinner spnNroFases, spnNroJugadores, spnIning;
	Tab tabRegistrosBasicos, tabLigaPorCompetencia, tabCategoriaPorCompetencia;
	Textbox txtNombre,txtcondicionesGenerales,txtdesempate,txtextrainning;
	Timebox spnHora;

	String fase, tipoOrg;

	int ining, horas ;
	int codigoliga;
	boolean actualizar = false, aperturada = false, comcategcarg = false, modfclasificacion = false, lsbxcategcargado = false, controlcateg = false;
	DatoBasico dato;
	TipoDato tipo;


	public void doAfterCompose(Component c) throws Exception {
		super.doAfterCompose(c);
		c.setVariable("cntrl", this, true);
		formulario = c;
		competencia = new Competencia();
		tipoOrg = new String();
		tipo = new TipoDato();
		dato = new DatoBasico();
		faseCompetencia = new FaseCompetencia();
		faseID = new FaseCompetenciaId();
		tiposCompetencias = servicioDatoBasico.listarTipoDato("TIPO COMPETENCIA");
		ordenarDatoBasico(tiposCompetencias);
		lapsoDeportivos = servicioLapsoDeportivo.listarActivos();
		ordenarlapso(lapsoDeportivos);
		estados = servicioDatoBasico.listarEstados();
		ordenarDatoBasico(estados);
		OrganizacionCompetencias = servicioDatoBasico.listarOrganizacionCompetencia();
		ordenarDatoBasico(OrganizacionCompetencias);
//		categorias = servicioCategoria.listarActivos();
		
		ligas = servicioLiga.listarActivos();
		ordenarLiga(ligas);
		competencia.setCantidadFase(1);
		competencia.setCantidadJugador(9);
		txtmontoInscripcion.setText("");

		faseID.setNumeroFase(1);
//		faseCompetencia.setId(faseID);
		faseCompetencia.setNumeroFase(1);
		faseCompetencia.setEquipoIngresan(2);
		faseCompetencia.setEquipoClasifican(2);
		faseCompetencias.add(faseCompetencia);
		
		cmbClasificacion.setDisabled(true);
		
		cmbTipoCompetencia.setText("-- Seleccione --");
		cmbTipoOrganizacion.setText("-- Seleccione --");
		cmbTemporada.setText("-- Seleccione --");
		cmbEstado.setText("-- Seleccione --");
		cmbClasificacion.setText("-- Seleccione --");

		// datefechaInicio.setDisabled(true);
		// datefechaFin.setDisabled(true);
		btnApertura.setLabel("Apertura/Clausura");
		aplicarConstraints();
		codigoliga = 0;
		Date hora = new Date(0,0,0,1,0,0);
//		spnHora.setValue(hora);
//		spnIning.setValue(1);
		lsbxligasCategorias.setVisible(false);
//		lsbxCategorias.setVisible(true);
//		lsbxCategoriasAux.setVisible(false);
		cmbTipoOrganizacion.setDisabled(false);
	}

	
	// Lista los tipos de clasificacion dependiendo del tipo de competencia seleccionado
	public void onChange$cmbTipoCompetencia() { 
		
		DatoBasico db = (DatoBasico) cmbTipoCompetencia.getSelectedItem().getValue();
		cmbClasificacion.setDisabled(false);
		cmbClasificacion.setText("-- Seleccione --");
		clasificacionCompetencias = servicioClasificacionCompetencia.listarClasificacion(db);
		ordenarClasificacion(clasificacionCompetencias);
		binder.loadAll();
	}	

	
	// Agrega el tipo de clasificacion seleccionado a la competencia
	public void onChange$cmbClasificacion(){
		
		modfclasificacion = true;
		competencia.setClasificacionCompetencia((ClasificacionCompetencia) cmbClasificacion.getSelectedItem().getValue());
	}
	
	
	// Valida que la fecha de inicio para la competencia sea correcta
	public void onChange$datefechaInicio() {

		Date fecha = datefechaInicio.getValue();
		binder.loadAll();
	
		if (cmbTemporada.getText().equalsIgnoreCase("-- Seleccione --")) {
			
		}else{	
			LapsoDeportivo lapso = (LapsoDeportivo) cmbTemporada.getSelectedItem().getValue();
			Date fechamin = lapso.getFechaInicio();
			Date fechamax = lapso.getFechaFin();
			
			String fechaminima, fechamaxima, fechafin;
			SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
			fechaminima=formato.format(lapso.getFechaInicio());
			fechamaxima=formato.format(lapso.getFechaFin());
			
			if(fecha.before(fechamin)){
				datefechaInicio.setValue(fechamin);
				throw new WrongValueException(datefechaInicio, "Debe Seleccionar una Fecha Valida("+fechaminima+" - "+fechamaxima+")");	
			} 
			if(fecha.after(fechamax)){
				datefechaInicio.setValue(fechamax);
				throw new WrongValueException(datefechaInicio, "Debe Seleccionar una Fecha Valida("+fechaminima+" - "+fechamaxima+")");
			}
			if (datefechaFin.getText()!="") {
				fechafin=formato.format(datefechaFin.getValue());	
				
				if(fecha.after(datefechaFin.getValue())){ 				
					throw new WrongValueException(datefechaInicio, "Debe Seleccionar una Fecha Valida("+fechaminima+" - "+fechafin+")");
				}
			}
		} 	
	}

	
	// Valida que la fecha de fin para la competencia sea correcta	
	public void onChange$datefechaFin() {

		Date fecha = datefechaFin.getValue();
		binder.loadAll();
		
		if (cmbTemporada.getText().equalsIgnoreCase("-- Seleccione --")) {
			
		}else{
			
			LapsoDeportivo lapso = (LapsoDeportivo) cmbTemporada.getSelectedItem().getValue();
			Date fechamin = lapso.getFechaInicio();
			Date fechamax = lapso.getFechaFin();
			
			String fechaminima, fechamaxima, fechainicio;
			SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
			fechaminima=formato.format(lapso.getFechaInicio());
			fechamaxima=formato.format(lapso.getFechaFin());
			
			if(fecha.before(fechamin)){
				datefechaFin.setValue(fechamin);
				throw new WrongValueException(datefechaFin, "Debe Seleccionar una Fecha Valida("+fechaminima+" - "+fechamaxima+")");
			} 
			if(fecha.after(fechamax)){
				datefechaFin.setValue(fechamax);
				throw new WrongValueException(datefechaFin, "Debe Seleccionar una Fecha Valida("+fechaminima+" - "+fechamaxima+")");
			}
			if (datefechaInicio.getText()!="") {
				fechainicio=formato.format(datefechaInicio.getValue());		
				if(fecha.before(datefechaInicio.getValue())){
					throw new WrongValueException(datefechaFin, "Debe Seleccionar una Fecha Valida("+fechainicio+" - "+fechamaxima+")");
				}
			}
		} 
	}

	
	// Determina los valores tope para las fechas de inicio y de fin
	public void onChange$cmbTemporada() {

		LapsoDeportivo lapso = (LapsoDeportivo) cmbTemporada.getSelectedItem().getValue();
		Date fechamin = lapso.getFechaInicio();
		Date fechamax = lapso.getFechaFin();
		binder.loadAll();

		String fechaminima, fechamaxima;
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		fechaminima=formato.format(lapso.getFechaInicio());
		fechamaxima=formato.format(lapso.getFechaFin());
		
		if(datefechaInicio.getText()!=""){		
			Date fechainicio = datefechaInicio.getValue(); 
			
			if(fechainicio.before(fechamin)){	
				throw new WrongValueException(datefechaInicio, "Debe Seleccionar una Fecha Valida("+fechaminima+" - "+fechamaxima+")");
			} 
			if(fechainicio.after(fechamax)){
				throw new WrongValueException(datefechaInicio, "Debe Seleccionar una Fecha Valida("+fechaminima+" - "+fechamaxima+")");
			}		
		}
		if(datefechaFin.getText()!=""){
			Date fechafin = datefechaFin.getValue();
		
			if(fechafin.before(fechamin)){
				throw new WrongValueException(datefechaFin, "Debe Seleccionar una Fecha Valida("+fechaminima+" - "+fechamaxima+")");
			} 		
			if(fechafin.after(fechamax)){
				throw new WrongValueException(datefechaFin, "Debe Seleccionar una Fecha Valida("+fechaminima+" - "+fechamaxima+")");
			}
		}
	}

	
	// Determina el tipo de organizacion de la competencia
	public void onChange$cmbTipoOrganizacion() {

		DatoBasico org = (DatoBasico) cmbTipoOrganizacion.getSelectedItem().getValue();

		if (org.getCodigoDatoBasico() == 312) {  //TipoOrganizacion PorCategorias
			tabLigaPorCompetencia.setVisible(false);
			tabCategoriaPorCompetencia.setVisible(true);
			tipoOrg = "C";
//			categorias = servicioCategoria.listarActivos();
			lsbxCategorias.getItems().clear();
			lsbxCategorias.invalidate();
			cargarlsbxCategoriasInicial();
			
			lsbxCategorias.setVisible(true);
			binder.loadAll();
		} else {	
			if (comcategcarg == true){ categoriaCompetencias.removeAll(categoriaCompetencias); }
				tabLigaPorCompetencia.setVisible(true);
				tipoOrg = "L";
				tabCategoriaPorCompetencia.setVisible(false);
			}
	}

	
	// Toma el valor del spnNroFases para determinar la cantidad de fases de la competenica
	public void onChanging$spnNroFases(InputEvent event) {
		
		faseCompetencias.removeAll(faseCompetencias);
		Integer cantFases = Integer.valueOf(event.getValue());
		agregarFases(cantFases);
	}
	
	
	// Agrega fases a la cometencia segun la cantidad de fases indicada
	public void agregarFases(int cantfase) {

		for (int i = 1; i <= cantfase; i++) {
			faseCompetencia = new FaseCompetencia();
			FaseCompetenciaId faseID = new FaseCompetenciaId();
//			faseID.setNumeroFase(i);
//			faseCompetencia.setId(faseID);
			faseCompetencia.setNumeroFase(i);
			faseCompetencia.setCompetencia(competencia);
			faseCompetencia.setEquipoIngresan(2);
			faseCompetencia.setEquipoClasifican(2);
			faseCompetencias.add(faseCompetencia);
			binder.loadAll();
		}
	}

	
	// Llama al metodo que permite seleccionar ligas por Competencia
	public void onClick$btnMoverD() {
		seleccionarLigasD();
	}

	
	// Llama al metodo que permite quitar las ligas seleccionadas
	public void onClick$btnMoverI() {
		seleccionarLigasI();
	}

	
	// Llama al metodo cargar que por ahora no hace nada
	public void onClick$btnExaminar() {
		
		FileLoader fl = new FileLoader();
		byte[] archivo = fl.cargarArchivo();
		competencia.setDocumento(archivo);
	}

	
	// Metodo para cargar un archivo pdf
	public void Cargar() {
		new FileLoader().cargarArchivo();
	}

	// Mueve las ligas del lsbxLigas a lsbxligasseleccionadas
	public void seleccionarLigasD() {

		int cant = ligasAux.size();
		Date hora = new Date(0,0,0,1,0,0);


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
						categoriaLigas2 = ConvertirConjuntoALista(lig.getCategoriaLigas());

						Set conj = ConvertirListaAConjunto(categoriaLigas2);
						Iterator iter2 = conj.iterator();
						CategoriaLiga cat;

						while (iter2.hasNext()) {
							cat = (CategoriaLiga) iter2.next();
							CategoriaCompetencia cc = new CategoriaCompetencia();
							cc.setCategoria(cat.getCategoria());
							cc.setDuracionHora(hora);
							cc.setDuracionInning(1);
							cc.setCompetencia(competencia);
							categoriaLigas.add(cc);
							binder.loadAll();
						}
						binder.loadAll();
					}
				}
			} else {
				ligasAux.add(lig);
				lsbxligasCategorias.setVisible(true);

				categoriaLigas2 = ConvertirConjuntoALista(lig.getCategoriaLigas());
				Set conj = ConvertirListaAConjunto(categoriaLigas2);
				Iterator iter2 = conj.iterator();
				CategoriaLiga cat;

				while (iter2.hasNext()) {
			 		cat = (CategoriaLiga) iter2.next();
					CategoriaCompetencia cc = new CategoriaCompetencia();
					cc.setCategoria(cat.getCategoria());
					cc.setDuracionHora(hora);
					cc.setDuracionInning(1);
					cc.setCompetencia(competencia);
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

			ligasAux.remove(lig);
			
			if(ligasAux.size()<=0){lsbxligasCategorias.setVisible(false);}
			
			categoriaLigas2 = ConvertirConjuntoALista(lig.getCategoriaLigas());
			Set conj = ConvertirListaAConjunto(categoriaLigas2);
			Iterator iter = conj.iterator();
			CategoriaLiga cat;

			while (iter.hasNext()) {
				cat = (CategoriaLiga) iter.next();
				CategoriaCompetencia cc = new CategoriaCompetencia();
				Set conjligacatg = ConvertirListaAConjunto(categoriaLigas);
				Iterator iter2 = conjligacatg.iterator();

				while (iter2.hasNext()) {
					cc = (CategoriaCompetencia) iter2.next();

					if (cat.getCategoria() == cc.getCategoria()) {
						categoriaLigas.remove(cc);
					}
				}
				binder.loadAll();
			}
			binder.loadAll();
		}
		ordenarLiga(ligas);
	}

	
	// Ordena una lista de Liga por nombre
	public void ordenarLiga(List<Liga> ligLista) {

		Collections.sort(ligLista, new Comparator() {

			public int compare(Object o1, Object o2) {
				Liga liga1 = (Liga) o1;
				Liga liga2 = (Liga) o2;
				return liga1.getNombre().compareToIgnoreCase(liga2.getNombre());
			}
		});
	}

	
	// Ordena una lista de DatoBasico por nombre
	public void ordenarDatoBasico(List<DatoBasico> datoLista) {

		Collections.sort(datoLista, new Comparator() {

			public int compare(Object o1, Object o2) {
				DatoBasico dato1 = (DatoBasico) o1;
				DatoBasico dato2 = (DatoBasico) o2;
				return dato1.getNombre().compareToIgnoreCase(dato2.getNombre());
			}
		});
	}

	// Ordena una lista de ClasifiacionCompetencia por nombre
	public void ordenarClasificacion(List<ClasificacionCompetencia> clasiflista){
		
		Collections.sort(clasiflista, new Comparator() {

		public int compare(Object o1, Object o2) {  
			ClasificacionCompetencia clasif1 = (ClasificacionCompetencia) o1;  
			ClasificacionCompetencia clasif2 = (ClasificacionCompetencia) o2;
		    return clasif1.getNombre().compareToIgnoreCase(clasif2.getNombre());  
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


	// Llama al catalogo de competencias
	public void onClick$btnBuscarCompetencia() {

		clasificacionCompetencias = servicioClasificacionCompetencia.listarActivos();
		binder.loadAll();		
	
		Component catalogo = Executions.createComponents(
				"/Competencias/Vistas/FrmCatalogoCompetencia.zul", null, null);
		catalogo.setVariable("formulario", formulario, false);
		catalogo.setVariable("estado_comp", EstadoCompetencia.REGISTRADA + EstadoCompetencia.APERTURADA, false);
		catalogo.setVariable("codigo", EstadoCompetencia.REGISTRADA, false);

		formulario.addEventListener("onCatalogoCerrado", new EventListener() {
			@Override
			public void onEvent(Event arg0) throws Exception {
				
				competencia = (Competencia) formulario.getVariable("competencia", false);
				cmbTemporada.setSelectedIndex(buscarLapsoDeportivo(competencia.getLapsoDeportivo(), lapsoDeportivos));
				cmbEstado.setSelectedIndex(buscarDatoBasico(competencia.getDatoBasicoByCodigoEstado(), estados));
				cmbTipoOrganizacion.setSelectedIndex(buscarDatoBasico(competencia.getDatoBasicoByCodigoOrganizacion(),OrganizacionCompetencias));
								
				DatoBasico org = (DatoBasico) cmbTipoOrganizacion.getSelectedItem().getValue();

				if (org.getCodigoDatoBasico() == 312) { // TipoOrganizacion PorCategoria
					tabLigaPorCompetencia.setVisible(false);
					tabCategoriaPorCompetencia.setVisible(true);
					tipoOrg = "C";
//					categoriaCompetencias = ConvertirConjuntoALista(competencia.getCategoriaCompetencias());
					categoriaCompetencias  = new ArrayList<CategoriaCompetencia>();
					categoriaCompetencias = servicioCategoriaCompetencia.listarCategoriaPorCompetencia(competencia.getCodigoCompetencia());
					categorias = new ArrayList<Categoria>();
					lsbxCategorias.getItems().clear();
					lsbxCategorias.invalidate();
					cargarlsbxCategorias();
//					itemCategoria.setSelected(true);
					lsbxCategorias.setVisible(true);
					lsbxCategorias.setMultiple(false);
					lsbxCategorias.setCheckmark(false);

					categoriaLigas = new ArrayList<CategoriaCompetencia>();
					categoriaLigas = servicioCategoriaCompetencia.listarCategoriaPorCompetencia(competencia.getCodigoCompetencia());

					
				} else {
					tabLigaPorCompetencia.setVisible(true);
					tipoOrg = "L";
					tabCategoriaPorCompetencia.setVisible(false);
//					categoriaLigas = ConvertirConjuntoALista(competencia.getCategoriaCompetencias());
					categoriaLigas = new ArrayList<CategoriaCompetencia>();
					categoriaLigas = servicioCategoriaCompetencia.listarCategoriaPorCompetencia(competencia.getCodigoCompetencia());
					ligasAux.removeAll(ligasAux);
					lsbxligasCategorias.setVisible(true);
					binder.loadAll();
					
					for (Iterator i= categoriaLigas.iterator(); i.hasNext();){						
						CategoriaCompetencia id = (CategoriaCompetencia) i.next();
														
						List<CategoriaLiga> catlig =  ConvertirConjuntoALista(id.getCategoria().getCategoriaLigas());
					
						Set conjlig2 = ConvertirListaAConjunto(ligasAux);						
						binder.loadAll();	
						
						for (Iterator i2 = catlig.iterator(); i2.hasNext();){
							CategoriaLiga catlig2 = (CategoriaLiga) i2.next();
							
							Liga liga1 = catlig2.getLiga();	
							binder.loadAll();
							int cant = ligasAux.size();	
							
							if (cant == 0){
								ligasAux.add(liga1);
								binder.loadAll();							
							}else{
								Iterator iter = conjlig2.iterator();
								Liga liga2;
								boolean encont=false;
								
								while(iter.hasNext() && encont==false){
									liga2 = (Liga) iter.next();
									codigoliga = liga2.getCodigoLiga();
									binder.loadAll();
									
									if(liga1.getCodigoLiga()!=codigoliga){	
									}
									else{
										encont=true;
									}								
								}
								if (encont== false){ 	
									ligasAux.add(liga1);
								}
								binder.loadAll();
								}							
							binder.loadAll();
						}						
					}
					binder.loadAll();				
				}
                ordenarLiga(ligasAux);
                cmbTipoCompetencia.setSelectedIndex(buscarDatoBasico(competencia.getClasificacionCompetencia().getDatoBasico(),tiposCompetencias));                     
                DatoBasico db = (DatoBasico) cmbTipoCompetencia.getSelectedItem().getValue();        		
 //       		cmbClasificacion.setSelectedIndex(buscarClasificacion(competencia.getClasificacionCompetencia(),clasificacionCompetencias));                           
                faseCompetencias = servicioFaseCompetencia.listarPorCompetencia(competencia.getCodigoCompetencia());
                AperturarClausurar();
                cmbTipoOrganizacion.setDisabled(true);
                if (competencia.getDatoBasicoByCodigoEstadoCompetencia().getCodigoDatoBasico() == 288){	
                	aperturada = true; 
                }
                spnNroFases.setDisabled(true);
                btnMoverI.setDisabled(true);
                btnMoverD.setDisabled(true);
           		cmbClasificacion.setDisabled(false);
                actualizar = true;
				binder.loadAll();
				selectComboClasificacion();
			}
		});
	}
	
	
	public void selectComboClasificacion(){
		
		DatoBasico db = (DatoBasico) cmbTipoCompetencia.getSelectedItem().getValue();        		
	    clasificacionCompetencias = servicioClasificacionCompetencia.listarClasificacion(db);
	    cmbClasificacion.setText(competencia.getClasificacionCompetencia().getNombre());
		binder.loadAll();
		
	}
	
    	
	public void ordenarCategoriaPorEdad(List<CategoriaCompetencia> lista ){
		Collections.sort(lista, new Comparator(){
			public int compare(Object o1, Object o2) {
				CategoriaCompetencia catgcomp1 = (CategoriaCompetencia) o1;
				CategoriaCompetencia catgcomp2 = (CategoriaCompetencia) o2;
				if (catgcomp1.getCategoria().getEdadSuperior() > catgcomp2.getCategoria().getEdadSuperior())
					return 1;
				
				else 
					if (catgcomp1.getCategoria().getEdadSuperior() < catgcomp2.getCategoria().getEdadSuperior())
						return -1;
					else
						return 0;
				
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

	
	// Busca un DatoBasico espesicico en una lista de tipo DatoBasico
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

	
	// Busca un LapsoDeportivo espesicico en una lista de tipo LapsoDeportivo
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
	
	
	// Busca una ClasificacionComp espesicica en una lista de tipo ClasificacionComp	
	public int buscarClasificacion(ClasificacionCompetencia d, List l) {
		int j = -1;
		for (Iterator<ClasificacionCompetencia> i = l.iterator(); i.hasNext();) {
			ClasificacionCompetencia db = i.next();
			j++;
			if (db.getNombre().equals(d.getNombre())) {
				return j;
			}
		}
		return j;
	}
	
			
	// Busca una Categoria espesicica en una lista de tipo Categoria	
	public Categoria buscarCategoria(List<Categoria> lista, String cat_nombre) {

		Categoria catg = new Categoria();
		
		for (Iterator<Categoria> i = lista.iterator(); i.hasNext();) {
			catg = i.next();
			if (catg.getNombre() == cat_nombre) {
				return catg;
			}
		}
		return catg;
	}
	
	
	// Busca el codigo de una CategoriaCompetencia dependiendo de una categoria espesicica en una lista de tipo CategoriaCompetencia	
	public void AsignarIdCategComp() {

		System.out.println("entro a buscarCategomp");
		System.out.println(categoriaCompetencias.size());
		
		List<CategoriaCompetencia> categoriaCompetenciasAux2 = categoriaCompetenciasAux;
		
		categoriaCompetenciasAux.removeAll(categoriaCompetenciasAux);
		
		for (Iterator i= categoriaCompetencias.iterator(); i.hasNext();){
			
			CategoriaCompetencia catg = new CategoriaCompetencia();
			catg = (CategoriaCompetencia) i.next();
			
			for (Iterator i2= categoriaCompetenciasAux2.iterator(); i.hasNext();){
				
				CategoriaCompetencia catg2 = new CategoriaCompetencia();
				catg2 = (CategoriaCompetencia) i.next();
				
				System.out.println(catg.getCategoria().getNombre());
				System.out.println(catg2.getCategoria().getNombre());
				
				if (catg.getCategoria().getNombre() == catg2.getCategoria().getNombre() ){
					
					System.out.println("entro aqui");
					
					System.out.println(catg.getId().getCodigoCompetencia());
					System.out.println(catg.getId().getCodigoCategoria());
					
					catg2.getId().setCodigoCategoria(catg.getCategoria().getCodigoCategoria());
					catg2.getId().setCodigoCompetencia(catg.getCompetencia().getCodigoCompetencia());
					
					categoriaCompetenciasAux.add(catg2);
					
					System.out.println("hecho");
					binder.loadAll(); 
				}
			}
		}	
	}
	
	
	public void selectCategoriasactualizar(){
		
		
			
	//		CategoriaCompetenciaId categID= new CategoriaCompetenciaId();
	//		categID.setCodigoCategoria(categoria.getCodigoCategoria());
	//		categID.setCodigoCompetencia(competencia.getCodigoCompetencia());
	//		categ.setEstatus('A');
			
		//	categ.setId(categID);

				 binder.loadAll(); 
				
		categoriaCompetenciasAux = categoriaCompetencias;
		
	}
	
	
	
	
	// Selecciona las categrias a guardar para una competencia
	public void selectCategoriasguardar(){
		
		System.out.println("select categ guardar");
		
		for (Iterator i = lsbxCategorias.getSelectedItems().iterator(); i.hasNext();){
			
			CategoriaCompetencia categ = new CategoriaCompetencia();
			String categaux;
			CategoriaCompetenciaId id = new CategoriaCompetenciaId();
			
			Listitem li=(Listitem) i.next();	
			Listcell lca = (Listcell) li.getChildren().get(0);
			Listcell lc = (Listcell) li.getChildren().get(1);
			Listcell lc2 = (Listcell) li.getChildren().get(2);
			
			Label lb = (Label) lca.getFirstChild();
            Timebox tb = (Timebox) lc.getFirstChild();
            Spinner spnr = (Spinner) lc2.getFirstChild();
     
 //           System.out.println("Categ seleccionadas");
            
            categaux = lb.getValue();
            categorias = servicioCategoria.listarActivos();
            Categoria categoria = new Categoria();
            categoria  = buscarCategoria(categorias, categaux);
             
//            System.out.println("Categ Seleccionada");
             
            categ.setCategoria(categoria);
            categ.setCompetencia(competencia); 
            categ.setDuracionHora(tb.getValue());  
			categ.setDuracionInning(spnr.getValue()); 
			
           System.out.println(categ.getCategoria().getNombre());  			
           System.out.println(categ.getDuracionHora());  
           System.out.println(categ.getDuracionInning()); 
            
            categoriaCompetenciasAux.add(categ);
            binder.loadAll(); 
		}
		

	}
	
	// Llena el lisboxCategorias con las categorias pertenecientes a una competencia especifica	
	public void cargarlsbxCategorias(){
				
		for (CategoriaCompetencia ccp: categoriaCompetencias){

			Categoria cat = ccp.getCategoria();
			
			Label categ = new Label();
			categ.setValue(cat.getNombre());			
			Timebox time = new Timebox();
			time.setValue(ccp.getDuracionHora());
			Spinner spnr = new Spinner();
			spnr.setValue(ccp.getDuracionInning());
			
			Listcell categoria = new Listcell();
			categoria.appendChild(categ);
			Listcell hora = new Listcell();
			hora.appendChild(time);
			Listcell ining = new Listcell();
			ining.appendChild(spnr);
	
			itemCategoria = new Listitem();
			itemCategoria.appendChild(categoria);
			itemCategoria.appendChild(hora);
			itemCategoria.appendChild(ining);
			itemCategoria.setSelected(true);
			lsbxCategorias.appendChild(itemCategoria);			 
		}	
	
	}
	
	
	// Llena el lisboxCategorias con todas las categorias existentes		
	public void cargarlsbxCategoriasInicial(){
			
	    categorias = servicioCategoria.listarActivos();
		Date hora = new Date(0,0,0,1,0,0);

		for (Categoria categoria: categorias){
			Label categ = new Label();
			categ.setValue(categoria.getNombre());			
			Timebox time = new Timebox();
			time.setValue(hora);
			Spinner spnr = new Spinner();
			spnr.setValue(1);
			
			Listcell lbcategoria = new Listcell();
			lbcategoria.appendChild(categ);
			Listcell timehora = new Listcell();
			timehora.appendChild(time);
			Listcell ining = new Listcell();
			ining.appendChild(spnr);
	
			itemCategoria = new Listitem();
			itemCategoria.appendChild(lbcategoria);
			itemCategoria.appendChild(timehora);
			itemCategoria.appendChild(ining);
			itemCategoria.setSelected(false);
			lsbxCategorias.appendChild(itemCategoria);			
		}	
	}

		
	// Lleva a mayusculas todos los campos string del formulario
	public void llevarAMayusculas() {

		competencia.setNombre(competencia.getNombre().toUpperCase().toUpperCase());
		
		if (txtcondicionesGenerales.getValue()!=""){
			competencia.setCondicionesGenerales(competencia.getCondicionesGenerales().toUpperCase());
		}
		if (txtdesempate.getValue()!=""){
			competencia.setDesempate(competencia.getDesempate().toUpperCase());
		}
		if (txtextrainning.getValue()!=""){
			competencia.setExtrainning(competencia.getExtrainning().toUpperCase());
		}	
	}

	
	public void agregarEstadoCompetencia() {

		dato = servicioDatoBasico.buscarPorCodigo(287);
	}
	
	
	// Asigna el texto al boton de apertura o clausura
	public void AperturarClausurar(){
		
		if (competencia.getDatoBasicoByCodigoEstadoCompetencia().getCodigoDatoBasico() == EstadoCompetencia.REGISTRADA ){	
			btnApertura.setWidth("120px");
			btnApertura.setLabel("Aperturar");	
		}else if (competencia.getDatoBasicoByCodigoEstadoCompetencia().getCodigoDatoBasico() == EstadoCompetencia.APERTURADA ){
			btnApertura.setLabel("Clausurar");
			btnApertura.setWidth("120px");	
		}	
	}
	
	
	// Llama al formulario FrmEstatusCompetencia
	public void onClick$btnApertura() throws InterruptedException {
		
		if (actualizar==true){
			Component catalogo = Executions.createComponents(
					"/Competencias/Vistas/FrmEstatusCompetencia.zul", null, null);
			catalogo.setVariable("formulario", formulario, false);
			catalogo.setVariable("comp", competencia , false);		
		}
		else{
			tabRegistrosBasicos.setSelected(true);
			throw new WrongValueException(btnBuscarCompetencia, "Debe Seleccionar una Competencia");
		}			
	}
	
	
	// Aplica las restricciones necesarias al campo txtNombre
	private void aplicarConstraints() {	
		txtNombre.setConstraint(Restriccion.TEXTO_SIMPLE.asignarRestriccionExtra("no empty"));
	}
	
	
	// Valida que los datos a guardar para una competencia sean correctos
	public void validarDatosAGuardar(){	
		
		if (txtNombre.getValue().isEmpty()) {
			tabRegistrosBasicos.setSelected(true);
			throw new WrongValueException(txtNombre, "Debe Ingresar el Nombre de la Competencia");
		} 
		else if (cmbTipoCompetencia.getText().equalsIgnoreCase("-- Seleccione --")) { 
			tabRegistrosBasicos.setSelected(true);
			throw new WrongValueException(cmbTipoCompetencia, "Debe Seleccionar un Tipo de Competencia");
		} 
		else if (cmbClasificacion.getText().equalsIgnoreCase("-- Seleccione --")) {
			tabRegistrosBasicos.setSelected(true);
			throw new WrongValueException(cmbClasificacion, "Debe Seleccionar un Tipo de Clasificación");
		} 		
		else if (datefechaInicio.getText().equalsIgnoreCase("")) { // , 
			tabRegistrosBasicos.setSelected(true);
			throw new WrongValueException(datefechaInicio, "Debe Seleccionar una Fecha de Inicio");
		} 		
		else if (datefechaFin.getText().equalsIgnoreCase("")) { // , datefechaFin
			tabRegistrosBasicos.setSelected(true);
			throw new WrongValueException(datefechaFin, "Debe Seleccionar una Fecha de Fin");
		}
		else if (competencia.getFechaInicio().after(competencia.getFechaFin())){
			SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
			String fechainicio=formato.format(competencia.getFechaInicio());
			tabRegistrosBasicos.setSelected(true);
			throw new WrongValueException(datefechaFin, "Debe Seleccionar una Fecha Valida despues de la Fecha de Inicio("+fechainicio+")");			
		}		
		else if (cmbTemporada.getText().equalsIgnoreCase("-- Seleccione --")) {
			tabRegistrosBasicos.setSelected(true);
			throw new WrongValueException(cmbTemporada, "Debe Seleccionar una Temporada");
		} 
		else if (cmbEstado.getText().equalsIgnoreCase("-- Seleccione --")) {
			tabRegistrosBasicos.setSelected(true);
			throw new WrongValueException(cmbEstado, "Debe Seleccionar un Estado");
		}
		else if (cmbTipoOrganizacion.getText().equalsIgnoreCase("-- Seleccione --")) {
			tabRegistrosBasicos.setSelected(true);
			throw new WrongValueException(cmbTipoOrganizacion, "Debe Seleccionar un Tipo de Organizaciï¿½n");
		}
		else if (txtmontoInscripcion.getValue()==0) {
			tabRegistrosBasicos.setSelected(true);
			throw new WrongValueException(txtmontoInscripcion, "Debe Ingresar un Monto de Inscripciï¿½n");
		}				
		else if(categoriaCompetenciasAux.size()<=0){ 	
			if (tipoOrg == "C") {
				tabCategoriaPorCompetencia.setSelected(true);
				throw new WrongValueException(lsbxCategorias, "Debe Seleccionar Al menos una Categoria");
			} else {
				tabLigaPorCompetencia.setSelected(true);
				throw new WrongValueException(lsbxLigasSeleccionadas, "Debe Seleccionar Al menos una Liga");
			}	
		}		
	}

	
	public void onClick$btnGuardar() throws InterruptedException {
		
//		selectCategoriasguardar();
	
		agregarEstadoCompetencia();

		if (tipoOrg == "C") {
			selectCategoriasguardar();
						
		} else {
			categoriaCompetenciasAux = categoriaLigas;
		}
	
		validarDatosAGuardar();		

		llevarAMayusculas();

	//	competencia.setClasificacionCompetencia((ClasificacionCompetencia) cmbClasificacion.getSelectedItem().getValue());
		competencia.setDatoBasicoByCodigoOrganizacion((DatoBasico) cmbTipoOrganizacion.getSelectedItem().getValue());
		competencia.setLapsoDeportivo((LapsoDeportivo) cmbTemporada.getSelectedItem().getValue());
		competencia.setDatoBasicoByCodigoEstado((DatoBasico) cmbEstado.getSelectedItem().getValue());
		competencia.setDatoBasicoByCodigoEstadoCompetencia(dato);
		
		if (actualizar==true){
			
			
			if (tipoOrg == "C") {
				selectCategoriasactualizar();
							
			} else {
				categoriaCompetenciasAux = categoriaLigas;
			}
			
		
			System.out.println(competencia.getDatoBasicoByCodigoEstadoCompetencia().getCodigoDatoBasico());
			
			if(aperturada == true ){
				
				Messagebox.show("La Competencia ya ha sido Aperturada, No puede se pueden Modificar los Datos ", "Mensaje",
						Messagebox.OK, Messagebox.EXCLAMATION);
				
			}else if (aperturada == false ) {			
				servicioCompetencia.actualizar(competencia);
				servicioFaseCompetencia.actualizar(faseCompetencias, competencia);
				servicioCategoriaCompetencia.actualizar(categoriaCompetenciasAux,competencia);
				
				Messagebox.show("Datos actualizados exitosamente", "Mensaje",Messagebox.OK, Messagebox.EXCLAMATION);
				restaurar();
				binder.loadAll();
			}
		}else{
			
			List<Competencia> listcomp = servicioCompetencia.buscarCompetenciaPorNombre(competencia.getNombre(), competencia.getLapsoDeportivo());
						
			if(listcomp.size()>0){ 
				
				tabRegistrosBasicos.setSelected(true);
				throw new WrongValueException(txtNombre, "Este nombre pertenece a una competencia, por favor ingrese otro nombre");
				
			}else if (listcomp.size()<=0){
			
				
				if (tipoOrg == "C") {
					selectCategoriasguardar();
								
				} else {
					categoriaCompetenciasAux = categoriaLigas;
				}	
				
			servicioCompetencia.agregar(competencia);
			int codcomp = servicioCompetencia.obtenerCodigoCompetencia();
			servicioFaseCompetencia.agregar(faseCompetencias,codcomp);	
			servicioCategoriaCompetencia.agregar(categoriaCompetenciasAux, codcomp);
				

			Messagebox.show("Datos agregados exitosamente", "Mensaje",Messagebox.OK, Messagebox.EXCLAMATION);	
			restaurar();
			}
		}
			
		binder.loadAll();
	}

	
	public void onClick$btnEliminar() throws InterruptedException {
		
		if (actualizar==true){	
			if(competencia.getDatoBasicoByCodigoEstadoCompetencia().getCodigoDatoBasico() == EstadoCompetencia.APERTURADA){
				Messagebox.show("La Competencia ya ha sido Aperturada, No puede ser Eliminanda", "Mensaje",
						Messagebox.OK, Messagebox.EXCLAMATION);	
			}else{
				
				if (Messagebox.show("Â¿Realmente desea eliminar esta Competencia",
						"Mensaje", Messagebox.YES + Messagebox.NO, Messagebox.QUESTION) == Messagebox.YES) {
					servicioCompetencia.eliminar(competencia);
					restaurar();
					binder.loadAll();
					Messagebox.show("Datos eliminados exitosamente", "Mensaje",
							Messagebox.OK, Messagebox.EXCLAMATION);
				}
			}
		}
		else{			
			tabRegistrosBasicos.setSelected(true);
			throw new WrongValueException(btnBuscarCompetencia, "Debe Seleccionar una Competencia");
		}
	}
	

	// Establece los valores iniciales para todos los campos del formulario
	public void restaurar() {

		competencia = new Competencia();
		tipoOrg = new String();
		tipo = new TipoDato();
		dato = new DatoBasico();
		faseCompetencia = new FaseCompetencia();
		faseID = new FaseCompetenciaId();

//		System.out.println(lsbxCategorias.getSelectedItems().size());
		lsbxCategorias.getItems().clear();
		lsbxCategorias.invalidate();
		
		
		categorias.removeAll(categorias);
		// tipoCompetencias = servicioTipoCompetencia.listarActivos();
		lapsoDeportivos = servicioLapsoDeportivo.listarActivos();
		estados = servicioDatoBasico.listarEstados();
		ordenarDatoBasico(estados);
//		categorias = servicioCategoria.listarActivos();
		ligas = servicioLiga.listarActivos();
		ordenarLiga(ligas);

		cmbTipoCompetencia.setText("-- Seleccione --");
		cmbTipoOrganizacion.setText("-- Seleccione --");
		cmbTemporada.setText("-- Seleccione --");
		cmbEstado.setText("-- Seleccione --");
		cmbClasificacion.setText("-- Seleccione --");

		tabLigaPorCompetencia.setVisible(false);
		tabCategoriaPorCompetencia.setVisible(false);

//		datefechaInicio.setDisabled(true);
//		datefechaFin.setDisabled(true);

		competencia.setCantidadFase(1);
		competencia.setCantidadJugador(9);		
		
		faseCompetencias.removeAll(faseCompetencias);
		faseID.setNumeroFase(1);
//		faseCompetencia.set.set.setId(faseID);
		faseCompetencia.setNumeroFase(1);
		faseCompetencia.setEquipoIngresan(2);
		faseCompetencia.setEquipoClasifican(2);
		faseCompetencias.add(faseCompetencia);

		categoriaCompetenciasAux.removeAll(categoriaCompetenciasAux);
		categoriaCompetencias.removeAll(categoriaCompetencias);

		categoriaLigas.removeAll(categoriaLigas);
		cmbClasificacion.setDisabled(true);
		
		btnApertura.setLabel("Apertura/Clausura");
		btnApertura.setWidth("120px");

		lsbxCategorias.setVisible(true);
		lsbxligasCategorias.setVisible(false);
		spnNroFases.setDisabled(false);
        btnMoverI.setDisabled(false);
        btnMoverD.setDisabled(false);
		ligasAux.removeAll(ligasAux);
		actualizar = false;
		tabRegistrosBasicos.setSelected(true);
//		itemCategoria.setSelected(false);
		Date hora = new Date(0,0,0,1,0,0);
		
		cmbTipoOrganizacion.setDisabled(false);
//		spnHora.setValue(hora);
//		spnIning.setValue(1);
		
		binder.loadAll();

	}


	// Llama al metodo restaurar
	public void onClick$btnCancelar() {
		restaurar();
		binder.loadAll();
	}

	
	// Cierra el formulario
	public void onClick$btnSalir()  throws InterruptedException {
		
		if (Messagebox.show("ï¿½Desea salir?",
				"Mensaje", Messagebox.YES + Messagebox.NO, Messagebox.QUESTION) == Messagebox.YES) {
			 formulario.detach();
		}
	}
	

	//////// GETTER Y SETTERS ////////
	
	public Competencia getCompetencia() {
		return competencia;
	}
	
	public void setCompetencia(Competencia competencia) {
		this.competencia = competencia;
	}

	public ServicioCompetencia getServicioCompetencia() {
		return servicioCompetencia;
	}
	
	public void setServicioCompetencia(ServicioCompetencia servicioCompetencia) {
		this.servicioCompetencia = servicioCompetencia;
	}
		
	public List<LapsoDeportivo> getLapsoDeportivos() {
		return lapsoDeportivos;
	}
	
	public void setLapsoDeportivos(List<LapsoDeportivo> lapsoDeportivos) {
		this.lapsoDeportivos = lapsoDeportivos;
	}
	
	public List<DatoBasico> getEstados() {
		return estados;
	}
	
	public void setEstados(List<DatoBasico> estados) {
		this.estados = estados;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}
	
	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

	public List<Liga> getLigas() {
		return ligas;
	}
	
	public void setLigas(List<Liga> ligas) {
		this.ligas = ligas;
	}

	public List<Liga> getLigasAux() {
		return ligasAux;
	}
	
	public void setLigasAux(List<Liga> ligasAux) {
		this.ligasAux = ligasAux;
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

	public List<DatoBasico> getOrganizacionCompetencias() {
		return OrganizacionCompetencias;
	}

	public void setOrganizacionCompetencias(
			List<DatoBasico> organizacionCompetencias) {
		OrganizacionCompetencias = organizacionCompetencias;
	}

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

	public List<DatoBasico> getTiposCompetencias() {
		return tiposCompetencias;
	}

	public void setTiposCompetencias(List<DatoBasico> tiposCompetencias) {
		this.tiposCompetencias = tiposCompetencias;
	}

	public List<ClasificacionCompetencia> getClasificacionCompetencias() {
		return clasificacionCompetencias;
	}

	public void setClasificacionCompetencias(
			List<ClasificacionCompetencia> clasificacionCompetencias) {
		this.clasificacionCompetencias = clasificacionCompetencias;
	}

	public int getCodigoliga() {
		return codigoliga;
	}

	public void setCodigoliga(int codigoliga) {
		this.codigoliga = codigoliga;
	}

	public boolean isActualizar() {
		return actualizar;
	}

	public void setActualizar(boolean actualizar) {
		this.actualizar = actualizar;
	}

	public DatoBasico getDatoBasio() {
		return datoBasio;
	}

	public void setDatoBasio(DatoBasico datoBasio) {
		this.datoBasio = datoBasio;
	}

	public TipoDato getTipo() {
		return tipo;
	}
	public void setTipo(TipoDato tipo) {
		this.tipo = tipo;
	}

	public boolean isAperturada() {
		return aperturada;
	}

	public void setAperturada(boolean aperturada) {
		this.aperturada = aperturada;
	}

	public Spinner getSpnIning() {
		return spnIning;
	}
	public void setSpnIning(Spinner spnIning) {
		this.spnIning = spnIning;
	}

	public Timebox getSpnHora() {
		return spnHora;
	}
	public void setSpnHora(Timebox spnHora) {
		this.spnHora = spnHora;
	}

	public boolean isLsbxcategcargado() {
		return lsbxcategcargado;
	}
	public void setLsbxcategcargado(boolean lsbxcategcargado) {
		this.lsbxcategcargado = lsbxcategcargado;
	}

	public boolean isControlcateg() {
		return controlcateg;
	}
	public void setControlcateg(boolean controlcateg) {
		this.controlcateg = controlcateg;
	}
	
	

}
