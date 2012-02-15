package controlador.entrenamiento;

import modelo.ActividadCalendario;
import modelo.ActividadEjecutada;
import modelo.ActividadEntrenamiento;
import modelo.AsistenciaJugador;
import modelo.DesempennoJugador;
import modelo.Equipo;
import modelo.IndicadorActividadEscala;
import modelo.Jugador;
import modelo.JugadorPlan;
import modelo.LapsoDeportivo;
import modelo.PuntuacionJugador;
import modelo.PuntuacionJugadorId;
import modelo.Roster;
import modelo.RosterPlan;
import modelo.SesionEjecutada;
import modelo.ValorEscala;

import org.hibernate.Session;
import org.hibernate.SessionException;
import org.hibernate.Transaction;
import org.omg.CORBA.OMGVMCID;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Auxhead;
import org.zkoss.zul.Auxheader;
import org.zkoss.zul.Button;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listhead;
import org.zkoss.zul.Listheader;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Timebox;
import org.zkoss.zul.Window;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ListItem;

import servicio.implementacion.ServicioActividadCalendario;
import servicio.implementacion.ServicioAsistenciaJugador;
import servicio.implementacion.ServicioDesempennoJugador;
import servicio.implementacion.ServicioLapsoDeportivo;
import servicio.implementacion.ServicioPuntuacionJugador;
import servicio.implementacion.ServicioRoster;
import servicio.implementacion.ServicioSesionEjecutada;
import servicio.implementacion.ServicioValorEscala;

import controlador.entrenamiento.ListenerCheck;
import controlador.entrenamiento.ModeloListBox;
import controlador.entrenamiento.Render;
import controlador.general.CntrlFrmAgendaEntrenamiento;
import controlador.general.EventoSimpleCalendario;
import dao.generico.GenericDao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CntrlFrmDesempennoJugador extends GenericForwardComposer {
	Window desempenoJugador;
	Button btnSalir,btnCancelar, btnGuardar, btnImprimir;
	Combobox cmbFase, cmbActividad, cmbMaterial, cmbCantidad;
	Listitem lst2;
	Intbox ibTiempo;
	Listbox lbActividades, lista;
	Textbox txtFec;
	Datebox dtbox1, dtbox2;
	Checkbox chcGeneral;
	ListenerCheck escuchador;
	Auxhead auxCabecera,auxTitulos;
	Listhead Cabecera;
	Equipo equipo;
	SesionEjecutada sesionEjecutada;
	Roster roster;
	LapsoDeportivo lapsoDeportivo;
	ServicioRoster servicioRoster;
	ServicioSesionEjecutada servicioSesionEjecutada;
	ServicioLapsoDeportivo servicioLapsoDeportivo;
	ServicioAsistenciaJugador servicioAsistenciaJugador;
	ServicioDesempennoJugador servicioDesempennoJugador;
	ServicioPuntuacionJugador servicioPuntuacionJugador;
	ServicioValorEscala servicioValorEscala;
	ServicioActividadCalendario servicioActividadCalendario;
	List<Combobox> combos;
	ListenerCombo escuchadorCombo;
	CntrlFrmAgendaEntrenamiento cntrlFrmAgendaEntrenamiento;
	ActividadCalendario actividadCalendario;
	EventoSimpleCalendario eventoSimpleCalendario;
	
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		comp.setVariable("ctrl", this, true);
		cntrlFrmAgendaEntrenamiento = (CntrlFrmAgendaEntrenamiento)execution.getAttribute("controlador");
		actividadCalendario= (ActividadCalendario)execution.getAttribute("actividadCalendario");
		eventoSimpleCalendario = (EventoSimpleCalendario)execution.getAttribute("evento");		
		sesionEjecutada = servicioSesionEjecutada.buscarPorSesionEquipoFecha(actividadCalendario.getSesion(),actividadCalendario.getSesion().getEquipo() , actividadCalendario.getFechaInicio());
		equipo= actividadCalendario.getSesion().getEquipo();
		lapsoDeportivo = actividadCalendario.getSesion().getPlanEntrenamiento().getPlanTemporada().getLapsoDeportivo();
		inicializar();
	}
	
	
	
	private void ocultarCabeceras() {
		// TODO Auto-generated method stub
		Listheader lh1,lh2;
		 for (int i = 0; i < Cabecera.getChildren().size(); i++) {
		     for (int j = i+1; j <Cabecera.getChildren().size() - 1; j++) {
		    	 lh1=(Listheader)Cabecera.getChildren().get(i);
		    	 lh2=(Listheader)Cabecera.getChildren().get(j);
		    	 if(lh1.getLabel().compareTo(lh2.getLabel())==0)
		    		 lh2.setLabel("");
		     }
		}
	}



	private void cargarCabecera() {
		// TODO Auto-generated method stub
		Listheader lh;
		Combobox combo;
		Comboitem item;
		Auxheader ah;
		int id = 2;
		for (ActividadEjecutada ejecutadas: sesionEjecutada.getActividadEjecutadas()) {
			ActividadEntrenamiento ae=ejecutadas.getActividadEntrenamiento();
			lh = new Listheader(ae.getNombre());
				lh.setWidth(((ae.getDatoBasico().getNombre().length() * 10)) + "px");
				lh.setParent(Cabecera);
				combo = new Combobox();
				combo.setButtonVisible(true);
				combo.setWidth("50px");
				combo.addEventListener("onChange", escuchadorCombo);
				combo.setAttribute("index",id);
				id++;
				ah = new Auxheader();
				ah.setAlign("left");
				IndicadorActividadEscala iae=null;
				for(IndicadorActividadEscala escala : ae.getIndicadorActividadEscalas()){
					iae = escala;
					break;
					
				}
				if(iae!=null){
					for (ValorEscala ve : servicioValorEscala.buscarValor(iae.getEscalaMedicion())) {
						item = new Comboitem();
						item.setValue(ejecutadas);
						item.setLabel(ve.getValor());
						item.setTooltiptext(ve.getDescripcion());
						item.setParent(combo);
					}
					ah.setWidth(((iae.getDatoBasico().getNombre().length() * 10)+60) + "px");
				}
				combo.setSelectedIndex(0);
				combos.add(combo);
				ah.appendChild(combo);
				auxCabecera.appendChild(ah);
			
		}
		auxCabecera.appendChild(new Auxheader());
		lh = new Listheader("OBSERVACION");
		lh.setWidth("110px");
		lh.setParent(Cabecera);
		auxCabecera.appendChild(new Auxheader());
		lh = new Listheader("LESIONADO");
		lh.setWidth("90px");
		lh.setParent(Cabecera);
		auxCabecera.appendChild(new Auxheader());
		lh = new Listheader("SANCIONADO");
		lh.setWidth("100px");
		lh.setParent(Cabecera);
	}
	

	private void cargarActividades() {
//		for (ActividadesEjecutadas ejecutadas: sesionEjecutada.getActividadesEjecutadases()) {
//			for(IndicadorActividadEscala escala:ejecutadas.getActividadEntrenamiento().getIndicadorActividadEscalas()){}
//			
//		}
		
		
	}



	private void cargarRostrer() {
		// TODO Auto-generated method stub
		List registro;
		List matriz = new ArrayList();
		if(lapsoDeportivo.getDatoBasico().getNombre().compareTo("TEMPORADA REGULAR")==0)
			for (Roster roster : equipo.getRosters()) {
				Jugador jugador = roster.getJugador();
				registro = new ArrayList();
				mostrarJugador(registro, jugador,roster);
				matriz.add(registro);
			}
		else
			for (RosterPlan roster : equipo.getRosterPlans()) {
				JugadorPlan jugador = roster.getJugadorPlan();
				registro = new ArrayList();
				mostrarJugadorPlan(registro, jugador,roster);
				matriz.add(registro);
			}
		ModeloListBox modelo = new ModeloListBox(matriz);
		lista.setModel(modelo);
		lista.setItemRenderer(new Render(combos));
	}
	
	public void mostrarJugador(List registro,Jugador jugador,Roster roster) {
		Checkbox c;
		String numero=""+jugador.getNumero();
		if(numero.length()==2)
			c = new Checkbox(numero);
		else
			c = new Checkbox("   "+numero);
		c.setWidth("20px");
		c.setChecked(true);
		c.addEventListener("onCheck", escuchador);
		registro.add(c);
		registro.add(jugador.getPersonaNatural().getPrimerNombre()+" "+jugador.getPersonaNatural().getPrimerApellido());
		for (int i = 0; i < combos.size(); i++) {
			registro.add(i);
		}
		registro.add(new Textbox());
		registro.add(new Checkbox());
		registro.add(new Checkbox());
		registro.add(roster);
	}
	
	public void mostrarJugadorPlan(List registro,JugadorPlan jugador,RosterPlan rosterPlan) {
		Checkbox c;
		String numero=""+jugador.getNombre();
		if(numero.length()==2)
			c = new Checkbox(numero);
		else
			c = new Checkbox("   "+numero);
		c.setWidth("20px");
		c.addEventListener("onCheck", escuchador);
		registro.add(c);
		registro.add(jugador.getNombre()+" "+jugador.getApellido());
		for (int i = 0; i < combos.size(); i++) {
			registro.add(i);
		}
		registro.add(new Textbox());
		registro.add(new Checkbox());
		registro.add(new Checkbox());
		registro.add(rosterPlan);
	}



	public ListenerCheck getEscuchador() {
		return escuchador;
	}



	public void setEscuchador(ListenerCheck escuchador) {
		this.escuchador = escuchador;
	}



	public void onClick$btnSalir() {
		desempenoJugador.detach();
	}
	
	
	public Object obtenerComponenteListcell(int indexItem,int indexListCell) {
		return ((Listcell)lista.getItemAtIndex(indexItem).getChildren().get(indexListCell)).getChildren().get(0);
	}
	
	public void onClick$btnGuardar() throws InterruptedException {
		AsistenciaJugador asistenciaJugador;
		Checkbox checkAsistencia,checkLesionado,checkSancionado;
		Textbox txtObservacion;
		Combobox comboActividad;
		Roster roster;
		RosterPlan rosterPlan;
		ActividadEjecutada actividadesEjecutada = null;
		DesempennoJugador desempennoJugador;
		PuntuacionJugador puntuacionJugador;
		if(lapsoDeportivo.getDatoBasico().getNombre().compareTo("TEMPORADA REGULAR")==0){
			for (int i = 0; i < lista.getItemCount(); i++) {
				if(lista.getItemAtIndex(i).isDisabled())
					continue;
				asistenciaJugador = new AsistenciaJugador();
				asistenciaJugador.setCodigoAsistencia(servicioAsistenciaJugador.listar().size()+1);
				checkAsistencia = (Checkbox)obtenerComponenteListcell(i,0);
				checkSancionado = (Checkbox)obtenerComponenteListcell(i,lista.getItemAtIndex(i).getChildren().size()-1);
				checkLesionado = (Checkbox)obtenerComponenteListcell(i,lista.getItemAtIndex(i).getChildren().size()-2);
				txtObservacion = (Textbox)obtenerComponenteListcell(i,lista.getItemAtIndex(i).getChildren().size()-3);
				roster = (Roster)lista.getItemAtIndex(i).getValue();
				asistenciaJugador.setAsistencia(checkAsistencia.isChecked());
				asistenciaJugador.setEstatus('A');
				asistenciaJugador.setRoster(roster);
				asistenciaJugador.setSesionEjecutada(sesionEjecutada);
				asistenciaJugador.setObservacion(txtObservacion.getText());
				if(checkLesionado.isChecked() && checkSancionado.isChecked())
					roster.getJugador().setEstatus('M');
				else if(checkLesionado.isChecked())
					roster.getJugador().setEstatus('L');
				else if(checkSancionado.isChecked())
					roster.getJugador().setEstatus('S');
				servicioAsistenciaJugador.guardar(asistenciaJugador);
				for (int j = 2; j < (lista.getItemAtIndex(i).getChildren().size()-3); j++) {
					comboActividad =(Combobox) obtenerComponenteListcell(i, j);
					actividadesEjecutada=(ActividadEjecutada)comboActividad.getItemAtIndex(0).getValue();
					desempennoJugador= new DesempennoJugador();
					desempennoJugador.setCodigoDesempennoJugador(servicioDesempennoJugador.listar().size()+1);
					desempennoJugador.setAsistenciaJugador(asistenciaJugador);
					desempennoJugador.setActividadEjecutada(actividadesEjecutada);
					desempennoJugador.setEstatus('A');
					servicioDesempennoJugador.guardar(desempennoJugador);
					for(IndicadorActividadEscala indicadorActividadEscala : actividadesEjecutada.getActividadEntrenamiento().getIndicadorActividadEscalas()){
						puntuacionJugador= new PuntuacionJugador();
						puntuacionJugador.setId(new PuntuacionJugadorId(desempennoJugador.getCodigoDesempennoJugador(), indicadorActividadEscala.getCodigoIndicadorActividadEscala()));
						puntuacionJugador.setEstatus('A');
						puntuacionJugador.setPuntuacion(""+comboActividad.getSelectedItem().getLabel());
						servicioPuntuacionJugador.agregar(puntuacionJugador);
					}
				}
			}
		}
		sesionEjecutada.setEstatus('C');
		servicioSesionEjecutada.actualizar(sesionEjecutada);
		actividadCalendario.setEstatus('F');		
		servicioActividadCalendario.actualizar(actividadCalendario);
		cntrlFrmAgendaEntrenamiento.reiniciarModelo();
		Messagebox
		.show("Resultados Guardados Exitosamente",
				"Olimpo - Informacion", Messagebox.OK,
				Messagebox.INFORMATION);
		desempenoJugador.detach();
	}
	
	public void inicializar() {
		desempenoJugador.setTitle("Rendimiento del Jugador - "+actividadCalendario.getSesion().getEquipo().getNombre()+" - "+actividadCalendario.getSesion().getEquipo().getCategoria().getNombre());
		txtFec.setValue(new SimpleDateFormat("dd/MM/yyyy").format(actividadCalendario.getFechaInicio()));
		combos= new ArrayList<Combobox>();
		escuchadorCombo = new ListenerCombo(lista);
		cargarCabecera();
		escuchador = new ListenerCheck(lista,chcGeneral);
		chcGeneral.addEventListener("onCheck",escuchador);
		chcGeneral.setChecked(true);
		ocultarCabeceras();
		int cant = lista.getItemCount();
		for (int i = 0; i < cant; i++) {
			lista.removeItemAt(0);
		}
		System.gc();
		cargarRostrer();
	}

	public void limpiar() {
		for (int i = 0; i < lista.getItemCount(); i++) {
			Listitem  listitem =lista.getItemAtIndex(i);
			if (listitem.getChildren().size()<=1)
				continue;
			Checkbox checkAsistencia = (Checkbox)((Listcell)listitem.getChildren().get(0)).getChildren().get(0);
			Checkbox checkSancionado = (Checkbox)((Listcell)listitem.getChildren().get(listitem.getChildren().size()-1)).getChildren().get(0);
			Checkbox checkLesionado = (Checkbox)((Listcell)listitem.getChildren().get(listitem.getChildren().size()-2)).getChildren().get(0);
			Textbox txtObservacion = (Textbox)((Listcell)listitem.getChildren().get(listitem.getChildren().size()-3)).getChildren().get(0);
			checkAsistencia.setChecked(true);
			checkLesionado.setChecked(false);
			checkSancionado.setChecked(false);
			txtObservacion.setValue("");
			checkAsistencia.setDisabled(false);
			checkLesionado.setDisabled(false);
			checkSancionado.setDisabled(false);
			txtObservacion.setDisabled(false);
			for (int j = 2; j < (listitem.getChildren().size()-3); j++) {
				Combobox combo =(Combobox)((Listcell)listitem.getChildren().get(j)).getChildren().get(0);
				combo.setSelectedIndex(0);
				combo.setDisabled(false);
			}
			listitem.setDisabled(false);
		}
		chcGeneral.setChecked(true);
		System.out.println(auxCabecera.getChildren()+" "+auxCabecera.getChildren().size());
		for (int j = 1; j < (auxCabecera.getChildren().size()-3); j++) {
			Auxheader auxheader = (Auxheader) auxCabecera.getChildren().get(j);
			Combobox combo =(Combobox) auxheader.getChildren().get(0);
			combo.setSelectedIndex(0);
			System.out.println(combo);
		}
	}

	public void onClick$btnCancelar() {
		limpiar();
	}
}
