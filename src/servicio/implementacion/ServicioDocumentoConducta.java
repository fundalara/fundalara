package servicio.implementacion;

import java.util.List;

import servicio.interfaz.IServicioDocumentoConducta;

import dao.general.DaoDocumentoConducta;

import modelo.DocumentoConducta;

public class ServicioDocumentoConducta implements IServicioDocumentoConducta {
	
	DaoDocumentoConducta daoDocumentoConducta;
	
	public DaoDocumentoConducta getDaoDocumentoConducta() {
		return daoDocumentoConducta;
	}

	public void setDaoDocumentoConducta(DaoDocumentoConducta daoDocumentoConducta) {
		this.daoDocumentoConducta = daoDocumentoConducta;
	}

	@Override
	public void eliminar(DocumentoConducta c) {
		daoDocumentoConducta.eliminar(c);

	}

	@Override
	public void agregar(DocumentoConducta c) {
		daoDocumentoConducta.guardar(c);
	}

	@Override
	public void actualizar(DocumentoConducta c) {
		daoDocumentoConducta.actualizar(c);

	}

	@Override
	public List<DocumentoConducta> listar() {
		return daoDocumentoConducta.listar( DocumentoConducta.class);
	}

}
