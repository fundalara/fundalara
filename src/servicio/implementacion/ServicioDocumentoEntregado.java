package servicio.implementacion;

import java.util.List;

import servicio.interfaz.IServicioDocumentoEntregado;

import dao.general.DaoDocumentoEntregado;

import modelo.DocumentoEntregado;

public class ServicioDocumentoEntregado implements IServicioDocumentoEntregado {

	DaoDocumentoEntregado daoDocumentoEntregado;
	
	public DaoDocumentoEntregado getDaoDocumentoEntregado() {
		return daoDocumentoEntregado;
	}

	public void setDaoDocumentoEntregado(DaoDocumentoEntregado daoDocumentoEntregado) {
		this.daoDocumentoEntregado = daoDocumentoEntregado;
	}

	@Override
	public void eliminar(DocumentoEntregado c) {
		daoDocumentoEntregado.eliminar(c);

	}

	@Override
	public void agregar(DocumentoEntregado c) {
		daoDocumentoEntregado.guardar(c);

	}

	@Override
	public void actualizar(DocumentoEntregado c) {
		daoDocumentoEntregado.actualizar(c);

	}

	@Override
	public List<DocumentoEntregado> listar() {
		return daoDocumentoEntregado.listar( DocumentoEntregado.class);
	}

}
