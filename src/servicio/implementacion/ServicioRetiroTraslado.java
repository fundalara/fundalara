package servicio.implementacion;

import java.util.List;

import servicio.interfaz.IServicioRetiroTraslado;

import dao.general.DaoRetiroTraslado;

import modelo.Jugador;
import modelo.RetiroTraslado;

public class ServicioRetiroTraslado implements IServicioRetiroTraslado {

	DaoRetiroTraslado daoRetiroTraslado;
	
	public DaoRetiroTraslado getDaoRetiroTraslado() {
		return daoRetiroTraslado;
	}

	public void setDaoRetiroTraslado(DaoRetiroTraslado daoRetiroTraslado) {
		this.daoRetiroTraslado = daoRetiroTraslado;
	}

	@Override
	public void eliminar(RetiroTraslado c) {
		daoRetiroTraslado.eliminar(c);
	}

	@Override
	public void agregar(RetiroTraslado c) {
		daoRetiroTraslado.guardar(c);
	}

	@Override
	public void actualizar(RetiroTraslado c) {
		daoRetiroTraslado.actualizar(c);
	}

	@Override
	public List<RetiroTraslado> listar() {
		return daoRetiroTraslado.listar( RetiroTraslado.class);
	}

	public int contarfilas (RetiroTraslado r, int operacion){
		return daoRetiroTraslado.contarfilas(r,operacion);
	}
	
	public void reingresarJugador(Jugador jugador){
		daoRetiroTraslado.reingresarJugador(jugador);
	}
		
}
