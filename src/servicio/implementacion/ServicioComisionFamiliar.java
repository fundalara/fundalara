package servicio.implementacion;

import java.util.List;

import dao.general.DaoComisionFamiliar;

import modelo.ComisionActividad;
import modelo.ComisionFamiliar;
import modelo.DatoBasico;
import servicio.interfaz.IServicioComisionFamiliar;

public class ServicioComisionFamiliar implements IServicioComisionFamiliar {

	DaoComisionFamiliar daoComisionFamiliar;
	@Override
	public void eliminar(ComisionFamiliar cf) {
		daoComisionFamiliar.eliminar(cf);

	}

	@Override
	public void agregar(ComisionFamiliar cf) {
		daoComisionFamiliar.guardar(cf);

	}

	@Override
	public void actualizar(ComisionFamiliar cf) {
		daoComisionFamiliar.actualizar(cf);

	}

	@Override
	public List<ComisionFamiliar> listar() {
		// TODO Auto-generated method stub
		return daoComisionFamiliar.listar(ComisionFamiliar.class);
	}

	@Override
	public List<ComisionFamiliar> listarRepresentantesPorComision(
			DatoBasico comision) {
		// TODO Auto-generated method stub
		return daoComisionFamiliar.listarRepresentantesPorComision(comision);
	}

	public DaoComisionFamiliar getDaoComisionFamiliar() {
		return daoComisionFamiliar;
	}

	public void setDaoComisionFamiliar(DaoComisionFamiliar daoComisionFamiliar) {
		this.daoComisionFamiliar = daoComisionFamiliar;
	}

	@Override
	public ComisionFamiliar buscar(String cedulaRif) {
		return daoComisionFamiliar.buscar(cedulaRif);
	}

	@Override
	public List<ComisionFamiliar> ListarPorComision(
			ComisionActividad comisionActividad) {
		// TODO Auto-generated method stub
		return this.daoComisionFamiliar.listarPorComision(comisionActividad);
	}

}
