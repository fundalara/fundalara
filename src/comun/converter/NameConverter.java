package comun.converter;

import modelo.*;

import org.zkoss.zkplus.databind.TypeConverter;

//import dao.general.DaoRoster;

//import servicio.implementacion.ServicioRoster;

public class NameConverter implements TypeConverter {
 
  private static String valorRetornado = "";
  private static PersonaNatural persona;
  private static Jugador jugador;
  private static FamiliarJugador familiarJugador;
  private static Roster roster;
  
  public Object coerceToBean(java.lang.Object val,
          org.zkoss.zk.ui.Component comp) {
    return null;
  }
 
  public Object coerceToUi(java.lang.Object val,
          org.zkoss.zk.ui.Component comp) {
	  String segundoA, segundoN = "";
	  //ServicioRoster servicioRoster = new ServicioRoster(); 
	 // DaoRoster daoRoster = new DaoRoster();
	  
	  if (val instanceof PersonaNatural){
	  persona = ((PersonaNatural) val);
	  if (persona.getSegundoNombre() == null)
		  segundoN = "";
	  else segundoN = persona.getSegundoNombre();
	  
	  if (persona.getSegundoApellido() == null)
		  segundoA = "";
	  else segundoA = persona.getSegundoApellido();
	  
	  valorRetornado = persona.getPrimerNombre() + " " + segundoN
			 + " " + persona.getPrimerApellido() + " " + segundoA;
	  }
	  else if (val instanceof PersonaJuridica){
		  
	  }
//	  else if (val instanceof Jugador){
//		  jugador = ((Jugador) val);
//		  roster = daoRoster.buscarPorJugador(jugador);
//		  if (roster == null)
//			  return valorRetornado = "";
//		  else valorRetornado = roster.getEquipo().getNombre();
//	  }
//	  else if (val instanceof FamiliarJugador){
//		  familiarJugador = ((FamiliarJugador) val);
//		  roster = daoRoster.buscarPorJugador(jugador);
//		  if (roster == null)
//			  return valorRetornado = "";
//		  else valorRetornado = roster.getEquipo().getCategoria().getNombre();
//	  }
	  
	  return valorRetornado;
  }
}