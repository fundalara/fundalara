package servicio.implementacion;

import java.util.List;

import dao.general.DaoDocumentoPersonal;

import modelo.DocumentoEntregado;
import modelo.DocumentoPersonal;
import modelo.Jugador;

import servicio.interfaz.IServicioDocumentoPersonal;


/**
 * Clase para brindar los servicios para manejar los datos relacionados con los documentos personales de los jugadores
 * @author Robert A
 * @author German L
 * @version 0.1 02/01/2012
 *
 */
public class ServicioDocumentoPersonal implements IServicioDocumentoPersonal {
	DaoDocumentoPersonal daoDocumentoPersonal;
	

	public DaoDocumentoPersonal getDaoDocumentoPersonal() {
		return daoDocumentoPersonal;
	}

	public void setDaoDocumentoPersonal(DaoDocumentoPersonal daoDocumentoPersonal) {
		this.daoDocumentoPersonal = daoDocumentoPersonal;
	}

	@Override
	public void eliminar(DocumentoPersonal a) {
		daoDocumentoPersonal.eliminar(a);
		
	}

	@Override
	public void agregar(DocumentoPersonal a) {
		daoDocumentoPersonal.guardar(a);
		
	}

	@Override
	public void actualizar(DocumentoPersonal a) {
		daoDocumentoPersonal.actualizar(a);
		
	}

	@Override
	public List<DocumentoPersonal> listar() {
		return daoDocumentoPersonal.listar(DocumentoPersonal.class);
	}
	
	@Override
	public void guardar(List<DocumentoEntregado> documentos, Jugador jugador) {
		daoDocumentoPersonal.guardar(documentos, jugador);
		
	}

}
