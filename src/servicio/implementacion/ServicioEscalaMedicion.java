package servicio.implementacion;

import java.util.List;

import servicio.interfaz.IServicioEscalaMedicion;

import dao.general.DaoEscalaMedicion;

import modelo.DatoBasico;
import modelo.EscalaMedicion;

public class ServicioEscalaMedicion implements IServicioEscalaMedicion {

	DaoEscalaMedicion daoEscalaMedicion;

	public DaoEscalaMedicion getDaoEscalaMedicion() {
		return daoEscalaMedicion;
	}

	public void setDaoEscalaMedicion(DaoEscalaMedicion daoEscalaMedicion) {
		this.daoEscalaMedicion = daoEscalaMedicion;
	}

	@Override
	public void eliminar(EscalaMedicion e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void agregar(EscalaMedicion e) {
		daoEscalaMedicion.guardar(e);
	}

	@Override
	public void actualizar(EscalaMedicion e) {
		daoEscalaMedicion.actualizar(e);
	}

	@Override
	public List<EscalaMedicion> listar() {
		return  daoEscalaMedicion.listarActivos(EscalaMedicion.class);
	}

	@Override
	public EscalaMedicion buscar(Integer codigo) {
		return daoEscalaMedicion.buscarCodigo(codigo);		
	}

	public List<EscalaMedicion> listarPorTipoEscala(DatoBasico db){
		return daoEscalaMedicion.listarPorTipoEscala(db);
	}
	
	public int generarCodigo(){
		return daoEscalaMedicion.generarCodigo(EscalaMedicion.class);
	}
}
