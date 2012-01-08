package servicio.interfaz;

import java.util.List;

import comun.TipoDatoBasico;

import modelo.DatoBasico;
import modelo.TipoDato;

public interface IServicioDatoBasico {
	public abstract void eliminar(DatoBasico d);
	
	public abstract void agregar(DatoBasico d);
		
	public abstract void actualizar(DatoBasico d);	
	
	public abstract List<DatoBasico> listar();	
	
	public abstract List<DatoBasico> buscarPorTipoDato(TipoDato td);
	
	public abstract DatoBasico buscarPorCodigo(String td);
	
	public abstract List<DatoBasico> listarParroquias();
	
	public abstract DatoBasico buscarPorCodigo(Integer i);
	
	public abstract List<DatoBasico> buscar(TipoDatoBasico tipoDato);
	
	public List<DatoBasico> buscarDatosPorRelacion(DatoBasico datoBasico);


}
