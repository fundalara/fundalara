package servicio.implementacion;

import java.util.List;

import dao.general.DaoDocumentoAcreedor;

import modelo.CuentaPagar;
import modelo.DocumentoAcreedor;
import servicio.interfaz.IServicioDocumentoAcreedor;

public class ServicioDocumentoAcreedor implements IServicioDocumentoAcreedor {
	
	DaoDocumentoAcreedor daoDocumentoAcreedor;
	public DaoDocumentoAcreedor getDaoDocumentoAcreedor() {
		return daoDocumentoAcreedor;
	}

	public void setDaoDocumentoAcreedor(DaoDocumentoAcreedor daoDocumentoAcreedor) {
		this.daoDocumentoAcreedor = daoDocumentoAcreedor;
	}

	@Override
	public void eliminar(DocumentoAcreedor c) {
		daoDocumentoAcreedor.eliminar(c);

	}

	@Override
	public void agregar(DocumentoAcreedor c) {
		daoDocumentoAcreedor.guardar(c);

	}

	@Override
	public void actualizar(DocumentoAcreedor c) {
		daoDocumentoAcreedor.actualizar(c);

	}

	@Override
	public List<DocumentoAcreedor> listar() {
		return daoDocumentoAcreedor.listar(DocumentoAcreedor.class);
	}

	@Override
	public List<DocumentoAcreedor> listarActivos() {
		return daoDocumentoAcreedor.listarActivos(DocumentoAcreedor.class);
	}

	@Override
	public DocumentoAcreedor buscarPorCodigo(DocumentoAcreedor d) {
		// TODO Auto-generated method stub
		return null;
	}

}
