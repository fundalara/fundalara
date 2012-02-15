package servicio.implementacion;

import java.util.List;

import dao.general.DaoLapsoDeportivo;

import modelo.DatoBasico;
import modelo.DesempennoJugador;
import modelo.LapsoDeportivo;
import servicio.interfaz.IServicioLapsoDeportivo;

public class ServicioLapsoDeportivo implements IServicioLapsoDeportivo {

	

	@Override
	public LapsoDeportivo buscarporTipoLapso(DatoBasico db) {
		return daoLapsoDeportivo.buscarPorTipoLapso(db);
	}


	
	DaoLapsoDeportivo daoLapsoDeportivo = new DaoLapsoDeportivo();
	
	public DaoLapsoDeportivo getDaoLapsoDeportivo() {
		return daoLapsoDeportivo;
	}

	public void setDaoLapsoDeportivo(DaoLapsoDeportivo daoLapsoDeportivo) {
		this.daoLapsoDeportivo = daoLapsoDeportivo;
	}
	
	@Override
	public void guardar(LapsoDeportivo ae) {
		// TODO Auto-generated method stub
		daoLapsoDeportivo.guardar(ae);
	}

	@Override
	public void actualizar(LapsoDeportivo ae) {
		// TODO Auto-generated method stub
		daoLapsoDeportivo.actualizar(ae);
	}

	@Override
	public void eliminar(LapsoDeportivo ae) {
		// TODO Auto-generated method stub
		daoLapsoDeportivo.eliminar(ae);
	}

	@Override
	public List<LapsoDeportivo> listar() {
		// TODO Auto-generated method stub
		return daoLapsoDeportivo.listar(LapsoDeportivo.class);
	}
	@Override
	public LapsoDeportivo buscarDosCampos(DatoBasico d){
		try{
			return (LapsoDeportivo) daoLapsoDeportivo.buscarDosCampos(LapsoDeportivo.class, "estatus", 'A', "datoBasico", d);
		} catch (Exception e){
			return null;
		}
	}

	@Override
	public List<LapsoDeportivo> listarActivos() {
		return daoLapsoDeportivo.listarActivos(LapsoDeportivo.class);
	}


	public List<LapsoDeportivo> buscarPorTipoLapso(DatoBasico db) {
		return daoLapsoDeportivo.buscarPorTipoLapso2(db);
	}	
	
}
