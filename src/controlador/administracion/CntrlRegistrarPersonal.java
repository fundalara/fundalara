package controlador.administracion;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;

import java.util.ArrayList;
import java.util.List;

import modelo.AfeccionPersonal;
import modelo.AfeccionPersonalId;
import modelo.ConceptoNomina;
import modelo.DatoAcademicoPersonal;
import modelo.DatoBasico;
import modelo.Persona;
import modelo.PersonaNatural;
import modelo.Personal;
import modelo.PersonalCargo;
import modelo.PersonalConceptoNomina;
import modelo.PersonalContrato;
import modelo.PersonalTipoNomina;
import modelo.TipoDato;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Button;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Spinner;
import org.zkoss.zul.Textbox;

import servicio.implementacion.ServicioAfeccionPersonal;
import servicio.implementacion.ServicioConceptoNomina;
import servicio.implementacion.ServicioDatoAcademicoPersonal;
import servicio.implementacion.ServicioDatoBasico;
import servicio.implementacion.ServicioPersona;
import servicio.implementacion.ServicioPersonaNatural;
import servicio.implementacion.ServicioPersonal;
import servicio.implementacion.ServicioPersonalCargo;
import servicio.implementacion.ServicioPersonalConceptoNomina;
import servicio.implementacion.ServicioPersonalContrato;
import servicio.implementacion.ServicioPersonalTipoNomina;
import servicio.implementacion.ServicioTipoDato;

public class CntrlRegistrarPersonal extends GenericForwardComposer {

	/* Instancias de las Clases Java del Modelo a Utilizar */
	DatoBasico datoBasico;
	TipoDato tipoDato = new TipoDato();
	Persona persona = new Persona();
	PersonaNatural personaNatural = new PersonaNatural();
	Personal personal = new Personal();
	PersonalContrato personalContrato = new PersonalContrato();
	PersonalTipoNomina personalTipoNomina = new PersonalTipoNomina();
	DatoAcademicoPersonal datoAcademicoPersonal = new DatoAcademicoPersonal();
	AfeccionPersonal afeccionPersonal;
	AfeccionPersonalId afeccionPersonalId = new AfeccionPersonalId();
	PersonalConceptoNomina personalConceptoNomina = new PersonalConceptoNomina();
	ConceptoNomina conceptoNomina = new ConceptoNomina();
	PersonalCargo personalCargo = new PersonalCargo();

	/* Instancias de los Servicios a Utilizar */
	ServicioDatoBasico servicioDatoBasico;
	ServicioTipoDato servicioTipoDato;
	ServicioPersona servicioPersona;
	ServicioPersonaNatural servicioPersonaNatural;
	ServicioPersonal servicioPersonal;
	ServicioPersonalContrato servicioPersonalContrato;
	ServicioPersonalTipoNomina servicioPersonalTipoNomina;
	ServicioDatoAcademicoPersonal servicioDatoAcademicoPersonal;
	ServicioAfeccionPersonal servicioAfeccionPersonal;
	ServicioPersonalConceptoNomina servicioPersonalConceptoNomina;
	ServicioConceptoNomina servicioConceptoNomina;
	ServicioPersonalCargo servicioPersonalCargo;

	/* Instancias de las Listas a Utilizar */

	List<DatoBasico> parroquias = new ArrayList<DatoBasico>();
	List<DatoBasico> edosCivil = new ArrayList<DatoBasico>();
	List<DatoBasico> sexos = new ArrayList<DatoBasico>();
	List<DatoBasico> estados = new ArrayList<DatoBasico>();
	List<DatoBasico> municipios = new ArrayList<DatoBasico>();
	List<DatoBasico> alergias = new ArrayList<DatoBasico>();
	List<DatoBasico> GrupoSanguineo = new ArrayList<DatoBasico>();
	List<DatoBasico> FactorSanguineo = new ArrayList<DatoBasico>();
	List<DatoBasico> tipoEmpleados = new ArrayList<DatoBasico>();
	List<DatoBasico> tipoNominas = new ArrayList<DatoBasico>();
	List<DatoBasico> cargos = new ArrayList<DatoBasico>();
	List<DatoBasico> codAreas = new ArrayList<DatoBasico>();
	List<DatoBasico> codCels = new ArrayList<DatoBasico>();
	List<DatoBasico> tipoCeds = new ArrayList<DatoBasico>();
	List<PersonalCargo> personalCargos = new ArrayList<PersonalCargo>();
	List<PersonalContrato> personalContratos = new ArrayList<PersonalContrato>();
	List<PersonalTipoNomina> personalTipoNominas = new ArrayList<PersonalTipoNomina>();
	List<DatoAcademicoPersonal> datoAcademicosP = new ArrayList<DatoAcademicoPersonal>();
	List<AfeccionPersonal> afeccionPersonales = new ArrayList<AfeccionPersonal>();

	/* Instancias de los combos a Utilizar */
	Combobox cmbCed, cmbGenero, cmbEdoCivil, cmbCodArea, cmbCodOper, cmbEdo,
			cmbMunicipio, cmbParroquia, cmbGrupSanguineo, cmbFactor,
			cmbAlergia, cmbCargo, cmbTipoNomina, cmbHoraTrab, cmbTipoEmpleado,
			cmbAsignacion, cmbDeduccion;

	/* Instancias de los datebox a Utilizar */

	Datebox dtFechaNac, dtFechaEgreso, dtFechaCargo, dtFechaIngresoEsc, dtFechaEgresoEsc;

	/* Instancias de los botones a Utilizar */

	Button btnGuardar, btnModificar, btnCancelar, btnSalir, btnNvaAlergia,
			btnEliminar, btnNvoCargo, btnNvoTipoNomina, btnNvoHoraTrab,
			btnNvoTipoEmpleado, btnHistorial, btnAgregarAlerg, btnQuitarAlerg,
			btnQuitarDatAcad, btnAgregarDatAcad, btnBuscarCed;

	/* Instancias de los texbox a Utilizar */

	Textbox txtCed;
	Textbox txtPrimNombre;
	Textbox txtSegNombre;
	Textbox txtPrimApellido;
	Textbox txtSegApellido;
	Textbox txtTelfHab;
	Textbox txtTelfCel;
	Textbox txtCorreo;
	Textbox txtTwitter;
	Textbox txtDir;
	Textbox txtInstituto;
	Textbox txtTituloObt;

	/* Instancias de los Spinner a Utilizar */

	Spinner spNroHijos;

	/* Instancias de los Grid a Utilizar */
	Listbox gridAlergia;
	Listitem colAlergias;
	Listbox gridDatoAcademicos;
	Listitem colDatAcad;

	AnnotateDataBinder binder;
	Component formulario, formularioRegPers;

	/* Desarrollo de los Metodos y Funciones del Controlador */
	// ------------------------------------------------------------------------------------------------------
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		comp.setVariable("cntrl", this, true);
		formulario = comp;
		formulario.setId("frmPersonas");
		formulario.setAttribute("padre", "PERSONAL REMUNERADO");
		cmbParroquia.setDisabled(true);
		cmbMunicipio.setDisabled(true);
		this.llenarCmbEstadoCivil();
		this.llenarCmbGenero();
		this.llenarCmbEstadoVenezuela();
		this.llenarCmbAlergia();
		this.llenarCmbGrupoSanguineo();
		this.llenarCmbFactorSanguineo();
		this.llenarCmbTipoEmpleado();
		this.llenarCmbTipoNomina();
		this.llenarCmbCargo();
		this.llenarCmbCodArea();
		this.llenarCmbCodCel();
		// llenarCmbTipoCed();
		limpiarFormulario();
	}

	// ------------------------------------------------------------------------------------------------------
	public void llenarCmbTipoCed() {
		cmbCed.getItems().clear();
		tipoDato = servicioTipoDato.buscarTipo("TIPO CEDULA");
		tipoCeds = servicioDatoBasico.buscarPorTipoDato(tipoDato);

		if (tipoCeds.equals(null)) {
			alert("No se encontro");
		} else {
			for (int i = 0; i < tipoCeds.size(); i++) {
				Comboitem item = new Comboitem();

				item.setLabel(tipoCeds.get(i).getNombre().toString());
				item.setValue(tipoCeds.get(i).getCodigoDatoBasico());

				cmbCed.appendChild(item);
			}
		}
	}

	// ------------------------------------------------------------------------------------------------------
	public void llenarCmbCodArea() {
		cmbCodArea.getItems().clear();
		tipoDato = servicioTipoDato.buscarTipo("CODIGO_AREA");
		codAreas = servicioDatoBasico.buscarPorTipoDato(tipoDato);
		if (codAreas.equals(null)) {
			alert("No se encontro");
		} else {
			for (int i = 0; i < codAreas.size(); i++) {
				Comboitem item = new Comboitem();

				item.setLabel(codAreas.get(i).getNombre().toString());
				item.setValue(codAreas.get(i).getCodigoDatoBasico());

				cmbCodArea.appendChild(item);
			}
		}
	}

	// ------------------------------------------------------------------------------------------------------
	public void llenarCmbCodCel() {
		cmbCodOper.getItems().clear();
		tipoDato = servicioTipoDato.buscarTipo("CODIGO_CELULAR");
		codCels = servicioDatoBasico.buscarPorTipoDato(tipoDato);

		if (codCels.equals(null)) {
			alert("No se encontro");
		} else {
			for (int i = 0; i < codCels.size(); i++) {
				Comboitem item = new Comboitem();

				item.setLabel(codCels.get(i).getNombre().toString());
				item.setValue(codCels.get(i).getCodigoDatoBasico());

				cmbCodOper.appendChild(item);
			}
		}
	}

	// ------------------------------------------------------------------------------------------------------
	public void llenarCmbGenero() {
		cmbGenero.getItems().clear();
		tipoDato = servicioTipoDato.buscarTipo("GENERO");
		sexos = servicioDatoBasico.buscarPorTipoDato(tipoDato);

		if (sexos.equals(null)) {
			alert("No se encontro");
		} else {
			for (int i = 0; i < sexos.size(); i++) {
				Comboitem item = new Comboitem();

				item.setLabel(sexos.get(i).getNombre().toString());
				item.setValue(sexos.get(i).getCodigoDatoBasico());

				cmbGenero.appendChild(item);
			}
		}
	}

	// ------------------------------------------------------------------------------------------------------
	public void llenarCmbEstadoCivil() {
		cmbEdoCivil.getItems().clear();
		tipoDato = servicioTipoDato.buscarTipo("ESTADO CIVIL");
		edosCivil = servicioDatoBasico.buscarPorTipoDato(tipoDato);

		if (edosCivil.equals(null)) {
			alert("No se encontro");
		} else {
			for (int i = 0; i < edosCivil.size(); i++) {
				Comboitem item = new Comboitem();

				item.setLabel(edosCivil.get(i).getNombre().toString());
				item.setValue(edosCivil.get(i).getCodigoDatoBasico());

				cmbEdoCivil.appendChild(item);
			}
		}
	}

	// ------------------------------------------------------------------------------------------------------
	public void llenarCmbEstadoVenezuela() {
		cmbEdo.getItems().clear();
		tipoDato = servicioTipoDato.buscarTipo("ESTADO_VENEZUELA");
		estados = servicioDatoBasico.buscarPorTipoDato(tipoDato);

		if (estados.equals(null)) {
			alert("No se encontro");
		} else {
			for (int i = 0; i < estados.size(); i++) {
				Comboitem item = new Comboitem();

				item.setLabel(estados.get(i).getNombre().toString());
				item.setValue(estados.get(i).getCodigoDatoBasico());

				cmbEdo.appendChild(item);
			}
		}
	}

	// ------------------------------------------------------------------------------------------------------
	public void onSelect$cmbEdo() {
		try {
			cmbParroquia.setValue("-Seleccione-");
			cmbMunicipio.setValue("-Seleccione-");
			municipios = new ArrayList<DatoBasico>();
			municipios = servicioDatoBasico.listarPorPadre(
					"MUNICIPIO",
					Integer.parseInt(cmbEdo.getSelectedItem().getValue()
							.toString()));
			cmbMunicipio.setDisabled(false);
			binder.loadComponent(cmbMunicipio);
		} catch (Exception e) {
			// -----------
		}
	}

	// ------------------------------------------------------------------------------------------------------
	public void onSelect$cmbMunicipio() {
		try {
			cmbParroquia.setDisabled(false);
			cmbParroquia.setValue("-Seleccione-");
			parroquias = new ArrayList<DatoBasico>();
			parroquias = servicioDatoBasico.listarPorPadre(
					"PARROQUIA",
					Integer.parseInt(cmbMunicipio.getSelectedItem().getValue()
							.toString()));
			binder.loadComponent(cmbParroquia);
		} catch (Exception e) {
			// -----------
		}
	}

	// ------------------------------------------------------------------------------------------------------
	public void onSelect$cmbParroquia() {
		try {
			cmbParroquia.setContext(cmbParroquia.getSelectedItem().getValue()
					.toString());
		} catch (Exception e) {
			// -----------
		}
	}

	// ------------------------------------------------------------------------------------------------------
	public void llenarCmbAlergia() {
		cmbAlergia.getItems().clear();
		tipoDato = servicioTipoDato.buscarTipo("AFECCION");
		alergias = servicioDatoBasico.buscarPorTipoDato(tipoDato);

		if (alergias.equals(null)) {
			alert("No se encontro");
		} else {
			for (int i = 0; i < alergias.size(); i++) {
				Comboitem item = new Comboitem();

				item.setLabel(alergias.get(i).getNombre().toString());
				item.setValue(alergias.get(i).getCodigoDatoBasico());

				cmbAlergia.appendChild(item);
			}
		}
	}

	// ------------------------------------------------------------------------------------------------------
	public void llenarCmbGrupoSanguineo() {
		cmbGrupSanguineo.getItems().clear();
		tipoDato = servicioTipoDato.buscarTipo("GRUPO_SANGUINEO");
		GrupoSanguineo = servicioDatoBasico.buscarPorTipoDato(tipoDato);

		if (GrupoSanguineo.equals(null)) {
			alert("No se encontro");
		} else {
			for (int i = 0; i < GrupoSanguineo.size(); i++) {
				Comboitem item = new Comboitem();

				item.setLabel(GrupoSanguineo.get(i).getNombre().toString());
				item.setValue(GrupoSanguineo.get(i).getCodigoDatoBasico());

				cmbGrupSanguineo.appendChild(item);
			}
		}

	}

	// ------------------------------------------------------------------------------------------------------
	public void llenarCmbFactorSanguineo() {
		cmbFactor.getItems().clear();
		tipoDato = servicioTipoDato.buscarTipo("FACTOR_SANGUINEO");
		FactorSanguineo = servicioDatoBasico.buscarPorTipoDato(tipoDato);

		if (FactorSanguineo.equals(null)) {
			alert("No se encontro");
		} else {
			for (int i = 0; i < FactorSanguineo.size(); i++) {
				Comboitem item = new Comboitem();

				item.setLabel(FactorSanguineo.get(i).getNombre().toString());
				item.setValue(FactorSanguineo.get(i).getCodigoDatoBasico());

				cmbFactor.appendChild(item);
			}
		}
	}

	// ------------------------------------------------------------------------------------------------------
	public void llenarCmbTipoEmpleado() {
		cmbTipoEmpleado.getItems().clear();
		tipoDato = servicioTipoDato.buscarTipo("MODALIDAD DE CONTRATO");
		tipoEmpleados = servicioDatoBasico.buscarPorTipoDato(tipoDato);

		if (tipoEmpleados.equals(null)) {
			alert("No se encontro");
		} else {
			for (int i = 0; i < tipoEmpleados.size(); i++) {
				Comboitem item = new Comboitem();

				item.setLabel(tipoEmpleados.get(i).getNombre().toString());
				item.setValue(tipoEmpleados.get(i).getCodigoDatoBasico());

				cmbTipoEmpleado.appendChild(item);
			}
		}
	}

	// ------------------------------------------------------------------------------------------------------
	public void llenarCmbTipoNomina() {
		cmbTipoNomina.getItems().clear();
		tipoDato = servicioTipoDato.buscarTipo("TIPO NOMINA");
		tipoNominas = servicioDatoBasico.buscarPorTipoDato(tipoDato);

		if (tipoNominas.equals(null)) {
			alert("No se encontro");
		} else {
			for (int i = 0; i < tipoNominas.size(); i++) {
				Comboitem item = new Comboitem();

				item.setLabel(tipoNominas.get(i).getNombre().toString());
				item.setValue(tipoNominas.get(i).getCodigoDatoBasico());

				cmbTipoNomina.appendChild(item);
			}
		}
	}

	// ------------------------------------------------------------------------------------------------------
	public void llenarCmbCargo() {
		cmbCargo.getItems().clear();
		tipoDato = servicioTipoDato.buscarTipo("CARGO PERSONAL REMUNERADO");
		cargos = servicioDatoBasico.buscarPorTipoDato(tipoDato);

		if (cargos.equals(null)) {
			alert("No se encontro");
		} else {
			for (int i = 0; i < cargos.size(); i++) {
				Comboitem item = new Comboitem();

				item.setLabel(cargos.get(i).getNombre().toString());
				item.setValue(cargos.get(i).getCodigoDatoBasico());

				cmbCargo.appendChild(item);
			}
		}
	}

	// ------------------------------------------------------------------------------------------------------
	public void onClick$btnAgregarAlerg() {
		if (gridAlergia.getItemCount() == 0) {
			colAlergias = new Listitem();
			colAlergias.appendChild(new Listcell(this.cmbAlergia
					.getSelectedItem().getLabel()));
			gridAlergia.appendChild(colAlergias);
		} else {

			int i = 0;
			do {
				colAlergias = gridAlergia.getItemAtIndex(i);
				Listcell celda0 = (Listcell) colAlergias.getChildren().get(0);
				if (this.cmbAlergia.getSelectedItem().getLabel()
						.equals(celda0.getLabel())) {

					alert("Esta Alergia ya ha sido incluida.");
					return;
				}
				i++;
			} while (i < gridAlergia.getItemCount());

			colAlergias = new Listitem();
			colAlergias.appendChild(new Listcell(this.cmbAlergia
					.getSelectedItem().getLabel()));
			gridAlergia.appendChild(colAlergias);
		}
	}

	// ------------------------------------------------------------------------------------------------------
	public void onClick$btnQuitarAlerg() {
		if (gridAlergia.getItemCount() == 0) {
			alert("No hay Alergias agregadas.");
		} else {
			if (gridAlergia.getSelectedIndex() == -1) {
				alert("Debe Seleccionar una Alergia.");
			} else {
				gridAlergia.removeItemAt(gridAlergia.getSelectedIndex());
			}
		}
	}

	// ------------------------------------------------------------------------------------------------------
	public void onClick$btnAgregarDatAcad() {
		if (gridDatoAcademicos.getItemCount() == 0) {
			colDatAcad = new Listitem();
			colDatAcad.appendChild(new Listcell(this.txtInstituto.getValue()
					.toString()));
			colDatAcad.appendChild(new Listcell(this.txtTituloObt.getValue()
					.toString()));
			colDatAcad.appendChild(new Listcell(this.dtFechaEgreso.getValue()
					.toString()));
			gridDatoAcademicos.appendChild(colDatAcad);
		} else {
			int i = 0;
			do {
				colDatAcad = gridDatoAcademicos.getItemAtIndex(i);
				Listcell celda0 = (Listcell) colDatAcad.getChildren().get(0);
				Listcell celda1 = (Listcell) colDatAcad.getChildren().get(1);
				Listcell celda2 = (Listcell) colDatAcad.getChildren().get(2);
				if (this.txtInstituto.getValue().toString()
						.equals(celda0.getLabel())
						&& this.txtTituloObt.getValue().toString()
								.equals(celda1.getLabel())
						&& this.dtFechaEgreso.getValue().toString()
								.equals(celda2.getLabel())) {

					alert("Esta Alergia ya ha sido incluida.");
					return;
				}
				i++;
			} while (i < gridDatoAcademicos.getItemCount());

			colDatAcad = new Listitem();
			colDatAcad.appendChild(new Listcell(this.txtInstituto.getValue()
					.toString()));
			colDatAcad.appendChild(new Listcell(this.txtTituloObt.getValue()
					.toString()));
			colDatAcad.appendChild(new Listcell(this.dtFechaEgreso.getValue()
					.toString()));
			gridDatoAcademicos.appendChild(colDatAcad);
		}
		txtInstituto.setText("");
		txtTituloObt.setText("");
		dtFechaEgreso.setText("");
	}

	// ------------------------------------------------------------------------------------------------------
	public void onClick$btnQuitarDatAcad() {

		if (gridDatoAcademicos.getItemCount() == 0) {
			alert("No hay Alergias agregadas.");
		} else {
			if (gridDatoAcademicos.getSelectedIndex() == -1) {
				alert("Debe Seleccionar algún registro.");
			} else {
				gridDatoAcademicos.removeItemAt(gridDatoAcademicos
						.getSelectedIndex());
			}
		}
	}

	// ------------------------------------------------------------------------------------------------------
	public void onClick$btnGuardar() {
		Integer n = 0;
		String cedula = cmbCed.getValue().toString()
				+ txtCed.getValue().toString();
		String telefono = cmbCodArea.getValue().toString()
				+ txtTelfHab.getValue().toString();
		String celular = cmbCodOper.getValue().toString()
				+ txtTelfCel.getValue().toString();

		persona.setCedulaRif(cedula);
		persona.setFechaIngreso(dtFechaIngresoEsc.getValue());
		persona.setDireccion(txtDir.getValue());
		persona.setTelefonoHabitacion(telefono);
		persona.setCorreoElectronico(txtCorreo.getValue());
		persona.setTwitter(txtTwitter.getValue());
		// persona.setFacebook(txtFace.getValue());
		datoBasico = servicioDatoBasico.buscarPorString(cmbParroquia
				.getSelectedItem().getLabel());
		persona.setDatoBasicoByCodigoParroquia(datoBasico);
		datoBasico = servicioDatoBasico.buscarPorCodigo(12);
		persona.setDatoBasicoByCodigoTipoPersona(datoBasico);

		personaNatural.setPersona(persona);
		personaNatural.setCelular(celular);
		personaNatural.setPrimerNombre(txtPrimNombre.getValue());
		personaNatural.setSegundoNombre(txtSegNombre.getValue());
		personaNatural.setPrimerApellido(txtPrimApellido.getValue());
		personaNatural.setSegundoApellido(txtSegApellido.getValue());

		if (cmbGenero.getValue().equals("Masculino")) {
			personaNatural.setDatoBasico(servicioDatoBasico
					.buscarPorString("MASCULINO"));
		} else if (cmbGenero.getValue().equals("Masculino")) {
			personaNatural.setDatoBasico(servicioDatoBasico
					.buscarPorString("FEMENINO"));
		}
		personaNatural.setFechaNacimiento(dtFechaNac.getValue());

		personal.setPersonaNatural(personaNatural);
		personal.setCantidadHijos(spNroHijos.getValue());
		personal.setEstatus('A');
		personal.setDatoBasico(servicioDatoBasico.buscarPorString(cmbEdoCivil
				.getSelectedItem().getLabel()));

		String tipoSangre = cmbGrupSanguineo.getValue() + cmbFactor.getValue();
		personal.setTipoSangre(tipoSangre);

		personalCargos = servicioPersonalCargo.listarActivos();
		n = personalCargos.size();
		n++;
		personalCargo.setCodigoPersonalCargo(n);
		datoBasico = servicioDatoBasico.buscarPorString(cmbTipoEmpleado
				.getSelectedItem().getLabel());
		personalCargo.setDatoBasico(datoBasico);
		personalCargo.setPersonal(personal);
		if (cmbTipoEmpleado.getSelectedItem().getLabel().equals("Contratado")) {
			personalCargo.setFechaFin(dtFechaEgresoEsc.getValue());
		}
		personalCargo.setFechaInicio(dtFechaCargo.getValue());
		personalCargo.setEstatus('A');

		personalContratos = servicioPersonalContrato.listar();
		n = personalContratos.size();
		n++;
		personalContrato.setCodigoPersonalContrato(n);
		datoBasico = servicioDatoBasico.buscarPorString(cmbTipoEmpleado
				.getSelectedItem().getLabel());
		personalContrato.setDatoBasicoByCodigoModalidad(datoBasico);
		personalContrato.setPersonal(personal);
//		personalContrato.setFechaInicio(dtFechaContrato.getValue());
		if (cmbTipoEmpleado.getSelectedItem().getLabel().equals("Contratado")) {
			personalContrato.setFechaFin(dtFechaEgresoEsc.getValue());
		}

		personalTipoNominas = servicioPersonalTipoNomina.listarActivos();
		n = personalTipoNominas.size();
		n++;
		personalTipoNomina.setCodigoPersonalTipoNomina(n);
		datoBasico = servicioDatoBasico.buscarPorString(cmbTipoNomina
				.getSelectedItem().getLabel());
		personalTipoNomina.setDatoBasico(datoBasico);
		personalTipoNomina.setPersonal(personal);
		//personalTipoNomina.setFechaInicio(dtFechaTipoNomina.getValue());
		if (cmbTipoEmpleado.getSelectedItem().getLabel().equals("Contratado")) {
			personalTipoNomina.setFechaFin(dtFechaEgresoEsc.getValue());
		}
		personalTipoNomina.setEstatus('A');

		servicioPersona.agregar(persona);
		servicioPersonaNatural.agregar(personaNatural);
		servicioPersonal.agregar(personal);
		servicioPersonalCargo.agregar(personalCargo);
		servicioPersonalContrato.agregar(personalContrato);
		servicioPersonalTipoNomina.agregar(personalTipoNomina);
		tipoDato = servicioTipoDato.buscarTipo("AFECCION");
		alergias = servicioDatoBasico.buscarPorTipoDato(tipoDato);

		binder.loadComponent(gridAlergia);
		for (int i = 0; i < gridAlergia.getItemCount(); i++) {

			colDatAcad = gridAlergia.getItemAtIndex(i);
			datoBasico = servicioDatoBasico.buscarPorString(colDatAcad
					.getLabel());
			for (int j = 0; j < alergias.size(); j++) {
				if (alergias.get(j).getNombre().toString()
						.equals(colDatAcad.getLabel().toString())) {
					afeccionPersonalId = new AfeccionPersonalId();
					afeccionPersonalId.setCedulaRif(txtCed.getValue());
					afeccionPersonalId.setCodigoTipoAfeccion(alergias.get(j)
							.getCodigoDatoBasico());
				}
			}

			afeccionPersonal = new AfeccionPersonal();
			afeccionPersonal.setId(afeccionPersonalId);
			System.out.println(afeccionPersonal.getId().getCedulaRif());
			System.out
					.println(afeccionPersonal.getId().getCodigoTipoAfeccion());
			// afeccionPersonal.setDatoBasico(datoBasico);
			System.out.println(afeccionPersonal.getDatoBasico()
					.getCodigoDatoBasico());
			afeccionPersonal.setPersonal(personal);
			System.out.println(afeccionPersonal.getPersonal().getCedulaRif());
			afeccionPersonal.setEstatus('A');
			System.out.println(afeccionPersonal.getEstatus());

			servicioAfeccionPersonal.agregar(afeccionPersonal);

		}
		persona = new Persona();
		personal = new Personal();
		personaNatural = new PersonaNatural();
		personalCargo = new PersonalCargo();
		personalContrato = new PersonalContrato();
		personalTipoNomina = new PersonalTipoNomina();
		afeccionPersonal = new AfeccionPersonal();

		alert("Guardado");

	}

	// ------------------------------------------------------------------------------------------------------
	public void onClick$btnCancelar() {
		limpiarFormulario();
	}

	// ------------------------------------------------------------------------------------------------------
	public void limpiarFormulario() {
		txtCed.setText("");
		txtPrimNombre.setText("");
		txtSegNombre.setText("");
		txtPrimApellido.setText("");
		txtSegApellido.setText("");
		txtTelfHab.setText("");
		txtTelfCel.setText("");
		txtCorreo.setText("");
		txtTwitter.setText("");
		txtDir.setText("");
		txtInstituto.setText("");
		txtTituloObt.setText("");

		dtFechaNac.setText("");
		dtFechaEgreso.setText("");
		dtFechaCargo.setText("");
	//	dtFechaTipoNomina.setText("");
	//	dtFechaContrato.setText("");
		dtFechaIngresoEsc.setText("");
		dtFechaEgresoEsc.setText("");

		int n = gridAlergia.getItemCount();
		for (int i = 0; i < n; i++) {
			gridAlergia.removeItemAt(0);
		}
		int m = gridDatoAcademicos.getItemCount();
		for (int j = 0; j < m; j++) {
			gridDatoAcademicos.removeItemAt(0);
		}

		cmbCed.setText("");
		cmbCed.setSelectedIndex(-1);
		cmbCed.setValue("-");
		cmbGenero.setText("");
		cmbGenero.setSelectedIndex(-1);
		cmbGenero.setValue("--Seleccione--");
		cmbEdoCivil.setText("");
		cmbEdoCivil.setSelectedIndex(-1);
		cmbEdoCivil.setValue("--Seleccione--");
		cmbCodArea.setText("");
		cmbCodArea.setSelectedIndex(-1);
		cmbCodArea.setValue("-");
		cmbCodOper.setText("");
		cmbCodOper.setSelectedIndex(-1);
		cmbCodOper.setValue("-");
		cmbEdo.setText("");
		cmbEdo.setSelectedIndex(-1);
		cmbEdo.setValue("--Seleccione--");
		cmbMunicipio.setText("");
		cmbMunicipio.setSelectedIndex(-1);
		cmbMunicipio.setValue("--Seleccione--");
		cmbParroquia.setText("");
		cmbParroquia.setSelectedIndex(-1);
		cmbParroquia.setValue("--Seleccione--");
		cmbGrupSanguineo.setText("");
		cmbGrupSanguineo.setSelectedIndex(-1);
		cmbGrupSanguineo.setValue("--Seleccione--");
		cmbFactor.setText("");
		cmbFactor.setSelectedIndex(-1);
		cmbFactor.setValue("Seleccione--");
		cmbAlergia.setText("");
		cmbAlergia.setSelectedIndex(-1);
		cmbAlergia.setValue("--Seleccione--");
		cmbCargo.setText("");
		cmbCargo.setSelectedIndex(-1);
		cmbCargo.setValue("--Seleccione--");
		cmbTipoNomina.setText("");
		cmbTipoNomina.setSelectedIndex(-1);
		cmbTipoNomina.setValue("--Seleccione--");
		cmbTipoEmpleado.setText("");
		cmbTipoEmpleado.setSelectedIndex(-1);
		cmbTipoEmpleado.setValue("--Seleccione--");

		spNroHijos.setText("");

		parroquias = new ArrayList<DatoBasico>();
		edosCivil = new ArrayList<DatoBasico>();
		sexos = new ArrayList<DatoBasico>();
		estados = new ArrayList<DatoBasico>();
		municipios = new ArrayList<DatoBasico>();
		alergias = new ArrayList<DatoBasico>();
		GrupoSanguineo = new ArrayList<DatoBasico>();
		FactorSanguineo = new ArrayList<DatoBasico>();
		tipoEmpleados = new ArrayList<DatoBasico>();
		tipoNominas = new ArrayList<DatoBasico>();
		cargos = new ArrayList<DatoBasico>();
		codAreas = new ArrayList<DatoBasico>();
		codCels = new ArrayList<DatoBasico>();
		tipoCeds = new ArrayList<DatoBasico>();
		personalCargos = new ArrayList<PersonalCargo>();
		personalContratos = new ArrayList<PersonalContrato>();
		personalTipoNominas = new ArrayList<PersonalTipoNomina>();
		datoAcademicosP = new ArrayList<DatoAcademicoPersonal>();
		afeccionPersonales = new ArrayList<AfeccionPersonal>();
	}

	// ------------------------------------------------------------------------------------------------------
	public void onClick$btnBuscarCed() {
		
		formulario.setAttribute("padre", "PERSONAL REMUNERADO");
		Component catalogo = Executions.createComponents(
				"/Administracion/Vistas/frmCatalogoPersonasNaturales.zul",
				formulario, null);
		formulario.addEventListener("onCierreNatural", new EventListener() {
					@Override
					public void onEvent(Event arg0) throws Exception {
						persona = (Persona) formulario.getVariable("persona", false);
						
						cmbCed.setValue(persona.getCedulaRif().substring(0, 2));
						txtCed.setValue(persona.getCedulaRif().substring(2,persona.getCedulaRif().length()));
						String segundoN = "", segundoA = "";
						
						if (persona.getPersonaNatural().getSegundoNombre() != null)
							segundoN = persona.getPersonaNatural().getSegundoNombre();
						
						if (persona.getPersonaNatural().getSegundoNombre() != null)
							segundoA = persona.getPersonaNatural().getSegundoApellido();
						
						txtPrimNombre.setText(persona.getPersonaNatural().getPrimerNombre());
						txtSegNombre.setText(segundoN);
						txtPrimApellido.setText(persona.getPersonaNatural().getPrimerApellido());
						txtSegApellido.setText(segundoA);
						
						dtFechaNac.setValue(persona.getPersonaNatural().getFechaNacimiento());
						cmbGenero.setText(persona.getPersonaNatural().getDatoBasico().getNombre());
						cmbEdoCivil.setText(persona.getPersonaNatural().getPersonal().getDatoBasico().getNombre());
						spNroHijos.setValue(persona.getPersonaNatural().getPersonal().getCantidadHijos());
						
						cmbCodArea.setText(persona.getTelefonoHabitacion().substring(0, 4));
						txtTelfHab.setText(persona.getTelefonoHabitacion().substring(5, persona.getTelefonoHabitacion().length()));
						
						cmbCodOper.setValue(persona.getPersonaNatural().getCelular().substring(0, 4));
						txtTelfCel.setText(persona.getPersonaNatural().getCelular().substring(5, persona.getPersonaNatural().getCelular().length()));
						
						txtCorreo.setText(persona.getCorreoElectronico());
						txtTwitter.setText(persona.getTwitter());
						
						if (persona.getDatoBasicoByCodigoParroquia() == null) {

						} else {
							
							cmbEdo.setText(persona.getDatoBasicoByCodigoParroquia()
									.getDatoBasico().getDatoBasico().getNombre());
							cmbMunicipio.setText(persona.getDatoBasicoByCodigoParroquia()
									.getDatoBasico().getNombre());
							cmbParroquia.setText(persona.getDatoBasicoByCodigoParroquia()
									.getNombre().toString());
						}
						txtDir.setText(persona.getDireccion());
						
						String cadena;
						cadena=persona.getPersonaNatural().getPersonal().getTipoSangre().toString();					
						cmbGrupSanguineo.setText(cadena.substring(0, cadena.indexOf("-")));
						cmbFactor.setText(cadena.substring(cadena.indexOf("-")+1, cadena.length()));
						
//						afeccionPersonales = new ArrayList<AfeccionPersonal>();
//						afeccionPersonales = servicioAfeccionPersonal.listarPorCedula(persona.getCedulaRif());
//						System.out.println(afeccionPersonales.get(0).getDatoBasico().getNombre());
//						System.out.println(afeccionPersonales.get(1).getDatoBasico().getNombre());
//						for (int i = 0; i < afeccionPersonales.size(); i++) {
//							
//							colAlergias = new Listitem();
//							colAlergias.appendChild(new Listcell(afeccionPersonales.get(i).getDatoBasico().getNombre()));
//							gridAlergia.appendChild(colAlergias);	
//						}
						
						datoAcademicosP = new ArrayList<DatoAcademicoPersonal>();
						datoAcademicosP = servicioDatoAcademicoPersonal.listarPorCedula(persona.getCedulaRif());
						for (int i = 0; i < datoAcademicosP.size(); i++) {
							
							colDatAcad = new Listitem();
							colDatAcad.appendChild(new Listcell(datoAcademicosP.get(i).getInstituto().toString()));
							colDatAcad.appendChild(new Listcell(datoAcademicosP.get(i).getTitulo().toString()));
							colDatAcad.appendChild(new Listcell(datoAcademicosP.get(i).getFechaEgreso().toString()));
							gridDatoAcademicos.appendChild(colDatAcad);	
						}
						
						personalCargo=servicioPersonalCargo.buscarCargoActual(persona.getPersonaNatural().getPersonal());
						dtFechaCargo.setValue(personalCargo.getFechaInicio());
						cmbCargo.setText(personalCargo.getDatoBasico().getNombre());
						
						personalContrato=servicioPersonalContrato.buscarPorCedulaRif(persona.getCedulaRif());
						cmbTipoEmpleado.setText(personalContrato.getDatoBasicoByCodigoModalidad().getNombre());
//						personalContratos=servicioPersonalContrato.listarActivos();
//						for (int i = 0; i < personalContratos.size(); i++) {
//							if(personalContratos.get(i).getPersonal().equals(persona.getPersonaNatural().getPersonal())){
//								cmbTipoEmpleado.setText(personalContratos.get(i).getDatoBasicoByCodigoModalidad().getDatoBasico().getNombre());	
//							}	
//						}
						
						personalTipoNomina=servicioPersonalTipoNomina.buscarPorCedulaRif(persona.getCedulaRif());
						cmbTipoNomina.setText(personalTipoNomina.getDatoBasico().getNombre());
//						personalTipoNominas=servicioPersonalTipoNomina.listarActivos();
//						for (int i = 0; i < personalTipoNominas.size(); i++) {
//							if(personalTipoNominas.get(i).getPersonal().equals(persona.getPersonaNatural().getPersonal())){
//								cmbTipoNomina.setText(personalTipoNominas.get(i).getDatoBasico().getNombre());
//							}	
//						}
						
						dtFechaIngresoEsc.setValue(persona.getFechaIngreso());
						dtFechaEgresoEsc.setValue(persona.getFechaEgreso());
						
						binder.loadAll();
					}
				});
	}

	/* Get y Set de Cada Uno de Los Objetos Declarados */
	// ------------------------------------------------------------------------------------------------------
	public AfeccionPersonalId getAfeccionPersonalId() {
		return afeccionPersonalId;
	}

	public void setAfeccionPersonalId(AfeccionPersonalId afeccionPersonalId) {
		this.afeccionPersonalId = afeccionPersonalId;
	}

	public List<DatoBasico> getAlergias() {
		return alergias;
	}

	public void setAlergias(List<DatoBasico> alergias) {
		this.alergias = alergias;
	}

	public List<PersonalCargo> getPersonalCargos() {
		return personalCargos;
	}

	public void setPersonalCargos(List<PersonalCargo> personalCargos) {
		this.personalCargos = personalCargos;
	}

	public List<PersonalContrato> getPersonalContratos() {
		return personalContratos;
	}

	public void setPersonalContratos(List<PersonalContrato> personalContratos) {
		this.personalContratos = personalContratos;
	}

	public List<PersonalTipoNomina> getPersonalTipoNominas() {
		return personalTipoNominas;
	}

	public void setPersonalTipoNominas(
			List<PersonalTipoNomina> personalTipoNominas) {
		this.personalTipoNominas = personalTipoNominas;
	}

	public List<AfeccionPersonal> getAfeccionPersonales() {
		return afeccionPersonales;
	}

	public void setAfeccionPersonales(List<AfeccionPersonal> afeccionPersonales) {
		this.afeccionPersonales = afeccionPersonales;
	}

	public DatoBasico getDatoBasico() {
		return datoBasico;
	}

	public void setDatoBasico(DatoBasico datoBasico) {
		this.datoBasico = datoBasico;
	}

	public TipoDato getTipoDato() {
		return tipoDato;
	}

	public void setTipoDato(TipoDato tipoDato) {
		this.tipoDato = tipoDato;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public PersonaNatural getPersonaNatural() {
		return personaNatural;
	}

	public void setPersonaNatural(PersonaNatural personaNatural) {
		this.personaNatural = personaNatural;
	}

	public Personal getPersonal() {
		return personal;
	}

	public void setPersonal(Personal personal) {
		this.personal = personal;
	}

	public PersonalContrato getPersonalContrato() {
		return personalContrato;
	}

	public void setPersonalContrato(PersonalContrato personalContrato) {
		this.personalContrato = personalContrato;
	}

	public PersonalTipoNomina getPersonalTipoNomina() {
		return personalTipoNomina;
	}

	public void setPersonalTipoNomina(PersonalTipoNomina personalTipoNomina) {
		this.personalTipoNomina = personalTipoNomina;
	}

	public DatoAcademicoPersonal getDatoAcademicoPersonal() {
		return datoAcademicoPersonal;
	}

	public void setDatoAcademicoPersonal(
			DatoAcademicoPersonal datoAcademicoPersonal) {
		this.datoAcademicoPersonal = datoAcademicoPersonal;
	}

	public AfeccionPersonal getAfeccionPersonal() {
		return afeccionPersonal;
	}

	public void setAfeccionPersonal(AfeccionPersonal afeccionPersonal) {
		this.afeccionPersonal = afeccionPersonal;
	}

	public PersonalConceptoNomina getPersonalConceptoNomina() {
		return personalConceptoNomina;
	}

	public void setPersonalConceptoNomina(
			PersonalConceptoNomina personalConceptoNomina) {
		this.personalConceptoNomina = personalConceptoNomina;
	}

	public ConceptoNomina getConceptoNomina() {
		return conceptoNomina;
	}

	public void setConceptoNomina(ConceptoNomina conceptoNomina) {
		this.conceptoNomina = conceptoNomina;
	}

	public PersonalCargo getPersonalCargo() {
		return personalCargo;
	}

	public void setPersonalCargo(PersonalCargo personalCargo) {
		this.personalCargo = personalCargo;
	}

	public List<DatoBasico> getParroquias() {
		return parroquias;
	}

	public void setParroquias(List<DatoBasico> parroquias) {
		this.parroquias = parroquias;
	}

	public List<DatoBasico> getEstados() {
		return estados;
	}

	public void setEstados(List<DatoBasico> estados) {
		this.estados = estados;
	}

	public List<DatoBasico> getMunicipios() {
		return municipios;
	}

	public void setMunicipios(List<DatoBasico> municipios) {
		this.municipios = municipios;
	}
	// ------------------------------------------------------------------------------------------------------
	
	public AnnotateDataBinder getBinder() {
		return binder;
	}

	public void setBinder(AnnotateDataBinder binder) {
		this.binder = binder;
	}

	public Component getFormulario() {
		return formulario;
	}

	public void setFormulario(Component formulario) {
		this.formulario = formulario;
	}
	public Button getBtnBuscarCed() {
		return btnBuscarCed;
	}

	public void setBtnBuscarCed(Button btnBuscarCed) {
		this.btnBuscarCed = btnBuscarCed;
	}
}