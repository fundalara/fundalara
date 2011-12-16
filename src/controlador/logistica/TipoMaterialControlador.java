package controlador.logistica;

import java.util.List;

import modelo.ClaseMaterial;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.metainfo.ComponentInfo;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.SimpleListModel;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import servicio.logistica.IServicioClaseMaterial;
import servicio.logistica.IServicioTipoMaterial;
import servicio.logistica.IServicioUnidadMedida;
import modelo.TipoMaterial;
import dao.general.GenericDAO;

public class TipoMaterialControlador extends GenericForwardComposer {

	private Window tipoMaterial;
	private Listbox lista;
	List<TipoMaterial> tiposMateriales;
	List<ClaseMaterial> clasesMateriales;
	Combobox cmbClases;
	
	public List<ClaseMaterial> getClasesMateriales() {

		List<ClaseMaterial> cm;
		

		BeanFactory bf = (BeanFactory) new ClassPathXmlApplicationContext(
				"ApplicationContext.xml");
		IServicioClaseMaterial servicioClaseMaterial = (IServicioClaseMaterial) bf
				.getBean("servicioClaseMaterial");

		clasesMateriales = (List<ClaseMaterial>) servicioClaseMaterial.listarClasesMateriales();
		return clasesMateriales;
	}
	
	public List<TipoMaterial> getTiposMateriales() {

		BeanFactory bf = (BeanFactory) new ClassPathXmlApplicationContext(
				"ApplicationContext.xml");
		IServicioTipoMaterial servicioTipoMaterial = (IServicioTipoMaterial) bf
				.getBean("servicioTipoMaterial");

		tiposMateriales = (List<TipoMaterial>) servicioTipoMaterial.listarTiposMateriales();
		return tiposMateriales;
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
		IServicioTipoMaterial servicioTipoMaterial = (IServicioTipoMaterial) bf
				.getBean("servicioTipoMaterial");


		TipoMaterial tm;

		if (lista.getSelectedItem() == null) {
			tm = new TipoMaterial();
			tm.setCodigoTipoMaterial(servicioTipoMaterial.generarCodigo());
		} else
			tm = (TipoMaterial) lista.getSelectedItem().getValue();

		tm.setDescripcion(((Textbox) tipoMaterial.getVariable(
				"txtNombre", false)).getText());
		tm.setEstatus('A');
		
		if(cmbClases.getSelectedIndex() != -1)
			tm.setClaseMaterial(clasesMateriales.get(cmbClases.getSelectedIndex()));

		servicioTipoMaterial.agregar(tm);
		
		//lista.setModel( new SimpleListModel( this.getTiposMateriales() ) );
		
		cmbClases.setModel(new SimpleListModel( this.getClasesMateriales() ));
	}

	public void onClick$btnEliminar() {

		if (lista.getSelectedItem() == null) {
			return;
		}

		BeanFactory bf = (BeanFactory) new ClassPathXmlApplicationContext(
				"ApplicationContext.xml");
		IServicioTipoMaterial servicioTipoMaterial = (IServicioTipoMaterial) bf
				.getBean("servicioTipoMaterial");


		TipoMaterial tm = (TipoMaterial) lista.getSelectedItem()
				.getValue();

		servicioTipoMaterial.eliminar(tm);
		
		//lista.setModel( new SimpleListModel( this.getTiposMateriales() ) );
		
		cmbClases.setModel(new SimpleListModel( this.getClasesMateriales() ));
	}

	public void onSelect$lista() {

		if (lista.getSelectedItem() == null) {
			return;
		}		
		
		TipoMaterial tm = (TipoMaterial) lista.getSelectedItem().getValue();
		
		BeanFactory bf = (BeanFactory) new ClassPathXmlApplicationContext(
				"ApplicationContext.xml");
		IServicioTipoMaterial servicioTipoMaterial = (IServicioTipoMaterial) bf
				.getBean("servicioTipoMaterial");

		
		for (int i=0;i<cmbClases.getModel().getSize();i++) {
			if (((ClaseMaterial) cmbClases.getModel().getElementAt(i)).getDescripcion().equals(tm.getClaseMaterial().getDescripcion())){
				System.out.println(i);
				cmbClases.setSelectedIndex(i);
				}
		}
			
	}


	
}