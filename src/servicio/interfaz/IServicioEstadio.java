package servicio.interfaz;

import java.util.List;


import modelo.Divisa;
import modelo.Estadio;


public interface IServicioEstadio {
	
	public abstract void eliminar(Estadio e);
	
	public abstract void agregar(Estadio e);
		
	public abstract void actualizar(Estadio e);
	
	//Servicios personalizados
		public List<Estadio> listarActivos();
		public List<Estadio> listar();
		public List<Estadio> filtrar (String cad);

}
