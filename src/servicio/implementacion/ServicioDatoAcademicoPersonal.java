package servicio.implementacion;

import java.util.List;

import dao.general.DaoDatoAcademicoPersonal;

import modelo.DatoAcademicoPersonal;
import servicio.interfaz.IServicioDatoAcademicoPersonal;

public class ServicioDatoAcademicoPersonal implements
		IServicioDatoAcademicoPersonal {
	
	DaoDatoAcademicoPersonal daoDatoAcademicoPersonal;
	@Override
	public void eliminar(DatoAcademicoPersonal c) {
		daoDatoAcademicoPersonal.eliminar(c);

	}

	@Override
	public void agregar(DatoAcademicoPersonal c) {
		daoDatoAcademicoPersonal.guardar(c);

	}

	@Override
	public void actualizar(DatoAcademicoPersonal c) {
		daoDatoAcademicoPersonal.actualizar(c);
	}

	@Override
	public List listar() {
		// TODO Auto-generated method stub
		return daoDatoAcademicoPersonal.listar(new DatoAcademicoPersonal());
	}

	public DaoDatoAcademicoPersonal getDaoDatoAcademicoPersonal() {
		return daoDatoAcademicoPersonal;
	}

	public void setDaoDatoAcademicoPersonal(
			DaoDatoAcademicoPersonal daoDatoAcademicoPersonal) {
		this.daoDatoAcademicoPersonal = daoDatoAcademicoPersonal;
	}

}
