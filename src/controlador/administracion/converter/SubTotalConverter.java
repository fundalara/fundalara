package controlador.administracion.converter;

import modelo.*;

import org.zkoss.zkplus.databind.TypeConverter;

public class SubTotalConverter implements TypeConverter {
 
  private static Double subTotal = (double) 0;
  private static CuentaPagarMaterial material;
  
  public Object coerceToBean(java.lang.Object val,
          org.zkoss.zk.ui.Component comp) {
    return null;
  }
 
  public Object coerceToUi(java.lang.Object val,
          org.zkoss.zk.ui.Component comp) {
	  if (val instanceof CuentaPagarMaterial){
	  material = ((CuentaPagarMaterial) val);
	  
	  subTotal = material.getPrecioUnitario() * material.getCantidad();
	  }
	  
	  return String.valueOf(subTotal);
  }
}