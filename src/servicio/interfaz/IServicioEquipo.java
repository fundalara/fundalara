package servicio.interfaz;

import java.util.List;

import modelo.Categoria;
import modelo.Equipo;

public interface IServicioEquipo {
	
	public abstract void eliminar(Equipo c);
	
	public abstract void agregar(Equipo c);
		
	public abstract void actualizar(Equipo c);	
	
	public abstract List<Equipo> listar();
	
	public abstract List<Equipo> listarEquipoPorCategoria(int codigo);
	
	public abstract List<Equipo> listarEquipoForaneos();
	
    public abstract List<Equipo> buscarPorCategoria(Categoria categoria);
    
    public abstract List<Equipo> buscarEquiposForaneosPorCategoria(int categoria);
	
	public abstract boolean buscarPorCodigo (Equipo equipo);
	
}
