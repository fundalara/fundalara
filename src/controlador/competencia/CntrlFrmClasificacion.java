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
import org.zkoss.zk.ui.WrongValueException;
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

/**
 * @author Merielen Gaspar
 * @author Diana Santiago
 * 
 */

public class CntrlFrmClasificacion extends GenericForwardComposer {

	/**
	 * @version 2.0 01/02/2012 Clase CntrlFrmClasificacion para acceso/manejo de
	 *          la interfaz (vista) de las clasificaciones de una competencia
	 *          (FrmClasificacion.zul) Utiliza los Servicios
	 *          ServicioClasificacionCompetencia, ServicioCondicionCompetencia,
	 *          ServicioDatoBasico
	 */

	/** ATRIBUTOS */
	ClasificacionCompetencia clasificacionCompetencia;
	CondicionCompetencia condicionCompetencia;
	CondicionCompetencia condi;
	AnnotateDataBinder binder;
	DatoBasico datoBasico;
	Component formulario;
	List<ClasificacionCompetencia> clasificacionCompetencias;
	List<CondicionCompetencia> condicionesSeleccionadas;
	List<CondicionCompetencia> auxCondiciones;
	List<DatoBasico> condicionCompetencias;
	List<DatoBasico> tipoCompetencias;
	Boolean clasificacionBuscada;
	

	/** SERVICIOS UTILIZADOS */
	ServicioClasificacionCompetencia servicioClasificacionCompetencia;
	ServicioCondicionCompetencia servicioCondicionCompetencia;
	ServicioDatoBasico servicioDatoBasico;

	/** VISTAS */
	Listbox lsbxCondicionesSeleccionadas;
	Listbox lsbxCondiciones;

	Combobox cmbTipoCompetencia;

	Textbox txtClasificacion;
	Textbox txtDescripcion;

	Button btnEliminar;
	Button btnAgregar;
	Button btnQuitar;
	Button btnBuscar;

	String descripcion;
	String nombre;

	
	/** ESTE METODO SE LLAMA AL CARGAR LA VENTANA */
	public void doAfterCompose(Component c) throws Exception {
		super.doAfterCompose(c);
		c.setVariable("cntrl", this, true);
		tipoCompetencias = servicioDatoBasico.listarTipoCompetencia();
		condicionCompetencias = servicioDatoBasico.listarCondicion();
		formulario = c;
		restaurar();
		condicionesSeleccionadas = new ArrayList<CondicionCompetencia>();
		auxCondiciones = new ArrayList<CondicionCompetencia>();
		apagar();
	}

	/** ESTE METODO SE USA PARA DESHABILITAR LOS CAMPOS */
	public void apagar() {
		txtDescripcion.setDisabled(true);
		txtClasificacion.setDisabled(true);
		btnAgregar.setDisabled(true);
		btnQuitar.setDisabled(true);

	}

	/** ESTE METODO SE USA PARA HABILITAR LOS CAMPOS */
	public void prender() {
		txtDescripcion.setDisabled(false);
		txtClasificacion.setDisabled(false);
		btnAgregar.setDisabled(false);
		btnQuitar.setDisabled(false);

	}

	/**
	 * ESTE METODO SE USA PARA HABILITAR LOS CAMPOS UNA VEZ SELECCIONADO EL TIPO
	 * DE COMPETENCIA
	 */
	public void onChange$cmbTipoCompetencia() {
		DatoBasico clasi = (DatoBasico) cmbTipoCompetencia.getSelectedItem()
				.getValue();
		prender();
		binder.loadAll();
	}

	/**
	 * ESTE METODO SE USA PARA LIMPIAR LOS CAMPOS AL HACER CLICK EN EL BOTON
	 * GUARDAR, ELIMINAR O CANCELAR
	 */
	public void restaurar() {
		cmbTipoCompetencia.setText("-- Seleccione --");
		clasificacionCompetencia = new ClasificacionCompetencia();
		condicionesSeleccionadas = new ArrayList<CondicionCompetencia>();
		clasificacionBuscada = false;
	}

	/** ESTE METODO LLAMA AL METODO AGREGAR AL HACER CLICK EN EL BOTON AGREGAR */
	public void onClick$btnAgregar() {
		Agregar(lsbxCondiciones, lsbxCondicionesSeleccionadas,
				condicionesSeleccionadas);
		binder.loadAll();
	}

	/** ESTE METODO SE USA PARA MOVER LAS CONDICIONES DE UNA LISTA A OTRA */
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

	/** ESTE METODO LLAMA AL METODO QUITAR AL HACER CLICK EN EL BOTON QUITAR */
	public void onClick$btnQuitar() {
		Quitar(lsbxCondicionesSeleccionadas, condicionesSeleccionadas);
		binder.loadAll();

	}

	/** ESTE METODO SE USA PARA QUITAR LAS CONDICIONES DE UNA LISTA */
	public void Quitar(Listbox origen, List lista) {

		Set seleccionados = origen.getSelectedItems();
		for (Iterator i = seleccionados.iterator(); i.hasNext();) {
			Listitem li = (Listitem) i.next();
			CondicionCompetencia cc = (CondicionCompetencia) li.getValue();
			if (cc.getCodigoCondicionCompetencia() != 0) {
				auxCondiciones.add(cc);
			}
			lista.remove(cc);
		}
	}

	/**
	 * ESTE METODO SE USA PARA LLAMAR AL CATALAGO QUE MUESTRA LOS TIPOS DE
	 * COMPETENCIA (FrmCatalogoClasificacion.zul)
	 */
	public void onClick$btnBuscar() {
		Component catalogo = Executions
				.createComponents(
						"/Competencias/Vistas/FrmCatalogoClasificacion.zul",
						null, null);
		catalogo.setVariable("formulario", formulario, false);
		formulario.addEventListener("onCatalogoCerrado", new EventListener() {
			@Override
			public void onEvent(Event arg0) throws Exception {
				condicionesSeleccionadas = new ArrayList<CondicionCompetencia>();
				clasificacionCompetencia = (ClasificacionCompetencia) formulario
						.getVariable("clasificacion", false);
				cmbTipoCompetencia.setSelectedIndex(buscarCombo(
						clasificacionCompetencia, tipoCompetencias, 1));
				condicionesSeleccionadas = servicioCondicionCompetencia
						.listarCondicionSeleccionada(clasificacionCompetencia);
				prender();
				clasificacionBuscada = true;
				binder.loadAll();
			}
		});

	}

	/**
	 * ESTE METODO SE LLAMA AL HACER CLICK EN EL BOTON BUSCAR Y MUESTRA EN EL
	 * COMBO DE LA PANTALLA PRINCIPAL EL TIPO DE COMPETENCIA SELECCIONADA EN EL
	 * CATALOGO
	 */
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

	/** BOTONES */

	/**
	 * ESTE METODO SE USA PARA GUARDAR LOS DATOS DE LA PANTALLA EN LAS
	 * RESPECTIVAS TABLAS
	 */
	public void onClick$btnGuardar() throws InterruptedException {

			if (cmbTipoCompetencia.getValue() != "-- Seleccione --")
				if (!txtClasificacion.getValue().isEmpty())
					if (!txtDescripcion.getValue().isEmpty()){
		
						clasificacionCompetencia.setDatoBasico((DatoBasico) cmbTipoCompetencia
								.getSelectedItem().getValue());
						servicioClasificacionCompetencia.agregar(clasificacionCompetencia);
						if (condicionesSeleccionadas.size() > 0) {
							for (int i = 0; i < condicionesSeleccionadas.size(); i++) {
								servicioCondicionCompetencia.agregar(condicionesSeleccionadas
										.get(i));
							}

						}
						for (int j = 0; j < auxCondiciones.size(); j++) {
							auxCondiciones.get(j).setEstatus('E');
							servicioCondicionCompetencia.agregar(auxCondiciones.get(j));
						}
						Messagebox.show("Datos agregados exitosamente", "Mensaje",
								Messagebox.OK, Messagebox.EXCLAMATION);
						restaurar();
						binder.loadAll();
		
		}  else
			throw new WrongValueException(txtDescripcion,"El campo 'Descripción' es obligatorio");
		else
		 throw new WrongValueException(txtClasificacion,"El campo 'Clasificación' es obligatorio");
	else
	 throw new WrongValueException(cmbTipoCompetencia, "Debe seleccionar un 'Tipo de Competencia'");

	}

	/**
	 * ESTE METODO SE USA PARA ELIMINAR LOGICAMENTE LA CLASIFICACION
	 * SELECCIONADA DE LAS RESPECTIVAS TABLAS ASI COMO SUS CONDICIONES ASOCIADAS
	 */
	public void onClick$btnEliminar() throws InterruptedException {
		if (clasificacionBuscada == false)
			throw new WrongValueException(btnBuscar,
					" Debe seleccionar una 'Clasificación'");
		
		if (Messagebox.show("¿Realmente desea eliminar esta Clasificación?",
				"Mensaje", Messagebox.YES + Messagebox.NO, Messagebox.QUESTION) == Messagebox.YES) {

			int codigo = clasificacionCompetencia
					.getCodigoClasificacionCompetencia();
			for (int j = 0; j < condicionesSeleccionadas.size(); j++) {

				if (codigo == condicionesSeleccionadas.get(j)
						.getClasificacionCompetencia()
						.getCodigoClasificacionCompetencia())

					servicioCondicionCompetencia
							.eliminar(condicionesSeleccionadas.get(j));
			}
			servicioClasificacionCompetencia.eliminar(clasificacionCompetencia);

			restaurar();
			binder.loadAll();
			Messagebox.show("Datos eliminados exitosamente", "Mensaje",
					Messagebox.OK, Messagebox.EXCLAMATION);
		}
	}

	/** ESTE METODO SE USA PARA LIMPIAR LOS CAMPOS */
	public void onClick$btnCancelar() {
		apagar();
		restaurar();
		binder.loadAll();
	}

	/** ESTE METODO SE USA PARA SALIR DE LA PANTALLA ACTUAL */
	public void onClick$btnSalir() throws InterruptedException {
		if (Messagebox.show("                  ¿Desea salir?", "Mensaje",
				Messagebox.YES + Messagebox.NO, Messagebox.QUESTION) == Messagebox.YES)
			formulario.detach();

	}

	/** GETTERS AND SETTERS */
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
