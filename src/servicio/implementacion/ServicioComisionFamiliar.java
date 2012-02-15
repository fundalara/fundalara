package servicio.implementacion;

import java.util.List;

import dao.general.DaoComisionFamiliar;

import modelo.ComisionActividad;
import modelo.ComisionFamiliar;
import modelo.DatoBasico;
import modelo.Familiar;
import servicio.interfaz.IServicioComisionFamiliar;

public class ServicioComisionFamiliar implements IServicioComisionFamiliar {

	DaoComisionFamiliar daoComisionFamiliar;
	
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
	
	
	///
	
	
	@Override
	public void eliminar(ComisionFamiliar c) {
		daoComisionFamiliar.eliminar(c);
	}

	@Override
	public void agregar(ComisionFamiliar c) {
		daoComisionFamiliar.guardar(c);
	}

	@Override
	public void actualizar(ComisionFamiliar c) {
		daoComisionFamiliar.actualizar(c);
	}

	@Override
	public List<ComisionFamiliar> listar() {
		return daoComisionFamiliar.listar(ComisionFamiliar.class);
	}
	
	
	public void agregar(List<ComisionFamiliar> comisiones) {
		daoComisionFamiliar.guardar(comisiones);
	}
	
	
	public List<DatoBasico> buscarComisiones(Familiar familiar) {
		return daoComisionFamiliar.buscarComisiones(familiar);
	}

}
