package servicio.interfaz;

import java.util.List;

import modelo.Categoria;
import modelo.EquipoFaseCompetencia;
import modelo.FaseCompetencia;

public interface IServicioEquipoFaseCompetencia {

	public abstract void eliminar(List l);
	
	public abstract void actualizar(EquipoFaseCompetencia l);

	public abstract void agregar(EquipoFaseCompetencia l);

	public abstract List<EquipoFaseCompetencia> listarActivos();
	
	public abstract  List<EquipoFaseCompetencia> buscarEquipoPorFaseYCategoria (FaseCompetencia fase,Categoria categoria);


}
