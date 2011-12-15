package controlador.logistica;

import java.util.HashSet;

import java.util.List;

import modelo.ClaseMantenimiento;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.metainfo.ComponentInfo;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;
import servicio.logistica.IServicioClaseMantenimiento;

public class ClaseMantenimientoControlador extends GenericForwardComposer {

	private static final long serialVersionUID = 1L;
	private Window clasificacionMantenimiento;
	private Listbox lista;

	public List<ClaseMantenimiento> getClasesMantenimientos() {

		List<ClaseMantenimiento> cm;

		BeanFactory bf = (BeanFactory) new ClassPathXmlApplicationContext(
				"ApplicationContext.xml");
		IServicioClaseMantenimiento servicioClaseMantenimiento = (IServicioClaseMantenimiento) bf
				.getBean("servicioClaseMantenimiento");

		cm = (List<ClaseMantenimiento>) servicioClaseMantenimiento.listar();
		return cm;
	}

	@Override
	public ComponentInfo doBeforeCompose(Page page, Component parent,
			ComponentInfo compInfo) {

		return super.doBeforeCompose(page, parent, compInfo);
	}

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
	}

	public void onClick$btnGuardar() {
		BeanFactory bf = (BeanFactory) new ClassPathXmlApplicationContext(
				"ApplicationContext.xml");
		IServicioClaseMantenimiento servicioClaseMantenimiento = (IServicioClaseMantenimiento) bf
				.getBean("servicioClaseMantenimiento");

		ClaseMantenimiento cm;

		if (lista.getSelectedItem() == null) {
			cm = new ClaseMantenimiento();
			cm.setCodigoClaseMantenimiento(servicioClaseMantenimiento
					.generarCodigo());
		} else
			cm = (ClaseMantenimiento) lista.getSelectedItem().getValue();

		cm.setDescripcion(((Textbox) clasificacionMantenimiento.getVariable(
				"txtNombre", false)).getText());
		cm.setEstatus('A');

		servicioClaseMantenimiento.guardar(cm);
	}

	public void onClick$btnEliminar() {

		if (lista.getSelectedItem() == null) {
			return;
		}

		BeanFactory bf = (BeanFactory) new ClassPathXmlApplicationContext(
				"ApplicationContext.xml");
		IServicioClaseMantenimiento servicioClaseMantenimiento = (IServicioClaseMantenimiento) bf
				.getBean("servicioClaseMantenimiento");

		ClaseMantenimiento cm = (ClaseMantenimiento) lista.getSelectedItem()
				.getValue();

		servicioClaseMantenimiento.eliminar(cm);
	}

	public void onSelect$lista() {

		if (lista.getSelectedItem() == null) {
			return;
		}

	}

}
