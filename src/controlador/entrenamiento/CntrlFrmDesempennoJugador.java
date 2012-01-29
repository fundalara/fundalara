package controlador.entrenamiento;

import modelo.ActividadEntrenamiento;
import modelo.ActividadesEjecutadas;
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
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Timebox;
import org.zkoss.zul.Window;

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
import dao.generico.GenericDao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CntrlFrmDesempennoJugador extends GenericForwardComposer {
	Window wDesempeno_Atleta;
	Button btnSalir,btnCancelar, btnGuardar, btnImprimir;
	Combobox cmbFase, cmbActividad, cmbMaterial, cmbCantidad;
	Listitem lst2;
	Intbox ibTiempo;
	Listbox lbActividades, lista;
	Datebox dtbox1, dtbox2;
	Checkbox chcGeneral;
	ListenerCheck escuchador;
	Auxhead auxCabecera,auxTitulos;
	Listhead Cabecera;
	Equipo equipo;
	SesionEjecutada sesionEjecutada;
	Roster roster;
	ServicioRoster servicioRoster;
	ServicioSesionEjecutada servicioSesionEjecutada;
	ServicioLapsoDeportivo servicioLapsoDeportivo;
	ServicioAsistenciaJugador servicioAsistenciaJugador;
	ServicioDesempennoJugador servicioDesempennoJugador;
	ServicioPuntuacionJugador servicioPuntuacionJugador;
	ServicioValorEscala servicioValorEscala;
	List<Combobox> combos;
	ListenerCombo escuchadorCombo;
	
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		comp.setVariable("ctrl", this, true);
		Session session =new GenericDao().getSession();
		Transaction tx =  session.beginTransaction();
		equipo = (Equipo)session.load(Equipo.class, 11);
		Date date,hI,hF;
		DateFormat dateFormat = new SimpleDateFormat("dd");
		date= new Date();
		hF= new Date(date.getYear(), date.getMonth(), Integer.parseInt(dateFormat.format(date)), 12, 0);
		hI= new Date(date.getYear(), date.getMonth(), Integer.parseInt(dateFormat.format(date)), 11, 0);
		sesionEjecutada = servicioSesionEjecutada.buscarPorFechaHoraEquipo(equipo, date, hF, hI);
		combos= new ArrayList<Combobox>();
		escuchadorCombo = new ListenerCombo(lista);
		cargarCabecera();
		escuchador = new ListenerCheck(lista,chcGeneral);
		cargarRostrer();
		chcGeneral.addEventListener("onCheck",escuchador);
		ocultarCabeceras();
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
		for (ActividadesEjecutadas ejecutadas: sesionEjecutada.getActividadesEjecutadases()) {
			ActividadEntrenamiento ae=ejecutadas.getActividadEntrenamiento();
			lh = new Listheader(ae.getNombre());
				lh.setWidth(((ae.getDatoBasico().getNombre().length() * 10)) + "px");
				lh.setParent(Cabecera);
				combo = new Combobox();
				combo.setButtonVisible(true);
				combo.setWidth("50px");
				combo.addEventListener("onChange", escuchadorCombo);
				combo.setId(""+id);
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
		if(((LapsoDeportivo)servicioLapsoDeportivo.getDaoLapsoDeportivo().buscarUnCampo(LapsoDeportivo.class, "estatus", "A")).getDatoBasico().getNombre().compareTo("TEMPORADA REGULAR")==0)
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
		wDesempeno_Atleta.detach();
	}
	
	
	public Object obtenerComponenteListcell(int indexItem,int indexListCell) {
		return ((Listcell)lista.getItemAtIndex(indexItem).getChildren().get(indexListCell)).getChildren().get(0);
	}
	public void onClick$btnGuardar() {
		AsistenciaJugador asistenciaJugador;
		Checkbox checkAsistencia,checkLesionado,checkSancionado;
		Textbox txtObservacion;
		Combobox comboActividad;
		Roster roster;
		RosterPlan rosterPlan;
		ActividadesEjecutadas actividadesEjecutada = null;
		DesempennoJugador desempennoJugador;
		PuntuacionJugador puntuacionJugador;
		if(((LapsoDeportivo)servicioLapsoDeportivo.getDaoLapsoDeportivo().buscarUnCampo(LapsoDeportivo.class, "estatus", "A")).getDatoBasico().getNombre().compareTo("TEMPORADA REGULAR")==0){
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
					actividadesEjecutada=(ActividadesEjecutadas)comboActividad.getItemAtIndex(0).getValue();
					desempennoJugador= new DesempennoJugador();
					desempennoJugador.setCodigoDesempennoJugador(servicioDesempennoJugador.listar().size()+1);
					desempennoJugador.setAsistenciaJugador(asistenciaJugador);
					desempennoJugador.setActividadesEjecutadas(actividadesEjecutada);
					desempennoJugador.setEstatus('A');
					servicioDesempennoJugador.guardar(desempennoJugador);
					for(IndicadorActividadEscala indicadorActividadEscala : actividadesEjecutada.getActividadEntrenamiento().getIndicadorActividadEscalas()){
						puntuacionJugador= new PuntuacionJugador();
						puntuacionJugador.setId(new PuntuacionJugadorId(desempennoJugador.getCodigoDesempennoJugador(), indicadorActividadEscala.getCodigoIndicadorActividadEscala()));
						puntuacionJugador.setEstatus('A');
						puntuacionJugador.setPuntuacion(""+comboActividad.getSelectedItem().getLabel());
						servicioPuntuacionJugador.guardar(puntuacionJugador);
					}
				}
			}
		}
	}
	
	public void inicializar() {
		cmbFase.setDisabled(true);
		cmbActividad.setDisabled(true);
		ibTiempo.setDisabled(true);
		cmbMaterial.setDisabled(true);
		cmbCantidad.setDisabled(true);
		btnGuardar.setDisabled(true);
		btnImprimir.setDisabled(true);

	}

	public void limpiar1() {
		cmbFase.setSelectedIndex(-1);
		cmbFase.setValue(cmbFase.getValue().valueOf("--Seleccione--"));
		cmbActividad.setSelectedIndex(-1);
		cmbActividad.setValue(cmbFase.getValue().valueOf("--Seleccione--"));
		ibTiempo.setValue(0);
	}

	public void onClick$btnCancelar() {

		limpiar1();
		dtbox1.setValue(null);
		dtbox2.setValue(null);

		
		int contador = lbActividades.getItemCount();
		for (int A = 0; A < contador; A++) {
			lbActividades.removeItemAt(0);
		}
		inicializar();
	}
}