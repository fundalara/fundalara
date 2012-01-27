package servicio.implementacion;

import java.util.List;

import servicio.interfaz.IServicioDocumentoAcademico;

import dao.general.DaoDocumentoAcademico;

import modelo.DatoAcademico;
import modelo.DocumentoAcademico;
import modelo.DocumentoEntregado;

/**
 * Clase para brindar los servicios para manejar los datos relacionados con los documentos academicos de los jugadores
 * @author Robert A
 * @author German L
 * @version 0.1 02/01/2012
 *
 */
public class ServicioDocumentoAcademico implements IServicioDocumentoAcademico {
 
	DaoDocumentoAcademico daoDocumentoAcademico;
	
	public DaoDocumentoAcademico getDaoDocumentoAcademico() {
		return daoDocumentoAcademico;
	}

	public void setDaoDocumentoAcademico(DaoDocumentoAcademico daoDocumentoAcademico) {
		this.daoDocumentoAcademico = daoDocumentoAcademico;
	}

	@Override
	public void eliminar(DocumentoAcademico c) {
		daoDocumentoAcademico.eliminar(c);

	}

	@Override
	public void agregar(DocumentoAcademico c) {
		daoDocumentoAcademico.guardar(c);

	}

	@Override
	public void actualizar(DocumentoAcademico c) {
		daoDocumentoAcademico.actualizar(c);

	}

	@Override
	public List<DocumentoAcademico> listar() {
		return daoDocumentoAcademico.listar( DocumentoAcademico.class);
	}
	
	@Override
	public void guardar(List<DocumentoEntregado> documentos,
		DatoAcademico datoAcademico) {
		daoDocumentoAcademico.guardar(documentos, datoAcademico);
	
	}
}
