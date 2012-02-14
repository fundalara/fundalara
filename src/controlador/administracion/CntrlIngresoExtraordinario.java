package controlador.administracion;

import modelo.DocumentoAcreedor;
import modelo.Ingreso;
import modelo.IngresoDocumentoAcreedor;
import modelo.IngresoDocumentoAcreedorId;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;
import org.zkoss.zul.api.Doublebox;

import servicio.implementacion.ServicioDocumentoAcreedor;
import servicio.implementacion.ServicioIngreso;
import servicio.implementacion.ServicioIngresoDocumentoAcreedor;
import servicio.implementacion.ServicioTipoIngreso;

public class CntrlIngresoExtraordinario extends GenericForwardComposer {
	Textbox txtConcepto;
	Doublebox dbxMonto;
	Datebox dtxFechaEmision;
	Button btnSalir, btnRegistrar,btnCancelar;
	Window frmIngresoExtraordinario;
	ServicioDocumentoAcreedor servicioDocumentoAcreedor;
	ServicioIngreso servicioIngreso;
	ServicioIngresoDocumentoAcreedor servicioIngresoDocumentoAcreedor;
	ServicioTipoIngreso servicioTipoIngreso;
	
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		comp.setVariable("cntrl", this, true);
	}
   //---------------------------------------------------------------------------------------------------------------	
	public void onClick$btnSalir() {
		frmIngresoExtraordinario.detach();
	}
	//--------------------------------------------------------------------------------------------------------------
	public void clear(){
		txtConcepto.setValue(" ");
		dbxMonto.setValue(null);
		dtxFechaEmision.setValue(null);
	}
	//--------------------------------------------------------------------------------------------------------------
	public void onClick$btnCancelar(){
		clear();
	}
	//---------------------------------------------------------------------------------------------------------------
	public boolean vacio(){
		boolean sw=false;
		if(txtConcepto.getValue()==""){
			alert("Debe ingresar el concepto ");
			return sw=true;
		}
		else if (dbxMonto.getValue()==null){
			alert("Debe ingresar el monto");
			return sw=true;
		}
		else if(dtxFechaEmision.getValue()==null){
			alert("Debe Ingresar la fecha");
			return sw=true;
		}else
			return sw;
	}
	//---------------------------------------------------------------------------------------------------------------
	public void onClick$btnRegistrar(){
		if(!vacio()){
			//llenar datos ne la tabla documento acreedor
			DocumentoAcreedor documentoAcreedor=new DocumentoAcreedor();
			documentoAcreedor.setEstatus('A');
			documentoAcreedor.setEstado('C');
			documentoAcreedor.setConcepto(txtConcepto.getValue().toUpperCase().trim());
			documentoAcreedor.setMonto(dbxMonto.getValue());
			documentoAcreedor.setFechaEmision(dtxFechaEmision.getValue());
			documentoAcreedor.setFechaVencimiento(dtxFechaEmision.getValue()); //lo coloco null en el modelo??
			documentoAcreedor.setCodigoDocumentoAcreedor(servicioDocumentoAcreedor.listar().size()+1);
			//System.out.println(servicioTipoIngreso.buscarPorNombre("INGRESO EXTRAORDINARIO").getDescripcion());
			
			documentoAcreedor.setTipoIngreso(servicioTipoIngreso.buscarPorNombre("OTRO"));
			documentoAcreedor.setSaldo(0);
			servicioDocumentoAcreedor.agregar(documentoAcreedor);
			//llenar dato en la tabla ingreso
			Ingreso ingreso=new Ingreso();
			ingreso.setEstatus('A');
			ingreso.setFechaPago(dtxFechaEmision.getValue());
			ingreso.setCodigoIngreso(servicioIngreso.listar().size()+1);
			servicioIngreso.agregar(ingreso);
			//como hago con el numero de documento
			//llenar Ingreso Documento Acreedor
			IngresoDocumentoAcreedor ingresoDocumentoAcreedor= new IngresoDocumentoAcreedor();
			ingresoDocumentoAcreedor.setEstatus('A');
			ingresoDocumentoAcreedor.setIngreso(ingreso);
			ingresoDocumentoAcreedor.setMontoAbonado(dbxMonto.getValue());
			ingresoDocumentoAcreedor.setDocumentoAcreedor(documentoAcreedor);
			ingresoDocumentoAcreedor.setId(new IngresoDocumentoAcreedorId( documentoAcreedor.getCodigoDocumentoAcreedor(),ingreso.getCodigoIngreso()));
			servicioIngresoDocumentoAcreedor.agregar(ingresoDocumentoAcreedor);
			alert("Guardado con Exito");
			clear();
		}
	}
}
