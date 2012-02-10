package controlador.competencia;

import java.text.SimpleDateFormat;
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
import modelo.EquipoJuego;
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
import org.zkoss.zk.ui.WrongValueException;
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

	ServicioCategoria servicioCategoria;
	ServicioCategoriaCompetencia servicioCategoriaCompetencia;
	ServicioClasificacionCompetencia servicioClasificacionCompetencia;
	ServicioCompetencia servicioCompetencia;
	ServicioDatoBasico servicioDatoBasico;
	ServicioFaseCompetencia servicioFaseCompetencia;
	ServicioLapsoDeportivo servicioLapsoDeportivo;
	ServicioLiga servicioLiga, servicioLigaAux;

	// List<ClasificacionCompetencia> clasificacionCompetencias;
	List<Categoria> categorias, categoriaLigas2;
	List<CategoriaCompetencia> categoriaCompetencias = new ArrayList<CategoriaCompetencia>(),
			categoriaCompetenciasAux = new ArrayList<CategoriaCompetencia>(),
			categoriaLigas = new ArrayList<CategoriaCompetencia>();
	List<ClasificacionCompetencia> clasificacionCompetencias;
	List<DatoBasico> tiposCompetencias,estados, OrganizacionCompetencias;;
	List<FaseCompetencia> faseCompetencias = new ArrayList<FaseCompetencia>(), faseCompetenciasAux = new ArrayList<FaseCompetencia>();
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
	Tab tabRegistrosBasicos, tabLigaPorCompetencia, tabCategoriaPorCompetencia;
	Textbox txtNombre,txtcondicionesGenerales,txtdesempate,txtextrainning;

	String fase, tipoOrg;

	int ining, horas ;
	int codigoliga;
	boolean actualizar = false;
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
		categorias = servicioCategoria.listarActivos();
		
		ligas = servicioLiga.listarActivos();
		ordenarLiga(ligas);
		competencia.setCantidadFase(1);
		competencia.setCantidadJugador(9);
		txtmontoInscripcion.setText("");

		faseID.setNumeroFase(1);
		
//		faseCompetencia.setId(faseID);
		faseCompetencia.setEquipoIngresan(2);
		faseCompetencia.setEquipoClasifican(2);
		faseCompetencias.add(faseCompetencia);
		
		cmbClasificacion.setDisabled(true);

		// datefechaInicio.setDisabled(true);
		// datefechaFin.setDisabled(true);
		codigoliga = 0;
	}

	// Habilita y desabilita los tabs de LigaPorCategoria y
	// CategoriaPorCompetencia
	public void onChange$cmbTipoOrganizacion() {

		DatoBasico org = (DatoBasico) cmbTipoOrganizacion.getSelectedItem().getValue();

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

	}
	
	
	// Agrega fases de la cometencia segun la cantidad de fases indicada
	public void agregarFases(int cantfase) {

		for (int i = 1; i <= cantfase; i++) {

			faseCompetencia = new FaseCompetencia();
			FaseCompetenciaId faseID = new FaseCompetenciaId();
			faseID.setNumeroFase(i);

//			faseCompetencia.setId(faseID);
			faseCompetencia.setCompetencia(competencia);
			faseCompetencia.setEquipoIngresan(2);
			faseCompetencia.setEquipoClasifican(2);
			faseCompetencias.add(faseCompetencia);
			binder.loadAll();

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


	public void onChange$cmbTipoCompetencia() {

		DatoBasico db = (DatoBasico) cmbTipoCompetencia.getSelectedItem().getValue();

		cmbClasificacion.setDisabled(false);
		clasificacionCompetencias = servicioClasificacionCompetencia.listarClasificacion(db);
		ordenarClasificacion(clasificacionCompetencias);

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
						categoriaLigas2 = ConvertirConjuntoALista(lig.getCategoriaLigas());

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
				lsbxligasCategorias.setVisible(true);

				categoriaLigas2 = ConvertirConjuntoALista(lig.getCategoriaLigas());
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
			
			if(ligasAux.size()<=0){lsbxligasCategorias.setVisible(false);}
			
			categoriaLigas2 = ConvertirConjuntoALista(lig.getCategoriaLigas());
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
	public void ordenarDatoBasico(List<DatoBasico> datoLista) {

		Collections.sort(datoLista, new Comparator() {

			public int compare(Object o1, Object o2) {
				DatoBasico dato1 = (DatoBasico) o1;
				DatoBasico dato2 = (DatoBasico) o2;
				return dato1.getNombre().compareToIgnoreCase(dato2.getNombre());
			}
		});
	}

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
	
	
	// Ordena una lista de LapsoDeportivo por nombre
	public void ordenarCategorias(List<CategoriaCompetencia> categLista) {

		for (Iterator i= categLista.iterator(); i.hasNext();){
			
		CategoriaCompetencia categ,categaux;
		
		categ = (CategoriaCompetencia) i.next();
	
		}
			
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

				if (org.getCodigoDatoBasico() == 312) {
					tabLigaPorCompetencia.setVisible(false);
					tabCategoriaPorCompetencia.setVisible(true);
					tipoOrg = "C";
//					categoriaCompetencias = ConvertirConjuntoALista(competencia.getCategoriaCompetencias());
					categoriaCompetencias = servicioCategoriaCompetencia.listarCategoriaPorCompetencia(competencia.getCodigoCompetencia());

				} else {
					tabLigaPorCompetencia.setVisible(true);
					tipoOrg = "L";
					tabCategoriaPorCompetencia.setVisible(false);
//					categoriaLigas = ConvertirConjuntoALista(competencia.getCategoriaCompetencias());
					categoriaLigas = servicioCategoriaCompetencia.listarCategoriaPorCompetencia(competencia.getCodigoCompetencia());
					ligasAux.removeAll(ligasAux);
					binder.loadAll();
					
					for (Iterator i= categoriaLigas.iterator(); i.hasNext();){						
						CategoriaCompetencia id = (CategoriaCompetencia) i.next();
														
						List<Liga> lig =  ConvertirConjuntoALista(id.getCategoria().getCategoriaLigas());
					
						Set conjlig2 = ConvertirListaAConjunto(ligasAux);						
						binder.loadAll();	
						
						for (Iterator i2 = lig.iterator(); i2.hasNext();){
							Liga liga1 = (Liga) i2.next();
						
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

        		cmbClasificacion.setSelectedIndex(buscarClasificacion(competencia.getClasificacionCompetencia(),clasificacionCompetencias));               
        		cmbClasificacion.setDisabled(false);
                clasificacionCompetencias = servicioClasificacionCompetencia.listarClasificacion(db);
//                faseCompetencias = ConvertirConjuntoALista(competencia.getFaseCompetencias());
                faseCompetencias = servicioFaseCompetencia.listarPorCompetencia(competencia.getCodigoCompetencia());
//                faseCompetenciasAux = ConvertirConjuntoALista(competencia.getFaseCompetencias());
                spnNroFases.setDisabled(true);
                btnMoverI.setDisabled(true);
                btnMoverD.setDisabled(true);
        		actualizar = true;
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
	
	

	public int buscarTipoCompetencia(DatoBasico d, List l) {
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
			throw new WrongValueException(cmbClasificacion, "Debe Seleccionar un Tipo de Clasificaci�n");
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
			throw new WrongValueException(cmbTipoOrganizacion, "Debe Seleccionar un Tipo de Organizaci�n");
		}
		else if (txtmontoInscripcion.getValue()==0) {
			tabRegistrosBasicos.setSelected(true);
			throw new WrongValueException(txtmontoInscripcion, "Debe Ingresar un Monto de Inscripci�n");
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
	
		agregarEstadoCompetencia();
		
		if (tipoOrg == "C") {
			seleccionarCategoriasAGuardar(lsbxCategorias);
		} else {
			categoriaCompetenciasAux = categoriaLigas;
		}
	
		validarDatosAGuardar();		

		llevarAMayusculas();

		competencia.setClasificacionCompetencia((ClasificacionCompetencia) cmbClasificacion.getSelectedItem().getValue());
		competencia.setDatoBasicoByCodigoOrganizacion((DatoBasico) cmbTipoOrganizacion.getSelectedItem().getValue());
		competencia.setLapsoDeportivo((LapsoDeportivo) cmbTemporada.getSelectedItem().getValue());
		competencia.setDatoBasicoByCodigoEstado((DatoBasico) cmbEstado.getSelectedItem().getValue());
		competencia.setDatoBasicoByCodigoEstadoCompetencia(dato);
		
		if(actualizar==true){
			servicioCompetencia.actualizar(competencia);
			servicioFaseCompetencia.actualizar(faseCompetencias, competencia);
			servicioCategoriaCompetencia.actualizar(categoriaCompetenciasAux,competencia);
			Messagebox.show("Datos actualizados exitosamente", "Mensaje",Messagebox.OK, Messagebox.EXCLAMATION);
			binder.loadAll();
		}else{
			servicioCompetencia.agregar(competencia);
			int codcomp = servicioCompetencia.obtenerCodigoCompetencia();
			servicioCategoriaCompetencia.agregar(categoriaCompetenciasAux, codcomp);
			servicioFaseCompetencia.agregar(faseCompetencias,codcomp);		

			Messagebox.show("Datos agregados exitosamente", "Mensaje",Messagebox.OK, Messagebox.EXCLAMATION);		
		}
			
		restaurar();	
		binder.loadAll();
	}

	
	public void onClick$btnEliminar() throws InterruptedException {
		
		
		if (actualizar==true){

			if (Messagebox.show("¿Realmente desea eliminar esta Competencia",
					"Mensaje", Messagebox.YES + Messagebox.NO, Messagebox.QUESTION) == Messagebox.YES) {
				servicioCompetencia.eliminar(competencia);
				restaurar();
				binder.loadAll();
				Messagebox.show("Datos eliminados exitosamente", "Mensaje",
					Messagebox.OK, Messagebox.EXCLAMATION);
			}
		}
		else{
			Messagebox.show("Seleccione una competencia", "Mensaje",
					Messagebox.OK, Messagebox.EXCLAMATION);
		}
	}

	public void restaurar() {

		competencia = new Competencia();
		tipoOrg = new String();
		tipo = new TipoDato();
		dato = new DatoBasico();
		faseCompetencia = new FaseCompetencia();
		faseID = new FaseCompetenciaId();

		// tipoCompetencias = servicioTipoCompetencia.listarActivos();
		lapsoDeportivos = servicioLapsoDeportivo.listarActivos();
		estados = servicioDatoBasico.listarEstados();
		ordenarDatoBasico(estados);
		categorias = servicioCategoria.listarActivos();
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
//		faseCompetencia.setId(faseID);
		faseCompetencia.setEquipoIngresan(2);
		faseCompetencia.setEquipoClasifican(2);
		faseCompetencias.add(faseCompetencia);

		categoriaCompetenciasAux.removeAll(categoriaCompetenciasAux);
		categoriaCompetencias.removeAll(categoriaCompetencias);

		categoriaLigas.removeAll(categoriaLigas);
		cmbClasificacion.setDisabled(true);

		lsbxligasCategorias.setVisible(false);
		spnNroFases.setDisabled(false);
        btnMoverI.setDisabled(false);
        btnMoverD.setDisabled(false);
		ligasAux.removeAll(ligasAux);
		actualizar = false;
		tabRegistrosBasicos.setSelected(true);
		binder.loadAll();

	}

	public void onClick$btnCancelar() {
		restaurar();
		binder.loadAll();
	}

	public void onClick$btnSalir()  throws InterruptedException {
		if (Messagebox.show("�Desea salir?",
				"Mensaje", Messagebox.YES + Messagebox.NO, Messagebox.QUESTION) == Messagebox.YES) {
			 formulario.detach();
		}

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

}
