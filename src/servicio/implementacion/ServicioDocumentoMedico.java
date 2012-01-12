package servicio.implementacion;

import java.util.List;

import servicio.interfaz.IServicioDocumentoMedico;

import dao.general.DaoDocumentoMedico;

import modelo.DatoMedico;
import modelo.DocumentoEntregado;
import modelo.DocumentoMedico;


/**
 * Clase para brindar los servicios para manejar los datos relacionados con los documentos medicos de los jugadores
 * @author Robert A
 * @author German L
 * @version 0.1 02/01/2012
 *
 */
public class ServicioDocumentoMedico implements IServicioDocumentoMedico {

	DaoDocumentoMedico daoDocumentoMedico;
	
	public DaoDocumentoMedico getDaoDocumentoMedico() {
		return daoDocumentoMedico;
	}

	public void setDaoDocumentoMedico(DaoDocumentoMedico daoDocumentoMedico) {
		this.daoDocumentoMedico = daoDocumentoMedico;
	}

	@Override
	public void eliminar(DocumentoMedico c) {
		daoDocumentoMedico.eliminar(c);

	}

	@Override
	public void agregar(DocumentoMedico c) {
		daoDocumentoMedico.guardar(c);

	}

	@Override
	public void actualizar(DocumentoMedico c) {
		daoDocumentoMedico.actualizar(c);

	}

	@Override
	public List<DocumentoMedico> listar() {
		return daoDocumentoMedico.listar( DocumentoMedico.class);
	}
	
	@Override
	public void guardar(List<DocumentoEntregado> documentos,
			DatoMedico datoMedico) {
		daoDocumentoMedico.guardar(documentos, datoMedico);
		
	}

}
