package servicio.interfaz;

import java.util.List;

import modelo.Anuario;

public interface IServicioAnuario {
	
	public abstract void eliminar(Anuario a);

	public abstract void agregar(Anuario a);

	public abstract void actualizar(Anuario a);

	public abstract List<Anuario> listar();
	

}
