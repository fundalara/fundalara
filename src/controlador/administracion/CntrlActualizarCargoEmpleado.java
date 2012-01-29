package controlador.administracion;

import java.awt.Button;
import java.util.ArrayList;

import java.util.List;

import org.zkoss.zhtml.Messagebox;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Datebox;

import servicio.implementacion.ServicioConceptoNomina;
import servicio.implementacion.ServicioDatoBasico;
import servicio.implementacion.ServicioPersona;
import servicio.implementacion.ServicioPersonal;
import servicio.implementacion.ServicioPersonaNatural;
import servicio.implementacion.ServicioPersonalConceptoNomina;
import servicio.implementacion.ServicioPersonalContrato;
import servicio.implementacion.ServicioPersonalCargo;
import servicio.implementacion.ServicioTipoDato;
import modelo.ConceptoNomina;
import modelo.DatoBasico;
import modelo.Persona;
import modelo.Personal;
import modelo.PersonalCargo;
import modelo.PersonaNatural;
import modelo.PersonalConceptoNomina;
import modelo.PersonalContrato;
import modelo.TallaPorIndumentaria;
import modelo.TipoDato;

public class CntrlActualizarCargoEmpleado extends GenericForwardComposer {

	Button btnBuscarCed, btnCancelar, btnGuardar;
	Component formulario, formularioNomEmp;
	AnnotateDataBinder binder;
	Persona persona = new Persona();
	Textbox txtCed, txtNombre, txtCargo;
	PersonaNatural personaNatural = new PersonaNatural();
	PersonalCargo personalCargo;
	PersonalCargo personalCargoAux;
	PersonalCargo personalCargoEdi;
	PersonalCargo personalCargoAgr;
	ServicioPersonalCargo servicioPersonalCargo;
	List<DatoBasico> cargos = new ArrayList<DatoBasico>();
	ServicioDatoBasico servicioDatoBasico;
	Combobox cmbCargo;
	DatoBasico cargo;
	DatoBasico cargoAux;
	TipoDato tipoDatoAux;
	ServicioTipoDato servicioTipoDato;
	List<PersonalCargo> personalCargos = new ArrayList<PersonalCargo>();
	Datebox dbxFechaInicio, dbxFechaFinalizacion;

	public List<PersonalCargo> getPersonalCargos() {
		return personalCargos;
	}

	public void setPersonalCargos(List<PersonalCargo> personalCargos) {
		this.personalCargos = personalCargos;
	}

	Listbox gridPersonalCargo;

	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		comp.setVariable("cntrl", this, true);
		formulario = comp;
		formulario.setId("frmPersonas");
		formulario.setAttribute("padre", "PERSONAL REMUNERADO");
		formularioNomEmp = comp;
		this.llenarCmbCargo();
	}

	// ---------------------------------------------------------------------------------------------------

	public void onClick$btnGuardar() {

		if (personalCargos.size() != 0)
		{
		personalCargoEdi = servicioPersonalCargo.buscarPorCodigo(personalCargos
				.get(personalCargos.size() - 1).getCodigoPersonalCargo());
		personalCargoEdi.setEstatus('E');
		personalCargoEdi.setFechaFin(dbxFechaInicio.getValue());
		servicioPersonalCargo.actualizar(personalCargoEdi);
		}
		System.out.println(servicioPersonalCargo.listar().size() + 1 );
		cargoAux = (DatoBasico)cmbCargo.getSelectedItem().getValue();
		System.out.println(cargoAux.getNombre());
		personalCargoAgr = new PersonalCargo();
		personalCargoAgr.setDatoBasico(cargoAux);
		System.out.println(persona.getPersonaNatural().getPersonal().getCedulaRif());
		personalCargoAgr.setCodigoPersonalCargo(servicioPersonalCargo.listar().size() + 1 );
		personalCargoAgr.setPersonal(persona.getPersonaNatural().getPersonal());
		
		System.out.println(dbxFechaInicio.getValue());
		System.out.println(dbxFechaFinalizacion.getValue());
		personalCargoAgr.setFechaInicio(dbxFechaInicio.getValue());
		personalCargoAgr.setFechaFin(dbxFechaFinalizacion.getValue());
		personalCargoAgr.setEstatus('A');
		servicioPersonalCargo.agregar(personalCargoAgr);
		personalCargos = servicioPersonalCargo.buscarHistorial(persona
				.getPersonaNatural().getPersonal());
		try {
			Messagebox.show("El cargo se ha agregado exitosamente", "Exito",
					Messagebox.OK, Messagebox.INFORMATION);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		binder.loadAll();
		
	}

	public void onClick$btnBuscarCed() {

		System.out.println("Hola");
		Component catalogo = Executions.createComponents(
				"/Administracion/Vistas/FrmCatalogoPersonasNaturales.zul",
				formulario, null);

		formulario.addEventListener("onCierre", new EventListener() {
			@Override
			public void onEvent(Event arg0) throws Exception {

				persona = (Persona) formulario.getVariable("persona", false);
				txtCed.setValue(persona.getCedulaRif());
				personaNatural = persona.getPersonaNatural();
				String segundoN = "", segundoA = "";

				if (persona.getPersonaNatural().getSegundoNombre() == null)
					segundoN = "";
				else
					segundoN = persona.getPersonaNatural().getSegundoNombre();

				if (persona.getPersonaNatural().getSegundoNombre() == null)
					segundoA = "";
				else
					segundoA = persona.getPersonaNatural().getSegundoApellido();

				txtNombre.setText(persona.getPersonaNatural().getPrimerNombre()
						+ " " + segundoN + " "
						+ persona.getPersonaNatural().getPrimerApellido() + " "
						+ segundoA);
				personalCargo = servicioPersonalCargo.buscarCargoActual(persona
						.getPersonaNatural().getPersonal());
				if (personalCargo != null) {
					txtCargo.setText(personalCargo.getDatoBasico().getNombre());
				}
				personalCargos = servicioPersonalCargo.buscarHistorial(persona
						.getPersonaNatural().getPersonal());
				binder.loadAll();
			}
		});

	}

	// ---------------------------------------------------------------------------------------------------

	public void llenarCmbCargo() {
		//tipoDatoAux = servicioTipoDato.buscarcargo();
		tipoDatoAux = servicioTipoDato.buscarPorTipo("CARGO PERSONAL REMUNERADO");
		cmbCargo.getItems().clear();
		cargos = servicioDatoBasico.buscarPorTipoDato(tipoDatoAux);

		if (cargos.equals(null)) {
			alert("No se encontro");
		} else {
			for (int i = 0; i < cargos.size(); i++) {
				Comboitem item = new Comboitem();

				item.setLabel(cargos.get(i).getNombre().toString());
				item.setValue(cargos.get(i));

				cmbCargo.appendChild(item);
			}
		}
	}


public void onClick$btnCancelar() {
cmbCargo.setValue(" ");
dbxFechaInicio.setValue(null) ;
dbxFechaFinalizacion.setValue(null) ;
}
}
	
