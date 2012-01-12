package servicio.interfaz;

import java.util.List;

import modelo.PersonalContrato;
import modelo.PersonalTipoNomina ;

public interface IServicioPersonalTipoNomina {
	
	public abstract void eliminar(PersonalTipoNomina  c);
	
	public abstract void agregar(PersonalTipoNomina  c);
		
	public abstract void actualizar(PersonalTipoNomina  c);	
	
	public abstract  List<PersonalTipoNomina   > listar ();

	public abstract List<PersonalTipoNomina   > listarActivos();
	
	public abstract PersonalTipoNomina    buscarPorCodigo (PersonalTipoNomina    d);

}
