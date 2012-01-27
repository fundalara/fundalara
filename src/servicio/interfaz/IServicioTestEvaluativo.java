package servicio.interfaz;

import java.util.List;

import modelo.TestEvaluativo;;

public interface IServicioTestEvaluativo {
	public abstract void guardar(TestEvaluativo ae);
	
	public abstract void actualizar(TestEvaluativo ae);
	
	public abstract void eliminar(TestEvaluativo ae);
	
	public abstract List<TestEvaluativo> listar(); 
}
