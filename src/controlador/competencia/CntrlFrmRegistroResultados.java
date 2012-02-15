package controlador.competencia;

import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import modelo.Competencia;
import modelo.DatoBasico;
import modelo.DesempennoColectivo;
import modelo.Equipo;
import modelo.EquipoJuego;
import modelo.IndicadorCategoriaCompetencia;
import modelo.Juego;
import modelo.LineUp;
import modelo.PersonalForaneo;
import modelo.PersonalForaneoJuego;
import modelo.PersonalForaneoJuegoId;

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

import com.jhlabs.image.PerspectiveFilter;

import comun.Inning;

import servicio.implementacion.ServicioCategoriaCompetencia;
import servicio.implementacion.ServicioDatoBasico;
import servicio.implementacion.ServicioDesempennoColectivo;
import servicio.implementacion.ServicioEquipoJuego;
import servicio.implementacion.ServicioFaseCompetencia;
import servicio.implementacion.ServicioIndicadorCategoriaCompetencia;
import servicio.implementacion.ServicioJuego;
import servicio.implementacion.ServicioLineUp;
import servicio.implementacion.ServicioPersonalForaneo;
import servicio.implementacion.ServicioPersonalForaneoJuego;

public class CntrlFrmRegistroResultados extends GenericForwardComposer {

	AnnotateDataBinder binder;
	Juego juego;
	Component formulario;
	Textbox txtJuego;
	Textbox txtInnings;
	Textbox txtFase;

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
	Timebox tbxHoraF;
	ServicioDatoBasico servicioDatoBasico;
	ServicioPersonalForaneo servicioPersonalForaneo;
	ServicioCategoriaCompetencia servicioCategoriaCompetencia;
	ServicioIndicadorCategoriaCompetencia servicioIndicadorCategoriaCompetencia;
	ServicioFaseCompetencia servicioFaseCompetencia;
	ServicioPersonalForaneoJuego servicioPersonalForaneoJuego;
	ServicioLineUp servicioLineUp;
	ServicioJuego servicioJuego;
	ServicioDesempennoColectivo servicioDesempennoColectivo;
	ServicioEquipoJuego servicioEquipoJuego;
	List<PersonalForaneo> umpires;
	List<PersonalForaneo> anotadores;
	List<DatoBasico> posiciones;
	Combobox cmbUmpires;
	Combobox cmbPosiciones;
	Combobox cmbAnotador;
	List<PersonalForaneoJuego> umpiresJuego;
	Grid gridResultados;
	Boolean sw = true;

	public void restaurar() {
		// cmbUmpires.setText("-- Seleccione --");
		umpiresJuego = new ArrayList<PersonalForaneoJuego>();
		anotadores = new ArrayList<PersonalForaneo>();
		umpires = new ArrayList<PersonalForaneo>();

		anotadores = servicioPersonalForaneo.listarAnotadores();
	}

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		comp.setVariable("cntrl", this, true);
		formulario = comp;
		restaurar();

	}

	public List<PersonalForaneo> noAsignados() {
		List<PersonalForaneo> aux = new ArrayList<PersonalForaneo>();
		for (PersonalForaneo pf : umpires) {
			if (!buscarPersonal(pf)) {
				aux.add(pf);
			}
		}
		return aux;
	}

	public List<DatoBasico> ajustarPosiciones() {
		List<DatoBasico> aux = new ArrayList<DatoBasico>();
		for (DatoBasico db : posiciones) {
			if (!buscarPosicion(db)) {
				aux.add(db);
			}
		}
		return aux;
	}

	public boolean buscarPosicion(DatoBasico db) {
		for (PersonalForaneoJuego pfj : umpiresJuego) {
			if (pfj.getDatoBasico().getCodigoDatoBasico() == db
					.getCodigoDatoBasico())
				return true;

		}
		return false;
	}

	public boolean buscarPersonal(PersonalForaneo pf) {
		for (PersonalForaneoJuego pfj : umpiresJuego) {
			if (pfj.getPersonalForaneo().getCodigoPersonalForaneo() == pf
					.getCodigoPersonalForaneo())
				return true;

		}
		return false;
	}

	public void onCreate$FrmRegistroResultados() {
		int codigo = (Integer) formulario.getVariable("juego", false);
		juego = servicioJuego.buscarJuego(codigo);
		equipos = ConvertirConjuntoALista(juego.getEquipoJuegos());

		equipoA = equipos.get(0).getEquipoCompetencia().getEquipo();
		equipoB = equipos.get(1).getEquipoCompetencia().getEquipo();
		txtJuego.setText(equipoA.getNombre() + " vs " + equipoB.getNombre());

		// umpires
		umpiresJuego = servicioPersonalForaneoJuego.listarUmpireJuego(juego);
		umpires = servicioPersonalForaneo.listarUmpires();
		posiciones = servicioDatoBasico.listarPosiciones();
		umpires = noAsignados();
		posiciones = ajustarPosiciones();

		int duracionA = servicioCategoriaCompetencia
				.getDuraccionCategoria(equipoA.getCategoria());
		int duracionB = servicioCategoriaCompetencia
				.getDuraccionCategoria(equipoB.getCategoria());
		if (duracionA > duracionB) {
			txtInnings.setText(String.valueOf(duracionA));
			llenar(duracionA);
		} else {
			txtInnings.setValue(String.valueOf(duracionB));
			llenar(duracionB);
		}

		Date duraccionHA = servicioCategoriaCompetencia
				.getDuraccionCategoriaHora(equipoA.getCategoria());
		Date duraccionHB = servicioCategoriaCompetencia
				.getDuraccionCategoriaHora(equipoB.getCategoria());
		Date mayor;

		if (duraccionHA.before(duraccionHB))
			mayor = duraccionHB;
		else
			mayor = duraccionHA;
		Date hora = new Date(0, 0, 0, juego.getHoraInicio().getHours()
				+ mayor.getHours(), juego.getHoraInicio().getMinutes()
				+ mayor.getMinutes());
		tbxHoraF.setValue(hora);

		// Determina a que equipos se les procesa resultados individuales

		if (equipoA.getDivisa().getCodigoDivisa() == 1) {
			lblEquipoA.setStyle("text-decoration:underline;color:blue");
			lblEquipoA.addForward(Events.ON_CLICK, formulario,
					"onIndividualesA");
		}

		if (equipoB.getDivisa().getCodigoDivisa() == 1) {
			lblEquipoB.setStyle("text-decoration:underline;color:blue");
			lblEquipoB.addForward(Events.ON_CLICK, formulario,
					"onIndividualesB");

		}

		lblEquipoA.setValue(equipoA.getNombre());
		lblEquipoB.setValue(equipoB.getNombre());
		String fase = String
				.valueOf(juego.getFaseCompetencia().getNumeroFase());
		String fases = String.valueOf(servicioFaseCompetencia
				.listarPorCompetencia(codigo).size());
		txtFase.setValue(fase + " de " + fases);

		binder.loadAll();

	}

	public void onIndividualesA() {

		int contP = servicioLineUp.listarPlanificados(juego, equipoA).size();
		int contD = servicioLineUp.listarDefinitivos(juego, equipoA).size();
		Component f;
		if (contD >= 9)
			f = Executions.createComponents(
					"/Competencias/Vistas/FrmResultadosIndividuales.zul", null,
					null);
		else
			f = Executions.createComponents(
					"/Competencias/Vistas/FrmCargarLineUp.zul", null, null);
		Window w = (Window) f;
		w.setPosition("center");
		w.setVariable("equipo", equipos.get(0).getEquipoCompetencia(), false);
		w.setVariable("juego", juego.getCodigoJuego(), false);
		w.doHighlighted();
	}

	public void onIndividualesB() {

		int contP = servicioLineUp.listarPlanificados(juego, equipoB).size();
		int contD = servicioLineUp.listarDefinitivos(juego, equipoB).size();
		Component f;
		if (contD >= 9)
			f = Executions.createComponents(
					"/Competencias/Vistas/FrmResultadosIndividuales.zul", null,
					null);
		else
			f = Executions.createComponents(
					"/Competencias/Vistas/FrmCargarLineUp.zul", null, null);

		Window w = (Window) f;
		w.setPosition("center");
		w.setVariable("equipo", equipos.get(1).getEquipoCompetencia(), false);
		w.setVariable("juego", juego.getCodigoJuego(), false);
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
		EquipoJuego ej1 = equipos.get(0);
		EquipoJuego ej2 = equipos.get(1);
		IndicadorCategoriaCompetencia icc = servicioIndicadorCategoriaCompetencia
				.obtenerIndicadorCarrera();

		DesempennoColectivo dc1 = servicioDesempennoColectivo
				.buscarCarrerasPorEquipo(ej1, icc, Integer.valueOf(valor));
		DesempennoColectivo dc2 = servicioDesempennoColectivo
				.buscarCarrerasPorEquipo(ej2, icc, Integer.valueOf(valor));

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
		spr1.addForward(Events.ON_CHANGING, formulario, "onCambio");
		Spinner spr2 = new Spinner(0);
		spr2.setCols(2);
		spr2.setId("spnrB" + valor);
		spr2.setConstraint("min 0");
		spr2.addForward(Events.ON_CHANGING, formulario, "onCambio");
		Label lblCarrerasA = (Label) formulario.getFellow("lblCarrerasA");
		Label lblCarrerasB = (Label) formulario.getFellow("lblCarrerasB");
		if (dc1 != null && dc1.getValor() != 0) {
			int v = (int) dc1.getValor();
			spr1.setValue(v);
		}
		if (dc2 != null && dc1.getValor() != 0) {
			int v = (int) dc1.getValor();
			spr2.setValue(v);
		}
		fila1.insertBefore(spr1, lblCarrerasA);
		fila2.insertBefore(spr2, lblCarrerasB);

		gridResultados.invalidate();

	}

	public void onCambio(InputEvent e) {

		Spinner spnr = (Spinner) formulario.getFellow(e.getTarget().getId());
		spnr.setValue(Integer.valueOf(e.getValue()));
		spnr.invalidate();
		acumular();

	}

	public void acumular() {
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
		gridResultados.invalidate();

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
		PersonalForaneo pf = (PersonalForaneo) cmbUmpires.getSelectedItem()
				.getValue();
		pfj.setJuego(juego);
		pfj.setPersonalForaneo(pf);
		pfj.setDatoBasico((DatoBasico) cmbPosiciones.getSelectedItem()
				.getValue());
		PersonalForaneoJuegoId id = new PersonalForaneoJuegoId(
				juego.getCodigoJuego(), pf.getCodigoPersonalForaneo());
		pfj.setId(id);
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
				Messagebox.show("¡Datos almacenados exitosamente", "Mensaje",
						Messagebox.OK, Messagebox.EXCLAMATION);

		}
	}

	public void onClick$btnPreliminar() {

		guardarColectivos();
		for (PersonalForaneoJuego pfj : umpiresJuego) {
			servicioPersonalForaneoJuego.agregar(pfj);
		}
		agregarAnotador();
		alert("Datos almacenados exitosamente!");
	}

	public void guardarColectivos() {
		Row row1 = (Row) gridResultados.getRows().getChildren().get(0);
		Row row2 = (Row) gridResultados.getRows().getChildren().get(1);
		EquipoJuego ej1 = equipos.get(0);
		EquipoJuego ej2 = equipos.get(1);
		IndicadorCategoriaCompetencia icc = servicioIndicadorCategoriaCompetencia
				.obtenerIndicadorCarrera();
		int innings = Integer.valueOf(txtInnings.getValue());
		for (int i = 1; i <= innings; i++) {
			DesempennoColectivo dc1 = servicioDesempennoColectivo.buscarCarrerasPorEquipo(ej1, icc, i);
			DesempennoColectivo dc2 = servicioDesempennoColectivo.buscarCarrerasPorEquipo(ej2, icc, i);
			if (dc1 == null) {
				dc1 = new DesempennoColectivo();
				dc1.setEquipoJuego(equipos.get(0));
				dc1.setIndicadorCategoriaCompetencia(icc);
				dc1.setInning(i);
				
			}	
			Spinner spnr1 = new Spinner();
			spnr1 = (Spinner) row1.getChildren().get(i);
			dc1.setValor(spnr1.getValue());
			
			if (dc2 == null) {
			   dc2 = new DesempennoColectivo();
				dc2.setEquipoJuego(equipos.get(1));
				dc2.setIndicadorCategoriaCompetencia(icc);
				dc2.setInning(i);
			}
			Spinner spnr2 = new Spinner();
			spnr2 = (Spinner) row2.getChildren().get(i);
			dc2.setValor(spnr2.getValue());

			

			servicioDesempennoColectivo.agregar(dc1);
			servicioDesempennoColectivo.agregar(dc2);

		}
		Label c1 = (Label) row1.getChildren().get(innings + 1);
		Label c2 = (Label) row2.getChildren().get(innings + 1);
		Spinner h1 = (Spinner) row1.getChildren().get(innings + 2);
		Spinner h2 = (Spinner) row2.getChildren().get(innings + 2);
		Spinner e1 = (Spinner) row2.getChildren().get(innings + 3);
		Spinner e2 = (Spinner) row2.getChildren().get(innings + 3);
		ej1.setCarrera(Integer.valueOf(c1.getValue()));
		ej2.setCarrera(Integer.valueOf(c2.getValue()));
		ej1.setHit(h1.getValue());
		ej2.setHit(h2.getValue());
		ej1.setError(e1.getValue());
		ej2.setError(e2.getValue());

		if (ej1.getCarrera() > ej2.getCarrera()) {
			ej1.setEstatus('G');
			ej1.setEstatus('P');
		} else if (ej1.getCarrera() < ej2.getCarrera()) {
			ej1.setEstatus('P');
			ej1.setEstatus('G');
		} else {
			ej1.setEstatus('P');
			ej1.setEstatus('P');
		}

		servicioEquipoJuego.agregar(ej1);
		servicioEquipoJuego.agregar(ej2);
	}

	public void agregarAnotador() {
		if (cmbAnotador.getSelectedItem() != null) {
			PersonalForaneoJuego pfj = new PersonalForaneoJuego();
			PersonalForaneo pf = (PersonalForaneo) cmbAnotador
					.getSelectedItem().getValue();
			pfj.setJuego(juego);
			pfj.setPersonalForaneo(pf);
			PersonalForaneoJuegoId id = new PersonalForaneoJuegoId(
					juego.getCodigoJuego(), pf.getCodigoPersonalForaneo());
			pfj.setId(id);
			DatoBasico db = servicioDatoBasico.buscarPorString("ANOTADOR");
			pfj.setDatoBasico(db);
			servicioPersonalForaneoJuego.agregar(pfj);
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

	public List<PersonalForaneo> getAnotadores() {
		return anotadores;
	}

	public void setAnotadores(List<PersonalForaneo> anotadores) {
		this.anotadores = anotadores;
	}

}
