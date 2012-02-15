package controlador.competencia;




import org.apache.poi.hssf.util.HSSFColor.ROSE;
import org.zkoss.zk.ui.event.EventListener;
import modelo.PersonalCargo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import modelo.Categoria;
import modelo.Competencia;
import modelo.Divisa;
import modelo.Equipo;
import modelo.EquipoCompetencia;
import modelo.JugadorForaneo;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.api.Comboitem;

import comun.EstadoCompetencia;

import servicio.implementacion.ServicioCategoria;
import servicio.implementacion.ServicioEquipo;
import servicio.implementacion.ServicioEquipoCompetencia;
import servicio.implementacion.ServicioJugadorForaneo;
import servicio.implementacion.ServicioRoster;
import servicio.implementacion.ServicioRosterCompetencia;

import org.zkoss.zk.ui.event.EventListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import modelo.Categoria;
import modelo.CategoriaCompetencia;
import modelo.ClasificacionCompetencia;
import modelo.Competencia;
import modelo.Divisa;
import modelo.Equipo;
import modelo.Jugador;

import modelo.PersonaNatural;
import modelo.Roster;
import modelo.RosterCompetencia;


import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.api.Comboitem;

import comun.EstadoCompetencia;

import servicio.implementacion.ServicioCategoria;
import servicio.implementacion.ServicioEquipo;
import servicio.implementacion.ServicioRoster;
import servicio.implementacion.ServicioPersonalCargo;
import servicio.implementacion.ServicioPersonalEquipo;

import modelo.PersonalEquipo;
import modelo.DatoBasico;


public class CntrlFrmRoster extends GenericForwardComposer {
	
	
	
	//Servicios utilizados
	ServicioJugadorForaneo servicioJugadorForaneo;
	ServicioEquipoCompetencia servicioEquipoCompetencia;
	ServicioCategoria servicioCategoria;
	ServicioEquipo servicioEquipo;
	ServicioPersonalCargo servicioPersonalCargo;
	ServicioPersonalEquipo servicioPersonalEquipo;
	ServicioRosterCompetencia servicioRosterCompetencia;
	
	
	
	//Listas
	
	List<PersonalCargo> managers;
	List<PersonalEquipo> personalxEquipo;
	List<PersonalEquipo> personas;
	List<EquipoCompetencia> equipoxCompetencia;
	List<JugadorForaneo> jugadoresSeleccionados;
    List<JugadorForaneo> jugadors = new ArrayList<JugadorForaneo>();
    List<Jugador> jugadores;
    List<RosterCompetencia> rCompetencia;
    List<Roster> jugadoresxRoster = new ArrayList<Roster>();
    List<Categoria> categorias;
	List<EquipoCompetencia> equipos;
	List<RosterCompetencia> competenciasRegistradas;
	List<RosterCompetencia> rosterCompetenciaNuevas = new ArrayList<RosterCompetencia>();
    
    //Atributos
	int totalJugadorForaneo;
	ClasificacionCompetencia clasificacionCompetencia;
	EquipoCompetencia eq;
	RosterCompetencia rostercompetenciaforaneo;
	RosterCompetencia rostercompetenciajugador;
	RosterCompetencia jugadorRcomp;
	RosterCompetencia auxRcomp;
	Roster rosterSeleccion;
	Integer codEq;
	Integer codCat;
	Textbox txtCedula;
	Textbox txtNombre;
	Textbox txtEquipo;
	AnnotateDataBinder binder;
	Component formulario;
	Textbox txtIdCompetencia;
	Button btnEliminarForaneo;
	Categoria categoria;
	Equipo equipo;
	Competencia competencia;
	Combobox cmbCategoria;
	ServicioRoster servicioRoster;
	Combobox cmbEquipo;
	String categoriaSeleccion;
	int codManager= 166;
	JugadorForaneo jExis;
	JugadorForaneo jugadorForaneo;
    EquipoCompetencia equipoCompetencia;
    String Categorias;
    Listbox lsbxJugadors;
    Listbox lsbxJugadoresSeleccionados;
    Listbox lsbxJugadoresxRoster;
    RosterCompetencia rosterCompetencia;
    
    Equipo equipoForaneo;
    Combobox cmbNacionalidad;
    PersonaNatural personaNatural;
    Textbox txtManager;
    DatoBasico datoBasico;
    PersonalEquipo personalEquipo;
    Combobox cmbManager;
    CategoriaCompetencia categoriaCompetencia;
	Roster roster;
	RosterCompetencia rostercompnuevo;
	Listbox lsbxJugadores;
	Jugador jugador;
	Textbox txtNombreCompetencia;
	Textbox txtTipo;
	Textbox txtModalidad;
	Textbox txtfinicio;
	Textbox txtffin;
	Textbox txtDelegado;
	int totalMaxForaneos = 3;
	int totalMaxRoster = 20;
	int totalMinRoster = 10;
	int totalListaRoster = 0;
    Button btnBuscarEquipo;
	int jugadoresforaneos = 0;
	Button btnAgregarRoster;
	Button btnQuitarRoster;
	Button btnGuardarRoster;
	Button btnCancelarRoster;
	Button btnBuscarJForaneo;
	Button btnAgregarForaneo;
	Button btnQuitarForaneo;
	int codcomp;
	
    
    
    
    
    
    
	
    //INICIO GETTERS & SETTERS
	
	
	
	public JugadorForaneo getjExis() {
		return jExis;
	}
	public RosterCompetencia getRostercompetenciajugador() {
		return rostercompetenciajugador;
	}
	public void setRostercompetenciajugador(
			RosterCompetencia rostercompetenciajugador) {
		this.rostercompetenciajugador = rostercompetenciajugador;
	}
	public void setjExis(JugadorForaneo jExis) {
		this.jExis = jExis;
	}

    
	public List<RosterCompetencia> getRosterCompetenciaNuevas() {
		return rosterCompetenciaNuevas;
	}
	public void setRosterCompetenciaNuevas(
			List<RosterCompetencia> rosterCompetenciaNuevas) {
		this.rosterCompetenciaNuevas = rosterCompetenciaNuevas;
	}
	public RosterCompetencia getRostercompetenciaforaneo() {
		return rostercompetenciaforaneo;
	}

	public void setRostercompetenciaforaneo(
			RosterCompetencia rostercompetenciaforaneo) {
		this.rostercompetenciaforaneo = rostercompetenciaforaneo;
	}
    public DatoBasico getDatoBasico() {
		return datoBasico;
	}
	public PersonalEquipo getPersonalEquipo() {
		return personalEquipo;
	}
	public void setPersonalEquipo(PersonalEquipo personalEquipo) {
		this.personalEquipo = personalEquipo;
	}
	public void setDatoBasico(DatoBasico datoBasico) {
		this.datoBasico = datoBasico;
	}

	List<RosterCompetencia> jugadoresRosterC;
	public List<RosterCompetencia> getJugadoresRosterC() {
		return jugadoresRosterC;
	}
	public void setJugadoresRosterC(List<RosterCompetencia> jugadoresRosterC) {
		this.jugadoresRosterC = jugadoresRosterC;
	}

	
	public PersonaNatural getPersonaNatural() {
		return personaNatural;
	}
	public void setPersonaNatural(PersonaNatural personaNatural) {
		this.personaNatural = personaNatural;
	}

	public List<EquipoCompetencia> getEquipoxCompetencia() {
		return equipoxCompetencia;
	}
	public void setEquipoxCompetencia(List<EquipoCompetencia> equipoxCompetencia) {
		this.equipoxCompetencia = equipoxCompetencia;
	}
	public String getCategoriaSeleccion() {
		return categoriaSeleccion;
	}
	public void setCategoriaSeleccion(String categoriaSeleccion) {
		this.categoriaSeleccion = categoriaSeleccion;
	}
	public void setCategorias(String categorias) {
		Categorias = categorias;
	}
	public List<Jugador> getJugadores() {
		return jugadores;
	}
	public void setJugadores(List<Jugador> jugadores) {
		this.jugadores = jugadores;
	}
	public Listbox getLsbxJugadoresxRoster() {
		return lsbxJugadoresxRoster;
	}
	public void setLsbxJugadoresxRoster(Listbox lsbxJugadoresxRoster) {
		this.lsbxJugadoresxRoster = lsbxJugadoresxRoster;
	}
	public RosterCompetencia getRosterCompetencia() {
		return rosterCompetencia;
	}
	public void setRosterCompetencia(RosterCompetencia rosterCompetencia) {
		this.rosterCompetencia = rosterCompetencia;
	}
	public List<RosterCompetencia> getrCompetencia() {
		return rCompetencia;
	}
	public void setrCompetencia(List<RosterCompetencia> rCompetencia) {
		this.rCompetencia = rCompetencia;
	}
	
	public List<Roster> getJugadoresxRoster() {
		return jugadoresxRoster;
	}

	public void setJugadoresxRoster(List<Roster> jugadoresxRoster) {
		this.jugadoresxRoster = jugadoresxRoster;
	}

	public Roster getRoster() {
		return roster;
	}

	public void setRoster(Roster roster) {
		this.roster = roster;
	}

	public Listbox getLsbxJugadores() {
		return lsbxJugadores;
	}

	public void setLsbxJugadores(Listbox lsbxJugadores) {
		this.lsbxJugadores = lsbxJugadores;
	}

	public Jugador getJugador() {
		return jugador;
	}

	public void setJugador(Jugador jugador) {
		this.jugador = jugador;
	}

	public CategoriaCompetencia getCategoriaCompetencia() {
		return categoriaCompetencia;
	}

	public void setCategoriaCompetencia(CategoriaCompetencia categoriaCompetencia) {
		this.categoriaCompetencia = categoriaCompetencia;
	}

	public Equipo getEquipo() {
		return equipo;
	}

	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}

	public List<EquipoCompetencia> getEquipos() {
		return equipos;
	}

	public void setEquipos(List<EquipoCompetencia> equipos) {
		this.equipos = equipos;
	}

	public Combobox getCmbEquipo() {
		return cmbEquipo;
	}

	public void setCmbEquipo(Combobox cmbEquipo) {
		this.cmbEquipo = cmbEquipo;
	}

	
	
	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Combobox getCmbCategoria() {
		return cmbCategoria;
	}

	public void setCmbCategoria(Combobox cmbCategoria) {
		this.cmbCategoria = cmbCategoria;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}
	
	public List<PersonalCargo> getManagers() {
		return managers;
	}
	public void setManagers(List<PersonalCargo> managers) {
		this.managers = managers;
	}
	public List<PersonalEquipo> getPersonalxEquipo() {
		return personalxEquipo;
	}
	public void setPersonalxEquipo(List<PersonalEquipo> personalxEquipo) {
		this.personalxEquipo = personalxEquipo;
	}
	
    public List<JugadorForaneo> getJugadoresSeleccionados() {
    	return jugadoresSeleccionados;
    }
    public List<JugadorForaneo> getJugadors() {
		return jugadors;
	}
	public void setJugadors(List<JugadorForaneo> jugadors) {
		this.jugadors = jugadors;
	}
	public void setJugadoresSeleccionado(List<JugadorForaneo> jugadoresSeleccionados) {
    	this.jugadoresSeleccionados = jugadoresSeleccionados;
    }
    public Listbox getLsbxJugadors() {
		return lsbxJugadors;
	}

	public void setLsbxJugadors(Listbox lsbxJugadors) {
		this.lsbxJugadors = lsbxJugadors;
	}

	public void setJugadoresSeleccionados(
			List<JugadorForaneo> jugadoresSeleccionados) {
		this.jugadoresSeleccionados = jugadoresSeleccionados;
	}

	public Listbox getLsbxJugadoresSeleccionados() {
    	return lsbxJugadoresSeleccionados;
    }
    public void setLsbxJugadoresSeleccionados(Listbox lsbxJugadoresSeleccionados) {
    	this.lsbxJugadoresSeleccionados = lsbxJugadoresSeleccionados;
    }
    public ServicioJugadorForaneo getServicioJugadorForaneo() {
    	return servicioJugadorForaneo;
    }
    public void setServicioJugadorForaneo(
    		ServicioJugadorForaneo servicioJugadorForaneo) {
    	this.servicioJugadorForaneo = servicioJugadorForaneo;
    }
    public JugadorForaneo getJugadorForaneo() {
    	return jugadorForaneo;
    }
    public void setJugadorForaneo(JugadorForaneo jugadorForaneo) {
    	this.jugadorForaneo = jugadorForaneo;
    }

    public EquipoCompetencia getEquipoCompetencia() {
    	return equipoCompetencia;
    }
    public void setEquipoCompetencia(EquipoCompetencia equipoCompetencia) {
    	this.equipoCompetencia = equipoCompetencia;
    }

    public Competencia getCompetencia() {
		return competencia;
	}

	public void setCompetencia(Competencia competencia) {
		this.competencia = competencia;
	}
	
	public Equipo getEquipoForaneo() {
		return equipoForaneo;
	}
	public void setEquipoForaneo(Equipo equipoForaneo) {
		this.equipoForaneo = equipoForaneo;
	}
	
	public RosterCompetencia getJugadorRcomp() {
		return jugadorRcomp;
	}
	public void setJugadorRcomp(RosterCompetencia jugadorRcomp) {
		this.jugadorRcomp = jugadorRcomp;
	}
	public RosterCompetencia getAuxRcomp() {
		return auxRcomp;
	}
	public void setAuxRcomp(RosterCompetencia auxRcomp) {
		this.auxRcomp = auxRcomp;
	}
	
	
	public ClasificacionCompetencia getClasificacionCompetencia() {
		return clasificacionCompetencia;
	}
	public void setClasificacionCompetencia(
			ClasificacionCompetencia clasificacionCompetencia) {
		this.clasificacionCompetencia = clasificacionCompetencia;
	}
	//FIN GETTERS & SETTERS
	
	
	public void doAfterCompose(Component c) throws Exception {
		super.doAfterCompose(c);
	
		c.setVariable("cntrl", this, true);
	
		formulario = c;
		
		
	restaurar();
	
	if (lsbxJugadores.getItems().size() != 0)
		lsbxJugadores.setSelectedIndex(0);
	
	

		
	}
	public void restaurar(){
		txtDelegado.setDisabled(true);
		//btnQuitarForaneo.setDisabled(true);
		//btnAgregarForaneo.setDisabled(true);
		txtNombreCompetencia.setDisabled(true);
		txtTipo.setDisabled(true);
		txtModalidad.setDisabled(true);
		txtfinicio.setDisabled(true);
		txtffin.setDisabled(true);
		txtNombre.setDisabled(true);
		cmbNacionalidad.setDisabled(true);
		txtCedula.setReadonly(true);
		btnBuscarJForaneo.setDisabled(true);
		btnBuscarEquipo.setDisabled(true);
		cmbCategoria.setDisabled(true);
		cmbEquipo.setDisabled(true);
		cmbManager.setDisabled(true);
		btnQuitarRoster.setDisabled(true);
		btnAgregarRoster.setDisabled(true);
		btnGuardarRoster.setDisabled(true);
		btnCancelarRoster.setDisabled(true);
		
		equipoCompetencia = new EquipoCompetencia();
		
		
		
		categoria = new Categoria();
		
		personaNatural = new PersonaNatural();
		personalEquipo = new PersonalEquipo();
		clasificacionCompetencia = new ClasificacionCompetencia();
		
		rostercompetenciaforaneo = new RosterCompetencia();
		rostercompetenciajugador = new RosterCompetencia();
		equipoForaneo = new Equipo();
		jugadorForaneo = new JugadorForaneo();
		cmbNacionalidad.setValue("-");
		rostercompetenciaforaneo = new RosterCompetencia();
		rosterCompetencia = new RosterCompetencia();
		jugadorForaneo = new JugadorForaneo(); 
		
		
		managers = servicioPersonalCargo.listarporCargo(codManager);
		
		System.out.print("cant de managers=");
		System.out.println(managers.size());
	}
	
	public void restaurarForaneo(){
		
		equipoForaneo = new Equipo();
		jugadorForaneo = new JugadorForaneo();
		cmbNacionalidad.setValue("-");
		rostercompetenciaforaneo = new RosterCompetencia();
		jugadorForaneo = new JugadorForaneo();
		
	}

	// INICIO COMBOBOX,TEXTBOX PESTAÑA ROSTER-------------------------------------------------------------------------------------------------------------------
	
	public void onChange$cmbEquipo() throws InterruptedException{
		
	
		if (cmbEquipo.getText()!="--Seleccione--"){
			Comboitem cmbItemEq = cmbEquipo.getSelectedItem();
			 equipoCompetencia = (EquipoCompetencia) cmbItemEq.getValue();
			 eq = equipoCompetencia;
			codEq = equipoCompetencia.getEquipo().getCodigoEquipo();
			equipo = (Equipo) eq.getEquipo();
			System.out.print("Cod de Equipo=");
			System.out.println(codEq);
			
			
			
			
			
			competenciasRegistradas = servicioRosterCompetencia.listarCompetenciasExistentes(codcomp, codEq);
			
			if (competenciasRegistradas.size()>0) {
				Messagebox.show("Roster ya ha sido creado para esta competencia", "Olimpo - Información",
						Messagebox.OK, Messagebox.EXCLAMATION);	
			
				
			
			for (Iterator i = competenciasRegistradas.iterator(); i.hasNext();){
				
				RosterCompetencia Rcompetencia = (RosterCompetencia) i.next();
				System.out.println(Rcompetencia.getRoster());
				if (Rcompetencia.getRoster()!=null){
					
					System.out.println("AGREGO A LA LISTA ROSTER");
					
					rosterCompetenciaNuevas.add(Rcompetencia);
					
					
					
					System.out.print("AGREGO A LA LISTA ROSTER=");
					System.out.println(rosterCompetenciaNuevas.size());
					
					//binder.loadAll();
					
					
				}else if (Rcompetencia.getJugadorForaneo()!=null){
					
					
					jugadors.add(Rcompetencia.getJugadorForaneo());
					binder.loadAll();
					
					
				}
				
				
				
				
			}
				
			}else if (competenciasRegistradas.size()==0){
				
				
				
				jugadoresxRoster = servicioRoster.listar(codEq);
				
				
				for (Iterator i = jugadoresxRoster.iterator(); i.hasNext();){
					
					
					Roster roscompetencia = (Roster) i.next();
					RosterCompetencia roscomp = new RosterCompetencia();
					roscomp.setRoster(roscompetencia);
					roscomp.setCompetencia(competencia);
					roscomp.setEquipo(equipo);
					rosterCompetenciaNuevas.add(roscomp);
					
					
					
				}
				
				
				
				totalListaRoster = jugadoresxRoster.size();
				
				
				
			}
			
			
			
			
			
			
			
			
			
			
			
			System.out.print("jugadores=");
			System.out.println(jugadoresxRoster.size());
			System.out.print("NOMBRE DEL DELEGADO");
			System.out.print(equipoCompetencia.getPersonaNatural().getPrimerNombre());
			System.out.println(equipoCompetencia.getPersonaNatural().getPrimerApellido());
			personas = servicioPersonalEquipo.listarxequipo(codEq);
			cmbManager.setDisabled(false);
			//txtDelegado.setDisabled(false);
			btnAgregarRoster.setDisabled(false);
			btnQuitarRoster.setDisabled(false);
			btnGuardarRoster.setDisabled(false);
			
			
			
			txtDelegado.setText(equipoCompetencia.getPersonaNatural().getPrimerNombre() + " " + equipoCompetencia.getPersonaNatural().getPrimerApellido() );
			
			personalxEquipo = compararListaManager();
			
			 for(int k= 0; k < personalxEquipo.size(); k++){
				 
					String Nombre = personalxEquipo.get(k).getPersonal().getPersonaNatural().getPrimerNombre();
					String Apellido = personalxEquipo.get(k).getPersonal().getPersonaNatural().getPrimerApellido();
					
					cmbManager.appendItem(Nombre + " " + Apellido );
				 
			 }		
			
			 	
					
			
			
			
			System.out.print("personal por equipo=");
			System.out.println(personalxEquipo.size());
			binder.loadAll();
											
			
		}else{
			
		}
				
	}
	
	
	public void onChange$cmbCategoria() throws InterruptedException{
		
		equipo = new Equipo();
		cmbEquipo.setText("--Seleccione--");
		
		if (cmbCategoria.getText()!="--Seleccione--"){
			
			
			
		    Comboitem cmbItem = cmbCategoria.getSelectedItem();
		    Categoria cat = (Categoria) cmbItem.getValue();
		    codCat = cat.getCodigoCategoria();
		    btnBuscarEquipo.setDisabled(false);
		    btnBuscarJForaneo.setDisabled(false);
		    cmbEquipo.setDisabled(false);
		    
		    
		   equipos = compararLista(codCat);
		   
		   
		   System.out.print("equipos x categoria=");
		   System.out.println(equipos.size());
			binder.loadAll();	
		}else{cmbEquipo.setText("--Seleccione--"); }
	} 
	
	public void onClick$btnBuscar() {
		// se crea el catalogo y se llama
		Component catalogo = Executions.createComponents(
				"/Competencias/Vistas/FrmCatalogoCompetencia.zul", null, null);
		// asigna una referencia del formulario al catalogo.
		catalogo.setVariable("formulario", formulario, false);
		catalogo.setVariable("estado_comp", EstadoCompetencia.REGISTRADA, false);
		formulario.addEventListener("onCatalogoCerrado", new EventListener() {
			@Override
			// Este metodo se llama cuando se envia la señal desde el catalogo
			public void onEvent(Event arg0) throws Exception {
				// se obtiene la competencia
				arg0.stopPropagation();
				competencia = (Competencia) formulario.getVariable(
						"competencia", false);
				codcomp =competencia.getCodigoCompetencia();
				System.out.print("Cod Competencia=");
				System.out.println(codcomp);
				cmbCategoria.setDisabled(false);
				btnCancelarRoster.setDisabled(false);
				
				equipoxCompetencia = servicioEquipoCompetencia.listarEquipos(codcomp);
				cmbCategoria.setValue("--Seleccione--");
				cmbEquipo.setValue("--Seleccione--");
				txtDelegado.setValue("");
				
				System.out.print("Equipos en competencia=");
				System.out.println(equipoxCompetencia.size());
				categorias = ConvertirConjuntoALista(competencia.getCategoriaCompetencias()); 
				binder.loadAll();
			}
		});

	}
	
	public void onClick$btnAgregarRoster() throws InterruptedException{
		
		totalListaRoster = rosterCompetenciaNuevas.size();

				if (jugadoresforaneos+rosterCompetenciaNuevas.size()==totalMaxRoster) {
					Messagebox.show("Roster Completo para la competencia", "Olimpo - Información",
							Messagebox.OK, Messagebox.EXCLAMATION);
						
					
				} else if (jugadoresforaneos+rosterCompetenciaNuevas.size()<totalMaxRoster){
					System.out.println("ENTRO AL IF COMPARACION");
					Component catalogo = Executions.createComponents(
							"/Competencias/Vistas/FrmCatalogoJugador.zul", null, null);
					catalogo.setVariable("formulario", formulario, false);
					catalogo.setVariable("codigoEq", codEq, false);
					catalogo.setVariable("codigoCat", codCat, false);
					catalogo.setVariable("ljugadores", rosterCompetenciaNuevas, false);
					catalogo.setVariable("codigocomp", codcomp, false);
					catalogo.setVariable("competencia", competencia, false);
					System.out.print(equipo);
					catalogo.setVariable("equipo", equipo, false);
					
					formulario.addEventListener("onCatalogoCerrado3", new EventListener() {
						@Override
					
						public void onEvent(Event arg0) throws Exception {
					
							
							RosterCompetencia rostercomp = (RosterCompetencia) formulario.getVariable("r", false);
							
							arg0.stopPropagation();
							
							
							
								
								System.out.print("Nro de nuevo jugador=");
								System.out.println(rostercomp.getRoster().getJugador().getNumero());
								rosterCompetenciaNuevas.add(rostercomp);
								//totalRoster = totalRoster + 3 ;
							;
								
							
							binder.loadAll();
						}
					});				
				}
			}

	public List<EquipoCompetencia> compararLista(int codigo){
		
		
		
		List<EquipoCompetencia> lista = new ArrayList<EquipoCompetencia>();
		
		for (Iterator i = equipoxCompetencia.iterator(); i.hasNext(); ){
			
			
			EquipoCompetencia EqComp = (EquipoCompetencia) i.next();
			int AuxCodEq = EqComp.getEquipo().getCategoria().getCodigoCategoria();
			int AuxCodDiv = EqComp.getEquipo().getDivisa().getCodigoDivisa();
			
			if (AuxCodEq==codigo & AuxCodDiv==1){
				
				lista.add(EqComp);
			}
		}
		
		return lista;
	}
		
	public List<PersonalEquipo> compararListaManager(){
		
		
		
		List<PersonalEquipo> lista = new ArrayList<PersonalEquipo>();
		
		for (Iterator i = personas.iterator(); i.hasNext(); ){
			
			
			PersonalEquipo Pequipo = (PersonalEquipo) i.next();
			int AuxcodPersonal = Pequipo.getCodigoPersonalEquipo();
			
			for (Iterator k = managers.iterator(); k.hasNext();){
				
				PersonalCargo Pcargo = (PersonalCargo) k.next();
				int AuxcodPersonalCargo = Pcargo.getCodigoPersonalCargo();
				
					if (AuxcodPersonal==AuxcodPersonalCargo){
						System.out.println("ENTRO AL IF");
						lista.add(Pequipo);
						
					}
			}
		}
			
		return lista;
	}
	
	
	
	
	
public void onClick$btnGuardarRoster() throws InterruptedException {
	if(rosterCompetenciaNuevas.size()+jugadors.size()<2){
	
		Messagebox.show("No Puede ser guardado, no cumple con el minimo de jugadores para conformar un Roster", "Olimpo - Información",
				Messagebox.OK, Messagebox.EXCLAMATION);
	
	}else if (competenciasRegistradas.size()>0)    { 
		
		
		for (int k= 0; k < rosterCompetenciaNuevas.size(); k++){
		
		auxRcomp = rosterCompetenciaNuevas.get(k);
		
		servicioRosterCompetencia.agregar(auxRcomp);

		
		}System.out.print("EDITO ROSTER EXISTENTE");		
		if (jugadors.size() >0){
			
			
					for (int i = 0; i < jugadors.size(); i++){
						
						jExis =(JugadorForaneo) jugadors.get(i);
						System.out.println(jExis);
						rostercompetenciaforaneo.setJugadorForaneo(jExis);
			            rostercompetenciaforaneo.setCodigoRosterCompetencia(servicioRosterCompetencia.listarActivos().size()+1);
						rostercompetenciaforaneo.setCompetencia(competencia);
						rostercompetenciaforaneo.setEquipo(equipo);
						
						servicioRosterCompetencia.agregar(rostercompetenciaforaneo);
						
						if (ExisteForaneo(jExis.getCedula())){
							System.out.print("AGREGO NUEVO FORANEO");
							servicioJugadorForaneo.agregar(jExis);
						}
						
						restaurarForaneo();
					}
		}
		
		Messagebox.show("Datos agregados exitosamente", "Olimpo - Información", Messagebox.OK, Messagebox.EXCLAMATION);
		restaurar();
		rosterCompetenciaNuevas = new ArrayList<RosterCompetencia>();
		binder.loadAll();
		
	}else if (competenciasRegistradas.size()==0){
		
		for (int k= 0; k < rosterCompetenciaNuevas.size(); k++){
			
			rostercompnuevo = rosterCompetenciaNuevas.get(k);
			rostercompetenciajugador.setCodigoRosterCompetencia(servicioRosterCompetencia.listarActivos().size()+1);
			rostercompetenciajugador.setCompetencia(competencia);
			rostercompetenciajugador.setRoster(rostercompnuevo.getRoster());
			rostercompetenciajugador.setEquipo(equipo);
			servicioRosterCompetencia.agregar(rostercompetenciajugador);
			}
		
		
	}System.out.print("GUARDO ROSTER NUEVO");		
	if (jugadors.size() >0){
		
		
		for (int i = 0; i < jugadors.size(); i++){
			
			jExis =(JugadorForaneo) jugadors.get(i);
			System.out.println(jExis);
			rostercompetenciaforaneo.setJugadorForaneo(jExis);
			//rostercompetenciaforaneo.setCodigoRosterCompetencia(servicioRosterCompetencia.listarActivos().size()+1);
			//rostercompetenciaforaneo.setCompetencia(competencia);
			//rostercompetenciaforaneo.setEquipo(equipo);
			servicioRosterCompetencia.agregar(rostercompetenciaforaneo);
			if (ExisteForaneo(jExis.getCedula())){
				System.out.print("AGREGO NUEVO FORANEO");
				servicioJugadorForaneo.agregar(jExis);
			
			}
			
			restaurarForaneo();
		}
}

Messagebox.show("Datos agregados exitosamente", "Olimpo - Información", Messagebox.OK, Messagebox.EXCLAMATION);
restaurar();
rosterCompetenciaNuevas = new ArrayList<RosterCompetencia>();
binder.loadAll();

}
	
	
	
	
	

	
public void onClick$btnQuitarRoster(){
	System.out.print("ENTRO AL QUITAR!");
	Quitar(lsbxJugadores,rosterCompetenciaNuevas);
	binder.loadAll();
	
	
}
	
	// INICIO COMBOBOX,TEXTBOX PESTAÑA JUGADOR FORANEO---------------------------------------------------------------------------------------------------------
	
	
	public void onClick$btnBuscarEquipo() {
		// se crea el catalogo y se llama
		if (codCat!=null){
			Component catalogo = Executions
					.createComponents(
							"/Competencias/Vistas/FrmCatalogoEquipoForaneo.zul",
							null, null);
			// asigna una referencia del formulario al catalogo.
			catalogo.setVariable("formulario", formulario, false);
			System.out.println(formulario.getId());
			catalogo.setVariable("categoria", codCat, false);
			formulario.addEventListener("onCatalogoCerrado6", new EventListener() {
				@Override
				// Este metodo se llama cuando se envia la seÃ±al desde el catalogo
				public void onEvent(Event arg0) throws Exception {
					// se obtiene la divisa
					arg0.stopPropagation();
					equipoForaneo = (Equipo) formulario.getVariable("equipos",
							false);
					jugadorForaneo = new JugadorForaneo();
					txtCedula.setReadonly(false);
					cmbNacionalidad.setDisabled(false);
					txtNombre.setDisabled(false);
					btnQuitarForaneo.setDisabled(false);
					btnAgregarForaneo.setDisabled(false);
					binder.loadAll();
		}
		

			
			
		});
		
		
	}else {
		alert("Debe seleccionar la categoria en la pestaña anterior");
	}
	}
	// Llama al catalogo}
	public void onClick$btnBuscarJForaneo() {

		if(codCat!=null){
		
		jugadorForaneo = new JugadorForaneo();
		// se crea el catalogo y se llama
		Component catalogo = Executions.createComponents(
				"/Competencias/Vistas/FrmCatalogoJugadorForaneo.zul", null,
				null);
		// asigna una referencia del formulario al catalogo.
		catalogo.setVariable("formulario", formulario, false);
		catalogo.setVariable("categoria",codCat, false);
		formulario.addEventListener("onCatalogoCerrado2", new EventListener() {
			@Override
			// Este metodo se llama cuando se envia la seÃ±al desde el catalogo
			public void onEvent(Event arg0) throws Exception {
				jugadorForaneo = new JugadorForaneo();

				jugadorForaneo = (JugadorForaneo) formulario.getVariable(
						"jugadorForaneo", false);

				arg0.stopPropagation();
				
				  String cedula = jugadorForaneo.getCedula();
				  cmbNacionalidad.setValue(cedula.substring(0, 2));
				  txtCedula.setValue(cedula.substring(2));
				  
				  jugadorForaneo.setCedula(txtCedula.getValue());
				  
				 
				equipoForaneo = jugadorForaneo.getEquipo();
				btnBuscarEquipo.setDisabled(true);
				binder.loadAll();

			}

		});
		}else{
			alert("Seleccione la categoria en la patalla anterior");
		}
		
	}

	
	
	///BOTONES DE LA LISTA JUGADOR FORANEO
	
	public boolean ExisteForaneo(String cedula){
		List<JugadorForaneo> foraneos;
		foraneos = (List<JugadorForaneo>) servicioJugadorForaneo.buscarJugadorForaneo(cedula);
		
		if (foraneos.size()>0){
			return true;
		}else
			return false;
		
		
		
		
	}
	
	public boolean BuscarLista(JugadorForaneo jnuevo) {

		boolean sw = false;
		for (int i = 0; i < jugadors.size(); i++) {

			jExis = jugadors.get(i);

			if (jExis.getCedula().equals(jnuevo.getCedula()))

			{
				sw = true;
				break;
			}
		}
		return sw;
	}
		
	public void onClick$btnAgregarForaneo() throws InterruptedException {

		if (txtCedula.getValue().isEmpty()) { // ----- MODIFICAAAR -----
			throw new WrongValueException(txtCedula,
					"Debe ingresar la cedula del jugador");
		} else if (txtNombre.getValue().isEmpty()) { // ----- MODIFICAAAR
														// -----
			throw new WrongValueException(txtNombre,
					"Debe ingresar el nombre del jugador");
		} else {
			if (txtEquipo.getValue().isEmpty()) { // ----- MODIFICAAAR
				// -----
				throw new WrongValueException(txtEquipo,
						"Debe ingresar el nombre del jugador");
			}

			else {

				
				 String cedulacomp = (cmbNacionalidad.getValue() + jugadorForaneo .getCedula());
				 
				 jugadorForaneo.setCedula(cedulacomp);
				 jugadorForaneo.setEquipo(equipoForaneo);
				 
				if (BuscarLista(jugadorForaneo) == false & jugadors.size()+totalListaRoster<totalMaxRoster & jugadors.size()<totalMaxForaneos) {
					System.out.println("ENTRO AL IF!");
					jugadors.add(jugadorForaneo);
					restaurarForaneo();
					binder.loadAll();
					btnBuscarEquipo.setDisabled(false);
					btnBuscarJForaneo.setDisabled(false);
					txtNombre.setDisabled(false);
					txtCedula.setDisabled(false);
					txtCedula.setReadonly(false);
					cmbNacionalidad.setDisabled(false);
					jugadoresforaneos = jugadors.size();
					}else if (jugadors.size()+totalListaRoster==totalMaxRoster){
						
						
						Messagebox.show("Roster Completo para la COmpetencia", "Olimpo - Información",
								Messagebox.OK, Messagebox.EXCLAMATION);
						
					}else if (jugadors.size()== totalMaxForaneos){
						
						
						Messagebox.show("Max. Jugadores Foraneos", "Olimpo - Información",
								Messagebox.OK, Messagebox.EXCLAMATION);
					}
					
				} }
					
					
					
				}
			
		
	

		
		public void onClick$btnQuitarForaneo() {

			Quitar(lsbxJugadors, jugadors);
			binder.loadAll();
		}

		
		public void onClick$btnAgregar() {
			Agregar(lsbxJugadores, lsbxJugadoresSeleccionados,
					jugadoresSeleccionados);
			binder.loadAll();
		}

		
		
		

		public void onClick$btnQuitar(){
		    Quitar(lsbxJugadoresSeleccionados,jugadoresSeleccionados);
			binder.loadAll();

		}
		
		public boolean camposVacios() {
			boolean vacio = false;
			if (txtEquipo.getValue().equals("") || txtCedula.getValue().equals("")
					|| txtNombre.getValue().equals(""))

			{
				vacio = true;
			}
			return vacio;
		}

		

		
		public void onClick$btnGuardarForaneo() throws InterruptedException {
			// Categorias = equipoForaneo.getCategoria().getNombre();

			if (jugadors.size() != 0) {

				for (int i = 0; i < jugadors.size(); i++) {

					jExis = jugadors.get(i);
					servicioJugadorForaneo.agregar(jExis);
					restaurarForaneo();
					

				}
				jugadors = new ArrayList<JugadorForaneo>();

				Messagebox.show("Datos agregados exitosamente", "Olimpo - Información",
						Messagebox.OK, Messagebox.EXCLAMATION);
				// jugadors = new ArrayList<JugadorForaneo>();
				binder.loadAll();
			}

		}


		
		
		
		


		public void onClick$btnEliminarForaneo() throws InterruptedException {

			String cedulacom = (cmbNacionalidad.getValue())
					+ jugadorForaneo.getCedula();
			jugadorForaneo.setCedula(cedulacom);

			if (camposVacios() != true) {

				if (Messagebox.show("Â¿Realmente desea eliminar este jugador",
						"Mensaje", Messagebox.YES + Messagebox.NO,
						Messagebox.QUESTION) == Messagebox.YES) {

					servicioJugadorForaneo.eliminar(jugadorForaneo);
					restaurar();
					binder.loadAll();
					Messagebox.show("Datos eliminados exitosamente", "Olimpo - Información",
							Messagebox.OK, Messagebox.EXCLAMATION);
				}

			}
		}







//PROCEDIMIENTOS GENERALES -------------------------------------------------------------------------------------------------------------------------------




		
	public void Agregar(Listbox origen, Listbox destino, List lista) {

		Set seleccionados = origen.getSelectedItems();
		for (Iterator i = seleccionados.iterator(); i.hasNext();) {
			boolean sw = false;
			Listitem li = (Listitem) i.next();
			JugadorForaneo jf = (JugadorForaneo) li.getValue();
			List seleccionDestino = destino.getItems();
			for (Iterator j = seleccionDestino.iterator(); j.hasNext();) {
				Listitem li2 = (Listitem) j.next();
				JugadorForaneo jf2 = (JugadorForaneo) li2.getValue();
				if (jf.getNombre().equals(jf2.getNombre())) {
					sw = true;
					break;
				
				}
			}
			if (!sw) {
				lista.add(jf);
			}
		}
	}
	
	public void AgregarJugador(Listbox origen, Listbox destino, List lista) {

		Set seleccionados = origen.getSelectedItems();
		for (Iterator i = seleccionados.iterator(); i.hasNext();) {
			boolean sw = false;
			Listitem li = (Listitem) i.next();
			Roster c1 = (Roster) li.getValue();
			List seleccionDestino = destino.getItems();
			for (Iterator j = seleccionDestino.iterator(); j.hasNext();) {
				Listitem li2 = (Listitem) j.next();
				Roster c2 = (Roster) li2.getValue();

				if (c1.getJugador().getCedulaRif().equals(c2.getJugador().getCedulaRif())) {
					sw = true;
					break;
				}
			}
			if (!sw) {
				lista.add(c1);
			}
		}
	}	
	
	public void QuitarJugador(Listbox origen, List lista) {
		Set seleccionados = origen.getSelectedItems();
		for (Iterator i = seleccionados.iterator(); i.hasNext();) {
			Listitem li = (Listitem) i.next();
			Object o = li.getValue();
			lista.remove(o);
		}
	}
	
	public void onClick$btnCancelar() {
	    restaurar();
	    binder.loadAll();
	}

	public void onClick$btnSalir() {
		formulario.detach();

	}
	
	public Set ConvertirListaAConjunto(List lista) {
		Set c = new HashSet();
		for (Iterator i = lista.iterator(); i.hasNext();) {
			c.add(i.next());
		}
		return c;
	}





	public void Quitar(Listbox origen, List lista) {
		Set seleccionados = origen.getSelectedItems();
		for (Iterator i = seleccionados.iterator(); i.hasNext();) {
			Listitem li = (Listitem) i.next();
			Object o = li.getValue();
			lista.remove(o);
		}
	}
	
	public List ConvertirConjuntoALista(Set conjunto) {
		List l = new ArrayList();
		for (Iterator i = conjunto.iterator(); i.hasNext();) {
			l.add(i.next());
		}
		return l;
	}
	

}