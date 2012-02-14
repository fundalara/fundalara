package servicio.interfaz;


import java.text.ParseException;
import java.util.List;

import modelo.AfeccionPersonal;
import modelo.ConceptoNomina;
import modelo.CuentaPagar;
import modelo.Persona;

public interface IServicioCuentaPagar {
	
	public abstract void eliminar(CuentaPagar c);
	
	public abstract void agregar(CuentaPagar  c);
		
	public abstract void actualizar(CuentaPagar  c);	
	
	public abstract  List<CuentaPagar> listar ();
	
	public abstract List<CuentaPagar> listarActivos();
	
	public abstract  CuentaPagar buscarPorCodigo (CuentaPagar d);

	public abstract  List<CuentaPagar> listarCuentaPorPagarPorFecha(String inicio, String fin,
			String filtro, Persona persona) throws ParseException;

	public abstract  List<CuentaPagar> listarCuentaPorPagarFiltro(String s, Persona persona);

	public abstract CuentaPagar buscarNumeroDocumento(String cp);


}
