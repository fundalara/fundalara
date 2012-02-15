package controlador.jugador;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Button;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Textbox;

//Servicio Implementacion
import servicio.implementacion.ServicioAfeccionJugador;
import servicio.implementacion.ServicioDatoBasico;
import servicio.implementacion.ServicioDatoMedico;
import servicio.implementacion.ServicioRecaudoPorProceso;
import servicio.implementacion.ServicioMedico;

//Comun
import comun.Ruta;
import comun.TipoDatoBasico;

//Modelos Importados
import modelo.DatoBasico;
import modelo.DatoMedico;
import modelo.DocumentoEntregado;
import modelo.Jugador;
import modelo.Medico;
import modelo.RecaudoPorProceso;

/**
 * Clase controladora de los eventos de la vista de igual nombre y manejo de los
 * servicios de datos para la actualizacion de los datos correspondientes a un
 * jugador.
 * 
 * @author Edgar L.
 * @author Alberto P.
 * @author Aimee M.
 * @author Glendy O.
 * @author Greisy R.
 * @version 0.2.7 03/01/2012
 * 
 * */
public class CntrlActualizarDatosMedicos extends GenericForwardComposer {
	
	// Constantes
	private static final DatoBasico Actualizar = null;
	// Componentes Visuales
	private Datebox dtboxFechaInf;
	private Datebox dtboxFechaRein;
	private Button btnCatalogoMedico;
	private Button btnSubirdocumento;
	private Textbox txtObservacion;
	private Textbox txtMedico;
	private Textbox txtNroColegio;
	private Listbox listDocMedicos;
	private String rutasJug = Ruta.JUGADOR.getRutaVista();

	// Servicios
	private ServicioDatoBasico servicioDatoBasico;
	private ServicioMedico servicioMedico;
	private ServicioDatoMedico servicioDatoMedico;
	private ServicioAfeccionJugador servicioAfeccionJugador;
	private ServicioRecaudoPorProceso servicioRecaudoPorProceso;

	// Modelos
	private Jugador jugador = new Jugador();
	private controlador.jugador.bean.Jugador jugadorBean = new controlador.jugador.bean.Jugador();
	private DatoMedico datoMedico = new DatoMedico();
	private List<DocumentoEntregado> documentosMedicos = new ArrayList<DocumentoEntregado>();
	private RecaudoPorProceso recaudoMedico = new RecaudoPorProceso();
	private DocumentoEntregado docEntMed = new DocumentoEntregado();
	private Medico medico = new Medico();

	private Component formulario;
	
	// Binder
	private AnnotateDataBinder binder;
	/**
	 * Mantiene un Map con todos los atributos asociados a la ejecucion actual
	 */
	private Map<String, Object> requestScope;

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		comp.setVariable("controller", this, false);
		formulario = comp;
	}

	public controlador.jugador.bean.Jugador getJugadorBean() {
		return jugadorBean;
	}

	public void setJugadorBean(controlador.jugador.bean.Jugador jugadorBean) {
		this.jugadorBean = jugadorBean;
	}

	public Jugador getJugador() {
		return jugador;
	}

	public void setJugador(Jugador jugador) {
		this.jugador = jugador;
	}

	public DatoMedico getDatoMedico() {
		return datoMedico;
	}

	public void setDatoMedico(DatoMedico datoMedico) {
		this.datoMedico = datoMedico;
	}

	public Map<String, Object> getRequestScope() {
		return requestScope;
	}

	public void setRequestScope(Map<String, Object> requestScope) {
		this.requestScope = requestScope;
	}

	public RecaudoPorProceso getRecaudoMedico() {
		return recaudoMedico;
	}

	public void setRecaudoMedico(RecaudoPorProceso recaudoMedico) {
		this.recaudoMedico = recaudoMedico;
	}
	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	public Medico getMedico() {
		return medico;
	}

	public List<DocumentoEntregado> getRecaudosMedicos() {
		List<RecaudoPorProceso> lista = servicioRecaudoPorProceso
				.buscarPorProceso(Actualizar, TipoDatoBasico.TIPO_DOCUMENTO,
						"Medico");
		for (RecaudoPorProceso recaudoPorProceso : lista) {
			DocumentoEntregado docE = new DocumentoEntregado();
			docE.setRecaudoPorProceso(recaudoPorProceso);
			documentosMedicos.add(docE);
		}
		return documentosMedicos;
	}
	
	public void onClick$btnCatalogoMedico() {
		Component catalogo = Executions.createComponents(
				"/Jugador/Vistas/frmBuscarMedico.zul", null, null);
		catalogo.setVariable("formulario", formulario, false);

		formulario.addEventListener("onCatalogoCerrado", new EventListener() {
			/* (non-Javadoc)
			 * @see org.zkoss.zk.ui.event.EventListener#onEvent(org.zkoss.zk.ui.event.Event)
			 */
			@Override
			public void onEvent(Event arg0) throws Exception {
				medico = (Medico) formulario.getVariable("medico", false);
				String nombre = medico.getNombre();
				String apellido = medico.getApellido();
				txtMedico.setValue(nombre + " " + apellido);
				txtNroColegio.setValue(medico.getNumeroColegio());
			}
		});
	}

}