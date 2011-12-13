package controlador.logistica;

import modelo.ClaseMantenimiento;

import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Column;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import dao.general.GenericDAO;

public class TipoMantenimientoControlador extends GenericForwardComposer {
	 
	private Window TipoMantenimiento;
		
	   public void onClick$btnGuardar(){
		     
		     modelo.TipoMantenimiento tm = new modelo.TipoMantenimiento();
		     ClaseMantenimiento cm = new dao.logistica.ClaseMantenimientoDAO().obtenerClasePorCodigo("12345");
		     
		     tm.setCodigoTipoMantenimiento("c1");
		     tm.setEstatus('A');
		     tm.setDescripcion( ((Textbox) TipoMantenimiento.getVariable("txtDescripcion", false)).getText());
		    
		     tm.setClaseMantenimiento(cm);
		     
		     GenericDAO daoM = new GenericDAO();
		     
		     daoM.guardar(tm);
	   }

}
