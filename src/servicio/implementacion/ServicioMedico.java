package servicio.implementacion;

import java.util.List;

import modelo.Medico;

import org.hibernate.criterion.Restrictions;

import servicio.interfaz.IServicioMedico;

import dao.general.DaoMedico;


public class ServicioMedico implements IServicioMedico {

	DaoMedico daoMedico;
	
	public DaoMedico getDaoMedico() {
		return daoMedico;
	}

	public void setDaoMedico(DaoMedico daoMedico) {
		this.daoMedico = daoMedico;
	}

	@Override
	public void eliminar(Medico c) {
		daoMedico.eliminar(c);

	}

	@Override
	public void agregar(Medico c) {
		daoMedico.guardar(c);

	}

	@Override
	public void actualizar(Medico c) {
		daoMedico.actualizar(c);

	}

	@Override
	public List<Medico> listar() {
		return daoMedico.listar( Medico.class);
	}

	@Override
	public Medico buscar(String id) {
		return daoMedico.buscar(id);
	}

	/*@Override
	public Medico buscar(Medico c) {
		return (Medico) daoMedico.getSession().createCriteria(Medico.class).add(Restrictions.eq("numeroColegio",c.getNumeroColegio())).list().get(0);
	}*/

}
