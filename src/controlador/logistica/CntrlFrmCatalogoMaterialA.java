package controlador.logistica;

import java.util.ArrayList;
import java.util.List;

import modelo.DatoBasico;
import modelo.Material;
import modelo.TipoDato;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;

import servicio.interfaz.IServicioDatoBasico;
import servicio.interfaz.IServicioMaterial;

public class CntrlFrmCatalogoMaterialA extends GenericForwardComposer {

	IServicioMaterial servicioMaterial;
	IServicioDatoBasico servicioDatoBasico;

	DatoBasico claseMaterial;
	DatoBasico claseInstalacion;
	List<DatoBasico> clasificacionMaterial;
	List<DatoBasico> tiposMaterial = new ArrayList<DatoBasico>();

	Material material;
	DatoBasico tipoMaterial;
	List<Material> listaMaterial;

	BeanFactory beanFactory;
	Component catalogoMaterial;
	Component frmPlanificarActividad;
	Listbox lboxMaterial;
	Combobox cmbTipo;
	Combobox cmbClase;
	Intbox txtCantidad;

	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		comp.setVariable("cntrl", this, true);

		catalogoMaterial = comp;
		beanFactory = new ClassPathXmlApplicationContext("ApplicationContext.xml");

		TipoDato td = new TipoDato();

		td.setCodigoTipoDato(106);
		td.setEstatus('A');
		tiposMaterial = servicioDatoBasico.buscarPorTipoDato(td);
	}

	public void onSelect$cmbTipo() {
		clasificacionMaterial = servicioDatoBasico.buscarDatosPorRelacion(tipoMaterial);
	}

	public void onSelect$cmbClase() {
		listaMaterial = servicioMaterial.listarMaterialPorTipo(claseMaterial);
	}

	public void onClick$btnGuardar() throws InterruptedException {

		// Se comprueba que se haya seleccionado un elemento de la lista
		if ((lboxMaterial.getSelectedIndex() != -1)) {

			if ((txtCantidad.getValue() != 0)) {
				// se obtiene la tarea seleccionada
				material = listaMaterial.get(lboxMaterial.getSelectedIndex());

				// se obtiene la referencia del formulario
				Component frmPlanificarActividad = (Component) catalogoMaterial.getVariable("frmPlanificarActividad", false);

				// se le asigna el objeto tarea al formulario
				frmPlanificarActividad.setVariable("material", material, false);

				int cantidad = txtCantidad.intValue();

				frmPlanificarActividad.setVariable("cantidad", cantidad, false);

				// se le envia una señal al formulario indicado que el
				// formulario se cerro y que los datos se han enviado
				Events.sendEvent(new Event("onCatalogoMaterialCerrado", frmPlanificarActividad));

				// se cierra el catalogo
				catalogoMaterial.detach();

			} else {
				Messagebox.show("Seleccione una cantidad ", "Mensaje", Messagebox.YES, Messagebox.INFORMATION);
				txtCantidad.focus();

			}
		} else {
			Messagebox.show("Seleccione un material ", "Mensaje", Messagebox.YES, Messagebox.INFORMATION);

		}

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

	public List<DatoBasico> getTiposMaterial() {
		return tiposMaterial;
	}

	public void setTiposMaterial(List<DatoBasico> tiposMaterial) {
		this.tiposMaterial = tiposMaterial;
	}

	public DatoBasico getTipoMaterial() {
		return tipoMaterial;
	}

	public void setTipoMaterial(DatoBasico tipoMaterial) {
		this.tipoMaterial = tipoMaterial;
	}

	public Combobox getCmbTipo() {
		return cmbTipo;
	}

	public void setCmbTipo(Combobox cmbTipo) {
		this.cmbTipo = cmbTipo;
	}

	public Combobox getCmbClase() {
		return cmbClase;
	}

	public void setCmbClase(Combobox cmbClase) {
		this.cmbClase = cmbClase;
	}

	public Intbox getTxtCantidad() {
		return txtCantidad;
	}

	public void setTxtCantidad(Intbox txtCantidad) {
		this.txtCantidad = txtCantidad;
	}

}
