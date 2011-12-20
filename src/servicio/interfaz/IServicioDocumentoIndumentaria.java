package servicio.interfaz;

import java.util.List;

import modelo.DatoAcademicoPersonal;
import modelo.DocumentoIndumentaria;

public interface IServicioDocumentoIndumentaria {
	
	public abstract void eliminar(DocumentoIndumentaria c);
	
	public abstract void agregar(DocumentoIndumentaria c);
		
	public abstract void actualizar(DocumentoIndumentaria c);	
	
	public abstract  List<DocumentoIndumentaria> listar ();
	
	public abstract List<DocumentoIndumentaria> listarActivos();
	
	public abstract  DocumentoIndumentaria buscarPorCodigo (DocumentoIndumentaria  d);


}
