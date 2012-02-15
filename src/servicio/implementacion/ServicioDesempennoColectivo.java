package servicio.implementacion;

import java.util.List;

import dao.general.DaoDesempennoColectivo;

import modelo.DesempennoColectivo;
import modelo.EquipoJuego;
import modelo.IndicadorCategoriaCompetencia;
import servicio.interfaz.IServicioDesempennoColectivo;

public class ServicioDesempennoColectivo implements
		IServicioDesempennoColectivo {
	
	DaoDesempennoColectivo daoDesempennoColectivo;

	public DaoDesempennoColectivo getDaoDesempennoColectivo() {
		return daoDesempennoColectivo;
	}

	public void setDaoDesempennoColectivo(
			DaoDesempennoColectivo daoDesempennoColectivo) {
		this.daoDesempennoColectivo = daoDesempennoColectivo;
	}

	@Override
	public void eliminar(DesempennoColectivo d) {
		// TODO Auto-generated method stub

	}

	@Override
	public void agregar(DesempennoColectivo d) {
		if (d.getCodigoDesempennoColectivo() == 0){
			int codigo = daoDesempennoColectivo.listar(DesempennoColectivo.class).size()+1;
			d.setCodigoDesempennoColectivo(codigo);
		}
		daoDesempennoColectivo.guardar(d);

	}

	@Override
	public List<DesempennoColectivo> listar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DesempennoColectivo> listarActivos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DesempennoColectivo buscarCarrerasPorEquipo(EquipoJuego ej,
			IndicadorCategoriaCompetencia icc, int inning) {		
		return daoDesempennoColectivo.buscarCarrerasPorEquipo(ej, icc, inning);
	}

}
