package controlador.competencia;

import java.util.List;

import modelo.Categoria;
import modelo.CategoriaCompetencia;
import modelo.Competencia;
import modelo.Indicador;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Listbox;

import servicio.competencia.ServicioCategoriaCompetencia;
import servicio.competencia.ServicioIndicador;

public class CntrlFrmIndicador extends GenericForwardComposer {
	Indicador indicador;
	List<CategoriaCompetencia> categorias;
	Component comp;
	ServicioCategoriaCompetencia servicioCategoriaCompetencia;
	ServicioIndicador servicioIndicador;
	Competencia competencia;
	AnnotateDataBinder binder;
	List<Indicador> indicadores;
	Listbox lsbxIndicadores;

	public void doAfterCompose(Component c) throws Exception {
		super.doAfterCompose(c);
		c.setVariable("cntrl", this, true);
		comp = c;
		indicador = new Indicador();
		int cod = servicioIndicador.listar().size() + 1;
		
		indicador.setCodigoIndicador(String.valueOf(cod));
		indicadores = servicioIndicador.listarActivos();
		

	}
	
	
	public void onClick$btnBuscar() {
	    comp = Executions.createComponents("/Competencias/Vistas/FrmCatalogoCompetencia.zul",null,null);
	    comp.setVariable("anterior",comp,false);
	    comp.addEventListener("onRetorno", new EventListener() {
		@Override
		public void onEvent(Event arg0) throws Exception {
		      competencia = (Competencia) comp.getVariable("competencia",false);
		      categorias =  (List<CategoriaCompetencia>) comp.getVariable("categoria",false);
		      binder.loadAll();
		}
		});
	}


	


	public List<CategoriaCompetencia> getCategorias() {
		return categorias;
	}


	public void setCategorias(List<CategoriaCompetencia> categorias) {
		this.categorias = categorias;
		binder.loadAll();
	}


	public Competencia getCompetencia() {
		return competencia;
	}


	public void setCompetencia(Competencia competencia) {
		this.competencia = competencia;
		binder.loadAll();
	}


	public Indicador getIndicador() {
		return indicador;
	}


	public void setIndicador(Indicador indicador) {
		this.indicador = indicador;
	}


	public List<Indicador> getIndicadores() {
		return indicadores;
	}


	public void setIndicadores(List<Indicador> indicadores) {
		this.indicadores = indicadores;
	}


	


	


	

}
