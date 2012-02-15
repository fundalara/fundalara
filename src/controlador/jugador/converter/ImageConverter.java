package controlador.jugador.converter;


import org.zkoss.zk.ui.Component;
import org.zkoss.zkplus.databind.TypeConverter;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Image;
import org.zkoss.zul.Radio;


public class ImageConverter implements TypeConverter{

	@Override
	public Object coerceToBean(Object arg0, Component arg1) {
		System.out.println("la clase del objeto e4s "+arg0.getClass().toString());
		 return arg0;

	}

	@Override
	public Object coerceToUi(Object arg0, Component arg1) {
		Image img ; 
		
		if (arg0==null){
			System.out.println("Clase UI NULL objeto UI ");	
			// img = new Image();
			//img.setSrc("../../Recursos/Imagenes/noFoto.jpg");
			return IGNORE;//.getContent().getByteData();
		
		}else{
			System.out.println("UI dif null");
			return arg0;
			}
	}

}
