package controlador.competencia;

import java.awt.Button;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import modelo.Categoria;
import modelo.CategoriaCompetencia;
import modelo.ClasificacionCompetencia;
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
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

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
	List<Indicador> indicadoresColectivo;
	List<IndicadorCategoriaCompetencia> indicadoresSeleccionados = new ArrayList<IndicadorCategoriaCompetencia>();
	List<IndicadorCategoriaCompetencia> indicadoresSeleccionadosColectivos = new ArrayList<IndicadorCategoriaCompetencia>();
	List<Indicador> indicadoresAux = new ArrayList<Indicador>();
	Listbox lsbxIndicadores, lsbxIndicadoresSeleccionados, lsbxIndicadoresColectivos, lsbxIndicadoresSeleccionadosColectivos;
	Component formulario;
	Combobox cmbSeleccionarCategoria, cmbSeleccionarCategoriaColectivo, cmbSeleccionarModalidad, cmbSeleccionarModalidadColectivo;
	List<DatoBasico> lsbxModalidadIndicador;
	ServicioTipoDato servicioTipoDato;
	ServicioDatoBasico servicioDatoBasico;
	org.zkoss.zul.Button btnGuardar, btnEliminar, btnCancelar, btnMoverDerecha, btnMoverIzquierda, btnMoverDerechaColectivo, btnMoverIzquierdaColectivo;
	Textbox txtNombreCompetencia, txtTipoCompetencia, txtClasificacion, txtFechaInicioCompetencia, txtFechaFin;
	List<IndicadorCategoriaCompetencia> eliminar = new ArrayList<IndicadorCategoriaCompetencia>();
	Window frmIndicador;
	
	
	
	public List<DatoBasico> getLsbxModalidadIndicador() {
		return lsbxModalidadIndicador;
	}

	public void setLsbxModalidadIndicador(List<DatoBasico> lsbxModalidadIndicador) {
		this.lsbxModalidadIndicador = lsbxModalidadIndicador;
	}

	

	public void doAfterCompose(Component c) throws Exception {
		super.doAfterCompose(c);
		c.setVariable("cntrl", this, true);
		inicializar();
		btnMoverDerecha.setDisabled(true);
		btnMoverIzquierda.setDisabled(true);
		btnMoverDerechaColectivo.setDisabled(true);
		btnMoverIzquierdaColectivo.setDisabled(true);
		TipoDato modalidadIndicador = servicioTipoDato.buscarPorTipo("MODALIDAD INDICADOR");
		lsbxModalidadIndicador = servicioDatoBasico.buscarPorTipoDato(modalidadIndicador);
		btnMoverDerecha.setDisabled(true);
		btnMoverIzquierda.setDisabled(true);
		btnMoverDerechaColectivo.setDisabled(true);
		btnMoverIzquierdaColectivo.setDisabled(true);	
		cmbSeleccionarCategoria.setDisabled(true);
		cmbSeleccionarCategoriaColectivo.setDisabled(true);
		cmbSeleccionarModalidad.setDisabled(true);
		cmbSeleccionarModalidadColectivo.setDisabled(true);
		formulario = c;
	}

	public void inicializar() {
		
		btnGuardar.setDisabled(true);
		btnCancelar.setDisabled(true);
		cmbSeleccionarCategoria.setReadonly(true);
		cmbSeleccionarModalidad.setReadonly(true);
		cmbSeleccionarCategoriaColectivo.setReadonly(true);
		cmbSeleccionarModalidadColectivo.setReadonly(true);
		txtNombreCompetencia.setReadonly(true);
		txtTipoCompetencia.setReadonly(true);
		txtClasificacion.setReadonly(true);
		lsbxIndicadores.setDisabled(true);
		lsbxIndicadoresSeleccionados.setDisabled(true);
		lsbxIndicadoresColectivos.setDisabled(true);
		indicador = new Indicador(); 
		
	}
	
	public void camposHabilitados (){
		cmbSeleccionarCategoria.setReadonly(false);
		cmbSeleccionarModalidad.setReadonly(false);
		cmbSeleccionarCategoriaColectivo.setReadonly(false);
		cmbSeleccionarModalidadColectivo.setReadonly(false);
		cmbSeleccionarCategoria.setDisabled(false);
		cmbSeleccionarCategoriaColectivo.setDisabled(false);
		cmbSeleccionarModalidad.setDisabled(false);
		cmbSeleccionarModalidadColectivo.setDisabled(false);
		btnGuardar.setDisabled(false);
		btnCancelar.setDisabled(false);
		lsbxIndicadores.setDisabled(false);
		lsbxIndicadoresSeleccionados.setDisabled(false);

		
	}
	
	public void limpiar() {
		cmbSeleccionarCategoria.setText("--Seleccione--");
		cmbSeleccionarModalidad.setText("--Seleccione--");
		cmbSeleccionarCategoriaColectivo.setText("--Seleccione--");
		cmbSeleccionarModalidadColectivo.setText("--Seleccione--");
		txtTipoCompetencia.setText("");
		txtClasificacion.setText("");
		competencia = new Competencia();
		competencia.setClasificacionCompetencia(new ClasificacionCompetencia());
		indicadoresSeleccionadosColectivos = new ArrayList<IndicadorCategoriaCompetencia>();
		indicadoresSeleccionados = new ArrayList<IndicadorCategoriaCompetencia>();
		indicadores = new ArrayList<Indicador>();
		indicadoresColectivo = new ArrayList<Indicador>();
		eliminar = new ArrayList<IndicadorCategoriaCompetencia>();
		btnMoverDerecha.setDisabled(true);
		btnMoverIzquierda.setDisabled(true);
		btnMoverDerechaColectivo.setDisabled(true);
		btnMoverIzquierdaColectivo.setDisabled(true);
		cmbSeleccionarCategoria.setDisabled(true);
		cmbSeleccionarCategoriaColectivo.setDisabled(true);
		cmbSeleccionarModalidad.setDisabled(true);
		cmbSeleccionarModalidadColectivo.setDisabled(true);
		
		
	}
	
	
	public void onClick$btnSalir() throws InterruptedException {
		if (indicador != null) {
			int result = Messagebox
					.show("Existen elementos en el formulario ¿Realmente desea salir?",
							"Olimpo - Confirmar Operación", Messagebox.OK | Messagebox.CANCEL,
							Messagebox.QUESTION);
			switch (result) {
			case Messagebox.OK:
				onClick$btnCancelar();
				binder.loadAll();
				frmIndicador.detach();
				break;
			case Messagebox.CANCEL:
				break;
			default:
				break;
			}
		} else {
			onClick$btnCancelar();
			binder.loadAll();
			frmIndicador.detach();
		}

	}

	public void onClick$btnCancelar() {
		limpiar();
		binder.loadAll();
	}
	
	public void onClick$btnBuscarCompetencia() {

		Component catalogo = Executions.createComponents(
				"/Competencias/Vistas/FrmCatalogoCompetencia.zul", null, null);
		catalogo.setVariable("formulario", formulario, false);
		catalogo.setVariable("estado_comp", EstadoCompetencia.REGISTRADA, false);		

		formulario.addEventListener("onCatalogoCerrado", new EventListener() {
			@Override
			public void onEvent(Event arg0) throws Exception {
				competencia = (Competencia) formulario.getVariable("competencia", false);
				categorias = ConvertirConjuntoALista(competencia.getCategoriaCompetencias());
				txtTipoCompetencia.setText(competencia.getClasificacionCompetencia().getDatoBasico().getNombre());
				txtClasificacion.setText(competencia.getClasificacionCompetencia().getNombre());
				camposHabilitados();
				binder.loadAll();
			}
		});

	}
	
	public void onClick$btnGuardar() throws InterruptedException {
		for (int i=0;i<indicadoresSeleccionados.size();i++){
			servicioIndicadorCategoriaCompetencia.agregar(indicadoresSeleccionados.get(i));
		}
		for (int i=0;i<indicadoresSeleccionadosColectivos.size();i++){
			servicioIndicadorCategoriaCompetencia.agregar(indicadoresSeleccionadosColectivos.get(i));
		}
		
		for (int i=0;i<eliminar.size();i++){
			servicioIndicadorCategoriaCompetencia.eliminar(eliminar.get(i));
		}
		
			
		Messagebox.show("Datos agregados exitosamente", "Olimpo - Operación Exitosa",
				Messagebox.OK, Messagebox.EXCLAMATION);
		limpiar();
		binder.loadAll();
	}
	

	public List<IndicadorCategoriaCompetencia> getIndicadoresSeleccionados() {
		return indicadoresSeleccionados;
	}

	public void setIndicadoresSeleccionados(
			List<IndicadorCategoriaCompetencia> indicadoresSeleccionados) {
		this.indicadoresSeleccionados = indicadoresSeleccionados;
	}

	public void onChange$cmbSeleccionarModalidad(){
	
		indicadores= servicioIndicador.listarIndicadorIndividualPorModalidad((DatoBasico) cmbSeleccionarModalidad.getSelectedItem().getValue());
		if (competencia != null){
			String categoria =  cmbSeleccionarCategoria.getValue();
			if (!categoria.equals("--Seleccione--")){
				Categoria cat = (Categoria) cmbSeleccionarCategoria.getSelectedItem().getValue();
			    DatoBasico db = (DatoBasico) cmbSeleccionarModalidad.getSelectedItem().getValue();
				indicadoresSeleccionados = servicioIndicadorCategoriaCompetencia.listarIndicadoresIndividualesPorCategoria(cat, competencia,db);
			    if (indicadoresSeleccionados.size() == 0){
			    	List<Indicador> temp = servicioIndicador.listarIndicadorPorModalidadyTipo(db,"INDIVIDUAL");
			    	for (Indicador i: temp){
			    		IndicadorCategoriaCompetencia icc = new IndicadorCategoriaCompetencia();
			    		icc.setCategoria(cat);
			    		icc.setCompetencia(competencia);
			    		icc.setEstatus('A');
			    		icc.setIndicador(i);
			    		indicadoresSeleccionados.add(icc);
			    	}
			    }
				
				btnMoverDerecha.setDisabled(false);
			    btnMoverIzquierda.setDisabled(false);
			}
		}
		binder.loadAll();
		
	}
	
	public void onChange$cmbSeleccionarModalidadColectivo(){
		indicadoresColectivo= servicioIndicador.listarIndicadorColectivoPorModalidad((DatoBasico) cmbSeleccionarModalidadColectivo.getSelectedItem().getValue());
		if (competencia != null){
			String categoria =  cmbSeleccionarCategoriaColectivo.getValue();
			if (!categoria.equals("--Seleccione--")){
				Categoria cat = (Categoria) cmbSeleccionarCategoriaColectivo.getSelectedItem().getValue();
			    DatoBasico db = (DatoBasico) cmbSeleccionarModalidadColectivo.getSelectedItem().getValue();
				indicadoresSeleccionadosColectivos = servicioIndicadorCategoriaCompetencia.listarIndicadoresColectivosPorCategoria(cat, competencia,db);
				 if (indicadoresSeleccionadosColectivos.size() == 0){
				    	List<Indicador> temp = servicioIndicador.listarIndicadorPorModalidadyTipo(db,"COLECTIVO");
				    	for (Indicador i: temp){
				    		IndicadorCategoriaCompetencia icc = new IndicadorCategoriaCompetencia();
				    		icc.setCategoria(cat);
				    		icc.setCompetencia(competencia);
				    		icc.setEstatus('A');
				    		icc.setIndicador(i);
				    		indicadoresSeleccionadosColectivos.add(icc);
				    	}
				    }
				 
				btnMoverDerechaColectivo.setDisabled(false);
			    btnMoverIzquierdaColectivo.setDisabled(false);
			}
		}
		binder.loadAll();
		
	}

	public void Agregar(Listbox origen, Listbox destino, List lista, Combobox combo) {

		Set seleccionados = origen.getSelectedItems();
		for (Iterator i = seleccionados.iterator(); i.hasNext();) {
			boolean sw = false;
			Listitem li = (Listitem) i.next();
			Indicador ind = (Indicador) li.getValue();
			IndicadorCategoriaCompetencia icc =new IndicadorCategoriaCompetencia();
			icc.setCompetencia(competencia);
			icc.setIndicador(ind);
			icc.setCategoria((Categoria) combo.getSelectedItem().getValue());
			List seleccionDestino = destino.getItems();
			for (Iterator j = seleccionDestino.iterator(); j.hasNext();) {
				Listitem li2 = (Listitem) j.next();
				IndicadorCategoriaCompetencia ic = (IndicadorCategoriaCompetencia ) li2.getValue();
				if (icc.getIndicador().getNombre().equals(ic.getIndicador().getNombre())) {
					sw = true;
					break;
				}
			}
			if (!sw) {
				lista.add(icc);
			}
		}
	}
	

	public void Quitar(Listbox origen, List lista,List elim) {
		Set seleccionados = origen.getSelectedItems();
		for (Iterator i = seleccionados.iterator(); i.hasNext();) {
			Listitem li = (Listitem) i.next();
			IndicadorCategoriaCompetencia icc = (IndicadorCategoriaCompetencia) li.getValue();
			if (icc.getCodigoIndicadorCategoriaCompetencia() != 0)
				eliminar.add(icc);
			lista.remove(icc);
		}
	}

	

	public void onClick$btnMoverDerecha() {
		Agregar(lsbxIndicadores,lsbxIndicadoresSeleccionados,indicadoresSeleccionados,cmbSeleccionarCategoria);
		binder.loadAll();
	}
	
	public void onClick$btnMoverDerechaColectivo() {
		Agregar(lsbxIndicadoresColectivos,lsbxIndicadoresSeleccionadosColectivos,indicadoresSeleccionadosColectivos,cmbSeleccionarCategoriaColectivo);
		binder.loadAll();
	}

	public void onClick$btnMoverIzquierda() {
		Quitar(lsbxIndicadoresSeleccionados, indicadoresSeleccionados,eliminar);
		binder.loadAll();
	}
	
	public void onClick$btnMoverIzquierdaColectivo() {
		Quitar(lsbxIndicadoresSeleccionadosColectivos, indicadoresSeleccionadosColectivos,eliminar);
		binder.loadAll();
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

	public List<Indicador> getIndicadoresAux() {
		return indicadoresAux;
	}

	public void setIndicadoresAux(List<Indicador> indicadoresAux) {
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
	
	

	public List<Indicador> getIndicadoresColectivo() {
		return indicadoresColectivo;
	}

	public void setIndicadoresColectivo(List<Indicador> indicadoresColectivo) {
		this.indicadoresColectivo = indicadoresColectivo;
	}
	
	

	public List<IndicadorCategoriaCompetencia> getIndicadoresSeleccionadosColectivos() {
		return indicadoresSeleccionadosColectivos;
	}

	public void setIndicadoresSeleccionadosColectivos(
			List<IndicadorCategoriaCompetencia> indicadoresSeleccionadosColectivos) {
		this.indicadoresSeleccionadosColectivos = indicadoresSeleccionadosColectivos;
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