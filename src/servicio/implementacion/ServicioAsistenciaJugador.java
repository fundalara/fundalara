package servicio.implementacion;

import java.util.List;

import dao.general.DaoAsistenciaJugador;

import modelo.AsistenciaJugador;
import servicio.interfaz.IServicioAsistenciaJugador;

public class ServicioAsistenciaJugador implements IServicioAsistenciaJugador {

	DaoAsistenciaJugador daoAsistenciaJugador;
	@Override
	public void guardar(AsistenciaJugador aj) {
		// TODO Auto-generated method stub
		daoAsistenciaJugador.guardar(aj);
	}

	@Override
	public void actualizar(AsistenciaJugador aj) {
		// TODO Auto-generated method stub
		daoAsistenciaJugador.actualizar(aj);
	}

	@Override
	public void eliminar(AsistenciaJugador aj) {
		// TODO Auto-generated method stub
		daoAsistenciaJugador.eliminar(aj);
	}

	@Override
	public List<AsistenciaJugador> listar() {
		// TODO Auto-generated method stub
		return daoAsistenciaJugador.listar(AsistenciaJugador.class);
	}

	public DaoAsistenciaJugador getDaoAsistenciaJugador() {
		return daoAsistenciaJugador;
	}

	public void setDaoAsistenciaJugador(DaoAsistenciaJugador daoAsistenciaJugador) {
		this.daoAsistenciaJugador = daoAsistenciaJugador;
	}
	
}
