package servicio.implementacion;

import java.util.List;

import modelo.PuntuacionJugador;
import servicio.interfaz.IServicioPuntuacionJugador;
import dao.general.DaoPuntuacionJugador;

public class ServicioPuntuacionJugador implements IServicioPuntuacionJugador{
	
	DaoPuntuacionJugador daoPuntuacionJugador;

	@Override
	public void eliminar(PuntuacionJugador pj) {
		daoPuntuacionJugador.eliminar(pj);
	}

	@Override
	public List<PuntuacionJugador> listar() {
		// TODO Auto-generated method stub
		return daoPuntuacionJugador.listar(PuntuacionJugador.class);
	}

	public DaoPuntuacionJugador getDaoPuntuacionJugador() {
		return daoPuntuacionJugador;
	}

	public void setDaoPuntuacionJugador(DaoPuntuacionJugador daoPuntuacionJugador) {
		this.daoPuntuacionJugador = daoPuntuacionJugador;
	}

	@Override
	public void agregar(PuntuacionJugador ps) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actualizar(PuntuacionJugador ps) {
		// TODO Auto-generated method stub
		
	}

}
