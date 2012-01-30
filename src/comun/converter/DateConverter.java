package comun.converter;

import org.zkoss.zkplus.databind.TypeConverter;
import org.zkoss.zul.Listcell;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConverter implements TypeConverter {
 
  private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
  
  public Object coerceToBean(java.lang.Object val,
          org.zkoss.zk.ui.Component comp) {
    return null;
  }
 
  public Object coerceToUi(java.lang.Object val,
          org.zkoss.zk.ui.Component comp) {
    return sdf.format((Date) val);
  }
}