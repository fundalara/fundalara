package controlador.administracion;


import java.util.ArrayList;
import java.util.List;


import modelo.DatoBasico;
import modelo.TipoIngreso;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Doublebox;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Radio;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;
import org.zkoss.zul.Button;

import servicio.implementacion.ServicioTipoIngreso;
import servicio.implementacion.ServicioDatoBasico;
import servicio.implementacion.ServicioTipoDato;

public class CntrlActualizarCompromisos extends GenericForwardComposer  {
	Window frmActualizarConceptoNomina;
	ServicioTipoIngreso servicioTipoIngreso;
	ServicioDatoBasico servicioDatoBasico;
	ServicioTipoDato servicioTipoDato;
	AnnotateDataBinder binder;
	Textbox txtDescripcion;
	Doublebox dbxPrecio;
	Listbox lbxTipoIngresos;
	Button btnRegistrar, btnSalir, btnAgregar, btnQuitar, btnCancelar;
	Combobox cmbPeriodicidad, cmbTipoIngreso;
	DatoBasico tipoIngresoCombo, periodicidad;
	Radio rdoNo,rdoSi;
	List<DatoBasico> tipoIngresoCombos, periodicidades= new ArrayList<DatoBasico>();
	
	List<TipoIngreso> listaTipoIngresos= new ArrayList<TipoIngreso>();
	List<TipoIngreso> listaAuxTipoIngresos= new ArrayList<TipoIngreso>();
	TipoIngreso tipoIngreso=new TipoIngreso();
	Boolean flag = false;
	int indice = 0;
	
	// ---------------------------------------------------------------------------------------------------
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		comp.setVariable("cntrl", this, true);
		listaTipoIngresos=servicioTipoIngreso.listarActivos();
		periodicidades=servicioDatoBasico.listarPorTipoDato("PERIODICIDAD");
		tipoIngresoCombos=servicioDatoBasico.listarPorTipoDato("CLASIFICACION DE INGRESO");
		
		
	}
	// ---------------------------------------------------------------------------------------------------
//	public void actualizarTipoIngreso() {
//		//cuidado si cambian el codigo
//		tipoIngreso.setCodigoTipoIngreso(servicioTipoIngreso.listar().size()+1);
//		tipoIngreso.setDescripcion(txtDescripcion.getValue().toUpperCase());
//		tipoIngreso.setEstatus('A');
//		tipoIngreso.setMonto(dbxPrecio.getValue());
//	}
	// ---------------------------------------------------------------------------------------------------
	public void onClick$btnRegistrar() {
		try {
			Integer qs = Messagebox.show("¿Desea guardar los cambios?",
					"Importante", Messagebox.OK | Messagebox.CANCEL,
					Messagebox.QUESTION);
			if (qs.equals(1)) {
				for (int i = 0; i < listaTipoIngresos.size(); i++) {
					Integer a = listaTipoIngresos.get(i).getCodigoTipoIngreso();
					System.out.println(a);
					if(a==0){
						listaTipoIngresos.get(i).setCodigoTipoIngreso(servicioTipoIngreso.listar().size()+1);
						servicioTipoIngreso.agregar(listaTipoIngresos.get(i));
					}
					else{
						servicioTipoIngreso.agregar(listaTipoIngresos.get(i));
					}
				}
				for (int i = 0; i < listaAuxTipoIngresos.size(); i++) {
					servicioTipoIngreso.agregar(listaAuxTipoIngresos.get(i));
					System.out.println("prueba 2");
				}	
				alert("Registrado con Exito");
				clear();
				listaAuxTipoIngresos = new ArrayList<TipoIngreso>();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
				
	// ---------------------------------------------------------------------------------------------------
	public void onClick$btnQuitar() {
		if (flag == false){
			listaTipoIngresos.get(lbxTipoIngresos.getSelectedIndex()).setEstatus('E');

			listaAuxTipoIngresos.add(listaTipoIngresos.get(lbxTipoIngresos.getSelectedIndex()));
			listaTipoIngresos.remove(lbxTipoIngresos.getSelectedIndex());

			binder.loadComponent(lbxTipoIngresos);
		} else {
			alert("Debe terminar el proceso de edición del Compromiso");
		}
		btnQuitar.setDisabled(true);
	}
	
	
	// ---------------------------------------------------------------------------------------------------
	public void onClick$btnAgregar() {
		
		if(!buscar()){
			if (flag == false){ 
				if (txtDescripcion.getText().trim() == "") {
					alert("Debe indicar una Descripcion o Nombre para el Ingreso");
					txtDescripcion.setFocus(true);
					return;
				} else  {
					tipoIngreso.setCodigoTipoIngreso(servicioTipoIngreso.listar().size()+1);

					tipoIngreso.setDatoBasicoByCodigoPeriodicidad((DatoBasico) cmbPeriodicidad.getSelectedItem().getValue());
					tipoIngreso.setDatoBasicoByCodigoTipo((DatoBasico) cmbTipoIngreso.getSelectedItem().getValue());
					tipoIngreso.setDescripcion(txtDescripcion.getValue().toUpperCase());
					tipoIngreso.setEstatus('A');
					tipoIngreso.setMonto(dbxPrecio.getValue());
					listaTipoIngresos.add(tipoIngreso);
					if(rdoSi.isChecked())
						tipoIngreso.setAplicaRepresentante(rdoSi.isChecked());
					else
						tipoIngreso.setAplicaRepresentante(false);
					clear();
				}
			} else {
				alert("Debe terminar el proceso de edición del Concepto");
			}
			tipoIngreso = new TipoIngreso();
		}else{
			if(lbxTipoIngresos.getSelectedCount()!=0){
			System.out.println("hola");
			tipoIngreso=(TipoIngreso) lbxTipoIngresos.getSelectedItem().getValue();
			tipoIngreso.setMonto(dbxPrecio.getValue());
			tipoIngreso.setDatoBasicoByCodigoPeriodicidad((DatoBasico) cmbPeriodicidad.getSelectedItem().getValue());
			binder.loadComponent(lbxTipoIngresos);
			}else
				alert("Este compromiso ya esta registro");
			
		}
		btnQuitar.setDisabled(true);
	}
	

	// ---------------------------------------------------------------------------------------------------	
	public void onSelect$lbxTipoIngresos(){
		TipoIngreso auxIngreso= new TipoIngreso();
		auxIngreso=(TipoIngreso) lbxTipoIngresos.getSelectedItem().getValue();
		txtDescripcion.setValue(auxIngreso.getDescripcion().toString());
		dbxPrecio.setValue(auxIngreso.getMonto());
		cmbPeriodicidad.setValue(auxIngreso.getDatoBasicoByCodigoPeriodicidad().getNombre());
		cmbTipoIngreso.setValue(auxIngreso.getDatoBasicoByCodigoTipo().getNombre());
		txtDescripcion.setDisabled(false);
		if(auxIngreso.isAplicaRepresentante())
			rdoSi.setChecked(true);
		else
			rdoNo.setChecked(true);
		btnQuitar.setDisabled(false);
		//clear();
		//binder.loadComponent(cmbTipoTalla);
	}
	// ---------------------------------------------------------------------------------------------------
	public void clear() {
		txtDescripcion.setText("");
		dbxPrecio.setText("");
		btnQuitar.setDisabled(true);
		btnAgregar.setDisabled(false);
		cmbPeriodicidad.setText("--Seleccione--");
		cmbTipoIngreso.setText("--Seleccione--");
		rdoNo.setChecked(false);
		rdoSi.setChecked(false);
		binder.loadAll();
	}
	// ---------------------------------------------------------------------------------------------------
	public void onClick$btnCancelar(){
		clear();
	}

	// ---------------------------------------------------------------------------------------------------
	public boolean buscar(){
		int indice=listaTipoIngresos.size();
		int i=0;
		boolean encontrado=false;
		while(i<indice && !encontrado){
			if(txtDescripcion.getValue().equals(listaTipoIngresos.get(i).getDescripcion())){
				//alert(" Este tipo de comprmiso ya existe");
				encontrado=true;
				return encontrado;
			}
			System.out.println("YUJU");
			i++;
		}
		return encontrado;
	}
	//----------------------------------------------------------------------------------------------------
	public TipoIngreso getTipoIngreso() {
		return tipoIngreso;
	}
	public void setTipoIngreso(TipoIngreso tipoIngreso) {
		this.tipoIngreso = tipoIngreso;
	}
	public List<TipoIngreso> getListaTipoIngresos() {
		return listaTipoIngresos;
	}
	public void setListaTipoIngresos(List<TipoIngreso> listaTipoIngresos) {
		this.listaTipoIngresos = listaTipoIngresos;
	}
	public List<DatoBasico> getTipoIngresoCombos() {
		return tipoIngresoCombos;
	}
	public void setTipoIngresoCombos(List<DatoBasico> tipoIngresoCombos) {
		this.tipoIngresoCombos = tipoIngresoCombos;
	}
	public List<DatoBasico> getPeriodicidades() {
		return periodicidades;
	}
	public void setPeriodicidades(List<DatoBasico> periodicidades) {
		this.periodicidades = periodicidades;
	}
	public DatoBasico getPeriodicidad() {
		return periodicidad;
	}
	public void setPeriodicidad(DatoBasico periodicidad) {
		this.periodicidad = periodicidad;
	}
	public DatoBasico getTipoIngresoCombo() {
		return tipoIngresoCombo;
	}
	public void setTipoIngresoCombo(DatoBasico tipoIngresoCombo) {
		this.tipoIngresoCombo = tipoIngresoCombo;
	}
	// ---------------------------------------------------------------------------------------------------



}
