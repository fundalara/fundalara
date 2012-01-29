package controlador.administracion;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import modelo.CuentaPagar;
import modelo.DatoBasico;
import modelo.Egreso;
import modelo.Persona;
import modelo.PersonaJuridica;
import modelo.PersonaNatural;

import org.zkoss.util.media.AMedia;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Button;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Filedownload;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Iframe;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listhead;
import org.zkoss.zul.Listheader;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Window;

import servicio.implementacion.ServicioCuentaPagar;
import servicio.implementacion.ServicioDatoBasico;

public class CntrlReportesCompras extends GenericForwardComposer {
	Egreso egreso;
	CuentaPagar cuentaPagar, factura;
	DatoBasico datoBasico;
	Persona persona;
	PersonaNatural personaN;
	PersonaJuridica personaJ;
	ServicioDatoBasico servicioDatoBasico;
	ServicioCuentaPagar servicioCuentaPagar;
	Component componente; 
	AnnotateDataBinder binder;
	List<CuentaPagar> lstFacturas = new ArrayList<CuentaPagar>();
	List<Egreso> lstEgresos = new ArrayList<Egreso>();
	Combobox cmbTipoReporte,cmbEstado;
	Grid gridFiltros;
	Checkbox cb1,cb2,cb3;
	Datebox dtInicio,dtFin;
	Button btnBuscarFact,btnExportar,btnCancelar,btnSalir;
	Listbox lbxReporte;
	Window winReportesCompras;
	String paramEstado;
	Listitem item;
	Listcell cell;
	Listhead head;
	Listheader header;
	
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
		comp.setVariable("cntrl", this, true);
		componente = comp;
		cuentaPagar = new CuentaPagar();
		paramEstado = "A";
		datoBasico = servicioDatoBasico.buscarPorString("ADQUISICION");
		lstFacturas = servicioCuentaPagar.listarCuentaPorPagarFiltro(datoBasico,paramEstado);
		cuentaPagar.getPersona();
		
	}
	
	public void onClick$btnBuscarFact() throws WrongValueException, ParseException{
		limpiarListaFacturas();
		String estado = cmbEstado.getValue();
		if (estado.equals("Eliminado")){
			paramEstado = "E"; 
		}else if(estado.equals("Cancelado")){
			paramEstado = "C";
		}else if(estado.equals("Pendiente")){
			paramEstado = "P";
		}else{
			paramEstado = "A";
		}
		datoBasico = servicioDatoBasico.buscarPorString("ADQUISICION");
		if (dtInicio.getText().equals("") || dtFin.getText().equals("")){
			lstFacturas = servicioCuentaPagar.listarCuentaPorPagarFiltro(datoBasico,paramEstado);
			
			
		}else {
			lstFacturas = servicioCuentaPagar.listarCuentaPorPagarPorFecha(datoBasico, dtInicio.getText().toString(), dtFin.getText().toString(),paramEstado);
			
		}
		binder.loadComponent(lbxReporte);
		
		
	}
	
	public void imprimirArchivoTexto() throws IOException{
		String s = " || ";
		 Locale hola = new Locale("es_es");
		
		 SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy hh:mm", hola);
		 String fecha = formateador.format(new Date());  
	     StringBuffer sb = new StringBuffer();
	     	sb.append("Reporte de Facturas "+ fecha +"\n");
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
	        Filedownload.save(sb.toString().getBytes(), "text/plain", "reporte.txt");
	}
	
	public void onClick$btnExportar() throws IOException{
		imprimirArchivoTexto();
		//frame.setContent(amedia);
	}
	
	public void onClick$btnCancelar(){
		limpiarListaFacturas();
		dtFin.setText("");
		dtInicio.setText("");
		cmbEstado.setValue("");
		
	}
	
	public void onClick$btnSalir(){
		winReportesCompras.detach();
		
	}
	
	public void limpiarListaFacturas() {
		int ind = lbxReporte.getItemCount();
		for (int i = 0; i < ind; i++) {
			lbxReporte.removeItemAt(0);
		}
	}

	public Egreso getEgreso() {
		return egreso;
	}

	public void setEgreso(Egreso egreso) {
		this.egreso = egreso;
	}

	public CuentaPagar getCuentaPagar() {
		return cuentaPagar;
	}

	public void setCuentaPagar(CuentaPagar cuentaPagar) {
		this.cuentaPagar = cuentaPagar;
	}

	public CuentaPagar getFactura() {
		return factura;
	}

	public void setFactura(CuentaPagar factura) {
		this.factura = factura;
	}

	public DatoBasico getDatoBasico() {
		return datoBasico;
	}

	public void setDatoBasico(DatoBasico datoBasico) {
		this.datoBasico = datoBasico;
	}

	public ServicioDatoBasico getServicioDatoBasico() {
		return servicioDatoBasico;
	}

	public void setServicioDatoBasico(ServicioDatoBasico servicioDatoBasico) {
		this.servicioDatoBasico = servicioDatoBasico;
	}

	public ServicioCuentaPagar getServicioCuentaPagar() {
		return servicioCuentaPagar;
	}

	public void setServicioCuentaPagar(ServicioCuentaPagar servicioCuentaPagar) {
		this.servicioCuentaPagar = servicioCuentaPagar;
	}

	public Component getComponente() {
		return componente;
	}

	public void setComponente(Component componente) {
		this.componente = componente;
	}

	public List<CuentaPagar> getLstFacturas() {
		return lstFacturas;
	}

	public void setLstFacturas(List<CuentaPagar> lstFacturas) {
		this.lstFacturas = lstFacturas;
	}

	public List<Egreso> getLstEgresos() {
		return lstEgresos;
	}

	public void setLstEgresos(List<Egreso> lstEgresos) {
		this.lstEgresos = lstEgresos;
	}

	public Combobox getCmbTipoReporte() {
		return cmbTipoReporte;
	}

	public void setCmbTipoReporte(Combobox cmbTipoReporte) {
		this.cmbTipoReporte = cmbTipoReporte;
	}

	public Combobox getCmbEstado() {
		return cmbEstado;
	}

	public void setCmbEstado(Combobox cmbEstado) {
		this.cmbEstado = cmbEstado;
	}

	public Grid getGridFiltros() {
		return gridFiltros;
	}

	public void setGridFiltros(Grid gridFiltros) {
		this.gridFiltros = gridFiltros;
	}

	public Checkbox getCb1() {
		return cb1;
	}

	public void setCb1(Checkbox cb1) {
		this.cb1 = cb1;
	}

	public Checkbox getCb2() {
		return cb2;
	}

	public void setCb2(Checkbox cb2) {
		this.cb2 = cb2;
	}

	public Checkbox getCb3() {
		return cb3;
	}

	public void setCb3(Checkbox cb3) {
		this.cb3 = cb3;
	}

	public Datebox getDtInicio() {
		return dtInicio;
	}

	public void setDtInicio(Datebox dtInicio) {
		this.dtInicio = dtInicio;
	}

	public Datebox getDtFin() {
		return dtFin;
	}

	public void setDtFin(Datebox dtFin) {
		this.dtFin = dtFin;
	}

	public Button getBtnBuscarFact() {
		return btnBuscarFact;
	}

	public void setBtnBuscarFact(Button btnBuscarFact) {
		this.btnBuscarFact = btnBuscarFact;
	}

	public Button getBtnExportar() {
		return btnExportar;
	}

	public void setBtnExportar(Button btnExportar) {
		this.btnExportar = btnExportar;
	}

	public Button getBtnCancelar() {
		return btnCancelar;
	}

	public void setBtnCancelar(Button btnCancelar) {
		this.btnCancelar = btnCancelar;
	}

	public Button getBtnSalir() {
		return btnSalir;
	}

	public void setBtnSalir(Button btnSalir) {
		this.btnSalir = btnSalir;
	}

	public Listbox getLbxReporte() {
		return lbxReporte;
	}

	public void setLbxReporte(Listbox lstReporte) {
		this.lbxReporte = lstReporte;
	}
	
	
	
}
