package servicio.interfaz;

import java.util.List;

import modelo.LogroPorJugador;

public interface IServicioLogroPorJugador {

	public abstract void eliminar(LogroPorJugador c);

	public abstract void agregar(LogroPorJugador c);

	public abstract void actualizar(LogroPorJugador c);

	public abstract List<LogroPorJugador> listar();
}
