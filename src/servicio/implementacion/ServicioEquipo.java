package servicio.implementacion;

import java.util.List;

import servicio.interfaz.IServicioEquipo;

import dao.general.DaoEquipo;
import modelo.Categoria;
import modelo.Equipo;

/**
 * Clase para brindar los servicios para manejar los datos relacionados con los equipos
 * @author Robert A
 * @author German L
 * @version 0.1 29/12/2011
 *
 */

public class ServicioEquipo implements IServicioEquipo {

	DaoEquipo daoEquipo;
		
	public DaoEquipo getDaoEquipo() {
		return daoEquipo;
	}

	public void setDaoEquipo(DaoEquipo daoEquipo) {
		this.daoEquipo = daoEquipo;
	}

	@Override
	public void eliminar(Equipo c) {
		daoEquipo.eliminar(c);

	}

	@Override
	public void agregar(Equipo c) {
		daoEquipo.guardar(c);

	}

	@Override
	public void actualizar(Equipo c) {
		daoEquipo.actualizar(c);

	}

	@Override
	public List<Equipo> listar() {
		return daoEquipo.listar( Equipo.class);
	}
	
	public List<Equipo> listarEquipoPorCategoria(int codigo) {
		return daoEquipo.listarEquipoPorCategoria(Equipo.class, codigo);
	}

	public List<Equipo> listarEquipoForaneos(){
		return daoEquipo.listarEquipoForaneos();
	}
	
	
	public List<Equipo> buscarPorCategoria(Categoria categoria){
		return daoEquipo.buscarEquiposPorCategoria(categoria);
	}
	public List<Equipo> buscarEquiposForaneosPorCategoria(int categoria){
		return daoEquipo.buscarEquiposForaneosPorCategoria(categoria);
	}
	public boolean buscarPorCodigo (Equipo equipo) {
		return daoEquipo.buscarPorCodigo(equipo);
	}
}
