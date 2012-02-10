package controlador.competencia.converter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import modelo.EquipoJuego;

import org.zkoss.zk.ui.Component;
import org.zkoss.zkplus.databind.TypeConverter;

public class JuegoConverter implements TypeConverter {

	@Override
	public Object coerceToBean(Object arg0, Component arg1) {
		return IGNORE;
	}

	@Override
	public Object coerceToUi(Object arg0, Component arg1) {
		 
		Set<EquipoJuego> conjunto = (Set<EquipoJuego>) arg0;
	    List<EquipoJuego> lista = ConvertirConjuntoALista(conjunto);
	    EquipoJuego ej1 = lista.get(0);
	    EquipoJuego ej2 = lista.get(1);
	    String equipo1 = ej1.getEquipoCompetencia().getEquipo().getNombre();
	    String equipo2 = ej2.getEquipoCompetencia().getEquipo().getNombre();
	    String divisa1 = ej1.getEquipoCompetencia().getEquipo().getDivisa().getNombre();
	    String divisa2 = ej2.getEquipoCompetencia().getEquipo().getDivisa().getNombre();
	    String cadena = equipo1 + " ("+divisa1+") "+ " vs "+equipo2 + " ("+divisa2+")"; 	 
	    return cadena;
	}

	// Agregado Convierte un conjunto a una lista...
	public List ConvertirConjuntoALista(Set conjunto) {
		List l = new ArrayList();
		for (Iterator i = conjunto.iterator(); i.hasNext();) {
			l.add(i.next());
		}
		return l;
	}

}
