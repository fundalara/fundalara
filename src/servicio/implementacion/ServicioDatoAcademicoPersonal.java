package servicio.implementacion;

import java.util.List;

import dao.general.DaoDatoAcademicoPersonal;

import modelo.CuentaPagarMaterial;
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
	public List<DatoAcademicoPersonal> listar() {
		return daoDatoAcademicoPersonal.listar(DatoAcademicoPersonal.class);
	}

	@Override
	public List<DatoAcademicoPersonal> listarActivos() {
		return daoDatoAcademicoPersonal.listarActivos(DatoAcademicoPersonal.class);
	}

	@Override
	public DatoAcademicoPersonal buscarPorCodigo(DatoAcademicoPersonal d) {
		// TODO Auto-generated method stub
		return null;
	}

	public DaoDatoAcademicoPersonal getDaoDatoAcademicoPersonal() {
		return daoDatoAcademicoPersonal;
	}

	public void setDaoDatoAcademicoPersonal(
			DaoDatoAcademicoPersonal daoDatoAcademicoPersonal) {
		this.daoDatoAcademicoPersonal = daoDatoAcademicoPersonal;
	}

}
