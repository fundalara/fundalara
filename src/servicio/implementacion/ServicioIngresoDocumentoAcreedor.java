package servicio.implementacion;

import java.util.List;

import dao.general.DaoIngresoDocumentoAcreedor;

import modelo.Egreso;
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
	public List<IngresoDocumentoAcreedor> listar() {
		return daoIngresoDocumentoAcreedor.listar(IngresoDocumentoAcreedor.class);
	}

	@Override
	public List<IngresoDocumentoAcreedor> listarActivos() {
		return daoIngresoDocumentoAcreedor.listarActivos(IngresoDocumentoAcreedor.class);
	}

	@Override
	public IngresoDocumentoAcreedor buscarPorCodigo (IngresoDocumentoAcreedor d) {
		// TODO Auto-generated method stub
		return null;
	}
}
