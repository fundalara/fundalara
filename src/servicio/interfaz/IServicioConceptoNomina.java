package servicio.interfaz;

import java.util.List;
import modelo.ConceptoNomina;

public interface IServicioConceptoNomina {
	 	public abstract void eliminar(ConceptoNomina c);
		
		public abstract void agregar(ConceptoNomina c);
			
		public abstract void actualizar(ConceptoNomina c);	
		
		public abstract List listar();


}
