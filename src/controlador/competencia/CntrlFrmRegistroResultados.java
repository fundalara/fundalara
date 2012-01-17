package controlador.competencia;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import modelo.DatoBasico;
import modelo.Equipo;
import modelo.EquipoJuego;
import modelo.Juego;
import modelo.PersonalForaneo;
import modelo.PersonalForaneoJuego;

import org.python.modules.synchronize;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.InputEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Column;
import org.zkoss.zul.Columns;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listhead;
import org.zkoss.zul.Listheader;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Row;
import org.zkoss.zul.Spinner;
import org.zkoss.zul.Textbox;

import comun.Inning;

import servicio.implementacion.ServicioDatoBasico;
import servicio.implementacion.ServicioPersonalForaneo;

public class CntrlFrmRegistroResultados extends GenericForwardComposer {

	AnnotateDataBinder binder;
	Juego juego;
	Component formulario;
	Textbox txtJuego;
	Spinner spnrInnigs;
	Equipo equipoA;
	Equipo equipoB;
	List<EquipoJuego> equipos;
	List<Inning> innings;
	Listbox lsbxEquipos;
	Listbox lsbxInnigs;
	Listbox lsbxUmpires;
	Listhead lhLsbxInnigs;
	Listitem liEquipoA;
	Listitem liEquipoB;
	Listcell lcEquipoA;
	Listcell lcEquipoB;
	Label lblEquipoA;
	Label lblEquipoB;
	Grid grid;
	ServicioDatoBasico servicioDatoBasico;
	ServicioPersonalForaneo servicioPersonalForaneo;
	List<PersonalForaneo> umpires;
	List<DatoBasico> posiciones;
	Combobox cmbUmpires;
	Combobox cmbPosiciones;
	List<PersonalForaneoJuego> umpiresJuego;
	Grid resultados;
	Boolean sw = true;

	public List<PersonalForaneo> getUmpires() {
		return umpires;
	}

	public void setUmpires(List<PersonalForaneo> umpires) {
		this.umpires = umpires;
	}

	public List<DatoBasico> getPosiciones() {
		return posiciones;
	}

	public void setPosiciones(List<DatoBasico> posiciones) {
		this.posiciones = posiciones;
	}

	public List<PersonalForaneoJuego> getUmpiresJuego() {
		return umpiresJuego;
	}

	public void setUmpiresJuego(List<PersonalForaneoJuego> umpiresJuego) {
		this.umpiresJuego = umpiresJuego;
	}

	public void restaurar() {
		// cmbUmpires.setText("-- Seleccione --");
		umpires = servicioPersonalForaneo.listarUmpires();
		posiciones = servicioDatoBasico.listarPosiciones();
	}

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		comp.setVariable("cntrl", this, true);
		formulario = comp;
		umpiresJuego = new ArrayList<PersonalForaneoJuego>();
		restaurar();

	}

	public void onCreate$FrmRegistroResultados() {
		juego = (Juego) formulario.getVariable("juego", false);
		System.out.println(juego.getCodigoJuego());
		equipos = ConvertirConjuntoALista(juego.getEquipoJuegos());
		equipoA = equipos.get(0).getEquipoCompetencia().getEquipo();
		equipoB = equipos.get(1).getEquipoCompetencia().getEquipo();
		txtJuego.setText(equipoA.getNombre() + " vs " + equipoB.getNombre());
		lblEquipoA.setValue(equipoA.getNombre());
		lblEquipoB.setValue(equipoB.getNombre());
		innings = new ArrayList<Inning>();
		innings.add(new Inning(1, 0));
		innings.add(new Inning(2, 0));
		innings.add(new Inning(3, 0));
		binder.loadAll();
	}

	public List ConvertirConjuntoALista(Set conjunto) {
		List l = new ArrayList();
		for (Iterator i = conjunto.iterator(); i.hasNext();) {
			l.add(i.next());
		}
		return l;

	}

	public void onChanging$spnrInnigs(InputEvent e) {

		Columns cols = (Columns) formulario.getFellow("titulo");
		Column col = new Column(e.getValue());
		if (!buscar(cols, e.getValue())) {
				col.setWidth("55px");
				Column columnCarreras = (Column) formulario.getFellow("columnCarreras");
				cols.insertBefore(col, columnCarreras);
				Row fila1 = (Row) formulario.getFellow("fila1");
				Row fila2 = (Row) formulario.getFellow("fila2");
				Spinner spr1 = new Spinner(0);
				spr1.setCols(1);
				spr1.setId("spnrA"+e.getValue());
				Spinner spr2 = new Spinner(0);
				spr2.setCols(1);
				spr2.setId("spnrB"+e.getValue());
				Label lblCarrerasA = (Label) formulario.getFellow("lblCarrerasA");
				Label lblCarrerasB = (Label) formulario.getFellow("lblCarrerasB");
				fila1.insertBefore(spr1, lblCarrerasA);
				fila2.insertBefore(spr2, lblCarrerasB);
				resultados.invalidate();
				e.stopPropagation();
	     }
		
	}

	public Boolean buscar(Columns cols, String valor) {
		List lista = cols.getChildren();
		for (int i = 0; i < lista.size(); i++) {
			Column col = (Column) lista.get(i);
			if (col.getLabel().equals(valor)) {
				return true;
			}
		}
		return false;
	}

	public void onClick$btnAgregar() {
		PersonalForaneoJuego pfj = new PersonalForaneoJuego();
		pfj.setJuego(juego);
		pfj.setPersonalForaneo((PersonalForaneo) cmbUmpires.getSelectedItem()
				.getValue());
		pfj.setDatoBasico((DatoBasico) cmbPosiciones.getSelectedItem()
				.getValue());
		umpires.remove(cmbUmpires.getSelectedIndex());
		cmbUmpires.setText("-- Seleccione --");
		posiciones.remove(cmbPosiciones.getSelectedIndex());
		cmbPosiciones.setText("-- Seleccione --");
		umpiresJuego.add(pfj);
		binder.loadAll();
	}
	
	public void onClick$btnQuitar() throws InterruptedException{
		if (lsbxUmpires.getItemCount() > 0) {
		    if (lsbxUmpires.getSelectedItem() != null){	
				PersonalForaneoJuego pfj = (PersonalForaneoJuego) lsbxUmpires.getSelectedItem().getValue();
				umpires.add(pfj.getPersonalForaneo());
				posiciones.add(pfj.getDatoBasico());
				umpiresJuego.remove(lsbxUmpires.getSelectedIndex());
				binder.loadAll();
		    }else
		    	Messagebox.show("Debe seleccionar un elemento", "Mensaje",Messagebox.OK, Messagebox.EXCLAMATION);
			
		}
	}

	public Juego getJuego() {
		return juego;
	}

	public void setJuego(Juego juego) {
		this.juego = juego;
	}

	public Equipo getEquipoA() {
		return equipoA;
	}

	public void setEquipoA(Equipo equipoA) {
		this.equipoA = equipoA;
	}

	public Equipo getEquipoB() {
		return equipoB;
	}

	public void setEquipoB(Equipo equipoB) {
		this.equipoB = equipoB;
	}

	public List<EquipoJuego> getEquipos() {
		return equipos;
	}

	public void setEquipos(List<EquipoJuego> equipos) {
		this.equipos = equipos;
	}

	public List<Inning> getInnings() {
		return innings;
	}

	public void setInnings(List<Inning> innings) {
		this.innings = innings;
	}

}
