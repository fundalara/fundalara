package controlador.logistica;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import modelo.DatoBasico;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zkplus.databind.BindingListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Textbox;

import servicio.interfaz.IServicioComisionFamiliar;
import servicio.interfaz.IServicioDatoBasico;

public class CntrlCatalogoComisiones extends GenericForwardComposer {

	AnnotateDataBinder binder;

	DatoBasico datoBasico = new DatoBasico();
	DatoBasico aux = new DatoBasico();
	IServicioDatoBasico servicioDatoBasico;
	IServicioComisionFamiliar servicioComisionFamiliar;

	List<DatoBasico> listadoComisiones = new ArrayList<DatoBasico>();
	List<DatoBasico> listado = new ArrayList<DatoBasico>();
	List<DatoBasico> auxListadoComisiones = new ArrayList<DatoBasico>();
	List<DatoBasico> todas = new ArrayList<DatoBasico>();

	Component CatalogoComision;
	Component frmPadre;
	Listbox lboxListadoComisiones;
	Textbox txtNombre;

	/**
	 * El metodo doAfterCompose se encarga de enviar las acciones,metodos y
	 * eventos desde el controlador java hasta el componente Zk
	 * 
	 * @param comp
	 * @exception super
	 *                .doAfterCompose(comp)
	 */
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		comp.setVariable("cntrl", this, false);

		CatalogoComision = comp;
	}

	/**
	 * El metodo onCreate$catalogoComisiones() se ejecuta cuando se esta creando
	 * el formulario, el carga la lista con las comisiones que tiene
	 * representantes
	 */

	public void onCreate$catalogoComisiones() {

		auxListadoComisiones = (List<DatoBasico>) CatalogoComision.getVariable("comision", true);
		List<DatoBasico> tieneRepresentante = new ArrayList<DatoBasico>();
		todas = servicioDatoBasico.listarComisiones();

		for (DatoBasico db : todas) {
			if (servicioComisionFamiliar.listarRepresentantesPorComision(db).size() != 0) {
				tieneRepresentante.add(db);
			}
		}

		if (auxListadoComisiones != null) {
			for (DatoBasico datoBasico : tieneRepresentante) {
				if (!auxListadoComisiones.contains(datoBasico)) {
					listadoComisiones.add(datoBasico);

				}
			}
		} else {
			listadoComisiones = tieneRepresentante;
		}
		binder.loadAll();
	}

	/**
	 * El metodo: onClick$btnGuardar() se ejecuta cuando se selecciona un
	 * material con su respectiva cantidad, retorna el material al formulario
	 * que lo ha llamado
	 */
	public void onClick$btnAceptar() {
		Component frmPadre = (Component) this.CatalogoComision.getVariable("frmPadre", false);
		for (int i = 0; i < lboxListadoComisiones.getModel().getSize(); i++) {
			if (lboxListadoComisiones.getItemAtIndex(i).isSelected()) {
				aux = (DatoBasico) lboxListadoComisiones.getItemAtIndex(i).getValue();
				listado.add(aux);

			}
		}
		frmPadre.setVariable("listaComision", listado, false);
		Events.sendEvent(new Event("onCatalogoComisionCerrado", frmPadre));
		this.CatalogoComision.detach();
	}

	/**
	 * El metodo: onClick$btnSalir() se ejecuta cuando se le da click al boton
	 * salir, cierra el formulario
	 */
	public void onClick$btnSalir() {
		this.CatalogoComision.detach();
	}

	public DatoBasico getDatoBasico() {
		return datoBasico;
	}

	public void setDatoBasico(DatoBasico datoBasico) {
		this.datoBasico = datoBasico;
	}

	public List<DatoBasico> getListadoComisiones() {
		return listadoComisiones;
	}

	public void setListadoComisiones(List<DatoBasico> listadoComisiones) {
		this.listadoComisiones = listadoComisiones;
	}

	public Component getCatalogoComision() {
		return CatalogoComision;
	}

	public void setCatalogoComision(Component catalogoComision) {
		CatalogoComision = catalogoComision;
	}

	public Component getFrmPadre() {
		return frmPadre;
	}

	public void setFrmPadre(Component frmPadre) {
		this.frmPadre = frmPadre;
	}

	public List<DatoBasico> getAuxListadoComisiones() {
		return auxListadoComisiones;
	}

	public void setAuxListadoComisiones(List<DatoBasico> auxListadoComisiones) {
		this.auxListadoComisiones = auxListadoComisiones;
	}

	public void onChanging$txtNombre(Event event) throws InterruptedException {
		lboxListadoComisiones.setModel(new BindingListModelList(this.filtrar2(txtNombre.getText()), false));
	}

	public List<DatoBasico> filtrar2(String descripcion) {
		List<DatoBasico> filtrados = new ArrayList<DatoBasico>();
		for (Iterator<DatoBasico> i = listadoComisiones.iterator(); i.hasNext();) {
			DatoBasico tmp = i.next();
			if (tmp.getNombre().toLowerCase().indexOf(descripcion.trim().toLowerCase()) >= 0) {
				filtrados.add(tmp);
			}
		}
		return filtrados;
	}

}
