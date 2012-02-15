package controlador.competencia;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import modelo.Categoria;
import modelo.CategoriaCompetencia;
import modelo.Competencia;
import modelo.EquipoCompetencia;
import modelo.EquipoFaseCompetencia;
import modelo.EquipoJuego;
import modelo.FaseCompetencia;
import modelo.Juego;
import modelo.Liga;

import org.hibernate.Query;
import org.hibernate.Session;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listheader;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import servicio.implementacion.ServicioEquipoCompetencia;
import servicio.implementacion.ServicioEquipoFaseCompetencia;
import servicio.implementacion.ServicioEquipoJuego;
import servicio.implementacion.ServicioFaseCompetencia;

import comun.EstadoCompetencia;
import comun.TablaPosiciones;
import dao.general.DaoFaseCompetencia;

public class CntrlFrmTablaPosiciones extends GenericForwardComposer {
	Component formulario;
	AnnotateDataBinder binder;
	
	Competencia competencia;
	CategoriaCompetencia categoria;
	List<EquipoCompetencia> equiposCompetencia;
	List<EquipoFaseCompetencia> equiposFaseCompetencia;
	FaseCompetencia fase;
	
	ServicioEquipoCompetencia servicioEquipoCompetencia;
	ServicioFaseCompetencia servicioFaseCompetencia;
	ServicioEquipoFaseCompetencia servicioEquipoFaseCompetencia;
	ServicioEquipoJuego servicioEquipoJuego;
	
	Listbox lstTabla;
	Listheader lthPuntos;
	List<TablaPosiciones> tablaPosiciones;
	Button btnSiguiente;
	Textbox txtCompetencia;
	Textbox txtCategoria;
	Textbox txtDesde;
	Textbox txtHasta;
	
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

	public void onCreate$FrmTablaPosiciones() {
		tablaPosiciones = new ArrayList<TablaPosiciones>();
		competencia = (Competencia) formulario.getVariable("competencia", false);
		categoria = (CategoriaCompetencia) formulario.getVariable("categoria",false);
		binder.loadAll();
		fase = (FaseCompetencia) formulario.getVariable("fase", false);
		if (fase.getEstatus()=='A'){
			if (fase.getNumeroFase()<competencia.getCantidadFase())
				btnSiguiente.setLabel("Siguiente Ronda");
			else {
				btnSiguiente.setLabel("Finalizar Fase");
		}	
		}else
			btnSiguiente.setVisible(false);
		equiposFaseCompetencia = servicioEquipoFaseCompetencia.buscarEquipoPorFaseYCategoria(fase, categoria.getCategoria());
		txtDesde.setText(String.valueOf(fase.getNumeroFase()));
		txtHasta.setText(String.valueOf(competencia.getCantidadFase()));
		int p = 1;
		int jj = 0;
		for (EquipoFaseCompetencia ec : equiposFaseCompetencia) {
			List<EquipoJuego> equipojuegos = servicioEquipoJuego.buscarJuegoporCompetencia(competencia,ec.getEquipoCompetencia());
			TablaPosiciones tabla = new TablaPosiciones();
			tabla.setNombre(ec.getEquipoCompetencia().getEquipo().getNombre());
			jj = equipojuegos.size();
			tabla.setJuegos_jugados(jj);
			int jg = 0;
			int jp = 0;
			int je = 0;
			int puntos = 0;
			for (EquipoJuego ej : equipojuegos) {
				if (ej.getGanado() == 'G') {
					jg = jg + 1;
					puntos = puntos + 1000;
				} else {
					if (ej.getGanado() == 'E') {
						je = je + 1;
						puntos = puntos + 500;
					} else {
						if (ej.getGanado() == 'F') {
							jp = jp + 1;
							puntos = puntos - 500;
						} else
							jp =jp +1;
					}
				}
			}
			tabla.setJuegos_ganados(jg);
			tabla.setJuegos_empatados(je);
			tabla.setJuegos_perdidos(jp);
			tabla.setPuntos(puntos);
			tabla.setPuntos_acumulados(0);
			tablaPosiciones.add(tabla);
			
		}
		ordenarLista();
		for (TablaPosiciones tb : tablaPosiciones) {
			Listcell posicion = new Listcell();
			posicion.setLabel(String.valueOf(p));
			Listcell equipo = new Listcell();
			equipo.setLabel(tb.getNombre());
			Listitem res = new Listitem();
			Listcell juegosj = new Listcell();
			juegosj.setLabel(String.valueOf(tb.getJuegos_jugados()));
			Listcell juegosg = new Listcell();
			juegosg.setLabel(String.valueOf(tb.getJuegos_ganados()));
			Listcell juegose = new Listcell();
			juegose.setLabel(String.valueOf(tb.getJuegos_empatados()));
			Listcell juegosp = new Listcell();
			juegosp.setLabel(String.valueOf(tb.getJuegos_perdidos()));
			Listcell ptos = new Listcell();
			ptos.setLabel(String.valueOf(tb.getPuntos()));
			Listcell pptos = new Listcell();
			pptos.setLabel(String.valueOf(tb.getPuntos()/tb.getJuegos_jugados()));
			res.appendChild(posicion);
			res.appendChild(equipo);
			res.appendChild(juegosj);
			res.appendChild(juegosg);
			res.appendChild(juegose);
			res.appendChild(juegosp);
			res.appendChild(ptos);
			res.appendChild(pptos);
			p = p + 1;
			lstTabla.appendChild(res);
			
		}
	}

	public void ordenarLista(){
		Collections.sort(tablaPosiciones, new Comparator(){
			public int compare(Object o1, Object o2) {
				TablaPosiciones tabla1 = (TablaPosiciones) o1;
				TablaPosiciones tabla2 = (TablaPosiciones) o2;
				if (tabla1.getPuntos()<tabla2.getPuntos())
					return 1;
				
				else 
					if (tabla1.getPuntos()>tabla2.getPuntos())
						return -1;
					else
						return 0;
				
			}
		});
	}
	
	public void onClick$btnSiguiente() throws InterruptedException{
		if (btnSiguiente.getLabel()=="Siguiente Ronda"){
			if (fase.getEquipoClasifican()==lstTabla.getSelectedItems().size()){
				if (Messagebox.show("¿Seguro que desea pasar esos equipos a la siguiente ronda?","Mensaje", Messagebox.YES + Messagebox.NO,
							Messagebox.QUESTION) == Messagebox.YES) {
					List<Listitem> eqs =ConvertirConjuntoALista(lstTabla.getSelectedItems());
					for (Listitem e:eqs) {
						for (EquipoFaseCompetencia ec : equiposFaseCompetencia){
							if (ec.getEquipoCompetencia().getEquipo().getNombre()==tablaPosiciones.get(e.getIndex()).getNombre()){
								EquipoFaseCompetencia efc = new EquipoFaseCompetencia();
								efc.setEquipoCompetencia(ec.getEquipoCompetencia());
								FaseCompetencia fc=servicioFaseCompetencia.buscarFaseSiguiente(ec.getFaseCompetencia());
								efc.setFaseCompetencia(fc);
								efc.setEstatus('A');
								servicioEquipoFaseCompetencia.agregar(efc);
							}//del if
						}//del for interno
					}//for externo
					Messagebox.show("Los datos de la fase han sido guardados","Mensaje", Messagebox.OK,
						Messagebox.INFORMATION);
					fase.setEstatus('C');
					servicioFaseCompetencia.actualizar1(fase);
					formulario.detach();
				
				}
			}else
				Messagebox.show("Debe seleccionar "+ String.valueOf(fase.getEquipoClasifican())+" equipos", "Mensaje", Messagebox.OK,
					Messagebox.EXCLAMATION);
		}else{
			if (lstTabla.getSelectedItems().size()==fase.getEquipoClasifican()){
				if (Messagebox.show("¿Seguro que desea finalizar la fase?","Mensaje", Messagebox.YES + Messagebox.NO,
					Messagebox.QUESTION) == Messagebox.YES){
					fase.setEstatus('C');
					servicioFaseCompetencia.actualizar1(fase);
					Messagebox.show("El campeon de la competencia en la categoria " + txtCategoria.getText()+ " es: " + tablaPosiciones.get(lstTabla.getSelectedIndex()).getNombre(), "Mensaje", Messagebox.OK,
					Messagebox.EXCLAMATION);
					formulario.detach();
				}
	
			}else
			Messagebox.show("Debe seleccionar solo 1 equipo", "Mensaje", Messagebox.OK,
					Messagebox.EXCLAMATION);
		}
	}
		
	
	// Agregado Convierte un conjunto a una lista...
	public List ConvertirConjuntoALista(Set conjunto) {
		List l = new ArrayList();
		for (Iterator i = conjunto.iterator(); i.hasNext();) {
			l.add(i.next());
		}
		return l;
	}

	public List<TablaPosiciones> getTablaPosiciones() {
		return tablaPosiciones;
	}

	public void setTablaPosiciones(List<TablaPosiciones> tablaPosiciones) {
		this.tablaPosiciones = tablaPosiciones;
	}

}
