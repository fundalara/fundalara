package controlador.competencia;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import modelo.ClasificacionCompetencia;
import modelo.CondicionCompetencia;
import modelo.DatoBasico;
import modelo.IndicadorCategoriaCompetencia;
import modelo.Roster;

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

public class CntrlFrmNuevaModalidad extends GenericForwardComposer {

	// ATRIBUTOS
	Component formulario;
	AnnotateDataBinder binder;
	DatoBasico datoBasico;
	ClasificacionCompetencia clasificacionCompetencia;	

	// SERVICIOS UTILIZADOS
	ServicioDatoBasico servicioDatoBasico;
	ServicioClasificacionCompetencia servicioClasificacionCompetencia;
	ServicioCondicionCompetencia servicioCondicionCompetencia;

	List<ClasificacionCompetencia> clasificacionCompetencias;
	List<DatoBasico> tipoCompetencias;
	List<DatoBasico> condicionCompetencias;
	
	Listbox lsbxCondiciones;
	Combobox cmbTipoCompetencia;
	Combobox cmbClasificacion;
	Textbox txtDescripcion;
	Button btnEliminar;
	
	

	// ESTE METODO SE LLAMA AL CARGAR LA VENTANA
	public void doAfterCompose(Component c) throws Exception {
		super.doAfterCompose(c);
		// SE UTILIZA PARA HACER REFERENCIA A LOS OBJETOS DESDE LA VISTA (ej
		// cntrl.algo). DEBE IR SIEMPRE AQUI
		c.setVariable("cntrl", this, true);
		// SE GUARDA LA REFERENCIA AL FORMULARIO ACTUAL
		tipoCompetencias = servicioDatoBasico.listarTipoCompetencia();
		formulario = c;
		restaurar();
	}

	// CARGA EL COMBO DE CLASIFICACION
	public void onChange$cmbTipoCompetencia() {
		DatoBasico clasi = (DatoBasico) cmbTipoCompetencia.getSelectedItem()
				.getValue();
		clasificacionCompetencias = servicioClasificacionCompetencia
				.listarClasificacion(clasi);
		binder.loadAll();
	}
	
	public void onChange$cmbClasificacion(){
		//DatoBasico condi = (DatoBasico) cmbClasificacion.getSelectedItem().getValue();
		condicionCompetencias = servicioDatoBasico.listar();
		binder.loadAll();
	}
	

	// GUARDAR LOS DATOS DE LA PANTALLA EN LAS RESPECTIVAS TABLAS
	public void onClick$btnGuardar() throws InterruptedException {

		clasificacionCompetencia.setDatoBasico((DatoBasico) cmbTipoCompetencia
				.getSelectedItem().getValue());
		servicioClasificacionCompetencia.agregar(clasificacionCompetencia);
		Messagebox.show("Datos agregados exitosamente", "Mensaje",
				Messagebox.OK, Messagebox.EXCLAMATION);
		restaurar();
		binder.loadAll();
	}

	
	// LIMPIA LA PANTALLA AL GUARDAR, ELIMINAR Y CANCELAR
	public void restaurar() {
		cmbTipoCompetencia.setText("-- Seleccione --");
		clasificacionCompetencia = new ClasificacionCompetencia();
		cmbClasificacion.setText("-- Seleccione --");
	}

	public void onClick$btnCancelar() {
		restaurar();
		binder.loadAll();
	}

	public void onClick$btnSalir() {
		formulario.detach();

	}

	public void onClick$btnEliminar() throws InterruptedException {
		if (Messagebox.show("�Realmente desea eliminar esta modalidad?",
				"Mensaje", Messagebox.YES + Messagebox.NO, Messagebox.QUESTION) == Messagebox.YES) {
			servicioClasificacionCompetencia.eliminar(clasificacionCompetencia);
			restaurar();
			binder.loadAll();
			Messagebox.show("Datos eliminados exitosamente", "Mensaje",
					Messagebox.OK, Messagebox.EXCLAMATION);
		}
	}
	

	// MUEVE LAS CONDICIONES DE UNA LISTA A OTRA
	public void Agregar(Listbox origen, Listbox destino, List lista) {

		Set seleccionados = origen.getSelectedItems();
		for (Iterator i = seleccionados.iterator(); i.hasNext();) {
			boolean sw = false;
			Listitem li = (Listitem) i.next();
			Roster c1 = (Roster) li.getValue();
			List seleccionDestino = destino.getItems();
			for (Iterator j = seleccionDestino.iterator(); j.hasNext();) {
				Listitem li2 = (Listitem) j.next();
				Roster c2 = (Roster) li2.getValue();

				if (c1.getJugador().getCedulaRif()
						.equals(c2.getJugador().getCedulaRif())) {
					sw = true;
					break;
				}
			}
			if (!sw) {
				lista.add(c1);
			}
		}
	}
	

	public void Quitar(Listbox origen, List lista) {
		Set seleccionados = origen.getSelectedItems();
		for (Iterator i = seleccionados.iterator(); i.hasNext();) {
			Listitem li = (Listitem) i.next();
			Object o = li.getValue();
			lista.remove(o);
		}
	}
	
	
	
	
	
 //GETTERS AND SETTERS
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

}
