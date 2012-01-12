package servicio.implementacion;

import java.util.List;

import dao.general.DaoIndicadorTest;

import modelo.IndicadorTest;
import servicio.interfaz.IServicioIndicadorTest;

public class ServicioIndicadorTest implements IServicioIndicadorTest {
	
	DaoIndicadorTest daoIndicadorTest;
	
	

	@Override
	public void guardar(IndicadorTest it) {
		daoIndicadorTest.guardar(it);

	}

	@Override
	public void actualizar(IndicadorTest it) {
		daoIndicadorTest.actualizar(it);

	}

	@Override
	public void eliminar(IndicadorTest it) {
		daoIndicadorTest.eliminar(it);

	}

	@Override
	public List<IndicadorTest> listar() {
		return daoIndicadorTest.listar(IndicadorTest.class);
	}

	public DaoIndicadorTest getDaoIndicadorTest() {
		return daoIndicadorTest;
	}

	public void setDaoIndicadorTest(DaoIndicadorTest daoIndicadorTest) {
		this.daoIndicadorTest = daoIndicadorTest;
	}

	
}
