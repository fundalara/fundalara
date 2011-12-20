package servicio.implementacion;



import java.util.List;

import dao.general.DaoDocumentoIndumentaria;

import modelo.DocumentoIndumentaria;
import servicio.interfaz.IServicioDocumentoIndumentaria;

public class ServicioDocumentoIndumentaria implements
		IServicioDocumentoIndumentaria {
	
	DaoDocumentoIndumentaria daoDocumentoIndumentaria;
	public DaoDocumentoIndumentaria getDaoDocumentoIndumentaria() {
		return daoDocumentoIndumentaria;
	}

	public void setDaoDocumentoIndumentaria(
			DaoDocumentoIndumentaria daoDocumentoIndumentaria) {
		this.daoDocumentoIndumentaria = daoDocumentoIndumentaria;
	}

	@Override
	public void eliminar(DocumentoIndumentaria c) {
		daoDocumentoIndumentaria.eliminar(c);

	}

	@Override
	public void agregar(DocumentoIndumentaria c) {
		daoDocumentoIndumentaria.guardar(c);

	}

	@Override
	public void actualizar(DocumentoIndumentaria c) {
		daoDocumentoIndumentaria.actualizar(c);
	}

	@Override
	public List listar() {
		// TODO Auto-generated method stub
		return daoDocumentoIndumentaria.listar(new DocumentoIndumentaria());
	}

}
