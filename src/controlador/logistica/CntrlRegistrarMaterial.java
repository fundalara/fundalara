package controlador.logistica;

import java.util.List;

import modelo.DatoBasico;
import modelo.Material;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zkplus.databind.BindingListModelList;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Spinner;
import org.zkoss.zul.Textbox;

import servicio.interfaz.IServicioDatoBasico;
import servicio.interfaz.IServicioMaterial;

import comun.TipoDatoBasico;

/**
 * Clase controladora de los eventos de la vista de igual nombre y manejo de los
 * servicios de datos para el registro de nuevos materiales
 * 
 * @author Reinaldo L.
 * 
 * */
public class CntrlRegistrarMaterial extends GenericForwardComposer {

	private Material material = new Material();	
	private DatoBasico tipoMaterial = new DatoBasico();
	
	private IServicioDatoBasico servicioDatoBasico;
	private IServicioMaterial servicioMaterial;
	
	private List<DatoBasico> tiposMateriales;
	private List<DatoBasico> clasificacionesMateriales;
	private List<DatoBasico> unidadesMedida;
	private List<Material> materiales;	
	
	private Combobox cmbTipos;
	private Combobox cmbClasificaciones;
	private Combobox cmbUnidades;
	private Textbox txtDescripcion;
	private Spinner spExistencia;
	
	AnnotateDataBinder binder;
	
	public void doAfterCompose(Component comp)throws Exception{
		super.doAfterCompose(comp);		
		comp.setVariable("cntrl", this, false);
		clasificacionesMateriales = servicioDatoBasico.buscar(TipoDatoBasico.CLASIFICACIONES_MATERIALES);
	}
			
	public List<DatoBasico> getClasificacionesMateriales() {	
		return clasificacionesMateriales;
	}

	public void setClasificacionesMateriales(
			List<DatoBasico> clasificacionesMateriales) {
		this.clasificacionesMateriales = clasificacionesMateriales;
	}

	public DatoBasico getTipoMaterial() {
		return tipoMaterial;
	}

	public void setTipoMaterial(DatoBasico tipoMaterial) {
		this.tipoMaterial = tipoMaterial;
	}

	public List<DatoBasico> getTiposMateriales() {
		tiposMateriales = servicioDatoBasico.buscar(TipoDatoBasico.TIPOS_MATERIALES);
		return tiposMateriales;
	}

	public void setTiposMateriales(List<DatoBasico> tiposMateriales) {
		this.tiposMateriales = tiposMateriales;
	}

	public List<DatoBasico> getUnidadesMedida() {
		unidadesMedida = servicioDatoBasico.buscar(TipoDatoBasico.UNIDADES_MEDIDA);
		return unidadesMedida;
	}

	public void setUnidadesMedida(List<DatoBasico> unidadesMedida) {
		this.unidadesMedida = unidadesMedida;
	}

	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}


	public List<Material> getMateriales() {
		materiales = servicioMaterial.listarActivos();
		return materiales;
	}

	public void setMateriales(List<Material> materiales) {
		this.materiales = materiales;
	}
	
	public void onSelect$cmbTipos(){
		clasificacionesMateriales = servicioDatoBasico.buscarDatosPorRelacion(tipoMaterial);
	}
	
	public void onClick$btnRegistrar() throws InterruptedException{
		
		validar();
			
		material.setCantidadDisponible(material.getCantidadExistencia());
		material.setEstatus('A');
		
		servicioMaterial.agregar(material);
		
		this.onClick$btnCancelar();
		Messagebox.show("Datos agregados exitosamente","Mensaje",Messagebox.OK,Messagebox.EXCLAMATION);
		
	}
	
	public void onClick$btnCancelar(){		
		material = new Material();
		tipoMaterial = new DatoBasico();
		binder.loadAll();		
	}
	
	public void onSelect$lboxMateriales() throws InterruptedException {
		
		DatoBasico clasificacion = material.getDatoBasicoByCodigoTipoMaterial();
		DatoBasico unidadMedida  = material.getDatoBasicoByCodigoUnidadMedida(); 
		tipoMaterial = clasificacion.getDatoBasico();
		
		
		for(int i=0; i<tiposMateriales.size(); i++){
			if(tiposMateriales.get(i).getNombre().equals((tipoMaterial.getNombre()))){
				cmbTipos.setSelectedIndex(i);
				break;
			}
		}
		
		onSelect$cmbTipos();
		cmbClasificaciones.setModel(new BindingListModelList(clasificacionesMateriales, true));
		
		Messagebox.show("Datos cargados exitosamente","Mensaje",Messagebox.OK,Messagebox.EXCLAMATION);
		
		for(int i=0; i<clasificacionesMateriales.size(); i++){
			if(clasificacionesMateriales.get(i).getNombre().equals(clasificacion.getNombre())){				
				cmbClasificaciones.setSelectedIndex(i);
				break;
			}
		}
		
		if(unidadMedida != null){
			for(int i=0; i<unidadesMedida.size(); i++){
				if(unidadesMedida.get(i).getNombre().equals(unidadMedida.getNombre())){				
					cmbUnidades.setSelectedIndex(i);
					break;
				}
			}
		}
	}
	
	
	public void onClick$btnModificar() throws InterruptedException{		
		servicioMaterial.actualizar(material);
		this.onClick$btnCancelar();
		Messagebox.show("Datos modificados exitosamente","Mensaje",Messagebox.OK,Messagebox.EXCLAMATION);
	}
	
	public void onClick$btnEliminar() throws InterruptedException{
		if(Messagebox.show("¿Realmente desea eliminar este material?","Mensaje",Messagebox.YES+Messagebox.NO,Messagebox.QUESTION) == Messagebox.YES){
			material.setEstatus('E');
			servicioMaterial.actualizar(material);
			this.onClick$btnCancelar();
			Messagebox.show("Datos eliminados exitosamente","Mensaje",Messagebox.OK,Messagebox.EXCLAMATION);
		}else{
			onClick$btnCancelar();
		}
	}
	
	public void validar(){
		cmbTipos.getValue();
		cmbClasificaciones.getValue();
		txtDescripcion.getValue();
		spExistencia.getValue();
	}
	
	
}
