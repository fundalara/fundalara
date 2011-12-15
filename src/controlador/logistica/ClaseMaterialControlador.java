package controlador.logistica;

import java.util.HashSet;
import java.util.List;

import modelo.ClaseMaterial;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.metainfo.ComponentInfo;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.GroupsModel;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.SimpleGroupsModel;
import org.zkoss.zul.SimpleListModel;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;
import org.zkoss.zul.event.GroupsDataListener;

import servicio.logistica.IServicioClaseMaterial;
import servicio.logistica.ServicioClaseMaterial;


public class ClaseMaterialControlador extends GenericForwardComposer {

	private Window clasificacionMaterial;
	private Listbox lista;

	
	public List<ClaseMaterial> getClasesMateriales() {

		List<ClaseMaterial> cm;

		BeanFactory bf = (BeanFactory) new ClassPathXmlApplicationContext(
				"ApplicationContext.xml");
		IServicioClaseMaterial servicioClaseMaterial = (IServicioClaseMaterial) bf
				.getBean("servicioClaseMaterial");

		cm = (List<ClaseMaterial>) servicioClaseMaterial.listarClasesMateriales();
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
		IServicioClaseMaterial servicioClaseMaterial = (IServicioClaseMaterial) bf
				.getBean("servicioClaseMaterial");


		ClaseMaterial cm;

		if (lista.getSelectedItem() == null) {
			cm = new ClaseMaterial();
			cm.setCodigoClaseMaterial(servicioClaseMaterial.generarCodigo());
			System.out.println("hiho");
		} else
			cm = (ClaseMaterial) lista.getSelectedItem().getValue();

		cm.setDescripcion(((Textbox) clasificacionMaterial.getVariable(
				"txtNombre", false)).getText());
		cm.setEstatus('A');

		servicioClaseMaterial.agregar(cm);
		
		lista.setModel( new SimpleListModel( this.getClasesMateriales() ) );
	}

	public void onClick$btnEliminar() {

		if (lista.getSelectedItem() == null) {
			return;
		}

		BeanFactory bf = (BeanFactory) new ClassPathXmlApplicationContext(
				"ApplicationContext.xml");
		IServicioClaseMaterial servicioClaseMaterial = (IServicioClaseMaterial) bf
				.getBean("servicioClaseMaterial");


		ClaseMaterial cm = (ClaseMaterial) lista.getSelectedItem()
				.getValue();

		servicioClaseMaterial.eliminar(cm);
		
		lista.setModel( new SimpleListModel( this.getClasesMateriales() ) );
	}

	public void onSelect$lista() {

		if (lista.getSelectedItem() == null) {
			return;
		}

	}

}
