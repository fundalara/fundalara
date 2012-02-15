package servicio.interfaz;

import java.util.List;

import modelo.DatoAcademicoPersonal;
import modelo.DocumentoAcreedor;
import modelo.Persona;

public interface IServicioDocumentoAcreedor {
	
public abstract void eliminar(DocumentoAcreedor c);
	
	public abstract void agregar(DocumentoAcreedor c);
		
	public abstract void actualizar(DocumentoAcreedor c);	
	
	public abstract  List<DocumentoAcreedor> listar ();
	
	public abstract List<DocumentoAcreedor> listarActivos();
	
	public abstract  DocumentoAcreedor  buscarPorCodigo (DocumentoAcreedor  d);

	public abstract List<DocumentoAcreedor> buscarPendientesPorRif(Persona td);

}
