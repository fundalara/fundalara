package servicio.interfaz;

import java.util.List;

import modelo.PuntuacionJugador;

public interface IServicioPuntuacionJugador {
	public abstract void eliminar(PuntuacionJugador ps);
	
	public abstract void agregar(PuntuacionJugador ps);
		
	public abstract void actualizar(PuntuacionJugador ps);
	
	public abstract List<PuntuacionJugador> listar();
}
