package servicio.interfaz;

import java.util.List;

import modelo.DatoBasico;
import modelo.TallaPorIndumentaria;

public interface IServicioTallaPorIndumentaria {
	
	public abstract void eliminar(TallaPorIndumentaria c);

	public abstract void agregar(TallaPorIndumentaria c);

	public abstract void actualizar(TallaPorIndumentaria c);

	public abstract List<TallaPorIndumentaria> listar();

	public abstract List<TallaPorIndumentaria> listarActivosOrdenados();

	public abstract TallaPorIndumentaria buscarPorDatoBasico(DatoBasico datoBasico);
}
