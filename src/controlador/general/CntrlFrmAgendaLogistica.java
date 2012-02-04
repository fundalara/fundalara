package controlador.general;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import modelo.Actividad;
import modelo.DatoBasico;
import modelo.EstadoActividad;
import modelo.PlanificacionActividad;

import org.zkoss.calendar.event.CalendarsEvent;
import org.zkoss.calendar.impl.SimpleCalendarEvent;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Timebox;
import org.zkoss.zul.Window;

import servicio.interfaz.IServicioPlanificacionActividad;

public class CntrlFrmAgendaLogistica extends CntrlFrmAgendaGeneral{
	
	private String opcionMenu;
	public EventosCalendario ec;
	public Window frmAgenda;
	private Window frmPlanificar;
	private Datebox frmPlanificar$fechaInicio;
	private Datebox frmPlanificar$fechaFin;
	private Timebox frmPlanificar$horaInicio;
	private Timebox frmPlanificar$horaFin;
	private IServicioPlanificacionActividad servicioPlanificacionActividad; 
    
	@Override
	public void doAfterCompose(Component component) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(component);
		
		frmAgenda = (Window) component;
		
		// configurando visualizacion
		calendars.setMold("default");
		calendars.setDays(7);
		calendars.setReadonly(false);
		
		opcionMenu = (String) frmAgenda.getAttribute("opcion");
		
		if(opcionMenu.equals("ActividadComplementaria")){
//			List<PlanificacionActividad> pa = servicioPlanificacionActividad.listarComplementarias();
//			System.out.println("Tamañooooo: "+pa.size());
			cargarCalendario(servicioPlanificacionActividad.listarComplementarias());
			calendars.setTooltiptext("Haga click sobre una fecha para planificar una actividad");				
			Messagebox.show("Haga click sobre una fecha para planificar una actividad", "Mensaje",Messagebox.OK, Messagebox.INFORMATION);
		}
		else{
			cargarCalendario(servicioPlanificacionActividad.listarMantenimientos());
			calendars.setTooltiptext("Haga click sobre una fecha para planificar un mantenimiento");				
			Messagebox.show("Haga click sobre una fecha para planificar un mantenimiento", "Mensaje",Messagebox.OK, Messagebox.INFORMATION);
		}
	}
	
	public void cargarCalendario(List<PlanificacionActividad> eventos){
		
		SimpleCalendarEvent evento;		
		ec = new EventosCalendario();
		
		System.out.println("cargandooo calandarioooooooooo :"+eventos.size());
		
		for(PlanificacionActividad evt: eventos){
			 Set<Actividad> actividades = evt.getActividads();
			 for(Actividad actividad: actividades){
				 Date fi = new Date(actividad.getFechaInicio().getTime() + actividad.getHoraInicio().getTime());
				 Date ff = new Date(actividad.getFechaCulminacion().getTime() + actividad.getHoraFin().getTime());
				 String color = obtenerColor(actividad);
				 evento = crearEvento(fi,ff, evt.getDescripcion(), color, evt);							
				 ec.cargarEvento(evento);
			 }
		}
		calendars.setModel(ec.getModel());
		
	}
	
	public String obtenerColor(Actividad a){
		
		EstadoActividad estatus = new EstadoActividad();
		DatoBasico estado = new DatoBasico();
		
		Iterator<EstadoActividad> i = a.getEstadoActividads().iterator();
		while(i.hasNext()){
			estatus = i.next();
//			System.out.println(estatus.getDatoBasico().getDescripcion());
			if(estatus.getEstatus()=='A'){
				estado = estatus.getDatoBasico();
			}
		}
		if(estado.getNombre() != null){
		 if(estado.getNombre().equals("SUSPENDIDA")){
			 //System.out.println("suspendida");
			 return color[0];
		 }else if(estado.getNombre().equals("TERMINADA")){
			//System.out.println("completada con resultados ya registrados");
			 return color[2];
		 }
		 else if (a.getFechaInicio().after(new Date())){
			//System.out.println("programada por realizarse");
			return color[1];
		 }else{
			//System.out.println("culminada sin registrar resultados");
			 return color[3];
		 }
		}
		else return null;
	}
	
	public void onEventCreate$calendars(ForwardEvent event) throws InterruptedException {
		if(opcionMenu.equals("ActividadComplementaria")){
			planificar(event, "Logistica/Vistas/frmPlanificarActividad.zul");
		}
		else{
			planificar(event, "Logistica/Vistas/frmPlanificarMantenimiento.zul");
		}							
	}
	
	public void onEventEdit$calendars(CalendarsEvent e) {
		System.out.println("editando las actividades");
		if(opcionMenu.equals("ActividadComplementaria")){
			registrarResultados(e, "Logistica/Vistas/frmResultadosActividadComplementaria.zul");			
		}
		else{
			registrarResultados(e, "Logistica/Vistas/frmResultadosMantenimiento.zul");
		}
		
//		ec = new EventosCalendario();
//		SimpleCalendarEvent evento = crearEvento(e.getBeginDate(),e.getEndDate(), "ANIVERSARIO FUNDALARA", color[2], null);							
//		ec.cargarEvento(evento);
		 
//		calendars.setModel(ec.getModel());
		
		
	}
	
	public void registrarResultados(CalendarsEvent e, String url){
		EventoSimpleCalendario esc = (EventoSimpleCalendario) e.getCalendarEvent();		
		Window w = (Window) Executions.createComponents(url, null, null);
        w.setPosition("center");
        PlanificacionActividad planificacionActividad = (PlanificacionActividad) esc.getValue();
        
        System.out.println(planificacionActividad.getCodigoPlanificacionActividad());
        w.setVariable("planificacionActividad", planificacionActividad, false);
        w.setVariable("esc", esc, false);
        w.setVariable("agenda", this, false);
        
//        esc.setContentColor(color[0]);
        
        
        //ec = new EventosCalendario();
		//SimpleCalendarEvent evento = crearEvento(ce.getBeginDate(),ce.getEndDate(), pa.getDescripcion(), color[1], pa);							
		
	}
	
	
	public void cargarUltimo(SimpleCalendarEvent esc){
		//ec.cargarEvento(esc);
		 
		calendars.setModel(ec.getModel());
	}
	
	public void planificar(ForwardEvent event, String url){
				
		frmPlanificar = (Window) Executions.createComponents(url, null,	null);
		
		frmPlanificar.setAttribute("calendario", this);
		
		CalendarsEvent ce = (CalendarsEvent) event.getOrigin(); 
		
		frmPlanificar$fechaInicio = (Datebox) frmPlanificar.getFellow("fechaInicio");
		frmPlanificar$fechaFin = (Datebox) frmPlanificar.getFellow("fechaFin");
		frmPlanificar$horaInicio = (Timebox) frmPlanificar.getFellow("horaInicio");
		frmPlanificar$horaFin = (Timebox) frmPlanificar.getFellow("horaFin");
		
		Date fechaInicio = ce.getBeginDate();
		fechaInicio =  new Date(fechaInicio.getYear(), fechaInicio.getMonth(), fechaInicio.getDate());
		Date fechaFin = ce.getEndDate();
		fechaFin =  new Date(fechaFin.getYear(), fechaFin.getMonth(), fechaFin.getDate());				
				
		frmPlanificar$fechaInicio.setValue(fechaInicio);
		frmPlanificar$fechaFin.setValue(fechaFin);
		
		frmPlanificar$horaInicio.setValue(ce.getBeginDate());
		frmPlanificar$horaFin.setValue(ce.getEndDate());	
		
		frmPlanificar.setAttribute("ce", ce);
		
	}
	
	public void cargar(CalendarsEvent ce, PlanificacionActividad pa){
		//ec = new EventosCalendario();
		SimpleCalendarEvent evento = crearEvento(ce.getBeginDate(),ce.getEndDate(), pa.getDescripcion(), color[1], pa);							
		ec.cargarEvento(evento);
		 
		calendars.setModel(ec.getModel());
	}

}
