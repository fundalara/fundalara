package servicio.implementacion;

import java.util.List;

import dao.general.DaoIngresoDocumentoAcreedor;

import modelo.IngresoDocumentoAcreedor;
import servicio.interfaz.IServicioIngresoDocumentoAcreedor;

public class ServicioIngresoDocumentoAcreedor implements
		IServicioIngresoDocumentoAcreedor {
	
	DaoIngresoDocumentoAcreedor daoIngresoDocumentoAcreedor;
	
	public DaoIngresoDocumentoAcreedor getDaoIngresoDocumentoAcreedor() {
		return daoIngresoDocumentoAcreedor;
	}

	public void setDaoIngresoDocumentoAcreedor(
			DaoIngresoDocumentoAcreedor daoIngresoDocumentoAcreedor) {
		this.daoIngresoDocumentoAcreedor = daoIngresoDocumentoAcreedor;
	}

	@Override
	public void eliminar(IngresoDocumentoAcreedor c) {
		daoIngresoDocumentoAcreedor.eliminar(c);

	}

	@Override
	public void agregar(IngresoDocumentoAcreedor c) {
		daoIngresoDocumentoAcreedor.guardar(c);

	}

	@Override
	public void actualizar(IngresoDocumentoAcreedor c) {
		daoIngresoDocumentoAcreedor.actualizar(c);

	}

	@Override
	public List listar() {
		// TODO Auto-generated method stub
		return daoIngresoDocumentoAcreedor.listar(new IngresoDocumentoAcreedor());
	}

}
