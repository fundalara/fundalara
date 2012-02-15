package controlador.jugador.converter;

import java.util.ArrayList;
import java.util.List;

import modelo.*;

import org.zkoss.zkplus.databind.TypeConverter;

import dao.general.DaoRoster;

import servicio.implementacion.ServicioRoster;

public class EquipoConverter implements TypeConverter {

	private static String valorRetornado = "";
	private static PersonaNatural persona;
	private static Jugador jugador;
	private static Roster roster;
	private static Medico medico;
	private static DatoMedico datoMedico;
	private static List<Roster> listaRoster = new ArrayList<Roster>();

	public Object coerceToBean(java.lang.Object val,
			org.zkoss.zk.ui.Component comp) {
		return null;
	}

	public Object coerceToUi(java.lang.Object val,
			org.zkoss.zk.ui.Component comp) {
		DaoRoster daoRoster = new DaoRoster();

		if (val instanceof DatoMedico) {
			datoMedico = (DatoMedico) val;
			listaRoster = daoRoster.buscarEquipo(datoMedico.getJugador(),
					datoMedico.getFechaInforme());
			if (listaRoster.size() == 0) {
				return valorRetornado = "";
			} else {
				int tam = listaRoster.size();
				int j = 0;
				//System.out.println("Tamaño: " + tam);
				for (int i = 0; i < tam; i++) {
					//System.out.println(listaRoster.get(i).getJugador()
					//		.getPersonaNatural().getPrimerNombre());
					//System.out.println(listaRoster.get(i).getFechaIngreso());
					//System.out.println(datoMedico.getFechaInforme());
					roster = listaRoster.get(i);
					// j = i + 1;
					if (roster.getFechaIngreso().before(
							datoMedico.getFechaInforme())) {
						//System.out.println("after");
						return roster.getEquipo().getNombre();
					}
				}
			}
		}
		return valorRetornado;
	}
}