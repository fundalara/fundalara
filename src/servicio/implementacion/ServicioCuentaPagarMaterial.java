package servicio.implementacion;

import java.util.List;


import dao.general.DaoCuentaPagarMaterial;

import modelo.CuentaPagar;
import modelo.CuentaPagarMaterial;
import servicio.interfaz.IServicioCuentaPagarMaterial;

public class ServicioCuentaPagarMaterial implements
		IServicioCuentaPagarMaterial {

	DaoCuentaPagarMaterial daoCuentaPagarMaterial;
	public DaoCuentaPagarMaterial getDaoCuentaPagarMaterial() {
		return daoCuentaPagarMaterial;
	}

	public void setDaoCuentaPagarMaterial(
			DaoCuentaPagarMaterial daoCuentaPagarMaterial) {
		this.daoCuentaPagarMaterial = daoCuentaPagarMaterial;
	}

	@Override
	public void eliminar(CuentaPagarMaterial c) {
		daoCuentaPagarMaterial.eliminar(c);
	}

	@Override
	public void agregar(CuentaPagarMaterial c) {
		daoCuentaPagarMaterial.guardar(c);

	}

	@Override
	public void actualizar(CuentaPagarMaterial c) {
		daoCuentaPagarMaterial.actualizar(c);

	}

	@Override
	public List<CuentaPagarMaterial> listar() {
		return daoCuentaPagarMaterial.listar(CuentaPagarMaterial.class);
	}

	@Override
	public List<CuentaPagarMaterial> listarActivos() {
		return daoCuentaPagarMaterial.listarActivos(CuentaPagarMaterial.class);
	}

	@Override
	public CuentaPagarMaterial buscarPorCodigo(CuentaPagarMaterial d) {
		// TODO Auto-generated method stub
		return null;
	}

}
