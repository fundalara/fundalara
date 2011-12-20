package servicio.interfaz;

import java.util.List;

import modelo.TestJugador;


public interface IServicioTestJugador {
	
	public abstract void guardar(TestJugador tj);
	
	public abstract void actualizar(TestJugador tj);
	
	public abstract void eliminar(TestJugador tj);
	
	public abstract List<TestJugador> listar(); 

}
