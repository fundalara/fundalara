package controlador.administracion;

import org.zkoss.image.AImage;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import modelo.AfeccionPersonal;
//import modelo.AfeccionPersonalId;
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

import org.zkoss.zul.Image;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Button;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Doublebox;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Spinner;
import org.zkoss.zul.Textbox;

import comun.FileLoader;

import bsh.ParseException;

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
	List<DatoBasico> horario = new ArrayList<DatoBasico>();
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
			cmbAlergia, cmbCargo, cmbHoraTrab, cmbHorario, cmbAsignacion,
			cmbDeduccion;

	/* Instancias de los datebox a Utilizar */

	Datebox dtFechaNac, dtFechaEgreso, dtFechaCargo, dtFechaIngresoEsc,
			dtFechaEgresoEsc;

	/* Instancias de los botones a Utilizar */

	Button btnGuardar, btnModificar, btnCancelar, btnSalir, btnNvaAlergia,
			btnEliminar, btnNvoCargo, btnNvoTipoNomina, btnNvoHoraTrab,
			btnNvoTipoEmpleado, btnHistorial, btnAgregarAlerg, btnQuitarAlerg,
			btnQuitarDatAcad, btnAgregarDatAcad, btnBuscarCed, btnFoto;

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
	Textbox txtObsAlergias;

	/* Instancias de los Spinner a Utilizar */

	Spinner spNroHijos;

	Image imgPersonal;

	/* Instancias de los Grid a Utilizar */
	Listbox gridAlergia;
	Listitem colAlergias;
	Listbox gridDatoAcademicos;
	Listitem colDatAcad;

	AnnotateDataBinder binder;
	Component formulario, formularioRegPers;

	byte[] foto;

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
		this.llenarcmbHorario();
		// this.llenarCmbTipoNomina();
		this.llenarCmbCargo();
		this.llenarCmbCodArea();
		this.llenarCmbCodCel();
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
		tipoDato = servicioTipoDato.buscarTipo("CODIGO AREA");
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
		tipoDato = servicioTipoDato.buscarTipo("CODIGO CELULAR");
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
		tipoDato = servicioTipoDato.buscarTipo("ESTADO");
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
		tipoDato = servicioTipoDato.buscarTipo("GRUPO SANGUINEO");
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
		tipoDato = servicioTipoDato.buscarTipo("FACTOR SANGUINEO");
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
	public void llenarcmbHorario() {
		cmbHorario.getItems().clear();
		tipoDato = servicioTipoDato.buscarTipo("HORARIO DE TRABAJO");
		horario = servicioDatoBasico.buscarPorTipoDato(tipoDato);

		if (horario.equals(null)) {
			alert("No se encontro");
		} else {
			for (int i = 0; i < horario.size(); i++) {
				Comboitem item = new Comboitem();

				item.setLabel(horario.get(i).getNombre().toString());
				item.setValue(horario.get(i).getCodigoDatoBasico());

				cmbHorario.appendChild(item);
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
			colAlergias.appendChild(new Listcell(this.txtObsAlergias.getValue()
					.toString()));
			gridAlergia.appendChild(colAlergias);
		} else {

			int i = 0;
			do {
				colAlergias = gridAlergia.getItemAtIndex(i);
				Listcell celda0 = (Listcell) colAlergias.getChildren().get(0);
				Listcell celda1 = (Listcell) colAlergias.getChildren().get(1);
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
			colAlergias.appendChild(new Listcell(this.txtObsAlergias.getValue()
					.toString().toUpperCase()));
			gridAlergia.appendChild(colAlergias);
		}

		cmbAlergia.setValue("--Seleccione--");
		txtObsAlergias.setValue(null);
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
					.toString().toUpperCase()));
			colDatAcad.appendChild(new Listcell(this.txtTituloObt.getValue()
					.toString().toUpperCase()));
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
				if (this.txtInstituto.getValue().toString().toUpperCase()
						.equals(celda0.getLabel())
						&& this.txtTituloObt.getValue().toString()
								.toUpperCase().equals(celda1.getLabel())
						&& this.dtFechaEgreso.getValue().toString()
								.equals(celda2.getLabel())) {

					alert("Esta Alergia ya ha sido incluida.");
					return;
				}
				i++;
			} while (i < gridDatoAcademicos.getItemCount());

			colDatAcad = new Listitem();
			colDatAcad.appendChild(new Listcell(this.txtInstituto.getValue()
					.toString().toUpperCase()));
			colDatAcad.appendChild(new Listcell(this.txtTituloObt.getValue()
					.toString().toUpperCase()));
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

	public void onClick$btnFoto() {
		FileLoader fl = new FileLoader();
		foto = fl.cargarImagenEnBean(imgPersonal);
		System.out.print(foto);
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

		persona = new Persona();
		persona.setCedulaRif(cedula);
		persona.setFechaIngreso(dtFechaIngresoEsc.getValue());
		persona.setDireccion(txtDir.getValue().toUpperCase());
		persona.setTelefonoHabitacion(telefono);
		persona.setCorreoElectronico(txtCorreo.getValue().toUpperCase());
		persona.setTwitter(txtTwitter.getValue().toUpperCase());
		datoBasico = servicioDatoBasico.buscarPorString(cmbParroquia
				.getSelectedItem().getLabel());
		persona.setDatoBasicoByCodigoParroquia(datoBasico);
		datoBasico = servicioDatoBasico.buscarPorString("PERSONAL REMUNERADO");
		persona.setDatoBasicoByCodigoTipoPersona(datoBasico);
		persona.setEstatus('A');
		servicioPersona.agregar(persona);

		personaNatural = new PersonaNatural();
		personaNatural.setPersona(persona);
		personaNatural.setCelular(celular);
		personaNatural.setPrimerNombre(txtPrimNombre.getValue().toUpperCase());
		personaNatural.setSegundoNombre(txtSegNombre.getValue().toUpperCase());
		personaNatural.setPrimerApellido(txtPrimApellido.getValue()
				.toUpperCase());
		personaNatural.setSegundoApellido(txtSegApellido.getValue()
				.toUpperCase());

		if (cmbGenero.getValue().equals("MASCULINO")) {
			personaNatural.setDatoBasico(servicioDatoBasico
					.buscarPorString("MASCULINO"));
		} else if (cmbGenero.getValue().equals("FEMENINO")) {
			personaNatural.setDatoBasico(servicioDatoBasico
					.buscarPorString("FEMENINO"));
		}
		personaNatural.setFechaNacimiento(dtFechaNac.getValue());
		if (foto != null) {
			personaNatural.setFoto(foto);
			System.out.print("entro");
		}
		personaNatural.setEstatus('A');
		personaNatural.setFoto(null);
		servicioPersonaNatural.agregar(personaNatural);

		personal = new Personal();
		personal.setPersonaNatural(personaNatural);
		personal.setCantidadHijos(spNroHijos.getValue());
		personal.setEstatus('A');
		personal.setDatoBasico(servicioDatoBasico.buscarPorString(cmbEdoCivil
				.getSelectedItem().getLabel()));

		String tipoSangre = cmbGrupSanguineo.getValue() + "-"
				+ cmbFactor.getValue();
		personal.setTipoSangre(tipoSangre);
		servicioPersonal.agregar(personal);

		personalCargo = new PersonalCargo();
		personalCargos = servicioPersonalCargo.listarActivos();
		n = personalCargos.size();
		n++;
		personalCargo.setCodigoPersonalCargo(n);
		datoBasico = servicioDatoBasico.buscarPorString(cmbCargo
				.getSelectedItem().getLabel());
		personalCargo.setDatoBasico(datoBasico);
		personalCargo.setPersonal(personal);
		personalCargo.setFechaFin(dtFechaEgresoEsc.getValue());
		personalCargo.setFechaInicio(dtFechaCargo.getValue());
		personalCargo.setEstatus('A');
		servicioPersonalCargo.agregar(personalCargo);

		personalContrato = new PersonalContrato();
		personalContratos = servicioPersonalContrato.listar();
		n = personalContratos.size();
		n++;
		personalContrato.setCodigoPersonalContrato(n);
		personalContrato.setFechaInicio(dtFechaIngresoEsc.getValue());
		personalContrato.setPersonal(personal);
		personalContrato.setFechaFin(dtFechaEgresoEsc.getValue());
		personalContrato.setDatoBasicoByCodigoModalidad(servicioDatoBasico
				.buscarPorString("TIEMPO COMPLETO")); // OJO CUAL ES LA
														// MODALIDAD DEL
														// CONTRATO?
		personalContrato.setDatoBasicoByCodigoHorario(servicioDatoBasico
				.buscarPorString(cmbHorario.getText().toString()));
		personalContrato.setEstatus('A');
		servicioPersonalContrato.agregar(personalContrato);

		tipoDato = servicioTipoDato.buscarTipo("AFECCION");
		alergias = servicioDatoBasico.buscarPorTipoDato(tipoDato);

		afeccionPersonal = new AfeccionPersonal();
		binder.loadComponent(gridAlergia);
		for (int i = 0; i < gridAlergia.getItemCount(); i++) {

			System.out.print("esta entrando al for");

			colAlergias = gridAlergia.getItemAtIndex(i);
			Listcell celda1 = (Listcell) colAlergias.getChildren().get(0);
			Listcell celda2 = (Listcell) colAlergias.getChildren().get(1);

			afeccionPersonal.setCodigoAfeccionPersonal(servicioAfeccionPersonal
					.listar().size() + 1);
			afeccionPersonal.setPersonal(personal);
			afeccionPersonal.setDatoBasico(servicioDatoBasico
					.buscarPorString(celda1.getLabel().toString()));
			afeccionPersonal.setObservacion(celda2.getLabel().toString());
			afeccionPersonal.setEstatus('A');
			servicioAfeccionPersonal.agregar(afeccionPersonal);
		}

		/*
		 * datoAcademicosP = servicioDatoAcademicoPersonal.listarActivos(); n =
		 * datoAcademicosP.size(); n++;
		 */
		binder.loadComponent(gridDatoAcademicos);
		for (int i = 0; i < gridDatoAcademicos.getItemCount(); i++) {

			colDatAcad = gridDatoAcademicos.getItemAtIndex(i);
			Listcell celda1 = (Listcell) colDatAcad.getChildren().get(0);
			Listcell celda2 = (Listcell) colDatAcad.getChildren().get(1);
			Listcell celda3 = (Listcell) colDatAcad.getChildren().get(2);

			Date fecha = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			fecha.parse("" + fecha.getDate() + "/" + (fecha.getMonth() + 1)
					+ "/" + (fecha.getYear() + 1900));
			System.out.println(fecha.toString());
			datoAcademicoPersonal
					.setCodigoDatoAcademico(servicioDatoAcademicoPersonal
							.listar().size() + 1);
			datoAcademicoPersonal.setPersonal(personal);
			datoAcademicoPersonal.setEstatus('A');

			datoAcademicoPersonal.setInstituto(celda1.getLabel().toString()
					.toUpperCase());
			datoAcademicoPersonal.setTitulo(celda2.getLabel().toString()
					.toUpperCase());
			datoAcademicoPersonal.setFechaEgreso(fecha);

			servicioDatoAcademicoPersonal.agregar(datoAcademicoPersonal);

		}

		/*
		 * persona = new Persona(); personaNatural = new PersonaNatural();
		 * 
		 * personal = new Personal();
		 * 
		 * personalCargo = new PersonalCargo(); personalContrato = new
		 * PersonalContrato(); personalTipoNomina = new PersonalTipoNomina();
		 * afeccionPersonal = new AfeccionPersonal(); datoAcademicoPersonal =
		 * new DatoAcademicoPersonal();
		 */
		limpiarFormulario();
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
		cmbHorario.setText("");
		cmbHorario.setSelectedIndex(-1);
		cmbHorario.setValue("--Seleccione--");

		spNroHijos.setValue(0);

		parroquias = new ArrayList<DatoBasico>();
		edosCivil = new ArrayList<DatoBasico>();
		sexos = new ArrayList<DatoBasico>();
		estados = new ArrayList<DatoBasico>();
		municipios = new ArrayList<DatoBasico>();
		alergias = new ArrayList<DatoBasico>();
		GrupoSanguineo = new ArrayList<DatoBasico>();
		FactorSanguineo = new ArrayList<DatoBasico>();
		tipoEmpleados = new ArrayList<DatoBasico>();
		horario = new ArrayList<DatoBasico>();
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

		Map params = new HashMap();
		params.put("padre", "PERSONAL REMUNERADO");
		Executions.createComponents(
				"/Administracion/Vistas/frmCatalogoPersonasNaturales.zul",
				null, params);
		formulario.addEventListener("onCierreNatural", new EventListener() {
			@Override
			public void onEvent(Event arg0) throws Exception {

				persona = (Persona) formulario.getVariable("persona", false);

				cmbCed.setValue(persona.getCedulaRif().substring(0, 2));
				txtCed.setValue(persona.getCedulaRif().substring(2,
						persona.getCedulaRif().length()));
				String segundoN = "", segundoA = "";

				if (persona.getPersonaNatural().getSegundoNombre() != null)
					segundoN = persona.getPersonaNatural().getSegundoNombre();

				if (persona.getPersonaNatural().getSegundoNombre() != null)
					segundoA = persona.getPersonaNatural().getSegundoApellido();

				txtPrimNombre.setText(persona.getPersonaNatural()
						.getPrimerNombre());
				txtSegNombre.setText(segundoN);
				txtPrimApellido.setText(persona.getPersonaNatural()
						.getPrimerApellido());
				txtSegApellido.setText(segundoA);

				dtFechaNac.setValue(persona.getPersonaNatural()
						.getFechaNacimiento());
				cmbGenero.setText(persona.getPersonaNatural().getDatoBasico()
						.getNombre());
				cmbEdoCivil.setText(persona.getPersonaNatural().getPersonal()
						.getDatoBasico().getNombre());
				spNroHijos.setValue(persona.getPersonaNatural().getPersonal()
						.getCantidadHijos());
				foto = persona.getPersonaNatural().getFoto();
				if (foto != null) {
					try {
						AImage aImage = new AImage("foto.jpg", foto);
						imgPersonal.setContent(aImage);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

				if (persona.getTelefonoHabitacion() == null) {
					cmbCodArea.setValue("-");
					txtTelfHab.setValue(null);
				} else {
					cmbCodArea.setText(persona.getTelefonoHabitacion()
							.substring(0, 4));
					txtTelfHab.setText(persona.getTelefonoHabitacion()
							.substring(5,
									persona.getTelefonoHabitacion().length()));
				}

				if (persona.getPersonaNatural().getCelular() == null) {
					cmbCodOper.setValue("-");
					txtTelfCel.setValue(null);
				} else {
					cmbCodOper.setValue(persona.getPersonaNatural()
							.getCelular().substring(0, 4));
					txtTelfCel.setText(persona
							.getPersonaNatural()
							.getCelular()
							.substring(
									5,
									persona.getPersonaNatural().getCelular()
											.length()));
				}

				if (persona.getCorreoElectronico() == null) {
					txtCorreo.setValue(null);
				} else {
					txtCorreo.setText(persona.getCorreoElectronico());
				}

				if (persona.getTwitter() == null) {
					txtTwitter.setValue(null);
				} else {
					txtTwitter.setText(persona.getTwitter());
				}

				if (persona.getDatoBasicoByCodigoParroquia() != null) {

					cmbEdo.setText(persona.getDatoBasicoByCodigoParroquia()
							.getDatoBasico().getDatoBasico().getNombre());
					cmbMunicipio.setText(persona
							.getDatoBasicoByCodigoParroquia().getDatoBasico()
							.getNombre());
					cmbParroquia.setText(persona
							.getDatoBasicoByCodigoParroquia().getNombre()
							.toString());
				}
				txtDir.setText(persona.getDireccion());

				String cadena;
				cadena = persona.getPersonaNatural().getPersonal()
						.getTipoSangre().toString();
				if (cadena == null) {
					cmbGrupSanguineo.setValue("--Seleccione--");
					cmbFactor.setValue("--Seleccione--");
				} else {
					cmbGrupSanguineo.setText(cadena.substring(0,
							cadena.indexOf("-")));
					cmbFactor.setText(cadena.substring(cadena.indexOf("-") + 1,
							cadena.length()));
				}

				afeccionPersonales = new ArrayList<AfeccionPersonal>();
				afeccionPersonales = servicioAfeccionPersonal
						.listarPorCedula(persona.getCedulaRif());

				for (int i = 0; i < afeccionPersonales.size(); i++) {

					colAlergias = new Listitem();
					colAlergias.appendChild(new Listcell(afeccionPersonales
							.get(i).getDatoBasico().getNombre().toString()));
					colAlergias.appendChild(new Listcell(afeccionPersonales
							.get(i).getObservacion().toString()));
					gridAlergia.appendChild(colAlergias);
				}

				datoAcademicosP = new ArrayList<DatoAcademicoPersonal>();
				datoAcademicosP = servicioDatoAcademicoPersonal
						.listarPorCedula(persona.getCedulaRif());

				for (int i = 0; i < datoAcademicosP.size(); i++) {

					colDatAcad = new Listitem();
					colDatAcad.appendChild(new Listcell(datoAcademicosP.get(i)
							.getInstituto().toString()));
					colDatAcad.appendChild(new Listcell(datoAcademicosP.get(i)
							.getTitulo().toString()));
					colDatAcad.appendChild(new Listcell(datoAcademicosP.get(i)
							.getFechaEgreso().toString()));
					gridDatoAcademicos.appendChild(colDatAcad);
				}

				personalCargo = servicioPersonalCargo.buscarCargoActual(persona
						.getPersonaNatural().getPersonal());
				dtFechaCargo.setValue(personalCargo.getFechaInicio());
				cmbCargo.setText(personalCargo.getDatoBasico().getNombre());

				personalContrato = servicioPersonalContrato
						.buscarPorCedulaRif(persona.getCedulaRif());
				cmbHorario.setText(personalContrato
						.getDatoBasicoByCodigoHorario().getNombre());

				dtFechaIngresoEsc.setValue(persona.getFechaIngreso());
				dtFechaEgresoEsc.setValue(persona.getFechaEgreso());

				binder.loadAll();

				// Inhabilitar();
				txtTelfHab.setDisabled(false);
				txtTelfCel.setDisabled(false);
				txtCorreo.setDisabled(false);
				txtTwitter.setDisabled(false);
				txtDir.setDisabled(false);
				txtInstituto.setDisabled(false);
				txtTituloObt.setDisabled(false);

				dtFechaEgreso.setDisabled(false);
				dtFechaCargo.setDisabled(false);

				cmbAlergia.setDisabled(false);

				cmbEdoCivil.setDisabled(false);
				cmbCodArea.setDisabled(false);
				cmbCodOper.setDisabled(false);
				cmbEdo.setDisabled(false);
				cmbMunicipio.setDisabled(false);
				cmbParroquia.setDisabled(false);
				cmbAlergia.setDisabled(false);
				cmbCargo.setDisabled(false);
				cmbHorario.setDisabled(false);

				spNroHijos.setDisabled(false);

				btnGuardar.setDisabled(true);
				btnModificar.setDisabled(false);
				btnCancelar.setDisabled(false);
				btnSalir.setDisabled(false);
				btnNvaAlergia.setDisabled(false);
				btnEliminar.setDisabled(false);
				btnAgregarAlerg.setDisabled(false);
				btnAgregarDatAcad.setDisabled(false);

			}
		});
	}

	// ------------------------------------------------------------------------------------------------------
	public void Inhabilitar() {

		txtCed.setDisabled(true);
		txtPrimNombre.setDisabled(true);
		txtSegNombre.setDisabled(true);
		txtPrimApellido.setDisabled(true);
		txtSegApellido.setDisabled(true);
		txtTelfHab.setDisabled(true);
		txtTelfCel.setDisabled(true);
		txtCorreo.setDisabled(true);
		txtTwitter.setDisabled(true);
		txtDir.setDisabled(true);
		txtInstituto.setDisabled(true);
		txtTituloObt.setDisabled(true);

		dtFechaNac.setDisabled(true);
		dtFechaEgreso.setDisabled(true);
		dtFechaCargo.setDisabled(true);
		dtFechaIngresoEsc.setDisabled(true);
		dtFechaEgresoEsc.setDisabled(true);

		gridAlergia.setDisabled(true);
		gridDatoAcademicos.setDisabled(true);

		cmbCed.setDisabled(true);
		cmbGenero.setDisabled(true);
		cmbEdoCivil.setDisabled(true);
		cmbCodArea.setDisabled(true);
		cmbCodOper.setDisabled(true);
		/*
		 * cmbEdo.setDisabled(true); cmbMunicipio.setDisabled(true);
		 * cmbParroquia.setDisabled(true);
		 */
		cmbGrupSanguineo.setDisabled(true);
		cmbFactor.setDisabled(true);
		cmbAlergia.setDisabled(true);
		cmbCargo.setDisabled(true);
		cmbHorario.setDisabled(true);

		spNroHijos.setDisabled(true);

		btnGuardar.setDisabled(true);
		btnModificar.setDisabled(true);
		btnCancelar.setDisabled(true);
		btnSalir.setDisabled(true);
		btnNvaAlergia.setDisabled(true);
		btnEliminar.setDisabled(true);
		// btnNvoTipoEmpleado.setDisabled(true);
		btnAgregarAlerg.setDisabled(true);
		btnQuitarAlerg.setDisabled(true);
		btnQuitarDatAcad.setDisabled(true);
		btnAgregarDatAcad.setDisabled(true);
		btnBuscarCed.setDisabled(true);

	}

	// ------------------------------------------------------------------------------------------------------
	public void onClick$btnModificar() {

		Integer n = 0;
		String cedula = cmbCed.getValue().toString()
				+ txtCed.getValue().toString();
		String telefono = cmbCodArea.getValue().toString()
				+ txtTelfHab.getValue().toString();
		String celular = cmbCodOper.getValue().toString()
				+ txtTelfCel.getValue().toString();

		persona = new Persona();
		persona.setCedulaRif(cedula);
		persona.setFechaIngreso(dtFechaIngresoEsc.getValue());
		persona.setDireccion(txtDir.getValue().toUpperCase());
		persona.setTelefonoHabitacion(telefono);
		persona.setCorreoElectronico(txtCorreo.getValue().toUpperCase());
		persona.setTwitter(txtTwitter.getValue().toUpperCase());
		datoBasico = servicioDatoBasico.buscarPorString(cmbParroquia.getText());
		persona.setDatoBasicoByCodigoParroquia(datoBasico);
		datoBasico = servicioDatoBasico.buscarPorString("PERSONAL REMUNERADO");
		persona.setDatoBasicoByCodigoTipoPersona(datoBasico);
		persona.setEstatus('A');
		servicioPersona.actualizar(persona);

		personaNatural = new PersonaNatural();
		personaNatural.setPersona(persona);
		personaNatural.setCelular(celular);
		personaNatural.setPrimerNombre(txtPrimNombre.getValue().toUpperCase());
		personaNatural.setSegundoNombre(txtSegNombre.getValue().toUpperCase());
		personaNatural.setPrimerApellido(txtPrimApellido.getValue()
				.toUpperCase());
		personaNatural.setSegundoApellido(txtSegApellido.getValue()
				.toUpperCase());

		if (cmbGenero.getValue().equals("MASCULINO")) {
			personaNatural.setDatoBasico(servicioDatoBasico
					.buscarPorString("MASCULINO"));
		} else if (cmbGenero.getValue().equals("FEMENINO")) {
			personaNatural.setDatoBasico(servicioDatoBasico
					.buscarPorString("FEMENINO"));
		}
		personaNatural.setFechaNacimiento(dtFechaNac.getValue());
		if (foto != null) {
			personaNatural.setFoto(foto);
			System.out.print("entro");
		}
		personaNatural.setEstatus('A');
		personaNatural.setFoto(null);
		servicioPersonaNatural.agregar(personaNatural);

		personal = new Personal();
		personal.setPersonaNatural(personaNatural);
		personal.setCantidadHijos(spNroHijos.getValue());
		personal.setEstatus('A');
		personal.setDatoBasico(servicioDatoBasico.buscarPorString(cmbEdoCivil
				.getSelectedItem().getLabel()));

		String tipoSangre = cmbGrupSanguineo.getValue() + "-"
				+ cmbFactor.getValue();
		personal.setTipoSangre(tipoSangre);
		servicioPersonal.agregar(personal);

		personalCargo = new PersonalCargo();
		personalCargos = servicioPersonalCargo.listarActivos();
		n = personalCargos.size();
		n++;
		personalCargo.setCodigoPersonalCargo(n);
		datoBasico = servicioDatoBasico.buscarPorString(cmbCargo
				.getSelectedItem().getLabel());
		personalCargo.setDatoBasico(datoBasico);
		personalCargo.setPersonal(personal);
		personalCargo.setFechaFin(dtFechaEgresoEsc.getValue());
		personalCargo.setFechaInicio(dtFechaCargo.getValue());
		personalCargo.setEstatus('A');
		servicioPersonalCargo.agregar(personalCargo);

		personalContrato = new PersonalContrato();
		personalContratos = servicioPersonalContrato.listar();
		n = personalContratos.size();
		n++;
		personalContrato.setCodigoPersonalContrato(n);
		personalContrato.setFechaInicio(dtFechaIngresoEsc.getValue());
		personalContrato.setPersonal(personal);
		personalContrato.setFechaFin(dtFechaEgresoEsc.getValue());
		personalContrato.setDatoBasicoByCodigoModalidad(servicioDatoBasico
				.buscarPorString("TIEMPO COMPLETO")); // OJO CUAL ES LA
														// MODALIDAD DEL
														// CONTRATO?
		personalContrato.setDatoBasicoByCodigoHorario(servicioDatoBasico
				.buscarPorString(cmbHorario.getText().toString()));
		personalContrato.setEstatus('A');
		servicioPersonalContrato.agregar(personalContrato);

		tipoDato = servicioTipoDato.buscarTipo("AFECCION");
		alergias = servicioDatoBasico.buscarPorTipoDato(tipoDato);

		afeccionPersonal = new AfeccionPersonal();
		binder.loadComponent(gridAlergia);
		for (int i = 0; i < gridAlergia.getItemCount(); i++) {

			System.out.print("esta entrando al for");

			colAlergias = gridAlergia.getItemAtIndex(i);
			Listcell celda1 = (Listcell) colAlergias.getChildren().get(0);
			Listcell celda2 = (Listcell) colAlergias.getChildren().get(1);

			afeccionPersonal.setCodigoAfeccionPersonal(servicioAfeccionPersonal
					.listar().size() + 1);
			afeccionPersonal.setPersonal(personal);
			afeccionPersonal.setDatoBasico(servicioDatoBasico
					.buscarPorString(celda1.getLabel().toString()));
			afeccionPersonal.setObservacion(celda2.getLabel().toString());
			afeccionPersonal.setEstatus('A');
			servicioAfeccionPersonal.agregar(afeccionPersonal);
		}

		/*
		 * datoAcademicosP = servicioDatoAcademicoPersonal.listarActivos(); n =
		 * datoAcademicosP.size(); n++;
		 */
		binder.loadComponent(gridDatoAcademicos);
		for (int i = 0; i < gridDatoAcademicos.getItemCount(); i++) {

			colDatAcad = gridDatoAcademicos.getItemAtIndex(i);
			Listcell celda1 = (Listcell) colDatAcad.getChildren().get(0);
			Listcell celda2 = (Listcell) colDatAcad.getChildren().get(1);
			Listcell celda3 = (Listcell) colDatAcad.getChildren().get(2);

			Date fecha = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			fecha.parse("" + fecha.getDate() + "/" + (fecha.getMonth() + 1)
					+ "/" + (fecha.getYear() + 1900));
			System.out.println(fecha.toString());
			datoAcademicoPersonal
					.setCodigoDatoAcademico(servicioDatoAcademicoPersonal
							.listar().size() + 1);
			datoAcademicoPersonal.setPersonal(personal);
			datoAcademicoPersonal.setEstatus('A');

			datoAcademicoPersonal.setInstituto(celda1.getLabel().toString()
					.toUpperCase());
			datoAcademicoPersonal.setTitulo(celda2.getLabel().toString()
					.toUpperCase());
			datoAcademicoPersonal.setFechaEgreso(fecha);

			servicioDatoAcademicoPersonal.agregar(datoAcademicoPersonal);

		}
		
		limpiarFormulario();
		alert("Registro modificado exitosamente");

	}

	/*
	 * *
	 * ------------------------------------------------------------------------
	 * ------------------------------ // public void onClick$btnEliminar() { //
	 * try { // Integer qs = Messagebox.show("¿Desea eliminar los datos?", //
	 * "Importante", Messagebox.OK | Messagebox.CANCEL, // Messagebox.QUESTION);
	 * // if (qs.equals(1)) { // //
	 * System.out.println("Persona"+persona.getCedulaRif()); //
	 * persona.setEstatus('E'); // servicioPersona.actualizar(persona); // //
	 * personaNatural=new PersonaNatural(); //
	 * personaNatural=servicioPersonaNatural
	 * .buscarPersonaNatural(persona.getCedulaRif()); //
	 * System.out.println("Persona Natural"+personaNatural.getCedulaRif()); //
	 * if(personaNatural.getCedulaRif().equals(persona)){ //
	 * personaNatural.setEstatus('E'); //
	 * servicioPersonaNatural.actualizar(personaNatural); // } // //
	 * personal=new Personal(); //
	 * personal=servicioPersonal.buscarPorCedulaRif(persona.getCedulaRif()); //
	 * System.out.println("Personal"+personal.getCedulaRif()); //
	 * if(personal.getCedulaRif().equals(persona)){ // personal.setEstatus('E');
	 * // servicioPersonal.actualizar(personal); // } // // personalCargo=new
	 * PersonalCargo(); //
	 * personalCargo=servicioPersonalCargo.buscarPorCedulaRif
	 * (persona.getCedulaRif()); //
	 * System.out.println("Personal Cargo"+personalCargo
	 * .getPersonal().getCedulaRif()); //
	 * if(personalCargo.getPersonal().getCedulaRif().equals(persona)){ //
	 * personalCargo.setEstatus('E'); //
	 * servicioPersonalCargo.actualizar(personalCargo); // } // //
	 * personalContrato=new PersonalContrato(); //
	 * personalContrato=servicioPersonalContrato
	 * .buscarPorCedulaRif(persona.getCedulaRif()); //
	 * System.out.println("Personal Contrato"
	 * +personalContrato.getPersonal().getCedulaRif()); //
	 * if(personalContrato.getPersonal().getCedulaRif().equals(persona)){ //
	 * personalContrato.setEstatus('E'); //
	 * servicioPersonalContrato.actualizar(personalContrato); // } // //
	 * personalTipoNomina=new PersonalTipoNomina(); //
	 * personalTipoNomina=servicioPersonalTipoNomina
	 * .buscarPorCedulaRif(persona.getCedulaRif()); //
	 * if(personalTipoNomina.getPersonal().getCedulaRif().equals(persona)){ //
	 * personalTipoNomina.setEstatus('E'); //
	 * servicioPersonalTipoNomina.actualizar(personalTipoNomina); // } // // //
	 * afeccionPersonales
	 * =servicioAfeccionPersonal.listarPorCedula(persona.getCedulaRif()); // for
	 * (int i = 0; i < afeccionPersonales.size(); i++) { //
	 * afeccionPersonales.get(i).setEstatus('E'); //
	 * servicioAfeccionPersonal.eliminar(afeccionPersonales.get(i)); // // } //
	 * datoAcademicosP
	 * =servicioDatoAcademicoPersonal.listarPorCedula(persona.getCedulaRif());
	 * // for (int i = 0; i < datoAcademicosP.size(); i++) { //
	 * datoAcademicosP.get(i).setEstatus('E'); //
	 * servicioDatoAcademicoPersonal.eliminar(datoAcademicosP.get(i)); // // }
	 * // limpiarFormulario(); // binder.loadAll(); // alert("Eliminado"); // }
	 * // } catch (InterruptedException e) { // e.printStackTrace(); // } // //
	 * }
	 * 
	 * /* Get y Set de Cada Uno de Los Objetos Declarados
	 */
	// ------------------------------------------------------------------------------------------------------
	/*
	 * public AfeccionPersonalId getAfeccionPersonalId() { return
	 * afeccionPersonalId; }
	 * 
	 * public void setAfeccionPersonalId(AfeccionPersonalId afeccionPersonalId)
	 * { this.afeccionPersonalId = afeccionPersonalId; }
	 */

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