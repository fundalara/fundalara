package dao.general;

import modelo.Categoria;
import modelo.LapsoDeportivo;
import modelo.PlanTemporada;
import dao.generico.GenericDao;

public class DaoPlanTemporada extends GenericDao{
	
	public PlanTemporada buscarPorCategoriaLapsoDeportivo(Categoria ct, LapsoDeportivo ld) {
		return (PlanTemporada) buscarDosCamposActivos(PlanTemporada.class, "categoria", ct,"lapsoDeportivo", ld);
	}
}
