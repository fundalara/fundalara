package servicio.implementacion;

import java.util.List;

import dao.general.DaoDocumentoAcreedorMaterial;

import modelo.DocumentoAcreedor;
import modelo.DocumentoAcreedorMaterial;
import servicio.interfaz.IServicioDocumentoAcreedorMaterial;

public class ServicioDocumentoAcreedorMaterial implements
		IServicioDocumentoAcreedorMaterial {

	DaoDocumentoAcreedorMaterial daoDocumentoAcreedorMaterial;
	public DaoDocumentoAcreedorMaterial getDaoDocumentoAcreedorMaterial() {
		return daoDocumentoAcreedorMaterial;
	}

	public void setDaoDocumentoAcreedorMaterial(
			DaoDocumentoAcreedorMaterial daoDocumentoAcreedorMaterial) {
		this.daoDocumentoAcreedorMaterial = daoDocumentoAcreedorMaterial;
	}

	@Override
	public void eliminar(DocumentoAcreedorMaterial c) {
		daoDocumentoAcreedorMaterial.eliminar(c);

	}

	@Override
	public void agregar(DocumentoAcreedorMaterial c) {
		daoDocumentoAcreedorMaterial.guardar(c);

	}

	@Override
	public void actualizar(DocumentoAcreedorMaterial c) {
		daoDocumentoAcreedorMaterial.actualizar(c);

	}

	@Override
	public List<DocumentoAcreedorMaterial> listar() {
		return daoDocumentoAcreedorMaterial.listar(DocumentoAcreedorMaterial.class);
	}

	@Override
	public List<DocumentoAcreedorMaterial> listarActivos() {
		return daoDocumentoAcreedorMaterial.listarActivos(DocumentoAcreedorMaterial.class);
	}

	@Override
	public DocumentoAcreedorMaterial buscarPorCodigo(DocumentoAcreedorMaterial d) {
		// TODO Auto-generated method stub
		return null;
	}

}
