package servicio.implementacion;

import java.util.Iterator;
import java.util.List;

import dao.general.DaoEquipoCompetencia;

import modelo.Competencia;
import modelo.ConstanteCategoria;
import modelo.EquipoCompetencia;
import servicio.interfaz.IServicioEquipoCompetencia;

public class ServicioEquipoCompetencia implements IServicioEquipoCompetencia {

	DaoEquipoCompetencia daoEquipoCompetencia;

	public DaoEquipoCompetencia getDaoEquipoCompetencia() {
		return daoEquipoCompetencia;
	}

	public void setDaoEquipoCompetencia(
			DaoEquipoCompetencia daoEquipoCompetencia) {
		this.daoEquipoCompetencia = daoEquipoCompetencia;
	}

	@Override
	public void eliminar(List l) {
		// TODO Auto-generated method stub
		for (Iterator i = l.iterator(); i.hasNext();) {
			EquipoCompetencia id = (EquipoCompetencia) i.next();
			id.setEstatus('E');
			daoEquipoCompetencia.eliminar(id);
		}

	}

	public void actualizar(List<EquipoCompetencia> l) {
		// TODO Auto-generated method stub
		for (Iterator i = l.iterator(); i.hasNext();) {
			EquipoCompetencia id = (EquipoCompetencia) i.next();
			if (id.getCodigoEquipoCompetencia()==0){
				int codEquipoCompetencia = daoEquipoCompetencia.listar(EquipoCompetencia.class).size() + 1;
				id.setCodigoEquipoCompetencia(codEquipoCompetencia);
			}
			daoEquipoCompetencia.actualizar(id);
			
			
		}

	}

	@Override
	public void agregar(List<EquipoCompetencia> l) {
		// TODO Auto-generated method stub
		for (Iterator i = l.iterator(); i.hasNext();) {
			EquipoCompetencia id = (EquipoCompetencia) i.next();
			int codEquipoCompetencia = daoEquipoCompetencia.listar(
					EquipoCompetencia.class).size() + 1;
			id.setCodigoEquipoCompetencia(codEquipoCompetencia);//
			daoEquipoCompetencia.guardar(id);
		}

	}

	@Override
	public List<EquipoCompetencia> listar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EquipoCompetencia> listarActivos() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<EquipoCompetencia> buscarEquipoporCompetencia(Competencia c) {
		return daoEquipoCompetencia.buscarEquipoporCompetencia(c);

	}

}
