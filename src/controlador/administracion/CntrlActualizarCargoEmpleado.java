package controlador.administracion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import java.util.List;

import org.zkoss.zhtml.Messagebox;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Datebox;

import comun.Mensaje;
import comun.MensajeMostrar;

import servicio.implementacion.ServicioDatoBasico;
import servicio.implementacion.ServicioPersonalCargo;
import servicio.implementacion.ServicioTipoDato;
import modelo.DatoBasico;
import modelo.Persona;
import modelo.PersonalCargo;
import modelo.PersonaNatural;
import modelo.TipoDato;

public class CntrlActualizarCargoEmpleado extends GenericForwardComposer {
	Button btnBuscarCed, btnCancelar, btnGuardar;
	Component formulario;
	AnnotateDataBinder binderA;
	Persona persona;
	Textbox txtCed, txtCargo, txtNombreEmpleado;
	PersonaNatural personaNatural = new PersonaNatural();
	PersonalCargo personalCargo;
	PersonalCargo personalCargoAux;
	PersonalCargo personalCargoEdi;
	PersonalCargo personalCargoAgr;
	ServicioPersonalCargo servicioPersonalCargo;
	List<DatoBasico> cargos = new ArrayList<DatoBasico>();
	ServicioDatoBasico servicioDatoBasico;
	Combobox cmbCargo, cmbPersona;
	DatoBasico cargo;
	DatoBasico cargoAux;
	TipoDato tipoDatoAux;
	ServicioTipoDato servicioTipoDato;
	List<PersonalCargo> personalCargos = new ArrayList<PersonalCargo>();
	Datebox dbxFechaInicio, dbxFechaFinalizacion;
	//MensajeMostrar mensaje = new MensajeMostrar();
	Listbox gridPersonalCargo;

	// ---------------------------------------------------------------------------------------------------
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		comp.setVariable("cntrl", this, true);
		formulario = comp;
		
		
	}

	// ---------------------------------------------------------------------------------------------------
	public void onClick$btnGuardar() throws InterruptedException {
		if (persona != null) {
			if (dbxFechaFinalizacion.getValue() == null) {
				throw new WrongValueException(dbxFechaFinalizacion,
						"Seleccione una Fecha");
			} else if (cmbCargo.getValue() == null
					|| cmbCargo.getValue() == "--Seleccione--") {
				throw new WrongValueException(cmbCargo,
						"Seleccione el nuevo Cargo");
			} else if (dbxFechaInicio.getValue() == null) {
				throw new WrongValueException(dbxFechaInicio,
						"Seleccione una Fecha");
			} else {

				Messagebox.show(MensajeMostrar.GUARDAR,
						MensajeMostrar.TITULO.toString() + "Importante", Messagebox.OK
								| Messagebox.CANCEL, Messagebox.QUESTION,
						new EventListener() {
							@Override
							public void onEvent(Event arg0)
									throws InterruptedException {
								if (arg0.getName().toString() == "onOK") {
									if (personalCargos.size() != 0) {
										personalCargoEdi = servicioPersonalCargo.buscarPorCodigo(personalCargos
												.get(personalCargos.size() - 1)
												.getCodigoPersonalCargo());
										personalCargoEdi.setEstatus('E');
										personalCargoEdi
												.setFechaFin(dbxFechaFinalizacion
														.getValue());
										servicioPersonalCargo
												.actualizar(personalCargoEdi);
									}
									cargoAux = (DatoBasico) cmbCargo
											.getSelectedItem().getValue();
									personalCargoAgr = new PersonalCargo();
									personalCargoAgr.setDatoBasico(cargoAux);
									personalCargoAgr
											.setCodigoPersonalCargo(servicioPersonalCargo
													.listar().size() + 1);
									personalCargoAgr.setPersonal(persona
											.getPersonaNatural().getPersonal());
									personalCargoAgr
											.setFechaInicio(dbxFechaInicio
													.getValue());
//									personalCargoAgr.setFechaFin(dbxFechaInicio
//											.getValue());
									personalCargoAgr.setEstatus('A');
									servicioPersonalCargo
											.agregar(personalCargoAgr);
									personalCargos = servicioPersonalCargo
											.buscarHistorial(persona
													.getPersonaNatural()
													.getPersonal());

									personalCargo = servicioPersonalCargo
											.buscarCargoActual(persona
													.getPersonaNatural()
													.getPersonal());
									if (personalCargo != null) {
										txtCargo.setText(personalCargo
												.getDatoBasico().getNombre());
									}

									dbxFechaInicio.setValue(null);
									dbxFechaFinalizacion.setValue(null);
									cmbCargo.setValue("--Seleccione--");
									binderA.loadAll();
									Messagebox.show(MensajeMostrar.REGISTRO_EXITOSO,
											MensajeMostrar.TITULO + "Información",
											Messagebox.OK,
											Messagebox.INFORMATION);
								}
							}
						});

			}
		} else {
			throw new WrongValueException(btnBuscarCed,
					MensajeMostrar.PERSONA_NO_UBICADA);
		}
	}

	// ---------------------------------------------------------------------------------------------------
	public void onClick$btnBuscarCed() {
		if (cmbPersona.getSelectedIndex() != -1) {
			Map params = new HashMap();
			params.put("padre", cmbPersona.getSelectedItem().getValue());
			params.put("formulario", formulario);
			Executions.createComponents(
					"/Administracion/Vistas/frmCatalogoPersonasNaturales.zul",
					null, params);

			formulario.addEventListener("onCierreNatural", new EventListener() {
				@Override
				public void onEvent(Event arg0) throws Exception {

					persona = (Persona) formulario
							.getVariable("persona", false);
					txtCed.setValue(persona.getCedulaRif());
					personaNatural = persona.getPersonaNatural();
					String segundoN = "", segundoA = "";
					if (persona.getPersonaNatural().getSegundoNombre() == null
							|| persona.getPersonaNatural().getSegundoNombre() == "null")
						segundoN = "";
					else
						segundoN = persona.getPersonaNatural()
								.getSegundoNombre();

					if (persona.getPersonaNatural().getSegundoApellido() == null
							|| persona.getPersonaNatural().getSegundoApellido()
									.toString() == "null")
						segundoA = "";
					else
						segundoA = persona.getPersonaNatural()
								.getSegundoApellido();

					txtNombreEmpleado.setText(persona.getPersonaNatural()
							.getPrimerNombre()
							+ " "
							+ segundoN
							+ " "
							+ persona.getPersonaNatural().getPrimerApellido()
							+ " " + segundoA);
					personalCargo = servicioPersonalCargo
							.buscarCargoActual(persona.getPersonaNatural()
									.getPersonal());
					if (personalCargo != null) {
						txtCargo.setText(personalCargo.getDatoBasico()
								.getNombre());
					}
					personalCargos = servicioPersonalCargo
							.buscarHistorial(persona.getPersonaNatural()
									.getPersonal());
					if (persona.getDatoBasicoByCodigoTipoPersona().getNombre()
							.equals("PERSONAL REMUNERADO")) {
						llenarCmbCargo("CARGO PERSONAL REMUNERADO");
					} else {
						llenarCmbCargo("CARGO PERSONAL AD HONOREM");
					}
					dbxFechaFinalizacion.setValue(null);
					dbxFechaInicio.setValue(null);
					binderA.loadAll();
				}
			});
		} else {
			throw new WrongValueException(cmbPersona,
					"Debe seleccionar el Tipo de Personal");
		}
	}

	// ---------------------------------------------------------------------------------------------------
	public void llenarCmbCargo(String tipoCargo) {
		tipoDatoAux = servicioTipoDato.buscarPorTipo(tipoCargo);
		cmbCargo.getItems().clear();
		cargos = servicioDatoBasico.buscarPorTipoDato(tipoDatoAux);

		if (cargos.equals(null)) {
			throw new WrongValueException(cmbCargo, "No hay Cargos registrados");
		} else {
			for (int i = 0; i < cargos.size(); i++) {
				Comboitem item = new Comboitem();

				item.setLabel(cargos.get(i).getNombre().toString());
				item.setValue(cargos.get(i));

				cmbCargo.appendChild(item);
			}
		}
	}

	// ---------------------------------------------------------------------------------------------------
	public void limpiar() {
		cmbCargo.setValue(" ");
		dbxFechaInicio.setValue(null);
		dbxFechaFinalizacion.setValue(null);
		txtCargo.setText("");
		txtCed.setText("");
		txtNombreEmpleado.setText("");
		cmbCargo.setValue("--Seleccione--");
		cargos = new ArrayList<DatoBasico>();
		personalCargos = new ArrayList<PersonalCargo>();
		binderA.loadAll();
	}

	// ---------------------------------------------------------------------------------------------------
	public void onClick$btnCancelar() {
		limpiar();
	}

	// ---------------------------------------------------------------------------------------------------
	public List<PersonalCargo> getPersonalCargos() {
		return personalCargos;
	}

	public void setPersonalCargos(List<PersonalCargo> personalCargos) {
		this.personalCargos = personalCargos;
	}
	// ---------------------------------------------------------------------------------------------------
}