package servicio.interfaz;

import java.util.List;

import modelo.IndicadorTest;

public interface IServicioIndicadorTest {
	
	
	public abstract void guardar(IndicadorTest it);

	public abstract void actualizar(IndicadorTest it);

	public abstract void eliminar(IndicadorTest it);

	public abstract List<IndicadorTest> listar();

}
