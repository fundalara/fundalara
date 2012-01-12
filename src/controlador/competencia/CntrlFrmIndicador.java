package controlador.competencia;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import modelo.Categoria;
import modelo.CategoriaCompetencia;
import modelo.Competencia;
import modelo.DatoBasico;
import modelo.Indicador;
import modelo.IndicadorCategoriaCompetencia;
import modelo.TipoDato;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;

import comun.EstadoCompetencia;

import servicio.implementacion.ServicioCategoriaCompetencia;
import servicio.implementacion.ServicioDatoBasico;
import servicio.implementacion.ServicioIndicador;
import servicio.implementacion.ServicioIndicadorCategoriaCompetencia;
import servicio.implementacion.ServicioTipoDato;

public class CntrlFrmIndicador extends GenericForwardComposer {
	Indicador indicador;
	List<CategoriaCompetencia> categorias;
	Component comp;
	ServicioCategoriaCompetencia servicioCategoriaCompetencia;
	ServicioIndicadorCategoriaCompetencia servicioIndicadorCategoriaCompetencia;
	ServicioIndicador servicioIndicador;
	Competencia competencia;
	AnnotateDataBinder binder;
	List<Indicador> indicadores;
	List<IndicadorCategoriaCompetencia> indicadoresAux = new ArrayList<IndicadorCategoriaCompetencia>();
	Listbox lsbxIndicadores, lsbxIndicadoresSeleccionados;
	Component formulario;
	Combobox cmbSeleccionarCategoria, cmbSeleccionarModalidad;
	List<DatoBasico> lsbxModalidadIndicador;
	ServicioTipoDato servicioTipoDato;
	ServicioDatoBasico servicioDatoBasico;

	public void doAfterCompose(Component c) throws Exception {
		super.doAfterCompose(c);
		c.setVariable("cntrl", this, true);

		indicador = new Indicador();
		formulario = c;
		
		/*TipoDato modalidadIndicador = servicioTipoDato.buscarPorTipo("MODALIDAD INDICADOR");
		lsbxModalidadIndicador = servicioDatoBasico.buscarPorTipoDato(modalidadIndicador);*/
		

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
				categorias = ConvertirConjuntoALista(competencia
						.getCategoriaCompetencias());
				binder.loadAll();
			}
		});

	}

	public void onChange$cmbSeleccionarCategoria() {
		Categoria cat =  (Categoria) cmbSeleccionarCategoria.getSelectedItem().getValue();
		indicadores = servicioIndicadorCategoriaCompetencia
				.listarIndicadoresPorCategoria(cat, competencia);
		binder.loadAll();

	}
	
	public void onChange$cmbSeleccionarModalidad(){
		/*cmbSeleccionarModalidad.getSelectedItem().getValue();
		*/
		
	}

	// Mueve los indicadores del lsbxIndicadores a lsbxIndicadoresSeleccionados
	public void seleccionarIndicadores() {
		Set set = lsbxIndicadores.getSelectedItems();
		for (Object obj : new ArrayList(set)) {
			IndicadorCategoriaCompetencia ind = (IndicadorCategoriaCompetencia) ((Listitem) obj).getValue();

			indicadoresAux.add(ind);
			binder.loadAll();
		}
	}

	public void indicadoresSeleccionados() {
		Set set = lsbxIndicadoresSeleccionados.getSelectedItems();
		for (Object obj : new ArrayList(set)) {
			IndicadorCategoriaCompetencia ind = (IndicadorCategoriaCompetencia) ((Listitem) obj).getValue();

			indicadoresAux.remove(ind);
			binder.loadAll();

		}

	}

	public void onClick$btnMoverDerecha() {
		seleccionarIndicadores();

	}

	public void onClick$btnMoverIzquierda() {
		indicadoresSeleccionados();

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

	public List<IndicadorCategoriaCompetencia> getIndicadoresAux() {
		return indicadoresAux;
	}

	public void setIndicadoresAux(List<IndicadorCategoriaCompetencia> indicadoresAux) {
		this.indicadoresAux = indicadoresAux;
	}

	public Listbox getLsbxIndicadores() {
		return lsbxIndicadores;
	}

	public void setLsbxIndicadores(Listbox lsbxIndicadores) {
		this.lsbxIndicadores = lsbxIndicadores;
	}

	public Listbox getLsbxIndicadoresSeleccionados() {
		return lsbxIndicadoresSeleccionados;
	}

	public void setLsbxIndicadoresSeleccionados(
			Listbox lsbxIndicadoresSeleccionados) {
		this.lsbxIndicadoresSeleccionados = lsbxIndicadoresSeleccionados;
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

}