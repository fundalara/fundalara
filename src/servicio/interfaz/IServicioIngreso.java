package servicio.interfaz;

import java.util.List;

import modelo.ConceptoNomina;
import modelo.Ingreso;

public interface IServicioIngreso {

	public abstract void eliminar(Ingreso c);
	
	public abstract void agregar(Ingreso c);
		
	public abstract void actualizar(Ingreso c);	
	
	public abstract  List<Ingreso> listar ();
	
	public abstract List<Ingreso> listarActivos();
	
	public abstract  Ingreso buscarPorCodigo (Ingreso d);


}
