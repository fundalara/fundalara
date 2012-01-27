package controlador.competencia;

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

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
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
import servicio.implementacion.ServicioEstadio;
import servicio.implementacion.ServicioFaseCompetencia;



import comun.EstadoCompetencia;

/**
 * Controlador para el archivo 'FrmCalendario.zul'
 * 
 * @author G�mez Niurka, Villanueva Alix
 * @version 1000.8
 */

public class CntrlFrmCalendario extends GenericForwardComposer {
	
	
	Component formulario; 
	AnnotateDataBinder binder;	
	
	Competencia competencia;
	Estadio estadio;
	Juego juego;
	EquipoCompetencia equipoCompetencia1;
	EquipoCompetencia equipoCompetencia2;
	
	ServicioFaseCompetencia servicioFaseCompetencia;
	ServicioEstadio servicioEstadio;
	ServicioDatoBasico servicioDatoBasico;
	ServicioEquipoCompetencia servicioEquipoCompetencia;
	
	List<Categoria> categorias;
	List<FaseCompetencia> fases;
	List<Estadio> estadios;
	List<Juego> juegos;
	List<EquipoCompetencia> equiposCompetencias;
	
	
	Set<EquipoCompetencia> equipoCompetencia;
	
	Textbox txtHomeClub;
	Textbox txtVisitante;
	Timebox tmbxHoraInicio;
	Datebox dtbxFecha;
	Spinner spnCantInning;
	
	Combobox cmbCategoria;
	Combobox cmbFases;
	Combobox cmbEstadios;
	
	Listbox lsbxEnfrentamientos;
		
	/**
	 * Metodo que se ejecuta una vez es renderizado el componente. Sobreescrito
	 * de la clase GenericFordwardComposer
	 * @param c Component
	 */
	@Override
	public void doAfterCompose(Component c) throws Exception {	
		super.doAfterCompose(c); 
		c.setVariable("cntrl", this, true);
		formulario = c; 
		restaurar();
		
	}
	
	public void restaurar(){
		competencia = new Competencia();
		equipoCompetencia1 = new EquipoCompetencia();
		equipoCompetencia2 = new EquipoCompetencia();
		juego = new Juego();
		juegos = new ArrayList<Juego>();
		//cmbCategoria.setText("-- Seleccione --");
		cmbFases.setText("-- Seleccione --");
		cmbEstadios.setText("-- Seleccione --");
		
	}
	
	//LLAMA AL CATALOGO DE COMPETENCIAS
	public void onClick$btnBuscarCompetencia(){
		
		Component catalogo = Executions.createComponents("/Competencias/Vistas/FrmCatalogoCompetencia.zul",null,null);
        catalogo.setVariable("formulario",formulario, false);
        catalogo.setVariable("estatus",EstadoCompetencia.REGISTRADA,false);
        
        formulario.addEventListener("onCatalogoCerrado", new EventListener() {
			@Override
			public void onEvent(Event arg0) throws Exception {
				  competencia =  (Competencia) formulario.getVariable("competencia",false);	
				  categorias = ConvertirConjuntoALista(competencia.getCategoriaCompetencias());
				  estadios = servicioEstadio.listarActivos();
				  //estadios = servicioEstadio.listarEstadiosCompetencia(competencia);
				 //fases = servicioFaseCompetencia.listarFaseCompetencia(competencia);
				 
				  binder.loadAll();
			}
		});        
                
	}
	
	public void onChange$cmbCategoria() throws InterruptedException{
		
		
		if (cmbCategoria.getText()!="--Seleccione--"){
		    Comboitem cmbItem = cmbCategoria.getSelectedItem();
		    System.out.println(cmbItem.getLabel());
		    CategoriaCompetencia cat = (CategoriaCompetencia) cmbItem.getValue();
		    System.out.println(cat.getCategoria().getCodigoCategoria());
		    
		    Integer codCat = cat.getId().getCodigoCategoria();

		    		
			//equiposCompetencia = servicioEquipoCompetencia.listarEquipoPorCategoria(codCat);	
			binder.loadAll();
		}
		
		
		
	} 
	
	
	//LLAMA AL CATALOGO DE EQUIPOS
	public void onClick$btnBuscarEquipo() {
		//se crea el catalogo y se llama
		Component catalogo = Executions.createComponents("/Competencias/Vistas/FrmCatalogoEquipoCompetencia.zul", null, null);
		//asigna una referencia del formulario al catalogo.
		catalogo.setVariable("competencia",competencia, false);
		catalogo.setVariable("formulario",formulario, false);
	    catalogo.setVariable("id",1, false);	
	    catalogo.setVariable("equipoCompetencia",equipoCompetencia2,false);
		formulario.addEventListener("onCatalogoCerrado1", new EventListener() {		
			//Este metodo se llama cuando se envia la se�al desde el catalogo
			@Override
			public void onEvent(Event arg0) throws Exception {
				//SE OBTIENE LOS EQUIPOS
				equipoCompetencia1 = (EquipoCompetencia) formulario.getVariable("equipoCompetencia",false);
				binder.loadAll();				
			}
		});
	}
	
	//LLAMA AL CATALOGO DE EQUIPOS
	public void onClick$btnBuscarEquipo2() {
		//se crea el catalogo y se llama
		Component catalogo = Executions.createComponents("/Competencias/Vistas/FrmCatalogoEquipoCompetencia.zul", null, null);
		//asigna una referencia del formulario al catalogo.
		catalogo.setVariable("competencia",competencia, false);
		catalogo.setVariable("formulario",formulario, false);
		catalogo.setVariable("equipoCompetencia",equipoCompetencia1,false);
		catalogo.setVariable("id",2, false);	
		formulario.addEventListener("onCatalogoCerrado2", new EventListener() {		
			//Este metodo se llama cuando se envia la se�al desde el catalogo
			@Override
			public void onEvent(Event arg0) throws Exception {
				//SE OBTIENE LOS EQUIPOS
				equipoCompetencia2 = (EquipoCompetencia) formulario.getVariable("equipoCompetencia",false);
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
		
	// Agregado Convierte una lista a un conjunto...
//		public Set ConvertirListaAConjunto(List lista) {
//			Set c = new HashSet();
//			for (Iterator i = lista.iterator(); i.hasNext();) {
//				c.add(i.next());
//			}
//			return c;
//		}
		
		//BOTONES AGREGAR Y QUITAR...
		public void onClick$btnAgregar(){
			EquipoJuego ej= new EquipoJuego();
			juegos.add(juego);
			binder.loadAll();
			
		}
		
		//BOTONES GUARDAR, CANCELAR Y SALIR...
		
		public void onClick$btnCancelar(){
			restaurar();
			binder.loadAll();
		}
		
		public void onClick$btnGuardar() throws InterruptedException{
			Messagebox.show("Datos almacenados exitosamente", "Mensaje",Messagebox.OK, Messagebox.EXCLAMATION);	
			juego = new Juego();
			juegos = new ArrayList<Juego>();
		    estadios = new ArrayList<Estadio>();
		    equipoCompetencia1 = new EquipoCompetencia();
		    equipoCompetencia2 = new EquipoCompetencia();
		    cmbEstadios.setText("--Seleccione--");
		    cmbFases.setText("--Seleccione--");
		    competencia = new Competencia();
		    binder.loadAll();
		}
		
		public void onClick$btnTablaPosiciones(){
			Component catalogo = Executions.createComponents("/Competencias/Vistas/FrmTablaPosiciones.zul", null, null);
			binder.loadAll();
		}
		
		public void onClick$btnSalir(){
			formulario.detach();
			
		}
		

		//SETTERS Y GETTERS
		public Competencia getCompetencia() {
			return competencia;
		}


		public void setCompetencia(Competencia competencia) {
			this.competencia = competencia;
		}


		public List<Categoria> getCategorias() {
			return categorias;
		}


		public void setCategorias(List<Categoria> categorias) {
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

		
		
	

}
