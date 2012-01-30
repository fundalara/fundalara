package comun.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.zkoss.zk.ui.Component;
import org.zkoss.zkplus.databind.TypeConverter;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Radio;

public class CharConverter implements TypeConverter{
	@Override
	public Object coerceToBean(Object arg0, Component arg1) {
		Datebox dateF = new Datebox();
		String valor = "";
		Date df = (Date)arg0;
		dateF.setValue(df);
		 //valor = dateF.getText();
		 valor = (String)arg0;
		 System.out.println("-------"+valor);
//		else if ((arg0 instanceof Comboitem))
//			 valor=((Comboitem)arg0).getValue().toString().charAt(0);
		return valor.substring(0, 3); 
	}

	@Override
	public Object coerceToUi(Object arg0, Component arg1) {
		return null;
	}

}
