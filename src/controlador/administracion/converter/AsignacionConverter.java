package controlador.administracion.converter;

import modelo.Movimiento;
import org.zkoss.zk.ui.Component;
import org.zkoss.zkplus.databind.TypeConverter;

public class AsignacionConverter implements TypeConverter {
	
	
	  private static Movimiento movimiento;

	@Override
	public Object coerceToBean(java.lang.Object val,
	          org.zkoss.zk.ui.Component comp) {
		return null;
	}

	@Override
	public Object coerceToUi(java.lang.Object val,
	          org.zkoss.zk.ui.Component comp) {
		movimiento = ((Movimiento) val);
		  if(movimiento.getConceptoNomina().getDatoBasico().getNombre().equals("ASIGNACION")){
			  Double monto=new Double(movimiento.getMonto());
			  return monto.toString();
		  }else
			  return " ";
			  
	}
	

}
