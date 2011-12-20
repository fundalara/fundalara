package servicio.implementacion;

import java.util.List;

import dao.general.DaoTipoDato;

import servicio.interfaz.IServicioTipoDato;

public class ServicioTipoDato implements IServicioTipoDato {
	DaoTipoDato daoTipoDato;
	
	public DaoTipoDato getDaoTipoDato() {
		return daoTipoDato;
	}

	public void setDaoTipoDato(DaoTipoDato daoTipoDato) {
		this.daoTipoDato = daoTipoDato;
	}

	

	@Override
	public void guardar(ServicioTipoDato s) {
		// TODO Auto-generated method stub
daoTipoDato.guardar(s);
	}

	@Override
	public void actualizar(ServicioTipoDato s) {
		// TODO Auto-generated method stub
daoTipoDato.actualizar(s);
	}

	@Override
	public void eliminar(ServicioTipoDato s) {
		// TODO Auto-generated method stub
daoTipoDato.eliminar(s);
	}

	@Override
	public List<ServicioTipoDato> listar() {
		// TODO Auto-generated method stub
		return daoTipoDato.listar(new DaoTipoDato());
	}

}
