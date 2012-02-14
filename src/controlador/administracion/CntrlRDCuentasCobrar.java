package controlador.administracion;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import modelo.DocumentoAcreedor;
import modelo.TipoIngreso;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Filedownload;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listhead;
import org.zkoss.zul.Listheader;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Window;

import comun.Validacion;

import servicio.implementacion.ServicioDocumentoAcreedor;
import servicio.implementacion.ServicioTipoIngreso;

public class CntrlRDCuentasCobrar extends GenericForwardComposer {
	Datebox dtbInicio, dtbFin;
	Combobox cmbTipoIngreso, cmbEstado;
	Listbox lbxReporte;
	Button btnBuscarFact, btnExportar, btnCancelar, btnSalir;	
	Listitem item;
	Listcell cell;
	Listhead head;
	Listheader header;	
	Component componente; 
	AnnotateDataBinder binder;
	Window winReportesCuentasPagar;
	
	List<TipoIngreso> lstTipoIngresos = new ArrayList<TipoIngreso>();
	List<DocumentoAcreedor> lstDocAcrredor = new ArrayList<DocumentoAcreedor>();
	ServicioTipoIngreso servicioTipoIngreso;
	ServicioDocumentoAcreedor servicioDocumentoAcreedor;
	

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
		comp.setVariable("cntrl", this, true);
		componente = comp;
		lstTipoIngresos = servicioTipoIngreso.listar();
		lstDocAcrredor = servicioDocumentoAcreedor.listar();
		
	}
	
	
	
	public void onClick$btnBuscarFact() throws WrongValueException, ParseException{
		boolean sw = true;
		if (cmbTipoIngreso.getSelectedItem() == null){
			sw = false;
			throw new WrongValueException(cmbTipoIngreso,
			"Seleccione un tipo de ingreso");
		}
		if (cmbEstado.getSelectedItem() == null){
			sw = false;
			throw new WrongValueException(cmbEstado,
			"Seleccione un estado");
		}
		if (Validacion.validarFecha(dtbInicio, dtbFin) && sw){
			DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			Date startDate = (Date)format.parse(dtbInicio.getText());
			Date endDate = (Date)format.parse(dtbFin.getText());
			String tipoI = cmbTipoIngreso.getSelectedItem().getLabel().toString();
			String estado = cmbEstado.getSelectedItem().getValue().toString();
			lstDocAcrredor = servicioDocumentoAcreedor.buscarFiltrado(tipoI, estado, startDate, endDate);
			
			binder.loadComponent(lbxReporte);
		}
	}
	
	public void onClick$btnCancelar(){
		dtbFin.setText("");
		dtbInicio.setText("");
		cmbEstado.setSelectedIndex(-1);
		cmbTipoIngreso.setSelectedIndex(-1);
	}
	
	public void imprimirArchivoTexto() throws IOException{
		String s = " || ";
		 Locale hola = new Locale("es_es");
		
		 SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy hh:mm", hola);
		 String fecha = formateador.format(new Date());  
	     StringBuffer sb = new StringBuffer();
	     	sb.append("Reporte de Cuentas por Cobrar "+ fecha +"\n");
	        for (Object head : lbxReporte.getHeads()) {
	          String h = "";
	          for (Object header : ((Listhead) head).getChildren()) {
	            h += ((Listheader) header).getLabel() + s;
	          }
	          sb.append(h + "\n");
	        }
	        for (Object item :  lbxReporte.getItems()) {
	        	String i = "";
	          for (Object cell : ((Listitem) item).getChildren()) {
	            i += ((Listcell) cell).getLabel() + s;
	          }
	          sb.append(i + "\n");
	        }
	        Filedownload.save(sb.toString().getBytes(), "text/plain", "Reporte cuentas por Cobrar.txt");
	}
	
	public void onClick$btnExportar() throws IOException{
		imprimirArchivoTexto();
		
	}
	
	public void onClick$btnSalir(){
		winReportesCuentasPagar.onClose();
	}

	public List<TipoIngreso> getLstTipoIngresos() {
		return lstTipoIngresos;
	}


	public void setLstTipoIngresos(List<TipoIngreso> lstTipoIngresos) {
		this.lstTipoIngresos = lstTipoIngresos;
	}


	public List<DocumentoAcreedor> getLstDocAcrredor() {
		return lstDocAcrredor;
	}


	public void setLstDocAcrredor(List<DocumentoAcreedor> lstDocAcrredor) {
		this.lstDocAcrredor = lstDocAcrredor;
	}

}
