package servicio.interfaz;

import java.util.List;

import modelo.Divisa;
import modelo.Equipo;

public interface IServicioRegistroEquipo {
public abstract void eliminar(Equipo d);
	
	public abstract void agregar(Equipo d);
		
	//public abstract void actualizar(Divisa d);	
	
	public abstract  List<Equipo> listar ();
	
	public abstract List<Equipo> listarActivos();
	
//	public abstract  Divisa buscarPorCodigo (Divisa d);

}
