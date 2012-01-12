package controlador.jugador.converter;

import org.zkoss.zk.ui.Component;
import org.zkoss.zkplus.databind.TypeConverter;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Radio;

public class CharConverter implements TypeConverter{

	@Override
	public Object coerceToBean(Object arg0, Component arg1) {
		char valor= ' ';
		if (arg0 instanceof Radio)
		 valor=((Radio)arg0).getValue().charAt(0);
		else if ((arg0 instanceof Comboitem))
			 valor=((Comboitem)arg0).getValue().toString().charAt(0);
		 return valor; 
	}

	@Override
	public Object coerceToUi(Object arg0, Component arg1) {
		return null;
	}

}
