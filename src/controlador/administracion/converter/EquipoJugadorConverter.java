package controlador.administracion.converter;

import modelo.*;

import org.zkoss.zkplus.databind.TypeConverter;
import dao.general.DaoRoster;

public class EquipoJugadorConverter implements TypeConverter {
 
  private static String valorRetornado = "";
  private static Jugador jugador;
  private static Roster roster;
  
  public Object coerceToBean(java.lang.Object val,
          org.zkoss.zk.ui.Component comp) {
    return null;
  }
 
  public Object coerceToUi(java.lang.Object val,
          org.zkoss.zk.ui.Component comp) {
	  
	  DaoRoster daoRoster = new DaoRoster();
	  
	  if (val instanceof Jugador){
		  jugador = ((Jugador) val);
		  roster = daoRoster.buscarRoster(jugador.getCedulaRif());
		  if (roster == null)
			  return valorRetornado = "";
		  else valorRetornado = roster.getEquipo().getNombre();
	  }
	  return valorRetornado;
  }
}