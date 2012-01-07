package servicio.interfaz;

import java.util.List;

import modelo.SeguridadFuncional;

public interface IServicioSeguridadFuncional {
	public abstract void eliminar(SeguridadFuncional s);

	public abstract void agregar(SeguridadFuncional s);

	public abstract List<SeguridadFuncional> listar();

	public abstract List<SeguridadFuncional> listarActivos();


}
