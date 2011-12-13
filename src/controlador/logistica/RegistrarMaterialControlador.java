package controlador.logistica;

import java.util.List;

import modelo.ClaseMaterial;
import modelo.Material;
import modelo.TipoMaterial;
import modelo.UnidadMedida;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.SimpleListModel;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import servicio.logistica.IServicioClaseMaterial;
import servicio.logistica.IServicioMaterial;
import servicio.logistica.IServicioTipoMaterial;
import servicio.logistica.IServicioUnidadMedida;
import servicio.logistica.ServicioClaseMaterial;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class RegistrarMaterialControlador extends GenericForwardComposer {

	Window frmRM;
	List<ClaseMaterial> clasesMateriales;
	List<TipoMaterial> tiposMateriales;
	List<UnidadMedida> unidadesMedida;
	ServicioClaseMaterial servicioClaseMaterial;
	BeanFactory beanFactory;
	Combobox cmbCM, cmbTM, cmbUM;
	Textbox txtDescripcion;
	Intbox txtCantidad, txtPresentacion, txtStockMinimo, txtStockMaximo;
	Checkbox checkReutilizable;	
	Comboitem cmbTipo, cmbUnidad;
	Listbox lista;
	
	public void doAfterCompose(Component comp)throws Exception{
		super.doAfterCompose(comp);
		
		beanFactory = new ClassPathXmlApplicationContext("ApplicationContext.xml");
		
		IServicioClaseMaterial scm = (IServicioClaseMaterial) beanFactory.getBean("servicioClaseMaterial");
		clasesMateriales = scm.listarClasesMateriales();
		
		IServicioUnidadMedida sum = (IServicioUnidadMedida) beanFactory.getBean("servicioUnidadMedida");
		unidadesMedida = sum.listarUnidadesMedida();
		
		IServicioTipoMaterial stm = (IServicioTipoMaterial) beanFactory.getBean("servicioTipoMaterial");

		setTiposMateriales(stm.listarTiposMateriales());

	}
	
	public void filtrarTipos(String codClase){
		IServicioTipoMaterial stm = (IServicioTipoMaterial) beanFactory.getBean("servicioTipoMaterial");
		tiposMateriales = stm.filtrarTiposPorClases("TipoMaterial", "claseMaterial", codClase);							
		cmbTM.setModel(new SimpleListModel(tiposMateriales));		
	}
	
	public void onSelect$cmbCM(){
		String codClase = clasesMateriales.get(cmbCM.getSelectedIndex()).getCodigoClaseMaterial();
		this.filtrarTipos(codClase);
	}
	
	
	public List<ClaseMaterial> getClasesMateriales() {
		return clasesMateriales;
	}

	public void setClasesMateriales(List<ClaseMaterial> clasesMateriales) {
		this.clasesMateriales = clasesMateriales;
	}

	public List<TipoMaterial> getTiposMateriales() {
		return tiposMateriales;
	}

	public void setTiposMateriales(List<TipoMaterial> tiposMateriales) {
		this.tiposMateriales = tiposMateriales;
	}				
	
	public List<UnidadMedida> getUnidadesMedida() {
		return unidadesMedida;
	}

	public void setUnidadesMedida(List<UnidadMedida> unidadesMedida) {
		this.unidadesMedida = unidadesMedida;
	}

	public void onClick$btnSalir() {
		frmRM.detach();
	}
	
	public void onClick$btnGuardar(){
		Material m = new Material();
		m.setCodigoMaterial("2");
		m.setDescripcion(txtDescripcion.getValue());
		m.setCantidadExistencia(txtCantidad.getValue());		
		m.setReutilizable(checkReutilizable.isChecked());
		m.setStockMinimo(txtStockMinimo.getValue());
		m.setStockMaximo(txtStockMaximo.getValue());
		m.setCantidadDisponible(m.getCantidadExistencia());
		m.setCantidadPresentacion(txtPresentacion.getValue());
		m.setEstatus('A');				
		
		m.setTipoMaterial(tiposMateriales.get(cmbTM.getSelectedIndex()));
		
		m.setUnidadMedida(unidadesMedida.get(cmbUM.getSelectedIndex()));
		
		IServicioMaterial sm = (IServicioMaterial) beanFactory.getBean("servicioMaterial");
		sm.agregar(m);
		
	}
	
	public void onSelect$lista() {
		
		System.out.println("entro1");
		
		if (lista.getSelectedItem() == null) {
			return;
		}		
		
		Material m = (Material) lista.getSelectedItem().getValue();
		
		
		for (int i=0;i<cmbTM.getModel().getSize();i++) {
			System.out.println(((TipoMaterial) cmbTM.getModel().getElementAt(i)).getDescripcion());
			if (((TipoMaterial) cmbTM.getModel().getElementAt(i)).getDescripcion().equals(m.getTipoMaterial().getDescripcion())){
				System.out.println("entro");
				cmbTM.setSelectedIndex(i);
			}
			
			
			
		}
	}
	
	public List<Material> getMateriales() {

		List<Material> materiales;
		

		BeanFactory bf = (BeanFactory) new ClassPathXmlApplicationContext(
				"ApplicationContext.xml");
		IServicioMaterial servicioMaterial = (IServicioMaterial) bf
				.getBean("servicioMaterial");

		materiales = (List<Material>) servicioMaterial.listarMateriales();
		return materiales;
	}
}
