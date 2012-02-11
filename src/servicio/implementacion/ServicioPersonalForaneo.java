package servicio.implementacion;

import java.util.List;

import dao.general.DaoPersonalForaneo;
import dao.general.DaoPersonalJuego;

import modelo.Constante;
import modelo.Estadio;
import modelo.PersonalForaneo;
import servicio.interfaz.IServicioPersonalForaneo;
import modelo.DatoBasico;

public class ServicioPersonalForaneo implements IServicioPersonalForaneo {
	
	DaoPersonalForaneo daoPersonalForaneo;

	public DaoPersonalForaneo getDaoPersonalForaneo() {
		return daoPersonalForaneo;
	}

	public void setDaoPersonalForaneo(DaoPersonalForaneo daoPersonalForaneo) {
		this.daoPersonalForaneo = daoPersonalForaneo;
	}

	@Override
	public void eliminar(PersonalForaneo p) {
		p.setEstatus('E');
		daoPersonalForaneo.eliminar(p);

	}

	@Override
	public void agregar(PersonalForaneo p) {
		if (p.getCodigoPersonalForaneo() == 0){
			int codPersonalForaneo = daoPersonalForaneo.listar(PersonalForaneo.class).size()+1;
			p.setCodigoPersonalForaneo(codPersonalForaneo);
			p.setEstatus('A');	
			daoPersonalForaneo.guardar(p);
			daoPersonalForaneo.getSession().close();
		   
		
		}

	}

	@Override
	public List<PersonalForaneo> listar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PersonalForaneo> listarActivos() {
		// TODO Auto-generated method stub
		return daoPersonalForaneo.listarActivos(PersonalForaneo.class);
	}
	
	@Override
	public List<PersonalForaneo> listarUmpires() {
		return daoPersonalForaneo.listarUmpires();

	}

	@Override
	public DatoBasico consultarDB() {
    return daoPersonalForaneo.consultarDatoBasico();
	}

	public List<PersonalForaneo> listarPersonalPorFiltro(String dato){
		return daoPersonalForaneo.listarPersonalPorFiltro(dato);
	}
}
