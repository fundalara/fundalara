package controlador.competencia;

import org.zkoss.zk.ui.event.EventListener;
import java.util.List;

import modelo.Categoria;
import modelo.Competencia;
import modelo.Divisa;
import modelo.Equipo;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.api.Comboitem;

import comun.EstadoCompetencia;

import servicio.implementacion.ServicioCategoria;
import servicio.implementacion.ServicioEquipo;
import servicio.implementacion.ServicioRoster;

public class CntrlFrmRoster extends GenericForwardComposer {

	
	ServicioCategoria servicioCategoria;
	ServicioEquipo servicioEquipo;
	AnnotateDataBinder binder;
	Component formulario;
	
	Categoria categoria;
	Equipo equipo;
	Competencia competencia;
	Combobox cmbCategoria;
	ServicioRoster servicioRoster;
	List<Categoria> categorias;
	List<Equipo> equipos;
	Combobox cmbEquipo;
	String categoriaSeleccion;
	
	
	
	
	
	
	
	public Equipo getEquipo() {
		return equipo;
	}

	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}

	public List<Equipo> getEquipos() {
		return equipos;
	}

	public void setEquipos(List<Equipo> equipos) {
		this.equipos = equipos;
	}

	public Combobox getCmbEquipo() {
		return cmbEquipo;
	}

	public void setCmbEquipo(Combobox cmbEquipo) {
		this.cmbEquipo = cmbEquipo;
	}

	public void doAfterCompose(Component c) throws Exception {
		super.doAfterCompose(c);
	
		c.setVariable("cntrl", this, true);
	
		formulario = c;
		
		restaurarCategoria();
		
		
		
	}
	
	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Combobox getCmbCategoria() {
		return cmbCategoria;
	}

	public void setCmbCategoria(Combobox cmbCategoria) {
		this.cmbCategoria = cmbCategoria;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

	public void restaurarCategoria(){
		
		categoria = new Categoria();
		
		
		categorias = servicioCategoria.listar();
		
		cmbCategoria.setText("--Seleccione--");
		
		
	
	}
	
	public void onChange$cmbCategoria() throws InterruptedException{
		
		equipo = new Equipo();
		cmbEquipo.setText("--Seleccione--");
		
		if (cmbCategoria.getText()!="--Seleccione--"){
		    Comboitem cmbItem = cmbCategoria.getSelectedItem();
		    Categoria cat = (Categoria) cmbItem.getValue();
		    Integer codCat = cat.getCodigoCategoria();
		    		
			equipos = servicioEquipo.listarEquipoPorCategoria( codCat);	
			binder.loadAll();	
		}else{cmbEquipo.setText("--Seleccione--"); }
		
		
		
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
			
				binder.loadAll();
			}
		});

	}
	

	public Competencia getCompetencia() {
		return competencia;
	}

	public void setCompetencia(Competencia competencia) {
		this.competencia = competencia;
	}
		
		
	}
	
	

