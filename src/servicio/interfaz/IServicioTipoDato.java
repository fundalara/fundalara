package servicio.interfaz;

import java.util.List;

import servicio.implementacion.ServicioTipoDato;



public interface IServicioTipoDato  {
public abstract void guardar(ServicioTipoDato s);
	
	public abstract void actualizar(ServicioTipoDato s);
	
	public abstract void eliminar(ServicioTipoDato s);
	
	public abstract List<ServicioTipoDato> listar(); 

}
