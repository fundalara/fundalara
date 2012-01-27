package controlador.competencia;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

<<<<<<< HEAD
=======
<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> 9d67536bdd2e3b33aa6400eb3bb09741a4535bb0
>>>>>>> 873ccb018e72b00b831aa9284cc0745bd1c2d514
import jxl.biff.drawing.ComboBox;

import modelo.Categoria;
import modelo.DatoBasico;
import modelo.Equipo;
import modelo.EquipoCompetencia;
import modelo.EquipoJuego;
import modelo.Juego;
import modelo.LineUp;
import modelo.Roster;
import modelo.RosterCompetencia;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Constraint;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import servicio.implementacion.ServicioDatoBasico;
import servicio.implementacion.ServicioLineUp;

public class CntrlFrmCargarLineUp extends GenericForwardComposer {

	Component formulario;
	AnnotateDataBinder binder;
	EquipoCompetencia equipoCompetencia;
	Textbox txtJuego;
	List<RosterCompetencia> rosters;
	List<LineUp> lineups;
	List<DatoBasico> posiciones;
	Listbox lsbxRoster;
	Listbox lsbxLineUp;
	ServicioDatoBasico servicioDatoBasico;
	ServicioLineUp servicioLineUp;
	Juego juego;

<<<<<<< HEAD
=======
<<<<<<< HEAD
=======
=======
import modelo.Equipo;
import modelo.EquipoCompetencia;
import modelo.Roster;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Textbox;

public class CntrlFrmCargarLineUp extends GenericForwardComposer {
	
	
	Component formulario;
	AnnotateDataBinder binder;
	EquipoCompetencia equipoCompetencia;
	Textbox txtDelegado;
    List<Roster> rosters;
    Listbox lsbxRoster;
	
>>>>>>> 7d4823278d0a354855f5032a99e2b48c65b33e7f
>>>>>>> 9d67536bdd2e3b33aa6400eb3bb09741a4535bb0
>>>>>>> 873ccb018e72b00b831aa9284cc0745bd1c2d514
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
<<<<<<< HEAD
=======
<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> 9d67536bdd2e3b33aa6400eb3bb09741a4535bb0
>>>>>>> 873ccb018e72b00b831aa9284cc0745bd1c2d514
		comp.setVariable("cntrl", this, true);
		formulario = comp;
		lineups = new ArrayList<LineUp>();

	}

	public void onCreate$FrmCargarLineUp() {
		equipoCompetencia = (EquipoCompetencia) formulario.getVariable(
				"equipo", false);
		juego = (Juego) formulario.getVariable("juego", false);
		List<EquipoJuego> equipos =ConvertirConjuntoALista(juego.getEquipoJuegos());		
		String equipoA = equipos.get(0).getEquipoCompetencia().getEquipo().getNombre();
		String equipoB = equipos.get(1).getEquipoCompetencia().getEquipo().getNombre();
		txtJuego.setText(equipoA + " vs " + equipoB);
		lineups = servicioLineUp.listarPlanificados(juego,equipos.get(0).getEquipoCompetencia().getEquipo());
		posiciones = servicioDatoBasico.listarPosicionesJugadores();
		Set conjunto = equipoCompetencia.getEquipo().getRosterCompetencias();
		rosters = ConvertirConjuntoALista(conjunto);
		binder.loadAll();

	}

	public void onSelect$lsbxLineUp() {

	}

	public void onClick$btnAgregar() {
		Agregar(lsbxRoster, lsbxLineUp, lineups);
		binder.loadAll();
	}

	public void onClick$btnQuitar() {
		Quitar(lsbxLineUp, lineups);
		binder.loadAll();

	}

	public void onClick$btnSubir() throws InterruptedException {
		if (lsbxLineUp.getSelectedItems().size() != 0) {
			if (lsbxLineUp.getSelectedItems().size() == 1) {
				if (lsbxLineUp.getSelectedIndex() != 0) {
					int i = lsbxLineUp.getSelectedIndex();
					intercambiar(i, i - 1);
					binder.loadAll();
				}
			} else {
				Messagebox.show("Solo puede seleccionarse un jugador a la vez",
						"Mensaje", Messagebox.OK, Messagebox.EXCLAMATION);
			}

		} else {
			Messagebox.show("Seleccione un jugador", "Mensaje", Messagebox.OK,
					Messagebox.EXCLAMATION);
		}

	}

	public void onClick$btnBajar() throws InterruptedException {

		if (lsbxLineUp.getSelectedItems().size() != 0) {
			if (lsbxLineUp.getSelectedItems().size() == 1) {
				if (lsbxLineUp.getSelectedIndex() != lineups.size() - 1) {
					int i = lsbxLineUp.getSelectedIndex();
					intercambiar(i, i + 1);
					binder.loadAll();
				}
			} else {
				Messagebox.show("Solo puede seleccionarse un jugador a la vez",
						"Mensaje", Messagebox.OK, Messagebox.EXCLAMATION);
			}

		} else {
			Messagebox.show("Seleccione un jugador", "Mensaje", Messagebox.OK,
					Messagebox.EXCLAMATION);
		}

	}

	public void onClick$btnFinalizar() throws InterruptedException {
		if (lineups.size() > 0) { // cambiarlo a 9
			sinPosicion();
			noDuplicados();
			for (int i = 0; i < lineups.size(); i++) {
				lineups.get(i).setDatoBasicoByCodigoEstadoLineUp(
						servicioDatoBasico.buscarPorString("DEFINITIVO"));
				servicioLineUp.agregar(lineups.get(i));
			}
			Messagebox.show("Datos almacenados exitosamente", "Mensaje",
					Messagebox.OK, Messagebox.EXCLAMATION);
			formulario.detach();
			Component c = Executions.createComponents("/Competencias/Vistas/FrmResultadosIndividuales.zul",null,null);
			Window w = (Window)c;
			w.setPosition("center");   	
		    w.setVariable("equipo",equipoCompetencia,false);
		    w.doHighlighted();
		   
		} else
			throw new WrongValueException(lsbxLineUp,
					"Debe seleccionar un maximo de 9 jugadores");
	}

	public void onClick$btnGuardar() throws InterruptedException {
		if (lineups.size() > 0) {
			for (int i = 0; i < lineups.size(); i++) {
				lineups.get(i).setDatoBasicoByCodigoEstadoLineUp(
						servicioDatoBasico.buscarPorString("BORRADOR"));
				servicioLineUp.agregar(lineups.get(i));
			}
			Messagebox.show("Datos almacenados exitosamente", "Mensaje",
					Messagebox.OK, Messagebox.EXCLAMATION);

		} else {

		}
	}

	public void sinPosicion() {
		List items = lsbxLineUp.getItems();
		for (int i = 0; i < items.size(); i++) {
			Listitem li = (Listitem) items.get(i);
			Listcell lc = (Listcell) li.getLastChild();
			Combobox cmb = (Combobox) lc.getChildren().get(0);
			cmb.setConstraint("no empty:Debe seleccionar una posicion");
			String s = cmb.getValue();
		}

	}

	public void noDuplicados() {
		List items = lsbxLineUp.getItems();
		for (int i = items.size() - 1; i > -1; i--) {
			Listitem li = (Listitem) items.get(i);
			Listcell lc = (Listcell) li.getLastChild();
			Combobox cmb = (Combobox) lc.getChildren().get(0);
			if (buscarRepetido(i) != -1) {
				throw new WrongValueException(cmb, "Posicion duplicada");
			}
			String s = cmb.getValue();
		}
	}

	public int buscarRepetido(int i) {
		for (int j = 0; j < lineups.size(); j++) {
			if (i != j) {
				LineUp actual = lineups.get(j);
				LineUp buscado = lineups.get(i);
				if (actual
						.getDatoBasicoByCodigoPosicion()
						.getNombre()
						.equals(buscado.getDatoBasicoByCodigoPosicion()
								.getNombre())) {
					return j;
				}

			}
		}
		return -1;
	}

	public void intercambiar(int x, int y) {
		LineUp aux = lineups.get(x);
		int posAux = lineups.get(y).getOrdenBate();
		lineups.get(y).setOrdenBate(aux.getOrdenBate());
		lineups.set(x, lineups.get(y));
		aux.setOrdenBate(posAux);
		lineups.set(y, aux);
	}

	public void Agregar(Listbox origen, Listbox destino, List lista) {
		Set seleccionados = origen.getSelectedItems();
		for (Iterator i = seleccionados.iterator(); i.hasNext();) {
			boolean sw = false;
			if (lista.size() == 9)
				break;
			
			Listitem li = (Listitem) i.next();
			RosterCompetencia rc = (RosterCompetencia) li.getValue();
			LineUp lu = new LineUp();
			lu.setRosterCompetencia(rc);
			lu.setOrdenBate(lineups.size() + 1);
			lu.setJuego(juego);
			List seleccionDestino = destino.getItems();
			for (int j = 0; j < seleccionDestino.size(); j++) {
				Listitem li2 = (Listitem) seleccionDestino.get(j);
				LineUp lu2 = (LineUp) li2.getValue();
				if (lu.getRosterCompetencia().getCodigoRosterCompetencia() == lu2
						.getRosterCompetencia().getCodigoRosterCompetencia()) {
					sw = true;
					break;
				}
			}
			if (!sw) {
				lista.add(lu);
			}
		}
	}

	public void Quitar(Listbox origen, List lista) {
		Set seleccionados = origen.getSelectedItems();
		for (Iterator i = seleccionados.iterator(); i.hasNext();) {
			Listitem li = (Listitem) i.next();
			Object o = li.getValue();
			lista.remove(o);
		}
	}

<<<<<<< HEAD
=======
<<<<<<< HEAD
=======
=======
			comp.setVariable("cntrl", this, true);
	    formulario = comp;
	    	    
	}
	
	public void onCreate$FrmCargarLineUp(){
		equipoCompetencia =  (EquipoCompetencia) formulario.getVariable("equipo",false);
		rosters = ConvertirConjuntoALista(equipoCompetencia.getEquipo().getRosters());
		String nombre = equipoCompetencia.getPersonaNatural().getPrimerNombre();
		String apellido= equipoCompetencia.getPersonaNatural().getPrimerApellido();
		txtDelegado.setText(nombre+" "+apellido);
		binder.loadAll();
		
      
	}
	
>>>>>>> 7d4823278d0a354855f5032a99e2b48c65b33e7f
>>>>>>> 9d67536bdd2e3b33aa6400eb3bb09741a4535bb0
>>>>>>> 873ccb018e72b00b831aa9284cc0745bd1c2d514
	public List ConvertirConjuntoALista(Set conjunto) {
		List l = new ArrayList();
		for (Iterator i = conjunto.iterator(); i.hasNext();) {
			l.add(i.next());
		}
		return l;
	}

	public EquipoCompetencia getEquipoCompetencia() {
		return equipoCompetencia;
	}

	public void setEquipoCompetencia(EquipoCompetencia equipoCompetencia) {
		this.equipoCompetencia = equipoCompetencia;
	}

<<<<<<< HEAD
=======
<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> 9d67536bdd2e3b33aa6400eb3bb09741a4535bb0
>>>>>>> 873ccb018e72b00b831aa9284cc0745bd1c2d514
	public List<RosterCompetencia> getRosters() {
		return rosters;
	}

	public void setRosters(List<RosterCompetencia> rosters) {
<<<<<<< HEAD
=======
<<<<<<< HEAD
=======
=======
	public List<Roster> getRosters() {
		return rosters;
	}

	public void setRosters(List<Roster> rosters) {
>>>>>>> 7d4823278d0a354855f5032a99e2b48c65b33e7f
>>>>>>> 9d67536bdd2e3b33aa6400eb3bb09741a4535bb0
>>>>>>> 873ccb018e72b00b831aa9284cc0745bd1c2d514
		this.rosters = rosters;
	}

	public Listbox getLsbxRoster() {
		return lsbxRoster;
	}

	public void setLsbxRoster(Listbox lsbxRoster) {
		this.lsbxRoster = lsbxRoster;
	}
<<<<<<< HEAD
=======
<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> 9d67536bdd2e3b33aa6400eb3bb09741a4535bb0
>>>>>>> 873ccb018e72b00b831aa9284cc0745bd1c2d514

	public List<LineUp> getLineups() {
		return lineups;
	}

	public void setLineups(List<LineUp> lineups) {
		this.lineups = lineups;
	}

	public List<DatoBasico> getPosiciones() {
		return posiciones;
	}

	public void setPosiciones(List<DatoBasico> posiciones) {
		this.posiciones = posiciones;
	}

	public Juego getJuego() {
		return juego;
	}

	public void setJuego(Juego juego) {
		this.juego = juego;
	}

<<<<<<< HEAD
=======
<<<<<<< HEAD
=======
=======
	
	
    
	
	
	
>>>>>>> 7d4823278d0a354855f5032a99e2b48c65b33e7f
>>>>>>> 9d67536bdd2e3b33aa6400eb3bb09741a4535bb0
>>>>>>> 873ccb018e72b00b831aa9284cc0745bd1c2d514
}
