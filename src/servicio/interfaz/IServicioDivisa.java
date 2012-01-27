package servicio.interfaz;

import java.util.List;

import modelo.Divisa;

public interface IServicioDivisa {
    
	public abstract void eliminar(Divisa d);
	
	public abstract void agregar(Divisa d);
		
	//public abstract void actualizar(Divisa d);	
	
	public abstract  List<Divisa> listar ();
	
	public abstract List<Divisa> listarActivos();
	
	public abstract  Divisa buscarPorCodigo (Divisa d);
	
	public List<Divisa> filtrar (String cad);
	
	public abstract List<Divisa> listarDivisaForanea();
}
