package controlador.entrenamiento;

import org.omg.CORBA.PUBLIC_MEMBER;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Timebox;
import org.zkoss.zul.Window;
import java.util.Date;



public class Sesion_Entrenamiento extends GenericForwardComposer{
	Window wSesionEntrenamiento;
	Button btnSalir,btnAgregar1,btnQuitar1,btnAgregar2,btnQuitar2,btnCancelar,btnGuardar,btnImprimir;
	Combobox cmbFase, cmbActividad, cmbMaterial, cmbCantidad;
	Listitem lst2;
	Intbox ibTiempo;
	Listbox lbActividades,lsb;
	Datebox dtbox1,dtbox2;
	
	public void onClick$btnSalir(){
		wSesionEntrenamiento.detach();
	}
	
	public void  onCreate$wSesionEntrenamiento() {
		inicializar();
		System.out.println("");
	}
	
	public void inicializar(){
		cmbFase.setDisabled(true);
		cmbActividad.setDisabled(true);
		ibTiempo.setDisabled(true);
		btnAgregar1.setDisabled(true);
		btnAgregar2.setDisabled(true);
		btnQuitar1.setDisabled(true);
		btnQuitar2.setDisabled(true);
		cmbMaterial.setDisabled(true);
		cmbCantidad.setDisabled(true);
		btnGuardar.setDisabled(true);
		btnImprimir.setDisabled(true);
				
	}
	public void limpiar1() {
		cmbFase.setSelectedIndex(-1);
		cmbFase.setValue(cmbFase.getValue().valueOf("--Seleccione--"));
		cmbActividad.setSelectedIndex(-1);
		cmbActividad.setValue(cmbFase.getValue().valueOf("--Seleccione--"));
		ibTiempo.setValue(0);
		btnAgregar1.setDisabled(true);
	}
	
	public void limpiar2() {
		cmbMaterial.setSelectedIndex(-1);
		cmbMaterial.setValue(cmbMaterial.getValue().valueOf("--Seleccione--"));
		cmbCantidad.setSelectedIndex(-1);
		cmbCantidad.setValue(cmbCantidad.getValue().valueOf("--Seleccione--"));
		btnAgregar2.setDisabled(true);
	}
	public void onClick$btnAgregar1(){
		Listitem item = new Listitem();
		item.appendChild(new Listcell(cmbFase.getSelectedItem().getLabel()));
		item.appendChild(new Listcell(cmbActividad.getSelectedItem().getLabel()));
		item.appendChild(new Listcell(ibTiempo.getValue().toString()));
		lbActividades.appendChild(item);
		limpiar1();
				
	}
	public void onClick$btnAgregar2() {
		//System.out.println("Tamaño "+ lsb.getItemCount());
			
			if (lsb.getItemCount() == 0) {		
		Listitem item =new Listitem();
		item.appendChild(new Listcell(cmbMaterial.getSelectedItem().getLabel()));
		item.appendChild(new Listcell(cmbCantidad.getSelectedItem().getLabel()));
		lsb.appendChild(item);
			                   }
			
			else {
				int I=0;
				do{
					Listitem item = lsb.getItemAtIndex(I);
					Listcell lc = (Listcell)item.getChildren().get(0);
					if(cmbMaterial.getSelectedItem().getLabel().equals(lc.getLabel())){
						alert("No puede seleccionar el mismo material");
						return;
					}
					I++;
				}while(I < lsb.getItemCount());
				Listitem item =new Listitem();
				item.appendChild(new Listcell(cmbMaterial.getSelectedItem().getLabel()));
				item.appendChild(new Listcell(cmbCantidad.getSelectedItem().getLabel()));
				lsb.appendChild(item);
			}
	limpiar2();		
	}
	
	public void onClick$btnQuitar1(){
		lbActividades.removeItemAt(lbActividades.getSelectedIndex());
	}
	
	public void onChange$dtbox2(){
	
		Date date = dtbox2.getValue();
		if (date.before(dtbox1.getValue())){
			alert("La fecha final es mayor que la fecha inicial");
			
			
		}else{
			
			cmbFase.setDisabled(false);
			cmbMaterial.setDisabled(false);
			
		}
	}
	
	public void onSelect$cmbFase() {
		cmbActividad.setDisabled(false);
		ibTiempo.setDisabled(true);
		btnAgregar1.setDisabled(true);
		btnQuitar1.setDisabled(true);
	}
	
	public void onSelect$cmbActividad() {
		ibTiempo.setDisabled(false);
	}
	
	public void onFocus$ibTiempo() {
		btnAgregar1.setDisabled(false);
		btnQuitar1.setDisabled(false);
	}
	
	public void removerTodoCombo(Combobox combo) {
		int cantidad = combo.getItemCount();
		for (int i = 0; i < cantidad; i++) 
			combo.removeItemAt(0);
	}
	
	public void onSelect$cmbMaterial() {
		cmbCantidad.setDisabled(false);
	}
	
	public void onSelect$cmbCantidad() {
		btnAgregar2.setDisabled(false);
		btnQuitar2.setDisabled(false);
	}
	
	public void onClick$btnQuitar2() {
		
		lsb.removeItemAt(lsb.getSelectedIndex());
		
		}

		public void onClick$cmbMaterial() { 
			cmbCantidad.setDisabled(true);
		}
		
		public void onClick$cmbCantidad() { 
			btnAgregar2.setDisabled(true);
			btnQuitar2.setDisabled(true);
		}
		
		public void onClick$btnCancelar() {
			
			limpiar1();
			limpiar2();
			dtbox1.setValue(null);
			dtbox2.setValue(null);
			
			int cantidad=lsb.getItemCount();
			for (int I=0;I<cantidad;I++){
				lsb.removeItemAt(0);
			}
			int contador=lbActividades.getItemCount();
			for (int A=0;A<contador;A++){
				lbActividades.removeItemAt(0);
			}
			inicializar();
		}
}