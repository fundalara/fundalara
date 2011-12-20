package servicio.interfaz;

import java.util.List;

import modelo.DesempeoJugador;;

public interface IServicioDesempennoJugador {

	public abstract void guardar(DesempeoJugador ae);
	
	public abstract void actualizar(DesempeoJugador ae);
	
	public abstract void eliminar(DesempeoJugador ae);
	
	public abstract List<DesempeoJugador> listar(); 
}
