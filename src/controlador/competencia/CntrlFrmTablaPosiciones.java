package controlador.competencia;

import java.util.List;

import modelo.Categoria;
import modelo.CategoriaCompetencia;
import modelo.Competencia;
import modelo.EquipoCompetencia;
import modelo.EquipoFaseCompetencia;
import modelo.FaseCompetencia;
import modelo.Juego;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Textbox;

import servicio.implementacion.ServicioEquipoCompetencia;
import servicio.implementacion.ServicioEquipoFaseCompetencia;

import comun.EstadoCompetencia;

public class CntrlFrmTablaPosiciones extends GenericForwardComposer {
	Component formulario;
	AnnotateDataBinder binder;
	Textbox txtCompetencia;
	Textbox txtCategoria;
	Competencia competencia;
	CategoriaCompetencia categoria;
	List<EquipoCompetencia> equiposCompetencia;
	List<EquipoFaseCompetencia> equiposFaseCompetencia;
	ServicioEquipoCompetencia servicioEquipoCompetencia;
	Listbox lstTabla;
	FaseCompetencia fase;
	ServicioEquipoFaseCompetencia servicioEquipoFaseCompetencia;
	
	public void doAfterCompose(Component c) throws Exception {
		super.doAfterCompose(c);
		c.setVariable("cntrl", this, true);
		formulario = c;
	}
	
	public Competencia getCompetencia() {
		return competencia;
	}

	public void setCompetencia(Competencia competencia) {
		this.competencia = competencia;
	}

	public CategoriaCompetencia getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriaCompetencia categoria) {
		this.categoria = categoria;
	}

	public void onCreate$FrmTablaPosiciones(){
	    competencia = (Competencia) formulario.getVariable("competencia",false);
	    categoria = (CategoriaCompetencia) formulario.getVariable("categoria",false);
	    binder.loadAll();
	    fase = (FaseCompetencia) formulario.getVariable("fase", false);
	    equiposFaseCompetencia = servicioEquipoFaseCompetencia.buscarEquipoPorFaseYCategoria(fase, categoria.getCategoria());
//		equiposCompetencia = (List<EquipoCompetencia>) servicioEquipoCompetencia.listarEquipoporCompetenciaCategoria(competencia,categoria); 
		System.out.println(equiposCompetencia.size());
//		int p = 1;
//		for (EquipoCompetencia ec: equiposCompetencia){
//			System.out.println("entre al for");
//			Listcell posicion = new Listcell();
//			posicion.setLabel(String.valueOf(p));
//			Listcell equipo = new Listcell();
//			equipo.setLabel(ec.getEquipo().getNombre());
//			Listitem res = new Listitem();
//			res.appendChild(posicion);
//			res.appendChild(equipo);          
//			lstTabla.appendChild(res);
//			p=p+1;
//		}
	}
}
