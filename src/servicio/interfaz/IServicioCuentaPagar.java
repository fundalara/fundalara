package servicio.interfaz;


import java.util.List;

import modelo.AfeccionPersonal;
import modelo.ConceptoNomina;
import modelo.CuentaPagar;

public interface IServicioCuentaPagar {
	
	public abstract void eliminar(CuentaPagar c);
	
	public abstract void agregar(CuentaPagar  c);
		
	public abstract void actualizar(CuentaPagar  c);	
	
	public abstract  List<CuentaPagar> listar ();
	
	public abstract List<CuentaPagar> listarActivos();
	
	public abstract  CuentaPagar buscarPorCodigo (CuentaPagar d);


}
