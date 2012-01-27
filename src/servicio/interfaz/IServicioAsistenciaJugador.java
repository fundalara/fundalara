package servicio.interfaz;

import java.util.List;

import modelo.AsistenciaJugador;

public interface IServicioAsistenciaJugador {
	public abstract void guardar (AsistenciaJugador aj);
	
	public abstract void actualizar(AsistenciaJugador aj);
	
	public abstract void eliminar(AsistenciaJugador aj);
	
	public abstract List<AsistenciaJugador> listar(); 
}

