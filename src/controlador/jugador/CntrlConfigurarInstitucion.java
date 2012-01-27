package controlador.jugador;

import java.util.ArrayList;
import java.util.List;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.*;
import comun.TipoDatoBasico;
import modelo.Equipo;
import modelo.Institucion;
import modelo.DatoBasico;
import modelo.Medico;
import servicio.implementacion.ServicioDatoBasico;
import servicio.implementacion.ServicioInstitucion;

/**
 * Clase controladora de los eventos de la vista de igual nombre y manejo de los
 * servicios de datos para la configuración de la institución
 * 
 * @author Mariangel M
 * @version 0.1.0 29/12/2011
 * 
 * */

public class CntrlConfigurarInstitucion extends GenericForwardComposer {
	// Componentes visuales
	List<DatoBasico> datosbasicos;
	Textbox txtCodigo;
	Textbox txtNombre;
	Textbox txtDireccion;
	Combobox cmbTipo, cmbEstadoResi, cmbParroquiaResi, cmbMunicipioResi;
	AnnotateDataBinder binder;
	Component formulario;
	// Servicios
	ServicioInstitucion servicioInstitucion;
	ServicioDatoBasico servicioDatoBasico;
	// Modelos
	private DatoBasico estado = new DatoBasico();
	private Institucion institucion = new Institucion();

	// Getters y setters
	public DatoBasico getEstado() {
		return estado;
	}

	public void setEstado(DatoBasico estado) {
		this.estado = estado;
	}

	private DatoBasico municipio = new DatoBasico();

	public DatoBasico getMunicipio() {
		return municipio;
	}

	public void setMunicipio(DatoBasico municipio) {
		this.municipio = municipio;
	}

	private DatoBasico parroquia = new DatoBasico();

	public DatoBasico getParroquia() {
		return parroquia;
	}

	public void setParroquia(DatoBasico parroquia) {
		this.parroquia = parroquia;
	}

	private DatoBasico tipoinstitucion = new DatoBasico();

	public DatoBasico getTipoinstitucion() {
		return tipoinstitucion;
	}

	public void setTipoinstitucion(DatoBasico tipoinstitucion) {
		this.tipoinstitucion = tipoinstitucion;
	}

	public Institucion getInstitucion() {
		return institucion;
	}

	public void setInstitucion(Institucion institucion) {
		this.institucion = institucion;
	}

	public List<DatoBasico> getDatosBasico() {
		return servicioDatoBasico.listar();
	}

	// Metodos para carga de combos/listbox
	public List<DatoBasico> getDatosBasicos() {
		return servicioDatoBasico.listar();
	}

	public List<DatoBasico> getTipoInstituciones() {
		return servicioDatoBasico.buscar(TipoDatoBasico.INSTITUCION);
	}

	public List<DatoBasico> getEstadosVenezuela() {
		return servicioDatoBasico.buscar(TipoDatoBasico.ESTADO_VENEZUELA);
	}

	public List<DatoBasico> getMunicipiosEstadosResi() {
		return servicioDatoBasico.buscarDatosPorRelacion(estado);
	}

	public List<DatoBasico> getParroquiasMunicipioResi() {
		return servicioDatoBasico.buscarDatosPorRelacion(municipio);
	}

	// Eventos
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		comp.setVariable("cntrl", this, true); 
		//se guarda la referencia al formulario actual
		formulario = comp; 	
	}

	public void onClick$btnGuardar() {
		institucion
				.setCodigoInstitucion(servicioInstitucion.listar().size() + 1);
		institucion.setNombre(txtNombre.getValue());
		institucion.setDireccion(txtDireccion.getValue());
		institucion.setDatoBasicoByCodigoParroquia(parroquia);
		institucion.setEstatus('A');
		institucion.setDatoBasicoByCodigoTipoInstitucion(tipoinstitucion);
		servicioInstitucion.agregar(institucion);
		institucion = new Institucion();
		try {
			Messagebox.show("Institución agregada", "Exito", Messagebox.OK,
					Messagebox.INFORMATION);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		limpiar();
		binder.loadAll();
	}

	public void limpiar() {
		institucion = new Institucion();
		binder.loadAll();
		txtNombre.setValue("");
		txtDireccion.setValue("");
	}

	public void onClick$btnCancelar() {
		limpiar();
	}

	public void onClick$btnBuscar() {
		//se crea el catalogo y se llama
		Component catalogo = Executions.createComponents("/Jugador/Vistas/FrmBuscarInstitucion.zul", null, null);
		//asigna una referencia del formulario al catalogo.
		catalogo.setVariable("formulario",formulario, false);
			    		
		formulario.addEventListener("onCatalogoCerrado", new EventListener() {		
		@Override
		//Este metodo se llama cuando se envia la señal desde el catalogo
		public void onEvent(Event arg0) throws Exception {
		//se obtiene el jugador
		institucion = (Institucion) formulario.getVariable("institucion",false);
		binder.loadAll();				
					}
		});
		}
}
