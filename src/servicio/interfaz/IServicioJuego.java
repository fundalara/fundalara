package servicio.interfaz;

import modelo.Categoria;
import modelo.Competencia;
import modelo.DatoBasico;
import modelo.FaseCompetencia;
import modelo.Juego;
import java.util.List;

import modelo.Juego;

public interface IServicioJuego {

	public abstract void eliminar(Juego j);

	public abstract void agregar(Juego j);

	public abstract List<Juego> listar();

	public abstract List<Juego> listarActivos();

	public abstract int listarJuegosProgramados(DatoBasico datoBasico);

	public abstract Juego buscarJuego(int codigo);

	public abstract List<Juego> listarJuegosPorFaseCompetenciaYCategoria(
			Competencia competencia, FaseCompetencia faseCompetencia,Categoria categoria);

}
