package controlador.jugador;

import java.util.Date;
import java.util.List;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.Executions;

import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Textbox;

import servicio.implementacion.ServicioMedico;
import servicio.implementacion.ServicioDatoBasico;

import comun.TipoDatoBasico;

import modelo.Medico;

import modelo.DatoBasico;

/**
 * Clase controladora de los eventos de la vista de igual nombre el presente
 * controlador se encarga de agregar, eliminar logicamente, la modificacion se
 * ha pospuesto momentaneamente por problemas con los combos
 * 
 * @author Robert A
 * @author Miguel B
 * 
 * @version 2.0 29/12/2011
 * 
 * */
public class CntrlConfigurarMedico extends GenericForwardComposer {
	private ServicioMedico servicioMedico;
	private ServicioDatoBasico servicioDatoBasico;

	// private DatoBasico especialidad = new DatoBasico();
	private Medico medico = new Medico();

	Button btnGuardar;
	Button btnModificar;
	Button btnEliminar;
	Button btnCancelar;
	Button btnSalir;
	Button btnBuscar;
	Textbox txtApellido,txtTelefonoCelular,txtTelefonoHabitacion,txtNumcol, txtCedula;
	Component formulario;
	private Combobox cmbEspecialidad,cmbNacionalidad,cmbCodCelular,cmbCodArea;
	
	List<DatoBasico> codigosArea;
	List<DatoBasico> codigosCelular;
	List<DatoBasico> especialidades;


	private AnnotateDataBinder binder;

	public void onCreate$winConfigurarMedico(){
	
	}
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		comp.setVariable("controller", this, true);
		// se guarda la referencia al formulario actual
		formulario = comp;
		

	}

	public void onClick$btnGuardar() {

		medico.setCedulaMedico(cmbNacionalidad.getValue()+"-"+txtCedula.getValue().toString());
		medico.setTelefonoCelular(codigosCelular.get(cmbCodCelular.getSelectedIndex()).getNombre()+txtTelefonoCelular.getValue());
		medico.setDatoBasico(especialidades.get(cmbEspecialidad.getSelectedIndex()));
		medico.setTelefonoOficina(codigosArea.get(cmbCodArea.getSelectedIndex()).getNombre()+txtTelefonoHabitacion.getValue());
		medico.setEstatus('A');
		Date fecha = new Date();
		medico.setFechaIngreso(fecha);
		servicioMedico.agregar(medico);
		limpiar();
	}

	public void onClick$btnModificar() {

		// problemas con asignar el valor del combo cuando traigo los datos
		/*
		 * if (especialidad != null) { medico.setEstatus('A');
		 * //medico.setDatoBasico(especialidad);
		 * servicioMedico.actualizar(medico); medico = new Medico();
		 * binder.loadAll(); } else {
		 */
		medico.setCedulaMedico(cmbNacionalidad.getValue()+"-"+txtCedula.getValue().toString());
		medico.setTelefonoCelular(codigosCelular.get(cmbCodCelular.getSelectedIndex()).getNombre()+txtTelefonoCelular.getValue());
		medico.setDatoBasico(especialidades.get(cmbEspecialidad.getSelectedIndex()));
		medico.setTelefonoOficina(codigosArea.get(cmbCodArea.getSelectedIndex()).getNombre()+txtTelefonoHabitacion.getValue());
		medico.setEstatus('A');
		servicioMedico.actualizar(medico);
		limpiar();

		// }
	}

	public void onClick$btnEliminar() {
		medico.setEstatus('E');
		servicioMedico.actualizar(medico);
		limpiar();
		
	}

	public void onClick$btnCancelar() {
		limpiar();
	}

	public void onClick$btnBuscar() {
		// se crea el catalogo y se llama
		Component catalogo = Executions.createComponents(
				"/Jugador/Vistas/FrmBuscarMedico.zul", null, null);
		// asigna una referencia del formulario al catalogo.
		catalogo.setVariable("formulario", formulario, false);
		
		formulario.addEventListener("onCatalogoCerrado", new EventListener() {
			@Override
			// Este metodo se llama cuando se envia la señal desde el catalogo
			public void onEvent(Event arg0) throws Exception {
				// se obtiene el jugador
				medico = (Medico) formulario.getVariable("medico", false);
				cmbEspecialidad.setSelectedIndex(buscaresp(medico));
				cmbCodArea.setSelectedIndex(buscarcarea(medico));
				cmbCodCelular.setSelectedIndex(buscarcelu(medico));
				txtTelefonoCelular.setValue(medico.getTelefonoCelular().substring(5));
				txtTelefonoHabitacion.setValue(medico.getTelefonoOficina().substring(5));
				cmbNacionalidad.setValue(medico.getCedulaMedico().substring(0,1));
				txtCedula.setValue(medico.getCedulaMedico().substring(2));
				txtNumcol.setDisabled(true);
				binder.loadAll();
				
			}
		});
	}

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}


	public List<DatoBasico> getEspecialidades() {
		especialidades=servicioDatoBasico.buscar(TipoDatoBasico.ESPECIALIDAD);
		return servicioDatoBasico.buscar(TipoDatoBasico.ESPECIALIDAD);
	}
	
	public List<DatoBasico> getCodigosCelular() {
		codigosCelular=servicioDatoBasico.buscar(TipoDatoBasico.CODIGO_CELULAR);
		return servicioDatoBasico.buscar(TipoDatoBasico.CODIGO_CELULAR);
	}
	
	public List<DatoBasico> getCodigosArea() {
		codigosArea=servicioDatoBasico.buscar(TipoDatoBasico.CODIGO_AREA);
		return servicioDatoBasico.buscar(TipoDatoBasico.CODIGO_AREA);
	}

	public void setCodigosArea(List<DatoBasico> codigosArea) {
		this.codigosArea = codigosArea;
	}
	
	public void limpiar(){
		txtTelefonoCelular.setValue("");
		txtTelefonoHabitacion.setValue("");
		cmbEspecialidad.setValue("");
		cmbNacionalidad.setValue("");
		cmbCodCelular.setValue("");
		cmbCodArea.setValue("");
		txtCedula.setValue("");
		medico = new Medico();
		txtNumcol.setDisabled(false);
		binder.loadAll();
		
	}

	public int buscaresp(Medico medico){
		for (int i = 0; i < especialidades.size(); i++) {
			if (especialidades.get(i).getNombre().equals(medico.getDatoBasico().getNombre())){
				System.out.println(i);
				return i;
			}
		}
		return -1;
	}
	
	public int buscarcarea(Medico medico){
		for (int i = 0; i < codigosArea.size(); i++) {
			if (codigosArea.get(i).getNombre().equals(medico.getTelefonoOficina().substring(0,4))){
				return i;
			}
		}
		return -1;
	}
	
	public int buscarcelu(Medico medico){
		for (int i = 0; i < codigosCelular.size(); i++) {
			if (codigosCelular.get(i).getNombre().equals(medico.getTelefonoCelular().substring(0,4))){
				return i;
			}
		}
		return -1;
	}
	
	
	
	
	
}
