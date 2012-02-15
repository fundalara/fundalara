package servicio.interfaz;

import java.util.List;

import modelo.DesempennoJugador;

public interface IServicioDesempennoJugador {

	public abstract void guardar(DesempennoJugador ae);
	
	public abstract void actualizar(DesempennoJugador ae);
	
	public abstract void eliminar(DesempennoJugador ae);
	
	public abstract List<DesempennoJugador> listar(); 
}
