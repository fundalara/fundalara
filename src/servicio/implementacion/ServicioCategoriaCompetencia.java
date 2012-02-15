package servicio.implementacion;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import servicio.interfaz.IServicioCategoriaCompetencia;

import dao.general.DaoCategoriaCompetencia;

import modelo.Categoria;
import modelo.CategoriaCompetencia;
import modelo.CategoriaCompetenciaId;
import modelo.Competencia;
import modelo.Divisa;
import modelo.IndicadorCategoriaCompetencia;

public class ServicioCategoriaCompetencia implements
		IServicioCategoriaCompetencia {

	DaoCategoriaCompetencia daoCategoriaCompetencia;
	@Override
	public void eliminar(Categoria cc) {
		// TODO Auto-generated method stub

	}

	@Override
	public void agregar(IndicadorCategoriaCompetencia icc) {
	     if (icc.getCodigoIndicadorCategoriaCompetencia() == 0){
	    	 int codigo = daoCategoriaCompetencia.listar(IndicadorCategoriaCompetencia.class).size()+1;
	    	 icc.setCodigoIndicadorCategoriaCompetencia(codigo);
	     }
	     daoCategoriaCompetencia.guardar(icc);
	}



	@Override
	public List<Categoria> listar() {
		return daoCategoriaCompetencia.listar(IndicadorCategoriaCompetencia.class);
	}

	@Override
	public List<Categoria> listarActivos() {
		// TODO Auto-generated method stub
		return null;
	}
		

	public DaoCategoriaCompetencia getDaoCategoriaCompetencia() {
		return daoCategoriaCompetencia;
	}

	public void setDaoCategoriaCompetencia(
			DaoCategoriaCompetencia daoCategoriaCompetencia) {
		this.daoCategoriaCompetencia = daoCategoriaCompetencia;
	}

	@Override
	public List<CategoriaCompetencia> listarCategoriaPorCompetencia(int codigo) {
		
		return daoCategoriaCompetencia.listarCategoriaPorCompetencia(CategoriaCompetencia.class,codigo);
	}

	@Override
	public int getDuraccionCategoria(Categoria cat) {
		return daoCategoriaCompetencia.getDuraccionCategoria(cat);
		
	}

	@Override
	public Date getDuraccionCategoriaHora(Categoria cat) {
		return daoCategoriaCompetencia.getDuraccionCategoriaHoras(cat);
	}

	
	@Override
	public void agregar(List<CategoriaCompetencia> lista,int comp) {
		// TODO Auto-generated method stub

		
//		int codigoCompetencia = daoCompetencia.listar(Competencia.class).size();
		
		for (Iterator i= lista.iterator(); i.hasNext();){
			CategoriaCompetencia id = (CategoriaCompetencia) i.next();
			
			id.getCompetencia().setCodigoCompetencia(comp);
			
			
			int codcateg = id.getCategoria().getCodigoCategoria();
			
			CategoriaCompetenciaId categID= new CategoriaCompetenciaId();
			categID.setCodigoCategoria(codcateg);
			categID.setCodigoCompetencia(comp);
			
			id.setId(categID);
			id.setEstatus('A');
			
			daoCategoriaCompetencia.guardar(id);		
	    }

//		daoCategoriaCompetencia.guardar(cc);		
				
	}
	
	@Override
	public void actualizar(List<CategoriaCompetencia> lista1, List<CategoriaCompetencia> lista2, String tipoOrg) {
		// TODO Auto-generated method stub

		if (tipoOrg == "C") {
			for (Iterator i= lista1.iterator(); i.hasNext();){
			CategoriaCompetencia catg = (CategoriaCompetencia) i.next();

				for (Iterator i2= lista2.iterator(); i2.hasNext();){
				CategoriaCompetencia catg2 = (CategoriaCompetencia) i2.next();
			
					if (catg.getCategoria().getCodigoCategoria() == catg2.getCategoria().getCodigoCategoria()){
					
						catg.setDuracionHora(catg2.getDuracionHora());
						catg.setDuracionInning(catg2.getDuracionInning());
					
						daoCategoriaCompetencia.actualizar(catg);				
					}		
				}
			}
		
		}else{	
			for (Iterator l= lista2.iterator(); l.hasNext();){
				CategoriaCompetencia id = (CategoriaCompetencia) l.next();
			
				daoCategoriaCompetencia.actualizar(id);			
			}	
		}
	}
}
