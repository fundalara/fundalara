package servicio.interfaz;

import java.util.List;

import modelo.Ascenso;
import modelo.Roster;

public interface IServicioAscenso {
	
	public abstract void eliminar(Ascenso c);

	public abstract void agregar(Ascenso c);

	public abstract void actualizar(Ascenso c);

	public abstract List<Ascenso> listar();
	
	public abstract void actualizarAscenso(Roster r);
	
}
