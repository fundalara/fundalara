package servicio.implementacion;

import java.util.List;

import servicio.interfaz.IServicioDocumentoAscenso;

import dao.general.DaoDocumentoAscenso;

import modelo.DocumentoAscenso;

public class ServicioDocumentoAscenso implements IServicioDocumentoAscenso {

	DaoDocumentoAscenso daoDocumentoAscenso;
	
	public DaoDocumentoAscenso getDaoDocumentoAscenso() {
		return daoDocumentoAscenso;
	}

	public void setDaoDocumentoAscenso(DaoDocumentoAscenso daoDocumentoAscenso) {
		this.daoDocumentoAscenso = daoDocumentoAscenso;
	}

	@Override
	public void eliminar(DocumentoAscenso c) {
		daoDocumentoAscenso.eliminar(c);

	}

	@Override
	public void agregar(DocumentoAscenso c) {
		daoDocumentoAscenso.guardar(c);

	}

	@Override
	public void actualizar(DocumentoAscenso c) {
		daoDocumentoAscenso.actualizar(c);

	}

	@Override
	public List<DocumentoAscenso> listar() {
		return daoDocumentoAscenso.listar( DocumentoAscenso.class);
	}

}
