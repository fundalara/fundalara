package servicio.implementacion;

import java.util.List;

import modelo.ClasificacionCompetencia;
import modelo.DatoBasico;
import servicio.interfaz.IServicioClasificacionCompetencia;
import dao.general.DaoClasificacionCompetencia;

public class ServicioClasificacionCompetencia implements
		IServicioClasificacionCompetencia  {
			
			DaoClasificacionCompetencia daoClasificacionCompetencia;
			
			

			public DaoClasificacionCompetencia getDaoClasificacionCompetencia() {
				return daoClasificacionCompetencia;
			}

			public void setDaoClasificacionCompetencia(
					DaoClasificacionCompetencia daoClasificacionCompetencia) {
				this.daoClasificacionCompetencia = daoClasificacionCompetencia;
			}

			@Override
			public void eliminar(ClasificacionCompetencia cc) {
				cc.setEstatus('E');
				daoClasificacionCompetencia.eliminar(cc);

			}

			@Override
			public void agregar(ClasificacionCompetencia cc) {
				if (cc.getCodigoClasificacionCompetencia() == 0){
					   int cod = daoClasificacionCompetencia.listar(ClasificacionCompetencia.class).size()+1;
					   cc.setCodigoClasificacionCompetencia(cod);
					   cc.setEstatus('A');
					}
					daoClasificacionCompetencia.guardar(cc);	
			}

			
			
			@Override
			public List<ClasificacionCompetencia> listar() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public List<ClasificacionCompetencia> listarActivos() {
				return daoClasificacionCompetencia.listarActivos();
			}

			
			@Override
			public List<ClasificacionCompetencia> listarClasificacionPorFiltro(String dato){
				return daoClasificacionCompetencia.listarClasificacionPorFiltro(dato);
			}
			
			@Override
			public List<ClasificacionCompetencia> listarClasificacion(DatoBasico d) {
				return daoClasificacionCompetencia.listarClasificacion(d);
			}

		}