package controlador.logistica;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import modelo.DatoBasico;
import modelo.Material;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.BindingListModelList;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import servicio.interfaz.IServicioDatoBasico;
import servicio.interfaz.IServicioMaterial;

import comun.MensajeMostrar;

public class CntrlFrmCatalogoMaterial extends GenericForwardComposer {

	IServicioMaterial servicioMaterial;
	IServicioDatoBasico servicioDatoBasico;

	DatoBasico claseMaterial;
	DatoBasico claseInstalacion;
	List<DatoBasico> clasificacionMaterial;

	Material material;
	DatoBasico tipoMaterial;
	List<Material> listaMaterial;

	Component catalogoMaterial;
	Component frmPlanificarMantenimiento;
	Listbox lboxMaterial;
	Intbox txtCantidad;
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
		comp.setVariable("cntrl", this, true);

		catalogoMaterial = comp;
		DatoBasico db = new DatoBasico();
		db.setCodigoDatoBasico(261);
		db.setEstatus('A');
		clasificacionMaterial = servicioDatoBasico.buscarDatosPorRelacion(db);

	}

	/**
	 * El metodo: onSelect$cmbClase() se ejecuta cuando se selecciona un item en
	 * el ComboBox cmbClase, en el se carga todas los materiales de esa
	 * clasificacion
	 * 
	 */
	public void onSelect$cmbClase() {
		listaMaterial = servicioMaterial.listarMaterialPorTipo(claseMaterial);
	}

	/**
	 * El metodo: onClick$btnGuardar() se ejecuta cuando se selecciona un
	 * material con su respectiva cantidad, retorna el material al formulario
	 * que lo ha llamado
	 */
	public void onClick$btnGuardar() throws InterruptedException {

		// Se comprueba que se haya seleccionado un elemento de la lista
		if ((lboxMaterial.getSelectedIndex() != -1)) {

			if ((txtCantidad.getValue() != 0 || !txtCantidad.getText().isEmpty())) {
				// se obtiene la tarea seleccionada
				material = listaMaterial.get(lboxMaterial.getSelectedIndex());

				// se obtiene la referencia del formulario
				Component frmPlanificarMantenimiento = (Component) catalogoMaterial.getVariable("frmPlanificarMantenimiento", false);

				// se le asigna el objeto tarea al formulario
				frmPlanificarMantenimiento.setVariable("material", material, false);

				int cantidad = txtCantidad.intValue();

				frmPlanificarMantenimiento.setVariable("cantidad", cantidad, false);

				// se le envia una señal al formulario indicado que el
				// formulario se cerro y que los datos se han enviado
				Events.sendEvent(new Event("onCatalogoMaterialCerrado", frmPlanificarMantenimiento));

				// se cierra el catalogo
				catalogoMaterial.detach();

			} else {
				Messagebox.show("Seleccione una cantidad ", MensajeMostrar.TITULO + "Información", Messagebox.YES, Messagebox.INFORMATION);
				txtCantidad.focus();

			}
		} else {
			throw new WrongValueException(txtCantidad, "Escriba la cantidad del material");

		}

	}

	/**
	 * El metodo: onClick$btnSalir() se ejecuta cuando se le da click al boton
	 * salir, cierra el formulario
	 */
	public void onClick$btnSalir() {
		catalogoMaterial.detach();
	}

	public DatoBasico getClaseMaterial() {
		return claseMaterial;
	}

	public void setClaseMaterial(DatoBasico claseMaterial) {
		this.claseMaterial = claseMaterial;
	}

	public List<DatoBasico> getClasificacionMaterial() {
		return clasificacionMaterial;
	}

	public void setClasificacionMaterial(List<DatoBasico> clasificacionMaterial) {
		this.clasificacionMaterial = clasificacionMaterial;
	}

	public List<Material> getListaMaterial() {
		return listaMaterial;
	}

	public void setListaMaterial(List<Material> listaMaterial) {
		this.listaMaterial = listaMaterial;
	}

	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	public Listbox getLboxMaterial() {
		return lboxMaterial;
	}

	public void setLboxMaterial(Listbox lboxMaterial) {
		this.lboxMaterial = lboxMaterial;
	}

	public void onChanging$txtDescripcion(Event event) throws InterruptedException {
		lboxMaterial.setModel(new BindingListModelList(this.filtrarMateriales(txtNombre.getText()), false));
	}

	public List<Material> filtrarMateriales(String descripcion) {
		List<Material> materialesFiltrados = new ArrayList<Material>();
		for (Iterator<Material> i = listaMaterial.iterator(); i.hasNext();) {
			Material tmp = i.next();
			if (tmp.getDescripcion().toLowerCase().indexOf(descripcion.trim().toLowerCase()) >= 0) {
				materialesFiltrados.add(tmp);
			}
		}
		return materialesFiltrados;
	}
}
