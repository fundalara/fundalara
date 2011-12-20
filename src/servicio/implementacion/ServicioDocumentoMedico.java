package servicio.implementacion;

import java.util.List;

import servicio.interfaz.IServicioDocumentoMedico;

import dao.general.DaoDocumentoMedico;

import modelo.DocumentoMedico;

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

}
