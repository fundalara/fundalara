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
	
	public abstract List<DatoBasico> listarEstados();
	
	public abstract List<DatoBasico> listarMunicipios();
	
	public abstract List<DatoBasico> listarMunicipiosPorEstados(DatoBasico db );
	
	public abstract List<DatoBasico> listarParroquiasPorMunicipios(DatoBasico db );
	
	public abstract List<DatoBasico> listarPosiciones();
	
	public abstract List<DatoBasico> buscar(TipoDatoBasico tipoDato);
	
	public List<DatoBasico> buscarDatosPorRelacion(DatoBasico datoBasico);

	public abstract DatoBasico buscarTipo(TipoDatoBasico tipoDato, String nombre);
	
	public abstract List<DatoBasico> listarOrganizacionCompetencia();	
	
	public abstract List<DatoBasico> listarPosicionesJugadores();
	
	public List<DatoBasico>listarTipoCompetencia();
	
	public List<DatoBasico> listarCondicion ();
	
	public abstract List<DatoBasico> listarTipoDato(String nombre);
	
	public abstract List<DatoBasico> listarComisiones();
	
	public abstract List<DatoBasico> listarTipoInstalacion();
	
	public abstract List<DatoBasico> listarTipoMantenimiento();

	public abstract List<DatoBasico> listarPersonalForaneo(DatoBasico datoBasico);
}
