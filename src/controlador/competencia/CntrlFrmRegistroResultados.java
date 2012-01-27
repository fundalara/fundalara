<<<<<<< HEAD
=======
<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> 9d67536bdd2e3b33aa6400eb3bb09741a4535bb0
>>>>>>> 873ccb018e72b00b831aa9284cc0745bd1c2d514
package controlador.competencia;

import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import modelo.DatoBasico;
import modelo.Equipo;
import modelo.EquipoJuego;
import modelo.Juego;
import modelo.LineUp;
import modelo.PersonalForaneo;
import modelo.PersonalForaneoJuego;

import org.python.modules.synchronize;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.event.InputEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Button;
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
import org.zkoss.zul.Timebox;
import org.zkoss.zul.Window;

import comun.Inning;

import servicio.implementacion.ServicioCategoriaCompetencia;
import servicio.implementacion.ServicioDatoBasico;
import servicio.implementacion.ServicioLineUp;
import servicio.implementacion.ServicioPersonalForaneo;

public class CntrlFrmRegistroResultados extends GenericForwardComposer {

	AnnotateDataBinder binder;
	Juego juego;
	Component formulario;
	Textbox txtJuego;
	Textbox txtInnings;
	Button btnAgregarI;
	Button btnQuitarI;
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
	Label lblCarrerasA;
	Label lblEquipoB;
	Label lblCarrerasB;
	Grid grid;
	Timebox tbxHoraF;
	ServicioDatoBasico servicioDatoBasico;
	ServicioPersonalForaneo servicioPersonalForaneo;
	ServicioCategoriaCompetencia servicioCategoriaCompetencia;
	ServicioLineUp servicioLineUp;
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
		equipos = ConvertirConjuntoALista(juego.getEquipoJuegos());
		
		equipoA = equipos.get(0).getEquipoCompetencia().getEquipo();
		equipoB = equipos.get(1).getEquipoCompetencia().getEquipo();
		txtJuego.setText(equipoA.getNombre() + " vs " + equipoB.getNombre());
		
		
		//Establece la duraccion por defecto de los innings
		int duracionA = servicioCategoriaCompetencia.getDuraccionCategoria(equipoA.getCategoria());
		int duracionB = servicioCategoriaCompetencia.getDuraccionCategoria(equipoB.getCategoria());		
		if (duracionA > duracionB) {
			txtInnings.setText(String.valueOf(duracionA));
			llenar(duracionA);
		} else {
			txtInnings.setValue(String.valueOf(duracionB));
			llenar(duracionB);
		}
		
		Date duraccionHA = servicioCategoriaCompetencia.getDuraccionCategoriaHora(equipoA.getCategoria());
		Date duraccionHB = servicioCategoriaCompetencia.getDuraccionCategoriaHora(equipoB.getCategoria());
		Date mayor;
		
		if (duraccionHA.before(duraccionHB))
			mayor = duraccionHB;
		else
			mayor = duraccionHA;
		Date hora = new Date(0,0,0,juego.getHoraInicio().getHours()+ mayor.getHours(),juego.getHoraInicio().getMinutes()+mayor.getMinutes());
		tbxHoraF.setValue(hora);
		
		//Determina a que equipos se les procesa resultados individuales
		
		if (equipoA.getDivisa().getCodigoDivisa() == 1 ){	
			lblEquipoA.setStyle("text-decoration:underline;color:blue");
            lblEquipoA.addForward(Events.ON_CLICK,formulario,"onIndividualesA");
		}
		
		if (equipoB.getDivisa().getCodigoDivisa() == 1 ){			
			lblEquipoB.setStyle("text-decoration:underline;color:blue");
			lblEquipoB.addForward(Events.ON_CLICK,formulario,"onIndividualesB");
			
		}
		
		lblEquipoA.setValue(equipoA.getNombre());
		lblEquipoB.setValue(equipoB.getNombre());
		
		binder.loadAll();
	}
	
	
	public void onIndividualesA(){

		int contP = servicioLineUp.listarPlanificados(juego, equipoB).size();
    	int contD = servicioLineUp.listarDefinitivos(juego, equipoB).size();
		Component f;
		
		if (contD == 9)
			f = Executions.createComponents("/Competencias/Vistas/FrmResultadosIndividuales.zul",null,null);
		else 
			f = Executions.createComponents("/Competencias/Vistas/FrmCargarLineUp.zul",null,null);	
		
		Window w = (Window)f;
		w.setPosition("center");   	
	    w.setVariable("equipo",equipos.get(0).getEquipoCompetencia(),false);
	    w.setVariable("juego",juego,false);
	    w.doHighlighted();
	}
	
    public void onIndividualesB(){

    	int contP = servicioLineUp.listarPlanificados(juego, equipoB).size();
    	int contD = servicioLineUp.listarDefinitivos(juego, equipoB).size();
    	Component f;
    	
    	if (contD == 9)
			f = Executions.createComponents("/Competencias/Vistas/FrmResultadosIndividuales.zul",null,null);
		else 
			f = Executions.createComponents("/Competencias/Vistas/FrmCargarLineUp.zul",null,null);	
		
	    Window w = (Window)f;
		w.setPosition("center");
		w.setVariable("equipo",equipos.get(1).getEquipoCompetencia(),false);
		w.setVariable("juego",juego,false);
		w.doHighlighted();
	}
    

   
	public void onClick$btnAgregarI() {
		int val = Integer.valueOf(txtInnings.getText());
		txtInnings.setText(String.valueOf(val + 1));
		agregar(txtInnings.getText());
	}

	public void onClick$btnQuitarI() {
		int val = Integer.valueOf(txtInnings.getText());
		if (val > 1) {
			txtInnings.setText(String.valueOf(val - 1));
			quitar(String.valueOf(val));
		}
	}

	public void llenar(int valor) {
		for (int i = 0; i < valor; i++) {
			agregar(String.valueOf(i + 1));
		}
	}

	public List ConvertirConjuntoALista(Set conjunto) {
		List l = new ArrayList();
		for (Iterator i = conjunto.iterator(); i.hasNext();) {
			l.add(i.next());
		}
		return l;

	}

	public void agregar(String valor) {
		Columns cols = (Columns) formulario.getFellow("titulo");
		Column col = new Column(valor);
		col.setWidth("55px");
		col.setAlign("center");
		col.setId("col" + valor);
		Column columnCarreras = (Column) formulario.getFellow("columnCarreras");
		cols.insertBefore(col, columnCarreras);
		Row fila1 = (Row) formulario.getFellow("fila1");
		Row fila2 = (Row) formulario.getFellow("fila2");
		Spinner spr1 = new Spinner(0);
		spr1.setCols(2);
		spr1.setId("spnrA" + valor);
		spr1.setConstraint("min 0");
		spr1.addForward(Events.ON_CHANGING,formulario, "onCambio");
		Spinner spr2 = new Spinner(0);
		spr2.setCols(2);
		spr2.setId("spnrB" + valor);
		spr2.setConstraint("min 0");
		spr2.addForward(Events.ON_CHANGING, formulario, "onCambio");
		Label lblCarrerasA = (Label) formulario.getFellow("lblCarrerasA");
		Label lblCarrerasB = (Label) formulario.getFellow("lblCarrerasB");
		fila1.insertBefore(spr1, lblCarrerasA);
		fila2.insertBefore(spr2, lblCarrerasB);
		resultados.invalidate();

	}

	
    
   public void onCambio(InputEvent e) {
		
	    Spinner spnr = (Spinner) formulario.getFellow(e.getTarget().getId());
	    spnr.setValue(Integer.valueOf(e.getValue()));
	    spnr.invalidate();
		acumular();
		
	}
	public void acumular () {
		Columns cols = (Columns) formulario.getFellow("titulo");
		int n = cols.getChildren().size() - 4;
		int acumA = 0;
		int acumB = 0;
		for (int i = 1; i <= n; i++) {
			String idA = "spnrA" + String.valueOf(i);
			String idB = "spnrB" + String.valueOf(i);			
			Spinner spnrA = (Spinner) formulario.getFellow(idA);
			acumA += Integer.valueOf(spnrA.getValue());
			Spinner spnrB = (Spinner) formulario.getFellow(idB);
			acumB += Integer.valueOf(spnrB.getValue());
		}
		lblCarrerasA.setValue(String.valueOf(acumA));
		lblCarrerasB.setValue(String.valueOf(acumB));

	}

	public void quitar(String valor) {
		Columns cols = (Columns) formulario.getFellow("titulo");
		Column col = (Column) formulario.getFellow("col" + valor);
		cols.removeChild(col);
		Row fila1 = (Row) formulario.getFellow("fila1");
		Row fila2 = (Row) formulario.getFellow("fila2");
		Spinner spr1 = (Spinner) formulario.getFellow("spnrA" + valor);
		Spinner spr2 = (Spinner) formulario.getFellow("spnrB" + valor);
		fila1.removeChild(spr1);
		fila2.removeChild(spr2);
		acumular();
		resultados.invalidate();

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

	public void onClick$btnQuitar() throws InterruptedException {
		if (lsbxUmpires.getItemCount() > 0) {
			if (lsbxUmpires.getSelectedItem() != null) {
				PersonalForaneoJuego pfj = (PersonalForaneoJuego) lsbxUmpires
						.getSelectedItem().getValue();
				umpires.add(pfj.getPersonalForaneo());
				posiciones.add(pfj.getDatoBasico());
				umpiresJuego.remove(lsbxUmpires.getSelectedIndex());
				binder.loadAll();
			} else
				Messagebox.show("Debe seleccionar un elemento", "Mensaje",
						Messagebox.OK, Messagebox.EXCLAMATION);

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
<<<<<<< HEAD
=======
<<<<<<< HEAD
=======
=======
package controlador.competencia;

import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import modelo.DatoBasico;
import modelo.Equipo;
import modelo.EquipoJuego;
import modelo.Juego;
import modelo.LineUp;
import modelo.PersonalForaneo;
import modelo.PersonalForaneoJuego;

import org.python.modules.synchronize;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.event.InputEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Button;
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
import org.zkoss.zul.Timebox;
import org.zkoss.zul.Window;

import comun.Inning;

import servicio.implementacion.ServicioCategoriaCompetencia;
import servicio.implementacion.ServicioDatoBasico;
import servicio.implementacion.ServicioLineUp;
import servicio.implementacion.ServicioPersonalForaneo;

public class CntrlFrmRegistroResultados extends GenericForwardComposer {

	AnnotateDataBinder binder;
	Juego juego;
	Component formulario;
	Textbox txtJuego;
	Textbox txtInnings;
	Button btnAgregarI;
	Button btnQuitarI;
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
	Label lblCarrerasA;
	Label lblEquipoB;
	Label lblCarrerasB;
	Grid grid;
	Timebox tbxHoraF;
	ServicioDatoBasico servicioDatoBasico;
	ServicioPersonalForaneo servicioPersonalForaneo;
	ServicioCategoriaCompetencia servicioCategoriaCompetencia;
	ServicioLineUp servicioLineUp;
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
		
		
		//Establece la duraccion por defecto de los innings
		int duracionA = servicioCategoriaCompetencia.getDuraccionCategoria(equipoA.getCategoria());
		int duracionB = servicioCategoriaCompetencia.getDuraccionCategoria(equipoB.getCategoria());		
		if (duracionA > duracionB) {
			txtInnings.setText(String.valueOf(duracionA));
			llenar(duracionA);
		} else {
			txtInnings.setValue(String.valueOf(duracionB));
			llenar(duracionB);
		}
		
		Date duraccionHA = servicioCategoriaCompetencia.getDuraccionCategoriaHora(equipoA.getCategoria());
		Date duraccionHB = servicioCategoriaCompetencia.getDuraccionCategoriaHora(equipoB.getCategoria());
		Date mayor;
		
		if (duraccionHA.before(duraccionHB))
			mayor = duraccionHB;
		else
			mayor = duraccionHA;
		Date hora = new Date(0,0,0,juego.getHoraInicio().getHours()+ mayor.getHours(),juego.getHoraInicio().getMinutes()+mayor.getMinutes());
		tbxHoraF.setValue(hora);
		
		//Determina a que equipos se les procesa resultados individuales
		
		if (equipoA.getDivisa().getCodigoDivisa() == 1 ){	
			lblEquipoA.setStyle("text-decoration:underline;color:blue");
            lblEquipoA.addForward(Events.ON_CLICK,formulario,"onIndividualesA");
		}
		
		if (equipoB.getDivisa().getCodigoDivisa() == 1 ){			
			lblEquipoB.setStyle("text-decoration:underline;color:blue");
			lblEquipoB.addForward(Events.ON_CLICK,formulario,"onIndividualesB");
			
		}
		
		lblEquipoA.setValue(equipoA.getNombre());
		lblEquipoB.setValue(equipoB.getNombre());
		
		binder.loadAll();
	}
	
	
	public void onIndividualesA(){
		
		List<LineUp> lineups = ConvertirConjuntoALista(juego.getLineUps());
		int cont=0;
		for (int i=0;i<lineups.size();i++){
			int codigo = lineups.get(i).getRosterCompetencia().getRoster().getEquipo().getCodigoEquipo();
			if (codigo == equipoA.getCodigoEquipo()){
				cont++;
			}
		}
		Component f;

		if (cont==0){
			 f = Executions.createComponents("/Competencias/Vistas/FrmCargarLineUp.zul",null,null);
		}else
			 f = Executions.createComponents("/Competencias/Vistas/FrmResultadosIndividuales.zul",null,null);
		Window w = (Window)f;
		w.setPosition("center");   	
	    w.setVariable("equipo",equipos.get(0).getEquipoCompetencia(),false);
	    w.doHighlighted();
	}
	
    public void onIndividualesB(){
    	List<LineUp> lineups = ConvertirConjuntoALista(juego.getLineUps());
		int cont=0;		
		for (int i=0;i<lineups.size();i++){
			int codigo = lineups.get(i).getRosterCompetencia().getRoster().getEquipo().getCodigoEquipo();
			if (codigo == equipoB.getCodigoEquipo()){
				cont++;
			}
		}
		Component f;
	
		if (cont==0){
			 f = Executions.createComponents("/Competencias/Vistas/FrmCargarLineUp.zul",null,null);
		}else
			 f = Executions.createComponents("/Competencias/Vistas/FrmResultadosIndividuales.zul",null,null);
		Window w = (Window)f;
		w.setPosition("center");
		w.setVariable("equipo",equipos.get(1).getEquipoCompetencia(),false);
		w.doHighlighted();
	}
    

   
	public void onClick$btnAgregarI() {
		int val = Integer.valueOf(txtInnings.getText());
		txtInnings.setText(String.valueOf(val + 1));
		agregar(txtInnings.getText());
	}

	public void onClick$btnQuitarI() {
		int val = Integer.valueOf(txtInnings.getText());
		if (val > 1) {
			txtInnings.setText(String.valueOf(val - 1));
			quitar(String.valueOf(val));
		}
	}

	public void llenar(int valor) {
		for (int i = 0; i < valor; i++) {
			agregar(String.valueOf(i + 1));
		}
	}

	public List ConvertirConjuntoALista(Set conjunto) {
		List l = new ArrayList();
		for (Iterator i = conjunto.iterator(); i.hasNext();) {
			l.add(i.next());
		}
		return l;

	}

	public void agregar(String valor) {
		Columns cols = (Columns) formulario.getFellow("titulo");
		Column col = new Column(valor);
		col.setWidth("55px");
		col.setAlign("center");
		col.setId("col" + valor);
		Column columnCarreras = (Column) formulario.getFellow("columnCarreras");
		cols.insertBefore(col, columnCarreras);
		Row fila1 = (Row) formulario.getFellow("fila1");
		Row fila2 = (Row) formulario.getFellow("fila2");
		Spinner spr1 = new Spinner(0);
		spr1.setCols(2);
		spr1.setId("spnrA" + valor);
		spr1.setConstraint("min 0");
		spr1.addForward(Events.ON_CHANGING,formulario, "onCambio");
		Spinner spr2 = new Spinner(0);
		spr2.setCols(2);
		spr2.setId("spnrB" + valor);
		spr2.setConstraint("min 0");
		spr2.addForward(Events.ON_CHANGING, formulario, "onCambio");
		Label lblCarrerasA = (Label) formulario.getFellow("lblCarrerasA");
		Label lblCarrerasB = (Label) formulario.getFellow("lblCarrerasB");
		fila1.insertBefore(spr1, lblCarrerasA);
		fila2.insertBefore(spr2, lblCarrerasB);
		resultados.invalidate();

	}

	
    
   public void onCambio(InputEvent e) {
		
	    Spinner spnr = (Spinner) formulario.getFellow(e.getTarget().getId());
	    spnr.setValue(Integer.valueOf(e.getValue()));
	    spnr.invalidate();
		acumular();
		
	}
	public void acumular () {
		Columns cols = (Columns) formulario.getFellow("titulo");
		int n = cols.getChildren().size() - 4;
		int acumA = 0;
		int acumB = 0;
		for (int i = 1; i <= n; i++) {
			String idA = "spnrA" + String.valueOf(i);
			String idB = "spnrB" + String.valueOf(i);			
			Spinner spnrA = (Spinner) formulario.getFellow(idA);
			acumA += Integer.valueOf(spnrA.getValue());
			Spinner spnrB = (Spinner) formulario.getFellow(idB);
			acumB += Integer.valueOf(spnrB.getValue());
		}
		lblCarrerasA.setValue(String.valueOf(acumA));
		lblCarrerasB.setValue(String.valueOf(acumB));

	}

	public void quitar(String valor) {
		Columns cols = (Columns) formulario.getFellow("titulo");
		Column col = (Column) formulario.getFellow("col" + valor);
		cols.removeChild(col);
		Row fila1 = (Row) formulario.getFellow("fila1");
		Row fila2 = (Row) formulario.getFellow("fila2");
		Spinner spr1 = (Spinner) formulario.getFellow("spnrA" + valor);
		Spinner spr2 = (Spinner) formulario.getFellow("spnrB" + valor);
		fila1.removeChild(spr1);
		fila2.removeChild(spr2);
		acumular();
		resultados.invalidate();

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

	public void onClick$btnQuitar() throws InterruptedException {
		if (lsbxUmpires.getItemCount() > 0) {
			if (lsbxUmpires.getSelectedItem() != null) {
				PersonalForaneoJuego pfj = (PersonalForaneoJuego) lsbxUmpires
						.getSelectedItem().getValue();
				umpires.add(pfj.getPersonalForaneo());
				posiciones.add(pfj.getDatoBasico());
				umpiresJuego.remove(lsbxUmpires.getSelectedIndex());
				binder.loadAll();
			} else
				Messagebox.show("Debe seleccionar un elemento", "Mensaje",
						Messagebox.OK, Messagebox.EXCLAMATION);

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
>>>>>>> 7d4823278d0a354855f5032a99e2b48c65b33e7f
>>>>>>> 9d67536bdd2e3b33aa6400eb3bb09741a4535bb0
>>>>>>> 873ccb018e72b00b831aa9284cc0745bd1c2d514
