package controlador.jugador.converter;

import modelo.*;

import org.zkoss.zkplus.databind.TypeConverter;

import dao.general.DaoRoster;

import servicio.implementacion.ServicioRoster;

public class NameConcatenacion implements TypeConverter {
 
  private static String valorRetornado = "";
  private static PersonaNatural persona;
  private static Jugador jugador;
  private static FamiliarJugador familiarJugador;
  private static Roster roster;
  private static Medico medico;
  
  public Object coerceToBean(java.lang.Object val,
          org.zkoss.zk.ui.Component comp) {
    return null;
  }
 
  public Object coerceToUi(java.lang.Object val,
          org.zkoss.zk.ui.Component comp) {
	  String segundoA, segundoN = "";
	  ServicioRoster servicioRoster = new ServicioRoster(); 
	  DaoRoster daoRoster = new DaoRoster();
	  
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
	  else if (val instanceof Jugador){
		  jugador = ((Jugador) val);
		  roster = daoRoster.buscarRoster(jugador.getCedulaRif());
		  if (roster == null)
			  return valorRetornado = "";
		  else valorRetornado = roster.getEquipo().getNombre();
	  }
	  else if (val instanceof FamiliarJugador){
		  familiarJugador = ((FamiliarJugador) val);
		  roster = daoRoster.buscarRoster(jugador.getCedulaRif());
		  if (roster == null)
			  return valorRetornado = "";
		  else valorRetornado = roster.getEquipo().getCategoria().getNombre();
	  }
	  else if (val instanceof Medico){
		  medico = ((Medico) val);
		  
		  valorRetornado = medico.getNombre() + " " + medico.getApellido();
		  }
	  return valorRetornado;
  }
}