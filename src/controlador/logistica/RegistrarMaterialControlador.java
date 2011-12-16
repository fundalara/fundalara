package controlador.logistica;

import java.util.List;

import modelo.ClaseMaterial;
import modelo.Material;
import modelo.TipoMaterial;
import modelo.UnidadMedida;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Window;

import servicio.logistica.IServicioClaseMaterial;
import servicio.logistica.IServicioMaterial;
import servicio.logistica.IServicioTipoMaterial;
import servicio.logistica.IServicioUnidadMedida;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class RegistrarMaterialControlador extends GenericForwardComposer {

	Material material = new Material();
	ClaseMaterial claseMaterial = new ClaseMaterial();
	
	BeanFactory beanFactory;
	IServicioClaseMaterial scm;
	IServicioUnidadMedida sum;
	IServicioTipoMaterial stm;
	IServicioMaterial sm;
	
	List<ClaseMaterial> clasesMateriales;
	List<TipoMaterial> tiposMateriales;
	List<UnidadMedida> unidadesMedida;
	List<Material> materiales;	
	
	Combobox cmbTM;
	Combobox cmbCM;
	Window frmRM;
	
	public void doAfterCompose(Component comp)throws Exception{
		super.doAfterCompose(comp);
		
		beanFactory = new ClassPathXmlApplicationContext("ApplicationContext.xml");
		
		scm = (IServicioClaseMaterial) beanFactory.getBean("servicioClaseMaterial");
		clasesMateriales = scm.listarClasesMateriales();
		
		stm = (IServicioTipoMaterial) beanFactory.getBean("servicioTipoMaterial");
		tiposMateriales = stm.listarTiposMateriales();
		
		sum = (IServicioUnidadMedida) beanFactory.getBean("servicioUnidadMedida");
		unidadesMedida = sum.listarUnidadesMedida();

		sm = (IServicioMaterial) beanFactory.getBean("servicioMaterial");
		
	}
	
	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
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

	public List<Material> getMateriales() {		
		materiales = sm.listarMateriales();
		return materiales;
	}

	public void setMateriales(List<Material> materiales) {
		this.materiales = materiales;
	}
	
	public ClaseMaterial getClaseMaterial() {
		return claseMaterial;
	}

	public void setClaseMaterial(ClaseMaterial claseMaterial) {
		this.claseMaterial = claseMaterial;
	}

	public void filtrarTipos(String codClase){
		tiposMateriales = stm.filtrarTiposPorClases("TipoMaterial", "claseMaterial", codClase);									
	}
	
	public void onSelect$cmbCM(){
		this.filtrarTipos(claseMaterial.getCodigoClaseMaterial());
	}
	
	public void onClick$btnRegistrar(){
		
		String codigo = sm.generarCodigo();		
		
		material.setCodigoMaterial(codigo);	
		material.setCantidadDisponible(material.getCantidadExistencia());
		material.setEstatus('A');
		
		sm.agregar(material);
		
		this.onClick$btnCancelar();
		
	}
	
	public void onClick$btnCancelar(){
		material = new Material();
		claseMaterial = new ClaseMaterial();

	}
	
	public void onSelect$lista() {
		claseMaterial = material.getTipoMaterial().getClaseMaterial();
	}
	
	
	public void onClick$btnModificar(){		
		sm.actualizar(material);
		this.onClick$btnCancelar();
	}
	
	public void onClick$btnEliminar(){
		material.setEstatus('E');
		sm.actualizar(material);
		this.onClick$btnCancelar();
	}
	
}
