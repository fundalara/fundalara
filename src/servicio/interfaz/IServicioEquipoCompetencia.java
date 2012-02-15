package servicio.interfaz;

import java.util.List;

import modelo.CategoriaCompetencia;
import modelo.Competencia;
import modelo.EquipoCompetencia;

public interface IServicioEquipoCompetencia {
	
	public abstract void eliminar(List l);
	
	public abstract void actualizar(List<EquipoCompetencia> l);

	public abstract void agregar(EquipoCompetencia ec);

	public abstract List<EquipoCompetencia> listar();

	public abstract List<EquipoCompetencia> listarActivos();
	
	public abstract List<EquipoCompetencia> buscarEquipoporCompetencia(Competencia c);
	
	public abstract List<EquipoCompetencia> listarEquipos(int codigo);
	
	public abstract List<EquipoCompetencia> listarEquipoPorCompetenciaCategoria(Competencia c, CategoriaCompetencia cat) ;

	
}

