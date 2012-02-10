package controlador.competencia;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import modelo.ClasificacionCompetencia;
import modelo.CondicionCompetencia;
import modelo.DatoBasico;
import modelo.Divisa;
import modelo.Indicador;
import modelo.IndicadorCategoriaCompetencia;
import modelo.Roster;

import org.joni.ast.CClassNode;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.api.Listbox;

import servicio.implementacion.ServicioCondicionCompetencia;
import servicio.implementacion.ServicioDatoBasico;
import servicio.implementacion.ServicioDivisa;
import servicio.implementacion.ServicioClasificacionCompetencia;

public class CntrlFrmClasificacion extends GenericForwardComposer {

	// ATRIBUTOS
	Component formulario;
	AnnotateDataBinder binder;
	DatoBasico datoBasico;
	ClasificacionCompetencia clasificacionCompetencia;
	CondicionCompetencia condicionCompetencia;
	CondicionCompetencia condi;

	// SERVICIOS UTILIZADOS
	ServicioDatoBasico servicioDatoBasico;
	ServicioClasificacionCompetencia servicioClasificacionCompetencia;
	ServicioCondicionCompetencia servicioCondicionCompetencia;

	List<ClasificacionCompetencia> clasificacionCompetencias;
	List<DatoBasico> tipoCompetencias;
	List<DatoBasico> condicionCompetencias;
	List<CondicionCompetencia> condicionesSeleccionadas;
	List<CondicionCompetencia> auxCondiciones;
	

	Listbox lsbxCondiciones;
	Listbox lsbxCondicionesSeleccionadas;
	Combobox cmbTipoCompetencia;
	Textbox txtDescripcion;
	Textbox txtClasificacion;
	Button btnEliminar;
	Button btnAgregar;
	Button btnQuitar;

	String nombre;
	String descripcion;

	// ESTE METODO SE LLAMA AL CARGAR LA VENTANA
	public void doAfterCompose(Component c) throws Exception {
		super.doAfterCompose(c);
		// SE UTILIZA PARA HACER REFERENCIA A LOS OBJETOS DESDE LA VISTA (ej
		// cntrl.algo). DEBE IR SIEMPRE AQUI
		c.setVariable("cntrl", this, true);
		// SE GUARDA LA REFERENCIA AL FORMULARIO ACTUAL
		tipoCompetencias = servicioDatoBasico.listarTipoCompetencia();
		condicionCompetencias = servicioDatoBasico.listarCondicion();
		formulario = c;
		restaurar();
		condicionesSeleccionadas = new ArrayList<CondicionCompetencia>();
		auxCondiciones = new ArrayList<CondicionCompetencia>();
		inicializar();
	}

	public void inicializar() {
		txtDescripcion.setDisabled(true);
		txtClasificacion.setDisabled(true);
		btnAgregar.setDisabled(true);
		btnQuitar.setDisabled(true);
		
	}

	public void prender() {
		txtDescripcion.setDisabled(false);
		txtClasificacion.setDisabled(false);
		btnAgregar.setDisabled(false);
		btnQuitar.setDisabled(false);
		
	}

	// CARGA EL COMBO DE CLASIFICACION
	public void onChange$cmbTipoCompetencia() {
		DatoBasico clasi = (DatoBasico) cmbTipoCompetencia.getSelectedItem()
				.getValue();
		clasificacionCompetencias = servicioClasificacionCompetencia
				.listarClasificacion(clasi);
		prender();
		binder.loadAll();
	}

	// LIMPIA LA PANTALLA AL GUARDAR, ELIMINAR Y CANCELAR
	public void restaurar() {
		cmbTipoCompetencia.setText("-- Seleccione --");
		clasificacionCompetencia = new ClasificacionCompetencia();
		condicionesSeleccionadas = new ArrayList<CondicionCompetencia>();
	}

	// MUEVE LAS CONDICIONES DE UNA LISTA A OTRA
	public void Agregar(Listbox origen, Listbox destino, List lista) {

		Set seleccionados = origen.getSelectedItems();
		for (Iterator i = seleccionados.iterator(); i.hasNext();) {
			boolean sw = false;
			Listitem li = (Listitem) i.next();
			DatoBasico c1 = (DatoBasico) li.getValue();
			condi = new CondicionCompetencia();
			condi.setDatoBasico(c1);
			condi.setClasificacionCompetencia(clasificacionCompetencia);
			condi.setEstatus('A');
			List seleccionDestino = destino.getItems();
			for (Iterator j = seleccionDestino.iterator(); j.hasNext();) {
				Listitem li2 = (Listitem) j.next();
				CondicionCompetencia c2 = (CondicionCompetencia) li2.getValue();

				if (c1.getNombre().equals(c2.getDatoBasico().getNombre())) {
					sw = true;
					break;
				}
			}
			if (!sw) {
				lista.add(condi);
			}
		}
	}

	public void Quitar(Listbox origen, List lista) {

		Set seleccionados = origen.getSelectedItems();
		for (Iterator i = seleccionados.iterator(); i.hasNext();) {
			Listitem li = (Listitem) i.next();
			CondicionCompetencia cc = (CondicionCompetencia) li.getValue();	
			if (cc.getCodigoCondicionCompetencia() !=0 ){
				auxCondiciones.add(cc);
			}			
			lista.remove(cc);
		}
	}

	
	
	// Llama al catalogo
	public void onClick$btnBuscar() {
		// se crea el catalogo y se llama
		Component catalogo = Executions
				.createComponents(
						"/Competencias/Vistas/FrmCatalogoClasificacion.zul",
						null, null);
		// asigna una referencia del formulario al catalogo.
		catalogo.setVariable("formulario", formulario, false);
		formulario.addEventListener("onCatalogoCerrado", new EventListener() {
			@Override
			// Este metodo se llama cuando se envia la señal desde el catalogo
			public void onEvent(Event arg0) throws Exception {
				// se obtiene la clasificacion
				condicionesSeleccionadas = new ArrayList<CondicionCompetencia>();
				clasificacionCompetencia = (ClasificacionCompetencia) formulario.getVariable("clasificacion", false);
				cmbTipoCompetencia.setSelectedIndex(buscarCombo(clasificacionCompetencia, tipoCompetencias, 1));
				condicionesSeleccionadas = servicioCondicionCompetencia.listarCondicionSeleccionada(clasificacionCompetencia);		
				prender();
				binder.loadAll();
			}
		});

	}

	// <--- BOTONES --->

	// GUARDAR LOS DATOS DE LA PANTALLA EN LAS RESPECTIVAS TABLAS
	public void onClick$btnGuardar() throws InterruptedException {

		clasificacionCompetencia.setDatoBasico((DatoBasico) cmbTipoCompetencia.getSelectedItem().getValue());
		servicioClasificacionCompetencia.agregar(clasificacionCompetencia);
		if (condicionesSeleccionadas.size() > 0) {
			for (int i = 0; i < condicionesSeleccionadas.size(); i++) {
			servicioCondicionCompetencia.agregar(condicionesSeleccionadas.get(i));
			}
			
		}
		for (int j = 0; j < auxCondiciones.size(); j++){
			auxCondiciones.get(j).setEstatus('E');			
			servicioCondicionCompetencia.agregar(auxCondiciones.get(j));
		}		
		Messagebox.show("Datos agregados exitosamente", "Mensaje",
				Messagebox.OK, Messagebox.EXCLAMATION);
		restaurar();
		binder.loadAll();
	}

	// XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXx

	public void onClick$btnEliminar() throws InterruptedException {
		if (Messagebox.show("¿Realmente desea eliminar esta Clasificación?",
				"Mensaje", Messagebox.YES + Messagebox.NO, Messagebox.QUESTION) == Messagebox.YES) {
			
			int codigo = clasificacionCompetencia.getCodigoClasificacionCompetencia();
			for (int j = 0; j < condicionesSeleccionadas.size(); j++){
				
				if (codigo == condicionesSeleccionadas.get(j).getClasificacionCompetencia().getCodigoClasificacionCompetencia())
					
					servicioCondicionCompetencia.eliminar(condicionesSeleccionadas.get(j));
			}	
			servicioClasificacionCompetencia.eliminar(clasificacionCompetencia);
			
			
			
			restaurar();
			binder.loadAll();
			Messagebox.show("Datos eliminados exitosamente", "Mensaje",
					Messagebox.OK, Messagebox.EXCLAMATION);
		}
	}

	public void onClick$btnCancelar() {
		restaurar();
		binder.loadAll();
	}

	public void onClick$btnSalir() {
		formulario.detach();

	}

	public int buscarCombo(ClasificacionCompetencia objeto,
			List<DatoBasico> lista, Integer campo) {

		int x = 0;
		int j = -1;
		for (Iterator<DatoBasico> k = lista.iterator(); k.hasNext();) {
			DatoBasico db = k.next();
			j++;
			if (db.getCodigoDatoBasico() == objeto.getDatoBasico()
					.getCodigoDatoBasico()) {
				x = j;
			}
		}
		return x;
	}

	// PASA DE UNA LISTA A OTRA
	public void onClick$btnAgregar() {
		Agregar(lsbxCondiciones, lsbxCondicionesSeleccionadas,condicionesSeleccionadas);
		binder.loadAll();
	}

	public void onClick$btnQuitar() {
		Quitar(lsbxCondicionesSeleccionadas, condicionesSeleccionadas);
		binder.loadAll();

	}

	// GETTERS AND SETTERS
	public DatoBasico getDatoBasico() {
		return datoBasico;
	}

	public void setDatoBasico(DatoBasico datoBasico) {
		this.datoBasico = datoBasico;
	}

	public ClasificacionCompetencia getClasificacionCompetencia() {
		return clasificacionCompetencia;
	}

	public void setClasificacionCompetencia(
			ClasificacionCompetencia clasificacionCompetencia) {
		this.clasificacionCompetencia = clasificacionCompetencia;
	}

	public List<ClasificacionCompetencia> getClasificacionCompetencias() {
		return clasificacionCompetencias;
	}

	public void setClasificacionCompetencias(
			List<ClasificacionCompetencia> clasificacionCompetencias) {
		this.clasificacionCompetencias = clasificacionCompetencias;
	}

	public List<DatoBasico> getTipoCompetencias() {
		return tipoCompetencias;
	}

	public void setTipoCompetencias(List<DatoBasico> tipoCompetencias) {
		this.tipoCompetencias = tipoCompetencias;
	}

	public AnnotateDataBinder getBinder() {
		return binder;
	}

	public void setBinder(AnnotateDataBinder binder) {
		this.binder = binder;
	}

	public List<DatoBasico> getCondicionCompetencias() {
		return condicionCompetencias;
	}

	public void setCondicionCompetencias(List<DatoBasico> condicionCompetencias) {
		this.condicionCompetencias = condicionCompetencias;
	}

	public List<CondicionCompetencia> getCondicionesSeleccionadas() {
		return condicionesSeleccionadas;
	}

	public void setCondicionesSeleccionadas(
			List<CondicionCompetencia> condicionesSeleccionadas) {
		this.condicionesSeleccionadas = condicionesSeleccionadas;
	}

	public List<CondicionCompetencia> getAuxCondiciones() {
		return auxCondiciones;
	}

	public void setAuxCondiciones(List<CondicionCompetencia> auxCondiciones) {
		this.auxCondiciones = auxCondiciones;
	}


	
	

}
