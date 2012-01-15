package controlador.competencia;

import java.util.ArrayList;//
import java.util.Collection;
import java.util.Collections;//
import java.util.Comparator;//
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set; //

import javax.swing.JFileChooser;

import jxl.read.biff.File;

import comun.FileLoader;

import modelo.Competencia;
import modelo.FaseCompetenciaId;
import modelo.TipoCompetencia;
import modelo.LapsoDeportivo;
import modelo.DatoBasico;
import modelo.ModalidadCompetencia;
import modelo.Categoria;
import modelo.Liga; 
import modelo.FaseCompetencia;
import modelo.TipoDato;

import org.zkoss.zk.scripting.Namespace;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.InputEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Combobox;
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

import servicio.implementacion.ServicioCompetencia;
import servicio.implementacion.ServicioTipoCompetencia;
import servicio.implementacion.ServicioLapsoDeportivo;
import servicio.implementacion.ServicioDatoBasico;
import servicio.implementacion.ServicioModalidadCompetencia;
import servicio.implementacion.ServicioCategoria;
import servicio.implementacion.ServicioLiga;

import org.zkoss.zk.ui.util.GenericForwardComposer;

public class CntrlFrmCrearCompetencia extends GenericForwardComposer {
	
	AnnotateDataBinder binder;
	Component formulario;
	
	Competencia competencia;
	
	ServicioCompetencia servicioCompetencia;
	ServicioTipoCompetencia servicioTipoCompetencia;
	ServicioLapsoDeportivo servicioLapsoDeportivo;
	ServicioDatoBasico servicioDatoBasico;
	ServicioModalidadCompetencia servicioModalidadCompetencia;
	ServicioCategoria servicioCategoria;
	ServicioLiga servicioLiga,servicioLigaAux;
	
	List<TipoCompetencia> tipoCompetencias;
	List<LapsoDeportivo> lapsoDeportivos;
	List<DatoBasico> datoBasicos,OrganizacionCompetencias;
	List<ModalidadCompetencia> modalidadCompetencias;
	List<Categoria> categorias,categoriaLigas2,categoriaLigas = new ArrayList<Categoria>();
	List<Liga> ligas,ligasAux = new ArrayList<Liga>();
	List<String> fases = new ArrayList<String>();
		
	TipoCompetencia tipoCompetencia;
	LapsoDeportivo lapsoDeportivo;
	DatoBasico datoBasio,organizacionCompetencia,estado;
	ModalidadCompetencia modalidadCompetencia;
	Categoria categoria;
	Liga liga,ligaAux;
	FaseCompetencia faseCompetencia;
	
	Combobox cmbTipoCompetencia,cmbModalidadCompetencia,cmbNroFases,cmbTipoOrganizacion,cmbTemporada,cmbEstado;
	Panel pnlCategorias;
	Tab tabLigaPorCompetencia,tabCategoriaPorCompetencia;
	Spinner spnNroFases,spnNroJugadores;
	Listbox lsbxFases,lsbxCategorias,lsbxLigas,lsbxLigasSeleccionadas,lsbxligasCategorias;
	Button btnMoverD,btnMoverI,btnExaminar;
	Textbox txtNombre;
	
	String fase;
	Listitem itemFases;
	
	
	private Image img;

	private EventListener arg;

	
	public void doAfterCompose (Component c) throws Exception{		
		super.doAfterCompose(c);		
	    c.setVariable("cntrl",this,true);
		formulario = c;	
		competencia = new Competencia();
	    tipoCompetencias = servicioTipoCompetencia.listarActivos();	
	    lapsoDeportivos = servicioLapsoDeportivo.listarActivos();
	    datoBasicos = servicioDatoBasico.listarEstados();
	    ordenarEstado(datoBasicos);
	    OrganizacionCompetencias = servicioDatoBasico.listarOrganizacionCompetencia();
	    categorias = servicioCategoria.listarActivos();
	    ligas = servicioLiga.listarActivos();
	    ordenarLiga(ligas);
	    fase = new String();
	    fase="Fase 1";
	    setFase(fase);
		fases.add(fase);


	    
/*	    FaseCompetenciaId id = null;
	    id.setNumeroFase(1);
		faseCompetencia.setId(id);
		fases.add(faseCompetencia);
*/	
	
	}

	
// Habilita y desabilita los tabs de LigaPorCategoria y CategoriaPorCompetencia
	public void onChange$cmbTipoOrganizacion(){
				
		if (cmbTipoOrganizacion.getText().equals("POR CATEGORIAS")) {
			tabLigaPorCompetencia.setVisible(false);
			tabCategoriaPorCompetencia.setVisible(true);
			
		} else {
			tabLigaPorCompetencia.setVisible(true);
			tabCategoriaPorCompetencia.setVisible(false);			
		}		
	}	
	
	
// Al seleccionar un tipo de competencia carga cmbModalidadCompetencia con las modalidades de competencia dependiendo del tipo de competencia seleccionado 
	public void onChange$cmbNroFases(){

		fases.removeAll(fases);
		
		Integer nroFase = Integer.valueOf(cmbNroFases.getText());

		for(int i =1;i<=nroFase;i++){
			
//		fase = new String();	
//		fase = "2";	
			
		String numfase = String.valueOf(i);
		String fase = "Fase ";
		fase += numfase;
			
		fases.add(fase);
		binder.loadAll();
			
		}
	}


	public void onChanging$spnNroFases(InputEvent event){
		
		fases.removeAll(fases);
		
		Integer cantFases = Integer.valueOf(event.getValue());
		
		for(int i =1;i<=cantFases;i++){
				    
		fase = new String();	
			
		String numfase = String.valueOf(i);
		fase = "Fase ";
//		fase += numfase;
//		System.out.println(fase);

		setFase(fase);
		String fase2 = getFase();
		
			fases.add(fase2);
			binder.loadAll();
			
		}
			
	}
		
	
	public void onChange$cmbTipoCompetencia(){


		TipoCompetencia  tipocomp =  (TipoCompetencia) cmbTipoCompetencia.getSelectedItem().getValue();

		modalidadCompetencias = servicioModalidadCompetencia.listarModalidad(tipocomp);
		binder.loadAll(); 
	}	
	
	
	
	
	public void onClick$btnMoverD(){

		seleccionarLigasD();
	}
	
	public void onClick$btnMoverI(){

		seleccionarLigasI();	
	}

		
	public void onClick$btnExaminar(){

		Cargar();
	}
	
	public void Cargar () {
		
     	JFileChooser fc = new JFileChooser();
		int respuesta = fc.showOpenDialog(fc);
		if (respuesta == JFileChooser.APPROVE_OPTION)
        {
            //Crear un objeto File con el archive elegido
            java.io.File archivoElegido = fc.getSelectedFile();
            //Mostrar el nombre del archvivo en un campo de texto
          //  txtNombre.setText(archivoElegido.getName());
        }

		//new FileLoader().cargarImagen(img);

	}
	
	
	
// Mueve las ligas del lsbxLigas a lsbxligasseleccionadas 	
	public void seleccionarLigasD() {
		
		int cant = ligasAux.size();
		

		
		
		
		Set set = lsbxLigas.getSelectedItems();
		for (Object obj : new ArrayList(set)) {
			Liga lig = (Liga) ((Listitem) obj).getValue();            
			
		
			if(cant>0) {   Set conjligasAux = ConvertirListaAConjunto(ligasAux);
			   Iterator iter = conjligasAux.iterator();
			   Liga ligaAux;

			   while (iter.hasNext()){  ligaAux = (Liga) iter.next();
			
			
			if(lig!=ligaAux){
				ligasAux.add(lig); 

				categoriaLigas2 = ConvertirConjuntoALista(lig.getCategorias() );
				
				Set conj = ConvertirListaAConjunto(categoriaLigas2);
				
				Iterator iter2 = conj.iterator();
				
				Categoria cat;

				while (iter2.hasNext()){  cat = (Categoria) iter2.next();  categoriaLigas.add(cat); }				
				
				
				
				binder.loadAll();
			}
			

			}
			}else{
				
				ligasAux.add(lig);

				categoriaLigas2 = ConvertirConjuntoALista(lig.getCategorias() );
				
				Set conj = ConvertirListaAConjunto(categoriaLigas2);
				
				Iterator iter2 = conj.iterator();
				
				Categoria cat;

				while (iter2.hasNext()){  cat = (Categoria) iter2.next();  categoriaLigas.add(cat); }				

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
			
		//	ligas.add(lig);
			ligasAux.remove(lig);

			categoriaLigas2 = ConvertirConjuntoALista(lig.getCategorias());
			
			Set conj = ConvertirListaAConjunto(categoriaLigas2);
			
			Iterator iter = conj.iterator();
			
			Categoria cat;

			while (iter.hasNext()){  cat = (Categoria) iter.next();  categoriaLigas.remove(cat);}
			
						
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
	        return estado1.getNombre().compareToIgnoreCase(estado2.getNombre());  
	    }
	    });  
	}  

	
	public void mostrarLigasCategorias(Liga ligaCat ){
		
		categoriaLigas =  ConvertirConjuntoALista(ligaCat.getCategorias());
 
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
	
	
	public void onClick$btnGuardar() throws InterruptedException {
		
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
		
			
		competencia.setModalidadCompetencia((ModalidadCompetencia)  cmbModalidadCompetencia.getSelectedItem().getValue());
		competencia.setDatoBasicoByCodigoOrganizacion((DatoBasico) cmbTipoOrganizacion.getSelectedItem().getValue());
		competencia.setLapsoDeportivo((LapsoDeportivo) cmbTemporada.getSelectedItem().getValue());
		competencia.setDatoBasicoByCodigoEstado((DatoBasico) cmbEstado.getSelectedItem().getValue());
		competencia.setDatoBasicoByCodigoEstadoCompetencia(dato);	
		
		servicioCompetencia.agregar(competencia);
		Messagebox.show("Datos agregados exitosamente", "Mensaje",Messagebox.OK, Messagebox.EXCLAMATION);
		restaurar();
		binder.loadAll();		
	}
	
	public void onClick$btnEliminar() throws InterruptedException{
		if (Messagebox.show("¿Realmente desea eliminar esta Competencia", "Mensaje",
				Messagebox.YES + Messagebox.NO, Messagebox.QUESTION) == Messagebox.YES) {
			servicioCompetencia.eliminar(competencia);
            restaurar();
            binder.loadAll();
			Messagebox.show("Datos eliminados exitosamente", "Mensaje",	Messagebox.OK, Messagebox.EXCLAMATION);
		}

	}
	
	public void restaurar(){
		competencia = new Competencia(); 
	    tipoCompetencias = servicioTipoCompetencia.listarActivos();	
	    lapsoDeportivos = servicioLapsoDeportivo.listarActivos();
	    datoBasicos = servicioDatoBasico.listarEstados(); 
	    ordenarEstado(datoBasicos);
	    categorias = servicioCategoria.listarActivos();
	    ligas = servicioLiga.listarActivos();
	    ordenarLiga(ligas);
	    
		cmbTipoCompetencia.setText("-- Seleccione --");
		cmbModalidadCompetencia.setText("-- Seleccione --");
		cmbTipoOrganizacion.setText("-- Seleccione --");
		cmbTemporada.setText("-- Seleccione --");
		cmbEstado.setText("-- Seleccione --");
		
		
	}
	
	
	public void onClick$btnCancelar() {
	    restaurar();
	    binder.loadAll();
	}
	
	
	public void onClick$btnSalir() {
		formulario.detach();

	}	
	
		
// GET y SET para Competencia	
	public Competencia getCompetencia() {
		return competencia;
	}


	public void setCompetencia(Competencia competencia) {
		this.competencia = competencia;
	}
//////

	public ServicioCompetencia getServicioCompetencia() {
		return servicioCompetencia;
	}

// GET y SET para servicioCompetencia
	public void setServicioCompetencia(ServicioCompetencia servicioCompetencia) {
		this.servicioCompetencia = servicioCompetencia;
	}
//////

// GET y SET para Tipo de Competencia	
	public List<TipoCompetencia> getTipoCompetencias() {
		return tipoCompetencias;
	}

	public void setTipoCompetencias(List<TipoCompetencia> tipoCompetencias) {
		this.tipoCompetencias = tipoCompetencias;
	}
//////
	
// GET y SET para Lapso Deportivo	 
	public List<LapsoDeportivo> getLapsoDeportivos() {
		return lapsoDeportivos;
	}

	public void setLapsoDeportivos(List<LapsoDeportivo> lapsoDeportivos) {
		this.lapsoDeportivos = lapsoDeportivos;
	}
//////	
	
// GET y SET para Dato Basico	
	public List<DatoBasico> getDatoBasicos() {
		return datoBasicos;
	}

	public void setDatoBasicos(List<DatoBasico> datoBasicos) {
		this.datoBasicos = datoBasicos;
	}
//////	

// GET y SET para Modalidad Competencia
	public List<ModalidadCompetencia> getModalidadCompetencias() {
		return modalidadCompetencias;
	}

	public void setModalidadCompetencias(
			List<ModalidadCompetencia> modalidadCompetencias) {
		this.modalidadCompetencias = modalidadCompetencias;
	}
//////	

// GET y SET para Categoria
	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}
//////	

// GET y SET para Liga
	public List<Liga> getLigas() {
		return ligas;
	}

	public void setLigas(List<Liga> ligas) {
		this.ligas = ligas;
	}
//////	

// GET y SET para LigaAux
	public List<Liga> getLigasAux() {
		return ligasAux;
	}

	public void setLigasAux(List<Liga> ligasAux) {
		this.ligasAux = ligasAux;
	}
//////	


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


	public List<Categoria> getCategoriaLigas() {
		return categoriaLigas;
	}


	public void setCategoriaLigas(List<Categoria> categoriaLigas) {
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
//////	
		
}
