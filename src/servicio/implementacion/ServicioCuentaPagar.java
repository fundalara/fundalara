package servicio.implementacion;
import java.text.ParseException;
import java.util.List;
import servicio.interfaz.IServicioCuentaPagar;

import dao.general.DaoCuentaPagar;

import modelo.CuentaPagar;
import modelo.DatoBasico;
import modelo.Divisa;
import modelo.Persona;


public class ServicioCuentaPagar implements IServicioCuentaPagar {

	DaoCuentaPagar daoCuentaPagar;
	public DaoCuentaPagar getDaoCuentaPagar() {
		return daoCuentaPagar;
	}

	public void setDaoCuentaPagar(DaoCuentaPagar daoCuentaPagar) {
		this.daoCuentaPagar = daoCuentaPagar;
	}

	@Override
	public void eliminar(CuentaPagar c) {
		daoCuentaPagar.eliminar(c);

	}

	@Override
	public void agregar(CuentaPagar c) {
		daoCuentaPagar.guardar(c);

	}

	@Override
	public void actualizar(CuentaPagar c) {
		daoCuentaPagar.actualizar(c);

	}


	
	@Override
	public List<CuentaPagar> listar() {
		return daoCuentaPagar.listar(CuentaPagar.class);
	}

	@Override
	public List<CuentaPagar> listarActivos() {
		return daoCuentaPagar.listarActivos(CuentaPagar.class);
	}

	@Override
	public CuentaPagar buscarPorCodigo(CuentaPagar d) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<CuentaPagar> listarCuentaPorPagar(DatoBasico dato) {
		return  daoCuentaPagar.listarCuentaPorPagar(dato);
	}

	@Override
	public CuentaPagar buscarNumeroDocumento(String cp) {
		return daoCuentaPagar.buscarNumeroDocumento(cp);
	}

	public List<CuentaPagar> listarCuentaPorPagarFiltro(DatoBasico d, String s){
		return daoCuentaPagar.listarCuentaPorPagarFiltro(d, s);
	}
	
	public List<CuentaPagar> buscarPendientesPorRif(Persona persona) {
		return daoCuentaPagar.buscarPendientesPorRif(persona);
	}

	public List<CuentaPagar> buscarPendienteporTipoPersona(String nombre) {
		return daoCuentaPagar.buscarPendienteporPersona(nombre);
	}
	public List<CuentaPagar> BuscarPorPersona(Persona persona) {
		return daoCuentaPagar.BuscarPorPersona(persona);
	}
	
	public List<CuentaPagar> listarCuentaPorPagarPorFecha(DatoBasico dato, String inicio, String fin, String filtro) throws ParseException{
		return daoCuentaPagar.listarCuentaPorPagarPorFecha(dato,inicio,fin,filtro);
	}
	
	public List<CuentaPagar> listarCuentaPorPagarPorFechaPersona(String inicio,
			String fin, String paramEstado, Persona persona) throws ParseException {
		return daoCuentaPagar.listarCuentaPorPagarPorFechaPersona(inicio, fin, paramEstado, persona);
	}

	public List<CuentaPagar> listarCuentaPorPagarFiltroPersona(
			String paramEstado, Persona persona) {
		return daoCuentaPagar.listarCuentaPorPagarFiltroPersona(paramEstado, persona);
	}
	
	public List<CuentaPagar> listarPorFecha( String inicio, String fin,Persona persona) throws ParseException{
		return daoCuentaPagar.listarPorFecha(inicio,fin,persona);
	}
	@Override
	public List<CuentaPagar> listarCuentaPorPagarPorFecha( String inicio, String fin, String filtro,Persona persona) throws ParseException{
		return daoCuentaPagar.listarCuentaPorPagarPorFechaPersona(inicio, fin, filtro, persona);
	}
	@Override
	public List<CuentaPagar> listarCuentaPorPagarFiltro( String s,Persona persona){
		return daoCuentaPagar.listarCuentaPorPagarFiltroPersona(s, persona);
	}	

}
