package controlador.competencia;

import java.util.List;

import modelo.Categoria;
import modelo.CategoriaCompetencia;
import modelo.Competencia;


import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;

import servicio.implementacion.ServicioCategoriaCompetencia;
import servicio.implementacion.ServicioCompetencia;



/**
 * Controlador para el archivo 'FrmCatalogoCompetencia.zul'
 * 
 * @author Eduardo Ochoa
 * @version 1.0
 */
public class CntrlFrmCatalogoCompetencia extends GenericForwardComposer {
    
	
	AnnotateDataBinder binder;
	ServicioCompetencia servicioCompetencia;
	List<Competencia> competencias;
	Listbox lsbxCompetencias;
	Component catalogo;
	ServicioCategoriaCompetencia servicioCategoriaCompetencia;
	List<CategoriaCompetencia> categoria;

	public void onCreate$FrmCatalogoC(){
	    int estatus = (Integer) catalogo.getVariable("estatus",false);	
	    competencias = servicioCompetencia.listarPorEstatus(estatus);
	    binder.loadAll();
	}
	
	
	public void doAfterCompose(Component c) throws Exception {
		super.doAfterCompose(c);
		c.setVariable("cntrl", this, true);
		catalogo = c;
	}

	public void onClick$btnAceptar() {
//		if (lsbxCompetencias.getSelectedIndex() != -1) {
//			
//			Competencia c = competencias.get(lsbxCompetencias.getSelectedIndex());
//			
//			categoria = servicioCategoriaCompetencia.listarCategoriaPorCompetencia(c.getCodigoCompetencia());
//			CntrlFrmIndicador cn = (CntrlFrmIndicador) comp.getVariable("ref", false);
//			cn.setCompetencia(c);
//			cn.setCategorias(categoria);
//			comp.detach();
//
//		} else {
//			try {
//				Messagebox.show("Seleccione una Competencia", "Mensaje",
//						Messagebox.YES, Messagebox.INFORMATION);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//
//		}

	}

	public void onClick$btnSalir() {
		catalogo.detach();
	}

	public List<Competencia> getCompetencias() {
		return competencias;
	}

	public void setCompetencias(List<Competencia> competencias) {
		this.competencias = competencias;
	}


}
