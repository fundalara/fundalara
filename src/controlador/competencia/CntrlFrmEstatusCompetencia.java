package controlador.competencia;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import modelo.Categoria;
import modelo.Competencia;
import modelo.DatoBasico;
import modelo.FaseCompetencia;
//import modelo.FaseCompetenciaId;
import modelo.Liga;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Button;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import comun.EstadoCompetencia;

import servicio.implementacion.ServicioCompetencia;
import servicio.implementacion.ServicioDatoBasico;
import servicio.implementacion.ServicioFaseCompetencia;
import servicio.implementacion.ServicioJuego;

public class CntrlFrmEstatusCompetencia extends GenericForwardComposer {

	Component formulario;
	AnnotateDataBinder binder;
	ServicioCompetencia servicioCompetencia;
	ServicioFaseCompetencia servicioFaseCompetencia;
	ServicioJuego servicioJuego;
	Competencia competencia;
	ServicioDatoBasico servicioDatoBasico;
	DatoBasico datoBasico;
	Component catalogo;

//	FaseCompetenciaId faseCompetenciaId;
	FaseCompetencia faseCompetencia;

	List listaFases;

	// Vista...
	Button btnAperturar;
	Button btnCulminar;
	Textbox txtNombre,txtfechaInicio,txtfechaFin,txtTemporada,txtClasificacion,txtCantEquipos;
	Textbox txtJugRoster;
	Textbox txtJuegosProgramados;
	Textbox txtJuegosRegistrados;
	Window frmEstatusCompetencia;

	public Competencia getCompetencia() {
		return competencia;
	}

	public void setCompetencia(Competencia competencia) {
		this.competencia = competencia;
	}

//	public FaseCompetenciaId getFaseCompetenciaId() {
//		return faseCompetenciaId;
//	}

//	public void setFaseCompetenciaId(FaseCompetenciaId faseCompetenciaId) {
//		this.faseCompetenciaId = faseCompetenciaId;
//	}

	public FaseCompetencia getFaseCompetencia() {
		return faseCompetencia;
	}

	public void setFaseCompetencia(FaseCompetencia faseCompetencia) {
		this.faseCompetencia = faseCompetencia;
	}

	public void doAfterCompose(Component c) throws Exception {
		super.doAfterCompose(c);
		c.setVariable("cntrl", this, true);
		formulario = c;
		restaurar();
		
		competencia = new Competencia();
		datoBasico = new DatoBasico();
	}

	private void restaurar() {
		competencia = new Competencia();

	}

	// Agregado Convierte un conjunto a una lista...
	public List ConvertirConjuntoALista(Set conjunto) {
		List l = new ArrayList();
		for (Iterator i = conjunto.iterator(); i.hasNext();) {
			l.add(i.next());
		}
		return l;
	}

	// BOTONES BUSCAR, CANCELAR,SALIR ................
	public void onClick$btnBuscar() {

	}

	public void onClick$btnAperturar() throws InterruptedException {
		
		if (competencia.getDatoBasicoByCodigoEstadoCompetencia().getCodigoDatoBasico() == EstadoCompetencia.REGISTRADA){
			
			if (servicioJuego.listarJuegosProgramados(datoBasico) == 0){

				Messagebox.show("Deben haber juegos Programados para poder Aperturar esta Competencia", "Mensaje",
						Messagebox.OK, Messagebox.EXCLAMATION);
				
			}else{

			datoBasico = servicioDatoBasico.buscarPorCodigo(288);			
			competencia.setDatoBasicoByCodigoEstadoCompetencia(datoBasico);
			servicioCompetencia.aperturarClausurarcompetencia(competencia, datoBasico);

			restaurar();
			binder.loadAll();
			Messagebox.show("La compentencia ha sido aperturada...", "Mensaje",
					Messagebox.OK, Messagebox.EXCLAMATION);
			}
			}
		
		formulario.detach();
	}
	

	public void onClick$btnCulminar() throws InterruptedException {
		
		if (competencia.getDatoBasicoByCodigoEstadoCompetencia().getCodigoDatoBasico() == EstadoCompetencia.APERTURADA){
			
			if (competencia.getJuegos().size() < servicioJuego.listarJuegosProgramados(datoBasico) || competencia.getJuegos().size() == 0){
				
				Messagebox.show("No se puede Clausurar esta Competencia, Aun hay juegos sin realizar", "Mensaje",
						Messagebox.OK, Messagebox.EXCLAMATION);

			}else{

			datoBasico = servicioDatoBasico.buscarPorCodigo(289);
			competencia.setDatoBasicoByCodigoEstadoCompetencia(datoBasico);
			servicioCompetencia.aperturarClausurarcompetencia(competencia, datoBasico);

			restaurar();
			binder.loadAll();
			Messagebox.show("La competencia ha sido culminada...", "Mensaje",
					Messagebox.OK, Messagebox.EXCLAMATION);
			}
		}
		
		formulario.detach();
		
	}


	public void onClick$btnSalir() {
		formulario.detach();

	}

	
	public void onCreate$frmEstatusCompetencia(){
	    Competencia compt = (Competencia) formulario.getVariable("comp",false);	

	    int cantequipos, cantjugador, juegosreg, juegosprog;
		btnAperturar.setFocus(true);
		
		competencia = compt;
		
		Date fechaI = compt.getFechaInicio();
		Date fechaF = compt.getFechaFin();
		String fechaInicio, fechaFin; 
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		fechaInicio=formato.format(fechaI);
		fechaFin=formato.format(fechaF);
		
	    
	    competencia.setCodigoCompetencia(compt.getCodigoCompetencia());
	    competencia.setNombre(compt.getNombre());
	    txtNombre.setText(competencia.getNombre());
	    competencia.setFechaInicio(compt.getFechaInicio());
	    txtTemporada.setText(competencia.getLapsoDeportivo().getNombre());
	    competencia.setFechaFin(compt.getFechaFin());
	    competencia.setClasificacionCompetencia(compt.getClasificacionCompetencia());
	    txtClasificacion.setText(competencia.getClasificacionCompetencia().getNombre());
	    competencia.setLapsoDeportivo(compt.getLapsoDeportivo());
	    competencia.setCantidadJugador(compt.getCantidadJugador());
	    txtJugRoster.setText(String.valueOf(competencia.getCantidadJugador()));
	    competencia.setDatoBasicoByCodigoEstadoCompetencia(compt.getDatoBasicoByCodigoEstadoCompetencia());
	    
		txtfechaInicio.setText(fechaInicio);
		txtfechaFin.setText(fechaFin);
	    
		txtCantEquipos.setText(String.valueOf(servicioFaseCompetencia.EquiposRegistrados(competencia).getEquipoIngresan()));
		
		txtJuegosRegistrados.setValue(String.valueOf(competencia.getJuegos().size()));
		txtJuegosProgramados.setValue(String.valueOf(servicioJuego.listarJuegosProgramados(datoBasico)));
				
		
		
		if(compt.getDatoBasicoByCodigoEstadoCompetencia().getCodigoDatoBasico() == EstadoCompetencia.REGISTRADA){
			
			frmEstatusCompetencia.setTitle("Aperturar Competencia");
			btnAperturar.setVisible(true);
			btnCulminar.setVisible(false);
			
			
		}else if (compt.getDatoBasicoByCodigoEstadoCompetencia().getCodigoDatoBasico() == EstadoCompetencia.APERTURADA){
		
			frmEstatusCompetencia.setTitle("Clausurar Competencia");
			btnAperturar.setVisible(false);
			btnCulminar.setVisible(true);
		
		
		}
	    binder.loadAll();
	}
}
