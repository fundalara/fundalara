package servicio.interfaz;

import java.util.List;

import modelo.TallaPorJugador;

public interface IServicioTallaPorJugador {
	
	public abstract void eliminar(TallaPorJugador c);

	public abstract void agregar(TallaPorJugador c);

	public abstract void actualizar(TallaPorJugador c);

	public abstract List<TallaPorJugador> listar();
}

