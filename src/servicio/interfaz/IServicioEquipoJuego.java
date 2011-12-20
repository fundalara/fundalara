package servicio.interfaz;

import java.util.List;
import modelo.EquipoJuego;

public interface IServicioEquipoJuego {
	
	public abstract void eliminar(EquipoJuego e);

	public abstract void agregar(EquipoJuego e);
	
	public abstract  List<EquipoJuego> listar ();
	
	public abstract List<EquipoJuego> listarActivos();
}
