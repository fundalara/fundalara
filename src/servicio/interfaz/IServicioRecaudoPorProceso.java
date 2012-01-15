package servicio.interfaz;

import java.util.List;

import comun.TipoDatoBasico;

import modelo.DatoBasico;
import modelo.RecaudoPorProceso;

public interface IServicioRecaudoPorProceso {
	
	public abstract void eliminar(RecaudoPorProceso c);

	public abstract void agregar(RecaudoPorProceso c);

	public abstract void actualizar(RecaudoPorProceso c);

	public abstract List<RecaudoPorProceso> listar();
	
	public abstract List<RecaudoPorProceso> buscarPorProceso(
			DatoBasico proceso, TipoDatoBasico tipoDocumento, String nombre);

}
