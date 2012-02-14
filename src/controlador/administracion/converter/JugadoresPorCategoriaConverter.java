package controlador.administracion.converter;

import java.util.List;

import modelo.*;

import org.zkoss.zkplus.databind.TypeConverter;

import dao.general.DaoEquipo;
import dao.general.DaoRoster;

public class JugadoresPorCategoriaConverter implements TypeConverter {

	private static String valorRetornado = "";
	private static Categoria categoria;
	private static Equipo equipo;
	private static List<Roster> roster;
	private static List<Equipo> equipos;

	public Object coerceToBean(java.lang.Object val,
			org.zkoss.zk.ui.Component comp) {
		return null;
	}

	public Object coerceToUi(java.lang.Object val,
			org.zkoss.zk.ui.Component comp) {

		DaoRoster daoRoster = new DaoRoster();
		DaoEquipo daoEquipo = new DaoEquipo();

		if (val instanceof Categoria) {
			categoria = ((Categoria) val);
			equipos = daoEquipo.buscarEquiposPorCategoria(categoria);
			int contador = 0;
			for (Equipo equipo : equipos) {
				roster = daoRoster.listarCedxEquipo(Roster.class,
						equipo.getCodigoEquipo());
				contador = contador + roster.size();
			}
			valorRetornado = String.valueOf(contador);
		}

		if (val instanceof Equipo) {
			equipo = ((Equipo) val);
			int contador = 0;
			roster = daoRoster.listarCedxEquipo(Roster.class,
					equipo.getCodigoEquipo());
			contador = contador + roster.size();
			valorRetornado = String.valueOf(contador);
		}

		return valorRetornado;

	}
}