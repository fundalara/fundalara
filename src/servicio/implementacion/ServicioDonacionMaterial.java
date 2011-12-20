package servicio.implementacion;

import java.util.List;

import dao.general.DaoDonacionMaterial;

import modelo.DocumentoAcreedorMaterial;
import modelo.DonacionMaterial;
import servicio.interfaz.IServicioDonacionMaterial;

public class ServicioDonacionMaterial implements IServicioDonacionMaterial {
	
	DaoDonacionMaterial daoDonacionMaterial;
	public DaoDonacionMaterial getDaoDonacionMaterial() {
		return daoDonacionMaterial;
	}

	public void setDaoDonacionMaterial(DaoDonacionMaterial daoDonacionMaterial) {
		this.daoDonacionMaterial = daoDonacionMaterial;
	}

	@Override
	public void eliminar(DonacionMaterial c) {
		daoDonacionMaterial.eliminar(c);

	}

	@Override
	public void agregar(DonacionMaterial c) {
		daoDonacionMaterial.guardar(c);
	}

	@Override
	public void actualizar(DonacionMaterial c) {
		daoDonacionMaterial.actualizar(c);

	}

	@Override
	public List<DonacionMaterial> listar() {
		return daoDonacionMaterial.listar(DonacionMaterial.class);
	}

	@Override
	public List<DonacionMaterial> listarActivos() {
		return daoDonacionMaterial.listarActivos(DonacionMaterial.class);
	}

	@Override
	public DonacionMaterial buscarPorCodigo(DonacionMaterial d) {
		// TODO Auto-generated method stub
		return null;
	}

}
