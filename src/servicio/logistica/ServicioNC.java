package servicio.logistica;

import java.util.List;

import dao.logistica.NConfigurablesDAO;
import modelo.Comision;
import modelo.Material;
import modelo.TipoMaterial;

public class ServicioNC implements IServicioNC {
	
	NConfigurablesDAO ncDao;

	public NConfigurablesDAO getNcDao() {
		return ncDao;
	}

	public void setNcDao(NConfigurablesDAO ncDao) {
		this.ncDao = ncDao;
	}

	@Override
	public void eliminar(Comision m) {
		// TODO Auto-generated method stub

	}

	@Override
	public void agregar(Comision m) {
		// TODO Auto-generated method stub
		ncDao.guardar(m);

	}

	@Override
	public void actualizar(Comision m) {
		ncDao.actualizar(m);
		// TODO Auto-generated method stub

	}
	
//	public String generarCodigo(){
//		return "csm-" + Double.toString(ncDao.contarCodigos(new Comision())+Math.ceil(Math.random()*100));
//	}

	@Override
	public List<Comision> listarComision() {
		// TODO Auto-generated method stub
		return ncDao.listarComisiones();
	}
	
	public String generarCodigo(){
		Integer nuevoCodigo = ncDao.contarCodigos(new Comision()) + 1;
		return nuevoCodigo.toString();
	}

}
