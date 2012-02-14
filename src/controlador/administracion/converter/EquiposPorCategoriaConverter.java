package controlador.administracion.converter;

import java.util.List;
import modelo.*;
import org.zkoss.zkplus.databind.TypeConverter;
import dao.general.DaoEquipo;

public class EquiposPorCategoriaConverter implements TypeConverter {

	private static String valorRetornado = "";
	private static Categoria categoria;
	private static List<Equipo> equipos;

	public Object coerceToBean(java.lang.Object val,
			org.zkoss.zk.ui.Component comp) {
		return null;
	}

	public Object coerceToUi(java.lang.Object val,
			org.zkoss.zk.ui.Component comp) {

		DaoEquipo daoEquipo = new DaoEquipo();

		if (val instanceof Categoria) {
			categoria = ((Categoria) val);
			equipos = daoEquipo.buscarEquiposPorCategoria(categoria);
			valorRetornado = String.valueOf(equipos.size());
		}
		return valorRetornado;
	}
}