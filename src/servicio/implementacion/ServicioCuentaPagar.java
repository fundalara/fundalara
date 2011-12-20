package servicio.implementacion;

import java.util.List;

import servicio.interfaz.IServicioCuentaPagar;

import dao.general.DaoCuentaPagar;

import modelo.CuentaPagar;


public class ServicioCuentaPagar implements IServicioCuentaPagar {

	DaoCuentaPagar daoCuentaPagar;
	public DaoCuentaPagar getDaoCuentaPagar() {
		return daoCuentaPagar;
	}

	public void setDaoCuentaPagar(DaoCuentaPagar daoCuentaPagar) {
		this.daoCuentaPagar = daoCuentaPagar;
	}

	@Override
	public void eliminar(CuentaPagar c) {
		daoCuentaPagar.eliminar(c);

	}

	@Override
	public void agregar(CuentaPagar c) {
		daoCuentaPagar.guardar(c);

	}

	@Override
	public void actualizar(CuentaPagar c) {
		daoCuentaPagar.actualizar(c);

	}

	@Override
	public List listar() {
		// TODO Auto-generated method stub
		return daoCuentaPagar.listar(new CuentaPagar());
	}

}
