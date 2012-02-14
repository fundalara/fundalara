package servicio.interfaz;

import java.util.List;

import modelo.ConceptoNomina;
import modelo.CuentaPagar;
import modelo.EgresoCuentaPagar ;

public interface IServicioEgresoCuentaPagar {
	
	public abstract void eliminar(EgresoCuentaPagar  c);
	
	public abstract void agregar(EgresoCuentaPagar  c);
		
	public abstract void actualizar(EgresoCuentaPagar  c);	
	
	public abstract  List<EgresoCuentaPagar> listar ();
	
	public abstract List<EgresoCuentaPagar> listarActivos();
	
	public abstract  EgresoCuentaPagar buscarPorCodigo (EgresoCuentaPagar d);

	public abstract  List<EgresoCuentaPagar> listarPorCuentaPagar(CuentaPagar td);



}
