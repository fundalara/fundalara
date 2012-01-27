package controlador.competencia;




import org.zkoss.zk.ui.event.EventListener;

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
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.api.Comboitem;

import comun.EstadoCompetencia;

import servicio.implementacion.ServicioCategoria;
import servicio.implementacion.ServicioEquipo;
import servicio.implementacion.ServicioEquipoCompetencia;
import servicio.implementacion.ServicioJugadorForaneo;
import servicio.implementacion.ServicioRoster;

import org.zkoss.zk.ui.event.EventListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import modelo.Categoria;
import modelo.CategoriaCompetencia;
import modelo.Competencia;
import modelo.Divisa;
import modelo.Equipo;
import modelo.Jugador;
import modelo.Roster;

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



public class CntrlFrmRoster extends GenericForwardComposer {
	CategoriaCompetencia categoriaCompetencia;
	List<Roster> jugadoresxRoster;
	Roster roster;
	Listbox lsbxJugadores;
	Jugador jugador;
	//Servicios utilizados
	ServicioJugadorForaneo servicioJugadorForaneo;
	ServicioEquipoCompetencia servicioEquipoCompetencia;
	ServicioCategoria servicioCategoria;
	ServicioEquipo servicioEquipo;
	
	
	//Atributos
	AnnotateDataBinder binder;
	Component formulario;
	
	Categoria categoria;
	Equipo equipo;
	Competencia competencia;
	Combobox cmbCategoria;
	ServicioRoster servicioRoster;
	List<Categoria> categorias;
	List<Equipo> equipos;
	Combobox cmbEquipo;
	String categoriaSeleccion;
	

     JugadorForaneo jugadorForaneo;
     EquipoCompetencia equipoCompetencia;
    String Categorias;
    List<JugadorForaneo> jugadoresSeleccionados;
    List<JugadorForaneo> jugadors;
    Listbox lsbxJugadors;
    Listbox lsbxJugadoresSeleccionados;

	
	
	
	
	
	public void doAfterCompose(Component c) throws Exception {
		super.doAfterCompose(c);
	
		c.setVariable("cntrl", this, true);
	
		formulario = c;
		
		
	restaurar();
//	jugadors = servicioJugadorForaneo.listarActivos();
		
	}
	public void restaurar(){
		jugadorForaneo = new JugadorForaneo(); 
		equipoCompetencia = new EquipoCompetencia();
		categoria = new Categoria();
		
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

	public List<Equipo> getEquipos() {
		return equipos;
	}

	public void setEquipos(List<Equipo> equipos) {
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


	
	public void onChange$cmbEquipo() throws InterruptedException{
		
	
		if (cmbEquipo.getText()!="--Seleccione--"){
			Comboitem cmbItemEq = cmbEquipo.getSelectedItem();
			Equipo eq = (Equipo) cmbItemEq.getValue();
			Integer codEq = eq.getCodigoEquipo();
			System.out.print("Cod de Equipo=");
			System.out.println(eq.getCodigoEquipo());
			
			jugadoresxRoster = servicioRoster.listar(codEq);
			System.out.print("jugadores=");
			System.out.println(jugadoresxRoster);
			
			
			
			
			
		}else{
			
			
			
			
		}
		
		
		
		
			
	}
	
	
	public void onChange$cmbCategoria() throws InterruptedException{
		
		equipo = new Equipo();
		cmbEquipo.setText("--Seleccione--");
		
		if (cmbCategoria.getText()!="--Seleccione--"){
		    Comboitem cmbItem = cmbCategoria.getSelectedItem();
		    Categoria cat = (Categoria) cmbItem.getValue();
		    Integer codCat = cat.getCodigoCategoria();
		    		
			equipos = servicioEquipo.listarEquipoPorCategoria( codCat);	
			binder.loadAll();	
		}else{cmbEquipo.setText("--Seleccione--"); }
		
		
		
	} 
	
	// //////////// Llama al catalogo de Competencias
	// ///////////////////////////////
	public void onClick$btnBuscar() {
		// se crea el catalogo y se llama
		Component catalogo = Executions.createComponents(
				"/Competencias/Vistas/FrmCatalogoCompetencia.zul", null, null);
		// asigna una referencia del formulario al catalogo.
		catalogo.setVariable("formulario", formulario, false);
		catalogo.setVariable("estatus", EstadoCompetencia.REGISTRADA, false);
		formulario.addEventListener("onCatalogoCerrado", new EventListener() {
			@Override
			// Este metodo se llama cuando se envia la señal desde el catalogo
			public void onEvent(Event arg0) throws Exception {
				// se obtiene la competencia
				competencia = (Competencia) formulario.getVariable(
						"competencia", false);
				
				categorias = ConvertirConjuntoALista(competencia.getCategoriaCompetencias()); 
				
				binder.loadAll();
			}
		});

	}
	

	public Competencia getCompetencia() {
		return competencia;
	}

	public void setCompetencia(Competencia competencia) {
		this.competencia = competencia;
	}
	public List ConvertirConjuntoALista(Set conjunto) {
		List l = new ArrayList();
		for (Iterator i = conjunto.iterator(); i.hasNext();) {
			l.add(i.next());
		}
		return l;
	}
		



//Lisbeth------------------------


	

		
		
		
	  //SETTERS Y GETTERS...

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
	

		//Llama al catalogo
		public void onClick$btnBuscarEquipo() {
			//se crea el catalogo y se llama
			Component catalogo = Executions.createComponents("/Competencias/Vistas/FrmCatalogoEquipoCompetencia.zul", null, null);
			//asigna una referencia del formulario al catalogo.
			catalogo.setVariable("formulario",formulario, false);
		    		
			formulario.addEventListener("onCatalogoCerrado", new EventListener() {		
				@Override
				//Este metodo se llama cuando se envia la señal desde el catalogo
				
				public void onEvent(Event arg0) throws Exception {
					//se obtiene la divisa
					equipoCompetencia = (EquipoCompetencia) formulario.getVariable("equipoCompetencia",false);
					binder.loadAll();				
				}
			});


		}


		
		
	//METODO PARA TRABAJAR LA LISTA..........



	// Convierte una conjunto a una lista...
	


	// Convierte una lista a un conjunto...
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



	public void onClick$btnAgregar() {
		Agregar(lsbxJugadores, lsbxJugadoresSeleccionados,
				jugadoresSeleccionados);
		binder.loadAll();
	}


	public void onClick$btnQuitar(){
	    Quitar(lsbxJugadoresSeleccionados,jugadoresSeleccionados);
		binder.loadAll();

	}








	//BOTONES GUARDAR, ELIMINAR, CANCELAR Y SALIR

	public void onClick$btnGuardarForaneo() throws InterruptedException {
	  Categorias = equipoCompetencia.getEquipo().getCategoria().getNombre();

	 
	  if (Categorias.equals("PREPARATORIO NIVEL I"))
	       {
		  Messagebox.show("No Puede agregar jugador Refuerzo para esta categoria", "Mensaje",Messagebox.OK, Messagebox.EXCLAMATION);
		   }
	  else
	  {
		  //agregar
				jugadorForaneo.setEquipoCompetencia(equipoCompetencia);
				
				servicioJugadorForaneo.agregar(jugadorForaneo);
				Messagebox.show("Datos agregados exitosamente", "Mensaje",Messagebox.OK, Messagebox.EXCLAMATION);
				restaurar();
				binder.loadAll();  
		
	  }
	    

	}


	public void onClick$btnEliminarForaneo() throws InterruptedException {
		if (Messagebox.show("¿Realmente desea eliminar este jugador", "Mensaje",
				Messagebox.YES + Messagebox.NO, Messagebox.QUESTION) == Messagebox.YES) {
			servicioJugadorForaneo.eliminar(jugadorForaneo);
	        restaurar();
	        binder.loadAll();
			Messagebox.show("Datos eliminados exitosamente", "Mensaje",	Messagebox.OK, Messagebox.EXCLAMATION);
		}
	}



	public void onClick$btnCancelar() {
	    restaurar();
	    binder.loadAll();
	}

	public void onClick$btnSalir() {
		formulario.detach();

	}

}