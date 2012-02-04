package dao.general;

import java.util.ArrayList;
import java.util.List;

import modelo.ComisionActividad;
import modelo.ComisionFamiliar;
import modelo.DatoBasico;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import dao.generico.GenericDao;

public class DaoComisionFamiliar extends GenericDao {

	public List<ComisionFamiliar> listarRepresentantesPorComision(DatoBasico comision) {
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		Criteria c = getSession().createCriteria(ComisionFamiliar.class);
		c.add(Restrictions.eq("datoBasico",comision));
		c.add(Restrictions.eq("estatus", "A"));
		return c.list();
	}
	
	public ComisionFamiliar buscar(String cedulaRif){
		ComisionFamiliar f =new ComisionFamiliar();
		List<ComisionFamiliar> lfc = listar(ComisionFamiliar.class);
		for(int i = 0; i < lfc.size(); i++){
			if(lfc.get(i).getFamiliarJugador().getFamiliar().getCedulaRif().equals(cedulaRif)){
				f = lfc.get(i);
			}
		}	
		return f;
	}

	public List<ComisionFamiliar> listarPorComision(
			ComisionActividad comisionActividad) {
		
		List<ComisionFamiliar> a = this.listar(ComisionFamiliar.class);
		List<ComisionFamiliar> b = new ArrayList<ComisionFamiliar>();
		for(int i = 0; i < a.size(); i++){
			if(a.get(i).getDatoBasico().getCodigoDatoBasico() == comisionActividad.getDatoBasico().getCodigoDatoBasico()){
				b.add(a.get(i));
			}
		}
		
		return b;
	}
	
	
}
