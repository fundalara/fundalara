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

import org.zkoss.zk.scripting.Namespace;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Image;
import org.zkoss.zul.Panel;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Spinner;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Button; 
import org.zkoss.zk.ui.*;
import org.zkoss.zul.Listitem;//
import org.zkoss.zul.Textbox;   

import servicio.implementacion.ServicioTipoCompetencia;
import servicio.implementacion.ServicioLapsoDeportivo;
import servicio.implementacion.ServicioDatoBasico;
import servicio.implementacion.ServicioModalidadCompetencia;
import servicio.implementacion.ServicioCategoria;
import servicio.implementacion.ServicioLiga;

import org.zkoss.zk.ui.util.GenericForwardComposer;

public class CntrlFrmCrearCompetencia extends GenericForwardComposer {
	
	AnnotateDataBinder binder;
	
	ServicioTipoCompetencia servicioTipoCompetencia;
	ServicioLapsoDeportivo servicioLapsoDeportivo;
	ServicioDatoBasico servicioDatoBasico;
	ServicioModalidadCompetencia servicioModalidadCompetencia;
	ServicioCategoria servicioCategoria;
	ServicioLiga servicioLiga,servicioLigaAux;
	
	List<TipoCompetencia> tipoCompetencias;
	List<LapsoDeportivo> lapsoDeportivos;
	List<DatoBasico> datoBasicos;
	List<ModalidadCompetencia> modalidadCompetencias;
	List<Categoria> categorias,categoriaLigas2,categoriaLigas = new ArrayList<Categoria>();
	List<Liga> ligas,ligasAux = new ArrayList<Liga>();
	List<String> fases = new ArrayList<String>();
		
	TipoCompetencia tipoCompetencia;
	LapsoDeportivo lapsoDeportivo;
	DatoBasico datoBasio;
	ModalidadCompetencia modalidadCompetencia;
	Categoria categoria;
	Liga liga,ligaAux;
	FaseCompetencia faseCompetencia;
	
	Combobox cmbTipoCompetencia,cmbModalidadCompetencia,cmbTipoOrganizacion;
	Panel pnlCategorias;
	Tab tabLigaPorCompetencia,tabCategoriaPorCompetencia;
	Spinner spnNroFases;
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
	    tipoCompetencias = servicioTipoCompetencia.listarActivos();	
	    lapsoDeportivos = servicioLapsoDeportivo.listarActivos();
//	    datoBasicos = servicioDatoBasico.listarEstados(); 
//	    ordenarEstado(datoBasicos);
	    categorias = servicioCategoria.listarActivos();
	    ligas = servicioLiga.listarActivos();
	    ordenarLiga(ligas);
	    fase="Fase 1";
		fases.add(fase);
		fases.add(fase);
//		fases.add("Fase 2");

	    
/*	    FaseCompetenciaId id = null;
	    id.setNumeroFase(1);
		faseCompetencia.setId(id);
		fases.add(faseCompetencia);
*/	
	
	}

	
// Habilita y desabilita los tabs de LigaPorCategoria y CategoriaPorCompetencia
	public void onChange$cmbTipoOrganizacion(){
				
		if (cmbTipoOrganizacion.getText().equals("Categoria")) {
			tabLigaPorCompetencia.setVisible(false);
			tabCategoriaPorCompetencia.setVisible(true);
			
		} else {
			tabLigaPorCompetencia.setVisible(true);
			tabCategoriaPorCompetencia.setVisible(false);			
		}		
	}	
	
	
// Al seleccionar un tipo de competencia carga cmbModalidadCompetencia con las modalidades de competencia dependiendo del tipo de competencia seleccionado 
	public void onChange$cmbTipoCompetencia(){


		TipoCompetencia  tipocomp =  (TipoCompetencia) cmbTipoCompetencia.getSelectedItem().getValue();

		modalidadCompetencias = servicioModalidadCompetencia.listarPorTipoCompetencia(tipocomp);
		binder.loadAll(); 
	}


	public void onChanging$spnNroFases(){
		
		
		
			 		
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
		Set set = lsbxLigas.getSelectedItems();
		for (Object obj : new ArrayList(set)) {
			Liga lig = (Liga) ((Listitem) obj).getValue();            
			
			ligasAux.add(lig);

			categoriaLigas2 = ConvertirConjuntoALista(lig.getCategorias());
			
			Set conj = ConvertirListaAConjunto(categoriaLigas2);
			
			Iterator iter = conj.iterator();
			
			Categoria cat;

			while (iter.hasNext()){  cat = (Categoria) iter.next();  categoriaLigas.add(cat); }
			
			
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
	
	
	
	
	
	
}
