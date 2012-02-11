package controlador.logistica;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.UiException;

import modelo.Material;
import modelo.RecepcionMaterial;
import modelo.RecepcionMaterialId;
import modelo.TipoDato;
import modelo.DatoBasico;
import servicio.interfaz.IServicioMaterial;
import servicio.interfaz.IServicioNotaEntrega;
import servicio.interfaz.IServicioTipoDato;
import servicio.interfaz.IServicioDatoBasico;
import servicio.implementacion.ServicioRecepcionMaterial;

import org.jfree.text.TextBox;
import org.jfree.ui.Spinner;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import modelo.Material;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;

import modelo.NotaEntrega;

import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Messagebox;

import org.zkoss.zul.Window;

//import com.sun.org.apache.bcel.internal.generic.NEW;

import comun.TipoDatoBasico;

import servicio.interfaz.IServicioRecepcionMaterial;

public class CntrlRecepcionMaterial extends GenericForwardComposer {
	
	AnnotateDataBinder binder;

	RecepcionMaterial recepcionMaterial = new RecepcionMaterial();
	TipoDato tipoDato = new TipoDato();
	DatoBasico tipoDatoBasico = new DatoBasico();
	Material tipoMaterial = new Material();
	NotaEntrega notaE = new NotaEntrega();
	DatoBasico clasificacionMaterial = new DatoBasico();

	List<RecepcionMaterial> recepcionMateriales;
	List<TipoDato> tipoDatos;
	List<DatoBasico> claseMateriales;
	List<DatoBasico> tipoMateriales;
	List<Material> Materiales;
	List<NotaEntrega> notaEntregas;
	List<RecepcionMaterial> materialesPrevios = new ArrayList<RecepcionMaterial>();

	BeanFactory beanFactory;
	IServicioRecepcionMaterial rm;
	IServicioTipoDato tp;
	IServicioDatoBasico cm;
	IServicioMaterial mt;
	IServicioNotaEntrega nt;
	private Window wnRecepcion;
	private IServicioDatoBasico servicioDatoBasico;
	
	org.zkoss.zul.Spinner spCantidad;
	Combobox cmbTipoDato;
	Datebox fechaRecepcion;
	


	public org.zkoss.zul.Spinner getSpCantidad() {
		return spCantidad;
	}

	public void setSpCantidad(org.zkoss.zul.Spinner spCantidad) {
		this.spCantidad = spCantidad;
	}
	
	public Datebox getFechaRecepcion() {
		return fechaRecepcion;
	}

	public void setFechaRecepcion(Datebox fechaRecepcion) {
		this.fechaRecepcion = fechaRecepcion;
	}

	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);

		comp.setVariable("cntrl", this, true);
		beanFactory = new ClassPathXmlApplicationContext(
				"ApplicationContext.xml");
		
		rm = (IServicioRecepcionMaterial) beanFactory
				.getBean("servicioRecepcionMaterial");
		recepcionMateriales = rm.listarMateriales();
		
		

		tp = (IServicioTipoDato) beanFactory.getBean("servicioTipoDato");
		tipoDatos = tp.listarTipoDatos();

		cm = (IServicioDatoBasico) beanFactory.getBean("servicioDatoBasico");
		// claseMateriales = cm.listarDatoBasico();

		mt = (IServicioMaterial) beanFactory.getBean("servicioMaterial");
		// Materiales = mt.listarActivos();

		nt = (IServicioNotaEntrega) beanFactory.getBean("servicioNotaEntrega");
		notaEntregas = nt.listar();

	}

	public DatoBasico getClasificacionMaterial() {
		return clasificacionMaterial;
	}

	public void setClasificacionMaterial(DatoBasico clasificacionMaterial) {
		this.clasificacionMaterial = clasificacionMaterial;
	}

	public NotaEntrega getNotaE() {
		return notaE;
	}

	public void setNotaE(NotaEntrega notaE) {
		this.notaE = notaE;
	}

	public List<NotaEntrega> getNotaEntregas() {
		return notaEntregas;
	}

	public void setNotaEntregas(List<NotaEntrega> notaEntregas) {
		this.notaEntregas = notaEntregas;
	}

	public List<DatoBasico> getTipoMateriales() {
		tipoMateriales = cm.buscar(TipoDatoBasico.TIPOS_MATERIALES);
		return tipoMateriales;
	}

	public void setTipoMateriales(List<DatoBasico> tipoMateriales) {
		this.tipoMateriales = tipoMateriales;
	}

	public DatoBasico getTipoDatoBasico() {
		return tipoDatoBasico;
	}

	public void setTipoDatoBasico(DatoBasico tipoDatoBasico) {
		this.tipoDatoBasico = tipoDatoBasico;
	}

	public List<DatoBasico> getClaseMateriales() {
		// claseMateriales =
		// cm.buscar(TipoDatoBasico.CLASIFICACIONES_MATERIALES);
		return claseMateriales;
	}

	public void setClaseMateriales(List<DatoBasico> claseMateriales) {
		this.claseMateriales = claseMateriales;
	}

	public Material getTipoMaterial() {
		return tipoMaterial;
	}

	public void setTipoMaterial(Material tipoMaterial) {
		this.tipoMaterial = tipoMaterial;
	}

	public List<Material> getMateriales() {

		return Materiales;
	}

	public void setMateriales(List<Material> materiales) {
		Materiales = materiales;
	}

	public RecepcionMaterial getRecepcionMaterial() {

		return recepcionMaterial;
	}

	public void setRecepcionMaterial(RecepcionMaterial recepcionMaterial) {
		this.recepcionMaterial = recepcionMaterial;
	}

	public List<RecepcionMaterial> getRecepcionMateriales() {
		recepcionMateriales = rm.listarMateriales();
		return recepcionMateriales;
	}

	public void setRecepcionMateriales(
			List<RecepcionMaterial> recepcionMateriales) {
		this.recepcionMateriales = recepcionMateriales;
	}

	public List<TipoDato> getTipoDatos() {
		return tipoDatos;
	}

	public void setTipoDatos(List<TipoDato> tipoDatos) {
		this.tipoDatos = tipoDatos;
	}

	public void onSelect$cmbTD() {
		// System.out.println(tipoDatoBasico.getNombre());
		claseMateriales = cm.buscarDatosPorRelacion(tipoDatoBasico);

	}

	public void onSelect$cmbCM() {
		// alert(String.valueOf(tipoMaterial.getCodigoMaterial())) ;

		Materiales = mt.buscarDatosPorRelacion(clasificacionMaterial);
		// alert(String.valueOf(Materiales.size()));
		// for (int i= 0;i < Materiales.size();i++ ){
		//
		// alert(String.valueOf(Materiales.get(i).getDescripcion())) ;
		//
		// }
		//
	}

	public void onClick$btnAgregar() {

		recepcionMaterial = new RecepcionMaterial();
		RecepcionMaterialId rmid = new RecepcionMaterialId();
		rmid.setCodigoMaterial(tipoMaterial.getCodigoMaterial());
		// rmid.setCodigoNotaEntrega(12);
		recepcionMaterial.setEstatus('A');
		
		recepcionMaterial.setId(rmid);
		recepcionMaterial.setObservaciones(((Combobox) wnRecepcion.getVariable(
				"cmbDM", false)).getText());
		recepcionMaterial
				.setCantidadRecibida(((org.zkoss.zul.Spinner) wnRecepcion
						.getVariable("spCantidad", false)).getValue());
		recepcionMaterial.setMaterial(tipoMaterial);
		materialesPrevios.add(recepcionMaterial);
		// rm.agregar(recepcionMaterial);
		
		this.onClick$btnCancelar();

	}

	public List<RecepcionMaterial> getMaterialesPrevios() {
		return materialesPrevios;
	}

	public void setMaterialesPrevios(List<RecepcionMaterial> materialesPrevios) {
		this.materialesPrevios = materialesPrevios;
	}

	public void onClick$btnGuardar() throws InterruptedException {
		
		validar();

		notaE = new NotaEntrega();
		notaE.setCodigoNotaEntrega(nt.devolverUltimo() + 1);
		notaE.setCuentaPagar(null);
		notaE.setEstatus('A');
		notaE.setFechaRecepcion(((Datebox) wnRecepcion.getVariable(
				"fechaRecepcion", false)).getValue());
		nt.agregar(notaE);
		notaE.setCodigoNotaEntrega(nt.devolverUltimo());

		//System.out.println(this.materialesPrevios.size());
		// for (int i = 0; i < materialesPrevios.size(); i++) {
		// System.out.println(i);
		// materialesPrevios.get(i).setNotaEntrega(notaE);
		// materialesPrevios.get(i).getId()
		// .setCodigoNotaEntrega(notaE.getCodigoNotaEntrega());
		// alert(String.valueOf(materialesPrevios.get(i).getNotaEntrega()
		// .getCodigoNotaEntrega()));
		// //
		// alert(String.valueOf(materialesPrevios.get(i).getMaterial().getCodigoMaterial()));
		// //
		// alert(String.valueOf(materialesPrevios.get(i).getId().getCodigoNotaEntrega()));
		// //
		// alert(String.valueOf(materialesPrevios.get(i).getId().getCodigoMaterial()));
		// //
		// alert(String.valueOf(materialesPrevios.get(i).getObservaciones()));
		// //
		// alert(String.valueOf(materialesPrevios.get(i).getCantidadRecibida()));
		// // alert(String.valueOf(materialesPrevios.get(i).getEstatus()));
		// rm.agregar(materialesPrevios.get(i));
		// }
		if (materialesPrevios.size() == 1) {
			RecepcionMaterial rem = materialesPrevios.get(0);
			rem.setNotaEntrega(notaE);
			rem.getId().setCodigoNotaEntrega(notaE.getCodigoNotaEntrega());
			rm.agregar(rem);
			rm.actualizar(rem);
			
			
		} else {
			for (Iterator<RecepcionMaterial> iterator = materialesPrevios.iterator(); iterator
					.hasNext();) {

				
				// rem = new RecepcionMaterial();
				 RecepcionMaterial rem = (RecepcionMaterial) iterator.next();
				rem.setNotaEntrega(notaE);
				rem.getId().setCodigoNotaEntrega(notaE.getCodigoNotaEntrega());

				rm.agregar(rem);
				
			}
			
			
		}
		this.materialesPrevios.clear();
		this.onClick$btnCancelar();
		
		Messagebox.show("Datos agregados exitosamente","Mensaje",Messagebox.OK,Messagebox.EXCLAMATION);
	}
	
	public int onSelect$lboxRecepcionMateriales() throws InterruptedException{
		
		int codigo = recepcionMaterial.getId().getCodigoMaterial();
		
		return codigo;
		

	}
	
	public void onClick$btnQuitar() throws InterruptedException{ 
		
		
		int c = onSelect$lboxRecepcionMateriales();
		//System.out.println(c);
		for(int i = 0;i<materialesPrevios.size();i++)
			
		{
			//System.out.println(materialesPrevios.size());
			if(materialesPrevios.get(i).getId().getCodigoMaterial()==c){
				
				materialesPrevios.remove(i);
				//System.out.println(materialesPrevios.size());
			}
			
		}
		
	}
	public void onClick$btnCancelar(){		
		
		claseMateriales = new ArrayList<DatoBasico>();
		tipoMateriales = new ArrayList<DatoBasico>();
		Materiales = new ArrayList<Material>();
		spCantidad.setValue(0);
		
		binder.loadAll();		
		
	}
	
	public void validar()
	{
		spCantidad.getValue();
		fechaRecepcion.getValue();
		
	}
	

	
}