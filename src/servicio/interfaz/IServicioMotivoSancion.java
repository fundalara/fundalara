package servicio.interfaz;

import java.util.List;

import modelo.MotivoSancion;

public interface IServicioMotivoSancion {
	
	public abstract void eliminar(MotivoSancion c);

	public abstract void agregar(MotivoSancion c);

	public abstract void actualizar(MotivoSancion c);

	public abstract List<MotivoSancion> listar();
}
