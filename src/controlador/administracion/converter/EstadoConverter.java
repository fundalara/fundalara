package controlador.administracion.converter;

import modelo.CuentaPagar;
import modelo.Movimiento;
import modelo.Personal;

import org.zkoss.zk.ui.Component;
import org.zkoss.zkplus.databind.TypeConverter;

import servicio.implementacion.ServicioPersonalConceptoNomina;

public class EstadoConverter implements TypeConverter {
	
	
	  private static CuentaPagar cuenta;
	  private static String valor;

	@Override
	public Object coerceToBean(java.lang.Object val,
	          org.zkoss.zk.ui.Component comp) {
		return null;
	}
	
	@Override
	public Object coerceToUi(java.lang.Object val,
	          org.zkoss.zk.ui.Component comp) {
		valor="";
		cuenta = ((CuentaPagar) val);
		  if(cuenta.getEstado()=='P'){
			  return"Pendiente";
		  }else
			  return "Cancelada ";
		  //return valor;
	}
}