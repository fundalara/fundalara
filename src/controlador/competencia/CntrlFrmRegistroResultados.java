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

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.InputEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listhead;
import org.zkoss.zul.Listheader;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Spinner;
import org.zkoss.zul.Textbox;

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
	List<Integer> innings;
	Listbox lsbxEquipos;
	Listbox lsbxInnigs;
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
	
	public void restaurar(){ 
		//cmbUmpires.setText("-- Seleccione --");
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
		equipos = ConvertirConjuntoALista(juego.getEquipoJuegos());
		equipoA = equipos.get(0).getEquipoCompetencia().getEquipo();
		equipoB = equipos.get(1).getEquipoCompetencia().getEquipo();
		txtJuego.setText(equipoA.getNombre() + " vs " + equipoB.getNombre());
		lblEquipoA.setValue(equipoA.getNombre());
		lblEquipoB.setValue(equipoB.getNombre());
		
		binder.loadAll();
	}

	public List ConvertirConjuntoALista(Set conjunto) {
		List l = new ArrayList();
		for (Iterator i = conjunto.iterator(); i.hasNext();) {
			l.add(i.next());
		}
		return l;
		
		
		
	}

//	public void onChanging$spnrInnigs(InputEvent e){
//	     
//		 Listheader lhr = new Listheader(e.getValue());
//		
//		 lhLsbxInnigs.appendChild(lhr);
//		 List<Listitem> items = lsbxInnigs.getItems();
//		 for (Iterator i = items.iterator(); i.hasNext();) {
//           //  Listitem  = (Listitem) i.next();
//             Listcell lc = new Listcell();
//             Spinner spr = new Spinner(0);
//        
//             lc.appendChild(spr);
//             liEquipoA.appendChild(lc);
//             liEquipoB.appendChild(lc);
//            
//             
//		}
//	     binder.loadAll();
//	     
//	}
	
	public void onClick$btnAgregar(){
		PersonalForaneoJuego pfj= new PersonalForaneoJuego();
		pfj.setJuego(juego);
		pfj.setPersonalForaneo((PersonalForaneo) cmbUmpires.getSelectedItem().getValue());
		pfj.setDatoBasico((DatoBasico) cmbPosiciones.getSelectedItem().getValue());
		umpiresJuego.add(pfj);
		binder.loadAll();
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

	public List<Integer> getInnings() {
		return innings;
	}

	public void setInnings(List<Integer> innings) {
		this.innings = innings;
	}

	

}
